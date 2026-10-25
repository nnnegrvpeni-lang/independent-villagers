package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

final class FarmerIrrigationHelper {
   private static final Map<Colony, IrrigationCanal> x7hy25hsbab3i = new WeakHashMap<>();

   private FarmerIrrigationHelper() {
   }

   static boolean x2gox5tp6i8qkj(ServerWorld var0, Colony var1, VillagerEntity var2, BlockPos var3) {
      IrrigationCanal var4 = x7hy25hsbab3i.get(var1);
      if (var4 == null) {
         int var5 = var3.getX() - var2.getBlockX();
         int var6 = var3.getZ() - var2.getBlockZ();
         Direction var7 = Math.abs(var5) >= Math.abs(var6) ? (var5 >= 0 ? Direction.EAST : Direction.WEST) : (var6 >= 0 ? Direction.SOUTH : Direction.NORTH);

         for (int var11 : new int[]{0, -1}) {
            BlockPos var12 = var2.getBlockPos().up(var11);

            for (int var13 = 1; var13 <= 10 && var4 == null; var13++) {
               BlockPos var14 = var12.offset(var7, var13);
               if (!xi4xj9pzi82m3(var0, var14)) {
                  break;
               }

               if (var0.getFluidState(var14).isIn(FluidTags.WATER)) {
                  ArrayList var15 = new ArrayList();

                  BlockPos var16;
                  for (var16 = var14;
                     var15.size() < 24
                        && xi4xj9pzi82m3(var0, var16)
                        && var0.getFluidState(var16).isIn(FluidTags.WATER)
                        && var0.getBlockState(var16).isOf(Blocks.WATER)
                        && ColonyProtectionUtils.x13f7be5vlapn7(var0, var16);
                     var16 = var16.offset(var7)
                  ) {
                     var15.add(var16);
                  }

                  BlockPos var17 = var14.offset(var7.getOpposite(), 2);
                  if (!ColonyNavigationHelper.x9bnd6e2jczhd(var0, var17)) {
                     var17 = var17.up();
                  }

                  BlockPos var18 = ColonyNavigationHelper.x9bnd6e2jczhd(var0, var16) ? var16 : var16.up();
                  if (!var15.isEmpty()
                     && xi4xj9pzi82m3(var0, var16)
                     && !var0.getFluidState(var16).isIn(FluidTags.WATER)
                     && ColonyNavigationHelper.x9bnd6e2jczhd(var0, var18)
                     && ColonyNavigationHelper.x9bnd6e2jczhd(var0, var17)) {
                     var4 = new IrrigationCanal(List.copyOf(var15), var17);
                  }
                  break;
               }
            }

            if (var4 != null) {
               break;
            }
         }

         if (var4 == null) {
            return false;
         }

         x7hy25hsbab3i.put(var1, var4);
      }

      BlockPos var19 = var4.x1f1dkpug78222().stream().filter(var1x -> var0.getFluidState(var1x).isIn(FluidTags.WATER)).findFirst().orElse(null);
      if (var19 == null) {
         x7hy25hsbab3i.remove(var1);
         ColonyNavigationHelper.x2fxoqmbs0henb(var2);
         ColonyMovementPlanner.x3oupbjeq14mj8(var2);
         return false;
      } else if (ColonyProtectionUtils.x13f7be5vlapn7(var0, var19) && var0.getBlockState(var19).isOf(Blocks.WATER)) {
         ColonyNavigationHelper.xf8lnb134kd0j(var0, var2, var4.x1ekcqx613mv1d(), 0.9);
         if (var2.getEntityPos().squaredDistanceTo(Vec3d.ofBottomCenter(var4.x1ekcqx613mv1d())) > 1.0) {
            return true;
         } else {
            BlockState var20 = var0.getBlockState(var19);
            boolean var21 = var1.x3171qim2pcjd7()
               .entrySet()
               .stream()
               .anyMatch(var0x -> new ItemStack((ItemConvertible)Registries.ITEM.get(var0x.getKey())).isIn(ItemTags.LOGS));
            boolean var22;
            if (var21) {
               var22 = ColonyProtectionUtils.x1bq0f8n53gtob(var0, var19, Blocks.OAK_PLANKS.getDefaultState(), 3);
               if (var22) {
                  FounderMigrationTask.xpl4idj7kx63g(var1);
                  ColonyState.get(var0).markDirty();
               }
            } else {
               var22 = ColonyManager.x38ipozok9mc0x(
                  var0,
                  var1,
                  () -> ColonyManager.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.isIn(ItemTags.LOGS), 1)
                     && ColonyProtectionUtils.x1bq0f8n53gtob(var0, var19, Blocks.OAK_PLANKS.getDefaultState(), 3),
                  List.of()
               );
            }

            if (!var22 && var0.getBlockState(var19) != var20) {
               ColonyWorldUtils.x2aebumvi646n9(() -> var0.setBlockState(var19, var20, 3));
            }

            if (!var22 && !var21 && !ColonyManager.x1tl8iu49onump(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS))) {
               x7hy25hsbab3i.remove(var1);
               return false;
            } else {
               return true;
            }
         }
      } else {
         x7hy25hsbab3i.remove(var1);
         return false;
      }
   }

   private static boolean xi4xj9pzi82m3(ServerWorld var0, BlockPos var1) {
      return var0.getWorldBorder().contains(var1) && var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4);
   }
}
