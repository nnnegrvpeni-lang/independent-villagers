package net.smartcolonies.r;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public final class IronGolemAttackHelper {
   private static final RegistryKey<DamageType> xraqws2b0ihjx = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("smart_colonies", "golem_attack"));

   private IronGolemAttackHelper() {
   }

   public static double x2rmpu6d2tdks1(ItemStack var0, RegistryEntry<EntityAttribute> var1, double var2) {
      return ((AttributeModifiersComponent)var0.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT))
         .applyOperations(var1, var2, EquipmentSlot.MAINHAND);
   }

   public static float x3c7ytg0gca1m6(ServerWorld var0, LivingEntity var1, LivingEntity var2, DamageSource var3) {
      ItemStack var4 = var1.getMainHandStack();
      float var5 = (float)Math.max(1.0, x2rmpu6d2tdks1(var4, EntityAttributes.ATTACK_DAMAGE, 1.0));
      return EnchantmentHelper.getDamage(var0, var4, var2, var3, var5);
   }

   public static int x3jcrekq98bvm8(ItemStack var0) {
      double var1 = Math.max(0.1, x2rmpu6d2tdks1(var0, EntityAttributes.ATTACK_SPEED, 4.0));
      return Math.clamp((long)((int)Math.ceil(20.0 / var1)), 5, 200);
   }

   public static DamageSource xqzpg1xselqv5(ServerWorld var0, LivingEntity var1, Entity var2, DamageSource var3) {
      if (var2 instanceof PlayerEntity && !ColonyManager.x3psqezd5jem54(var0, var1.getBlockPos()).isEmpty()) {
         Reference var4 = var0.getRegistryManager().getOrThrow(RegistryKeys.DAMAGE_TYPE).getOrThrow(xraqws2b0ihjx);
         return new DamageSource(var4, var1);
      } else {
         return var3;
      }
   }
}
