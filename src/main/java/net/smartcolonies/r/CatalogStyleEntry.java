package net.smartcolonies.r;


import java.util.Objects;
import java.util.List;

final class CatalogStyleEntry {
   public final List<String> x21whxpe5mewip;
   public final List<String> x1egj5ug4fom0i;

   public CatalogStyleEntry(List<String> x21whxpe5mewip, List<String> x1egj5ug4fom0i) {
      this.x21whxpe5mewip = x21whxpe5mewip;
      this.x1egj5ug4fom0i = x1egj5ug4fom0i;
   }

   public List<String> x21whxpe5mewip() {
      return this.x21whxpe5mewip;
   }

   public List<String> x1egj5ug4fom0i() {
      return this.x1egj5ug4fom0i;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof CatalogStyleEntry other)) return false;
      return Objects.equals(this.x21whxpe5mewip, other.x21whxpe5mewip) && Objects.equals(this.x1egj5ug4fom0i, other.x1egj5ug4fom0i);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x21whxpe5mewip, this.x1egj5ug4fom0i);
   }

   @Override
   public String toString() {
      return "CatalogStyleEntry[" + "x21whxpe5mewip=" + this.x21whxpe5mewip + ", " + "x1egj5ug4fom0i=" + this.x1egj5ug4fom0i + "]";
   }
}
