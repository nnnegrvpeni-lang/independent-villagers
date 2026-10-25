package net.smartcolonies.mixin;

import java.util.Optional;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.smartcolonies.r.ColonyMovementPlanner;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.RaidDetectionHelper;
import net.smartcolonies.r.VillageTagHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MobEntity.class})
public abstract class xzbqyf90jzjb5 {
   @Inject(
      method = {"method_5958"},
      at = {@At("TAIL")}
   )
   private void x1z6i51xrrd3ws(ServerWorld var1, CallbackInfo var2) {
      if ((Object)this instanceof IronGolemEntity var3) {
         Optional var4 = ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos());
         if (var4.isPresent() && Math.floorMod(var1.getTime() + var3.getId(), 20) == 0) {
            if (var3.getHealth() < var3.getMaxHealth()) {
               var3.heal(2.0F);
            }

            var3.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 240, 0, false, false), var3);
            var3.setAbsorptionAmount(Math.min(var3.getMaxAbsorption(), var3.getAbsorptionAmount() + 1.0F));
         }

         if (var3.isTouchingWater()) {
            Vec3d var5 = var3.getVelocity();
            Vec3d var6 = var3.getTarget() != null ? var3.getTarget().getEntityPos() : null;
            if (var6 == null) {
               var6 = ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos()).map(var0 -> Vec3d.ofCenter(var0.core())).orElse(var3.getEntityPos());
            }

            Vec3d var7 = var6.subtract(var3.getEntityPos());
            double var8 = Math.sqrt(var7.x * var7.x + var7.z * var7.z);
            double var10 = var8 > 0.01 ? 0.075 : 0.0;
            var3.setSwimming(true);
            var3.setVelocity(var8 > 0.01 ? var7.x / var8 * var10 : var5.x * 0.5, Math.max(var5.y, 0.11), var8 > 0.01 ? var7.z / var8 * var10 : var5.z * 0.5);
         } else if (var3.isSwimming()) {
            var3.setSwimming(false);
         }

         if ((var1.getTime() + var3.getId()) % 4L == 0L) {
            ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos())
               .ifPresent(
                  var2x -> {
                     if (var3.getTarget() != null && !RaidDetectionHelper.xs8awvrrlhug9(var2x, var3.getTarget())) {
                        var3.setTarget(null);
                        var3.getNavigation().stop();
                     }

                     if (var3.getTarget() instanceof VillagerEntity var3x
                        && ColonyManager.x3psqezd5jem54(var1, var3x.getBlockPos()).map(var1xx -> var1xx.id().equals(var2x.id())).orElse(false)) {
                        var3.setTarget(null);
                     }

                     RaidDetectionHelper.x3kaxmxx3tadjj(var1, var2x).ifPresent(var2xx -> {
                        var3.setTarget(var2xx);
                        ColonyMovementPlanner.x1wi30zgg7nf81(var1, var3, var2xx.getBlockPos(), 1.8);
                     });
                     VillageTagHelper.x1qwgvfo0xc7pp(var1, var3, var2x);
                  }
               );
         }
      }
   }
}
