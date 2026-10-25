package net.smartcolonies.mixin;

import net.minecraft.entity.ai.brain.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({Activity.class})
public interface x1jxs4i1mk0ei2 {
   @Invoker("method_19210")
   static Activity x3arpyl63fzrty(String var0) {
      throw new AssertionError("Activity invoker was not transformed");
   }
}
