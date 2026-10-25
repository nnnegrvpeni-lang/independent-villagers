package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public final class BuilderStarterChestTask extends MultiTickTask<VillagerEntity> {
   private Colony xnwtgacms2tpk;
   private BlockPos x307t38ql367ok;
   private long x1hqs3il4h0q4l;
   private final BlockInteractionTracker x2ws1oqe8gabg8 = new BlockInteractionTracker();
   private ItemStack x1zzy1362cikeg = ItemStack.EMPTY;

   public BuilderStarterChestTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 2400);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && ColonyProtectionUtils.x35ev8l9vc9uk7(var1)) {
         this.xnwtgacms2tpk = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r && var1x.x2s37os1kht7vr())
            .orElse(null);
         return this.xnwtgacms2tpk != null;
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return this.xnwtgacms2tpk != null
         && this.xnwtgacms2tpk.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r
         && this.xnwtgacms2tpk.x2s37os1kht7vr()
         && !var2.isSleeping()
         && !var2.hasCustomer();
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x307t38ql367ok = null;
      this.x1hqs3il4h0q4l = var3;
      this.x1zzy1362cikeg = var2.getMainHandStack();
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x307t38ql367ok == null) {
         if (var3 < this.x1hqs3il4h0q4l) {
            return;
         }

         this.x307t38ql367ok = ColonyManager.x2l4eb2ibsf118(var1, this.xnwtgacms2tpk).orElse(null);
         this.x1hqs3il4h0q4l = var3 + 100L;
         if (this.x307t38ql367ok == null) {
            ColonyChatFeedback.x1qd0mutwsr3sc(var2, "нет безопасного места для начального склада");
            return;
         }
      }

      if (ColonyNavigationHelper.xps35ehel6nkk(var2, this.x307t38ql367ok, var3)) {
         this.x307t38ql367ok = null;
      } else {
         var2.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.CHEST));
         if (var2.getBlockPos().getSquaredDistance(this.x307t38ql367ok) > 9.0) {
            ColonyNavigationHelper.x2wzylgizxltef(var1, var2, this.x307t38ql367ok, 0.65);
            ColonyChatFeedback.x1qd0mutwsr3sc(var2, "установка начального склада: " + this.x307t38ql367ok.toShortString());
         } else if (this.x2ws1oqe8gabg8.xpohy1q30uxlk(var1, var2, this.x307t38ql367ok, 20, false)) {
            if (!ColonyManager.xt55z8grtuvpq(var1, this.xnwtgacms2tpk, this.x307t38ql367ok, StorageCategory.x2y2vmpsepzv3)) {
               this.x307t38ql367ok = null;
            } else {
               this.xnwtgacms2tpk.x1z3izo4s1trbu();
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
               ColonyManager.x2cl1crde2rnxy(var1, this.xnwtgacms2tpk, new ItemStack(Items.OAK_LOG, 16));
               ColonyManager.x2cl1crde2rnxy(var1, this.xnwtgacms2tpk, new ItemStack(Items.WHEAT_SEEDS, 16));
               ColonyChatFeedback.x2t98zlq4lu49t(var2, "поставлен начальный двойной склад", 2);
               var2.swingHand(Hand.MAIN_HAND);
               var2.setStackInHand(Hand.MAIN_HAND, this.x1zzy1362cikeg);
               this.x1zzy1362cikeg = ItemStack.EMPTY;
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x2ws1oqe8gabg8.xupxzzyodxtwj(var1, var2);
      ColonyNavigationHelper.x2fxoqmbs0henb(var2);
      this.xnwtgacms2tpk = null;
      this.x307t38ql367ok = null;
      if (var2.getMainHandStack().isOf(Items.CHEST)) {
         var2.setStackInHand(Hand.MAIN_HAND, this.x1zzy1362cikeg);
      }

      this.x1zzy1362cikeg = ItemStack.EMPTY;
   }
}
