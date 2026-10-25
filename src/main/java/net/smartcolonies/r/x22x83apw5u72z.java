package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;

public final class x22x83apw5u72z extends MultiTickTask<VillagerEntity> {
   private x15kgoaq6i3m6i x2u8nlt588jgdf;
   private x2eha1n45eqf4l x14qty2zxfnsdq;
   private final x20lc7jle5mw9c x29jk2160vr00r = new x20lc7jle5mw9c();
   private long xt2mnxmxrcwkx;
   private long x48z7fhj2ntkf;

   public x22x83apw5u72z() {
      super(ImmutableMap.of(), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer()) {
         this.x2u8nlt588jgdf = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var2x -> var2x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r && x27aao31y6r2ta.xuj80syz8c60l(var1, var2x, var2))
            .orElse(null);
         return this.x2u8nlt588jgdf != null;
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return this.x2u8nlt588jgdf != null
         && this.x2u8nlt588jgdf.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
         && !var2.isSleeping()
         && !var2.hasCustomer()
         && x27aao31y6r2ta.xuj80syz8c60l(var1, this.x2u8nlt588jgdf, var2);
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x14qty2zxfnsdq = null;
      this.xt2mnxmxrcwkx = var3;
      this.x48z7fhj2ntkf = var3;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         if (this.x14qty2zxfnsdq == null || !this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x1q0gb1h69ywu7.contains(this.x14qty2zxfnsdq)) {
            this.x14qty2zxfnsdq = x27aao31y6r2ta.x2h9dz6t7qspz2(var1, this.x2u8nlt588jgdf, var2);
            this.xt2mnxmxrcwkx = var3;
            this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
         }

         if (this.x14qty2zxfnsdq != null) {
            BlockPos var5 = this.x14qty2zxfnsdq.x1oppt2nr5wv2g();
            BlockState var6 = var1.getBlockState(var5);
            if (this.x14qty2zxfnsdq.xttubpoe0ch3r() || !x29zfr9q2yep60(var1, var5) && !this.x2u8nlt588jgdf.x2o79xnwz4g9nh(var5, 1)) {
               if (!this.x14qty2zxfnsdq.xttubpoe0ch3r()
                  || var6.getBlock() == this.x14qty2zxfnsdq.x1bbgyigo2fq58().getBlock()
                     && this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var5)) {
                  if (!x1dxdlnevtfbhm.xjs779qdest9c(var1, var5)
                     && !x20rghnsl2yowl.x239t1m3hfi74d(var1, var5)
                     && (!this.x2u8nlt588jgdf.xhu9xvlie05r6(var5, 1) || this.x14qty2zxfnsdq.xttubpoe0ch3r())) {
                     if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && var6.getBlock() == this.x14qty2zxfnsdq.x1bbgyigo2fq58().getBlock()) {
                        if (this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x16wpjsjmva3od.containsKey(var5)) {
                           x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                        } else {
                           x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                        }

                        this.x14qty2zxfnsdq = null;
                     } else if (var3 - this.xt2mnxmxrcwkx > 600L) {
                        x27aao31y6r2ta.xj9o3at3dqqse(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq);
                        x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "стена: ищет другой подход");
                        this.x14qty2zxfnsdq = null;
                     } else {
                        boolean var15 = var6.isIn(BlockTags.LEAVES) || var6.isIn(BlockTags.LOGS);
                        if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && !var6.isAir() && !var6.isReplaceable() && !var15) {
                           x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                           this.x14qty2zxfnsdq = null;
                        } else {
                           double var16 = var2.getX() - (var5.getX() + 0.5);
                           double var10 = var2.getZ() - (var5.getZ() + 0.5);
                           if (var2.getBoundingBox().intersects(new Box(var5)) || var2.getBlockPos().down().equals(var5)) {
                              for (Direction var20 : Type.HORIZONTAL) {
                                 BlockPos var21 = var5.offset(var20, 2);
                                 var21 = new BlockPos(
                                    var21.getX(),
                                    var1.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, var21.getX(), var21.getZ()),
                                    var21.getZ()
                                 );
                                 if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var21)) {
                                    x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var21, 0.8);
                                    break;
                                 }
                              }
                           } else if (!(var16 * var16 + var10 * var10 > 9.0) && !(Math.abs(var2.getY() - var5.getY()) > 5.0)) {
                              var2.getNavigation().stop();
                              if (!this.x14qty2zxfnsdq.xttubpoe0ch3r() && var15) {
                                 if (this.x29jk2160vr00r.xpohy1q30uxlk(var1, var2, var5, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, var5), true)
                                    && x1dxdlnevtfbhm.x231if4rvsvptj(var1, var5, true, var2)) {
                                    this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
                                 }
                              } else if (this.x29jk2160vr00r
                                 .xpohy1q30uxlk(
                                    var1,
                                    var2,
                                    var5,
                                    this.x14qty2zxfnsdq.xttubpoe0ch3r()
                                       ? x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, var5)
                                       : x3sqkg3n6v972r.x2by5f9ehxvwk9().work.placement_ticks,
                                    this.x14qty2zxfnsdq.xttubpoe0ch3r()
                                 )) {
                                 if (this.x14qty2zxfnsdq.xttubpoe0ch3r()) {
                                    if (var2.getBoundingBox().intersects(new Box(var5)) || var2.getBlockPos().down().equals(var5)) {
                                       return;
                                    }

                                    List<ItemStack> var12 = Block.getDroppedStacks(var6, var1, var5, var1.getBlockEntity(var5), var2, var2.getMainHandStack());
                                    if (var1.breakBlock(var5, false, var2)) {
                                       for (ItemStack var14 : var12) {
                                          if (!x2n55y2tom37h2.x2cl1crde2rnxy(var1, this.x2u8nlt588jgdf, var14)) {
                                             var2.dropStack(var1, var14);
                                          }
                                       }

                                       x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                                       x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "перенос старой стены", 1);
                                       this.x14qty2zxfnsdq = null;
                                    }
                                 } else {
                                    Predicate var17 = x27aao31y6r2ta.xdo05oqfqaqzq(this.x14qty2zxfnsdq.x1bbgyigo2fq58());
                                    if (x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.x2u8nlt588jgdf, var17) <= 32) {
                                       return;
                                    }

                                    if (!var1.getOtherEntities(null, new Box(var5), var0 -> var0.isAlive() && var0 instanceof LivingEntity).isEmpty()) {
                                       return;
                                    }

                                    boolean var19 = x2n55y2tom37h2.x38ipozok9mc0x(
                                       var1,
                                       this.x2u8nlt588jgdf,
                                       () -> !x2n55y2tom37h2.x22hjebj4vmztt(var1, this.x2u8nlt588jgdf, var17, 1)
                                          ? false
                                          : var1.setBlockState(var5, this.x14qty2zxfnsdq.x1bbgyigo2fq58(), 3),
                                       List.of()
                                    );
                                    if (var19) {
                                       x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, true);
                                       x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "стена деревни", 1);
                                       this.x14qty2zxfnsdq = null;
                                    }
                                 }

                                 var2.swingHand(Hand.MAIN_HAND);
                              }
                           } else {
                              if (var3 >= this.x48z7fhj2ntkf) {
                                 x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var5, 0.8);
                                 this.x48z7fhj2ntkf = var3 + 20L;
                              }
                           }
                        }
                     }
                  } else {
                     x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                     this.x14qty2zxfnsdq = null;
                  }
               } else {
                  x27aao31y6r2ta.x2kvmlso28quo8(var1, this.x2u8nlt588jgdf, this.x14qty2zxfnsdq, false);
                  this.x14qty2zxfnsdq = null;
               }
            } else {
               for (x2eha1n45eqf4l var8 : List.copyOf(this.x2u8nlt588jgdf.x2f7z1gnnht5ab().x1q0gb1h69ywu7)) {
                  if (!var8.xttubpoe0ch3r() && var8.x1oppt2nr5wv2g().getX() == var5.getX() && var8.x1oppt2nr5wv2g().getZ() == var5.getZ()) {
                     x27aao31y6r2ta.x33x279v3webhm(var1, this.x2u8nlt588jgdf, var8);
                  }
               }

               this.x14qty2zxfnsdq = null;
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x29jk2160vr00r.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      this.x2u8nlt588jgdf = null;
      this.x14qty2zxfnsdq = null;
   }

   private static boolean x29zfr9q2yep60(ServerWorld var0, BlockPos var1) {
      for (int var2 = 0; var2 <= 4; var2++) {
         BlockPos var3 = var1.down(var2);
         if (!var0.getFluidState(var3).isEmpty()) {
            return true;
         }

         BlockState var4 = var0.getBlockState(var3);
         if (!var4.isAir() && !var4.isReplaceable()) {
            return false;
         }
      }

      return false;
   }
}
