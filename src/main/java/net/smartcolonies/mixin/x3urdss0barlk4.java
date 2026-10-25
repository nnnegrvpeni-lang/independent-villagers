package net.smartcolonies.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.StemBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.util.math.random.Random;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x2fvuqnp9zks2x;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x3d5vk1zj3fvkj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({StemBlock.class})
public abstract class x3urdss0barlk4 {
   @Inject(
      method = {"method_9514"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x2lk3osvzflfm7(BlockState var1, ServerWorld var2, BlockPos var3, Random var4, CallbackInfo var5) {
      if (!x3d5vk1zj3fvkj.get(var2).isPlayerBlock(var3)) {
         x2n55y2tom37h2.x3psqezd5jem54(var2, var3).ifPresent(var3x -> {
            if (!x2fvuqnp9zks2x.x14gj5yhqspcfw(var2, var3x, var3.down()) || xtwcee6bxkzkm(var2, var3).isEmpty()) {
               var5.cancel();
            }
         });
      }
   }

   @Redirect(
      method = {"method_9514"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2350$class_2353;method_10183(Lnet/minecraft/class_5819;)Lnet/minecraft/class_2350;"
      )
   )
   private Direction x3fxui0ux025wm(Type var1, Random var2, BlockState var3, ServerWorld var4, BlockPos var5, Random var6) {
      if (!x3d5vk1zj3fvkj.get(var4).isPlayerBlock(var5) && !x2n55y2tom37h2.x3psqezd5jem54(var4, var5).isEmpty()) {
         List var7 = xtwcee6bxkzkm(var4, var5);
         return var7.isEmpty() ? var1.random(var2) : (Direction)var7.get(var2.nextInt(var7.size()));
      } else {
         return var1.random(var2);
      }
   }

   @Unique
   private static List<Direction> xtwcee6bxkzkm(ServerWorld var0, BlockPos var1) {
      Optional var2 = x2n55y2tom37h2.x3psqezd5jem54(var0, var1);
      ArrayList var3 = new ArrayList();

      for (Direction var5 : Type.HORIZONTAL) {
         if (var2.isEmpty() || x2fvuqnp9zks2x.x14gj5yhqspcfw(var0, (x15kgoaq6i3m6i)var2.get(), var1.down().offset(var5))) {
            var3.add(var5);
         }
      }

      return var3;
   }
}
