package net.smartcolonies.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.smartcolonies.r.xyghu7rojfjwk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({WorldChunk.class})
public abstract class x25vss465c2f88 {
   @Shadow
   @Final
   private World field_12858;

   @Inject(
      method = {"method_12010"},
      at = {@At("RETURN")}
   )
   private void x1f6pfyu5te63k(BlockPos var1, BlockState var2, int var3, CallbackInfoReturnable<BlockState> var4) {
      if (this.field_12858 instanceof ServerWorld var5) {
         xyghu7rojfjwk.x2d89mem7j8r8n(var5, var1, (BlockState)var4.getReturnValue(), var2);
      }
   }
}
