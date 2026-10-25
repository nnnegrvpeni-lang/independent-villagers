package net.smartcolonies.mixin;

import net.minecraft.block.entity.Hopper;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.smartcolonies.r.x1pk6h11cwmsba;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({HopperBlockEntity.class})
public abstract class x3q50tg4x7kpfk {
   @Inject(
      method = {"method_11241"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void xkwkf0w6v8o70(World var0, Hopper var1, CallbackInfoReturnable<Boolean> var2) {
      if (var0 instanceof ServerWorld var3) {
         BlockPos var4 = new BlockPos(MathHelper.floor(var1.getHopperX()), MathHelper.floor(var1.getHopperY() + 1.0), MathHelper.floor(var1.getHopperZ()));
         if (x1pk6h11cwmsba.x33zofxkqevoo8(var3, var4)) {
            var2.setReturnValue(false);
         }
      }
   }
}
