package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap.Type;

public final class RancherAnimalTask extends MultiTickTask<VillagerEntity> {
   public static final int x2p4azrgz36t1c = 5;
   private static final int x34264qhqiplto = 10;
   public static final int x361h5d7gkiq12 = 32;
   private final Map<UUID, Long> x2nroh3xvht8t9 = new HashMap<>();
   private final Map<UUID, Long> x1tnak5qfia21t = new HashMap<>();
   private Colony x9b03bdckmlwm;
   private ColonyWallState x3m757n03ci4pu;
   private BlockPos x1bm3tnyu1jatt;
   private AnimalEntity x3f72o6zqrgoi5;
   private Item xmj689j1i2yoe;
   private long x2ch2ssr886gm8;
   private long x2numq6ar051rx;
   private long x1bkk3ozc9rnb9;
   private long x2ptpwjdc895we;
   private double x10nnii6m4pbue;
   private int x2ueth4fvnxw81;
   private int xhdr6wfedevn7;
   private ColonyWallState x2owmu2l8oehuk;
   private boolean x17tcufg7yn81;
   private boolean x3213rjbn8ppz4;

   public RancherAnimalTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   private static long xd01akzx86p4c(ServerWorld var0) {
      return var0.getTime();
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && xd01akzx86p4c(var1) >= this.x2numq6ar051rx) {
         this.x9b03bdckmlwm = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == ColonyRole.xeoehg87qwfrn && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x9b03bdckmlwm != null;
      } else {
         return false;
      }
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      long var5 = xd01akzx86p4c(var1);
      this.x3m757n03ci4pu = ColonyWallState.x1fs6jf9ovfs9g;
      this.x1bm3tnyu1jatt = this.x9b03bdckmlwm.x329rgb9lpsari().orElse(null);
      this.x3f72o6zqrgoi5 = null;
      this.xmj689j1i2yoe = null;
      this.x2ueth4fvnxw81 = 0;
      this.xhdr6wfedevn7 = 0;
      this.x2owmu2l8oehuk = null;
      this.x3213rjbn8ppz4 = false;
      this.x2ch2ssr886gm8 = this.x1bkk3ozc9rnb9 = var5;
      this.x1tnak5qfia21t.entrySet().removeIf(var2x -> var2x.getValue() <= var5);
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x9b03bdckmlwm != null
         && this.x9b03bdckmlwm.roleOf(var2.getUuid()) == ColonyRole.xeoehg87qwfrn
         && !this.x3213rjbn8ppz4;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      var3 = xd01akzx86p4c(var1);
      if (this.x3m757n03ci4pu != this.x2owmu2l8oehuk) {
         this.x2owmu2l8oehuk = this.x3m757n03ci4pu;
         this.xhdr6wfedevn7 = 0;
      } else {
         this.xhdr6wfedevn7++;
      }

      if (var3 >= this.x2ch2ssr886gm8) {
         this.x2ch2ssr886gm8 = var3 + 5L;
         if (this.x3m757n03ci4pu == ColonyWallState.x1fs6jf9ovfs9g || this.x3m757n03ci4pu == ColonyWallState.xilv2h3tr1fau) {
            this.x3vp1wn6x4mhdg(var1, var2, var3);
         } else if (this.x3m757n03ci4pu == ColonyWallState.x3aod5iqvtcvuf) {
            this.x1hxapso7q6mpf(var1, var2, var3);
         } else if (this.x2ueth4fvnxw81 < 12 && x26v4oc4f99cpr(var2) < 16) {
            this.x1i4hyghtu35ce(var1, var2);
            if (this.x3m757n03ci4pu == ColonyWallState.xpthcbuap2szb) {
               this.x1jph62phzfvq9(var1, var2, var3);
            } else if (this.x3f72o6zqrgoi5 != null && this.x3f72o6zqrgoi5.isAlive() && x2rzbu6s47zw2x(this.x3f72o6zqrgoi5)) {
               var2.getBrain().remember(ColonyMemoryModules.x2ppem7wipzctm, this.x3f72o6zqrgoi5.getBlockPos());
               if (this.x3m757n03ci4pu == ColonyWallState.x3qduszygbdbqh) {
                  this.x2w3c3qpcb0ux6(var1, var2, var3);
               } else if (this.x2745j5ue4rw4q(this.x3f72o6zqrgoi5) && !this.x2745j5ue4rw4q(var2)) {
                  BlockPos var5 = this.x3u1km6otsxwtp();
                  if (this.xhdr6wfedevn7 > 400) {
                     this.x3f72o6zqrgoi5 = null;
                     this.x3m757n03ci4pu = ColonyWallState.xilv2h3tr1fau;
                     this.x1bkk3ozc9rnb9 = var3;
                  } else {
                     if (var2.getBlockPos().getSquaredDistance(var5) > 9.0) {
                        this.x3v731fax5g1nx(var1);
                        this.x1yqub9v49uk07(var1, var2, var5.north(2), 0.72);
                     } else {
                        this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
                        this.x3p0xf9wskmc24(var1);
                        this.x1yqub9v49uk07(var1, var2, this.x1bm3tnyu1jatt, 0.72);
                     }
                  }
               } else if (var2.squaredDistanceTo(this.x3f72o6zqrgoi5) > 9.0) {
                  this.x1yqub9v49uk07(var1, var2, this.x3f72o6zqrgoi5.getBlockPos(), 0.85);
               } else {
                  this.xv7bmx8jq0onf(var1, var2, this.x3f72o6zqrgoi5, var3);
                  this.x3v731fax5g1nx(var1);
                  this.x3f72o6zqrgoi5 = null;
                  this.x3m757n03ci4pu = ColonyWallState.xpthcbuap2szb;
                  this.x1bkk3ozc9rnb9 = var3;
               }
            } else {
               this.x3m757n03ci4pu = ColonyWallState.xpthcbuap2szb;
            }
         } else {
            this.x3m757n03ci4pu = ColonyWallState.xilv2h3tr1fau;
            this.x1bkk3ozc9rnb9 = var3;
            this.x3f72o6zqrgoi5 = null;
         }
      }
   }

   private void x3vp1wn6x4mhdg(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x1bm3tnyu1jatt != null && this.x2745j5ue4rw4q(var2)) {
         this.x1f00g882945e(var1, null);
         this.x3p0xf9wskmc24(var1);
         BlockPos var12 = this.x3u1km6otsxwtp().north();
         this.x1yqub9v49uk07(var1, var2, var12.north(), 0.7);
         if (var3 - this.x1bkk3ozc9rnb9 > 400L) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 100L;
         }
      } else {
         this.x3v731fax5g1nx(var1);
         Optional var5 = ColonyManager.x3jrfw2d3yykeu(var1, this.x9b03bdckmlwm, var2, StorageCategory.x2uujme1um3d1w);
         if (var5.isEmpty()) {
            this.x3213rjbn8ppz4 = true;
         } else {
            var2.getBrain().remember(ColonyMemoryModules.x3o547clcv9kaw, (BlockPos)var5.get());
            if (var2.getBlockPos().getSquaredDistance((Vec3i)var5.get()) > 9.0) {
               this.x1yqub9v49uk07(var1, var2, (BlockPos)var5.get(), 0.85);
            } else {
               ColonyManager.x1asz375yez9sw(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.EGG), 32);
               this.x2kdf6zxkbv4uj(var1, var2);
               ColonyStorageAuditor.x2m8lyhoez9v7c(var1, this.x9b03bdckmlwm);
               ColonyStorageAuditor.x3tihjtlnbfyrs(var2.getInventory(), Math.max(0, 4 - ColonyStorageAuditor.x31lz6msk67uxc(var1, this.x9b03bdckmlwm)));

               for (int var6 = 0; var6 < var2.getInventory().size(); var6++) {
                  if (x3e0xb3lfomiyr(var2.getInventory().getStack(var6))) {
                     ColonyChatFeedback.x25cotfj45r525(var1, this.x9b03bdckmlwm, var2, var2.getInventory().getStack(var6));
                  }
               }

               int var13 = 2;

               for (int var7 = 0; var7 < var2.getInventory().size(); var7++) {
                  ItemStack var8 = var2.getInventory().getStack(var7);
                  if (var8.isOf(Items.BUCKET)) {
                     int var9 = Math.min(var13, var8.getCount());
                     var13 -= var9;
                     int var10 = var8.getCount() - var9;
                     if (var10 != 0) {
                        ItemStack var11 = var8.copyWithCount(var10);
                        ColonyChatFeedback.x25cotfj45r525(var1, this.x9b03bdckmlwm, var2, var11);
                        var8.decrement(var10 - var11.getCount());
                     }
                  }
               }

               this.x27sd5416kpqqj(var1, var2);
               this.x33mnq7ijq2l5z(var1, var2);
               int var14 = Math.max(0, 4 - ColonyStorageAuditor.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm));
               if (var14 > 0) {
                  this.x31djqoo9oci6(var1, var2, Math.min(2, var14));
               }

               this.x12fl4tcpl6f1x(var1, var2, Items.WHEAT, 8);
               this.x12fl4tcpl6f1x(var1, var2, Items.WHEAT_SEEDS, 8);
               this.x12fl4tcpl6f1x(var1, var2, Items.CARROT, 8);
               if (this.x3m757n03ci4pu == ColonyWallState.xilv2h3tr1fau) {
                  this.x3213rjbn8ppz4 = true;
                  this.x2numq6ar051rx = var3 + 40L;
               } else {
                  this.x3m757n03ci4pu = ColonyWallState.x3aod5iqvtcvuf;
                  this.x1bkk3ozc9rnb9 = var3;
               }
            }
         }
      }
   }

   private void x1hxapso7q6mpf(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x1bm3tnyu1jatt == null) {
         this.x1bm3tnyu1jatt = this.x2hjrd57df3dbn(var1).orElse(null);
         if (this.x1bm3tnyu1jatt == null) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 400L;
            return;
         }

         this.x9b03bdckmlwm.x146t7j3h7t042(this.x1bm3tnyu1jatt);
         ColonyManager.xchl1k1tnh3w8(var1).markDirty();
      }

      List<BlockPos> var5 = this.x2upxz7dqb7edt(var1, this.x1bm3tnyu1jatt);
      if (!var5.isEmpty()) {
         var5.removeIf(var1x -> !var1.getBlockState(var1x).isReplaceable() || !ColonyProtectionUtils.x13f7be5vlapn7(var1, var1x));
         if (var5.isEmpty()) {
            ColonyChatFeedback.x1qd0mutwsr3sc(var2, "периметр загона заблокирован");
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 600L;
         } else if (!ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.isIn(ItemTags.LOGS), 1)) {
            this.x3213rjbn8ppz4 = true;
            this.x2numq6ar051rx = var3 + 200L;
         } else {
            int var10 = 0;

            for (int var11 = 0; var11 < Math.min(3, var5.size()); var11++) {
               BlockPos var8 = (BlockPos)var5.get(var11);
               BlockState var9 = var8.equals(this.x3u1km6otsxwtp())
                  ? (BlockState)Blocks.OAK_FENCE_GATE.getDefaultState().with(FenceGateBlock.FACING, Direction.SOUTH)
                  : Blocks.OAK_FENCE.getDefaultState();
               if (var1.getBlockState(var8).isReplaceable() && ColonyProtectionUtils.x1bq0f8n53gtob(var1, var8, var9, 3)) {
                  var10++;
               }
            }

            if (var10 == 0) {
               VillagerBucketRefillHelper.xqe8twcjwxn55(var1, this.x9b03bdckmlwm, var2, new ItemStack(Items.OAK_LOG));
            } else {
               ColonyChatFeedback.x2t98zlq4lu49t(var2, "загон", var10);
            }

            var2.swingHand(Hand.MAIN_HAND);
            this.x2ueth4fvnxw81++;
            this.x2ch2ssr886gm8 = var3 + 8L;
         }
      } else {
         List var6 = this.x2ujvd9g07uaij(var1);
         if (var6.isEmpty()) {
            this.x3v731fax5g1nx(var1);
            this.x3m757n03ci4pu = ColonyWallState.xpthcbuap2szb;
            this.x1bkk3ozc9rnb9 = var3;
            this.x2ueth4fvnxw81 = 0;
         } else {
            for (int var7 = 0; var7 < Math.min(3, var6.size()); var7++) {
               ColonyProtectionUtils.x231if4rvsvptj(var1, (BlockPos)var6.get(var7), false, var2);
            }

            var2.swingHand(Hand.MAIN_HAND);
            this.x2ch2ssr886gm8 = var3 + 8L;
         }
      }
   }

   private void x1jph62phzfvq9(ServerWorld var1, VillagerEntity var2, long var3) {
      List<AnimalEntity> var5 = this.x3q7ywqh31h55d(var1);
      Optional var6 = var5.stream()
         .filter(var1x -> !this.x2745j5ue4rw4q(var1x))
         .filter(var3x -> this.x1tnak5qfia21t.getOrDefault(var3x.getUuid(), 0L) <= var3)
         .filter(var2x -> this.xpn2n0o78r82j(var5, var2x) < 10)
         .filter(var1x -> x1zya4i1rpwuhr(var1x) != null && xq6j4f862i6km(var2, x1zya4i1rpwuhr(var1x)))
         .min(Comparator.<AnimalEntity>comparingInt(var0 -> var0 instanceof SheepEntity ? 0 : 1).thenComparingDouble(var2::squaredDistanceTo));
      if (var6.isPresent()) {
         this.x2fj0r64lb2ked(var1, var2, (AnimalEntity)var6.get(), var3);
      } else {
         Optional var7 = var5.stream()
            .filter(var5x -> this.x2745j5ue4rw4q(var5x) && this.xgwotc5f7rrat(var1, var2, var5x, var3))
            .min(Comparator.comparingDouble(var2::squaredDistanceTo));
         if (var7.isPresent()) {
            this.x3f72o6zqrgoi5 = (AnimalEntity)var7.get();
            this.x3m757n03ci4pu = ColonyWallState.x2pks19bffk66q;
            this.x1bkk3ozc9rnb9 = var3;
         } else if (this.x1gx3m3f1j5h9e(var1, var2, var5, var3)) {
            this.x2ueth4fvnxw81++;
            ColonyChatFeedback.x2t98zlq4lu49t(var2, "разведение животных", 1);
            this.x2ch2ssr886gm8 = var3 + 40L;
         } else if (x26v4oc4f99cpr(var2) > 0) {
            this.x3m757n03ci4pu = ColonyWallState.xilv2h3tr1fau;
            this.x1bkk3ozc9rnb9 = var3;
         } else {
            this.x3m757n03ci4pu = ColonyWallState.xilv2h3tr1fau;
            this.x1bkk3ozc9rnb9 = var3;
         }
      }
   }

   private void x2w3c3qpcb0ux6(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x2745j5ue4rw4q(this.x3f72o6zqrgoi5) && !this.x3f72o6zqrgoi5.getBoundingBox().intersects(new Box(this.x3u1km6otsxwtp()).expand(0.5))) {
         this.x3dxvfalesg3gp(var1, var2, true);
      } else if (!this.xmdm1vdfbqp32(var1, var2)) {
         this.x1tnak5qfia21t.put(this.x3f72o6zqrgoi5.getUuid(), var3 + 400L);
         this.x3dxvfalesg3gp(var1, var2, false);
      } else {
         IronGolemDefendColonyGoal.follow(this.x3f72o6zqrgoi5, var2, this.xmj689j1i2yoe);
         if (!this.x17tcufg7yn81 && this.x2745j5ue4rw4q(var2)) {
            this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
            this.x3p0xf9wskmc24(var1);
            this.x1yqub9v49uk07(var1, var2, this.x3u1km6otsxwtp().north(2), 0.65);
         } else {
            if (!this.x17tcufg7yn81) {
               this.x17tcufg7yn81 = true;
               this.x3v731fax5g1nx(var1);
            }

            double var5 = this.x3f72o6zqrgoi5.getBlockPos().getSquaredDistance(this.x1bm3tnyu1jatt);
            if (var5 + 1.0 < this.x10nnii6m4pbue) {
               this.x10nnii6m4pbue = var5;
               this.x2ptpwjdc895we = var3;
            }

            if (this.xhdr6wfedevn7 <= 2000 && var3 - this.x2ptpwjdc895we <= 600L) {
               var2.getLookControl().lookAt(this.x3f72o6zqrgoi5, 30.0F, 30.0F);
               this.x3f72o6zqrgoi5.getLookControl().lookAt(var2, 30.0F, 30.0F);
               if (var2.squaredDistanceTo(this.x3f72o6zqrgoi5) > 36.0) {
                  this.x3v731fax5g1nx(var1);
                  this.x1yqub9v49uk07(var1, var2, this.x3f72o6zqrgoi5.getBlockPos(), 0.7);
               } else if (var2.squaredDistanceTo(this.x3f72o6zqrgoi5) > 16.0) {
                  var2.getNavigation().stop();
               } else {
                  BlockPos var7 = this.x3u1km6otsxwtp();
                  if (var2.getBlockPos().getSquaredDistance(var7) > 9.0) {
                     this.x3v731fax5g1nx(var1);
                     this.x1yqub9v49uk07(var1, var2, var7.north(2), 0.62);
                  } else {
                     this.x1f00g882945e(var1, this.x3f72o6zqrgoi5);
                     this.x3p0xf9wskmc24(var1);
                     this.x1yqub9v49uk07(var1, var2, this.x1bm3tnyu1jatt.south(2), 0.62);
                  }
               }
            } else {
               ColonyChatFeedback.x1qd0mutwsr3sc(var2, "животному недоступен путь в загон: выбран другой маршрут/животное");
               this.x1tnak5qfia21t.put(this.x3f72o6zqrgoi5.getUuid(), var3 + 2400L);
               this.x3dxvfalesg3gp(var1, var2, false);
            }
         }
      }
   }

   private void x2fj0r64lb2ked(ServerWorld var1, VillagerEntity var2, AnimalEntity var3, long var4) {
      Item var6 = x1zya4i1rpwuhr(var3);
      this.x2vvpgkqtpg958(var1, var2);
      this.xmj689j1i2yoe = var6;
      if (!this.xmdm1vdfbqp32(var1, var2)) {
         this.xmj689j1i2yoe = null;
         this.x3m757n03ci4pu = ColonyWallState.xpthcbuap2szb;
      } else {
         this.x3f72o6zqrgoi5 = var3;
         this.x3m757n03ci4pu = ColonyWallState.x3qduszygbdbqh;
         this.x1bkk3ozc9rnb9 = this.x2ptpwjdc895we = var4;
         this.x17tcufg7yn81 = !this.x2745j5ue4rw4q(var2);
         this.x10nnii6m4pbue = var3.getBlockPos().getSquaredDistance(this.x1bm3tnyu1jatt);
      }
   }

   private void x3dxvfalesg3gp(ServerWorld var1, VillagerEntity var2, boolean var3) {
      if (this.x3f72o6zqrgoi5 != null) {
         IronGolemDefendColonyGoal.release(this.x3f72o6zqrgoi5);
         this.x3f72o6zqrgoi5.getNavigation().stop();
      }

      if (var3) {
         this.x1f00g882945e(var1, null);
      }

      this.x2vvpgkqtpg958(var1, var2);
      this.x3v731fax5g1nx(var1);
      this.x3f72o6zqrgoi5 = null;
      if (var3) {
         this.x2ueth4fvnxw81++;
         ColonyChatFeedback.x2t98zlq4lu49t(var2, "животное приведено в загон", 1);
         this.x3m757n03ci4pu = ColonyWallState.xpthcbuap2szb;
      } else {
         this.x3m757n03ci4pu = ColonyWallState.xilv2h3tr1fau;
         this.x1bkk3ozc9rnb9 = var1.getTime();
      }
   }

   private void xv7bmx8jq0onf(ServerWorld var1, VillagerEntity var2, AnimalEntity var3, long var4) {
      if (var3 instanceof SheepEntity var6 && var6.isShearable() && xq6j4f862i6km(var2, Items.SHEARS)) {
         var6.setSheared(true);
         this.x2xg4t4fgfn92z(var1, var2, new ItemStack(x3k9mdozxqhn1u(var6), 1 + var1.random.nextInt(3)));
         this.x2nroh3xvht8t9.put(var3.getUuid(), var4 + 1200L);
         this.x2ueth4fvnxw81++;
         ColonyChatFeedback.x2t98zlq4lu49t(var2, "стрижка", 1);
         var2.swingHand(Hand.MAIN_HAND);
      } else if (var3 instanceof ChickenEntity) {
         this.x2xg4t4fgfn92z(var1, var2, new ItemStack(Items.EGG));
         this.x2nroh3xvht8t9.put(var3.getUuid(), var4 + 1200L);
         this.x2ueth4fvnxw81++;
         ColonyChatFeedback.x2t98zlq4lu49t(var2, "сбор яиц", 1);
         var2.swingHand(Hand.MAIN_HAND);
      } else {
         if ((var3 instanceof CowEntity || var3 instanceof GoatEntity)
            && ColonyStorageAuditor.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm) < 4
            && x165qssxeku6kz(var2, Items.BUCKET)) {
            this.x2xg4t4fgfn92z(var1, var2, new ItemStack(Items.MILK_BUCKET));
            this.x2nroh3xvht8t9.put(var3.getUuid(), var4 + 2400L);
            this.x2ueth4fvnxw81++;
            ColonyChatFeedback.x2t98zlq4lu49t(var2, "дойка", 1);
            var2.swingHand(Hand.MAIN_HAND);
         }
      }
   }

   private boolean x1gx3m3f1j5h9e(ServerWorld var1, VillagerEntity var2, List<AnimalEntity> var3, long var4) {
      if (this.x1yv8cepr980a2(var3) >= 40) {
         return false;
      } else {
         for (int var6 = 0; var6 < var3.size(); var6++) {
            AnimalEntity var7 = (AnimalEntity)var3.get(var6);
            if (this.x2745j5ue4rw4q(var7) && !var7.isBaby() && var7.getBreedingAge() == 0) {
               Item var8 = x1zya4i1rpwuhr(var7);
               if (var8 != null) {
                  for (int var9 = var6 + 1; var9 < var3.size(); var9++) {
                     AnimalEntity var10 = (AnimalEntity)var3.get(var9);
                     if (this.x2745j5ue4rw4q(var10)
                        && !var10.isBaby()
                        && var10.getBreedingAge() == 0
                        && var7.getType() == var10.getType()
                        && this.x2idhnuuiroo7r(var1, var2, var8, 2)) {
                        var7.setLoveTicks(600);
                        var10.setLoveTicks(600);
                        var7.breed(var1, var10);
                        var1.spawnParticles(
                           ParticleTypes.HEART,
                           this.x1bm3tnyu1jatt.getX() + 0.5,
                           this.x1bm3tnyu1jatt.getY() + 1,
                           this.x1bm3tnyu1jatt.getZ() + 0.5,
                           8,
                           2.0,
                           0.5,
                           2.0,
                           0.0
                        );
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      }
   }

   private boolean xgwotc5f7rrat(ServerWorld var1, VillagerEntity var2, AnimalEntity var3, long var4) {
      if (var3.isBaby() || this.x2nroh3xvht8t9.getOrDefault(var3.getUuid(), 0L) > var4) {
         return false;
      } else if (var3 instanceof SheepEntity var6) {
         return var6.isShearable() && xq6j4f862i6km(var2, Items.SHEARS);
      } else {
         return var3 instanceof ChickenEntity
            ? this.x8bmwjivt7kr4(var1, var2) < 32
            : (var3 instanceof CowEntity || var3 instanceof GoatEntity)
               && xq6j4f862i6km(var2, Items.BUCKET)
               && ColonyStorageAuditor.x2m95xmyunf3vb(var1, this.x9b03bdckmlwm) < 4;
      }
   }

   private void x1i4hyghtu35ce(ServerWorld var1, VillagerEntity var2) {
      if (this.x1bm3tnyu1jatt != null) {
         for (ItemEntity var4 : var1.getEntitiesByClass(ItemEntity.class, new Box(this.x1bm3tnyu1jatt).expand(6.0, 2.0, 6.0), Entity::isAlive)) {
            if (x3e0xb3lfomiyr(var4.getStack()) && ResourceDeliveryService.x2t0sm1vfzey0p(var1, this.x9b03bdckmlwm, var2, var4, ColonyRole.xeoehg87qwfrn)) {
               int var5 = var4.getStack().isOf(Items.EGG) ? Math.max(0, 32 - this.x8bmwjivt7kr4(var1, var2)) : Integer.MAX_VALUE;
               ResourceDeliveryService.x105bgml33wsy(var1, this.x9b03bdckmlwm, var2, var4, ColonyRole.xeoehg87qwfrn, var5);
            }
         }
      }
   }

   private Optional<BlockPos> x2hjrd57df3dbn(ServerWorld var1) {
      for (int var2 = 16; var2 <= 40; var2++) {
         for (int var3 = 0; var3 < var2 * 8; var3++) {
            int var4 = var3 % (var2 * 8);
            int var5;
            int var6;
            if (var4 < var2 * 2) {
               var5 = -var2 + var4;
               var6 = -var2;
            } else if (var4 < var2 * 4) {
               var5 = var2;
               var6 = -var2 + (var4 - var2 * 2);
            } else if (var4 < var2 * 6) {
               var5 = var2 - (var4 - var2 * 4);
               var6 = var2;
            } else {
               var5 = -var2;
               var6 = var2 - (var4 - var2 * 6);
            }

            int var7 = this.x9b03bdckmlwm.core().getX() + var5;
            int var8 = this.x9b03bdckmlwm.core().getZ() + var6;
            if (var1.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
               BlockPos var9 = new BlockPos(var7, var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var7, var8), var8);
               if (this.x29zcp8wrpahei(var1, var9)) {
                  return Optional.of(var9);
               }
            }
         }
      }

      return Optional.empty();
   }

   private boolean x29zcp8wrpahei(ServerWorld var1, BlockPos var2) {
      for (BlockPos var4 : BlockPos.iterate(var2.add(-5, 0, -5), var2.add(5, 1, 5))) {
         if (var1.getChunkManager().isChunkLoaded(var4.getX() >> 4, var4.getZ() >> 4)
            && !ColonyTimeService.x3gqyq7yie9jxr(var1, var4)
            && !this.x9b03bdckmlwm.xhu9xvlie05r6(var4, 2)) {
            if ((var1.getBlockState(var4).isAir() || var1.getBlockState(var4).isReplaceable()) && var1.getBlockEntity(var4) == null) {
               BlockState var5 = var1.getBlockState(new BlockPos(var4.getX(), var2.getY() - 1, var4.getZ()));
               if (var5.isSideSolidFullSquare(var1, new BlockPos(var4.getX(), var2.getY() - 1, var4.getZ()), Direction.UP)
                  && !var5.isOf(Blocks.DIRT_PATH)
                  && !var5.isOf(Blocks.FARMLAND)
                  && !var5.isIn(BlockTags.PLANKS)) {
                  continue;
               }

               return false;
            }

            return false;
         }

         return false;
      }

      for (ConstructionJob var7 : this.x9b03bdckmlwm.x1ja61emeln2hc()) {
         if (var7.x24kbqv6g4kkrg()
            .intersects(new BlockBox(var2.getX() - 5 - 2, var2.getY() - 2, var2.getZ() - 5 - 2, var2.getX() + 5 + 2, var2.getY() + 3, var2.getZ() + 5 + 2))) {
            return false;
         }
      }

      return true;
   }

   private List<BlockPos> x2upxz7dqb7edt(ServerWorld var1, BlockPos var2) {
      ArrayList var3 = new ArrayList();

      for (int var4 = -5; var4 <= 5; var4++) {
         for (int var5 = -5; var5 <= 5; var5++) {
            if (Math.abs(var4) == 5 || Math.abs(var5) == 5) {
               BlockPos var6 = var2.add(var4, 0, var5);
               BlockState var7 = var1.getBlockState(var6);
               if (var6.equals(this.x3u1km6otsxwtp()) ? !var7.isOf(Blocks.OAK_FENCE_GATE) : !var7.isOf(Blocks.OAK_FENCE)) {
                  var3.add(var6);
               }
            }
         }
      }

      return var3;
   }

   private List<BlockPos> x2ujvd9g07uaij(ServerWorld var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = -3; var3 <= 3; var3++) {
         for (int var4 = -3; var4 <= 3; var4++) {
            if (Math.abs(var3) == 3 || Math.abs(var4) == 3) {
               BlockPos var5 = this.x1bm3tnyu1jatt.add(var3, 0, var4);
               BlockState var6 = var1.getBlockState(var5);
               if (var6.isOf(Blocks.OAK_FENCE) || var6.isOf(Blocks.OAK_FENCE_GATE)) {
                  var2.add(var5);
               }
            }
         }
      }

      return var2;
   }

   private BlockPos x3u1km6otsxwtp() {
      return this.x1bm3tnyu1jatt.north(5);
   }

   private void x3v731fax5g1nx(ServerWorld var1) {
      if (this.x1bm3tnyu1jatt != null) {
         BlockPos var2 = this.x3u1km6otsxwtp();
         if (var1.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
            if (var1.getOtherEntities(null, new Box(var2).expand(0.15, 0.2, 0.15), var0 -> var0.isAlive() && !var0.isSpectator()).isEmpty()) {
               BlockState var3 = var1.getBlockState(var2);
               if (var3.isOf(Blocks.OAK_FENCE_GATE) && (Boolean)var3.get(FenceGateBlock.OPEN)) {
                  ColonyProtectionUtils.x1bq0f8n53gtob(var1, var2, (BlockState)var3.with(FenceGateBlock.OPEN, false), 3);
               }
            }
         }
      }
   }

   private void x3p0xf9wskmc24(ServerWorld var1) {
      if (this.x1bm3tnyu1jatt != null) {
         BlockPos var2 = this.x3u1km6otsxwtp();
         if (var1.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
            BlockState var3 = var1.getBlockState(var2);
            if (var3.isOf(Blocks.OAK_FENCE_GATE) && !(Boolean)var3.get(FenceGateBlock.OPEN)) {
               ColonyProtectionUtils.x1bq0f8n53gtob(var1, var2, (BlockState)var3.with(FenceGateBlock.OPEN, true), 3);
            }
         }
      }
   }

   private void x1f00g882945e(ServerWorld var1, AnimalEntity var2) {
      for (AnimalEntity var4 : this.x3q7ywqh31h55d(var1)) {
         if (var4 != var2 && this.x2745j5ue4rw4q(var4)) {
            var4.getNavigation().startMovingTo(this.x1bm3tnyu1jatt.getX() + 0.5, this.x1bm3tnyu1jatt.getY(), this.x1bm3tnyu1jatt.getZ() + 0.5, 0.8);
         }
      }
   }

   private List<AnimalEntity> x3q7ywqh31h55d(ServerWorld var1) {
      return var1.getEntitiesByClass(
         AnimalEntity.class,
         new Box(this.x9b03bdckmlwm.core()).expand(Math.min(56, this.x9b03bdckmlwm.radius()), 32.0, Math.min(56, this.x9b03bdckmlwm.radius())),
         var0 -> var0.isAlive() && x2rzbu6s47zw2x(var0)
      );
   }

   private boolean x2745j5ue4rw4q(Entity var1) {
      return this.x1bm3tnyu1jatt != null
         && Math.abs(var1.getX() - (this.x1bm3tnyu1jatt.getX() + 0.5)) < 5.0
         && Math.abs(var1.getZ() - (this.x1bm3tnyu1jatt.getZ() + 0.5)) < 5.0
         && Math.abs(var1.getY() - this.x1bm3tnyu1jatt.getY()) < 3.0;
   }

   private int xpn2n0o78r82j(List<AnimalEntity> var1, AnimalEntity var2) {
      return (int)var1.stream().filter(this::x2745j5ue4rw4q).filter(var1x -> var1x.getType() == var2.getType()).count();
   }

   private int x1yv8cepr980a2(List<AnimalEntity> var1) {
      return (int)var1.stream().filter(this::x2745j5ue4rw4q).count();
   }

   private static boolean x2rzbu6s47zw2x(AnimalEntity var0) {
      return var0 instanceof CowEntity
         || var0 instanceof SheepEntity
         || var0 instanceof ChickenEntity
         || var0 instanceof PigEntity
         || var0 instanceof RabbitEntity
         || var0 instanceof GoatEntity;
   }

   private static Item x1zya4i1rpwuhr(AnimalEntity var0) {
      if (var0 instanceof ChickenEntity) {
         return Items.WHEAT_SEEDS;
      } else if (var0 instanceof PigEntity || var0 instanceof RabbitEntity) {
         return Items.CARROT;
      } else {
         return !(var0 instanceof CowEntity) && !(var0 instanceof SheepEntity) && !(var0 instanceof GoatEntity) ? null : Items.WHEAT;
      }
   }

   private static Item x3k9mdozxqhn1u(SheepEntity var0) {
      return switch (var0.getColor()) {
         case ORANGE -> Items.ORANGE_WOOL;
         case MAGENTA -> Items.MAGENTA_WOOL;
         case LIGHT_BLUE -> Items.LIGHT_BLUE_WOOL;
         case YELLOW -> Items.YELLOW_WOOL;
         case LIME -> Items.LIME_WOOL;
         case PINK -> Items.PINK_WOOL;
         case GRAY -> Items.GRAY_WOOL;
         case LIGHT_GRAY -> Items.LIGHT_GRAY_WOOL;
         case CYAN -> Items.CYAN_WOOL;
         case PURPLE -> Items.PURPLE_WOOL;
         case BLUE -> Items.BLUE_WOOL;
         case BROWN -> Items.BROWN_WOOL;
         case GREEN -> Items.GREEN_WOOL;
         case RED -> Items.RED_WOOL;
         case BLACK -> Items.BLACK_WOOL;
         default -> Items.WHITE_WOOL;
      };
   }

   private static boolean x3e0xb3lfomiyr(ItemStack var0) {
      if (var0.isEmpty()) {
         return false;
      } else {
         String var1 = Registries.ITEM.getId(var0.getItem()).getPath();
         return var0.isOf(Items.EGG)
            || var0.isOf(Items.MILK_BUCKET)
            || var0.isOf(Items.LEATHER)
            || var0.isOf(Items.FEATHER)
            || var0.isOf(Items.RABBIT_HIDE)
            || var1.endsWith("_wool");
      }
   }

   private static int x26v4oc4f99cpr(VillagerEntity var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (x3e0xb3lfomiyr(var0.getInventory().getStack(var2))) {
            var1 += var0.getInventory().getStack(var2).getCount();
         }
      }

      return var1;
   }

   private int x8bmwjivt7kr4(ServerWorld var1, VillagerEntity var2) {
      return x18gt41621rh5l(var2, Items.EGG) + ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.EGG));
   }

   private void x2kdf6zxkbv4uj(ServerWorld var1, VillagerEntity var2) {
      int var3 = Math.max(0, 32 - ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.EGG)));

      for (int var4 = 0; var4 < var2.getInventory().size(); var4++) {
         ItemStack var5 = var2.getInventory().getStack(var4);
         if (var5.isOf(Items.EGG)) {
            int var6 = Math.min(var3, var5.getCount());
            var3 -= var6;
            if (var6 < var5.getCount()) {
               var5.setCount(var6);
            }
         }
      }
   }

   private void x33mnq7ijq2l5z(ServerWorld var1, VillagerEntity var2) {
      if (!xq6j4f862i6km(var2, Items.SHEARS)) {
         Optional var3 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.SHEARS));
         if (var3.isPresent()) {
            this.x2xg4t4fgfn92z(var1, var2, (ItemStack)var3.get());
         } else {
            if (ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.IRON_INGOT), 2)) {
               this.x2xg4t4fgfn92z(var1, var2, new ItemStack(Items.SHEARS));
            }
         }
      }
   }

   private void x31djqoo9oci6(ServerWorld var1, VillagerEntity var2, int var3) {
      while (x18gt41621rh5l(var2, Items.BUCKET) < var3) {
         Optional var4 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.BUCKET));
         if (var4.isPresent()) {
            this.x2xg4t4fgfn92z(var1, var2, (ItemStack)var4.get());
         } else {
            if (!ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var0 -> var0.isOf(Items.IRON_INGOT), 3)) {
               return;
            }

            this.x2xg4t4fgfn92z(var1, var2, new ItemStack(Items.BUCKET));
         }
      }
   }

   private void x27sd5416kpqqj(ServerWorld var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < var2.getInventory().size(); var3++) {
         if (ColonyManager.xhahxgpa08yc9(var2.getInventory().getStack(var3)) > 0) {
            return;
         }
      }

      ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var0 -> ColonyManager.xhahxgpa08yc9(var0) > 0)
         .ifPresent(var3x -> this.x2xg4t4fgfn92z(var1, var2, var3x));
   }

   private void x12fl4tcpl6f1x(ServerWorld var1, VillagerEntity var2, Item var3, int var4) {
      while (x18gt41621rh5l(var2, var3) + (var2.getMainHandStack().isOf(var3) ? var2.getMainHandStack().getCount() : 0) < var4) {
         Optional var5 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var1x -> var1x.isOf(var3));
         if (var5.isEmpty()) {
            return;
         }

         this.x2xg4t4fgfn92z(var1, var2, (ItemStack)var5.get());
      }
   }

   private boolean xmdm1vdfbqp32(ServerWorld var1, VillagerEntity var2) {
      if (this.xmj689j1i2yoe == null) {
         return false;
      } else if (var2.getMainHandStack().isOf(this.xmj689j1i2yoe)) {
         return true;
      } else {
         if (!var2.getMainHandStack().isEmpty()) {
            ItemStack var3 = var2.getMainHandStack().copy();
            var2.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.x2xg4t4fgfn92z(var1, var2, var3);
         }

         ItemStack var4 = xt9wcfi8kl303(var2, this.xmj689j1i2yoe);
         if (var4.isEmpty()) {
            var4 = ColonyManager.x3lxwdirnn77qc(var1, this.x9b03bdckmlwm, var1x -> var1x.isOf(this.xmj689j1i2yoe)).orElse(ItemStack.EMPTY);
         }

         if (var4.isEmpty()) {
            return false;
         } else {
            var2.equipStack(EquipmentSlot.MAINHAND, var4);
            return true;
         }
      }
   }

   private static ItemStack xt9wcfi8kl303(VillagerEntity var0, Item var1) {
      if (var1 == null) {
         return ItemStack.EMPTY;
      } else {
         for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
            if (var0.getInventory().getStack(var2).isOf(var1)) {
               ItemStack var3 = var0.getInventory().getStack(var2).copyWithCount(1);
               var0.getInventory().getStack(var2).decrement(1);
               return var3;
            }
         }

         return ItemStack.EMPTY;
      }
   }

   private boolean x2idhnuuiroo7r(ServerWorld var1, VillagerEntity var2, Item var3, int var4) {
      int var5 = x18gt41621rh5l(var2, var3)
         + (var2.getMainHandStack().isOf(var3) ? var2.getMainHandStack().getCount() : 0)
         + ColonyManager.x1mlne8tl8k5ag(var1, this.x9b03bdckmlwm, var1x -> var1x.isOf(var3));
      if (var5 < var4) {
         return false;
      } else {
         int var6 = var4;

         for (int var7 = 0; var7 < var2.getInventory().size() && var6 > 0; var7++) {
            if (var2.getInventory().getStack(var7).isOf(var3)) {
               int var8 = Math.min(var6, var2.getInventory().getStack(var7).getCount());
               var2.getInventory().getStack(var7).decrement(var8);
               var6 -= var8;
            }
         }

         if (var6 > 0 && var2.getMainHandStack().isOf(var3)) {
            int var9 = Math.min(var6, var2.getMainHandStack().getCount());
            var2.getMainHandStack().decrement(var9);
            var6 -= var9;
         }

         return var6 == 0 || ColonyManager.x22hjebj4vmztt(var1, this.x9b03bdckmlwm, var1x -> var1x.isOf(var3), var6);
      }
   }

   private void x2vvpgkqtpg958(ServerWorld var1, VillagerEntity var2) {
      if (this.xmj689j1i2yoe != null && var2.getMainHandStack().isOf(this.xmj689j1i2yoe)) {
         ItemStack var3 = var2.getMainHandStack().copy();
         var2.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
         this.x2xg4t4fgfn92z(var1, var2, var3);
         this.xmj689j1i2yoe = null;
      }
   }

   private static boolean xq6j4f862i6km(VillagerEntity var0, Item var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (var0.getInventory().getStack(var2).isOf(var1)) {
            return true;
         }
      }

      return false;
   }

   private static int x18gt41621rh5l(VillagerEntity var0, Item var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.getInventory().size(); var3++) {
         if (var0.getInventory().getStack(var3).isOf(var1)) {
            var2 += var0.getInventory().getStack(var3).getCount();
         }
      }

      return var2;
   }

   private static boolean x165qssxeku6kz(VillagerEntity var0, Item var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (var0.getInventory().getStack(var2).isOf(var1)) {
            var0.getInventory().getStack(var2).decrement(1);
            return true;
         }
      }

      return false;
   }

   private void x2xg4t4fgfn92z(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      ItemStack var4 = var2.getInventory().addStack(var3);
      if (!var4.isEmpty()) {
         var2.dropStack(var1, var4);
      }
   }

   private void x1yqub9v49uk07(ServerWorld var1, VillagerEntity var2, BlockPos var3, double var4) {
      ColonyNavigationHelper.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var3.getX() + 0.5, var3.getY(), var3.getZ() + 0.5), var4);
   }

   public String debugState() {
      return (this.x3m757n03ci4pu == null ? "ожидание" : this.x3m757n03ci4pu.name().toLowerCase(Locale.ROOT))
         + (this.xmj689j1i2yoe == null ? "" : ", приманка=" + Registries.ITEM.getId(this.xmj689j1i2yoe).getPath())
         + ", собрано="
         + this.x2ueth4fvnxw81;
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x3f72o6zqrgoi5 != null) {
         IronGolemDefendColonyGoal.release(this.x3f72o6zqrgoi5);
      }

      this.x2vvpgkqtpg958(var1, var2);
      this.x3v731fax5g1nx(var1);
      var2.getNavigation().stop();
      var2.getBrain().forget(ColonyMemoryModules.x2ppem7wipzctm);
      var2.getBrain().forget(ColonyMemoryModules.x3o547clcv9kaw);
      this.x9b03bdckmlwm = null;
      this.x3f72o6zqrgoi5 = null;
   }
}
