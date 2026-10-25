package net.smartcolonies.r;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.VillagerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public final class VillagerEquipmentFeatureRenderer extends FeatureRenderer<VillagerEntityRenderState, VillagerResemblingModel> {
   private final BipedEntityModel<BipedEntityRenderState> xgh13qspwnc6y = new BipedEntityModel(
      TexturedModelData.of(BipedEntityModel.getModelData(Dilation.NONE, 0.0F), 64, 32).createModel()
   );
   private final ArmorFeatureRenderer<BipedEntityRenderState, BipedEntityModel<BipedEntityRenderState>, ColonyBipedModel> x1aondm6sulwto;
   private final VillagerHeldItemFeatureRenderer xbte8c97h0kev;

   public VillagerEquipmentFeatureRenderer(FeatureRendererContext<VillagerEntityRenderState, VillagerResemblingModel> var1, Context var2) {
      super(var1);
      FeatureRendererContext var3 = () -> this.xgh13qspwnc6y;
      EquipmentModelData var4 = BipedEntityModel.createEquipmentModelData(new Dilation(1.0F), new Dilation(0.5F)).map(var0 -> {
         ModelPartData var1x = var0.getRoot();
         ModelTransform var2x = ModelTransform.of(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F);
         Dilation var3x = new Dilation(0.35F);
         var1x.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, var3x), var2x);
         var1x.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, var3x), var2x);
         return new ColonyBipedModel(TexturedModelData.of(var0, 64, 32).createModel());
      });
      this.x1aondm6sulwto = new ArmorFeatureRenderer(var3, var4, var2.getEquipmentRenderer());
      this.xbte8c97h0kev = new VillagerHeldItemFeatureRenderer(var3);
   }

   public void render(MatrixStack var1, OrderedRenderCommandQueue var2, int var3, VillagerEntityRenderState var4, float var5, float var6) {
      if (var4 instanceof ColonyVillagerRenderState var7 && !var4.invisible) {
         BipedEntityRenderState var8 = var7.x2wtazye3qgwz3;
         this.xgh13qspwnc6y.setAngles(var8);
         this.x1aondm6sulwto.render(var1, var2, var3, var8, var5, var6);
         this.xbte8c97h0kev.render(var1, var2, var3, var8, var5, var6);
      }
   }
}
