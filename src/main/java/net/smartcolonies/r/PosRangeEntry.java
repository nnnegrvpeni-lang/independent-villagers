package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.util.math.BlockPos;

final class PosRangeEntry {
   public final BlockPos xjmya1m07w50b;
   public final BlockPos x2gv3orj2jn99t;
   public final long x2cpmgiveflnwy;

   public PosRangeEntry(BlockPos xjmya1m07w50b, BlockPos x2gv3orj2jn99t, long x2cpmgiveflnwy) {
      this.xjmya1m07w50b = xjmya1m07w50b;
      this.x2gv3orj2jn99t = x2gv3orj2jn99t;
      this.x2cpmgiveflnwy = x2cpmgiveflnwy;
   }

   public BlockPos xjmya1m07w50b() {
      return this.xjmya1m07w50b;
   }

   public BlockPos x2gv3orj2jn99t() {
      return this.x2gv3orj2jn99t;
   }

   public long x2cpmgiveflnwy() {
      return this.x2cpmgiveflnwy;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof PosRangeEntry other)) return false;
      return Objects.equals(this.xjmya1m07w50b, other.xjmya1m07w50b) && Objects.equals(this.x2gv3orj2jn99t, other.x2gv3orj2jn99t) && Objects.equals(this.x2cpmgiveflnwy, other.x2cpmgiveflnwy);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.xjmya1m07w50b, this.x2gv3orj2jn99t, this.x2cpmgiveflnwy);
   }

   @Override
   public String toString() {
      return "PosRangeEntry[" + "xjmya1m07w50b=" + this.xjmya1m07w50b + ", " + "x2gv3orj2jn99t=" + this.x2gv3orj2jn99t + ", " + "x2cpmgiveflnwy=" + this.x2cpmgiveflnwy + "]";
   }
}
