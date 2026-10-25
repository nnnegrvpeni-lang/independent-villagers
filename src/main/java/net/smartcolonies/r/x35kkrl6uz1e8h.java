package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Type;

public final class x35kkrl6uz1e8h {
   private static final Map<VillagerEntity, x2cu91tum5yd6h> x25u0cyk1y45um = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<VillagerEntity, Long> x237zvjlldjb5z = Collections.synchronizedMap(new WeakHashMap<>());
   private static final Map<VillagerEntity, x102ju4l0cty5k> xeksvy84118bm = Collections.synchronizedMap(new WeakHashMap<>());
   private static final int x2zxyof023qhq1 = 100;
   private static final int x1kadx2a48avi2 = 60;
   private static final int x1h0gaextn519b = 360;

   private x35kkrl6uz1e8h() {
   }

   public static void x1feket2ghytw5(VillagerEntity var0, long var1) {
      x237zvjlldjb5z.put(var0, var1);
   }

   public static void x8pl4kh8avnpr(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      if ((var0.getTime() + var2.getId()) % 20L == 0L) {
         if (x3u53699ao0yas(var2, var0.getTime())) {
            x25u0cyk1y45um.remove(var2);
         } else if (var1.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x3r8p2tvihwpph
            && var2.getBrain().getOptionalRegisteredMemory(xxao083dbo7ag.x14b5znl8ndblc).isPresent()) {
            x25u0cyk1y45um.remove(var2);
         } else {
            long var3 = Math.floorMod(var0.getTimeOfDay(), 24000L);
            if (var2.isSleeping() || var2.hasCustomer() || var3 >= 9000L) {
               x25u0cyk1y45um.remove(var2);
               xeksvy84118bm.remove(var2);
            } else if (x20lc7jle5mw9c.x1fhuggzt16ek2(var2, var0.getTime())) {
               x25u0cyk1y45um.remove(var2);
               xeksvy84118bm.remove(var2);
            } else if (x102ui8nytk53n(var0, var2)) {
               x25u0cyk1y45um.remove(var2);
            } else if (x1c1r5c6a13gtl.x1f6ghn62xaxti(var2, var0.getTime())) {
               if (x1sixoyijd0z9u(var0, var1, var2)) {
                  x25u0cyk1y45um.remove(var2);
               }
            } else {
               BlockPos var5 = x1om0jz0ba9i8l(var2);
               if (var5 != null && !(var2.getBlockPos().getSquaredDistance(var5) <= 9.0)) {
                  double var6 = var2.getBlockPos().getSquaredDistance(var5);
                  Vec3d var8 = var2.getEntityPos();
                  x2cu91tum5yd6h var9 = x25u0cyk1y45um.get(var2);
                  if (var9 != null && !(var9.x3hblxrvsbx0pw().getSquaredDistance(var5) > 4.0)) {
                     boolean var10 = var9.x1zfnlcsuk3t1i().squaredDistanceTo(var8) >= 0.16;
                     boolean var11 = var6 + 1.0 < var9.xwwyae897rr8r();
                     if (!var10 && !var11) {
                        long var12 = var0.getTime() - var9.x38qwv32hf8w5w();
                        boolean var14 = !var2.isOnGround() && var0.getTime() - var9.x2sysqaosnmrak() >= 100L && !x3smw0c29vok53(var0, var2.getBlockPos(), 12);
                        if (!var14 && (var12 < 360L || !var2.getNavigation().isIdle() && var9.x3d25jiqyb27mb() < 3)) {
                           if (var12 >= 100L && var0.getTime() - var9.x16yow35qysf17() >= 60L) {
                              BlockPos var15 = var2.getBlockPos();
                              int var16 = var5.getX() - var15.getX();
                              int var17 = var5.getZ() - var15.getZ();
                              Direction var18 = var16 == 0 && var17 == 0
                                 ? Direction.NORTH
                                 : (
                                    Math.abs(var16) >= Math.abs(var17)
                                       ? (var16 > 0 ? Direction.EAST : Direction.WEST)
                                       : (var17 > 0 ? Direction.SOUTH : Direction.NORTH)
                                 );
                              int var19 = 0;
                              if (var16 == 0 && var17 == 0) {
                                 for (Direction var21 : Type.HORIZONTAL) {
                                    var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var21, var9.x26xkfgjum6mf6());
                                    if (var19 != 0) {
                                       var18 = var21;
                                       break;
                                    }
                                 }
                              } else if (var9.x3d25jiqyb27mb() % 2 == 0) {
                                 var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var18, var9.x26xkfgjum6mf6());
                              }

                              if (var19 == 0 && xahx1liq9jvh7(var0, var1, var2, var15, var5, var18)) {
                                 var19 = 1;
                              }

                              if (var19 == 0) {
                                 var19 = x2g2khbsuvhzto(var0, var1, var2, var15, var5, var18, var9.x26xkfgjum6mf6());
                              }

                              if (var19 == 2) {
                                 var2.swingHand(Hand.MAIN_HAND);
                              }

                              x25u0cyk1y45um.put(
                                 var2,
                                 new x2cu91tum5yd6h(
                                    var5.toImmutable(),
                                    var9.x1zfnlcsuk3t1i(),
                                    var9.x2sysqaosnmrak(),
                                    var9.x38qwv32hf8w5w(),
                                    var9.x1urgnoe13hqro(),
                                    var0.getTime(),
                                    var9.x3d25jiqyb27mb() + 1,
                                    var9.x26xkfgjum6mf6() + (var19 == 2 ? 1 : 0),
                                    Math.min(var6, var9.xwwyae897rr8r())
                                 )
                              );
                           }
                        } else {
                           if (x1sixoyijd0z9u(var0, var1, var2)) {
                              x25u0cyk1y45um.remove(var2);
                           }
                        }
                     } else {
                        x25u0cyk1y45um.put(
                           var2,
                           new x2cu91tum5yd6h(
                              var5.toImmutable(),
                              var8,
                              var9.x2sysqaosnmrak(),
                              var10 ? var0.getTime() : var9.x38qwv32hf8w5w(),
                              var11 ? var0.getTime() : var9.x1urgnoe13hqro(),
                              var9.x16yow35qysf17(),
                              0,
                              0,
                              Math.min(var6, var9.xwwyae897rr8r())
                           )
                        );
                     }
                  } else {
                     x25u0cyk1y45um.put(
                        var2, new x2cu91tum5yd6h(var5.toImmutable(), var8, var0.getTime(), var0.getTime(), var0.getTime(), var0.getTime(), 0, 0, var6)
                     );
                  }
               } else {
                  x25u0cyk1y45um.remove(var2);
               }
            }
         }
      }
   }

   public static boolean x3u53699ao0yas(VillagerEntity var0, long var1) {
      Long var3 = x237zvjlldjb5z.get(var0);
      if (var3 == null) {
         return false;
      } else if (var1 < var3) {
         return true;
      } else {
         x237zvjlldjb5z.remove(var0);
         return false;
      }
   }

   private static boolean x102ui8nytk53n(ServerWorld var0, VillagerEntity var1) {
      if (!var1.isOnGround()) {
         xeksvy84118bm.remove(var1);
         return false;
      } else {
         BlockPos var2 = var1.getBlockPos();

         for (Direction var4 : Type.HORIZONTAL) {
            if (x147zp9ypin2wg(var0, var2.offset(var4))) {
               xeksvy84118bm.remove(var1);
               return false;
            }
         }

         BlockPos var9 = null;

         label74:
         for (int var10 = 1; var10 <= 3; var10++) {
            for (int var5 = 1; var5 <= 2; var5++) {
               for (int var6 = -var10; var6 <= var10; var6++) {
                  for (int var7 = -var10; var7 <= var10; var7++) {
                     if (Math.max(Math.abs(var6), Math.abs(var7)) == var10) {
                        BlockPos var8 = var2.add(var6, var5, var7);
                        if (x147zp9ypin2wg(var0, var8)) {
                           var9 = var8.toImmutable();
                           break label74;
                        }
                     }
                  }
               }
            }
         }

         if (var9 == null) {
            xeksvy84118bm.remove(var1);
            return false;
         } else {
            long var11 = var0.getTime();
            x102ju4l0cty5k var12 = xeksvy84118bm.get(var1);
            if (var12 == null || var12.xjmya1m07w50b().getSquaredDistance(var2) > 4.0 || !var12.x2gv3orj2jn99t().equals(var9)) {
               var12 = new x102ju4l0cty5k(var2.toImmutable(), var9, var11);
               xeksvy84118bm.put(var1, var12);
            }

            if (var11 - var12.x2cpmgiveflnwy() >= 80L) {
               var1.getNavigation().stop();
               var1.refreshPositionAndAngles(var9.getX() + 0.5, var9.getY(), var9.getZ() + 0.5, var1.getYaw(), var1.getPitch());
               var1.setVelocity(Vec3d.ZERO);
               var1.onLanding();
               xeksvy84118bm.remove(var1);
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var1, "выход из небольшой ямы");
            } else if (x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
               var1.getNavigation().startMovingTo(var9.getX() + 0.5, var9.getY(), var9.getZ() + 0.5, 0.9);
            }

            return true;
         }
      }
   }

   private static int x2g2khbsuvhzto(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, BlockPos var3, BlockPos var4, Direction var5, int var6) {
      BlockPos var7 = var3.offset(var5);
      boolean var8 = var1.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x18g4mqx9kxs89;
      if (var4.getY() > var3.getY()) {
         if (x147zp9ypin2wg(var0, var7.up()) && x3s6b8w8xand71(var0, var2, var7.up())) {
            return 1;
         }

         if (!var8
            && var6 < 3
            && !var0.getBlockState(var7.up()).getCollisionShape(var0, var7.up()).isEmpty()
            && var0.getBlockState(var7.up(2)).getCollisionShape(var0, var7.up(2)).isEmpty()
            && x13f18m8o5x2va(var0, var1, var2, var7.up())
            && x1dxdlnevtfbhm.x231if4rvsvptj(var0, var7.up(), false, var2)) {
            return 2;
         }

         if (var6 < 3 && var0.getBlockState(var7).isAir() && var0.getBlockState(var7.up()).isAir() && xjbg5jauzmkc2(var0, var1, var2, var7)) {
            return 2;
         }
      }

      if (var4.getY() < var3.getY()) {
         if (x147zp9ypin2wg(var0, var7.down()) && x3s6b8w8xand71(var0, var2, var7.down())) {
            return 1;
         }

         if (x147zp9ypin2wg(var0, var7.down(2)) && x3s6b8w8xand71(var0, var2, var7.down(2))) {
            return 1;
         }
      }

      if (var6 < 3
         && var0.getBlockState(var7).getCollisionShape(var0, var7).isEmpty()
         && !var0.getBlockState(var7.down()).isSideSolidFullSquare(var0, var7.down(), Direction.UP)) {
         for (int var9 = 2; var9 <= 3; var9++) {
            if (x147zp9ypin2wg(var0, var3.offset(var5, var9)) && xjbg5jauzmkc2(var0, var1, var2, var7.down())) {
               return 2;
            }
         }
      }

      if (!var8 && var6 < 2 && var4.getY() <= var3.getY()) {
         for (BlockPos var10 : List.of(var7.up(), var7)) {
            if (!var0.getBlockState(var10).getCollisionShape(var0, var10).isEmpty()
               && x13f18m8o5x2va(var0, var1, var2, var10)
               && x1dxdlnevtfbhm.x231if4rvsvptj(var0, var10, false, var2)) {
               return 2;
            }
         }
      }

      return 0;
   }

   private static boolean xahx1liq9jvh7(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, BlockPos var3, BlockPos var4, Direction var5) {
      List<BlockPos> var6 = new ArrayList<>();

      for (int var7 = 1; var7 <= 4; var7++) {
         for (int var8 = -var7; var8 <= var7; var8++) {
            for (int var9 = -var7; var9 <= var7; var9++) {
               if (Math.max(Math.abs(var8), Math.abs(var9)) == var7) {
                  for (int var13 : new int[]{0, 1, -1, 2, -2}) {
                     BlockPos var14 = var3.add(var8, var13, var9);
                     double var15 = (double)var14.getX() - var1.core().getX();
                     double var17 = (double)var14.getZ() - var1.core().getZ();
                     if (!(var15 * var15 + var17 * var17 > (double)(var1.radius() - 2) * (var1.radius() - 2))
                        && x147zp9ypin2wg(var0, var14)
                        && !var14.equals(var3.offset(var5))
                        && !(var14.getSquaredDistance(var4) > var3.getSquaredDistance(var4) + 16.0)
                        && var0.getOtherEntities(var2, new Box(var14).expand(0.35), var0x -> var0x.isAlive() && var0x instanceof LivingEntity).isEmpty()) {
                        var6.add(var14.toImmutable());
                        break;
                     }
                  }
               }
            }
         }
      }

      var6.sort(Comparator.comparingDouble(var2x -> var2x.getSquaredDistance(var4) + var2x.getSquaredDistance(var3) * 0.25));

      for (int var19 = 0; var19 < Math.min(6, var6.size()); var19++) {
         if (!x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
            return false;
         }

         BlockPos var20 = (BlockPos)var6.get(var19);
         Path var21 = var2.getNavigation().findPathTo(var20, 0);
         if (var21 != null && var21.reachesTarget()) {
            return var2.getNavigation().startMovingAlong(var21, 0.85);
         }
      }

      return false;
   }

   private static boolean x3s6b8w8xand71(ServerWorld var0, VillagerEntity var1, BlockPos var2) {
      return x2n55y2tom37h2.x5ll9elqpk1a4(var0) && var1.getNavigation().startMovingTo(var2.getX() + 0.5, var2.getY(), var2.getZ() + 0.5, 0.85);
   }

   private static boolean x147zp9ypin2wg(ServerWorld var0, BlockPos var1) {
      return var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)
         && var0.getFluidState(var1).isEmpty()
         && var0.getFluidState(var1.down()).isEmpty()
         && var0.getBlockState(var1).getCollisionShape(var0, var1).isEmpty()
         && var0.getBlockState(var1.up()).getCollisionShape(var0, var1.up()).isEmpty()
         && var0.getBlockState(var1.down()).isSideSolidFullSquare(var0, var1.down(), Direction.UP);
   }

   private static boolean x3smw0c29vok53(ServerWorld var0, BlockPos var1, int var2) {
      for (int var3 = 1; var3 <= var2; var3++) {
         if (var0.getBlockState(var1.down(var3)).isSideSolidFullSquare(var0, var1.down(var3), Direction.UP) && var0.getFluidState(var1.down(var3)).isEmpty()) {
            return true;
         }
      }

      return false;
   }

   private static BlockPos x1om0jz0ba9i8l(VillagerEntity var0) {
      BlockPos var1 = x1c1r5c6a13gtl.x37u4pqy3fbps(var0);
      if (var1 != null) {
         return var1;
      } else {
         for (MemoryModuleType var3 : List.of(
            xxao083dbo7ag.x15kki2mjvh7tw,
            xxao083dbo7ag.x1r15huley8x9h,
            xxao083dbo7ag.x1h4kjv4hra8oe,
            xxao083dbo7ag.x2bwom1pasl6g5,
            xxao083dbo7ag.x263ucjykllb1b,
            xxao083dbo7ag.x2ppem7wipzctm,
            xxao083dbo7ag.x3o547clcv9kaw
         )) {
            Optional var4 = var0.getBrain().getOptionalRegisteredMemory(var3);
            if (var4.isPresent()) {
               return (BlockPos)var4.get();
            }
         }

         return var0.getNavigation().getTargetPos();
      }
   }

   private static boolean x13f18m8o5x2va(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, BlockPos var3) {
      if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var3)) {
         return false;
      } else if (var0.getChunkManager().isChunkLoaded(var3.getX() >> 4, var3.getZ() >> 4) && var0.getBlockEntity(var3) == null) {
         if (var3.getY() >= var2.getBlockY() && !var3.equals(var2.getBlockPos().down())) {
            if (!var0.getOtherEntities(var2, new Box(var3.up()).expand(0.05), var0x -> var0x.isAlive() && var0x instanceof LivingEntity).isEmpty()) {
               return false;
            } else {
               BlockState var4 = var0.getBlockState(var3);
               if (!var4.isIn(BlockTags.BASE_STONE_OVERWORLD)
                  && !var4.isIn(BlockTags.SAND)
                  && !var4.isOf(Blocks.GRAVEL)
                  && !var4.isOf(Blocks.DIRT)
                  && !var4.isOf(Blocks.GRASS_BLOCK)
                  && !var4.isOf(Blocks.COARSE_DIRT)
                  && !var4.isOf(Blocks.PODZOL)
                  && !var4.isOf(Blocks.TUFF)
                  && !var4.isOf(Blocks.SNOW_BLOCK)) {
                  return false;
               } else {
                  Direction[] var5 = Direction.values();
                  int var6 = var5.length;
                  int var7 = 0;

                  while (var7 < var6) {
                     Direction var8 = var5[var7];
                     BlockPos var9 = var3.offset(var8);
                     BlockState var10 = var0.getBlockState(var9);
                     if (var0.getFluidState(var9).isEmpty()
                        && !var10.isOf(Blocks.DIRT_PATH)
                        && !var10.isOf(Blocks.FARMLAND)
                        && !var10.isIn(BlockTags.BEDS)
                        && !var10.isIn(BlockTags.DOORS)) {
                        String var11 = Registries.BLOCK.getId(var10.getBlock()).getPath();
                        if (!var11.contains("planks") && !var11.contains("fence") && !var11.contains("brick")) {
                           var7++;
                           continue;
                        }

                        return false;
                     }

                     return false;
                  }

                  for (x2arpn38dclo1g var13 : var1.x1ja61emeln2hc()) {
                     BlockBox var14 = var13.x24kbqv6g4kkrg();
                     if (var3.getX() >= var14.getMinX() - 3
                        && var3.getX() <= var14.getMaxX() + 3
                        && var3.getY() >= var14.getMinY() - 3
                        && var3.getY() <= var14.getMaxY() + 3
                        && var3.getZ() >= var14.getMinZ() - 3
                        && var3.getZ() <= var14.getMaxZ() + 3) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xjbg5jauzmkc2(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, BlockPos var3) {
      if (var0.getBlockState(var3).isAir() && !x1556papwgwmb0(var0, var1, var3)) {
         for (int var4 = 0; var4 < var2.getInventory().size(); var4++) {
            ItemStack var5 = var2.getInventory().getStack(var4);
            BlockState var6 = var5.isOf(Items.COBBLESTONE)
               ? Blocks.COBBLESTONE.getDefaultState()
               : (var5.isOf(Items.DIRT) ? Blocks.DIRT.getDefaultState() : null);
            if (var6 != null && x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var3, var6, 3)) {
               var5.decrement(1);
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean x1556papwgwmb0(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (var0.getBlockEntity(var2) != null) {
         return true;
      } else {
         for (Direction var6 : Direction.values()) {
            BlockState var7 = var0.getBlockState(var2.offset(var6));
            if (var7.isOf(Blocks.DIRT_PATH) || var7.isOf(Blocks.FARMLAND) || var7.isIn(BlockTags.BEDS) || var7.isIn(BlockTags.DOORS)) {
               return true;
            }
         }

         for (x2arpn38dclo1g var9 : var1.x1ja61emeln2hc()) {
            BlockBox var10 = var9.x24kbqv6g4kkrg();
            if (var2.getX() >= var10.getMinX() - 2
               && var2.getX() <= var10.getMaxX() + 2
               && var2.getY() >= var10.getMinY() - 2
               && var2.getY() <= var10.getMaxY() + 2
               && var2.getZ() >= var10.getMinZ() - 2
               && var2.getZ() <= var10.getMaxZ() + 2) {
               return true;
            }
         }

         return false;
      }
   }

   public static boolean xdo71dbv16zut(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      return x1sixoyijd0z9u(var0, var1, var2);
   }

   private static boolean x1sixoyijd0z9u(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2) {
      for (int var3 = 1; var3 <= 12; var3++) {
         for (int var4 = -var3; var4 <= var3; var4++) {
            for (int var5 = -var3; var5 <= var3; var5++) {
               if (Math.abs(var4) == var3 || Math.abs(var5) == var3) {
                  int var6 = var1.core().getX() + var4;
                  int var7 = var1.core().getZ() + var5;
                  if (var0.getChunkManager().isChunkLoaded(var6 >> 4, var7 >> 4)) {
                     int var8 = var0.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, var6, var7);
                     BlockPos var9 = new BlockPos(var6, var8, var7);
                     if (var0.getFluidState(var9).isEmpty()
                        && var0.getFluidState(var9.down()).isEmpty()
                        && var0.getBlockState(var9).getCollisionShape(var0, var9).isEmpty()
                        && var0.getBlockState(var9.up()).getCollisionShape(var0, var9.up()).isEmpty()
                        && var0.getBlockState(var9.down()).isSideSolidFullSquare(var0, var9.down(), Direction.UP)
                        && var0.getOtherEntities(var2, new Box(var9).expand(0.35), var0x -> var0x.isAlive() && var0x instanceof LivingEntity).isEmpty()) {
                        var0.spawnParticles(ParticleTypes.PORTAL, var2.getX(), var2.getBodyY(0.5), var2.getZ(), 16, 0.3, 0.6, 0.3, 0.1);
                        var2.getNavigation().stop();
                        if (var1.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x18g4mqx9kxs89) {
                           var2.addCommandTag("smart_colonies_emergency_return");
                        } else {
                           x3omirynxb9l0d(var2);
                           x237zvjlldjb5z.put(var2, var0.getTime() + 200L);
                        }

                        var2.refreshPositionAndAngles(var6 + 0.5, var8, var7 + 0.5, var2.getYaw(), var2.getPitch());
                        var2.setVelocity(Vec3d.ZERO);
                        var2.onLanding();
                        var0.spawnParticles(ParticleTypes.PORTAL, var6 + 0.5, var8 + 1, var7 + 0.5, 16, 0.3, 0.6, 0.3, 0.1);
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private static void x3omirynxb9l0d(VillagerEntity var0) {
      for (MemoryModuleType var2 : List.of(
         xxao083dbo7ag.x1r15huley8x9h,
         xxao083dbo7ag.x1h4kjv4hra8oe,
         xxao083dbo7ag.x2bwom1pasl6g5,
         xxao083dbo7ag.x263ucjykllb1b,
         xxao083dbo7ag.x2ppem7wipzctm,
         xxao083dbo7ag.x3o547clcv9kaw
      )) {
         var0.getBrain().forget(var2);
      }
   }
}
