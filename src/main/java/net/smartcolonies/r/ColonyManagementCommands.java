package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.village.VillagerType;
import net.minecraft.world.Heightmap.Type;

public final class ColonyManagementCommands {
   private static final Map<ServerWorld, Map<UUID, FilmDevelopmentSession>> x2jxf155fb1smi = new IdentityHashMap<>();
   private static final int x16fg2ntpvc4ue = 12000;

   private ColonyManagementCommands() {
   }

   public static boolean x2d8qtehqomuj6(ServerWorld var0) {
      return x2jxf155fb1smi.containsKey(var0);
   }

   public static void xevco0r0wp03v(ServerWorld var0) {
      x2jxf155fb1smi.computeIfAbsent(var0, var0x -> new HashMap<>());
   }

   public static void x3evocl9yxshic(ServerWorld var0) {
      Map<UUID, FilmDevelopmentSession> var1 = x2jxf155fb1smi.remove(var0);
      if (var1 != null) {
         var1.forEach((var1x, var2) -> ColonyState.get(var0).byId(var1x).ifPresent(var2x -> xilv8zdrjafo6(var0, var2x, var2)));
      }
   }

   public static void x1161stz4yb35x() {
      for (ServerWorld var1 : new ArrayList<>(x2jxf155fb1smi.keySet())) {
         x3evocl9yxshic(var1);
      }
   }

   public static boolean x2r8c2c8wlnbsc(ServerWorld var0, Colony var1) {
      return x2jxf155fb1smi.containsKey(var0) && x2jxf155fb1smi.get(var0).containsKey(var1.id());
   }

   public static List<VillagerEntity> x2ymjp22rympo2(ServerWorld var0, Colony var1) {
      return var0.getEntitiesByType(EntityType.VILLAGER, ColonyManager.x150ccxomrlozr(var0, var1), Entity::isAlive)
         .stream()
         .filter(var2 -> ColonyManager.x3psqezd5jem54(var0, var2.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
         .sorted(Comparator.comparing(Entity::getUuid))
         .toList();
   }

   public static CommandFeedbackResult xcqb3adsjgu8q(ServerWorld var0, Colony var1, int var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (var2 >= 2 && var2 <= Math.min(64, ColonyConfig.x2by5f9ehxvwk9().village.max_population_per_bell)) {
         ArrayList var3 = new ArrayList<>(x2ymjp22rympo2(var0, var1));
         int var4 = Math.max(var1.population(), var3.size());
         int var5 = 0;

         for (int var6 = 2; var6 <= 16 && var4 + var5 < var2; var6++) {
            for (int var7 = -var6; var7 <= var6 && var4 + var5 < var2; var7++) {
               for (int var8 = -var6; var8 <= var6 && var4 + var5 < var2; var8++) {
                  if (Math.abs(var7) == var6 || Math.abs(var8) == var6) {
                     int var9 = var1.core().getX() + var7;
                     int var10 = var1.core().getZ() + var8;
                     if (var0.getChunkManager().isChunkLoaded(var9 >> 4, var10 >> 4)) {
                        BlockPos var11 = new BlockPos(var9, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var9, var10), var10);
                        if (Math.abs(var11.getY() - var1.core().getY()) <= 4
                           && var0.getWorldBorder().contains(var11)
                           && var0.getWorldBorder().contains(var11.up())
                           && ColonyNavigationHelper.x9bnd6e2jczhd(var0, var11)
                           && !ColonyTimeService.x3gqyq7yie9jxr(var0, var11)
                           && !ColonyProtectionUtils.x3o1lagqjim5tz(var0, var11.down())
                           && !var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().contains(var11))
                           && var0.getOtherEntities(null, new Box(var11).stretch(0.0, 1.0, 0.0).expand(0.05), var0x -> var0x.isAlive() && !var0x.isSpectator())
                              .isEmpty()) {
                           VillagerEntity var12 = (VillagerEntity)EntityType.VILLAGER.create(var0, SpawnReason.COMMAND);
                           if (var12 != null) {
                              var12.setPosition(var11.getX() + 0.5, var11.getY(), var11.getZ() + 0.5);
                              var12.setVillagerData(
                                 var12.getVillagerData().withType(var0.getRegistryManager(), VillagerType.forBiome(var0.getBiome(var1.core())))
                              );
                              var12.setPersistent();
                              if (var0.spawnEntity(var12)) {
                                 var3.add(var12);
                                 var5++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         var1.x1c0m5jj5x6wpm(var4 + var5, var1.beds(), var0.getTime());
         ColonyState.get(var0).markDirty();
         return new CommandFeedbackResult(
            var5,
            "Добавлено жителей: "
               + var5
               + "; население по учёту="
               + (var4 + var5)
               + "/"
               + var2
               + "; загружено="
               + var3.size()
               + ". Профессии распределит обычная экономика; существующие жители сохранены."
         );
      } else {
         return new CommandFeedbackResult(
            0, "Допустимое население: 2.." + Math.min(64, ColonyConfig.x2by5f9ehxvwk9().village.max_population_per_bell) + ". Жители не удаляются."
         );
      }
   }

   public static CommandFeedbackResult x2b94htgx8j8wb(ServerWorld var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         if (ColonyManager.x11dwzrdveisv7(var0, var1) == 0 && var1.x2ad3s8aentmo4().isEmpty()) {
            Optional var2 = ColonyManager.x2l4eb2ibsf118(var0, var1);
            if (var2.isEmpty() || !ColonyManager.xt55z8grtuvpq(var0, var1, (BlockPos)var2.get(), StorageCategory.x2y2vmpsepzv3)) {
               return new CommandFeedbackResult(0, "Нет безопасного места для общественного склада. Личные сундуки не используются.");
            }

            var1.x1z3izo4s1trbu();
         }

         String var14 = VillagerType.forBiome(var0.getBiome(var1.core())).getValue().getPath();
         Item var3 = var14.equals("savanna") ? Items.ACACIA_LOG : (!var14.equals("taiga") && !var14.equals("snow") ? Items.OAK_LOG : Items.SPRUCE_LOG);
         Map<Item, Integer> var4 = new LinkedHashMap<>();
         var4.put(Items.BREAD, 256);
         var4.put(var3, 512);
         var4.put(Items.COBBLESTONE, 768);
         var4.put(Items.IRON_INGOT, 192);
         var4.put(Items.COAL, 128);
         var4.put(Items.DIAMOND, 16);
         var4.put(Items.WHEAT_SEEDS, 64);
         var4.put(Items.WHEAT, 64);
         var4.put(Items.CARROT, 32);
         var4.put(Items.POTATO, 32);
         var4.put(Items.PUMPKIN, 32);
         var4.put(Items.PUMPKIN_SEEDS, 32);
         var4.put(Items.EMERALD, 128);
         var4.put(Items.WHITE_WOOL, 64);
         var4.put(Items.GLASS, 128);
         var4.put(Items.SAND, 256);
         var4.put(Items.CLAY_BALL, 128);
         var4.put(Items.TORCH, 64);
         var4.put(Items.STICK, 64);
         var4.put(Items.STRING, 32);
         var4.put(Items.ARROW, 64);
         var4.put(Items.BUCKET, 4);
         var4.put(Items.SHEARS, 2);
         var4.put(
            var14.equals("savanna") ? Items.ACACIA_SAPLING : (!var14.equals("taiga") && !var14.equals("snow") ? Items.OAK_SAPLING : Items.SPRUCE_SAPLING), 32
         );
         int var5 = 0;
         int var6 = 0;

         for (Entry<Item, Integer> var8 : var4.entrySet()) {
            Item var9 = (Item)var8.getKey();
            int var10 = Math.max(0, (Integer)var8.getValue() - ColonyManager.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.isOf(var9)));

            while (var10 > 0) {
               int var11 = Math.min(var10, var9.getMaxCount());
               ItemStack var12 = new ItemStack(var9, var11);
               ColonyManager.x2cl1crde2rnxy(var0, var1, var12);
               int var13 = var11 - var12.getCount();
               var5 += var13;
               var10 -= var13;
               if (var13 < var11) {
                  var6 += var10;
                  break;
               }
            }
         }

         ColonyManager.x2u0vsam1tcmhq(var0, var1);
         ColonyState.get(var0).markDirty();
         return new CommandFeedbackResult(
            var5, "Съёмочные ресурсы добавлены в общие склады: " + var5 + "; не поместилось: " + var6 + ". Это выдача командой, не добыча жителей."
         );
      }
   }

   public static CommandFeedbackResult x2zuchidlrmdh(ServerWorld var0, Colony var1, ColonyRole var2, int var3, BlockPos var4) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (var2 != ColonyRole.x1saccptp3i00w && var2 != ColonyRole.x2xja04fpuesoh && var3 >= 1 && var3 <= 16) {
         int var5 = 0;

         for (VillagerEntity var7 : x2ymjp22rympo2(var0, var1)
            .stream()
            .sorted(
               Comparator.<VillagerEntity>comparingInt(var1x -> var1.roleOf(var1x.getUuid()) == ColonyRole.x2j116s1wug0g2 ? 0 : 1)
                  .thenComparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var4))
            )
            .toList()) {
            if (var5 >= var3) {
               break;
            }

            ColonyRole var8 = var1.roleOf(var7.getUuid());
            if (!var7.isBaby() && !var7.hasCustomer() && !var1.x3hurh64xwtw51(var7.getUuid()) && var8 != ColonyRole.x1saccptp3i00w && var8 != var2) {
               ColonyManager.x3drrj6t9qvanr(var0, var7, var2);
               var1.xvamv2rzn6h0g(var7.getUuid(), var0.getTime());
               ColonyManager.x21g1rujs9r1h6(var0, var7, var2);
               var5++;
            }
         }

         var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
         ColonyState.get(var0).markDirty();
         return new CommandFeedbackResult(var5, "Постановочно назначено " + var2.serializedName() + ": " + var5 + ". Далее действует обычный кулдаун и спрос.");
      } else {
         return new CommandFeedbackResult(0, "Специальные роли не назначаются этой командой; количество 1..16.");
      }
   }

   public static CommandFeedbackResult xl7doh5h33e42(ServerWorld var0, Colony var1, boolean var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandFeedbackResult(0, "Сначала завершите или остановите max: он пополняет запасы.");
      } else {
         int var3 = ColonyManager.x1asz375yez9sw(var0, var1, var1x -> var2 ? ColonyManager.xhahxgpa08yc9(var1x) > 0 : var1x.isIn(ItemTags.LOGS), 0);
         ColonyManager.x2u0vsam1tcmhq(var0, var1);
         ColonyState.get(var0).markDirty();
         return new CommandFeedbackResult(
            var3,
            "Из ОБЩИХ складов убрано "
               + (var2 ? "еды" : "брёвен")
               + ": "
               + var3
               + ". Личные вещи не затронуты. Профессии не подменены: ждите обычную переоценку спроса и кулдаун."
         );
      }
   }

   public static CommandFeedbackResult x2sc3yk5od1kev(ServerWorld var0, Colony var1, ColonyRole var2, BlockPos var3) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         VillagerEntity var4 = x2ymjp22rympo2(var0, var1)
            .stream()
            .filter(
               var2x -> !var2x.isBaby()
                  && !var2x.hasCustomer()
                  && !var2x.isSleeping()
                  && !var1.x3hurh64xwtw51(var2x.getUuid())
                  && var1.roleOf(var2x.getUuid()) != ColonyRole.x1saccptp3i00w
                  && var2x.getBlockPos().getSquaredDistance(var3) <= 144.0
            )
            .min(Comparator.comparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var3)))
            .orElse(null);
         if (var4 == null) {
            return new CommandFeedbackResult(0, "Подойдите ближе: нужен свободный взрослый житель в пределах 12 блоков, не старейшина и не переселенец.");
         } else {
            ColonyRole var5 = var1.roleOf(var4.getUuid());
            if (var5 == var2) {
               return new CommandFeedbackResult(0, "У ближайшего жителя уже профессия " + var2.serializedName() + ". Подойдите к другому или выберите другую роль.");
            } else {
               for (Task var7 : List.copyOf(var4.getBrain().getRunningTasks())) {
                  var7.stop(var0, var4, var0.getTime());
               }

               ColonyNavigationHelper.x2fxoqmbs0henb(var4);
               ColonyMovementPlanner.x3oupbjeq14mj8(var4);
               ColonyManager.x3drrj6t9qvanr(var0, var4, var2);
               var1.xvamv2rzn6h0g(var4.getUuid(), var0.getTime());
               ColonyManager.x21g1rujs9r1h6(var0, var4, var2);
               var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
               ColonyState.get(var0).markDirty();
               return new CommandFeedbackResult(
                  1,
                  "Постановочная смена: "
                     + var5.serializedName()
                     + " → "
                     + var2.serializedName()
                     + "; житель "
                     + var4.getUuid()
                     + "; координаты "
                     + var4.getBlockPos().toShortString()
                     + ". Скин и инструменты меняются обычной логикой."
               );
            }
         }
      }
   }

   public static CommandFeedbackResult x32g8vo9hf8rm3(ServerWorld var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else {
         List<VillagerEntity> var2 = x2ymjp22rympo2(var0, var1);
         Map<UUID, ColonyRole> var3 = new HashMap<>();
         var2.forEach(var2x -> var3.put(var2x.getUuid(), var1.roleOf(var2x.getUuid())));
         ColonyManager.xsd7almzt4rct(var0, var1, var2);
         ArrayList var4 = new ArrayList();

         for (VillagerEntity var6 : var2) {
            if (var3.get(var6.getUuid()) != var1.roleOf(var6.getUuid())) {
               var4.add(
                  ((ColonyRole)var3.get(var6.getUuid())).serializedName()
                     + " → "
                     + var1.roleOf(var6.getUuid()).serializedName()
                     + " @ "
                     + var6.getBlockPos().toShortString()
               );
            }
         }

         return new CommandFeedbackResult(
            var4.size(),
            "Переоценка реального спроса: "
               + (var4.isEmpty() ? "изменений нет (проверьте избыток профессий, кулдаун 120 с, груз, торговлю и активную стройку)" : String.join("; ", var4))
               + ". "
               + ResourceScoutingHelper.x32tk2361rdt82(var1)
         );
      }
   }

   public static CommandFeedbackResult x241eq3b06s3l0(ServerWorld var0, Colony var1, BlockPos var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (!x2r8c2c8wlnbsc(var0, var1) && !var1.x2a380ray4md5t().isPresent() && !VillagerRaidShelterTask.active(var0, var1)) {
         CommandFeedbackResult var3 = x2b94htgx8j8wb(var0, var1);
         if (var1.x2ad3s8aentmo4().isEmpty()) {
            return var3;
         } else {
            if (ColonyManager.x11dwzrdveisv7(var0, var1) < 27) {
               Optional var4 = ColonyManager.x2l4eb2ibsf118(var0, var1);
               if (var4.isPresent()
                  && ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS)) >= 4
                  && ColonyManager.xt55z8grtuvpq(var0, var1, (BlockPos)var4.get(), StorageCategory.x2y2vmpsepzv3)) {
                  ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 4);
               }
            }

            xcqb3adsjgu8q(var0, var1, 24);
            x2zuchidlrmdh(var0, var1, ColonyRole.x35csp4lojh59r, 2, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.x8jydaiei7iry, 4, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.x2hwljbtybtlwc, 2, var2);
            x2zuchidlrmdh(var0, var1, ColonyRole.x18g4mqx9kxs89, 3, var2);
            return new CommandFeedbackResult(
               1,
               "Постановочно подготовлены склад, население и работники; расчёт ещё НЕ запускался. Снимите деревню ДО, затем film develop 3, затем film develop status. "
                  + var3.x2fa4oag1sp0w9()
            );
         }
      } else {
         return new CommandFeedbackResult(0, "Завершите max, экспедицию или нападение перед подготовкой сцены.");
      }
   }

   public static CommandFeedbackResult x1uwgtgswk0hva(ServerWorld var0, Colony var1, int var2) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandFeedbackResult(0, "Развитие max уже выполняется.");
      } else {
         VillageConfig var3 = ColonyConfig.x2by5f9ehxvwk9().village;
         if (ColonyProtectionUtils.x35ev8l9vc9uk7(var0) && var3.offline_development && var3.offline_max_days != 0) {
            if (x2ymjp22rympo2(var0, var1).size() < 2) {
               return new CommandFeedbackResult(0, "Нужно хотя бы два жителя.");
            } else {
               int var4 = Math.min(var2, var3.offline_max_days);
               if (var4 < 1) {
                  return new CommandFeedbackResult(0, "Допустимые дни: 1..30.");
               } else {
                  var1.xhwx3flu2btqb(x2ymjp22rympo2(var0, var1));
                  long var5 = var1.x1ja61emeln2hc()
                     .stream()
                     .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6 && var0x.x2nfrt8qxdscso().isEmpty())
                     .count();
                  var1.x14t0iw6xpk0xx(var0.getTime() - var4 * 24000L);
                  ColonySimulationService.x3ij59k18pnfmd(var0, var1);
                  var1.xs28n9ortj0j6(0L);
                  ColonyState.get(var0).markDirty();
                  return new CommandFeedbackResult(
                     var4,
                     "Смоделировано отсутствие "
                        + var4
                        + " игровых дней без перевода часов мира. До расчёта готовых новых общественных домов="
                        + var5
                        + ". "
                        + ColonySimulationService.x1rq82fkez3pr7(var0, var1)
                        + ". Заработанные дома применяются ускоренно на безопасных загруженных участках; если труд=0, проверьте еду и строителей. Очередь сохраняется до выполнения. Это демонстрация обычной формулы, не добыча в реальном времени."
                  );
               }
            }
         } else {
            return new CommandFeedbackResult(0, "Нужны разрешённые изменения ландшафта и offline_development с ненулевым лимитом дней.");
         }
      }
   }

   public static CommandFeedbackResult x3hszkva73cyco(ServerWorld var0, Colony var1) {
      if (!x2d8qtehqomuj6(var0)) {
         return xfqwmmra1djk();
      } else if (x2r8c2c8wlnbsc(var0, var1)) {
         return new CommandFeedbackResult(0, "max уже выполняется; /smartcolonies film status покажет состояние.");
      } else {
         ColonyConfig var2 = ColonyConfig.x2by5f9ehxvwk9();
         if (!ColonyProtectionUtils.x35ev8l9vc9uk7(var0) || !var2.roles.enable_auto_expansion || !var2.village.offline_development) {
            return new CommandFeedbackResult(0, "Для max нужны изменения ландшафта, авторасширение и offline_development. Конфиг команда не меняет.");
         } else if (VillagerRaidShelterTask.active(var0, var1)) {
            return new CommandFeedbackResult(0, "Во время нападения подготовка max не запускается.");
         } else if (!var1.x2a380ray4md5t().isPresent() && var1.x11xljsmcmocmw() <= 0 && var1.x2p9t1nvxo82zp() <= 0 && var1.x1i0d1gs2hqpsr().isEmpty()) {
            int var3 = Math.min(12, var2.village.max_constructed_houses);
            FilmDevelopmentSession var4 = new FilmDevelopmentSession(var0.getTime() + 12000L, var3);
            x2jxf155fb1smi.get(var0).put(var1.id(), var4);
            var1.x38l97g8nvc9ui(true);
            x2b94htgx8j8wb(var0, var1);
            xcqb3adsjgu8q(var0, var1, Math.min(48, var2.village.max_population_per_bell));
            var1.xs28n9ortj0j6(0L);
            return new CommandFeedbackResult(
               1,
               "Подготовка развитой деревни запущена: цель "
                  + var3
                  + " новых общественных домов и до 48 жителей. Это ускоренная постановка, не мгновенная замена деревни. Лимит 10 минут; нужны загруженные участки. Остановить: film stop."
            );
         } else {
            return new CommandFeedbackResult(0, "Сначала завершите активную экспедицию или расчёт офлайн-развития.");
         }
      }
   }

   public static CommandFeedbackResult x2dwx84ddtzhb0(ServerWorld var0, Colony var1) {
      Map var2 = x2jxf155fb1smi.get(var0);
      FilmDevelopmentSession var3 = var2 == null ? null : (FilmDevelopmentSession)var2.remove(var1.id());
      if (var3 == null) {
         return new CommandFeedbackResult(0, "Ускоренная подготовка этой деревни не запущена.");
      } else {
         xilv8zdrjafo6(var0, var1, var3);
         return new CommandFeedbackResult(1, "Ускорение остановлено. Построенные блоки, выданные ресурсы и новые жители остаются; это не откат мира.");
      }
   }

   private static void xilv8zdrjafo6(ServerWorld var0, Colony var1, FilmDevelopmentSession var2) {
      var2.x1bjciaf2920n9.forEach(var0x -> var0x.xkv5b17xl7vd6(false));
      var1.x38l97g8nvc9ui(false);
      var1.x23n2tdykuxd7a(0);
      var1.x20lu0dxozk5g(-var1.x11xljsmcmocmw());
      var1.xemejsnsuhts2(var0.getTime());
      ColonyState.get(var0).markDirty();
   }

   public static void x2twncqk5nkey3(ServerWorld var0) {
      Map var1 = x2jxf155fb1smi.get(var0);
      if (var1 != null && !var1.isEmpty() && var0.getTime() % 20L == 0L) {
         Iterator var2 = var1.entrySet().iterator();

         while (var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            Colony var4 = ColonyState.get(var0).byId((UUID)var3.getKey()).orElse(null);
            if (var4 == null) {
               var2.remove();
            } else {
               FilmDevelopmentSession var5 = (FilmDevelopmentSession)var3.getValue();
               long var6 = var4.x1ja61emeln2hc()
                  .stream()
                  .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6 && var0x.x2nfrt8qxdscso().isEmpty())
                  .count();
               if (var6 < var5.xzklk0cnnmaka
                  && var0.getTime() < var5.xvy74q5kam3tt
                  && ColonyProtectionUtils.x35ev8l9vc9uk7(var0)
                  && ColonyConfig.x2by5f9ehxvwk9().village.offline_development
                  && ColonyConfig.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
                  if (var0.shouldTickEntityAt(var4.core()) && var0.getChunkManager().isChunkLoaded(var4.core().getX() >> 4, var4.core().getZ() >> 4)) {
                     if (var0.getTime() % 200L == 0L) {
                        x2b94htgx8j8wb(var0, var4);
                     }

                     var4.x23n2tdykuxd7a((int)Math.min(4L, var5.xzklk0cnnmaka - var6));
                     var4.x20lu0dxozk5g(12000 - var4.x11xljsmcmocmw());
                     var4.xemejsnsuhts2(var0.getTime() + 12000L);

                     for (ConstructionJob var9 : var4.x1ja61emeln2hc()) {
                        if (var9.x2nfrt8qxdscso().isEmpty() && var9.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6) {
                           var9.xkv5b17xl7vd6(true);
                           var5.x1bjciaf2920n9.add(var9);
                        }
                     }

                     ColonySimulationService.x1yz1dkagfdrrb(var0, var4);
                     ColonyState.get(var0).markDirty();
                  }
               } else {
                  xilv8zdrjafo6(var0, var4, var5);
                  var2.remove();
               }
            }
         }
      }
   }

   public static String x24jh62s2oq06y(ServerWorld var0, Colony var1) {
      FilmDevelopmentSession var2 = x2jxf155fb1smi.getOrDefault(var0, Map.of()).get(var1.id());
      long var3 = var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6 && var0x.x2nfrt8qxdscso().isEmpty())
         .count();
      return "Съёмочный режим="
         + x2d8qtehqomuj6(var0)
         + "; max="
         + (var2 == null ? "выключен" : var3 + "/" + var2.xzklk0cnnmaka + ", осталось " + Math.max(0L, var2.xvy74q5kam3tt - var0.getTime()) / 20L + " с")
         + "; жителей="
         + x2ymjp22rympo2(var0, var1).size()
         + "; готовых новых общественных домов="
         + var3
         + "; "
         + ColonySimulationService.x1rq82fkez3pr7(var0, var1)
         + "; экспедиция="
         + var1.x2a380ray4md5t().map(UUID::toString).orElse("нет")
         + "; сопровождающих="
         + var1.x2zftltl6vhwwx().size()
         + "; цель="
         + var1.x1x0agg3sdxjk8().<String>map(Vec3i::toShortString).orElse("-");
   }

   private static CommandFeedbackResult xfqwmmra1djk() {
      return new CommandFeedbackResult(0, "Сначала /smartcolonies film on. Только на копии мира: команды меняют население, ресурсы и постройки.");
   }
}
