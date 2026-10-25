package net.smartcolonies.r;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public final class ColonyMemoryModules {
   private static final Codec<ColonyRole> x3jkqdj6uxtk52 = Codec.STRING.xmap(ColonyRole::byName, ColonyRole::serializedName);
   public static final MemoryModuleType<ColonyRole> x3ecoc0soedv0m = x23e6ofgmfaayg("colony_role", x3jkqdj6uxtk52);
   public static final MemoryModuleType<BlockPos> x1r15huley8x9h = x23e6ofgmfaayg("build_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockPos> x1h4kjv4hra8oe = x23e6ofgmfaayg("tree_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockPos> x2bwom1pasl6g5 = x23e6ofgmfaayg("farm_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockPos> x15kki2mjvh7tw = x23e6ofgmfaayg("mine_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockPos> x263ucjykllb1b = x23e6ofgmfaayg("fish_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockPos> x2ppem7wipzctm = x23e6ofgmfaayg("animal_target_pos", BlockPos.CODEC);
   public static final MemoryModuleType<BlockBox> x2ooa9y9romfh = x23e6ofgmfaayg("current_structure_bounds", BlockBox.CODEC);
   public static final MemoryModuleType<BlockPos> x3o547clcv9kaw = x23e6ofgmfaayg("storage_chest_pos", BlockPos.CODEC);
   public static final MemoryModuleType<LivingEntity> x14b5znl8ndblc = x1xgu987j7gij9("threat_target");

   private ColonyMemoryModules() {
   }

   public static void x2ve9opbgxakpk() {
   }

   public static List<MemoryModuleType<?>> x1eiihmuh1mj7p() {
      return List.of(
         x3ecoc0soedv0m,
         x1r15huley8x9h,
         x1h4kjv4hra8oe,
         x2bwom1pasl6g5,
         x15kki2mjvh7tw,
         x263ucjykllb1b,
         x2ppem7wipzctm,
         x2ooa9y9romfh,
         x3o547clcv9kaw,
         x14b5znl8ndblc
      );
   }

   private static <T> MemoryModuleType<T> x23e6ofgmfaayg(String var0, Codec<T> var1) {
      return (MemoryModuleType<T>)Registry.register(
         Registries.MEMORY_MODULE_TYPE, Identifier.of("smart_colonies", var0), new MemoryModuleType(Optional.of(var1))
      );
   }

   private static <T> MemoryModuleType<T> x1xgu987j7gij9(String var0) {
      return (MemoryModuleType<T>)Registry.register(
         Registries.MEMORY_MODULE_TYPE, Identifier.of("smart_colonies", var0), new MemoryModuleType(Optional.empty())
      );
   }
}
