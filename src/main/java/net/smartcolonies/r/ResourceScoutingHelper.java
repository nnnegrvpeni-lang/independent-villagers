package net.smartcolonies.r;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap.Type;

public final class ResourceScoutingHelper {
   private static final Map<Colony, ModelPartHelper> x1bniuvikkeslx = new WeakHashMap<>();

   private ResourceScoutingHelper() {
   }

   public static void x1cgirut0dfx4w(ServerWorld var0, Colony var1) {
      ModelPartHelper var2 = x1bniuvikkeslx.computeIfAbsent(var1, var0x -> new ModelPartHelper());
      if (var2.x1o4o8xboni3e8 == Long.MIN_VALUE) {
         var2.x1o4o8xboni3e8 = var0.getTime();
         var2.xlbk4c97ghjy = var1.cachedFood();
         var2.xrosgbbvdp82b = var1.cachedLogs();
         var2.x1yo2g8rhr59ei = var1.cachedStone();
      }

      if (var0.getTime() - var2.x1o4o8xboni3e8 >= 600L) {
         var2.x3nmfccx25rnli = var1.cachedFood() - var2.xlbk4c97ghjy;
         var2.x13n7yetjru0p4 = var1.cachedLogs() - var2.xrosgbbvdp82b;
         var2.xi51lceq0w0wj = var1.cachedStone() - var2.x1yo2g8rhr59ei;
         var2.xlbk4c97ghjy = var1.cachedFood();
         var2.xrosgbbvdp82b = var1.cachedLogs();
         var2.x1yo2g8rhr59ei = var1.cachedStone();
         var2.x1o4o8xboni3e8 = var0.getTime();
      }

      int var3 = Math.min(96, var1.radius() - 8);
      int var4 = var3 / 4 + 1;
      int var5 = var4 * var4;
      if (var2.x18cylnq6b3mht >= var5) {
         var2.x18cylnq6b3mht = 0;
         var2.xpfb01pmvz35k = 0;
         var2.x3flapherkr2s2 = var2.x15em0agwht2le = var2.x219nexa31oys0 = 0;
      }

      for (int var6 = 0; var6 < 64 && var2.x18cylnq6b3mht < var5; var2.x18cylnq6b3mht++) {
         int var7 = var1.core().getX() - var3 + var2.x18cylnq6b3mht % var4 * 8;
         int var8 = var1.core().getZ() - var3 + var2.x18cylnq6b3mht / var4 * 8;
         if (var0.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
            var2.xpfb01pmvz35k++;
            BlockPos var9 = new BlockPos(var7, var0.getTopY(Type.MOTION_BLOCKING, var7, var8) - 1, var8);
            BlockState var10 = var0.getBlockState(var9);
            if (var10.isIn(BlockTags.LEAVES) || var10.isIn(BlockTags.LOGS)) {
               var2.x3flapherkr2s2++;
            }

            if (var10.isOf(Blocks.WATER) && var9.getSquaredDistance(var1.core().withY(var9.getY())) <= 2304.0) {
               var2.x15em0agwht2le++;
            }

            if (var10.isIn(BlockTags.DIRT) || var10.isOf(Blocks.FARMLAND)) {
               var2.x219nexa31oys0++;
            }
         }

         var6++;
      }

      if (var2.x18cylnq6b3mht >= var5) {
         var2.xk34jfx0ry30w = var2.xpfb01pmvz35k == var5;
         var2.xn8fkkfabhplq = var2.x3flapherkr2s2;
         var2.xn6juy1i2cuj8 = var2.x15em0agwht2le;
         var2.x207nyec47ataq = var2.x219nexa31oys0;
      }

      if (var2.x2cfskbx6c4wj9 == Long.MIN_VALUE || var0.getTime() - var2.x2cfskbx6c4wj9 >= 600L) {
         var2.x2uy4yads13li4 = var0.getEntitiesByClass(AnimalEntity.class, ColonyManager.x150ccxomrlozr(var0, var1), var0x -> var0x.isAlive()).size();
         var2.x2cfskbx6c4wj9 = var0.getTime();
      }
   }

   public static void x2hptx3oklbfn2(Colony var0, EnumMap<ColonyRole, Integer> var1) {
      ModelPartHelper var2 = x1bniuvikkeslx.get(var0);
      if (var2 != null) {
         boolean var3 = var0.cachedFood() < Math.max(48, var0.population() * 8);
         if (var2.xk34jfx0ry30w) {
            if (var2.xn8fkkfabhplq == 0) {
               var1.put(ColonyRole.x2hwljbtybtlwc, Math.min(15, (Integer)var1.get(ColonyRole.x2hwljbtybtlwc)));
            }

            if (var2.xn6juy1i2cuj8 == 0) {
               var1.put(ColonyRole.xhds9crfjdp9i, 10);
            }

            if (var2.x207nyec47ataq == 0 && var0.x1hdthtpd2qb8g().isEmpty()) {
               var1.put(ColonyRole.x8jydaiei7iry, 15);
            }
         }

         if (var2.x2uy4yads13li4 < 2 && var0.x329rgb9lpsari().isEmpty()) {
            var1.put(ColonyRole.xeoehg87qwfrn, 10);
         }

         if (var3 && var2.x3nmfccx25rnli < 0) {
            for (ColonyRole var5 : List.of(ColonyRole.x8jydaiei7iry, ColonyRole.xhds9crfjdp9i, ColonyRole.xeoehg87qwfrn)) {
               if ((Integer)var1.get(var5) > 15) {
                  var1.put(var5, Math.min(100, (Integer)var1.get(var5) + 15));
               }
            }
         }

         if (var0.cachedLogs() < 32 && var2.x13n7yetjru0p4 < 0 && (Integer)var1.get(ColonyRole.x2hwljbtybtlwc) > 15) {
            var1.put(ColonyRole.x2hwljbtybtlwc, 100);
         }

         if (var0.cachedStone() < 96 && var2.xi51lceq0w0wj < 0) {
            var1.put(ColonyRole.x18g4mqx9kxs89, 100);
         }

         if (ColonyConfig.x2by5f9ehxvwk9().roles.enable_village_walls && !var0.x2f7z1gnnht5ab().x1q0gb1h69ywu7.isEmpty()) {
            var1.put(ColonyRole.x35csp4lojh59r, Math.max(60, (Integer)var1.get(ColonyRole.x35csp4lojh59r)));
            if (var0.cachedStone() < 256) {
               var1.put(ColonyRole.x18g4mqx9kxs89, Math.max(85, (Integer)var1.get(ColonyRole.x18g4mqx9kxs89)));
            }
         }
      }
   }

   public static boolean x2hrr4jjriuh85(ServerWorld var0, Colony var1, VillagerEntity var2, long var3) {
      if (!var2.isSleeping() && !var2.hasCustomer() && !VillagerRaidShelterTask.active(var0, var1) && !var1.x3hurh64xwtw51(var2.getUuid())) {
         if (var3 - var1.x2bp8u9p8dnt9e(var2.getUuid()) < 2400L) {
            return false;
         } else if (var1.x1ja61emeln2hc()
            .stream()
            .anyMatch(
               var1x -> (var1x.x32oz43hmeynhc() == ConstructionStatus.x2tgnsmc98zm7p || var1x.x32oz43hmeynhc() == ConstructionStatus.x1mzkgtgadv3jh)
                  && var1x.x3vu8mnvg0blk4().filter(var2.getUuid()::equals).isPresent()
            )) {
            return false;
         } else {
            boolean var5 = var2.getBrain().getRunningTasks().stream().noneMatch(var0x -> var0x.getClass().getName().startsWith("net.smartcolonies."))
               || ColonyChatFeedback.x1z41ec22rzeo2(var2, 600L) && var2.getNavigation().isIdle();
            int var6 = 0;

            for (int var7 = 0; var7 < var2.getInventory().size(); var7++) {
               ItemStack var8 = var2.getInventory().getStack(var7);
               if (var8.isIn(ItemTags.LOGS)
                  || var8.isIn(ItemTags.STONE_CRAFTING_MATERIALS)
                  || Registries.ITEM.getId(var8.getItem()).getPath().startsWith("raw_")) {
                  var6 += var8.getCount();
               }
            }

            return var5 && var6 <= (var1.roleOf(var2.getUuid()) == ColonyRole.x18g4mqx9kxs89 ? 16 : 0);
         }
      } else {
         return false;
      }
   }

   public static void xw6l4sc9khw02(Colony var0, VillagerEntity var1, long var2) {
      var0.xvamv2rzn6h0g(var1.getUuid(), var2);
   }

   public static String x32tk2361rdt82(Colony var0) {
      ModelPartHelper var1 = x1bniuvikkeslx.get(var0);
      return var1 == null
         ? "разведка не начата"
         : "разведка="
            + (var1.xk34jfx0ry30w ? "готова" : "идёт")
            + ", лес="
            + var1.xn8fkkfabhplq
            + ", вода="
            + var1.xn6juy1i2cuj8
            + ", почва="
            + var1.x207nyec47ataq
            + ", животных="
            + var1.x2uy4yads13li4
            + ", изменение запасов еды/дерева/камня за 30с="
            + var1.x3nmfccx25rnli
            + "/"
            + var1.x13n7yetjru0p4
            + "/"
            + var1.xi51lceq0w0wj;
   }
}
