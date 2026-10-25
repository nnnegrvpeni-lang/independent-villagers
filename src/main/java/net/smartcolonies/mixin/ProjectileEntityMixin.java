package net.smartcolonies.mixin;

import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.smartcolonies.r.Colony;
import net.smartcolonies.r.ColonyRole;
import net.smartcolonies.r.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ProjectileEntity.class})
public abstract class ProjectileEntityMixin {
   @Inject(
      method = {"method_7454"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x7nte3afhd17b(EntityHitResult var1, CallbackInfo var2) {
      ProjectileEntity var3 = (ProjectileEntity)(Object)this;
      if (var3.getEntityWorld() instanceof ServerWorld var4 && var3.getOwner() instanceof VillagerEntity var5) {
         Entity var9 = var1.getEntity();
         if (var9 instanceof VillagerEntity || var9 instanceof IronGolemEntity) {
            Optional var7 = ColonyManager.x3psqezd5jem54(var4, var5.getBlockPos());
            if (!var7.isEmpty() && ((Colony)var7.get()).roleOf(var5.getUuid()) == ColonyRole.x3r8p2tvihwpph) {
               if (ColonyManager.x3psqezd5jem54(var4, var9.getBlockPos()).map(var1x -> var1x.id().equals(((Colony)var7.get()).id())).orElse(false)) {
                  var3.discard();
                  var2.cancel();
               }
            }
         }
      }
   }
}
