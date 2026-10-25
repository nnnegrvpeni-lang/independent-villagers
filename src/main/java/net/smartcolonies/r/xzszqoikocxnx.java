package net.smartcolonies.r;

import java.util.Comparator;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public final class xzszqoikocxnx {
   private static final Map<VillagerEntity, Long> x36r0gqxc0c4p4 = new WeakHashMap<>();
   private static final Map<VillagerEntity, xjiidbqvc8qff> x35xo3a8r0bq7r = new WeakHashMap<>();
   private static final Map<x15kgoaq6i3m6i, xbgyumsp3yi3a> x8qmioahtujgp = new WeakHashMap<>();
   private static long x2m5hd6j88izsr;

   public static long x6x64hgghjnq6() {
      return x2m5hd6j88izsr;
   }

   public static void x30zm3b0hljw9q() {
      x36r0gqxc0c4p4.clear();
      x35xo3a8r0bq7r.clear();
      x8qmioahtujgp.clear();
      x2m5hd6j88izsr = 0L;
   }

   private xzszqoikocxnx() {
   }

   public static boolean x1dlmo6wmzb2ly(VillagerEntity var0, PersistentProjectileEntity var1) {
      return var1.getOwner() != var0 && !var1.isRemoved() ? xxrhfgph156lx(var0, var1) < Double.POSITIVE_INFINITY && var0.canSee(var1) : false;
   }

   private static double xxrhfgph156lx(VillagerEntity var0, PersistentProjectileEntity var1) {
      Vec3d var2 = var1.getVelocity();
      if (var2.lengthSquared() < 0.04) {
         return Double.POSITIVE_INFINITY;
      } else {
         Vec3d var3 = var1.getEntityPos();
         Box var4 = var0.getBoundingBox().expand(0.2);
         double var5 = var1.isTouchingWater() ? 0.6 : 0.99;

         for (int var7 = 0; var7 < 24; var7++) {
            Vec3d var8 = var3.add(var2);
            if (var4.contains(var3) || var4.raycast(var3, var8).isPresent()) {
               return var7;
            }

            var3 = var8;
            var2 = var2.multiply(var5).add(0.0, -var1.getFinalGravity(), 0.0);
         }

         return Double.POSITIVE_INFINITY;
      }
   }

   public static boolean x12ghbsk7tys8r(ServerWorld var0, VillagerEntity var1, x15kgoaq6i3m6i var2) {
      if (var2.roleOf(var1.getUuid()) == x1rxiy9mtu5b3h.x3r8p2tvihwpph
         && !var1.isSleeping()
         && !var1.hasCustomer()
         && var1.getOffHandStack().isOf(Items.SHIELD)) {
         long var3 = var0.getTime();
         xjiidbqvc8qff var5 = x35xo3a8r0bq7r.get(var1);
         if (var5 != null && var5.x2uroynf8b4qnl == var3) {
            return var5.xxr5jl7q1ghs0;
         } else {
            xbgyumsp3yi3a var6 = x8qmioahtujgp.get(var2);
            if (var6 == null || var6.x3pwphu5fgb0hr != var0 || var6.x3tdytvdbh7xq2 != var3) {
               x2m5hd6j88izsr++;
               var6 = new xbgyumsp3yi3a(
                  var0,
                  var3,
                  var0.getEntitiesByClass(
                     PersistentProjectileEntity.class,
                     x37idyr79ed75m.xx2tq85gza9uw(var2).expand(32.0),
                     var0x -> !var0x.isRemoved() && var0x.getVelocity().lengthSquared() >= 0.04
                  )
               );
               x8qmioahtujgp.put(var2, var6);
            }

            PersistentProjectileEntity var7 = var6.x1j7ge6q8c5e6g
               .stream()
               .filter(
                  var2x -> x1dlmo6wmzb2ly(var1, var2x)
                     && !(var2x.getOwner() instanceof VillagerEntity var3x && var2.roleOf(var3x.getUuid()) == x1rxiy9mtu5b3h.x3r8p2tvihwpph)
               )
               .min(Comparator.comparingDouble(var1x -> x1inv2pzkj8ftu(var1, var1x)))
               .orElse(null);
            LivingEntity var8 = null;
            if (var7 == null) {
               LivingEntity var9 = (LivingEntity)var1.getBrain().getOptionalRegisteredMemory(xxao083dbo7ag.x14b5znl8ndblc).orElse(null);
               if (var9 != null
                  && var9.isAlive()
                  && var1.squaredDistanceTo(var9) <= 576.0
                  && var1.canSee(var9)
                  && (
                     var9.isUsingItem() && (var9.getActiveItem().isOf(Items.BOW) || var9.getActiveItem().isOf(Items.CROSSBOW))
                        || var9 instanceof AbstractSkeletonEntity var10
                           && var10.getTarget() == var1
                           && var10.getMainHandStack().isOf(Items.BOW)
                           && Math.floorMod(var3, 40) < 20
                  )) {
                  var8 = var9;
               }
            }

            if (var7 != null || var8 != null) {
               Entity var15 = var7 != null ? var7 : var8;
               double var16 = var15.getX() - var1.getX();
               double var12 = var15.getZ() - var1.getZ();
               float var14 = (float)(Math.toDegrees(Math.atan2(var12, var16)) - 90.0);
               var1.setYaw(var14);
               var1.setBodyYaw(var14);
               var1.setHeadYaw(var14);
               var1.getLookControl().lookAt((Entity)var15, 90.0F, 90.0F);
               x36r0gqxc0c4p4.put(var1, var3 + (var7 == null && !var8.isUsingItem() ? 6 : 12));
            }

            if (var3 >= x36r0gqxc0c4p4.getOrDefault(var1, Long.MIN_VALUE)) {
               if (x36r0gqxc0c4p4.remove(var1) != null && var1.isUsingItem() && var1.getActiveHand() == Hand.OFF_HAND) {
                  var1.stopUsingItem();
               }

               x35xo3a8r0bq7r.put(var1, new xjiidbqvc8qff(var3, false));
               return false;
            } else {
               if (!var1.isUsingItem() || var1.getActiveHand() != Hand.OFF_HAND) {
                  var1.stopUsingItem();
                  var1.setCurrentHand(Hand.OFF_HAND);
               }

               var1.getNavigation().stop();
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var1, "прикрывается щитом от стрелы");
               x35xo3a8r0bq7r.put(var1, new xjiidbqvc8qff(var3, true));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   private static double x1inv2pzkj8ftu(VillagerEntity var0, PersistentProjectileEntity var1) {
      return xxrhfgph156lx(var0, var1);
   }
}
