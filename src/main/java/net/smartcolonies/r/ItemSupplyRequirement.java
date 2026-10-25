package net.smartcolonies.r;


import java.util.Objects;
import java.util.function.Predicate;
import net.minecraft.item.ItemStack;

final class ItemSupplyRequirement {
   public final Predicate<ItemStack> x3r2nd9ja5plho;
   public final int x1w6emwv8ukb2;

   public ItemSupplyRequirement(Predicate<ItemStack> x3r2nd9ja5plho, int x1w6emwv8ukb2) {
      this.x3r2nd9ja5plho = x3r2nd9ja5plho;
      this.x1w6emwv8ukb2 = x1w6emwv8ukb2;
   }

   public Predicate<ItemStack> x3r2nd9ja5plho() {
      return this.x3r2nd9ja5plho;
   }

   public int x1w6emwv8ukb2() {
      return this.x1w6emwv8ukb2;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ItemSupplyRequirement other)) return false;
      return Objects.equals(this.x3r2nd9ja5plho, other.x3r2nd9ja5plho) && Objects.equals(this.x1w6emwv8ukb2, other.x1w6emwv8ukb2);
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.x3r2nd9ja5plho, this.x1w6emwv8ukb2);
   }

   @Override
   public String toString() {
      return "ItemSupplyRequirement[" + "x3r2nd9ja5plho=" + this.x3r2nd9ja5plho + ", " + "x1w6emwv8ukb2=" + this.x1w6emwv8ukb2 + "]";
   }
}
