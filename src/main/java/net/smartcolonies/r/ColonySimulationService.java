package net.smartcolonies.r;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus;

public final class ColonySimulationService {
   private static final Map<ConstructionJob, List<BlockPlacementEntry>> x1m6javv76y909 = new WeakHashMap<>();

   private ColonySimulationService() {
   }

   public static void x2rwu2l29t4lpj() {
      x1m6javv76y909.clear();
   }

   public static void x3ij59k18pnfmd(ServerWorld var0, Colony var1) {
      long var2 = var0.getTime();
      long var4 = var1.x1t2gtpnwtd60z();
      if (var4 != var2) {
         if (var1.population() >= 2 || var1.x216l9aitzit41()) {
            boolean var6 = var0.shouldTickEntityAt(var1.core()) || var1.x3tnjjviw7kr77() >= var2 - 1L;
            if (var6 || var1.x216l9aitzit41() || var4 == Long.MIN_VALUE || var2 <= var4 || var2 - var4 >= 1200L || var1.x3p06s7riestqm() != 0L) {
               var1.x14t0iw6xpk0xx(var2);
               VillageConfig var7 = ColonyConfig.x2by5f9ehxvwk9().village;
               if (var4 == Long.MIN_VALUE) {
                  ColonyManager.xchl1k1tnh3w8(var0).markDirty();
               }

               if (var4 != Long.MIN_VALUE
                  && var2 > var4
                  && (var2 - var4 >= 1200L || var1.x216l9aitzit41() || var1.x3p06s7riestqm() != 0L)
                  && var7.offline_development
                  && ColonyProtectionUtils.x35ev8l9vc9uk7(var0)) {
                  long var8 = var2 - var4 < 1200L && !var1.x216l9aitzit41() && var6 ? 0L : var2 - var4;
                  long var10 = Math.min(var8 + var1.x3p06s7riestqm(), var7.offline_max_days * 24000L);
                  if (var10 > 0L) {
                     if (!var1.x216l9aitzit41() && xk3x156edihju(var0, var1, var10 / 24000.0)) {
                        var1.xtcqji4527aor(var10);
                        ColonyManager.xchl1k1tnh3w8(var0).markDirty();
                     } else {
                        var1.xtcqji4527aor(0L);
                        var1.x3vh0r7o2vtxxq(var10);
                        double var12 = var10 / 24000.0;
                        boolean var14 = var1.x216l9aitzit41();
                        if (var14) {
                           var1.x1nmmpj859zi63();
                        }

                        var1.xqdbjfmq8abgh(false);
                        int var15 = Math.max(var14 ? 8 : 2, var1.population());
                        int var16 = var1.x18two0xs8s6p9(ColonyRole.x8jydaiei7iry);
                        int var17 = var1.x18two0xs8s6p9(ColonyRole.xhds9crfjdp9i);
                        int var18 = var1.x18two0xs8s6p9(ColonyRole.xeoehg87qwfrn);
                        int var19 = var1.x18two0xs8s6p9(ColonyRole.x2hwljbtybtlwc);
                        int var20 = var1.x18two0xs8s6p9(ColonyRole.x18g4mqx9kxs89);
                        int var21 = var1.x18two0xs8s6p9(ColonyRole.x35csp4lojh59r);
                        if (var14) {
                           var16 = Math.max(1, var16);
                           var17 = Math.max(1, var17);
                           var18 = Math.max(1, var18);
                           var19 = Math.max(1, var19);
                           var20 = Math.max(1, var20);
                           var21 = Math.max(1, var21);
                        }

                        ColonyManager.x2u0vsam1tcmhq(var0, var1);
                        double var22 = var12 * (var16 * 24 * 5 + var17 * 18 * 5 + var18 * 12 * 8);
                        double var24 = var12 * var15 * 10.0;
                        double var26 = Math.clamp((var1.cachedFood() + var22) / Math.max(1.0, var24), 0.0, 1.0);
                        int var28 = (int)Math.min((double)var1.cachedFood(), Math.ceil(Math.max(0.0, var24 - var22)));
                        if (var28 > 0) {
                           ColonyManager.x2o11j2ko0ev05(var0, var1, var28);
                        }

                        double var29 = Math.max(0.0, var22 - var24) / Math.max(1.0, var22);
                        x1snasam03o544(var1, Items.BREAD, var12 * var16 * 24.0 * var29);
                        x1snasam03o544(var1, Items.COOKED_COD, var12 * var17 * 18.0 * var29);
                        x1snasam03o544(var1, Items.COOKED_BEEF, var12 * var18 * 12.0 * var29);
                        x1snasam03o544(var1, xzhi8fvcu7goa(var0, var1), var12 * var26 * var19 * 256.0);
                        x1snasam03o544(var1, Items.COBBLESTONE, var12 * var26 * var20 * 384.0);
                        x1snasam03o544(var1, Items.IRON_INGOT, var12 * var26 * var20 * 12.0);
                        x1snasam03o544(var1, Items.COAL, var12 * var26 * var20 * 16.0);
                        x1snasam03o544(var1, Items.DIAMOND, var12 * var26 * var20 * 0.5);
                        boolean var31 = var26 >= 0.75;
                        if (var31 && var21 > 0 && var7.offline_max_days > 0) {
                           var1.x20lu0dxozk5g(var1.x275t1qstogox8(var12 * var26 * var21 * 1000.0));
                           var1.x23n2tdykuxd7a(var1.x2p9t1nvxo82zp() + var1.x3eazb9acbxmlj(var12 * var26 * var21));
                           x1yz1dkagfdrrb(var0, var1);
                        }

                        if (var31) {
                           var1.x1hehk3f3lrpr(var1.x3h8wqo6wk0f7p() + var1.x3eljz0p0x6yek(var12 * var26 * Math.max(1, var15 / 8)));
                        }

                        var1.xemejsnsuhts2(Long.MAX_VALUE);
                        ColonyManager.xchl1k1tnh3w8(var0).markDirty();
                     }
                  }
               }
            }
         }
      }
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   private static boolean xk3x156edihju(ServerWorld var0, Colony var1, double var2) {
      double var4 = var2 * Math.max(2, var1.population()) * 10.0;
      double var6 = var2
         * (
            var1.x18two0xs8s6p9(ColonyRole.x8jydaiei7iry) * 120
               + var1.x18two0xs8s6p9(ColonyRole.xhds9crfjdp9i) * 90
               + var1.x18two0xs8s6p9(ColonyRole.xeoehg87qwfrn) * 96
         );
      if (var6 >= var4) {
         return false;
      } else {
         boolean var8 = false;
         int var9 = 0;
         Iterator var10 = var1.x2ad3s8aentmo4().iterator();

         label60:
         while (true) {
            if (!var10.hasNext()) {
               int var10000 = 90523992 ^ -1900893688;

               while (true) {
                  switch (var10000) {
                     case -1948785840:
                     default:
                        if (var8 && var9 + var6 < var4) {
                           return true;
                        }

                        var10000 = -340626074 ^ 534678421;
                        break;
                     case -194183437:
                        return false;
                  }
               }
            }

            BlockPos var11 = (BlockPos)var10.next();
            WorldChunk var12 = var0.getChunkManager().getWorldChunk(var11.getX() >> 4, var11.getZ() >> 4);
            if (var12 == null) {
               var8 = true;
            } else {
               if (var12.getBlockEntity(var11) instanceof Inventory var13) {
                  for (int var15 = 0; var15 < var13.size(); var15++) {
                     var9 += ColonyManager.xhahxgpa08yc9(var13.getStack(var15)) * var13.getStack(var15).getCount();
                  }
               }

               switch (255250274 ^ -1405242498) {
                  case -1559528420:
                  default:
                     break;
                  case -1253416605:
                     continue;
               }
            }

            while (true) {
               label50:
               switch (2044294574 ^ -862776115) {
                  case -1559528420:
                  default:
                     break;
                  case -1253416605:
                     continue label60;
               }
            }
         }
      }
   }

   public static void x1yz1dkagfdrrb(ServerWorld var0, Colony var1) {
      if (var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0) {
         int var2 = var1.x2p9t1nvxo82zp();

         for (ConstructionJob var4 : var1.x1ja61emeln2hc()) {
            if (var2 <= 0) {
               break;
            }

            if (!var4.x2nfrt8qxdscso().isPresent()
               && !var4.x2s8emavervqrz()
               && var4.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6
               && (var4.x32oz43hmeynhc() != ConstructionStatus.x3vi306qg1ms75 || var4.x2zq2smhbu3lzo() != 0L)) {
               var4.xkv5b17xl7vd6(true);
               var4.x3vu8mnvg0blk4().ifPresent(var1x -> {
                  if (var0.getEntity(var1x) instanceof VillagerEntity var2x) {
                     for (Task var4x : List.copyOf(var2x.getBrain().getRunningTasks())) {
                        if (var4x instanceof BuilderConstructionTask) {
                           var4x.stop(var0, var2x, var0.getTime());
                        }
                     }
                  }
               });
               var4.x23bey8uos6gyt();
               var2--;
            }
         }
      }
   }

   private static Item xzhi8fvcu7goa(ServerWorld var0, Colony var1) {
      String var2 = var0.getBiome(var1.core()).getKey().map(var0x -> var0x.getValue().getPath()).orElse("plains");
      return var2.contains("savanna") ? Items.ACACIA_LOG : (!var2.contains("taiga") && !var2.contains("snow") ? Items.OAK_LOG : Items.SPRUCE_LOG);
   }

   private static void x1snasam03o544(Colony var0, Item var1, double var2) {
      int var4 = var0.x1e0cfaky0ruvh(Registries.ITEM.getId(var1), var2);
      if (var4 > 0) {
         var0.x1i0d1gs2hqpsr().merge(Registries.ITEM.getId(var1), Math.min(4096, var4), (var0x, var1x) -> Math.min(4096, var0x + var1x));
      }
   }

   public static boolean xezn2zouajb9i(ServerWorld var0, Colony var1, Predicate<ItemStack> var2) {
      return var1.x1i0d1gs2hqpsr()
            .entrySet()
            .stream()
            .anyMatch(var1x -> var1x.getValue() > 0 && var2.test(new ItemStack((ItemConvertible)Registries.ITEM.get(var1x.getKey()))))
         || ColonyManager.x1tl8iu49onump(var0, var1, var2);
   }

   public static boolean x31mf0b9fst14k(ServerWorld var0, Colony var1, Predicate<ItemStack> var2) {
      Iterator var3 = var1.x1i0d1gs2hqpsr().entrySet().iterator();

      while (var3.hasNext()) {
         Entry var4 = (Entry)var3.next();
         if ((Integer)var4.getValue() > 0 && var2.test(new ItemStack((ItemConvertible)Registries.ITEM.get((Identifier)var4.getKey())))) {
            if ((Integer)var4.getValue() == 1) {
               var3.remove();
            } else {
               var4.setValue((Integer)var4.getValue() - 1);
            }

            return true;
         }
      }

      return ColonyManager.x22hjebj4vmztt(var0, var1, var2, 1);
   }

   private static int x1fctwp4pjtanw(ServerWorld var0, Colony var1, int var2) {
      int var3 = 0;
      int var4 = 0;
      Iterator var5 = var1.x1n1a2vp01kxzf().iterator();

      while (var5.hasNext() && var3 < Math.min(32, var2) && var4++ < 64 && var1.x11xljsmcmocmw() > 0) {
         BlockPos var6 = (BlockPos)var5.next();
         if (var0.getChunkManager().isChunkLoaded(var6.getX() >> 4, var6.getZ() >> 4)) {
            if (!StructurePieceHelper.xxon284tpck85(var0, var1, var6)) {
               var5.remove();
            } else if (StructurePieceHelper.x3hqfrterusj2u(var0, var6)) {
               var5.remove();
            } else {
               Predicate<ItemStack> var7 = var0x -> var0x.isIn(ItemTags.LOGS) || var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS);
               if (!xezn2zouajb9i(var0, var1, var7)) {
                  break;
               }

               BlockState var8 = var0.getBlockState(var6);
               if (ColonyProtectionUtils.x1bq0f8n53gtob(var0, var6, Blocks.DIRT_PATH.getDefaultState(), 3)) {
                  if (!x31mf0b9fst14k(var0, var1, var7)) {
                     ColonyWorldUtils.x2aebumvi646n9(() -> var0.setBlockState(var6, var8, 3));
                     break;
                  }

                  var1.x3g65nuli3tuzl();
                  var5.remove();
                  var3++;
               }
            }
         }
      }

      if (var4 > 0) {
         ColonyManager.xchl1k1tnh3w8(var0).markDirty();
      }

      return var3;
   }

   public static int x12vivbc3rlygx(ServerWorld var0, Colony var1, int var2) {
      if (!ColonyProtectionUtils.x35ev8l9vc9uk7(var0)) {
         return 0;
      } else if (!ColonyConfig.x2by5f9ehxvwk9().village.offline_development) {
         return 0;
      } else {
         if (!var1.x1i0d1gs2hqpsr().isEmpty()
            && var1.x2ad3s8aentmo4()
               .stream()
               .noneMatch(
                  var1x -> var0.getChunkManager().isChunkLoaded(var1x.getX() >> 4, var1x.getZ() >> 4) && var0.getBlockEntity(var1x) instanceof Inventory
               )) {
            ColonyManager.x2l4eb2ibsf118(var0, var1).ifPresent(var2x -> {
               if (ColonyManager.xt55z8grtuvpq(var0, var1, var2x, StorageCategory.x2y2vmpsepzv3)) {
                  var1.x1z3izo4s1trbu();
                  ColonyManager.xchl1k1tnh3w8(var0).markDirty();
               }
            });
         }

         if (var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0 && var2 > 0) {
            x1yz1dkagfdrrb(var0, var1);
            ColonyManager.x32npq1dyt2s4h(var0, var1);
         }

         boolean var3 = var1.x1ja61emeln2hc().stream().anyMatch(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6);
         if (var0.getTime() % 5L == 0L && !var1.x1i0d1gs2hqpsr().isEmpty()) {
            int var4 = 0;
            Iterator var5 = var1.x1i0d1gs2hqpsr().entrySet().iterator();

            while (var5.hasNext() && var4++ < 16) {
               Entry var6 = (Entry)var5.next();
               Item var7 = (Item)Registries.ITEM.get((Identifier)var6.getKey());
               if (!var3 || !new ItemStack(var7).isIn(ItemTags.LOGS) && !new ItemStack(var7).isIn(ItemTags.STONE_CRAFTING_MATERIALS)) {
                  int var8 = Math.min((Integer)var6.getValue(), var7.getMaxCount());
                  ItemStack var9 = new ItemStack(var7, var8);
                  ColonyManager.x2cl1crde2rnxy(var0, var1, var9);
                  int var10 = var8 - var9.getCount();
                  if (var10 > 0) {
                     var6.setValue((Integer)var6.getValue() - var10);
                     ColonyManager.xchl1k1tnh3w8(var0).markDirty();
                  }

                  if ((Integer)var6.getValue() <= 0) {
                     var5.remove();
                  }
               }
            }
         }

         if (var1.x3h8wqo6wk0f7p() > 0 && var0.getTime() % 10L == 0L && var0.shouldTickEntityAt(var1.core())) {
            x25bgjce4mxb68(var0, var1);
         }

         ConstructionJob var12 = var1.x1ja61emeln2hc()
            .stream()
            .filter(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6)
            .findFirst()
            .orElse(null);
         int var13 = var1.x2p9t1nvxo82zp() > 0 ? x1fctwp4pjtanw(var0, var1, var2) : 0;
         var2 -= var13;
         if (var12 == null || var2 <= 0) {
            return var13;
         } else if (var1.x11xljsmcmocmw() <= 0) {
            var12.xkv5b17xl7vd6(false);
            return var13;
         } else {
            List var14 = x1m6javv76y909.computeIfAbsent(var12, var1x -> BuilderConstructionTask.loadPlan(var0, var1x).orElse(List.of()));
            if (var14.isEmpty()) {
               var12.xkv5b17xl7vd6(false);
               x1m6javv76y909.remove(var12);
               return var13;
            } else {
               int var15 = 0;

               for (int var16 = 0; var16 < 16 && var15 < var2 && var12.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6; var16++) {
                  int var17 = var12.xmu75rcopbjnh();
                  var15 += BuilderConstructionTask.catchupStep(var0, var1, var12, var14, var2 - var15);
                  if (var12.xmu75rcopbjnh() == var17) {
                     break;
                  }
               }

               if (var12.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6) {
                  var12.xkv5b17xl7vd6(false);
                  x1m6javv76y909.remove(var12);
                  var1.x23n2tdykuxd7a(var1.x2p9t1nvxo82zp() - 1);
                  ColonyManager.xchl1k1tnh3w8(var0).markDirty();
               }

               return var15 + var13;
            }
         }
      }
   }

   private static void x25bgjce4mxb68(ServerWorld var0, Colony var1) {
      List var2 = var0.getEntitiesByType(EntityType.VILLAGER, ColonyManager.x150ccxomrlozr(var0, var1), var0x -> var0x.isAlive());
      int var3 = ColonyConfig.x2by5f9ehxvwk9().village.max_population_per_bell;
      int var4 = (int)var0.getPointOfInterestStorage()
         .count(var0x -> var0x.matchesKey(PointOfInterestTypes.HOME), var1.core(), var1.radius(), OccupationStatus.ANY);
      if (var2.size() < Math.min(var3, var4) && var2.size() >= 2) {
         BlockPos var5 = ColonyManager.xcmgrg6f6maj1(var0, var1, var2.size()).filter(var1x -> !ColonyTimeService.x3gqyq7yie9jxr(var0, var1x)).orElse(null);
         if (var5 != null) {
            VillagerEntity var6 = (VillagerEntity)EntityType.VILLAGER.create(var0, SpawnReason.BREEDING);
            if (var6 != null) {
               var6.setPosition(var5.getX() + 0.5, var5.getY(), var5.getZ() + 0.5);
               var6.setBreedingAge(-1800);
               var6.setPersistent();
               var6.setVillagerData(((VillagerEntity)var2.getFirst()).getVillagerData().withProfession(var0.getRegistryManager(), VillagerProfession.NONE));
               if (ColonyManager.x38ipozok9mc0x(var0, var1, () -> ColonyManager.x2o11j2ko0ev05(var0, var1, 24) && var0.spawnEntity(var6), List.of())) {
                  var1.x36xpmo21hmp9h(var6.getUuid(), ColonyRole.x2j116s1wug0g2);
                  var1.x1hehk3f3lrpr(var1.x3h8wqo6wk0f7p() - 1);
                  ColonyManager.xchl1k1tnh3w8(var0).markDirty();
               }
            }
         }
      }
   }

   public static String x2ypihddkgbmhu(Colony var0) {
      return "уровень="
         + var0.x3sjn9mskj5v9f()
         + ", учтено отсутствия="
         + var0.x2ya2lfuvm01ea() / 20L
         + " с, запас на выдачу="
         + var0.x1i0d1gs2hqpsr().values().stream().mapToInt(Integer::intValue).sum()
         + ", труд="
         + var0.x11xljsmcmocmw()
         + ", домов к достройке="
         + var0.x2p9t1nvxo82zp()
         + ", прирост="
         + var0.x3h8wqo6wk0f7p();
   }

   public static String x1rq82fkez3pr7(ServerWorld var0, Colony var1) {
      ConstructionJob var2 = var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x3fgv5ft5sp7zt() && var0x.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6)
         .findFirst()
         .orElse(null);
      String var3 = var2 == null
         ? ColonyManager.x2y69zyuty3rxs(var0, var1)
         : var2.x3ij6gescvakv2().toShortString() + " " + var2.xmu75rcopbjnh() + "/" + var2.xxjwqetbgzzle();
      return x2ypihddkgbmhu(var1)
         + "; склады="
         + var1.x2ad3s8aentmo4().size()
         + ", свободных слотов="
         + ColonyManager.x11dwzrdveisv7(var0, var1)
         + "; еда/брёвна/камень="
         + var1.cachedFood()
         + "/"
         + var1.cachedLogs()
         + "/"
         + var1.cachedStone()
         + "; стройка="
         + var3
         + "; учтено до тика="
         + var1.x1t2gtpnwtd60z()
         + "; ожидает данных склада="
         + var1.x3p06s7riestqm() / 20L
         + " с";
   }
}
