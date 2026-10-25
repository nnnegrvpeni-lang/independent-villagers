package net.smartcolonies.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.Brain.Profile;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus;
import net.smartcolonies.r.Colony;
import net.smartcolonies.r.ColonyMathUtils;
import net.smartcolonies.r.ColonyNavigationHelper;
import net.smartcolonies.r.ElderTradeService;
import net.smartcolonies.r.ColonySimulationService;
import net.smartcolonies.r.ColonySensorTypes;
import net.smartcolonies.r.RancherAnimalTask;
import net.smartcolonies.r.ColonyRole;
import net.smartcolonies.r.BuilderVillageWallTask;
import net.smartcolonies.r.ConstructionJob;
import net.smartcolonies.r.BuilderConstructionTask;
import net.smartcolonies.r.VillagerDeliverToStorageTask;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.ColonyChatFeedback;
import net.smartcolonies.r.BuilderRepairTask;
import net.smartcolonies.r.VillagerStuckHelper;
import net.smartcolonies.r.VillagerRaidShelterTask;
import net.smartcolonies.r.RaidDetectionHelper;
import net.smartcolonies.r.MinerExpeditionTask;
import net.smartcolonies.r.BuilderRoadTask;
import net.smartcolonies.r.LumberjackTask;
import net.smartcolonies.r.ColonyRuntimeCaches;
import net.smartcolonies.r.DeedItemHandler;
import net.smartcolonies.r.ColonyMathHelper;
import net.smartcolonies.r.VillagerDepositLootTask;
import net.smartcolonies.r.FishermanTask;
import net.smartcolonies.r.ResourceDeliveryService;
import net.smartcolonies.r.BuilderStarterChestTask;
import net.smartcolonies.r.ColonyActivities;
import net.smartcolonies.r.FarmerFieldTask;
import net.smartcolonies.r.DeedPropertyData;
import net.smartcolonies.r.ColonyMemoryModules;
import net.smartcolonies.r.MilitiaCombatTask;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({VillagerEntity.class})
public abstract class x12eywdeqyc6hd {
   @Shadow
   @Final
   private static ImmutableList<MemoryModuleType<?>> field_18538;
   @Shadow
   @Final
   private static ImmutableList<SensorType<? extends Sensor<? super VillagerEntity>>> field_18539;

   @Inject(
      method = {"method_5992"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void xs8pnsbhdld2q(PlayerEntity var1, Hand var2, CallbackInfoReturnable<ActionResult> var3) {
      VillagerEntity var4 = (VillagerEntity)(Object)this;
      if (var2 == Hand.MAIN_HAND && !var4.isBaby() && var4.getEntityWorld() instanceof ServerWorld var5) {
         ColonyManager.x3psqezd5jem54(var5, var4.getBlockPos())
            .ifPresent(
               var5x -> {
                  ColonyRole var6 = var5x.roleOf(var4.getUuid());
                  int var7 = DeedPropertyData.xau920lx57wg3(var1.getStackInHand(var2));
                  if (var6 == ColonyRole.x35csp4lojh59r && var7 > 0) {
                     if (DeedPropertyData.x11oga8h5115u7(var1.getStackInHand(var2))) {
                        DeedPropertyData.x3000fumbeekrm(var5.getServer(), var1.getStackInHand(var2));
                        var1.sendMessage(Text.literal("Этот листок уже использован. Повторный заказ невозможен."), true);
                     } else {
                        Optional var8 = DeedPropertyData.x2l483kg9fflzs(var5, var5x, var1, var2);
                        var1.sendMessage(
                           Text.literal(
                              var8.isPresent()
                                 ? "Строитель принял заказ. Дом: " + ((ConstructionJob)var8.get()).x3ij6gescvakv2().toShortString()
                                 : "Заказ пока не принят: нужно завершить дорогу/предыдущий заказ или найти свободный участок. Листок не использован."
                           ),
                           true
                        );
                     }

                     var3.setReturnValue(ActionResult.SUCCESS);
                  } else if (var6 != ColonyRole.x2j116s1wug0g2 && !var4.isSleeping() && !var4.hasCustomer()) {
                     ColonyManager.x21g1rujs9r1h6(var5, var4, var6);
                     ElderTradeService.x88t1bn7r4bt7(var5, var5x, var4, var6);
                     ColonyMathHelper.x3qgnd0wqcbp6n(var4);
                     ((x3jzeu04kdkziv)var4).xj1jj9iwsa6et(var1);
                     var3.setReturnValue(ActionResult.SUCCESS);
                  }
               }
            );
      }
   }

   @Inject(
      method = {"method_18008"},
      at = {@At("TAIL")}
   )
   private void x2mxal3pq2t9ku(TradeOffer var1, CallbackInfo var2) {
      VillagerEntity var3 = (VillagerEntity)(Object)this;
      if (var3.getEntityWorld() instanceof ServerWorld var4) {
         ElderTradeService.x1qctxo20x2yyf(var4, var3, var1);
      }
   }

   @Inject(
      method = {"method_28306"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3aj3xzpyni326(CallbackInfoReturnable<Profile<VillagerEntity>> var1) {
      ImmutableList var2 = ImmutableList.builder().addAll(field_18538).addAll(ColonyMemoryModules.x1eiihmuh1mj7p()).build();
      ImmutableList var3 = ImmutableList.builder().addAll(field_18539).add(ColonySensorTypes.x38iq4tihdy4v8).build();
      var1.setReturnValue(Brain.createProfile(var2, var3));
   }

   @Inject(
      method = {"method_5958"},
      at = {@At("HEAD")}
   )
   private void xd52oyun3ud5a(ServerWorld var1, CallbackInfo var2) {
      VillagerEntity var3 = (VillagerEntity)(Object)this;
      if (!var3.isBaby() && var3.getVillagerData().profession().matchesKey(VillagerProfession.NONE)) {
         ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos()).ifPresent(var2x -> {
            ColonyRole var3x = var2x.roleOf(var3.getUuid());
            if (var3x != ColonyRole.x2j116s1wug0g2) {
               ColonyManager.x21g1rujs9r1h6(var1, var3, var3x);
            }
         });
      }
   }

   @Inject(
      method = {"method_5958"},
      at = {@At("TAIL")}
   )
   private void x26yz6oi4dtq7f(ServerWorld var1, CallbackInfo var2) {
      VillagerEntity var3 = (VillagerEntity)(Object)this;
      Optional var4 = ColonyManager.x2yj2v4iudqlvb(var1, var3.getUuid()).or(() -> ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos()));
      ColonyRuntimeCaches.x2zyakuv0vnixz(var1, var3);
      if (var4.isEmpty()) {
         var3.getBrain().forget(ColonyMemoryModules.x3ecoc0soedv0m);
         if (var3.getBrain()
            .getFirstPossibleNonCoreActivity()
            .filter(
               var0 -> var0.equals(ColonyActivities.x12qpuae7zsr5m)
                  || var0.equals(ColonyActivities.x2t145qayl7tz3)
                  || var0.equals(ColonyActivities.x38u24k78sk73y)
                  || var0.equals(Activity.FIGHT)
            )
            .isPresent()) {
            var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
         }
      } else {
         Colony var5 = (Colony)var4.get();
         if (ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos()).map(var1x -> var1x.id().equals(var5.id())).orElse(false)) {
            var5.x1j7a15w06f1fz(var1.getTime());
            ColonySimulationService.x3ij59k18pnfmd(var1, var5);
         }

         ResourceDeliveryService.x3nc609h8fgb5y(var1, var5, var3);
         ColonyRole var6 = var5.roleOf(var3.getUuid());
         if (VillagerRaidShelterTask.active(var1, var5) && VillagerRaidShelterTask.civilian(var5, var3)) {
            if (var3.hasCustomer()) {
               var3.setCustomer(null);
            }

            if (var3.isSleeping()) {
               var3.wakeUp();
            }

            if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(ColonyActivities.x38u24k78sk73y::equals).isEmpty()) {
               for (Task var19 : List.copyOf(var3.getBrain().getRunningTasks())) {
                  if (!(var19 instanceof VillagerRaidShelterTask)) {
                     var19.stop(var1, var3, var1.getTime());
                  }
               }

               ColonyNavigationHelper.x2fxoqmbs0henb(var3);
            }

            var3.getBrain().forget(MemoryModuleType.WALK_TARGET);
            var3.getBrain().doExclusively(ColonyActivities.x38u24k78sk73y);
         } else {
            if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(ColonyActivities.x38u24k78sk73y::equals).isPresent()) {
               var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
            }

            if (!MilitiaCombatTask.x12ghbsk7tys8r(var1, var3, var5)) {
               if (var6 == ColonyRole.x2xja04fpuesoh) {
                  var3.getBrain().remember(ColonyMemoryModules.x3ecoc0soedv0m, var6);
                  if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(ColonyActivities.x2t145qayl7tz3::equals).isEmpty()) {
                     for (Task var18 : List.copyOf(var3.getBrain().getRunningTasks())) {
                        var18.stop(var1, var3, var1.getTime());
                     }

                     ColonyNavigationHelper.x2fxoqmbs0henb(var3);
                  }

                  var3.getBrain().forget(MemoryModuleType.WALK_TARGET);
                  var3.getBrain().doExclusively(ColonyActivities.x2t145qayl7tz3);
               } else if (var3.hasCustomer()) {
                  ColonyMathHelper.x3qgnd0wqcbp6n(var3);
               } else {
                  if ((var1.getTime() + var3.getId()) % 100L == 0L) {
                     var3.getBrain().remember(ColonyMemoryModules.x3ecoc0soedv0m, var6);
                  }

                  boolean var7 = var5.x3klfyffbbfab2() != Long.MIN_VALUE && var1.getTime() - var5.x3klfyffbbfab2() <= 200L
                     || VillagerRaidShelterTask.active(var1, var5)
                     || var6 == ColonyRole.x3r8p2tvihwpph && RaidDetectionHelper.x3kaxmxx3tadjj(var1, var5).isPresent();
                  boolean var8 = Math.floorMod(var1.getTimeOfDay(), 24000L) >= 1000L && Math.floorMod(var1.getTimeOfDay(), 24000L) < 9000L;
                  boolean var9 = Math.floorMod(var1.getTimeOfDay(), 24000L) >= 12000L;
                  boolean var10 = Math.floorMod(var1.getTimeOfDay(), 24000L) >= 9000L && Math.floorMod(var1.getTimeOfDay(), 24000L) < 12000L;
                  this.xwfvqwvn9pi6v(var1, var3, var5, var9, var7);
                  if (var3.isSleeping()) {
                     var3.getNavigation().stop();
                     var3.getBrain().forget(MemoryModuleType.WALK_TARGET);
                     var3.setVelocity(Vec3d.ZERO);
                     var3.getBrain().doExclusively(Activity.REST);
                  } else {
                     boolean var11 = this.x19aa321fd7oor(var1, var3, var5, var10);
                     if (!DeedItemHandler.x20vp2osbvc2gk(var1, var3, var5)) {
                        boolean var12;
                        boolean var13;
                        boolean var10000;
                        label280: {
                           ColonyMathUtils.xdfuvky2q520(var1, var5, var3);
                           ColonyChatFeedback.x1m5qncn5p4tgc(var1, var5, var3);
                           VillagerStuckHelper.x8pl4kh8avnpr(var1, var5, var3);
                           var12 = VillagerStuckHelper.x3u53699ao0yas(var3, var1.getTime());
                           var13 = var6 == ColonyRole.x3r8p2tvihwpph
                              && (
                                 var3.getBrain()
                                       .getOptionalRegisteredMemory(ColonyMemoryModules.x14b5znl8ndblc)
                                       .filter(var1x -> RaidDetectionHelper.x1bj0phudo3a5m(var1x) && RaidDetectionHelper.xs8awvrrlhug9(var5, var1x))
                                       .isPresent()
                                    || RaidDetectionHelper.x3kaxmxx3tadjj(var1, var5).isPresent()
                              );
                           label205:
                           if (var6 != ColonyRole.x3r8p2tvihwpph || !var7 && !var13) {
                              if (var8) {
                                 switch (var6) {
                                    case x35csp4lojh59r:
                                       if (var5.x2s37os1kht7vr()
                                          || var5.x2ad3s8aentmo4().isEmpty()
                                          || var5.x3e4ycl8cmmmgc()
                                          || !var5.x1n1a2vp01kxzf().isEmpty()
                                          || !var5.x2xqc3q79vfkw3().isEmpty()
                                          || !var5.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case x8jydaiei7iry:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case x2hwljbtybtlwc:
                                       break label205;
                                    case x18g4mqx9kxs89:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case xhds9crfjdp9i:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case xeoehg87qwfrn:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                       break;
                                    case x3r8p2tvihwpph:
                                       if (!var5.x2ad3s8aentmo4().isEmpty()) {
                                          break label205;
                                       }
                                 }
                              }

                              var10000 = false;
                              break label280;
                           }

                           var10000 = true;
                        }

                        boolean var14 = var10000;
                        Activity var15 = var12 || var11
                           ? Activity.IDLE
                           : (
                              var9 && !var7
                                 ? Activity.REST
                                 : (
                                    var6 != ColonyRole.x3r8p2tvihwpph || !var13 && !var7 && !(var3.getHealth() < var3.getMaxHealth() * 0.3F)
                                       ? (var14 ? ColonyActivities.x12qpuae7zsr5m : null)
                                       : Activity.FIGHT
                                 )
                           );
                        if (var15 != null) {
                           var3.getBrain().doExclusively(var15);
                        } else if (var3.getBrain()
                           .getFirstPossibleNonCoreActivity()
                           .filter(
                              var0 -> var0.equals(Activity.FIGHT) || var0.equals(ColonyActivities.x12qpuae7zsr5m) || var0.equals(ColonyActivities.x2t145qayl7tz3)
                           )
                           .isPresent()) {
                           var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
                        }

                        if (var6 == ColonyRole.x3r8p2tvihwpph
                           && var8
                           && !var7
                           && (var1.getTime() + var3.getId()) % 40L == 0L
                           && var3.getBrain().getRunningTasks().stream().noneMatch(var0 -> var0 instanceof VillagerDepositLootTask)) {
                           VillagerDeliverToStorageTask.patrol(var1, var5, var3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean x19aa321fd7oor(ServerWorld var1, VillagerEntity var2, Colony var3, boolean var4) {
      int var5 = x1ukapw8ymk7ox(var2);
      boolean var6 = !var2.isBaby() && var2.getBreedingAge() == 0;
      boolean var7 = x1pbipaexrd92u(var2);
      boolean var8 = var4 && (!var7 || var6 && var5 < 12);
      if ((var1.getTime() + var2.getId()) % 20L != 0L) {
         return var8;
      } else {
         if (var2.getHealth() < var2.getMaxHealth() && (var1.getTime() + var2.getId()) % 40L == 0L) {
            for (int var9 = 0; var9 < var2.getInventory().size(); var9++) {
               ItemStack var10 = var2.getInventory().getStack(var9);
               FoodComponent var11 = (FoodComponent)var10.get(DataComponentTypes.FOOD);
               if (var11 != null) {
                  var10.decrement(1);
                  var2.heal(Math.max(2.0F, var11.nutrition() * 1.5F));
                  var2.swingHand(Hand.MAIN_HAND);
                  return var8;
               }
            }

            ColonyManager.x1falh21nhy4yk(var1, var3, var2.getBlockPos()).ifPresent(var3x -> {
               if (var2.getBlockPos().getSquaredDistance(var3x) <= 9.0 && var3.cachedFood() > var3.population() * 12) {
                  ColonyManager.x3lxwdirnn77qc(var1, var3, var0x -> var0x.get(DataComponentTypes.FOOD) != null).ifPresent(var1xx -> {
                     FoodComponent var2xx = (FoodComponent)var1xx.get(DataComponentTypes.FOOD);
                     var2.heal(var2xx == null ? 2.0F : Math.max(2.0F, var2xx.nutrition() * 1.5F));
                     var2.swingHand(Hand.MAIN_HAND);
                  });
               }
            });
         }

         if (!var8) {
            return false;
         } else {
            Optional var14 = ColonyManager.x1falh21nhy4yk(var1, var3, var2.getBlockPos());
            if (var14.isEmpty()) {
               return true;
            } else if (var2.getBlockPos().getSquaredDistance((Vec3i)var14.get()) > 9.0) {
               var2.getBrain().remember(ColonyMemoryModules.x3o547clcv9kaw, (BlockPos)var14.get());
               ColonyNavigationHelper.x2wzylgizxltef(var1, var2, (BlockPos)var14.get(), 0.85);
               return true;
            } else {
               int var15 = var6 ? 12 : (var7 ? 0 : 1);

               for (int var16 = 0; var16 < 12 && var5 < var15; var16++) {
                  Optional var12 = ColonyManager.x3lxwdirnn77qc(var1, var3, var0 -> var0.get(DataComponentTypes.FOOD) != null);
                  if (var12.isEmpty()) {
                     break;
                  }

                  var5 += ColonyManager.xhahxgpa08yc9((ItemStack)var12.get());
                  ItemStack var13 = var2.getInventory().addStack((ItemStack)var12.get());
                  if (!var13.isEmpty()) {
                     ColonyManager.x2cl1crde2rnxy(var1, var3, var13);
                  }
               }

               if (!x1pbipaexrd92u(var2)) {
                  ColonyManager.x3lxwdirnn77qc(var1, var3, var0 -> var0.get(DataComponentTypes.FOOD) != null).ifPresent(var3x -> {
                     ItemStack var4x = var2.getInventory().addStack(var3x);
                     if (!var4x.isEmpty()) {
                        ColonyManager.x2cl1crde2rnxy(var1, var3, var4x);
                     }
                  });
               }

               var2.getBrain().forget(ColonyMemoryModules.x3o547clcv9kaw);
               return !x1pbipaexrd92u(var2) || var6 && x1ukapw8ymk7ox(var2) < 12;
            }
         }
      }
   }

   private static boolean x1pbipaexrd92u(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         if (var0.getInventory().getStack(var1).get(DataComponentTypes.FOOD) != null) {
            return true;
         }
      }

      return false;
   }

   private static int x1ukapw8ymk7ox(VillagerEntity var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         ItemStack var3 = var0.getInventory().getStack(var2);
         var1 += ColonyManager.xhahxgpa08yc9(var3) * var3.getCount();
      }

      return var1;
   }

   @Inject(
      method = {"method_19174"},
      at = {@At("TAIL")}
   )
   private void x2hkdgr70am6hi(Brain<VillagerEntity> var1, CallbackInfo var2) {
      var1.setTaskList(ColonyActivities.x2t145qayl7tz3, ImmutableList.of());
      var1.setTaskList(ColonyActivities.x38u24k78sk73y, ImmutableList.of(Pair.of(0, new VillagerRaidShelterTask())));
      var1.setTaskList(Activity.FIGHT, ImmutableList.of(Pair.of(0, new VillagerDeliverToStorageTask())));
      var1.setTaskList(
         ColonyActivities.x12qpuae7zsr5m,
         ImmutableList.of(
            Pair.of(0, new BuilderStarterChestTask()),
            Pair.of(0, new BuilderRepairTask()),
            Pair.of(0, new BuilderRoadTask()),
            Pair.of(1, new BuilderConstructionTask()),
            Pair.of(1, new BuilderVillageWallTask()),
            Pair.of(1, new VillagerDepositLootTask()),
            Pair.of(2, new FarmerFieldTask()),
            Pair.of(3, new LumberjackTask()),
            Pair.of(4, new MinerExpeditionTask()),
            Pair.of(5, new FishermanTask()),
            Pair.of(6, new RancherAnimalTask())
         )
      );
   }

   private void xwfvqwvn9pi6v(ServerWorld var1, VillagerEntity var2, Colony var3, boolean var4, boolean var5) {
      if (var4 && !var5) {
         for (Task var7 : List.copyOf(var2.getBrain().getRunningTasks())) {
            if (var7.getClass().getName().startsWith("net.smartcolonies.") || var7 instanceof FarmerVillagerTask) {
               var7.stop(var1, var2, var1.getTime());
            }
         }

         if (var2.isSleeping()) {
            var2.getNavigation().stop();
            var2.getBrain().forget(MemoryModuleType.WALK_TARGET);
         } else if ((var1.getTime() + var2.getId()) % 20L == 0L) {
            Optional var9 = var2.getBrain()
               .getOptionalRegisteredMemory(MemoryModuleType.HOME)
               .filter(var1x -> var1x.dimension().equals(var1.getRegistryKey()))
               .<BlockPos>map(GlobalPos::pos)
               .filter(var2x -> xi6qq1j9vjyfk(var1, var2x, var2));
            Optional var10 = var9.isPresent()
               ? var9
               : var1.getPointOfInterestStorage()
                  .getPositions(
                     var0 -> var0.matchesKey(PointOfInterestTypes.HOME),
                     var2x -> xi6qq1j9vjyfk(var1, var2x, var2),
                     var3.core(),
                     var3.radius(),
                     OccupationStatus.ANY
                  )
                  .min(Comparator.comparingDouble(var1x -> var1x.getSquaredDistance(var2.getBlockPos())));
            if (!var10.isEmpty()) {
               BlockPos var8 = (BlockPos)var10.get();
               var2.getBrain().remember(MemoryModuleType.HOME, GlobalPos.create(var1.getRegistryKey(), var8));
               if (var2.getBlockPos().getSquaredDistance(var8) <= 4.0) {
                  var2.getNavigation().stop();
                  var2.sleep(var8);
               } else {
                  ColonyNavigationHelper.x1lrdp6728c3ml(var1, var2, var8, 0.85);
               }
            }
         }
      } else {
         if (var2.isSleeping()) {
            var2.wakeUp();
         }
      }
   }

   private static boolean xi6qq1j9vjyfk(ServerWorld var0, BlockPos var1, VillagerEntity var2) {
      if (!var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)) {
         return false;
      } else {
         BlockState var3 = var0.getBlockState(var1);
         if (!var3.isIn(BlockTags.BEDS)) {
            return false;
         } else {
            return var3.contains(BedBlock.OCCUPIED) && var3.get(BedBlock.OCCUPIED) ? var2.getSleepingPosition().<Boolean>map(var1::equals).orElse(false) : true;
         }
      }
   }
}
