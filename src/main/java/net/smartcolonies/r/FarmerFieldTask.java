package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap.Type;

public final class FarmerFieldTask extends MultiTickTask<VillagerEntity> {
   private static final List<BlockPos> x8hdlkby0zqy7 = x28pmcpngjzkye();
   private Colony x2t4nzijeau7w8;
   private FarmerFieldPhase x35hbh6x2b34v;
   private BlockPos x186dphgnqvqj8;
   private BlockPos x2zlk0t7dy583;
   private int x3ge7r6nupqfxf;
   private int x2t1b8rmtaayej;
   private int x3hb4q8slofu00;
   private int x248yv75v4ezcm;
   private int x22gyzpic72nbl;
   private BlockPos x3573wm1i9fumn;
   private BlockPos xubfe0kx0uwh0;
   private BlockPos x3abk5220xo1ii;
   private BlockPos x302ihddc8urvz;
   private BlockPos x170sea3kgcr1k;
   private BlockPos x383bqm5z33o4t;
   private BlockPos xai7ni1lsqyge;
   private BlockPos x2iq1y0s1fcym6;
   private final Set<BlockPos> x35axhpl2cj5i9 = new HashSet<>();
   private long x108884j082kq2;
   private double x2k8pxoh02imeh;
   private final Map<BlockPos, Long> x1joansufiq3vh = new HashMap<>();
   private long x1hnez0xuhdh5r;
   private long x3kpmopdhvi7xy;
   private final BlockInteractionTracker x39cq9cwr96dck = new BlockInteractionTracker();
   private boolean x3uazqikqycd5p;

   public FarmerFieldTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && var1.getTime() >= this.x3kpmopdhvi7xy && ColonyProtectionUtils.x35ev8l9vc9uk7(var1)) {
         this.x2t4nzijeau7w8 = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == ColonyRole.x8jydaiei7iry && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x2t4nzijeau7w8 != null;
      } else {
         return false;
      }
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x35hbh6x2b34v = FarmerFieldPhase.xi9t9eaf5346z;
      this.x3uazqikqycd5p = false;
      this.x248yv75v4ezcm = 0;
      this.x22gyzpic72nbl = 0;
      this.x1hnez0xuhdh5r = var3;
      this.x186dphgnqvqj8 = null;
      this.x302ihddc8urvz = null;
      this.x1k14u7t078rxc();
      this.x3ge7r6nupqfxf = 0;
      this.x1joansufiq3vh.entrySet().removeIf(var2x -> var2x.getValue() <= var3);
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x2t4nzijeau7w8 != null
         && this.x2t4nzijeau7w8.roleOf(var2.getUuid()) == ColonyRole.x8jydaiei7iry
         && !this.x3uazqikqycd5p;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x1hnez0xuhdh5r) {
         if (ColonyProtectionUtils.x35ev8l9vc9uk7(var1)) {
            this.x1hnez0xuhdh5r = var3 + 2L;
            if (this.x35hbh6x2b34v == FarmerFieldPhase.xi9t9eaf5346z || this.x35hbh6x2b34v == FarmerFieldPhase.x27smpl89pkspo) {
               this.x16irl4fyul8tl(var1, var2);
            } else if (this.x248yv75v4ezcm >= 64) {
               this.x35hbh6x2b34v = FarmerFieldPhase.x27smpl89pkspo;
            } else if (this.x35hbh6x2b34v == FarmerFieldPhase.xb5ox5sk2beiz) {
               if (!this.x2osz16rzlj1uy(var1, var2)) {
                  if (this.x22gyzpic72nbl >= 1) {
                     // Only build a farm when NEEDED:
                     // 1. Colony has less than 28 farmland blocks (i.e. no full farm yet)
                     // 2. Farmer has a water bucket
                     // 3. Farmer has at least 14 seeds in inventory
                     int currentFarmland = this.x2t4nzijeau7w8.x1hdthtpd2qb8g().size();
                     int maxFarmland = Math.max(28, this.x2t4nzijeau7w8.radius() > 120 ? 56 : 28);
                     if (currentFarmland < maxFarmland && x3ek8tyax561ln(var2) >= 14 && x23na3hzbpcw6h(var2, Items.WATER_BUCKET)) {
                        this.x2zlk0t7dy583 = this.x2zlm4l0rs2vkb(var1, var2).orElse(null);
                        if (this.x2zlk0t7dy583 != null) {
                           this.x35hbh6x2b34v = FarmerFieldPhase.x3iyjmrguzv8fd;
                           this.x3hb4q8slofu00 = 0;
                           this.x186dphgnqvqj8 = this.x2zlk0t7dy583;
                           return;
                        }
                     }

                     this.x35hbh6x2b34v = FarmerFieldPhase.x27smpl89pkspo;
                  }
               }
            } else if (this.x35hbh6x2b34v == FarmerFieldPhase.x3iyjmrguzv8fd) {
               this.x3cfcl93yta6x3(var1, var2);
            } else if (this.x186dphgnqvqj8 == null) {
               this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
            } else if (this.x2t4nzijeau7w8.x2a24huns14rw4(this.x186dphgnqvqj8, 3)) {
               this.x1joansufiq3vh.put(this.x186dphgnqvqj8.toImmutable(), var3 + 1200L);
               this.x186dphgnqvqj8 = null;
               this.x302ihddc8urvz = null;
               this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
               this.x3ge7r6nupqfxf = 0;
               this.x22gyzpic72nbl = 0;
               ColonyChatFeedback.x1qd0mutwsr3sc(var2, "цель фермера освобождена для стройплощадки");
            } else {
               var2.getBrain().remember(ColonyMemoryModules.x2bwom1pasl6g5, this.x186dphgnqvqj8);
               if (this.x3ihyxa2ovvxin(var1, var2, this.x186dphgnqvqj8)) {
                  BlockState var5 = var1.getBlockState(this.x186dphgnqvqj8);
                  if (this.x39cq9cwr96dck.xpohy1q30uxlk(var1, var2, this.x186dphgnqvqj8, ColonyConfig.x2by5f9ehxvwk9().work.farming_ticks, false)) {
                     if (this.x35hbh6x2b34v == FarmerFieldPhase.x35y9muclzp2wc) {
                        if (var5.getBlock() instanceof CropBlock var6 && var6.isMature(var5)) {
                           Item var9 = var5.isOf(Blocks.CARROTS)
                              ? Items.CARROT
                              : (var5.isOf(Blocks.POTATOES) ? Items.POTATO : (var5.isOf(Blocks.BEETROOTS) ? Items.BEETROOT : Items.WHEAT));
                           Item var8 = var5.isOf(Blocks.CARROTS)
                              ? Items.CARROT
                              : (var5.isOf(Blocks.POTATOES) ? Items.POTATO : (var5.isOf(Blocks.BEETROOTS) ? Items.BEETROOT_SEEDS : Items.WHEAT_SEEDS));
                           if (this.x14ghv3cndp6s0(var1, this.x186dphgnqvqj8.down())
                              && var6.getDefaultState().canPlaceAt(var1, this.x186dphgnqvqj8)
                              && ColonyProtectionUtils.x1bq0f8n53gtob(var1, this.x186dphgnqvqj8, var6.getDefaultState(), 3)) {
                              this.x3bk6yjss3lhsi(var1, this.x186dphgnqvqj8.down());
                              this.xfiiclr55ays8(var1, var2, new ItemStack(var9, var8 == var9 ? 2 : 1));
                              if (var8 != var9) {
                                 this.xfiiclr55ays8(var1, var2, new ItemStack(var8, 2));
                              }

                              ColonyChatFeedback.x2t98zlq4lu49t(var2, "сбор и пересев", 1);
                              this.x248yv75v4ezcm++;
                           }
                        } else if ((var5.isOf(Blocks.PUMPKIN) || var5.isOf(Blocks.MELON))
                           && this.x14ghv3cndp6s0(var1, this.x186dphgnqvqj8.down())
                           && ColonyProtectionUtils.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)) {
                           this.xfiiclr55ays8(var1, var2, new ItemStack(var5.isOf(Blocks.PUMPKIN) ? Items.PUMPKIN : Items.MELON_SLICE, 1));
                           this.x248yv75v4ezcm++;
                        }
                     } else if (this.x35hbh6x2b34v == FarmerFieldPhase.x2w0oud80dmgzo) {
                        if (var5.isOf(Blocks.HAY_BLOCK)
                           && this.x3fr9c9b48dbto(var1, this.x186dphgnqvqj8)
                           && ColonyProtectionUtils.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)) {
                           this.xfiiclr55ays8(var1, var2, new ItemStack(Items.WHEAT, 9));
                           this.x248yv75v4ezcm++;
                           var2.swingHand(Hand.MAIN_HAND);
                        }
                     } else if (this.x35hbh6x2b34v == FarmerFieldPhase.x1q82g0tnxasf1) {
                        this.x10t5vgir9ic2y(var1, var2, this.x186dphgnqvqj8.down());
                     } else if (this.x35hbh6x2b34v == FarmerFieldPhase.x2vx6hii50nsm4) {
                        if ((var5.isOf(Blocks.SHORT_GRASS) || var5.isOf(Blocks.TALL_GRASS))
                           && ColonyProtectionUtils.x231if4rvsvptj(var1, this.x186dphgnqvqj8, false, var2)
                           && var1.random.nextBoolean()) {
                           this.xfiiclr55ays8(var1, var2, new ItemStack(Items.WHEAT_SEEDS));
                        }
                     } else if (this.x35hbh6x2b34v == FarmerFieldPhase.x30wk28d2cesij
                        && x3g51t72kitfhf(var1, this.x186dphgnqvqj8)
                        && x17w4mwk2x8sut(var2, Items.BUCKET)) {
                        this.xfiiclr55ays8(var1, var2, new ItemStack(Items.WATER_BUCKET));
                     }

                     var2.swingHand(Hand.MAIN_HAND);
                     this.x186dphgnqvqj8 = null;
                     this.x302ihddc8urvz = null;
                     this.x22gyzpic72nbl = 0;
                     if (this.x35hbh6x2b34v != FarmerFieldPhase.x27smpl89pkspo) {
                        this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
                     }
                  }
               }
            }
         }
      }
   }

   private void x16irl4fyul8tl(ServerWorld var1, VillagerEntity var2) {
      Optional var3 = ColonyManager.x3jrfw2d3yykeu(var1, this.x2t4nzijeau7w8, var2, StorageCategory.xnpajviatkbuo);
      if (this.x35hbh6x2b34v == FarmerFieldPhase.xi9t9eaf5346z && x3ek8tyax561ln(var2) < 8) {
         Optional var4 = this.x2t4nzijeau7w8
            .x2ad3s8aentmo4()
            .stream()
            .filter(var1x -> var1.getChunkManager().isChunkLoaded(var1x.getX() >> 4, var1x.getZ() >> 4))
            .filter(
               var1x -> var1.getBlockEntity(var1x) instanceof Inventory var2x
                  && IntStream.range(0, var2x.size()).anyMatch(var1xx -> x1lanxufahckh0(var2x.getStack(var1xx)))
            )
            .min(
               Comparator.<BlockPos>comparingInt(
                     var1x -> {
                        Inventory var2x = (Inventory)var1.getBlockEntity(var1x);
                        return IntStream.range(0, var2x.size())
                              .anyMatch(var1xx -> x1lanxufahckh0(var2x.getStack(var1xx)) && !var2x.getStack(var1xx).isOf(Items.PUMPKIN_SEEDS))
                           ? 0
                           : 1;
                     }
                  )
                  .thenComparingDouble(var1x -> var1x.getSquaredDistance(var2.getBlockPos()))
            );
         if (var4.isPresent()) {
            var3 = var4;
         }
      }

      if (var3.isEmpty()) {
         this.x3uazqikqycd5p = true;
      } else if (this.x3ihyxa2ovvxin(var1, var2, (BlockPos)var3.get())) {
         for (int var8 = 0; var8 < var2.getInventory().size(); var8++) {
            ItemStack var5 = var2.getInventory().getStack(var8);
            if (x1lanxufahckh0(var5)) {
               int var6 = Math.max(0, var5.getCount() - 8);
               if (var6 > 0) {
                  ItemStack var7 = var5.copyWithCount(var6);
                  ColonyChatFeedback.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var7);
                  var5.decrement(var6 - var7.getCount());
               }
            } else if (var5.isOf(Items.BREAD)) {
               int var12 = Math.max(0, var5.getCount() - 1);
               if (var12 > 0) {
                  ItemStack var13 = var5.copyWithCount(var12);
                  ColonyChatFeedback.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var13);
                  var5.decrement(var12 - var13.getCount());
               }
            } else if (x1lanxufahckh0(var5)
               || var5.isOf(Items.WHEAT)
               || var5.isOf(Items.BREAD)
               || var5.isOf(Items.BEETROOT)
               || var5.isOf(Items.PUMPKIN)
               || var5.isOf(Items.MELON_SLICE)) {
               ColonyChatFeedback.x25cotfj45r525(var1, this.x2t4nzijeau7w8, var2, var5);
            }
         }

         int var9 = 0;

         while (
            var9 < 32
               && ColonyManager.x1mlne8tl8k5ag(var1, this.x2t4nzijeau7w8, var0 -> var0.isOf(Items.WHEAT)) >= 19
               && ColonyManager.x38ipozok9mc0x(
                  var1,
                  this.x2t4nzijeau7w8,
                  () -> ColonyManager.x22hjebj4vmztt(var1, this.x2t4nzijeau7w8, var0 -> var0.isOf(Items.WHEAT), 3),
                  List.of(new ItemStack(Items.BREAD))
               )
         ) {
            var9++;
         }

         if (!x2tznyqefzmfni(var2)) {
            ColonyManager.x3lxwdirnn77qc(var1, this.x2t4nzijeau7w8, var0 -> var0.get(DataComponentTypes.FOOD) != null)
               .ifPresent(var3x -> this.xfiiclr55ays8(var1, var2, var3x));
         }

         if (this.x35hbh6x2b34v == FarmerFieldPhase.x27smpl89pkspo) {
            this.x3uazqikqycd5p = true;
            this.x3kpmopdhvi7xy = var1.getTime() + 20L;
         } else {
            VillagerBucketRefillHelper.x31tan2efb24l6(var1, this.x2t4nzijeau7w8, var2, ItemTags.HOES, Items.WOODEN_HOE, Items.STONE_HOE);
            VillagerBucketRefillHelper.x3bl8qz81ha13t(var1, this.x2t4nzijeau7w8, var2);
            if (!ColonyManager.x10q255l9ii1rw(var1, this.x2t4nzijeau7w8, Items.PUMPKIN_SEEDS)
               && ColonyManager.x1mlne8tl8k5ag(var1, this.x2t4nzijeau7w8, var0 -> var0.isOf(Items.PUMPKIN)) >= 2) {
               ColonyManager.x38ipozok9mc0x(
                  var1,
                  this.x2t4nzijeau7w8,
                  () -> ColonyManager.x22hjebj4vmztt(var1, this.x2t4nzijeau7w8, var0 -> var0.isOf(Items.PUMPKIN), 1),
                  List.of(new ItemStack(Items.PUMPKIN_SEEDS, 4))
               );
            }

            if (x3ek8tyax561ln(var2) < 32) {
               var9 = 32 - x3ek8tyax561ln(var2);
               Optional<ItemStack> var11 = ColonyManager.x5jd7014csotp(var1, (BlockPos)var3.get(), var0 -> x1lanxufahckh0(var0) && !var0.isOf(Items.PUMPKIN_SEEDS), var9);
               if (var11.isEmpty()) {
                  var11 = ColonyManager.x5jd7014csotp(var1, (BlockPos)var3.get(), FarmerFieldTask::x1lanxufahckh0, Math.min(4, var9));
               }

               var11.ifPresent(var3x -> this.xfiiclr55ays8(var1, var2, var3x));
            }

            this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
            this.x3ge7r6nupqfxf = 0;
            this.x22gyzpic72nbl = 0;
            this.x1k14u7t078rxc();
         }
      }
   }

   private boolean x2osz16rzlj1uy(ServerWorld var1, VillagerEntity var2) {
      int var3 = Math.min(this.x2t4nzijeau7w8.radius() - 4, 128);
      int var4 = x3ek8tyax561ln(var2);

      for (int var5 = 0; var5 < 2048; var5++) {
         if (this.x3ge7r6nupqfxf >= x8hdlkby0zqy7.size() || x8hdlkby0zqy7.get(this.x3ge7r6nupqfxf).getSquaredDistance(BlockPos.ORIGIN) > var3 * var3) {
            this.x3ge7r6nupqfxf = 0;
            this.x22gyzpic72nbl = 1;
            if (var4 > 0 && this.x170sea3kgcr1k != null) {
               this.x186dphgnqvqj8 = this.x170sea3kgcr1k;
               this.x35hbh6x2b34v = FarmerFieldPhase.x1q82g0tnxasf1;
            } else if (var4 > 0 && this.x3573wm1i9fumn != null) {
               this.x186dphgnqvqj8 = this.x3573wm1i9fumn;
               this.x35hbh6x2b34v = FarmerFieldPhase.x1q82g0tnxasf1;
            } else if (this.xai7ni1lsqyge != null) {
               this.x186dphgnqvqj8 = this.xai7ni1lsqyge;
               this.x35hbh6x2b34v = FarmerFieldPhase.x35y9muclzp2wc;
            } else if (this.x2iq1y0s1fcym6 != null) {
               this.x186dphgnqvqj8 = this.x2iq1y0s1fcym6;
               this.x35hbh6x2b34v = FarmerFieldPhase.x2w0oud80dmgzo;
            // random grass tilling disabled to enforce bounded modular farms
            } else if (var4 > 0 && this.x3abk5220xo1ii != null && !x23na3hzbpcw6h(var2, Items.WATER_BUCKET)) {
               this.x186dphgnqvqj8 = this.x3abk5220xo1ii;
               this.x35hbh6x2b34v = FarmerFieldPhase.x30wk28d2cesij;
            } else if (var4 == 0 && this.xubfe0kx0uwh0 != null) {
               this.x186dphgnqvqj8 = this.xubfe0kx0uwh0;
               this.x35hbh6x2b34v = FarmerFieldPhase.x2vx6hii50nsm4;
            }

            this.x1k14u7t078rxc();
            return this.x186dphgnqvqj8 != null;
         }

         BlockPos var6 = x8hdlkby0zqy7.get(this.x3ge7r6nupqfxf++);
         int var7 = this.x2t4nzijeau7w8.core().getX() + var6.getX();
         int var8 = this.x2t4nzijeau7w8.core().getZ() + var6.getZ();
         if (var1.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
            int var9 = var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var7, var8) - 1;

            for (int var10 = Math.max(var1.getBottomY(), var9 - 3); var10 <= var9 + 2; var10++) {
               BlockPos var11 = new BlockPos(var7, var10, var8);
               BlockState var12 = var1.getBlockState(var11);
               if ((
                     var12.getBlock() instanceof CropBlock
                        || var12.isOf(Blocks.FARMLAND)
                        || var12.isOf(Blocks.HAY_BLOCK)
                        || var12.isOf(Blocks.MELON)
                        || var12.isOf(Blocks.PUMPKIN)
                        || var12.isOf(Blocks.WATER)
                        || var12.isOf(Blocks.SHORT_GRASS)
                        || var12.isOf(Blocks.TALL_GRASS)
                        || x2c1mm5rnqniq6(var12) && x2csm4l72r60h(var1, var11)
                  )
                  && !this.x2t4nzijeau7w8.x2a24huns14rw4(var11, 3)
                  && ColonyBoundaryChecker.x14gj5yhqspcfw(var1, this.x2t4nzijeau7w8, var11)
                  && !ColonyState.get(var1).isPlayerBlock(var11)
                  && var1.getBlockEntity(var11) == null) {
                  if (var12.isOf(Blocks.FARMLAND)) {
                     this.x3bk6yjss3lhsi(var1, var11);
                  }

                  if (this.x1joansufiq3vh.getOrDefault(var11, 0L) <= var1.getTime() && this.x1joansufiq3vh.getOrDefault(var11.up(), 0L) <= var1.getTime()) {
                     if (var12.isOf(Blocks.HAY_BLOCK) && this.x3fr9c9b48dbto(var1, var11)) {
                        this.x2iq1y0s1fcym6 = x2xg33b8fprhix(var2, this.x2iq1y0s1fcym6, var11);
                     }

                     if (var12.getBlock() instanceof CropBlock var13 && var13.isMature(var12) && this.x14ghv3cndp6s0(var1, var11.down())
                        || var12.isOf(Blocks.MELON)
                        || var12.isOf(Blocks.PUMPKIN) && (x1zz5gn7bdzakj(var1, var11) || this.x14ghv3cndp6s0(var1, var11.down()))) {
                        this.xai7ni1lsqyge = x2xg33b8fprhix(var2, this.xai7ni1lsqyge, var11);
                     }

                     if (var4 > 0 && x2csm4l72r60h(var1, var11) && this.x7lsf0es95maw(var1, var2, var11.up()) >= 0 && this.x14ghv3cndp6s0(var1, var11)) {
                        if (var12.isOf(Blocks.FARMLAND)) {
                           this.x3573wm1i9fumn = x2xg33b8fprhix(var2, this.x3573wm1i9fumn, var11.up());
                        } else if (x2c1mm5rnqniq6(var12) && x3unncyqyb7c08(var1, var11) && xmxmjfdwaicjw(var1, var11)) {
                           this.x383bqm5z33o4t = x2xg33b8fprhix(var2, this.x383bqm5z33o4t, var11.up());
                        }
                     }

                     if (var4 > 0
                        && x2c1mm5rnqniq6(var12)
                        && x2csm4l72r60h(var1, var11)
                        && this.x7lsf0es95maw(var1, var2, var11.up()) >= 0
                        && this.x14ghv3cndp6s0(var1, var11)
                        && xmxmjfdwaicjw(var1, var11)
                        && (this.x2t4nzijeau7w8.x1hdthtpd2qb8g().contains(var11) || BuildingBlueprintHelper.x1e11i1kd8301v(var1, var11))) {
                        this.x3bk6yjss3lhsi(var1, var11);
                        this.x170sea3kgcr1k = x2xg33b8fprhix(var2, this.x170sea3kgcr1k, var11.up());
                     }

                     if (var4 == 0 && this.xubfe0kx0uwh0 == null && (var12.isOf(Blocks.SHORT_GRASS) || var12.isOf(Blocks.TALL_GRASS))) {
                        this.xubfe0kx0uwh0 = var11;
                     }

                     if (this.x3abk5220xo1ii == null && x23na3hzbpcw6h(var2, Items.BUCKET) && x3g51t72kitfhf(var1, var11)) {
                        this.x3abk5220xo1ii = var11;
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   private static BlockPos x2xg33b8fprhix(VillagerEntity var0, BlockPos var1, BlockPos var2) {
      return var1 != null && !(var2.getSquaredDistance(var0.getBlockPos()) < var1.getSquaredDistance(var0.getBlockPos())) ? var1 : var2;
   }

   private void x1k14u7t078rxc() {
      this.x3573wm1i9fumn = this.xubfe0kx0uwh0 = this.x3abk5220xo1ii = this.x170sea3kgcr1k = this.xai7ni1lsqyge = this.x2iq1y0s1fcym6 = this.x383bqm5z33o4t = null;
   }

   private void x3bk6yjss3lhsi(ServerWorld var1, BlockPos var2) {
      if (this.x2t4nzijeau7w8.x1tdgzp0ab8tn6(var2)) {
         ColonyManager.xchl1k1tnh3w8(var1).markDirty();
      }
   }

   private Optional<BlockPos> x2zlm4l0rs2vkb(ServerWorld var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < 1024; var3++) {
         BlockPos var4 = x8hdlkby0zqy7.get(Math.floorMod(this.x2t1b8rmtaayej++, x8hdlkby0zqy7.size()));
         double distSq = var4.getSquaredDistance(BlockPos.ORIGIN);
         // Keep farm away from town center plaza (min 14 blocks, max 48 blocks)
         if (distSq < 14.0 * 14.0 || distSq > 48.0 * 48.0) {
            continue;
         }

         int var6 = this.x2t4nzijeau7w8.core().getX() + var4.getX();
         int var7 = this.x2t4nzijeau7w8.core().getZ() + var4.getZ();
         if (var1.getChunkManager().isChunkLoaded(var6 >> 4, var7 >> 4)) {
            BlockPos var8 = new BlockPos(var6, var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var6, var7) - 1, var7);
            if (this.x1joansufiq3vh.getOrDefault(var8, 0L) <= var1.getTime()
               && this.x14ghv3cndp6s0(var1, var8)
               && x2csm4l72r60h(var1, var8)
               && x2c1mm5rnqniq6(var1.getBlockState(var8))) {

               // Must be at least 16 blocks away from any existing colony farmland
               boolean tooClose = this.x2t4nzijeau7w8.x1hdthtpd2qb8g().stream()
                  .anyMatch(existingPos -> existingPos.getSquaredDistance(var8) < 16.0 * 16.0);
               if (tooClose) {
                  continue;
               }

               // Check 7x9 area flatness and suitability (at least 52 of 63 blocks must be flat terrain)
               int validBlocks = 0;
               for (BlockPos var11 : BlockPos.iterate(var8.add(-3, 0, -4), var8.add(3, 0, 4))) {
                  int surfaceY = var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var11.getX(), var11.getZ()) - 1;
                  if (Math.abs(surfaceY - var8.getY()) <= 1
                     && this.x14ghv3cndp6s0(var1, var11)
                     && !this.x2t4nzijeau7w8.x2a24huns14rw4(var11, 2)
                     && !ColonyState.get(var1).isPlayerBlock(var11)) {
                     validBlocks++;
                  }
               }

               if (validBlocks >= 52) {
                  return Optional.of(var8);
               }
            }
         }
      }

      return Optional.empty();
   }

   private static boolean x2c1mm5rnqniq6(BlockState var0) {
      return var0.isOf(Blocks.DIRT) || var0.isOf(Blocks.GRASS_BLOCK);
   }

   private static boolean x2csm4l72r60h(ServerWorld var0, BlockPos var1) {
      BlockState var2 = var0.getBlockState(var1.up());
      return var2.isAir() || var2.isOf(Blocks.SHORT_GRASS) || var2.isOf(Blocks.TALL_GRASS);
   }

   private static boolean x3unncyqyb7c08(ServerWorld var0, BlockPos var1) {
      for (BlockPos var3 : BlockPos.iterate(var1.add(-1, 0, -1), var1.add(1, 0, 1))) {
         if (var0.getBlockState(var3).isOf(Blocks.FARMLAND)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x1zz5gn7bdzakj(ServerWorld var0, BlockPos var1) {
      for (Direction var3 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
         if (var0.getBlockState(var1.offset(var3)).isOf(Blocks.ATTACHED_PUMPKIN_STEM)) {
            return true;
         }
      }

      return false;
   }

   private boolean x3fr9c9b48dbto(ServerWorld var1, BlockPos var2) {
      StructureStart var3 = var1.getStructureAccessor().getStructureContaining(var2, StructureTags.VILLAGE);
      if (var3 != StructureStart.DEFAULT && var3.hasChildren() && var1.getStructureAccessor().structureContains(var2, var3)) {
         return true;
      } else if (var2.getSquaredDistance(this.x2t4nzijeau7w8.core()) > 2304.0) {
         return false;
      } else {
         for (BlockPos var5 : BlockPos.iterate(var2.add(-4, -5, -4), var2.add(4, 1, 4))) {
            if (var1.getBlockState(var5).isOf(Blocks.DIRT_PATH)) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean x14ghv3cndp6s0(ServerWorld var1, BlockPos var2) {
      if (!var1.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4)) {
         return false;
      } else if (!ColonyBoundaryChecker.x14gj5yhqspcfw(var1, this.x2t4nzijeau7w8, var2)) {
         return false;
      } else {
         for (BlockPos var4 : BlockPos.iterate(var2.add(-1, 0, -1), var2.add(1, 1, 1))) {
            BlockState var5 = var1.getBlockState(var4);
            if (var5.isOf(Blocks.DIRT_PATH) || var5.isOf(Blocks.SMOOTH_SANDSTONE) || var1.getBlockEntity(var4) != null) {
               return false;
            }
         }

         return true;
      }
   }

   private static List<BlockPos> x28pmcpngjzkye() {
      ArrayList<BlockPos> var0 = new ArrayList<>();

      for (int var1 = -128; var1 <= 128; var1++) {
         for (int var2 = -128; var2 <= 128; var2++) {
            var0.add(new BlockPos(var1, 0, var2));
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.getSquaredDistance(BlockPos.ORIGIN)));
      return List.copyOf(var0);
   }

   // Canonical Minecraft 7x9 Village Farm (plains_small_farm_1)
   private static final BlockPos[] FARM_OFFSETS_7X9 = new BlockPos[]{
      // Phase 1: Perimeter Border logs (28 blocks)
      new BlockPos(-3, 0, -4), new BlockPos(-2, 0, -4), new BlockPos(-1, 0, -4), new BlockPos(0, 0, -4), new BlockPos(1, 0, -4), new BlockPos(2, 0, -4), new BlockPos(3, 0, -4),
      new BlockPos(3, 0, -3), new BlockPos(3, 0, -2), new BlockPos(3, 0, -1), new BlockPos(3, 0, 0), new BlockPos(3, 0, 1), new BlockPos(3, 0, 2), new BlockPos(3, 0, 3),
      new BlockPos(3, 0, 4), new BlockPos(2, 0, 4), new BlockPos(1, 0, 4), new BlockPos(0, 0, 4), new BlockPos(-1, 0, 4), new BlockPos(-2, 0, 4), new BlockPos(-3, 0, 4),
      new BlockPos(-3, 0, 3), new BlockPos(-3, 0, 2), new BlockPos(-3, 0, 1), new BlockPos(-3, 0, 0), new BlockPos(-3, 0, -1), new BlockPos(-3, 0, -2), new BlockPos(-3, 0, -3),
      // Phase 2: Interior Farmland rows (28 blocks)
      new BlockPos(-2, 0, -3), new BlockPos(-2, 0, -2), new BlockPos(-2, 0, -1), new BlockPos(-2, 0, 0), new BlockPos(-2, 0, 1), new BlockPos(-2, 0, 2), new BlockPos(-2, 0, 3),
      new BlockPos(-1, 0, -3), new BlockPos(-1, 0, -2), new BlockPos(-1, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, 2), new BlockPos(-1, 0, 3),
      new BlockPos(1, 0, -3), new BlockPos(1, 0, -2), new BlockPos(1, 0, -1), new BlockPos(1, 0, 0), new BlockPos(1, 0, 1), new BlockPos(1, 0, 2), new BlockPos(1, 0, 3),
      new BlockPos(2, 0, -3), new BlockPos(2, 0, -2), new BlockPos(2, 0, -1), new BlockPos(2, 0, 0), new BlockPos(2, 0, 1), new BlockPos(2, 0, 2), new BlockPos(2, 0, 3),
      // Phase 3: Center Water Trench (7 blocks)
      new BlockPos(0, 0, -3), new BlockPos(0, 0, -2), new BlockPos(0, 0, -1), new BlockPos(0, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, 2), new BlockPos(0, 0, 3),
      // Phase 4: Composter (1 block at 2, 1, 3)
      new BlockPos(2, 1, 3)
   };

   private static BlockState getBorderBlockForBiome(ServerWorld var0, BlockPos var1) {
      Optional<RegistryKey<Biome>> var2 = var0.getBiome(var1).getKey();
      if (var2.isPresent()) {
         String var3 = var2.get().getValue().getPath();
         if (var3.contains("taiga") || var3.contains("snowy") || var3.contains("frozen") || var3.contains("grove")) {
            return Blocks.STRIPPED_SPRUCE_LOG.getDefaultState();
         }
         if (var3.contains("savanna")) {
            return Blocks.STRIPPED_ACACIA_LOG.getDefaultState();
         }
         if (var3.contains("desert") || var3.contains("badlands")) {
            return Blocks.SMOOTH_SANDSTONE.getDefaultState();
         }
         if (var3.contains("jungle")) {
            return Blocks.STRIPPED_JUNGLE_LOG.getDefaultState();
         }
         if (var3.contains("dark_forest")) {
            return Blocks.STRIPPED_DARK_OAK_LOG.getDefaultState();
         }
         if (var3.contains("birch")) {
            return Blocks.STRIPPED_BIRCH_LOG.getDefaultState();
         }
      }
      return Blocks.STRIPPED_OAK_LOG.getDefaultState();
   }

   private void x3cfcl93yta6x3(ServerWorld var1, VillagerEntity var2) {
      if (this.x2zlk0t7dy583 != null && !this.x14ghv3cndp6s0(var1, this.x2zlk0t7dy583)) {
         this.x1joansufiq3vh.put(this.x2zlk0t7dy583.toImmutable(), var1.getTime() + 1200L);
         this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
         this.x186dphgnqvqj8 = null;
         this.x2zlk0t7dy583 = null;
         this.x302ihddc8urvz = null;
         this.x3ge7r6nupqfxf = 0;
         this.x22gyzpic72nbl = 0;
      } else if (this.x3hb4q8slofu00 < FARM_OFFSETS_7X9.length) {
         var2.getBrain().remember(ColonyMemoryModules.x2bwom1pasl6g5, this.x2zlk0t7dy583);
         BlockPos var3 = this.x2zlk0t7dy583.add(FARM_OFFSETS_7X9[this.x3hb4q8slofu00]);
         if (!this.x14ghv3cndp6s0(var1, var3)) {
            this.x3hb4q8slofu00++;
            this.x302ihddc8urvz = null;
         } else if (this.x3ihyxa2ovvxin(var1, var2, var3.up())) {
            if (this.x39cq9cwr96dck.xpohy1q30uxlk(var1, var2, var3.up(), ColonyConfig.x2by5f9ehxvwk9().work.farming_ticks, false)) {
               // Support block below if empty to prevent floating/leaking
               if (var1.getBlockState(var3.down()).isAir() || !var1.getBlockState(var3.down()).isOpaqueFullCube()) {
                  ColonyProtectionUtils.x1bq0f8n53gtob(var1, var3.down(), Blocks.DIRT.getDefaultState(), 3);
               }
               // Clear air space above
               if (!var1.getBlockState(var3.up()).isAir()) {
                  ColonyProtectionUtils.x231if4rvsvptj(var1, var3.up(), false, var2);
               }

               if (this.x3hb4q8slofu00 < 28) {
                  // Phase 1: Perimeter Border log (28 blocks)
                  BlockState borderState = getBorderBlockForBiome(var1, var3);
                  ColonyProtectionUtils.x1bq0f8n53gtob(var1, var3, borderState, 3);
                  var2.swingHand(Hand.MAIN_HAND);
               } else if (this.x3hb4q8slofu00 < 56) {
                  // Phase 2: Farmland & crops (28 blocks) - guarantee all 28 farmland blocks placed
                  ColonyProtectionUtils.x1bq0f8n53gtob(var1, var3, (BlockState)Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7), 3);
                  this.x3bk6yjss3lhsi(var1, var3);
                  if (x3ek8tyax561ln(var2) > 0) {
                     this.x2o0pdq0sbpo5(var1, var2, var3.up());
                  }
                  var2.swingHand(Hand.MAIN_HAND);
               } else if (this.x3hb4q8slofu00 < 63) {
                  // Phase 3: Center Water Trench (7 blocks)
                  if (!var1.getBlockState(var3).isOf(Blocks.WATER)) {
                     ColonyProtectionUtils.x1bq0f8n53gtob(var1, var3, Blocks.WATER.getDefaultState(), 3);
                  }
                  if (this.x3hb4q8slofu00 == 62 && x17w4mwk2x8sut(var2, Items.WATER_BUCKET)) {
                     this.xfiiclr55ays8(var1, var2, new ItemStack(Items.BUCKET));
                  }
                  var2.swingHand(Hand.MAIN_HAND);
               } else {
                  // Phase 4: Composter (1 block at 2, 1, 3)
                  BlockPos composterPos = this.x2zlk0t7dy583.add(2, 1, 3);
                  if (var1.getBlockState(composterPos.down()).isOf(Blocks.FARMLAND) || x2c1mm5rnqniq6(var1.getBlockState(composterPos.down())) || var1.getBlockState(composterPos.down()).isAir()) {
                     ColonyProtectionUtils.x1bq0f8n53gtob(var1, composterPos.down(), Blocks.DIRT.getDefaultState(), 3);
                  }
                  ColonyProtectionUtils.x1bq0f8n53gtob(var1, composterPos, Blocks.COMPOSTER.getDefaultState(), 3);
                  var2.swingHand(Hand.MAIN_HAND);
                  ColonyChatFeedback.x2t98zlq4lu49t(var2, "огород построен", 1);
               }
               this.x3hb4q8slofu00++;
               this.x302ihddc8urvz = null;
            }
         }
      } else {
         this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
         this.x186dphgnqvqj8 = null;
         this.x302ihddc8urvz = null;
         this.x3ge7r6nupqfxf = 0;
         this.x22gyzpic72nbl = 0;
         this.x1k14u7t078rxc();
      }
   }

   private void x10t5vgir9ic2y(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (x3ek8tyax561ln(var2) != 0 && x2csm4l72r60h(var1, var3) && this.x14ghv3cndp6s0(var1, var3)) {
         if (this.x7lsf0es95maw(var1, var2, var3.up()) >= 0) {
            BlockState var4 = var1.getBlockState(var3);
            if (var4.isOf(Blocks.FARMLAND) || x2c1mm5rnqniq6(var4) && (this.x35hbh6x2b34v == FarmerFieldPhase.x3iyjmrguzv8fd || xmxmjfdwaicjw(var1, var3))) {
               if (!var1.getBlockState(var3.up()).isAir()) {
                  ColonyProtectionUtils.x231if4rvsvptj(var1, var3.up(), false, var2);
               }

               if (!var4.isOf(Blocks.FARMLAND)) {
                  Box var5 = new Box(var3.getX(), var3.getY(), var3.getZ(), var3.getX() + 1, var3.getY() + 0.9375, var3.getZ() + 1);
                  if (!ColonyProtectionUtils.x13f7be5vlapn7(var1, var3)
                     || !var1.getOtherEntities(null, var5, var0 -> var0.isAlive() && var0 instanceof LivingEntity).isEmpty()) {
                     return;
                  }

                  if (!var1.setBlockState(var3, (BlockState)Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7), 3)) {
                     return;
                  }
               }

               this.x3bk6yjss3lhsi(var1, var3);
               this.x2o0pdq0sbpo5(var1, var2, var3.up());
            }
         }
      }
   }

   private void x2o0pdq0sbpo5(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (this.x14ghv3cndp6s0(var1, var3.down())
         && var1.getBlockState(var3.down()).isOf(Blocks.FARMLAND)
         && var1.getBlockState(var3).isAir()
         && x1z1pu5d45jp7l(var1, var3)) {
         int var4 = -1;
         BlockPos var5 = null;
         if (this.x35axhpl2cj5i9.size() < 2) {
            for (int var6 = 0; var6 < var2.getInventory().size(); var6++) {
               if (var2.getInventory().getStack(var6).isOf(Items.PUMPKIN_SEEDS)) {
                  var5 = this.xkkvqxmgd5bq8(var1, var3);
                  if (var5 != null) {
                     var4 = var6;
                     break;
                  }
               }
            }
         }

         if (var4 < 0) {
            var4 = this.x7lsf0es95maw(var1, var2, var3);
         }

         if (var4 >= 0) {
            ItemStack var8 = var2.getInventory().getStack(var4);
            Block var7 = var8.isOf(Items.CARROT)
               ? Blocks.CARROTS
               : (
                  var8.isOf(Items.POTATO)
                     ? Blocks.POTATOES
                     : (var8.isOf(Items.BEETROOT_SEEDS) ? Blocks.BEETROOTS : (var8.isOf(Items.PUMPKIN_SEEDS) ? Blocks.PUMPKIN_STEM : Blocks.WHEAT))
               );
            if (var8.isOf(Items.PUMPKIN_SEEDS)) {
               if (var5 == null) {
                  var5 = this.xkkvqxmgd5bq8(var1, var3);
               }

               if (var5 == null) {
                  return;
               }
            }

            if (var7.getDefaultState().canPlaceAt(var1, var3) && ColonyProtectionUtils.x1bq0f8n53gtob(var1, var3, var7.getDefaultState(), 3)) {
               if (var5 != null) {
                  this.x35axhpl2cj5i9.add(var5.toImmutable());
               }

               var8.decrement(1);
               var2.swingHand(Hand.MAIN_HAND);
               ColonyChatFeedback.x2t98zlq4lu49t(var2, "посадка", 1);
            }
         }
      }
   }

   private int x7lsf0es95maw(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (!x1z1pu5d45jp7l(var1, var3)) {
         return -1;
      } else {
         for (int var4 = 0; var4 < var2.getInventory().size(); var4++) {
            ItemStack var5 = var2.getInventory().getStack(var4);
            if (!var5.isEmpty()
               && x1lanxufahckh0(var5)
               && (!var5.isOf(Items.PUMPKIN_SEEDS) || this.x35axhpl2cj5i9.size() < 2 && this.xkkvqxmgd5bq8(var1, var3) != null)) {
               return var4;
            }
         }

         return -1;
      }
   }

   private static boolean x1z1pu5d45jp7l(ServerWorld var0, BlockPos var1) {
      return var0.getLightLevel(var1) >= 8 || var0.isSkyVisible(var1);
   }

   private BlockPos xkkvqxmgd5bq8(ServerWorld var1, BlockPos var2) {
      for (Direction var4 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
         BlockPos var5 = var2.down().offset(var4);
         if (!this.x35axhpl2cj5i9.contains(var5) && x2c1mm5rnqniq6(var1.getBlockState(var5)) && x2csm4l72r60h(var1, var5) && this.x14ghv3cndp6s0(var1, var5)) {
            return var5.toImmutable();
         }
      }

      return null;
   }

   private boolean x3ihyxa2ovvxin(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      double var4 = var2.getBlockPos().getSquaredDistance(var3);
      if (var4 <= 9.0) {
         this.x302ihddc8urvz = null;
         return true;
      } else {
         if (!var3.equals(this.x302ihddc8urvz)) {
            this.x302ihddc8urvz = var3;
            this.x2k8pxoh02imeh = var4;
            this.x108884j082kq2 = var1.getTime();
         }

         if (var4 < this.x2k8pxoh02imeh - 1.0) {
            this.x2k8pxoh02imeh = var4;
            this.x108884j082kq2 = var1.getTime();
         }

         if (var1.getTime() - this.x108884j082kq2 <= 200L) {
            ColonyNavigationHelper.x2wzylgizxltef(var1, var2, var3, 0.9);
            return false;
         } else {
            this.x1joansufiq3vh.put(var3, var1.getTime() + 1200L);
            if (this.x35hbh6x2b34v != FarmerFieldPhase.xi9t9eaf5346z && this.x35hbh6x2b34v != FarmerFieldPhase.x27smpl89pkspo) {
               if (this.x35hbh6x2b34v == FarmerFieldPhase.x3iyjmrguzv8fd) {
                  this.x1joansufiq3vh.put(this.x2zlk0t7dy583, var1.getTime() + 1200L);
               }

               this.x35hbh6x2b34v = FarmerFieldPhase.xb5ox5sk2beiz;
               this.x186dphgnqvqj8 = null;
               this.x3ge7r6nupqfxf = 0;
               this.x22gyzpic72nbl = 0;
               this.x1k14u7t078rxc();
            } else {
               this.x3uazqikqycd5p = true;
               this.x3kpmopdhvi7xy = var1.getTime() + 100L;
            }

            this.x302ihddc8urvz = null;
            return false;
         }
      }
   }

   public String debugState() {
      return this.x35hbh6x2b34v == null ? "ожидание" : this.x35hbh6x2b34v.name().toLowerCase(Locale.ROOT);
   }

   private static boolean x1lanxufahckh0(ItemStack var0) {
      return var0.isOf(Items.WHEAT_SEEDS)
         || var0.isOf(Items.BEETROOT_SEEDS)
         || var0.isOf(Items.CARROT)
         || var0.isOf(Items.POTATO)
         || var0.isOf(Items.PUMPKIN_SEEDS);
   }

   private static int x3ek8tyax561ln(VillagerEntity var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (x1lanxufahckh0(var0.getInventory().getStack(var2))) {
            var1 += var0.getInventory().getStack(var2).getCount();
         }
      }

      return var1;
   }

   private static boolean x23na3hzbpcw6h(VillagerEntity var0, Item var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (var0.getInventory().getStack(var2).isOf(var1)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x2tznyqefzmfni(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         if (var0.getInventory().getStack(var1).get(DataComponentTypes.FOOD) != null) {
            return true;
         }
      }

      return false;
   }

   private static boolean x17w4mwk2x8sut(VillagerEntity var0, Item var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         if (var0.getInventory().getStack(var2).isOf(var1)) {
            var0.getInventory().getStack(var2).decrement(1);
            return true;
         }
      }

      return false;
   }

   private void xfiiclr55ays8(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      ItemStack var4 = var2.getInventory().addStack(var3);
      if (!var4.isEmpty()) {
         var2.dropStack(var1, var4);
         this.x35hbh6x2b34v = FarmerFieldPhase.x27smpl89pkspo;
      }
   }

   private static boolean xmxmjfdwaicjw(ServerWorld var0, BlockPos var1) {
      for (BlockPos var3 : BlockPos.iterate(var1.add(-4, 0, -4), var1.add(4, 1, 4))) {
         if (var0.getBlockState(var3).isOf(Blocks.WATER)) {
            return true;
         }
      }

      return false;
   }

   private static boolean x3g51t72kitfhf(ServerWorld var0, BlockPos var1) {
      if (var0.getBlockState(var1).isOf(Blocks.WATER) && var0.getFluidState(var1).isStill()) {
         int var2 = 0;

         for (Direction var4 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
            if (var0.getBlockState(var1.offset(var4)).isOf(Blocks.WATER)) {
               var2++;
            }
         }

         return var2 >= 2;
      } else {
         return false;
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x39cq9cwr96dck.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      var2.getBrain().forget(ColonyMemoryModules.x2bwom1pasl6g5);
      this.x2t4nzijeau7w8 = null;
      this.x186dphgnqvqj8 = null;
   }
}
