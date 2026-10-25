package net.smartcolonies.r;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;

public final class x22fpul10e08s {
   public static final List<xzld74pdvhfbu> x3ck5rmycrqg51 = List.of(
      new xzld74pdvhfbu(Items.WOODEN_SWORD, 2, null, 0, 1),
      new xzld74pdvhfbu(Items.STONE_SWORD, 4, Items.COBBLESTONE, 2, 1),
      new xzld74pdvhfbu(Items.IRON_SWORD, 8, Items.IRON_INGOT, 2, 1),
      new xzld74pdvhfbu(Items.DIAMOND_SWORD, 18, Items.DIAMOND, 2, 1),
      new xzld74pdvhfbu(Items.BOW, 6, Items.STRING, 3, 1),
      new xzld74pdvhfbu(Items.SHIELD, 6, Items.IRON_INGOT, 1, 2),
      new xzld74pdvhfbu(Items.IRON_HELMET, 10, Items.IRON_INGOT, 5, 0),
      new xzld74pdvhfbu(Items.IRON_CHESTPLATE, 16, Items.IRON_INGOT, 8, 0),
      new xzld74pdvhfbu(Items.IRON_LEGGINGS, 14, Items.IRON_INGOT, 7, 0),
      new xzld74pdvhfbu(Items.IRON_BOOTS, 8, Items.IRON_INGOT, 4, 0),
      new xzld74pdvhfbu(Items.DIAMOND_HELMET, 24, Items.DIAMOND, 5, 0),
      new xzld74pdvhfbu(Items.DIAMOND_CHESTPLATE, 40, Items.DIAMOND, 8, 0),
      new xzld74pdvhfbu(Items.DIAMOND_LEGGINGS, 34, Items.DIAMOND, 7, 0),
      new xzld74pdvhfbu(Items.DIAMOND_BOOTS, 20, Items.DIAMOND, 4, 0)
   );

   private x22fpul10e08s() {
   }

   public static Predicate<ItemStack> xeccokn9mws72(Item var0) {
      ItemStack var1 = new ItemStack(var0);
      return var1x -> !var1x.isEmpty() && ItemStack.areItemsAndComponentsEqual(var1x, var1);
   }

   public static void x1p0bvs1guedf7(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = var0.getEntitiesByType(
            EntityType.IRON_GOLEM, var1x -> var1x.isAlive() && var1x.getBlockPos().getSquaredDistance(var1.core()) <= var1.radius() * var1.radius()
         )
         .size();
      int var3 = Math.max(36, (4 - var2) * 36);

      for (xzld74pdvhfbu var5 : x3ck5rmycrqg51) {
         int var6 = 2 - x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, xeccokn9mws72(var5.x3dtjj1ybya6jb()));
         if (var6 > 0) {
            Predicate<ItemStack> var7 = var1x -> var1x.isOf(var5.x38p5wnjfccixq());
            int var8 = var5.x38p5wnjfccixq() == Items.IRON_INGOT
               ? var3
               : (var5.x38p5wnjfccixq() == Items.DIAMOND ? 4 : (var5.x38p5wnjfccixq() == Items.COBBLESTONE ? 64 : 2));
            if ((var5.x2wg9tuwow5zee() <= 0 || x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var7) >= var8 + var5.x2wg9tuwow5zee() * var6)
               && (
                  var5.x3nya5dnq7wnel() <= 0
                     || x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS)) >= 48 + var5.x3nya5dnq7wnel() * var6
               )) {
               x2n55y2tom37h2.x38ipozok9mc0x(
                  var0,
                  var1,
                  () -> (var5.x2wg9tuwow5zee() == 0 || x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var7, var5.x2wg9tuwow5zee() * var6))
                     && (
                        var5.x3nya5dnq7wnel() == 0
                           || x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.isIn(ItemTags.LOGS), var5.x3nya5dnq7wnel() * var6)
                     ),
                  List.of(new ItemStack(var5.x3dtjj1ybya6jb(), var6))
               );
            }
         }
      }
   }
}
