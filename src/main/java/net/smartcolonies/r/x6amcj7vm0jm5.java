package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.WeakHashMap;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.EmptyBlockView;
import net.minecraft.world.Heightmap.Type;

public final class x6amcj7vm0jm5 {
   public static final int x3hucludc7erve = 8;
   private static final String x2w7cyo0bvcpzm = "smart_colonies_land";
   private static final Map<x3d5vk1zj3fvkj, Map<UUID, x3rldzj1ugn0ma>> x1qp0aad0xefh = new WeakHashMap<>();

   private x6amcj7vm0jm5() {
   }

   private static Map<UUID, x3rldzj1ugn0ma> xt33a2rbozpnr(x3d5vk1zj3fvkj var0) {
      return x1qp0aad0xefh.computeIfAbsent(var0, var0x -> new HashMap<>());
   }

   public static ItemStack x31zhh2yvui44c(ServerWorld var0, x15kgoaq6i3m6i var1) {
      ItemStack var2 = new ItemStack(Items.PAPER);
      NbtCompound var3 = new NbtCompound();
      var3.putBoolean("smart_colonies_land", true);
      var3.putString("colony", var1.id().toString());
      var3.putString("dimension", var0.getRegistryKey().getValue().toString());
      var2.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var3));
      var2.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.smart_colonies.land_deed"));
      var2.set(DataComponentTypes.LORE, new LoreComponent(List.of(Text.translatable("item.smart_colonies.land_deed.use", new Object[]{17, 17}))));
      return var2;
   }

   public static boolean x3ew3lrc2q3oxu(ItemStack var0) {
      NbtComponent var1 = (NbtComponent)var0.get(DataComponentTypes.CUSTOM_DATA);
      return var0.isOf(Items.PAPER) && var1 != null && var1.copyNbt().getBoolean("smart_colonies_land", false);
   }

   public static boolean x1mzhkk1en9rl(ServerWorld var0, PlayerEntity var1, Hand var2, BlockPos var3) {
      ItemStack var4 = var1.getStackInHand(var2);
      if (!x3ew3lrc2q3oxu(var4)) {
         return false;
      } else {
         NbtCompound var5 = ((NbtComponent)var4.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
         if (var5.getBoolean("used", false)) {
            var1.sendMessage(Text.translatable("item.smart_colonies.land_deed.used"), false);
            return false;
         } else {
            x3d5vk1zj3fvkj var6 = x3d5vk1zj3fvkj.get(var0);

            x15kgoaq6i3m6i var7;
            try {
               var7 = var6.byId(UUID.fromString(var5.getString("colony", ""))).orElse(null);
            } catch (IllegalArgumentException var9) {
               return false;
            }

            if (var7 != null
               && var0.getRegistryKey().getValue().toString().equals(var5.getString("dimension", ""))
               && xboirziij2fbq(var0, var7, var1.getUuid(), var3)) {
               var6.addLand(new x3o14q3v4oy99m(var1.getUuid(), var7.id(), var3, 8));
               xt33a2rbozpnr(var6).remove(var1.getUuid());
               ItemStack var8 = var4.copyWithCount(1);
               var5.putBoolean("used", true);
               var5.putLong("center", var3.asLong());
               var8.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var5));
               var8.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.smart_colonies.land_deed.complete", new Object[]{var3.toShortString()}));
               var4.decrement(1);
               var1.setStackInHand(var2, var8);
               if (!var4.isEmpty() && !var1.getInventory().insertStack(var4)) {
                  var1.dropItem(var4, false);
               }

               x2uh56vnzc4u51.x34rm129tews28(var0);
               return true;
            } else {
               var1.sendMessage(Text.translatable("message.smart_colonies.land_unavailable"), false);
               return false;
            }
         }
      }
   }

   public static boolean xboirziij2fbq(ServerWorld var0, x15kgoaq6i3m6i var1, UUID var2, BlockPos var3) {
      if (Math.hypot(var3.getX() - var1.core().getX(), var3.getZ() - var1.core().getZ()) + 11.36 > var1.radius()) {
         return false;
      } else if (Math.max(Math.abs(var3.getX() - var1.core().getX()), Math.abs(var3.getZ() - var1.core().getZ())) < 14) {
         return false;
      } else {
         x3o14q3v4oy99m var4 = new x3o14q3v4oy99m(var2, var1.id(), var3, 8);
         if (x3d5vk1zj3fvkj.get(var0).foreignBlocks(var4)) {
            return false;
         } else if (x3d5vk1zj3fvkj.get(var0)
            .land()
            .stream()
            .anyMatch(
               var1x -> Math.abs(var1x.x2kystgxl68xrn().getX() - var3.getX()) <= var1x.x3rgd0i6cz19eu() + 8
                  && Math.abs(var1x.x2kystgxl68xrn().getZ() - var3.getZ()) <= var1x.x3rgd0i6cz19eu() + 8
            )) {
            return false;
         } else {
            for (x15kgoaq6i3m6i var6 : x3d5vk1zj3fvkj.get(var0).colonies()) {
               if (var6.x1ja61emeln2hc().stream().anyMatch(var1x -> var4.intersects(var1x.x24kbqv6g4kkrg()))) {
                  return false;
               }
            }

            for (int var11 = -8; var11 <= 8; var11++) {
               for (int var12 = -8; var12 <= 8; var12++) {
                  BlockPos var7 = var3.add(var11, 0, var12);
                  if (!var0.getWorldBorder().contains(var7)) {
                     return false;
                  }

                  if (!var0.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)) {
                     return false;
                  }

                  int var8 = var0.getTopY(Type.MOTION_BLOCKING_NO_LEAVES, var7.getX(), var7.getZ()) - 1;
                  var7 = new BlockPos(var7.getX(), var8, var7.getZ());
                  if (x1ajta1wleukjn(var0, var7)
                     || var1.x29j0o9zdxri2().containsKey(var7)
                     || var0.getBlockState(var7).isOf(Blocks.FARMLAND)
                     || var0.getBlockState(var7).isOf(Blocks.BELL)
                     || xamz6pfj7qa87.x3hqfrterusj2u(var0, var7)) {
                     return false;
                  }

                  for (BlockPos var10 : BlockPos.iterate(var7.down(8), var7.up(8))) {
                     if (x3d5vk1zj3fvkj.get(var0).blockOwner(var10).filter(var1x -> !var1x.equals(var2)).isPresent() || var1.x29j0o9zdxri2().containsKey(var10)
                        )
                      {
                        return false;
                     }
                  }
               }
            }

            return true;
         }
      }
   }

   private static boolean x1ajta1wleukjn(ServerWorld var0, BlockPos var1) {
      return var0.getStructureAccessor()
         .getStructureStarts(new ChunkPos(var1), var0x -> true)
         .stream()
         .flatMap(var0x -> var0x.getChildren().stream())
         .anyMatch(
            var1x -> {
               BlockBox var2 = var1x.getBoundingBox();
               if (var1x instanceof PoolStructurePiece var3
                  && var1.getX() >= var2.getMinX()
                  && var1.getX() <= var2.getMaxX()
                  && var1.getZ() >= var2.getMinZ()
                  && var1.getZ() <= var2.getMaxZ()
                  && var3.getPoolElement() instanceof SinglePoolElement var4) {
                  try {
                     String var7 = var4.getIdOrThrow().getPath();
                     return var7.startsWith("village/")
                        && (var7.contains("/houses/") || var7.contains("/town_centers/") || var7.contains("/streets/") || var7.contains("meeting_point"));
                  } catch (IllegalStateException var6) {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         );
   }

   public static boolean x34sw32fdrc9v3(BlockState var0) {
      return var0.isFullCube(EmptyBlockView.INSTANCE, BlockPos.ORIGIN)
         || var0.getBlock() instanceof DoorBlock
         || var0.getBlock() instanceof BedBlock
         || var0.getBlock() instanceof ChestBlock
         || var0.getBlock() instanceof BarrelBlock
         || var0.getBlock() instanceof StairsBlock
         || var0.getBlock() instanceof SlabBlock;
   }

   public static void x273xo57gqmrz9(ServerWorld var0, PlayerEntity var1, BlockPos var2, BlockState var3) {
      if (!var1.isCreative() && !var1.isSpectator() && x34sw32fdrc9v3(var3) && !x2uh56vnzc4u51.xlzfxuhnckux8(var0, var1.getUuid(), var2)) {
         x15kgoaq6i3m6i var4 = x2n55y2tom37h2.x3psqezd5jem54(var0, var2).orElse(null);
         if (var4 != null) {
            if (!(var2.getSquaredDistance(var4.core()) > (double)Math.min(96, var4.radius()) * Math.min(96, var4.radius()))) {
               x3d5vk1zj3fvkj var5 = x3d5vk1zj3fvkj.get(var0);
               x3rldzj1ugn0ma var6 = xt33a2rbozpnr(var5).get(var1.getUuid());
               if (var6 != null && var6.x3r0wyaaw6oa4f.equals(var4.id()) && (var6.xba2llgzzadr8 < 0L || var0.getTime() - var6.xba2llgzzadr8 <= 24000L)) {
                  var6.x19bsssr8009ew = var2.toImmutable();
                  if (var6.xba2llgzzadr8 >= 0L && var0.getTime() - var6.xba2llgzzadr8 >= 600L) {
                     var6.x3o0ypcqec9o4n++;
                     if (var6.x3o0ypcqec9o4n >= 3) {
                        var6.x2z4teazzptlwj = true;
                        x37idyr79ed75m.xgnwo32dqwtgi(var0, var4, var1, 4);
                     }
                  }

                  var5.markDirty();
               } else {
                  xt33a2rbozpnr(var5).put(var1.getUuid(), new x3rldzj1ugn0ma(var1.getUuid(), var4.id(), var2));
                  var5.markDirty();
               }
            }
         }
      }
   }

   public static void x1w4hwyk1suofz(ServerWorld var0) {
      if (var0.getTime() % 20L == 0L) {
         x3d5vk1zj3fvkj var1 = x3d5vk1zj3fvkj.get(var0);
         if (Math.floorMod(var0.getTimeOfDay(), 24000L) < 12000L) {
            for (x3rldzj1ugn0ma var3 : new ArrayList<>(xt33a2rbozpnr(var1).values())) {
               if (var3.xba2llgzzadr8 < 0L) {
                  PlayerEntity var4 = var0.getPlayerByUuid(var3.x118py1lrhxh0v);
                  x15kgoaq6i3m6i var5 = var1.byId(var3.x3r0wyaaw6oa4f).orElse(null);
                  if (var4 != null && var5 != null && !var4.isSpectator()) {
                     VillagerEntity var6 = (VillagerEntity)var0.getEntitiesByType(
                           EntityType.VILLAGER,
                           x2n55y2tom37h2.x150ccxomrlozr(var0, var5),
                           var1x -> var1x.isAlive()
                              && !var1x.isBaby()
                              && !var1x.isSleeping()
                              && !var1x.hasCustomer()
                              && var5.roleOf(var1x.getUuid()) == x1rxiy9mtu5b3h.x1saccptp3i00w
                        )
                        .stream()
                        .findFirst()
                        .orElse(null);
                     if (var6 != null) {
                        if (var6.squaredDistanceTo(var4) > 16.0) {
                           x1c1r5c6a13gtl.x2wzylgizxltef(var0, var6, var4.getBlockPos(), 0.9);
                        } else {
                           ItemStack var7 = new ItemStack(Items.PAPER);
                           var7.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.smart_colonies.land_notice"));
                           var7.set(
                              DataComponentTypes.LORE,
                              new LoreComponent(
                                 List.of(
                                    Text.translatable("item.smart_colonies.land_notice.price", new Object[]{x1a5rxjbs73iva.x2vr85xzgtiacc(var5, 24, true)}),
                                    Text.translatable("item.smart_colonies.land_notice.warning")
                                 )
                              )
                           );
                           if (!var4.getInventory().insertStack(var7)) {
                              var4.dropItem(var7, false);
                           }

                           var4.sendMessage(Text.translatable("item.smart_colonies.land_notice.warning"), false);
                           var3.xba2llgzzadr8 = var0.getTime();
                           var1.markDirty();
                           x1c1r5c6a13gtl.x2fxoqmbs0henb(var6);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static boolean x20vp2osbvc2gk(ServerWorld var0, VillagerEntity var1, x15kgoaq6i3m6i var2) {
      if (var2.roleOf(var1.getUuid()) == x1rxiy9mtu5b3h.x1saccptp3i00w
         && !var1.isSleeping()
         && !var1.hasCustomer()
         && Math.floorMod(var0.getTimeOfDay(), 24000L) < 12000L
         && (var2.x3klfyffbbfab2() == Long.MIN_VALUE || var0.getTime() - var2.x3klfyffbbfab2() >= 200L)) {
         PlayerEntity var3 = xt33a2rbozpnr(x3d5vk1zj3fvkj.get(var0))
            .values()
            .stream()
            .filter(var1x -> var1x.x3r0wyaaw6oa4f.equals(var2.id()) && var1x.xba2llgzzadr8 < 0L)
            .map(var1x -> var0.getPlayerByUuid(var1x.x118py1lrhxh0v))
            .filter(Objects::nonNull)
            .filter(var1x -> !var1x.isSpectator() && var1x.getBlockPos().getSquaredDistance(var2.core()) <= (double)var2.radius() * var2.radius())
            .min(Comparator.comparingDouble(var1::squaredDistanceTo))
            .orElse(null);
         if (var3 == null) {
            return false;
         } else {
            var1.getBrain().doExclusively(xnssk2qgda4r6.x12qpuae7zsr5m);
            if (var1.squaredDistanceTo(var3) > 16.0) {
               x1c1r5c6a13gtl.x2wzylgizxltef(var0, var1, var3.getBlockPos(), 0.9);
            } else {
               x1c1r5c6a13gtl.x2fxoqmbs0henb(var1);
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static void x2xqw0zvyjpw3b(NbtCompound var0, x3d5vk1zj3fvkj var1) {
      NbtList var2 = new NbtList();
      xt33a2rbozpnr(var1).values().forEach(var1x -> {
         NbtCompound var2x = new NbtCompound();
         var2x.putString("player", var1x.x118py1lrhxh0v.toString());
         var2x.putString("colony", var1x.x3r0wyaaw6oa4f.toString());
         var2x.putLong("pos", var1x.x19bsssr8009ew.asLong());
         var2x.putLong("delivered", var1x.xba2llgzzadr8);
         var2x.putInt("continued", var1x.x3o0ypcqec9o4n);
         var2x.putBoolean("punished", var1x.x2z4teazzptlwj);
         var2.add(var2x);
      });
      var0.put("land_warnings", var2);
   }

   public static void x1vdpt6b5sksjj(NbtCompound var0, x3d5vk1zj3fvkj var1) {
      NbtList var2 = var0.getListOrEmpty("land_warnings");

      for (int var3 = 0; var3 < var2.size(); var3++) {
         try {
            NbtCompound var4 = var2.getCompoundOrEmpty(var3);
            x3rldzj1ugn0ma var5 = new x3rldzj1ugn0ma(
               UUID.fromString(var4.getString("player", "")), UUID.fromString(var4.getString("colony", "")), BlockPos.fromLong(var4.getLong("pos", 0L))
            );
            var5.xba2llgzzadr8 = var4.getLong("delivered", -1L);
            var5.x3o0ypcqec9o4n = Math.clamp((long)var4.getInt("continued", 0), 0, 3);
            var5.x2z4teazzptlwj = var4.getBoolean("punished", false);
            xt33a2rbozpnr(var1).put(var5.x118py1lrhxh0v, var5);
         } catch (IllegalArgumentException var6) {
         }
      }
   }
}
