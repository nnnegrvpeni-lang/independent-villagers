package net.smartcolonies.r;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.smartcolonies.mixin.x2gac63vevkbv3;

public final class x20rghnsl2yowl {
   private static final Map<x15kgoaq6i3m6i, Map<BlockPos, Long>> xm2t2pguz7drc = new WeakHashMap<>();

   private x20rghnsl2yowl() {
   }

   public static boolean x2b8s98wj2zcbw(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (!var0.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
         return false;
      } else {
         BlockState var3 = var1.x2xqc3q79vfkw3().get(var2);
         BlockState var4 = var0.getBlockState(var2);
         if (var3 == null) {
            return false;
         } else if (var4.getBlock() == var3.getBlock()) {
            return true;
         } else if (var0.getTime() < xm2t2pguz7drc.getOrDefault(var1, Map.of()).getOrDefault(var2, Long.MIN_VALUE)) {
            return false;
         } else if ((var4.isAir() || var4.isReplaceable()) && x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var2)) {
            Item var5 = x2cvbyh5y5day0.materialItem(var3);
            if (var5 == Items.AIR) {
               return false;
            } else {
               Predicate var6 = x2cvbyh5y5day0.materialPredicate(var3, var5);
               return x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var6) > 0
                  ? true
                  : var0.getEntitiesByType(
                        EntityType.VILLAGER,
                        x2n55y2tom37h2.x150ccxomrlozr(var0, var1),
                        var1x -> var1x.isAlive() && var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
                     )
                     .stream()
                     .anyMatch(var1x -> var6.test(var1x.getMainHandStack()) || var1x.getInventory().containsAny(var6));
            }
         } else {
            return false;
         }
      }
   }

   public static void xqxgtse6quf4h(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      xm2t2pguz7drc.computeIfAbsent(var1, var0x -> new HashMap<>()).put(var2.toImmutable(), var0.getTime() + 600L);
   }

   public static boolean x81tynoeus5lh(ServerWorld var0, x15kgoaq6i3m6i var1) {
      return var1.x2xqc3q79vfkw3().keySet().stream().anyMatch(var2 -> x2b8s98wj2zcbw(var0, var1, var2));
   }

   public static boolean x5epxvcc1p5ir(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (var1.x29j0o9zdxri2().containsKey(var2) || var1.core().equals(var2)) {
         return true;
      } else if (var1.x1ja61emeln2hc()
         .stream()
         .anyMatch(var1x -> var1x.x2nfrt8qxdscso().isPresent() && var1x.x2s8emavervqrz() && var1x.x24kbqv6g4kkrg().contains(var2))) {
         return true;
      } else if (x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var2)) {
         return false;
      } else if (var1.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var2)) {
         return true;
      } else {
         return var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().contains(var2)) ? true : x239t1m3hfi74d(var0, var2);
      }
   }

   public static boolean x239t1m3hfi74d(ServerWorld var0, BlockPos var1) {
      if (!var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)) {
         return false;
      } else {
         StructureStart var2 = var0.getStructureAccessor().getStructureContaining(var1, StructureTags.VILLAGE);
         return var2 == StructureStart.DEFAULT
            ? false
            : var2.getChildren()
               .stream()
               .anyMatch(
                  var1x -> {
                     if (var1x instanceof PoolStructurePiece var2x
                        && var1x.getBoundingBox().contains(var1)
                        && var2x.getPoolElement() instanceof SinglePoolElement var3) {
                        try {
                           String var6 = var3.getIdOrThrow().getPath();
                           return var6.contains("/houses/") && !var6.contains("farm") && !var6.contains("pen") && !var6.contains("animal");
                        } catch (IllegalStateException var5) {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  }
               );
      }
   }

   public static boolean x1e11i1kd8301v(ServerWorld var0, BlockPos var1) {
      if (!var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)) {
         return false;
      } else {
         StructureStart var2 = var0.getStructureAccessor().getStructureContaining(var1, StructureTags.VILLAGE);
         if (var2 == StructureStart.DEFAULT) {
            return false;
         } else {
            for (StructurePiece var4 : var2.getChildren()) {
               if (var4 instanceof PoolStructurePiece var5 && var5.getBoundingBox().contains(var1) && var5.getPoolElement() instanceof SinglePoolElement var6) {
                  Identifier var12;
                  try {
                     var12 = var6.getIdOrThrow();
                  } catch (IllegalStateException var11) {
                     continue;
                  }

                  if (var12.getPath().contains("farm")) {
                     StructureTemplate var8 = (StructureTemplate)var0.getStructureTemplateManager().getTemplate(var12).orElse(null);
                     if (var8 != null) {
                        StructurePlacementData var9 = new StructurePlacementData().setRotation(var5.getRotation());
                        List<StructureTemplate.StructureBlockInfo> var10 = ((x2gac63vevkbv3)var8).xbqthaw3aplko().getFirst().getAll();
                        if (var10.stream()
                           .anyMatch(
                              var3 -> var3.state().isOf(Blocks.FARMLAND) && StructureTemplate.transform(var9, var3.pos()).add(var5.getPos()).equals(var1)
                           )) {
                           return true;
                        }
                     }
                  }
               }
            }

            return false;
         }
      }
   }

   public static Map<BlockPos, BlockState> xg03tmq830uoq(ServerWorld var0, BlockPos var1) {
      return x335dkgs8aizip(var0, var1, var0.getBlockState(var1));
   }

   public static Map<BlockPos, BlockState> x335dkgs8aizip(ServerWorld var0, BlockPos var1, BlockState var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (!var2.isAir() && !var2.isReplaceable() && !(var2.getBlock() instanceof CropBlock) && !var2.isOf(Blocks.DIRT_PATH) && !var2.isOf(Blocks.FARMLAND)) {
         if (var2.getBlock() instanceof DoorBlock) {
            BlockPos var4 = var2.get(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? var1 : var1.down();

            for (BlockPos var8 : new BlockPos[]{var4, var4.up()}) {
               BlockState var9 = var8.equals(var1) ? var2 : var0.getBlockState(var8);
               if (var9.getBlock() instanceof DoorBlock) {
                  var3.put(var8.toImmutable(), (BlockState)((BlockState)var9.with(DoorBlock.OPEN, false)).with(DoorBlock.POWERED, false));
               }
            }
         } else if (var2.getBlock() instanceof BedBlock) {
            BlockPos var10 = var2.get(BedBlock.PART) == BedPart.FOOT ? var1 : var1.offset(((Direction)var2.get(BedBlock.FACING)).getOpposite());

            for (BlockPos var14 : new BlockPos[]{var10, var10.offset((Direction)var2.get(BedBlock.FACING))}) {
               BlockState var15 = var14.equals(var1) ? var2 : var0.getBlockState(var14);
               if (var15.getBlock() instanceof BedBlock) {
                  var3.put(var14.toImmutable(), (BlockState)var15.with(BedBlock.OCCUPIED, false));
               }
            }
         } else {
            var3.put(var1.toImmutable(), var2);
         }

         return var3;
      } else {
         return var3;
      }
   }

   public static void x17m23t077i8f8(ServerWorld var0, x15kgoaq6i3m6i var1, Map<BlockPos, BlockState> var2) {
      var2.forEach((var2x, var3) -> {
         if (!var0.getBlockState(var2x).equals(var3)) {
            var1.x2xqc3q79vfkw3().putIfAbsent(var2x, var3);
         }
      });
      x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
   }
}
