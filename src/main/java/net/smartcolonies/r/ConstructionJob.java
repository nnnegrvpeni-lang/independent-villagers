package net.smartcolonies.r;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public final class ConstructionJob {
   private final UUID x30g64uq6npagv;
   private final Identifier x2lhubtwtapkxl;
   private final BlockPos x79ihhcocnyk1;
   private final int x5x75nz0elcmr;
   private final int x1wuggvufpo7hz;
   private final int x82v6jq0p0uao;
   private UUID x2yls3kck49fqo;
   private int x27hzctmw7oy9z;
   private int x2h56s5xwkzifr;
   private int x5g93fvp62vx7;
   private long x6m27u57uvw29;
   private ConstructionStatus x1jlq787lmscj6;
   private long x2pyv5aa4h53xv;
   private int x3affjeqf49yv1;
   private long xso2wmmiyc66d = Long.MIN_VALUE;
   private UUID x1m95xojpiieh4;
   private int x165uev4yx79br;
   private boolean xhgwli2r8opws;
   private final Map<UUID, Long> x1oaaxvbq4ab5j = new HashMap<>();
   private boolean xmcr06aulcksa;

   public boolean xxo2yeczi7qrw(UUID var1, long var2) {
      this.x1oaaxvbq4ab5j.entrySet().removeIf(var2x -> var2x.getValue() <= var2);
      return !this.x1oaaxvbq4ab5j.containsKey(var1);
   }

   public void x1pry0l0yrsmkc(UUID var1, long var2) {
      this.x1oaaxvbq4ab5j.put(var1, var2);
   }

   public long x2zq2smhbu3lzo() {
      return this.x2pyv5aa4h53xv;
   }

   public int xbnccd29nwem4() {
      return this.x3affjeqf49yv1;
   }

   public void x2hk920e3qof5e(long var1) {
      this.x1jlq787lmscj6 = ConstructionStatus.x3vi306qg1ms75;
      this.x2yls3kck49fqo = null;
      this.x2pyv5aa4h53xv = var1;
   }

   public void x1ogar57a8j8ls() {
      this.x1jlq787lmscj6 = ConstructionStatus.x2tgnsmc98zm7p;
      this.x2yls3kck49fqo = null;
      this.x2pyv5aa4h53xv = 0L;
   }

   public void x3feu6p476x7c(long var1) {
      if (this.xso2wmmiyc66d != var1) {
         this.x3affjeqf49yv1 = Math.min(1200, this.x3affjeqf49yv1 + 20);
         this.xso2wmmiyc66d = var1;
      }
   }

   public void x1rzj1yojiksff() {
      this.x3affjeqf49yv1 = 0;
      this.xso2wmmiyc66d = Long.MIN_VALUE;
   }

   public ConstructionJob(UUID var1, Identifier var2, BlockPos var3) {
      this(var1, var2, var3, 1, 1, 1, null, 0, 0, Long.MIN_VALUE, ConstructionStatus.x2tgnsmc98zm7p, 0, null, 0);
   }

   public ConstructionJob(UUID var1, Identifier var2, BlockPos var3, int var4, int var5, int var6) {
      this(var1, var2, var3, var4, var5, var6, null, 0, 0, Long.MIN_VALUE, ConstructionStatus.x2tgnsmc98zm7p, 0, null, 0);
   }

   public ConstructionJob(UUID var1, Identifier var2, BlockPos var3, int var4, int var5, int var6, UUID var7, int var8) {
      this(var1, var2, var3, var4, var5, var6, null, 0, 0, Long.MIN_VALUE, ConstructionStatus.x2tgnsmc98zm7p, 0, var7, Math.clamp((long)var8, 0, 3));
   }

   private ConstructionJob(
      UUID var1,
      Identifier var2,
      BlockPos var3,
      int var4,
      int var5,
      int var6,
      UUID var7,
      int var8,
      int var9,
      long var10,
      ConstructionStatus var12,
      int var13,
      UUID var14,
      int var15
   ) {
      this.x30g64uq6npagv = var1;
      this.x2lhubtwtapkxl = var2;
      this.x79ihhcocnyk1 = var3.toImmutable();
      this.x5x75nz0elcmr = Math.max(1, var4);
      this.x1wuggvufpo7hz = Math.max(1, var5);
      this.x82v6jq0p0uao = Math.max(1, var6);
      this.x2yls3kck49fqo = var7;
      this.x27hzctmw7oy9z = Math.max(0, var8);
      this.x2h56s5xwkzifr = Math.max(0, var9);
      this.x5g93fvp62vx7 = Math.max(0, var13);
      this.x6m27u57uvw29 = var10;
      this.x1jlq787lmscj6 = var12;
      this.x1m95xojpiieh4 = var14;
      this.x165uev4yx79br = Math.clamp((long)var15, 0, 3);
   }

   public UUID id() {
      return this.x30g64uq6npagv;
   }

   public boolean x3fgv5ft5sp7zt() {
      return this.xmcr06aulcksa;
   }

   public void xkv5b17xl7vd6(boolean var1) {
      this.xmcr06aulcksa = var1;
   }

   public Identifier x3kqe1qjsw9oty() {
      return this.x2lhubtwtapkxl;
   }

   public BlockPos x3ij6gescvakv2() {
      return this.x79ihhcocnyk1;
   }

   public Optional<UUID> x3vu8mnvg0blk4() {
      return Optional.ofNullable(this.x2yls3kck49fqo);
   }

   public int xmu75rcopbjnh() {
      return this.x27hzctmw7oy9z;
   }

   public int xxjwqetbgzzle() {
      return this.x2h56s5xwkzifr;
   }

   public long x16o43azbz1opa() {
      return this.x6m27u57uvw29;
   }

   public ConstructionStatus x32oz43hmeynhc() {
      return this.x1jlq787lmscj6;
   }

   public Optional<UUID> x2nfrt8qxdscso() {
      return Optional.ofNullable(this.x1m95xojpiieh4);
   }

   public int x37hzrzyr2xivs() {
      return this.x165uev4yx79br;
   }

   public boolean x2s8emavervqrz() {
      return this.xhgwli2r8opws || this.x1jlq787lmscj6 == ConstructionStatus.xqfd7s4y4gux6;
   }

   public BlockBox x24kbqv6g4kkrg() {
      return new BlockBox(
         this.x79ihhcocnyk1.getX(),
         this.x79ihhcocnyk1.getY(),
         this.x79ihhcocnyk1.getZ(),
         this.x79ihhcocnyk1.getX() + this.x5x75nz0elcmr - 1,
         this.x79ihhcocnyk1.getY() + this.x1wuggvufpo7hz - 1,
         this.x79ihhcocnyk1.getZ() + this.x82v6jq0p0uao - 1
      );
   }

   public void x208u8540wth7a(UUID var1) {
      this.x2yls3kck49fqo = var1;
      if (this.x1jlq787lmscj6 == ConstructionStatus.x2tgnsmc98zm7p) {
         this.x1jlq787lmscj6 = ConstructionStatus.x1mzkgtgadv3jh;
      }
   }

   public void xkvyd96wj0qui(UUID var1, long var2) {
      if (!var1.equals(this.x2yls3kck49fqo)) {
         this.x6m27u57uvw29 = var2;
      }

      this.x208u8540wth7a(var1);
   }

   public void x23bey8uos6gyt() {
      this.x2yls3kck49fqo = null;
      if (this.x1jlq787lmscj6 == ConstructionStatus.x1mzkgtgadv3jh) {
         this.x1jlq787lmscj6 = ConstructionStatus.x2tgnsmc98zm7p;
      }
   }

   public void x29hoj3w31nt46(int var1, long var2) {
      if (this.x2h56s5xwkzifr == 0) {
         this.x27hzctmw7oy9z = 0;
      }

      this.x2h56s5xwkzifr = var1;
      if (this.x6m27u57uvw29 == Long.MIN_VALUE) {
         this.x6m27u57uvw29 = var2;
      }
   }

   public void x248ps383cwu1s(int var1, long var2, int var4) {
      if (this.x5g93fvp62vx7 != var4) {
         this.x27hzctmw7oy9z = 0;
         this.x6m27u57uvw29 = var2;
         this.x5g93fvp62vx7 = var4;
      }

      this.x29hoj3w31nt46(var1, var2);
   }

   public void x26iyzb2d5j0bx() {
      this.x27hzctmw7oy9z++;
   }

   public void xdhrxq42ir75w(long var1) {
      this.x27hzctmw7oy9z++;
      this.x6m27u57uvw29 = var1;
   }

   public void x3lcv8vebl7uhh() {
      this.x1jlq787lmscj6 = ConstructionStatus.xqfd7s4y4gux6;
      this.xhgwli2r8opws = true;
   }

   public void x17m4m683lqbv9() {
      this.x1jlq787lmscj6 = ConstructionStatus.x3vi306qg1ms75;
   }

   public void xy78n60m5p9uj(long var1) {
      if (this.x1m95xojpiieh4 != null && this.x2s8emavervqrz()) {
         this.x3lcv8vebl7uhh();
         this.x2yls3kck49fqo = null;
      } else {
         this.x1jlq787lmscj6 = ConstructionStatus.x2tgnsmc98zm7p;
         this.x2yls3kck49fqo = null;
         this.x27hzctmw7oy9z = 0;
         this.x2pyv5aa4h53xv = 0L;
         this.x6m27u57uvw29 = var1;
      }
   }

   public NbtCompound xklnhlce5e8jn() {
      NbtCompound var1 = new NbtCompound();
      var1.putLong("retry_after", this.x2pyv5aa4h53xv);
      var1.putBoolean("catchup", this.xmcr06aulcksa);
      var1.putInt("supply_stall_ticks", this.x3affjeqf49yv1);
      var1.putString("id", this.x30g64uq6npagv.toString());
      var1.putString("template", this.x2lhubtwtapkxl.toString());
      var1.putInt("architecture_version", 1);
      var1.putLong("origin", this.x79ihhcocnyk1.asLong());
      var1.putInt("size_x", this.x5x75nz0elcmr);
      var1.putInt("size_y", this.x1wuggvufpo7hz);
      var1.putInt("size_z", this.x82v6jq0p0uao);
      if (this.x2yls3kck49fqo != null) {
         var1.putString("builder", this.x2yls3kck49fqo.toString());
      }

      var1.putInt("next_block", this.x27hzctmw7oy9z);
      var1.putInt("total_blocks", this.x2h56s5xwkzifr);
      var1.putInt("plan_version", this.x5g93fvp62vx7);
      var1.putLong("last_progress_tick", this.x6m27u57uvw29);
      var1.putString("status", this.x1jlq787lmscj6.name());
      if (this.x1m95xojpiieh4 != null) {
         var1.putString("owner", this.x1m95xojpiieh4.toString());
      }

      var1.putInt("property_level", this.x165uev4yx79br);
      var1.putBoolean("completed_once", this.x2s8emavervqrz());
      return var1;
   }

   public static ConstructionJob x20eftzvkuvpro(NbtCompound var0) {
      UUID var1 = xk58m2ye6g58t(var0, "builder");

      ConstructionStatus var2;
      try {
         var2 = ConstructionStatus.byName(var0.getString("status", ConstructionStatus.x2tgnsmc98zm7p.name()));
      } catch (IllegalArgumentException var5) {
         var2 = ConstructionStatus.x2tgnsmc98zm7p;
      }

      Identifier var3 = Optional.ofNullable(Identifier.tryParse(var0.getString("template", "")))
         .orElse(Identifier.of("minecraft", "village/plains/houses/plains_small_house_1"));
      if (var0.getInt("architecture_version", 0) == 0
         && var3.getNamespace().equals("smart_colonies")
         && var3.getPath().startsWith("village/")
         && !var3.getPath().startsWith("village/legacy_beta24/")) {
         var3 = Identifier.of("smart_colonies", "village/legacy_beta24/" + var3.getPath().substring("village/".length()));
      }

      ConstructionJob var4 = new ConstructionJob(
         x1eqbbmfpgmxge(var0, "id", UUID.randomUUID()),
         var3,
         BlockPos.fromLong(var0.getLong("origin", 0L)),
         var0.getInt("size_x", 1),
         var0.getInt("size_y", 1),
         var0.getInt("size_z", 1),
         var1,
         var0.getInt("next_block", 0),
         var0.getInt("total_blocks", 0),
         var0.getLong("last_progress_tick", Long.MIN_VALUE),
         var2,
         var0.getInt("plan_version", 0),
         xk58m2ye6g58t(var0, "owner"),
         var0.getInt("property_level", 0)
      );
      var4.x2pyv5aa4h53xv = var0.getLong("retry_after", 0L);
      var4.xmcr06aulcksa = var0.getBoolean("catchup", false);
      var4.xhgwli2r8opws = var0.getBoolean("completed_once", var2 == ConstructionStatus.xqfd7s4y4gux6);
      if (var4.x1m95xojpiieh4 != null && var4.x2s8emavervqrz()) {
         var4.x3lcv8vebl7uhh();
         var4.x2yls3kck49fqo = null;
      }

      var4.x3affjeqf49yv1 = Math.clamp((long)var0.getInt("supply_stall_ticks", 0), 0, 1200);
      return var4;
   }

   private static UUID xk58m2ye6g58t(NbtCompound var0, String var1) {
      return x1eqbbmfpgmxge(var0, var1, null);
   }

   private static UUID x1eqbbmfpgmxge(NbtCompound var0, String var1, UUID var2) {
      try {
         String var3 = var0.getString(var1, "");
         if (!var3.isEmpty()) {
            return UUID.fromString(var3);
         } else {
            int[] var4 = var0.getIntArray(var1).orElse(new int[0]);
            return var4.length == 4 ? Uuids.toUuid(var4) : var2;
         }
      } catch (IllegalArgumentException var5) {
         return var2;
      }
   }
}
