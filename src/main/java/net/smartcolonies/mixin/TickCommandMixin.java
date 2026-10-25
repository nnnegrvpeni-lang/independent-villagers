package net.smartcolonies.mixin;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.server.command.TickCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TickCommand.class)
public abstract class TickCommandMixin {
   @ModifyArg(
      method = "register",
      at = @At(
         value = "INVOKE",
         target = "Lcom/mojang/brigadier/arguments/FloatArgumentType;floatArg(FF)Lcom/mojang/brigadier/arguments/FloatArgumentType;"
      ),
      index = 1
   )
   private static float smartColonies$unlimitMaxTickRate(float originalMax) {
      return 1_000_000_000.0F;
   }
}
