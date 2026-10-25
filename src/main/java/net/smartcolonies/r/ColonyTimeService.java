package net.smartcolonies.r;

import java.util.HashSet;
import java.util.UUID;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;

public final class ColonyTimeService {
   private ColonyTimeService() {
   }

   public static boolean x3gqyq7yie9jxr(ServerWorld var0, BlockPos var1) {
      ColonyState var2 = ColonyState.get(var0);
      return var2.land().stream().anyMatch(var1x -> var1x.contains(var1))
         || var2.colonies()
            .stream()
            .anyMatch(
               var1x -> var1x.x1ja61emeln2hc().stream().anyMatch(var1xx -> var1xx.x2nfrt8qxdscso().isPresent() && var1xx.x24kbqv6g4kkrg().contains(var1))
            );
   }

   public static boolean xlzfxuhnckux8(ServerWorld var0, UUID var1, BlockPos var2) {
      ColonyState var3 = ColonyState.get(var0);
      return var3.land().stream().anyMatch(var2x -> var2x.xetxwd9l2gcx6().equals(var1) && var2x.contains(var2))
         || var3.colonies()
            .stream()
            .anyMatch(
               var2x -> var2x.x1ja61emeln2hc()
                  .stream()
                  .anyMatch(
                     var2xx -> var2xx.x2s8emavervqrz() && var2xx.x2nfrt8qxdscso().filter(var1::equals).isPresent() && var2xx.x24kbqv6g4kkrg().contains(var2)
                  )
            );
   }

   private static boolean xwhfuj6t8luq3(ServerWorld var0, BlockPos var1) {
      return ColonyState.get(var0).isPlayerBlock(var1) || x3gqyq7yie9jxr(var0, var1);
   }

   public static boolean xic1z39wiy3pn(ServerWorld var0, BlockPos var1) {
      if (xwhfuj6t8luq3(var0, var1)) {
         return true;
      } else if (ColonyState.get(var0).colonies().stream().anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var1))) {
         return false;
      } else if (!var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)) {
         return false;
      } else {
         WorldChunk var2 = var0.getChunkManager().getWorldChunk(var1.getX() >> 4, var1.getZ() >> 4);
         if (var2 == null) {
            return false;
         } else {
            BlockState var3 = var2.getBlockState(var1);
            if (var3.isOf(Blocks.CHEST) && var3.get(ChestBlock.CHEST_TYPE) != ChestType.SINGLE) {
               BlockPos var4 = var1.offset(ChestBlock.getFacing(var3));
               if (!var0.getChunkManager().isChunkLoaded(var4.getX() >> 4, var4.getZ() >> 4)) {
                  return false;
               } else {
                  WorldChunk var5 = var0.getChunkManager().getWorldChunk(var4.getX() >> 4, var4.getZ() >> 4);
                  return var5 != null && var5.getBlockState(var4).isOf(Blocks.CHEST) && xwhfuj6t8luq3(var0, var4);
               }
            } else {
               return false;
            }
         }
      }
   }

   public static void x1se6fg76pktcw(ServerWorld var0, BlockPos var1) {
      WorldChunk var2 = var0.getChunkManager().getWorldChunk(var1.getX() >> 4, var1.getZ() >> 4);
      if (var2 != null) {
         BlockState var3 = var2.getBlockState(var1);
         if (var3.isOf(Blocks.CHEST) && var3.get(ChestBlock.CHEST_TYPE) != ChestType.SINGLE) {
            BlockPos var4 = var1.offset(ChestBlock.getFacing(var3));
            WorldChunk var5 = var0.getChunkManager().getWorldChunk(var4.getX() >> 4, var4.getZ() >> 4);
            if (var5 != null && var5.getBlockState(var4).isOf(Blocks.CHEST) && !xwhfuj6t8luq3(var0, var4)) {
               boolean var6 = ColonyState.get(var0)
                  .colonies()
                  .stream()
                  .anyMatch(var1x -> var1x.x29j0o9zdxri2().containsKey(var4) || var1x.x2ad3s8aentmo4().contains(var4));
               if (xwhfuj6t8luq3(var0, var1) && var6) {
                  byte var7 = 18;
                  var0.setBlockState(var1, (BlockState)var3.with(ChestBlock.CHEST_TYPE, ChestType.SINGLE), var7);
                  var0.setBlockState(var4, (BlockState)var0.getBlockState(var4).with(ChestBlock.CHEST_TYPE, ChestType.SINGLE), var7);
               }
            }
         }
      }
   }

   public static void x34rm129tews28(ServerWorld var0) {
      ColonyState var1 = ColonyState.get(var0);
      boolean var2 = false;

      for (Colony var4 : var1.colonies()) {
         boolean var5 = false;
         var2 |= var4.x2xqc3q79vfkw3().keySet().removeIf(var1x -> var1.land().stream().anyMatch(var1xx -> var1xx.contains(var1x)));
         HashSet<BlockPos> var6 = new HashSet<>(var4.x2ad3s8aentmo4());
         var6.addAll(var4.x29j0o9zdxri2().keySet());

         for (BlockPos var8 : var6) {
            if (var0.getChunkManager().isChunkLoaded(var8.getX() >> 4, var8.getZ() >> 4) && xic1z39wiy3pn(var0, var8)) {
               var4.x3qhye0v48751k(var8);
               var2 = true;
               var5 = true;
            }
         }

         for (BlockPos var12 : var6) {
            if (var0.getChunkManager().isChunkLoaded(var12.getX() >> 4, var12.getZ() >> 4)) {
               WorldChunk var9 = var0.getChunkManager().getWorldChunk(var12.getX() >> 4, var12.getZ() >> 4);
               if (var9 != null) {
                  BlockState var10 = var9.getBlockState(var12);
                  if (var10.isOf(Blocks.CHEST) && var10.get(ChestBlock.CHEST_TYPE) != ChestType.SINGLE) {
                     x1se6fg76pktcw(var0, var12.offset(ChestBlock.getFacing(var10)));
                  }
               }
            }
         }

         if (var5) {
            ColonyManager.x2u0vsam1tcmhq(var0, var4);
         }
      }

      if (var2) {
         var1.markDirty();
      }
   }
}
