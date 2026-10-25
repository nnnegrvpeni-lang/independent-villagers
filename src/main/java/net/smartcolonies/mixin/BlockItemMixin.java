package net.smartcolonies.mixin;

import java.util.UUID;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.smartcolonies.r.ColonyTimeService;
import net.smartcolonies.r.ColonyState;
import net.smartcolonies.r.DeedItemHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockItem.class})
public abstract class BlockItemMixin {
   @Inject(
      method = {"method_7708"},
      at = {@At("RETURN")}
   )
   private void x2yggnyutaihex(ItemPlacementContext var1, BlockState var2, CallbackInfoReturnable<Boolean> var3) {
      if ((Boolean)var3.getReturnValue() && var1.getPlayer() != null && var1.getWorld() instanceof ServerWorld var4) {
         ColonyState var8 = ColonyState.get(var4);
         BlockPos var6 = var1.getBlockPos();
         UUID var7 = var1.getPlayer().getUuid();
         DeedItemHandler.x273xo57gqmrz9(var4, var1.getPlayer(), var6, var2);
         var8.recordPlacement(var6, var7, var4.getTime());
         if (var2.getBlock() instanceof DoorBlock) {
            var8.recordPlacement(var6.up(), var7, var4.getTime());
         }

         if (var2.getBlock() instanceof BedBlock) {
            var8.recordPlacement(var6.offset((Direction)var2.get(BedBlock.FACING)), var7, var4.getTime());
         }

         ColonyTimeService.x1se6fg76pktcw(var4, var6);
         ColonyTimeService.x34rm129tews28(var4);
      }
   }
}
