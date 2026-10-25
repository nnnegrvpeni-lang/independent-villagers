package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

final class TargetPosTimestamp {
   public final BlockPos x3hblxrvsbx0pw;
   public final Vec3d x1zfnlcsuk3t1i;
   public final long x2sysqaosnmrak;
   public final long x38qwv32hf8w5w;
   public final long x1urgnoe13hqro;
   public final long x16yow35qysf17;
   public final int x3d25jiqyb27mb;
   public final int x26xkfgjum6mf6;
   public final double xwwyae897rr8r;

   public TargetPosTimestamp(BlockPos x3hblxrvsbx0pw, Vec3d x1zfnlcsuk3t1i, long x2sysqaosnmrak, long x38qwv32hf8w5w, long x1urgnoe13hqro, long x16yow35qysf17, int x3d25jiqyb27mb, int x26xkfgjum6mf6, double xwwyae897rr8r) {
      this.x3hblxrvsbx0pw = x3hblxrvsbx0pw;
      this.x1zfnlcsuk3t1i = x1zfnlcsuk3t1i;
      this.x2sysqaosnmrak = x2sysqaosnmrak;
      this.x38qwv32hf8w5w = x38qwv32hf8w5w;
      this.x1urgnoe13hqro = x1urgnoe13hqro;
      this.x16yow35qysf17 = x16yow35qysf17;
      this.x3d25jiqyb27mb = x3d25jiqyb27mb;
      this.x26xkfgjum6mf6 = x26xkfgjum6mf6;
      this.xwwyae897rr8r = xwwyae897rr8r;
   }

   public BlockPos x3hblxrvsbx0pw() {
      return this.x3hblxrvsbx0pw;
   }

   public Vec3d x1zfnlcsuk3t1i() {
      return this.x1zfnlcsuk3t1i;
   }

   public long x2sysqaosnmrak() {
      return this.x2sysqaosnmrak;
   }

   public long x38qwv32hf8w5w() {
      return this.x38qwv32hf8w5w;
   }

   public long x1urgnoe13hqro() {
      return this.x1urgnoe13hqro;
   }

   public long x16yow35qysf17() {
      return this.x16yow35qysf17;
   }

   public int x3d25jiqyb27mb() {
      return this.x3d25jiqyb27mb;
   }

   public int x26xkfgjum6mf6() {
      return this.x26xkfgjum6mf6;
   }

   public double xwwyae897rr8r() {
      return this.xwwyae897rr8r;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TargetPosTimestamp other)) return false;
      return Objects.equals(this.x3hblxrvsbx0pw, other.x3hblxrvsbx0pw) && Objects.equals(this.x1zfnlcsuk3t1i, other.x1zfnlcsuk3t1i) && Objects.equals(this.x2sysqaosnmrak, other.x2sysqaosnmrak) && Objects.equals(this.x38qwv32hf8w5w, other.x38qwv32hf8w5w) && Objects.equals(this.x1urgnoe13hqro, other.x1urgnoe13hqro) && Objects.equals(this.x16yow35qysf17, other.x16yow35qysf17) && Objects.equals(this.x3d25jiqyb27mb, other.x3d25jiqyb27mb) && Objects.equals(this.x26xkfgjum6mf6, other.x26xkfgjum6mf6) && Objects.equals(this.xwwyae897rr8r, other.xwwyae897rr8r);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3hblxrvsbx0pw, this.x1zfnlcsuk3t1i, this.x2sysqaosnmrak, this.x38qwv32hf8w5w, this.x1urgnoe13hqro, this.x16yow35qysf17, this.x3d25jiqyb27mb, this.x26xkfgjum6mf6, this.xwwyae897rr8r);
   }

   @Override
   public String toString() {
      return "TargetPosTimestamp[" + "x3hblxrvsbx0pw=" + this.x3hblxrvsbx0pw + ", " + "x1zfnlcsuk3t1i=" + this.x1zfnlcsuk3t1i + ", " + "x2sysqaosnmrak=" + this.x2sysqaosnmrak + ", " + "x38qwv32hf8w5w=" + this.x38qwv32hf8w5w + ", " + "x1urgnoe13hqro=" + this.x1urgnoe13hqro + ", " + "x16yow35qysf17=" + this.x16yow35qysf17 + ", " + "x3d25jiqyb27mb=" + this.x3d25jiqyb27mb + ", " + "x26xkfgjum6mf6=" + this.x26xkfgjum6mf6 + ", " + "xwwyae897rr8r=" + this.xwwyae897rr8r + "]";
   }
}
