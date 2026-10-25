package net.smartcolonies.mixin;

import java.util.UUID;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ItemEntity.class})
public interface ItemEntityAccessor {
   @Accessor("field_41893")
   UUID x6t8mlwqv273n();
}
