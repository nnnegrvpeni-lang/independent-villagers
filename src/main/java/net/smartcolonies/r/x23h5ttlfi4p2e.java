package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.util.math.BlockPos;

final class x23h5ttlfi4p2e {
   public final BlockPos x191jg6ukc9dt8;
   public final int x2xm2fy4xaxww7;
   public final double x3bn75ty1xrpx3;

   public x23h5ttlfi4p2e(BlockPos x191jg6ukc9dt8, int x2xm2fy4xaxww7, double x3bn75ty1xrpx3) {
      this.x191jg6ukc9dt8 = x191jg6ukc9dt8;
      this.x2xm2fy4xaxww7 = x2xm2fy4xaxww7;
      this.x3bn75ty1xrpx3 = x3bn75ty1xrpx3;
   }

   public BlockPos x191jg6ukc9dt8() {
      return this.x191jg6ukc9dt8;
   }

   public int x2xm2fy4xaxww7() {
      return this.x2xm2fy4xaxww7;
   }

   public double x3bn75ty1xrpx3() {
      return this.x3bn75ty1xrpx3;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof x23h5ttlfi4p2e other)) return false;
      return Objects.equals(this.x191jg6ukc9dt8, other.x191jg6ukc9dt8) && Objects.equals(this.x2xm2fy4xaxww7, other.x2xm2fy4xaxww7) && Objects.equals(this.x3bn75ty1xrpx3, other.x3bn75ty1xrpx3);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x191jg6ukc9dt8, this.x2xm2fy4xaxww7, this.x3bn75ty1xrpx3);
   }

   @Override
   public String toString() {
      return "x23h5ttlfi4p2e[" + "x191jg6ukc9dt8=" + this.x191jg6ukc9dt8 + ", " + "x2xm2fy4xaxww7=" + this.x2xm2fy4xaxww7 + ", " + "x3bn75ty1xrpx3=" + this.x3bn75ty1xrpx3 + "]";
   }
}
