package net.smartcolonies.r;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.MultiTickTask.Status;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;

public final class x172amoveu75vj<E extends LivingEntity> implements Task<E> {
   private final Task<E> x1h6xn35v2y5uc;
   private final boolean x1rdpmsfrf8owh;

   public x172amoveu75vj(Task<E> var1) {
      this(var1, false);
   }

   public x172amoveu75vj(Task<E> var1, boolean var2) {
      this.x1h6xn35v2y5uc = var1;
      this.x1rdpmsfrf8owh = var2;
   }

   private boolean x2wppgvkmmnftn(ServerWorld var1, E var2) {
      return var2 instanceof VillagerEntity
         && (
            x2n55y2tom37h2.x2yj2v4iudqlvb(var1, var2.getUuid()).isPresent()
               || x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
                  .filter(var2x -> this.x1rdpmsfrf8owh || var2x.roleOf(var2.getUuid()) != x1rxiy9mtu5b3h.x2j116s1wug0g2)
                  .isPresent()
         );
   }

   public Status getStatus() {
      return this.x1h6xn35v2y5uc.getStatus();
   }

   public String getName() {
      return this.x1h6xn35v2y5uc.getName();
   }

   public boolean tryStarting(ServerWorld world, E entity, long time) {
      return !this.x2wppgvkmmnftn(world, (E)entity) && this.x1h6xn35v2y5uc.tryStarting(world, entity, time);
   }

   public void tick(ServerWorld world, E entity, long time) {
      if (this.x2wppgvkmmnftn(world, (E)entity)) {
         this.x1h6xn35v2y5uc.stop(world, entity, time);
      } else {
         this.x1h6xn35v2y5uc.tick(world, entity, time);
      }
   }

   public void stop(ServerWorld world, E entity, long time) {
      this.x1h6xn35v2y5uc.stop(world, entity, time);
   }
}
