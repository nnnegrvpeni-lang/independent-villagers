package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.Predicate;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.chunk.WorldChunk;

public final class x313o9woxa22fw {
   private static final int x2ioh1n5gw3t9w = 24;
   private static final int x3ilosdurnyzb2 = 192;
   private static final int x19pp771uo78n5 = 320;

   private x313o9woxa22fw() {
   }

   public static void x28wuwjwutxh4c(ServerWorld var0, x3d5vk1zj3fvkj var1, long var2) {
      if (Math.floorMod(var2, 20) == 0 && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0) && x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         for (x15kgoaq6i3m6i var5 : new ArrayList<>(var1.colonies())) {
            if (var0.shouldTickEntityAt(var5.core()) || !var5.x2a380ray4md5t().isEmpty()) {
               if (var5.x2a380ray4md5t().isEmpty() && !x8veg662b2f5p.x2d8qtehqomuj6(var0)) {
                  xutdbpd6qmqlv(var0, var1, var5, var2);
               } else {
                  x38mjeaqbg6qzt(var0, var1, var5, var2);
               }
            }
         }
      }
   }

   private static void xutdbpd6qmqlv(ServerWorld var0, x3d5vk1zj3fvkj var1, x15kgoaq6i3m6i var2, long var3) {
      if (var3 >= var2.x15y708t7gvjof()
         && var2.population() >= 24
         && !x36u1fhs2z6uoi.active(var0, var2)
         && var2.cachedFood() >= 256
         && var2.cachedLogs() >= 128
         && var2.cachedStone() >= 256) {
         List var5 = x6whfxtraodl3(var0, var2);
         if (var5.size() >= 5) {
            Optional var6 = xrxwk7xpq9bee(var0, var1, var2);
            if (var6.isEmpty()) {
               var2.x3ki4hc4ldp1us(var3 + 1200L);
               var1.markDirty();
            } else {
               x8e2cs7lq2652(var0, var1, var2, var5, (BlockPos)var6.get(), var3);
            }
         }
      }
   }

   private static List<VillagerEntity> x6whfxtraodl3(ServerWorld var0, x15kgoaq6i3m6i var1) {
      List<VillagerEntity> var2 = var0.getEntitiesByType(
         EntityType.VILLAGER,
         x2n55y2tom37h2.x150ccxomrlozr(var0, var1),
         var2x -> var2x.isAlive()
            && !var2x.isBaby()
            && x2n55y2tom37h2.x3psqezd5jem54(var0, var2x.getBlockPos()).map(var1xx -> var1xx.id().equals(var1.id())).orElse(false)
      );
      EnumMap<x1rxiy9mtu5b3h, Integer> var3 = new EnumMap<>(x1rxiy9mtu5b3h.class);

      for (VillagerEntity var5 : var2) {
         var3.merge(var1.roleOf(var5.getUuid()), 1, Integer::sum);
      }

      ArrayList var8 = new ArrayList();

      for (VillagerEntity var6 : var2.stream()
         .sorted(Comparator.comparingInt(var1x -> var1.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x2j116s1wug0g2 ? 0 : 1))
         .toList()) {
         x1rxiy9mtu5b3h var7 = var1.roleOf(var6.getUuid());
         if (!var6.hasCustomer()
            && !var6.isSleeping()
            && var7 != x1rxiy9mtu5b3h.x1saccptp3i00w
            && var7 != x1rxiy9mtu5b3h.x2xja04fpuesoh
            && (var7 == x1rxiy9mtu5b3h.x2j116s1wug0g2 || (Integer)var3.get(var7) > 1)) {
            var8.add(var6);
            var3.merge(var7, -1, Integer::sum);
            if (var8.size() == 5) {
               break;
            }
         }
      }

      return var8;
   }

   private static void x8e2cs7lq2652(ServerWorld var0, x3d5vk1zj3fvkj var1, x15kgoaq6i3m6i var2, List<VillagerEntity> var3, BlockPos var4, long var5) {
      Map<Identifier, Integer> var7 = new LinkedHashMap<>();

      for (x3hb7nnusq8hzf var9 : List.of(
         new x3hb7nnusq8hzf(var0x -> var0x.isIn(ItemTags.LOGS), 32),
         new x3hb7nnusq8hzf(var0x -> var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS), 112),
         new x3hb7nnusq8hzf(var0x -> x2n55y2tom37h2.xhahxgpa08yc9(var0x) > 0, 32)
      )) {
         for (int var10 = 0; var10 < var9.x1w6emwv8ukb2; var10++) {
            x2n55y2tom37h2.x3lxwdirnn77qc(var0, var2, var9.x3r2nd9ja5plho)
               .ifPresent(var1x -> var7.merge(Registries.ITEM.getId(var1x.getItem()), var1x.getCount(), Integer::sum));
         }
      }

      for (VillagerEntity var13 : var3) {
         x1c1r5c6a13gtl.x2fxoqmbs0henb(var13);
         x18ucnhocy9qn5.x3oupbjeq14mj8(var13);
         var13.getBrain().forget(MemoryModuleType.WALK_TARGET);
         var13.getBrain().forget(MemoryModuleType.PATH);
      }

      VillagerEntity var12 = (VillagerEntity)var3.getFirst();
      var12.setPersistent();
      var2.x36xpmo21hmp9h(var12.getUuid(), x1rxiy9mtu5b3h.x2xja04fpuesoh);
      var2.xveqe1h09qne3(var12.getUuid(), var4, var5);
      var2.x1i1560srleos1(var7);

      for (VillagerEntity var15 : var3.subList(1, 5)) {
         var15.setPersistent();
         var2.x2zftltl6vhwwx().add(var15.getUuid());
         var2.x36xpmo21hmp9h(var15.getUuid(), x1rxiy9mtu5b3h.x2xja04fpuesoh);
         x2n55y2tom37h2.x21g1rujs9r1h6(var0, var15, x1rxiy9mtu5b3h.x2xja04fpuesoh);
      }

      var12.setVillagerData(var12.getVillagerData().withProfession(var0.getRegistryManager(), VillagerProfession.CARTOGRAPHER));
      var12.setCustomName(Text.translatable("entity.smart_colonies.role.founder"));
      var12.setCustomNameVisible(true);
      var1.markDirty();
   }

   static boolean xpl4idj7kx63g(x15kgoaq6i3m6i var0) {
      Iterator var1 = var0.x3171qim2pcjd7().entrySet().iterator();

      while (var1.hasNext()) {
         Entry var2 = (Entry)var1.next();
         if (new ItemStack((ItemConvertible)Registries.ITEM.get((Identifier)var2.getKey())).isIn(ItemTags.LOGS)) {
            if ((Integer)var2.getValue() == 1) {
               var1.remove();
            } else {
               var2.setValue((Integer)var2.getValue() - 1);
            }

            return true;
         }
      }

      return false;
   }

   private static boolean xe2rg0vv8nbqj(x15kgoaq6i3m6i var0, int var1) {
      Map<Identifier, Integer> var2 = var0.x3171qim2pcjd7();
      if (var2.entrySet().stream().filter(var0x -> x2b07n23fgkh41(var0x.getKey())).mapToInt(Entry::getValue).sum() < var1) {
         return false;
      } else {
         List<Identifier> var3 = new ArrayList<>(var2.keySet());
         var3.sort(Comparator.comparingInt(var0x -> new ItemStack((ItemConvertible)Registries.ITEM.get(var0x)).isIn(ItemTags.STONE_CRAFTING_MATERIALS) ? 0 : 1));

         for (Identifier var5 : var3) {
            if (x2b07n23fgkh41(var5)) {
               int var6 = Math.min(var1, (Integer)var2.get(var5));
               int var7 = (Integer)var2.get(var5) - var6;
               if (var7 == 0) {
                  var2.remove(var5);
               } else {
                  var2.put(var5, var7);
               }

               var1 -= var6;
               if (var1 == 0) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private static boolean x2b07n23fgkh41(Identifier var0) {
      ItemStack var1 = new ItemStack((ItemConvertible)Registries.ITEM.get(var0));
      return var1.isIn(ItemTags.LOGS) || var1.isIn(ItemTags.STONE_CRAFTING_MATERIALS) || x2n55y2tom37h2.xhahxgpa08yc9(var1) > 0;
   }

   private static void x1c3y5ewaifjlb(ServerWorld var0, x15kgoaq6i3m6i var1, x15kgoaq6i3m6i var2, boolean var3) {
      Iterator var4 = var1.x3171qim2pcjd7().entrySet().iterator();

      while (var4.hasNext()) {
         Entry var5 = (Entry)var4.next();
         Item var6 = (Item)Registries.ITEM.get((Identifier)var5.getKey());
         int var7 = (Integer)var5.getValue();

         while (var7 > 0) {
            int var8 = Math.min(var6.getMaxCount(), var7);
            ItemStack var9 = new ItemStack(var6, var8);
            x2n55y2tom37h2.x2cl1crde2rnxy(var0, var2, var9);
            int var10 = var8 - var9.getCount();
            var7 -= var10;
            if (!var9.isEmpty()) {
               if (!var3) {
                  break;
               }

               var0.spawnEntity(new ItemEntity(var0, var2.core().getX() + 0.5, var2.core().getY() + 1, var2.core().getZ() + 0.5, var9));
               var7 -= var9.getCount();
            }
         }

         if (var7 == 0) {
            var4.remove();
         } else {
            var5.setValue(var7);
         }
      }

      x3d5vk1zj3fvkj.get(var0).markDirty();
   }

   public static x1lf12nv67hipd x3seqvpn908svy(ServerWorld var0, x15kgoaq6i3m6i var1, BlockPos var2) {
      if (!x8veg662b2f5p.x2d8qtehqomuj6(var0)) {
         return new x1lf12nv67hipd(0, "Сначала /smartcolonies film on.");
      } else if (!x1dxdlnevtfbhm.x35ev8l9vc9uk7(var0) || !x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_auto_expansion) {
         return new x1lf12nv67hipd(0, "Переселение или изменения ландшафта выключены в конфиге.");
      } else if (var1.x2a380ray4md5t().isPresent() || x8veg662b2f5p.x2r8c2c8wlnbsc(var0, var1)) {
         return new x1lf12nv67hipd(0, "Экспедиция или подготовка max уже выполняется.");
      } else if (x36u1fhs2z6uoi.active(var0, var1)) {
         return new x1lf12nv67hipd(0, "Сначала завершите нападение: переселенцы не отправляются из атакованной деревни.");
      } else {
         x3d5vk1zj3fvkj var3 = x3d5vk1zj3fvkj.get(var0);
         BlockPos var4 = var2 == null ? xrxwk7xpq9bee(var0, var3, var1).orElse(null) : var2.toImmutable();
         if (var4 == null) {
            return new x1lf12nv67hipd(0, "Не найден загруженный участок в 192..320 блоках. Можно указать координаты: film expedition X Y Z.");
         } else if (!x2d69km2ccnj1p(var0, var4)) {
            return new x1lf12nv67hipd(0, "Участок слишком близко к постройкам/земле игрока либо окружающий ландшафт непригоден для поселения.");
         } else if (var0.isOutOfHeightLimit(var4)
            || var0.isOutOfHeightLimit(var4.up())
            || !var0.getWorldBorder().contains(var4)
            || !var0.getChunkManager().isChunkLoaded(var4.getX() >> 4, var4.getZ() >> 4)) {
            return new x1lf12nv67hipd(0, "Цель должна быть внутри мира и в загруженном чанке.");
         } else if (var3.colonies().stream().anyMatch(var1x -> x1g8mc3ys7ith3(var1x.core(), var4) < 25600.0)
            || x1g8mc3ys7ith3(var1.core(), var4) > 409600.0
            || !x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var4)
            || !var0.getBlockState(var4).isAir()
            || !x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var4)
            || x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var4.down())) {
            return new x1lf12nv67hipd(0, "Нужна свободная сухая площадка на земле, не ближе 160 блоков к колоколам и не дальше 640 блоков от родной деревни.");
         } else if (x2n55y2tom37h2.x2l4eb2ibsf118(var0, new x15kgoaq6i3m6i(UUID.randomUUID(), var4, 96, Long.MIN_VALUE)).isEmpty()) {
            return new x1lf12nv67hipd(0, "У цели нет безопасного места для двойного склада. Осмотрите площадку и загрузите соседние чанки.");
         } else {
            List var5 = x6whfxtraodl3(var0, var1);
            if (var5.size() < 5) {
               return new x1lf12nv67hipd(
                  0, "Нужно пять свободных взрослых жителей сверх обязательных профессий; старейшина не уходит. Для подготовки: film population 24."
               );
            } else if (!x2n55y2tom37h2.x22hjebj4vmztt(
               var0, var1, var0x -> var0x.isIn(ItemTags.LOGS) || var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS) || x2n55y2tom37h2.xhahxgpa08yc9(var0x) > 0, 48
            )) {
               return new x1lf12nv67hipd(0, "Нужно 48 единиц еды, брёвен или камня в общественном складе для стартового капитала.");
            } else {
               x8e2cs7lq2652(var0, var3, var1, var5, var4, var0.getTime() + 200L);

               for (int var6 = 0; var6 < 3; var6++) {
                  var1.xy40t7pepehnr(8);
               }

               var3.markDirty();
               return new x1lf12nv67hipd(
                  1,
                  "Основатель и четверо сопровождающих собираются 10 секунд, затем идут в "
                     + var4.toShortString()
                     + ". Капитал подготовлен командой за 48 ресурсов; поход, основание и смена профессий — обычная логика. Следуйте за группой для загрузки маршрута."
               );
            }
         }
      }
   }

   private static double x1g8mc3ys7ith3(BlockPos var0, BlockPos var1) {
      double var2 = (double)var0.getX() - var1.getX();
      double var4 = (double)var0.getZ() - var1.getZ();
      return var2 * var2 + var4 * var4;
   }

   private static Optional<BlockPos> xrxwk7xpq9bee(ServerWorld var0, x3d5vk1zj3fvkj var1, x15kgoaq6i3m6i var2) {
      for (int var3 = 0; var3 < 16; var3++) {
         double var4 = var0.getRandom().nextDouble() * Math.PI * 2.0;
         int var6 = 192 + var0.getRandom().nextInt(129);
         int var7 = var2.core().getX() + (int)Math.round(Math.cos(var4) * var6);
         int var8 = var2.core().getZ() + (int)Math.round(Math.sin(var4) * var6);
         if (var0.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
            BlockPos var9 = new BlockPos(var7, var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var7, var8), var8);
            if (var0.getWorldBorder().contains(var9)
               && var0.getFluidState(var9).isEmpty()
               && var0.getBlockState(var9).getCollisionShape(var0, var9).isEmpty()
               && var0.getBlockState(var9.up()).getCollisionShape(var0, var9.up()).isEmpty()
               && var0.getBlockState(var9.down()).isSideSolidFullSquare(var0, var9.down(), Direction.UP)
               && x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var9)
               && !x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var9.down())) {
               boolean var10 = var1.colonies().stream().anyMatch(var1x -> var1x.core().getSquaredDistance(var9) < 25600.0);
               if (!var10 && x2d69km2ccnj1p(var0, var9)) {
                  return Optional.of(var9.toImmutable());
               }
            }
         }
      }

      return Optional.empty();
   }

   public static boolean x2d69km2ccnj1p(ServerWorld var0, BlockPos var1) {
      x3d5vk1zj3fvkj var2 = x3d5vk1zj3fvkj.get(var0);
      if (var2.playerBuildNear(var1, 104)
         || var2.land()
            .stream()
            .anyMatch(
               var1x -> Math.abs((long)var1x.x2kystgxl68xrn().getX() - var1.getX()) <= 104 + var1x.x3rgd0i6cz19eu()
                  && Math.abs((long)var1x.x2kystgxl68xrn().getZ() - var1.getZ()) <= 104 + var1x.x3rgd0i6cz19eu()
            )) {
         return false;
      } else if (x1dxdlnevtfbhm.xz4flfirg81x1(var0, var1)) {
         return false;
      } else {
         for (int var3 = var1.getX() - 104 >> 4; var3 <= var1.getX() + 104 >> 4; var3++) {
            for (int var4 = var1.getZ() - 104 >> 4; var4 <= var1.getZ() + 104 >> 4; var4++) {
               if (var0.getChunkManager().isChunkLoaded(var3, var4)) {
                  WorldChunk var5 = var0.getChunkManager().getWorldChunk(var3, var4);
                  if (var5 != null) {
                     for (Entry var7 : var5.getBlockEntities().entrySet()) {
                        BlockPos var8 = (BlockPos)var7.getKey();
                        if (Math.abs(var8.getY() - var1.getY()) <= 32
                           && x1g8mc3ys7ith3(var8, var1) <= 10816.0
                           && (var7.getValue() instanceof Inventory || var0.getBlockState(var8).getBlock() instanceof BedBlock)) {
                           return false;
                        }
                     }
                  }
               }
            }
         }

         int var10 = Integer.MAX_VALUE;
         int var11 = Integer.MIN_VALUE;

         for (int var12 = -8; var12 <= 8; var12++) {
            int var13 = -8;

            while (var13 <= 8) {
               BlockPos var14 = var1.add(var12, 0, var13);
               if (var0.getWorldBorder().contains(var14) && var0.getChunkManager().isChunkLoaded(var14.getX() >> 4, var14.getZ() >> 4)) {
                  int var15 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var14.getX(), var14.getZ()) - 1;
                  BlockPos var9 = new BlockPos(var14.getX(), var15, var14.getZ());
                  if (x1dxdlnevtfbhm.xroinv4hk6l7h(var0.getBlockState(var9))
                     && var0.getFluidState(var9.up()).isEmpty()
                     && var0.getBlockState(var9.up()).getCollisionShape(var0, var9.up()).isEmpty()) {
                     if (x1dxdlnevtfbhm.xroinv4hk6l7h(var0.getBlockState(var9.down())) && x1dxdlnevtfbhm.xroinv4hk6l7h(var0.getBlockState(var9.down(2)))) {
                        var10 = Math.min(var10, var15);
                        var11 = Math.max(var11, var15);
                        if (var11 - var10 > 3) {
                           return false;
                        }

                        var13++;
                        continue;
                     }

                     return false;
                  }

                  return false;
               }

               return false;
            }
         }

         return var11 - var10 <= 3 && Math.abs(var1.getY() - 1 - (var10 + var11) / 2) <= 3;
      }
   }

   public static void xw6o9aon4s40m(ServerWorld var0, VillagerEntity var1) {
      for (x15kgoaq6i3m6i var3 : x3d5vk1zj3fvkj.get(var0).colonies()) {
         if (var3.x3hurh64xwtw51(var1.getUuid())) {
            if (var3.x2a380ray4md5t().filter(var1.getUuid()::equals).isPresent()) {
               var3.x1nxldt1cw1h5h();
            } else {
               var3.x2zftltl6vhwwx().remove(var1.getUuid());
            }

            var3.x36xpmo21hmp9h(var1.getUuid(), x1rxiy9mtu5b3h.x2j116s1wug0g2);
            var3.x2qmn6nt922pp6();
            if (var3.x1x26mhvd93s02() && var3.x2zftltl6vhwwx().isEmpty()) {
               for (Entry var5 : var3.x3171qim2pcjd7().entrySet()) {
                  Item var6 = (Item)Registries.ITEM.get((Identifier)var5.getKey());
                  int var7 = (Integer)var5.getValue();

                  while (var7 > 0) {
                     int var8 = Math.min(var7, var6.getMaxCount());
                     var0.spawnEntity(new ItemEntity(var0, var1.getX(), var1.getY(), var1.getZ(), new ItemStack(var6, var8)));
                     var7 -= var8;
                  }
               }

               var3.x3171qim2pcjd7().clear();
            }

            x3d5vk1zj3fvkj.get(var0).markDirty();
         }
      }
   }

   private static void x38mjeaqbg6qzt(ServerWorld var0, x3d5vk1zj3fvkj var1, x15kgoaq6i3m6i var2, long var3) {
      UUID var5 = var2.x2a380ray4md5t().orElse(null);
      BlockPos var6 = var2.x1x0agg3sdxjk8().orElse(null);
      if (var5 != null && var6 != null) {
         if (var2.x1x26mhvd93s02()) {
            UUID var7 = var2.x2zftltl6vhwwx()
               .stream()
               .filter(var1x -> var0.getEntity(var1x) instanceof VillagerEntity var2x && var2x.isAlive())
               .findFirst()
               .orElse(null);
            if (var7 == null) {
               if (var2.x2zftltl6vhwwx().isEmpty()) {
                  var2.x3ch5vvzyhhzwj(var3 + 24000L);
                  var1.markDirty();
               }

               return;
            }

            var2.x35wpd0lcwvgn6(var7);
            var5 = var7;
            var1.markDirty();
         }

         Entity var23 = var0.getEntity(var5);
         if (var23 != null) {
            if (var23 instanceof VillagerEntity var8 && var8.isAlive()) {
               if (var8.age - var8.getLastAttackedTime() <= 100 && x37idyr79ed75m.x1bj0phudo3a5m(var8.getAttacker())
                  || var2.x2zftltl6vhwwx()
                     .stream()
                     .anyMatch(
                        var1x -> var0.getEntity(var1x) instanceof VillagerEntity var2x
                           && var2x.age - var2x.getLastAttackedTime() <= 100
                           && x37idyr79ed75m.x1bj0phudo3a5m(var2x.getAttacker())
                     )) {
                  var2.x2qmn6nt922pp6();
               }

               if (var2.x2zftltl6vhwwx().isEmpty()) {
                  var2.x2qmn6nt922pp6();
               }

               if (var2.xfx95ux1xn73w()) {
                  List<UUID> var25 = new ArrayList<>(var2.x2zftltl6vhwwx());
                  var25.add(var5);
                  boolean var10 = true;

                  for (UUID var27 : var25) {
                     if (!(var0.getEntity(var27) instanceof VillagerEntity var28 && var28.isAlive())) {
                        var10 = false;
                     } else if (var28.getBlockPos().getSquaredDistance(var2.core()) > 144.0) {
                        var10 = false;
                        x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var28, var2.core(), 0.9);
                        x18ucnhocy9qn5.x3hi5h4uz5pg79(var0, var28);
                     }
                  }

                  if (var10) {
                     x1c3y5ewaifjlb(var0, var2, var2, true);
                     var2.x3ch5vvzyhhzwj(var3 + 24000L);
                     var1.markDirty();
                  }
               } else if (var3 % 200L != 0L
                  || !var0.getChunkManager().isChunkLoaded(var6.getX() >> 4, var6.getZ() >> 4)
                  || x2d69km2ccnj1p(var0, var6)
                     && !var1.colonies().stream().anyMatch(var1x -> x1g8mc3ys7ith3(var1x.core(), var2.x1x0agg3sdxjk8().orElseThrow()) < 25600.0)) {
                  long var24 = var3 - var2.x1spmho2xy9muc();
                  boolean var11 = true;
                  boolean var12 = false;
                  boolean var13 = var8.getBlockPos().getSquaredDistance(var6) <= 36.0;
                  int var14 = 0;

                  for (UUID var16 : new ArrayList<>(var2.x2zftltl6vhwwx())) {
                     int var17 = var14++ % 4;
                     Entity var18 = var0.getEntity(var16);
                     if (var18 == null) {
                        var11 = false;
                        var12 = true;
                     } else if (!(var18 instanceof VillagerEntity var19 && var19.isAlive())) {
                        var2.x2zftltl6vhwwx().remove(var16);
                        var2.x36xpmo21hmp9h(var16, x1rxiy9mtu5b3h.x2j116s1wug0g2);
                        var1.markDirty();
                     } else if (!var19.hasCustomer() && !var19.isSleeping()) {
                        if (var19.squaredDistanceTo(var8) > 16.0 || var13 && var19.getBlockPos().getSquaredDistance(var6) > 25.0) {
                           BlockPos var20 = var8.getBlockPos();
                           if (var13) {
                              int[][] var21 = new int[][]{{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
                              BlockPos var22 = var6.add(var21[var17][0], 0, var21[var17][1]);
                              var20 = x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var22) ? var22 : var6;
                           }

                           x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var19, var20, 0.95);
                           x18ucnhocy9qn5.x3hi5h4uz5pg79(var0, var19);
                        }

                        if (var19.squaredDistanceTo(var8) > 144.0) {
                           var12 = true;
                        }

                        if (var19.getBlockPos().getSquaredDistance(var6) > 36.0) {
                           var11 = false;
                        } else if (var2.x13ua0ftegttnr() >= 24 && var8.getBlockPos().getSquaredDistance(var6) <= 9.0 && !x14xuvjghdgxyq(var0, var19, var6)) {
                           var11 = false;
                        }
                     } else {
                        var11 = false;
                        var12 = true;
                     }
                  }

                  if (var24 < 0L) {
                     if (var8.getBlockPos().getSquaredDistance(var2.core()) > 9.0) {
                        x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var8, var2.core(), 0.9);
                     }
                  } else {
                     if (var2.x37br4tln8q08z() < 3 && var24 >= (var2.x37br4tln8q08z() + 1L) * 1200L) {
                        boolean var30 = var2.xlklmlbkuwa0t()
                           ? xe2rg0vv8nbqj(var2, 16)
                           : x2n55y2tom37h2.x22hjebj4vmztt(
                              var0,
                              var2,
                              var0x -> var0x.isIn(ItemTags.LOGS) || var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS) || x2n55y2tom37h2.xhahxgpa08yc9(var0x) > 0,
                              16
                           );
                        if (var30) {
                           var2.xy40t7pepehnr(8);
                           var1.markDirty();
                        }
                     }

                     if (!var8.isSleeping() && !var8.hasCustomer()) {
                        if (var12) {
                           x18ucnhocy9qn5.x3oupbjeq14mj8(var8);
                        } else if (var8.getBlockPos().getSquaredDistance(var6) <= 9.0) {
                           if (var2.x13ua0ftegttnr() >= 24 && var11 && x14xuvjghdgxyq(var0, var8, var6)) {
                              var8.getNavigation().stop();
                              xn4bhm2rctqli(var0, var1, var2, var8, var6, var3);
                           }
                        } else {
                           if (!x1wie612630ex.x2gox5tp6i8qkj(var0, var2, var8, var6)) {
                              x18ucnhocy9qn5.x1wi30zgg7nf81(var0, var8, var6, 0.9);
                           }

                           x18ucnhocy9qn5.x3hi5h4uz5pg79(var0, var8);
                        }
                     }
                  }
               } else {
                  Optional var9 = xrxwk7xpq9bee(var0, var1, var2);
                  if (var9.isPresent()) {
                     var2.x324hyyfyx41wk((BlockPos)var9.get());
                  } else {
                     var2.x2qmn6nt922pp6();
                  }

                  var1.markDirty();
               }
            } else {
               var2.x1nxldt1cw1h5h();
               var1.markDirty();
            }
         }
      }
   }

   private static boolean x14xuvjghdgxyq(ServerWorld var0, VillagerEntity var1, BlockPos var2) {
      if (!var1.getBoundingBox().intersects(new Box(var2))) {
         return true;
      } else {
         BlockPos var3 = (BlockPos)BlockPos.findClosest(
               var2,
               3,
               1,
               var3x -> Math.max(Math.abs(var3x.getX() - var2.getX()), Math.abs(var3x.getZ() - var2.getZ())) >= 2
                  && x1c1r5c6a13gtl.x9bnd6e2jczhd(var0, var3x)
                  && x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var3x)
                  && !x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var3x.down())
                  && var0.getOtherEntities(var1, new Box(var3x).stretch(0.0, 1.0, 0.0).expand(0.1), Entity::isAlive).isEmpty()
            )
            .orElse(null);
         if (var3 != null) {
            x1c1r5c6a13gtl.xf8lnb134kd0j(var0, var1, var3, 0.9);
         }

         return false;
      }
   }

   private static void xn4bhm2rctqli(ServerWorld var0, x3d5vk1zj3fvkj var1, x15kgoaq6i3m6i var2, VillagerEntity var3, BlockPos var4, long var5) {
      if (x2d69km2ccnj1p(var0, var4) && !var2.x2zftltl6vhwwx().isEmpty()) {
         if (x1dxdlnevtfbhm.x13f7be5vlapn7(var0, var4)
            && !x1dxdlnevtfbhm.x3o1lagqjim5tz(var0, var4.down())
            && var0.getWorldBorder().contains(var4)
            && var0.getBlockState(var4).isAir()
            && var0.getBlockState(var4.down()).isSideSolidFullSquare(var0, var4.down(), Direction.UP)) {
            x15kgoaq6i3m6i var7 = new x15kgoaq6i3m6i(UUID.randomUUID(), var4, 96, Long.MIN_VALUE);
            Optional var8 = x2n55y2tom37h2.x2l4eb2ibsf118(var0, var7);
            if (!var8.isEmpty() && var0.getOtherEntities(null, new Box(var4), var0x -> var0x.isAlive() && var0x instanceof LivingEntity).isEmpty()) {
               if (x1dxdlnevtfbhm.x1bq0f8n53gtob(var0, var4, Blocks.BELL.getDefaultState(), 3)) {
                  if (!x2n55y2tom37h2.xt55z8grtuvpq(var0, var7, (BlockPos)var8.get(), x2jj5edkiu5orf.x2y2vmpsepzv3)) {
                     var0.setBlockState(var4, Blocks.AIR.getDefaultState(), 3);
                  } else {
                     var1.add(var7);
                     if (var2.xlklmlbkuwa0t()) {
                        x1c3y5ewaifjlb(var0, var2, var7, true);
                     } else {
                        xqy84pu018mqc(var0, var2, var7, var0x -> var0x.isIn(ItemTags.LOGS), 32);
                        xqy84pu018mqc(var0, var2, var7, var0x -> var0x.isIn(ItemTags.STONE_CRAFTING_MATERIALS), 64);
                        xqy84pu018mqc(var0, var2, var7, var0x -> x2n55y2tom37h2.xhahxgpa08yc9(var0x) > 0, 32);
                     }

                     var7.x36xpmo21hmp9h(var3.getUuid(), x1rxiy9mtu5b3h.x1saccptp3i00w);
                     var7.x3c59xc9hrfvhz(var3.getUuid());
                     x1rxiy9mtu5b3h[] var9 = new x1rxiy9mtu5b3h[]{
                        x1rxiy9mtu5b3h.x35csp4lojh59r, x1rxiy9mtu5b3h.x8jydaiei7iry, x1rxiy9mtu5b3h.x2hwljbtybtlwc, x1rxiy9mtu5b3h.x18g4mqx9kxs89
                     };
                     int var10 = 0;

                     for (UUID var12 : var2.x2zftltl6vhwwx()) {
                        if (var0.getEntity(var12) instanceof VillagerEntity var13) {
                           x1rxiy9mtu5b3h var15 = var9[var10++ % var9.length];
                           var7.x36xpmo21hmp9h(var12, var15);
                           x2n55y2tom37h2.x21g1rujs9r1h6(var0, var13, var15);
                        }
                     }

                     var3.setVillagerData(var3.getVillagerData().withProfession(var0.getRegistryManager(), VillagerProfession.CARTOGRAPHER));
                     var3.setCustomName(Text.translatable("entity.smart_colonies.role.elder"));
                     var3.setCustomNameVisible(true);
                     var2.x36xpmo21hmp9h(var3.getUuid(), x1rxiy9mtu5b3h.x2j116s1wug0g2);
                     var2.x3ch5vvzyhhzwj(var5 + 24000L);
                     var1.markDirty();
                  }
               }
            }
         }
      }
   }

   private static void xqy84pu018mqc(ServerWorld var0, x15kgoaq6i3m6i var1, x15kgoaq6i3m6i var2, Predicate<ItemStack> var3, int var4) {
      for (int var5 = 0; var5 < var4; var5++) {
         Optional var6 = x2n55y2tom37h2.x3lxwdirnn77qc(var0, var1, var3);
         if (var6.isEmpty()) {
            return;
         }

         if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var2, (ItemStack)var6.get())) {
            if (!x2n55y2tom37h2.x2cl1crde2rnxy(var0, var1, (ItemStack)var6.get())) {
               var0.spawnEntity(new ItemEntity(var0, var1.core().getX() + 0.5, var1.core().getY() + 1, var1.core().getZ() + 0.5, (ItemStack)var6.get()));
            }

            return;
         }
      }
   }
}
