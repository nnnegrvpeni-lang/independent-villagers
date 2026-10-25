package net.smartcolonies.r;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.smartcolonies.mixin.xnknrz08jrie3;

public final class x3m66wv1c6161h {
   private static final Map<ServerWorld, Map<BlockPos, Long>> x125nzphw6b46r = new WeakHashMap<>();

   private x3m66wv1c6161h() {
   }

   public static void x2ik4hs9xwj2v7() {
      x125nzphw6b46r.clear();
   }

   public static void x2zyakuv0vnixz(ServerWorld var0, VillagerEntity var1) {
      x2pmhgbzwd63c4(var0, var1, var0.getTime());
   }

   public static void x2pmhgbzwd63c4(ServerWorld var0, VillagerEntity var1, long var2) {
      if (!x2n55y2tom37h2.x3psqezd5jem54(var0, var1.getBlockPos()).isEmpty() || !x2n55y2tom37h2.x2yj2v4iudqlvb(var0, var1.getUuid()).isEmpty()) {
         var1.getNavigation().setCanOpenDoors(!var1.isSleeping());
         if (Math.floorMod(var2 + var1.getId(), 3) == 0) {
            Map var4 = x125nzphw6b46r.computeIfAbsent(var0, var0x -> new HashMap<>());
            BlockPos var5 = var1.getBlockPos();

            for (BlockPos var7 : BlockPos.iterate(var5.add(-4, -1, -4), var5.add(4, 2, 4))) {
               if (var0.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)) {
                  BlockState var8 = var0.getBlockState(var7);
                  boolean var9 = var8.getBlock() instanceof FenceGateBlock;
                  boolean var10 = var8.getBlock() instanceof DoorBlock && DoorBlock.canOpenByHand(var8);
                  boolean var11 = var8.getBlock() instanceof TrapdoorBlock var12 && ((xnknrz08jrie3)var12).x27hnusyzwjzma().canOpenByHand();
                  if ((var9 || var10 || var11)
                     && (!var9 || !x27aao31y6r2ta.x3di73z4zkdknf(var0, var7))
                     && (!var10 || var8.get(DoorBlock.HALF) != DoubleBlockHalf.UPPER)
                     && (!var9 || !(Boolean)var8.get(FenceGateBlock.POWERED))
                     && (!var10 || !(Boolean)var8.get(DoorBlock.POWERED))
                     && (!var11 || !(Boolean)var8.get(TrapdoorBlock.POWERED))
                     && (
                        !var9
                           || !x2n55y2tom37h2.x3psqezd5jem54(var0, var7)
                              .flatMap(x15kgoaq6i3m6i::x329rgb9lpsari)
                              .map(var1x -> var1x.north(5).equals(var7))
                              .orElse(false)
                     )) {
                     double var21 = var7.getSquaredDistance(var5);
                     if (!(var21 > 16.25)) {
                        BlockPos var14 = var7.toImmutable();
                        if (x2s7jlhnk5vi31(var1, var14) || var11 && xuh5rud0uhpzw(var1, var14)) {
                           if (!var11 || !var14.equals(var5.down())) {
                              if (var9 && !(Boolean)var8.get(FenceGateBlock.OPEN)) {
                                 var0.setBlockState(var14, (BlockState)var8.with(FenceGateBlock.OPEN, true), 3);
                              } else if (var10 && !(Boolean)var8.get(DoorBlock.OPEN)) {
                                 ((DoorBlock)var8.getBlock()).setOpen(var1, var0, var8, var14, true);
                              } else if (var11 && !(Boolean)var8.get(TrapdoorBlock.OPEN)) {
                                 x16779z1in3x0y(var0, var14, var8, true);
                              }

                              var4.put(var14, var2);
                           }
                        } else if (Math.floorMod(var0.getTimeOfDay(), 24000) >= 12000 && (Boolean)var8.get(Properties.OPEN)) {
                           var4.putIfAbsent(var14, var2 - 12L);
                        }
                     }
                  }
               }
            }

            if (Math.floorMod(var2, 5) == 0) {
               Iterator var15 = var4.entrySet().iterator();

               while (var15.hasNext()) {
                  Entry var16 = (Entry)var15.next();
                  BlockPos var17 = (BlockPos)var16.getKey();
                  if (var0.getChunkManager().isChunkLoaded(var17.getX() >> 4, var17.getZ() >> 4)) {
                     BlockState var18 = var0.getBlockState(var17);
                     if (x27aao31y6r2ta.x3di73z4zkdknf(var0, var17)) {
                        var15.remove();
                     } else if (!(var18.getBlock() instanceof FenceGateBlock)
                        && !(var18.getBlock() instanceof DoorBlock)
                        && !(var18.getBlock() instanceof TrapdoorBlock)) {
                        var15.remove();
                     } else if ((!(var18.getBlock() instanceof DoorBlock) || !(Boolean)var18.get(DoorBlock.POWERED))
                        && (!(var18.getBlock() instanceof FenceGateBlock) || !(Boolean)var18.get(FenceGateBlock.POWERED))
                        && (!(var18.getBlock() instanceof TrapdoorBlock) || !(Boolean)var18.get(TrapdoorBlock.POWERED))) {
                        if (var2 - (Long)var16.getValue() >= 12L) {
                           boolean var19 = !var0.getOtherEntities(
                                 null,
                                 new Box(var17).expand(0.15, 0.2, 0.15),
                                 var0x -> var0x.isAlive()
                                    && !var0x.isSpectator()
                                    && (var0x instanceof VillagerEntity || var0x instanceof IronGolemEntity || var0x instanceof PlayerEntity)
                              )
                              .isEmpty();
                           if (var19) {
                              var16.setValue(var2);
                           } else {
                              if (var18.getBlock() instanceof FenceGateBlock && (Boolean)var18.get(FenceGateBlock.OPEN)) {
                                 var0.setBlockState(var17, (BlockState)var18.with(FenceGateBlock.OPEN, false), 3);
                              } else if (var18.getBlock() instanceof DoorBlock var20 && (Boolean)var18.get(DoorBlock.OPEN)) {
                                 var20.setOpen(var1, var0, var18, var17, false);
                              } else if (var18.getBlock() instanceof TrapdoorBlock && (Boolean)var18.get(TrapdoorBlock.OPEN)) {
                                 x16779z1in3x0y(var0, var17, var18, false);
                              }

                              var15.remove();
                           }
                        }
                     } else {
                        var15.remove();
                     }
                  }
               }

               if (var4.isEmpty()) {
                  x125nzphw6b46r.remove(var0);
               }
            }
         }
      }
   }

   private static void x16779z1in3x0y(ServerWorld var0, BlockPos var1, BlockState var2, boolean var3) {
      if (var0.setBlockState(var1, (BlockState)var2.with(TrapdoorBlock.OPEN, var3), 3)) {
         BlockSetType var4 = ((xnknrz08jrie3)var2.getBlock()).x27hnusyzwjzma();
         var0.playSound(null, var1, var3 ? var4.trapdoorOpen() : var4.trapdoorClose(), SoundCategory.BLOCKS, 1.0F, 1.0F);
         var0.emitGameEvent(null, var3 ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, var1);
      }
   }

   private static boolean x2s7jlhnk5vi31(VillagerEntity var0, BlockPos var1) {
      if (!var0.isSleeping() && !var0.hasCustomer()) {
         Path var2 = var0.getNavigation().getCurrentPath();
         if (var2 != null && !var2.isFinished() && !(var0.getBlockPos().getSquaredDistance(var1) > 12.0)) {
            for (int var3 = Math.max(0, var2.getCurrentNodeIndex() - 1); var3 < Math.min(var2.getLength(), var2.getCurrentNodeIndex() + 3); var3++) {
               BlockPos var4 = var2.getNodePos(var3);
               if (Math.abs(var4.getY() - var1.getY()) <= 1 && Math.abs(var4.getX() - var1.getX()) <= 1 && Math.abs(var4.getZ() - var1.getZ()) <= 1) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean xuh5rud0uhpzw(VillagerEntity var0, BlockPos var1) {
      if (!var0.isSleeping() && !var0.hasCustomer() && !(var0.getBlockPos().getSquaredDistance(var1) > 9.0)) {
         BlockPos var2 = x1c1r5c6a13gtl.x37u4pqy3fbps(var0);
         if (var2 == null) {
            var2 = var0.getBrain().getOptionalRegisteredMemory(MemoryModuleType.WALK_TARGET).map(var0x -> var0x.getLookTarget().getBlockPos()).orElse(null);
         }

         if (var2 != null && !var2.equals(var0.getBlockPos())) {
            Vec3d var3 = var0.getEntityPos().add(0.0, 0.5, 0.0);
            Vec3d var4 = Vec3d.ofCenter(var2).add(0.0, 0.5, 0.0);
            Vec3d var5 = var4.subtract(var3);
            double var6 = var5.lengthSquared();
            if (var6 < 0.01) {
               return false;
            } else {
               Vec3d var8 = Vec3d.ofCenter(var1);
               double var9 = Math.clamp(var8.subtract(var3).dotProduct(var5) / var6, 0.0, 1.0);
               return var3.add(var5.multiply(var9)).squaredDistanceTo(var8) <= 1.5;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
