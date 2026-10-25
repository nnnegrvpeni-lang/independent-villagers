package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.smartcolonies.api.VillageCapability;

public final class Colony implements VillageCapability {
   public static final int x30n0f1iqh4m6r = 96;
   private final UUID x2hgk2wt393jsy;
   private final ExpansionPlan x1etmd9pd3k1qs = new ExpansionPlan();
   private final BlockPos x2dhgcsyyc68nh;
   private int x47m31tkwvosg;
   private long x2mgia30c9gjpg;
   private final Map<UUID, ColonyRole> xz579fac0iypp = new HashMap<>();
   private final Map<UUID, Long> xwr7cev77m675 = new HashMap<>();
   private final List<ConstructionJob> x39imgpk2g3vot = new ArrayList<>();
   private final Map<BlockPos, BlockState> x1rmz08m2pff7n = new LinkedHashMap<>();
   private final Set<BlockPos> x1uz9a72wueqj3 = new LinkedHashSet<>();
   private UUID xqj2iytkjmzel;
   private long x340orbbymdtix;
   private final List<BlockPos> x1a28iuq87no94 = new ArrayList<>();
   private BlockPos x1ygyaznl61dpp;
   private int x2nj3t0lzk7jfz;
   private int x51rgmtegpcgf;
   private long xxx1acfq50hfm = Long.MIN_VALUE;
   private final List<BlockPos> x2u8uud8v25pqu = new ArrayList<>();
   private int x1cc026fz85m13;
   private long x12ofjvhxit4j3;
   private long x1hczhlek9mlwo;
   private int x79nzxhkzs9b6;
   private int xnd5dsf3jxrl2;
   private int x2wtyc6bev9yu9;
   private UUID x2ww0le5bwi4ag;
   private long x3ugpi3xl1enk9;
   private int x1ke59hs5pt8hd;
   private long xlrozmkubaq1f;
   private long xiumjw77zh0yh;
   private BlockPos x26ic1a2o6gidb;
   private boolean x3b6w6hzspqxrh;
   private boolean xkoa8u079xsr3;
   private int x1f2uh6usp8fck;
   private long xh167yn1ysfz8 = Long.MIN_VALUE;
   private int x7gi4s9gz55hq;
   private int x1heehu7l48s14;
   private int x2n18aror2r9a2;
   private int x26831qk2usbwm;
   private int x21r4zuablw701;
   private int xs5fi6ms53sqb;
   private int x1762gf5vbqxt3;
   private int xw7933eaxp58j;
   private int x1t490nn7i21zq;
   private int x1roxw5puvm5an;
   private int x2uigv42wmee8k;
   private int x23ykb5bdm3izk = -1;
   private long x2hukdmox69wmw;
   private UUID x2tqwoay91ohef;
   private UUID x1m85kyvnlqu6h;
   private boolean x1apdsgf1fliim;
   private boolean xkqz0klyye24t;
   private final Map<Identifier, Integer> xben77893btj = new HashMap<>();
   private boolean xhm5xvft5a6k1;
   private final List<UUID> xexll3xl00u2v = new ArrayList<>();
   private BlockPos x28l2r1eabo8gl;
   private long x33hz9qmgclel9;
   private int x15mvyfu0qs0fu;
   private int x1wpzqzlc7qsnv;
   private long x10b1tak6qhkqx;
   private UUID xw7djz7rzgv6h;
   private int x1gy07vj24mbxq;
   private long x4k9rqk9xk0tv = Long.MIN_VALUE;
   private int x3buthex9xdlv1 = -1;
   private long xvm33ru31nhuk = Long.MIN_VALUE;
   private long xn39uhns0404o = Long.MIN_VALUE;
   private long x1yhayxclg630u;
   private long x3g25p2v8eo3co;
   private int x32bh1kl5l4q0a;
   private int x32lk4fbet7msc;
   private int x3rv3y8bqhfxu;
   private boolean xapn5eywcx9ac;
   private long x2wgei1pqtnb51;
   private boolean x36qyfddkn0ige;
   private double x27913dbtvwuc;
   private double x1dvtjkul06pbu;
   private double x2y4l170e3igxd;
   private final Map<Identifier, Double> x1wgiyrr5cktih = new LinkedHashMap<>();
   private final Map<Identifier, Integer> xh6wjg4rkkglx = new LinkedHashMap<>();
   private final Map<ColonyRole, Integer> x3k7syhtb17z1d = new EnumMap<>(ColonyRole.class);
   private final List<BlockPos> xishkumiwy0v9 = new ArrayList<>();
   private final List<BlockPos> x8ten6j4ymf75 = new ArrayList<>();
   private final Map<BlockPos, StorageCategory> x3c40yiqdwgrq2 = new HashMap<>();
   private final Map<BlockPos, BlockPos> x18btidfb9c6k1 = new HashMap<>();
   private long x3evjs8epiedfy = Long.MIN_VALUE;
   private int x39mcj5wvcbcqz = -1;

   public boolean xlklmlbkuwa0t() {
      return this.xhm5xvft5a6k1;
   }

   public void x1i1560srleos1(Map<Identifier, Integer> var1) {
      this.xben77893btj.putAll(var1);
      this.xhm5xvft5a6k1 = true;
   }

   public Map<Identifier, Integer> x3171qim2pcjd7() {
      return this.xben77893btj;
   }

   public boolean x1x26mhvd93s02() {
      return this.x1apdsgf1fliim;
   }

   public boolean xfx95ux1xn73w() {
      return this.xkqz0klyye24t;
   }

   public void x1nxldt1cw1h5h() {
      this.x1apdsgf1fliim = true;
   }

   public void x35wpd0lcwvgn6(UUID var1) {
      this.x1m85kyvnlqu6h = var1;
      this.x1apdsgf1fliim = false;
      this.xexll3xl00u2v.remove(var1);
   }

   public void x324hyyfyx41wk(BlockPos var1) {
      this.x28l2r1eabo8gl = var1.toImmutable();
   }

   public void x2qmn6nt922pp6() {
      this.xkqz0klyye24t = true;
      this.x28l2r1eabo8gl = this.x2dhgcsyyc68nh;
   }

   public void x3ki4hc4ldp1us(long var1) {
      this.x10b1tak6qhkqx = var1;
   }

   public long x3tnjjviw7kr77() {
      return this.xn39uhns0404o;
   }

   public void x1j7a15w06f1fz(long var1) {
      this.xn39uhns0404o = var1;
   }

   public long x1t2gtpnwtd60z() {
      return this.xvm33ru31nhuk;
   }

   public boolean x216l9aitzit41() {
      return this.x36qyfddkn0ige;
   }

   public void xqdbjfmq8abgh(boolean var1) {
      this.x36qyfddkn0ige = var1;
   }

   public void x1nmmpj859zi63() {
      if (this.x7gi4s9gz55hq == 0) {
         this.x7gi4s9gz55hq = 8;

         for (ColonyRole var4 : new ColonyRole[]{
            ColonyRole.x1saccptp3i00w,
            ColonyRole.x35csp4lojh59r,
            ColonyRole.x8jydaiei7iry,
            ColonyRole.x2hwljbtybtlwc,
            ColonyRole.x18g4mqx9kxs89,
            ColonyRole.xhds9crfjdp9i,
            ColonyRole.xeoehg87qwfrn,
            ColonyRole.x3r8p2tvihwpph
         }) {
            this.x3k7syhtb17z1d.put(var4, 1);
         }
      }
   }

   private static int xhc9med32n2ee(double var0) {
      return (int)Math.floor(var0 + 1.0E-9);
   }

   private static double x2kksy8tul54vj(double var0) {
      return Double.isFinite(var0) ? Math.clamp(var0, 0.0, Math.nextDown(1.0)) : 0.0;
   }

   public int x3eazb9acbxmlj(double var1) {
      this.x27913dbtvwuc += var1;
      int var3 = xhc9med32n2ee(this.x27913dbtvwuc);
      this.x27913dbtvwuc = Math.max(0.0, this.x27913dbtvwuc - var3);
      return var3;
   }

   public int x3eljz0p0x6yek(double var1) {
      this.x1dvtjkul06pbu += var1;
      int var3 = xhc9med32n2ee(this.x1dvtjkul06pbu);
      this.x1dvtjkul06pbu = Math.max(0.0, this.x1dvtjkul06pbu - var3);
      return var3;
   }

   public int x275t1qstogox8(double var1) {
      this.x2y4l170e3igxd += var1;
      int var3 = xhc9med32n2ee(this.x2y4l170e3igxd);
      this.x2y4l170e3igxd = Math.max(0.0, this.x2y4l170e3igxd - var3);
      return var3;
   }

   public int x1e0cfaky0ruvh(Identifier var1, double var2) {
      double var4 = this.x1wgiyrr5cktih.getOrDefault(var1, 0.0) + var2;
      int var6 = xhc9med32n2ee(var4);
      int var7 = Math.min(4096, var6);
      this.x1wgiyrr5cktih.put(var1, Math.max(0.0, var4 - var6));
      return var7;
   }

   public void x14t0iw6xpk0xx(long var1) {
      this.xvm33ru31nhuk = var1;
   }

   public long x2ya2lfuvm01ea() {
      return this.x1yhayxclg630u;
   }

   public long x3p06s7riestqm() {
      return this.x3g25p2v8eo3co;
   }

   public void xtcqji4527aor(long var1) {
      this.x3g25p2v8eo3co = Math.clamp(var1, 0L, 720000L);
   }

   public void x3vh0r7o2vtxxq(long var1) {
      this.x1yhayxclg630u += var1;
   }

   public int x11xljsmcmocmw() {
      return this.x32bh1kl5l4q0a;
   }

   public void x20lu0dxozk5g(int var1) {
      this.x32bh1kl5l4q0a = Math.clamp((long)this.x32bh1kl5l4q0a + var1, 0, 12000);
   }

   public void x3g65nuli3tuzl() {
      this.x32bh1kl5l4q0a = Math.max(0, this.x32bh1kl5l4q0a - 1);
   }

   public int x3h8wqo6wk0f7p() {
      return this.x32lk4fbet7msc;
   }

   public void x1hehk3f3lrpr(int var1) {
      this.x32lk4fbet7msc = Math.clamp((long)var1, 0, 8);
   }

   public int x2p9t1nvxo82zp() {
      return this.x3rv3y8bqhfxu;
   }

   public void x38l97g8nvc9ui(boolean var1) {
      this.xapn5eywcx9ac = var1;
   }

   public void x23n2tdykuxd7a(int var1) {
      this.x3rv3y8bqhfxu = Math.clamp((long)var1, 0, 4);
   }

   public long x3tkladv9pjeao() {
      return this.x2wgei1pqtnb51;
   }

   public void xemejsnsuhts2(long var1) {
      this.x2wgei1pqtnb51 = var1;
   }

   public Map<Identifier, Integer> x1i0d1gs2hqpsr() {
      return this.xh6wjg4rkkglx;
   }

   public int x18two0xs8s6p9(ColonyRole var1) {
      return this.x3k7syhtb17z1d.getOrDefault(var1, 0);
   }

   public void xhwx3flu2btqb(List<VillagerEntity> var1) {
      this.x3k7syhtb17z1d.clear();

      for (VillagerEntity var3 : var1) {
         if (var3.isAlive() && !var3.isBaby() && !this.x3hurh64xwtw51(var3.getUuid())) {
            this.x3k7syhtb17z1d.merge(this.roleOf(var3.getUuid()), 1, Integer::sum);
         }
      }
   }

   public int x3sjn9mskj5v9f() {
      long var1 = this.x39imgpk2g3vot.stream().filter(var0 -> var0.x32oz43hmeynhc() == ConstructionStatus.xqfd7s4y4gux6 && var0.x2nfrt8qxdscso().isEmpty()).count();
      return 1 + (int)Math.min(4L, Math.min((long)(this.x7gi4s9gz55hq / 12), var1 / 3L));
   }

   public Colony(UUID var1, BlockPos var2, int var3, long var4) {
      this.x2hgk2wt393jsy = var1;
      this.x2dhgcsyyc68nh = var2.toImmutable();
      this.x47m31tkwvosg = var3;
      this.x2mgia30c9gjpg = var4;
   }

   @Override
   public UUID id() {
      return this.x2hgk2wt393jsy;
   }

   public ExpansionPlan x2f7z1gnnht5ab() {
      return this.x1etmd9pd3k1qs;
   }

   public long x2bp8u9p8dnt9e(UUID var1) {
      return this.xwr7cev77m675.getOrDefault(var1, -4611686018427387904L);
   }

   public void xvamv2rzn6h0g(UUID var1, long var2) {
      this.xwr7cev77m675.put(var1, var2);
   }

   public boolean x2s37os1kht7vr() {
      return !this.xkoa8u079xsr3 && this.x3c40yiqdwgrq2.isEmpty();
   }

   public void x1z3izo4s1trbu() {
      this.xkoa8u079xsr3 = true;
   }

   @Override
   public BlockPos core() {
      return this.x2dhgcsyyc68nh;
   }

   @Override
   public int radius() {
      return this.x47m31tkwvosg;
   }

   public long x3klfyffbbfab2() {
      return this.x2mgia30c9gjpg;
   }

   public List<BlockPos> x2ad3s8aentmo4() {
      return this.xishkumiwy0v9;
   }

   public Map<BlockPos, StorageCategory> x29j0o9zdxri2() {
      return Collections.unmodifiableMap(this.x3c40yiqdwgrq2);
   }

   public StorageCategory x2h8eqlzb5mm0w(BlockPos var1) {
      return this.x3c40yiqdwgrq2.getOrDefault(var1, StorageCategory.x2y2vmpsepzv3);
   }

   public Map<BlockPos, BlockPos> x2ivzhhwd37mdn() {
      return Collections.unmodifiableMap(this.x18btidfb9c6k1);
   }

   public void x35zk9b4abyi6a(BlockPos var1, BlockPos var2) {
      if (!var1.equals(var2)
         && !this.x18btidfb9c6k1.containsKey(var1)
         && !this.x18btidfb9c6k1.containsKey(var2)
         && !this.x18btidfb9c6k1.containsValue(var1)
         && !this.x18btidfb9c6k1.containsValue(var2)) {
         this.x18btidfb9c6k1.put(var1.toImmutable(), var2.toImmutable());
      }
   }

   public long x3k3m01633dfw1() {
      return this.x3evjs8epiedfy;
   }

   public boolean x28w4myn4c9xwu() {
      return this.x39mcj5wvcbcqz >= 0;
   }

   public int x3kz9uqjj0g2fr() {
      return this.x39mcj5wvcbcqz;
   }

   public List<BlockPos> x36r3qqcopafen() {
      return this.x8ten6j4ymf75;
   }

   public List<ConstructionJob> x1ja61emeln2hc() {
      return this.x39imgpk2g3vot;
   }

   public Map<BlockPos, BlockState> x2xqc3q79vfkw3() {
      return this.x1rmz08m2pff7n;
   }

   public Set<BlockPos> x1hdthtpd2qb8g() {
      return Collections.unmodifiableSet(this.x1uz9a72wueqj3);
   }

   public boolean x1tdgzp0ab8tn6(BlockPos var1) {
      return this.x1uz9a72wueqj3.size() < 4096 && this.x1uz9a72wueqj3.add(var1.toImmutable());
   }

   public void x8tht6cvqikr(UUID var1, long var2) {
      this.xqj2iytkjmzel = var1;
      this.x340orbbymdtix = var2;
   }

   public Optional<UUID> x10fb3wo7mboo(long var1) {
      return var1 < this.x340orbbymdtix ? Optional.ofNullable(this.xqj2iytkjmzel) : Optional.empty();
   }

   @Override
   public int population() {
      return this.x7gi4s9gz55hq;
   }

   @Override
   public int beds() {
      return this.x1heehu7l48s14;
   }

   public int x182p7mjnr75sg() {
      return Math.max(0, this.x1heehu7l48s14 - this.x7gi4s9gz55hq);
   }

   @Override
   public int cachedFood() {
      return this.x2n18aror2r9a2;
   }

   @Override
   public int cachedLogs() {
      return this.x26831qk2usbwm;
   }

   @Override
   public int cachedStone() {
      return this.x21r4zuablw701;
   }

   public int x2zm76h0pkiu6o() {
      return this.xs5fi6ms53sqb;
   }

   public int x12nt3bv2et7b7() {
      return this.x1762gf5vbqxt3;
   }

   public int x3peiu091351qg() {
      return this.xw7933eaxp58j;
   }

   public int x2qncyh2nmpkyq() {
      return this.x1t490nn7i21zq;
   }

   public int x324ff4i2oimhg() {
      return this.x1roxw5puvm5an;
   }

   public void x2itw5qfmlx92d(int var1) {
      this.x1t490nn7i21zq++;
      this.x1roxw5puvm5an = this.x1roxw5puvm5an + Math.max(0, var1);
   }

   public int x3eeagbf8g6pbh() {
      int var1 = this.x2u8uud8v25pqu.size();
      if (var1 > this.x23ykb5bdm3izk) {
         this.x23ykb5bdm3izk = var1;
         this.x2uigv42wmee8k = 0;
      } else {
         this.x2uigv42wmee8k++;
      }

      return this.x2uigv42wmee8k;
   }

   public long x1wlbtxwjz4vz1() {
      return this.x2hukdmox69wmw;
   }

   public void xq8drze9hw6pb(long var1) {
      this.x2hukdmox69wmw = var1;
   }

   public Optional<UUID> x3ctixeo4to9a4() {
      return Optional.ofNullable(this.x2tqwoay91ohef);
   }

   public void x3c59xc9hrfvhz(UUID var1) {
      this.x2tqwoay91ohef = var1;
   }

   public Optional<UUID> x2a380ray4md5t() {
      return Optional.ofNullable(this.x1m85kyvnlqu6h);
   }

   public List<UUID> x2zftltl6vhwwx() {
      return this.xexll3xl00u2v;
   }

   public boolean x3hurh64xwtw51(UUID var1) {
      return this.x1m85kyvnlqu6h != null && (this.x1m85kyvnlqu6h.equals(var1) || this.xexll3xl00u2v.contains(var1));
   }

   public Optional<BlockPos> x1x0agg3sdxjk8() {
      return Optional.ofNullable(this.x28l2r1eabo8gl);
   }

   public long x1spmho2xy9muc() {
      return this.x33hz9qmgclel9;
   }

   public int x37br4tln8q08z() {
      return this.x15mvyfu0qs0fu;
   }

   public int x13ua0ftegttnr() {
      return this.x1wpzqzlc7qsnv;
   }

   public long x15y708t7gvjof() {
      return this.x10b1tak6qhkqx;
   }

   public void xveqe1h09qne3(UUID var1, BlockPos var2, long var3) {
      this.x1m85kyvnlqu6h = var1;
      this.x28l2r1eabo8gl = var2 == null ? null : var2.toImmutable();
      this.x33hz9qmgclel9 = var3;
      this.x15mvyfu0qs0fu = 0;
      this.x1wpzqzlc7qsnv = 0;
      this.x1apdsgf1fliim = false;
      this.xkqz0klyye24t = false;
      this.xben77893btj.clear();
      this.xhm5xvft5a6k1 = false;
      this.xexll3xl00u2v.clear();
   }

   public void xy40t7pepehnr(int var1) {
      this.x15mvyfu0qs0fu++;
      this.x1wpzqzlc7qsnv = this.x1wpzqzlc7qsnv + Math.max(0, var1);
   }

   public void x3ch5vvzyhhzwj(long var1) {
      this.xben77893btj.clear();
      this.xhm5xvft5a6k1 = false;
      if (this.x1m85kyvnlqu6h != null) {
         this.x36xpmo21hmp9h(this.x1m85kyvnlqu6h, ColonyRole.x2j116s1wug0g2);
      }

      this.x1apdsgf1fliim = false;
      this.xkqz0klyye24t = false;

      for (UUID var4 : this.xexll3xl00u2v) {
         this.x36xpmo21hmp9h(var4, ColonyRole.x2j116s1wug0g2);
      }

      this.xexll3xl00u2v.clear();
      this.x1m85kyvnlqu6h = null;
      this.x28l2r1eabo8gl = null;
      this.x33hz9qmgclel9 = 0L;
      this.x15mvyfu0qs0fu = 0;
      this.x1wpzqzlc7qsnv = 0;
      this.x10b1tak6qhkqx = var1;
   }

   public Optional<UUID> x3t9uchcxi5zlm() {
      return Optional.ofNullable(this.xw7djz7rzgv6h);
   }

   public int x1beis4altbswn() {
      return this.x1gy07vj24mbxq;
   }

   public boolean x3mumqgcvnzi4m(UUID var1, int var2) {
      if (this.xw7djz7rzgv6h != null) {
         return false;
      } else {
         this.xw7djz7rzgv6h = var1;
         this.x1gy07vj24mbxq = Math.clamp((long)var2, 1, 3);
         return true;
      }
   }

   public void xqsw8yhmtxlpl() {
      this.xw7djz7rzgv6h = null;
      this.x1gy07vj24mbxq = 0;
   }

   public long x202n2ys5dq0ho() {
      return this.x4k9rqk9xk0tv;
   }

   public boolean x2zi111bgbtgzq() {
      return this.x3buthex9xdlv1 >= 0;
   }

   public int x1eughlg5bm9gx() {
      return this.x3buthex9xdlv1;
   }

   public List<BlockPos> x1n1a2vp01kxzf() {
      return this.x1a28iuq87no94;
   }

   public int x3nmwt0p1j1m6g() {
      return this.x2nj3t0lzk7jfz;
   }

   public int x2frtu6yleucxg() {
      return this.x51rgmtegpcgf;
   }

   public void x15dc4qxz6dpo3(List<BlockPos> var1) {
      this.x1a28iuq87no94.clear();
      var1.stream().map(BlockPos::toImmutable).forEach(this.x1a28iuq87no94::add);
      this.x1ygyaznl61dpp = this.x1a28iuq87no94.isEmpty() ? null : this.x1a28iuq87no94.getFirst();
      this.x2nj3t0lzk7jfz = 0;
      this.x51rgmtegpcgf = 0;
      this.xxx1acfq50hfm = Long.MIN_VALUE;
   }

   public void x1nocpys4yjo2k(long var1) {
      if (!this.x1a28iuq87no94.isEmpty()) {
         BlockPos var3 = this.x1a28iuq87no94.getFirst();
         if (!var3.equals(this.x1ygyaznl61dpp)) {
            this.x1ygyaznl61dpp = var3.toImmutable();
            this.x2nj3t0lzk7jfz = 0;
            this.xxx1acfq50hfm = Long.MIN_VALUE;
         }

         if (this.xxx1acfq50hfm != var1) {
            this.x2nj3t0lzk7jfz = Math.min(1200, this.x2nj3t0lzk7jfz + 10);
            this.xxx1acfq50hfm = var1;
         }
      }
   }

   public void x1tvwg66ci1f7k(boolean var1) {
      if (!this.x1a28iuq87no94.isEmpty()) {
         this.x1a28iuq87no94.removeFirst();
      }

      if (var1) {
         this.x51rgmtegpcgf++;
      }

      this.x1ygyaznl61dpp = this.x1a28iuq87no94.isEmpty() ? null : this.x1a28iuq87no94.getFirst();
      this.x2nj3t0lzk7jfz = 0;
      this.xxx1acfq50hfm = Long.MIN_VALUE;
   }

   public List<BlockPos> x31ow2tlg0dm5w() {
      return this.x2u8uud8v25pqu;
   }

   public int xdd8htksk84yo() {
      return this.x1cc026fz85m13;
   }

   public void xmskodayog7rh(int var1) {
      this.x1cc026fz85m13 = Math.floorMod(var1, 4);
   }

   public long x1ljiw4h4dmxfs() {
      return this.x12ofjvhxit4j3;
   }

   public void x163kb3edimpm3(long var1) {
      this.x12ofjvhxit4j3 = var1;
   }

   public long x3b9zegn105yce() {
      return this.x1hczhlek9mlwo;
   }

   public void x3kvixtipq5eqg(long var1) {
      this.x1hczhlek9mlwo = var1;
   }

   public int x3dkkolbe7ust6() {
      return this.x79nzxhkzs9b6;
   }

   public void x18w8ltfnp5moy() {
      this.x79nzxhkzs9b6++;
   }

   public int x4zx3pxdqkrq() {
      return this.xnd5dsf3jxrl2;
   }

   public int x1v1doml6bflbz() {
      return this.x2wtyc6bev9yu9;
   }

   public void x3ktd3i0hq4h53() {
      this.xnd5dsf3jxrl2++;
   }

   public void xqcex85brzoq2() {
      this.x2wtyc6bev9yu9++;
   }

   public void x2jh70rewptpw1(UUID var1, long var2, int var4) {
      this.x2ww0le5bwi4ag = var1;
      this.x3ugpi3xl1enk9 = Math.max(this.x3ugpi3xl1enk9, var2);
      this.x1ke59hs5pt8hd = this.x1ke59hs5pt8hd + Math.max(0, var4);
   }

   public Optional<UUID> x2npriys7z2eli(long var1) {
      return this.x2ww0le5bwi4ag != null && var1 < this.x3ugpi3xl1enk9 ? Optional.of(this.x2ww0le5bwi4ag) : Optional.empty();
   }

   public long x3ifovfwl4jqpk() {
      return this.x3ugpi3xl1enk9;
   }

   public int x6at97kfxajem() {
      return this.x1ke59hs5pt8hd;
   }

   public long x147mtuve9wuay() {
      return this.xlrozmkubaq1f;
   }

   public void xs28n9ortj0j6(long var1) {
      this.xlrozmkubaq1f = var1;
   }

   public long x180oul2yysi55() {
      return this.xiumjw77zh0yh;
   }

   public void x336kh3r7239fs(long var1) {
      this.xiumjw77zh0yh = var1;
   }

   public Optional<BlockPos> x329rgb9lpsari() {
      return Optional.ofNullable(this.x26ic1a2o6gidb);
   }

   public void x146t7j3h7t042(BlockPos var1) {
      this.x26ic1a2o6gidb = var1 == null ? null : var1.toImmutable();
   }

   public boolean x2vcibw4inamjw(BlockBox var1, int var2) {
      if (this.x26ic1a2o6gidb == null) {
         return false;
      } else {
         int var3 = 5 + var2;
         return var1.getMinX() <= this.x26ic1a2o6gidb.getX() + var3
            && var1.getMaxX() >= this.x26ic1a2o6gidb.getX() - var3
            && var1.getMinZ() <= this.x26ic1a2o6gidb.getZ() + var3
            && var1.getMaxZ() >= this.x26ic1a2o6gidb.getZ() - var3;
      }
   }

   public boolean x2o79xnwz4g9nh(BlockPos var1, int var2) {
      return this.x2vcibw4inamjw(new BlockBox(var1), var2);
   }

   public boolean xxqujir3s0ild() {
      return this.x3b6w6hzspqxrh;
   }

   public void xvuz8u6ue7m7c() {
      this.x3b6w6hzspqxrh = true;
   }

   public int x3e7xus757aqpt() {
      return ++this.x1f2uh6usp8fck;
   }

   public long x2c2srygrjrm6g() {
      return this.xh167yn1ysfz8;
   }

   public void xjq48e1i3w4gj(long var1) {
      this.xh167yn1ysfz8 = var1;
   }

   public void x1ne2dy4ss3d7w(int var1) {
      this.x47m31tkwvosg = var1;
   }

   public void xz6ij33c0780z(long var1) {
      this.x2mgia30c9gjpg = var1;
   }

   public void x1sssgviunlcgk(long var1) {
      this.x3evjs8epiedfy = var1;
   }

   public void x1c0m5jj5x6wpm(int var1, int var2, long var3) {
      this.x7gi4s9gz55hq = var1;
      this.x1heehu7l48s14 = var2;
      this.x4k9rqk9xk0tv = var3;
   }

   public void x2rezm3a3rz7iu(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.x2n18aror2r9a2 = var1;
      this.x26831qk2usbwm = var2;
      this.x21r4zuablw701 = var3;
      this.xs5fi6ms53sqb = var4;
      this.x1762gf5vbqxt3 = var5;
      this.xw7933eaxp58j = var6;
   }

   public void x2sh8yupnxmo71(Item var1, int var2) {
      if (var2 > 0) {
         ItemStack var3 = new ItemStack(var1);
         if (var3.isIn(ItemTags.LOGS)) {
            this.x26831qk2usbwm = Math.max(0, this.x26831qk2usbwm - var2);
         }

         if (var3.isIn(ItemTags.STONE_CRAFTING_MATERIALS)) {
            this.x21r4zuablw701 = Math.max(0, this.x21r4zuablw701 - var2);
         }

         if (var3.isIn(ItemTags.SAPLINGS)) {
            this.xs5fi6ms53sqb = Math.max(0, this.xs5fi6ms53sqb - var2);
         }

         if (var3.isOf(Items.IRON_INGOT) || var3.isOf(Items.RAW_IRON)) {
            this.x1762gf5vbqxt3 = Math.max(0, this.x1762gf5vbqxt3 - var2);
         }

         if (var3.isOf(Items.DIAMOND)) {
            this.xw7933eaxp58j = Math.max(0, this.xw7933eaxp58j - var2);
         }

         FoodComponent var4 = (FoodComponent)var3.get(DataComponentTypes.FOOD);
         if (var4 != null) {
            this.x2n18aror2r9a2 = Math.max(0, this.x2n18aror2r9a2 - Math.max(1, var4.nutrition()) * var2);
         }
      }
   }

   public void x31uoom8ikakob() {
      this.x3buthex9xdlv1 = 0;
   }

   public void x2xopfa6a46mo9() {
      this.x3buthex9xdlv1++;
   }

   public void x2lr91dptxy1ww() {
      this.x3buthex9xdlv1 = -1;
   }

   public void x3db8zzffozhu6() {
      this.x8ten6j4ymf75.clear();
      this.x39mcj5wvcbcqz = 0;
   }

   public void x11k3dhx0f8bj3() {
      this.x39mcj5wvcbcqz++;
   }

   public void xtffnc1wwh86p(long var1) {
      this.xishkumiwy0v9.clear();
      this.xishkumiwy0v9.addAll(this.x8ten6j4ymf75);

      for (BlockPos var4 : this.x3c40yiqdwgrq2.keySet()) {
         if (!this.xishkumiwy0v9.contains(var4)) {
            this.xishkumiwy0v9.add(var4);
         }
      }

      this.x8ten6j4ymf75.clear();
      this.x39mcj5wvcbcqz = -1;
      this.x3evjs8epiedfy = var1;
   }

   public void xqikhqhd8ag0s(BlockPos var1) {
      BlockPos var2 = var1.toImmutable();
      if (!this.xishkumiwy0v9.contains(var2)) {
         this.xishkumiwy0v9.add(var2);
      }

      if (this.x28w4myn4c9xwu() && !this.x8ten6j4ymf75.contains(var2)) {
         this.x8ten6j4ymf75.add(var2);
      }
   }

   public void x3qhye0v48751k(BlockPos var1) {
      this.xishkumiwy0v9.remove(var1);
      this.x8ten6j4ymf75.remove(var1);
      this.x3c40yiqdwgrq2.remove(var1);
      this.x18btidfb9c6k1.entrySet().removeIf(var1x -> var1x.getKey().equals(var1) || var1x.getValue().equals(var1));
   }

   public void x1v7s85y1rhqmd(BlockPos var1, StorageCategory var2) {
      BlockPos var3 = var1.toImmutable();
      this.x3c40yiqdwgrq2.put(var3, var2 == null ? StorageCategory.x2y2vmpsepzv3 : var2);
      this.xqikhqhd8ag0s(var3);
   }

   @Override
   public ColonyRole roleOf(UUID var1) {
      return this.xz579fac0iypp.getOrDefault(var1, ColonyRole.x2j116s1wug0g2);
   }

   public boolean x36xpmo21hmp9h(UUID var1, ColonyRole var2) {
      return this.xz579fac0iypp.put(var1, var2) != var2;
   }

   public void x31ve0dsfeaxo4(ConstructionJob var1) {
      this.x39imgpk2g3vot.add(var1);
   }

   public Optional<ConstructionJob> x39hvql45i0wl6(UUID var1) {
      return this.x39imgpk2g3vot
         .stream()
         .filter(var0 -> var0.x32oz43hmeynhc() == ConstructionStatus.x2tgnsmc98zm7p || var0.x32oz43hmeynhc() == ConstructionStatus.x1mzkgtgadv3jh)
         .filter(var1x -> var1x.x3vu8mnvg0blk4().isEmpty() || var1x.x3vu8mnvg0blk4().get().equals(var1))
         .findFirst();
   }

   public boolean x3e4ycl8cmmmgc() {
      return this.x39imgpk2g3vot
         .stream()
         .anyMatch(
            var0 -> var0.x32oz43hmeynhc() == ConstructionStatus.x2tgnsmc98zm7p
               || var0.x32oz43hmeynhc() == ConstructionStatus.x1mzkgtgadv3jh
               || var0.x32oz43hmeynhc() == ConstructionStatus.x3vi306qg1ms75 && var0.x2zq2smhbu3lzo() > 0L
         );
   }

   public boolean xhu9xvlie05r6(BlockPos var1, int var2) {
      for (ConstructionJob var4 : this.x39imgpk2g3vot) {
         if (var4.x32oz43hmeynhc() != ConstructionStatus.xqfd7s4y4gux6 && (var4.x32oz43hmeynhc() != ConstructionStatus.x3vi306qg1ms75 || var4.x2zq2smhbu3lzo() != 0L)) {
            BlockBox var5 = var4.x24kbqv6g4kkrg();
            if (var1.getX() >= var5.getMinX() - var2
               && var1.getX() <= var5.getMaxX() + var2
               && var1.getZ() >= var5.getMinZ() - var2
               && var1.getZ() <= var5.getMaxZ() + var2) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean x2a24huns14rw4(BlockPos var1, int var2) {
      if (ColonyBiomeHelper.x1hmaqzrqcstj(this, var1, var2)) {
         return true;
      } else {
         for (ConstructionJob var4 : this.x39imgpk2g3vot) {
            BlockBox var5 = var4.x24kbqv6g4kkrg();
            if (var1.getX() >= var5.getMinX() - var2
               && var1.getX() <= var5.getMaxX() + var2
               && var1.getZ() >= var5.getMinZ() - var2
               && var1.getZ() <= var5.getMaxZ() + var2) {
               return true;
            }
         }

         return false;
      }
   }

   public NbtCompound xufzblerzrsnk() {
      NbtCompound var1 = new NbtCompound();
      var1.putString("id", this.x2hgk2wt393jsy.toString());
      var1.putLong("simulated_until", this.xvm33ru31nhuk);
      var1.putLong("catchup_ticks", this.x1yhayxclg630u);
      var1.putLong("deferred_catchup_ticks", this.x3g25p2v8eo3co);
      var1.putInt("catchup_labor", this.x32bh1kl5l4q0a);
      var1.putInt("catchup_births", this.x32lk4fbet7msc);
      var1.putInt("catchup_houses", this.x3rv3y8bqhfxu);
      var1.putBoolean("filming_development", this.xapn5eywcx9ac);
      var1.putLong("catchup_expires", this.x2wgei1pqtnb51);
      var1.putBoolean("initial_development", this.x36qyfddkn0ige);
      var1.putDouble("house_credit", this.x27913dbtvwuc);
      var1.putDouble("birth_credit", this.x1dvtjkul06pbu);
      var1.putDouble("labor_credit", this.x2y4l170e3igxd);
      NbtCompound var2 = new NbtCompound();
      this.x1wgiyrr5cktih.forEach((var1x, var2x) -> var2.putDouble(var1x.toString(), var2x));
      var1.put("production_credit", var2);
      var1.putInt("last_population", this.x7gi4s9gz55hq);
      var1.putInt("last_beds", this.x1heehu7l48s14);
      NbtCompound var3 = new NbtCompound();
      this.x3k7syhtb17z1d.forEach((var1x, var2x) -> var3.putInt(var1x.serializedName(), var2x));
      var1.put("labor_census", var3);
      NbtList var4 = new NbtList();
      this.xh6wjg4rkkglx.forEach((var1x, var2x) -> {
         NbtCompound var3x = new NbtCompound();
         var3x.putString("item", var1x.toString());
         var3x.putInt("count", var2x);
         var4.add(var3x);
      });
      var1.put("catchup_stock", var4);
      var1.put("fortifications", this.x1etmd9pd3k1qs.x11ueu6ur29o1u());
      if (this.xqj2iytkjmzel != null) {
         var1.putString("assault_attacker", this.xqj2iytkjmzel.toString());
      }

      var1.putLong("assault_until", this.x340orbbymdtix);
      NbtList var5 = new NbtList();
      this.x1rmz08m2pff7n.forEach((var1x, var2x) -> {
         NbtCompound var3x = new NbtCompound();
         var3x.putLong("pos", var1x.asLong());
         var3x.put("state", NbtHelper.fromBlockState(var2x));
         var5.add(var3x);
      });
      var1.put("repairs", var5);
      var1.putLongArray("farmland", this.x1uz9a72wueqj3.stream().mapToLong(BlockPos::asLong).toArray());
      var1.putLong("core", this.x2dhgcsyyc68nh.asLong());
      var1.putInt("radius", this.x47m31tkwvosg);
      var1.putLong("last_threat_tick", this.x2mgia30c9gjpg);
      var1.putLongArray("road_queue", this.x1a28iuq87no94.stream().mapToLong(BlockPos::asLong).toArray());
      if (this.x1ygyaznl61dpp != null) {
         var1.putLong("road_attempt_head", this.x1ygyaznl61dpp.asLong());
      }

      var1.putInt("road_stall_ticks", this.x2nj3t0lzk7jfz);
      var1.putInt("skipped_road_segments", this.x51rgmtegpcgf);
      var1.putLongArray("mine_route", this.x2u8uud8v25pqu.stream().mapToLong(BlockPos::asLong).toArray());
      var1.putInt("mine_direction", this.x1cc026fz85m13);
      var1.putLong("next_golem_tick", this.x12ofjvhxit4j3);
      var1.putLong("next_economy_tick", this.x1hczhlek9mlwo);
      var1.putInt("economy_cursor", this.x79nzxhkzs9b6);
      var1.putInt("sold_trades", this.xnd5dsf3jxrl2);
      var1.putInt("bought_trades", this.x2wtyc6bev9yu9);
      var1.putInt("deep_mining_trips", this.x1t490nn7i21zq);
      var1.putInt("deep_mining_yield", this.x1roxw5puvm5an);
      var1.putInt("mine_stall_trips", this.x2uigv42wmee8k);
      var1.putInt("last_mine_route_size", this.x23ykb5bdm3izk);
      if (this.x2tqwoay91ohef != null) {
         var1.putString("elder", this.x2tqwoay91ohef.toString());
      }

      if (this.x1m85kyvnlqu6h != null) {
         var1.putString("founder", this.x1m85kyvnlqu6h.toString());
      }

      var1.putBoolean("founder_leader_lost", this.x1apdsgf1fliim);
      var1.putBoolean("founder_returning", this.xkqz0klyye24t);
      var1.putBoolean("founder_cargo_reserved", this.xhm5xvft5a6k1);
      NbtCompound var6 = new NbtCompound();
      this.xben77893btj.forEach((var1x, var2x) -> var6.putInt(var1x.toString(), var2x));
      var1.put("founder_cargo", var6);
      NbtList var7 = new NbtList();

      for (UUID var9 : this.xexll3xl00u2v) {
         var7.add(NbtString.of(var9.toString()));
      }

      var1.put("founder_companions", var7);
      if (this.x28l2r1eabo8gl != null) {
         var1.putLong("founder_target", this.x28l2r1eabo8gl.asLong());
      }

      var1.putLong("founder_started_tick", this.x33hz9qmgclel9);
      var1.putInt("founder_visits", this.x15mvyfu0qs0fu);
      var1.putInt("founder_capital", this.x1wpzqzlc7qsnv);
      var1.putLong("next_founder_tick", this.x10b1tak6qhkqx);
      if (this.xw7djz7rzgv6h != null) {
         var1.putString("property_owner", this.xw7djz7rzgv6h.toString());
      }

      var1.putInt("property_level", this.x1gy07vj24mbxq);
      if (this.x2ww0le5bwi4ag != null) {
         var1.putString("reported_thief", this.x2ww0le5bwi4ag.toString());
      }

      var1.putLong("theft_until", this.x3ugpi3xl1enk9);
      var1.putInt("stolen_items", this.x1ke59hs5pt8hd);
      var1.putLong("next_breeding_tick", this.xiumjw77zh0yh);
      if (this.x26ic1a2o6gidb != null) {
         var1.putBoolean("has_livestock_pen", true);
         var1.putLong("livestock_pen", this.x26ic1a2o6gidb.asLong());
      }

      var1.putBoolean("profession_bootstrap_complete", this.x3b6w6hzspqxrh);
      var1.putBoolean("starter_warehouse_placed", this.xkoa8u079xsr3 || !this.x3c40yiqdwgrq2.isEmpty());
      var1.putLong("last_profession_rebalance_tick", this.xh167yn1ysfz8);
      NbtList var12 = new NbtList();
      this.x3c40yiqdwgrq2.forEach((var1x, var2x) -> {
         NbtCompound var3x = new NbtCompound();
         var3x.putLong("pos", var1x.asLong());
         var3x.putString("kind", var2x.name().toLowerCase(Locale.ROOT));
         var12.add(var3x);
      });
      var1.put("managed_storages", var12);
      NbtList var13 = new NbtList();
      this.x18btidfb9c6k1.forEach((var1x, var2x) -> {
         NbtCompound var3x = new NbtCompound();
         var3x.putLong("left", var1x.asLong());
         var3x.putLong("right", var2x.asLong());
         var13.add(var3x);
      });
      var1.put("managed_chest_pairs", var13);
      NbtList var10 = new NbtList();
      this.xz579fac0iypp.forEach((var2x, var3x) -> {
         NbtCompound var4x = new NbtCompound();
         var4x.putString("villager", var2x.toString());
         var4x.putString("role", var3x.serializedName());
         var4x.putLong("changed_at", this.x2bp8u9p8dnt9e(var2x));
         var10.add(var4x);
      });
      var1.put("roles", var10);
      NbtList var11 = new NbtList();
      this.x39imgpk2g3vot.forEach(var1x -> var11.add(var1x.xklnhlce5e8jn()));
      var1.put("construction_jobs", var11);
      return var1;
   }

   public static Colony x3bvvkrzdde479(NbtCompound var0) {
      Colony var1 = new Colony(
         x2oz9lsexq53ht(var0, "id"),
         BlockPos.fromLong(var0.getLong("core", 0L)),
         Math.max(96, var0.getInt("radius", 96)),
         var0.getLong("last_threat_tick", Long.MIN_VALUE)
      );
      NbtList var2 = var0.getListOrEmpty("roles");
      var1.xvm33ru31nhuk = var0.getLong("simulated_until", Long.MIN_VALUE);
      var1.x1yhayxclg630u = Math.max(0L, var0.getLong("catchup_ticks", 0L));
      var1.xtcqji4527aor(var0.getLong("deferred_catchup_ticks", 0L));
      var1.x32bh1kl5l4q0a = Math.clamp((long)var0.getInt("catchup_labor", 0), 0, 12000);
      var1.x1hehk3f3lrpr(var0.getInt("catchup_births", 0));
      var1.x23n2tdykuxd7a(var0.getInt("catchup_houses", 0));
      var1.x2wgei1pqtnb51 = var0.getLong("catchup_expires", 0L);
      var1.x36qyfddkn0ige = var0.getBoolean("initial_development", false);
      var1.x27913dbtvwuc = x2kksy8tul54vj(var0.getDouble("house_credit", 0.0));
      var1.x1dvtjkul06pbu = x2kksy8tul54vj(var0.getDouble("birth_credit", 0.0));
      var1.x2y4l170e3igxd = x2kksy8tul54vj(var0.getDouble("labor_credit", 0.0));
      NbtCompound var3 = var0.getCompoundOrEmpty("production_credit");

      for (String var5 : var3.getKeys()) {
         Identifier var6 = Identifier.tryParse(var5);
         if (var6 != null && Registries.ITEM.containsId(var6)) {
            var1.x1wgiyrr5cktih.put(var6, x2kksy8tul54vj(var3.getDouble(var5, 0.0)));
         }
      }

      var1.x7gi4s9gz55hq = Math.clamp((long)var0.getInt("last_population", 0), 0, 200);
      var1.x1heehu7l48s14 = Math.clamp((long)var0.getInt("last_beds", 0), 0, 1000);
      NbtCompound var15 = var0.getCompoundOrEmpty("labor_census");

      for (ColonyRole var8 : ColonyRole.values()) {
         var1.x3k7syhtb17z1d.put(var8, Math.clamp((long)var15.getInt(var8.serializedName(), 0), 0, 200));
      }

      for (NbtElement var20 : var0.getListOrEmpty("catchup_stock")) {
         if (var20 instanceof NbtCompound var24) {
            Identifier var33 = Identifier.tryParse(var24.getString("item", ""));
            if (var33 != null && Registries.ITEM.containsId(var33)) {
               var1.xh6wjg4rkkglx.put(var33, Math.clamp((long)var24.getInt("count", 0), 0, 4096));
            }
         }
      }

      var1.x1etmd9pd3k1qs.x3e1m4qdtyt44s(var0.getCompoundOrEmpty("fortifications"));
      var1.x1apdsgf1fliim = var0.getBoolean("founder_leader_lost", false);
      var1.xkqz0klyye24t = var0.getBoolean("founder_returning", false);
      var1.xhm5xvft5a6k1 = var0.getBoolean("founder_cargo_reserved", false);
      NbtCompound var18 = var0.getCompoundOrEmpty("founder_cargo");

      for (String var25 : var18.getKeys()) {
         Identifier var34 = Identifier.tryParse(var25);
         if (var34 != null && Registries.ITEM.containsId(var34) && var18.getInt(var25, 0) > 0) {
            var1.xben77893btj.put(var34, Math.clamp((long)var18.getInt(var25, 0), 0, 4096));
         }
      }

      for (long var9 : var0.getLongArray("farmland").orElse(new long[0])) {
         BlockPos var11 = BlockPos.fromLong(var9);
         if (Math.abs((long)var11.getX() - var1.x2dhgcsyyc68nh.getX()) <= var1.x47m31tkwvosg
            && Math.abs((long)var11.getZ() - var1.x2dhgcsyyc68nh.getZ()) <= var1.x47m31tkwvosg) {
            var1.x1tdgzp0ab8tn6(var11);
         }
      }

      try {
         var1.xqj2iytkjmzel = UUID.fromString(var0.getString("assault_attacker", ""));
      } catch (IllegalArgumentException var14) {
      }

      var1.x340orbbymdtix = var0.getLong("assault_until", 0L);
      NbtList var23 = var0.getListOrEmpty("repairs");

      for (int var27 = 0; var27 < var23.size(); var27++) {
         NbtCompound var36 = var23.getCompoundOrEmpty(var27);
         BlockState var43 = NbtHelper.toBlockState(Registries.BLOCK, var36.getCompoundOrEmpty("state"));
         if (!var43.isAir()) {
            var1.x1rmz08m2pff7n.put(BlockPos.fromLong(var36.getLong("pos", 0L)), var43);
         }
      }

      for (long var10 : var0.getLongArray("road_queue").orElse(new long[0])) {
         var1.x1a28iuq87no94.add(BlockPos.fromLong(var10));
      }

      if (!var1.x1a28iuq87no94.isEmpty()) {
         BlockPos var29 = BlockPos.fromLong(var0.getLong("road_attempt_head", var1.x1a28iuq87no94.getFirst().asLong()));
         var1.x1ygyaznl61dpp = var29.equals(var1.x1a28iuq87no94.getFirst()) ? var29 : var1.x1a28iuq87no94.getFirst();
         var1.x2nj3t0lzk7jfz = Math.clamp((long)var0.getInt("road_stall_ticks", 0), 0, 1200);
      }

      var1.x51rgmtegpcgf = Math.max(0, var0.getInt("skipped_road_segments", 0));

      for (long var50 : var0.getLongArray("mine_route").orElse(new long[0])) {
         var1.x2u8uud8v25pqu.add(BlockPos.fromLong(var50));
      }

      var1.xmskodayog7rh(var0.getInt("mine_direction", 0));
      var1.x12ofjvhxit4j3 = 0L;
      var1.xkoa8u079xsr3 = var0.getBoolean("starter_warehouse_placed", false);
      var1.x1hczhlek9mlwo = var0.getLong("next_economy_tick", 0L);
      var1.x79nzxhkzs9b6 = Math.max(0, var0.getInt("economy_cursor", 0));
      var1.xnd5dsf3jxrl2 = Math.max(0, var0.getInt("sold_trades", 0));
      var1.x2wtyc6bev9yu9 = Math.max(0, var0.getInt("bought_trades", 0));
      var1.x1t490nn7i21zq = Math.max(0, var0.getInt("deep_mining_trips", 0));
      var1.x1roxw5puvm5an = Math.max(0, var0.getInt("deep_mining_yield", 0));
      var1.x2uigv42wmee8k = Math.max(0, var0.getInt("mine_stall_trips", 0));
      var1.x23ykb5bdm3izk = var0.getInt("last_mine_route_size", var1.x2u8uud8v25pqu.size());
      var1.x2tqwoay91ohef = xu74m7p3sue9g(var0, "elder");
      var1.x1m85kyvnlqu6h = xu74m7p3sue9g(var0, "founder");

      for (NbtElement var39 : var0.getListOrEmpty("founder_companions")) {
         try {
            var1.xexll3xl00u2v.add(UUID.fromString(var39.asString().orElse("")));
         } catch (IllegalArgumentException var13) {
         }
      }

      if (var0.contains("founder_target")) {
         var1.x28l2r1eabo8gl = BlockPos.fromLong(var0.getLong("founder_target", 0L));
      }

      var1.x33hz9qmgclel9 = var0.getLong("founder_started_tick", 0L);
      var1.x15mvyfu0qs0fu = Math.max(0, var0.getInt("founder_visits", 0));
      var1.x1wpzqzlc7qsnv = Math.max(0, var0.getInt("founder_capital", 0));
      var1.x10b1tak6qhkqx = var0.getLong("next_founder_tick", 0L);
      var1.xw7djz7rzgv6h = xu74m7p3sue9g(var0, "property_owner");
      var1.x1gy07vj24mbxq = Math.clamp((long)var0.getInt("property_level", 0), 0, 3);
      var1.x2ww0le5bwi4ag = x2oz9lsexq53ht(var0, "reported_thief");
      var1.x3ugpi3xl1enk9 = var0.getLong("theft_until", 0L);
      var1.x1ke59hs5pt8hd = Math.max(0, var0.getInt("stolen_items", 0));
      var1.xiumjw77zh0yh = var0.getLong("next_breeding_tick", 0L);
      if (var0.getBoolean("has_livestock_pen", false)) {
         var1.x26ic1a2o6gidb = BlockPos.fromLong(var0.getLong("livestock_pen", 0L));
      }

      var1.x3b6w6hzspqxrh = var0.getBoolean("profession_bootstrap_complete", false);
      var1.xh167yn1ysfz8 = var0.getLong("last_profession_rebalance_tick", Long.MIN_VALUE);
      NbtList var32 = var0.getListOrEmpty("managed_storages");

      for (int var40 = 0; var40 < var32.size(); var40++) {
         NbtCompound var46 = var32.getCompoundOrEmpty(var40);
         var1.x1v7s85y1rhqmd(BlockPos.fromLong(var46.getLong("pos", 0L)), StorageCategory.byName(var46.getString("kind", "general")));
      }

      for (int var41 = 0; var41 < var2.size(); var41++) {
         NbtCompound var47 = var2.getCompoundOrEmpty(var41);
         UUID var51 = x2oz9lsexq53ht(var47, "villager");
         var1.xz579fac0iypp.put(var51, ColonyRole.byName(var47.getString("role", "citizen")));
         var1.xwr7cev77m675.put(var51, var47.getLong("changed_at", -4611686018427387904L));
      }

      NbtList var42 = var0.getListOrEmpty("managed_chest_pairs");

      for (int var48 = 0; var48 < var42.size(); var48++) {
         NbtCompound var52 = var42.getCompoundOrEmpty(var48);
         BlockPos var54 = BlockPos.fromLong(var52.getLong("left", 0L));
         BlockPos var12 = BlockPos.fromLong(var52.getLong("right", 0L));
         if (var12.equals(var54.east()) && var1.x3c40yiqdwgrq2.containsKey(var54) && var1.x3c40yiqdwgrq2.get(var54) == var1.x3c40yiqdwgrq2.get(var12)) {
            var1.x35zk9b4abyi6a(var54, var12);
         }
      }

      var1.x3c40yiqdwgrq2
         .keySet()
         .stream()
         .sorted(Comparator.comparingInt(Vec3i::getY).thenComparingInt(Vec3i::getZ).thenComparingInt(Vec3i::getX))
         .forEach(var1x -> {
            if (var1.x3c40yiqdwgrq2.get(var1x.east()) == var1.x3c40yiqdwgrq2.get(var1x)) {
               var1.x35zk9b4abyi6a(var1x, var1x.east());
            }
         });
      NbtList var49 = var0.getListOrEmpty("construction_jobs");

      for (int var53 = 0; var53 < var49.size(); var53++) {
         var1.x39imgpk2g3vot.add(ConstructionJob.x20eftzvkuvpro(var49.getCompoundOrEmpty(var53)));
      }

      if (var0.getBoolean("filming_development", false)) {
         var1.x32bh1kl5l4q0a = 0;
         var1.x3rv3y8bqhfxu = 0;
         var1.x2wgei1pqtnb51 = 0L;
         var1.x39imgpk2g3vot.stream().filter(var0x -> var0x.x2nfrt8qxdscso().isEmpty()).forEach(var0x -> var0x.xkv5b17xl7vd6(false));
      }

      return var1;
   }

   private static UUID x2oz9lsexq53ht(NbtCompound var0, String var1) {
      try {
         String var2 = var0.getString(var1, "");
         if (!var2.isEmpty()) {
            return UUID.fromString(var2);
         }

         int[] var3 = var0.getIntArray(var1).orElse(new int[0]);
         if (var3.length == 4) {
            return Uuids.toUuid(var3);
         }
      } catch (IllegalArgumentException var4) {
      }

      return UUID.randomUUID();
   }

   private static UUID xu74m7p3sue9g(NbtCompound var0, String var1) {
      String var2 = var0.getString(var1, "");
      if (var2.isEmpty()) {
         return null;
      } else {
         try {
            return UUID.fromString(var2);
         } catch (IllegalArgumentException var4) {
            return null;
         }
      }
   }
}
