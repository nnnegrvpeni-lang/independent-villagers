package net.smartcolonies.mixin;

import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathContext;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.r.x15kgoaq6i3m6i;
import net.smartcolonies.r.x27aao31y6r2ta;
import net.smartcolonies.r.x2n55y2tom37h2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LandPathNodeMaker.class})
public abstract class xul6oxgve55tu extends PathNodeMaker {
   @Inject(
      method = {"method_17"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3dwmoit02qwaq(PathContext var1, int var2, int var3, int var4, CallbackInfoReturnable<PathNodeType> var5) {
      MobEntity var6 = this.entity;
      if (var6 != null && var6.getEntityWorld() instanceof ServerWorld var7) {
         BlockPos var12 = new BlockPos(var2, var3, var4);
         boolean var9 = x27aao31y6r2ta.x3di73z4zkdknf(var7, var12);
         boolean var10 = var6 instanceof VillagerEntity var11
            && var7.getBlockState(var12).getBlock() instanceof FenceGateBlock
            && (x2n55y2tom37h2.x3psqezd5jem54(var7, var11.getBlockPos()).isPresent() || x2n55y2tom37h2.x2yj2v4iudqlvb(var7, var11.getUuid()).isPresent())
            && x2n55y2tom37h2.x3psqezd5jem54(var7, var12).flatMap(x15kgoaq6i3m6i::x329rgb9lpsari).map(var1x -> !var1x.north(5).equals(var12)).orElse(true);
         if ((var6 instanceof VillagerEntity || var6 instanceof IronGolemEntity) && var9 || var10) {
            var5.setReturnValue(PathNodeType.WALKABLE);
         }
      }
   }
}
