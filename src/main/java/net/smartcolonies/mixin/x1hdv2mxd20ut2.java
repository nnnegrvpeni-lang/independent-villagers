package net.smartcolonies.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.r.x1rxiy9mtu5b3h;
import net.smartcolonies.r.x20rghnsl2yowl;
import net.smartcolonies.r.x2fvuqnp9zks2x;
import net.smartcolonies.r.x2n55y2tom37h2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FarmerVillagerTask.class})
public abstract class x1hdv2mxd20ut2 {
   @Shadow
   private BlockPos field_18858;

   private static boolean x4944zlwr77nr(ServerWorld var0, BlockPos var1) {
      return !x20rghnsl2yowl.x239t1m3hfi74d(var0, var1) && !x20rghnsl2yowl.x239t1m3hfi74d(var0, var1.down())
         ? x2n55y2tom37h2.x3psqezd5jem54(var0, var1).map(var2 -> x2fvuqnp9zks2x.x14gj5yhqspcfw(var0, var2, var1.down())).orElse(true)
         : false;
   }

   @Inject(
      method = {"method_20640"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void xrdrvyewtzcau(BlockPos var1, ServerWorld var2, CallbackInfoReturnable<Boolean> var3) {
      if (!x4944zlwr77nr(var2, var1)) {
         var3.setReturnValue(false);
      }
   }

   @Inject(
      method = {"method_19565"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3lj4iilmwwuz9(ServerWorld var1, VillagerEntity var2, long var3, CallbackInfo var5) {
      if (x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos()).map(var1x -> var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x8jydaiei7iry).orElse(false)) {
         var5.cancel();
      } else {
         if (this.field_18858 != null && !x4944zlwr77nr(var1, this.field_18858)) {
            var5.cancel();
         }
      }
   }

   @Inject(
      method = {"method_20394"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void xw9szotgsq06a(ServerWorld var1, VillagerEntity var2, long var3, CallbackInfoReturnable<Boolean> var5) {
      if (this.field_18858 != null && !x4944zlwr77nr(var1, this.field_18858)) {
         var5.setReturnValue(false);
      }
   }

   @Redirect(
      method = {"method_19565"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3218;method_8501(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Z"
      )
   )
   private boolean x2r3nxju7gwxoq(ServerWorld var1, BlockPos var2, BlockState var3) {
      return x4944zlwr77nr(var1, var2) && var1.setBlockState(var2, var3);
   }

   @Redirect(
      method = {"method_19565"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3218;method_8651(Lnet/minecraft/class_2338;ZLnet/minecraft/class_1297;)Z"
      )
   )
   private boolean x1h3bpp1bpzsgy(ServerWorld var1, BlockPos var2, boolean var3, Entity var4) {
      return x4944zlwr77nr(var1, var2) && var1.breakBlock(var2, var3, var4);
   }
}
