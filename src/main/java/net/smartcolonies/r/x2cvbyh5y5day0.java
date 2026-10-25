package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Property;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplate.PalettedBlockInfoList;
import net.minecraft.structure.StructureTemplate.StructureBlockInfo;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap.Type;
import net.smartcolonies.mixin.x2gac63vevkbv3;

public final class x2cvbyh5y5day0 extends MultiTickTask<VillagerEntity> {
   private static final int x2j5qpw4473kyo = 12000;
   private static final double xqz1t0tgdckuk = 36.0;
   private static final double x1r0ny3vabstq4 = 9.0;
   private static final int x1l7wzrnir2mle = 20;
   private static final int x2b0guwc8pt2x0 = 8;
   private x15kgoaq6i3m6i x80pqmx58uqey;
   private x2arpn38dclo1g x6xecrdg78nv4;
   private List<x2xwxksqmx9pch> x2npvmjy6y5jzn = List.of();
   private long xb9ohdv6bza8d;
   private long x35o9smdlqo00n;
   private long x24dc5178j3w8p;
   private int x1dhh6f58ylaqh;
   private long x1f9jpbscw9lb9;
   private BlockPos x2nyqpt8pxcmd0;
   private BlockPos x1cmffrn8pk1ze;
   private long xmf2hvjz8duvg;
   private double xase2imhyie81;
   private boolean x83c5i71zw6iy;
   private final x20lc7jle5mw9c x28a0hyz23oia6 = new x20lc7jle5mw9c();

   public x2cvbyh5y5day0() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && var1.getTime() >= this.x1f9jpbscw9lb9 && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         Optional var3 = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos()).filter(var1x -> var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r);
         if (!var3.isEmpty()
            && !((x15kgoaq6i3m6i)var3.get()).x2s37os1kht7vr()
            && !((x15kgoaq6i3m6i)var3.get()).x2ad3s8aentmo4().isEmpty()
            && ((x15kgoaq6i3m6i)var3.get()).x1n1a2vp01kxzf().isEmpty()
            && !x20rghnsl2yowl.x81tynoeus5lh(var1, (x15kgoaq6i3m6i)var3.get())
            && !x27aao31y6r2ta.xuj80syz8c60l(var1, (x15kgoaq6i3m6i)var3.get(), var2)) {
            Optional var4 = ((x15kgoaq6i3m6i)var3.get())
               .x39hvql45i0wl6(var2.getUuid())
               .filter(var0 -> !var0.x3fgv5ft5sp7zt())
               .filter(var2x -> var2x.xxo2yeczi7qrw(var2.getUuid(), var1.getTime()));
            if (var4.isEmpty()) {
               return false;
            } else if (!protectPen(var1, (x15kgoaq6i3m6i)var3.get(), (x2arpn38dclo1g)var4.get())) {
               return false;
            } else {
               Optional var5 = loadPlan(var1, (x2arpn38dclo1g)var4.get());
               if (var5.isEmpty()) {
                  ((x2arpn38dclo1g)var4.get()).x2hk920e3qof5e(var1.getTime() + 1200L);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                  return false;
               } else {
                  this.x80pqmx58uqey = (x15kgoaq6i3m6i)var3.get();
                  this.x6xecrdg78nv4 = (x2arpn38dclo1g)var4.get();
                  this.x2npvmjy6y5jzn = (List<x2xwxksqmx9pch>)var5.get();
                  this.x6xecrdg78nv4.x248ps383cwu1s(this.x2npvmjy6y5jzn.size(), var1.getTime(), 8);
                  this.x6xecrdg78nv4.xkvyd96wj0qui(var2.getUuid(), var1.getTime());
                  this.x2nm02vmn59j42(var2);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                  return this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size();
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x80pqmx58uqey != null
         && this.x80pqmx58uqey.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
         && !x27aao31y6r2ta.xuj80syz8c60l(var1, this.x80pqmx58uqey, var2)
         && !x20rghnsl2yowl.x81tynoeus5lh(var1, this.x80pqmx58uqey)
         && this.x6xecrdg78nv4 != null
         && !this.x6xecrdg78nv4.x3fgv5ft5sp7zt()
         && this.x6xecrdg78nv4.x32oz43hmeynhc() == x20r5v7z43p3d5.x1mzkgtgadv3jh
         && this.x6xecrdg78nv4.x3vu8mnvg0blk4().filter(var2.getUuid()::equals).isPresent()
         && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size();
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.xb9ohdv6bza8d = var3;
      this.x35o9smdlqo00n = var3;
      this.x24dc5178j3w8p = var3;
      this.x1dhh6f58ylaqh = 0;
      this.x2nyqpt8pxcmd0 = null;
      this.x1cmffrn8pk1ze = null;
      this.x83c5i71zw6iy = this.x6xecrdg78nv4.x2s8emavervqrz()
         || this.x2npvmjy6y5jzn
            .stream()
            .anyMatch(
               var1x -> !var1x.x3ln30gt147gjn()
                  && !var1x.x1jjn414zs82u2()
                  && (
                     var1x.x2hmkda3gwcacx().isIn(BlockTags.PLANKS)
                        || var1x.x2hmkda3gwcacx().isIn(BlockTags.DOORS)
                        || var1x.x2hmkda3gwcacx().isIn(BlockTags.BEDS)
                  )
                  && var1.getBlockState(var1x.x31k0w8t7ul189()).equals(var1x.x2hmkda3gwcacx())
            );
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (!protectPen(var1, this.x80pqmx58uqey, this.x6xecrdg78nv4)) {
         x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "стройка пересекает загон: поиск другого участка");
      } else if (var3 >= this.xb9ohdv6bza8d && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
         if (!x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "изменение мира выключено");
         } else if (!this.x83c5i71zw6iy
            && this.x6xecrdg78nv4.x2nfrt8qxdscso().isEmpty()
            && this.x6xecrdg78nv4.x3kqe1qjsw9oty().getNamespace().equals("smart_colonies")
            && !this.x6xecrdg78nv4.x3kqe1qjsw9oty().getPath().startsWith("village/legacy_beta24/")
            && this.x6xecrdg78nv4.x3ij6gescvakv2().getY() > this.x80pqmx58uqey.core().getY() + 16
            && this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()).x3ln30gt147gjn()) {
            this.x6xecrdg78nv4.x2hk920e3qof5e(0L);
            this.x80pqmx58uqey.x2lr91dptxy1ww();
            this.x80pqmx58uqey.xs28n9ortj0j6(var3);
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "не начатая стройка на высоком склоне: поиск другого участка");
            x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
         } else if (var3 - this.x24dc5178j3w8p > 1200L) {
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "стройка без прогресса: отложена для повторной проверки");
            this.x6xecrdg78nv4.x1pry0l0yrsmkc(var2.getUuid(), var3 + 2400L);
            this.x6xecrdg78nv4.x2hk920e3qof5e(var3 + 600L);
            this.x1f9jpbscw9lb9 = var3 + 2400L;
            x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
         } else {
            int var5;
            for (var5 = 0;
               this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()
                  && var5 < 64
                  && this.x2byjgmxzxpv4f(var1, this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()));
               var5++
            ) {
               this.x6xecrdg78nv4.xdhrxq42ir75w(var3);
            }

            if (var5 > 0) {
               this.x24dc5178j3w8p = var3;
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            }

            if (this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
               this.x6xecrdg78nv4.x3lcv8vebl7uhh();
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            } else if (var5 != 64 || !this.x2byjgmxzxpv4f(var1, this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh()))) {
               x2xwxksqmx9pch var6 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
               BlockPos var7 = var6.x31k0w8t7ul189();
               var2.getBrain().remember(xxao083dbo7ag.x1r15huley8x9h, var7);
               if (!var6.x3ln30gt147gjn()
                  && !var1.getBlockState(var7).equals(var6.x2hmkda3gwcacx())
                  && !this.x2pq34yhi7gjxs(var2, var6.x2hmkda3gwcacx(), materialItem(var6.x2hmkda3gwcacx()))) {
                  this.x14d1iudi3z1jl(var1, var2, var6, var3);
               } else {
                  boolean var8 = var6.x3ln30gt147gjn()
                     ? var2.getBlockPos().down().equals(var7)
                     : !var6.x2hmkda3gwcacx().getCollisionShape(var1, var7).isEmpty() && var2.getBoundingBox().intersects(new Box(var7));
                  if (!var8 && withinBuildReach(var2, var7)) {
                     var2.getNavigation().stop();
                     int var9 = var6.x3ln30gt147gjn()
                        ? x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, var7)
                        : Math.max(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.build_delay_ticks, x3sqkg3n6v972r.x2by5f9ehxvwk9().work.placement_ticks);
                     if (this.x28a0hyz23oia6.xpohy1q30uxlk(var1, var2, var7, var9, var6.x3ln30gt147gjn())) {
                        int var10 = 0;

                        while (var10 < 1 && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
                           x2xwxksqmx9pch var11 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
                           if (!withinBuildReach(var2, var11.x31k0w8t7ul189())
                              || !var11.x3ln30gt147gjn()
                                 && !var1.getBlockState(var11.x31k0w8t7ul189()).equals(var11.x2hmkda3gwcacx())
                                 && !this.x2pq34yhi7gjxs(var2, var11.x2hmkda3gwcacx(), materialItem(var11.x2hmkda3gwcacx()))
                              || !this.x1dcdu1mepirat(var1, var2, var11)) {
                              break;
                           }

                           this.x24dc5178j3w8p = var3;
                           this.x1dhh6f58ylaqh = 0;
                           var10++;
                           x2vaj7rsm57i4n.x2t98zlq4lu49t(
                              var2, var11.x3ln30gt147gjn() ? "подготовка участка" : (var11.x1jjn414zs82u2() ? "фундамент" : "строительство"), 1
                           );
                           this.x6xecrdg78nv4.xdhrxq42ir75w(var1.getTime());
                        }

                        if (var10 > 0) {
                           x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                        }

                        this.xb9ohdv6bza8d = var3 + 1L;
                        if (this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
                           this.x6xecrdg78nv4.x3lcv8vebl7uhh();
                           x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                        }
                     }
                  } else {
                     if (var8) {
                        x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "освобождение места для строящегося блока");
                     }

                     if (this.x2nyqpt8pxcmd0 != null && var2.getNavigation().isIdle() && this.x2nyqpt8pxcmd0.getSquaredDistance(var2.getBlockPos()) <= 9.0) {
                        x1c1r5c6a13gtl.x1uiixbj22gkfz(var2, this.x2nyqpt8pxcmd0, var3);
                        this.x2nyqpt8pxcmd0 = null;
                     }

                     if (var3 >= this.x35o9smdlqo00n) {
                        this.x15kyz5qjj4odo(var1, var2, var7);
                        this.x35o9smdlqo00n = var3 + 20L;
                     }
                  }
               }
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x28a0hyz23oia6.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      var2.getBrain().forget(xxao083dbo7ag.x1r15huley8x9h);
      if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.xmu75rcopbjnh() >= this.x2npvmjy6y5jzn.size()) {
         this.x6xecrdg78nv4.x3lcv8vebl7uhh();
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
      } else if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.x3vu8mnvg0blk4().filter(var2.getUuid()::equals).isPresent()) {
         this.x6xecrdg78nv4.x23bey8uos6gyt();
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
      }

      this.x80pqmx58uqey = null;
      this.x6xecrdg78nv4 = null;
      this.x2npvmjy6y5jzn = List.of();
   }

   private boolean x1dcdu1mepirat(ServerWorld var1, VillagerEntity var2, x2xwxksqmx9pch var3) {
      BlockState var4 = var3.x2hmkda3gwcacx();
      BlockState var5 = var1.getBlockState(var3.x31k0w8t7ul189());
      if (this.x2byjgmxzxpv4f(var1, var3)) {
         return true;
      } else if (var3.x3ln30gt147gjn()) {
         if (x10z0hm6bbvhlr(var1, var3.x31k0w8t7ul189(), var5)) {
            this.x6xecrdg78nv4.x2hk920e3qof5e(var1.getTime() + 2400L);
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "расчистка остановлена защитой участка");
            x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            return false;
         } else if (!var5.isAir() && x3b6f14zbbbwjh(var5)) {
            if (var2.getBlockPos().down().equals(var3.x31k0w8t7ul189())) {
               this.x15kyz5qjj4odo(var1, var2, var3.x31k0w8t7ul189());
               return false;
            } else {
               return x1dxdlnevtfbhm.x231if4rvsvptj(var1, var3.x31k0w8t7ul189(), false, var2);
            }
         } else {
            return true;
         }
      } else if (x1dxdlnevtfbhm.x3o1lagqjim5tz(var1, var3.x31k0w8t7ul189())) {
         x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "участок защищён: " + var3.x31k0w8t7ul189().toShortString());
         this.x6xecrdg78nv4.x2hk920e3qof5e(var1.getTime() + 2400L);
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
         return false;
      } else {
         if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.builder_respects_player_builds) {
            TagKey var6 = TagKey.of(RegistryKeys.BLOCK, Identifier.of("smart_colonies", "builder_protected"));
            if (var5.isIn(var6) || var1.getBlockEntity(var3.x31k0w8t7ul189()) != null) {
               this.x6xecrdg78nv4.x2hk920e3qof5e(var1.getTime() + 2400L);
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "помеха на участке: дом будет продолжен, а не брошен");
               x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
               return false;
            }
         }

         Item var12 = materialItem(var4);
         if (var12 != Items.AIR && this.x2pq34yhi7gjxs(var2, var4, var12)) {
            if (!var4.getCollisionShape(var1, var3.x31k0w8t7ul189()).isEmpty()) {
               List<Entity> var7 = var1.getOtherEntities(
                  null,
                  new Box(var3.x31k0w8t7ul189()),
                  var3x -> var3x instanceof LivingEntity
                     && var3x.isAlive()
                     && !var3x.isSpectator()
                     && var4.getCollisionShape(var1, var3.x31k0w8t7ul189())
                        .getBoundingBoxes()
                        .stream()
                        .anyMatch(
                           var2xx -> var2xx.offset(var3.x31k0w8t7ul189().getX(), var3.x31k0w8t7ul189().getY(), var3.x31k0w8t7ul189().getZ())
                              .intersects(var3x.getBoundingBox())
                        )
               );
               if (!var7.isEmpty()) {
                  x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "строящийся блок занят: " + var3.x31k0w8t7ul189().toShortString());

                  for (Entity var9 : var7) {
                     if ((var9 instanceof VillagerEntity || var9 instanceof IronGolemEntity)
                        && var9 instanceof MobEntity var10
                        && x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
                        BlockPos var11 = this.x9bqehx0b70gk(var1, var9.getBlockPos());
                        var10.getNavigation().startMovingTo(var11.getX() + 0.5, var11.getY(), var11.getZ() + 0.5, 0.65);
                     }
                  }

                  return false;
               }
            }

            if (!x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var3.x31k0w8t7ul189(), var4, 3)) {
               x1sqdj274gd8s2(var1, this.x6xecrdg78nv4, var3);
               return false;
            } else if (!this.x2g9vwrz4x5lcw(var2, var4, var12)) {
               x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var3.x31k0w8t7ul189(), var5, 3);
               return false;
            } else {
               var2.swingHand(Hand.MAIN_HAND);
               BlockSoundGroup var13 = var4.getSoundGroup();
               var1.playSound(
                  null, var3.x31k0w8t7ul189(), var13.getPlaceSound(), var2.getSoundCategory(), (var13.getVolume() + 1.0F) / 2.0F, var13.getPitch() * 0.8F
               );
               return true;
            }
         } else {
            return false;
         }
      }
   }

   private boolean x2byjgmxzxpv4f(ServerWorld var1, x2xwxksqmx9pch var2) {
      BlockState var3 = var1.getBlockState(var2.x31k0w8t7ul189());
      if (x1zol2fshkzbul(var3, var2.x2hmkda3gwcacx())) {
         return true;
      } else {
         return var2.x3ln30gt147gjn()
            ? var3.isAir() || !x3b6f14zbbbwjh(var3)
            : var2.x1jjn414zs82u2()
               && var3.isSideSolidFullSquare(var1, var2.x31k0w8t7ul189(), Direction.UP)
               && var1.getFluidState(var2.x31k0w8t7ul189()).isEmpty();
      }
   }

   public static Optional<List<x2xwxksqmx9pch>> loadPlan(ServerWorld var0, x2arpn38dclo1g var1) {
      return var0.getServer()
         .getStructureTemplateManager()
         .getTemplate(var1.x3kqe1qjsw9oty())
         .map(
            var1x -> {
               StructurePlacementData var2 = new StructurePlacementData().setMirror(BlockMirror.NONE).setRotation(BlockRotation.NONE).setIgnoreEntities(true);
               List var3 = ((x2gac63vevkbv3)var1x).xbqthaw3aplko();
               if (var3.isEmpty()) {
                  return List.of();
               } else {
                  List<x2xwxksqmx9pch> var4 = new ArrayList<>();
                  HashMap<BlockPos, BlockState> var5 = new HashMap<>();

                  for (StructureBlockInfo var7 : ((PalettedBlockInfoList)var3.getFirst()).getAll()) {
                     BlockState var8 = var7.state().mirror(var2.getMirror()).rotate(var2.getRotation());
                     if (!var8.isAir() && !var8.isOf(Blocks.STRUCTURE_VOID) && !var8.isOf(Blocks.JIGSAW)) {
                        var5.put(var1.x3ij6gescvakv2().add(StructureTemplate.transform(var2, var7.pos())), var8);
                     }
                  }

                  BlockBox var11 = var1.x24kbqv6g4kkrg();

                  for (int var12 = var11.getMinX() - 1; var12 <= var11.getMaxX() + 1; var12++) {
                     for (int var14 = var11.getMinZ() - 1; var14 <= var11.getMaxZ() + 1; var14++) {
                        for (int var9 = var11.getMinY() + 1; var9 <= var11.getMaxY() + 2; var9++) {
                           BlockPos var10 = new BlockPos(var12, var9, var14);
                           var4.add(new x2xwxksqmx9pch(var10, var5.getOrDefault(var10, Blocks.AIR.getDefaultState()), true, false));
                        }

                        if (var12 >= var11.getMinX() && var12 <= var11.getMaxX() && var14 >= var11.getMinZ() && var14 <= var11.getMaxZ()) {
                           for (int var16 = var11.getMinY(); var16 >= var11.getMinY() - 4; var16--) {
                              BlockPos var18 = new BlockPos(var12, var16, var14);
                              var4.add(new x2xwxksqmx9pch(var18, Blocks.COBBLESTONE.getDefaultState(), false, true));
                           }
                        }
                     }
                  }

                  for (StructureBlockInfo var15 : ((PalettedBlockInfoList)var3.getFirst()).getAll()) {
                     BlockState var17 = var15.state().mirror(var2.getMirror()).rotate(var2.getRotation());
                     if (!var17.isAir() && !var17.isOf(Blocks.STRUCTURE_VOID) && !var17.isOf(Blocks.JIGSAW)) {
                        BlockPos var19 = StructureTemplate.transform(var2, var15.pos());
                        var4.add(new x2xwxksqmx9pch(var1.x3ij6gescvakv2().add(var19), var17, false, false));
                     }
                  }

                  var4.sort(
                     Comparator.comparingInt(x2cvbyh5y5day0::xpqrxw62ch5me)
                        .thenComparingInt(var0xx -> var0xx.x3ln30gt147gjn() ? -var0xx.x31k0w8t7ul189().getY() : var0xx.x31k0w8t7ul189().getY())
                        .thenComparingInt(var0xx -> -var0xx.x31k0w8t7ul189().getZ())
                        .thenComparingInt(var0xx -> -var0xx.x31k0w8t7ul189().getX())
                  );
                  return List.copyOf(var4);
               }
            }
         );
   }

   private static int xpqrxw62ch5me(x2xwxksqmx9pch var0) {
      if (var0.x3ln30gt147gjn()) {
         return 0;
      } else if (var0.x1jjn414zs82u2()) {
         return 1;
      } else {
         String var1 = Registries.BLOCK.getId(var0.x2hmkda3gwcacx().getBlock()).getPath();
         if (var1.contains("chain")) {
            return 3;
         } else {
            return !(var0.x2hmkda3gwcacx().getBlock() instanceof PlantBlock)
                  && !(var0.x2hmkda3gwcacx().getBlock() instanceof DoorBlock)
                  && !(var0.x2hmkda3gwcacx().getBlock() instanceof BedBlock)
                  && !List.of("lantern", "torch", "ladder", "carpet", "sign", "button", "lever", "rail", "banner", "candle", "flower_pot", "pressure_plate")
                     .stream()
                     .anyMatch(var1::contains)
               ? 2
               : 4;
         }
      }
   }

   private void x2nm02vmn59j42(VillagerEntity var1) {
      var1.getBrain().remember(xxao083dbo7ag.x3ecoc0soedv0m, x1rxiy9mtu5b3h.x35csp4lojh59r);
      var1.getBrain().remember(xxao083dbo7ag.x2ooa9y9romfh, this.x6xecrdg78nv4.x24kbqv6g4kkrg());
      if (!this.x80pqmx58uqey.x2ad3s8aentmo4().isEmpty()) {
         var1.getBrain().remember(xxao083dbo7ag.x3o547clcv9kaw, this.x80pqmx58uqey.x2ad3s8aentmo4().getFirst());
      }
   }

   private void x14d1iudi3z1jl(ServerWorld var1, VillagerEntity var2, x2xwxksqmx9pch var3, long var4) {
      Item var6 = materialItem(var3.x2hmkda3gwcacx());
      int var7 = x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.x80pqmx58uqey, var0 -> var0.isIn(ItemTags.LOGS));
      int var8 = x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.x80pqmx58uqey, var0 -> var0.isIn(ItemTags.STONE_CRAFTING_MATERIALS));
      Predicate var9 = materialPredicate(var3.x2hmkda3gwcacx(), var6)
         .and(var2x -> var2x.isIn(ItemTags.LOGS) ? var7 > 8 : (var2x.isIn(ItemTags.STONE_CRAFTING_MATERIALS) ? var8 > 8 : true));
      Optional var10 = x2n55y2tom37h2.x1dhbpietunk9i(var1, this.x80pqmx58uqey, var2, var9);
      boolean var11 = false;
      if (var10.isEmpty()) {
         Optional var12 = x2n55y2tom37h2.x2ytjyhq411pf7(var1, this.x80pqmx58uqey, var2.getBlockPos(), var9);
         if (var12.isPresent()) {
            this.x6xecrdg78nv4.x3feu6p476x7c(var4);
            x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
            if (this.x6xecrdg78nv4.xbnccd29nwem4() < 600) {
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "путь к складу стройматериалов временно недоступен");
               this.xb9ohdv6bza8d = var4 + 20L;
               return;
            }

            var10 = var12;
            var11 = true;
         }
      }

      if (var10.isEmpty()) {
         x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "ожидание стройматериалов");
         this.xb9ohdv6bza8d = var4 + 40L;
      } else {
         BlockPos var24 = (BlockPos)var10.get();
         var2.getBrain().remember(xxao083dbo7ag.x3o547clcv9kaw, var24);
         double var13 = var2.squaredDistanceTo(var24.getX() + 0.5, var24.getY() + 0.5, var24.getZ() + 0.5);
         if (this.x1cmffrn8pk1ze == null || this.x1cmffrn8pk1ze.getSquaredDistance(var24) > 2.0) {
            this.x1cmffrn8pk1ze = var24;
            this.xmf2hvjz8duvg = var4;
            this.xase2imhyie81 = var13;
         } else if (var13 + 16.0 < this.xase2imhyie81) {
            this.xase2imhyie81 = var13;
            this.xmf2hvjz8duvg = var4;
         }

         if (var13 > 9.0 && var4 - this.xmf2hvjz8duvg >= 600L) {
            var11 = true;
         }

         if (!var11 && var2.squaredDistanceTo(var24.getX() + 0.5, var24.getY() + 0.5, var24.getZ() + 0.5) > 9.0) {
            if (var4 >= this.x35o9smdlqo00n) {
               boolean var25 = x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var24.getX() + 0.5, var24.getY(), var24.getZ() + 0.5), 0.6);
               if (!var25) {
                  this.x6xecrdg78nv4.x3feu6p476x7c(var4);
                  x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
               }

               this.x35o9smdlqo00n = var4 + 20L;
            }
         } else {
            boolean var15 = false;

            for (int var16 = 0; var16 < var2.getInventory().size(); var16++) {
               ItemStack var17 = var2.getInventory().getStack(var16);
               if (!var17.isEmpty() && !materialPredicate(var3.x2hmkda3gwcacx(), var6).test(var17)) {
                  if (!var15 && x2n55y2tom37h2.xhahxgpa08yc9(var17) > 0) {
                     var15 = true;
                  } else {
                     x2vaj7rsm57i4n.x25cotfj45r525(var1, this.x80pqmx58uqey, var2, var17);
                  }
               }
            }

            if (var1.getBlockEntity(var24) instanceof Inventory var26) {
               List<Integer> var28 = new ArrayList<>();

               for (int var18 = 0; var18 < var26.size(); var18++) {
                  if (var9.test(var26.getStack(var18))) {
                     var28.add(var18);
                  }
               }

               String var29 = Registries.BLOCK.getId(var3.x2hmkda3gwcacx().getBlock()).getPath();
               var28.sort(Comparator.comparingInt(var4x -> {
                  ItemStack var5 = var26.getStack(var4x);
                  if (var5.isOf(var6)) {
                     return 0;
                  } else if (var5.isIn(ItemTags.STONE_CRAFTING_MATERIALS)) {
                     return x3eloj7pplrkw8(var29) ? 1 : 2;
                  } else if (!var5.isIn(ItemTags.LOGS)) {
                     return 1;
                  } else {
                     return x2wwfwdfwbj3ts(var29) && var7 >= 40 ? 1 : 3;
                  }
               }));

               for (int var20 : var28) {
                  ItemStack var21 = var26.getStack(var20);
                  if (!var21.isEmpty() && var9.test(var21)) {
                     int var22 = var21.isIn(ItemTags.LOGS) ? var7 - 8 : (var21.isIn(ItemTags.STONE_CRAFTING_MATERIALS) ? var8 - 8 : 32);
                     if (var22 > 0) {
                        Item var23 = var21.getItem();
                        x2n55y2tom37h2.x5jd7014csotp(var1, var24, var1x -> var1x.isOf(var23), Math.min(64, var22)).ifPresent(var3x -> {
                           this.x3gd6g2uloauvn(var1, var2, var3x);
                           var2.swingHand(Hand.MAIN_HAND);
                        });
                        this.x6xecrdg78nv4.x1rzj1yojiksff();
                        this.x1cmffrn8pk1ze = null;
                        this.x24dc5178j3w8p = var4;
                        if (var11) {
                           x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "снабжение стройки", 1);
                        }

                        x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                        this.xb9ohdv6bza8d = var4 + 10L;
                        return;
                     }
                  }
               }
            }

            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "сохраняется запас для инструментов и факелов");
            this.xb9ohdv6bza8d = var4 + 10L;
         }
      }
   }

   private boolean x2pq34yhi7gjxs(VillagerEntity var1, BlockState var2, Item var3) {
      Predicate var4 = materialPredicate(var2, var3);
      if (var4.test(var1.getMainHandStack())) {
         return true;
      } else {
         for (int var5 = 0; var5 < var1.getInventory().size(); var5++) {
            if (var4.test(var1.getInventory().getStack(var5))) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean x2g9vwrz4x5lcw(VillagerEntity var1, BlockState var2, Item var3) {
      Predicate var4 = materialPredicate(var2, var3);
      if (var4.test(var1.getMainHandStack())) {
         var1.getMainHandStack().decrement(1);
         return true;
      } else {
         for (int var5 = 0; var5 < var1.getInventory().size(); var5++) {
            ItemStack var6 = var1.getInventory().getStack(var5);
            if (!var6.isEmpty() && var4.test(var6)) {
               var6.decrement(1);
               return true;
            }
         }

         return false;
      }
   }

   private void x3gd6g2uloauvn(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      ItemStack var4 = var2.getMainHandStack().copy();
      var2.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
      if (!var4.isEmpty()) {
         ItemStack var5 = var2.getInventory().addStack(var4);
         if (!var5.isEmpty() && !x2n55y2tom37h2.x2cl1crde2rnxy(var1, this.x80pqmx58uqey, var5)) {
            var2.dropStack(var1, var5);
         }
      }

      var2.equipStack(EquipmentSlot.MAINHAND, var3);
   }

   public static Predicate<ItemStack> materialPredicate(BlockState var0, Item var1) {
      if (var1 == Items.AIR) {
         var1 = materialItem(var0);
      }

      Item var2 = var1;
      Predicate<ItemStack> var3 = var1x -> !var1x.isEmpty() && var1x.isOf(var2);
      String var4 = Registries.BLOCK.getId(var0.getBlock()).getPath();
      if (x2wwfwdfwbj3ts(var4)) {
         var3 = var3.or((ItemStack var0x) -> var0x.isIn(ItemTags.LOGS));
      }

      if (x3eloj7pplrkw8(var4)) {
         var3 = var3.or((ItemStack var0x) -> var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS));
      }

      if (var4.contains("sandstone")) {
         var3 = var3.or((ItemStack var0x) -> var0x.isOf(Items.SAND) || var0x.isOf(Items.RED_SAND));
      }

      return var3.or((ItemStack var0x) -> var0x.isIn(ItemTags.LOGS) || var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS));
   }

   public static Item materialItem(BlockState var0) {
      Item var1 = var0.getBlock().asItem();
      if (var1 != Items.AIR) {
         return var1;
      } else if (var0.isOf(Blocks.FARMLAND) || var0.isOf(Blocks.DIRT_PATH)) {
         return Items.DIRT;
      } else if (var0.isOf(Blocks.WATER)) {
         return Items.WATER_BUCKET;
      } else if (var0.isOf(Blocks.LAVA)) {
         return Items.LAVA_BUCKET;
      } else if (var0.isOf(Blocks.PUMPKIN_STEM) || var0.isOf(Blocks.ATTACHED_PUMPKIN_STEM)) {
         return Items.PUMPKIN_SEEDS;
      } else if (var0.isOf(Blocks.MELON_STEM) || var0.isOf(Blocks.ATTACHED_MELON_STEM)) {
         return Items.MELON_SEEDS;
      } else if (var0.isOf(Blocks.REDSTONE_WIRE)) {
         return Items.REDSTONE;
      } else {
         return var0.isOf(Blocks.TRIPWIRE) ? Items.STRING : var1;
      }
   }

   private BlockPos x9bqehx0b70gk(ServerWorld var1, BlockPos var2) {
      BlockBox var3 = this.x6xecrdg78nv4.x24kbqv6g4kkrg();
      List<BlockPos> var4 = new ArrayList<>();

      for (int var5 = 2; var5 <= 5; var5++) {
         for (int var6 = var3.getMinX() - var5; var6 <= var3.getMaxX() + var5; var6++) {
            for (int var10 : new int[]{var3.getMinZ() - var5, var3.getMaxZ() + var5}) {
               this.x3ug5hpy6o0yih(var1, var2, var4, var6, var10);
            }
         }

         for (int var11 = var3.getMinZ() - var5 + 1; var11 < var3.getMaxZ() + var5; var11++) {
            for (int var15 : new int[]{var3.getMinX() - var5, var3.getMaxX() + var5}) {
               this.x3ug5hpy6o0yih(var1, var2, var4, var15, var11);
            }
         }

         if (!var4.isEmpty()) {
            break;
         }
      }

      return var4.stream().min(Comparator.comparingDouble(var2::getSquaredDistance)).orElse(this.x6xecrdg78nv4.x3ij6gescvakv2());
   }

   private void x15kyz5qjj4odo(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (this.x2nyqpt8pxcmd0 != null
         && x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, this.x2nyqpt8pxcmd0)
         && x1tj11tseztzrt(this.x2nyqpt8pxcmd0, var3) <= 9.0
         && Math.abs(this.x2nyqpt8pxcmd0.getY() - var3.getY()) <= 13
         && !this.x2nyqpt8pxcmd0.down().equals(var3)
         && !this.x2nyqpt8pxcmd0.equals(var3)
         && !x3o70vbguw4qn7(var2, this.x2nyqpt8pxcmd0, var3)
         && !x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x2nyqpt8pxcmd0, var1.getTime())) {
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x2nyqpt8pxcmd0, 0.8);
      } else {
         BlockBox var4 = this.x6xecrdg78nv4.x24kbqv6g4kkrg();
         ArrayList<BlockPos> var5 = new ArrayList<>();

         for (int var6 = 2; var6 <= 4; var6++) {
            for (int var7 = var4.getMinX() - var6; var7 <= var4.getMaxX() + var6; var7++) {
               for (int var11 : new int[]{var4.getMinZ() - var6, var4.getMaxZ() + var6}) {
                  this.x3ug5hpy6o0yih(var1, var3, var5, var7, var11);
               }
            }

            for (int var17 = var4.getMinZ() - var6 + 1; var17 < var4.getMaxZ() + var6; var17++) {
               for (int var24 : new int[]{var4.getMinX() - var6, var4.getMaxX() + var6}) {
                  this.x3ug5hpy6o0yih(var1, var3, var5, var24, var17);
               }
            }
         }

         for (int var15 = -5; var15 <= 5; var15++) {
            for (int var18 = -5; var18 <= 5; var18++) {
               if (!(var15 * var15 + var18 * var18 > 9.0)) {
                  for (int var20 = var3.getY() - 13; var20 <= var3.getY() + 13; var20++) {
                     BlockPos var22 = new BlockPos(var3.getX() + var15, var20, var3.getZ() + var18);
                     if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var22)) {
                        var5.add(var22);
                     }
                  }
               }
            }
         }

         var5.removeIf(
            var3x -> x1tj11tseztzrt(var3x, var3) > 9.0
               || Math.abs(var3x.getY() - var3.getY()) > 13
               || var3x.down().equals(var3)
               || var3x.equals(var3)
               || x3o70vbguw4qn7(var2, var3x, var3)
               || x1c1r5c6a13gtl.xps35ehel6nkk(var2, var3x, var1.getTime())
         );
         var5.sort(Comparator.comparingDouble(var2x -> 0.35 * x1tj11tseztzrt(var2x, var3) + var2x.getSquaredDistance(var2.getBlockPos())));
         if (var5.isEmpty()) {
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "нет безопасного подхода к стройке");
         } else if (x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
            var2.getNavigation().setMaxFollowRange(128.0F);
            var2.getNavigation().setRangeMultiplier(8.0F);

            Path var16;
            try {
               var16 = var2.getNavigation().findPathTo(new HashSet(var5), 0);
            } finally {
               var2.getNavigation().resetRangeMultiplier();
            }

            if (var16 != null && var16.reachesTarget()) {
               this.x2nyqpt8pxcmd0 = var16.getTarget();
            } else {
               this.x2nyqpt8pxcmd0 = (BlockPos)var5.getFirst();
               x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "поиск доступного подхода к стройке");
            }

            x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x2nyqpt8pxcmd0, 0.8);
         }
      }
   }

   private void x3ug5hpy6o0yih(ServerWorld var1, BlockPos var2, List<BlockPos> var3, int var4, int var5) {
      BlockPos var6 = this.x2m6fmt7uq9mrl(var1, var4, var5);
      if (Math.abs(var6.getY() - var2.getY()) <= 13) {
         if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var6)) {
            var3.add(var6);
         }
      }
   }

   public static boolean withinBuildReach(VillagerEntity var0, BlockPos var1) {
      double var2 = var0.getX() - (var1.getX() + 0.5);
      double var4 = var0.getZ() - (var1.getZ() + 0.5);
      return var2 * var2 + var4 * var4 <= 36.0 && Math.abs(var0.getY() - var1.getY()) <= 14.0;
   }

   public static boolean protectPen(ServerWorld var0, x15kgoaq6i3m6i var1, x2arpn38dclo1g var2) {
      if (!var1.x2vcibw4inamjw(var2.x24kbqv6g4kkrg(), 1) || var2.x2s8emavervqrz()) {
         return true;
      } else if (var2.x2nfrt8qxdscso().isPresent()) {
         var2.x2hk920e3qof5e(var0.getTime() + 1200L);
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
         return false;
      } else {
         var2.x2hk920e3qof5e(0L);
         var1.x2lr91dptxy1ww();
         var1.xs28n9ortj0j6(var0.getTime());
         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
         return false;
      }
   }

   private static boolean x3o70vbguw4qn7(VillagerEntity var0, BlockPos var1, BlockPos var2) {
      double var3 = var0.getWidth() / 2.0;
      return new Box(
            var1.getX() + 0.5 - var3, var1.getY(), var1.getZ() + 0.5 - var3, var1.getX() + 0.5 + var3, var1.getY() + var0.getHeight(), var1.getZ() + 0.5 + var3
         )
         .intersects(new Box(var2));
   }

   private static double x1tj11tseztzrt(BlockPos var0, BlockPos var1) {
      double var2 = var0.getX() - var1.getX();
      double var4 = var0.getZ() - var1.getZ();
      return var2 * var2 + var4 * var4;
   }

   private static boolean x3b6f14zbbbwjh(BlockState var0) {
      return var0.isReplaceable()
         || var0.getBlock() instanceof CropBlock
         || var0.getBlock() instanceof StemBlock
         || var0.getBlock() instanceof AttachedStemBlock
         || var0.isOf(Blocks.PUMPKIN)
         || var0.isOf(Blocks.MELON)
         || var0.isOf(Blocks.FARMLAND)
         || var0.isIn(BlockTags.LEAVES)
         || var0.isIn(BlockTags.LOGS)
         || x1dxdlnevtfbhm.xroinv4hk6l7h(var0);
   }

   private static boolean x10z0hm6bbvhlr(ServerWorld var0, BlockPos var1, BlockState var2) {
      if (x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var1)) {
         return true;
      } else if (var0.getBlockEntity(var1) != null) {
         return true;
      } else {
         return !x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.builder_respects_player_builds
            ? false
            : var2.isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("smart_colonies", "builder_protected")));
      }
   }

   private BlockPos x2m6fmt7uq9mrl(ServerWorld var1, int var2, int var3) {
      int var4 = var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var2, var3);
      BlockPos var5 = new BlockPos(var2, var4, var3);
      BlockState var6 = var1.getBlockState(var5.down());
      return var6.isOf(Blocks.SNOW) && var6.get(SnowBlock.LAYERS) < 8 ? var5.down() : var5;
   }

   private static boolean x2wwfwdfwbj3ts(String var0) {
      return var0.contains("planks")
         || var0.contains("wood")
         || var0.contains("log")
         || var0.contains("fence")
         || var0.contains("door")
         || var0.contains("trapdoor")
         || var0.contains("wooden_")
         || var0.contains("_stairs")
         || var0.contains("_slab");
   }

   public String debugState() {
      if (this.x6xecrdg78nv4 != null && this.x6xecrdg78nv4.xmu75rcopbjnh() < this.x2npvmjy6y5jzn.size()) {
         x2xwxksqmx9pch var1 = this.x2npvmjy6y5jzn.get(this.x6xecrdg78nv4.xmu75rcopbjnh());
         return (var1.x3ln30gt147gjn() ? "расчистка" : (var1.x1jjn414zs82u2() ? "фундамент" : "дом"))
            + " "
            + this.x6xecrdg78nv4.xmu75rcopbjnh()
            + "/"
            + this.x2npvmjy6y5jzn.size()
            + " target="
            + var1.x31k0w8t7ul189().toShortString()
            + " block="
            + Registries.BLOCK.getId(var1.x2hmkda3gwcacx().getBlock())
            + (this.x2nyqpt8pxcmd0 == null ? "" : " stand=" + this.x2nyqpt8pxcmd0.toShortString());
      } else {
         return "ожидание";
      }
   }

   private static boolean x3eloj7pplrkw8(String var0) {
      return var0.contains("cobblestone") || var0.contains("stone") || var0.contains("andesite") || var0.contains("diorite") || var0.contains("granite");
   }

   private static void x1sqdj274gd8s2(ServerWorld var0, x2arpn38dclo1g var1, x2xwxksqmx9pch var2) {
      if (var0.getTime() % 20L == 0L) {
         for (Entity var4 : var0.getOtherEntities(
            null, new Box(var2.x31k0w8t7ul189()), var0x -> var0x.isAlive() && (var0x instanceof VillagerEntity || var0x instanceof IronGolemEntity)
         )) {
            if (var4 instanceof MobEntity var5 && x2n55y2tom37h2.x5ll9elqpk1a4(var0)) {
               BlockPos var6 = null;
               double var7 = Double.MAX_VALUE;
               int var9 = var1.x24kbqv6g4kkrg().getMaxX() - var1.x24kbqv6g4kkrg().getMinX() + 1;
               int var10 = var1.x24kbqv6g4kkrg().getMaxZ() - var1.x24kbqv6g4kkrg().getMinZ() + 1;

               for (int[] var14 : new int[][]{{-3, var10 / 2}, {var9 + 2, var10 / 2}, {var9 / 2, -3}, {var9 / 2, var10 + 2}}) {
                  int var15 = var1.x3ij6gescvakv2().getX() + var14[0];
                  int var16 = var1.x3ij6gescvakv2().getZ() + var14[1];
                  if (var0.getChunkManager().isChunkLoaded(var15 >> 4, var16 >> 4)) {
                     BlockPos var17 = new BlockPos(var15, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var15, var16), var16);
                     if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var17)
                        && !x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var17)
                        && var17.getSquaredDistance(var5.getBlockPos()) < var7) {
                        var7 = var17.getSquaredDistance(var5.getBlockPos());
                        var6 = var17;
                     }
                  }
               }

               if (var6 != null) {
                  if (var5 instanceof VillagerEntity var18) {
                     for (Task var20 : List.copyOf(var18.getBrain().getRunningTasks())) {
                        if (!(var20 instanceof x2cvbyh5y5day0) && var20.getClass().getName().startsWith("net.smartcolonies.")) {
                           var20.stop(var0, var18, var0.getTime());
                        }
                     }

                     x1c1r5c6a13gtl.xf8lnb134kd0j(var0, var18, var6, 0.9);
                  } else {
                     var5.getNavigation().startMovingTo(var6.getX() + 0.5, var6.getY(), var6.getZ() + 0.5, 0.9);
                  }
               }
            }
         }
      }
   }

   private static boolean x1zol2fshkzbul(BlockState var0, BlockState var1) {
      if (var0.getBlock() != var1.getBlock()) {
         return false;
      } else {
         for (Property var3 : var1.getProperties()) {
            if (!Set.of("shape", "north", "south", "east", "west", "up", "down", "open", "occupied", "powered", "lit", "age", "distance", "persistent")
                  .contains(var3.getName())
               && (!(var0.getBlock() instanceof ChestBlock) || !var3.getName().equals("type"))
               && !var0.get(var3).equals(var1.get(var3))) {
               return false;
            }
         }

         return true;
      }
   }

   public static int catchupStep(ServerWorld var0, x15kgoaq6i3m6i var1, x2arpn38dclo1g var2, List<x2xwxksqmx9pch> var3, int var4) {
      if (!protectPen(var0, var1, var2)) {
         return 0;
      } else if (!var2.x2nfrt8qxdscso().isPresent() && !var2.x2s8emavervqrz() && !var2.x3vu8mnvg0blk4().isPresent() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         var2.x248ps383cwu1s(var3.size(), var0.getTime(), 8);
         int var5 = 0;
         int var6 = 0;

         while (var2.xmu75rcopbjnh() < var3.size() && var6++ < 128 && var5 < var4 && var1.x11xljsmcmocmw() > 0) {
            x2xwxksqmx9pch var7 = (x2xwxksqmx9pch)var3.get(var2.xmu75rcopbjnh());
            BlockPos var8 = var7.x31k0w8t7ul189();
            if (!var0.getChunkManager().isChunkLoaded(var8.getX() >> 4, var8.getZ() >> 4)) {
               break;
            }

            BlockState var9 = var0.getBlockState(var8);
            if (!x1zol2fshkzbul(var9, var7.x2hmkda3gwcacx())
               && (!var7.x1jjn414zs82u2() || !var9.isSideSolidFullSquare(var0, var8, Direction.UP) || !var0.getFluidState(var8).isEmpty())
               && (!var7.x3ln30gt147gjn() || !var9.isAir())) {
               if (x10z0hm6bbvhlr(var0, var8, var9)) {
                  break;
               }

               if (var7.x3ln30gt147gjn()) {
                  if (!x3b6f14zbbbwjh(var9) || !x1dxdlnevtfbhm.x231if4rvsvptj(var0, var8, false, null)) {
                     break;
                  }
               } else {
                  boolean var10 = var9.isOf(Blocks.COBBLESTONE)
                     && var8.getY() <= var2.x3ij6gescvakv2().getY()
                     && var8.getY() >= var2.x3ij6gescvakv2().getY() - 4
                     && var8.getX() >= var2.x24kbqv6g4kkrg().getMinX()
                     && var8.getX() <= var2.x24kbqv6g4kkrg().getMaxX()
                     && var8.getZ() >= var2.x24kbqv6g4kkrg().getMinZ()
                     && var8.getZ() <= var2.x24kbqv6g4kkrg().getMaxZ();
                  if (!var9.isAir() && !x3b6f14zbbbwjh(var9) && !var7.x1jjn414zs82u2() && !var10) {
                     break;
                  }

                  Predicate var11 = materialPredicate(var7.x2hmkda3gwcacx(), materialItem(var7.x2hmkda3gwcacx()));
                  if (!x1intnp6ax9q2k.xezn2zouajb9i(var0, var1, var11)) {
                     break;
                  }

                  if (!x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var8, var7.x2hmkda3gwcacx(), 3)) {
                     x1sqdj274gd8s2(var0, var2, var7);
                     break;
                  }

                  if (!x1intnp6ax9q2k.x31mf0b9fst14k(var0, var1, var11)) {
                     xyghu7rojfjwk.x2aebumvi646n9(() -> var0.setBlockState(var8, var9, 3));
                     break;
                  }
               }

               var1.x3g65nuli3tuzl();
               var2.xdhrxq42ir75w(var0.getTime());
               var5++;
            } else {
               var2.xdhrxq42ir75w(var0.getTime());
            }
         }

         if (var2.xmu75rcopbjnh() >= var3.size()) {
            var2.x3lcv8vebl7uhh();
         }

         x2n55y2tom37h2.xchl1k1tnh3w8(var0).markDirty();
         return var5;
      } else {
         return 0;
      }
   }
}
