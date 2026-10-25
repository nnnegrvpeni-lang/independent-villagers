package net.smartcolonies.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.TradeOutputSlot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.Merchant;
import net.minecraft.village.MerchantInventory;
import net.minecraft.village.TradeOffer;
import net.smartcolonies.r.ElderTradeService;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({TradeOutputSlot.class})
public abstract class TradeOutputSlotMixin {
   @Shadow
   @Final
   private Merchant field_7858;
   @Shadow
   @Final
   private MerchantInventory field_7860;

   @Inject(
      method = {"method_7671"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void x2g0wc5ylgvp3y(int var1, CallbackInfoReturnable<ItemStack> var2) {
      if (this.field_7858 instanceof VillagerEntity var3 && var3.getEntityWorld() instanceof ServerWorld var4) {
         TradeOffer var7 = this.field_7860.getTradeOffer();
         if (var7 != null && ElderTradeService.xcynwq3uap918(var3, var7) && !ElderTradeService.x1ujd85h9771yr(var4, var3, var7)) {
            var7.disable();
            this.field_7860.updateOffers();
            var2.setReturnValue(ItemStack.EMPTY);
         }
      }
   }
}
