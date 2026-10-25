package net.smartcolonies.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({VillagerEntity.class})
public interface VillagerEntityAccessor {
   @Invoker("method_19191")
   void xj1jj9iwsa6et(PlayerEntity var1);
}
