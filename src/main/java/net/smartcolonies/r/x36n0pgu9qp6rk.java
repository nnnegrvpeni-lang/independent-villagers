package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class x36n0pgu9qp6rk {
   private static final long x14f8xquqjscjr = 200L;

   private x36n0pgu9qp6rk() {
   }

   public static void xkfga2dpst7kt(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2, long var3) {
      if (var3 >= var1.x3b9zegn105yce() && !var1.x2ad3s8aentmo4().isEmpty()) {
         var1.x3kvixtipq5eqg(var3 + 200L);
         List<VillagerEntity> var5 = var2.stream()
            .filter(var1x -> !var1x.isBaby() && var1.roleOf(var1x.getUuid()) != x1rxiy9mtu5b3h.x2j116s1wug0g2)
            .sorted(Comparator.comparing(Entity::getUuid))
            .toList();
         if (!var5.isEmpty()) {
            x2n55y2tom37h2.x2u0vsam1tcmhq(var0, var1);
            boolean var6 = var1.cachedFood() < 32;
            boolean var7 = var1.cachedLogs() < 8;
            if (!var6 && !var7) {
               VillagerEntity var13 = var5.stream().filter(var1x -> {
                  x1rxiy9mtu5b3h var2x = var1.roleOf(var1x.getUuid());
                  return var2x == x1rxiy9mtu5b3h.x3r8p2tvihwpph || var2x == x1rxiy9mtu5b3h.x35csp4lojh59r;
               }).findFirst().orElse((VillagerEntity)var5.getFirst());
               if (xt78n188f3w97.x1gfvg3f4hcsus(var0, var1) < xt78n188f3w97.x20ln0j5i9gefl(var1)) {
                  if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.PUMPKIN_SEEDS)) == 0
                     && x33p9oz0tzh54f(var0, var1, var13, new ItemStack(Items.PUMPKIN_SEEDS, 4), 1, "семена тыквы для фермы")) {
                     return;
                  }

                  int var14 = xt78n188f3w97.x20ln0j5i9gefl(var1) - xt78n188f3w97.x1gfvg3f4hcsus(var0, var1);
                  int var16 = x2egalzebb4k1a(var0, var1, x36n0pgu9qp6rk::x2l8k383vfoz57);
                  if (var16 < var14 && x33p9oz0tzh54f(var0, var1, var13, new ItemStack(Items.PUMPKIN, Math.min(2, var14 - var16)), 1, "тыква для голема")) {
                     return;
                  }

                  int var18 = x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT));
                  if (var18 < 36 && x33p9oz0tzh54f(var0, var1, var13, new ItemStack(Items.IRON_INGOT, Math.min(8, 36 - var18)), 2, "железо для голема")) {
                     return;
                  }
               }

               int var15 = Math.floorMod(var1.x3dkkolbe7ust6(), var5.size());
               var1.x18w8ltfnp5moy();

               for (int var17 = 0; var17 < var5.size(); var17++) {
                  VillagerEntity var19 = (VillagerEntity)var5.get((var15 + var17) % var5.size());
                  x1rxiy9mtu5b3h var12 = var1.roleOf(var19.getUuid());
                  if (x1llll0zw8rv2n(var0, var1, var19, var12) || x3ce7hz5kx2dh(var0, var1, var19, var12)) {
                     return;
                  }
               }
            } else {
               VillagerEntity var8 = (VillagerEntity)var5.getFirst();
               if (!var6 || !x33p9oz0tzh54f(var0, var1, var8, new ItemStack(Items.BREAD, 8), 2, "аварийный запас еды")) {
                  if (!var7
                     || var6 && var1.cachedLogs() >= 5
                     || !x33p9oz0tzh54f(var0, var1, var8, new ItemStack(Items.OAK_LOG, 8), 1, "древесина для запуска хозяйства")) {
                     for (VillagerEntity var10 : var5) {
                        x1rxiy9mtu5b3h var11 = var1.roleOf(var10.getUuid());
                        if ((var11 == x1rxiy9mtu5b3h.x18g4mqx9kxs89 || var11 == x1rxiy9mtu5b3h.x35csp4lojh59r)
                           && xyt7zxwpica1j(
                              var0,
                              var1,
                              var10,
                              var11,
                              var0x -> var0x.isOf(Items.COBBLESTONE) || var0x.isOf(Items.COBBLED_DEEPSLATE),
                              32,
                              64,
                              1,
                              "камень на снабжение"
                           )) {
                           return;
                        }

                        if (x3ce7hz5kx2dh(var0, var1, var10, var11)) {
                           return;
                        }
                     }

                     x2vaj7rsm57i4n.x1qd0mutwsr3sc(var8, "не хватает еды/древесины: копим изумруды на снабжение");
                  }
               }
            }
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean x1llll0zw8rv2n(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, x1rxiy9mtu5b3h var3) {
      int var10000 = switch (var3) {
         case x35csp4lojh59r -> -1516598617 ^ 2103022955;
         case x18g4mqx9kxs89 -> -2092133756 ^ 1791918978;
         case x8jydaiei7iry -> 477854773 ^ 828944652;
         case x2hwljbtybtlwc -> 1129900787 ^ -1743688680;
         case xhds9crfjdp9i -> 665239924 ^ -1582140829;
         case xeoehg87qwfrn -> -1581585140 ^ 45426803;
         case x3r8p2tvihwpph -> 69278449 ^ -1222077420;
         default -> 1826562522 ^ -1198821139;
      };

      while (true) {
         switch (var10000) {
            case -2095589582:
            default:
               return false;
            case -2045463785:
               if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.STRING)) < 3
                  && x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.STRING, 3), 1, "леска")) {
                  return true;
               }

               var10000 = 1964242705 ^ -1177772898;
               continue;
            case -1963714483:
               return false;
            case -1955225018:
               return false;
            case -1559247489:
               if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.WHEAT)) < 16
                  && x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.WHEAT, 16), 2, "корм")) {
                  return true;
               }

               var10000 = 609716880 ^ -1488953950;
               continue;
            case -1510890893:
               return true;
            case -1291219227:
               if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.ARROW)) >= 32) {
                  var10000 = 2093996659 ^ -110226151;
                  continue;
               } else if (x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.ARROW, 32), 2, "стрелы")) {
                  var10000 = 1879001478 ^ 1129297199;
                  continue;
               }
            case -2052993174:
               if (var1.x12nt3bv2et7b7() >= 24 || !x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.IRON_INGOT, 8), 3, "железо для снаряжения")) {
                  var10000 = -1361205166 ^ 1442751622;
                  continue;
               }

               return true;
            case -1016934571:
               return true;
            case -857773169:
               return false;
            case -732671689:
               return false;
            case -658300980:
               if (var1.cachedLogs() >= 48) {
                  var10000 = 2056297927 ^ 359032166;
                  continue;
               } else if (x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.OAK_LOG, 16), 2, "брёвна")) {
                  var10000 = 1535099511 ^ -24223740;
                  continue;
               }
            case 1878456481:
               if (var1.cachedStone() >= 96 || !x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.COBBLESTONE, 32), 2, "камень")) {
                  var10000 = -1621125842 ^ -124833842;
                  continue;
               }

               return true;
            case -615938325:
               if (var1.x2zm76h0pkiu6o() < 8 && x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.OAK_SAPLING, 8), 1, "саженцы")) {
                  return true;
               }

               var10000 = 326374435 ^ -1719661458;
               continue;
            case -377351930:
               if (var1.cachedLogs() >= 16) {
                  var10000 = -632687916 ^ 1094283635;
                  continue;
               } else if (x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.OAK_LOG, 8), 1, "крепи шахты")) {
                  var10000 = -1141716978 ^ -2076249137;
                  continue;
               }
            case -1687125081:
               if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.COAL) || var0x.isOf(Items.CHARCOAL)) >= 16
                  || !x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.COAL, 8), 1, "уголь")) {
                  var10000 = 1222349130 ^ -1230693574;
                  continue;
               }

               return true;
            case -81589036:
               return false;
            case -25261456:
               return false;
            case 749796521:
               return true;
            case 756272441:
               if (x2egalzebb4k1a(var0, var1, var0x -> var0x.isOf(Items.PUMPKIN_SEEDS)) >= 4) {
                  var10000 = -736033384 ^ 543466836;
                  continue;
               } else if (x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.PUMPKIN_SEEDS, 4), 1, "семена тыквы")) {
                  break;
               }
            case -196763444:
               if (x2egalzebb4k1a(var0, var1, x36n0pgu9qp6rk::x161x1w456knty) >= 16) {
                  var10000 = 1743226344 ^ 141878033;
                  continue;
               } else if (x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.WHEAT_SEEDS, 16), 1, "семена")) {
                  break;
               }
            case 1871931129:
               if (var1.cachedFood() >= Math.max(64, var1.population() * 8) || !x33p9oz0tzh54f(var0, var1, var2, new ItemStack(Items.BREAD, 8), 2, "еда")) {
                  var10000 = -214895400 ^ 2017810078;
                  continue;
               }

               return true;
            case 1070349249:
               return true;
            case 1741732576:
               return false;
         }

         var10000 = -1241548851 ^ 1990047896;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static boolean x3ce7hz5kx2dh(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, x1rxiy9mtu5b3h var3) {
      int var10000 = switch (var3) {
         case x35csp4lojh59r -> -1475028330 ^ -1506054810;
         case x18g4mqx9kxs89 -> 386874187 ^ 1314905733;
         case x8jydaiei7iry -> 121313840 ^ 1153173138;
         case x2hwljbtybtlwc -> 1708111539 ^ 695515164;
         case xhds9crfjdp9i -> -131700626 ^ 1424792683;
         case xeoehg87qwfrn -> 679769920 ^ -1302660587;
         default -> 2114905183 ^ 835720493;
      };

      while (true) {
         switch (var10000) {
            case -1912257192:
            default:
               return false;
            case -1696690859:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.EGG), 16, 16, 1, "яйца")
                  || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.LEATHER), 8, 16, 1, "кожа")
                  || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.FEATHER), 16, 24, 1, "перья")) {
                  var10000 = -1408875051 ^ -1147682899;
                  break;
               } else if (!xyt7zxwpica1j(
                  var0, var1, var2, var3, var0x -> Registries.ITEM.getId(var0x.getItem()).getPath().endsWith("_wool"), 12, 24, 1, "шерсть"
               )) {
                  var10000 = -1990355794 ^ -997981973;
                  break;
               }
            case 395442808:
               return true;
            case -1395985403:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.COD), 15, 32, 1, "треска")) {
                  var10000 = -827333230 ^ -1917862133;
                  break;
               } else if (!xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.SALMON), 12, 24, 1, "лосось")) {
                  var10000 = -1428129139 ^ -477371747;
                  break;
               }
            case 1124096665:
               return true;
            case -598785525:
               return false;
            case 238008304:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isIn(ItemTags.PLANKS), 32, 128, 1, "доски")) {
                  var10000 = 325863808 ^ 121318087;
                  break;
               } else if (!xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS), 32, 256, 1, "строительный камень")) {
                  var10000 = 519787815 ^ -1028384980;
                  break;
               }
            case 341271367:
               return true;
            case 1128932074:
               return false;
            case 1132928162:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.WHEAT), 20, 64, 1, "пшеница")
                  || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.BREAD), 12, 32, 1, "хлеб")) {
                  var10000 = 340013444 ^ -467479378;
                  break;
               } else if (!xyt7zxwpica1j(
                  var0, var1, var2, var3, var0x -> var0x.isOf(Items.CARROT) || var0x.isOf(Items.POTATO) || var0x.isOf(Items.BEETROOT), 24, 48, 1, "урожай"
               )) {
                  var10000 = 1906110481 ^ 16268775;
                  break;
               }
            case -261692118:
               return true;
            case 1231788048:
               return false;
            case 1287327407:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isIn(ItemTags.LOGS), 16, 128, 1, "брёвна")) {
                  var10000 = -2118026373 ^ -335408101;
                  break;
               } else if (!xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isIn(ItemTags.SAPLINGS), 16, 32, 1, "саженцы")) {
                  var10000 = 1694396294 ^ 666127724;
                  break;
               }
            case 1841524576:
               return true;
            case 1306104901:
               return false;
            case 1340010866:
               return false;
            case 1498473934:
               if (xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.COBBLESTONE) || var0x.isOf(Items.COBBLED_DEEPSLATE), 32, 256, 1, "камень")
                  || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.COPPER_INGOT), 16, 32, 1, "медь")
                  || xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.IRON_INGOT), 8, 48, 2, "железо")) {
                  var10000 = -1075138357 ^ 1108786159;
                  break;
               } else if (!xyt7zxwpica1j(var0, var1, var2, var3, var0x -> var0x.isOf(Items.DIAMOND), 2, 8, 3, "алмазы")) {
                  var10000 = 1790817571 ^ -457641861;
                  break;
               }
            case -33813724:
               return true;
            case 1902431734:
               return false;
         }
      }
   }

   private static boolean xyt7zxwpica1j(
      ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, x1rxiy9mtu5b3h var3, Predicate<ItemStack> var4, int var5, int var6, int var7, String var8
   ) {
      if (xbctmjmoh96up(var0, var1, var3, var4) < var6 + var5) {
         return false;
      } else if (!x2n55y2tom37h2.x38ipozok9mc0x(var0, var1, () -> x2vihal082zx8k(var0, var1, var3, var4, var5), List.of(new ItemStack(Items.EMERALD, var7)))) {
         return false;
      } else {
         var1.x3ktd3i0hq4h53();
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "продажа: " + var8, var5);
         return true;
      }
   }

   private static boolean x33p9oz0tzh54f(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, ItemStack var3, int var4, String var5) {
      if (!x2n55y2tom37h2.x38ipozok9mc0x(var0, var1, () -> x2n55y2tom37h2.x22hjebj4vmztt(var0, var1, var0xx -> var0xx.isOf(Items.EMERALD), var4), List.of(var3))
         )
       {
         return false;
      } else {
         var1.xqcex85brzoq2();
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "покупка: " + var5, var3.getCount());
         return true;
      }
   }

   private static int xbctmjmoh96up(ServerWorld var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<ItemStack> var3) {
      x2jj5edkiu5orf var4 = x3rd7rxzqtevtb(var2);
      int var5 = 0;

      for (BlockPos var7 : var1.x2ad3s8aentmo4()) {
         if ((var1.x2h8eqlzb5mm0w(var7) == var4 || var1.x2h8eqlzb5mm0w(var7) == x2jj5edkiu5orf.x2y2vmpsepzv3)
            && var0.getBlockEntity(var7) instanceof Inventory var8) {
            for (int var11 = 0; var11 < var8.size(); var11++) {
               ItemStack var10 = var8.getStack(var11);
               if (!var10.isEmpty() && var3.test(var10)) {
                  var5 += var10.getCount();
               }
            }
         }
      }

      return var5;
   }

   private static int x2egalzebb4k1a(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2) {
      return x2n55y2tom37h2.x1mlne8tl8k5ag(var0, var1, var2);
   }

   private static boolean x2vihal082zx8k(ServerWorld var0, x15kgoaq6i3m6i var1, x1rxiy9mtu5b3h var2, Predicate<ItemStack> var3, int var4) {
      if (xbctmjmoh96up(var0, var1, var2, var3) < var4) {
         return false;
      } else {
         int var5 = var4;
         x2jj5edkiu5orf var6 = x3rd7rxzqtevtb(var2);

         for (BlockPos var8 : var1.x2ad3s8aentmo4()) {
            if ((var1.x2h8eqlzb5mm0w(var8) == var6 || var1.x2h8eqlzb5mm0w(var8) == x2jj5edkiu5orf.x2y2vmpsepzv3)
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

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static x2jj5edkiu5orf x3rd7rxzqtevtb(x1rxiy9mtu5b3h var0) {
      return switch (var0) {
         case x35csp4lojh59r, x2hwljbtybtlwc -> x2jj5edkiu5orf.x24dt8qhwemmti;
         case x18g4mqx9kxs89 -> x2jj5edkiu5orf.x3leq9hn5gf43c;
         case x8jydaiei7iry, xhds9crfjdp9i -> x2jj5edkiu5orf.xnpajviatkbuo;
         case xeoehg87qwfrn -> x2jj5edkiu5orf.x2uujme1um3d1w;
         default -> x2jj5edkiu5orf.x2y2vmpsepzv3;
      };
   }

   private static boolean x2l8k383vfoz57(ItemStack var0) {
      return var0.isOf(Items.PUMPKIN) || var0.isOf(Items.CARVED_PUMPKIN);
   }

   private static boolean x161x1w456knty(ItemStack var0) {
      return var0.isOf(Items.WHEAT_SEEDS)
         || var0.isOf(Items.BEETROOT_SEEDS)
         || var0.isOf(Items.PUMPKIN_SEEDS)
         || var0.isOf(Items.CARROT)
         || var0.isOf(Items.POTATO);
   }
}
