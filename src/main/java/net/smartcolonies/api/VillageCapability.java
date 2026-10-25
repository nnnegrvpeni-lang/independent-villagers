package net.smartcolonies.api;

import java.util.UUID;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.r.ColonyRole;

public interface VillageCapability {
   UUID id();

   BlockPos core();

   int radius();

   int population();

   int beds();

   int cachedFood();

   int cachedLogs();

   int cachedStone();

   ColonyRole roleOf(UUID var1);
}
