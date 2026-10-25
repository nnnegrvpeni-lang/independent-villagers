package net.smartcolonies.r;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;

public final class ColonyChatFeedback {
   private static final Map<VillagerEntity, VillagerIdleWorkTask> x3ciiv5lwkj918 = new WeakHashMap<>();

   private ColonyChatFeedback() {
   }

   private static VillagerIdleWorkTask xixsyawjjbup9(VillagerEntity var0) {
      return x3ciiv5lwkj918.computeIfAbsent(var0, var1 -> {
         VillagerIdleWorkTask var2 = new VillagerIdleWorkTask();
         var2.x36rgtts6rv2l8 = var0.getEntityWorld().getTime();
         return var2;
      });
   }

   public static void x2t98zlq4lu49t(VillagerEntity var0, String var1, int var2) {
      if (var2 > 0) {
         VillagerIdleWorkTask var3 = xixsyawjjbup9(var0);
         var3.x36rgtts6rv2l8 = var0.getEntityWorld().getTime();
         var3.x15462tujos2pg = 0L;
         var3.x1h5tqum4uq2e2 += var2;
         var3.x1f9nzaiq3dwmi = var1;
      }
   }

   public static void x1qd0mutwsr3sc(VillagerEntity var0, String var1) {
      xixsyawjjbup9(var0).x1f9nzaiq3dwmi = var1;
   }

   public static boolean x25cotfj45r525(ServerWorld var0, Colony var1, VillagerEntity var2, ItemStack var3) {
      int var4 = var3.getCount();
      String var5 = Registries.ITEM.getId(var3.getItem()).getPath();
      boolean var6 = ColonyManager.x2cl1crde2rnxy(var0, var1, var3);
      int var7 = var4 - var3.getCount();
      if (var7 > 0) {
         x2t98zlq4lu49t(var2, "доставка на склад", var7);
         xixsyawjjbup9(var2).x2hbwpgkuqkeiy += var7;
         xixsyawjjbup9(var2).x2zbcr7dag6qvf.merge(var5, (long)var7, Long::sum);
      } else if (var4 > 0) {
         x1qd0mutwsr3sc(var2, "склад заполнен");
      }

      return var6;
   }

   public static void x1m5qncn5p4tgc(ServerWorld var0, Colony var1, VillagerEntity var2) {
      if ((var0.getTime() + var2.getId()) % 20L == 0L) {
         VillagerIdleWorkTask var3 = xixsyawjjbup9(var2);
         long var4 = Math.floorMod(var0.getTimeOfDay(), 24000L);
         if (!var2.isSleeping() && var4 >= 1000L && var4 < 9000L && var1.roleOf(var2.getUuid()) != ColonyRole.x3r8p2tvihwpph) {
            List<Task<? super VillagerEntity>> var6 = var2.getBrain().getRunningTasks().stream().filter(var0x -> var0x.getClass().getName().startsWith("net.smartcolonies.")).toList();
            if (var6.isEmpty()) {
               var3.x15462tujos2pg = 0L;
            } else {
               var3.x15462tujos2pg += 20L;
               if (var3.x15462tujos2pg >= 1200L) {
                  if (!var6.stream().anyMatch(var0x -> var0x instanceof MinerExpeditionTask || var0x instanceof BuilderConstructionTask)) {
                     ColonyNavigationHelper.xi17ogl3h66v0(var2, var0.getTime());

                     for (Task<? super VillagerEntity> var8 : var6) {
                        var8.stop(var0, var2, var0.getTime());
                     }

                     var3.x15462tujos2pg = 0L;
                     var3.xq8h0uz9ud1id++;
                     var3.x1f9nzaiq3dwmi = "нет результата 60 с: повторный выбор работы";
                     VillagerStuckHelper.x1feket2ghytw5(var2, var0.getTime() + 100L);
                  }
               }
            }
         } else {
            var3.x15462tujos2pg = 0L;
         }
      }
   }

   public static String xnmb1jjez0ytg(VillagerEntity var0) {
      VillagerIdleWorkTask var1 = xixsyawjjbup9(var0);
      return var1.x1f9nzaiq3dwmi
         + ", actions="
         + var1.x1h5tqum4uq2e2
         + ", delivered="
         + var1.x2hbwpgkuqkeiy
         + ", cargo="
         + var1.x2zbcr7dag6qvf
         + ", idle="
         + Math.max(0L, var0.getEntityWorld().getTime() - var1.x36rgtts6rv2l8) / 20L
         + "s, retries="
         + var1.xq8h0uz9ud1id;
   }

   public static long x2m7mdj9eld89j(VillagerEntity var0) {
      return xixsyawjjbup9(var0)
         .x2zbcr7dag6qvf
         .entrySet()
         .stream()
         .filter(var0x -> var0x.getKey().startsWith("raw_") || Set.of("coal", "diamond", "emerald", "redstone", "lapis_lazuli").contains(var0x.getKey()))
         .mapToLong(Entry::getValue)
         .sum();
   }

   public static boolean x1z41ec22rzeo2(VillagerEntity var0, long var1) {
      return var0.getEntityWorld().getTime() - xixsyawjjbup9(var0).x36rgtts6rv2l8 >= var1;
   }
}
