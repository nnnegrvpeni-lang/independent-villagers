package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap.Type;

public final class FishermanTask extends MultiTickTask<VillagerEntity> {
   private static final List<BlockPos> x2kdfv2w398skl = x3j2bsjy7dlm8n();
   private Colony x2d46f6032ktvg;
   private PlotDirection x13ycknwfia743;
   private BlockPos x252scecjwbsjc;
   private BlockPos x2n8fs353bluyf;
   private int x3kmuxybabtcha;
   private int x2cuv9elefqg8b;
   private long x1ivrvuxnf7a8r;
   private long x1rc9hdzmwvbv4;
   private boolean x21q585dpo3fow;
   private FishingBobberEntity xm389zhbgl3fn;
   private long xapcnyboftre6;

   public FishermanTask() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 12000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby() && !var2.isSleeping() && !var2.hasCustomer() && var1.getTime() >= this.x1rc9hdzmwvbv4) {
         this.x2d46f6032ktvg = ColonyManager.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == ColonyRole.xhds9crfjdp9i && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.x2d46f6032ktvg != null;
      } else {
         return false;
      }
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x13ycknwfia743 = PlotDirection.x3txgvu31lr8pk;
      this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
      this.x3kmuxybabtcha = 0;
      this.x2cuv9elefqg8b = 0;
      this.x1ivrvuxnf7a8r = var3;
      this.x21q585dpo3fow = false;
      this.x32ermkhbb9bv2();
      this.xapcnyboftre6 = var3 + 100L;
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.x2d46f6032ktvg != null
         && this.x2d46f6032ktvg.roleOf(var2.getUuid()) == ColonyRole.xhds9crfjdp9i
         && !this.x21q585dpo3fow;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      if (var3 >= this.x1ivrvuxnf7a8r) {
         if (this.x13ycknwfia743 == PlotDirection.x3txgvu31lr8pk || this.x13ycknwfia743 == PlotDirection.x20xzifasgcsfe) {
            this.xbt4deyrou7q8(var1, var2, var3);
         } else if (this.x13ycknwfia743 == PlotDirection.x35pte2qdiflin) {
            if (!this.x38ykb3ggi4dan(var1, var2)) {
               if (this.x3kmuxybabtcha >= x2kdfv2w398skl.size()) {
                  this.x13ycknwfia743 = PlotDirection.x20xzifasgcsfe;
               }
            }
         } else if (this.x252scecjwbsjc != null && var1.getBlockState(this.x252scecjwbsjc).isOf(Blocks.WATER)) {
            var2.getBrain().remember(ColonyMemoryModules.x263ucjykllb1b, this.x252scecjwbsjc);
            if (var2.getBlockPos().getSquaredDistance(this.x2n8fs353bluyf) > 6.0) {
               this.x80alxwfk9adt(var1, var2, this.x2n8fs353bluyf, 0.75);
            } else {
               var2.getNavigation().stop();
               var2.getLookControl().lookAt(this.x252scecjwbsjc.getX() + 0.5, this.x252scecjwbsjc.getY() + 0.2, this.x252scecjwbsjc.getZ() + 0.5);
               if (this.xm389zhbgl3fn == null || this.xm389zhbgl3fn.isRemoved()) {
                  this.xm389zhbgl3fn = new FishingBobberEntity(EntityType.FISHING_BOBBER, var1);
                  this.xm389zhbgl3fn.setOwner(var2);
                  this.xm389zhbgl3fn.setNoGravity(true);
                  this.xm389zhbgl3fn
                     .refreshPositionAndAngles(
                        this.x252scecjwbsjc.getX() + 0.5, this.x252scecjwbsjc.getY() + 0.85, this.x252scecjwbsjc.getZ() + 0.5, 0.0F, 0.0F
                     );
                  if (!var1.spawnEntity(this.xm389zhbgl3fn)) {
                     this.xm389zhbgl3fn = null;
                     return;
                  }

                  var2.swingHand(Hand.MAIN_HAND);
                  this.xapcnyboftre6 = var3 + 100L + var1.random.nextInt(61);
               }

               if (var3 >= this.xapcnyboftre6) {
                  this.xapcnyboftre6 = var3 + 100L + var1.random.nextInt(61);
                  ItemStack var5 = new ItemStack(var1.random.nextInt(4) == 0 ? Items.SALMON : Items.COD);
                  ItemStack var6 = var2.getInventory().addStack(var5);
                  var2.swingHand(Hand.MAIN_HAND);
                  this.x2cuv9elefqg8b++;
                  if (var6.isEmpty()) {
                     ColonyChatFeedback.x2t98zlq4lu49t(var2, "рыбалка", 1);
                  } else {
                     var2.dropStack(var1, var6);
                  }

                  if (!var6.isEmpty() || this.x2cuv9elefqg8b >= 12) {
                     this.x32ermkhbb9bv2();
                     this.x13ycknwfia743 = PlotDirection.x20xzifasgcsfe;
                     this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
                  }
               }
            }
         } else {
            this.x13ycknwfia743 = PlotDirection.x35pte2qdiflin;
            this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
         }
      }
   }

   private void xbt4deyrou7q8(ServerWorld var1, VillagerEntity var2, long var3) {
      Optional var5 = ColonyManager.x3jrfw2d3yykeu(var1, this.x2d46f6032ktvg, var2, StorageCategory.xnpajviatkbuo);
      if (var5.isEmpty()) {
         this.x21q585dpo3fow = true;
      } else {
         var2.getBrain().remember(ColonyMemoryModules.x3o547clcv9kaw, (BlockPos)var5.get());
         if (var2.getBlockPos().getSquaredDistance((Vec3i)var5.get()) > 9.0) {
            this.x80alxwfk9adt(var1, var2, (BlockPos)var5.get(), 0.75);
         } else {
            for (int var6 = 0; var6 < var2.getInventory().size(); var6++) {
               ItemStack var7 = var2.getInventory().getStack(var6);
               if (var7.isOf(Items.COD) || var7.isOf(Items.SALMON) || var7.isOf(Items.COOKED_COD) || var7.isOf(Items.COOKED_SALMON)) {
                  ColonyChatFeedback.x25cotfj45r525(var1, this.x2d46f6032ktvg, var2, var7);
               }
            }

            this.x34vw5qt9vs5wg(var1);
            if (this.x13ycknwfia743 == PlotDirection.x20xzifasgcsfe) {
               this.x21q585dpo3fow = true;
               this.x1rc9hdzmwvbv4 = var3 + 40L;
            } else {
               if (!var2.getMainHandStack().isOf(Items.FISHING_ROD)) {
                  Optional var8 = ColonyManager.x3lxwdirnn77qc(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.FISHING_ROD));
                  if (var8.isPresent()) {
                     this.x1cfeftsaw9e5z(var1, var2);
                     var2.equipStack(EquipmentSlot.MAINHAND, (ItemStack)var8.get());
                  } else if (this.xzqd1l0vwcny0(var1)) {
                     this.x1cfeftsaw9e5z(var1, var2);
                     var2.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
                  }
               }

               if (!var2.getMainHandStack().isOf(Items.FISHING_ROD)) {
                  ColonyChatFeedback.x1qd0mutwsr3sc(var2, "для удочки нужны 5 брёвен или 2 бревна и 2 нити");
                  this.x21q585dpo3fow = true;
                  this.x1rc9hdzmwvbv4 = var3 + 200L;
               } else {
                  this.x13ycknwfia743 = PlotDirection.x35pte2qdiflin;
                  this.x3kmuxybabtcha = 0;
               }
            }
         }
      }
   }

   private boolean xzqd1l0vwcny0(ServerWorld var1) {
      int var2 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.isIn(ItemTags.LOGS));
      int var3 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.STRING));
      return var2 >= 2 && var3 >= 2
         ? ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.isIn(ItemTags.LOGS), 2)
            && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.STRING), 2)
         : var2 >= 5 && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.isIn(ItemTags.LOGS), 5);
   }

   private void x34vw5qt9vs5wg(ServerWorld var1) {
      int var2 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.COD));
      int var3 = ColonyManager.x1mlne8tl8k5ag(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.SALMON));
      int var4 = Math.min(8, var2 + var3);
      if (var4 != 0) {
         int var5 = Math.min(var2, var4);
         int var6 = var4 - var5;
         ArrayList var7 = new ArrayList();
         if (var5 > 0) {
            var7.add(new ItemStack(Items.COOKED_COD, var5));
         }

         if (var6 > 0) {
            var7.add(new ItemStack(Items.COOKED_SALMON, var6));
         }

         ColonyManager.x38ipozok9mc0x(
            var1,
            this.x2d46f6032ktvg,
            () -> ColonyManager.x22hjebj4vmztt(
                  var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.COAL) || var0.isOf(Items.CHARCOAL) || var0.isIn(ItemTags.LOGS), 1
               )
               && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.COD), var5)
               && ColonyManager.x22hjebj4vmztt(var1, this.x2d46f6032ktvg, var0 -> var0.isOf(Items.SALMON), var6),
            var7
         );
      }
   }

   private void x1cfeftsaw9e5z(ServerWorld var1, VillagerEntity var2) {
      ItemStack var3 = var2.getMainHandStack();
      if (!var3.isEmpty() && !ColonyManager.x2cl1crde2rnxy(var1, this.x2d46f6032ktvg, var3)) {
         ItemStack var4 = var2.getInventory().addStack(var3.copy());
         if (!var4.isEmpty()) {
            var2.dropStack(var1, var4);
         }
      }
   }

   private boolean x38ykb3ggi4dan(ServerWorld var1, VillagerEntity var2) {
      int var3 = Math.min(48, this.x2d46f6032ktvg.radius() - 4);

      for (int var4 = 0; var4 < 256 && this.x3kmuxybabtcha < x2kdfv2w398skl.size(); var4++) {
         BlockPos var5 = x2kdfv2w398skl.get(this.x3kmuxybabtcha++);
         if (var5.getSquaredDistance(BlockPos.ORIGIN) > var3 * var3) {
            this.x3kmuxybabtcha = x2kdfv2w398skl.size();
            break;
         }

         int var6 = this.x2d46f6032ktvg.core().getX() + var5.getX();
         int var7 = this.x2d46f6032ktvg.core().getZ() + var5.getZ();
         if (var1.getChunkManager().isChunkLoaded(var6 >> 4, var7 >> 4)) {
            int var8 = var1.getTopY(Type.MOTION_BLOCKING, var6, var7) - 1;
            BlockPos var9 = new BlockPos(var6, var8, var7);
            if (var1.getBlockState(var9).isOf(Blocks.WATER) && var1.getFluidState(var9).isStill() && var1.getBlockState(var9.up()).isAir()) {
               for (Direction var11 : net.minecraft.util.math.Direction.Type.HORIZONTAL) {
                  for (int var12 = 1; var12 <= 4; var12++) {
                     BlockPos var13 = var9.offset(var11, var12).up();
                     if (!ColonyNavigationHelper.xps35ehel6nkk(var2, var13, var1.getTime())
                        && var1.getBlockState(var13).isAir()
                        && var1.getBlockState(var13.up()).isAir()
                        && var1.getBlockState(var13.down()).isSideSolidFullSquare(var1, var13.down(), Direction.UP)) {
                        if (!ColonyManager.x5ll9elqpk1a4(var1)) {
                           return true;
                        }

                        Path var14 = var2.getNavigation().findPathTo(var13, 1);
                        if (var14 != null && var14.reachesTarget()) {
                           this.x252scecjwbsjc = var9;
                           this.x2n8fs353bluyf = var13;
                           this.x13ycknwfia743 = PlotDirection.x29tzvlsni8xhy;
                           this.x1ivrvuxnf7a8r = var1.getTime() + 40L;
                           return true;
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private void x80alxwfk9adt(ServerWorld var1, VillagerEntity var2, BlockPos var3, double var4) {
      ColonyNavigationHelper.x2wzylgizxltef(var1, var2, BlockPos.ofFloored(var3.getX() + 0.5, var3.getY(), var3.getZ() + 0.5), var4);
   }

   private void x32ermkhbb9bv2() {
      if (this.xm389zhbgl3fn != null) {
         this.xm389zhbgl3fn.discard();
      }

      this.xm389zhbgl3fn = null;
   }

   public String debugState() {
      return (this.x13ycknwfia743 == null ? "ожидание" : this.x13ycknwfia743.name().toLowerCase(Locale.ROOT)) + ", улов=" + this.x2cuv9elefqg8b;
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x32ermkhbb9bv2();
      var2.getNavigation().stop();
      var2.getBrain().forget(ColonyMemoryModules.x263ucjykllb1b);
      var2.getBrain().forget(ColonyMemoryModules.x3o547clcv9kaw);
      this.x2d46f6032ktvg = null;
      this.x252scecjwbsjc = this.x2n8fs353bluyf = null;
   }

   private static List<BlockPos> x3j2bsjy7dlm8n() {
      ArrayList<BlockPos> var0 = new ArrayList<>();

      for (int var1 = -48; var1 <= 48; var1++) {
         for (int var2 = -48; var2 <= 48; var2++) {
            var0.add(new BlockPos(var1, 0, var2));
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.getSquaredDistance(BlockPos.ORIGIN)));
      return List.copyOf(var0);
   }
}
