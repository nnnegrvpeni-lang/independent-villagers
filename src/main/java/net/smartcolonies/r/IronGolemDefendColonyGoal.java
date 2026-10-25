package net.smartcolonies.r;

import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.Goal.Control;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.smartcolonies.mixin.MobEntityAccessor;

public final class IronGolemDefendColonyGoal extends Goal {
   private static final Map<AnimalEntity, IronGolemDefendColonyGoal> x2jqgbs3h8y4a3 = new WeakHashMap<>();
   private final WeakReference<AnimalEntity> x14kgczmxh7igi;
   private VillagerEntity x3iilaqce7f5kt;
   private Item x2ez1qd2601876;
   private int xze5msx2a8rgp;

   private IronGolemDefendColonyGoal(AnimalEntity var1) {
      this.x14kgczmxh7igi = new WeakReference<>(var1);
      this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
   }

   public static void follow(AnimalEntity var0, VillagerEntity var1, Item var2) {
      IronGolemDefendColonyGoal var3 = x2jqgbs3h8y4a3.computeIfAbsent(var0, var0x -> {
         IronGolemDefendColonyGoal var1x = new IronGolemDefendColonyGoal(var0x);
         ((MobEntityAccessor)var0x).x26nz0ko7si5a7().add(1, var1x);
         return var1x;
      });
      var3.x3iilaqce7f5kt = var1;
      var3.x2ez1qd2601876 = var2;
   }

   public static void release(AnimalEntity var0) {
      IronGolemDefendColonyGoal var1 = x2jqgbs3h8y4a3.get(var0);
      if (var1 != null) {
         var1.x3iilaqce7f5kt = null;
         var1.x2ez1qd2601876 = null;
      }
   }

   public boolean canStart() {
      AnimalEntity var1 = this.x14kgczmxh7igi.get();
      return var1 != null
         && var1.isAlive()
         && this.x3iilaqce7f5kt != null
         && this.x3iilaqce7f5kt.isAlive()
         && !this.x3iilaqce7f5kt.isSleeping()
         && !this.x3iilaqce7f5kt.hasCustomer()
         && this.x2ez1qd2601876 != null
         && this.x3iilaqce7f5kt.getMainHandStack().isOf(this.x2ez1qd2601876)
         && var1.squaredDistanceTo(this.x3iilaqce7f5kt) <= 144.0;
   }

   public boolean shouldContinue() {
      return this.canStart();
   }

   public void start() {
      this.xze5msx2a8rgp = 0;
   }

   public void tick() {
      AnimalEntity var1 = this.x14kgczmxh7igi.get();
      if (var1 != null && this.x3iilaqce7f5kt != null) {
         var1.getLookControl().lookAt(this.x3iilaqce7f5kt, 30.0F, 30.0F);
         if (var1.squaredDistanceTo(this.x3iilaqce7f5kt) < 2.25) {
            var1.getNavigation().stop();
         } else {
            if (this.xze5msx2a8rgp-- <= 0) {
               this.xze5msx2a8rgp = 5;
               var1.getNavigation().startMovingTo(this.x3iilaqce7f5kt, 1.05);
            }
         }
      }
   }

   public void stop() {
      AnimalEntity var1 = this.x14kgczmxh7igi.get();
      if (var1 != null) {
         var1.getNavigation().stop();
      }
   }
}
