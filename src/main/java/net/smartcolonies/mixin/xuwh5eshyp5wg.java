package net.smartcolonies.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FishingBobberEntity.class})
public abstract class xuwh5eshyp5wg extends ProjectileEntity {
   protected xuwh5eshyp5wg(EntityType<? extends ProjectileEntity> var1, World var2) {
      super(var1, var2);
   }

   @Inject(
      method = {"method_5773"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3aj70jh2zxi80(CallbackInfo var1) {
      if (this.getOwner() instanceof VillagerEntity var2) {
         super.tick();
         if (!var2.isAlive()
            || var2.isSleeping()
            || var2.hasCustomer()
            || !var2.getMainHandStack().isOf(Items.FISHING_ROD)
            || this.squaredDistanceTo(var2) > 64.0) {
            this.discard();
         }

         this.setVelocity(0.0, 0.0, 0.0);
         var1.cancel();
      }
   }

   @Inject(
      method = {"method_31471"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3ot4yxde0cp7y(EntitySpawnS2CPacket var1, CallbackInfo var2) {
      Entity var3 = this.getEntityWorld().getEntityById(var1.getEntityData());
      if (var3 instanceof VillagerEntity) {
         super.onSpawnPacket(var1);
         this.setOwner(var3);
         var2.cancel();
      }
   }
}
