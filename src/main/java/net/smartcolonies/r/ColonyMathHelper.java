package net.smartcolonies.r;

import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;

public final class ColonyMathHelper {
   private ColonyMathHelper() {
   }

   public static void x3qgnd0wqcbp6n(VillagerEntity var0) {
      var0.getNavigation().stop();
      var0.getBrain().forget(MemoryModuleType.WALK_TARGET);
      var0.getBrain().doExclusively(Activity.IDLE);
      PlayerEntity var1 = var0.getCustomer();
      if (var1 != null) {
         var0.getLookControl().lookAt(var1, 30.0F, 30.0F);
      }
   }
}
