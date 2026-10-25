package net.smartcolonies.r;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;

final class VillagerHeldItemFeatureRenderer extends HeldItemFeatureRenderer<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>> {
   VillagerHeldItemFeatureRenderer(FeatureRendererContext<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>> var1) {
      super(var1);
   }

   public void render(MatrixStack var1, OrderedRenderCommandQueue var2, int var3, BipedEntityRenderState var4, float var5, float var6) {
      Arm var7 = var4.mainArm == Arm.RIGHT ? Arm.LEFT : Arm.RIGHT;
      this.renderItem(var4, var7 == Arm.LEFT ? var4.leftHandItemState : var4.rightHandItemState, var4.getItemStackForArm(var7), var7, var1, var2, var3);
   }
}
