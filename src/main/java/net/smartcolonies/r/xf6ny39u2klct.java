package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.mixin.x3u93owcuyb5d4;

public final class xf6ny39u2klct {
   private xf6ny39u2klct() {
   }

   public static void x3cumye59iar40(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      x1rxiy9mtu5b3h var3 = var1.roleOf(var2.getUuid());
      if (var3 != x1rxiy9mtu5b3h.x3r8p2tvihwpph) {
         for (EquipmentSlot var7 : new EquipmentSlot[]{
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.OFFHAND, EquipmentSlot.MAINHAND
         }) {
            ItemStack var8 = var2.getEquippedStack(var7);
            if (!var8.isEmpty()
               && (var7 != EquipmentSlot.MAINHAND || x26qf74mhgffbi(var8) || var8.isIn(ItemTags.AXES) && var3 != x1rxiy9mtu5b3h.x2hwljbtybtlwc)) {
               ItemStack var9 = var8.copy();
               var2.stopUsingItem();
               var2.equipStack(var7, ItemStack.EMPTY);
               x27tk113eckvag(var0, var1, var2, var9);
            }
         }

         BlockPos var10 = x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.getBlockPos()).orElse(null);
         if (var10 != null && var2.getBlockPos().getSquaredDistance(var10) <= 9.0) {
            for (int var11 = 0; var11 < var2.getInventory().size(); var11++) {
               ItemStack var12 = var2.getInventory().getStack(var11);
               if (x26qf74mhgffbi(var12) || var12.isIn(ItemTags.AXES) && var3 != x1rxiy9mtu5b3h.x2hwljbtybtlwc) {
                  x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var12);
               }
            }
         }
      }
   }

   private static void x27tk113eckvag(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, ItemStack var3) {
      BlockPos var4 = x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.getBlockPos()).orElse(null);
      if (var4 != null && var2.getBlockPos().getSquaredDistance(var4) <= 9.0) {
         x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, var3);
      }

      if (!var3.isEmpty()) {
         ItemStack var5 = var2.getInventory().addStack(var3);
         if (!var5.isEmpty()) {
            ItemEntity var6 = var2.dropStack(var0, var5);
            if (var6 != null) {
               var6.setToDefaultPickupDelay();
            }
         }
      }
   }

   public static boolean x26qf74mhgffbi(ItemStack var0) {
      return var0.isIn(ItemTags.SWORDS)
         || var0.isOf(Items.BOW)
         || var0.isOf(Items.CROSSBOW)
         || var0.isOf(Items.SHIELD)
         || var0.isOf(Items.ARROW)
         || var0.contains(DataComponentTypes.EQUIPPABLE);
   }

   public static boolean x23ik6olfsfgwk(ItemStack var0, x1rxiy9mtu5b3h var1) {
      if (var0.isEmpty()) {
         return false;
      } else {
         String var2 = Registries.ITEM.getId(var0.getItem()).getPath();
         return var0.contains(DataComponentTypes.FOOD)
            || var0.isIn(ItemTags.LOGS)
            || var0.isIn(ItemTags.PLANKS)
            || var0.isIn(ItemTags.STONE_CRAFTING_MATERIALS)
            || var0.isIn(ItemTags.SAPLINGS)
            || var0.isOf(Items.EMERALD)
            || var0.isOf(Items.DIAMOND)
            || var0.isOf(Items.COAL)
            || var0.isOf(Items.CHARCOAL)
            || var2.startsWith("raw_")
            || var2.endsWith("_ingot")
            || var2.endsWith("_nugget")
            || var0.isOf(Items.REDSTONE)
            || var0.isOf(Items.LAPIS_LAZULI)
            || var0.isOf(Items.QUARTZ)
            || var0.isOf(Items.STRING)
            || var0.isOf(Items.LEATHER)
            || var0.isOf(Items.WHEAT)
            || var0.isOf(Items.BONE_MEAL)
            || var0.isOf(Items.PUMPKIN)
            || var0.isOf(Items.CARVED_PUMPKIN)
            || var0.isOf(Items.HAY_BLOCK)
            || var0.isOf(Items.IRON_BLOCK)
            || var0.isOf(Items.GOLD_BLOCK)
            || var0.isOf(Items.DIAMOND_BLOCK)
            || var1 == x1rxiy9mtu5b3h.x35csp4lojh59r && (var0.isOf(Items.STONE) || var0.isOf(Items.STONE_BRICKS) || var0.isOf(Items.GLASS))
            || var0.isOf(Items.TORCH)
            || var0.isIn(ItemTags.WOOL)
            || x26qf74mhgffbi(var0)
            || var1 == x1rxiy9mtu5b3h.x8jydaiei7iry && var2.endsWith("_seeds")
            || var1 == x1rxiy9mtu5b3h.xeoehg87qwfrn
               && (var0.isOf(Items.EGG) || var0.isOf(Items.MILK_BUCKET) || var0.isOf(Items.BUCKET) || var0.isOf(Items.SHEARS))
            || var1 == x1rxiy9mtu5b3h.x18g4mqx9kxs89 && var0.isIn(ItemTags.PICKAXES)
            || var1 == x1rxiy9mtu5b3h.x2hwljbtybtlwc && var0.isIn(ItemTags.AXES)
            || var1 == x1rxiy9mtu5b3h.x8jydaiei7iry && var0.isIn(ItemTags.HOES)
            || var1 == x1rxiy9mtu5b3h.xhds9crfjdp9i && var0.isOf(Items.FISHING_ROD);
      }
   }

   public static void x3nc609h8fgb5y(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      if (Math.floorMod(var2.age + var2.getId(), 5) == 0) {
         boolean var3 = Math.floorMod(var2.age + var2.getId(), 20) == 0;
         if (var3) {
            x3cumye59iar40(var0, var1, var2);
         }

         if (!var2.isSleeping() && !var2.hasCustomer() && !var2.isBaby() && !x36u1fhs2z6uoi.active(var0, var1)) {
            x1rxiy9mtu5b3h var4 = var1.roleOf(var2.getUuid());
            if (var4 != x1rxiy9mtu5b3h.x3r8p2tvihwpph || !var2.getBrain().hasMemoryModule(xxao083dbo7ag.x14b5znl8ndblc)) {
               BlockPos var5 = var3 ? x2n55y2tom37h2.x1falh21nhy4yk(var0, var1, var2.getBlockPos()).orElse(null) : null;
               boolean var6 = var3 && var2.getBrain().getRunningTasks().stream().anyMatch(var0x -> var0x.getClass().getName().startsWith("net.smartcolonies."));
               if (var3 && !var6 && var5 != null && var2.getBlockPos().getSquaredDistance(var5) <= 9.0) {
                  for (int var7 = 0; var7 < var2.getInventory().size(); var7++) {
                     ItemStack var8 = var2.getInventory().getStack(var7);
                     if (!var8.isEmpty()
                        && !var8.contains(DataComponentTypes.FOOD)
                        && !var8.isOf(Items.TORCH)
                        && !var8.isIn(ItemTags.PICKAXES)
                        && !var8.isIn(ItemTags.AXES)
                        && !var8.isIn(ItemTags.HOES)
                        && !var8.isOf(Items.FISHING_ROD)
                        && (var4 != x1rxiy9mtu5b3h.x3r8p2tvihwpph || !x26qf74mhgffbi(var8))
                        && (
                           var4 != x1rxiy9mtu5b3h.xeoehg87qwfrn
                              || !var8.isOf(Items.SHEARS)
                                 && !var8.isOf(Items.BUCKET)
                                 && !var8.isOf(Items.WHEAT)
                                 && !var8.isOf(Items.WHEAT_SEEDS)
                                 && !var8.isOf(Items.CARROT)
                        )
                        && (
                           var4 != x1rxiy9mtu5b3h.x8jydaiei7iry
                              || !var8.isOf(Items.WHEAT) && !Registries.ITEM.getId(var8.getItem()).getPath().endsWith("_seeds")
                        )) {
                        x2vaj7rsm57i4n.x25cotfj45r525(var0, var1, var2, var8);
                     }
                  }
               }

               for (ItemEntity var11 : var0.getEntitiesByClass(
                  ItemEntity.class, var2.getBoundingBox().expand(1.8, 0.6, 1.8), var0x -> !var0x.isRemoved() && !var0x.cannotPickup()
               )) {
                  x105bgml33wsy(var0, var1, var2, var11, var4, Integer.MAX_VALUE);
               }

               boolean var10 = Math.floorMod(var0.getTimeOfDay(), 24000) >= 12000
                  || var2.getBrain().getFirstPossibleNonCoreActivity().filter(Activity.REST::equals).isPresent();
               if (var3 && !var6 && !var10 && var4 != x1rxiy9mtu5b3h.x2xja04fpuesoh) {
                  List<ItemEntity> var12 = var0.getEntitiesByClass(
                     ItemEntity.class,
                     var2.getBoundingBox().expand(8.0, 2.0, 8.0),
                     var4x -> x2t0sm1vfzey0p(var0, var1, var2, var4x, var4)
                        && var4x.squaredDistanceTo(var2) <= 64.0
                        && x17qx2f4ijtfsn(var2, var4x.getStack())
                        && !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var4x.getBlockPos(), var0.getTime())
                  );
                  var12.stream().min(Comparator.comparingDouble(var2::squaredDistanceTo)).ifPresent(var2x -> {
                     x1c1r5c6a13gtl.x1cssc4al5kdgu(var0, var2, var2x.getBlockPos(), 0.8);
                     x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "идёт подобрать: " + Registries.ITEM.getId(var2x.getStack().getItem()).getPath());
                  });
               }
            }
         }
      }
   }

   static int x105bgml33wsy(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, ItemEntity var3, x1rxiy9mtu5b3h var4, int var5) {
      if (!x2t0sm1vfzey0p(var0, var1, var2, var3, var4)) {
         return 0;
      } else {
         ItemStack var6 = var3.getStack();
         int var7 = Math.min(var5, var6.getCount());
         if (var6.isOf(Items.EGG)) {
            var7 = Math.min(var7, Math.max(0, 16 - x3nf466jvpoooi(var2, Items.EGG)));
         }

         if (var6.isOf(Items.MILK_BUCKET)) {
            var7 = Math.min(var7, Math.max(0, 4 - xw0xosmde9jmp.x2m95xmyunf3vb(var0, var1)));
         }

         if (var7 <= 0) {
            return 0;
         } else {
            ItemStack var8 = var6.copy();
            var8.setCount(var7);
            ItemStack var9 = var2.getInventory().addStack(var8);
            int var10 = var7 - var9.getCount();
            if (var10 <= 0) {
               return 0;
            } else {
               var6.decrement(var10);
               var2.sendPickup(var3, var10);
               if (var6.isEmpty()) {
                  var3.discard();
               } else {
                  var3.setStack(var6);
               }

               x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "подбор полезных предметов", var10);
               return var10;
            }
         }
      }
   }

   static boolean x2t0sm1vfzey0p(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, ItemEntity var3, x1rxiy9mtu5b3h var4) {
      if (var3.isRemoved()
         || var3.cannotPickup()
         || ((x3u93owcuyb5d4)var3).x6t8mlwqv273n() != null
         || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var3.getBlockPos())
         || !var2.canSee(var3)
         || !x23ik6olfsfgwk(var3.getStack(), var4)) {
         return false;
      } else {
         return var3.getStack().isOf(Items.EGG) && x3nf466jvpoooi(var2, Items.EGG) >= 16
            ? false
            : !var3.getStack().isOf(Items.MILK_BUCKET) || xw0xosmde9jmp.x2m95xmyunf3vb(var0, var1) < 4;
      }
   }

   private static boolean x17qx2f4ijtfsn(VillagerEntity var0, ItemStack var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         ItemStack var3 = var0.getInventory().getStack(var2);
         if (var3.isEmpty() || ItemStack.areItemsAndComponentsEqual(var3, var1) && var3.getCount() < var0.getInventory().getMaxCount(var1)) {
            return true;
         }
      }

      return false;
   }

   private static int x3nf466jvpoooi(VillagerEntity var0, Item var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.getInventory().size(); var3++) {
         ItemStack var4 = var0.getInventory().getStack(var3);
         if (var4.isOf(var1)) {
            var2 += var4.getCount();
         }
      }

      return var2;
   }
}
