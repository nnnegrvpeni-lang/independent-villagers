package net.smartcolonies.r;


import java.util.Objects;
import java.util.Optional;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;

final class TargetEntityEntry {
   public final ServerWorld x137xpxqz8kwz;
   public final long xfdtg8nu348d5;
   public final Optional<LivingEntity> x3d4l1o8shgghr;

   public TargetEntityEntry(ServerWorld x137xpxqz8kwz, long xfdtg8nu348d5, Optional<LivingEntity> x3d4l1o8shgghr) {
      this.x137xpxqz8kwz = x137xpxqz8kwz;
      this.xfdtg8nu348d5 = xfdtg8nu348d5;
      this.x3d4l1o8shgghr = x3d4l1o8shgghr;
   }

   public ServerWorld x137xpxqz8kwz() {
      return this.x137xpxqz8kwz;
   }

   public long xfdtg8nu348d5() {
      return this.xfdtg8nu348d5;
   }

   public Optional<LivingEntity> x3d4l1o8shgghr() {
      return this.x3d4l1o8shgghr;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TargetEntityEntry other)) return false;
      return Objects.equals(this.x137xpxqz8kwz, other.x137xpxqz8kwz) && Objects.equals(this.xfdtg8nu348d5, other.xfdtg8nu348d5) && Objects.equals(this.x3d4l1o8shgghr, other.x3d4l1o8shgghr);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x137xpxqz8kwz, this.xfdtg8nu348d5, this.x3d4l1o8shgghr);
   }

   @Override
   public String toString() {
      return "TargetEntityEntry[" + "x137xpxqz8kwz=" + this.x137xpxqz8kwz + ", " + "xfdtg8nu348d5=" + this.xfdtg8nu348d5 + ", " + "x3d4l1o8shgghr=" + this.x3d4l1o8shgghr + "]";
   }
}
