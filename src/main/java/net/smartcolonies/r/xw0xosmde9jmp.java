package net.smartcolonies.r;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class xw0xosmde9jmp {
   public static final int x2289wrswms5uz = 4;

   private xw0xosmde9jmp() {
   }

   private static boolean x1o3p2gzsi5ixs(ServerWorld var0, BlockPos var1) {
      return var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4) && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var1);
   }

   public static int x31lz6msk67uxc(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;

      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x1o3p2gzsi5ixs(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var5) {
            for (int var7 = 0; var7 < var5.size(); var7++) {
               if (var5.getStack(var7).isOf(Items.MILK_BUCKET)) {
                  var2 += var5.getStack(var7).getCount();
               }
            }
         }
      }

      return var2;
   }

   public static int x2m95xmyunf3vb(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = x31lz6msk67uxc(var0, var1);
      if (var2 >= 4) {
         return var2;
      } else {
         for (VillagerEntity var4 : var0.getEntitiesByType(EntityType.VILLAGER, x2n55y2tom37h2.x150ccxomrlozr(var0, var1), Entity::isAlive)) {
            if (x2n55y2tom37h2.x3psqezd5jem54(var0, var4.getBlockPos()).orElse(null) == var1) {
               for (int var5 = 0; var5 < var4.getInventory().size(); var5++) {
                  ItemStack var6 = var4.getInventory().getStack(var5);
                  if (var6.isOf(Items.MILK_BUCKET)) {
                     var2 += var6.getCount();
                  }
               }

               if (var2 >= 4) {
                  return var2;
               }
            }
         }

         return var2;
      }
   }

   public static int x2m8lyhoez9v7c(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = 4;
      int var3 = 0;
      Set var4 = Collections.newSetFromMap(new IdentityHashMap());

      for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
         if (x1o3p2gzsi5ixs(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var7 && var4.add(var7)) {
            var3 += x3tihjtlnbfyrs(var7, var2);

            for (int var9 = 0; var9 < var7.size(); var9++) {
               if (var7.getStack(var9).isOf(Items.MILK_BUCKET)) {
                  var2 = Math.max(0, var2 - var7.getStack(var9).getCount());
               }
            }
         }
      }

      return var3;
   }

   public static int x3tihjtlnbfyrs(Inventory var0, int var1) {
      int var2 = Math.max(0, var1);
      int var3 = 0;
      ItemStack var4 = new ItemStack(Items.BUCKET);

      for (int var5 = 0; var5 < var0.size(); var5++) {
         ItemStack var6 = var0.getStack(var5);
         if (var6.isOf(Items.MILK_BUCKET)) {
            int var7 = Math.min(var2, var6.getCount());
            var2 -= var7;
            int var8 = var6.getCount() - var7;
            if (var8 != 0) {
               int var9 = 0;

               for (int var10 = 0; var10 < var0.size(); var10++) {
                  ItemStack var11 = var0.getStack(var10);
                  if (var11.isEmpty()) {
                     var9 += var0.getMaxCount(var4);
                  } else if (ItemStack.areItemsAndComponentsEqual(var11, var4)) {
                     var9 += Math.max(0, var0.getMaxCount(var4) - var11.getCount());
                  }
               }

               if (var7 == 0 && var9 + var0.getMaxCount(var4) >= var8) {
                  var9 += var0.getMaxCount(var4);
               }

               int var15 = Math.min(var8, var9);
               if (var15 != 0) {
                  var6.decrement(var15);
                  if (var6.isEmpty()) {
                     var0.setStack(var5, ItemStack.EMPTY);
                  }

                  ItemStack var16 = new ItemStack(Items.BUCKET, var15);

                  for (int var12 = 0; var12 < var0.size() && !var16.isEmpty(); var12++) {
                     ItemStack var13 = var0.getStack(var12);
                     if (ItemStack.areItemsAndComponentsEqual(var13, var16)) {
                        int var14 = Math.min(var16.getCount(), Math.max(0, var0.getMaxCount(var16) - var13.getCount()));
                        var13.increment(var14);
                        var16.decrement(var14);
                     }
                  }

                  for (int var17 = 0; var17 < var0.size() && !var16.isEmpty(); var17++) {
                     if (var0.getStack(var17).isEmpty()) {
                        int var18 = Math.min(var16.getCount(), var0.getMaxCount(var16));
                        var0.setStack(var17, var16.copyWithCount(var18));
                        var16.decrement(var18);
                     }
                  }

                  var3 += var15;
                  var0.markDirty();
               }
            }
         }
      }

      return var3;
   }
}
