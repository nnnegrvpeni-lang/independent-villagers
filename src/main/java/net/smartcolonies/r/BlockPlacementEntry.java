package net.smartcolonies.r;


import java.util.Objects;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public final class BlockPlacementEntry {
   public final BlockPos x31k0w8t7ul189;
   public final BlockState x2hmkda3gwcacx;
   public final boolean x3ln30gt147gjn;
   public final boolean x1jjn414zs82u2;

   public BlockPlacementEntry(BlockPos x31k0w8t7ul189, BlockState x2hmkda3gwcacx, boolean x3ln30gt147gjn, boolean x1jjn414zs82u2) {
      this.x31k0w8t7ul189 = x31k0w8t7ul189;
      this.x2hmkda3gwcacx = x2hmkda3gwcacx;
      this.x3ln30gt147gjn = x3ln30gt147gjn;
      this.x1jjn414zs82u2 = x1jjn414zs82u2;
   }

   public BlockPos x31k0w8t7ul189() {
      return this.x31k0w8t7ul189;
   }

   public BlockState x2hmkda3gwcacx() {
      return this.x2hmkda3gwcacx;
   }

   public boolean x3ln30gt147gjn() {
      return this.x3ln30gt147gjn;
   }

   public boolean x1jjn414zs82u2() {
      return this.x1jjn414zs82u2;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof BlockPlacementEntry other)) return false;
      return Objects.equals(this.x31k0w8t7ul189, other.x31k0w8t7ul189) && Objects.equals(this.x2hmkda3gwcacx, other.x2hmkda3gwcacx) && Objects.equals(this.x3ln30gt147gjn, other.x3ln30gt147gjn) && Objects.equals(this.x1jjn414zs82u2, other.x1jjn414zs82u2);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x31k0w8t7ul189, this.x2hmkda3gwcacx, this.x3ln30gt147gjn, this.x1jjn414zs82u2);
   }

   @Override
   public String toString() {
      return "BlockPlacementEntry[" + "x31k0w8t7ul189=" + this.x31k0w8t7ul189 + ", " + "x2hmkda3gwcacx=" + this.x2hmkda3gwcacx + ", " + "x3ln30gt147gjn=" + this.x3ln30gt147gjn + ", " + "x1jjn414zs82u2=" + this.x1jjn414zs82u2 + "]";
   }
}
