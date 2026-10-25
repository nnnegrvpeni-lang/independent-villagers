package net.smartcolonies.mixin;

import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({MobEntity.class})
public interface MobEntityAccessor {
   @Accessor("field_6201")
   GoalSelector x26nz0ko7si5a7();
}
