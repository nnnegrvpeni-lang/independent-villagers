package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap.Type;

public final class ColonyStorageRelocator {
   private ColonyStorageRelocator() {}

   public static boolean isChestAt(ServerWorld world, BlockPos pos) {
      return world.getBlockState(pos).isOf(Blocks.CHEST);
   }

   /**
    * Relocates an obstructing chest from oldPos to a safe storage location,
    * preserving 100% of all items inside without dropping them on the ground.
    */
   public static boolean relocateChest(ServerWorld world, Colony colony, BlockPos chestPos, VillagerEntity villager) {
      if (world == null || colony == null || chestPos == null) return false;
      if (!world.getChunkManager().isChunkLoaded(chestPos.getX() >> 4, chestPos.getZ() >> 4)) return false;

      BlockState state = world.getBlockState(chestPos);
      if (!state.isOf(Blocks.CHEST)) return false;

      // 1. Gather all items from the chest and its connected double-chest half (if any)
      List<ItemStack> savedItems = new ArrayList<>();
      BlockEntity be = world.getBlockEntity(chestPos);
      if (be instanceof ChestBlockEntity cbe) {
         for (int i = 0; i < cbe.size(); i++) {
            ItemStack stack = cbe.getStack(i);
            if (!stack.isEmpty()) {
               savedItems.add(stack.copy());
            }
         }
         cbe.clear(); // Clear so removing the block drops nothing
      }

      // Check double chest partner
      BlockPos partnerPos = null;
      if (state.contains(ChestBlock.CHEST_TYPE) && state.get(ChestBlock.CHEST_TYPE) != ChestType.SINGLE) {
         Direction facing = state.get(ChestBlock.FACING);
         ChestType type = state.get(ChestBlock.CHEST_TYPE);
         Direction partnerDir = type == ChestType.LEFT ? facing.rotateYClockwise() : facing.rotateYCounterclockwise();
         BlockPos candidatePartner = chestPos.offset(partnerDir);
         BlockState partnerState = world.getBlockState(candidatePartner);
         if (partnerState.isOf(Blocks.CHEST)) {
            partnerPos = candidatePartner;
            BlockEntity pbe = world.getBlockEntity(partnerPos);
            if (pbe instanceof ChestBlockEntity pcbe) {
               for (int i = 0; i < pcbe.size(); i++) {
                  ItemStack stack = pcbe.getStack(i);
                  if (!stack.isEmpty()) {
                     savedItems.add(stack.copy());
                  }
               }
               pcbe.clear();
            }
         }
      }

      // 2. Find a safe location for the relocated chest
      Optional<BlockPos> safePlot = findSafeStoragePlot(world, colony, partnerPos != null);
      if (safePlot.isEmpty()) {
         // Fallback: put items back if no plot found
         restoreItemsToChest(world, chestPos, savedItems);
         return false;
      }

      BlockPos newPos = safePlot.get();
      boolean isDouble = partnerPos != null;

      // 3. Place new chest at safePlot
      if (isDouble) {
         BlockPos newPartner = newPos.east();
         world.setBlockState(newPos, Blocks.CHEST.getDefaultState()
            .with(ChestBlock.FACING, Direction.NORTH)
            .with(ChestBlock.CHEST_TYPE, ChestType.RIGHT), 3);
         world.setBlockState(newPartner, Blocks.CHEST.getDefaultState()
            .with(ChestBlock.FACING, Direction.NORTH)
            .with(ChestBlock.CHEST_TYPE, ChestType.LEFT), 3);
      } else {
         world.setBlockState(newPos, Blocks.CHEST.getDefaultState()
            .with(ChestBlock.FACING, Direction.NORTH)
            .with(ChestBlock.CHEST_TYPE, ChestType.SINGLE), 3);
      }

      // 4. Fill new chest(s) with saved items
      distributeItemsToNewChests(world, newPos, isDouble ? newPos.east() : null, savedItems);

      // 5. Update colony storage registry
      StorageCategory category = colony.x29j0o9zdxri2().getOrDefault(chestPos, StorageCategory.x2y2vmpsepzv3);
      colony.x29j0o9zdxri2().remove(chestPos);
      if (partnerPos != null) {
         colony.x29j0o9zdxri2().remove(partnerPos);
      }
      colony.x29j0o9zdxri2().put(newPos, category);
      if (isDouble) {
         colony.x29j0o9zdxri2().put(newPos.east(), category);
      }

      // 6. Safely remove old chest blocks now that new ones are populated
      world.removeBlockEntity(chestPos);
      world.setBlockState(chestPos, Blocks.AIR.getDefaultState(), 3);
      if (partnerPos != null) {
         world.removeBlockEntity(partnerPos);
         world.setBlockState(partnerPos, Blocks.AIR.getDefaultState(), 3);
      }

      ColonyManager.xchl1k1tnh3w8(world).markDirty();
      if (villager != null) {
         ColonyChatFeedback.x2t98zlq4lu49t(villager, "мешающий сундук перенесён на склад (ресурсы сохранены)", 2);
      }
      return true;
   }

   private static void restoreItemsToChest(ServerWorld world, BlockPos pos, List<ItemStack> items) {
      if (world.getBlockEntity(pos) instanceof ChestBlockEntity cbe) {
         int slot = 0;
         for (ItemStack s : items) {
            if (slot < cbe.size()) {
               cbe.setStack(slot++, s);
            }
         }
      }
   }

   private static void distributeItemsToNewChests(ServerWorld world, BlockPos pos1, BlockPos pos2, List<ItemStack> items) {
      int itemIdx = 0;
      if (world.getBlockEntity(pos1) instanceof ChestBlockEntity cbe1) {
         int size = cbe1.size();
         for (int slot = 0; slot < size && itemIdx < items.size(); slot++) {
            cbe1.setStack(slot, items.get(itemIdx++));
         }
      }
      if (pos2 != null && itemIdx < items.size() && world.getBlockEntity(pos2) instanceof ChestBlockEntity cbe2) {
         int size = cbe2.size();
         for (int slot = 0; slot < size && itemIdx < items.size(); slot++) {
            cbe2.setStack(slot, items.get(itemIdx++));
         }
      }
   }

   public static Optional<BlockPos> findSafeStoragePlot(ServerWorld world, Colony colony, boolean needsDouble) {
      BlockPos core = colony.core();
      // Search outward from core (radius 4 to 24) for a non-conflicting, flat plot
      for (int r = 4; r <= 24; r++) {
         for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
               if (Math.abs(dx) != r && Math.abs(dz) != r) continue;
               int x = core.getX() + dx;
               int z = core.getZ() + dz;
               if (!world.getChunkManager().isChunkLoaded(x >> 4, z >> 4)) continue;
               if (needsDouble && !world.getChunkManager().isChunkLoaded((x + 1) >> 4, z >> 4)) continue;

               int topY = world.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, x, z);
               if (Math.abs(topY - core.getY()) > 4) continue;

               BlockPos cand = new BlockPos(x, topY, z);
               if (isSafeChestSpot(world, colony, cand) && (!needsDouble || isSafeChestSpot(world, colony, cand.east()))) {
                  return Optional.of(cand);
               }
            }
         }
      }
      return Optional.empty();
   }

   public static boolean isSafeChestSpot(ServerWorld world, Colony colony, BlockPos pos) {
      if (!world.getChunkManager().isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4)) return false;
      // Below must be solid ground
      BlockState downState = world.getBlockState(pos.down());
      if (downState.isAir() || downState.isOf(Blocks.DIRT_PATH) || downState.isOf(Blocks.FARMLAND) || downState.isOf(Blocks.WATER)) {
         return false;
      }
      if (!downState.isOpaqueFullCube()) return false;

      // Position and above must be air
      if (!world.getBlockState(pos).isAir() && !world.getBlockState(pos).isReplaceable()) return false;
      if (!world.getBlockState(pos.up()).isAir() && !world.getBlockState(pos.up()).isReplaceable()) return false;

      // Must not be near roads
      if (isNearRoad(world, colony, pos, 2)) return false;

      // Must not intersect any construction job
      for (ConstructionJob job : colony.x1ja61emeln2hc()) {
         BlockBox box = job.x24kbqv6g4kkrg();
         if (pos.getX() >= box.getMinX() - 1 && pos.getX() <= box.getMaxX() + 1
            && pos.getZ() >= box.getMinZ() - 1 && pos.getZ() <= box.getMaxZ() + 1) {
            return false;
         }
      }

      // Must not conflict with existing colony storages
      if (colony.x29j0o9zdxri2().containsKey(pos) || colony.x29j0o9zdxri2().containsKey(pos.east())) {
         return false;
      }

      return true;
   }

   public static boolean isNearRoad(ServerWorld world, Colony colony, BlockPos pos, int dist) {
      for (int dx = -dist; dx <= dist; dx++) {
         for (int dz = -dist; dz <= dist; dz++) {
            BlockPos check = pos.add(dx, 0, dz);
            if (world.getBlockState(check).isOf(Blocks.DIRT_PATH) || world.getBlockState(check.down()).isOf(Blocks.DIRT_PATH)) {
               return true;
            }
            if (colony.x1n1a2vp01kxzf().contains(check) || colony.x1n1a2vp01kxzf().contains(check.down())) {
               return true;
            }
         }
      }
      return false;
   }
}
