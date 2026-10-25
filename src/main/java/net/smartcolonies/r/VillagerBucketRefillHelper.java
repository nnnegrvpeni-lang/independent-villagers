package net.smartcolonies.r;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;

public final class VillagerBucketRefillHelper {
   private VillagerBucketRefillHelper() {
   }

   public static void xqe8twcjwxn55(ServerWorld var0, Colony var1, VillagerEntity var2, ItemStack var3) {
      if (!var3.isEmpty() && !ColonyManager.x2cl1crde2rnxy(var0, var1, var3)) {
         ItemStack var4 = var2.getInventory().addStack(var3.copy());
         var3.setCount(0);
         if (!var4.isEmpty()) {
            var2.dropStack(var0, var4);
         }
      }
   }

   public static void x31tan2efb24l6(ServerWorld var0, Colony var1, VillagerEntity var2, TagKey<Item> var3, Item var4, Item var5) {
      Item var6 = x27jm3a98myhk6(var5);
      Item var7 = x64wuh9keue2y(var5);
      int var8 = x3ip8oxl48f0s7(var2.getMainHandStack(), var4, var5, var6, var7);
      if (var8 < 4) {
         Optional<ItemStack> var9 = ColonyManager.x3lxwdirnn77qc(var0, var1, var6x -> var6x.isIn(var3) && x3ip8oxl48f0s7(var6x, var4, var5, var6, var7) > var8);
         ItemStack var10 = var9.orElse(ItemStack.EMPTY);
         if (var10.isEmpty()) {
            int var11 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS));
            int var12 = var5 == Items.STONE_HOE ? 2 : 3;
            if (var7 != null && var11 >= 1 && ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.DIAMOND)) >= var12) {
               ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.DIAMOND), var12);
               ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 1);
               var10 = new ItemStack(var7);
            } else {
               if (var8 < 3) {
                  x7uj9zeib8utf(var0, var1, var12);
               }

               if (var8 < 3 && var6 != null && var11 >= 1 && ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT)) >= var12) {
                  ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT), var12);
                  ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 1);
                  var10 = new ItemStack(var6);
               }
            }

            if (var10.isEmpty() && var8 < 2 && var11 >= 1 && ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.COBBLESTONE)) >= 3) {
               ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 1);
               ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.COBBLESTONE), 3);
               var10 = new ItemStack(var5);
            } else if (var10.isEmpty() && var8 < 1 && var11 >= 2) {
               ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 2);
               var10 = new ItemStack(var4);
            }
         }

         if (!var10.isEmpty()) {
            ItemStack var13 = var2.getMainHandStack();
            if (!var13.isEmpty() && !ColonyManager.x2cl1crde2rnxy(var0, var1, var13)) {
               ItemStack var14 = var2.getInventory().addStack(var13.copy());
               if (!var14.isEmpty()) {
                  var2.dropStack(var0, var14);
               }
            }

            var2.equipStack(EquipmentSlot.MAINHAND, var10);
         }
      }
   }

   public static boolean x3bl8qz81ha13t(ServerWorld var0, Colony var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < var2.getInventory().size(); var3++) {
         if (var2.getInventory().getStack(var3).isOf(Items.BUCKET) || var2.getInventory().getStack(var3).isOf(Items.WATER_BUCKET)) {
            return true;
         }
      }

      boolean var5 = false;

      for (int var4 = 0; var4 < var2.getInventory().size(); var4++) {
         var5 |= var2.getInventory().getStack(var4).isEmpty();
      }

      if (!var5) {
         return false;
      } else {
         Optional var6 = ColonyManager.x3lxwdirnn77qc(var0, var1, var0x -> var0x.isOf(Items.WATER_BUCKET) || var0x.isOf(Items.BUCKET));
         if (var6.isPresent()) {
            var2.getInventory().addStack((ItemStack)var6.get());
            return true;
         } else {
            x7uj9zeib8utf(var0, var1, 3);
            if (ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT), 3)) {
               var2.getInventory().addStack(new ItemStack(Items.BUCKET));
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public static int x7uj9zeib8utf(ServerWorld var0, Colony var1, int var2) {
      return x2ia8mwg6bu4uh(var0, var1, Items.RAW_IRON, Items.IRON_INGOT, var2);
   }

   public static void x3bzx7lf7atdqb(ServerWorld var0, Colony var1) {
      x2ia8mwg6bu4uh(var0, var1, Items.RAW_IRON, Items.IRON_INGOT, Integer.MAX_VALUE);
      x2ia8mwg6bu4uh(var0, var1, Items.RAW_COPPER, Items.COPPER_INGOT, Integer.MAX_VALUE);
      x2ia8mwg6bu4uh(var0, var1, Items.RAW_GOLD, Items.GOLD_INGOT, Integer.MAX_VALUE);
   }

   private static int x2ia8mwg6bu4uh(ServerWorld var0, Colony var1, Item var2, Item var3, int var4) {
      int var5 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.isOf(var3));
      int var6 = Math.max(0, var4 - var5);
      int var7 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.isOf(var2));
      Predicate<ItemStack> var8 = var0x -> var0x.isOf(Items.COAL) || var0x.isOf(Items.CHARCOAL) || var0x.isIn(ItemTags.LOGS);
      int var9 = ColonyManager.x1mlne8tl8k5ag(var0, var1, var8);
      int var10 = (int)Math.min((long)Math.min(var6, var7), var9 * 8L);
      if (var10 <= 0) {
         return var5;
      } else {
         int var11 = (var10 + 7) / 8;
         boolean var12 = ColonyManager.x38ipozok9mc0x(
            var0,
            var1,
            () -> ColonyManager.x22hjebj4vmztt(var0, var1, var1xx -> var1xx.isOf(var2), var10) && ColonyManager.x22hjebj4vmztt(var0, var1, var8, var11),
            List.of(new ItemStack(var3, var10))
         );
         return var12 ? var5 + var10 : var5;
      }
   }

   private static Item x27jm3a98myhk6(Item var0) {
      return var0 == Items.STONE_PICKAXE ? Items.IRON_PICKAXE : (var0 == Items.STONE_AXE ? Items.IRON_AXE : (var0 == Items.STONE_HOE ? Items.IRON_HOE : null));
   }

   private static Item x64wuh9keue2y(Item var0) {
      return var0 == Items.STONE_PICKAXE
         ? Items.DIAMOND_PICKAXE
         : (var0 == Items.STONE_AXE ? Items.DIAMOND_AXE : (var0 == Items.STONE_HOE ? Items.DIAMOND_HOE : null));
   }

   private static int x3ip8oxl48f0s7(ItemStack var0, Item var1, Item var2, Item var3, Item var4) {
      if ((var4 == null || !var0.isOf(var4)) && !var0.isOf(Items.NETHERITE_PICKAXE) && !var0.isOf(Items.NETHERITE_AXE) && !var0.isOf(Items.NETHERITE_HOE)) {
         if (var3 != null && var0.isOf(var3)) {
            return 3;
         } else if (var0.isOf(var2)) {
            return 2;
         } else {
            return var0.isOf(var1) ? 1 : 0;
         }
      } else {
         return 4;
      }
   }
}
