package net.smartcolonies;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.smartcolonies.r.ColonyDiagnosticCommands;
import net.smartcolonies.r.ColonySensorTypes;
import net.smartcolonies.r.VillageChunkGenerateListener;
import net.smartcolonies.r.ColonyManager;
import net.smartcolonies.r.VillageChunkLoadListener;
import net.smartcolonies.r.ColonyConfig;
import net.smartcolonies.r.DeedItemHandler;
import net.smartcolonies.r.ColonyActivities;
import net.smartcolonies.r.DeedPropertyData;
import net.smartcolonies.r.ColonyMemoryModules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SmartColoniesMod implements ModInitializer {
   public static final String MOD_ID = "smart_colonies";
   public static final Logger LOGGER = LoggerFactory.getLogger("smart_colonies");

   public void onInitialize() {
      ColonyConfig.xhwm4ut4yzbjy();
      ColonyActivities.x1bu824b0po0bm();
      ColonyMemoryModules.x2ve9opbgxakpk();
      ColonySensorTypes.xyjj6h9u3nkbj();
      ColonyDiagnosticCommands.x32f59jmix8kb9();
      ServerTickEvents.END_WORLD_TICK.register((EndWorldTick)var0 -> {
         VillageChunkGenerateListener.x1f4i4km49zkmu(var0);
         ColonyManager.xhvlm4bwxgmtf(var0);
      });
      ServerTickEvents.END_SERVER_TICK.register(DeedPropertyData::x2pghlycghjdni);
      ServerChunkEvents.CHUNK_LOAD.register(VillageChunkLoadListener::xuo3itsmzrpvl);
      ServerChunkEvents.CHUNK_GENERATE.register(VillageChunkGenerateListener::x2yhjdk5vgl5bq);
      UseBlockCallback.EVENT.register((UseBlockCallback)(var0, var1, var2, var3) -> {
         if (!var1.isClient() && DeedItemHandler.x3ew3lrc2q3oxu(var0.getStackInHand(var2))) {
            DeedItemHandler.x1mzhkk1en9rl((ServerWorld)var1, var0, var2, var3.getBlockPos());
            return ActionResult.SUCCESS;
         } else {
            if (!var1.isClient() && var1.getBlockState(var3.getBlockPos()).isOf(Blocks.BELL)) {
               ColonyManager.x30py6w81fega5((ServerWorld)var1, var3.getBlockPos());
            }

            return ActionResult.PASS;
         }
      });
      LOGGER.info("Independent Villagers initialized for generated vanilla villages");
   }
}
