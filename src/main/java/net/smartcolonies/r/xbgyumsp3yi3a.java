package net.smartcolonies.r;


import java.util.Objects;
import java.util.List;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;

final class xbgyumsp3yi3a {
   public final ServerWorld x3pwphu5fgb0hr;
   public final long x3tdytvdbh7xq2;
   public final List<PersistentProjectileEntity> x1j7ge6q8c5e6g;

   public xbgyumsp3yi3a(ServerWorld x3pwphu5fgb0hr, long x3tdytvdbh7xq2, List<PersistentProjectileEntity> x1j7ge6q8c5e6g) {
      this.x3pwphu5fgb0hr = x3pwphu5fgb0hr;
      this.x3tdytvdbh7xq2 = x3tdytvdbh7xq2;
      this.x1j7ge6q8c5e6g = x1j7ge6q8c5e6g;
   }

   public ServerWorld x3pwphu5fgb0hr() {
      return this.x3pwphu5fgb0hr;
   }

   public long x3tdytvdbh7xq2() {
      return this.x3tdytvdbh7xq2;
   }

   public List<PersistentProjectileEntity> x1j7ge6q8c5e6g() {
      return this.x1j7ge6q8c5e6g;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof xbgyumsp3yi3a other)) return false;
      return Objects.equals(this.x3pwphu5fgb0hr, other.x3pwphu5fgb0hr) && Objects.equals(this.x3tdytvdbh7xq2, other.x3tdytvdbh7xq2) && Objects.equals(this.x1j7ge6q8c5e6g, other.x1j7ge6q8c5e6g);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3pwphu5fgb0hr, this.x3tdytvdbh7xq2, this.x1j7ge6q8c5e6g);
   }

   @Override
   public String toString() {
      return "xbgyumsp3yi3a[" + "x3pwphu5fgb0hr=" + this.x3pwphu5fgb0hr + ", " + "x3tdytvdbh7xq2=" + this.x3tdytvdbh7xq2 + ", " + "x1j7ge6q8c5e6g=" + this.x1j7ge6q8c5e6g + "]";
   }
}
