package net.smartcolonies.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.render.entity.state.VillagerEntityRenderState;
import net.smartcolonies.r.xp08rwn075i54;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({VillagerResemblingModel.class})
public abstract class x190c0l05t9fxo {
   @Shadow
   @Final
   private ModelPart field_55117;
   @Shadow
   @Final
   private ModelPart field_27527;
   @Unique
   private ModelPart x2kuxqci27a0xm;

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void xwee2k63fbe(ModelPart var1, CallbackInfo var2) {
      this.x2kuxqci27a0xm = var1.getChild("body");
   }

   @Inject(
      method = {"method_62123"},
      at = {@At("RETURN")}
   )
   private void xu7f9czin7t9e(VillagerEntityRenderState var1, CallbackInfo var2) {
      if (var1 instanceof xp08rwn075i54 var3) {
         this.field_55117.visible = true;
         if (this.field_27527.hasChild("hat")) {
            this.field_27527.getChild("hat").visible = var3.x2wtazye3qgwz3.equippedHeadStack.isEmpty();
         }

         this.x2kuxqci27a0xm.getChild("jacket").visible = var3.x2wtazye3qgwz3.equippedLegsStack.isEmpty();
      }
   }
}
