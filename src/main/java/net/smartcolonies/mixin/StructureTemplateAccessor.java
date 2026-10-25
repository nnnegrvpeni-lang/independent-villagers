package net.smartcolonies.mixin;

import java.util.List;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.PalettedBlockInfoList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({StructureTemplate.class})
public interface StructureTemplateAccessor {
   @Accessor("field_15586")
   List<PalettedBlockInfoList> xbqthaw3aplko();
}
