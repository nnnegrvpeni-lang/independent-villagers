package net.smartcolonies.mixin;

import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.smartcolonies.r.Colony;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.ColonyItemUtils;
import net.smartcolonies.r.RaidDetectionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({IronGolemEntity.class})
public abstract class x2rttsqiru166d {
   @Unique
   private Entity x2vmufm3ulqp0w;

   @ModifyArg(
      method = {"method_6121"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1297;method_64397(Lnet/minecraft/class_3218;Lnet/minecraft/class_1282;F)Z"
      ),
      index = 1
   )
   private DamageSource xjl73cpm54dpl(DamageSource var1) {
      IronGolemEntity var2 = (IronGolemEntity)(Object)this;
      return ColonyItemUtils.x3fmeiovmxy173((ServerWorld)var2.getEntityWorld(), var2, this.x2vmufm3ulqp0w, var1);
   }

   @Inject(
      method = {"method_64397"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x3efh7nyp5hqn(ServerWorld var1, DamageSource var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      if (var2.getAttacker() instanceof VillagerEntity var5 && this.sameColony(var1, var5)) {
         var4.setReturnValue(false);
      }
   }

   @Inject(
      method = {"method_64397"},
      at = {@At("RETURN")}
   )
   private void x2vf567eyy1di4(ServerWorld var1, DamageSource var2, float var3, CallbackInfoReturnable<Boolean> var4) {
      IronGolemEntity var5 = (IronGolemEntity)(Object)this;
      if ((Boolean)var4.getReturnValue() && var2.getAttacker() instanceof LivingEntity var6 && RaidDetectionHelper.x1bj0phudo3a5m(var6)) {
         ColonyManager.x3psqezd5jem54(var1, var5.getBlockPos()).ifPresent(var2x -> RaidDetectionHelper.x9xz66k4m5ee9(var1, var2x, var6));
      }
   }

   @Inject(
      method = {"method_6121"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x13do1yixlj4ue(ServerWorld var1, Entity var2, CallbackInfoReturnable<Boolean> var3) {
      this.x2vmufm3ulqp0w = var2;
      if (var2 instanceof VillagerEntity var4 && this.sameColony(var1, var4)) {
         ((IronGolemEntity)(Object)this).setTarget(null);
         var3.setReturnValue(false);
      }
   }

   @Inject(
      method = {"method_6121"},
      at = {@At("RETURN")}
   )
   private void x4yad5k3h1d3f(ServerWorld var1, Entity var2, CallbackInfoReturnable<Boolean> var3) {
      IronGolemEntity var4 = (IronGolemEntity)(Object)this;
      if ((Boolean)var3.getReturnValue() && var2 instanceof PlayerEntity var5 && ColonyManager.x3psqezd5jem54(var1, var4.getBlockPos()).isPresent()) {
         var5.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0), var4);
      }

      this.x2vmufm3ulqp0w = null;
   }

   private boolean sameColony(ServerWorld var1, VillagerEntity var2) {
      IronGolemEntity var3 = (IronGolemEntity)(Object)this;
      Optional var4 = ColonyManager.x3psqezd5jem54(var1, var3.getBlockPos());
      Optional var5 = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos());
      return var4.isPresent() && var5.isPresent() && ((Colony)var4.get()).id().equals(((Colony)var5.get()).id());
   }
}
