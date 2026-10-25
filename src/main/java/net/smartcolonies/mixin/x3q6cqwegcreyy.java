package net.smartcolonies.mixin;

import net.minecraft.entity.ai.brain.task.LoseJobOnSiteLossTask;
import net.minecraft.entity.ai.brain.task.TakeJobSiteTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.UpdateJobSiteTask;
import net.minecraft.entity.ai.brain.task.VillagerTaskListProvider;
import net.minecraft.entity.ai.brain.task.WorkStationCompetitionTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.smartcolonies.r.x172amoveu75vj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({VillagerTaskListProvider.class})
public abstract class x3q6cqwegcreyy {
   @Redirect(
      method = {"method_19020"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4295;method_47037()Lnet/minecraft/class_7893;"
      )
   )
   private static Task<VillagerEntity> x3aqvzhxibwdub() {
      return new x172amoveu75vj(LoseJobOnSiteLossTask.create());
   }

   @Redirect(
      method = {"method_19020"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4114;method_46887()Lnet/minecraft/class_7893;"
      )
   )
   private static Task<VillagerEntity> x1uqnnxad94n6i() {
      return new x172amoveu75vj(UpdateJobSiteTask.create());
   }

   @Redirect(
      method = {"method_19020"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5327;method_47207(F)Lnet/minecraft/class_7893;"
      )
   )
   private static Task<VillagerEntity> x1yxc4rp30so5v(float var0) {
      return new x172amoveu75vj(TakeJobSiteTask.create(var0));
   }

   @Redirect(
      method = {"method_19020"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5326;method_47006()Lnet/minecraft/class_7893;"
      )
   )
   private static Task<VillagerEntity> x1beck2melv4gr() {
      return new x172amoveu75vj(WorkStationCompetitionTask.create());
   }
}
