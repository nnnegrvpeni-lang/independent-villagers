package net.smartcolonies.r;


import java.util.Objects;
import java.util.List;
import net.minecraft.util.math.BlockPos;

final class IrrigationCanal {
   public final List<BlockPos> x1f1dkpug78222;
   public final BlockPos x1ekcqx613mv1d;

   public IrrigationCanal(List<BlockPos> x1f1dkpug78222, BlockPos x1ekcqx613mv1d) {
      this.x1f1dkpug78222 = x1f1dkpug78222;
      this.x1ekcqx613mv1d = x1ekcqx613mv1d;
   }

   public List<BlockPos> x1f1dkpug78222() {
      return this.x1f1dkpug78222;
   }

   public BlockPos x1ekcqx613mv1d() {
      return this.x1ekcqx613mv1d;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof IrrigationCanal other)) return false;
      return Objects.equals(this.x1f1dkpug78222, other.x1f1dkpug78222) && Objects.equals(this.x1ekcqx613mv1d, other.x1ekcqx613mv1d);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x1f1dkpug78222, this.x1ekcqx613mv1d);
   }

   @Override
   public String toString() {
      return "IrrigationCanal[" + "x1f1dkpug78222=" + this.x1f1dkpug78222 + ", " + "x1ekcqx613mv1d=" + this.x1ekcqx613mv1d + "]";
   }
}
