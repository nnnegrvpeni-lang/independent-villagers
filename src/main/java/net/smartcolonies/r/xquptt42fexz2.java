package net.smartcolonies.r;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public final class xquptt42fexz2 {
   private static final String x1g39vuwea1vgp = "smart_colonies_property_level";
   private static final String x1n3en8rys61rg = "smart_colonies_property_used";
   private static final String x2jw57s5wlcclv = "smart_colonies_property_job";
   private static final String x3vj699b9gonpt = "smart_colonies_property_colony";
   private static final String x3o9nnjfl2vrxc = "smart_colonies_property_dimension";
   private static final String x1dcmqcpdfz12n = "smart_colonies_property_origin";
   private static final String x2n5oznh3c02l0 = "smart_colonies_property_complete";

   private xquptt42fexz2() {
   }

   public static ItemStack xhgw7talm1sp8(int var0) {
      int var1 = Math.clamp((long)var0, 1, 3);
      ItemStack var2 = new ItemStack(Items.PAPER);
      var2.set(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.smart_colonies.property_deed"));
      var2.set(
         DataComponentTypes.LORE,
         new LoreComponent(List.of(Text.translatable(var1 == 1 ? "item.smart_colonies.property_deed.small" : "item.smart_colonies.property_deed.medium")))
      );
      NbtCompound var3 = new NbtCompound();
      var3.putInt("smart_colonies_property_level", var1);
      var2.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var3));
      return var2;
   }

   public static ItemStack x2qg2bi4tzny42(ServerWorld var0, x15kgoaq6i3m6i var1, int var2) {
      ItemStack var3 = xhgw7talm1sp8(var2);
      NbtCompound var4 = ((NbtComponent)var3.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
      var4.putString("smart_colonies_property_colony", var1.id().toString());
      var4.putString("smart_colonies_property_dimension", var0.getRegistryKey().getValue().toString());
      var3.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var4));
      return var3;
   }

   public static int x3kj2ljyx6tv0(ItemStack var0) {
      return x11oga8h5115u7(var0) ? 0 : xau920lx57wg3(var0);
   }

   public static int xau920lx57wg3(ItemStack var0) {
      if (!var0.isOf(Items.PAPER)) {
         return 0;
      } else {
         NbtComponent var1 = (NbtComponent)var0.get(DataComponentTypes.CUSTOM_DATA);
         return var1 == null ? 0 : Math.clamp((long)var1.copyNbt().getInt("smart_colonies_property_level", 0), 0, 3);
      }
   }

   public static boolean x11oga8h5115u7(ItemStack var0) {
      if (!var0.isOf(Items.PAPER)) {
         return false;
      } else {
         NbtComponent var1 = (NbtComponent)var0.get(DataComponentTypes.CUSTOM_DATA);
         if (var1 == null) {
            return false;
         } else {
            NbtCompound var2 = var1.copyNbt();
            return var2.getBoolean("smart_colonies_property_used", false) || !var2.getString("smart_colonies_property_job", "").isEmpty();
         }
      }
   }

   public static Optional<x2arpn38dclo1g> x2l483kg9fflzs(ServerWorld var0, x15kgoaq6i3m6i var1, PlayerEntity var2, Hand var3) {
      ItemStack var4 = var2.getStackInHand(var3);
      int var5 = x3kj2ljyx6tv0(var4);
      if (var5 == 0) {
         return Optional.empty();
      } else {
         NbtCompound var6 = ((NbtComponent)var4.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
         if (var6.getString("smart_colonies_property_colony", "").isEmpty()
            || var6.getString("smart_colonies_property_colony", "").equals(var1.id().toString())
               && var6.getString("smart_colonies_property_dimension", "").equals(var0.getRegistryKey().getValue().toString())) {
            Optional var7 = x2n55y2tom37h2.x2fxdvzecolqll(var0, var1, var2.getUuid(), var5);
            if (var7.isEmpty()) {
               return var7;
            } else {
               ItemStack var8 = var4.copyWithCount(1);
               NbtCompound var9 = ((NbtComponent)var8.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
               var9.putBoolean("smart_colonies_property_used", true);
               var9.putString("smart_colonies_property_job", ((x2arpn38dclo1g)var7.get()).id().toString());
               var9.putString("smart_colonies_property_colony", var1.id().toString());
               var9.putString("smart_colonies_property_dimension", var0.getRegistryKey().getValue().toString());
               var9.putLong("smart_colonies_property_origin", ((x2arpn38dclo1g)var7.get()).x3ij6gescvakv2().asLong());
               var9.putBoolean("smart_colonies_property_complete", false);
               var8.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var9));
               x1wadvar5cjjlm(var8, var9);
               var4.decrement(1);
               var2.setStackInHand(var3, var8);
               if (!var4.isEmpty() && !var2.getInventory().insertStack(var4)) {
                  var2.dropItem(var4, false);
               }

               return var7;
            }
         } else {
            return Optional.empty();
         }
      }
   }

   public static void x3000fumbeekrm(MinecraftServer var0, ItemStack var1) {
      if (x11oga8h5115u7(var1)) {
         NbtCompound var2 = ((NbtComponent)var1.get(DataComponentTypes.CUSTOM_DATA)).copyNbt();
         Identifier var3 = Identifier.tryParse(var2.getString("smart_colonies_property_dimension", ""));
         if (var3 != null) {
            ServerWorld var4 = var0.getWorld(RegistryKey.of(RegistryKeys.WORLD, var3));
            if (var4 != null) {
               try {
                  x2n55y2tom37h2.xchl1k1tnh3w8(var4)
                     .byId(UUID.fromString(var2.getString("smart_colonies_property_colony", "")))
                     .ifPresent(
                        var2x -> var2x.x1ja61emeln2hc()
                           .stream()
                           .filter(var1xx -> var1xx.id().toString().equals(var2.getString("smart_colonies_property_job", "")))
                           .findFirst()
                           .ifPresent(var2xx -> {
                              if (var2xx.x2s8emavervqrz() && !var2.getBoolean("smart_colonies_property_complete", false)) {
                                 var2.putBoolean("smart_colonies_property_complete", true);
                                 var1.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(var2));
                              }

                              x1wadvar5cjjlm(var1, var2);
                           })
                     );
               } catch (IllegalArgumentException var6) {
               }
            }
         }
      }
   }

   private static void x1wadvar5cjjlm(ItemStack var0, NbtCompound var1) {
      BlockPos var2 = BlockPos.fromLong(var1.getLong("smart_colonies_property_origin", 0L));
      MutableText var3 = Text.translatable(
         var1.getBoolean("smart_colonies_property_complete", false)
            ? "item.smart_colonies.property_deed.complete"
            : "item.smart_colonies.property_deed.building",
         new Object[]{var2.toShortString()}
      );
      if (!var3.equals(var0.get(DataComponentTypes.CUSTOM_NAME))) {
         var0.set(DataComponentTypes.CUSTOM_NAME, var3);
      }

      LoreComponent var4 = new LoreComponent(
         List.of(
            Text.translatable(xau920lx57wg3(var0) == 1 ? "item.smart_colonies.property_deed.small" : "item.smart_colonies.property_deed.medium"),
            Text.translatable("item.smart_colonies.property_deed.used")
         )
      );
      if (!var4.equals(var0.get(DataComponentTypes.LORE))) {
         var0.set(DataComponentTypes.LORE, var4);
      }
   }

   public static void x2cidrexyzeol0(MinecraftServer var0, Inventory var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         x3000fumbeekrm(var0, var1.getStack(var2));
      }
   }

   public static void x2pghlycghjdni(MinecraftServer var0) {
      if (var0.getTicks() % 20 == 0) {
         for (ServerPlayerEntity var2 : var0.getPlayerManager().getPlayerList()) {
            x2cidrexyzeol0(var0, var2.getInventory());
            x2cidrexyzeol0(var0, var2.getEnderChestInventory());

            for (Slot var4 : var2.currentScreenHandler.slots) {
               x3000fumbeekrm(var0, var4.getStack());
            }

            x3000fumbeekrm(var0, var2.currentScreenHandler.getCursorStack());
         }
      }
   }
}
