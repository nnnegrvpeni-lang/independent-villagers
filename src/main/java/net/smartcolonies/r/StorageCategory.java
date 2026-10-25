package net.smartcolonies.r;

import java.util.Locale;

public enum StorageCategory {
   x2y2vmpsepzv3("общий"),
   xnpajviatkbuo("еда и урожай"),
   x24dt8qhwemmti("древесина"),
   x3leq9hn5gf43c("камень и руда"),
   x2uujme1um3d1w("животноводство");

   private final String x70udpkzuguui;

   private StorageCategory(String var3) {
      this.x70udpkzuguui = var3;
   }

   public String displayName() {
      return this.x70udpkzuguui;
   }

   public static StorageCategory byName(String var0) {
      if (var0 == null) return x2y2vmpsepzv3;
      if (var0.equalsIgnoreCase("GENERAL") || var0.equalsIgnoreCase("x2y2vmpsepzv3")) return x2y2vmpsepzv3;
      if (var0.equalsIgnoreCase("FOOD") || var0.equalsIgnoreCase("xnpajviatkbuo")) return xnpajviatkbuo;
      if (var0.equalsIgnoreCase("WOOD") || var0.equalsIgnoreCase("x24dt8qhwemmti")) return x24dt8qhwemmti;
      if (var0.equalsIgnoreCase("MINING") || var0.equalsIgnoreCase("x3leq9hn5gf43c")) return x3leq9hn5gf43c;
      if (var0.equalsIgnoreCase("ANIMAL") || var0.equalsIgnoreCase("x2uujme1um3d1w")) return x2uujme1um3d1w;
      try {
         return valueOf(var0.toUpperCase(Locale.ROOT));
      } catch (IllegalArgumentException var2) {
         return x2y2vmpsepzv3;
      }
   }

   public static StorageCategory forHouse(long var0) {
      StorageCategory[] var2 = new StorageCategory[]{xnpajviatkbuo, x24dt8qhwemmti, x3leq9hn5gf43c, x2uujme1um3d1w, x2y2vmpsepzv3};
      return var2[Math.floorMod((int)var0, var2.length)];
   }
}
