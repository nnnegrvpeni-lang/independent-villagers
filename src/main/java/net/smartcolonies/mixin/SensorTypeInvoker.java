package net.smartcolonies.mixin;

import java.util.function.Supplier;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({SensorType.class})
public interface SensorTypeInvoker {
   @Invoker("<init>")
   static <U extends Sensor<?>> SensorType<U> x3nufuz5z25vn0(Supplier<U> var0) {
      throw new AssertionError("Mixin invoker was not applied");
   }
}
