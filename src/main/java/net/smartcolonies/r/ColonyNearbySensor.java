package net.smartcolonies.r;

import java.util.Comparator;
import java.util.Set;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;

public final class ColonyNearbySensor extends Sensor<VillagerEntity> {
   public ColonyNearbySensor() {
      super(100);
   }

   protected void sense(ServerWorld var1, VillagerEntity var2) {
      ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
         .ifPresentOrElse(
            var2x -> var2x.x2ad3s8aentmo4()
               .stream()
               .filter(var1xx -> var1.getChunkManager().isChunkLoaded(var1xx.getX() >> 4, var1xx.getZ() >> 4))
               .min(Comparator.comparingDouble(var1xx -> var1xx.getSquaredDistance(var2.getBlockPos())))
               .ifPresentOrElse(
                  var1xx -> var2.getBrain().remember(ColonyMemoryModules.x3o547clcv9kaw, var1xx), () -> var2.getBrain().forget(ColonyMemoryModules.x3o547clcv9kaw)
               ),
            () -> var2.getBrain().forget(ColonyMemoryModules.x3o547clcv9kaw)
         );
   }

   public Set<MemoryModuleType<?>> getOutputMemoryModules() {
      return Set.of(ColonyMemoryModules.x3o547clcv9kaw);
   }
}
