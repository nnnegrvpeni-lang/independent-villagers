package net.smartcolonies.r;

import net.minecraft.util.BlockRotation;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap.Type;

public final class StructurePieceHelper {
   private StructurePieceHelper() {
   }

   public static boolean x3hqfrterusj2u(ServerWorld var0, BlockPos var1) {
      return var0.getBlockState(var1).isOf(Blocks.DIRT_PATH) || var0.getBlockState(var1).isOf(Blocks.SMOOTH_SANDSTONE);
   }

   public static BlockPos x3qthrkxcelgb7(ServerWorld var0, int var1, int var2) {
      BlockPos var3 = new BlockPos(var1, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var1, var2) - 1, var2);

      for (int var4 = 0; var4 < 6 && ColonyBiomeHelper.x3hz8l3ppqisjl(var0, var3); var4++) {
         var3 = var3.down();
      }

      return var3;
   }

   public static boolean xxon284tpck85(ServerWorld var0, Colony var1, BlockPos var2) {
      if (!ColonyProtectionUtils.x35ev8l9vc9uk7(var0)) {
         return false;
      } else if (!var0.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
         return false;
      } else if (ColonyState.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var2))) {
         return false;
      } else if (BuildingBlueprintHelper.x239t1m3hfi74d(var0, var2)) {
         return false;
      } else if (x3hqfrterusj2u(var0, var2) || !ColonyState.get(var0).isPlayerBlock(var2) && !ColonyState.get(var0).isPlayerBlock(var2.up())) {
         if (!var0.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
            return false;
         } else {
            BlockState var3 = var0.getBlockState(var2);
            if (var3.isOf(Blocks.FARMLAND) || var0.getBlockState(var2.up()).getBlock() instanceof CropBlock) {
               return false;
            } else if ((ColonyProtectionUtils.xroinv4hk6l7h(var3) || x3hqfrterusj2u(var0, var2)) && var0.getBlockEntity(var2) == null) {
               if (var0.getFluidState(var2).isEmpty() && var0.getFluidState(var2.up()).isEmpty()) {
                  if (!var0.getBlockState(var2.up()).isAir()
                     && !var0.getBlockState(var2.up()).isReplaceable()
                     && !ColonyBiomeHelper.x3hz8l3ppqisjl(var0, var2.up())) {
                     return false;
                  } else if (!var0.getBlockState(var2.up(2)).isAir() && !ColonyBiomeHelper.x3hz8l3ppqisjl(var0, var2.up(2))) {
                     return false;
                  } else {
                     for (ConstructionJob var5 : var1.x1ja61emeln2hc()) {
                        BlockBox var6 = var5.x24kbqv6g4kkrg();
                        if (var2.getX() >= var6.getMinX() - 2
                           && var2.getX() <= var6.getMaxX() + 2
                           && var2.getZ() >= var6.getMinZ() - 2
                           && var2.getZ() <= var6.getMaxZ() + 2) {
                           return false;
                        }
                     }

                     return true;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public static BlockRotation getRotationForSide(ServerWorld var0, Colony var1, BlockPos var2, int var3, int var4) {
      int[][] candidateSides = new int[][]{{var3 / 2, -3}, {var3 / 2, var4 + 2}, {-3, var4 / 2}, {var3 + 2, var4 / 2}};
      int bestIdx = 0;
      double bestDistSq = Double.MAX_VALUE;
      for (int i = 0; i < candidateSides.length; i++) {
         int cx = var2.getX() + candidateSides[i][0];
         int cz = var2.getZ() + candidateSides[i][1];
         double dist = var1.core().getSquaredDistance(cx, var1.core().getY(), cz);
         if (dist < bestDistSq) {
            bestDistSq = dist;
            bestIdx = i;
         }
      }
      return switch (bestIdx) {
         case 1 -> BlockRotation.CLOCKWISE_180;
         case 2 -> BlockRotation.COUNTERCLOCKWISE_90;
         case 3 -> BlockRotation.CLOCKWISE_90;
         default -> BlockRotation.NONE;
      };
   }

   public static Optional<List<BlockPos>> x33m0vw9cimu8k(ServerWorld var0, Colony var1, BlockPos var2, int var3, int var4) {
      ArrayDeque var5 = new ArrayDeque();
      HashMap var6 = new HashMap();

      // Connect only from the best entrance side facing the village center, preventing parallel wire loops
      int[][] candidateSides = new int[][]{{var3 / 2, -3}, {var3 / 2, var4 + 2}, {-3, var4 / 2}, {var3 + 2, var4 / 2}};
      int[] bestSide = candidateSides[0];
      double bestDistSq = Double.MAX_VALUE;
      for (int[] candidate : candidateSides) {
         int cx = var2.getX() + candidate[0];
         int cz = var2.getZ() + candidate[1];
         double dist = var1.core().getSquaredDistance(cx, var1.core().getY(), cz);
         if (dist < bestDistSq) {
            bestDistSq = dist;
            bestSide = candidate;
         }
      }

      int var11 = var2.getX() + bestSide[0];
      int var12 = var2.getZ() + bestSide[1];
      if (var0.getChunkManager().isChunkLoaded(var11 >> 4, var12 >> 4)) {
         BlockPos var13 = x3qthrkxcelgb7(var0, var11, var12);
         if (xxon284tpck85(var0, var1, var13)) {
            var5.add(var13);
            var6.put(var13, var13);
         }
      }

      int var15 = 0;

      while (!var5.isEmpty() && var15++ < 1800) {
         BlockPos var16 = (BlockPos)var5.removeFirst();
         boolean var17 = var1.x1ja61emeln2hc().isEmpty()
            && Math.abs(var16.getX() - var1.core().getX()) <= 2
            && Math.abs(var16.getZ() - var1.core().getZ()) <= 2
            && Math.abs(var16.getY() + 1 - var1.core().getY()) <= 1;
         if (x3hqfrterusj2u(var0, var16) || var17) {
            ArrayList var18;
            for (var18 = new ArrayList(); !((BlockPos)var6.get(var16)).equals(var16); var16 = (BlockPos)var6.get(var16)) {
               if (!x3hqfrterusj2u(var0, var16)) {
                  var18.add(var16);
               }
            }

            if (!x3hqfrterusj2u(var0, var16)) {
               var18.add(var16);
            }

            return Optional.of(var18);
         }

         for (Direction var20 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
            int var21 = var16.getX() + var20.getOffsetX();
            int var22 = var16.getZ() + var20.getOffsetZ();
            if ((var21 < var2.getX() - 2 || var21 >= var2.getX() + var3 + 2 || var22 < var2.getZ() - 2 || var22 >= var2.getZ() + var4 + 2)
               && Math.abs(var21 - var2.getX()) <= 32
               && Math.abs(var22 - var2.getZ()) <= 32
               && var0.getChunkManager().isChunkLoaded(var21 >> 4, var22 >> 4)) {
               BlockPos var14 = x3qthrkxcelgb7(var0, var21, var22);
               if (Math.abs(var14.getY() - var16.getY()) <= 1 && !var6.containsKey(var14) && xxon284tpck85(var0, var1, var14)) {
                  var6.put(var14, var16);
                  var5.addLast(var14);
               }
            }
         }
      }

      return Optional.empty();
   }
}
