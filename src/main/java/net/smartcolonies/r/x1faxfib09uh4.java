package net.smartcolonies.r;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradedItem;

public final class x1faxfib09uh4 {
   private static final Map<VillagerEntity, Map<TradeOffer, xbwzbqntj80jx>> x2od5627wbgq9s = Collections.synchronizedMap(new WeakHashMap<>());

   private x1faxfib09uh4() {
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static TradeOfferList x88t1bn7r4bt7(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, x1rxiy9mtu5b3h var3) {
      TradeOfferList var4 = new TradeOfferList();
      IdentityHashMap var5 = new IdentityHashMap();
      switch (var3) {
         case x1saccptp3i00w:
            for (int var9 = 1; var9 <= 2; var9++) {
               int var12 = x1a5rxjbs73iva.x2vr85xzgtiacc(var1, var9 == 1 ? 32 : 64, false);
               TradeOffer var8 = new TradeOffer(
                  new TradedItem(Items.EMERALD, var12), xquptt42fexz2.x2qg2bi4tzny42(var0, var1, var9), x1a5rxjbs73iva.x286mwub0gxrf2(var1), 10 * var9, 0.0F
               );
               var4.add(var8);
               var5.put(var8, new xbwzbqntj80jx(var1, var3, Items.PAPER, 1, var12, true, 0, var0x -> false, true));
            }

            int var10 = x1a5rxjbs73iva.x2vr85xzgtiacc(var1, 24, true);
            TradeOffer var13 = new TradeOffer(
               new TradedItem(Items.EMERALD, var10), x6amcj7vm0jm5.x31zhh2yvui44c(var0, var1), x1a5rxjbs73iva.x286mwub0gxrf2(var1), 10, 0.0F
            );
            var4.add(var13);
            var5.put(var13, new xbwzbqntj80jx(var1, var3, Items.PAPER, 1, var10, true, 0, var0x -> false, true));
            break;
         case x18g4mqx9kxs89:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.COBBLESTONE) || var0x.isOf(Items.COBBLED_DEEPSLATE), 16, 64, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.COPPER_INGOT), 8, 8, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.IRON_INGOT), 4, 36, 2);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.DIAMOND), 1, 4, 4);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.OAK_LOG, 8, 16, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.COAL, 8, 16, 1);
            break;
         case x2hwljbtybtlwc:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isIn(ItemTags.LOGS), 8, 48, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isIn(ItemTags.SAPLINGS), 4, 8, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.OAK_SAPLING, 8, 8, 1);
            break;
         case x8jydaiei7iry:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.WHEAT), 16, 32, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.BREAD), 6, 16, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.CARROT) || var0x.isOf(Items.POTATO) || var0x.isOf(Items.BEETROOT), 12, 24, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.WHEAT_SEEDS, 16, 16, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.PUMPKIN_SEEDS, 4, 8, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.BREAD, 8, 64, 2);
            break;
         case xhds9crfjdp9i:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.COD) || var0x.isOf(Items.COOKED_COD), 8, 16, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.SALMON) || var0x.isOf(Items.COOKED_SALMON), 6, 12, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.STRING, 3, 3, 1);
            break;
         case xeoehg87qwfrn:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.EGG), 16, 16, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.LEATHER), 4, 8, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.FEATHER), 8, 12, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> Registries.ITEM.getId(var0x.getItem()).getPath().endsWith("_wool"), 6, 12, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.WHEAT, 16, 16, 2);
            break;
         case x35csp4lojh59r:
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isIn(ItemTags.PLANKS), 16, 64, 1);
            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS), 16, 128, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.OAK_LOG, 16, 48, 2);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.COBBLESTONE, 32, 96, 2);
            break;
         case x3r8p2tvihwpph:
            x22fpul10e08s.x1p0bvs1guedf7(var0, var1);

            for (xzld74pdvhfbu var7 : x22fpul10e08s.x3ck5rmycrqg51) {
               x2r5pchrzult8h(var0, var1, var3, var4, var5, x22fpul10e08s.xeccokn9mws72(var7.x3dtjj1ybya6jb()), 1, 1, var7.x1e0osd3mmpogq());
            }

            x2r5pchrzult8h(var0, var1, var3, var4, var5, var0x -> var0x.isOf(Items.ARROW), 16, 32, 1);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.ARROW, 32, 32, 2);
            x1hglz9jh0d1so(var0, var1, var3, var4, var5, Items.IRON_INGOT, 8, 24, 3);
            break;
         default:
            return var2.getOffers();
      }

      if (var4.isEmpty()) {
         Item var11 = switch (var3) {
            case x1saccptp3i00w -> Items.PAPER;
            case x18g4mqx9kxs89, x35csp4lojh59r -> Items.COBBLESTONE;
            case x2hwljbtybtlwc -> Items.OAK_LOG;
            case x8jydaiei7iry, xeoehg87qwfrn -> Items.WHEAT;
            case xhds9crfjdp9i -> Items.COD;
            case x3r8p2tvihwpph -> Items.ARROW;
            default -> Items.BREAD;
         };
         TradeOffer var14 = new TradeOffer(new TradedItem(Items.EMERALD), new ItemStack(var11), 1, 0, 0.0F);
         var14.disable();
         var4.add(var14);
      }

      x2od5627wbgq9s.put(var2, var5);
      var2.setOffers(var4);
      return var4;
   }

   public static boolean xcynwq3uap918(VillagerEntity var0, TradeOffer var1) {
      Map var2 = x2od5627wbgq9s.get(var0);
      return var2 != null && var2.containsKey(var1);
   }

   public static boolean x1ujd85h9771yr(ServerWorld var0, VillagerEntity var1, TradeOffer var2) {
      xbwzbqntj80jx var3 = x19rv12tlpuxm5(var1, var2);
      if (var3 == null) {
         return true;
      } else {
         x15kgoaq6i3m6i var4 = var3.x2dllwbgbx21jg();
         int var5 = var3.x1cdr8im5qpob() ? var2.getDisplayedFirstBuyItem().getCount() : var3.xq8izrlsuolkq();
         int var6 = var3.x1cdr8im5qpob() ? var3.xinr6pgmsbyag() : var2.getDisplayedFirstBuyItem().getCount();
         if (var3.x3bz3dxgnj5nsn()) {
            return x3qagqs1j5wazk(var0, var4, Items.EMERALD) >= var5;
         } else {
            return var3.x1cdr8im5qpob()
               ? xv9ih2vzuzpss(var0, var4, var3.x3quiphk00acds(), var3.x14h2cquxnospk()) >= var3.x2ybkscunemmqc() + var3.xinr6pgmsbyag()
                  && xv9ih2vzuzpss(var0, var4, var3.x3quiphk00acds(), var1x -> var1x.isOf(var3.x2l8db5concsuf()) && var3.x14h2cquxnospk().test(var1x))
                     >= var3.xinr6pgmsbyag()
                  && x3qagqs1j5wazk(var0, var4, Items.EMERALD) >= var5
               : x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var4, var0x -> var0x.isOf(Items.EMERALD)) >= var5
                  && x3qagqs1j5wazk(var0, var4, var3.x2l8db5concsuf()) >= var6;
         }
      }
   }

   public static void x1qctxo20x2yyf(ServerWorld var0, VillagerEntity var1, TradeOffer var2) {
      xbwzbqntj80jx var3 = x19rv12tlpuxm5(var1, var2);
      if (var3 != null) {
         x15kgoaq6i3m6i var4 = var3.x2dllwbgbx21jg();
         if (x1ujd85h9771yr(var0, var1, var2)) {
            int var5 = var3.x1cdr8im5qpob() ? var2.getDisplayedFirstBuyItem().getCount() : var3.xq8izrlsuolkq();
            int var6 = var3.x1cdr8im5qpob() ? var3.xinr6pgmsbyag() : var2.getDisplayedFirstBuyItem().getCount();
            if (var3.x3bz3dxgnj5nsn()) {
               if (x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new ItemStack(Items.EMERALD, var5))) {
                  var4.x3ktd3i0hq4h53();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "продажа разрешения", 1);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
               }
            } else {
               if (var3.x1cdr8im5qpob()) {
                  if (!x3gx6johh202p8(
                     var0, var4, var3.x3quiphk00acds(), var1x -> var1x.isOf(var3.x2l8db5concsuf()) && var3.x14h2cquxnospk().test(var1x), var3.xinr6pgmsbyag()
                  )) {
                     return;
                  }

                  if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new ItemStack(Items.EMERALD, var5))) {
                     return;
                  }

                  var4.x3ktd3i0hq4h53();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "торговля с игроком: продажа", var3.xinr6pgmsbyag());
               } else {
                  if (!x2n55y2tom37h2.x22hjebj4vmztt(var0, var4, var0x -> var0x.isOf(Items.EMERALD), var5)) {
                     return;
                  }

                  if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var4, new ItemStack(var3.x2l8db5concsuf(), var6))) {
                     return;
                  }

                  var4.xqcex85brzoq2();
                  x2vaj7rsm57i4n.x2t98zlq4lu49t(var1, "торговля с игроком: покупка", var6);
               }

               x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
            }
         }
      }
   }

   private static void x2r5pchrzult8h(
      ServerWorld var0,
      x15kgoaq6i3m6i var1,
      x1rxiy9mtu5b3h var2,
      TradeOfferList var3,
      Map<TradeOffer, xbwzbqntj80jx> var4,
      Predicate<ItemStack> var5,
      int var6,
      int var7,
      int var8
   ) {
      int var9 = xv9ih2vzuzpss(var0, var1, var2, var5);
      var7 = Math.max(var7, var2 != x1rxiy9mtu5b3h.x8jydaiei7iry && var2 != x1rxiy9mtu5b3h.xhds9crfjdp9i ? 0 : var1.population() * 2);
      var6 = x1a5rxjbs73iva.xpl8mvusb83z9(var6, var9, var7);
      var8 = x1a5rxjbs73iva.x742kp3et4jfu(var8, var9, var7, var6);
      Item var10 = x1n2hhi2syzjbb(var0, var1, var2, var5, var6);
      if (var10 != null) {
         int var11 = Math.min(
            12,
            Math.min(
               (xv9ih2vzuzpss(var0, var1, var2, var5) - var7) / var6, xv9ih2vzuzpss(var0, var1, var2, var2x -> var2x.isOf(var10) && var5.test(var2x)) / var6
            )
         );
         if (var11 > 0) {
            TradeOffer var12 = new TradeOffer(new TradedItem(Items.EMERALD, var8), new ItemStack(var10, var6), var11, 1, 0.0F);
            var3.add(var12);
            var4.put(var12, new xbwzbqntj80jx(var1, var2, var10, var6, var8, true, var7, var5, false));
         }
      }
   }

   private static void x1hglz9jh0d1so(
      ServerWorld var0,
      x15kgoaq6i3m6i var1,
      x1rxiy9mtu5b3h var2,
      TradeOfferList var3,
      Map<TradeOffer, xbwzbqntj80jx> var4,
      Item var5,
      int var6,
      int var7,
      int var8
   ) {
      int var9 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var1x -> var1x.isOf(var5));
      var7 = Math.max(var7, var2 != x1rxiy9mtu5b3h.x8jydaiei7iry && var2 != x1rxiy9mtu5b3h.xeoehg87qwfrn ? var7 : var1.population() * 2);
      var8 = x1a5rxjbs73iva.x8v45wzyc7cx3(var8, var9, var7);
      var6 = Math.max(1, Math.min(var6, Math.max(1, var7 - var9)));
      int var10 = x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.EMERALD)) / var8;
      int var11 = Math.min(12, Math.min(var10, Math.max(0, (var7 - var9 + var6 - 1) / var6)));
      if (var11 > 0 && x3qagqs1j5wazk(var0, var1, var5) >= var6) {
         TradeOffer var12 = new TradeOffer(new TradedItem(var5, var6), new ItemStack(Items.EMERALD, var8), var11, 1, 0.0F);
         var3.add(var12);
         var4.put(var12, new xbwzbqntj80jx(var1, var2, var5, var6, var8, false, 0, var1x -> var1x.isOf(var5), false));
      }
   }

   private static xbwzbqntj80jx x19rv12tlpuxm5(VillagerEntity var0, TradeOffer var1) {
      Map var2 = x2od5627wbgq9s.get(var0);
      return var2 == null ? null : (xbwzbqntj80jx)var2.get(var1);
   }

   private static Item x1n2hhi2syzjbb(ServerWorld var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<ItemStack> var3, int var4) {
      for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
         if (var0.getChunkManager().isChunkLoaded(var6.getX() >> 4, var6.getZ() >> 4)
            && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var6)
            && (
               var2 == x1rxiy9mtu5b3h.x3r8p2tvihwpph
                  || var1.x2h8eqlzb5mm0w(var6) == x2dtqvbx3ov8jv(var2)
                  || var1.x2h8eqlzb5mm0w(var6) == x2jj5edkiu5orf.x2y2vmpsepzv3
            )
            && var0.getBlockEntity(var6) instanceof Inventory var7) {
            for (int var10 = 0; var10 < var7.size(); var10++) {
               ItemStack var9 = var7.getStack(var10);
               if (!var9.isEmpty() && var3.test(var9) && xv9ih2vzuzpss(var0, var1, var2, var2x -> var2x.isOf(var9.getItem()) && var3.test(var2x)) >= var4) {
                  return var9.getItem();
               }
            }
         }
      }

      return null;
   }

   private static int xv9ih2vzuzpss(ServerWorld var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<ItemStack> var3) {
      int var4 = 0;
      x2jj5edkiu5orf var5 = x2dtqvbx3ov8jv(var2);

      for (BlockPos var7 : var1.x2ad3s8aentmo4()) {
         if (var0.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)
            && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var7)
            && (var2 == x1rxiy9mtu5b3h.x3r8p2tvihwpph || var1.x2h8eqlzb5mm0w(var7) == var5 || var1.x2h8eqlzb5mm0w(var7) == x2jj5edkiu5orf.x2y2vmpsepzv3)
            && var0.getBlockEntity(var7) instanceof Inventory var8) {
            for (int var11 = 0; var11 < var8.size(); var11++) {
               ItemStack var10 = var8.getStack(var11);
               if (!var10.isEmpty() && var3.test(var10)) {
                  var4 += var10.getCount();
               }
            }
         }
      }

      return var4;
   }

   private static boolean x3gx6johh202p8(ServerWorld var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<ItemStack> var3, int var4) {
      if (xv9ih2vzuzpss(var0, var1, var2, var3) < var4) {
         return false;
      } else {
         int var5 = var4;
         x2jj5edkiu5orf var6 = x2dtqvbx3ov8jv(var2);

         for (BlockPos var8 : var1.x2ad3s8aentmo4()) {
            if (var0.getChunkManager().isChunkLoaded(var8.getX() >> 4, var8.getZ() >> 4)
               && !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var8)
               && (var2 == x1rxiy9mtu5b3h.x3r8p2tvihwpph || var1.x2h8eqlzb5mm0w(var8) == var6 || var1.x2h8eqlzb5mm0w(var8) == x2jj5edkiu5orf.x2y2vmpsepzv3)
               && var0.getBlockEntity(var8) instanceof Inventory var10) {
               for (int var11 = 0; var11 < var10.size() && var5 > 0; var11++) {
                  ItemStack var12 = var10.getStack(var11);
                  if (!var12.isEmpty() && var3.test(var12)) {
                     int var13 = Math.min(var5, var12.getCount());
                     Item var14 = var12.getItem();
                     var12.decrement(var13);
                     var1.x2sh8yupnxmo71(var14, var13);
                     var5 -= var13;
                     var10.markDirty();
                  }
               }

               if (var5 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static int x3qagqs1j5wazk(ServerWorld var0, x15kgoaq6i3m6i var1, Item var2) {
      return x2n55y2tom37h2.x1amge7aoh04ca(var0, var1, new ItemStack(var2));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static x2jj5edkiu5orf x2dtqvbx3ov8jv(x1rxiy9mtu5b3h var0) {
      return switch (var0) {
         case x18g4mqx9kxs89 -> x2jj5edkiu5orf.x3leq9hn5gf43c;
         case x2hwljbtybtlwc, x35csp4lojh59r -> x2jj5edkiu5orf.x24dt8qhwemmti;
         case x8jydaiei7iry, xhds9crfjdp9i -> x2jj5edkiu5orf.xnpajviatkbuo;
         case xeoehg87qwfrn -> x2jj5edkiu5orf.x2uujme1um3d1w;
         default -> x2jj5edkiu5orf.x2y2vmpsepzv3;
      };
   }
}
