package net.smartcolonies.mixin;

import java.util.Map;
import net.minecraft.block.BlockState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.r.Colony;
import net.smartcolonies.r.ConstructionStatus;
import net.smartcolonies.r.BuildingBlueprintHelper;
import net.smartcolonies.r.ConstructionJob;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.RaidDetectionHelper;
import net.smartcolonies.r.ColonyState;
import net.smartcolonies.r.ColonyWorldUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ServerPlayerInteractionManager.class})
public abstract class x25n2b6nvj54yr {
   @Shadow
   protected ServerWorld field_14007;
   @Shadow
   protected ServerPlayerEntity field_14008;
   @Unique
   private Colony x1kxtmy73igiaq;
   @Unique
   private ConstructionJob x2z5231e1txc97;
   @Unique
   private boolean xq204v34j0ov;
   @Unique
   private Map<BlockPos, BlockState> x1qw76x55rvpdd = Map.of();

   @Inject(
      method = {"method_14266"},
      at = {@At("HEAD")}
   )
   private void xyxjynin8r80k(BlockPos var1, CallbackInfoReturnable<Boolean> var2) {
      this.x1kxtmy73igiaq = null;
      this.x2z5231e1txc97 = null;
      this.xq204v34j0ov = false;
      this.x1qw76x55rvpdd = Map.of();
      ColonyManager.x3psqezd5jem54(this.field_14007, var1)
         .ifPresent(
            var2x -> {
               if (BuildingBlueprintHelper.x5epxvcc1p5ir(this.field_14007, var2x, var1)) {
                  this.x2z5231e1txc97 = var2x.x1ja61emeln2hc().stream().filter(var1xx -> var1xx.x24kbqv6g4kkrg().contains(var1)).findFirst().orElse(null);
                  this.xq204v34j0ov = this.x2z5231e1txc97 != null
                     && this.x2z5231e1txc97.x2s8emavervqrz()
                     && this.x2z5231e1txc97.x2nfrt8qxdscso().filter(this.field_14008.getUuid()::equals).isPresent();
                  this.x1qw76x55rvpdd = BuildingBlueprintHelper.xg03tmq830uoq(this.field_14007, var1);
                  if (!this.x1qw76x55rvpdd.isEmpty() || this.xq204v34j0ov) {
                     this.x1kxtmy73igiaq = var2x;
                  }
               }
            }
         );
   }

   @Inject(
      method = {"method_14266"},
      at = {@At("RETURN")}
   )
   private void x2dynd4ebzq65(BlockPos var1, CallbackInfoReturnable<Boolean> var2) {
      if ((Boolean)var2.getReturnValue()) {
         ColonyState.get(this.field_14007).forgetPlacement(var1);
         if (this.x1kxtmy73igiaq != null) {
            if (this.xq204v34j0ov) {
               this.x1kxtmy73igiaq.x2xqc3q79vfkw3().remove(var1);
               this.x1qw76x55rvpdd.keySet().forEach(this.x1kxtmy73igiaq.x2xqc3q79vfkw3()::remove);
               this.x2z5231e1txc97.x3lcv8vebl7uhh();
            } else {
               BuildingBlueprintHelper.x17m23t077i8f8(this.field_14007, this.x1kxtmy73igiaq, this.x1qw76x55rvpdd);
               if (this.x2z5231e1txc97 != null && this.x2z5231e1txc97.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6) {
                  this.x2z5231e1txc97.xy78n60m5p9uj(this.field_14007.getTime());
               }

               RaidDetectionHelper.xgnwo32dqwtgi(this.field_14007, this.x1kxtmy73igiaq, this.field_14008, 4);
            }

            ColonyState.get(this.field_14007).markDirty();
         }
      }

      this.x1kxtmy73igiaq = null;
      this.x2z5231e1txc97 = null;
      this.xq204v34j0ov = false;
      this.x1qw76x55rvpdd = Map.of();
   }

   @Redirect(
      method = {"method_14266"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_3218;method_8650(Lnet/minecraft/class_2338;Z)Z"
      )
   )
   private boolean x1ewww0ckc9wc(ServerWorld var1, BlockPos var2, boolean var3) {
      return ColonyWorldUtils.x2aebumvi646n9(() -> var1.removeBlock(var2, var3));
   }
}
