package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.poi.PointOfInterestTypes;
import net.minecraft.world.poi.PointOfInterestStorage.OccupationStatus;
import net.smartcolonies.SmartColoniesMod;

public final class x2n55y2tom37h2 {
   private static final int x2dii1ed72e0qa = 16;
   private static final int x3hsc98x8oxfm9 = 100;
   private static final int x3iy3x09j2n16t = 768;
   private static final int x3jhact2bob0zj = 33;
   private static final int x2qbzy5v090jxv = 35937;
   private static final int x39ilanc4hhepr = 100;
   private static final int x1zv0o4iephg77 = 10;
   private static final int x20glesds2tqss = 8;
   private static final int xjngvrp8ssq6v = 24;
   private static final String x1ao7ast073lam = "smart_colonies_role_name";
   private static final Map<ServerWorld, x2df6ogxvxe0yk> x3vsxsdrywaymi = new IdentityHashMap<>();

   private x2n55y2tom37h2() {
   }

   public static void x35h05qb6ebno5() {
      x3vsxsdrywaymi.clear();
      x8veg662b2f5p.x1161stz4yb35x();
      x37idyr79ed75m.x3didvqu3hbx58();
      xzszqoikocxnx.x30zm3b0hljw9q();
      x1intnp6ax9q2k.x2rwu2l29t4lpj();
      x3m66wv1c6161h.x2ik4hs9xwj2v7();
      x36u1fhs2z6uoi.clearRuntimeCaches();
   }

   public static x3d5vk1zj3fvkj xchl1k1tnh3w8(ServerWorld var0) {
      return x3d5vk1zj3fvkj.get(var0);
   }

   public static void xhvlm4bwxgmtf(ServerWorld var0) {
      x3d5vk1zj3fvkj var1 = xchl1k1tnh3w8(var0);
      if (var0.getTime() % 20L == 0L) {
         x2uh56vnzc4u51.x34rm129tews28(var0);
      }

      x6amcj7vm0jm5.x1w4hwyk1suofz(var0);
      long var2 = var0.getTime();
      int var4 = 768;
      int var5 = var0.getPlayers().isEmpty()
         ? x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_blocks_per_tick
         : x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_arrival_blocks_per_tick;
      ArrayList<x15kgoaq6i3m6i> var6 = new ArrayList<>(var1.colonies());
      if (!var6.isEmpty()) {
         Collections.rotate(var6, -Math.floorMod(var2, var6.size()));
      }

      for (x15kgoaq6i3m6i var8 : var6) {
         if (x22gnz9u3m8aag(var0, var8.core())) {
            WorldChunk var9 = var0.getChunkManager().getWorldChunk(var8.core().getX() >> 4, var8.core().getZ() >> 4);
            if (var9 != null && var9.getBlockState(var8.core()).isOf(Blocks.BELL)) {
               boolean var10 = var0.shouldTickEntityAt(var8.core()) || var8.x3tnjjviw7kr77() >= var2 - 1L;
               boolean var11 = var0.getPlayers()
                  .stream()
                  .anyMatch(
                     var2x -> x2od8cp87hfx5s(var8.core(), var2x.getBlockPos())
                        <= Math.pow((var0.getServer().getPlayerManager().getViewDistance() + 1) * 16.0, 2.0)
                  );
               if (var10 || var11) {
                  if (var8.population() == 0 && var10) {
                     x2o9v4z8h5h1n1(var0, var8, var2);
                  }

                  x1intnp6ax9q2k.x3ij59k18pnfmd(var0, var8);
                  if (var8.x3p06s7riestqm() <= 0L || !x3sqkg3n6v972r.x2by5f9ehxvwk9().village.offline_development || !x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
                     var5 -= x1intnp6ax9q2k.x12vivbc3rlygx(var0, var8, var5);
                     if (var10) {
                        if (!var8.x28w4myn4c9xwu() && (var8.x3k3m01633dfw1() == Long.MIN_VALUE || var2 - var8.x3k3m01633dfw1() >= 100L)) {
                           var8.x3db8zzffozhu6();
                        }

                        if (var8.x28w4myn4c9xwu() && var4 > 0) {
                           var4 -= xllp0ajwexj5r(var0, var8, Math.min(256, var4));
                        }

                        if (Math.floorMod(var2 + var8.core().asLong(), 10) == 0) {
                           x1t38fv75txqm7(var0, var8, var2);
                        }

                        if (var8.x202n2ys5dq0ho() == Long.MIN_VALUE || var2 - var8.x202n2ys5dq0ho() >= 100L) {
                           x2o9v4z8h5h1n1(var0, var8, var2);
                           x1asz375yez9sw(var0, var8, var0x -> var0x.isOf(Items.EGG), 32);
                           xw0xosmde9jmp.x2m8lyhoez9v7c(var0, var8);
                           x16cwhuov65ela(var0, var8);
                           x3cjs4pzi3umdr(var0, var8);
                        }

                        if (var2 % 200L == 0L) {
                           var1.markDirty();
                        }

                        if (Math.floorMod(var2 + var8.core().asLong(), 4) == 0) {
                           x39ey6ni61f3zn(var0, var8);
                        }

                        x27aao31y6r2ta.xyvb622rqbii1(var0, var8, var2);
                        if (x3sqkg3n6v972r.x2by5f9ehxvwk9().debug.render_build_boxes && var2 % 20L == 0L) {
                           x3tgmhaj405foe(var0, var8);
                        }
                     }
                  }
               }
            }
         }
      }

      x313o9woxa22fw.x28wuwjwutxh4c(var0, var1, var2);
      x8veg662b2f5p.x2twncqk5nkey3(var0);
   }

   public static Optional<x15kgoaq6i3m6i> x3psqezd5jem54(ServerWorld var0, BlockPos var1) {
      return xchl1k1tnh3w8(var0)
         .colonies()
         .stream()
         .filter(var1x -> x2od8cp87hfx5s(var1x.core(), var1) <= (double)var1x.radius() * var1x.radius())
         .min(Comparator.comparingDouble(var1x -> x2od8cp87hfx5s(var1x.core(), var1)));
   }

   public static Optional<x15kgoaq6i3m6i> x2yj2v4iudqlvb(ServerWorld var0, UUID var1) {
      return xchl1k1tnh3w8(var0).colonies().stream().filter(var1x -> var1x.x3hurh64xwtw51(var1)).findFirst();
   }

   private static double x2od8cp87hfx5s(BlockPos var0, BlockPos var1) {
      double var2 = (double)var0.getX() - var1.getX();
      double var4 = (double)var0.getZ() - var1.getZ();
      return var2 * var2 + var4 * var4;
   }

   public static Box x150ccxomrlozr(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = var1.radius();
      return new Box(
         var1.core().getX() - var2,
         var0.getBottomY(),
         var1.core().getZ() - var2,
         var1.core().getX() + var2 + 1,
         var0.getTopYInclusive() + 1,
         var1.core().getZ() + var2 + 1
      );
   }

   public static boolean x5ll9elqpk1a4(ServerWorld var0) {
      x2df6ogxvxe0yk var1 = x3vsxsdrywaymi.computeIfAbsent(var0, var0x -> new x2df6ogxvxe0yk());
      if (var1.x15wujcws0eexn != var0.getTime()) {
         var1.x15wujcws0eexn = var0.getTime();
         var1.x1kbn5io9xhyfp = 0;
      }

      if (var1.x1kbn5io9xhyfp >= 6) {
         return false;
      } else {
         var1.x1kbn5io9xhyfp++;
         return true;
      }
   }

   public static void x30py6w81fega5(ServerWorld var0, BlockPos var1) {
      x3psqezd5jem54(var0, var1).filter(var1x -> var1x.core().getSquaredDistance(var1) <= 1024.0).ifPresent(var1x -> {
         var1x.xz6ij33c0780z(var0.getTime());
         xchl1k1tnh3w8(var0).markDirty();
      });
   }

   static x15kgoaq6i3m6i x3ermfoq0kwcv0(ServerWorld var0, BlockPos var1, BlockBox var2) {
      x3d5vk1zj3fvkj var3 = xchl1k1tnh3w8(var0);
      Optional var4 = var3.colonies().stream().filter(var1x -> var1x.core().equals(var1)).findFirst();
      if (var4.isPresent()) {
         x1l3hk8rr8x5jb((x15kgoaq6i3m6i)var4.get(), var2);
         var3.markDirty();
         return (x15kgoaq6i3m6i)var4.get();
      } else {
         Optional var5 = var3.colonies().stream().filter(var2x -> var2.contains(var2x.core()) || var2x.core().getSquaredDistance(var1) < 16384.0).findFirst();
         if (var5.isPresent()) {
            x1l3hk8rr8x5jb((x15kgoaq6i3m6i)var5.get(), var2);
            var3.markDirty();
            return (x15kgoaq6i3m6i)var5.get();
         } else {
            x15kgoaq6i3m6i var6 = new x15kgoaq6i3m6i(UUID.randomUUID(), var1, 96, Long.MIN_VALUE);
            var6.x14t0iw6xpk0xx(0L);
            var6.xqdbjfmq8abgh(true);
            x1l3hk8rr8x5jb(var6, var2);
            var3.add(var6);
            SmartColoniesMod.LOGGER.info("Registered generated vanilla village at {}", var1);
            return var6;
         }
      }
   }

   static void x1l3hk8rr8x5jb(x15kgoaq6i3m6i var0, BlockBox var1) {
      int var2 = Math.max(Math.abs(var1.getMinX() - var0.core().getX()), Math.abs(var1.getMaxX() - var0.core().getX()));
      int var3 = Math.max(Math.abs(var1.getMinZ() - var0.core().getZ()), Math.abs(var1.getMaxZ() - var0.core().getZ()));
      int var4 = Math.max(96, (int)Math.ceil(Math.hypot(var2, var3)) + 16);
      var0.x1ne2dy4ss3d7w(Math.max(var0.radius(), Math.min(512, var4)));
   }

   public static void x3drrj6t9qvanr(ServerWorld var0, VillagerEntity var1, x1rxiy9mtu5b3h var2) {
      x3psqezd5jem54(var0, var1.getBlockPos()).ifPresent(var3 -> {
         var3.x36xpmo21hmp9h(var1.getUuid(), var2);
         xf6ny39u2klct.x3cumye59iar40(var0, var3, var1);
         var1.getBrain().remember(xxao083dbo7ag.x3ecoc0soedv0m, var2);
         xchl1k1tnh3w8(var0).markDirty();
      });
   }

   public static Optional<x2arpn38dclo1g> x3ajt835fnqglk(ServerWorld var0, UUID var1, Identifier var2, BlockPos var3) {
      return xchl1k1tnh3w8(var0).byId(var1).map(var3x -> {
         x2arpn38dclo1g var4 = new x2arpn38dclo1g(UUID.randomUUID(), var2, var3);
         var3x.x31ve0dsfeaxo4(var4);
         xchl1k1tnh3w8(var0).markDirty();
         return var4;
      });
   }

   public static boolean x3dsqtct85933r(ServerWorld var0, x15kgoaq6i3m6i var1, Item var2) {
      return x3lxwdirnn77qc(var0, var1, var1x -> var1x.isOf(var2)).isPresent();
   }

   public static Optional<ItemStack> x3lxwdirnn77qc(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2) {
      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var6) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               ItemStack var8 = var6.getStack(var7);
               if (!var8.isEmpty() && var2.test(var8)) {
                  ItemStack var9 = var8.copyWithCount(1);
                  var8.decrement(1);
                  var6.markDirty();
                  var1.x2sh8yupnxmo71(var9.getItem(), 1);
                  return Optional.of(var9);
               }
            }
         }
      }

      return Optional.empty();
   }

   public static Optional<ItemStack> x5jd7014csotp(ServerWorld var0, BlockPos var1, Predicate<ItemStack> var2, int var3) {
      if (var3 > 0 && x22gnz9u3m8aag(var0, var1)) {
         if (var0.getBlockEntity(var1) instanceof Inventory var5) {
            for (int var6 = 0; var6 < var5.size(); var6++) {
               ItemStack var7 = var5.getStack(var6);
               if (!var7.isEmpty() && var2.test(var7)) {
                  int var8 = Math.min(var3, var7.getCount());
                  ItemStack var9 = var7.copyWithCount(var8);
                  var7.decrement(var8);
                  var5.markDirty();
                  x3psqezd5jem54(var0, var1).ifPresent(var2x -> var2x.x2sh8yupnxmo71(var9.getItem(), var8));
                  return Optional.of(var9);
               }
            }

            return Optional.empty();
         } else {
            return Optional.empty();
         }
      } else {
         return Optional.empty();
      }
   }

   public static Optional<BlockPos> x1falh21nhy4yk(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.getBlockEntity(var1x) instanceof Inventory)
         .min(Comparator.comparingDouble(var1x -> var1x.getSquaredDistance(var2)));
   }

   public static Optional<BlockPos> xjas28crgjspn(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2, x2jj5edkiu5orf var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.getBlockEntity(var1x) instanceof Inventory)
         .min(
            Comparator.<BlockPos>comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3))
               .thenComparingDouble(var1x -> var1x.getSquaredDistance(var2))
         );
   }

   public static Optional<BlockPos> x3jrfw2d3yykeu(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, x2jj5edkiu5orf var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var1x -> var0.getBlockEntity(var1x) instanceof Inventory)
         .filter(var2x -> !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var2x, var0.getTime()))
         .min(
            Comparator.<BlockPos>comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3))
               .thenComparingDouble(var1x -> var1x.getSquaredDistance(var2.getBlockPos()))
         );
   }

   public static Optional<BlockPos> x1dhbpietunk9i(ServerWorld var0, x15kgoaq6i3m6i var1, VillagerEntity var2, Predicate<ItemStack> var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var2x -> var0.getBlockEntity(var2x) instanceof Inventory var3x && x3edwxvtjh8348(var3x, var3))
         .filter(var2x -> !x1c1r5c6a13gtl.xps35ehel6nkk(var2, var2x, var0.getTime()))
         .min(Comparator.comparingDouble(var1x -> var1x.getSquaredDistance(var2.getBlockPos())));
   }

   public static Optional<BlockPos> x2ytjyhq411pf7(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2, Predicate<ItemStack> var3) {
      return var1.x2ad3s8aentmo4()
         .stream()
         .filter(var1x -> x22gnz9u3m8aag(var0, var1x))
         .filter(var2x -> var0.getBlockEntity(var2x) instanceof Inventory var3x && x3edwxvtjh8348(var3x, var3))
         .min(Comparator.comparingDouble(var1x -> var1x.getSquaredDistance(var2)));
   }

   private static boolean x3edwxvtjh8348(Inventory var0, Predicate<ItemStack> var1) {
      for (int var2 = 0; var2 < var0.size(); var2++) {
         if (!var0.getStack(var2).isEmpty() && var1.test(var0.getStack(var2))) {
            return true;
         }
      }

      return false;
   }

   public static boolean x10q255l9ii1rw(ServerWorld var0, x15kgoaq6i3m6i var1, Item var2) {
      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var6) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               if (var6.getStack(var7).isOf(var2) && !var6.getStack(var7).isEmpty()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean x1tl8iu49onump(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2) {
      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var6) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               ItemStack var8 = var6.getStack(var7);
               if (!var8.isEmpty() && var2.test(var8)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static int x1mlne8tl8k5ag(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2) {
      int var3 = 0;

      for (BlockPos var5 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var5) && var0.getBlockEntity(var5) instanceof Inventory var7) {
            for (int var8 = 0; var8 < var7.size(); var8++) {
               ItemStack var9 = var7.getStack(var8);
               if (!var9.isEmpty() && var2.test(var9)) {
                  var3 += var9.getCount();
               }
            }
         }
      }

      return var3;
   }

   public static boolean x22hjebj4vmztt(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2, int var3) {
      if (var3 <= 0) {
         return true;
      } else if (x1mlne8tl8k5ag(var0, var1, var2) < var3) {
         return false;
      } else {
         int var4 = var3;

         for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var8) {
               for (int var9 = 0; var9 < var8.size() && var4 > 0; var9++) {
                  ItemStack var10 = var8.getStack(var9);
                  if (!var10.isEmpty() && var2.test(var10)) {
                     int var11 = Math.min(var4, var10.getCount());
                     Item var12 = var10.getItem();
                     var10.decrement(var11);
                     var1.x2sh8yupnxmo71(var12, var11);
                     var4 -= var11;
                     var8.markDirty();
                  }
               }

               if (var4 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public static int x1asz375yez9sw(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2, int var3) {
      int var4 = Math.max(0, var3);
      int var5 = 0;

      for (BlockPos var7 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var7) && var0.getBlockEntity(var7) instanceof Inventory var8) {
            for (int var13 = 0; var13 < var8.size(); var13++) {
               ItemStack var10 = var8.getStack(var13);
               if (!var10.isEmpty() && var2.test(var10)) {
                  int var11 = Math.min(var4, var10.getCount());
                  int var12 = var10.getCount() - var11;
                  var4 -= var11;
                  if (var12 > 0) {
                     var10.decrement(var12);
                     var5 += var12;
                     var8.markDirty();
                  }
               }
            }
         }
      }

      return var5;
   }

   public static boolean x2cl1crde2rnxy(ServerWorld var0, x15kgoaq6i3m6i var1, ItemStack var2) {
      if (var2.isEmpty()) {
         return true;
      } else {
         x2jj5edkiu5orf var3 = x2ic8ypdr45hiz(var2);
         ArrayList<BlockPos> var4 = new ArrayList<>(var1.x2ad3s8aentmo4());
         var4.sort(Comparator.comparingInt(var2x -> x3nsk4svfallqp(var1.x2h8eqlzb5mm0w(var2x), var3)));

         for (BlockPos var6 : var4) {
            if (x22gnz9u3m8aag(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var8) {
               for (int var9 = 0; var9 < var8.size(); var9++) {
                  ItemStack var10 = var8.getStack(var9);
                  int var11 = var8.getMaxCount(var2);
                  if (var10.isEmpty()) {
                     int var12 = Math.min(var2.getCount(), var11);
                     var8.setStack(var9, var2.copyWithCount(var12));
                     var2.decrement(var12);
                     var8.markDirty();
                     if (var2.isEmpty()) {
                        return true;
                     }
                  } else if (ItemStack.areItemsAndComponentsEqual(var10, var2) && var10.getCount() < var11) {
                     int var13 = Math.min(var2.getCount(), var11 - var10.getCount());
                     var10.increment(var13);
                     var2.decrement(var13);
                     var8.markDirty();
                     if (var2.isEmpty()) {
                        return true;
                     }
                  }
               }
            }
         }

         return var2.isEmpty();
      }
   }

   public static int x1amge7aoh04ca(ServerWorld var0, x15kgoaq6i3m6i var1, ItemStack var2) {
      if (var2.isEmpty()) {
         return 0;
      } else {
         long var3 = 0L;

         for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var7) {
               for (int var10 = 0; var10 < var7.size(); var10++) {
                  ItemStack var9 = var7.getStack(var10);
                  if (var9.isEmpty()) {
                     var3 += var7.getMaxCount(var2);
                  } else if (ItemStack.areItemsAndComponentsEqual(var9, var2)) {
                     var3 += Math.max(0, var7.getMaxCount(var2) - var9.getCount());
                  }
               }
            }
         }

         return (int)Math.min(2147483647L, var3);
      }
   }

   public static boolean x38ipozok9mc0x(ServerWorld var0, x15kgoaq6i3m6i var1, BooleanSupplier var2, List<ItemStack> var3) {
      Map<Inventory, List<ItemStack>> var4 = new IdentityHashMap<>();

      for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var7) {
            var4.computeIfAbsent(var7, var0x -> IntStream.range(0, var0x.size()).mapToObj(var1x -> var0x.getStack(var1x).copy()).toList());
         }
      }

      boolean var12 = false;

      boolean var17;
      try {
         if (!var2.getAsBoolean()) {
            return false;
         }

         Iterator var13 = var3.iterator();

         ItemStack var16;
         do {
            if (!var13.hasNext()) {
               var12 = true;
               return true;
            }

            var16 = (ItemStack)var13.next();
         } while (x2cl1crde2rnxy(var0, var1, var16.copy()));

         var17 = false;
      } finally {
         if (!var12) {
            var4.forEach((var0x, var1x) -> {
               for (int var2x = 0; var2x < var1x.size(); var2x++) {
                  var0x.setStack(var2x, (ItemStack)var1x.get(var2x));
               }

               var0x.markDirty();
            });
         }

         x2u0vsam1tcmhq(var0, var1);
      }

      return var17;
   }

   private static int x3nsk4svfallqp(x2jj5edkiu5orf var0, x2jj5edkiu5orf var1) {
      if (var0 == var1) {
         return 0;
      } else {
         return var0 == x2jj5edkiu5orf.x2y2vmpsepzv3 ? 1 : 2;
      }
   }

   public static x2jj5edkiu5orf x2ic8ypdr45hiz(ItemStack var0) {
      if (var0.isEmpty()) {
         return x2jj5edkiu5orf.x2y2vmpsepzv3;
      } else {
         String var1 = Registries.ITEM.getId(var0.getItem()).getPath();
         if (var0.isOf(Items.EGG)
            || var0.isOf(Items.MILK_BUCKET)
            || var0.isOf(Items.LEATHER)
            || var0.isOf(Items.FEATHER)
            || var0.isOf(Items.RABBIT_HIDE)
            || var1.endsWith("_wool")) {
            return x2jj5edkiu5orf.x2uujme1um3d1w;
         } else if (xhahxgpa08yc9(var0) > 0 || var0.isOf(Items.WHEAT) || var0.isOf(Items.HAY_BLOCK) || var1.endsWith("_seeds")) {
            return x2jj5edkiu5orf.xnpajviatkbuo;
         } else if (var0.isIn(ItemTags.LOGS)
            || var0.isIn(ItemTags.PLANKS)
            || var0.isIn(ItemTags.SAPLINGS)
            || var0.isOf(Items.STICK)
            || var0.isOf(Items.CHARCOAL)) {
            return x2jj5edkiu5orf.x24dt8qhwemmti;
         } else {
            return !var0.isIn(ItemTags.STONE_CRAFTING_MATERIALS)
                  && !var1.contains("_ore")
                  && !var1.startsWith("raw_")
                  && !var1.endsWith("_ingot")
                  && !var1.endsWith("_nugget")
                  && !var0.isOf(Items.COAL)
                  && !var0.isOf(Items.DIAMOND)
                  && !var0.isOf(Items.EMERALD)
                  && !var0.isOf(Items.REDSTONE)
                  && !var0.isOf(Items.LAPIS_LAZULI)
                  && !var0.isOf(Items.FLINT)
               ? x2jj5edkiu5orf.x2y2vmpsepzv3
               : x2jj5edkiu5orf.x3leq9hn5gf43c;
         }
      }
   }

   private static void x3cjs4pzi3umdr(ServerWorld var0, x15kgoaq6i3m6i var1) {
      if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         List var2 = var1.x1ja61emeln2hc()
            .stream()
            .filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.xqfd7s4y4gux6 && var0x.x2nfrt8qxdscso().isEmpty())
            .toList();

         for (int var3 = 0; var3 < var2.size(); var3++) {
            x2arpn38dclo1g var4 = (x2arpn38dclo1g)var2.get(var3);
            if (!x2fs1y9t8j4hqu(var1, var4)) {
               Optional var5 = x6a0sltqj8ii7(var0, var4);
               if (!var5.isEmpty()) {
                  if (!x22hjebj4vmztt(var0, var1, var0x -> var0x.isIn(ItemTags.LOGS), 4)) {
                     return;
                  }

                  x2jj5edkiu5orf var6 = x2jj5edkiu5orf.forHouse(var3);
                  xt55z8grtuvpq(var0, var1, (BlockPos)var5.get(), var6);
                  xchl1k1tnh3w8(var0).markDirty();
                  return;
               }
            }
         }
      }
   }

   private static boolean x2fs1y9t8j4hqu(x15kgoaq6i3m6i var0, x2arpn38dclo1g var1) {
      BlockBox var2 = var1.x24kbqv6g4kkrg();
      return var0.x29j0o9zdxri2()
         .keySet()
         .stream()
         .anyMatch(
            var1x -> var1x.getX() >= var2.getMinX() - 2
               && var1x.getX() <= var2.getMaxX() + 2
               && var1x.getY() >= var2.getMinY() - 1
               && var1x.getY() <= var2.getMaxY() + 2
               && var1x.getZ() >= var2.getMinZ() - 2
               && var1x.getZ() <= var2.getMaxZ() + 2
         );
   }

   private static Optional<BlockPos> x6a0sltqj8ii7(ServerWorld var0, x2arpn38dclo1g var1) {
      BlockBox var2 = var1.x24kbqv6g4kkrg();

      for (int var3 = var2.getMinY() + 1; var3 <= var2.getMaxY() - 1; var3++) {
         for (int var4 = var2.getMinX(); var4 < var2.getMaxX(); var4++) {
            for (int var5 = var2.getMinZ(); var5 <= var2.getMaxZ(); var5++) {
               BlockPos var6 = new BlockPos(var4, var3, var5);
               BlockPos var7 = var6.east();
               if (x1svuyoyvp8ad5(var0, var6, var7) && (x1rn82cvt1s306(var0, var6) || x1rn82cvt1s306(var0, var7))) {
                  return Optional.of(var6);
               }
            }
         }
      }

      for (int var11 = 1; var11 <= 2; var11++) {
         for (int var12 = var2.getMinX() - var11; var12 < var2.getMaxX() + var11; var12++) {
            for (int var8 : new int[]{var2.getMinZ() - var11, var2.getMaxZ() + var11}) {
               int var9 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var12, var8);
               BlockPos var10 = new BlockPos(var12, var9, var8);
               if (x1svuyoyvp8ad5(var0, var10, var10.east())) {
                  return Optional.of(var10);
               }
            }
         }
      }

      return Optional.empty();
   }

   public static Optional<BlockPos> x2l4eb2ibsf118(ServerWorld var0, x15kgoaq6i3m6i var1) {
      for (int var2 = 3; var2 <= 20; var2++) {
         for (int var3 = -var2; var3 <= var2; var3++) {
            for (int var4 = -var2; var4 <= var2; var4++) {
               if (Math.abs(var3) == var2 || Math.abs(var4) == var2) {
                  int var5 = var1.core().getX() + var3;
                  int var6 = var1.core().getZ() + var4;
                  if (var0.getChunkManager().isChunkLoaded(var5 >> 4, var6 >> 4) && var0.getChunkManager().isChunkLoaded(var5 + 1 >> 4, var6 >> 4)) {
                     int var7 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var5, var6);
                     if (Math.abs(var7 - var1.core().getY()) <= 3) {
                        BlockPos var8 = new BlockPos(var5, var7, var6);
                        BlockPos var9 = var8.east();
                        if (x1svuyoyvp8ad5(var0, var8, var9)
                           && !var1.x1ja61emeln2hc().stream().anyMatch(var2x -> var2x.x24kbqv6g4kkrg().contains(var8) || var2x.x24kbqv6g4kkrg().contains(var9))
                           && !var0.getBlockState(var8.down()).isOf(Blocks.CHEST)
                           && !var0.getBlockState(var9.down()).isOf(Blocks.CHEST)) {
                           return Optional.of(var8);
                        }
                     }
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static boolean x1svuyoyvp8ad5(ServerWorld var0, BlockPos var1, BlockPos var2) {
      if (x1t48c42x6zfb3(var0, var1) || x1t48c42x6zfb3(var0, var2) || x1t48c42x6zfb3(var0, var1.up()) || x1t48c42x6zfb3(var0, var2.up())) {
         return false;
      } else if (x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var1) || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var2)) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var1) || !x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var2)) {
         return false;
      } else if (var0.getBlockState(var1).isAir()
         && var0.getBlockState(var2).isAir()
         && var0.getBlockState(var1.up()).isAir()
         && var0.getBlockState(var2.up()).isAir()) {
         if (!var0.getBlockState(var1.down()).isSideSolidFullSquare(var0, var1.down(), Direction.UP)
            || !var0.getBlockState(var2.down()).isSideSolidFullSquare(var0, var2.down(), Direction.UP)) {
            return false;
         } else if (var0.getBlockState(var1.down()).isOf(Blocks.DIRT_PATH)
            || var0.getBlockState(var2.down()).isOf(Blocks.DIRT_PATH)
            || var0.getBlockState(var1.down()).isOf(Blocks.FARMLAND)
            || var0.getBlockState(var2.down()).isOf(Blocks.FARMLAND)) {
            return false;
         } else if (!var0.getOtherEntities(null, new Box(var1).union(new Box(var2)).expand(0.02), var0x -> var0x.isAlive()).isEmpty()) {
            return false;
         } else {
            for (BlockPos var4 : BlockPos.iterate(var1.add(-2, -1, -2), var2.add(2, 2, 2))) {
               BlockState var5 = var0.getBlockState(var4);
               if (var5.isIn(BlockTags.DOORS) || var5.isIn(BlockTags.BEDS) || var5.isOf(Blocks.BELL)) {
                  return false;
               }
            }

            return x3dmceb8h6ov6d(var0, var1, var2, Direction.NORTH) || x3dmceb8h6ov6d(var0, var1, var2, Direction.SOUTH);
         }
      } else {
         return false;
      }
   }

   private static boolean x1rn82cvt1s306(ServerWorld var0, BlockPos var1) {
      for (Direction var3 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
         BlockPos var4 = var1.offset(var3);
         if (!var0.getBlockState(var4).getCollisionShape(var0, var4).isEmpty()) {
            return true;
         }
      }

      return false;
   }

   public static boolean xt55z8grtuvpq(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2, x2jj5edkiu5orf var3) {
      BlockPos var4 = var2.east();
      if (x1t48c42x6zfb3(var0, var2) || x1t48c42x6zfb3(var0, var4) || x1t48c42x6zfb3(var0, var2.up()) || x1t48c42x6zfb3(var0, var4.up())) {
         return false;
      } else if (x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var2) || x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var4)) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var2) || !x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var4)) {
         return false;
      } else if (!var0.getBlockState(var2).isAir() || !var0.getBlockState(var4).isAir()) {
         return false;
      } else if (!x3dmceb8h6ov6d(var0, var2, var4, Direction.NORTH) && !x3dmceb8h6ov6d(var0, var2, var4, Direction.SOUTH)) {
         return false;
      } else {
         xg5u0itczfrpg(var0, var2, var4, xdzsxthi9esqy(var0, var1, var2, var4));
         var1.x1v7s85y1rhqmd(var2, var3);
         var1.x1v7s85y1rhqmd(var4, var3);
         var1.x35zk9b4abyi6a(var2, var4);
         return true;
      }
   }

   private static boolean x1t48c42x6zfb3(ServerWorld var0, BlockPos var1) {
      return xchl1k1tnh3w8(var0).colonies().stream().anyMatch(var1x -> var1x.x2xqc3q79vfkw3().containsKey(var1));
   }

   public static void x16cwhuov65ela(ServerWorld var0, x15kgoaq6i3m6i var1) {
      if (x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         for (Entry var3 : var1.x2ivzhhwd37mdn().entrySet()) {
            BlockPos var4 = (BlockPos)var3.getKey();
            BlockPos var5 = (BlockPos)var3.getValue();
            if (x22gnz9u3m8aag(var0, var4)
               && x22gnz9u3m8aag(var0, var5)
               && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var4)
               && !x3d5vk1zj3fvkj.get(var0).isPlayerBlock(var5)) {
               BlockState var6 = var0.getBlockState(var4);
               BlockState var7 = var0.getBlockState(var5);
               if (var6.isOf(Blocks.CHEST) && var7.isOf(Blocks.CHEST)) {
                  Direction var8 = (Direction)var6.get(ChestBlock.FACING);
                  if (var8 != Direction.NORTH && var8 != Direction.SOUTH || !x3dmceb8h6ov6d(var0, var4, var5, var8)) {
                     var8 = xdzsxthi9esqy(var0, var1, var4, var5);
                  }

                  ChestType var9 = var8 == Direction.NORTH ? ChestType.LEFT : ChestType.RIGHT;
                  ChestType var10 = var8 == Direction.NORTH ? ChestType.RIGHT : ChestType.LEFT;
                  if (var6.get(ChestBlock.FACING) != var8
                     || var6.get(ChestBlock.CHEST_TYPE) != var9
                     || var7.get(ChestBlock.FACING) != var8
                     || var7.get(ChestBlock.CHEST_TYPE) != var10) {
                     xg5u0itczfrpg(var0, var4, var5, var8);
                  }
               }
            }
         }
      }
   }

   private static boolean x3dmceb8h6ov6d(ServerWorld var0, BlockPos var1, BlockPos var2, Direction var3) {
      for (BlockPos var5 : List.of(var1.offset(var3), var2.offset(var3))) {
         if (!x22gnz9u3m8aag(var0, var5)
            || !var0.getBlockState(var5).getCollisionShape(var0, var5).isEmpty()
            || !var0.getBlockState(var5.up()).getCollisionShape(var0, var5.up()).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   private static Direction xdzsxthi9esqy(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2, BlockPos var3) {
      boolean var4 = x3dmceb8h6ov6d(var0, var2, var3, Direction.NORTH);
      boolean var5 = x3dmceb8h6ov6d(var0, var2, var3, Direction.SOUTH);
      if (var4 != var5) {
         return var4 ? Direction.NORTH : Direction.SOUTH;
      } else {
         return var1.core().getZ() > var2.getZ() ? Direction.SOUTH : Direction.NORTH;
      }
   }

   private static void xg5u0itczfrpg(ServerWorld var0, BlockPos var1, BlockPos var2, Direction var3) {
      BlockState var4 = var0.getBlockState(var1);
      BlockState var5 = var0.getBlockState(var2);
      BlockState var6 = (BlockState)((BlockState)(var4.isOf(Blocks.CHEST) ? var4 : Blocks.CHEST.getDefaultState()).with(ChestBlock.FACING, var3))
         .with(ChestBlock.CHEST_TYPE, var3 == Direction.NORTH ? ChestType.LEFT : ChestType.RIGHT);
      BlockState var7 = (BlockState)((BlockState)(var5.isOf(Blocks.CHEST) ? var5 : Blocks.CHEST.getDefaultState()).with(ChestBlock.FACING, var3))
         .with(ChestBlock.CHEST_TYPE, var3 == Direction.NORTH ? ChestType.RIGHT : ChestType.LEFT);
      byte var8 = 18;
      var0.setBlockState(var1, var6, var8);
      var0.setBlockState(var2, var7, var8);
   }

   public static boolean x57l6vd1sx1rt(ServerWorld var0, x15kgoaq6i3m6i var1, Predicate<ItemStack> var2) {
      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var6) {
            for (int var7 = 0; var7 < var6.size(); var7++) {
               ItemStack var8 = var6.getStack(var7);
               if (var8.isEmpty() || var2.test(var8) && var8.getCount() < var8.getMaxCount()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static int x11dwzrdveisv7(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;

      for (BlockPos var4 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var4) && var0.getBlockEntity(var4) instanceof Inventory var5) {
            for (int var7 = 0; var7 < var5.size(); var7++) {
               if (var5.getStack(var7).isEmpty()) {
                  var2++;
               }
            }
         }
      }

      return var2;
   }

   public static boolean xis8cbwd2sinx(ServerWorld var0, VillagerEntity var1) {
      Optional var2 = x3psqezd5jem54(var0, var1.getBlockPos());
      return var2.isEmpty() ? true : x9jjpc1qt9hfg(var0, (x15kgoaq6i3m6i)var2.get()).isEmpty();
   }

   public static List<String> x9jjpc1qt9hfg(ServerWorld var0, x15kgoaq6i3m6i var1) {
      ArrayList<String> var2 = new ArrayList<>();
      int var3 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_population_per_bell;
      if (var1.population() >= var3) {
         var2.add("лимит населения " + var3);
      }

      if (var1.beds() <= var1.population()) {
         var2.add("нет лишней кровати");
      }

      int var4 = Math.max(24, var1.population() * 6);
      int var5 = x1397xrm4pk893(var0, var1);
      int var6 = var1.cachedFood() + var5;
      if (var6 < var4) {
         var2.add("еда всего " + var6 + "/" + var4);
      }

      if (var1.x3klfyffbbfab2() != Long.MIN_VALUE && var0.getTime() - var1.x3klfyffbbfab2() < 1200L) {
         var2.add("после угрозы осталось " + (1200L - (var0.getTime() - var1.x3klfyffbbfab2()) + 19L) / 20L + " с");
      }

      return var2;
   }

   public static int xhahxgpa08yc9(ItemStack var0) {
      FoodComponent var1 = (FoodComponent)var0.get(DataComponentTypes.FOOD);
      return var1 == null ? 0 : Math.max(1, var1.nutrition());
   }

   public static int x1397xrm4pk893(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;

      for (VillagerEntity var4 : var0.getEntitiesByType(EntityType.VILLAGER, x150ccxomrlozr(var0, var1), var0x -> var0x.isAlive())) {
         if (x3psqezd5jem54(var0, var4.getBlockPos()).map(var1x -> var1x.id().equals(var1.id())).orElse(false)) {
            var2 += x24dik3nra38j8(var4);
         }
      }

      return var2;
   }

   private static int x24dik3nra38j8(VillagerEntity var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         ItemStack var3 = var0.getInventory().getStack(var2);
         var1 += xhahxgpa08yc9(var3) * var3.getCount();
      }

      return var1;
   }

   private static boolean x3adevuvxjhqbp(VillagerEntity var0) {
      for (int var1 = 0; var1 < var0.getInventory().size(); var1++) {
         if (xhahxgpa08yc9(var0.getInventory().getStack(var1)) > 0) {
            return true;
         }
      }

      return false;
   }

   private static void x2qawesj33vqrj(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2) {
      long var3 = Math.floorMod(var0.getTimeOfDay(), 24000L);
      if (var3 >= 9000L && var3 < 12000L) {
         for (VillagerEntity var6 : var2) {
            int var7 = x24dik3nra38j8(var6);
            int var8 = !var6.isBaby() && var6.getBreedingAge() == 0 ? 12 : (x3adevuvxjhqbp(var6) ? 0 : 1);

            for (int var9 = 0; var9 < 12 && var7 < var8; var9++) {
               Optional var10 = x3lxwdirnn77qc(var0, var1, var0x -> xhahxgpa08yc9(var0x) > 0);
               if (var10.isEmpty()) {
                  break;
               }

               var7 += xhahxgpa08yc9((ItemStack)var10.get());
               ItemStack var11 = var6.getInventory().addStack((ItemStack)var10.get());
               if (!var11.isEmpty()) {
                  x2cl1crde2rnxy(var0, var1, var11);
               }
            }

            if (!x3adevuvxjhqbp(var6)) {
               x3lxwdirnn77qc(var0, var1, var0x -> xhahxgpa08yc9(var0x) > 0).ifPresent(var3x -> {
                  ItemStack var4 = var6.getInventory().addStack(var3x);
                  if (!var4.isEmpty()) {
                     x2cl1crde2rnxy(var0, var1, var4);
                  }
               });
            }
         }
      }
   }

   private static int xllp0ajwexj5r(ServerWorld var0, x15kgoaq6i3m6i var1, int var2) {
      BlockPos var3 = var1.core();
      Mutable var4 = new Mutable();

      int var5;
      for (var5 = 0; var1.x3kz9uqjj0g2fr() < 35937 && var5 < var2; var5++) {
         int var6 = var1.x3kz9uqjj0g2fr();
         int var7 = var6 % 33 - 16;
         int var8 = var6 / 33;
         int var9 = var8 % 33 - 16;
         int var10 = var8 / 33 - 16;
         var4.set(var3.getX() + var7, var3.getY() + var10, var3.getZ() + var9);
         if (x22gnz9u3m8aag(var0, var4)) {
            BlockEntity var11 = var0.getBlockEntity(var4);
            if (var11 instanceof ChestBlockEntity || var11 instanceof BarrelBlockEntity) {
               var1.x36r3qqcopafen().add(var4.toImmutable());
            }
         }

         var1.x11k3dhx0f8bj3();
      }

      if (var1.x3kz9uqjj0g2fr() >= 35937) {
         var1.xtffnc1wwh86p(var0.getTime());
         x2u0vsam1tcmhq(var0, var1);
      }

      return var5;
   }

   static void x2u0vsam1tcmhq(ServerWorld var0, x15kgoaq6i3m6i var1) {
      int var2 = 0;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (BlockPos var9 : var1.x2ad3s8aentmo4()) {
         if (x22gnz9u3m8aag(var0, var9) && var0.getBlockEntity(var9) instanceof Inventory var11) {
            for (int var12 = 0; var12 < var11.size(); var12++) {
               ItemStack var13 = var11.getStack(var12);
               if (!var13.isEmpty()) {
                  if (var13.get(DataComponentTypes.FOOD) != null) {
                     var2 += xhahxgpa08yc9(var13) * var13.getCount();
                  }

                  if (var13.isIn(ItemTags.LOGS)) {
                     var3 += var13.getCount();
                  }

                  if (var13.isIn(ItemTags.STONE_CRAFTING_MATERIALS)) {
                     var4 += var13.getCount();
                  }

                  if (var13.isIn(ItemTags.SAPLINGS)) {
                     var5 += var13.getCount();
                  }

                  if (var13.isOf(Items.IRON_INGOT) || var13.isOf(Items.RAW_IRON)) {
                     var6 += var13.getCount();
                  }

                  if (var13.isOf(Items.DIAMOND)) {
                     var7 += var13.getCount();
                  }
               }
            }
         }
      }

      var1.x2rezm3a3rz7iu(var2, var3, var4, var5, var6, var7);
   }

   private static void x2o9v4z8h5h1n1(ServerWorld var0, x15kgoaq6i3m6i var1, long var2) {
      Box var4 = x150ccxomrlozr(var0, var1);
      ArrayList<VillagerEntity> var5 = new ArrayList<>(
         var0.getEntitiesByType(EntityType.VILLAGER, var4, var0x -> var0x.isAlive())
            .stream()
            .filter(var2x -> x3psqezd5jem54(var0, var2x.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false))
            .toList()
      );
      int var6 = (int)var5.stream().filter(var0x -> !var0x.isBaby()).count();
      if (!var1.xxqujir3s0ild()) {
         if (var6 >= 8) {
            var1.xvuz8u6ue7m7c();
            xchl1k1tnh3w8(var0).markDirty();
         } else if (var1.x3e7xus757aqpt() >= 2) {
            x1yev2n44jz8yr(var0, var1, var5, 8 - var6);
            var6 = (int)var5.stream().filter(var0x -> !var0x.isBaby()).count();
            if (var6 >= 8) {
               var1.xvuz8u6ue7m7c();
            }

            xchl1k1tnh3w8(var0).markDirty();
         }
      }

      int var7 = (int)var0.getPointOfInterestStorage()
         .count(var0x -> var0x.matchesKey(PointOfInterestTypes.HOME), var1.core(), var1.radius(), OccupationStatus.ANY);
      var1.x1c0m5jj5x6wpm(var5.size(), var7, var2);
      x2qawesj33vqrj(var0, var1, var5);
      x1wvfvkvaehe0z(var0, var1, var5);
      x2u0vsam1tcmhq(var0, var1);
      xmgumxbnfkgss.x1cgirut0dfx4w(var0, var1);
      x1o5o1i05gjwxl(var0, var1, var5);
      var1.xhwx3flu2btqb(var5);
      x36n0pgu9qp6rk.xkfga2dpst7kt(var0, var1, var5, var2);
      xt78n188f3w97.x2pesumrvfap2d(var0, var1, var5);
      Set var8 = var5.stream()
         .filter(var1x -> !var1x.isBaby() && var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x35csp4lojh59r)
         .map(Entity::getUuid)
         .collect(Collectors.toSet());
      boolean var9 = false;
      long var10 = Math.floorMod(var0.getTimeOfDay(), 24000L);
      boolean var12 = var10 >= 1000L && var10 < 9000L;

      for (x2arpn38dclo1g var14 : var1.x1ja61emeln2hc()) {
         boolean var15 = var14.x3vu8mnvg0blk4()
            .flatMap(var1x -> var5.stream().filter(var1xx -> var1xx.getUuid().equals(var1x)).findFirst())
            .map(var0x -> var0x.getBrain().getRunningTasks().stream().anyMatch(var0xx -> var0xx instanceof x2cvbyh5y5day0))
            .orElse(false);
         if (var14.x32oz43hmeynhc() == x20r5v7z43p3d5.x3vi306qg1ms75 && var14.x2zq2smhbu3lzo() > 0L && var2 >= var14.x2zq2smhbu3lzo()) {
            var14.x1ogar57a8j8ls();
            var9 = true;
         }

         boolean var16 = var12
            && var14.x32oz43hmeynhc() == x20r5v7z43p3d5.x1mzkgtgadv3jh
            && var14.x16o43azbz1opa() != Long.MIN_VALUE
            && var2 - var14.x16o43azbz1opa() > (var15 ? 1800 : 200);
         if (var14.x3vu8mnvg0blk4().isPresent() && (!var8.contains(var14.x3vu8mnvg0blk4().get()) || var16)) {
            var14.x23bey8uos6gyt();
            var9 = true;
         }
      }

      if (var9) {
         xchl1k1tnh3w8(var0).markDirty();
      }

      x3gadv4asdqmks(var0, var1, var5, var2);
   }

   private static void x1yev2n44jz8yr(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2, int var3) {
      for (int var4 = 0; var4 < var3; var4++) {
         Optional var5 = xcmgrg6f6maj1(var0, var1, var4);
         if (var5.isEmpty()) {
            break;
         }

         VillagerEntity var6 = (VillagerEntity)EntityType.VILLAGER.create(var0, SpawnReason.MOB_SUMMONED);
         if (var6 == null) {
            break;
         }

         BlockPos var7 = (BlockPos)var5.get();
         var6.setPosition(var7.getX() + 0.5, var7.getY(), var7.getZ() + 0.5);
         var6.setPersistent();
         if (var0.spawnEntity(var6)) {
            var2.add(var6);
         }
      }
   }

   static Optional<BlockPos> xcmgrg6f6maj1(ServerWorld var0, x15kgoaq6i3m6i var1, int var2) {
      for (int var3 = 2; var3 <= 12; var3++) {
         for (int var4 = 0; var4 < var3 * 8; var4++) {
            int var5 = Math.floorMod(var4 + var2 * 7, var3 * 8);
            int var6;
            int var7;
            if (var5 < var3 * 2) {
               var6 = -var3 + var5;
               var7 = -var3;
            } else if (var5 < var3 * 4) {
               var6 = var3;
               var7 = -var3 + (var5 - var3 * 2);
            } else if (var5 < var3 * 6) {
               var6 = var3 - (var5 - var3 * 4);
               var7 = var3;
            } else {
               var6 = -var3;
               var7 = var3 - (var5 - var3 * 6);
            }

            int var8 = var1.core().getX() + var6;
            int var9 = var1.core().getZ() + var7;
            if (var0.getChunkManager().isChunkLoaded(var8 >> 4, var9 >> 4)) {
               BlockPos var10 = new BlockPos(var8, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var8, var9), var9);
               if (var0.getFluidState(var10).isEmpty()
                  && var0.getFluidState(var10.down()).isEmpty()
                  && var0.getBlockState(var10).getCollisionShape(var0, var10).isEmpty()
                  && var0.getBlockState(var10.up()).getCollisionShape(var0, var10.up()).isEmpty()
                  && var0.getBlockState(var10.down()).isSideSolidFullSquare(var0, var10.down(), Direction.UP)) {
                  Box var11 = new Box(var10).expand(0.2);
                  if (!x2uh56vnzc4u51.x3gqyq7yie9jxr(var0, var10)
                     && var0.getWorldBorder().contains(var10)
                     && var0.getOtherEntities(null, var11, var0x -> var0x.isAlive()).isEmpty()) {
                     return Optional.of(var10);
                  }
               }
            }
         }
      }

      return Optional.empty();
   }

   private static void x3gadv4asdqmks(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2, long var3) {
      if (var3 >= var1.x180oul2yysi55() && x9jjpc1qt9hfg(var0, var1).isEmpty() && var1.cachedFood() >= 24) {
         List<VillagerEntity> var5 = var2.stream()
            .filter(var0x -> !var0x.isBaby() && var0x.getBreedingAge() == 0)
            .sorted(Comparator.comparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core())))
            .limit(2L)
            .toList();
         if (var5.size() >= 2) {
            Optional var6 = xcmgrg6f6maj1(var0, var1, (int)(var3 / 100L));
            if (!var6.isEmpty()) {
               VillagerEntity var7 = (VillagerEntity)EntityType.VILLAGER.create(var0, SpawnReason.BREEDING);
               if (var7 != null) {
                  BlockPos var8 = (BlockPos)var6.get();
                  var7.setPosition(var8.getX() + 0.5, var8.getY(), var8.getZ() + 0.5);
                  var7.setBreedingAge(-1800);
                  var7.setPersistent();
                  if (x38ipozok9mc0x(var0, var1, () -> x2o11j2ko0ev05(var0, var1, 24) && var0.spawnEntity(var7), List.of())) {
                     var5.forEach(var0x -> var0x.setBreedingAge(1200));
                     var1.x336kh3r7239fs(var3 + 300L);
                     var0.spawnParticles(ParticleTypes.HEART, var8.getX() + 0.5, var8.getY() + 1, var8.getZ() + 0.5, 8, 0.8, 0.5, 0.8, 0.0);
                     xchl1k1tnh3w8(var0).markDirty();
                  }
               }
            }
         }
      }
   }

   public static boolean x2o11j2ko0ev05(ServerWorld var0, x15kgoaq6i3m6i var1, int var2) {
      if (var2 <= 0) {
         return true;
      } else {
         long var3 = 0L;

         for (BlockPos var6 : var1.x2ad3s8aentmo4()) {
            if (x22gnz9u3m8aag(var0, var6) && var0.getBlockEntity(var6) instanceof Inventory var7) {
               for (int var17 = 0; var17 < var7.size(); var17++) {
                  ItemStack var9 = var7.getStack(var17);
                  var3 += (long)xhahxgpa08yc9(var9) * var9.getCount();
               }
            }
         }

         if (var3 < var2) {
            return false;
         } else {
            int var14 = var2;

            for (BlockPos var16 : var1.x2ad3s8aentmo4()) {
               if (x22gnz9u3m8aag(var0, var16) && var0.getBlockEntity(var16) instanceof Inventory var18) {
                  for (int var20 = 0; var20 < var18.size() && var14 > 0; var20++) {
                     ItemStack var10 = var18.getStack(var20);
                     int var11 = xhahxgpa08yc9(var10);
                     if (var11 > 0) {
                        int var12 = Math.min(var10.getCount(), (var14 + var11 - 1) / var11);
                        Item var13 = var10.getItem();
                        var10.decrement(var12);
                        var1.x2sh8yupnxmo71(var13, var12);
                        var14 -= var12 * var11;
                        var18.markDirty();
                     }
                  }

                  if (var14 <= 0) {
                     return true;
                  }
               }
            }

            return false;
         }
      }
   }

   private static void x1o5o1i05gjwxl(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2) {
      x2ycvfpsvizlf9(var0, var1, var2, false);
   }

   public static void xsd7almzt4rct(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2) {
      x2u0vsam1tcmhq(var0, var1);
      xmgumxbnfkgss.x1cgirut0dfx4w(var0, var1);
      x2ycvfpsvizlf9(var0, var1, var2, true);
      var1.xhwx3flu2btqb(var2);
   }

   private static void x2ycvfpsvizlf9(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2, boolean var3) {
      List<VillagerEntity> var4 = var2.stream().filter(var0x -> !var0x.isBaby()).sorted(Comparator.comparing(Entity::getUuid)).toList();
      List<VillagerEntity> var5 = var4.stream().filter(var1x -> {
         x1rxiy9mtu5b3h var2x = var1.roleOf(var1x.getUuid());
         return var2x != x1rxiy9mtu5b3h.x1saccptp3i00w && var2x != x1rxiy9mtu5b3h.x2xja04fpuesoh;
      }).toList();
      EnumMap<x1rxiy9mtu5b3h, Integer> var6 = x17qz5r6rg31u8(var1, var0.getTime(), var5.size());
      EnumMap<x1rxiy9mtu5b3h, Integer> var7 = new EnumMap<>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var11 : x1rxiy9mtu5b3h.values()) {
         var7.put(var11, 0);
      }

      for (VillagerEntity var23 : var4) {
         var7.compute(var1.roleOf(var23.getUuid()), (var0x, var1x) -> (Integer)var1x + 1);
      }

      boolean var22 = false;
      int var24 = (Integer)var7.get(x1rxiy9mtu5b3h.x2j116s1wug0g2);
      boolean var25 = var24 > 0;
      boolean var26 = var3
         || var1.x2c2srygrjrm6g() != Long.MIN_VALUE
            && var0.getTime() - var1.x2c2srygrjrm6g() >= (var1.cachedFood() >= Math.max(48, var1.population() * 8) && var1.x182p7mjnr75sg() != 0 ? 2400 : 1200);
      int var12 = var25 ? var24 : (var26 ? 1 : 0);
      ArrayList<x1rxiy9mtu5b3h> var13 = new ArrayList<>(
         List.of(
            x1rxiy9mtu5b3h.x35csp4lojh59r,
            x1rxiy9mtu5b3h.x8jydaiei7iry,
            x1rxiy9mtu5b3h.x2hwljbtybtlwc,
            x1rxiy9mtu5b3h.x18g4mqx9kxs89,
            x1rxiy9mtu5b3h.xhds9crfjdp9i,
            x1rxiy9mtu5b3h.xeoehg87qwfrn,
            x1rxiy9mtu5b3h.x3r8p2tvihwpph
         )
      );
      EnumMap<x1rxiy9mtu5b3h, Integer> var14 = x8x0922dn63y6(var1, var0.getTime());
      var13.sort(
         Comparator.<x1rxiy9mtu5b3h>comparingInt(var2x -> var6.get(var2x) - var7.get(var2x))
            .reversed()
            .thenComparing(Comparator.<x1rxiy9mtu5b3h>comparingInt(var14::get).reversed())
      );

      label97:
      for (x1rxiy9mtu5b3h var16 : var13) {
         while (var7.get(var16).intValue() < var6.get(var16).intValue() && var12 > 0) {
            Optional var17 = var5.stream()
               .filter(var2x -> var1.roleOf(var2x.getUuid()) != var16)
               .filter(var3x -> var25 || xmgumxbnfkgss.x2hrr4jjriuh85(var0, var1, var3x, var0.getTime()))
               .filter(var4x -> {
                  x1rxiy9mtu5b3h var5x = var1.roleOf(var4x.getUuid());
                  return var25 ? var5x == x1rxiy9mtu5b3h.x2j116s1wug0g2 : (Integer)var7.get(var5x) > (Integer)var6.get(var5x);
               })
               .min(Comparator.comparingInt(var1x -> var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x2j116s1wug0g2 ? 0 : 1));
            if (var17.isEmpty()) {
               break;
            }

            x1rxiy9mtu5b3h var18 = var1.roleOf(((VillagerEntity)var17.get()).getUuid());

            for (Task var20 : List.copyOf(((VillagerEntity)var17.get()).getBrain().getRunningTasks())) {
               if (var20.getClass().getName().startsWith("net.smartcolonies.")) {
                  var20.stop(var0, (LivingEntity)var17.get(), var0.getTime());
               }
            }

            x1c1r5c6a13gtl.x2fxoqmbs0henb((VillagerEntity)var17.get());
            var22 |= var1.x36xpmo21hmp9h(((VillagerEntity)var17.get()).getUuid(), var16);
            xmgumxbnfkgss.xw6l4sc9khw02(var1, (VillagerEntity)var17.get(), var0.getTime());
            var7.put(var18, (Integer)var7.get(var18) - 1);
            var7.put(var16, (Integer)var7.get(var16) + 1);
            if (--var12 <= 0) {
               break label97;
            }
         }
      }

      if (var22 || var1.x2c2srygrjrm6g() == Long.MIN_VALUE) {
         var1.xjq48e1i3w4gj(var0.getTime());
         var22 = true;
      }

      for (VillagerEntity var29 : var4) {
         x21g1rujs9r1h6(var0, var29, var1.roleOf(var29.getUuid()));
      }

      x3d5vk1zj3fvkj var28 = xchl1k1tnh3w8(var0);
      if (var22) {
         var28.markDirty();
      }

      for (VillagerEntity var31 : var4) {
         var31.getBrain().remember(xxao083dbo7ag.x3ecoc0soedv0m, var1.roleOf(var31.getUuid()));
      }
   }

   private static void x1wvfvkvaehe0z(ServerWorld var0, x15kgoaq6i3m6i var1, List<VillagerEntity> var2) {
      List<VillagerEntity> var3 = var2.stream().filter(var0x -> !var0x.isBaby()).toList();
      Optional<VillagerEntity> var4 = var3.stream()
         .filter(var1x -> var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x1saccptp3i00w)
         .min(
            Comparator.<VillagerEntity>comparingInt(var1x -> var1.x3ctixeo4to9a4().filter(var1x.getUuid()::equals).isPresent() ? 0 : 1)
               .thenComparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core()))
         );
      if (var4.isPresent()) {
         for (VillagerEntity var11 : var3) {
            if (var11 != var4.get() && var1.roleOf(var11.getUuid()) == x1rxiy9mtu5b3h.x1saccptp3i00w) {
               var1.x36xpmo21hmp9h(var11.getUuid(), x1rxiy9mtu5b3h.x2j116s1wug0g2);
               xchl1k1tnh3w8(var0).markDirty();
            }
         }

         if (var1.x3ctixeo4to9a4().filter(((VillagerEntity)var4.get()).getUuid()::equals).isEmpty()) {
            var1.x3c59xc9hrfvhz(((VillagerEntity)var4.get()).getUuid());
            xchl1k1tnh3w8(var0).markDirty();
         }
      } else if (!var3.isEmpty()) {
         EnumMap<x1rxiy9mtu5b3h, Long> var5 = new EnumMap<>(x1rxiy9mtu5b3h.class);

         for (VillagerEntity var7 : var3) {
            var5.merge(var1.roleOf(var7.getUuid()), 1L, Long::sum);
         }

         Optional var10 = var3.stream()
            .filter(var1x -> var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x2j116s1wug0g2)
            .min(Comparator.comparingDouble(var1x -> var1x.getBlockPos().getSquaredDistance(var1.core())))
            .or(
               () -> var3.stream()
                  .filter(var1xx -> var1.roleOf(var1xx.getUuid()) != x1rxiy9mtu5b3h.x2xja04fpuesoh)
                  .filter(var2xx -> var5.getOrDefault(var1.roleOf(var2xx.getUuid()), 0L) > 1L)
                  .min(Comparator.comparingDouble(var1xx -> var1xx.getBlockPos().getSquaredDistance(var1.core())))
            );
         if (var10.isEmpty()) {
            Optional var12 = xcmgrg6f6maj1(var0, var1, var3.size());
            if (var12.isEmpty()) {
               return;
            }

            VillagerEntity var8 = (VillagerEntity)EntityType.VILLAGER.create(var0, SpawnReason.MOB_SUMMONED);
            if (var8 == null) {
               return;
            }

            var8.setPosition(((BlockPos)var12.get()).getX() + 0.5, ((BlockPos)var12.get()).getY(), ((BlockPos)var12.get()).getZ() + 0.5);
            var8.setPersistent();
            if (!var0.spawnEntity(var8)) {
               return;
            }

            var2.add(var8);
            var10 = Optional.of(var8);
         }

         VillagerEntity var13 = (VillagerEntity)var10.get();
         var1.x36xpmo21hmp9h(var13.getUuid(), x1rxiy9mtu5b3h.x1saccptp3i00w);
         var1.x3c59xc9hrfvhz(var13.getUuid());
         x21g1rujs9r1h6(var0, var13, x1rxiy9mtu5b3h.x1saccptp3i00w);
         xchl1k1tnh3w8(var0).markDirty();
      }
   }

   public static EnumMap<x1rxiy9mtu5b3h, Integer> x8x0922dn63y6(x15kgoaq6i3m6i var0, long var1) {
      EnumMap<x1rxiy9mtu5b3h, Integer> var3 = new EnumMap<>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var7 : x1rxiy9mtu5b3h.values()) {
         var3.put(var7, 0);
      }

      int var17 = Math.max(1, var0.population());
      int var18 = var0.cachedFood();
      int var19 = var0.cachedLogs();
      int var20 = var0.cachedStone();
      int var8 = var0.x12nt3bv2et7b7();
      boolean var9 = var0.x182p7mjnr75sg() < x1ext6bk0354f9(var0.population());

      int builderPriority = 25;
      if (var9) {
         builderPriority = 100;
      } else if (var0.x3e4ycl8cmmmgc()) {
         builderPriority = 90;
      }
      var3.put(x1rxiy9mtu5b3h.x35csp4lojh59r, builderPriority);

      int var10 = Math.max(48, var17 * 8);
      int var11 = Math.max(96, var17 * 20);
      if (var18 < var10) {
         var3.put(x1rxiy9mtu5b3h.x8jydaiei7iry, 75);
         var3.put(x1rxiy9mtu5b3h.xhds9crfjdp9i, 55);
         var3.put(x1rxiy9mtu5b3h.xeoehg87qwfrn, 40);
      } else if (var18 < var11) {
         var3.put(x1rxiy9mtu5b3h.x8jydaiei7iry, 60);
         var3.put(x1rxiy9mtu5b3h.xhds9crfjdp9i, 40);
         var3.put(x1rxiy9mtu5b3h.xeoehg87qwfrn, 30);
      } else {
         var3.put(x1rxiy9mtu5b3h.x8jydaiei7iry, 25);
         var3.put(x1rxiy9mtu5b3h.xhds9crfjdp9i, 15);
         var3.put(x1rxiy9mtu5b3h.xeoehg87qwfrn, 15);
      }

      int var12 = Math.max(64, var17 * 8);
      int lumberjackPriority = 20;
      if (var19 < Math.max(16, var12 / 4)) {
         lumberjackPriority = 100;
      } else if (var19 < var12) {
         lumberjackPriority = 70;
      }
      var3.put(x1rxiy9mtu5b3h.x2hwljbtybtlwc, lumberjackPriority);

      int var14 = Math.max(128, var17 * 16);
      int minerPriority = 15;
      if (var20 < var14 / 4) {
         minerPriority = 100;
      } else if (var20 < var14) {
         minerPriority = 65;
      }
      if (var8 < Math.max(8, var17)) {
         minerPriority = Math.max(minerPriority, 60);
      }
      var3.put(x1rxiy9mtu5b3h.x18g4mqx9kxs89, minerPriority);

      int guardPriority = 0;
      if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_guards) {
         boolean underAttack = var0.x3klfyffbbfab2() != Long.MIN_VALUE && var1 >= var0.x3klfyffbbfab2() && var1 - var0.x3klfyffbbfab2() < 2400L;
         if (underAttack) {
            guardPriority = 100;
         } else if (var17 >= 12) {
            guardPriority = 35;
         } else {
            guardPriority = 20;
         }
      }
      var3.put(x1rxiy9mtu5b3h.x3r8p2tvihwpph, guardPriority);

      xmgumxbnfkgss.x2hptx3oklbfn2(var0, var3);
      return var3;
   }

   public static EnumMap<x1rxiy9mtu5b3h, Integer> x17qz5r6rg31u8(x15kgoaq6i3m6i var0, long var1, int var3) {
      EnumMap<x1rxiy9mtu5b3h, Integer> var4 = x8x0922dn63y6(var0, var1);
      EnumMap<x1rxiy9mtu5b3h, Integer> var5 = new EnumMap<>(x1rxiy9mtu5b3h.class);
      EnumMap<x1rxiy9mtu5b3h, Double> var6 = new EnumMap<>(x1rxiy9mtu5b3h.class);

      for (x1rxiy9mtu5b3h var10 : x1rxiy9mtu5b3h.values()) {
         var5.put(var10, 0);
      }

      if (var3 <= 0) {
         return var5;
      }

      List<x1rxiy9mtu5b3h> var17 = List.of(
            x1rxiy9mtu5b3h.x35csp4lojh59r,
            x1rxiy9mtu5b3h.x8jydaiei7iry,
            x1rxiy9mtu5b3h.x2hwljbtybtlwc,
            x1rxiy9mtu5b3h.x18g4mqx9kxs89,
            x1rxiy9mtu5b3h.xhds9crfjdp9i,
            x1rxiy9mtu5b3h.xeoehg87qwfrn,
            x1rxiy9mtu5b3h.x3r8p2tvihwpph
         )
         .stream()
         .filter(var1x -> var4.get(var1x) > 0)
         .toList();

      int var18 = var3 >= var17.size() ? 1 : 0;
      for (x1rxiy9mtu5b3h var21 : var17) {
         var5.put(var21, var18);
      }

      int var20 = var3 - var18 * var17.size();
      int var22 = var17.stream().mapToInt(var4::get).sum();
      if (var22 <= 0) {
         var5.put(x1rxiy9mtu5b3h.x2j116s1wug0g2, var3);
         return var5;
      }

      int var11 = var18 * var17.size();
      for (x1rxiy9mtu5b3h var13 : var17) {
         double var14 = (double) var4.get(var13) * var20 / var22;
         int var16 = (int) Math.floor(var14);
         var5.put(var13, var5.get(var13) + var16);
         var6.put(var13, var14 - var16);
         var11 += var16;
      }

      while (var11 < var3) {
         x1rxiy9mtu5b3h var23 = var6.entrySet()
            .stream()
            .max(Comparator.<java.util.Map.Entry<x1rxiy9mtu5b3h, Double>>comparingDouble(java.util.Map.Entry::getValue)
               .thenComparingInt(var1x -> var4.get(var1x.getKey())))
            .map(java.util.Map.Entry::getKey)
            .orElse(x1rxiy9mtu5b3h.x8jydaiei7iry);
         var5.put(var23, var5.get(var23) + 1);
         var6.put(var23, -1.0);
         var11++;
      }

      return var5;
   }

   public static void x21g1rujs9r1h6(ServerWorld var0, VillagerEntity var1, x1rxiy9mtu5b3h var2) {
      x3psqezd5jem54(var0, var1.getBlockPos()).ifPresent(var2x -> xf6ny39u2klct.x3cumye59iar40(var0, var2x, var1));
      BlockPos var3 = x3psqezd5jem54(var0, var1.getBlockPos()).map(x15kgoaq6i3m6i::core).orElse(var1.getBlockPos());
      RegistryKey var4 = VillagerType.forBiome(var0.getBiome(var3));
      if (!var1.getVillagerData().type().matchesKey(var4)) {
         var1.setVillagerData(var1.getVillagerData().withType(var0.getRegistryManager(), var4));
      }
      RegistryKey var5 = switch (var2) {
         case x35csp4lojh59r -> VillagerProfession.MASON;
         case x3r8p2tvihwpph -> VillagerProfession.ARMORER;
         case x2hwljbtybtlwc, x18g4mqx9kxs89 -> VillagerProfession.TOOLSMITH;
         case x8jydaiei7iry -> VillagerProfession.FARMER;
         case xhds9crfjdp9i -> VillagerProfession.FISHERMAN;
         case xeoehg87qwfrn -> VillagerProfession.SHEPHERD;
         case x1saccptp3i00w, x2xja04fpuesoh -> VillagerProfession.CARTOGRAPHER;
         default -> VillagerProfession.NONE;
      };
      if (!var1.getVillagerData().profession().matchesKey(var5)) {
         var1.setVillagerData(var1.getVillagerData().withProfession(var0.getRegistryManager(), var5));
      }

      boolean var6 = var1.getCommandTags().contains("smart_colonies_role_name");
      if (!var1.hasCustomName() || x2qi06jvtolj3u(var1.getCustomName())) {
         if (var2 == x1rxiy9mtu5b3h.x2j116s1wug0g2) {
            if (var6) {
               var1.setCustomName(null);
               var1.setCustomNameVisible(false);
               var1.removeCommandTag("smart_colonies_role_name");
            }
         } else {
            var1.setCustomName(Text.translatable("entity.smart_colonies.role." + var2.serializedName()));
            var1.setCustomNameVisible(true);
            var1.addCommandTag("smart_colonies_role_name");
         }
      }
   }

   private static boolean x2qi06jvtolj3u(Text var0) {
      String var1 = var0.getString();
      return Set.of(
               "Builder",
               "Guard",
               "Militia",
               "Lumberjack",
               "Miner",
               "Farmer",
               "Fisherman",
               "Rancher",
               "Строитель",
               "Ополченец",
               "Лесоруб",
               "Шахтёр",
               "Фермер",
               "Рыбак",
               "Животновод",
               "Старейшина",
               "Основатель"
            )
            .contains(var1)
         || var1.startsWith("entity.smart_colonies.role.");
   }

   private static void x1t38fv75txqm7(ServerWorld var0, x15kgoaq6i3m6i var1, long var2) {
      if (var0.hasRaidAt(var1.core()) || x37idyr79ed75m.x3kaxmxx3tadjj(var0, var1).isPresent()) {
         var1.xz6ij33c0780z(var2);
         xchl1k1tnh3w8(var0).markDirty();
      }
   }

   private static void x39ey6ni61f3zn(ServerWorld var0, x15kgoaq6i3m6i var1) {
      if (var1.x2p9t1nvxo82zp() <= 0 || var1.x11xljsmcmocmw() <= 0) {
         if (var0.getTime() >= var1.x147mtuve9wuay() && var1.x1n1a2vp01kxzf().isEmpty()) {
            if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
               if (var1.population() != 0) {
                  long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.xqfd7s4y4gux6).count();
                  boolean var4 = var1.x3t9uchcxi5zlm().isPresent();
                  boolean var5 = var1.x182p7mjnr75sg() < x1ext6bk0354f9(var1.population());
                  boolean var6 = var2 < x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses;
                  if (var5 || var6 || var4) {
                     if (!var1.x3e4ycl8cmmmgc()) {
                        Identifier var7 = var4 ? x24xpzm43k9cqb(var0, var1, var1.x1beis4altbswn()) : xvg326225w07a(var0, var1);
                        Optional var8 = var0.getStructureTemplateManager().getTemplate(var7);
                        if (!var8.isEmpty()) {
                           Vec3i var9 = ((StructureTemplate)var8.get()).getSize();
                           if (!var1.x2zi111bgbtgzq()) {
                              var1.x31uoom8ikakob();
                           }

                           x32w10jmrowl3p.x2o5z7e7ygl136(var0, var1, var9.getX(), var9.getZ(), var9.getY()).ifPresent(var4x -> {
                              UUID var5x = var1.x3t9uchcxi5zlm().orElse(null);
                              int var6x = var1.x1beis4altbswn();
                              x2arpn38dclo1g var7x = new x2arpn38dclo1g(UUID.randomUUID(), var7, var4x, var9.getX(), var9.getY(), var9.getZ(), var5x, var6x);
                              if (var5x == null && var1.x2p9t1nvxo82zp() > 0 && var1.x11xljsmcmocmw() > 0 && var0.getTime() < var1.x3tkladv9pjeao()) {
                                 var7x.xkv5b17xl7vd6(true);
                              }

                              var1.x31ve0dsfeaxo4(var7x);
                              if (var5x != null) {
                                 var1.xqsw8yhmtxlpl();
                              }

                              int var8x = (int)Math.ceil(Math.sqrt(var1.core().getSquaredDistance(var4x))) + Math.max(var9.getX(), var9.getZ()) + 4;
                              var1.x1ne2dy4ss3d7w(Math.max(var1.radius(), Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var8x)));
                              xchl1k1tnh3w8(var0).markDirty();
                           });
                           if (!var1.x2zi111bgbtgzq() && !var1.x3e4ycl8cmmmgc()) {
                              var1.x1ne2dy4ss3d7w(
                                 Math.max(var1.radius(), Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var1.radius() + 16))
                              );
                              var1.xs28n9ortj0j6(var0.getTime() + 200L);
                              xchl1k1tnh3w8(var0).markDirty();
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   static void x32npq1dyt2s4h(ServerWorld var0, x15kgoaq6i3m6i var1) {
      if (x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion && !var1.x3e4ycl8cmmmgc() && var0.getTime() >= var1.x147mtuve9wuay()) {
         long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x2nfrt8qxdscso().isEmpty() && var0x.x2s8emavervqrz()).count();
         if (var2 >= x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses) {
            var1.x23n2tdykuxd7a(0);
         } else {
            Identifier var4 = xvg326225w07a(var0, var1);
            Optional var5 = var0.getStructureTemplateManager().getTemplate(var4);
            if (!var5.isEmpty()) {
               Vec3i var6 = ((StructureTemplate)var5.get()).getSize();
               if (!var1.x2zi111bgbtgzq()) {
                  var1.x31uoom8ikakob();
               }

               x32w10jmrowl3p.xzvjreewiw7bn(var0, var1, var6.getX(), var6.getZ(), var6.getY(), true).ifPresent(var4x -> {
                  x2arpn38dclo1g var5x = new x2arpn38dclo1g(UUID.randomUUID(), var4, var4x, var6.getX(), var6.getY(), var6.getZ());
                  var5x.xkv5b17xl7vd6(true);
                  var1.x31ve0dsfeaxo4(var5x);
                  xchl1k1tnh3w8(var0).markDirty();
               });
               if (!var1.x2zi111bgbtgzq() && !var1.x3e4ycl8cmmmgc()) {
                  var1.x1ne2dy4ss3d7w(Math.min(x3sqkg3n6v972r.x2by5f9ehxvwk9().village.expansion_radius_max, var1.radius() + 16));
                  var1.xs28n9ortj0j6(var0.getTime() + 200L);
                  xchl1k1tnh3w8(var0).markDirty();
               }
            }
         }
      }
   }

   public static int x1ext6bk0354f9(int var0) {
      return Math.clamp((long)((var0 + 3) / 4), 4, 16);
   }

   public static String x2y69zyuty3rxs(ServerWorld var0, x15kgoaq6i3m6i var1) {
      if (!var1.x1n1a2vp01kxzf().isEmpty()) {
         return "участок выбран, строится дорога (осталось " + var1.x1n1a2vp01kxzf().size() + ")";
      } else if (var1.x3e4ycl8cmmmgc()) {
         return "участок выбран, активно задание дома";
      } else if (var1.x2zi111bgbtgzq()) {
         return "поиск, проверен кандидат " + var1.x1eughlg5bm9gx();
      } else if (!x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         return "авторасширение выключено в конфиге";
      } else if (var1.population() == 0) {
         return "нет жителей";
      } else {
         long var2 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.xqfd7s4y4gux6).count();
         int var4 = x3sqkg3n6v972r.x2by5f9ehxvwk9().village.max_constructed_houses;
         int var5 = x1ext6bk0354f9(var1.population());
         if (var1.x182p7mjnr75sg() >= var5 && var2 >= var4) {
            return "городская цель достигнута: домов " + var2 + "/" + var4;
         } else {
            long var6 = Math.max(0L, var1.x147mtuve9wuay() - var0.getTime());
            if (var6 > 0L) {
               return "повтор поиска через " + (var6 + 19L) / 20L + " с";
            } else {
               return var1.x182p7mjnr75sg() < var5
                  ? "спрос на жильё есть, поиск запускается"
                  : "город растёт: домов " + var2 + "/" + var4 + ", поиск запускается";
            }
         }
      }
   }

   private static Identifier xvg326225w07a(ServerWorld var0, x15kgoaq6i3m6i var1) {
      String var2 = var0.getBiome(var1.core()).getKey().map(var0x -> var0x.getValue().getPath()).orElse("plains");
      String var10000;
      if (var2.contains("desert")) {
         var10000 = "desert";
      } else {
         int var7 = 798787158 ^ -1877370193;

         label31:
         while (true) {
            switch (var7) {
               case -1081795335:
               default:
                  if (var2.contains("savanna")) {
                     var10000 = "savanna";
                     break label31;
                  }

                  var7 = 1495013943 ^ 945331440;
                  break;
               case -429016261:
                  if (var2.contains("taiga")) {
                     var10000 = "taiga";
                     break label31;
                  }

                  var7 = -773761227 ^ -2085981895;
                  break;
               case 1380656652:
                  var10000 = "plains";
                  break label31;
               case 1631880903:
                  if (var2.contains("snow")) {
                     var7 = -641992087 ^ 147606088;
                     break;
                  } else if (!var2.contains("frozen")) {
                     var7 = -230391824 ^ 338281675;
                     break;
                  }
               case -780685279:
                  var10000 = "snowy";
                  break label31;
            }
         }
      }

      String var3 = var10000;
      long var4 = var1.x1ja61emeln2hc().stream().filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.xqfd7s4y4gux6).count();
      List var6 = x53nro1q5nc9v.xrbmqlm78i747(var3);
      return x53nro1q5nc9v.x4jnab85o8lop(var3, (String)var6.get(Math.floorMod((int)var4, var6.size())));
   }

   private static Identifier x24xpzm43k9cqb(ServerWorld var0, x15kgoaq6i3m6i var1, int var2) {
      String var3 = var0.getBiome(var1.core()).getKey().map(var0x -> var0x.getValue().getPath()).orElse("plains");
      String var10000;
      if (var3.contains("desert")) {
         var10000 = "desert";
      } else {
         int var5 = 381468270 ^ 762576951;

         label31:
         while (true) {
            switch (var5) {
               case -1409213695:
               default:
                  if (var3.contains("taiga")) {
                     var10000 = "taiga";
                     break label31;
                  }

                  var5 = -1493153808 ^ 1688920236;
                  break;
               case -1012226212:
                  var10000 = "plains";
                  break label31;
               case -279039341:
                  if (var3.contains("snow")) {
                     var5 = 305835204 ^ -1034307625;
                     break;
                  } else if (!var3.contains("frozen")) {
                     var5 = 2041967741 ^ -709562500;
                     break;
                  }
               case -798811373:
                  var10000 = "snowy";
                  break label31;
               case 1003011673:
                  if (var3.contains("savanna")) {
                     var10000 = "savanna";
                     break label31;
                  }

                  var5 = 280980781 ^ -2007106;
            }
         }
      }

      String var4 = var10000;
      return x53nro1q5nc9v.x2xvromqtmmr47(var4, var2);
   }

   public static boolean x3pcmrumjil4mo(ServerWorld var0, x15kgoaq6i3m6i var1, UUID var2, int var3) {
      return x2fxdvzecolqll(var0, var1, var2, var3).isPresent();
   }

   public static Optional<x2arpn38dclo1g> x2fxdvzecolqll(ServerWorld var0, x15kgoaq6i3m6i var1, UUID var2, int var3) {
      if (var3 >= 1 && var3 <= 3 && !var1.x3t9uchcxi5zlm().isPresent() && var1.x1n1a2vp01kxzf().isEmpty() && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0)) {
         boolean var4 = var1.x1ja61emeln2hc()
            .stream()
            .anyMatch(var1x -> var1x.x2nfrt8qxdscso().filter(var2::equals).isPresent() && var1x.x32oz43hmeynhc() != x20r5v7z43p3d5.xqfd7s4y4gux6);
         if (var4) {
            return Optional.empty();
         } else {
            Identifier var5 = x24xpzm43k9cqb(var0, var1, var3);
            Optional var6 = var0.getStructureTemplateManager().getTemplate(var5);
            if (var6.isEmpty()) {
               return Optional.empty();
            } else {
               Vec3i var7 = ((StructureTemplate)var6.get()).getSize();
               if (!var1.x2zi111bgbtgzq()) {
                  var1.x31uoom8ikakob();
               }

               Optional var8 = Optional.empty();

               for (int var9 = 0; var9 < 32 && var1.x2zi111bgbtgzq() && var8.isEmpty(); var9++) {
                  var8 = x32w10jmrowl3p.x2o5z7e7ygl136(var0, var1, var7.getX(), var7.getZ(), var7.getY());
               }

               xchl1k1tnh3w8(var0).markDirty();
               if (var8.isEmpty()) {
                  return Optional.empty();
               } else {
                  x2arpn38dclo1g var10 = new x2arpn38dclo1g(UUID.randomUUID(), var5, (BlockPos)var8.get(), var7.getX(), var7.getY(), var7.getZ(), var2, var3);
                  var1.x31ve0dsfeaxo4(var10);
                  return Optional.of(var10);
               }
            }
         }
      } else {
         return Optional.empty();
      }
   }

   private static void x3tgmhaj405foe(ServerWorld var0, x15kgoaq6i3m6i var1) {
      var1.x1ja61emeln2hc()
         .stream()
         .filter(var0x -> var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.x2tgnsmc98zm7p || var0x.x32oz43hmeynhc() == x20r5v7z43p3d5.x1mzkgtgadv3jh)
         .forEach(var1x -> {
            BlockBox var2 = var1x.x24kbqv6g4kkrg();

            for (int var6 : new int[]{var2.getMinX(), var2.getMaxX()}) {
               for (int var10 : new int[]{var2.getMinY(), var2.getMaxY()}) {
                  for (int var14 : new int[]{var2.getMinZ(), var2.getMaxZ()}) {
                     var0.spawnParticles(ParticleTypes.END_ROD, var6 + 0.5, var10 + 0.5, var14 + 0.5, 1, 0.0, 0.0, 0.0, 0.0);
                  }
               }
            }
         });
   }

   private static boolean x22gnz9u3m8aag(ServerWorld var0, BlockPos var1) {
      return var0.getChunkManager().isChunkLoaded(var1.getX() >> 4, var1.getZ() >> 4)
         && (!(var0.getBlockEntity(var1) instanceof Inventory) || !x2uh56vnzc4u51.xic1z39wiy3pn(var0, var1));
   }
}
