package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;

public final class x2vu3n4ffebkej extends MultiTickTask<VillagerEntity> {
   private x15kgoaq6i3m6i x3h8nrw0jzbxhj;
   private long x1txgr0c09368a;
   private long xnm14zy5rwkmf;
   private BlockPos xh8i537idrcwu;
   private BlockPos x174ibrm7q6xkj;

   public x2vu3n4ffebkej() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 600);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         this.x3h8nrw0jzbxhj = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r && !var1x.x2s37os1kht7vr())
            .orElse(null);
         return this.x3h8nrw0jzbxhj != null && x20rghnsl2yowl.x81tynoeus5lh(var1, this.x3h8nrw0jzbxhj);
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return this.x3h8nrw0jzbxhj != null
         && this.x3h8nrw0jzbxhj.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
         && x20rghnsl2yowl.x81tynoeus5lh(var1, this.x3h8nrw0jzbxhj)
         && !var2.isSleeping()
         && !var2.hasCustomer()
         && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1);
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x1txgr0c09368a = this.xnm14zy5rwkmf = var3;
      this.xh8i537idrcwu = null;
      this.x174ibrm7q6xkj = null;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x1txgr0c09368a) {
         this.x1txgr0c09368a = var3 + 10L;
         BlockPos var5 = this.x3h8nrw0jzbxhj
            .x2xqc3q79vfkw3()
            .keySet()
            .stream()
            .filter(var2x -> x20rghnsl2yowl.x2b8s98wj2zcbw(var1, this.x3h8nrw0jzbxhj, var2x))
            .min(Comparator.comparingDouble(var2.getBlockPos()::getSquaredDistance))
            .orElse(null);
         if (var5 != null) {
            this.xh8i537idrcwu = var5;
            BlockState var6 = this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().get(var5);
            BlockState var7 = var1.getBlockState(var5);
            if (var7.getBlock() == var6.getBlock()) {
               this.x22mz196jv2hz8(var1, var5);
            } else if (!var7.isAir() && !var7.isReplaceable()) {
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "ремонт: место занято");
            } else {
               BlockPos var8 = null;
               if (var6.getBlock() instanceof DoorBlock) {
                  var8 = var6.get(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? var5.up() : var5.down();
               } else if (var6.getBlock() instanceof BedBlock) {
                  var8 = var5.offset((Direction)var6.get(BedBlock.FACING), var6.get(BedBlock.PART) == BedPart.FOOT ? 1 : -1);
               }

               BlockState var9 = var8 == null ? null : this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().get(var8);
               if (var9 != null && var1.getBlockState(var8).getBlock() == var9.getBlock()) {
                  this.x22mz196jv2hz8(var1, var8);
                  var9 = null;
               }

               if (var9 == null || (var1.getBlockState(var8).isAir() || var1.getBlockState(var8).isReplaceable()) && x1dxdlnevtfbhm.x13f7be5vlapn7(var1, var8)) {
                  Item var10 = x2cvbyh5y5day0.materialItem(var6);
                  Predicate<ItemStack> var11 = x2cvbyh5y5day0.materialPredicate(var6, var10);
                  if (var10 != Items.AIR) {
                     ItemStack var12 = ItemStack.EMPTY;
                     if (var11.test(var2.getMainHandStack())) {
                        var12 = var2.getMainHandStack();
                     } else {
                        for (int var13 = 0; var13 < var2.getInventory().size(); var13++) {
                           if (var11.test(var2.getInventory().getStack(var13))) {
                              var12 = var2.getInventory().getStack(var13);
                              break;
                           }
                        }
                     }

                     if (var12.isEmpty()) {
                        Optional<BlockPos> var19 = x2n55y2tom37h2.x2ytjyhq411pf7(var1, this.x3h8nrw0jzbxhj, var2.getBlockPos(), var11);
                        if (var19.isEmpty()) {
                           x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "ремонт: нужны материалы");
                        } else if (var2.getBlockPos().getSquaredDistance((Vec3i)var19.get()) > 9.0) {
                           x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, (BlockPos)var19.get(), 0.8);
                        } else {
                           int var20 = Math.clamp(
                              this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().values().stream().filter(var1x -> x2cvbyh5y5day0.materialItem(var1x) == var10).count(),
                              1,
                              16
                           );
                           x2n55y2tom37h2.x5jd7014csotp(var1, (BlockPos)var19.get(), var11, var20).ifPresent(var3x -> {
                              ItemStack var4 = var2.getInventory().addStack(var3x);
                              if (!var4.isEmpty() && !x2n55y2tom37h2.x2cl1crde2rnxy(var1, this.x3h8nrw0jzbxhj, var4)) {
                                 var2.dropStack(var1, var4);
                              }
                           });
                        }
                     } else if (!x2cvbyh5y5day0.withinBuildReach(var2, var5)) {
                        this.x2le3dr4kogzvn(var1, var2, var5);
                     } else {
                        if (!var6.getCollisionShape(var1, var5).isEmpty()) {
                           List<Entity> var18 = var1.getOtherEntities(null, new Box(var5), var0 -> var0 instanceof LivingEntity && var0.isAlive());
                           if (!var18.isEmpty()) {
                              for (Entity var15 : var18) {
                                 if (var15 instanceof MobEntity var16) {
                                    BlockPos var17 = BlockPos.findClosest(
                                          var5, 4, 2, var2x -> x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var2x) && var2x.getSquaredDistance(var5) >= 4.0
                                       )
                                       .orElse(this.x3h8nrw0jzbxhj.core());
                                    if (x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
                                       var16.getNavigation().startMovingTo(var17.getX() + 0.5, var17.getY(), var17.getZ() + 0.5, 0.8);
                                    }
                                 }
                              }

                              return;
                           }
                        }

                        if (x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var5, var6, 2)) {
                           if (var9 != null && !x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var8, var9, 2)) {
                              var1.setBlockState(var5, var7, 2);
                           } else {
                              var12.decrement(1);
                              var1.updateNeighbors(var5, var6.getBlock());
                              if (var9 != null) {
                                 var1.updateNeighbors(var8, var9.getBlock());
                                 this.x22mz196jv2hz8(var1, var8);
                              }

                              var2.swingHand(Hand.MAIN_HAND);
                              x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "восстановление дома", 1);
                              this.x22mz196jv2hz8(var1, var5);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void x22mz196jv2hz8(ServerWorld var1, BlockPos var2) {
      this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().remove(var2);
      x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
   }

   private void x2le3dr4kogzvn(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (this.x174ibrm7q6xkj != null
         && x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, this.x174ibrm7q6xkj)
         && x2n3rio18qdgz4(this.x174ibrm7q6xkj, var3) <= 16.0
         && Math.abs(this.x174ibrm7q6xkj.getY() - var3.getY()) <= 13
         && !x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x174ibrm7q6xkj, var1.getTime())) {
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x174ibrm7q6xkj, 0.85);
      } else {
         HashSet var4 = new HashSet();

         for (int var5 = -4; var5 <= 4; var5++) {
            for (int var6 = -4; var6 <= 4; var6++) {
               if (var5 * var5 + var6 * var6 <= 16) {
                  for (int var7 = var3.getY() - 13; var7 <= var3.getY() + 3; var7++) {
                     BlockPos var8 = new BlockPos(var3.getX() + var5, var7, var3.getZ() + var6);
                     if (!var8.equals(var3)
                        && !var8.down().equals(var3)
                        && x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var8)
                        && !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var8, var1.getTime())) {
                        var4.add(var8);
                     }
                  }
               }
            }
         }

         if (!var4.isEmpty() && x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
            Path var9 = var2.getNavigation().findPathTo(var4, 0);
            if (var9 != null && var9.reachesTarget()) {
               this.x174ibrm7q6xkj = var9.getTarget();
               x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x174ibrm7q6xkj, 0.85);
            } else {
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "ремонт: поиск безопасного подхода");
            }
         }
      }
   }

   private static double x2n3rio18qdgz4(BlockPos var0, BlockPos var1) {
      double var2 = var0.getX() - var1.getX();
      double var4 = var0.getZ() - var1.getZ();
      return var2 * var2 + var4 * var4;
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x3h8nrw0jzbxhj != null
         && this.xh8i537idrcwu != null
         && this.x3h8nrw0jzbxhj.x2xqc3q79vfkw3().containsKey(this.xh8i537idrcwu)
         && var3 - this.xnm14zy5rwkmf >= 500L) {
         x20rghnsl2yowl.xqxgtse6quf4h(var1, this.x3h8nrw0jzbxhj, this.xh8i537idrcwu);
      }

      var2.getNavigation().stop();
      this.x3h8nrw0jzbxhj = null;
      this.xh8i537idrcwu = null;
      this.x174ibrm7q6xkj = null;
   }
}
