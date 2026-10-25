package net.smartcolonies.r;

import net.minecraft.item.ItemStack;

public final class ItemWrapper {
   private final ItemStack x24856ou7tviys;

   public ItemWrapper(ItemStack var1) {
      this.x24856ou7tviys = var1.copyWithCount(1);
   }

   @Override
   public int hashCode() {
      return ItemStack.hashCode(this.x24856ou7tviys);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof ItemWrapper var2 && ItemStack.areItemsAndComponentsEqual(this.x24856ou7tviys, var2.x24856ou7tviys);
   }
}
