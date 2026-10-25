package net.smartcolonies.r;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.Identifier;

public final class HouseVariantCatalog {
   public static final List<String> xk7wovhaogxn4 = List.of(
      "small_house_1",
      "small_house_2",
      "small_house_3",
      "small_house_4",
      "small_house_5",
      "small_house_6",
      "small_house_7",
      "small_house_8",
      "medium_house_1",
      "medium_house_2"
   );
   public static final List<String> x1k0jwunm7std5 = List.of(
      "butchers_shop_1",
      "butchers_shop_2",
      "tool_smith_1",
      "fletcher_house_1",
      "shepherd_1",
      "armorer_1",
      "fisher_cottage_1",
      "tannery_1",
      "cartographer_1",
      "library_1",
      "mason_1",
      "weaponsmith_1",
      "weaponsmith_2",
      "temple_1",
      "temple_2"
   );
   public static final List<String> x11oxp2ntj65hs = Stream.concat(xk7wovhaogxn4.stream(), x1k0jwunm7std5.stream()).toList();

   private HouseVariantCatalog() {
   }

   public static Identifier xhp130tff2ww4(String var0) {
      if (!x11oxp2ntj65hs.contains(var0)) {
         throw new IllegalArgumentException("Unknown savanna house: " + var0);
      } else {
         return Identifier.of("smart_colonies", "village/savanna/houses/savanna_" + var0);
      }
   }
}
