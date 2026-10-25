package net.smartcolonies.r;

import java.util.Comparator;
import java.util.Set;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;

public final class x1fj6t1cfh7db6 extends Sensor<VillagerEntity> {
   public x1fj6t1cfh7db6() {
      super(100);
   }

   protected void sense(ServerWorld var1, VillagerEntity var2) {
      x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
         .ifPresentOrElse(
            var2x -> var2x.x2ad3s8aentmo4()
               .stream()
               .filter(var1xx -> var1.getChunkManager().isChunkLoaded(var1xx.getX() >> 4, var1xx.getZ() >> 4))
               .min(Comparator.comparingDouble(var1xx -> var1xx.getSquaredDistance(var2.getBlockPos())))
               .ifPresentOrElse(
                  var1xx -> var2.getBrain().remember(xxao083dbo7ag.x3o547clcv9kaw, var1xx), () -> var2.getBrain().forget(xxao083dbo7ag.x3o547clcv9kaw)
               ),
            () -> var2.getBrain().forget(xxao083dbo7ag.x3o547clcv9kaw)
         );
   }

   public Set<MemoryModuleType<?>> getOutputMemoryModules() {
      return Set.of(xxao083dbo7ag.x3o547clcv9kaw);
   }
}
