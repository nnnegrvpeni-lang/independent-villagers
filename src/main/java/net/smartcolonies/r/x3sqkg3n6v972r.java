package net.smartcolonies.r;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import net.fabricmc.loader.api.FabricLoader;
import net.smartcolonies.SmartColoniesMod;

public final class x3sqkg3n6v972r {
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
   private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("smart_colonies.json");
   private static x3sqkg3n6v972r instance = new x3sqkg3n6v972r();
   public x3o0tuxsltwqoc village = new x3o0tuxsltwqoc();
   public x33ctntx6c92ol roles = new x33ctntx6c92ol();
   public x2191fqc0b3okh debug = new x2191fqc0b3okh();
   public x1btzvvc0mosh8 work = new x1btzvvc0mosh8();
   public int config_version = 2;

   public static x3sqkg3n6v972r x2by5f9ehxvwk9() {
      return instance;
   }

   public static void xhwm4ut4yzbjy() {
      x2ce1ihtn5bm4e(PATH);
   }

   public static void x2ce1ihtn5bm4e(Path var0) {
      x3sqkg3n6v972r var1 = new x3sqkg3n6v972r();

      try {
         Files.createDirectories(var0.toAbsolutePath().getParent());
         if (Files.exists(var0)) {
            try (BufferedReader var2 = Files.newBufferedReader(var0)) {
               x3sqkg3n6v972r var24 = (x3sqkg3n6v972r)GSON.fromJson(var2, x3sqkg3n6v972r.class);
               if (var24 == null) {
                  throw new JsonParseException("Empty config");
               }

               var1 = var24;
            } catch (IllegalStateException | JsonParseException var21) {
               Path var3 = var0.resolveSibling(var0.getFileName() + ".invalid-" + UUID.randomUUID() + ".bak");
               Files.copy(var0, var3);
               SmartColoniesMod.LOGGER.error("Invalid config {}; original preserved at {}; using defaults", new Object[]{var0, var3, var21});
            }
         }

         var1.xdnwoyh6tviyw();
         instance = var1;
         Path var23 = Files.createTempFile(var0.toAbsolutePath().getParent(), "smart-colonies-", ".tmp");

         try {
            try (BufferedWriter var25 = Files.newBufferedWriter(var23)) {
               GSON.toJson(instance, var25);
            }

            try {
               Files.move(var23, var0, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException var16) {
               Files.move(var23, var0, StandardCopyOption.REPLACE_EXISTING);
            }
         } finally {
            Files.deleteIfExists(var23);
         }
      } catch (JsonParseException | IOException var22) {
         var1.xdnwoyh6tviyw();
         instance = var1;
         SmartColoniesMod.LOGGER.error("Could not load/write {}; using validated settings without replacing the original", var0, var22);
      }
   }

   private void xdnwoyh6tviyw() {
      if (this.village == null) {
         this.village = new x3o0tuxsltwqoc();
      }

      if (this.roles == null) {
         this.roles = new x33ctntx6c92ol();
      }

      if (this.debug == null) {
         this.debug = new x2191fqc0b3okh();
      }

      if (this.work == null) {
         this.work = new x1btzvvc0mosh8();
      }

      if (this.config_version < 2) {
         this.roles.golems_break_attacker_pillars = true;
         this.config_version = 2;
      }

      this.config_version = Math.max(2, this.config_version);
      this.village.max_population_per_bell = Math.clamp((long)this.village.max_population_per_bell, 2, 200);
      this.village.expansion_radius_max = Math.clamp((long)this.village.expansion_radius_max, 96, 512);
      this.village.max_constructed_houses = Math.clamp((long)this.village.max_constructed_houses, 4, 200);
      this.village.build_delay_ticks = Math.clamp((long)this.village.build_delay_ticks, 2, 100);
      this.village.offline_max_days = Math.clamp((long)this.village.offline_max_days, 0, 30);
      this.village.offline_blocks_per_tick = Math.clamp((long)this.village.offline_blocks_per_tick, 1, 32);
      this.village.offline_arrival_blocks_per_tick = Math.clamp((long)this.village.offline_arrival_blocks_per_tick, 32, 1024);
      this.work.minimum_break_ticks = Math.clamp((long)this.work.minimum_break_ticks, 4, 100);
      this.work.placement_ticks = Math.clamp((long)this.work.placement_ticks, 4, 100);
      this.work.farming_ticks = Math.clamp((long)this.work.farming_ticks, 4, 100);
      this.work.deep_mining_ticks = Math.clamp((long)this.work.deep_mining_ticks, 400, 12000);
   }
}
