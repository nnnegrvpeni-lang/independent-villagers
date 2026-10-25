package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

public final class x37idyr79ed75m {
   public static final int xwho32kpnb881 = 32;
   public static final int x2vgg2qsnxoqv0 = 12;
   private static final double xqhki5nz20q5b = 144.0;
   private static final Map<x15kgoaq6i3m6i, x3aj7cq1p09gkf> x2smtlzj5x8if5 = new WeakHashMap<>();
   private static long x1h1idtou36wre;

   public static long x1xumgud6xlrp4() {
      return x1h1idtou36wre;
   }

   public static void x3didvqu3hbx58() {
      x2smtlzj5x8if5.clear();
      x1h1idtou36wre = 0L;
      x18ucnhocy9qn5.x26rn6haaqjid6();
   }

   private x37idyr79ed75m() {
   }

   public static Optional<LivingEntity> x3ekrcznmkym0j(ServerWorld var0, x15kgoaq6i3m6i var1) {
      Optional var2 = var1.x10fb3wo7mboo(var0.getTime())
         .<Entity>map(var0::getEntity)
         .filter(var0x -> var0x instanceof LivingEntity)
         .map(var0x -> (LivingEntity)var0x)
         .filter(x37idyr79ed75m::x1bj0phudo3a5m)
         .filter(var1x -> xs8awvrrlhug9(var1, var1x));
      if (var2.isPresent()) {
         return var2;
      } else {
         Optional var3 = var1.x2npriys7z2eli(var0.getTime())
            .<PlayerEntity>map(var0::getPlayerByUuid)
            .filter(x37idyr79ed75m::x1bj0phudo3a5m)
            .filter(var1x -> xs8awvrrlhug9(var1, var1x))
            .map(var0x -> (LivingEntity)var0x);
         return var3.isPresent()
            ? var3
            : var0.getEntitiesByClass(VillagerEntity.class, xx2tq85gza9uw(var1), LivingEntity::isAlive)
               .stream()
               .filter(var2x -> x2n55y2tom37h2.x3psqezd5jem54(var0, var2x.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
               .filter(var0x -> var0x.age - var0x.getLastAttackedTime() <= 100)
               .<LivingEntity>map(LivingEntity::getAttacker)
               .filter(x37idyr79ed75m::x1bj0phudo3a5m)
               .filter(var1x -> xs8awvrrlhug9(var1, var1x))
               .findFirst();
      }
   }

   public static boolean x1bj0phudo3a5m(LivingEntity var0) {
      return var0 != null
         && var0.isAlive()
         && !(var0 instanceof CreeperEntity)
         && !(var0 instanceof VillagerEntity)
         && !(var0 instanceof IronGolemEntity)
         && !(var0 instanceof PlayerEntity var1 && (var1.isCreative() || var1.isSpectator()));
   }

   public static boolean xs8awvrrlhug9(x15kgoaq6i3m6i var0, LivingEntity var1) {
      int var2 = var0.radius();
      double var3 = var1.getX() - (var0.core().getX() + 0.5);
      double var5 = var1.getZ() - (var0.core().getZ() + 0.5);
      return var3 * var3 + var5 * var5 <= var2 * var2 && Math.abs(var1.getY() - var0.core().getY()) <= 12.0;
   }

   public static Box xx2tq85gza9uw(x15kgoaq6i3m6i var0) {
      int var1 = var0.radius();
      return new Box(var0.core()).expand(var1, 12.0, var1);
   }

   public static Optional<LivingEntity> x3kaxmxx3tadjj(ServerWorld var0, x15kgoaq6i3m6i var1) {
      x3aj7cq1p09gkf var2 = x2smtlzj5x8if5.get(var1);
      long var3 = var0.getTime();
      if (var2 != null
         && var2.x137xpxqz8kwz == var0
         && var3 >= var2.xfdtg8nu348d5
         && var3 - var2.xfdtg8nu348d5 < 5L
         && var2.x3d4l1o8shgghr.filter(var1x -> !x1bj0phudo3a5m(var1x) || !xs8awvrrlhug9(var1, var1x)).isEmpty()) {
         return var2.x3d4l1o8shgghr;
      } else {
         x1h1idtou36wre++;
         Optional var5 = x2d6o0suqhd9mu(var0, var1);
         x2smtlzj5x8if5.put(var1, new x3aj7cq1p09gkf(var0, var3, var5));
         return var5;
      }
   }

   private static Optional<LivingEntity> x2d6o0suqhd9mu(ServerWorld var0, x15kgoaq6i3m6i var1) {
      Optional var2 = x3ekrcznmkym0j(var0, var1);
      if (var2.isPresent()) {
         return var2;
      } else {
         List var3 = var0.getEntitiesByClass(
            VillagerEntity.class,
            xx2tq85gza9uw(var1),
            var2x -> var2x.isAlive() && x2n55y2tom37h2.x3psqezd5jem54(var0, var2x.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false)
         );
         List var4 = var0.getEntitiesByClass(IronGolemEntity.class, xx2tq85gza9uw(var1), LivingEntity::isAlive);
         return var0.getEntitiesByClass(HostileEntity.class, xx2tq85gza9uw(var1), var0x -> var0x.isAlive() && !(var0x instanceof CreeperEntity))
            .stream()
            .filter(var3x -> xs8awvrrlhug9(var1, var3x) && x2dm767cry9ux8(var3x, var3, var4))
            .min(
               Comparator.<HostileEntity>comparingInt(
                     var1x -> var1x.getTarget() instanceof VillagerEntity var2x && var1.roleOf(var2x.getUuid()) == x1rxiy9mtu5b3h.x3r8p2tvihwpph
                        ? 0
                        : (var1x.getTarget() instanceof VillagerEntity ? 1 : 2)
                  )
                  .thenComparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core()))
            )
            .map(var0x -> (LivingEntity)var0x);
      }
   }

   private static boolean x2dm767cry9ux8(HostileEntity var0, List<VillagerEntity> var1, List<IronGolemEntity> var2) {
      LivingEntity var3 = var0.getTarget();
      return (!(var3 instanceof VillagerEntity) || !var1.contains(var3)) && (!(var3 instanceof IronGolemEntity) || !var2.contains(var3))
         ? var1.stream().anyMatch(var1x -> var1x.squaredDistanceTo(var0) <= 144.0 && Math.abs(var1x.getY() - var0.getY()) <= 6.0 && var1x.canSee(var0))
            || var2.stream().anyMatch(var1x -> var1x.squaredDistanceTo(var0) <= 144.0 && Math.abs(var1x.getY() - var0.getY()) <= 6.0 && var1x.canSee(var0))
         : true;
   }

   public static void xgnwo32dqwtgi(ServerWorld var0, x15kgoaq6i3m6i var1, PlayerEntity var2, int var3) {
      if (var3 > 0 && !var2.isCreative() && !var2.isSpectator()) {
         x2smtlzj5x8if5.remove(var1);
         var1.x2jh70rewptpw1(var2.getUuid(), var0.getTime() + 1200L, var3);
         var1.xz6ij33c0780z(var0.getTime());
         xhannuplm2isb(var0, var1, var2);
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
      }
   }

   public static void x9xz66k4m5ee9(ServerWorld var0, x15kgoaq6i3m6i var1, LivingEntity var2) {
      if (x1bj0phudo3a5m(var2) && xs8awvrrlhug9(var1, var2)) {
         x2smtlzj5x8if5.remove(var1);
         var1.x8tht6cvqikr(var2.getUuid(), var0.getTime() + 1200L);
         var1.xz6ij33c0780z(var0.getTime());
         xhannuplm2isb(var0, var1, var2);
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
      }
   }

   private static void xhannuplm2isb(ServerWorld var0, x15kgoaq6i3m6i var1, LivingEntity var2) {
      var0.getEntitiesByClass(IronGolemEntity.class, xx2tq85gza9uw(var1), LivingEntity::isAlive)
         .stream()
         .filter(var2x -> x2n55y2tom37h2.x3psqezd5jem54(var0, var2x.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
         .forEach(var2x -> {
            var2x.setTarget(var2);
            x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var2x, var2.getBlockPos(), 1.9);
         });
      var0.getEntitiesByClass(VillagerEntity.class, xx2tq85gza9uw(var1), LivingEntity::isAlive)
         .stream()
         .filter(var1x -> var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x3r8p2tvihwpph)
         .forEach(var2x -> {
            if (var2x.isSleeping()) {
               var2x.wakeUp();
            }

            if (var2x.hasCustomer()) {
               var2x.setCustomer(null);
            }

            var2x.getBrain().remember(xxao083dbo7ag.x14b5znl8ndblc, var2, 1200L);
            var2x.getBrain().doExclusively(Activity.FIGHT);
            x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var2x, var2.getBlockPos(), 1.15);
         });
   }
}
