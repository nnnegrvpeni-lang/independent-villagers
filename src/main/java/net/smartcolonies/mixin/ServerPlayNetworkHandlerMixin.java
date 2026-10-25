package net.smartcolonies.mixin;

import java.util.Map;
import java.util.UUID;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.smartcolonies.r.Colony;
import net.smartcolonies.r.ColonyRaycastHelper;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.ItemWrapper;
import net.smartcolonies.r.RaidDetectionHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerPlayNetworkHandler.class})
public abstract class ServerPlayNetworkHandlerMixin {
   @Shadow
   public ServerPlayerEntity field_14140;
   @Unique
   private UUID x3a415rzf5zerx;
   @Unique
   private Map<ItemWrapper, Integer> xw2sp1abe7j8z;
   @Unique
   private Inventory x1q6yox0rpcgbf;

   @Inject(
      method = {"method_12076"},
      at = {@At("HEAD")}
   )
   private void x1hy1igt7xg7w1(ClickSlotC2SPacket var1, CallbackInfo var2) {
      ServerWorld var4 = this.field_14140.getEntityWorld();
      if (var4 instanceof ServerWorld && var4.getServer().isOnThread()) {
         this.x3a415rzf5zerx = null;
         this.xw2sp1abe7j8z = null;
         this.x1q6yox0rpcgbf = null;
         if (this.field_14140.currentScreenHandler instanceof GenericContainerScreenHandler var6) {
            ColonyManager.xchl1k1tnh3w8(var4)
               .colonies()
               .stream()
               .filter(var2x -> ColonyRaycastHelper.xu27zvtjfruut(var4, var2x, var6.getInventory()))
               .findFirst()
               .ifPresent(var2x -> {
                  this.x3a415rzf5zerx = var2x.id();
                  this.x1q6yox0rpcgbf = var6.getInventory();
                  this.xw2sp1abe7j8z = ColonyRaycastHelper.x2l7fon36lgwu2(this.x1q6yox0rpcgbf);
               });
         }
      }
   }

   @Inject(
      method = {"method_12076"},
      at = {@At("RETURN")}
   )
   private void x1wv6ie5tgrumw(ClickSlotC2SPacket var1, CallbackInfo var2) {
      if (this.field_14140.getEntityWorld().getServer().isOnThread()) {
         if (this.x3a415rzf5zerx != null && this.xw2sp1abe7j8z != null) {
            ServerWorld var4 = this.field_14140.getEntityWorld();
            if (var4 instanceof ServerWorld) {
               Colony var6 = ColonyManager.xchl1k1tnh3w8(var4).byId(this.x3a415rzf5zerx).orElse(null);
               if (var6 != null) {
                  int var5 = ColonyRaycastHelper.x2qxayij05nw0s(this.xw2sp1abe7j8z, ColonyRaycastHelper.x2l7fon36lgwu2(this.x1q6yox0rpcgbf));
                  if (var5 > 0) {
                     RaidDetectionHelper.xgnwo32dqwtgi(var4, var6, this.field_14140, var5);
                  }
               }

               this.x3a415rzf5zerx = null;
               this.xw2sp1abe7j8z = null;
               this.x1q6yox0rpcgbf = null;
               return;
            }
         }
      }
   }
}
