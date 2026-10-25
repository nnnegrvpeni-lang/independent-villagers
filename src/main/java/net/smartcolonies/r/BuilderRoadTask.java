package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public final class BuilderRoadTask extends MultiTickTask<VillagerEntity> {
   private Colony x24sw4yjvvv3sl;
   private long x3c1e4wbf3gizu;
   private final BlockInteractionTracker x3nsayy5nxyiob = new BlockInteractionTracker();

   public BuilderRoadTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      this.x24sw4yjvvv3sl = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
         .filter(
            var2x -> !var2.isBaby()
               && !var2.isSleeping()
               && !var2.hasCustomer()
               && var2x.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r
               && !var2x.x2s37os1kht7vr()
               && !var2x.x2ad3s8aentmo4().isEmpty()
               && !var2x.x1n1a2vp01kxzf().isEmpty()
               && !ColonyBiomeHelper.xuj80syz8c60l(var1, var2x, var2)
               && !BuildingBlueprintHelper.x81tynoeus5lh(var1, var2x)
         )
         .orElse(null);
      return this.x24sw4yjvvv3sl != null;
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x3c1e4wbf3gizu = var3;
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x24sw4yjvvv3sl != null
         && this.x24sw4yjvvv3sl.roleOf(var2.getUuid()) == ColonyRole.x35csp4lojh59r
         && !this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()
         && !ColonyBiomeHelper.xuj80syz8c60l(var1, this.x24sw4yjvvv3sl, var2)
         && !BuildingBlueprintHelper.x81tynoeus5lh(var1, this.x24sw4yjvvv3sl);
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x3c1e4wbf3gizu) {
         if (!ColonyProtectionUtils.x35ev8l9vc9uk7(var1)) {
            ColonyChatFeedback.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else {
            this.x3c1e4wbf3gizu = var3 + 10L;
            BlockPos var5 = this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().getFirst();
            if (StructurePieceHelper.x3hqfrterusj2u(var1, var5)) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
            } else if (!StructurePieceHelper.xxon284tpck85(var1, this.x24sw4yjvvv3sl, var5)
               || ColonyNavigationHelper.xps35ehel6nkk(var2, var5.up(), var3)
               || this.x24sw4yjvvv3sl.x3nmwt0p1j1m6g() >= 600) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(true);
               ColonyChatFeedback.x1qd0mutwsr3sc(var2, "непроходимый сегмент дороги пропущен: " + var5.toShortString());
               ColonyManager.xchl1k1tnh3w8(var1).markDirty();
            } else if (var2.getBlockPos().getSquaredDistance(var5.up()) > 9.0) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               ColonyNavigationHelper.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var5.getX() + 0.5, var5.getY() + 1, var5.getZ() + 0.5), 0.65);
            } else if (!var1.getBlockState(var5.up()).isAir() && !ColonyProtectionUtils.x231if4rvsvptj(var1, var5.up(), true, var2)) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
            } else if (this.x3nsayy5nxyiob.xpohy1q30uxlk(var1, var2, var5, ColonyConfig.x2by5f9ehxvwk9().work.placement_ticks, false)) {
               if (ColonyProtectionUtils.x1bq0f8n53gtob(var1, var5, Blocks.DIRT_PATH.getDefaultState(), 3)) {
                  this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
                  ColonyChatFeedback.x2t98zlq4lu49t(var2, "дорога", 1);
                  var2.swingHand(Hand.MAIN_HAND);
                  ColonyManager.xchl1k1tnh3w8(var1).markDirty();
               } else {
                  this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               }
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x3nsayy5nxyiob.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      this.x24sw4yjvvv3sl = null;
   }
}
