package net.smartcolonies.r;

import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

public final class VillageChunkLoadListener {
   private VillageChunkLoadListener() {
   }

   public static void xuo3itsmzrpvl(ServerWorld var0, WorldChunk var1) {
      ChunkSection[] var2 = var1.getSectionArray();
      Mutable var3 = new Mutable();
      int var4 = var1.getPos().getStartX();
      int var5 = var1.getPos().getStartZ();
      int var6 = var1.getBottomY();

      for (int var7 = 0; var7 < var2.length; var7++) {
         ChunkSection var8 = var2[var7];
         if (!var8.isEmpty() && var8.hasAny(var0x -> var0x.isOf(Blocks.BELL))) {
            int var9 = var6 + var7 * 16;

            for (int var10 = 0; var10 < 16; var10++) {
               for (int var11 = 0; var11 < 16; var11++) {
                  for (int var12 = 0; var12 < 16; var12++) {
                     if (var8.getBlockState(var12, var10, var11).isOf(Blocks.BELL)) {
                        var3.set(var4 + var12, var9 + var10, var5 + var11);
                        x18a1web1ghx4e(var0, var3.toImmutable());
                     }
                  }
               }
            }
         }
      }
   }

   private static void x18a1web1ghx4e(ServerWorld var0, BlockPos var1) {
      StructureStart var2 = var0.getStructureAccessor().getStructureContaining(var1, StructureTags.VILLAGE);
      if (var2 != StructureStart.DEFAULT && var2.hasChildren()) {
         if (var0.getStructureAccessor().structureContains(var1, var2)) {
            ColonyManager.x3ermfoq0kwcv0(var0, var1, var2.getBoundingBox());
         }
      }
   }
}
