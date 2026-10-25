package net.smartcolonies.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.render.entity.state.FishingBobberEntityState;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FishingBobberEntityRenderer.class})
public abstract class FishingBobberEntityRendererMixin {
   @Inject(
      method = {"method_62442"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void x2lnx8tf9xctws(FishingBobberEntity var1, Frustum var2, double var3, double var5, double var7, CallbackInfoReturnable<Boolean> var9) {
      if (var1.getOwner() instanceof VillagerEntity) {
         var9.setReturnValue(var1.shouldRender(var1.squaredDistanceTo(var3, var5, var7)) && var2.isVisible(var1.getBoundingBox().expand(1.0)));
      }
   }

   @Inject(
      method = {"method_62443"},
      at = {@At("RETURN")}
   )
   private void x19hsepigvngv1(FishingBobberEntity var1, FishingBobberEntityState var2, float var3, CallbackInfo var4) {
      if (var1.getOwner() instanceof VillagerEntity var5) {
         float var8 = MathHelper.lerpAngleDegrees(var3, var5.lastBodyYaw, var5.bodyYaw) * (float) (Math.PI / 180.0);
         Vec3d var7 = var5.getLerpedPos(var3).add(new Vec3d(-0.25, 1.15, 0.45).rotateY(-var8));
         var2.pos = var7.subtract(var1.getLerpedPos(var3).add(0.0, 0.25, 0.0));
      }
   }
}
