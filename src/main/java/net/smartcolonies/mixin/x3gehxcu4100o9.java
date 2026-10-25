package net.smartcolonies.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.smartcolonies.r.x2n55y2tom37h2;
import net.smartcolonies.r.x313o9woxa22fw;
import net.smartcolonies.r.x35kkrl6uz1e8h;
import net.smartcolonies.r.x37idyr79ed75m;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntity.class})
public abstract class x3gehxcu4100o9 {
   @Inject(
      method = {"method_6078"},
      at = {@At("HEAD")}
   )
   private void x1gg6fo9ceuak1(DamageSource var1, CallbackInfo var2) {
      if ((Object)this instanceof VillagerEntity var3 && var3.getEntityWorld() instanceof ServerWorld var4) {
         x313o9woxa22fw.xw6o9aon4s40m(var4, var3);
      }
   }

   @Inject(
      method = {"method_64397"},
      at = {@At("RETURN")}
   )
   private void xhs2l1aufu5ek(ServerWorld var1, DamageSource var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if ((Boolean)var4.getReturnValue() && var3 > 0.0F && (Object)this instanceof VillagerEntity var5 && var2.getAttacker() instanceof LivingEntity var6) {
         x2n55y2tom37h2.x3psqezd5jem54(var1, var5.getBlockPos()).ifPresent(var2x -> x37idyr79ed75m.x9xz66k4m5ee9(var1, var2x, var6));
      }
   }

   @Inject(
      method = {"method_64397"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x1i7i9y2zs5yd0(ServerWorld var1, DamageSource var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if ((Object)this instanceof VillagerEntity var5) {
         boolean var6 = var2.isOf(DamageTypes.IN_WALL);
         boolean var7 = var2.isIn(DamageTypeTags.IS_FALL) && var3 >= var5.getHealth();
         if (var6 || var7) {
            x2n55y2tom37h2.x3psqezd5jem54(var1, var5.getBlockPos()).ifPresent(var3x -> {
               if (x35kkrl6uz1e8h.xdo71dbv16zut(var1, var3x, var5)) {
                  var4.setReturnValue(false);
               }
            });
         }
      }
   }
}
