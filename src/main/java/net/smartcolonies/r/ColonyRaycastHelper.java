package net.smartcolonies.r;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public final class ColonyRaycastHelper {
   public static Map<ItemWrapper, Integer> x2l7fon36lgwu2(Inventory var0) {
      Map<ItemWrapper, Integer> var1 = new HashMap<>();

      for (int var2 = 0; var2 < var0.size(); var2++) {
         ItemStack var3 = var0.getStack(var2);
         if (!var3.isEmpty()) {
            var1.merge(new ItemWrapper(var3), var3.getCount(), Integer::sum);
         }
      }

      return var1;
   }

   public static int x2qxayij05nw0s(Map<ItemWrapper, Integer> var0, Map<ItemWrapper, Integer> var1) {
      int var2 = 0;

      for (Entry var4 : var0.entrySet()) {
         var2 += Math.max(0, (Integer)var4.getValue() - var1.getOrDefault(var4.getKey(), 0));
      }

      return var2;
   }

   private ColonyRaycastHelper() {
   }

   public static boolean x33zofxkqevoo8(ServerWorld var0, BlockPos var1) {
      return ColonyTimeService.xic1z39wiy3pn(var0, var1)
         ? false
         : ColonyManager.xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var1));
   }

   public static boolean xu27zvtjfruut(ServerWorld var0, Colony var1, Inventory var2) {
      for (BlockPos var4 : var1.x29j0o9zdxri2().keySet()) {
         if (!ColonyTimeService.xic1z39wiy3pn(var0, var4)
            && var0.getBlockEntity(var4) instanceof Inventory var5
            && (var2 == var5 || var2 instanceof DoubleInventory var7 && var7.isPart(var5))) {
            return true;
         }
      }

      return false;
   }

   public static Map<Identifier, Integer> x2yl261ceqau5h(ServerWorld var0, Colony var1) {
      Map<Identifier, Integer> var2 = new HashMap<>();

      for (BlockPos var4 : var1.x29j0o9zdxri2().keySet()) {
         if (!ColonyTimeService.xic1z39wiy3pn(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var5) {
            for (int var8 = 0; var8 < var5.size(); var8++) {
               ItemStack var7 = var5.getStack(var8);
               if (!var7.isEmpty()) {
                  var2.merge(Registries.ITEM.getId(var7.getItem()), var7.getCount(), Integer::sum);
               }
            }
         }
      }

      return var2;
   }

   public static int xhz2g56hwio6r(Map<Identifier, Integer> var0, Map<Identifier, Integer> var1) {
      int var2 = 0;

      for (Entry var4 : var0.entrySet()) {
         var2 += Math.max(0, (Integer)var4.getValue() - var1.getOrDefault(var4.getKey(), 0));
      }

      return var2;
   }
}
