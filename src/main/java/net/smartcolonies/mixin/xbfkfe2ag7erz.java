package net.smartcolonies.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.OpenDoorsTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.smartcolonies.r.ColonyTaskWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({OpenDoorsTask.class})
public abstract class xbfkfe2ag7erz {
   @Inject(
      method = {"method_46964"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void x3iliq3cc086c3(CallbackInfoReturnable<Task<LivingEntity>> var0) {
      var0.setReturnValue(new ColonyTaskWrapper((Task)var0.getReturnValue(), true));
   }
}
