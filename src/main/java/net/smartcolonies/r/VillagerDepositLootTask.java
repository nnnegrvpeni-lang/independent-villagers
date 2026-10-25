package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public final class VillagerDepositLootTask extends MultiTickTask<VillagerEntity> {
   private static final Predicate<ItemStack> xom517cw0um4v = var0 -> var0.isOf(Items.IRON_INGOT);
   private static final Predicate<ItemStack> x34u6jjh5a935g = var0 -> var0.isOf(Items.DIAMOND);
   private static final Predicate<ItemStack> x194844gl7dagx = var0 -> var0.isIn(ItemTags.LOGS);
   private Colony x3hy0zpbzjwfng;
   private BlockPos xkzbncf5sbizi;
   private long xoq2gkiz7k5l2;
   private boolean x1lxqbh3uu8b29;
   private final IronGolemColonyHelper x1nptxhjn9u8rb = new IronGolemColonyHelper();

   public VillagerDepositLootTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 600);
   }

   public static boolean needsEquipment(ServerWorld var0, Colony var1, VillagerEntity var2) {
      if (x1oufdnvti1fyk(var2, var0x -> var0x.isOf(Items.BOW))
         && x1oufdnvti1fyk(var2, var0x -> var0x.isIn(ItemTags.SWORDS) || var0x.isIn(ItemTags.AXES))
         && var2.getOffHandStack().isOf(Items.SHIELD)) {
         for (EquipmentSlot var6 : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack var7 = var2.getEquippedStack(var6);
            if (var7.isEmpty() || !x313d952sywy3b(var7) && var1.x3peiu091351qg() >= x25z7tdvietcut(var6)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && var1.getTime() >= this.xoq2gkiz7k5l2) {
         Optional var3 = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos()).filter(var0 -> !var0.x2ad3s8aentmo4().isEmpty()).filter(var2x -> {
            ColonyRole var3x = var2x.roleOf(var2.getUuid());
            return var3x == ColonyRole.x3r8p2tvihwpph && (needsEquipment(var1, var2x, var2) || IronGolemColonyHelper.x3pk6ywniogk9m(var1, var2x));
         });
         if (var3.isEmpty()) {
            return false;
         } else {
            this.x3hy0zpbzjwfng = (Colony)var3.get();
            this.xkzbncf5sbizi = ColonyManager.x1falh21nhy4yk(var1, this.x3hy0zpbzjwfng, var2.getBlockPos()).orElse(null);
            this.x1lxqbh3uu8b29 = false;
            this.x1nptxhjn9u8rb.x3ku3wcoma0qbb();
            return this.xkzbncf5sbizi != null;
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x3hy0zpbzjwfng != null
         && this.x3hy0zpbzjwfng.roleOf(var2.getUuid()) == ColonyRole.x3r8p2tvihwpph
         && this.xkzbncf5sbizi != null
         && !this.x1lxqbh3uu8b29;
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      var2.getBrain().remember(ColonyMemoryModules.x3o547clcv9kaw, this.xkzbncf5sbizi);
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (this.x1nptxhjn9u8rb.xhboo89lslsdy()) {
         this.x1lxqbh3uu8b29 = this.x1nptxhjn9u8rb.x21yp5lsgxd9co(var1, this.x3hy0zpbzjwfng, var2, var3);
         if (this.x1lxqbh3uu8b29) {
            this.xoq2gkiz7k5l2 = var3 + 40L;
         }
      } else if (var2.squaredDistanceTo(this.xkzbncf5sbizi.getX() + 0.5, this.xkzbncf5sbizi.getY() + 0.5, this.xkzbncf5sbizi.getZ() + 0.5) > 9.0) {
         ColonyNavigationHelper.x2wzylgizxltef(
            var1, var2, BlockPos.ofFloored(this.xkzbncf5sbizi.getX() + 0.5, this.xkzbncf5sbizi.getY(), this.xkzbncf5sbizi.getZ() + 0.5), 0.65
         );
      } else if (!this.x1nptxhjn9u8rb.xl4620yflyi80(var1, this.x3hy0zpbzjwfng, var2)) {
         boolean var5 = this.x3hy0zpbzjwfng.roleOf(var2.getUuid()) == ColonyRole.x3r8p2tvihwpph
            && (this.xarpn7hhhzvxe(var1, var2) || this.xlzguy5xco81e(var1, var2));
         if (var5) {
            var2.swingHand(Hand.MAIN_HAND);
         }

         this.x1lxqbh3uu8b29 = true;
         this.xoq2gkiz7k5l2 = var3 + (var5 ? 40 : 200);
      }
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      var2.getNavigation().stop();
      this.x3hy0zpbzjwfng = null;
      this.xkzbncf5sbizi = null;
      this.x1nptxhjn9u8rb.x3ku3wcoma0qbb();
   }

   private boolean xarpn7hhhzvxe(ServerWorld var1, VillagerEntity var2) {
      if (!x1oufdnvti1fyk(var2, var0 -> var0.isOf(Items.BOW))) {
         Optional var3 = ColonyManager.x3lxwdirnn77qc(var1, this.x3hy0zpbzjwfng, var0 -> var0.isOf(Items.BOW));
         if (var3.isPresent()) {
            return this.x3pwsh2mdq6w1j(var1, var2, (ItemStack)var3.get());
         }

         if (this.x2a5t34vw8m1l3(var1, var0 -> var0.isOf(Items.STRING), 3, x194844gl7dagx, 1)
            || ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, x194844gl7dagx, 4)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new ItemStack(Items.BOW));
         }
      }

      if (!x1oufdnvti1fyk(var2, var0 -> var0.isIn(ItemTags.SWORDS) || var0.isIn(ItemTags.AXES))) {
         Optional var4 = ColonyManager.x3lxwdirnn77qc(
            var1, this.x3hy0zpbzjwfng, var0 -> var0.isOf(Items.DIAMOND_SWORD) || var0.isOf(Items.IRON_SWORD) || var0.isIn(ItemTags.AXES)
         );
         if (var4.isPresent()) {
            return this.x3pwsh2mdq6w1j(var1, var2, (ItemStack)var4.get());
         }

         if (this.x2a5t34vw8m1l3(var1, x34u6jjh5a935g, 2, x194844gl7dagx, 1)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new ItemStack(Items.DIAMOND_SWORD));
         }

         VillagerBucketRefillHelper.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, 2);
         if (this.x2a5t34vw8m1l3(var1, xom517cw0um4v, 2, x194844gl7dagx, 1)) {
            return this.x3pwsh2mdq6w1j(var1, var2, new ItemStack(Items.IRON_SWORD));
         }
      }

      if (!var2.getOffHandStack().isOf(Items.SHIELD)) {
         Optional var5 = ColonyManager.x3lxwdirnn77qc(var1, this.x3hy0zpbzjwfng, var0 -> var0.isOf(Items.SHIELD));
         if (var5.isPresent()) {
            this.x1d7s430m0tpuj(var1, var2, EquipmentSlot.OFFHAND);
            var2.equipStack(EquipmentSlot.OFFHAND, (ItemStack)var5.get());
            return true;
         }

         VillagerBucketRefillHelper.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, 1);
         if (this.x2a5t34vw8m1l3(var1, xom517cw0um4v, 1, x194844gl7dagx, 6)) {
            this.x1d7s430m0tpuj(var1, var2, EquipmentSlot.OFFHAND);
            var2.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
            return true;
         }
      }

      return false;
   }

   private boolean x3pwsh2mdq6w1j(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      if (!x3ur57yzm9k1a(var2.getMainHandStack())) {
         this.x1d7s430m0tpuj(var1, var2, EquipmentSlot.MAINHAND);
         var2.equipStack(EquipmentSlot.MAINHAND, var3);
         return true;
      } else {
         ItemStack var4 = var2.getInventory().addStack(var3);
         if (!var4.isEmpty()) {
            VillagerBucketRefillHelper.xqe8twcjwxn55(var1, this.x3hy0zpbzjwfng, var2, var4);
         }

         return true;
      }
   }

   private boolean xlzguy5xco81e(ServerWorld var1, VillagerEntity var2) {
      EquipmentDemand[] var3 = new EquipmentDemand[]{
         new EquipmentDemand(EquipmentSlot.CHEST, Items.DIAMOND_CHESTPLATE, Items.IRON_CHESTPLATE, 8),
         new EquipmentDemand(EquipmentSlot.LEGS, Items.DIAMOND_LEGGINGS, Items.IRON_LEGGINGS, 7),
         new EquipmentDemand(EquipmentSlot.HEAD, Items.DIAMOND_HELMET, Items.IRON_HELMET, 5),
         new EquipmentDemand(EquipmentSlot.FEET, Items.DIAMOND_BOOTS, Items.IRON_BOOTS, 4)
      };

      for (EquipmentDemand var7 : var3) {
         ItemStack var8 = var2.getEquippedStack(var7.x3v9a3rm2wy1oi());
         if (!x313d952sywy3b(var8)) {
            Optional var9 = ColonyManager.x3lxwdirnn77qc(
               var1, this.x3hy0zpbzjwfng, var2x -> var2x.isOf(var7.x2b30qxyd0cjni()) || var8.isEmpty() && var2x.isOf(var7.x6ma4582xqrzt())
            );
            if (var9.isPresent()) {
               this.x1d7s430m0tpuj(var1, var2, var7.x3v9a3rm2wy1oi());
               var2.equipStack(var7.x3v9a3rm2wy1oi(), (ItemStack)var9.get());
               return true;
            }

            if (ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, x34u6jjh5a935g, var7.xl7i1w1rdwzb1())) {
               this.x1d7s430m0tpuj(var1, var2, var7.x3v9a3rm2wy1oi());
               var2.equipStack(var7.x3v9a3rm2wy1oi(), new ItemStack(var7.x2b30qxyd0cjni()));
               return true;
            }

            if (var8.isEmpty()) {
               VillagerBucketRefillHelper.x7uj9zeib8utf(var1, this.x3hy0zpbzjwfng, var7.xl7i1w1rdwzb1());
               if (ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, xom517cw0um4v, var7.xl7i1w1rdwzb1())) {
                  var2.equipStack(var7.x3v9a3rm2wy1oi(), new ItemStack(var7.x6ma4582xqrzt()));
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean x2a5t34vw8m1l3(ServerWorld var1, Predicate<ItemStack> var2, int var3, Predicate<ItemStack> var4, int var5) {
      return ColonyManager.x1mlne8tl8k5ag(var1, this.x3hy0zpbzjwfng, var2) >= var3 && ColonyManager.x1mlne8tl8k5ag(var1, this.x3hy0zpbzjwfng, var4) >= var5
         ? ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, var2, var3) && ColonyManager.x22hjebj4vmztt(var1, this.x3hy0zpbzjwfng, var4, var5)
         : false;
   }

   private static boolean x3ur57yzm9k1a(ItemStack var0) {
      return !var0.isEmpty() && (var0.isOf(Items.BOW) || var0.isIn(ItemTags.SWORDS) || var0.isIn(ItemTags.AXES));
   }

   private static boolean x1oufdnvti1fyk(VillagerEntity var0, Predicate<ItemStack> var1) {
      if (!var1.test(var0.getMainHandStack()) && !var1.test(var0.getOffHandStack())) {
         for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
            if (var1.test(var0.getInventory().getStack(var2))) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private void x1d7s430m0tpuj(ServerWorld var1, VillagerEntity var2, EquipmentSlot var3) {
      VillagerBucketRefillHelper.xqe8twcjwxn55(var1, this.x3hy0zpbzjwfng, var2, var2.getEquippedStack(var3));
   }

   private static int x1svbmkuu5e77z(ItemStack var0) {
      if (var0.isOf(Items.DIAMOND_SWORD) || var0.isOf(Items.DIAMOND_AXE) || var0.isOf(Items.NETHERITE_SWORD) || var0.isOf(Items.NETHERITE_AXE)) {
         return 4;
      } else if (var0.isOf(Items.BOW)) {
         return 4;
      } else if (var0.isOf(Items.IRON_SWORD) || var0.isOf(Items.IRON_AXE)) {
         return 3;
      } else {
         return !var0.isIn(ItemTags.SWORDS) && !var0.isIn(ItemTags.AXES) ? 0 : 1;
      }
   }

   private static boolean x313d952sywy3b(ItemStack var0) {
      return var0.isOf(Items.DIAMOND_HELMET)
         || var0.isOf(Items.DIAMOND_CHESTPLATE)
         || var0.isOf(Items.DIAMOND_LEGGINGS)
         || var0.isOf(Items.DIAMOND_BOOTS)
         || var0.isOf(Items.NETHERITE_HELMET)
         || var0.isOf(Items.NETHERITE_CHESTPLATE)
         || var0.isOf(Items.NETHERITE_LEGGINGS)
         || var0.isOf(Items.NETHERITE_BOOTS);
   }

   private static int x25z7tdvietcut(EquipmentSlot var0) {
      return var0 == EquipmentSlot.CHEST ? 8 : (var0 == EquipmentSlot.LEGS ? 7 : (var0 == EquipmentSlot.HEAD ? 5 : 4));
   }
}
