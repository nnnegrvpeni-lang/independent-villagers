package net.smartcolonies.mixin;

import java.util.List;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({LivingEntityRenderer.class})
public interface LivingEntityRendererAccessor {
   @Accessor("field_4738")
   List<FeatureRenderer<?, ?>> x3cnxfasc95h6n();

   @Accessor("field_55298")
   ItemModelManager x4yhpn4qlmpkl();
}
