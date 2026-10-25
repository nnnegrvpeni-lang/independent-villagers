package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus;

public final class x36u1fhs2z6uoi extends MultiTickTask<VillagerEntity> {
   private static final Map<VillagerEntity, BlockPos> x2hbwcpenut843 = new WeakHashMap<>();
   private x15kgoaq6i3m6i x2d7pnemo6v9my;
   private BlockPos x2rn5xik6bqfpl;
   private BlockPos xq0qi837nwm2w;
   private long x28ccgmu677r01;
   private boolean x19rxy5xx7call;
   private long x2if2n1g199b49;
   private long x13n4ea8a3sluj;

   public static void clearRuntimeCaches() {
      x2hbwcpenut843.clear();
   }

   public x36u1fhs2z6uoi() {
      super(ImmutableMap.of(), 12000);
   }

   public static boolean active(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = Math.min(1024, var1.radius() + 32);
      Raid var3 = var0.getRaidManager().getRaidAt(var1.core(), var2 * var2);
      return var3 != null && !var3.isFinished() && !var3.hasStopped() && var3.isActive();
   }

   public static boolean civilian(x15kgoaq6i3m6i var0, VillagerEntity var1) {
      return var0.roleOf(var1.getUuid()) != x1rxiy9mtu5b3h.x3r8p2tvihwpph;
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      this.x2d7pnemo6v9my = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos()).filter(var2x -> active(var1, var2x) && civilian(var2x, var2)).orElse(null);
      return this.x2d7pnemo6v9my != null;
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      x2hbwcpenut843.remove(var2);
      this.x2rn5xik6bqfpl = this.xq0qi837nwm2w = null;
      this.x2if2n1g199b49 = this.x13n4ea8a3sluj = var3;
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return this.x2d7pnemo6v9my != null && active(var1, this.x2d7pnemo6v9my) && civilian(this.x2d7pnemo6v9my, var2);
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var2.isSleeping()) {
         var2.wakeUp();
      }

      if (this.x2rn5xik6bqfpl != null
         && (
            !x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, this.x2rn5xik6bqfpl)
               || var1.isSkyVisible(this.x2rn5xik6bqfpl)
               || var3 % 100L == 0L && !x15at4spp0fe1m(var1, this.x2rn5xik6bqfpl, new HashMap<>())
         )) {
         this.x2rn5xik6bqfpl = null;
         x2hbwcpenut843.remove(var2);
      }

      if (this.x2rn5xik6bqfpl == null && var3 >= this.x2if2n1g199b49) {
         this.x2rn5xik6bqfpl = findShelter(var1, this.x2d7pnemo6v9my, var2);
         this.x2if2n1g199b49 = var3 + 100L;
      }

      if (this.x2rn5xik6bqfpl == null) {
         x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "рейд: нет доступного укрытия");
      } else {
         x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "прячется от рейда: " + this.x2rn5xik6bqfpl.toShortString());
         var2.getBrain().forget(MemoryModuleType.WALK_TARGET);
         if (var2.squaredDistanceTo(this.x2rn5xik6bqfpl.getX() + 0.5, this.x2rn5xik6bqfpl.getY(), this.x2rn5xik6bqfpl.getZ() + 0.5) <= 1.0
            && this.x1l8tf1y94ka6n(var1, var2, var3)) {
            var2.getNavigation().stop();
         } else {
            if (var3 >= this.x13n4ea8a3sluj) {
               x1c1r5c6a13gtl.xf8lnb134kd0j(var1, var2, this.x2rn5xik6bqfpl, 1.05);
               if (x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x2rn5xik6bqfpl, var3)) {
                  this.x2rn5xik6bqfpl = null;
                  x2hbwcpenut843.remove(var2);
               }

               this.x13n4ea8a3sluj = var3 + 20L;
            }
         }
      }
   }

   public static BlockPos findShelter(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      HashSet<BlockPos> var3 = new HashSet<>();
      Map<BlockPos, Boolean> var4 = new HashMap<>();
      var0.getPointOfInterestStorage()
         .getPositions(var0x -> var0x.matchesKey(PointOfInterestTypes.HOME), var0x -> true, var1.core(), var1.radius(), OccupationStatus.ANY)
         .filter(var1x -> var0.getChunkManager().isChunkLoaded(var1x.getX() >> 4, var1x.getZ() >> 4))
         .sorted(Comparator.comparingDouble(var1x -> var1x.getSquaredDistance(var2.getBlockPos())))
         .limit(32L)
         .forEach(
            var4x -> {
               for (int var5 = -2; var5 <= 2; var5++) {
                  for (int var6x = -2; var6x <= 2; var6x++) {
                     BlockPos var7x = var4x.add(var5, 0, var6x);
                     if (var0.getChunkManager().isChunkLoaded(var7x.getX() >> 4, var7x.getZ() >> 4)
                        && x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var7x)
                        && x15at4spp0fe1m(var0, var7x, var4)
                        && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var7x)
                        && (
                           x20rghnsl2yowl.x239t1m3hfi74d(var0, var7x)
                              || var1.x1ja61emeln2hc()
                                 .stream()
                                 .anyMatch(var1xx -> var1xx.x2nfrt8qxdscso().isEmpty() && var1xx.x24kbqv6g4kkrg().contains(var7x))
                        )) {
                        var3.add(var7x);
                     }
                  }
               }
            }
         );

      for (x2arpn38dclo1g var6 : var1.x1ja61emeln2hc()) {
         if (!var6.x2nfrt8qxdscso().isPresent() && var6.x2s8emavervqrz()) {
            BlockBox var7 = var6.x24kbqv6g4kkrg();
            int var8 = (var7.getMinX() + var7.getMaxX()) / 2;
            int var9 = (var7.getMinZ() + var7.getMaxZ()) / 2;

            for (byte var10 = -4; var10 <= 4; var10 += 2) {
               for (byte var11 = -4; var11 <= 4; var11 += 2) {
                  for (int var12 = 1; var12 <= 4; var12++) {
                     BlockPos var13 = new BlockPos(var8 + var10, var7.getMinY() + var12, var9 + var11);
                     if (var7.contains(var13)
                        && var0.getChunkManager().isChunkLoaded(var13.getX() >> 4, var13.getZ() >> 4)
                        && x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var13)
                        && x15at4spp0fe1m(var0, var13, var4)
                        && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var13)) {
                        var3.add(var13);
                     }
                  }
               }
            }
         }
      }

      var3.removeIf(var2x -> x1c1r5c6a13gtl.xps35ehel6nkk(var2, var2x, var0.getTime()));
      HashSet<BlockPos> var17 = new HashSet<>(var3);
      var17.removeIf(
         var2x -> x2hbwcpenut843.entrySet()
            .stream()
            .anyMatch(
               var3x -> var3x.getKey() != var2
                  && var3x.getKey().isAlive()
                  && var3x.getKey().getEntityWorld() == var0
                  && var3x.getValue().getSquaredDistance(var2x) < 2.0
            )
      );
      if (!var17.isEmpty()) {
         var3.retainAll(var17);
      }

      if (!var3.isEmpty() && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
         var2.getNavigation().setCanOpenDoors(true);
         var2.getNavigation().setMaxFollowRange(128.0F);
         var2.getNavigation().setRangeMultiplier(8.0F);

         Path var18;
         try {
            var18 = var2.getNavigation().findPathTo(var3, 0);
         } finally {
            var2.getNavigation().resetRangeMultiplier();
         }

         if (var18 != null && var18.reachesTarget()) {
            x2hbwcpenut843.put(var2, var18.getTarget());
            return var18.getTarget();
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      x2hbwcpenut843.remove(var2);
      var2.getNavigation().stop();
      this.x2rn5xik6bqfpl = null;
      this.x2d7pnemo6v9my = null;
   }

   private boolean x1l8tf1y94ka6n(ServerWorld var1, VillagerEntity var2, long var3) {
      BlockPos var5 = var2.getBlockPos();
      if (!var5.equals(this.xq0qi837nwm2w) || var3 - this.x28ccgmu677r01 >= 100L) {
         this.xq0qi837nwm2w = var5;
         this.x28ccgmu677r01 = var3;
         this.x19rxy5xx7call = x15at4spp0fe1m(var1, var5, new HashMap<>());
      }

      return this.x19rxy5xx7call;
   }

   private static boolean x15at4spp0fe1m(ServerWorld var0, BlockPos var1, Map<BlockPos, Boolean> var2) {
      if (var2.containsKey(var1)) {
         return (Boolean)var2.get(var1);
      } else if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var1) && !var0.isSkyVisible(var1)) {
         HashSet<BlockPos> var3 = new HashSet<>();
         ArrayDeque<BlockPos> var4 = new ArrayDeque<>();
         var3.add(var1);
         var4.add(var1);
         boolean var5 = true;

         label76:
         while (!var4.isEmpty()) {
            BlockPos var6 = (BlockPos)var4.removeFirst();
            if (var3.size() <= 192 && Math.abs(var6.getX() - var1.getX()) <= 16 && Math.abs(var6.getZ() - var1.getZ()) <= 16 && !var0.isSkyVisible(var6)) {
               Iterator var7 = Type.HORIZONTAL.iterator();

               while (true) {
                  if (var7.hasNext()) {
                     Direction var8 = (Direction)var7.next();
                     BlockPos var9 = var6.offset(var8);
                     if (var0.getChunkManager().isChunkLoaded(var9.getX() >> 4, var9.getZ() >> 4)) {
                        BlockState var10 = var0.getBlockState(var9);
                        if (var10.getBlock() instanceof DoorBlock) {
                           continue;
                        }

                        boolean var11 = (var10.getCollisionShape(var0, var9).isEmpty() || var10.isOf(Blocks.SNOW) && (Integer)var10.get(SnowBlock.LAYERS) < 8)
                           && var0.getBlockState(var9.up()).getCollisionShape(var0, var9.up()).isEmpty();
                        if (var11 && var3.add(var9)) {
                           var4.addLast(var9);
                        }
                        continue;
                     }

                     var5 = false;
                  }

                  if (!var5) {
                     break label76;
                  }
                  continue label76;
               }
            }

            var5 = false;
            break;
         }

         for (BlockPos var13 : var3) {
            var2.put(var13, var5);
         }

         return var5;
      } else {
         return false;
      }
   }
}
