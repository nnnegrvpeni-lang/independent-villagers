package net.smartcolonies.r;

import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;

public final class ColonyMathUtils {
   private ColonyMathUtils() {
   }

   public static void xdfuvky2q520(ServerWorld var0, Colony var1, VillagerEntity var2) {
      if (var2.isAlive() && !var2.isSleeping() && Math.floorMod(var0.getTime() + var2.getId() * 17L, 120L) == 0L) {
         BlockPos var3 = var2.getBlockPos();
         if (var0.getLightLevel(LightType.BLOCK, var3) <= 4 && !x18ap5wluhtzk2(var0, var3)) {
            BlockPos var4 = x8fofjzx50yhu(var0, var1, var3);
            if (var4 != null) {
               ItemStack var5 = x1tm2jo7usf6ui(var2);
               ItemStack var6 = ItemStack.EMPTY;
               if (var5.isEmpty()) {
                  var6 = ColonyManager.x3lxwdirnn77qc(var0, var1, var0x -> var0x.isOf(Items.TORCH)).orElse(ItemStack.EMPTY);
                  if (var6.isEmpty() && x2uh9hhyc0v6wn(var0, var1)) {
                     var6 = new ItemStack(Items.TORCH, 4);
                  }

                  if (var6.isEmpty()) {
                     return;
                  }
               }

               if (ColonyProtectionUtils.x1bq0f8n53gtob(var0, var4, Blocks.TORCH.getDefaultState(), 3)) {
                  if (!var5.isEmpty()) {
                     var5.decrement(1);
                  } else {
                     var6.decrement(1);
                     if (!var6.isEmpty()) {
                        ItemStack var7 = var2.getInventory().addStack(var6);
                        if (!var7.isEmpty()) {
                           VillagerBucketRefillHelper.xqe8twcjwxn55(var0, var1, var2, var7);
                        }
                     }
                  }

                  var2.swingHand(Hand.MAIN_HAND);
               } else if (!var6.isEmpty()) {
                  VillagerBucketRefillHelper.xqe8twcjwxn55(var0, var1, var2, var6);
               }
            }
         }
      }
   }

   private static BlockPos x8fofjzx50yhu(ServerWorld var0, Colony var1, BlockPos var2) {
      // Only place torches in civilization areas: village center, along roads, or near/inside buildings!
      // NEVER in the open wilderness!
      boolean isCivilized = isNearColonyInfrastructure(var0, var1, var2);
      if (!isCivilized) {
         return null;
      }

      for (int var3 = 1; var3 <= 3; var3++) {
         for (int var4 = -var3; var4 <= var3; var4++) {
            for (int var5 = -var3; var5 <= var3; var5++) {
               if (Math.max(Math.abs(var4), Math.abs(var5)) == var3) {
                  for (int var6 = -1; var6 <= 1; var6++) {
                     BlockPos var7 = var2.add(var4, var6, var5);
                     if (var0.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)) {
                        if (var0.getLightLevel(LightType.BLOCK, var7) <= 4
                           && !x18ap5wluhtzk2(var0, var7)
                           && var0.getBlockState(var7).isAir()
                           && Blocks.TORCH.getDefaultState().canPlaceAt(var0, var7)) {
                           return var7.toImmutable();
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   private static boolean isNearColonyInfrastructure(ServerWorld world, Colony colony, BlockPos pos) {
      // 1. Village center (within 14 blocks of campfire)
      if (pos.getSquaredDistance(colony.core()) <= 14.0 * 14.0) {
         return true;
      }
      // 2. Along a road (within 2 blocks of dirt path)
      for (int dx = -2; dx <= 2; dx++) {
         for (int dz = -2; dz <= 2; dz++) {
            BlockPos p = pos.add(dx, 0, dz);
            if (world.getBlockState(p).isOf(Blocks.DIRT_PATH) || world.getBlockState(p.down()).isOf(Blocks.DIRT_PATH)) {
               return true;
            }
         }
      }
      // 3. Near or inside a building
      for (ConstructionJob job : colony.x1ja61emeln2hc()) {
         BlockBox box = job.x24kbqv6g4kkrg();
         if (pos.getX() >= box.getMinX() - 3 && pos.getX() <= box.getMaxX() + 3
            && pos.getZ() >= box.getMinZ() - 3 && pos.getZ() <= box.getMaxZ() + 3) {
            return true;
         }
      }
      return false;
   }

   private static boolean x18ap5wluhtzk2(ServerWorld var0, BlockPos var1) {
      for (BlockPos var3 : BlockPos.iterate(var1.add(-9, -3, -9), var1.add(9, 3, 9))) {
         if (var0.getChunkManager().isChunkLoaded(var3.getX() >> 4, var3.getZ() >> 4)
            && (var0.getBlockState(var3).isOf(Blocks.TORCH) || var0.getBlockState(var3).isOf(Blocks.WALL_TORCH))) {
            return true;
         }
      }

      return false;
   }

   private static ItemStack x1tm2jo7usf6ui(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         ItemStack var2 = var0.getInventory().getStack(var1);
         if (var2.isOf(Items.TORCH)) {
            return var2;
         }
      }

      return ItemStack.EMPTY;
   }

   private static boolean x2uh9hhyc0v6wn(ServerWorld var0, Colony var1) {
      boolean var2 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.COAL) || var0x.isOf(Items.CHARCOAL)) > 0;
      boolean var3 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.STICK) || var0x.isIn(ItemTags.LOGS)) > 0;
      if (var2 && var3) {
         ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.COAL) || var0x.isOf(Items.CHARCOAL), 1);
         ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.STICK) || var0x.isIn(ItemTags.LOGS), 1);
         return true;
      } else {
         return false;
      }
   }
}
