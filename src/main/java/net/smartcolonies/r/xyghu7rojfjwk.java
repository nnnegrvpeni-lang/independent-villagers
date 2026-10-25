package net.smartcolonies.r;

import java.util.Map;
import java.util.function.BooleanSupplier;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class xyghu7rojfjwk {
   private static final ThreadLocal<Integer> x148uq649zls15 = ThreadLocal.withInitial(() -> 0);

   private xyghu7rojfjwk() {
   }

   public static void x3mcqehepulqri() {
      x148uq649zls15.set(x148uq649zls15.get() + 1);
   }

   public static void xutm7g9geb0c0() {
      int var0 = x148uq649zls15.get() - 1;
      if (var0 <= 0) {
         x148uq649zls15.remove();
      } else {
         x148uq649zls15.set(var0);
      }
   }

   public static boolean x2aebumvi646n9(BooleanSupplier var0) {
      x3mcqehepulqri();

      boolean var1;
      try {
         var1 = var0.getAsBoolean();
      } finally {
         xutm7g9geb0c0();
      }

      return var1;
   }

   public static void x2d89mem7j8r8n(ServerWorld var0, BlockPos var1, BlockState var2, BlockState var3) {
      if (x148uq649zls15.get() <= 0
         && var2 != null
         && var2.getBlock() != var3.getBlock()
         && !var2.isAir()
         && !var2.isReplaceable()
         && (var3.isAir() || var3.isReplaceable())
         && var0.getServer().isOnThread()) {
         x2n55y2tom37h2.x3psqezd5jem54(var0, var1).ifPresent(var3x -> {
            if (!var3x.x1ja61emeln2hc().stream().anyMatch(var1xx -> var1xx.x24kbqv6g4kkrg().contains(var1) && !var1xx.x2s8emavervqrz())) {
               if (x20rghnsl2yowl.x5epxvcc1p5ir(var0, var3x, var1)) {
                  Map var4 = x20rghnsl2yowl.x335dkgs8aizip(var0, var1, var2);
                  if (!var4.isEmpty()) {
                     x20rghnsl2yowl.x17m23t077i8f8(var0, var3x, var4);
                  }
               }
            }
         });
      }
   }
}
