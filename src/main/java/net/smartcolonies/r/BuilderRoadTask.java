package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;

public final class BuilderRoadTask extends MultiTickTask<VillagerEntity> {
   private Colony x24sw4yjvvv3sl;
   private long x3c1e4wbf3gizu;
   private final BlockInteractionTracker x3nsayy5nxyiob = new BlockInteractionTracker();

   public BuilderRoadTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      this.x24sw4yjvvv3sl = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
         .filter(
            var2x -> !var2.isBaby()
               && !var2.isSleeping()
               && !var2.hasCustomer()
               && var2x.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r
               && !var2x.x2s37os1kht7vr()
               && !var2x.x2ad3s8aentmo4().isEmpty()
               && !ColonyBiomeHelper.xuj80syz8c60l(var1, var2x, var2)
               && !BuildingBlueprintHelper.x81tynoeus5lh(var1, var2x)
         )
         .orElse(null);
      if (this.x24sw4yjvvv3sl != null) {
         if (this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()) {
            scanAndQueueUnconnectedRoadGaps(var1, this.x24sw4yjvvv3sl);
         }
         return !this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty();
      }
      return false;
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x3c1e4wbf3gizu = var3;
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x24sw4yjvvv3sl != null
         && this.x24sw4yjvvv3sl.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r
         && !this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()
         && !ColonyBiomeHelper.xuj80syz8c60l(var1, this.x24sw4yjvvv3sl, var2)
         && !BuildingBlueprintHelper.x81tynoeus5lh(var1, this.x24sw4yjvvv3sl);
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x3c1e4wbf3gizu) {
         if (!ColonyProtectionUtils.x35ev8l9vc9uk7(var1)) {
            ColonyChatFeedback.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else {
            this.x3c1e4wbf3gizu = var3 + 10L;
            BlockPos var5 = this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().getFirst();
            if (StructurePieceHelper.x3hqfrterusj2u(var1, var5)) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
            } else if (!StructurePieceHelper.xxon284tpck85(var1, this.x24sw4yjvvv3sl, var5)
               || ColonyNavigationHelper.xps35ehel6nkk(var2, var5.up(), var3)
               || this.x24sw4yjvvv3sl.x3nmwt0p1j1m6g() >= 600) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(true);
               ColonyChatFeedback.x1qd0mutwsr3sc(var2, "непроходимый сегмент дороги пропущен: " + var5.toShortString());
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
            } else if (var2.getBlockPos().getSquaredDistance(var5.up()) > 9.0) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               ColonyNavigationHelper.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var5.getX() + 0.5, var5.getY() + 1, var5.getZ() + 0.5), 0.65);
            } else if (!var1.getBlockState(var5.up()).isAir() && !ColonyProtectionUtils.x231if4rvsvptj(var1, var5.up(), true, var2)) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
            } else if (this.x3nsayy5nxyiob.xpohy1q30uxlk(var1, var2, var5, ColonyConfig.x2by5f9ehxvwk9().work.placement_ticks, false)) {
               this.buildWideRoadSegment(var1, var2, var5);
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
               ColonyChatFeedback.x2t98zlq4lu49t(var2, "дорога", 1);
               var2.swingHand(Hand.MAIN_HAND);
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
               if (this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()) {
                  this.tryBridgeDeadEnd(var1, var2, var5);
               }
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x3nsayy5nxyiob.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      this.x24sw4yjvvv3sl = null;
   }
   private void buildWideRoadSegment(ServerWorld world, VillagerEntity builder, BlockPos center) {
      placeSingleRoadBlock(world, center, builder);

      // Widen to 3 blocks: check 3x3 footprint at ground surface
      for (int dx = -1; dx <= 1; dx++) {
         for (int dz = -1; dz <= 1; dz++) {
            if (dx == 0 && dz == 0) continue;
            int nx = center.getX() + dx;
            int nz = center.getZ() + dz;
            int ny = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, nx, nz) - 1;
            BlockPos neighbor = new BlockPos(nx, ny, nz);
            if (Math.abs(ny - center.getY()) <= 1) {
               placeSingleRoadBlock(world, neighbor, builder);
            }
         }
      }
   }

   private void placeSingleRoadBlock(ServerWorld world, BlockPos pos, VillagerEntity builder) {
      BlockState state = world.getBlockState(pos);
      if (state.isOf(Blocks.DIRT_PATH) || state.isOf(Blocks.FARMLAND) || state.isOf(Blocks.WATER)
         || state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.BEDS)
         || state.isOf(Blocks.CHEST) || state.isOf(Blocks.CRAFTING_TABLE) || state.isOf(Blocks.BELL)
         || state.isOf(Blocks.CAMPFIRE) || state.isOf(Blocks.COMPOSTER)) {
         return;
      }
      if (state.isOf(Blocks.GRASS_BLOCK) || state.isOf(Blocks.DIRT) || state.isOf(Blocks.COARSE_DIRT)
         || state.isOf(Blocks.PODZOL) || state.isOf(Blocks.SAND) || state.isOf(Blocks.SANDSTONE)
         || state.isOf(Blocks.GRAVEL)) {
         BlockState above = world.getBlockState(pos.up());
         if (!above.isAir() && above.isReplaceable()) {
            ColonyProtectionUtils.x231if4rvsvptj(world, pos.up(), false, builder);
         }
         ColonyProtectionUtils.x1bq0f8n53gtob(world, pos, Blocks.DIRT_PATH.getDefaultState(), 3);
      }
   }
   private static long lastBridgeCheckTime = 0L;

   private static void scanAndQueueUnconnectedRoadGaps(ServerWorld world, Colony colony) {
      if (world.getTime() - lastBridgeCheckTime < 400L) {
         return;
      }
      lastBridgeCheckTime = world.getTime();

      for (ConstructionJob job : colony.x1ja61emeln2hc()) {
         BlockPos origin = job.x3ij6gescvakv2();
         int w = job.xmu75rcopbjnh();
         int l = job.xxjwqetbgzzle();

         // Check canonical entrances only (4 positions per building)
         int[][] entranceOffsets = new int[][]{{w / 2, -3}, {w / 2, l + 2}, {-3, l / 2}, {w + 2, l / 2}};
         for (int[] off : entranceOffsets) {
            int px = origin.getX() + off[0];
            int pz = origin.getZ() + off[1];
            if (!world.getChunkManager().isChunkLoaded(px >> 4, pz >> 4)) {
               continue;
            }
            int py = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, px, pz) - 1;
            BlockPos p1 = new BlockPos(px, py, pz);
            if (!StructurePieceHelper.x3hqfrterusj2u(world, p1)) {
               continue;
            }

            for (Direction dir : Direction.Type.HORIZONTAL) {
               for (int step = 3; step <= 6; step++) {
                  int qx = p1.getX() + dir.getOffsetX() * step;
                  int qz = p1.getZ() + dir.getOffsetZ() * step;
                  if (!world.getChunkManager().isChunkLoaded(qx >> 4, qz >> 4)) {
                     continue;
                  }
                  int qy = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, qx, qz) - 1;
                  BlockPos p2 = new BlockPos(qx, qy, qz);
                  if (Math.abs(qy - py) <= 2 && StructurePieceHelper.x3hqfrterusj2u(world, p2)) {
                     boolean hasGap = false;
                     boolean obstructed = false;
                     for (int s = 1; s < step; s++) {
                        int mx = p1.getX() + dir.getOffsetX() * s;
                        int mz = p1.getZ() + dir.getOffsetZ() * s;
                        if (!world.getChunkManager().isChunkLoaded(mx >> 4, mz >> 4)) {
                           obstructed = true;
                           break;
                        }
                        int my = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, mx, mz) - 1;
                        BlockPos mpos = new BlockPos(mx, my, mz);
                        if (!StructurePieceHelper.x3hqfrterusj2u(world, mpos)) {
                           hasGap = true;
                        }
                        BlockState mState = world.getBlockState(mpos);
                        if (mState.isOf(Blocks.WATER) || mState.isIn(BlockTags.WALLS) || mState.isIn(BlockTags.DOORS) || mState.isIn(BlockTags.PLANKS)) {
                           obstructed = true;
                           break;
                        }
                     }
                     if (hasGap && !obstructed) {
                        for (int s = 1; s < step; s++) {
                           int mx = p1.getX() + dir.getOffsetX() * s;
                           int mz = p1.getZ() + dir.getOffsetZ() * s;
                           int my = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, mx, mz) - 1;
                           BlockPos mpos = new BlockPos(mx, my, mz);
                           if (!colony.x1n1a2vp01kxzf().contains(mpos)) {
                              colony.x1n1a2vp01kxzf().add(mpos);
                           }
                        }
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private void tryBridgeDeadEnd(ServerWorld world, VillagerEntity builder, BlockPos currentPos) {
      for (Direction dir : Direction.Type.HORIZONTAL) {
         for (int step = 3; step <= 6; step++) {
            int qx = currentPos.getX() + dir.getOffsetX() * step;
            int qz = currentPos.getZ() + dir.getOffsetZ() * step;
            if (!world.getChunkManager().isChunkLoaded(qx >> 4, qz >> 4)) {
               continue;
            }
            int qy = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, qx, qz) - 1;
            BlockPos target = new BlockPos(qx, qy, qz);
            if (Math.abs(qy - currentPos.getY()) <= 2 && StructurePieceHelper.x3hqfrterusj2u(world, target)) {
               boolean hasGap = false;
               boolean obstructed = false;
               for (int s = 1; s < step; s++) {
                  int mx = currentPos.getX() + dir.getOffsetX() * step;
                  int mz = currentPos.getZ() + dir.getOffsetZ() * step;
                  if (!world.getChunkManager().isChunkLoaded(mx >> 4, mz >> 4)) {
                     obstructed = true;
                     break;
                  }
                  int my = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, mx, mz) - 1;
                  BlockPos mpos = new BlockPos(mx, my, mz);
                  if (!StructurePieceHelper.x3hqfrterusj2u(world, mpos)) {
                     hasGap = true;
                  }
                  BlockState mState = world.getBlockState(mpos);
                  if (mState.isOf(Blocks.WATER) || mState.isIn(BlockTags.WALLS) || mState.isIn(BlockTags.DOORS) || mState.isIn(BlockTags.PLANKS)) {
                     obstructed = true;
                     break;
                  }
               }
               if (hasGap && !obstructed) {
                  for (int s = 1; s < step; s++) {
                     int mx = currentPos.getX() + dir.getOffsetX() * s;
                     int mz = currentPos.getZ() + dir.getOffsetZ() * s;
                     int my = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, mx, mz) - 1;
                     BlockPos mpos = new BlockPos(mx, my, mz);
                     if (!this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().contains(mpos)) {
                        this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().add(mpos);
                     }
                  }
                  ColonyChatFeedback.x2t98zlq4lu49t(builder, "перекрёсток соединён", 1);
                  return;
               }
            }
         }
      }
   }
}
