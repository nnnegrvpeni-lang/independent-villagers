package net.smartcolonies.r;


import java.util.Objects;
final class Dimension2D {
   public final int x3bqzqsu81ak1i;
   public final int x17l8srdozs7l;

   public Dimension2D(int x3bqzqsu81ak1i, int x17l8srdozs7l) {
      this.x3bqzqsu81ak1i = x3bqzqsu81ak1i;
      this.x17l8srdozs7l = x17l8srdozs7l;
   }

   public int x3bqzqsu81ak1i() {
      return this.x3bqzqsu81ak1i;
   }

   public int x17l8srdozs7l() {
      return this.x17l8srdozs7l;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Dimension2D other)) return false;
      return Objects.equals(this.x3bqzqsu81ak1i, other.x3bqzqsu81ak1i) && Objects.equals(this.x17l8srdozs7l, other.x17l8srdozs7l);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3bqzqsu81ak1i, this.x17l8srdozs7l);
   }

   @Override
   public String toString() {
      return "Dimension2D[" + "x3bqzqsu81ak1i=" + this.x3bqzqsu81ak1i + ", " + "x17l8srdozs7l=" + this.x17l8srdozs7l + "]";
   }
}
