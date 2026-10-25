package net.smartcolonies.r;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.PalettedBlockInfoList;
import net.minecraft.structure.StructureTemplate.StructureBlockInfo;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.smartcolonies.mixin.x2gac63vevkbv3;

public final class StructureBlockScanner {
   private StructureBlockScanner() {
   }

   public static int xc4tegq500os8(ServerWorld var0, Colony var1) {
      int var2 = var1.x2xqc3q79vfkw3().size();
      StructureStart var3 = var0.getStructureAccessor().getStructureContaining(var1.core(), StructureTags.VILLAGE);
      if (var3 != StructureStart.DEFAULT) {
         for (StructurePiece var5 : var3.getChildren()) {
            if (var5 instanceof PoolStructurePiece var6 && var6.getPoolElement() instanceof SinglePoolElement var7) {
               Identifier var15;
               try {
                  var15 = var7.getIdOrThrow();
               } catch (IllegalStateException var10) {
                  continue;
               }

               String var9 = var15.getPath();
               if (var9.contains("/houses/") && !var9.contains("farm") && !var9.contains("pen") && !var9.contains("animal")) {
                  x25dbr9jxsajgt(var0, var1, var15, var6.getPos(), var6.getRotation(), null);
               }
            }
         }
      }

      for (ConstructionJob var12 : var1.x1ja61emeln2hc()) {
         if (var12.x2s8emavervqrz() && !var12.x2nfrt8qxdscso().isPresent()) {
            StructureTemplate var13 = (StructureTemplate)var0.getStructureTemplateManager().getTemplate(var12.x3kqe1qjsw9oty()).orElse(null);
            if (var13 != null) {
               Vec3i var14 = var13.getSize();
               BlockBox var16 = var12.x24kbqv6g4kkrg();
               if (var14.getX() == var16.getBlockCountX() && var14.getY() == var16.getBlockCountY() && var14.getZ() == var16.getBlockCountZ()) {
                  x25dbr9jxsajgt(var0, var1, var12.x3kqe1qjsw9oty(), var12.x3ij6gescvakv2(), BlockRotation.NONE, var16);
               }
            }
         }
      }

      return var1.x2xqc3q79vfkw3().size() - var2;
   }

   private static void x25dbr9jxsajgt(ServerWorld var0, Colony var1, Identifier var2, BlockPos var3, BlockRotation var4, BlockBox var5) {
      StructureTemplate var6 = (StructureTemplate)var0.getStructureTemplateManager().getTemplate(var2).orElse(null);
      if (var6 != null) {
         StructurePlacementData var7 = new StructurePlacementData().setRotation(var4);
         List var8 = ((x2gac63vevkbv3)var6).xbqthaw3aplko();
         if (!var8.isEmpty()) {
            for (StructureBlockInfo var10 : ((PalettedBlockInfoList)var8.getFirst()).getAll()) {
               BlockState var11 = var10.state().rotate(var4);
               BlockPos var12 = StructureTemplate.transform(var7, var10.pos()).add(var3);
               if ((var5 == null || var5.contains(var12))
                  && var0.getChunkManager().isChunkLoaded(var12.getX() >> 4, var12.getZ() >> 4)
                  && !var11.isAir()
                  && !var11.isReplaceable()
                  && !var11.isOf(Blocks.JIGSAW)
                  && !var11.isOf(Blocks.STRUCTURE_BLOCK)
                  && !var11.isOf(Blocks.DIRT_PATH)
                  && !var11.isOf(Blocks.FARMLAND)
                  && !ColonyTimeService.x3gqyq7yie9jxr(var0, var12)
                  && !ColonyState.get(var0).isPlayerBlock(var12)) {
                  BlockState var13 = var0.getBlockState(var12);
                  if (var13.getBlock() != var11.getBlock()
                     && (var13.isAir() || var13.isReplaceable())
                     && !var1.x1ja61emeln2hc().stream().anyMatch(var1x -> var1x.x24kbqv6g4kkrg().contains(var12) && !var1x.x2s8emavervqrz())) {
                     BuildingBlueprintHelper.x17m23t077i8f8(var0, var1, BuildingBlueprintHelper.x335dkgs8aizip(var0, var12, var11));
                  }
               }
            }
         }
      }
   }
}
