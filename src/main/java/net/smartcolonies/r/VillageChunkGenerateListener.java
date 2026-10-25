package net.smartcolonies.r;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.NbtReadView;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.StructureBlockInfo;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import net.smartcolonies.mixin.x2gac63vevkbv3;

public final class VillageChunkGenerateListener {
   private static final Map<ServerWorld, Set<ChunkPos>> xp4ojrpa6zqhd = new WeakHashMap<>();

   private VillageChunkGenerateListener() {
   }

   public static void x2yhjdk5vgl5bq(ServerWorld var0, WorldChunk var1) {
      if (!var1.getStructureReferences().isEmpty()) {
         xp4ojrpa6zqhd.computeIfAbsent(var0, var0x -> new LinkedHashSet<>()).add(var1.getPos());
      }
   }

   public static void x1f4i4km49zkmu(ServerWorld var0) {
      Set<ChunkPos> var1 = xp4ojrpa6zqhd.remove(var0);
      if (var1 != null) {
         for (ChunkPos var3 : var1) {
            if (var0.getChunkManager().isChunkLoaded(var3.x, var3.z)) {
               BlockBox var4 = new BlockBox(var3.getStartX(), var0.getBottomY(), var3.getStartZ(), var3.getEndX(), var0.getTopYInclusive(), var3.getEndZ());

               label105:
               for (StructureStart var6 : var0.getStructureAccessor().getStructureStarts(var3, var0x -> true)) {
                  Iterator var7 = var6.getChildren().iterator();

                  while (true) {
                     Identifier var21 = null;
                     PoolStructurePiece var9 = null;
                     while (true) {
                        if (!var7.hasNext()) {
                           continue label105;
                        }

                        StructurePiece var8 = (StructurePiece)var7.next();
                        if (var8 instanceof PoolStructurePiece var9x
                           && var9x.getBoundingBox().intersects(var4)
                           && var9x.getPoolElement() instanceof SinglePoolElement var10) {
                           try {
                              var21 = var10.getIdOrThrow();
                              var9 = var9x;
                              break;
                           } catch (IllegalStateException var20) {
                           }
                        }
                     }

                     if (var21 != null
                        && var21.getNamespace().equals("smart_colonies")
                        && var21.getPath().startsWith("village/")
                        && var21.getPath().contains("/houses/")
                        && !var21.getPath().contains("/infrastructure/")) {
                        StructureTemplate var12 = (StructureTemplate)var0.getStructureTemplateManager().getTemplate(var21).orElseThrow();
                        StructurePlacementData var13 = new StructurePlacementData().setRotation(var9.getRotation());

                        for (StructureBlockInfo var16 : ((x2gac63vevkbv3)var12).xbqthaw3aplko().getFirst().getAll()) {
                           if (!var16.state().isOf(Blocks.JIGSAW) && !var16.state().isOf(Blocks.STRUCTURE_BLOCK)) {
                              BlockPos var17 = StructureTemplate.transform(var13, var16.pos()).add(var9.getPos());
                              if (var4.contains(var17)) {
                                 BlockState var18 = var16.state().rotate(var9.getRotation());
                                 if (var0.getBlockState(var17).getBlock() != var18.getBlock()) {
                                    ColonyWorldUtils.x2aebumvi646n9(() -> var0.setBlockState(var17, var18, 18));
                                    BlockEntity var19 = var0.getBlockEntity(var17);
                                    if (var19 != null && var16.nbt() != null) {
                                       var19.readComponentlessData(NbtReadView.create(ErrorReporter.EMPTY, var0.getRegistryManager(), var16.nbt()));
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
