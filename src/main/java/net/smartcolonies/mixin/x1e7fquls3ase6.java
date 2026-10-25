package net.smartcolonies.mixin;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.VillagerEntityRenderState;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.smartcolonies.r.x39nmg4rb9ffet;
import net.smartcolonies.r.xp08rwn075i54;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({VillagerEntityRenderer.class})
public abstract class x1e7fquls3ase6 {
   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void x76bxvkm1m8st(Context var1, CallbackInfo var2) {
      ((x1nggbfu6gda51)this).x3cnxfasc95h6n().add(new x39nmg4rb9ffet((FeatureRendererContext<VillagerEntityRenderState, VillagerResemblingModel>)this, var1));
   }

   @Inject(
      method = {"method_62568"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void xm83jgieheh8q(CallbackInfoReturnable<VillagerEntityRenderState> var1) {
      var1.setReturnValue(new xp08rwn075i54());
   }

   @Inject(
      method = {"method_62567"},
      at = {@At("RETURN")}
   )
   private void x2tfjfukp7hems(VillagerEntity var1, VillagerEntityRenderState var2, float var3, CallbackInfo var4) {
      if (var2 instanceof xp08rwn075i54 var5) {
         BipedEntityRenderState var6 = var5.x2wtazye3qgwz3;
         BipedEntityRenderer.updateBipedRenderState(var1, var6, var3, ((x1nggbfu6gda51)this).x4yhpn4qlmpkl());
         var6.age = var2.age;
         var6.baby = var2.baby;
         var6.pose = var2.pose;
         var6.relativeHeadYaw = var2.relativeHeadYaw;
         var6.pitch = var2.pitch;
         var6.bodyYaw = var2.bodyYaw;
         var6.limbSwingAnimationProgress = var2.limbSwingAnimationProgress;
         var6.limbSwingAmplitude = var2.limbSwingAmplitude;
         var6.baseScale = var2.baseScale;
         var6.ageScale = var2.ageScale;
         var6.hurt = var2.hurt;
         var6.invisible = var2.invisible;
         var6.outlineColor = var2.outlineColor;
         var6.rightArmPose = var6.rightHandItem.isEmpty() ? ArmPose.EMPTY : ArmPose.ITEM;
         var6.leftArmPose = var6.leftHandItem.isEmpty() ? ArmPose.EMPTY : ArmPose.ITEM;
         if (var1.isUsingItem() && var1.getActiveItem().isOf(Items.SHIELD)) {
            Arm var7 = var1.getActiveHand() == Hand.MAIN_HAND ? var1.getMainArm() : var1.getMainArm().getOpposite();
            if (var7 == Arm.LEFT) {
               var6.leftArmPose = ArmPose.BLOCK;
            } else {
               var6.rightArmPose = ArmPose.BLOCK;
            }
         }

         if (var1.isUsingItem() && var1.getActiveItem().isOf(Items.BOW)) {
            if (var1.getMainArm() == Arm.RIGHT) {
               var6.rightArmPose = ArmPose.BOW_AND_ARROW;
            } else {
               var6.leftArmPose = ArmPose.BOW_AND_ARROW;
            }
         }
      }
   }
}
