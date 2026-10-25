package net.smartcolonies.r;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.village.VillagerType;
import net.minecraft.world.Heightmap.Type;

public final class ColonyBiomeHelper {
   private ColonyBiomeHelper() {
   }

   public static void xyvb622rqbii1(ServerWorld var0, Colony var1, long var2) {
      ExpansionPlan var4 = var1.x2f7z1gnnht5ab();
      if (var2 % 5L == 0L) {
         x2wa9qjwcgiun6(var0, var1);
      }

      if (ColonyConfig.x2by5f9ehxvwk9().roles.enable_village_walls) {
         long completedHouses = var1.x1ja61emeln2hc().stream().filter(ConstructionJob::x2s8emavervqrz).count();
         if (completedHouses >= 3 && var2 >= var4.x37dzr2zekycme && !var1.x2s37os1kht7vr() && !var1.x2ad3s8aentmo4().isEmpty() && !VillagerRaidShelterTask.active(var0, var1)) {
            var4.x37dzr2zekycme = var2 + 1200L;
            int var5 = x2p7mtd5kxiire(var0, var1);
            if (var4.x14zjd0to6v6yv != 0 && var5 <= var4.x14zjd0to6v6yv) {
               x1afpp1y1qlxxi(var0, var1, var4.x14zjd0to6v6yv);
               x2r4heaqeaed6q(var0, var1);
            } else {
               if (var5 > var1.radius() - 4) {
                  if (var5 + 8 > ColonyConfig.x2by5f9ehxvwk9().village.expansion_radius_max) {
                     return;
                  }

                  var1.x1ne2dy4ss3d7w(var5 + 8);
               }

               x13dxs2zmn9lxz(var0, var1, var5);
               ColonyManager.xchl1k1tnh3w8(var0).markDirty();
            }
         }
      }
   }

   public static int x2p7mtd5kxiire(ServerWorld var0, Colony var1) {
      double var2 = 48.0;
      StructureStart var4 = var0.getStructureAccessor().getStructureContaining(var1.core(), StructureTags.VILLAGE);
      if (var4 != StructureStart.DEFAULT) {
         for (StructurePiece var6 : var4.getChildren()) {
            BlockBox var7 = var6.getBoundingBox();

            for (int var11 : new int[]{var7.getMinX(), var7.getMaxX()}) {
               for (int var15 : new int[]{var7.getMinZ(), var7.getMaxZ()}) {
                  var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var11, var15)));
               }
            }
         }
      }

      for (ConstructionJob var20 : var1.x1ja61emeln2hc()) {
         if (var20.x32oz43hmeynhc() != ConstructionStatus.x3vi306qg1ms75 || var20.x2zq2smhbu3lzo() != 0L) {
            BlockBox var24 = var20.x24kbqv6g4kkrg();

            for (int var32 : new int[]{var24.getMinX(), var24.getMaxX()}) {
               for (int var40 : new int[]{var24.getMinZ(), var24.getMaxZ()}) {
                  var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var32, var40)) + 10.0);
               }
            }
         }
      }

      for (BlockPos var21 : var1.x1hdthtpd2qb8g()) {
         var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var21.getX(), var21.getZ())));
      }

      for (BlockPos var22 : var1.x29j0o9zdxri2().keySet()) {
         var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var22.getX(), var22.getZ())));
      }

      if (var1.x329rgb9lpsari().isPresent()) {
         BlockPos var19 = var1.x329rgb9lpsari().get();
         byte var23 = 5;

         for (int var31 : new int[]{-var23, var23}) {
            for (int var39 : new int[]{-var23, var23}) {
               var2 = Math.max(var2, Math.sqrt(x1qj4tky9v09mn(var1.core(), var19.getX() + var31, var19.getZ() + var39)));
            }
         }
      }

      return (int)Math.ceil((var2 + 8.0) / 8.0) * 8;
   }

   private static double x1qj4tky9v09mn(BlockPos var0, int var1, int var2) {
      double var3 = var1 - var0.getX();
      double var5 = var2 - var0.getZ();
      return var3 * var3 + var5 * var5;
   }

   public static void x13dxs2zmn9lxz(ServerWorld var0, Colony var1, int var2) {
      ExpansionPlan var3 = var1.x2f7z1gnnht5ab();
      var3.x1q0gb1h69ywu7.clear();
      var3.x1b5j5bmruqafh.clear();
      var3.xb3n8htn3gf9q.clear();
      var3.x2fh8ft5lrq7el.clear();
      var3.x3pic36dm8dawn.clear();
      var3.x16wpjsjmva3od.forEach((var1x, var2x) -> var3.x1q0gb1h69ywu7.add(new BlockStateChangeEntry(var1x, var2x, true)));
      var3.x14zjd0to6v6yv = var2;
      x1afpp1y1qlxxi(var0, var1, var2);
   }

   private static void x1afpp1y1qlxxi(ServerWorld var0, Colony var1, int var2) {
      ExpansionPlan var3 = var1.x2f7z1gnnht5ab();
      Map<BlockPos, BlockState> var4 = x5vq586vowowx(var0, var1, var2);
      HashSet var5 = new HashSet<>(var3.x3pic36dm8dawn);
      var4.forEach((var2x, var3x) -> {
         long var4x = new BlockPos(var2x.getX(), 0, var2x.getZ()).asLong();
         if (!var5.contains(var4x) && !var3.x2fh8ft5lrq7el.contains(var2x)) {
            var3.x1q0gb1h69ywu7.add(new BlockStateChangeEntry(var2x, var3x, false));
         }

         var3.x3pic36dm8dawn.add(var4x);
      });
      ColonyManager.xchl1k1tnh3w8(var0).markDirty();
   }

   private static void x2r4heaqeaed6q(ServerWorld var0, Colony var1) {
      ExpansionPlan var2 = var1.x2f7z1gnnht5ab();
      HashSet var3 = new HashSet();
      var2.x1q0gb1h69ywu7.forEach(var1x -> var3.add(var1x.x1oppt2nr5wv2g()));
      var2.x16wpjsjmva3od
         .forEach(
            (var3x, var4) -> {
               if (!var3.contains(var3x)
                  && !var2.x2fh8ft5lrq7el.contains(var3x)
                  && var0.getChunkManager().isChunkLoaded(var3x.getX() >> 4, var3x.getZ() >> 4)
                  && var0.getBlockState(var3x).isAir()) {
                  var2.x1q0gb1h69ywu7.add(new BlockStateChangeEntry(var3x, var4, false));
               }
            }
         );
   }

   public static Map<BlockPos, BlockState> x5vq586vowowx(ServerWorld var0, Colony var1, int var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      String var4 = VillagerType.forBiome(var0.getBiome(var1.core())).getValue().getPath();

      Block var5 = switch (var4) {
         case "desert" -> Blocks.SANDSTONE;
         case "snow" -> Blocks.STONE_BRICKS;
         default -> Blocks.COBBLESTONE;
      };

      Block var6 = switch (var4) {
         case "desert" -> Blocks.CUT_SANDSTONE;
         case "savanna" -> Blocks.ORANGE_TERRACOTTA;
         default -> Blocks.COBBLESTONE;
      };

      Block var21 = switch (var4) {
         case "desert" -> Blocks.CHISELED_SANDSTONE;
         case "savanna" -> Blocks.ACACIA_LOG;
         case "taiga", "snow" -> Blocks.SPRUCE_LOG;
         default -> Blocks.OAK_LOG;
      };

      Block var22 = switch (var4) {
         case "savanna" -> Blocks.ACACIA_FENCE_GATE;
         case "taiga", "snow" -> Blocks.SPRUCE_FENCE_GATE;
         default -> Blocks.OAK_FENCE_GATE;
      };
      LinkedHashSet<BlockPos> var23 = new LinkedHashSet<>();

      for (int var24 = -var2; var24 <= var2; var24++) {
         int var11 = (int)Math.round(Math.sqrt(var2 * var2 - var24 * var24));

         for (int var15 : new int[]{-1, 1}) {
            var23.add(new BlockPos(var1.core().getX() + var24, 0, var1.core().getZ() + var15 * var11));
            var23.add(new BlockPos(var1.core().getX() + var15 * var11, 0, var1.core().getZ() + var24));
         }
      }

      for (BlockPos var26 : var23) {
         int var27 = var26.getX();
         int var28 = var26.getZ();
         if (var0.getChunkManager().isChunkLoaded(var27 >> 4, var28 >> 4)) {
            int var29 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var27, var28);

            for (int var30 = 0; var30 < 20 && var29 > var0.getBottomY(); var30++) {
               BlockState var16 = var0.getBlockState(new BlockPos(var27, var29 - 1, var28));
               if (!var16.isReplaceable() && !var16.isIn(BlockTags.LEAVES) && !var16.isIn(BlockTags.LOGS)) {
                  break;
               }

               var29--;
            }

            BlockPos var31 = new BlockPos(var27, var29, var28);
            if (ColonyProtectionUtils.xroinv4hk6l7h(var0.getBlockState(var31.down())) || StructurePieceHelper.x3hqfrterusj2u(var0, var31.down())) {
               boolean var32 = Math.abs(var27 - var1.core().getX()) <= 2;
               boolean var17 = Math.abs(var28 - var1.core().getZ()) <= 1;
               boolean var18 = var0.getBlockState(var31.down()).isOf(Blocks.DIRT_PATH);
               boolean var19 = var32 || var17 || var18;
               if (var19) {
                  var3.put(var31, (BlockState)var22.getDefaultState().with(FenceGateBlock.FACING, var32 ? Direction.NORTH : Direction.EAST));
                  if (var32 && var28 > var1.core().getZ()) {
                     var3.put(var31.up(4), var5.getDefaultState());
                  }
               } else {
                  boolean var20 = Math.floorMod(var27 + var28, 6) == 0;
                  var3.put(var31, var5.getDefaultState());
                  var3.put(var31.up(), (var20 ? var21 : var6).getDefaultState());
                  var3.put(var31.up(2), (var20 ? var21 : var6).getDefaultState());
                  if (var20) {
                     var3.put(var31.up(3), var5.getDefaultState());
                  }
               }
            }
         }
      }

      return var3;
   }

   public static boolean x3hz8l3ppqisjl(ServerWorld var0, BlockPos var1) {
      return ColonyManager.xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var1));
   }

   public static boolean x3di73z4zkdknf(ServerWorld var0, BlockPos var1) {
      return var0.getBlockState(var1).getBlock() instanceof FenceGateBlock && x3hz8l3ppqisjl(var0, var1);
   }

   public static boolean x1hmaqzrqcstj(Colony var0, BlockPos var1, int var2) {
      ExpansionPlan var3 = var0.x2f7z1gnnht5ab();
      if (var3.x14zjd0to6v6yv == 0) {
         return false;
      } else {
         double var4 = Math.sqrt(x1qj4tky9v09mn(var0.core(), var1.getX(), var1.getZ()));
         return Math.abs(var4 - var3.x14zjd0to6v6yv) <= var2 + 2;
      }
   }

   public static boolean xuj80syz8c60l(ServerWorld var0, Colony var1, VillagerEntity var2) {
      boolean var3 = var1.x2f7z1gnnht5ab().x1q0gb1h69ywu7.stream().anyMatch(BlockStateChangeEntry::xttubpoe0ch3r);
      if (!var1.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()
         && ColonyConfig.x2by5f9ehxvwk9().roles.enable_village_walls
         && (var3 || var1.x1n1a2vp01kxzf().isEmpty())
         && !BuildingBlueprintHelper.x81tynoeus5lh(var0, var1)
         && !var1.x2s37os1kht7vr()
         && !VillagerRaidShelterTask.active(var0, var1)) {
         List var4 = var0.getEntitiesByType(
               EntityType.VILLAGER,
               ColonyManager.x150ccxomrlozr(var0, var1),
               var1x -> var1x.isAlive() && !var1x.isBaby() && var1.roleOf(var1x.getUuid()) == ColonyRole.x35csp4lojh59r
            )
            .stream()
            .sorted(Comparator.comparing(Entity::getUuid))
            .toList();
         if (var4.isEmpty()) {
            return false;
         } else if (var4.size() == 1 && var1.x3e4ycl8cmmmgc() && !var3) {
            return false;
         } else if (!((VillagerEntity)var4.getLast()).getUuid().equals(var2.getUuid())) {
            return false;
         } else {
            BlockStateChangeEntry var5 = x2h9dz6t7qspz2(var0, var1, var2);
            if (var5 == null) {
               return false;
            } else if (var4.size() == 1 && var1.x3e4ycl8cmmmgc() && !xccmx864swr9g(var1, var5.x1oppt2nr5wv2g())) {
               return false;
            } else {
               return var5.xttubpoe0ch3r() ? true : ColonyManager.x1mlne8tl8k5ag(var0, var1, xdo05oqfqaqzq(var5.x1bbgyigo2fq58())) > 32;
            }
         }
      } else {
         return false;
      }
   }

   public static void x15itjvs0tajaz(ServerWorld var0, Colony var1, BlockPos var2, int var3, int var4) {
      if (var1.x2f7z1gnnht5ab().x14zjd0to6v6yv != 0) {
         double var5 = 0.0;

         for (int var10 : new int[]{var2.getX() - 4, var2.getX() + var3 + 4}) {
            for (int var14 : new int[]{var2.getZ() - 4, var2.getZ() + var4 + 4}) {
               var5 = Math.max(var5, Math.sqrt(x1qj4tky9v09mn(var1.core(), var10, var14)));
            }
         }

         int var15 = (int)Math.ceil((var5 + 8.0) / 8.0) * 8;
         if (var15 > var1.x2f7z1gnnht5ab().x14zjd0to6v6yv && var15 <= var1.radius() - 4) {
            x13dxs2zmn9lxz(var0, var1, var15);
            ColonyManager.xchl1k1tnh3w8(var0).markDirty();
         }
      }
   }

   public static Predicate<ItemStack> xdo05oqfqaqzq(BlockState var0) {
      return !var0.isIn(BlockTags.LOGS) && !(var0.getBlock() instanceof FenceGateBlock)
         ? var1 -> var1.isIn(ItemTags.STONE_CRAFTING_MATERIALS)
            || var1.isOf(Items.SANDSTONE)
            || var1.isOf(Items.RED_SANDSTONE)
            || var1.isOf(var0.getBlock().asItem())
         : var1 -> var1.isIn(ItemTags.LOGS) || var1.isOf(var0.getBlock().asItem());
   }

   public static BlockStateChangeEntry x2h9dz6t7qspz2(ServerWorld var0, Colony var1, VillagerEntity var2) {
      var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.entrySet().removeIf(var1x -> var1x.getValue() <= var0.getTime());
      boolean var3 = var1.x2f7z1gnnht5ab()
         .x1q0gb1h69ywu7
         .stream()
         .anyMatch(
            var2x -> var2x.xttubpoe0ch3r()
               && !var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.containsKey(var2x.x1oppt2nr5wv2g())
               && var0.getChunkManager().isChunkLoaded(var2x.x1oppt2nr5wv2g().getX() >> 4, var2x.x1oppt2nr5wv2g().getZ() >> 4)
         );
      Map<BlockState, Boolean> var4 = new HashMap<>();
      return var1.x2f7z1gnnht5ab()
         .x1q0gb1h69ywu7
         .stream()
         .filter(
            var4x -> var4x.xttubpoe0ch3r() == var3
               && !var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.containsKey(var4x.x1oppt2nr5wv2g())
               && (
                  var4x.xttubpoe0ch3r()
                     || var4.computeIfAbsent(var4x.x1bbgyigo2fq58(), var2xx -> ColonyManager.x1mlne8tl8k5ag(var0, var1, xdo05oqfqaqzq(var2xx)) > 32)
               )
               && var0.getChunkManager().isChunkLoaded(var4x.x1oppt2nr5wv2g().getX() >> 4, var4x.x1oppt2nr5wv2g().getZ() >> 4)
         )
         .min(
            Comparator.<BlockStateChangeEntry>comparingInt(var1x -> var1x.xttubpoe0ch3r() && xccmx864swr9g(var1, var1x.x1oppt2nr5wv2g()) ? 0 : 1)
               .thenComparingDouble(var1x -> var1x.x1oppt2nr5wv2g().getSquaredDistance(var2.getBlockPos()))
         )
         .orElse(null);
   }

   private static boolean xccmx864swr9g(Colony var0, BlockPos var1) {
      return var0.xhu9xvlie05r6(var1, 2) || var0.x1n1a2vp01kxzf().stream().anyMatch(var1x -> var1x.up().getSquaredDistance(var1) <= 4.0);
   }

   public static void xj9o3at3dqqse(ServerWorld var0, Colony var1, BlockStateChangeEntry var2) {
      if (var1.x2f7z1gnnht5ab().xb3n8htn3gf9q.merge(var2.x1oppt2nr5wv2g(), 1, Integer::sum) >= 3) {
         x33x279v3webhm(var0, var1, var2);
      } else {
         var1.x2f7z1gnnht5ab().x1b5j5bmruqafh.put(var2.x1oppt2nr5wv2g(), var0.getTime() + 1200L);
      }
   }

   public static void x33x279v3webhm(ServerWorld var0, Colony var1, BlockStateChangeEntry var2) {
      ExpansionPlan var3 = var1.x2f7z1gnnht5ab();
      var3.x1q0gb1h69ywu7.remove(var2);
      var3.x1b5j5bmruqafh.remove(var2.x1oppt2nr5wv2g());
      var3.xb3n8htn3gf9q.remove(var2.x1oppt2nr5wv2g());
      var3.x2fh8ft5lrq7el.add(var2.x1oppt2nr5wv2g());
      var3.x1fvrorwquv0wt++;
      ColonyManager.xchl1k1tnh3w8(var0).markDirty();
   }

   public static void x2kvmlso28quo8(ServerWorld var0, Colony var1, BlockStateChangeEntry var2, boolean var3) {
      ExpansionPlan var4 = var1.x2f7z1gnnht5ab();
      var4.x1q0gb1h69ywu7.remove(var2);
      var4.x1b5j5bmruqafh.remove(var2.x1oppt2nr5wv2g());
      var4.xb3n8htn3gf9q.remove(var2.x1oppt2nr5wv2g());
      if (var2.xttubpoe0ch3r()) {
         var4.x16wpjsjmva3od.remove(var2.x1oppt2nr5wv2g());
      } else if (var3) {
         var4.x16wpjsjmva3od.put(var2.x1oppt2nr5wv2g(), var2.x1bbgyigo2fq58());
      } else {
         var4.x1fvrorwquv0wt++;
      }

      var1.x2xqc3q79vfkw3().remove(var2.x1oppt2nr5wv2g());
      ColonyManager.xchl1k1tnh3w8(var0).markDirty();
   }

   private static void x2wa9qjwcgiun6(ServerWorld var0, Colony var1) {
      for (Entry var3 : var1.x2f7z1gnnht5ab().x16wpjsjmva3od.entrySet()) {
         BlockPos var4 = (BlockPos)var3.getKey();
         if (((BlockState)var3.getValue()).getBlock() instanceof FenceGateBlock && var0.getChunkManager().isChunkLoaded(var4.getX() >> 4, var4.getZ() >> 4)) {
            BlockState var5 = var0.getBlockState(var4);
            if (var5.getBlock() instanceof FenceGateBlock && !(Boolean)var5.get(FenceGateBlock.POWERED)) {
               boolean var6 = var0.getOtherEntities(
                     null,
                     new Box(var4).expand(6.0, 2.0, 6.0),
                     var0x -> var0x.isAlive() && (var0x instanceof VillagerEntity || var0x instanceof IronGolemEntity)
                  )
                  .stream()
                  .anyMatch(var1x -> {
                     if (!(var1x instanceof MobEntity var2 && !(var1x instanceof VillagerEntity var3x && (var3x.isSleeping() || var3x.hasCustomer())))) {
                        return false;
                     } else if (var1x.getBoundingBox().intersects(new Box(var4).expand(1.0, 1.0, 1.0))) {
                        return true;
                     } else {
                        Path var5x = var2.getNavigation().getCurrentPath();
                        if (var5x != null && !var5x.isFinished()) {
                           for (int var4x = var5x.getCurrentNodeIndex(); var4x < Math.min(var5x.getLength(), var5x.getCurrentNodeIndex() + 8); var4x++) {
                              if (var5x.getNodePos(var4x).getSquaredDistance(var4) <= 9.0) {
                                 return true;
                              }
                           }

                           return false;
                        } else {
                           return false;
                        }
                     }
                  });
               if ((Boolean)var5.get(FenceGateBlock.OPEN) != var6) {
                  var0.setBlockState(var4, (BlockState)var5.with(FenceGateBlock.OPEN, var6), 3);
               }
            }
         }
      }
   }
}
