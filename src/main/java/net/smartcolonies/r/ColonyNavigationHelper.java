package net.smartcolonies.r;

import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;

public final class ColonyNavigationHelper {
   private static final Map<VillagerEntity, EntityFilterHelper> x53dla2m2sjpv = new WeakHashMap<>();

   private ColonyNavigationHelper() {
   }

   public static BlockPos x37u4pqy3fbps(VillagerEntity var0) {
      EntityFilterHelper var1 = x53dla2m2sjpv.get(var0);
      return var1 != null && var0.getEntityWorld().getTime() - var1.x2yeh8j3blv419 < 60L ? var1.x1zh0xo7suwqwi : null;
   }

   public static boolean xps35ehel6nkk(VillagerEntity var0, BlockPos var1, long var2) {
      EntityFilterHelper var4 = x53dla2m2sjpv.get(var0);
      return var4 != null && var4.x3l3snwia7ft28.getOrDefault(var1, 0L) > var2;
   }

   public static boolean x1f6ghn62xaxti(VillagerEntity var0, long var1) {
      EntityFilterHelper var3 = x53dla2m2sjpv.get(var0);
      return var3 != null
         && var1 - var3.x2yeh8j3blv419 < 60L
         && var3.x14c5jvrao50ds >= 12
         && var1 - var3.x1l7j1x9mqjydy >= 600L
         && var3.x27ooc1aah0m7v != null
         && var3.x27ooc1aah0m7v.squaredDistanceTo(var0.getEntityPos()) < 16.0
         && var0.getNavigation().isIdle()
         && var3.x1zh0xo7suwqwi != null
         && var0.getBlockPos().getSquaredDistance(var3.x1zh0xo7suwqwi) > 9.0;
   }

   public static void xi17ogl3h66v0(VillagerEntity var0, long var1) {
      EntityFilterHelper var3 = x53dla2m2sjpv.get(var0);
      if (var3 != null && var3.x1zh0xo7suwqwi != null) {
         var3.x3l3snwia7ft28.put(var3.x1zh0xo7suwqwi, var1 + 600L);
      }

      var0.getNavigation().stop();
   }

   public static void x1uiixbj22gkfz(VillagerEntity var0, BlockPos var1, long var2) {
      EntityFilterHelper var4 = x53dla2m2sjpv.computeIfAbsent(var0, var0x -> new EntityFilterHelper());
      var4.x3l3snwia7ft28.put(var1.toImmutable(), var2 + 200L);
      if (var1.equals(var4.x1zh0xo7suwqwi)) {
         var0.getNavigation().stop();
      }
   }

   public static void x2fxoqmbs0henb(VillagerEntity var0) {
      x53dla2m2sjpv.remove(var0);
      var0.getNavigation().stop();
      var0.getBrain().forget(MemoryModuleType.WALK_TARGET);
      var0.getBrain().forget(MemoryModuleType.PATH);
   }

   public static boolean x2wzylgizxltef(ServerWorld var0, VillagerEntity var1, BlockPos var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 6.0);
   }

   public static boolean x1lrdp6728c3ml(ServerWorld var0, VillagerEntity var1, BlockPos var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 2.0);
   }

   public static boolean xf8lnb134kd0j(ServerWorld var0, VillagerEntity var1, BlockPos var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 0.25);
   }

   public static boolean x1cssc4al5kdgu(ServerWorld var0, VillagerEntity var1, BlockPos var2, double var3) {
      return x38akzuwnnzvsh(var0, var1, var2, var3, 1.0);
   }

   private static boolean x38akzuwnnzvsh(ServerWorld var0, VillagerEntity var1, BlockPos var2, double var3, double var5) {
      var1.getBrain().forget(MemoryModuleType.WALK_TARGET);
      var1.getBrain().forget(MemoryModuleType.PATH);
      EntityFilterHelper var7 = x53dla2m2sjpv.computeIfAbsent(var1, var0x -> new EntityFilterHelper());
      long var8 = var0.getTime();
      if (var7.x27ooc1aah0m7v != null && var7.x27ooc1aah0m7v.squaredDistanceTo(var1.getEntityPos()) >= 16.0) {
         var7.x27ooc1aah0m7v = null;
         var7.x14c5jvrao50ds = 0;
      }

      var7.x3l3snwia7ft28.entrySet().removeIf(var2x -> var2x.getValue() <= var8);
      boolean var10 = var2.equals(var7.x1zh0xo7suwqwi);
      boolean var11 = var8 - var7.x2yeh8j3blv419 < 60L;
      var7.x2yeh8j3blv419 = var8;
      if (var10 && var11 && var7.x9kftcrtna1an != null && !(var7.x9kftcrtna1an.squaredDistanceTo(var1.getEntityPos()) >= 0.0625)) {
         if (var8 - var7.x27415kcgpc72a >= 100L && var1.getNavigation().isFollowingPath() && var2.getSquaredDistance(var1.getBlockPos()) > 2.25) {
            var7.x3l3snwia7ft28.put(var2.toImmutable(), var8 + 200L);
            var1.getNavigation().stop();
            var7.x1wuut87u4s3dl = null;
            var7.xz4s2nt8f9lq5 = var8 + 20L;
            var7.x27415kcgpc72a = var8;
            ColonyChatFeedback.x1qd0mutwsr3sc(var1, "подход перекрыт: выбирается другая точка");
            return false;
         }
      } else {
         var7.x9kftcrtna1an = var1.getEntityPos();
         var7.x27415kcgpc72a = var8;
      }

      if (!var10) {
         boolean var12 = var7.x1zh0xo7suwqwi != null && var7.x1zh0xo7suwqwi.getSquaredDistance(var2) <= 9.0;
         var7.x1zh0xo7suwqwi = var2.toImmutable();
         if (!var12) {
            var7.xe4qlumt6ojc9 = 0;
            var7.xz4s2nt8f9lq5 = var8;
         }
      }

      if (xps35ehel6nkk(var1, var2, var8)) {
         return false;
      } else if (var10 && var7.x1wuut87u4s3dl != null && var7.x1wuut87u4s3dl == var1.getNavigation().getCurrentPath() && var1.getNavigation().isFollowingPath()
         )
       {
         return true;
      } else if (var8 < var7.xz4s2nt8f9lq5) {
         return false;
      } else {
         var7.xz4s2nt8f9lq5 = var8 + 10L + Math.floorMod(var1.getId(), 10);
         if (!ColonyManager.x5ll9elqpk1a4(var0)) {
            return false;
         } else {
            var1.getNavigation().setCanOpenDoors(true);
            var1.getNavigation().setCanSwim(true);
            var1.getNavigation().setMaxFollowRange(64.0F);
            HashSet var20 = new HashSet();
            if (x9bnd6e2jczhd(var0, var2)) {
               var20.add(var2);
            } else {
               for (int var13 = -2; var13 <= 2; var13++) {
                  for (int var14 = -2; var14 <= 2; var14++) {
                     for (int var15 = -1; var15 <= 1; var15++) {
                        BlockPos var16 = var2.add(var13, var15, var14);
                        if (var16.getSquaredDistance(var2) <= var5 && x9bnd6e2jczhd(var0, var16)) {
                           var20.add(var16);
                        }
                     }
                  }
               }
            }

            if (var20.isEmpty()) {
               var20.add(var2);
            }

            var1.getNavigation().setRangeMultiplier(var1.getBlockPos().getSquaredDistance(var2) > 576.0 ? 8.0F : 4.0F);

            Path var21;
            try {
               var21 = var1.getNavigation().findPathTo(var20, 0);
            } finally {
               var1.getNavigation().resetRangeMultiplier();
            }

            boolean var22 = var21 != null
               && var21.getEnd() != null
               && var21.getLength() > 1
               && var21.getEnd().getBlockPos().getSquaredDistance(var2) + 4.0 < var1.getBlockPos().getSquaredDistance(var2);
            if (var21 != null && (var21.reachesTarget() || var22) && var1.getNavigation().startMovingAlong(var21, var3)) {
               var7.x1wuut87u4s3dl = var21;
               var7.xe4qlumt6ojc9 = 0;
               if (var21.reachesTarget()) {
                  var7.x14c5jvrao50ds = 0;
                  var7.x27ooc1aah0m7v = null;
               }

               return true;
            } else {
               if (var7.x27ooc1aah0m7v == null || var7.x27ooc1aah0m7v.squaredDistanceTo(var1.getEntityPos()) >= 16.0) {
                  var7.x27ooc1aah0m7v = var1.getEntityPos();
                  var7.x1l7j1x9mqjydy = var8;
                  var7.x14c5jvrao50ds = 0;
               }

               var7.x14c5jvrao50ds++;
               if (++var7.xe4qlumt6ojc9 >= 4) {
                  var7.x3l3snwia7ft28.put(var2.toImmutable(), var8 + 200L);
                  var7.xe4qlumt6ojc9 = 0;
                  if (var0.getBlockState(var2).getBlock() instanceof ChestBlock) {
                     for (Direction var24 : Type.HORIZONTAL) {
                        BlockPos var17 = var2.offset(var24);
                        if (var0.getBlockState(var17).getBlock() instanceof ChestBlock) {
                           var7.x3l3snwia7ft28.put(var17, var8 + 200L);
                        }
                     }
                  }
               }

               ColonyChatFeedback.x1qd0mutwsr3sc(var1, "нет доступного пути к " + var2.toShortString());
               return false;
            }
         }
      }
   }

   public static boolean x9bnd6e2jczhd(ServerWorld var0, BlockPos var1) {
      if (!var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)) {
         return false;
      } else {
         BlockState var2 = var0.getBlockState(var1);
         boolean var3 = var2.isOf(Blocks.SNOW) && (Integer)var2.get(SnowBlock.LAYERS) < 8;
         return var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)
            && var0.getFluidState(var1).isEmpty()
            && var0.getFluidState(var1.down()).isEmpty()
            && (var2.getCollisionShape(var0, var1).isEmpty() || var3)
            && var0.getBlockState(var1.up()).getCollisionShape(var0, var1.up()).isEmpty()
            && var0.getBlockState(var1.down()).isSideSolidFullSquare(var0, var1.down(), Direction.UP);
      }
   }
}
