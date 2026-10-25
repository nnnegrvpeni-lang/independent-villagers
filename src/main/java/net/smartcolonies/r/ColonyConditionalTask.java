package net.smartcolonies.r;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.MultiTickTask.Status;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;

public final class ColonyConditionalTask<E extends LivingEntity> implements Task<E> {
   private final Task<E> x2sm9yj46yo0rq;

   public ColonyConditionalTask(Task<E> var1) {
      this.x2sm9yj46yo0rq = var1;
   }

   private boolean x2mdxtohyy24nk(E var1) {
      return var1 instanceof VillagerEntity var2
         && (
            ColonyNavigationHelper.x37u4pqy3fbps(var2) != null
               || var2.getBrain()
                  .getFirstPossibleNonCoreActivity()
                  .filter(
                     var0 -> var0.equals(ColonyActivities.x12qpuae7zsr5m)
                        || var0.equals(ColonyActivities.x38u24k78sk73y)
                        || var0.equals(ColonyActivities.x2t145qayl7tz3)
                  )
                  .isPresent()
         );
   }

   public Status getStatus() {
      return this.x2sm9yj46yo0rq.getStatus();
   }

   public String getName() {
      return this.x2sm9yj46yo0rq.getName();
   }

   public boolean tryStarting(ServerWorld world, E entity, long time) {
      if (this.x2mdxtohyy24nk((E)entity)) {
         entity.getBrain().forget(MemoryModuleType.WALK_TARGET);
         return false;
      } else {
         return this.x2sm9yj46yo0rq.tryStarting(world, entity, time);
      }
   }

   public void tick(ServerWorld world, E entity, long time) {
      if (this.x2mdxtohyy24nk((E)entity)) {
         this.x2sm9yj46yo0rq.stop(world, entity, time);
      } else {
         this.x2sm9yj46yo0rq.tick(world, entity, time);
      }
   }

   public void stop(ServerWorld world, E entity, long time) {
      this.x2sm9yj46yo0rq.stop(world, entity, time);
   }
}
