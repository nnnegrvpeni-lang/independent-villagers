package net.smartcolonies.r;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public final class ColonyDiagnosticCommands {
   private ColonyDiagnosticCommands() {
   }

   public static void x32f59jmix8kb9() {
      CommandRegistrationCallback.EVENT.register((CommandRegistrationCallback)(var0, var1, var2) -> x20dekagiglx0e(var0));
   }

   private static void x20dekagiglx0e(CommandDispatcher<ServerCommandSource> var0) {
      var0.register(
         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("smartcolonies")
                     .then(StructureTestCommands.x2aso8h2ks6ywe()))
                  .then(FilmCommandRegistration.x2bhz7qcxu7mfc()))
               .then(
                  ((LiteralArgumentBuilder)CommandManager.literal("repair").requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK)))
                     .executes(
                        var0x -> {
                           ServerCommandSource var1 = (ServerCommandSource)var0x.getSource();
                           Optional var2 = ColonyManager.x3psqezd5jem54(var1.getWorld(), BlockPos.ofFloored(var1.getPosition()));
                           if (var2.isEmpty()) {
                              var1.sendFeedback(() -> Text.literal("Рядом нет колонии."), false);
                              return 0;
                           } else {
                              int var3 = StructureBlockScanner.xc4tegq500os8(var1.getWorld(), (Colony)var2.get());
                              var1.sendFeedback(
                                 () -> Text.literal(
                                    "Проверены загруженные общественные дома. В очередь ремонта добавлено блоков: " + var3 + ". Нужны строители и материалы."
                                 ),
                                 false
                              );
                              return var3;
                           }
                        }
                     )
               ))
            .then(
               CommandManager.literal("status")
                  .executes(
                     var0x -> {
                        ServerCommandSource var1 = (ServerCommandSource)var0x.getSource();
                        BlockPos var2 = BlockPos.ofFloored(var1.getPosition());
                        Optional var3 = ColonyManager.x3psqezd5jem54(var1.getWorld(), var2);
                        if (var3.isEmpty()) {
                           var1.sendFeedback(() -> Text.literal("Independent Villagers: рядом нет сгенерированной ванильной деревни."), false);
                           return 0;
                        } else {
                           Colony var4 = (Colony)var3.get();
                           long var5 = var4.x3klfyffbbfab2() == Long.MIN_VALUE ? -1L : var1.getWorld().getTime() - var4.x3klfyffbbfab2();
                           List<VillagerEntity> var7 = var1.getWorld()
                              .getEntitiesByType(EntityType.VILLAGER, ColonyManager.x150ccxomrlozr(var1.getWorld(), var4), var0xx -> var0xx.isAlive())
                              .stream()
                              .filter(
                                 var2x -> ColonyManager.x3psqezd5jem54(var1.getWorld(), var2x.getBlockPos())
                                    .map(var1xx -> var1xx.id().equals(var4.id()))
                                    .orElse(false)
                              )
                              .toList();
                           String var8 = Arrays.stream(ColonyRole.values())
                              .map(var2x -> var2x.serializedName() + ":" + var7.stream().filter(var2xx -> var4.roleOf(var2xx.getUuid()) == var2x).count())
                              .collect(Collectors.joining("/"));
                           String var9 = Arrays.stream(ConstructionStatus.values())
                              .map(
                                 var1x -> var1x.name().toLowerCase()
                                    + ":"
                                    + var4.x1ja61emeln2hc().stream().filter(var1xx -> var1xx.x32oz43hmeynhc() == var1x).count()
                              )
                              .collect(Collectors.joining("/"));
                           long var10 = var7.stream()
                              .filter(
                                 var0xx -> var0xx.getBrain()
                                    .getRunningTasks()
                                    .stream()
                                    .anyMatch(var0xxx -> var0xxx.getClass().getName().startsWith("net.smartcolonies."))
                              )
                              .count();
                           String var12 = "Independent Villagers: core="
                              + var4.core().toShortString()
                              + ", radius="
                              + var4.radius()
                              + ", population="
                              + var4.population()
                              + "/"
                              + var4.beds()
                              + " beds, food="
                              + var4.cachedFood()
                              + ", carriedFood="
                              + ColonyManager.x1397xrm4pk893(var1.getWorld(), var4)
                              + ", logs="
                              + var4.cachedLogs()
                              + ", stone="
                              + var4.cachedStone()
                              + ", iron="
                              + var4.x12nt3bv2et7b7()
                              + ", diamonds="
                              + var4.x3peiu091351qg()
                              + ", storages="
                              + var4.x2ad3s8aentmo4().size()
                              + ", roles="
                              + var8
                              + ", jobs="
                              + var9
                              + ", activeWorkers="
                              + var10
                              + ", threatAge="
                              + var5;
                           var1.sendFeedback(() -> Text.literal(var12), false);
                           var1.sendFeedback(() -> Text.literal("Развитие: " + ColonySimulationService.x2ypihddkgbmhu(var4)), false);
                           List var13 = ColonyState.get(var1.getWorld())
                              .land()
                              .stream()
                              .filter(var1x -> var1x.x3mnabq6qj85f8().equals(var4.id()))
                              .map(var0xx -> var0xx.x2kystgxl68xrn().toShortString() + " радиус=" + var0xx.x3rgd0i6cz19eu())
                              .toList();
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Недвижимость: участки="
                                    + var13
                                    + "; цены сейчас: маленький дом="
                                    + ColonyRadiusHelper.x2vr85xzgtiacc(var4, 32, false)
                                    + ", средний дом="
                                    + ColonyRadiusHelper.x2vr85xzgtiacc(var4, 64, false)
                                    + ", участок="
                                    + ColonyRadiusHelper.x2vr85xzgtiacc(var4, 24, true)
                                    + " изумрудов."
                              ),
                              false
                           );
                           String var14 = ColonyManager.x8x0922dn63y6(var4, var1.getWorld().getTime())
                              .entrySet()
                              .stream()
                              .filter(var0xx -> var0xx.getKey() != ColonyRole.x2j116s1wug0g2)
                              .map(var0xx -> var0xx.getKey().serializedName() + ":" + var0xx.getValue() + "%")
                              .collect(Collectors.joining("/"));
                           var1.sendFeedback(() -> Text.literal("Спрос на профессии: " + var14), false);
                           var1.sendFeedback(() -> Text.literal(ResourceScoutingHelper.x32tk2361rdt82(var4)), false);
                           ExpansionPlan var15 = var4.x2f7z1gnnht5ab();
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Стены: радиус="
                                    + var15.x14zjd0to6v6yv
                                    + ", блоков="
                                    + var15.x16wpjsjmva3od.size()
                                    + ", осталось="
                                    + var15.x1q0gb1h69ywu7.size()
                                    + ", пропущено="
                                    + var15.x1fvrorwquv0wt
                                    + ", цель="
                                    + (var15.x1q0gb1h69ywu7.isEmpty() ? "-" : var15.x1q0gb1h69ywu7.getFirst().x1oppt2nr5wv2g().toShortString())
                              ),
                              false
                           );
                           List var16 = ColonyManager.x9jjpc1qt9hfg(var1.getWorld(), var4);
                           long var17 = var7.stream().filter(var0xx -> !var0xx.isBaby() && var0xx.getBreedingAge() == 0).count();
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Размножение: "
                                    + (var16.isEmpty() ? "колония разрешает" : String.join("; ", var16))
                                    + "; готовых родителей="
                                    + var17
                                    + "/2 минимум. Колония тратит 24 единицы питательности любой еды из общих складов; носить именно хлеб не нужно. Нужна свободная кровать; следующая попытка через "
                                    + Math.max(0L, var4.x180oul2yysi55() - var1.getWorld().getTime()) / 20L
                                    + " с."
                              ),
                              false
                           );
                           String var19 = var4.x1ja61emeln2hc()
                              .stream()
                              .filter(
                                 var0xx -> var0xx.x32oz43hmeynhc() == ConstructionStatus.x2tgnsmc98zm7p || var0xx.x32oz43hmeynhc() == ConstructionStatus.x1mzkgtgadv3jh
                              )
                              .map(var2x -> x224hvqfelnea1(var2x, var7, var1.getWorld().getTime()))
                              .collect(Collectors.joining("; "));
                           String var20 = x3u8cqfu11cpc6(var7, ColonyMemoryModules.x1h4kjv4hra8oe);
                           String var21 = x3u8cqfu11cpc6(var7, ColonyMemoryModules.x2bwom1pasl6g5);
                           String var22 = x3u8cqfu11cpc6(var7, ColonyMemoryModules.x15kki2mjvh7tw);
                           String var23 = x3u8cqfu11cpc6(var7, ColonyMemoryModules.x263ucjykllb1b);
                           String var24 = x3u8cqfu11cpc6(var7, ColonyMemoryModules.x2ppem7wipzctm);
                           String var25 = var4.x2ad3s8aentmo4()
                              .stream()
                              .map(var1x -> var4.x2h8eqlzb5mm0w(var1x).displayName() + "@" + var1x.toShortString())
                              .collect(Collectors.joining("; "));
                           String var26 = var7.stream()
                              .filter(var1x -> var4.roleOf(var1x.getUuid()) == ColonyRole.x3r8p2tvihwpph)
                              .map(
                                 var0xx -> var0xx.getUuidAsString().substring(0, 8)
                                    + "@"
                                    + var0xx.getBlockPos().toShortString()
                                    + " weapon="
                                    + x2hdxge3ubqygw(var0xx.getMainHandStack())
                                    + " armor="
                                    + x2hdxge3ubqygw(var0xx.getEquippedStack(EquipmentSlot.HEAD))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.getEquippedStack(EquipmentSlot.CHEST))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.getEquippedStack(EquipmentSlot.LEGS))
                                    + "/"
                                    + x2hdxge3ubqygw(var0xx.getEquippedStack(EquipmentSlot.FEET))
                              )
                              .collect(Collectors.joining("; "));
                           var1.sendFeedback(() -> Text.literal("Targets: houses=[" + var19 + "]"), false);
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Ремонт: блоков="
                                    + var4.x2xqc3q79vfkw3().size()
                                    + ", координаты=["
                                    + var4.x2xqc3q79vfkw3()
                                       .keySet()
                                       .stream()
                                       .limit(12L)
                                       .<CharSequence>map(Vec3i::toShortString)
                                       .collect(Collectors.joining("; "))
                                    + "]"
                              ),
                              false
                           );
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Строительство: свободных кроватей="
                                    + var4.x182p7mjnr75sg()
                                    + "/"
                                    + ColonyManager.x1ext6bk0354f9(var4.population())
                                    + " целевой запас, построено домов="
                                    + var4.x1ja61emeln2hc().stream().filter(var0xxx -> var0xxx.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6).count()
                                    + "/"
                                    + ColonyConfig.x2by5f9ehxvwk9().village.max_constructed_houses
                                    + ", состояние="
                                    + ColonyManager.x2y69zyuty3rxs(var1.getWorld(), var4)
                                    + "."
                              ),
                              false
                           );
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Targets: trees=["
                                    + var20
                                    + "], farms=["
                                    + var21
                                    + "], mines=["
                                    + var22
                                    + "], fishing=["
                                    + var23
                                    + "], animals=["
                                    + var24
                                    + "]"
                              ),
                              false
                           );
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Storages: ["
                                    + var25
                                    + "] freeSlots="
                                    + ColonyManager.x11dwzrdveisv7(var1.getWorld(), var4)
                                    + "; eggs="
                                    + ColonyManager.x1mlne8tl8k5ag(var1.getWorld(), var4, var0xxx -> var0xxx.isOf(Items.EGG))
                                    + "/32; milk="
                                    + ColonyStorageAuditor.x31lz6msk67uxc(var1.getWorld(), var4)
                                    + "/4"
                              ),
                              false
                           );
                           var1.sendFeedback(() -> Text.literal("Militia: [" + var26 + "]"), false);
                           String var27 = var4.x1n1a2vp01kxzf().isEmpty() ? "-" : var4.x1n1a2vp01kxzf().getFirst().toShortString();
                           String var28 = var4.x31ow2tlg0dm5w().isEmpty()
                              ? "-"
                              : "entrance="
                                 + var4.x31ow2tlg0dm5w().getFirst().toShortString()
                                 + " frontier="
                                 + var4.x31ow2tlg0dm5w().getLast().toShortString()
                                 + " steps="
                                 + (var4.x31ow2tlg0dm5w().size() - 1);
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Road: next="
                                    + var27
                                    + " remaining="
                                    + var4.x1n1a2vp01kxzf().size()
                                    + " stalled="
                                    + var4.x3nmwt0p1j1m6g()
                                    + " skipped="
                                    + var4.x2frtu6yleucxg()
                                    + "; Mine: "
                                    + var28
                                    + ", deepTrips="
                                    + var4.x2qncyh2nmpkyq()
                                    + ", deepYield="
                                    + var4.x324ff4i2oimhg()
                                    + "; Pen: "
                                    + var4.x329rgb9lpsari().<String>map(Vec3i::toShortString).orElse("-")
                              ),
                              false
                           );
                           String var29 = var1.getWorld()
                              .getEntitiesByClass(IronGolemEntity.class, ColonyManager.x150ccxomrlozr(var1.getWorld(), var4), var0xx -> var0xx.isAlive())
                              .stream()
                              .map(var0xx -> var0xx.getBlockPos().toShortString() + " hp=" + (int)var0xx.getHealth() + "/" + (int)var0xx.getMaxHealth())
                              .collect(Collectors.joining("; "));
                           long var30 = var1.getWorld()
                              .getEntitiesByClass(IronGolemEntity.class, ColonyManager.x150ccxomrlozr(var1.getWorld(), var4), var0xx -> var0xx.isAlive())
                              .size();
                           var1.sendFeedback(() -> Text.literal("Golems: " + var30 + "/" + IronGolemColonyHelper.x20ln0j5i9gefl(var4) + " [" + var29 + "]"), false);
                           boolean var32 = ColonyManager.x1tl8iu49onump(
                              var1.getWorld(), var4, var0xx -> var0xx.isOf(Items.PUMPKIN) || var0xx.isOf(Items.CARVED_PUMPKIN)
                           );
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Golem materials: iron="
                                    + var4.x12nt3bv2et7b7()
                                    + "/36, pumpkin="
                                    + var32
                                    + ", nextBuild="
                                    + Math.max(0L, var4.x1ljiw4h4dmxfs() - var1.getServer().getTicks())
                                    + " ticks"
                              ),
                              false
                           );
                           int var33 = ColonyManager.x1mlne8tl8k5ag(var1.getWorld(), var4, var0xx -> var0xx.isOf(Items.EMERALD));
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Economy: emeralds="
                                    + var33
                                    + ", sold="
                                    + var4.x4zx3pxdqkrq()
                                    + ", bought="
                                    + var4.x1v1doml6bflbz()
                                    + ", next="
                                    + Math.max(0L, var4.x3b9zegn105yce() - var1.getWorld().getTime())
                                    + " ticks"
                              ),
                              false
                           );
                           var1.sendFeedback(
                              () -> Text.literal(
                                 "Развитие: elder="
                                    + var4.x3ctixeo4to9a4().map(var0xxx -> var0xxx.toString().substring(0, 8)).orElse("-")
                                    + ", founder="
                                    + var4.x2a380ray4md5t().map(var0xxx -> var0xxx.toString().substring(0, 8)).orElse("-")
                                    + ", founderTarget="
                                    + var4.x1x0agg3sdxjk8().<String>map(Vec3i::toShortString).orElse("-")
                                    + ", visits="
                                    + var4.x37br4tln8q08z()
                                    + ", capital="
                                    + var4.x13ua0ftegttnr()
                                    + ", propertyOrder="
                                    + var4.x3t9uchcxi5zlm().map(var1x -> var1x.toString().substring(0, 8) + " L" + var4.x1beis4altbswn()).orElse("-")
                              ),
                              false
                           );
                           var4.x2npriys7z2eli(var1.getWorld().getTime())
                              .ifPresent(
                                 var2x -> var1.sendFeedback(
                                    () -> Text.literal(
                                       "Theft alarm: player="
                                          + var2x.toString().substring(0, 8)
                                          + ", stolen="
                                          + var4.x6at97kfxajem()
                                          + ", remaining="
                                          + Math.max(0L, var4.x3ifovfwl4jqpk() - var1.getWorld().getTime()) / 20L
                                          + "s"
                                    ),
                                    false
                                 )
                              );
                           String var34 = var7.stream()
                              .filter(var1x -> var4.roleOf(var1x.getUuid()) != ColonyRole.x2j116s1wug0g2)
                              .map(
                                 var2x -> var4.roleOf(var2x.getUuid()).serializedName()
                                    + "@"
                                    + var2x.getBlockPos().toShortString()
                                    + " held="
                                    + x2hdxge3ubqygw(var2x.getMainHandStack())
                                    + " recovery="
                                    + VillagerStuckHelper.x3u53699ao0yas(var2x, var1.getWorld().getTime())
                                    + " work=["
                                    + ColonyChatFeedback.xnmb1jjez0ytg(var2x)
                                    + "] tasks="
                                    + var2x.getBrain()
                                       .getRunningTasks()
                                       .stream()
                                       .filter(var0xxx -> var0xxx.getClass().getName().startsWith("net.smartcolonies."))
                                       .map(
                                          var1xx -> var1xx instanceof FarmerFieldTask var6
                                             ? "Farm[" + var6.debugState() + "]"
                                             : (
                                                var1xx instanceof MinerExpeditionTask var5x
                                                   ? "Mine[" + var5x.debugState(var2x) + "]"
                                                   : (
                                                      var1xx instanceof FishermanTask var4x
                                                         ? "Fish[" + var4x.debugState() + "]"
                                                         : (
                                                            var1xx instanceof RancherAnimalTask var3x
                                                               ? "Livestock[" + var3x.debugState() + "]"
                                                               : (
                                                                  var1xx instanceof BuilderConstructionTask var2xx
                                                                     ? "Build[" + var2xx.debugState() + "]"
                                                                     : var1xx.getClass().getSimpleName()
                                                               )
                                                         )
                                                   )
                                             )
                                       )
                                       .collect(Collectors.joining("/"))
                              )
                              .collect(Collectors.joining("; "));
                           var1.sendFeedback(() -> Text.literal("Workers: [" + var34 + "]"), false);
                           return 1;
                        }
                     }
                  )
            )
      );
   }

   private static String x3u8cqfu11cpc6(List<VillagerEntity> var0, MemoryModuleType<BlockPos> var1) {
      return var0.stream()
         .flatMap(
            var1x -> var1x.getBrain()
               .getOptionalRegisteredMemory(var1)
               .stream()
               .map(var1xx -> var1x.getUuidAsString().substring(0, 8) + "@" + var1xx.toShortString())
         )
         .collect(Collectors.joining("; "));
   }

   private static String x2hdxge3ubqygw(ItemStack var0) {
      return var0.isEmpty() ? "-" : Registries.ITEM.getId(var0.getItem()).getPath();
   }

   private static String x224hvqfelnea1(ConstructionJob var0, List<VillagerEntity> var1, long var2) {
      String var4 = var0.x3vu8mnvg0blk4()
         .flatMap(var1x -> var1.stream().filter(var1xx -> var1xx.getUuid().equals(var1x)).findFirst())
         .flatMap(var0x -> var0x.getBrain().getOptionalRegisteredMemory(ColonyMemoryModules.x1r15huley8x9h))
         .<String>map(Vec3i::toShortString)
         .orElse("-");
      String var5 = var0.x16o43azbz1opa() == Long.MIN_VALUE ? "-" : Long.toString(Math.max(0L, var2 - var0.x16o43azbz1opa()) / 20L);
      return var0.x32oz43hmeynhc().name().toLowerCase()
         + "@"
         + var0.x3ij6gescvakv2().toShortString()
         + " template="
         + var0.x3kqe1qjsw9oty().getPath().substring(var0.x3kqe1qjsw9oty().getPath().lastIndexOf(47) + 1)
         + " blocks="
         + var0.xmu75rcopbjnh()
         + "/"
         + var0.xxjwqetbgzzle()
         + " retry="
         + Math.max(0L, var0.x2zq2smhbu3lzo() - var2) / 20L
         + "s owner="
         + var0.x2nfrt8qxdscso().map(var1x -> var1x.toString().substring(0, 8) + " L" + var0.x37hzrzyr2xivs()).orElse("-")
         + " idle="
         + var5
         + "s supplyStall="
         + var0.xbnccd29nwem4() / 20
         + "s target="
         + var4;
   }
}
