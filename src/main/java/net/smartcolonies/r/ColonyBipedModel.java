package net.smartcolonies.r;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

final class ColonyBipedModel extends BipedEntityModel<BipedEntityRenderState> {
   ColonyBipedModel(ModelPart var1) {
      super(var1);
   }

   public void setAngles(BipedEntityRenderState var1) {
      super.setAngles(var1);
      this.head.originY = this.hat.originY = -2.0F;
      this.body.zScale = 1.55F;
      this.rightLeg.originX = -2.0F;
      this.leftLeg.originX = 2.0F;
      this.rightLeg.pitch = (float)Math.cos(var1.limbSwingAnimationProgress * 0.6662F) * 0.7F * var1.limbSwingAmplitude;
      this.leftLeg.pitch = (float)Math.cos(var1.limbSwingAnimationProgress * 0.6662F + Math.PI) * 0.7F * var1.limbSwingAmplitude;

      for (ModelPart var5 : new ModelPart[]{this.rightArm, this.leftArm}) {
         var5.originX = 0.0F;
         var5.originY = 3.0F;
         var5.originZ = -1.0F;
         var5.pitch = -0.75F;
         var5.yaw = var5.roll = 0.0F;
      }
   }
}
