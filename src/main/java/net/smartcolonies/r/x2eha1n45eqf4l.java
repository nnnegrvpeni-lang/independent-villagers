package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public final class x2eha1n45eqf4l {
   public final BlockPos x1oppt2nr5wv2g;
   public final BlockState x1bbgyigo2fq58;
   public final boolean xttubpoe0ch3r;

   public x2eha1n45eqf4l(BlockPos x1oppt2nr5wv2g, BlockState x1bbgyigo2fq58, boolean xttubpoe0ch3r) {
      this.x1oppt2nr5wv2g = x1oppt2nr5wv2g;
      this.x1bbgyigo2fq58 = x1bbgyigo2fq58;
      this.xttubpoe0ch3r = xttubpoe0ch3r;
   }

   public BlockPos x1oppt2nr5wv2g() {
      return this.x1oppt2nr5wv2g;
   }

   public BlockState x1bbgyigo2fq58() {
      return this.x1bbgyigo2fq58;
   }

   public boolean xttubpoe0ch3r() {
      return this.xttubpoe0ch3r;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof x2eha1n45eqf4l other)) return false;
      return Objects.equals(this.x1oppt2nr5wv2g, other.x1oppt2nr5wv2g) && Objects.equals(this.x1bbgyigo2fq58, other.x1bbgyigo2fq58) && Objects.equals(this.xttubpoe0ch3r, other.xttubpoe0ch3r);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x1oppt2nr5wv2g, this.x1bbgyigo2fq58, this.xttubpoe0ch3r);
   }

   @Override
   public String toString() {
      return "x2eha1n45eqf4l[" + "x1oppt2nr5wv2g=" + this.x1oppt2nr5wv2g + ", " + "x1bbgyigo2fq58=" + this.x1bbgyigo2fq58 + ", " + "xttubpoe0ch3r=" + this.xttubpoe0ch3r + "]";
   }
}
