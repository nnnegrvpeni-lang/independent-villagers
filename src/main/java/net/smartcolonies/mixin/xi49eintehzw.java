package net.smartcolonies.mixin;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.FindPointOfInterestTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.poi.PointOfInterestType;
import net.smartcolonies.r.x172amoveu75vj;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FindPointOfInterestTask.class})
public abstract class xi49eintehzw {
   @Inject(
      method = {"method_46883"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void xbr8l0773nvxr(
      Predicate<RegistryEntry<PointOfInterestType>> var0,
      MemoryModuleType<GlobalPos> var1,
      MemoryModuleType<GlobalPos> var2,
      boolean var3,
      Optional<Byte> var4,
      BiPredicate<ServerWorld, BlockPos> var5,
      CallbackInfoReturnable<Task<PathAwareEntity>> var6
   ) {
      if (var1 == MemoryModuleType.JOB_SITE
         || var1 == MemoryModuleType.POTENTIAL_JOB_SITE
         || var2 == MemoryModuleType.JOB_SITE
         || var2 == MemoryModuleType.POTENTIAL_JOB_SITE) {
         var6.setReturnValue(new x172amoveu75vj((Task)var6.getReturnValue()));
      }
   }
}
