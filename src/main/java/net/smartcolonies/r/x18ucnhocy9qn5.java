package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Direction.Type;

public final class x18ucnhocy9qn5 {
   private static final Map<MobEntity, x2gtpqlzi1e0te> xy2bukilziav0 = new WeakHashMap<>();
   private static final Map<ServerWorld, Long> x1tiv6ssain9ri = new WeakHashMap<>();
   private static final int x32hrhla2zhanh = 6000;

   private x18ucnhocy9qn5() {
   }

   public static void x26rn6haaqjid6() {
      xy2bukilziav0.clear();
      x1tiv6ssain9ri.clear();
   }

   public static void x3oupbjeq14mj8(MobEntity var0) {
      xy2bukilziav0.remove(var0);
      var0.getNavigation().stop();
   }

   public static String xs0vwkswo7jny(MobEntity var0) {
      x2gtpqlzi1e0te var1 = xy2bukilziav0.get(var0);
      return var1 == null
         ? "none"
         : "goal="
            + var1.xdxnox9g75zv6
            + " points="
            + var1.x35iwfetci3lq3
            + "/"
            + var1.x3djwzkmkmiiku.size()
            + " next="
            + (var1.x35iwfetci3lq3 < var1.x3djwzkmkmiiku.size() ? var1.x3djwzkmkmiiku.get(var1.x35iwfetci3lq3) : null)
            + " failures="
            + var1.x2n9nq6uu09ugo
            + " navEnd="
            + (var0.getNavigation().getCurrentPath() == null ? null : var0.getNavigation().getCurrentPath().getEnd())
            + " following="
            + var0.getNavigation().isFollowingPath()
            + " index="
            + (var1.x1h7unpbxmmlly == null ? "-" : var1.x1h7unpbxmmlly.getCurrentNodeIndex() + "/" + var1.x1h7unpbxmmlly.getLength())
            + " velocity="
            + var0.getVelocity();
   }

   public static boolean x1wi30zgg7nf81(ServerWorld var0, MobEntity var1, BlockPos var2, double var3) {
      if (var1 instanceof VillagerEntity var5) {
         var5.getBrain().forget(MemoryModuleType.WALK_TARGET);
         var5.getBrain().forget(MemoryModuleType.PATH);
      }

      EntityNavigation var25 = var1.getNavigation();
      var25.setCanOpenDoors(true);
      var25.setCanSwim(true);
      x2gtpqlzi1e0te var6 = xy2bukilziav0.computeIfAbsent(var1, var0x -> new x2gtpqlzi1e0te());
      long var7 = var0.getTime();
      if (var6.xdxnox9g75zv6 != null && !(var6.xdxnox9g75zv6.getSquaredDistance(var2) > 16.0)) {
         var6.xdxnox9g75zv6 = var2.toImmutable();
      } else {
         var6.xdxnox9g75zv6 = var2.toImmutable();
         var6.x3djwzkmkmiiku = List.of();
         var6.x35iwfetci3lq3 = 0;
         var6.x1h7unpbxmmlly = null;
         var6.xbrpkefumlqsh = 0L;
      }

      if (var6.x1qt4uh5iogtme == null || var6.x1qt4uh5iogtme.getSquaredDistance(var1.getBlockPos()) >= 4.0) {
         var6.x1qt4uh5iogtme = var1.getBlockPos();
         var6.x1etn08okxo4qh = var7;
         var6.x2n9nq6uu09ugo = 0;
      }

      if (var1.squaredDistanceTo(var2.getX() + 0.5, var2.getY(), var2.getZ() + 0.5) <= 2.25) {
         x3oupbjeq14mj8(var1);
         return true;
      } else {
         if (var6.x3djwzkmkmiiku.isEmpty()
            && var1.getBlockPos().getSquaredDistance(var2) < 2304.0
            && var7 >= var6.x220dzuvs9upmh
            && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            var6.x220dzuvs9upmh = var7 + 60L;
            var25.setRangeMultiplier(4.0F);

            Path var9;
            try {
               var9 = var25.findPathTo(var2, 1);
            } finally {
               var25.resetRangeMultiplier();
            }

            if (var9 != null && var9.reachesTarget()) {
               var6.x1h7unpbxmmlly = var9;
               var6.x3djwzkmkmiiku = List.of(var2.toImmutable());
               var6.x35iwfetci3lq3 = 0;
               return var25.startMovingAlong(var9, var3);
            }
         }

         if (var7 - var6.x1etn08okxo4qh > 120L && var7 - var6.x2fcxxyu45q1du >= 120L) {
            var6.x3djwzkmkmiiku = List.of();
            var6.x1h7unpbxmmlly = null;
            var6.x2fcxxyu45q1du = var7;
         }

         while (
            var6.x35iwfetci3lq3 < var6.x3djwzkmkmiiku.size()
               && var1.squaredDistanceTo(Vec3d.ofBottomCenter((Vec3i)var6.x3djwzkmkmiiku.get(var6.x35iwfetci3lq3))) < 2.25
         ) {
            var6.x35iwfetci3lq3++;
         }

         if (var6.x35iwfetci3lq3 >= var6.x3djwzkmkmiiku.size() && var7 >= var6.xbrpkefumlqsh) {
            if (var7 - x1tiv6ssain9ri.getOrDefault(var0, -4611686018427387904L) < 10L || !x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
               return false;
            }

            x1tiv6ssain9ri.put(var0, var7);
            var6.xbrpkefumlqsh = var7 + 100L;
            var6.x3djwzkmkmiiku = x3iiafc1l36ps5(var0, var1, var6.xdxnox9g75zv6);
            var6.x35iwfetci3lq3 = 0;
            var6.x1h7unpbxmmlly = null;
            if (var6.x3djwzkmkmiiku.isEmpty()) {
               var6.x2n9nq6uu09ugo++;
            }
         }

         if (var6.x35iwfetci3lq3 >= var6.x3djwzkmkmiiku.size()) {
            return false;
         } else if (var6.x1h7unpbxmmlly != null && !var6.x1h7unpbxmmlly.isFinished()) {
            if (var25.getCurrentPath() != var6.x1h7unpbxmmlly || !var25.isFollowingPath()) {
               var25.startMovingAlong(var6.x1h7unpbxmmlly, var3);
            }

            return true;
         } else if (var7 >= var6.x26j1gavkh2jv2 && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            var6.x26j1gavkh2jv2 = var7 + 10L;
            int var26 = Math.min(var6.x3djwzkmkmiiku.size() - 1, var6.x35iwfetci3lq3 + 7);
            BlockPos var10 = var6.x3djwzkmkmiiku.get(var26);
            var25.setRangeMultiplier(4.0F);

            try {
               var6.x1h7unpbxmmlly = var25.findPathTo(var10, 0);
            } finally {
               var25.resetRangeMultiplier();
            }

            if (var6.x1h7unpbxmmlly != null && var6.x1h7unpbxmmlly.reachesTarget()) {
               var6.x35iwfetci3lq3 = var26;
            } else {
               var6.x1h7unpbxmmlly = null;
               var25.setRangeMultiplier(4.0F);

               try {
                  var6.x1h7unpbxmmlly = var25.findPathTo(var6.x3djwzkmkmiiku.get(var6.x35iwfetci3lq3), 0);
               } finally {
                  var25.resetRangeMultiplier();
               }

               if (var6.x1h7unpbxmmlly == null || !var6.x1h7unpbxmmlly.reachesTarget()) {
                  var6.x2n9nq6uu09ugo++;
                  var6.x1h7unpbxmmlly = null;
                  var6.x3djwzkmkmiiku = List.of();
                  var6.xbrpkefumlqsh = var7 + 40L;
                  return false;
               }
            }

            return var25.startMovingAlong(var6.x1h7unpbxmmlly, var3);
         } else {
            return false;
         }
      }
   }

   public static boolean x3hi5h4uz5pg79(ServerWorld var0, VillagerEntity var1) {
      x2gtpqlzi1e0te var2 = xy2bukilziav0.get(var1);
      long var3 = var0.getTime();
      if (var2 != null
         && var2.x2n9nq6uu09ugo >= 8
         && var3 - var2.x1etn08okxo4qh >= 1200L
         && !var1.isSleeping()
         && !var1.hasCustomer()
         && var1.isOnGround()
         && var2.x3djwzkmkmiiku.isEmpty()
         && !(var1.getVelocity().lengthSquared() > 0.01)) {
         BlockPos var5 = var1.getBlockPos();

         for (int var6 = 1; var6 <= 6; var6++) {
            for (int var10 : new int[]{1, 2, 0, -1}) {
               for (int var11 = -var6; var11 <= var6; var11++) {
                  for (int var12 = -var6; var12 <= var6; var12++) {
                     if (Math.abs(var11) == var6 || Math.abs(var12) == var6) {
                        BlockPos var13 = var5.add(var11, var10, var12);
                        if (x21dbjs9wlccy4(var0, var1, var13)
                           && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var13)
                           && !x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var13.down())
                           && var0.getOtherEntities(var1, new Box(var13).stretch(0.0, 1.0, 0.0), var0x -> var0x.isAlive()).isEmpty()) {
                           var1.refreshPositionAndAngles(var13.getX() + 0.5, var13.getY(), var13.getZ() + 0.5, var1.getYaw(), var1.getPitch());
                           x3oupbjeq14mj8(var1);
                           return true;
                        }
                     }
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static List<BlockPos> x3iiafc1l36ps5(ServerWorld var0, MobEntity var1, BlockPos var2) {
      BlockPos var3 = var1.getBlockPos();
      PriorityQueue<x23h5ttlfi4p2e> var4 = new PriorityQueue<>(Comparator.comparingDouble(x23h5ttlfi4p2e::x3bn75ty1xrpx3));
      Map<BlockPos, Integer> var5 = new HashMap<>();
      Map<BlockPos, BlockPos> var6 = new HashMap<>();
      HashSet<BlockPos> var7 = new HashSet<>();
      Map<BlockPos, Boolean> var8 = new HashMap<>();
      var5.put(var3, 0);
      var4.add(new x23h5ttlfi4p2e(var3, 0, xp31m43a2ifr6(var3, var2)));
      BlockPos var9 = var3;
      double var10 = xp31m43a2ifr6(var3, var2);
      int var12 = 0;
      int var13 = Math.min(var3.getX(), var2.getX()) - 48;
      int var14 = Math.max(var3.getX(), var2.getX()) + 48;
      int var15 = Math.min(var3.getZ(), var2.getZ()) - 48;
      int var16 = Math.max(var3.getZ(), var2.getZ()) + 48;

      while (!var4.isEmpty() && var12++ < 6000) {
         x23h5ttlfi4p2e var17 = (x23h5ttlfi4p2e)var4.poll();
         BlockPos var18 = var17.x191jg6ukc9dt8();
         if (var7.add(var18)) {
            double var19 = xp31m43a2ifr6(var18, var2);
            if (var19 < var10) {
               var9 = var18;
               var10 = var19;
            }

            if (var18.getSquaredDistance(var2) <= 2.25) {
               var9 = var18;
               break;
            }

            for (Direction var22 : Type.HORIZONTAL) {
               BlockPos var23 = var18.offset(var22);
               if (var23.getX() >= var13 && var23.getX() <= var14 && var23.getZ() >= var15 && var23.getZ() <= var16) {
                  for (int var27 : new int[]{0, 1, -1}) {
                     BlockPos var28 = var23.up(var27);
                     if (var8.computeIfAbsent(var28, var2x -> x21dbjs9wlccy4(var0, var1, var2x)) && (var27 <= 0 || x1sodm3kk4gpjc(var0, var1, var18.up()))) {
                        int var29 = var17.x2xm2fy4xaxww7() + 10 + Math.abs(var27) * 4;
                        if (var29 < var5.getOrDefault(var28, Integer.MAX_VALUE)) {
                           var5.put(var28, var29);
                           var6.put(var28, var18);
                           var4.add(new x23h5ttlfi4p2e(var28, var29, var29 + xp31m43a2ifr6(var28, var2) * 10.0));
                        }
                        break;
                     }
                  }
               }
            }
         }
      }

      if (var9.equals(var3)) {
         return List.of();
      } else {
         ArrayList var30 = new ArrayList();

         for (BlockPos var31 = var9; !var31.equals(var3); var31 = (BlockPos)var6.get(var31)) {
            if (var31 == null) {
               return List.of();
            }

            var30.add(var31);
         }

         Collections.reverse(var30);
         return var30;
      }
   }

   private static double xp31m43a2ifr6(BlockPos var0, BlockPos var1) {
      return Math.abs(var0.getX() - var1.getX()) + Math.abs(var0.getZ() - var1.getZ()) + Math.abs(var0.getY() - var1.getY());
   }

   private static boolean x21dbjs9wlccy4(ServerWorld var0, MobEntity var1, BlockPos var2) {
      if (!var0.isOutOfHeightLimit(var2) && var0.getWorldBorder().contains(var2) && var0.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
         BlockState var3 = var0.getBlockState(var2.down());
         boolean var4 = var3.isSideSolidFullSquare(var0, var2.down(), Direction.UP)
            || var3.getBlock() instanceof StairsBlock
            || var3.getBlock() instanceof SlabBlock;
         boolean var5 = var0.getFluidState(var2).isIn(FluidTags.WATER) && var0.getFluidState(var2.up()).isEmpty();
         return (var0.getFluidState(var2.down()).isEmpty() && var4 || var5)
            && !var3.isOf(Blocks.MAGMA_BLOCK)
            && !var3.isOf(Blocks.CAMPFIRE)
            && x1sodm3kk4gpjc(var0, var1, var2);
      } else {
         return false;
      }
   }

   private static boolean x1sodm3kk4gpjc(ServerWorld var0, MobEntity var1, BlockPos var2) {
      double var3 = var1.getWidth() / 2.0;
      Box var5 = new Box(
         var2.getX() + 0.5 - var3 + 0.01,
         var2.getY() + 0.01,
         var2.getZ() + 0.5 - var3 + 0.01,
         var2.getX() + 0.5 + var3 - 0.01,
         var2.getY() + var1.getHeight() - 0.01,
         var2.getZ() + 0.5 + var3 - 0.01
      );

      label72:
      for (BlockPos var7 : BlockPos.iterate(BlockPos.ofFloored(var5.minX, var5.minY, var5.minZ), BlockPos.ofFloored(var5.maxX, var5.maxY, var5.maxZ))) {
         if (!var0.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)) {
            return false;
         }

         if (!var0.getFluidState(var7).isEmpty() && !var0.getFluidState(var7).isIn(FluidTags.WATER)) {
            return false;
         }

         BlockState var8 = var0.getBlockState(var7);
         if (!var0.getFluidState(var7).isIn(FluidTags.WATER)) {
            if (!var8.isOf(Blocks.FIRE)
               && !var8.isOf(Blocks.LAVA)
               && !var8.isOf(Blocks.CACTUS)
               && !var8.isOf(Blocks.SWEET_BERRY_BUSH)
               && !var8.isOf(Blocks.POWDER_SNOW)) {
               if (var8.getBlock() instanceof SnowBlock && (Integer)var8.get(SnowBlock.LAYERS) < 8 && var7.getY() == var2.getY()
                  || var8.getBlock() instanceof FenceGateBlock && (var1 instanceof VillagerEntity || x27aao31y6r2ta.x3di73z4zkdknf(var0, var7))
                  || var1 instanceof VillagerEntity && var8.getBlock() instanceof DoorBlock && DoorBlock.canOpenByHand(var8)) {
                  continue;
               }

               Iterator var9 = var8.getCollisionShape(var0, var7).getBoundingBoxes().iterator();

               Box var10;
               do {
                  if (!var9.hasNext()) {
                     continue label72;
                  }

                  var10 = (Box)var9.next();
               } while (!var10.offset(var7.getX(), var7.getY(), var7.getZ()).intersects(var5));

               return false;
            }

            return false;
         }
      }

      return true;
   }
}
