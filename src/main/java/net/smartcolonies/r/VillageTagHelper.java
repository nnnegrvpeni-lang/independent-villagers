package net.smartcolonies.r;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;

public final class VillageTagHelper {
   private VillageTagHelper() {
   }

   public static boolean x1qwgvfo0xc7pp(ServerWorld var0, IronGolemEntity var1, Colony var2) {
      if (ColonyConfig.x2by5f9ehxvwk9().roles.golems_break_attacker_pillars && ColonyProtectionUtils.x35ev8l9vc9uk7(var0)) {
         if (var1.getTarget() instanceof PlayerEntity var3 && !var3.isCreative() && !var3.isSpectator()) {
            double var13 = var3.getX() - var1.getX();
            double var6 = var3.getZ() - var1.getZ();
            double var8 = var3.getY() - var1.getY();
            if (!(var8 < 2.0) && !(var8 > 9.0) && !(var13 * var13 + var6 * var6 > 20.0) && Math.floorMod(var0.getTime() + var1.getId(), 12) == 0) {
               BlockPos var10 = null;

               for (int var11 = 1; var11 <= 8; var11++) {
                  BlockPos var12 = var3.getBlockPos().down(var11);
                  if (!var0.getBlockState(var12).isAir()) {
                     var10 = var12;
                     break;
                  }
               }

               if (var10 == null
                  || !xxdhr31as89gp(var0, var2, var10)
                  || !ColonyState.get(var0).isPlayerBlock(var10)
                  || ColonyProtectionUtils.xz4flfirg81x1(var0, var10)) {
                  return false;
               } else if (!var0.breakBlock(var10, false, var1)) {
                  return false;
               } else {
                  ColonyState.get(var0).forgetPlacement(var10);
                  ColonyMovementPlanner.x1wi30zgg7nf81(var0, var1, var3.getBlockPos(), 1.9);
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xxdhr31as89gp(ServerWorld var0, Colony var1, BlockPos var2) {
      BlockState var3 = var0.getBlockState(var2);
      if (var3.isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("smart_colonies", "builder_protected")))) {
         return false;
      } else if (!var3.isAir() && var0.getBlockEntity(var2) == null && !(var3.getHardness(var0, var2) < 0.0F)) {
         boolean var4 = var3.isIn(BlockTags.DIRT)
            || var3.isIn(BlockTags.BASE_STONE_OVERWORLD)
            || var3.isOf(Blocks.COBBLESTONE)
            || var3.isOf(Blocks.COBBLED_DEEPSLATE)
            || var3.isOf(Blocks.NETHERRACK);
         if (!var4) {
            return false;
         } else {
            int var5 = 0;

            for (Direction var7 : Type.HORIZONTAL) {
               if (var0.getBlockState(var2.offset(var7)).isAir()) {
                  var5++;
               }
            }

            if (var5 < 2) {
               return false;
            } else {
               for (ConstructionJob var11 : var1.x1ja61emeln2hc()) {
                  BlockBox var8 = var11.x24kbqv6g4kkrg();
                  if (var2.getX() >= var8.getMinX() - 2
                     && var2.getX() <= var8.getMaxX() + 2
                     && var2.getY() >= var8.getMinY() - 1
                     && var2.getY() <= var8.getMaxY() + 2
                     && var2.getZ() >= var8.getMinZ() - 2
                     && var2.getZ() <= var8.getMaxZ() + 2) {
                     return false;
                  }
               }

               for (BlockPos var12 : BlockPos.iterate(var2.add(-3, -3, -3), var2.add(3, 3, 3))) {
                  BlockState var13 = var0.getBlockState(var12);
                  if (var13.isIn(BlockTags.BEDS)
                     || var13.isIn(BlockTags.DOORS)
                     || var13.isOf(Blocks.BELL)
                     || var13.isOf(Blocks.CHEST)
                     || var13.isOf(Blocks.BARREL)) {
                     return false;
                  }
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }
}
