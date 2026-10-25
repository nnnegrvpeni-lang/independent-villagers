package net.smartcolonies.r;

import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.smartcolonies.mixin.SensorTypeInvoker;

public final class ColonySensorTypes {
   public static final SensorType<ColonyNearbySensor> x38iq4tihdy4v8 = (SensorType<ColonyNearbySensor>)Registry.register(
      Registries.SENSOR_TYPE, Identifier.of("smart_colonies", "village_storage"), SensorTypeInvoker.x3nufuz5z25vn0(ColonyNearbySensor::new)
   );

   private ColonySensorTypes() {
   }

   public static void xyjj6h9u3nkbj() {
   }
}
