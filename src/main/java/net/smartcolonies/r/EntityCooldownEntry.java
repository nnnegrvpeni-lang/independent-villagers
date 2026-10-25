package net.smartcolonies.r;


import java.util.Objects;
import java.util.UUID;

final class EntityCooldownEntry {
   public final UUID x1v3u0njrh3j7a;
   public final long xv5ezs7er1s4p;

   public EntityCooldownEntry(UUID x1v3u0njrh3j7a, long xv5ezs7er1s4p) {
      this.x1v3u0njrh3j7a = x1v3u0njrh3j7a;
      this.xv5ezs7er1s4p = xv5ezs7er1s4p;
   }

   public UUID x1v3u0njrh3j7a() {
      return this.x1v3u0njrh3j7a;
   }

   public long xv5ezs7er1s4p() {
      return this.xv5ezs7er1s4p;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof EntityCooldownEntry other)) return false;
      return Objects.equals(this.x1v3u0njrh3j7a, other.x1v3u0njrh3j7a) && Objects.equals(this.xv5ezs7er1s4p, other.xv5ezs7er1s4p);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x1v3u0njrh3j7a, this.xv5ezs7er1s4p);
   }

   @Override
   public String toString() {
      return "EntityCooldownEntry[" + "x1v3u0njrh3j7a=" + this.x1v3u0njrh3j7a + ", " + "xv5ezs7er1s4p=" + this.xv5ezs7er1s4p + "]";
   }
}
