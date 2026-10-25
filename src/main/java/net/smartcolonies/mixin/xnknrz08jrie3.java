package net.smartcolonies.mixin;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({TrapdoorBlock.class})
public interface xnknrz08jrie3 {
   @Accessor("field_42779")
   BlockSetType x27hnusyzwjzma();
}
