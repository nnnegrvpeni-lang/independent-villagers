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
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x19n3hxzuoqopl;
import net.smartcolonies.r.x1c1r5c6a13gtl;
import net.smartcolonies.r.x1faxfib09uh4;
import net.smartcolonies.r.x1intnp6ax9q2k;
import net.smartcolonies.r.x1ln7i1g8dapfg;
import net.smartcolonies.r.x1maxmocdanph0;
import net.smartcolonies.r.x1rxiy9mtu5b3h;
import net.smartcolonies.r.x22x83apw5u72z;
import net.smartcolonies.r.x2arpn38dclo1g;
import net.smartcolonies.r.x2cvbyh5y5day0;
import net.smartcolonies.r.x2ff3vfeunp7fx;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x2vaj7rsm57i4n;
import net.smartcolonies.r.x2vu3n4ffebkej;
import net.smartcolonies.r.x35kkrl6uz1e8h;
import net.smartcolonies.r.x36u1fhs2z6uoi;
import net.smartcolonies.r.x37idyr79ed75m;
import net.smartcolonies.r.x3gesg5rjwqz5j;
import net.smartcolonies.r.x3iscxmqtuzq8n;
import net.smartcolonies.r.x3lus134ezrtp5;
import net.smartcolonies.r.x3m66wv1c6161h;
import net.smartcolonies.r.x6amcj7vm0jm5;
import net.smartcolonies.r.x6g7jwukdjgn4;
import net.smartcolonies.r.x9ubd81l6sw6p;
import net.smartcolonies.r.xctqfh4vgmwfd;
import net.smartcolonies.r.xf6ny39u2klct;
import net.smartcolonies.r.xlo4chqtqjj9e;
import net.smartcolonies.r.xnssk2qgda4r6;
import net.smartcolonies.r.xpmscpidlka0a;
import net.smartcolonies.r.xquptt42fexz2;
import net.smartcolonies.r.xxao083dbo7ag;
import net.smartcolonies.r.xzszqoikocxnx;
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
         x2n55y2tom37h2.x3psqezd5jem54(var5, var4.getBlockPos())
            .ifPresent(
               var5x -> {
                  x1rxiy9mtu5b3h var6 = var5x.roleOf(var4.getUuid());
                  int var7 = xquptt42fexz2.xau920lx57wg3(var1.getStackInHand(var2));
                  if (var6 == x1rxiy9mtu5b3h.x35csp4lojh59r && var7 > 0) {
                     if (xquptt42fexz2.x11oga8h5115u7(var1.getStackInHand(var2))) {
                        xquptt42fexz2.x3000fumbeekrm(var5.getServer(), var1.getStackInHand(var2));
                        var1.sendMessage(Text.literal("Этот листок уже использован. Повторный заказ невозможен."), true);
                     } else {
                        Optional var8 = xquptt42fexz2.x2l483kg9fflzs(var5, var5x, var1, var2);
                        var1.sendMessage(
                           Text.literal(
                              var8.isPresent()
                                 ? "Строитель принял заказ. Дом: " + ((x2arpn38dclo1g)var8.get()).x3ij6gescvakv2().toShortString()
                                 : "Заказ пока не принят: нужно завершить дорогу/предыдущий заказ или найти свободный участок. Листок не использован."
                           ),
                           true
                        );
                     }

                     var3.setReturnValue(ActionResult.SUCCESS);
                  } else if (var6 != x1rxiy9mtu5b3h.x2j116s1wug0g2 && !var4.isSleeping() && !var4.hasCustomer()) {
                     x2n55y2tom37h2.x21g1rujs9r1h6(var5, var4, var6);
                     x1faxfib09uh4.x88t1bn7r4bt7(var5, var5x, var4, var6);
                     x6g7jwukdjgn4.x3qgnd0wqcbp6n(var4);
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
         x1faxfib09uh4.x1qctxo20x2yyf(var4, var3, var1);
      }
   }

   @Inject(
      method = {"method_28306"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3aj3xzpyni326(CallbackInfoReturnable<Profile<VillagerEntity>> var1) {
      ImmutableList var2 = ImmutableList.builder().addAll(field_18538).addAll(xxao083dbo7ag.x1eiihmuh1mj7p()).build();
      ImmutableList var3 = ImmutableList.builder().addAll(field_18539).add(x1ln7i1g8dapfg.x38iq4tihdy4v8).build();
      var1.setReturnValue(Brain.createProfile(var2, var3));
   }

   @Inject(
      method = {"method_5958"},
      at = {@At("HEAD")}
   )
   private void xd52oyun3ud5a(ServerWorld var1, CallbackInfo var2) {
      VillagerEntity var3 = (VillagerEntity)(Object)this;
      if (!var3.isBaby() && var3.getVillagerData().profession().matchesKey(VillagerProfession.NONE)) {
         x2n55y2tom37h2.x3psqezd5jem54(var1, var3.getBlockPos()).ifPresent(var2x -> {
            x1rxiy9mtu5b3h var3x = var2x.roleOf(var3.getUuid());
            if (var3x != x1rxiy9mtu5b3h.x2j116s1wug0g2) {
               x2n55y2tom37h2.x21g1rujs9r1h6(var1, var3, var3x);
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
      Optional var4 = x2n55y2tom37h2.x2yj2v4iudqlvb(var1, var3.getUuid()).or(() -> x2n55y2tom37h2.x3psqezd5jem54(var1, var3.getBlockPos()));
      x3m66wv1c6161h.x2zyakuv0vnixz(var1, var3);
      if (var4.isEmpty()) {
         var3.getBrain().forget(xxao083dbo7ag.x3ecoc0soedv0m);
         if (var3.getBrain()
            .getFirstPossibleNonCoreActivity()
            .filter(
               var0 -> var0.equals(xnssk2qgda4r6.x12qpuae7zsr5m)
                  || var0.equals(xnssk2qgda4r6.x2t145qayl7tz3)
                  || var0.equals(xnssk2qgda4r6.x38u24k78sk73y)
                  || var0.equals(Activity.FIGHT)
            )
            .isPresent()) {
            var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
         }
      } else {
         x15kgoaq6i3m6i var5 = (x15kgoaq6i3m6i)var4.get();
         if (x2n55y2tom37h2.x3psqezd5jem54(var1, var3.getBlockPos()).map(var1x -> var1x.id().equals(var5.id())).orElse(false)) {
            var5.x1j7a15w06f1fz(var1.getTime());
            x1intnp6ax9q2k.x3ij59k18pnfmd(var1, var5);
         }

         xf6ny39u2klct.x3nc609h8fgb5y(var1, var5, var3);
         x1rxiy9mtu5b3h var6 = var5.roleOf(var3.getUuid());
         if (x36u1fhs2z6uoi.active(var1, var5) && x36u1fhs2z6uoi.civilian(var5, var3)) {
            if (var3.hasCustomer()) {
               var3.setCustomer(null);
            }

            if (var3.isSleeping()) {
               var3.wakeUp();
            }

            if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(xnssk2qgda4r6.x38u24k78sk73y::equals).isEmpty()) {
               for (Task var19 : List.copyOf(var3.getBrain().getRunningTasks())) {
                  if (!(var19 instanceof x36u1fhs2z6uoi)) {
                     var19.stop(var1, var3, var1.getTime());
                  }
               }

               x1c1r5c6a13gtl.x2fxoqmbs0henb(var3);
            }

            var3.getBrain().forget(MemoryModuleType.WALK_TARGET);
            var3.getBrain().doExclusively(xnssk2qgda4r6.x38u24k78sk73y);
         } else {
            if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(xnssk2qgda4r6.x38u24k78sk73y::equals).isPresent()) {
               var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
            }

            if (!xzszqoikocxnx.x12ghbsk7tys8r(var1, var3, var5)) {
               if (var6 == x1rxiy9mtu5b3h.x2xja04fpuesoh) {
                  var3.getBrain().remember(xxao083dbo7ag.x3ecoc0soedv0m, var6);
                  if (var3.getBrain().getFirstPossibleNonCoreActivity().filter(xnssk2qgda4r6.x2t145qayl7tz3::equals).isEmpty()) {
                     for (Task var18 : List.copyOf(var3.getBrain().getRunningTasks())) {
                        var18.stop(var1, var3, var1.getTime());
                     }

                     x1c1r5c6a13gtl.x2fxoqmbs0henb(var3);
                  }

                  var3.getBrain().forget(MemoryModuleType.WALK_TARGET);
                  var3.getBrain().doExclusively(xnssk2qgda4r6.x2t145qayl7tz3);
               } else if (var3.hasCustomer()) {
                  x6g7jwukdjgn4.x3qgnd0wqcbp6n(var3);
               } else {
                  if ((var1.getTime() + var3.getId()) % 100L == 0L) {
                     var3.getBrain().remember(xxao083dbo7ag.x3ecoc0soedv0m, var6);
                  }

                  boolean var7 = var5.x3klfyffbbfab2() != Long.MIN_VALUE && var1.getTime() - var5.x3klfyffbbfab2() <= 200L
                     || x36u1fhs2z6uoi.active(var1, var5)
                     || var6 == x1rxiy9mtu5b3h.x3r8p2tvihwpph && x37idyr79ed75m.x3kaxmxx3tadjj(var1, var5).isPresent();
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
                     if (!x6amcj7vm0jm5.x20vp2osbvc2gk(var1, var3, var5)) {
                        boolean var12;
                        boolean var13;
                        boolean var10000;
                        label280: {
                           x19n3hxzuoqopl.xdfuvky2q520(var1, var5, var3);
                           x2vaj7rsm57i4n.x1m5qncn5p4tgc(var1, var5, var3);
                           x35kkrl6uz1e8h.x8pl4kh8avnpr(var1, var5, var3);
                           var12 = x35kkrl6uz1e8h.x3u53699ao0yas(var3, var1.getTime());
                           var13 = var6 == x1rxiy9mtu5b3h.x3r8p2tvihwpph
                              && (
                                 var3.getBrain()
                                       .getOptionalRegisteredMemory(xxao083dbo7ag.x14b5znl8ndblc)
                                       .filter(var1x -> x37idyr79ed75m.x1bj0phudo3a5m(var1x) && x37idyr79ed75m.xs8awvrrlhug9(var5, var1x))
                                       .isPresent()
                                    || x37idyr79ed75m.x3kaxmxx3tadjj(var1, var5).isPresent()
                              );
                           label205:
                           if (var6 != x1rxiy9mtu5b3h.x3r8p2tvihwpph || !var7 && !var13) {
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
                                    var6 != x1rxiy9mtu5b3h.x3r8p2tvihwpph || !var13 && !var7 && !(var3.getHealth() < var3.getMaxHealth() * 0.3F)
                                       ? (var14 ? xnssk2qgda4r6.x12qpuae7zsr5m : null)
                                       : Activity.FIGHT
                                 )
                           );
                        if (var15 != null) {
                           var3.getBrain().doExclusively(var15);
                        } else if (var3.getBrain()
                           .getFirstPossibleNonCoreActivity()
                           .filter(
                              var0 -> var0.equals(Activity.FIGHT) || var0.equals(xnssk2qgda4r6.x12qpuae7zsr5m) || var0.equals(xnssk2qgda4r6.x2t145qayl7tz3)
                           )
                           .isPresent()) {
                           var3.getBrain().refreshActivities(var1.getEnvironmentAttributes(), var1.getTime(), var3.getEntityPos());
                        }

                        if (var6 == x1rxiy9mtu5b3h.x3r8p2tvihwpph
                           && var8
                           && !var7
                           && (var1.getTime() + var3.getId()) % 40L == 0L
                           && var3.getBrain().getRunningTasks().stream().noneMatch(var0 -> var0 instanceof x9ubd81l6sw6p)) {
                           x2ff3vfeunp7fx.patrol(var1, var5, var3);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean x19aa321fd7oor(ServerWorld var1, VillagerEntity var2, x15kgoaq6i3m6i var3, boolean var4) {
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

            x2n55y2tom37h2.x1falh21nhy4yk(var1, var3, var2.getBlockPos()).ifPresent(var3x -> {
               if (var2.getBlockPos().getSquaredDistance(var3x) <= 9.0 && var3.cachedFood() > var3.population() * 12) {
                  x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0x -> var0x.get(DataComponentTypes.FOOD) != null).ifPresent(var1xx -> {
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
            Optional var14 = x2n55y2tom37h2.x1falh21nhy4yk(var1, var3, var2.getBlockPos());
            if (var14.isEmpty()) {
               return true;
            } else if (var2.getBlockPos().getSquaredDistance((Vec3i)var14.get()) > 9.0) {
               var2.getBrain().remember(xxao083dbo7ag.x3o547clcv9kaw, (BlockPos)var14.get());
               x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, (BlockPos)var14.get(), 0.85);
               return true;
            } else {
               int var15 = var6 ? 12 : (var7 ? 0 : 1);

               for (int var16 = 0; var16 < 12 && var5 < var15; var16++) {
                  Optional var12 = x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0 -> var0.get(DataComponentTypes.FOOD) != null);
                  if (var12.isEmpty()) {
                     break;
                  }

                  var5 += x2n55y2tom37h2.xhahxgpa08yc9((ItemStack)var12.get());
                  ItemStack var13 = var2.getInventory().addStack((ItemStack)var12.get());
                  if (!var13.isEmpty()) {
                     x2n55y2tom37h2.x2cl1crde2rnxy(var1, var3, var13);
                  }
               }

               if (!x1pbipaexrd92u(var2)) {
                  x2n55y2tom37h2.x3lxwdirnn77qc(var1, var3, var0 -> var0.get(DataComponentTypes.FOOD) != null).ifPresent(var3x -> {
                     ItemStack var4x = var2.getInventory().addStack(var3x);
                     if (!var4x.isEmpty()) {
                        x2n55y2tom37h2.x2cl1crde2rnxy(var1, var3, var4x);
                     }
                  });
               }

               var2.getBrain().forget(xxao083dbo7ag.x3o547clcv9kaw);
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
         var1 += x2n55y2tom37h2.xhahxgpa08yc9(var3) * var3.getCount();
      }

      return var1;
   }

   @Inject(
      method = {"method_19174"},
      at = {@At("TAIL")}
   )
   private void x2hkdgr70am6hi(Brain<VillagerEntity> var1, CallbackInfo var2) {
      var1.setTaskList(xnssk2qgda4r6.x2t145qayl7tz3, ImmutableList.of());
      var1.setTaskList(xnssk2qgda4r6.x38u24k78sk73y, ImmutableList.of(Pair.of(0, new x36u1fhs2z6uoi())));
      var1.setTaskList(Activity.FIGHT, ImmutableList.of(Pair.of(0, new x2ff3vfeunp7fx())));
      var1.setTaskList(
         xnssk2qgda4r6.x12qpuae7zsr5m,
         ImmutableList.of(
            Pair.of(0, new xlo4chqtqjj9e()),
            Pair.of(0, new x2vu3n4ffebkej()),
            Pair.of(0, new x3iscxmqtuzq8n()),
            Pair.of(1, new x2cvbyh5y5day0()),
            Pair.of(1, new x22x83apw5u72z()),
            Pair.of(1, new x9ubd81l6sw6p()),
            Pair.of(2, new xpmscpidlka0a()),
            Pair.of(3, new x3lus134ezrtp5()),
            Pair.of(4, new x3gesg5rjwqz5j()),
            Pair.of(5, new xctqfh4vgmwfd()),
            Pair.of(6, new x1maxmocdanph0())
         )
      );
   }

   private void xwfvqwvn9pi6v(ServerWorld var1, VillagerEntity var2, x15kgoaq6i3m6i var3, boolean var4, boolean var5) {
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
                  x1c1r5c6a13gtl.x1lrdp6728c3ml(var1, var2, var8, 0.85);
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
