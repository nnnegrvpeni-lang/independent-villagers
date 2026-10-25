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

public final class x3iscxmqtuzq8n extends MultiTickTask<VillagerEntity> {
   private x15kgoaq6i3m6i x24sw4yjvvv3sl;
   private long x3c1e4wbf3gizu;
   private final x20lc7jle5mw9c x3nsayy5nxyiob = new x20lc7jle5mw9c();

   public x3iscxmqtuzq8n() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      this.x24sw4yjvvv3sl = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
         .filter(
            var2x -> !var2.isBaby()
               && !var2.isSleeping()
               && !var2.hasCustomer()
               && var2x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
               && !var2x.x2s37os1kht7vr()
               && !var2x.x2ad3s8aentmo4().isEmpty()
               && !var2x.x1n1a2vp01kxzf().isEmpty()
               && !x27aao31y6r2ta.xuj80syz8c60l(var1, var2x, var2)
               && !x20rghnsl2yowl.x81tynoeus5lh(var1, var2x)
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
         && this.x24sw4yjvvv3sl.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
         && !this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().isEmpty()
         && !x27aao31y6r2ta.xuj80syz8c60l(var1, this.x24sw4yjvvv3sl, var2)
         && !x20rghnsl2yowl.x81tynoeus5lh(var1, this.x24sw4yjvvv3sl);
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x3c1e4wbf3gizu) {
         if (!x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else {
            this.x3c1e4wbf3gizu = var3 + 10L;
            BlockPos var5 = this.x24sw4yjvvv3sl.x1n1a2vp01kxzf().getFirst();
            if (xamz6pfj7qa87.x3hqfrterusj2u(var1, var5)) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            } else if (!xamz6pfj7qa87.xxon284tpck85(var1, this.x24sw4yjvvv3sl, var5)
               || x1c1r5c6a13gtl.xps35ehel6nkk(var2, var5.up(), var3)
               || this.x24sw4yjvvv3sl.x3nmwt0p1j1m6g() >= 600) {
               this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(true);
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "непроходимый сегмент дороги пропущен: " + var5.toShortString());
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            } else if (var2.getBlockPos().getSquaredDistance(var5.up()) > 9.0) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
               x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var5.getX() + 0.5, var5.getY() + 1, var5.getZ() + 0.5), 0.65);
            } else if (!var1.getBlockState(var5.up()).isAir() && !x1dxdlnevtfbhm.x231if4rvsvptj(var1, var5.up(), true, var2)) {
               this.x24sw4yjvvv3sl.x1nocpys4yjo2k(var3);
            } else if (this.x3nsayy5nxyiob.xpohy1q30uxlk(var1, var2, var5, x3sqkg3n6v972r.x2by5f9ehxvwk9().work.placement_ticks, false)) {
               if (x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var5, Blocks.DIRT_PATH.getDefaultState(), 3)) {
                  this.x24sw4yjvvv3sl.x1tvwg66ci1f7k(false);
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "дорога", 1);
                  var2.swingHand(Hand.MAIN_HAND);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
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
