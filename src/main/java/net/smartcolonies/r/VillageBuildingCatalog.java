package net.smartcolonies.r;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.util.Identifier;

public final class VillageBuildingCatalog {
   public static final List<String> x22d0iut9v3npb = List.of(
      "small_house_1",
      "small_house_2",
      "small_house_3",
      "small_house_4",
      "small_house_5",
      "medium_house_1",
      "medium_house_2",
      "medium_house_3",
      "medium_house_4"
   );
   public static final List<String> xky4ubt7wm1bo = List.of(
      "butcher_shop_1",
      "tool_smith_1",
      "fletcher_house_1",
      "shepherds_house_1",
      "armorer_house_1",
      "armorer_2",
      "fisher_cottage_1",
      "tannery_1",
      "cartographer_house_1",
      "library_1",
      "masons_house_1",
      "weaponsmith_1",
      "weaponsmith_2",
      "temple_1"
   );
   public static final List<String> x1krr6wlni86ip = Stream.concat(x22d0iut9v3npb.stream(), xky4ubt7wm1bo.stream()).toList();

   private VillageBuildingCatalog() {
   }

   public static Identifier x1l4umcgiak8iw(String var0) {
      if (!x1krr6wlni86ip.contains(var0)) {
         throw new IllegalArgumentException("Unknown taiga house: " + var0);
      } else {
         return Identifier.of("smart_colonies", "village/taiga/houses/taiga_" + var0);
      }
   }
}
