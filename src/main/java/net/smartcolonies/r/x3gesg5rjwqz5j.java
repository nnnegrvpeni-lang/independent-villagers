package net.smartcolonies.r;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Direction.Type;

public final class x3gesg5rjwqz5j extends MultiTickTask<VillagerEntity> {
   public static final String x3l030m30karg3 = "smart_colonies_emergency_return";
   private static final int x23l4wrkdbbupb = 512;
   private static final Direction[] x2n94koxvmk13t = new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
   private static final List<BlockPos> x1fgwiff13cmf2 = x3cil34rc17d8m();
   private x15kgoaq6i3m6i xy1q9c188jgfl;
   private xmq1yravtjp1k x1ajk4ev1s3vmk;
   private BlockPos x39r1px550mewh;
   private BlockPos x1co9a4owy6j0p;
   private BlockPos x3ima85yg8u7pt;
   private BlockPos x1pyrwt4gvtvim;
   private final ArrayDeque<BlockPos> x26z6s4l7xpegw = new ArrayDeque<>();
   private final ArrayDeque<BlockPos> x1wi4zdglz9eff = new ArrayDeque<>();
   private final List<BlockPos> x1m6docvvu7vog = new ArrayList<>();
   private long x1xqtm36d9leb0;
   private long x1lbhfpli59w62;
   private long x191fs1fsn83bz;
   private long xhvgstwe661u5;
   private final x20lc7jle5mw9c x1lb0jykmu89gj = new x20lc7jle5mw9c();
   private double x1uepbjw0wv8fc;
   private Vec3d x1vlro08p2yyfr;
   private int x35hjgfi0ny5oi;
   private int xtas7exwnkweh;
   private int xc9ckqw7lv8b1;
   private int x340ppe230jyu;
   private int x3alovr2tshkm1;
   private int x2bkom4nbrpupj;
   private int x342hqz5dxukyd;
   private boolean x1ow0ndkhuxfv7;
   private final Map<BlockPos, Long> x1mcvl1k31xh98 = new HashMap<>();
   private BlockPos x5s8pk56x49d;
   private double x1vajhas144pja;
   private long x2c83xgjkd1pgw;
   private int xdyvztdlmgr2v;
   private int x3034xvdt9o8yg;
   private long xvo4i1er6ijvn;
   private boolean xgxy3geynmx1k;
   private BlockPos x2qljtijql8vba;
   private long x1bhavtwhoa4l5;
   private long x7c7ayx9b17cq;
   private boolean x120csoysnauu8;
   private boolean x1yhzt10hzydbt;
   private long x2ayx8ea5u28av;
   private int x1ngl9rxhvf6cn;
   private double x2wpsg3os7b6uj;
   private long x1cibfctnwc0om;

   public x3gesg5rjwqz5j() {
      super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryModuleState.REGISTERED), 24000);
   }

   protected boolean shouldRun(ServerWorld var1, VillagerEntity var2) {
      if (!var2.isBaby()
         && !var2.isSleeping()
         && !var2.hasCustomer()
         && var1.getServer().getTicks() >= this.x191fs1fsn83bz
         && Math.floorMod(var1.getTimeOfDay(), 24000L) < 7800L
         && x1dxdlnevtfbhm.x35ev8l9vc9uk7(var1)) {
         this.xy1q9c188jgfl = x2n55y2tom37h2.x3psqezd5jem54(var1, var2.getBlockPos())
            .filter(var1x -> var1x.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x18g4mqx9kxs89 && !var1x.x2ad3s8aentmo4().isEmpty())
            .orElse(null);
         return this.xy1q9c188jgfl != null;
      } else {
         return false;
      }
   }

   protected void run(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.x1scd63yxj5vlw;
      this.x1ow0ndkhuxfv7 = false;
      this.x39r1px550mewh = null;
      this.x1pyrwt4gvtvim = null;
      this.x35hjgfi0ny5oi = 0;
      this.x26z6s4l7xpegw.clear();
      this.x1wi4zdglz9eff.clear();
      this.x1m6docvvu7vog.clear();
      this.x3alovr2tshkm1 = -1;
      this.x2bkom4nbrpupj = -1;
      this.x342hqz5dxukyd = 0;
      this.xgxy3geynmx1k = false;
      this.x1xqtm36d9leb0 = var3;
      this.x1lbhfpli59w62 = var1.getServer().getTicks();
      int var5 = this.x28tnsle11zyqe(var2);
      this.x340ppe230jyu = !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
            && this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var5).getSquaredDistance(var2.getBlockPos()) <= 9.0
            && var2.getBlockY() < this.xy1q9c188jgfl.x31ow2tlg0dm5w().getFirst().getY() - 2
         ? var5
         : 0;
      long var6 = var1.getServer().getTicks();
      this.xdyvztdlmgr2v = 0;
      this.x3034xvdt9o8yg = -1;
      this.x5s8pk56x49d = null;
      this.x2c83xgjkd1pgw = var6;
      this.xvo4i1er6ijvn = var6;
      this.x1mcvl1k31xh98.entrySet().removeIf(var2x -> var2x.getValue() <= var6);
      int var8 = this.xy1q9c188jgfl.x3eeagbf8g6pbh();
      this.x120csoysnauu8 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 32 && var8 >= 2 || var8 >= 6;
      this.x1yhzt10hzydbt = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 32 && var8 >= 6 && var8 < 12;
      this.x2ayx8ea5u28av = var6;
      this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      this.xhvgstwe661u5 = var6;
      this.x1uepbjw0wv8fc = Double.MAX_VALUE;
      this.x1vlro08p2yyfr = var2.getEntityPos();
      this.x2wpsg3os7b6uj = Double.MAX_VALUE;
      this.x1cibfctnwc0om = var6;
      this.x2idgvhct1beze(var2, this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() ? this.xy1q9c188jgfl.core() : this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast());
   }

   protected boolean shouldKeepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      return !var2.isSleeping()
         && !var2.hasCustomer()
         && this.xy1q9c188jgfl != null
         && this.xy1q9c188jgfl.roleOf(var2.getUuid()) == x1rxiy9mtu5b3h.x18g4mqx9kxs89
         && !this.x1ow0ndkhuxfv7;
   }

   protected void keepRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      long var5 = var1.getServer().getTicks();
      if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() > this.x1ngl9rxhvf6cn) {
         this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
         this.x2ayx8ea5u28av = var5;
      } else if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 32 && var5 - this.x2ayx8ea5u28av >= 400L
         || !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && var5 - this.x2ayx8ea5u28av >= 2400L) {
         this.x120csoysnauu8 = true;
      }

      if (var2.getCommandTags().contains("smart_colonies_emergency_return")) {
         var2.removeCommandTag("smart_colonies_emergency_return");
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.x3s2uqlmkylaf8;
         this.x39r1px550mewh = null;
         this.x1pyrwt4gvtvim = null;
         this.x340ppe230jyu = 0;
         this.x1m6docvvu7vog.clear();
         this.x342hqz5dxukyd = 0;
         this.x2bkom4nbrpupj = -1;
         this.x5s8pk56x49d = null;
         this.xgxy3geynmx1k = false;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.getEntityPos();
      }

      if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.x2zootg8evq806) {
         this.x1lam802btimdu(var1, var2, var3);
      } else if (this.x120csoysnauu8 && this.x1yhzt10hzydbt && this.x1ajk4ev1s3vmk == xmq1yravtjp1k.x1scd63yxj5vlw && this.x1xbvmwg584cth(var1, var2)) {
         this.x120csoysnauu8 = false;
         this.x1yhzt10hzydbt = false;
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.xdrr1h46oexk3;
         this.x2ayx8ea5u28av = this.x1lbhfpli59w62 = var5;
         this.x1ngl9rxhvf6cn = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      } else if (this.x120csoysnauu8 && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && var5 >= this.xy1q9c188jgfl.x1wlbtxwjz4vz1()) {
         this.x294dcmermbsdf(var1, var2, var3);
      } else if (var3 >= this.x1xqtm36d9leb0) {
         this.x1xqtm36d9leb0 = var3 + 1L;
         long var7 = Math.floorMod(var1.getTimeOfDay(), 24000L);
         if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.xdrr1h46oexk3 && var7 >= 8000L && var7 < 12000L) {
            this.xhzkfb8tfrmu6(var2);
         } else if (this.x1ajk4ev1s3vmk != xmq1yravtjp1k.xdrr1h46oexk3) {
            this.x3vdgwaoodch5a(var1, var2, var3);
         } else {
            if (this.x1pyrwt4gvtvim != null && var5 - this.x1lbhfpli59w62 > 200L) {
               this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
               this.x1pyrwt4gvtvim = null;
               this.x39r1px550mewh = null;
               this.x26z6s4l7xpegw.clear();
               if (!this.x1m6docvvu7vog.isEmpty()) {
                  this.xhzkfb8tfrmu6(var2);
                  this.xgxy3geynmx1k = true;
                  return;
               }
            }

            if (var5 - this.x1lbhfpli59w62 <= 1600L
               && this.x35hjgfi0ny5oi < 192
               && xocihbcrvdo4w(var2) < 256
               && x215m1zmqihw78(var2) < 24
               && !(var2.getHealth() < var2.getMaxHealth() * 0.6F)) {
               if (this.x39r1px550mewh != null) {
                  BlockState var22 = var1.getBlockState(this.x39r1px550mewh);
                  if (xe9s6y96sy1nw(var1, this.x39r1px550mewh)) {
                     this.x39r1px550mewh = null;
                     this.x1lbhfpli59w62 = var5;
                  } else if (!this.x2koaf0y8uk4zf(var1, this.x39r1px550mewh)) {
                     this.x39r1px550mewh = null;
                     this.x1pyrwt4gvtvim = null;
                  } else if (!x2pv6ejqyq6ou5(var2, var22)) {
                     this.x39r1px550mewh = null;
                     this.xhzkfb8tfrmu6(var2);
                  } else {
                     this.x2idgvhct1beze(var2, this.x39r1px550mewh);
                     if (var2.getBlockPos().getSquaredDistance(this.x1co9a4owy6j0p) > 2.5) {
                        if (x1c1r5c6a13gtl.xps35ehel6nkk(var2, this.x1co9a4owy6j0p, var5)) {
                           this.x1mcvl1k31xh98.put(this.x39r1px550mewh, var5 + 1200L);
                           this.x39r1px550mewh = null;
                           this.x1pyrwt4gvtvim = null;
                        } else {
                           this.x2qi4jek408dcr(var1, var2, this.x1co9a4owy6j0p);
                        }
                     } else if (!this.x3kfcd4ju4t319(var1, var2, var2.getBlockPos())) {
                        this.xhzkfb8tfrmu6(var2);
                     } else if (!(this.x39r1px550mewh.getSquaredDistance(var2.getBlockPos()) > 12.0) && !this.x39r1px550mewh.equals(var2.getBlockPos().down())) {
                        BlockState var24 = var1.getBlockState(this.x39r1px550mewh);
                        List<ItemStack> var26 = Block.getDroppedStacks(var24, var1, this.x39r1px550mewh, null, var2, var2.getMainHandStack());
                        if (!x3b13cjb6ycs5u(var2, var26)) {
                           this.xhzkfb8tfrmu6(var2);
                        } else if (this.x1lb0jykmu89gj
                           .xpohy1q30uxlk(var1, var2, this.x39r1px550mewh, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, this.x39r1px550mewh), true)) {
                           if (x1dxdlnevtfbhm.x231if4rvsvptj(var1, this.x39r1px550mewh, false, var2)) {
                              var26.forEach(var1x -> var2.getInventory().addStack(var1x));
                              var2.swingHand(Hand.MAIN_HAND);
                              this.x35hjgfi0ny5oi++;
                              this.x1lbhfpli59w62 = var5;
                              if (x1pn4sqnjt1xiv(var24)) {
                                 this.x9380ui9wkv3h(var1, this.x39r1px550mewh);
                              }
                           }

                           this.x39r1px550mewh = null;
                        }
                     } else {
                        this.x39r1px550mewh = null;
                     }
                  }
               } else if (!this.x2xwjgtx7cwwvg(var1, var2, var5)) {
                  if (this.x1pyrwt4gvtvim != null
                     && (
                        !x1pn4sqnjt1xiv(var1.getBlockState(this.x1pyrwt4gvtvim))
                           || !x2pv6ejqyq6ou5(var2, var1.getBlockState(this.x1pyrwt4gvtvim))
                           || this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(this.x1pyrwt4gvtvim.up())
                     )) {
                     this.x1pyrwt4gvtvim = null;
                  }

                  if (!this.x27hg6cma7yaw5(var1, var2)) {
                     if (this.x1pyrwt4gvtvim == null && this.x2bkom4nbrpupj >= 0 && this.x26z6s4l7xpegw.isEmpty()) {
                        this.xhzkfb8tfrmu6(var2);
                        this.xgxy3geynmx1k = true;
                     } else {
                        if (this.x1pyrwt4gvtvim == null && var5 >= this.xvo4i1er6ijvn) {
                           this.xvo4i1er6ijvn = var5 + 10L;
                           if (this.x2clgblb4ftzb2(var1, var2)) {
                              return;
                           }
                        }

                        if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()) {
                           this.x2ibslq4rhdko0(var1, var2);
                        } else {
                           BlockPos var9 = this.x1m6docvvu7vog.isEmpty() ? this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast() : this.x1m6docvvu7vog.getLast();
                           this.x2idgvhct1beze(var2, var9);
                           if (var2.getBlockPos().getSquaredDistance(var9) > 2.5) {
                              if (!this.x1m6docvvu7vog.isEmpty()) {
                                 this.x2qi4jek408dcr(var1, var2, var9);
                              } else {
                                 this.xdyvztdlmgr2v = Math.min(this.xdyvztdlmgr2v, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1);
                                 if (x1r7nkw0jt7ym0(var2, this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.xdyvztdlmgr2v))
                                    && this.xdyvztdlmgr2v < this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1) {
                                    this.xdyvztdlmgr2v++;
                                 }

                                 if (this.xdyvztdlmgr2v > this.x3034xvdt9o8yg) {
                                    this.x3034xvdt9o8yg = this.xdyvztdlmgr2v;
                                    this.x1lbhfpli59w62 = var5;
                                 }

                                 BlockPos var23 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.xdyvztdlmgr2v);
                                 this.x2qi4jek408dcr(var1, var2, var23);
                              }
                           } else if (!this.x3kfcd4ju4t319(var1, var2, var9)) {
                              this.xhzkfb8tfrmu6(var2);
                           } else if (this.x1pyrwt4gvtvim != null
                              || !this.x1m6docvvu7vog.isEmpty()
                              || this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() > 8 && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() % 8 != 0
                              || !this.x3kbhcj0gn3eds(var1, var9)) {
                              if (this.x1pyrwt4gvtvim != null && this.x1m6docvvu7vog.isEmpty()) {
                                 this.x2bkom4nbrpupj = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1;
                                 this.x1m6docvvu7vog.add(var9.toImmutable());
                              }

                              if (this.x1pyrwt4gvtvim == null && this.x1m6docvvu7vog.isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() >= 512) {
                                 this.x294dcmermbsdf(var1, var2, var3);
                              } else {
                                 BlockPos var10 = this.x1pyrwt4gvtvim == null
                                    ? nextStep(this.xy1q9c188jgfl, var1.getBottomY())
                                    : this.x7zvchtwcbs2l(var9, this.x1pyrwt4gvtvim);
                                 if (var10 == null) {
                                    if (this.x1pyrwt4gvtvim != null) {
                                       this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
                                    }

                                    this.x1pyrwt4gvtvim = null;
                                 } else if (x19i92dflbkly4(var1, var10.down()) || !this.x1npl0tf7hnjwg(var1, var2, var10)) {
                                    if (!this.xesfei3h73qdo(var1, var10)
                                       || this.x2rtvy1700fh69(var10)
                                       || this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var10)
                                       || this.x1m6docvvu7vog.contains(var10)) {
                                       if (this.x1pyrwt4gvtvim != null) {
                                          this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var5 + 6000L);
                                          this.x1pyrwt4gvtvim = null;
                                          return;
                                       }

                                       boolean var11 = false;
                                       int var12 = this.xy1q9c188jgfl.xdd8htksk84yo();

                                       for (int var13 = 1; var13 < 4; var13++) {
                                          this.xy1q9c188jgfl.xmskodayog7rh(var12 + var13);
                                          BlockPos var14 = nextStep(this.xy1q9c188jgfl, var1.getBottomY());
                                          if (this.xesfei3h73qdo(var1, var14)
                                             && !this.x2rtvy1700fh69(var14)
                                             && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var14)) {
                                             var10 = var14;
                                             var11 = true;
                                             x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                                             break;
                                          }
                                       }

                                       if (!var11) {
                                          label248:
                                          for (int var16 : new int[]{0, 1, -1}) {
                                             for (Direction var20 : x2n94koxvmk13t) {
                                                BlockPos var21 = var9.offset(var20).add(0, var16, 0);
                                                if (!this.x2rtvy1700fh69(var21)
                                                   && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var21)
                                                   && this.xesfei3h73qdo(var1, var21)) {
                                                   var10 = var21;
                                                   var11 = true;
                                                   break label248;
                                                }
                                             }
                                          }

                                          this.xy1q9c188jgfl.xmskodayog7rh(var12);
                                          if (!var11) {
                                             this.x294dcmermbsdf(var1, var2, var3);
                                             return;
                                          }
                                       }
                                    }

                                    for (int var25 = 2; var25 >= 0; var25--) {
                                       BlockPos var27 = var10.up(var25);
                                       if (!xe9s6y96sy1nw(var1, var27)) {
                                          if (!this.x2koaf0y8uk4zf(var1, var27)) {
                                             this.xhzkfb8tfrmu6(var2);
                                             return;
                                          }

                                          this.x39r1px550mewh = var27;
                                          this.x1co9a4owy6j0p = var9;
                                          this.x2idgvhct1beze(var2, this.x39r1px550mewh);
                                          return;
                                       }
                                    }

                                    if (this.x1pyrwt4gvtvim != null) {
                                       this.x1m6docvvu7vog.add(var10.toImmutable());
                                    } else {
                                       this.xy1q9c188jgfl.x31ow2tlg0dm5w().add(var10.toImmutable());
                                       this.xdyvztdlmgr2v = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1;
                                       x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                                    }

                                    this.x1lbhfpli59w62 = var5;
                                    this.x2qi4jek408dcr(var1, var2, var10);
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            } else {
               this.xhzkfb8tfrmu6(var2);
            }
         }
      }
   }

   private void x294dcmermbsdf(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x1lb0jykmu89gj.xupxzzyodxtwj(var1, var2);
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.x2zootg8evq806;
      this.x39r1px550mewh = null;
      this.x1pyrwt4gvtvim = null;
      this.x7c7ayx9b17cq = var1.getServer().getTicks() + x3sqkg3n6v972r.x2by5f9ehxvwk9().work.deep_mining_ticks;
      x1c1r5c6a13gtl.x2fxoqmbs0henb(var2);
      this.x2idgvhct1beze(var2, var2.getBlockPos());
      x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "глубинная экспедиция: работа в забое");
      this.x1lam802btimdu(var1, var2, var3);
   }

   private void x1lam802btimdu(ServerWorld var1, VillagerEntity var2, long var3) {
      x1c1r5c6a13gtl.x2fxoqmbs0henb(var2);
      this.x2idgvhct1beze(var2, var2.getBlockPos());
      if (var3 % 40L == 0L) {
         var2.swingHand(Hand.MAIN_HAND);
      }

      if (var1.getServer().getTicks() >= this.x7c7ayx9b17cq) {
         int var5 = 48 + var1.random.nextInt(49);
         int var6 = 8 + var1.random.nextInt(13);
         int var7 = 4 + var1.random.nextInt(9);
         int var8 = 4 + var1.random.nextInt(13);
         int var9 = this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.COBBLESTONE, var5))
            + this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.COAL, var6))
            + this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.RAW_IRON, var7))
            + this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.RAW_COPPER, var8));
         if (var2.getMainHandStack().isOf(Items.IRON_PICKAXE)
            || var2.getMainHandStack().isOf(Items.DIAMOND_PICKAXE)
            || var2.getMainHandStack().isOf(Items.NETHERITE_PICKAXE)) {
            if (var1.random.nextInt(3) == 0) {
               var9 += this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.DIAMOND, 1 + var1.random.nextInt(2)));
            }

            var9 += this.x3ldu7xuw4ylde(var1, var2, new ItemStack(Items.REDSTONE, 4 + var1.random.nextInt(9)));
         }

         this.xy1q9c188jgfl.x2itw5qfmlx92d(var9);
         this.xy1q9c188jgfl.xq8drze9hw6pb(var1.getServer().getTicks() + 600L);
         x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
         x2vaj7rsm57i4n.x2t98zlq4lu49t(var2, "глубинная добыча", var9);
         this.x35hjgfi0ny5oi += var9;
         this.x1xqtm36d9leb0 = var3;
         this.xhzkfb8tfrmu6(var2);
      }
   }

   private int x3ldu7xuw4ylde(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      int var4 = var3.getCount();
      x2vaj7rsm57i4n.x25cotfj45r525(var1, this.xy1q9c188jgfl, var2, var3);
      return var4 - var3.getCount();
   }

   public static BlockPos nextStep(x15kgoaq6i3m6i var0, int var1) {
      BlockPos var2 = var0.x31ow2tlg0dm5w().getLast();
      int var3 = var0.x31ow2tlg0dm5w().size() - 1;
      int var4 = Math.max(var1 + 10, -52);
      int var5 = 0;

      for (int var6 = var0.x31ow2tlg0dm5w().size() - 2; var6 >= 0 && var0.x31ow2tlg0dm5w().get(var6).getY() == var2.getY(); var6--) {
         var5++;
      }

      byte var11 = 64;
      if (var2.getY() <= var4 || var2.getY() == 16 && var5 < 2 * var11 + 3) {
         int var12 = var5 / (var11 + 3);
         int var8 = var5 % (var11 + 3);
         Direction var9 = x2n94koxvmk13t[var0.xdd8htksk84yo() % 4];
         Direction var10 = var8 < var11 ? (var12 % 2 == 0 ? var9 : var9.getOpposite()) : var9.rotateYClockwise();
         return var2.offset(var10);
      } else {
         Direction var7 = x2n94koxvmk13t[(var0.xdd8htksk84yo() + var3 / 24) % 4];
         return var2.offset(var7).down(var2.getY() > var4 ? 1 : 0);
      }
   }

   private void x3vdgwaoodch5a(ServerWorld var1, VillagerEntity var2, long var3) {
      long var5 = var1.getServer().getTicks();
      if (this.x342hqz5dxukyd > 0 && !this.x1m6docvvu7vog.isEmpty()) {
         this.x342hqz5dxukyd = Math.min(this.x342hqz5dxukyd, this.x1m6docvvu7vog.size() - 1);
         BlockPos var7 = this.x1m6docvvu7vog.get(this.x342hqz5dxukyd - 1);
         this.x2idgvhct1beze(var2, var7);
         double var8 = var2.getBlockPos().getSquaredDistance(var7);
         if (!x1r7nkw0jt7ym0(var2, var7)) {
            if (var8 + 1.0 < this.x1uepbjw0wv8fc) {
               this.x1uepbjw0wv8fc = var8;
               this.xhvgstwe661u5 = var5;
            }

            this.xwknhpp7tkplu(var2, var5);
            this.x2qi4jek408dcr(var1, var2, var7);
            if (var5 - this.xhvgstwe661u5 > 360L) {
               x35kkrl6uz1e8h.xdo71dbv16zut(var1, this.xy1q9c188jgfl, var2);
               this.x342hqz5dxukyd = 0;
            }

            return;
         }

         this.x342hqz5dxukyd--;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.getEntityPos();
      }

      if (!this.x1m6docvvu7vog.isEmpty()) {
         this.x1m6docvvu7vog.clear();
         this.x340ppe230jyu = this.x2bkom4nbrpupj >= 0
            ? Math.min(this.x2bkom4nbrpupj, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1)
            : this.x28tnsle11zyqe(var2);
         this.x2bkom4nbrpupj = -1;
         this.xhvgstwe661u5 = var5;
         this.x1uepbjw0wv8fc = Double.MAX_VALUE;
         this.x1vlro08p2yyfr = var2.getEntityPos();
      }

      if (this.xgxy3geynmx1k) {
         this.xgxy3geynmx1k = false;
         this.x1ajk4ev1s3vmk = xmq1yravtjp1k.xdrr1h46oexk3;
         this.x39r1px550mewh = null;
         this.x1pyrwt4gvtvim = null;
         this.xdyvztdlmgr2v = this.x340ppe230jyu;
         this.x3034xvdt9o8yg = this.xdyvztdlmgr2v;
         this.x5s8pk56x49d = null;
         this.x1lbhfpli59w62 = var5;
      } else {
         if (this.x340ppe230jyu > 0) {
            this.x340ppe230jyu = Math.min(this.x340ppe230jyu, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1);
            BlockPos var12 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.x340ppe230jyu - 1);
            this.x2idgvhct1beze(var2, var12);
            double var14 = var2.getBlockPos().getSquaredDistance(var12);
            if (!x1r7nkw0jt7ym0(var2, var12)) {
               if (var14 + 1.0 < this.x1uepbjw0wv8fc) {
                  this.x1uepbjw0wv8fc = var14;
                  this.xhvgstwe661u5 = var5;
               }

               this.xwknhpp7tkplu(var2, var5);
               this.x2qi4jek408dcr(var1, var2, var12);
               if (var5 - this.xhvgstwe661u5 > 360L) {
                  x35kkrl6uz1e8h.xdo71dbv16zut(var1, this.xy1q9c188jgfl, var2);
                  this.xhvgstwe661u5 = var5;
                  this.x1uepbjw0wv8fc = Double.MAX_VALUE;
                  this.x1vlro08p2yyfr = var2.getEntityPos();
               }

               return;
            }

            this.x340ppe230jyu--;
            this.xhvgstwe661u5 = var5;
            this.x1uepbjw0wv8fc = Double.MAX_VALUE;
            this.x1vlro08p2yyfr = var2.getEntityPos();
         }

         Optional var13 = x2n55y2tom37h2.x3jrfw2d3yykeu(var1, this.xy1q9c188jgfl, var2, x2jj5edkiu5orf.x3leq9hn5gf43c);
         if (var13.isEmpty()) {
            this.x1ow0ndkhuxfv7 = true;
         } else {
            double var15 = var2.getBlockPos().getSquaredDistance((Vec3i)var13.get());
            if (var15 > 9.0) {
               if (var15 + 1.0 < this.x2wpsg3os7b6uj) {
                  this.x2wpsg3os7b6uj = var15;
                  this.x1cibfctnwc0om = var5;
               }

               this.x2qi4jek408dcr(var1, var2, (BlockPos)var13.get());
               if ((var5 - this.x1cibfctnwc0om > 200L || x1c1r5c6a13gtl.x1f6ghn62xaxti(var2, var5)) && this.x2nr3c5c4mpjij(var1, var2, (BlockPos)var13.get())) {
                  this.x2wpsg3os7b6uj = Double.MAX_VALUE;
                  this.x1cibfctnwc0om = var5;
               }
            } else {
               this.x2wpsg3os7b6uj = Double.MAX_VALUE;
               this.x1cibfctnwc0om = var5;

               for (int var10 = 0; var10 < var2.getInventory().size(); var10++) {
                  ItemStack var11 = var2.getInventory().getStack(var10);
                  if (x3suldnm75afk3(var11)) {
                     x2vaj7rsm57i4n.x25cotfj45r525(var1, this.xy1q9c188jgfl, var2, var11);
                  }
               }

               x2fl60kuzia5xk.x3bzx7lf7atdqb(var1, this.xy1q9c188jgfl);
               if (this.x120csoysnauu8 && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && var1.getServer().getTicks() >= this.xy1q9c188jgfl.x1wlbtxwjz4vz1()) {
                  this.x294dcmermbsdf(var1, var2, var3);
               } else if (this.x120csoysnauu8 && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()) {
                  this.x1ow0ndkhuxfv7 = true;
               } else if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.x3s2uqlmkylaf8) {
                  this.x1ow0ndkhuxfv7 = true;
                  this.x191fs1fsn83bz = var5 + 40L;
               } else {
                  this.x1moizhe667oge(var1, var2);
                  this.x3mjvdhkyzto32(var1, var2);
                  this.x1medszyn1khgr(var1, var2);
                  if (var2.getMainHandStack().isIn(ItemTags.PICKAXES)
                     && xncu5r00mkmgq(var2, Items.TORCH) != 0
                     && x2n55y2tom37h2.x57l6vd1sx1rt(var1, this.xy1q9c188jgfl, x3gesg5rjwqz5j::x3suldnm75afk3)) {
                     this.x1ajk4ev1s3vmk = xmq1yravtjp1k.xdrr1h46oexk3;
                     this.x1lbhfpli59w62 = var5;
                     this.xdyvztdlmgr2v = 0;
                     this.x3034xvdt9o8yg = -1;
                     this.x5s8pk56x49d = null;
                  } else {
                     this.x1ow0ndkhuxfv7 = true;
                     this.x191fs1fsn83bz = var5 + 100L;
                  }
               }
            }
         }
      }
   }

   private boolean x2nr3c5c4mpjij(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      for (int var4 = 1; var4 <= 2; var4++) {
         for (int var5 = -var4; var5 <= var4; var5++) {
            for (int var6 = -var4; var6 <= var4; var6++) {
               if (Math.abs(var5) == var4 || Math.abs(var6) == var4) {
                  BlockPos var7 = var3.add(var5, 0, var6);
                  if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var7)
                     && var1.getOtherEntities(var2, new Box(var7).expand(0.35), var0 -> var0.isAlive() && var0 instanceof LivingEntity).isEmpty()) {
                     var2.getNavigation().stop();
                     var2.refreshPositionAndAngles(var7.getX() + 0.5, var7.getY(), var7.getZ() + 0.5, var2.getYaw(), var2.getPitch());
                     var2.setVelocity(Vec3d.ZERO);
                     var2.onLanding();
                     this.x5s8pk56x49d = null;
                     this.x2c83xgjkd1pgw = var1.getServer().getTicks();
                     x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "восстановление доступа к складу шахтёра");
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private void x1moizhe667oge(ServerWorld var1, VillagerEntity var2) {
      x2fl60kuzia5xk.x31tan2efb24l6(var1, this.xy1q9c188jgfl, var2, ItemTags.PICKAXES, Items.WOODEN_PICKAXE, Items.STONE_PICKAXE);
   }

   private void x17lv4ejkx2w4z(ServerWorld var1, VillagerEntity var2, ItemStack var3) {
      ItemStack var4 = var2.getMainHandStack();
      x2fl60kuzia5xk.xqe8twcjwxn55(var1, this.xy1q9c188jgfl, var2, var4);
      var2.equipStack(EquipmentSlot.MAINHAND, var3);
   }

   private void x3mjvdhkyzto32(ServerWorld var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < 32 && xncu5r00mkmgq(var2, Items.TORCH) < 32 && xaavk81ye4wra(var2, Items.TORCH); var3++) {
         Optional var4 = x2n55y2tom37h2.x3lxwdirnn77qc(var1, this.xy1q9c188jgfl, var0 -> var0.isOf(Items.TORCH));
         if (var4.isPresent()) {
            var2.getInventory().addStack((ItemStack)var4.get());
         } else {
            Predicate<ItemStack> var5 = var0 -> var0.isIn(ItemTags.LOGS);
            Predicate<ItemStack> var6 = var0 -> var0.isOf(Items.COAL) || var0.isOf(Items.CHARCOAL);
            if (x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var5) >= 1 && x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var6) >= 1) {
               x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var5, 1);
               x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var6, 1);
            } else if (!x2n55y2tom37h2.x22hjebj4vmztt(var1, this.xy1q9c188jgfl, var5, 2)) {
               break;
            }

            ItemStack var7 = var2.getInventory().addStack(new ItemStack(Items.TORCH, 4));
            if (!var7.isEmpty()) {
               x2fl60kuzia5xk.xqe8twcjwxn55(var1, this.xy1q9c188jgfl, var2, var7);
            }
         }
      }
   }

   private void x1medszyn1khgr(ServerWorld var1, VillagerEntity var2) {
      int var3 = xncu5r00mkmgq(var2, Items.COBBLESTONE);
      if (var3 < 16) {
         x2n55y2tom37h2.x1dhbpietunk9i(var1, this.xy1q9c188jgfl, var2, var0 -> var0.isOf(Items.COBBLESTONE))
            .flatMap(var2x -> x2n55y2tom37h2.x5jd7014csotp(var1, var2x, var0x -> var0x.isOf(Items.COBBLESTONE), 16 - var3))
            .ifPresent(var1x -> var2.getInventory().addStack(var1x));
      }
   }

   private boolean x1npl0tf7hnjwg(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      BlockPos var4 = var3.down();
      if (!this.x2rtvy1700fh69(var3)
         && var1.getFluidState(var3).isEmpty()
         && var1.getFluidState(var3.up()).isEmpty()
         && var1.getFluidState(var4).isEmpty()
         && xe9s6y96sy1nw(var1, var4)) {
         boolean var5 = false;

         for (Direction var7 : Type.HORIZONTAL) {
            if (var1.getBlockState(var4.offset(var7)).isSideSolidFullSquare(var1, var4.offset(var7), var7.getOpposite())) {
               var5 = true;
               break;
            }
         }

         if (!var5) {
            return false;
         } else {
            for (int var8 = 0; var8 < var2.getInventory().size(); var8++) {
               ItemStack var9 = var2.getInventory().getStack(var8);
               if (var9.isOf(Items.COBBLESTONE) && x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var4, Blocks.COBBLESTONE.getDefaultState(), 3)) {
                  var9.decrement(1);
                  var2.swingHand(Hand.MAIN_HAND);
                  this.x1lbhfpli59w62 = var1.getServer().getTicks();
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private boolean x3kfcd4ju4t319(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      for (BlockPos var5 : BlockPos.iterate(var3.add(-4, -2, -4), var3.add(4, 3, 4))) {
         if (var1.getBlockState(var5).isOf(Blocks.TORCH) || var1.getBlockState(var5).isOf(Blocks.WALL_TORCH)) {
            return true;
         }
      }

      if (var1.getBlockState(var3).isAir() && Blocks.TORCH.getDefaultState().canPlaceAt(var1, var3)) {
         for (int var6 = 0; var6 < var2.getInventory().size(); var6++) {
            ItemStack var7 = var2.getInventory().getStack(var6);
            if (var7.isOf(Items.TORCH) && x1dxdlnevtfbhm.x1bq0f8n53gtob(var1, var3, Blocks.TORCH.getDefaultState(), 3)) {
               var7.decrement(1);
               var2.swingHand(Hand.MAIN_HAND);
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private void xwknhpp7tkplu(VillagerEntity var1, long var2) {
      Vec3d var4 = var1.getEntityPos();
      if (this.x1vlro08p2yyfr == null || this.x1vlro08p2yyfr.squaredDistanceTo(var4) >= 16.0) {
         this.x1vlro08p2yyfr = var4;
         this.xhvgstwe661u5 = var2;
      }
   }

   private boolean x2xwjgtx7cwwvg(ServerWorld var1, VillagerEntity var2, long var3) {
      for (Direction var8 : Direction.values()) {
         BlockPos var9 = var2.getBlockPos().offset(var8);
         if (var8 != Direction.DOWN) {
            BlockState var10 = var1.getBlockState(var9);
            if (x1pn4sqnjt1xiv(var10) && this.x2koaf0y8uk4zf(var1, var9) && x2pv6ejqyq6ou5(var2, var10)) {
               List<ItemStack> var11 = Block.getDroppedStacks(var10, var1, var9, null, var2, var2.getMainHandStack());
               if (!x3b13cjb6ycs5u(var2, var11)) {
                  this.xhzkfb8tfrmu6(var2);
                  return true;
               }

               this.x2idgvhct1beze(var2, var9);
               if (!this.x1lb0jykmu89gj.xpohy1q30uxlk(var1, var2, var9, x20lc7jle5mw9c.x20ocn113pbk5f(var1, var2, var9), true)) {
                  return true;
               }

               if (x1dxdlnevtfbhm.x231if4rvsvptj(var1, var9, false, var2)) {
                  var11.forEach(var1x -> var2.getInventory().addStack(var1x));
                  var2.swingHand(Hand.MAIN_HAND);
                  this.x35hjgfi0ny5oi++;
                  this.x1lbhfpli59w62 = var3;
                  this.x9380ui9wkv3h(var1, var9);
               }

               return true;
            }
         }
      }

      return false;
   }

   private void x2ibslq4rhdko0(ServerWorld var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < 8; var3++) {
         int var4 = Math.floorMod(this.xc9ckqw7lv8b1++, 2401);
         int var5 = var4 % 49 - 24;
         int var6 = var4 / 49 - 24;
         if (Math.max(Math.abs(var5), Math.abs(var6)) >= 14) {
            int var7 = this.xy1q9c188jgfl.core().getX() + var5;
            int var8 = this.xy1q9c188jgfl.core().getZ() + var6;
            if (var1.getChunkManager().isChunkLoaded(var7 >> 4, var8 >> 4)) {
               BlockPos var9 = new BlockPos(var7, var1.getTopY(net.minecraft.world.Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, var7, var8), var8);
               if (xe9s6y96sy1nw(var1, var9) && xe9s6y96sy1nw(var1, var9.up()) && this.x2koaf0y8uk4zf(var1, var9.down())) {
                  boolean var10 = true;

                  for (BlockPos var12 : BlockPos.iterate(var9.add(-3, -1, -3), var9.add(3, 2, 3))) {
                     BlockState var13 = var1.getBlockState(var12);
                     if (var13.isOf(Blocks.DIRT_PATH)
                        || var1.getBlockEntity(var12) != null
                        || !var13.isAir() && !var13.isReplaceable() && !x2ql14ex7ui8sk(var13) && !var13.isIn(BlockTags.LEAVES)) {
                        var10 = false;
                        break;
                     }
                  }

                  if (var10 && this.x378g3z86pefiu(var1, var2, var9)) {
                     this.xy1q9c188jgfl.x31ow2tlg0dm5w().add(var9);
                     this.xy1q9c188jgfl.xmskodayog7rh(Math.abs(var5) > Math.abs(var6) ? (var5 > 0 ? 1 : 3) : (var6 > 0 ? 2 : 0));
                     x2n55y2tom37h2.xchl1k1tnh3w8(var1).markDirty();
                     this.x1lbhfpli59w62 = var1.getServer().getTicks();
                     return;
                  }
               }
            }
         }
      }
   }

   private boolean x2clgblb4ftzb2(ServerWorld var1, VillagerEntity var2) {
      for (int var3 = 0; var3 < 512; var3++) {
         if (this.x3hlg4mlj93nm0(var1, var2, var2.getBlockPos().add((Vec3i)x1fgwiff13cmf2.get(var3)))) {
            return true;
         }
      }

      if (this.x3ima85yg8u7pt == null || this.xtas7exwnkweh >= x1fgwiff13cmf2.size() || this.x3ima85yg8u7pt.getSquaredDistance(var2.getBlockPos()) > 1024.0) {
         this.x3ima85yg8u7pt = var2.getBlockPos();
         this.xtas7exwnkweh = 0;
      }

      for (int var5 = 0; var5 < 1024 && this.xtas7exwnkweh < x1fgwiff13cmf2.size(); var5++) {
         BlockPos var4 = this.x3ima85yg8u7pt.add((Vec3i)x1fgwiff13cmf2.get(this.xtas7exwnkweh++));
         if (this.x3hlg4mlj93nm0(var1, var2, var4)) {
            return true;
         }
      }

      return false;
   }

   private boolean x3hlg4mlj93nm0(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (this.x1mcvl1k31xh98.getOrDefault(var3, 0L) > var1.getServer().getTicks()) {
         return false;
      } else if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var3.up()) && !this.x1m6docvvu7vog.contains(var3.up()) && !this.x2rtvy1700fh69(var3)) {
         if (!var1.getChunkManager().isChunkLoaded(var3.getX() >> 4, var3.getZ() >> 4)) {
            return false;
         } else {
            BlockState var4 = var1.getBlockState(var3);
            if (!x1pn4sqnjt1xiv(var4) || !this.x2koaf0y8uk4zf(var1, var3) || !x2pv6ejqyq6ou5(var2, var4)) {
               return false;
            } else if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 20) {
               return false;
            } else {
               if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty() && this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() < 32) {
                  BlockPos var5 = nextStep(this.xy1q9c188jgfl, var1.getBottomY());
                  if (var3.equals(var5) || var3.equals(var5.up()) || var3.equals(var5.up(2))) {
                     return false;
                  }
               }

               for (Direction var6 : Type.HORIZONTAL) {
                  BlockPos var7 = var3.offset(var6);
                  if (xe9s6y96sy1nw(var1, var7)
                     && xe9s6y96sy1nw(var1, var7.up())
                     && x19i92dflbkly4(var1, var7.down())
                     && (var7.getSquaredDistance(var2.getBlockPos()) <= 4.0 || this.x378g3z86pefiu(var1, var2, var7))) {
                     this.x39r1px550mewh = var3;
                     this.x1co9a4owy6j0p = var7;
                     return true;
                  }
               }

               boolean var9 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
                  || var2.getBlockPos().getSquaredDistance((Vec3i)this.xy1q9c188jgfl.x31ow2tlg0dm5w().getLast()) <= 9.0
                  || !this.x1m6docvvu7vog.isEmpty() && var2.getBlockPos().getSquaredDistance((Vec3i)this.x1m6docvvu7vog.getLast()) <= 9.0;
               boolean var10 = var4.isOf(Blocks.IRON_ORE)
                  || var4.isOf(Blocks.COPPER_ORE)
                  || var4.isOf(Blocks.COAL_ORE)
                     && this.x35hjgfi0ny5oi < 16
                     && var3.getSquaredDistance(var2.getBlockPos()) <= 64.0
                     && x2n55y2tom37h2.x1mlne8tl8k5ag(var1, this.xy1q9c188jgfl, var0 -> var0.isOf(Items.COAL)) < 8;
               if (var9
                  && (var2.getBlockY() <= 48 || var10)
                  && var3.getY() <= var2.getBlockY() + 4
                  && (this.x1pyrwt4gvtvim == null || var3.getSquaredDistance(var2.getBlockPos()) < this.x1pyrwt4gvtvim.getSquaredDistance(var2.getBlockPos()))) {
                  this.x1pyrwt4gvtvim = var3.toImmutable();
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static boolean x1pn4sqnjt1xiv(BlockState var0) {
      return Registries.BLOCK.getId(var0.getBlock()).getPath().endsWith("_ore");
   }

   private BlockPos x7zvchtwcbs2l(BlockPos var1, BlockPos var2) {
      int var3 = var2.getX() - var1.getX();
      int var4 = var2.getZ() - var1.getZ();
      if (var3 == 0 && var4 == 0) {
         if (var2.getY() == var1.getY()) {
            return null;
         } else {
            int var9 = var2.getY() > var1.getY() ? 1 : -1;

            for (Direction var7 : Type.HORIZONTAL) {
               BlockPos var8 = var1.offset(var7).add(0, var9, 0);
               if (!this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var8) && !this.x1m6docvvu7vog.contains(var8)) {
                  return var8;
               }
            }

            return null;
         }
      } else {
         Direction var5 = Math.abs(var3) >= Math.abs(var4) ? (var3 > 0 ? Direction.EAST : Direction.WEST) : (var4 > 0 ? Direction.SOUTH : Direction.NORTH);
         return var1.offset(var5).add(0, var2.getY() < var1.getY() - 1 ? -1 : (var2.getY() > var1.getY() + 1 ? 1 : 0), 0);
      }
   }

   private static List<BlockPos> x3cil34rc17d8m() {
      List<BlockPos> var0 = new ArrayList<>();

      for (int var1 = -32; var1 <= 32; var1++) {
         for (int var2 = -12; var2 <= 12; var2++) {
            for (int var3 = -32; var3 <= 32; var3++) {
               var0.add(new BlockPos(var1, var2, var3));
            }
         }
      }

      var0.sort(Comparator.comparingDouble(var0x -> var0x.getSquaredDistance(BlockPos.ORIGIN)));
      return List.copyOf(var0);
   }

   private boolean x27hg6cma7yaw5(ServerWorld var1, VillagerEntity var2) {
      while (!this.x26z6s4l7xpegw.isEmpty()) {
         BlockPos var3 = this.x26z6s4l7xpegw.removeFirst();
         if (x1pn4sqnjt1xiv(var1.getBlockState(var3)) && !this.x2rtvy1700fh69(var3)) {
            if (this.x3hlg4mlj93nm0(var1, var2, var3)) {
               return true;
            }

            if (this.x1pyrwt4gvtvim != null) {
               return false;
            }
         }
      }

      return false;
   }

   private void x9380ui9wkv3h(ServerWorld var1, BlockPos var2) {
      for (int var3 = -1; var3 <= 1; var3++) {
         for (int var4 = -1; var4 <= 1; var4++) {
            for (int var5 = -1; var5 <= 1; var5++) {
               if (var3 != 0 || var4 != 0 || var5 != 0) {
                  BlockPos var6 = var2.add(var3, var4, var5).toImmutable();
                  if (x1pn4sqnjt1xiv(var1.getBlockState(var6)) && !this.x26z6s4l7xpegw.contains(var6)) {
                     this.x26z6s4l7xpegw.addLast(var6);
                  }
               }
            }
         }
      }
   }

   private boolean x3kbhcj0gn3eds(ServerWorld var1, BlockPos var2) {
      while (!this.x1wi4zdglz9eff.isEmpty()) {
         BlockPos var14 = this.x1wi4zdglz9eff.removeFirst();
         if (!xe9s6y96sy1nw(var1, var14) && this.x2koaf0y8uk4zf(var1, var14)) {
            this.x39r1px550mewh = var14;
            this.x1co9a4owy6j0p = var2;
            this.x2b8i0pzdq33m8(var14);
            return true;
         }
      }

      int var3 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size();
      if (var3 >= 2 && this.x3alovr2tshkm1 != var3) {
         this.x3alovr2tshkm1 = var3;
         BlockPos var4 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var3 - 2);
         int var5 = Integer.compare(var2.getX(), var4.getX());
         int var6 = Integer.compare(var2.getZ(), var4.getZ());
         if (var5 == 0 && var6 == 0) {
            return false;
         } else {
            int var7 = -var6;
            int var8 = var5;

            for (int var12 : new int[]{-1, 1}) {
               for (int var13 = 0; var13 <= 1; var13++) {
                  this.x1wi4zdglz9eff.addLast(var2.add(var7 * var12, var13, var8 * var12).toImmutable());
               }
            }

            return this.x3kbhcj0gn3eds(var1, var2);
         }
      } else {
         return false;
      }
   }

   private void x2b8i0pzdq33m8(BlockPos var1) {
      this.x39r1px550mewh = var1;
   }

   private boolean xesfei3h73qdo(ServerWorld var1, BlockPos var2) {
      if (var1.getChunkManager().isChunkLoaded(var2.getX() >> 4, var2.getZ() >> 4) && x19i92dflbkly4(var1, var2.down())) {
         for (int var3 = 0; var3 <= 2; var3++) {
            if (!xe9s6y96sy1nw(var1, var2.up(var3)) && !this.x2koaf0y8uk4zf(var1, var2.up(var3))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean x2koaf0y8uk4zf(ServerWorld var1, BlockPos var2) {
      if (this.xy1q9c188jgfl.x31ow2tlg0dm5w().contains(var2.up()) || this.x1m6docvvu7vog.contains(var2.up())) {
         return false;
      } else if (!x1dxdlnevtfbhm.x13f7be5vlapn7(var1, var2)) {
         return false;
      } else if (x20rghnsl2yowl.x239t1m3hfi74d(var1, var2)) {
         return false;
      } else if (x2ql14ex7ui8sk(var1.getBlockState(var2)) && var1.getBlockEntity(var2) == null) {
         for (Direction var6 : Direction.values()) {
            BlockPos var7 = var2.offset(var6);
            if (!var1.getChunkManager().isChunkLoaded(var7.getX() >> 4, var7.getZ() >> 4)
               || !var1.getFluidState(var7).isEmpty()
               || var6 == Direction.UP && (var1.getBlockState(var7).isOf(Blocks.GRAVEL) || var1.getBlockState(var7).isIn(BlockTags.SAND))) {
               return false;
            }
         }

         for (x2arpn38dclo1g var9 : this.xy1q9c188jgfl.x1ja61emeln2hc()) {
            BlockBox var10 = var9.x24kbqv6g4kkrg();
            if (var2.getY() >= var10.getMinY() - 5
               && var2.getX() >= var10.getMinX() - 3
               && var2.getX() <= var10.getMaxX() + 3
               && var2.getZ() >= var10.getMinZ() - 3
               && var2.getZ() <= var10.getMaxZ() + 3) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean x2ql14ex7ui8sk(BlockState var0) {
      return x1dxdlnevtfbhm.xroinv4hk6l7h(var0) || x1pn4sqnjt1xiv(var0);
   }

   private static boolean xe9s6y96sy1nw(ServerWorld var0, BlockPos var1) {
      return var0.getFluidState(var1).isEmpty() && var0.getBlockState(var1).getCollisionShape(var0, var1).isEmpty();
   }

   private static boolean x19i92dflbkly4(ServerWorld var0, BlockPos var1) {
      return var0.getFluidState(var1).isEmpty() && var0.getBlockState(var1).isSideSolidFullSquare(var0, var1, Direction.UP);
   }

   private boolean x2rtvy1700fh69(BlockPos var1) {
      double var2 = (double)var1.getX() - this.xy1q9c188jgfl.core().getX();
      double var4 = (double)var1.getZ() - this.xy1q9c188jgfl.core().getZ();
      return var2 * var2 + var4 * var4 > (double)(this.xy1q9c188jgfl.radius() - 4) * (this.xy1q9c188jgfl.radius() - 4);
   }

   private static boolean x2pv6ejqyq6ou5(VillagerEntity var0, BlockState var1) {
      ItemStack var2 = var0.getMainHandStack();
      boolean var3 = var2.isOf(Items.IRON_PICKAXE) || var2.isOf(Items.DIAMOND_PICKAXE) || var2.isOf(Items.NETHERITE_PICKAXE);
      boolean var4 = var3 || var2.isOf(Items.STONE_PICKAXE);
      String var5 = Registries.BLOCK.getId(var1.getBlock()).getPath();
      if (var5.contains("diamond") || var5.contains("gold") || var5.contains("redstone") || var5.contains("emerald")) {
         return var3;
      } else {
         return !var5.contains("iron") && !var5.contains("copper") ? true : var4;
      }
   }

   private static Item x142qgo06bbcrm(BlockState var0) {
      String var1 = Registries.BLOCK.getId(var0.getBlock()).getPath();
      if (var1.contains("diamond")) {
         return Items.DIAMOND;
      } else if (var1.contains("emerald")) {
         return Items.EMERALD;
      } else if (var1.contains("redstone")) {
         return Items.REDSTONE;
      } else if (var1.contains("lapis")) {
         return Items.LAPIS_LAZULI;
      } else if (var1.contains("copper")) {
         return Items.RAW_COPPER;
      } else if (var1.contains("gold")) {
         return Items.RAW_GOLD;
      } else if (var1.contains("iron")) {
         return Items.RAW_IRON;
      } else if (var1.contains("coal")) {
         return Items.COAL;
      } else if (var0.isIn(BlockTags.SAND)
         || var0.isIn(BlockTags.TERRACOTTA)
         || var0.isOf(Blocks.GRAVEL)
         || var0.isOf(Blocks.SANDSTONE)
         || var0.isOf(Blocks.RED_SANDSTONE)
         || var0.isOf(Blocks.SNOW_BLOCK)) {
         return var0.getBlock().asItem();
      } else if (var0.isOf(Blocks.DEEPSLATE)) {
         return Items.COBBLED_DEEPSLATE;
      } else {
         return var0.isIn(BlockTags.DIRT) ? Items.DIRT : Items.COBBLESTONE;
      }
   }

   private static boolean x3suldnm75afk3(ItemStack var0) {
      return !var0.isEmpty() && !var0.isIn(ItemTags.PICKAXES) && !var0.isOf(Items.TORCH) && x2n55y2tom37h2.xhahxgpa08yc9(var0) == 0;
   }

   private static int xncu5r00mkmgq(VillagerEntity var0, Item var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.getInventory().size(); var3++) {
         if (var0.getInventory().getStack(var3).isOf(var1)) {
            var2 += var0.getInventory().getStack(var3).getCount();
         }
      }

      return var2;
   }

   private static int x215m1zmqihw78(VillagerEntity var0) {
      return xncu5r00mkmgq(var0, Items.RAW_IRON)
         + xncu5r00mkmgq(var0, Items.RAW_COPPER)
         + xncu5r00mkmgq(var0, Items.RAW_GOLD)
         + xncu5r00mkmgq(var0, Items.DIAMOND)
         + xncu5r00mkmgq(var0, Items.EMERALD)
         + xncu5r00mkmgq(var0, Items.REDSTONE)
         + xncu5r00mkmgq(var0, Items.LAPIS_LAZULI)
         + xncu5r00mkmgq(var0, Items.COAL);
   }

   private static boolean xaavk81ye4wra(VillagerEntity var0, Item var1) {
      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         ItemStack var3 = var0.getInventory().getStack(var2);
         if (var3.isEmpty() || var3.isOf(var1) && var3.getCount() < var3.getMaxCount()) {
            return true;
         }
      }

      return false;
   }

   private boolean x378g3z86pefiu(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      if (!x2n55y2tom37h2.x5ll9elqpk1a4(var1)) {
         return false;
      } else {
         Path var4 = var2.getNavigation().findPathTo(var3, 0);
         return var4 != null && var4.reachesTarget();
      }
   }

   private void x2qi4jek408dcr(ServerWorld var1, VillagerEntity var2, BlockPos var3) {
      long var4 = var1.getServer().getTicks();
      if (this.x2qljtijql8vba != null && var4 < this.x1bhavtwhoa4l5) {
         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, this.x2qljtijql8vba, 0.85);
      } else {
         if (!var3.equals(this.x5s8pk56x49d)) {
            this.x5s8pk56x49d = var3.toImmutable();
            this.x1vajhas144pja = Double.MAX_VALUE;
            this.x2c83xgjkd1pgw = var4;
         }

         double var6 = var2.squaredDistanceTo(var3.getX() + 0.5, var3.getY(), var3.getZ() + 0.5);
         if (var6 + 0.5 < this.x1vajhas144pja) {
            this.x1vajhas144pja = var6;
            this.x2c83xgjkd1pgw = var4;
         }

         if (var4 - this.x2c83xgjkd1pgw > 60L
            && var2.getBlockPos().getSquaredDistance(var3) <= 16.0
            && !var1.getOtherEntities(var2, var2.getBoundingBox().expand(1.2), var1x -> var1x instanceof VillagerEntity && var1x.getId() < var2.getId())
               .isEmpty()) {
            for (Direction var9 : Type.HORIZONTAL) {
               BlockPos var10 = var2.getBlockPos().offset(var9);
               if (!var10.equals(var3)
                  && x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var10)
                  && var1.getOtherEntities(var2, new Box(var10).expand(0.1), var0 -> var0 instanceof LivingEntity).isEmpty()) {
                  this.x2qljtijql8vba = var10;
                  this.x1bhavtwhoa4l5 = var4 + 40L;
                  x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var10, 0.85);
                  return;
               }
            }
         }

         x1c1r5c6a13gtl.x2wzylgizxltef(var1, var2, var3, 1.05);
         if (this.x1ajk4ev1s3vmk == xmq1yravtjp1k.xdrr1h46oexk3 && var4 - this.x2c83xgjkd1pgw > 400L) {
            if (this.xdyvztdlmgr2v == 0 && this.x39r1px550mewh == null && xocihbcrvdo4w(var2) == 0 && this.x1xbvmwg584cth(var1, var2)) {
               return;
            }

            if (this.x39r1px550mewh != null) {
               this.x1mcvl1k31xh98.put(this.x39r1px550mewh, var4 + 1200L);
            }

            if (this.x1pyrwt4gvtvim != null) {
               this.x1mcvl1k31xh98.put(this.x1pyrwt4gvtvim, var4 + 6000L);
               this.x1pyrwt4gvtvim = null;
               this.x26z6s4l7xpegw.clear();
               this.xgxy3geynmx1k = false;
            }

            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "недоступный забой: возврат с грузом");
            this.xhzkfb8tfrmu6(var2);
         }
      }
   }

   private boolean x1xbvmwg584cth(ServerWorld var1, VillagerEntity var2) {
      int var3 = Math.max(0, this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 8);

      for (int var4 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().size() - 1; var4 >= var3; var4--) {
         BlockPos var5 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var4);
         if (x1c1r5c6a13gtl.x9bnd6e2jczhd(var1, var5)
            && var1.getOtherEntities(var2, new Box(var5).expand(0.35), var0 -> var0.isAlive() && var0 instanceof LivingEntity).isEmpty()) {
            var2.getNavigation().stop();
            var2.refreshPositionAndAngles(var5.getX() + 0.5, var5.getY(), var5.getZ() + 0.5, var2.getYaw(), var2.getPitch());
            var2.setVelocity(Vec3d.ZERO);
            var2.onLanding();
            this.xdyvztdlmgr2v = var4;
            this.x3034xvdt9o8yg = var4;
            this.x5s8pk56x49d = null;
            this.x2c83xgjkd1pgw = this.x1lbhfpli59w62 = var1.getServer().getTicks();
            x2vaj7rsm57i4n.x1qd0mutwsr3sc(var2, "восстановление доступа к шахтному маршруту");
            return true;
         }
      }

      return false;
   }

   private static boolean x1r7nkw0jt7ym0(VillagerEntity var0, BlockPos var1) {
      return Math.abs(var0.getY() - var1.getY()) < 0.6 && var0.squaredDistanceTo(var1.getX() + 0.5, var1.getY(), var1.getZ() + 0.5) < 1.3;
   }

   private static boolean x3b13cjb6ycs5u(VillagerEntity var0, List<ItemStack> var1) {
      SimpleInventory var2 = new SimpleInventory(var0.getInventory().size());

      for (int var3 = 0; var3 < var2.size(); var3++) {
         var2.setStack(var3, var0.getInventory().getStack(var3).copy());
      }

      for (ItemStack var4 : var1) {
         if (!var2.addStack(var4.copy()).isEmpty()) {
            return false;
         }
      }

      return true;
   }

   private int x28tnsle11zyqe(VillagerEntity var1) {
      int var2 = 0;
      double var3 = Double.MAX_VALUE;

      for (int var5 = 0; var5 < this.xy1q9c188jgfl.x31ow2tlg0dm5w().size(); var5++) {
         double var6 = this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(var5).getSquaredDistance(var1.getBlockPos());
         if (var6 < var3) {
            var3 = var6;
            var2 = var5;
         }
      }

      return var2;
   }

   private void xhzkfb8tfrmu6(VillagerEntity var1) {
      this.xgxy3geynmx1k = false;
      this.x342hqz5dxukyd = Math.max(0, this.x1m6docvvu7vog.size() - 1);
      this.x1ajk4ev1s3vmk = xmq1yravtjp1k.x3s2uqlmkylaf8;
      this.x39r1px550mewh = null;
      this.x5s8pk56x49d = null;
      this.x340ppe230jyu = this.x1m6docvvu7vog.isEmpty() ? this.x28tnsle11zyqe(var1) : Math.max(0, this.x2bkom4nbrpupj);
      if (this.x1m6docvvu7vog.isEmpty()
         && !this.xy1q9c188jgfl.x31ow2tlg0dm5w().isEmpty()
         && this.xy1q9c188jgfl.x31ow2tlg0dm5w().get(this.x340ppe230jyu).getSquaredDistance(var1.getBlockPos()) > 9.0) {
         this.x340ppe230jyu = 0;
      }

      this.xhvgstwe661u5 = ((ServerWorld)var1.getEntityWorld()).getServer().getTicks();
      this.x1uepbjw0wv8fc = Double.MAX_VALUE;
      this.x1vlro08p2yyfr = var1.getEntityPos();
   }

   private void x2idgvhct1beze(VillagerEntity var1, BlockPos var2) {
      var1.getBrain().remember(xxao083dbo7ag.x15kki2mjvh7tw, var2);
   }

   private static int xocihbcrvdo4w(VillagerEntity var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < var0.getInventory().size(); var2++) {
         ItemStack var3 = var0.getInventory().getStack(var2);
         if (x3suldnm75afk3(var3)) {
            var1 += var3.getCount();
         }
      }

      return var1;
   }

   public String debugState(VillagerEntity var1) {
      return (this.x1ajk4ev1s3vmk == null ? "ожидание" : this.x1ajk4ev1s3vmk.name().toLowerCase(Locale.ROOT))
         + ", выкопано="
         + this.x35hjgfi0ny5oi
         + ", груз="
         + xocihbcrvdo4w(var1)
         + ", руда="
         + x215m1zmqihw78(var1)
         + ", маршрут="
         + (this.xy1q9c188jgfl == null ? 0 : this.xy1q9c188jgfl.x31ow2tlg0dm5w().size())
         + ", точка="
         + this.xdyvztdlmgr2v
         + ", пропущено="
         + this.x1mcvl1k31xh98.size()
         + ", target="
         + this.x39r1px550mewh
         + ", stand="
         + this.x1co9a4owy6j0p
         + ", ore="
         + this.x1pyrwt4gvtvim
         + ", detour="
         + this.x1m6docvvu7vog.size()
         + ", return="
         + this.x340ppe230jyu;
   }

   protected void finishRunning(ServerWorld var1, VillagerEntity var2, long var3) {
      this.x1lb0jykmu89gj.xupxzzyodxtwj(var1, var2);
      var2.getNavigation().stop();
      var2.getBrain().forget(xxao083dbo7ag.x15kki2mjvh7tw);
      this.xy1q9c188jgfl = null;
      this.x39r1px550mewh = null;
   }
}
