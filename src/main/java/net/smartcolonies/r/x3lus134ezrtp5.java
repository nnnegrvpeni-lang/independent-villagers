package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap.Type;

public final class x3lus134ezrtp5 extends MultiTickTask<VillagerEntity> {
   private x15kgoaq6i3m6i x3vq8ve3p9yo05;
   private BlockPos x6y72uqrb3ast;
   private BlockPos x20nwp91vek9gs;
   private final ArrayDeque<BlockPos> x3lwz7qzwqov02 = new ArrayDeque<>();
   private int x1fcyn4788lnom;
   private long xlugh0yctpavf;
   private final x20lc7jle5mw9c x2sdf6du2dt880 = new x20lc7jle5mw9c();
   private int x1fyvpgtwlnea1;
   private Block x2ec4pv7n77crf;
   private boolean x36rtxyjcbqu0h;
   private BlockPos x3q8evkcp5s5ah;
   private boolean x1wdryuolkw2ax;
   private BlockPos x1lk82divdauiy;
   private static final List<BlockPos> x1mx6rmh8pcz11 = x1m23fzqx9vjd6();

   public x3lus134ezrtp5() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 6000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         Optional var3 = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(
               var1x -> var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x2hwljbtybtlwc
                  || var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r && !var1x.x2s37os1kht7vr() && var1x.x2ad3s8aentmo4().isEmpty()
            );
         if (var3.isEmpty()) {
            return false;
         } else if (this.xzivu84hciub6(var2) >= 4) {
            this.x3vq8ve3p9yo05 = (x15kgoaq6i3m6i)var3.get();
            this.x6y72uqrb3ast = null;
            this.x20nwp91vek9gs = null;
            this.x3lwz7qzwqov02.clear();
            this.x36rtxyjcbqu0h = true;
            this.x3q8evkcp5s5ah = null;
            return true;
         } else {
            Optional var4 = this.x1w0zwh8g6ezr3(var1, (x15kgoaq6i3m6i)var3.get(), var2);
            if (var4.isEmpty()) {
               return false;
            } else {
               this.x3vq8ve3p9yo05 = (x15kgoaq6i3m6i)var3.get();
               this.x6y72uqrb3ast = (BlockPos)var4.get();
               this.x3lwz7qzwqov02.clear();
               this.x3lwz7qzwqov02.addAll(xacevez0qzktk(var1, this.x6y72uqrb3ast));
               this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
               this.x1fyvpgtwlnea1 = 0;
               this.x36rtxyjcbqu0h = false;
               this.x3q8evkcp5s5ah = null;
               this.x2ec4pv7n77crf = this.x4sv44f0zvtt5(var1.getBlockState(this.x6y72uqrb3ast));
               var2.getBrain().remember(xxao083dbo7ag.x1h4kjv4hra8oe, this.x6y72uqrb3ast);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x3vq8ve3p9yo05 != null
         && (
            this.x3vq8ve3p9yo05.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x2hwljbtybtlwc
               || this.x3vq8ve3p9yo05.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r
                  && !this.x3vq8ve3p9yo05.x2s37os1kht7vr()
                  && this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty()
         )
         && (this.x36rtxyjcbqu0h || this.x20nwp91vek9gs != null);
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.xlugh0yctpavf = var3;
      this.x1wdryuolkw2ax = !var2.getMainHandStack().isIn(ItemTags.AXES) && !this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty();
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.xlugh0yctpavf) {
         if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
            if (this.x1wdryuolkw2ax) {
               Optional var5 = x2n55y2tom37h2.x3jrfw2d3yykeu(var1, this.x3vq8ve3p9yo05, var2, x2jj5edkiu5orf.x24dt8qhwemmti);
               if (var5.isPresent() && var2.getBlockPos().getSquaredDistance((Vec3i)var5.get()) > 9.0) {
                  x1c1r5c6a13gtl.x2wzylgizxltef(
                     var1,
                     var2,
                     BlockPos.ofFloored(((BlockPos)var5.get()).getX() + 0.5, ((BlockPos)var5.get()).getY(), ((BlockPos)var5.get()).getZ() + 0.5),
                     0.8
                  );
                  this.xlugh0yctpavf = var3 + 10L;
                  return;
               }

               x2fl60kuzia5xk.x31tan2efb24l6(var1, this.x3vq8ve3p9yo05, var2, ItemTags.AXES, Items.WOODEN_AXE, Items.STONE_AXE);
               this.x1wdryuolkw2ax = false;
            }

            if (this.x36rtxyjcbqu0h) {
               this.x2jcnu6th3z2yp(var1, var2, var3);
            } else {
               while (this.x20nwp91vek9gs != null && !var1.getBlockState(this.x20nwp91vek9gs).isIn(BlockTags.LOGS)) {
                  this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
               }

               if (this.x20nwp91vek9gs == null) {
                  this.x1n88l7ttiksvi(var1, var2);
                  this.x36rtxyjcbqu0h = true;
               } else {
                  BlockState var10 = var1.getBlockState(this.x20nwp91vek9gs);
                  if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var1, this.x20nwp91vek9gs)) {
                     this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
                     this.xlugh0yctpavf = var3 + 2L;
                  } else {
                     double var6 = var2.squaredDistanceTo(this.x6y72uqrb3ast.getX() + 0.5, var2.getY(), this.x6y72uqrb3ast.getZ() + 0.5);
                     if (var6 > 25.0) {
                        x1c1r5c6a13gtl.x2wzylgizxltef(
                           var1, var2, BlockPos.ofFloored(this.x6y72uqrb3ast.getX() + 0.5, this.x6y72uqrb3ast.getY(), this.x6y72uqrb3ast.getZ() + 0.5), 0.6
                        );
                     } else {
                        ItemStack var8 = new ItemStack(var10.getBlock().asItem());
                        if (this.x2sdf6du2dt880
                           .xpohy1q30uxlk(var1, var2, this.x20nwp91vek9gs, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, this.x20nwp91vek9gs), true)) {
                           if (x1dxdlnevtfbhm.x231if4rvsvptj(var1, this.x20nwp91vek9gs, false, var2)) {
                              ItemStack var9 = var2.getInventory().addStack(var8);
                              if (!var9.isEmpty()) {
                                 var2.dropStack(var1, var9);
                              }

                              this.x1fyvpgtwlnea1++;
                              var2.swingHand(Hand.MAIN_HAND);
                              this.x20nwp91vek9gs = this.x3lwz7qzwqov02.pollFirst();
                              if (this.x20nwp91vek9gs == null) {
                                 this.x1n88l7ttiksvi(var1, var2);
                                 this.x36rtxyjcbqu0h = true;
                              }
                           }

                           this.xlugh0yctpavf = var3 + 1L;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x2sdf6du2dt880.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      this.x3vq8ve3p9yo05 = null;
      this.x6y72uqrb3ast = null;
      this.x20nwp91vek9gs = null;
      this.x3lwz7qzwqov02.clear();
      this.x3q8evkcp5s5ah = null;
      var2.getBrain().forget(xxao083dbo7ag.x1h4kjv4hra8oe);
   }

   private Optional<BlockPos> x1w0zwh8g6ezr3(ServerWorld var1, x15kgoaq6i3m6i var2, VillagerEntity var3) {
      BlockPos var4 = var3.getBlockPos();
      int var5 = Math.min(var2.radius() - 4, 96);
      if (this.x1lk82divdauiy == null || this.x1lk82divdauiy.getSquaredDistance(var4) > 256.0 || this.x1fcyn4788lnom >= x1mx6rmh8pcz11.size()) {
         this.x1lk82divdauiy = var4.toImmutable();
         this.x1fcyn4788lnom = 0;
      }

      for (int var6 = 0; var6 < 1024 && this.x1fcyn4788lnom < x1mx6rmh8pcz11.size(); var6++) {
         BlockPos var7 = x1mx6rmh8pcz11.get(this.x1fcyn4788lnom++);
         int var8 = this.x1lk82divdauiy.getX() + var7.getX();
         int var9 = this.x1lk82divdauiy.getZ() + var7.getZ();
         double var10 = (double)var8 - var2.core().getX();
         double var12 = (double)var9 - var2.core().getZ();
         if (!(var10 * var10 + var12 * var12 > (double)var5 * var5) && var1.getChunkManager().isChunkLoaded(var8 >> 4, var9 >> 4)) {
            int var14 = var1.getTopY(Type.MOTION_BLOCKING, var8, var9) - 1;

            for (int var15 = var14; var15 >= var14 - 40; var15--) {
               BlockPos var16 = new BlockPos(var8, var15, var9);
               if (var1.getBlockState(var16).isIn(BlockTags.LOGS)) {
                  Optional var17 = x1cm237nywz8lg(var1, var16);
                  if (var17.isPresent()
                     && !x1c1r5c6a13gtl.xps35ehel6nkk(var3, (BlockPos)var17.get(), var1.getTime())
                     && !x3d5vk1zj3fvkj.get(var1).isPlayerBlock((BlockPos)var17.get())
                     && isNaturalTree(var1, var2, (BlockPos)var17.get())) {
                     return var17;
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static List<BlockPos> x1m23fzqx9vjd6() {
      List<BlockPos> var0 = new ArrayList<>();

      for (int var1 = -96; var1 <= 96; var1++) {
         for (int var2 = -96; var2 <= 96; var2++) {
            if (var1 * var1 + var2 * var2 <= 9216) {
               var0.add(new BlockPos(var1, 0, var2));
            }
         }
      }

      var0.sort(Comparator.comparingInt(var0x -> var0x.getX() * var0x.getX() + var0x.getZ() * var0x.getZ()));
      return List.copyOf(var0);
   }

   private static Optional<BlockPos> x1cm237nywz8lg(ServerWorld var0, BlockPos var1) {
      return x3plp61tcex66x(var0, var1, true)
         .stream()
         .filter(var1x -> x25k1zpzqhtmjn(var0, var1x.down()))
         .min(Comparator.comparingInt(Vec3i::getY).thenComparingDouble(var1x -> var1x.getSquaredDistance(var1)));
   }

   private static List<BlockPos> xacevez0qzktk(ServerWorld var0, BlockPos var1) {
      List var2 = x3plp61tcex66x(var0, var1, false);
      var2.sort(Comparator.comparingInt(Vec3i::getY).thenComparingDouble(var1x -> var1x.getSquaredDistance(var1)));
      return var2;
   }

   private static List<BlockPos> x3plp61tcex66x(ServerWorld var0, BlockPos var1, boolean var2) {
      ArrayDeque var3 = new ArrayDeque();
      HashSet var4 = new HashSet();
      ArrayList var5 = new ArrayList();
      var3.add(var1.toImmutable());
      var4.add(var1.toImmutable());

      while (!var3.isEmpty() && var5.size() < 256) {
         BlockPos var6 = (BlockPos)var3.removeFirst();
         if (var0.getBlockState(var6).isIn(BlockTags.LOGS)
            && Math.abs(var6.getX() - var1.getX()) <= 10
            && Math.abs(var6.getZ() - var1.getZ()) <= 10
            && var6.getY() >= (var2 ? var1.getY() - 40 : var1.getY())
            && var6.getY() <= var1.getY() + 40) {
            var5.add(var6);

            for (int var7 = -1; var7 <= 1; var7++) {
               for (int var8 = -1; var8 <= 1; var8++) {
                  for (int var9 = -1; var9 <= 1; var9++) {
                     if (var7 != 0 || var8 != 0 || var9 != 0) {
                        BlockPos var10 = var6.add(var7, var8, var9).toImmutable();
                        if (var4.add(var10)) {
                           var3.addLast(var10);
                        }
                     }
                  }
               }
            }
         }
      }

      return var5;
   }

   public static boolean isNaturalTree(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (!x25k1zpzqhtmjn(var0, var2.down())) {
         return false;
      } else {
         BlockState var3 = var0.getBlockState(var2);
         String var4 = Registries.BLOCK.getId(var3.getBlock()).getPath();
         if (var4.endsWith("_log") && !var4.startsWith("stripped_")) {
            List<BlockPos> var5 = xacevez0qzktk(var0, var2);
            if (var5.size() < 3) {
               return false;
            } else {
               int var6 = var5.stream().mapToInt(Vec3i::getY).max().orElse(var2.getY()) - var2.getY() + 1;
               if (var6 >= 3 && var6 <= 40) {
                  for (x2arpn38dclo1g var8 : var1.x1ja61emeln2hc()) {
                     BlockBox var9 = var8.x24kbqv6g4kkrg();
                     if (var2.getX() >= var9.getMinX() - 5
                        && var2.getX() <= var9.getMaxX() + 5
                        && var2.getZ() >= var9.getMinZ() - 5
                        && var2.getZ() <= var9.getMaxZ() + 5) {
                        return false;
                     }
                  }

                  int var13 = 0;

                  for (BlockPos var16 : BlockPos.iterate(var2.add(-4, 0, -4), var2.add(4, var6 + 3, 4))) {
                     if (!var0.getChunkManager().isChunkLoaded(var16.getX() >> 4, var16.getZ() >> 4)) {
                        return false;
                     }

                     BlockState var10 = var0.getBlockState(var16);
                     if (var10.isIn(BlockTags.LEAVES) && (!var10.contains(LeavesBlock.PERSISTENT) || !(Boolean)var10.get(LeavesBlock.PERSISTENT))) {
                        var13++;
                     }
                  }

                  for (BlockPos var17 : var5) {
                     if (var17.getY() <= var2.getY() + 3) {
                        for (BlockPos var11 : BlockPos.iterate(var17.add(-1, -1, -1), var17.add(1, 1, 1))) {
                           if (!var11.equals(var17)) {
                              BlockState var12 = var0.getBlockState(var11);
                              if (var12.isIn(BlockTags.PLANKS)
                                 || var12.isIn(BlockTags.BEDS)
                                 || var12.isIn(BlockTags.DOORS)
                                 || var12.isOf(Blocks.COBBLESTONE)
                                 || var12.isOf(Blocks.STONE_BRICKS)
                                 || var0.getBlockEntity(var11) != null) {
                                 return false;
                              }
                           }
                        }
                     }
                  }

                  return var13 >= 8;
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }
   }

   private static boolean x25k1zpzqhtmjn(ServerWorld var0, BlockPos var1) {
      BlockState var2 = var0.getBlockState(var1);
      return var2.isIn(BlockTags.DIRT) || var2.isOf(Blocks.MANGROVE_ROOTS) || var2.isOf(Blocks.MUDDY_MANGROVE_ROOTS);
   }

   private void x1n88l7ttiksvi(ServerWorld var1, VillagerEntity var2) {
      if (this.x1fyvpgtwlnea1 > 0) {
         ItemStack var3 = new ItemStack(Items.STICK, Math.max(1, this.x1fyvpgtwlnea1 / 2));
         var2.getInventory().addStack(var3);
      }

      if (this.x1fyvpgtwlnea1 > 0 && this.x2ec4pv7n77crf != null) {
         boolean var13 = this.x2ec4pv7n77crf == Blocks.DARK_OAK_SAPLING || this.x2ec4pv7n77crf == Blocks.PALE_OAK_SAPLING;
         int var4 = this.x3ja1z9v4hxh83(var1, this.x6y72uqrb3ast, 12);
         double var5 = this.x6y72uqrb3ast.getSquaredDistance(this.x3vq8ve3p9yo05.core());
         int var7 = !(var5 < 1024.0) && var4 < 3 ? (var4 >= 1 ? 2 : 3) : 1;
         if (var5 < 3136.0) {
            int var8 = this.xtgy4dce41scv(var1, 10);
            if (var8 >= 10) {
               return;
            }

            var7 = Math.min(var7, 10 - var8);
         }

         if (var13) {
            if (var5 < 3136.0 && this.xtgy4dce41scv(var1, 7) > 6) {
               return;
            }

            var7 = 4;
         }

         List<BlockPos> var14 = var13
            ? List.of(this.x6y72uqrb3ast, this.x6y72uqrb3ast.east(), this.x6y72uqrb3ast.south(), this.x6y72uqrb3ast.south().east())
            : List.of(
               this.x6y72uqrb3ast,
               this.x6y72uqrb3ast.east(4),
               this.x6y72uqrb3ast.west(4),
               this.x6y72uqrb3ast.south(4),
               this.x6y72uqrb3ast.north(4),
               this.x6y72uqrb3ast.add(3, 0, 3),
               this.x6y72uqrb3ast.add(-3, 0, 3),
               this.x6y72uqrb3ast.add(3, 0, -3),
               this.x6y72uqrb3ast.add(-3, 0, -3)
            );
         int var9 = 0;
         if (!var13 || !var14.stream().anyMatch(var2x -> !this.x3q7mzgphtw30p(var1, var2x) || !this.x2ec4pv7n77crf.getDefaultState().canPlaceAt(var1, var2x))) {
            for (BlockPos var11 : var14) {
               if (var9 >= var7) {
                  break;
               }

               if (this.x3q7mzgphtw30p(var1, var11)) {
                  BlockState var12 = this.x2ec4pv7n77crf.getDefaultState();
                  if (var12.canPlaceAt(var1, var11) && x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var11, var12, 3)) {
                     var9++;
                  }
               }
            }

            if (var9 > 0) {
               var2.swingHand(Hand.MAIN_HAND);
            }
         }
      }
   }

   private int xtgy4dce41scv(ServerWorld var1, int var2) {
      int var3 = 0;

      for (int var4 = -48; var4 <= 48; var4++) {
         for (int var5 = -48; var5 <= 48; var5++) {
            if (var4 * var4 + var5 * var5 <= 2304) {
               int var6 = this.x3vq8ve3p9yo05.core().getX() + var4;
               int var7 = this.x3vq8ve3p9yo05.core().getZ() + var5;
               if (var1.getChunkManager().isChunkLoaded(var6 >> 4, var7 >> 4)) {
                  int var8 = var1.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var6, var7);

                  for (int var9 = var8; var9 >= Math.max(var1.getBottomY(), var8 - 32); var9--) {
                     if (var1.getBlockState(new BlockPos(var6, var9, var7)).isIn(BlockTags.SAPLINGS)) {
                        if (++var3 >= var2) {
                           return var3;
                        }
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   private int x3ja1z9v4hxh83(ServerWorld var1, BlockPos var2, int var3) {
      int var4 = 0;

      for (int var5 = -20; var5 <= 20; var5++) {
         for (int var6 = -20; var6 <= 20; var6++) {
            if (var5 * var5 + var6 * var6 <= 400) {
               int var7 = var2.getX() + var5;
               int var8 = var2.getZ() + var6;
               if (var1.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
                  int var9 = var1.getTopY(Type.MOTION_BLOCKING, var7, var8);

                  for (int var10 = var9; var10 >= Math.max(var1.getBottomY(), var9 - 24); var10--) {
                     BlockPos var11 = new BlockPos(var7, var10, var8);
                     BlockState var12 = var1.getBlockState(var11);
                     if (var12.isIn(BlockTags.SAPLINGS) || var12.isIn(BlockTags.LOGS) && x25k1zpzqhtmjn(var1, var11.down())) {
                        var4++;
                        break;
                     }
                  }

                  if (var4 >= var3) {
                     return var4;
                  }
               }
            }
         }
      }

      return var4;
   }

   private boolean x3q7mzgphtw30p(ServerWorld var1, BlockPos var2) {
      if (!x2fvuqnp9zks2x.x14gj5yhqspcfw(var1, this.x3vq8ve3p9yo05, var2.down())) {
         return false;
      } else if (x1dxdlnevtfbhm.x13f7be5vlapn7(var1, var2) && !x3d5vk1zj3fvkj.get(var1).isPlayerBlock(var2.down())) {
         BlockState var3 = var1.getBlockState(var2);
         if (!var3.isAir() && !var3.isReplaceable()) {
            return false;
         } else if (var1.getBlockState(var2.up()).isAir() && var1.getBlockState(var2.up(2)).isAir()) {
            BlockState var4 = var1.getBlockState(var2.down());
            if (var4.isIn(BlockTags.DIRT) && !var4.isOf(Blocks.DIRT_PATH) && !var4.isOf(Blocks.FARMLAND)) {
               for (x2arpn38dclo1g var6 : this.x3vq8ve3p9yo05.x1ja61emeln2hc()) {
                  BlockBox var7 = var6.x24kbqv6g4kkrg();
                  if (var2.getX() >= var7.getMinX() - 3
                     && var2.getX() <= var7.getMaxX() + 3
                     && var2.getZ() >= var7.getMinZ() - 3
                     && var2.getZ() <= var7.getMaxZ() + 3) {
                     return false;
                  }
               }

               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void x2jcnu6th3z2yp(ServerWorld var1, VillagerEntity var2, long var3) {
      boolean var5 = this.x3vq8ve3p9yo05.x2ad3s8aentmo4().isEmpty() || x2n55y2tom37h2.x11dwzrdveisv7(var1, this.x3vq8ve3p9yo05) <= 4;
      Optional var6 = !var5 && x2n55y2tom37h2.x57l6vd1sx1rt(var1, this.x3vq8ve3p9yo05, var0 -> var0.isIn(ItemTags.LOGS) || var0.isOf(Items.STICK))
         ? x2n55y2tom37h2.x3jrfw2d3yykeu(var1, this.x3vq8ve3p9yo05, var2, x2jj5edkiu5orf.x24dt8qhwemmti)
         : Optional.empty();
      if (var6.isEmpty()) {
         if (this.xzivu84hciub6(var2) < 4) {
            this.x36rtxyjcbqu0h = false;
            return;
         }

         if (this.x3q8evkcp5s5ah == null) {
            this.x3q8evkcp5s5ah = x2n55y2tom37h2.x2l4eb2ibsf118(var1, this.x3vq8ve3p9yo05).orElse(null);
         }

         if (this.x3q8evkcp5s5ah == null) {
            this.x36rtxyjcbqu0h = false;
            return;
         }

         var2.getBrain().remember(xxao083dbo7ag.x3o547clcv9kaw, this.x3q8evkcp5s5ah);
         if (var2.squaredDistanceTo(this.x3q8evkcp5s5ah.getX() + 0.5, this.x3q8evkcp5s5ah.getY() + 0.5, this.x3q8evkcp5s5ah.getZ() + 0.5) > 9.0) {
            x1c1r5c6a13gtl.x2wzylgizxltef(
               var1, var2, BlockPos.ofFloored(this.x3q8evkcp5s5ah.getX() + 0.5, this.x3q8evkcp5s5ah.getY(), this.x3q8evkcp5s5ah.getZ() + 0.5), 0.6
            );
            return;
         }

         BlockPos var7 = this.x3q8evkcp5s5ah.east();
         if (!var1.getBlockState(this.x3q8evkcp5s5ah).isAir() || !var1.getBlockState(var7).isAir() || this.xzivu84hciub6(var2) < 4) {
            this.x3q8evkcp5s5ah = null;
            return;
         }

         if (!x2n55y2tom37h2.xt55z8grtuvpq(var1, this.x3vq8ve3p9yo05, this.x3q8evkcp5s5ah, x2jj5edkiu5orf.x24dt8qhwemmti)) {
            this.x3q8evkcp5s5ah = null;
            return;
         }

         this.xkldxdn7jvvus(var2, 4);
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
         var2.swingHand(Hand.MAIN_HAND);
         var6 = Optional.of(this.x3q8evkcp5s5ah);
      }

      BlockPos var10 = (BlockPos)var6.get();
      if (var2.squaredDistanceTo(var10.getX() + 0.5, var10.getY() + 0.5, var10.getZ() + 0.5) > 9.0) {
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var10.getX() + 0.5, var10.getY(), var10.getZ() + 0.5), 0.6);
      } else {
         for (int var8 = 0; var8 < var2.getInventory().size(); var8++) {
            ItemStack var9 = var2.getInventory().getStack(var8);
            if (var9.isIn(ItemTags.LOGS) || var9.isOf(Items.STICK)) {
               x2vaj7rsm57i4n.x25cotfj45r525(var1, this.x3vq8ve3p9yo05, var2, var9);
            }
         }

         var2.swingHand(Hand.MAIN_HAND);
         this.x36rtxyjcbqu0h = false;
         this.xlugh0yctpavf = var3 + 20L;
      }
   }

   private int xzivu84hciub6(VillagerEntity var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.getInventory().size(); var3++) {
         ItemStack var4 = var1.getInventory().getStack(var3);
         if (var4.isIn(ItemTags.LOGS)) {
            var2 += var4.getCount();
         }
      }

      return var2;
   }

   private boolean xkldxdn7jvvus(VillagerEntity var1, int var2) {
      if (this.xzivu84hciub6(var1) < var2) {
         return false;
      } else {
         int var3 = var2;

         for (int var4 = 0; var4 < var1.getInventory().size() && var3 > 0; var4++) {
            ItemStack var5 = var1.getInventory().getStack(var4);
            if (var5.isIn(ItemTags.LOGS)) {
               int var6 = Math.min(var3, var5.getCount());
               var5.decrement(var6);
               var3 -= var6;
            }
         }

         return true;
      }
   }

   private Block x4sv44f0zvtt5(BlockState var1) {
      String var2 = Registries.BLOCK.getId(var1.getBlock()).getPath();
      if (var2.contains("dark_oak")) {
         return Blocks.DARK_OAK_SAPLING;
      } else if (var2.contains("pale_oak")) {
         return Blocks.PALE_OAK_SAPLING;
      } else if (var2.contains("spruce")) {
         return Blocks.SPRUCE_SAPLING;
      } else if (var2.contains("birch")) {
         return Blocks.BIRCH_SAPLING;
      } else if (var2.contains("acacia")) {
         return Blocks.ACACIA_SAPLING;
      } else if (var2.contains("jungle")) {
         return Blocks.JUNGLE_SAPLING;
      } else if (var2.contains("mangrove")) {
         return Blocks.MANGROVE_PROPAGULE;
      } else {
         return var2.contains("cherry") ? Blocks.CHERRY_SAPLING : Blocks.OAK_SAPLING;
      }
   }
}
