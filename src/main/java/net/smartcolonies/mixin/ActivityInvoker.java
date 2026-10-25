package net.smartcolonies.mixin;

import net.minecraft.entity.ai.brain.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({Activity.class})
public interface ActivityInvoker {
   @Invoker("method_19210")
   static Activity x3arpyl63fzrty(String var0) {
      throw new AssertionError("Activity invoker was not transformed");
   }
}
