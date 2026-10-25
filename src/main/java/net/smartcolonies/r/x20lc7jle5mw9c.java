package net.smartcolonies.r;

import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public final class x20lc7jle5mw9c {
   private static final Map<VillagerEntity, Long> x2pz5vwhc9swi4 = new WeakHashMap<>();
   private BlockPos xh3z7y4r6eog0;
   private BlockState x3gbvo2clj2uxy;
   private long x5u2w3drumyur;
   private int xw22danc9z9hx;

   public static boolean x1fhuggzt16ek2(VillagerEntity var0, long var1) {
      return x2pz5vwhc9swi4.getOrDefault(var0, Long.MIN_VALUE) >= var1;
   }

   public static int x20ocn113pbk5f(ServerWorld var0, VillagerEntity var1, BlockPos var2) {
      BlockState var3 = var0.getBlockState(var2);
      float var4 = var3.getHardness(var0, var2);
      double var5 = Math.max(1.0F, var1.getMainHandStack().getMiningSpeedMultiplier(var3));
      return Math.clamp((long)((int)Math.ceil(Math.max(0.0F, var4) * 30.0F / var5)), x3sqkg3n6v972r.x2by5f9ehxvwk9().work.minimum_break_ticks, 200);
   }

   public boolean xpohy1q30uxlk(ServerWorld var1, VillagerEntity var2, BlockPos var3, int var4, boolean var5) {
      BlockState var6 = var1.getBlockState(var3);
      if (!var3.equals(this.xh3z7y4r6eog0) || !var6.equals(this.x3gbvo2clj2uxy)) {
         this.xupxzzyodxtwj(var1, var2);
         this.xh3z7y4r6eog0 = var3.toImmutable();
         this.x3gbvo2clj2uxy = var6;
         this.x5u2w3drumyur = var1.getTime();
         this.xw22danc9z9hx = Math.max(1, var4);
      }

      long var7 = var1.getTime() - this.x5u2w3drumyur;
      x2pz5vwhc9swi4.put(var2, var1.getTime() + 20L);
      if (var7 % 6L == 0L) {
         var2.swingHand(Hand.MAIN_HAND);
      }

      if (var5) {
         var1.setBlockBreakingInfo(var2.getId(), this.xh3z7y4r6eog0, (int)Math.min(9L, var7 * 10L / this.xw22danc9z9hx));
      }

      if (var7 < this.xw22danc9z9hx) {
         return false;
      } else {
         this.xupxzzyodxtwj(var1, var2);
         x2pz5vwhc9swi4.put(var2, var1.getTime() + 20L);
         return true;
      }
   }

   public void xupxzzyodxtwj(ServerWorld var1, VillagerEntity var2) {
      x2pz5vwhc9swi4.remove(var2);
      if (this.xh3z7y4r6eog0 != null) {
         var1.setBlockBreakingInfo(var2.getId(), this.xh3z7y4r6eog0, -1);
      }

      this.xh3z7y4r6eog0 = null;
      this.x3gbvo2clj2uxy = null;
   }
}
