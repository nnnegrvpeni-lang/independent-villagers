package net.smartcolonies.mixin;

import net.minecraft.entity.ai.brain.task.VillagerBreedTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.smartcolonies.r.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({VillagerBreedTask.class})
public abstract class VillagerBreedTaskMixin {
   @Inject(
      method = {"method_19571"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void xoe4r49nvirkm(ServerWorld var1, VillagerEntity var2, CallbackInfoReturnable<Boolean> var3) {
      if (!ColonyManager.xis8cbwd2sinx(var1, var2)) {
         var3.setReturnValue(false);
      }
   }
}
