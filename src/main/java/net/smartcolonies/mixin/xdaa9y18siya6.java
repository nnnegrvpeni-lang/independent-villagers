package net.smartcolonies.mixin;

import net.minecraft.server.MinecraftServer;
import net.smartcolonies.r.ColonyManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftServer.class})
public abstract class xdaa9y18siya6 {
   @Inject(
      method = {"method_3782"},
      at = {@At("TAIL")}
   )
   private void x526p2s6u4vlw(CallbackInfo var1) {
      ColonyManager.x35h05qb6ebno5();
   }
}
