package net.smartcolonies.r;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;

public final class IronGolemColonyHelper {
   public static final int x17pu6o7z48nge = 4;
   private IronGolemEntity x2opxfmx0yhl47;
   private BlockPos x2cyyiai9sia93;
   private BlockPos xyqlhatpmatxy;
   private long x26e0wozd7vxgm;

   private static List<IronGolemEntity> x37pu9d7f43xig(ServerWorld var0, Colony var1) {
      return var0.getEntitiesByClass(IronGolemEntity.class, new Box(var1.core()).expand(var1.radius()), LivingEntity::isAlive);
   }

   public static int x1gfvg3f4hcsus(ServerWorld var0, Colony var1) {
      return x37pu9d7f43xig(var0, var1).size();
   }

   public static int x20ln0j5i9gefl(Colony var0) {
      return Math.max(4, (var0.population() + 5) / 6);
   }

   private static long x2lwwcte00kkej(ServerWorld var0) {
      return var0.getServer().getTicks();
   }

   public static boolean x3pk6ywniogk9m(ServerWorld var0, Colony var1) {
      return x37pu9d7f43xig(var0, var1).stream().anyMatch(var0x -> var0x.getHealth() < var0x.getMaxHealth())
         || x2lwwcte00kkej(var0) >= var1.x1ljiw4h4dmxfs() && x37pu9d7f43xig(var0, var1).size() < x20ln0j5i9gefl(var1);
   }

   public static boolean x2pesumrvfap2d(ServerWorld var0, Colony var1, List<VillagerEntity> var2) {
      if (x2lwwcte00kkej(var0) >= var1.x1ljiw4h4dmxfs() && x1gfvg3f4hcsus(var0, var1) < x20ln0j5i9gefl(var1)) {
         VillagerEntity var3 = var2.stream()
            .filter(var1x -> var1x.isAlive() && !var1x.isBaby() && var1.roleOf(var1x.getUuid()) == ColonyRole.x3r8p2tvihwpph)
            .min(Comparator.comparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core())))
            .orElse(null);
         if (var3 != null && ColonyManager.x1tl8iu49onump(var0, var1, IronGolemColonyHelper::x323q5u6pfg7wx)) {
            x2aesxzdpwjel5(var0, var1, 36);
            if (ColonyManager.x1mlne8tl8k5ag(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT)) < 36) {
               return false;
            } else {
               BlockPos var4 = null;

               for (int var5 = 3; var5 <= 12 && var4 == null; var5++) {
                  for (Direction var7 : Type.HORIZONTAL) {
                     int var8 = var3.getBlockX() + var7.getOffsetX() * var5;
                     int var9 = var3.getBlockZ() + var7.getOffsetZ() * var5;
                     if (var0.getChunkManager().isChunkLoaded(var8 >> 4, var9 >> 4)) {
                        BlockPos var10 = new BlockPos(var8, var0.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, var8, var9), var9);
                        if (x2tryg7j1x110u(var0, var10) && !var1.xhu9xvlie05r6(var10, 3)) {
                           var4 = var10;
                        }
                     }
                  }
               }

               if (var4 == null) {
                  return false;
               } else {
                  IronGolemEntity var11 = (IronGolemEntity)EntityType.IRON_GOLEM.create(var0, SpawnReason.MOB_SUMMONED);
                  if (var11 == null) {
                     return false;
                  } else {
                     var11.setPosition(var4.getX() + 0.5, var4.getY(), var4.getZ() + 0.5);
                     var11.setPlayerCreated(false);
                     var11.setPersistent();
                     if (!var0.spawnEntity(var11)) {
                        return false;
                     } else {
                        ColonyManager.x22hjebj4vmztt(var0, var1, var0x -> var0x.isOf(Items.IRON_INGOT), 36);
                        ColonyManager.x22hjebj4vmztt(var0, var1, IronGolemColonyHelper::x323q5u6pfg7wx, 1);
                        var3.swingHand(Hand.MAIN_HAND);
                        var0.spawnParticles(ParticleTypes.POOF, var4.getX() + 0.5, var4.getY() + 1, var4.getZ() + 0.5, 12, 0.8, 1.0, 0.8, 0.02);
                        var1.x163kb3edimpm3(x2lwwcte00kkej(var0) + 300L);
                        ColonyManager.xchl1k1tnh3w8(var0).markDirty();
                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void x3ku3wcoma0qbb() {
      this.x2opxfmx0yhl47 = null;
      this.x2cyyiai9sia93 = null;
      this.xyqlhatpmatxy = null;
   }

   public boolean xhboo89lslsdy() {
      return this.x2opxfmx0yhl47 != null || this.x2cyyiai9sia93 != null;
   }

   public boolean xl4620yflyi80(ServerWorld var1, Colony var2, VillagerEntity var3) {
      this.x2opxfmx0yhl47 = x37pu9d7f43xig(var1, var2)
         .stream()
         .filter(var0 -> var0.getHealth() < var0.getMaxHealth())
         .min(Comparator.comparingDouble(var1x -> var1x.squaredDistanceTo(var3)))
         .orElse(null);
      if (this.x2opxfmx0yhl47 != null) {
         if (x1kf3zgkco82h3(var3) == null) {
            if (!x96n4hutou332(var3)) {
               this.x2opxfmx0yhl47 = null;
               return false;
            }

            x2aesxzdpwjel5(var1, var2, 1);
            Optional var15 = ColonyManager.x3lxwdirnn77qc(var1, var2, var0 -> var0.isOf(Items.IRON_INGOT));
            if (var15.isEmpty()) {
               this.x2opxfmx0yhl47 = null;
               return false;
            }

            var3.getInventory().addStack((ItemStack)var15.get());
         }

         return true;
      } else if (x2lwwcte00kkej(var1) >= var2.x1ljiw4h4dmxfs() && x37pu9d7f43xig(var1, var2).size() < x20ln0j5i9gefl(var2)) {
         if (!ColonyManager.x1tl8iu49onump(var1, var2, IronGolemColonyHelper::x323q5u6pfg7wx)) {
            return false;
         } else {
            x2aesxzdpwjel5(var1, var2, 36);
            if (ColonyManager.x1mlne8tl8k5ag(var1, var2, var0 -> var0.isOf(Items.IRON_INGOT)) < 36) {
               return false;
            } else {
               BlockPos var4 = null;
               double var5 = Double.MAX_VALUE;

               for (int var7 = -32; var7 <= 32; var7++) {
                  for (int var8 = -32; var8 <= 32; var8++) {
                     if (var7 * var7 + var8 * var8 >= 16 && var7 * var7 + var8 * var8 <= 1024) {
                        int var9 = var2.core().getX() + var7;
                        int var10 = var2.core().getZ() + var8;
                        if (var1.getChunkManager().isChunkLoaded(var9 >> 4, var10 >> 4)) {
                           BlockPos var11 = new BlockPos(var9, var1.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, var9, var10), var10);
                           BlockPos var12 = x3mty4h6w5e7b3(var1, var11);
                           if (x2tryg7j1x110u(var1, var11) && var12 != null && !var2.xhu9xvlie05r6(var11, 3)) {
                              double var13 = var11.getSquaredDistance(var3.getBlockPos()) + 0.2 * var11.getSquaredDistance(var2.core());
                              if (var13 < var5) {
                                 var4 = var11.toImmutable();
                                 this.xyqlhatpmatxy = var12;
                                 var5 = var13;
                              }
                           }
                        }
                     }
                  }
               }

               if (var4 != null) {
                  this.x2cyyiai9sia93 = var4;
                  this.x26e0wozd7vxgm = var1.getTime() + 60L;
                  return true;
               } else {
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public boolean x21yp5lsgxd9co(ServerWorld var1, Colony var2, VillagerEntity var3, long var4) {
      if (this.x2opxfmx0yhl47 != null) {
         if (!this.x2opxfmx0yhl47.isAlive() || this.x2opxfmx0yhl47.getHealth() >= this.x2opxfmx0yhl47.getMaxHealth()) {
            this.x3ku3wcoma0qbb();
            return true;
         } else if (var3.squaredDistanceTo(this.x2opxfmx0yhl47) > 9.0) {
            if (var4 % 10L == 0L && ColonyManager.x5ll9elqpk1a4(var1)) {
               var3.getNavigation().startMovingTo(this.x2opxfmx0yhl47, 0.8);
            }

            return false;
         } else {
            ItemStack var7 = x1kf3zgkco82h3(var3);
            if (var7 != null) {
               this.x2opxfmx0yhl47.heal(25.0F);
               var7.decrement(1);
               var3.swingHand(Hand.MAIN_HAND);
               var1.playSound(null, this.x2opxfmx0yhl47.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_REPAIR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            }

            this.x3ku3wcoma0qbb();
            return true;
         }
      } else if (this.x2cyyiai9sia93 != null) {
         if (!x2tryg7j1x110u(var1, this.x2cyyiai9sia93)) {
            this.x3ku3wcoma0qbb();
            return true;
         } else if (var3.getBlockPos().getSquaredDistance(this.xyqlhatpmatxy) > 4.0) {
            this.x26e0wozd7vxgm = var4 + 60L;
            if (var4 % 10L == 0L && ColonyManager.x5ll9elqpk1a4(var1)) {
               ColonyNavigationHelper.x2wzylgizxltef(var1, var3, this.xyqlhatpmatxy, 0.8);
            }

            return false;
         } else {
            var3.getNavigation().stop();
            if (var4 % 15L == 0L) {
               var3.swingHand(Hand.MAIN_HAND);
            }

            if (var4 < this.x26e0wozd7vxgm) {
               return false;
            } else if (x2lwwcte00kkej(var1) >= var2.x1ljiw4h4dmxfs()
               && x37pu9d7f43xig(var1, var2).size() < x20ln0j5i9gefl(var2)
               && ColonyManager.x1mlne8tl8k5ag(var1, var2, var0 -> var0.isOf(Items.IRON_INGOT)) >= 36
               && ColonyManager.x1tl8iu49onump(var1, var2, IronGolemColonyHelper::x323q5u6pfg7wx)) {
               IronGolemEntity var6 = (IronGolemEntity)EntityType.IRON_GOLEM.create(var1, SpawnReason.MOB_SUMMONED);
               if (var6 != null) {
                  var6.setPosition(this.x2cyyiai9sia93.getX() + 0.5, this.x2cyyiai9sia93.getY(), this.x2cyyiai9sia93.getZ() + 0.5);
                  var6.setPlayerCreated(false);
                  var6.setPersistent();
                  if (var1.spawnEntity(var6)) {
                     ColonyManager.x22hjebj4vmztt(var1, var2, var0 -> var0.isOf(Items.IRON_INGOT), 36);
                     ColonyManager.x22hjebj4vmztt(var1, var2, IronGolemColonyHelper::x323q5u6pfg7wx, 1);
                     var2.x163kb3edimpm3(x2lwwcte00kkej(var1) + 200L);
                     ColonyManager.xchl1k1tnh3w8(var1).markDirty();
                  }
               }

               this.x3ku3wcoma0qbb();
               return true;
            } else {
               this.x3ku3wcoma0qbb();
               return true;
            }
         }
      } else {
         return true;
      }
   }

   private static boolean x2tryg7j1x110u(ServerWorld var0, BlockPos var1) {
      for (int var2 = 0; var2 <= 1; var2++) {
         for (int var3 = 0; var3 <= 1; var3++) {
            BlockPos var4 = var1.add(var2, -1, var3);
            if (!var0.getChunkManager().isChunkLoaded(var4.getX() >> 4, var4.getZ() >> 4)) {
               return false;
            }

            if (!var0.getBlockState(var4).isSideSolidFullSquare(var0, var4, Direction.UP) || !var0.getFluidState(var4).isEmpty()) {
               return false;
            }

            for (int var5 = 0; var5 < 3; var5++) {
               if (!var0.getBlockState(var1.add(var2, var5, var3)).isAir()) {
                  return false;
               }
            }
         }
      }

      return var0.getOtherEntities(null, new Box(var1).expand(0.6, 0.0, 0.6).stretch(0.0, 2.0, 0.0)).isEmpty();
   }

   private static BlockPos x3mty4h6w5e7b3(ServerWorld var0, BlockPos var1) {
      for (int var2 = 2; var2 <= 4; var2++) {
         for (Direction var4 : Type.HORIZONTAL) {
            BlockPos var5 = var1.offset(var4, var2);
            if (var0.getChunkManager().isChunkLoaded(var5.getX() >> 4, var5.getZ() >> 4)
               && var0.getFluidState(var5).isEmpty()
               && var0.getFluidState(var5.down()).isEmpty()
               && var0.getBlockState(var5).getCollisionShape(var0, var5).isEmpty()
               && var0.getBlockState(var5.up()).getCollisionShape(var0, var5.up()).isEmpty()
               && var0.getBlockState(var5.down()).isSideSolidFullSquare(var0, var5.down(), Direction.UP)) {
               return var5.toImmutable();
            }
         }
      }

      return null;
   }

   private static boolean x323q5u6pfg7wx(ItemStack var0) {
      return var0.isOf(Items.PUMPKIN) || var0.isOf(Items.CARVED_PUMPKIN);
   }

   private static ItemStack x1kf3zgkco82h3(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         if (var0.getInventory().getStack(var1).isOf(Items.IRON_INGOT)) {
            return var0.getInventory().getStack(var1);
         }
      }

      return null;
   }

   private static boolean x96n4hutou332(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         if (var0.getInventory().getStack(var1).isEmpty()) {
            return true;
         }
      }

      return false;
   }

   private static void x2aesxzdpwjel5(ServerWorld var0, Colony var1, int var2) {
      VillagerBucketRefillHelper.x7uj9zeib8utf(var0, var1, var2);
   }
}
