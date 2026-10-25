package net.smartcolonies.r;

import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.rule.GameRules;

public final class ColonyProtectionUtils {
   private static final TagKey<Block> x2a42nw811caa4 = TagKey.of(RegistryKeys.BLOCK, Identifier.of("smart_colonies", "builder_protected"));

   private ColonyProtectionUtils() {
   }

   public static boolean xroinv4hk6l7h(BlockState var0) {
      return var0.isIn(BlockTags.DIRT)
         || var0.isIn(BlockTags.SAND)
         || var0.isIn(BlockTags.BASE_STONE_OVERWORLD)
         || var0.isIn(BlockTags.TERRACOTTA)
         || var0.isOf(Blocks.SANDSTONE)
         || var0.isOf(Blocks.RED_SANDSTONE)
         || var0.isOf(Blocks.GRAVEL)
         || var0.isOf(Blocks.SNOW_BLOCK)
         || var0.isOf(Blocks.TUFF);
   }

   public static boolean x35ev8l9vc9uk7(ServerWorld var0) {
      return ColonyConfig.x2by5f9ehxvwk9().roles.allow_terrain_changes && (Boolean)var0.getGameRules().getValue(GameRules.DO_MOB_GRIEFING);
   }

   public static boolean x3o1lagqjim5tz(ServerWorld var0, BlockPos var1) {
      return ColonyBiomeHelper.x3hz8l3ppqisjl(var0, var1) || xjs779qdest9c(var0, var1);
   }

   public static boolean xjs779qdest9c(ServerWorld var0, BlockPos var1) {
      if (var0.getBlockState(var1).isAir()) {
         ColonyState.get(var0).forgetPlacement(var1);
      }

      return ColonyState.get(var0).land().stream().anyMatch(var1x -> var1x.contains(var1))
         || ColonyState.get(var0).isPlayerBlock(var1)
         || var0.getBlockEntity(var1) != null
         || var0.getBlockState(var1).isIn(x2a42nw811caa4)
         || xz4flfirg81x1(var0, var1);
   }

   public static boolean xz4flfirg81x1(ServerWorld var0, BlockPos var1) {
      for (int var2 = var1.getX() - 32 >> 4; var2 <= var1.getX() + 32 >> 4; var2++) {
         for (int var3 = var1.getZ() - 32 >> 4; var3 <= var1.getZ() + 32 >> 4; var3++) {
            if (var0.getChunkManager().isChunkLoaded(var2, var3)) {
               WorldChunk var4 = var0.getChunkManager().getWorldChunk(var2, var3);
               if (var4 != null) {
                  for (Entry var6 : var4.getBlockEntities().entrySet()) {
                     if (Math.abs(((BlockPos)var6.getKey()).getX() - var1.getX()) <= 32
                        && Math.abs(((BlockPos)var6.getKey()).getZ() - var1.getZ()) <= 32
                        && var0.getBlockState((BlockPos)var6.getKey()).isOf(Blocks.BEACON)) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   public static boolean x13f7be5vlapn7(ServerWorld var0, BlockPos var1) {
      return x35ev8l9vc9uk7(var0) && var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4) && !x3o1lagqjim5tz(var0, var1);
   }

   public static boolean x1bq0f8n53gtob(ServerWorld var0, BlockPos var1, BlockState var2, int var3) {
      VoxelShape var4 = var2.getCollisionShape(var0, var1);
      if (!var4.isEmpty()) {
         for (Entity var7 : var0.getOtherEntities(
            null, new Box(var1).expand(0.0, 2.0, 0.0), var0x -> var0x.isAlive() && !var0x.isSpectator() && var0x instanceof LivingEntity
         )) {
            Box var8 = var7.getBoundingBox();
            if (var7 instanceof LivingEntity var9 && (var9.isSleeping() || var9.getPose() == EntityPose.SLEEPING)) {
               var8 = new Box(var7.getX() - 0.35, var7.getY(), var7.getZ() - 0.35, var7.getX() + 0.35, var7.getY() + 2.0, var7.getZ() + 0.35);
            }

            for (Box var10 : var4.getBoundingBoxes()) {
               if (var10.offset(var1.getX(), var1.getY(), var1.getZ()).intersects(var8)) {
                  return false;
               }
            }
         }
      }

      return x13f7be5vlapn7(var0, var1) && ColonyWorldUtils.x2aebumvi646n9(() -> var0.setBlockState(var1, var2, var3));
   }

   public static boolean x231if4rvsvptj(ServerWorld var0, BlockPos var1, boolean var2, Entity var3) {
      if (var3 != null && var3.getBlockPos().down().equals(var1)) {
         return false;
      } else if (var0.getBlockState(var1.up()).getBlock() instanceof FallingBlock) {
         return false;
      } else if (!var0.getOtherEntities(
            null, new Box(var1.up()).expand(0.02), var1x -> var1x.isAlive() && var1x instanceof LivingEntity && var1x.getBlockPos().down().equals(var1)
         )
         .isEmpty()) {
         return false;
      } else {
         boolean var4 = x13f7be5vlapn7(var0, var1) && ColonyWorldUtils.x2aebumvi646n9(() -> var0.breakBlock(var1, var2, var3));
         if (var4 && var3 instanceof VillagerEntity var5) {
            ColonyChatFeedback.x2t98zlq4lu49t(var5, "добыча/расчистка", 1);
         }

         return var4;
      }
   }
}
