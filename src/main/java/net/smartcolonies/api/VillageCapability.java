package net.smartcolonies.api;

import java.util.UUID;
import net.minecraft.util.math.BlockPos;
import net.smartcolonies.r.x1rxiy9mtu5b3h;

public interface VillageCapability {
   UUID id();

   BlockPos core();

   int radius();

   int population();

   int beds();

   int cachedFood();

   int cachedLogs();

   int cachedStone();

   x1rxiy9mtu5b3h roleOf(UUID var1);
}
