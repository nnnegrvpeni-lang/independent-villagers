package net.smartcolonies.r;


import java.util.Objects;
import java.util.UUID;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public final class x3o14q3v4oy99m {
   public final UUID xetxwd9l2gcx6;
   public final UUID x3mnabq6qj85f8;
   public final BlockPos x2kystgxl68xrn;
   public final int x3rgd0i6cz19eu;

   public UUID xetxwd9l2gcx6() {
      return this.xetxwd9l2gcx6;
   }

   public UUID x3mnabq6qj85f8() {
      return this.x3mnabq6qj85f8;
   }

   public BlockPos x2kystgxl68xrn() {
      return this.x2kystgxl68xrn;
   }

   public int x3rgd0i6cz19eu() {
      return this.x3rgd0i6cz19eu;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof x3o14q3v4oy99m other)) return false;
      return Objects.equals(this.xetxwd9l2gcx6, other.xetxwd9l2gcx6) && Objects.equals(this.x3mnabq6qj85f8, other.x3mnabq6qj85f8) && Objects.equals(this.x2kystgxl68xrn, other.x2kystgxl68xrn) && Objects.equals(this.x3rgd0i6cz19eu, other.x3rgd0i6cz19eu);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.xetxwd9l2gcx6, this.x3mnabq6qj85f8, this.x2kystgxl68xrn, this.x3rgd0i6cz19eu);
   }

   @Override
   public String toString() {
      return "x3o14q3v4oy99m[" + "xetxwd9l2gcx6=" + this.xetxwd9l2gcx6 + ", " + "x3mnabq6qj85f8=" + this.x3mnabq6qj85f8 + ", " + "x2kystgxl68xrn=" + this.x2kystgxl68xrn + ", " + "x3rgd0i6cz19eu=" + this.x3rgd0i6cz19eu + "]";
   }

public x3o14q3v4oy99m(UUID xetxwd9l2gcx6, UUID x3mnabq6qj85f8, BlockPos x2kystgxl68xrn, int x3rgd0i6cz19eu) {
      x2kystgxl68xrn = x2kystgxl68xrn.toImmutable();
      x3rgd0i6cz19eu = Math.clamp((long)x3rgd0i6cz19eu, 1, 32);
      this.xetxwd9l2gcx6 = xetxwd9l2gcx6;
      this.x3mnabq6qj85f8 = x3mnabq6qj85f8;
      this.x2kystgxl68xrn = x2kystgxl68xrn;
      this.x3rgd0i6cz19eu = x3rgd0i6cz19eu;
   }

   public boolean contains(BlockPos var1) {
      return Math.abs((long)var1.getX() - this.x2kystgxl68xrn.getX()) <= this.x3rgd0i6cz19eu
         && Math.abs((long)var1.getZ() - this.x2kystgxl68xrn.getZ()) <= this.x3rgd0i6cz19eu;
   }

   public boolean intersects(BlockBox var1) {
      return var1.getMaxX() >= this.x2kystgxl68xrn.getX() - this.x3rgd0i6cz19eu
         && var1.getMinX() <= this.x2kystgxl68xrn.getX() + this.x3rgd0i6cz19eu
         && var1.getMaxZ() >= this.x2kystgxl68xrn.getZ() - this.x3rgd0i6cz19eu
         && var1.getMinZ() <= this.x2kystgxl68xrn.getZ() + this.x3rgd0i6cz19eu;
   }

   public NbtCompound toNbt() {
      NbtCompound var1 = new NbtCompound();
      var1.putString("owner", this.xetxwd9l2gcx6.toString());
      var1.putString("colony", this.x3mnabq6qj85f8.toString());
      var1.putLong("center", this.x2kystgxl68xrn.asLong());
      var1.putInt("radius", this.x3rgd0i6cz19eu);
      return var1;
   }

   public static x3o14q3v4oy99m fromNbt(NbtCompound var0) {
      return new x3o14q3v4oy99m(
         UUID.fromString(var0.getString("owner", "")),
         UUID.fromString(var0.getString("colony", "")),
         BlockPos.fromLong(var0.getLong("center", 0L)),
         var0.getInt("radius", 8)
      );
   }
}
