package net.smartcolonies.r;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class x2fvuqnp9zks2x {
   private x2fvuqnp9zks2x() {
   }

   public static boolean x14gj5yhqspcfw(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (var1.x2o79xnwz4g9nh(var2, 1)) {
         return false;
      } else if (x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var2)) {
         return false;
      } else if (x20rghnsl2yowl.x239t1m3hfi74d(var0, var2) || x20rghnsl2yowl.x239t1m3hfi74d(var0, var2.up())) {
         return false;
      } else if (!var1.x2a24huns14rw4(var2, 3) && !x10bn1mdrsybs8(var1, var2)) {
         for (x15kgoaq6i3m6i var4 : x2n55y2tom37h2.xchl1k1tnh3w8(var0).colonies()) {
            if (!var4.id().equals(var1.id()) && (var4.x2a24huns14rw4(var2, 3) || x10bn1mdrsybs8(var4, var2))) {
               return false;
            }
         }

         for (BlockPos var6 : BlockPos.iterate(var2.add(-1, -2, -1), var2.add(1, 2, 1))) {
            if (!var0.getChunkManager().isChunkLoaded(var6.getX() >> 4, var6.getZ() >> 4)) {
               return false;
            }

            if (xamz6pfj7qa87.x3hqfrterusj2u(var0, var6)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean x10bn1mdrsybs8(x15kgoaq6i3m6i var0, BlockPos var1) {
      return var0.x1n1a2vp01kxzf().stream().anyMatch(var1x -> Math.abs(var1x.getX() - var1.getX()) <= 1 && Math.abs(var1x.getZ() - var1.getZ()) <= 1);
   }
}
