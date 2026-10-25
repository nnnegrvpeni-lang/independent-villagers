package net.smartcolonies.r;

public enum ConstructionStatus {
   x2tgnsmc98zm7p,
   x1mzkgtgadv3jh,
   xqfd7s4y4gux6,
   x3vi306qg1ms75;

   public static ConstructionStatus byName(String var0) {
      if (var0 == null) return x2tgnsmc98zm7p;
      if (var0.equalsIgnoreCase("QUEUED") || var0.equalsIgnoreCase("x2tgnsmc98zm7p")) return x2tgnsmc98zm7p;
      if (var0.equalsIgnoreCase("BUILDING") || var0.equalsIgnoreCase("x1mzkgtgadv3jh")) return x1mzkgtgadv3jh;
      if (var0.equalsIgnoreCase("COMPLETE") || var0.equalsIgnoreCase("xqfd7s4y4gux6")) return xqfd7s4y4gux6;
      if (var0.equalsIgnoreCase("BLOCKED") || var0.equalsIgnoreCase("x3vi306qg1ms75")) return x3vi306qg1ms75;
      return x2tgnsmc98zm7p;
   }
}
