package net.smartcolonies.r;

import java.util.Locale;

public enum ColonyRole {
   x35csp4lojh59r("builder"),
   x3r8p2tvihwpph("guard"),
   x2hwljbtybtlwc("lumberjack"),
   x18g4mqx9kxs89("miner"),
   x8jydaiei7iry("farmer"),
   xhds9crfjdp9i("fisherman"),
   xeoehg87qwfrn("rancher"),
   x1saccptp3i00w("elder"),
   x2xja04fpuesoh("founder"),
   x2j116s1wug0g2("citizen");

   private final String serializedName;

   ColonyRole(String serializedName) {
      this.serializedName = serializedName;
   }

   public String serializedName() {
      return this.serializedName;
   }

   public static ColonyRole byName(String var0) {
      if (var0 == null) {
         return x2j116s1wug0g2;
      }
      for (ColonyRole var4 : values()) {
         if (var4.serializedName.equalsIgnoreCase(var0) || var4.name().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return x2j116s1wug0g2;
   }
}
