package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap.Type;

public final class VillagerDeliverToStorageTask extends MultiTickTask<VillagerEntity> {
   private Colony x9c3bb07a70ik;
   private LivingEntity x2iis7neec0fi2;
   private long x20qrt67vkcoed;
   private long x26ep0h4mwdyaa;
   private long x21bf69nq5jtr5;
   private long x17fuqwxy7vxi0;

   public VillagerDeliverToStorageTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 1200);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer()) {
         Optional var3 = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos()).filter(var1x -> var1x.roleOf(var2.getUuid()) == ColonyRole.x3r8p2tvihwpph);
         if (var3.isEmpty()) {
            return false;
         } else {
            this.x9c3bb07a70ik = (Colony)var3.get();
            this.x2iis7neec0fi2 = this.x2h60gnu0hedrs(var2)
               .filter(var1x -> RaidDetectionHelper.x1bj0phudo3a5m(var1x) && RaidDetectionHelper.xs8awvrrlhug9(this.x9c3bb07a70ik, var1x))
               .orElseGet(() -> RaidDetectionHelper.x3kaxmxx3tadjj(var1, this.x9c3bb07a70ik).orElse(null));
            boolean var4 = this.x9c3bb07a70ik.x3klfyffbbfab2() != Long.MIN_VALUE && var1.getTime() - this.x9c3bb07a70ik.x3klfyffbbfab2() <= 100L;
            return this.x2iis7neec0fi2 != null || var4 || var2.getHealth() < var2.getMaxHealth() * 0.3F;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x9c3bb07a70ik != null
         && this.x9c3bb07a70ik.roleOf(var2.getUuid()) == ColonyRole.x3r8p2tvihwpph
         && (
            this.x2iis7neec0fi2 != null && this.x2iis7neec0fi2.isAlive() && RaidDetectionHelper.xs8awvrrlhug9(this.x9c3bb07a70ik, this.x2iis7neec0fi2)
               || this.x9c3bb07a70ik.x3klfyffbbfab2() != Long.MIN_VALUE && var3 - this.x9c3bb07a70ik.x3klfyffbbfab2() <= 100L
               || var2.getHealth() < var2.getMaxHealth() * 0.6F
         );
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x20qrt67vkcoed = var3;
      this.x26ep0h4mwdyaa = var3;
      this.x21bf69nq5jtr5 = var3;
      this.x17fuqwxy7vxi0 = var3;
      if (var2.getMainHandStack().isEmpty()) {
         ColonyManager.x3lxwdirnn77qc(
               var1, this.x9c3bb07a70ik, var0 -> var0.isIn(ItemTags.SWORDS) || var0.isIn(ItemTags.AXES) || var0.isIn(ItemTags.BOW_ENCHANTABLE)
            )
            .ifPresent(var1x -> var2.equipStack(EquipmentSlot.MAINHAND, var1x));
      }
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (!MilitiaCombatTask.x12ghbsk7tys8r(var1, var2, this.x9c3bb07a70ik)) {
         if (var2.getHealth() < var2.getMaxHealth() * 0.3F) {
            this.x2orverzrrwr2f(var1, var2, var3);
         } else {
            if (var3 >= this.x26ep0h4mwdyaa) {
               this.x2iis7neec0fi2 = this.x2h60gnu0hedrs(var2)
                  .filter(var1x -> RaidDetectionHelper.x1bj0phudo3a5m(var1x) && RaidDetectionHelper.xs8awvrrlhug9(this.x9c3bb07a70ik, var1x))
                  .orElseGet(() -> RaidDetectionHelper.x3kaxmxx3tadjj(var1, this.x9c3bb07a70ik).orElse(null));
               this.x26ep0h4mwdyaa = var3 + 10L;
            }

            if (this.x2iis7neec0fi2 == null) {
               var2.getBrain().forget(ColonyMemoryModules.x14b5znl8ndblc);
               BlockPos var12 = this.xel4gvy3cgwwh(var1, this.x9c3bb07a70ik, var2);
               if (var3 >= this.x20qrt67vkcoed && var2.getBlockPos().getSquaredDistance(var12) > 36.0 && ColonyManager.x5ll9elqpk1a4(var1)) {
                  var2.getNavigation().startMovingTo(var12.getX() + 0.5, var12.getY(), var12.getZ() + 0.5, 0.65);
                  this.x20qrt67vkcoed = var3 + 20L;
               }
            } else {
               var2.getBrain().remember(ColonyMemoryModules.x14b5znl8ndblc, this.x2iis7neec0fi2);
               double var5 = var2.squaredDistanceTo(this.x2iis7neec0fi2);
               boolean var7 = var2.getAttacker() != null && var2.age - var2.getLastAttackedTime() <= 30;
               boolean var8 = var5 >= 25.0 && !var7 && var2.canSee(this.x2iis7neec0fi2);
               if (var3 >= this.x17fuqwxy7vxi0 && this.x1t6stxd93drq7(var2, var8)) {
                  this.x17fuqwxy7vxi0 = var3 + 20L;
               }

               ItemStack var9 = var2.getMainHandStack();
               var2.getLookControl().lookAt(this.x2iis7neec0fi2, 30.0F, 30.0F);
               boolean var10 = var2.getOffHandStack().isOf(Items.SHIELD) && (var7 || var5 <= 16.0) && var3 < this.x20qrt67vkcoed;
               if (var10) {
                  float var13 = (float)(Math.toDegrees(Math.atan2(this.x2iis7neec0fi2.getZ() - var2.getZ(), this.x2iis7neec0fi2.getX() - var2.getX())) - 90.0);
                  var2.setYaw(var13);
                  var2.setBodyYaw(var13);
                  var2.setHeadYaw(var13);
                  if (!var2.isUsingItem() || var2.getActiveHand() != Hand.OFF_HAND) {
                     var2.setCurrentHand(Hand.OFF_HAND);
                  }

                  if (var5 > 4.0 && var3 >= this.x21bf69nq5jtr5) {
                     ColonyMovementPlanner.x1wi30zgg7nf81(var1, var2, this.x2iis7neec0fi2.getBlockPos(), 0.72);
                     this.x21bf69nq5jtr5 = var3 + 12L;
                  }
               } else {
                  if (var2.isUsingItem() && var2.getActiveHand() == Hand.OFF_HAND) {
                     var2.stopUsingItem();
                  }

                  if (var9.isOf(Items.BOW) && var5 <= 225.0 && var2.canSee(this.x2iis7neec0fi2)) {
                     if (!(var5 < 49.0) && this.x2mc1rt61nbu7q(var1, var2, this.x2iis7neec0fi2)) {
                        var2.getNavigation().stop();
                     } else if (var3 >= this.x21bf69nq5jtr5) {
                        this.x15umqhn1l74rw(var1, var2, this.x2iis7neec0fi2);
                        this.x21bf69nq5jtr5 = var3 + 12L;
                     }

                     if (var3 >= this.x20qrt67vkcoed && this.x2mc1rt61nbu7q(var1, var2, this.x2iis7neec0fi2)) {
                        this.x1hhdroc5eq3e0(var1, var2, this.x2iis7neec0fi2, var9);
                        this.x20qrt67vkcoed = var3 + 30L;
                     }
                  } else if (var5 > 4.0) {
                     if (var3 >= this.x21bf69nq5jtr5) {
                        ColonyMovementPlanner.x1wi30zgg7nf81(var1, var2, this.x2iis7neec0fi2.getBlockPos(), 0.85);
                        this.x21bf69nq5jtr5 = var3 + 12L;
                     }
                  } else if (var3 >= this.x20qrt67vkcoed && var2.canSee(this.x2iis7neec0fi2)) {
                     DamageSource var11 = IronGolemAttackHelper.xqzpg1xselqv5(var1, var2, this.x2iis7neec0fi2, var2.getDamageSources().mobAttack(var2));
                     if (this.x2iis7neec0fi2.damage(var1, var11, IronGolemAttackHelper.x3c7ytg0gca1m6(var1, var2, this.x2iis7neec0fi2, var11))) {
                        EnchantmentHelper.onTargetDamaged(var1, this.x2iis7neec0fi2, var11, var9);
                        var9.postDamageEntity(this.x2iis7neec0fi2, var2);
                     }

                     var2.swingHand(Hand.MAIN_HAND);
                     this.x20qrt67vkcoed = var3 + IronGolemAttackHelper.x3jcrekq98bvm8(var9);
                  }
               }
            }
         }
      }
   }

   private Optional<LivingEntity> x2h60gnu0hedrs(VillagerEntity var1) {
      return var1.getBrain().getOptionalRegisteredMemory(ColonyMemoryModules.x14b5znl8ndblc);
   }

   private boolean x1t6stxd93drq7(VillagerEntity var1, boolean var2) {
      Predicate<ItemStack> var3 = var2 ? (ItemStack var0) -> var0.isOf(Items.BOW) : (ItemStack var0) -> var0.isIn(ItemTags.SWORDS) || var0.isIn(ItemTags.AXES);
      if (var3.test(var1.getMainHandStack())) {
         return false;
      } else {
         for (int var4 = 0; var4 < var1.getInventory().size(); var4++) {
            ItemStack var5 = var1.getInventory().getStack(var4);
            if (var3.test(var5)) {
               var1.stopUsingItem();
               ItemStack var6 = var5.copy();
               var1.getInventory().setStack(var4, ItemStack.EMPTY);
               ItemStack var7 = var1.getMainHandStack().copy();
               var1.equipStack(EquipmentSlot.MAINHAND, var6);
               if (!var7.isEmpty()) {
                  ItemStack var8 = var1.getInventory().addStack(var7);
                  if (!var8.isEmpty()) {
                     var1.dropStack((ServerWorld)var1.getEntityWorld(), var8);
                  }
               }

               return true;
            }
         }

         return false;
      }
   }

   private void x1hhdroc5eq3e0(ServerWorld var1, VillagerEntity var2, LivingEntity var3, ItemStack var4) {
      ArrowEntity var5 = new ArrowEntity(var1, var2, new ItemStack(Items.ARROW), var4);
      double var6 = var3.getX() - var2.getX();
      double var8 = var3.getZ() - var2.getZ();
      double var10 = Math.sqrt(var6 * var6 + var8 * var8);
      double var12 = var3.getY() + var3.getHeight() * 0.5 - var5.getY() + var10 * 0.18;
      var5.setVelocity(var6, var12, var8, 1.6F, 5.0F);
      var5.setDamage(4.0);
      var1.spawnEntity(var5);
      var2.swingHand(Hand.MAIN_HAND);
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var2.isUsingItem() && var2.getActiveHand() == Hand.OFF_HAND) {
         var2.stopUsingItem();
      }

      ColonyMovementPlanner.x3oupbjeq14mj8(var2);
      var2.getBrain().forget(ColonyMemoryModules.x14b5znl8ndblc);
      this.x9c3bb07a70ik = null;
      this.x2iis7neec0fi2 = null;
   }

   private BlockPos xel4gvy3cgwwh(ServerWorld var1, Colony var2, VillagerEntity var3) {
      Box var4 = new Box(var2.core()).expand(Math.min(var2.radius(), 48));
      return var1.getEntitiesByClass(
            VillagerEntity.class,
            var4,
            var1x -> var1x.isAlive() && var2.roleOf(var1x.getUuid()) == ColonyRole.x3r8p2tvihwpph && !var1x.getMainHandStack().isOf(Items.BOW)
         )
         .stream()
         .min(Comparator.comparing(Entity::getUuid))
         .filter(var1x -> var1x != var3)
         .<BlockPos>map(Entity::getBlockPos)
         .orElse(var2.core());
   }

   public static void patrol(ServerWorld var0, Colony var1, VillagerEntity var2) {
      VillagerEntity var3 = var0.getEntitiesByClass(
            VillagerEntity.class, RaidDetectionHelper.xx2tq85gza9uw(var1), var1x -> var1x.isAlive() && var1.roleOf(var1x.getUuid()) == ColonyRole.x3r8p2tvihwpph
         )
         .stream()
         .min(Comparator.comparing(Entity::getUuid))
         .orElse(var2);
      BlockPos var4;
      if (var3 != var2) {
         if (var2.squaredDistanceTo(var3) < 16.0) {
            return;
         }

         var4 = var3.getBlockPos();
      } else {
         int var5 = (int)(var0.getTime() / 600L % 4L);
         int var6 = var1.core().getX() + (var5 == 0 ? 10 : (var5 == 2 ? -10 : 0));
         int var7 = var1.core().getZ() + (var5 == 1 ? 10 : (var5 == 3 ? -10 : 0));
         if (!var0.getChunkManager().isChunkLoaded(var6 >> 4, var7 >> 4)) {
            return;
         }

         var4 = new BlockPos(var6, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var6, var7), var7);
      }

      if (ColonyManager.x5ll9elqpk1a4(var0)) {
         var2.getNavigation().startMovingTo(var4.getX() + 0.5, var4.getY(), var4.getZ() + 0.5, 0.6);
      }
   }

   private boolean x2mc1rt61nbu7q(ServerWorld var1, VillagerEntity var2, LivingEntity var3) {
      Vec3d var4 = var2.getEyePos();
      Vec3d var5 = var3.getEyePos();
      return var1.getOtherEntities(
            var2, new Box(var4, var5).expand(1.0), var0 -> var0 instanceof VillagerEntity || var0 instanceof IronGolemEntity || var0 instanceof PlayerEntity
         )
         .stream()
         .filter(var1x -> var1x != var3)
         .noneMatch(var2x -> var2x.getBoundingBox().expand(0.4).raycast(var4, var5).isPresent());
   }

   private void x15umqhn1l74rw(ServerWorld var1, VillagerEntity var2, LivingEntity var3) {
      double var4 = var2.getX() - var3.getX();
      double var6 = var2.getZ() - var3.getZ();
      double var8 = Math.max(0.1, Math.sqrt(var4 * var4 + var6 * var6));
      double var10 = (var2.getId() & 1) == 0 ? 1.0 : -1.0;

      for (int var12 = 0; var12 < 3; var12++) {
         double var13 = var12 == 0 ? var10 * 2.0 : (var12 == 1 ? -var10 * 4.0 : 0.0);
         int var15 = (int)Math.floor(var2.getX() + var4 / var8 * 5.0 - var6 / var8 * var13);
         int var16 = (int)Math.floor(var2.getZ() + var6 / var8 * 5.0 + var4 / var8 * var13);
         if (var1.getChunkManager().isChunkLoaded(var15 >> 4, var16 >> 4)) {
            BlockPos var17 = new BlockPos(var15, var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var15, var16), var16);
            if (!(Math.abs(var17.getY() - var2.getY()) > 2.0)
               && var1.getFluidState(var17.down()).isEmpty()
               && !(var17.getSquaredDistance(this.x9c3bb07a70ik.core()) > (double)this.x9c3bb07a70ik.radius() * this.x9c3bb07a70ik.radius())) {
               if (!ColonyManager.x5ll9elqpk1a4(var1)) {
                  return;
               }

               Path var18 = var2.getNavigation().findPathTo(var17, 0);
               if (var18 != null && var18.reachesTarget()) {
                  var2.getNavigation().startMovingAlong(var18, 0.95);
                  return;
               }
            }
         }
      }
   }

   private void x2orverzrrwr2f(ServerWorld var1, VillagerEntity var2, long var3) {
      if (!this.x9c3bb07a70ik.x2ad3s8aentmo4().isEmpty()) {
         BlockPos var5 = this.x9c3bb07a70ik.x2ad3s8aentmo4().getFirst();
         ColonyNavigationHelper.x2wzylgizxltef(var1, var2, var5, 0.75);
         if (var2.squaredDistanceTo(var5.getX() + 0.5, var5.getY(), var5.getZ() + 0.5) <= 9.0 && var3 >= this.x20qrt67vkcoed) {
            ColonyManager.x3lxwdirnn77qc(var1, this.x9c3bb07a70ik, var0 -> var0.get(DataComponentTypes.FOOD) != null).ifPresent(var1x -> var2.heal(4.0F));
            this.x20qrt67vkcoed = var3 + 40L;
         }
      } else if (ColonyManager.x5ll9elqpk1a4(var1)) {
         var2.getNavigation().startMovingTo(this.x9c3bb07a70ik.core().getX(), this.x9c3bb07a70ik.core().getY(), this.x9c3bb07a70ik.core().getZ(), 0.75);
      }
   }
}
