package net.smartcolonies.r;

public final class ColonyRadiusHelper {
   private ColonyRadiusHelper() {
   }

   public static int x2vr85xzgtiacc(Colony var0, int var1, boolean var2) {
      double var3 = Math.clamp((var0.population() - var0.x182p7mjnr75sg() * 2) / 40.0, 0.0, 1.0);
      double var5 = Math.clamp((var0.cachedLogs() + var0.cachedStone() / 4.0) / 384.0, 0.0, 1.0);
      double var7 = Math.clamp((var0.population() * 12.0 - var0.cachedFood()) / Math.max(24.0, var0.population() * 12.0), 0.0, 1.0);
      double var9 = var2 ? 0.8 + var3 * 0.4 + var5 * 0.2 : 1.2 - var5 * 0.4 + var3 * 0.2 + var7 * 0.1;
      int var10000 = (int)Math.round(var1 * var9);
      if (var2) {
         return Math.clamp(var10000, 16, 40);
      } else if (var1 == 32) {
         return Math.clamp(var10000, 20, 48);
      } else {
         return Math.clamp(var10000, 40, 64);
      }
   }

   public static int x742kp3et4jfu(int var0, int var1, int var2, int var3) {
      double var4 = (double)Math.max(0, var1 - var2) / Math.max(1, var3 * 8);
      return Math.clamp((long)((int)Math.round(var0 * (1.6 - Math.clamp(var4, 0.0, 1.0) * 0.8))), 1, 64);
   }

   public static int x8v45wzyc7cx3(int var0, int var1, int var2) {
      double var3 = Math.clamp((double)(var2 - var1) / Math.max(1, var2), 0.0, 1.0);
      return Math.clamp((long)((int)Math.round(var0 * (0.75 + var3 * 0.75))), 1, 64);
   }

   public static int xpl8mvusb83z9(int var0, int var1, int var2) {
      int var3 = Math.max(0, var1 - var2);
      return Math.clamp((long)Math.min(var0, Math.max(1, var3 / 4)), 1, 64);
   }

   public static int x286mwub0gxrf2(Colony var0) {
      return Math.clamp((long)(1 + (var0.cachedLogs() + var0.cachedStone() / 4) / 192), 1, 4);
   }
}
