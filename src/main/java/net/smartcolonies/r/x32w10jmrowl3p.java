package net.smartcolonies.r;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.chunk.WorldChunk;

public final class x32w10jmrowl3p {
   public static final int x3fqjwal9ef9rt = 48;
   public static final int x3p6brpzuhzsao = 16;

   private x32w10jmrowl3p() {
   }

   public static Optional<BlockPos> x2o5z7e7ygl136(ServerWorld var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4) {
      return xzvjreewiw7bn(var0, var1, var2, var3, var4, false);
   }

   public static Optional<BlockPos> xzvjreewiw7bn(ServerWorld var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4, boolean var5) {
      int var6 = Math.min(var1.radius(), x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max);
      int var7 = (var6 * 2 + 1) * (var6 * 2 + 1);
      BlockPos var8 = var1.core();

      for (int var9 = 0; var9 < 48 && var1.x1eughlg5bm9gx() < var7; var9++) {
         int var10 = var1.x1eughlg5bm9gx();
         var1.x2xopfa6a46mo9();
         x4t5d0ytwq4ac var11 = xuu0zlh8kzgkl(var10);
         if (Math.max(Math.abs(var11.x3bqzqsu81ak1i), Math.abs(var11.x17l8srdozs7l)) >= 8
            && Math.max(Math.abs(var11.x3bqzqsu81ak1i), Math.abs(var11.x17l8srdozs7l)) <= var6 - Math.max(var2, var3)) {
            int var12 = var6 - Math.max(var2, var3) - 4;
            if (var11.x3bqzqsu81ak1i * var11.x3bqzqsu81ak1i + var11.x17l8srdozs7l * var11.x17l8srdozs7l <= var12 * var12) {
               int var13 = var8.getX() + var11.x3bqzqsu81ak1i;
               int var14 = var8.getZ() + var11.x17l8srdozs7l;
               if (x1710fugau4ahf(var0, var13, var14, var2, var3)) {
                  Optional var15 = x2lxftuktkskuu(var0, var1, var13, var14, var2, var3, var4);
                  if (var15.isPresent()) {
                     Optional<List<BlockPos>> var16 = xamz6pfj7qa87.x33m0vw9cimu8k(var0, var1, (BlockPos)var15.get(), var2, var3);
                     if (!var16.isEmpty() && (var5 || x1e0m4zk94351d(var0, var1, (BlockPos)var15.get(), var2, var3))) {
                        x27aao31y6r2ta.x15itjvs0tajaz(var0, var1, (BlockPos)var15.get(), var2, var3);
                        if (var5) {
                           for (BlockPos var18 : var16.get()) {
                              if (!var1.x1n1a2vp01kxzf().contains(var18)) {
                                 var1.x1n1a2vp01kxzf().add(var18);
                              }
                           }
                        } else {
                           var1.x15dc4qxz6dpo3(var16.get());
                        }

                        var1.x2lr91dptxy1ww();
                        return var15;
                     }
                  }
               }
            }
         }
      }

      if (var1.x1eughlg5bm9gx() >= var7) {
         var1.x2lr91dptxy1ww();
      }

      return Optional.empty();
   }

   private static Optional<BlockPos> x2lxftuktkskuu(ServerWorld var0, x15kgoaq6i3m6i var1, int var2, int var3, int var4, int var5, int var6) {
      if (var1.x2vcibw4inamjw(new BlockBox(var2, 0, var3, var2 + var4 - 1, 0, var3 + var5 - 1), 3)) {
         return Optional.empty();
      } else {
         for (int var7 = -2; var7 < var4 + 2; var7++) {
            for (int var8 = -2; var8 < var5 + 2; var8++) {
               BlockPos var9 = xamz6pfj7qa87.x3qthrkxcelgb7(var0, var2 + var7, var3 + var8);
               if (xamz6pfj7qa87.x3hqfrterusj2u(var0, var9)) {
                  return Optional.empty();
               }
            }
         }

         int var17 = Integer.MAX_VALUE;
         int var18 = Integer.MIN_VALUE;

         for (int var19 = 0; var19 < var4; var19++) {
            for (int var10 = 0; var10 < var5; var10++) {
               int var11 = x1zwog5kwmtqw8(var0, var2 + var19, var3 + var10);
               BlockPos var12 = new BlockPos(var2 + var19, var11, var3 + var10);
               if (x20rghnsl2yowl.x239t1m3hfi74d(var0, var12)) {
                  return Optional.empty();
               }

               if (!xrjsytz39cbck(var0.getBlockState(var12)) || var0.getBlockState(var12.up()).getBlock() instanceof CropBlock) {
                  return Optional.empty();
               }

               var17 = Math.min(var17, var11);
               var18 = Math.max(var18, var11);
            }
         }

         if (var18 - var17 > 4) {
            return Optional.empty();
         } else {
            int var20 = (var17 + var18 + 1) / 2;
            if (var20 > var1.core().getY() + 16) {
               return Optional.empty();
            } else {
               BlockPos var21 = new BlockPos(var2, var20, var3);
               BlockBox var22 = new BlockBox(var2 - 4, var20 - 4, var3 - 4, var2 + var4 + 3, var20 + var6 + 2, var3 + var5 + 3);
               if (x3d5vk1zj3fvkj.get(var0).land().stream().anyMatch(var1x -> var1x.intersects(var22))) {
                  return Optional.empty();
               } else {
                  for (x2arpn38dclo1g var13 : var1.x1ja61emeln2hc()) {
                     if (var13.x32oz43hmeynhc() != x20r5v7z43p3d5.xqfd7s4y4gux6
                        && (var13.x32oz43hmeynhc() != x20r5v7z43p3d5.x3vi306qg1ms75 || var13.x2zq2smhbu3lzo() != 0L)
                        && var13.x24kbqv6g4kkrg().intersects(var22)) {
                        return Optional.empty();
                     }
                  }

                  for (BlockPos var26 : var1.x31ow2tlg0dm5w()) {
                     if (var26.getY() >= var20 - 5
                        && var26.getY() <= var20 + var6 + 2
                        && var26.getX() >= var22.getMinX()
                        && var26.getX() <= var22.getMaxX()
                        && var26.getZ() >= var22.getMinZ()
                        && var26.getZ() <= var22.getMaxZ()) {
                        return Optional.empty();
                     }
                  }

                  for (int var25 = -1; var25 <= var4; var25++) {
                     for (int var27 = -1; var27 <= var5; var27++) {
                        int var14 = -4;

                        while (var14 <= var6 + 2) {
                           BlockPos var15 = var21.add(var25, var14, var27);
                           BlockState var16 = var0.getBlockState(var15);
                           if (x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var15)) {
                              return Optional.empty();
                           }

                           if (var0.getBlockEntity(var15) == null && var0.getFluidState(var15).isEmpty()) {
                              if (x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var15)
                                 || var16.isAir()
                                 || var16.isReplaceable()
                                 || xrjsytz39cbck(var16)
                                 || var16.isIn(BlockTags.LEAVES)
                                 || var16.isIn(BlockTags.LOGS) && x33tqnmmkvlc81(var0, var15)) {
                                 var14++;
                                 continue;
                              }

                              return Optional.empty();
                           }

                           return Optional.empty();
                        }
                     }
                  }

                  return x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.builder_respects_player_builds && xwcjcz0wnbzkv(var0, var22)
                     ? Optional.empty()
                     : Optional.of(var21);
               }
            }
         }
      }
   }

   private static boolean xrjsytz39cbck(BlockState var0) {
      return !var0.isOf(Blocks.DIRT_PATH) && !var0.isOf(Blocks.FARMLAND) && x1dxdlnevtfbhm.xroinv4hk6l7h(var0);
   }

   private static boolean x1e0m4zk94351d(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2, int var3, int var4) {
      List<VillagerEntity> var5 = var0.getEntitiesByType(
         EntityType.VILLAGER,
         x2n55y2tom37h2.x150ccxomrlozr(var0, var1),
         var1x -> var1x.isAlive() && !var1x.isBaby() && var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
      );
      if (var5.isEmpty()) {
         return false;
      } else {
         HashSet<BlockPos> var6 = new HashSet<>();

         for (int[] var10 : new int[][]{{var3 / 2, -3}, {var3 / 2, var4 + 2}, {-3, var4 / 2}, {var3 + 2, var4 / 2}}) {
            int var11 = var2.getX() + var10[0];
            int var12 = var2.getZ() + var10[1];
            BlockPos var13 = xamz6pfj7qa87.x3qthrkxcelgb7(var0, var11, var12).up();
            if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var13)) {
               var6.add(var13);
            }
         }

         if (!var6.isEmpty() && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            VillagerEntity var17 = var5.stream().min(Comparator.comparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core()))).orElseThrow();
            var17.getNavigation().setCanOpenDoors(true);
            var17.getNavigation().setCanSwim(true);
            var17.getNavigation().setMaxFollowRange(128.0F);
            var17.getNavigation().setRangeMultiplier(8.0F);

            Path var18;
            try {
               var18 = var17.getNavigation().findPathTo(var6, 0);
            } finally {
               var17.getNavigation().resetRangeMultiplier();
            }

            return var18 != null && var18.reachesTarget();
         } else {
            return false;
         }
      }
   }

   private static int x1zwog5kwmtqw8(ServerWorld var0, int var1, int var2) {
      int var3 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var1, var2) - 1;

      for (int var4 = 0; var4 < 16 && var3 > var0.getBottomY(); var4++) {
         BlockPos var5 = new BlockPos(var1, var3, var2);
         BlockState var6 = var0.getBlockState(var5);
         if (!x27aao31y6r2ta.x3hz8l3ppqisjl(var0, var5)
            && !var6.isReplaceable()
            && !var6.isIn(BlockTags.LEAVES)
            && (!var6.isIn(BlockTags.LOGS) || !x33tqnmmkvlc81(var0, var5))) {
            break;
         }

         var3--;
      }

      return var3;
   }

   private static boolean x33tqnmmkvlc81(ServerWorld var0, BlockPos var1) {
      if (x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var1)) {
         return false;
      } else {
         for (BlockPos var3 : BlockPos.iterate(var1.add(-3, -1, -3), var1.add(3, 8, 3))) {
            if (var0.getBlockState(var3).isIn(BlockTags.LEAVES)) {
               return true;
            }
         }

         return false;
      }
   }

   private static boolean xwcjcz0wnbzkv(ServerWorld var0, BlockBox var1) {
      int var2 = var1.getMinX() - 32;
      int var3 = var1.getMaxX() + 32;
      int var4 = var1.getMinZ() - 32;
      int var5 = var1.getMaxZ() + 32;

      for (int var6 = var2 >> 4; var6 <= var3 >> 4; var6++) {
         for (int var7 = var4 >> 4; var7 <= var5 >> 4; var7++) {
            if (var0.getChunkManager().isChunkLoaded(var6, var7)) {
               WorldChunk var8 = var0.getChunkManager().getWorldChunk(var6, var7);
               if (var8 != null) {
                  boolean var9 = var8.getBlockEntities()
                     .entrySet()
                     .stream()
                     .anyMatch(
                        var5x -> {
                           BlockPos var6x = (BlockPos)var5x.getKey();
                           return var6x.getX() >= var2
                              && var6x.getX() <= var3
                              && var6x.getZ() >= var4
                              && var6x.getZ() <= var5
                              && var0.getBlockState(var6x).isOf(Blocks.BEACON);
                        }
                     );
                  if (var9) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private static boolean x1710fugau4ahf(ServerWorld var0, int var1, int var2, int var3, int var4) {
      int var5 = var1 - 4 >> 4;
      int var6 = var1 + var3 + 3 >> 4;
      int var7 = var2 - 4 >> 4;
      int var8 = var2 + var4 + 3 >> 4;

      for (int var9 = var5; var9 <= var6; var9++) {
         for (int var10 = var7; var10 <= var8; var10++) {
            if (!var0.getChunkManager().isChunkLoaded(var9, var10)) {
               return false;
            }
         }
      }

      return true;
   }

   private static x4t5d0ytwq4ac xuu0zlh8kzgkl(int var0) {
      if (var0 == 0) {
         return new x4t5d0ytwq4ac(0, 0);
      } else {
         int var1 = (int)Math.ceil((Math.sqrt(var0 + 1) - 1.0) / 2.0);
         int var2 = var1 * 2;
         int var3 = (var1 * 2 + 1) * (var1 * 2 + 1) - 1;
         int var4 = var3 - var0;
         if (var4 < var2) {
            return new x4t5d0ytwq4ac(var1 - var4, -var1);
         } else if (var4 < var2 * 2) {
            return new x4t5d0ytwq4ac(-var1, -var1 + (var4 - var2));
         } else {
            return var4 < var2 * 3 ? new x4t5d0ytwq4ac(-var1 + (var4 - var2 * 2), var1) : new x4t5d0ytwq4ac(var1, var1 - (var4 - var2 * 3));
         }
      }
   }
}
