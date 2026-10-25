package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;

public final class x10d2jouiuut4g {
   public int x14zjd0to6v6yv;
   public long x37dzr2zekycme;
   public int x1fvrorwquv0wt;
   public final Map<BlockPos, BlockState> x16wpjsjmva3od = new LinkedHashMap<>();
   public final List<x2eha1n45eqf4l> x1q0gb1h69ywu7 = new ArrayList<>();
   public final Set<Long> x3pic36dm8dawn = new HashSet<>();
   public final Map<BlockPos, Long> x1b5j5bmruqafh = new HashMap<>();
   public final Map<BlockPos, Integer> xb3n8htn3gf9q = new HashMap<>();
   public final Set<BlockPos> x2fh8ft5lrq7el = new HashSet<>();

   public NbtCompound x11ueu6ur29o1u() {
      NbtCompound var1 = new NbtCompound();
      var1.putInt("radius", this.x14zjd0to6v6yv);
      var1.putInt("skipped", this.x1fvrorwquv0wt);
      var1.putLong("next_plan", this.x37dzr2zekycme);
      NbtList var2 = new NbtList();
      NbtList var3 = new NbtList();
      this.x16wpjsjmva3od.forEach((var1x, var2x) -> var2.add(x153vc7q6umx05(var1x, var2x, false)));
      this.x1q0gb1h69ywu7.forEach(var1x -> var3.add(x153vc7q6umx05(var1x.x1oppt2nr5wv2g(), var1x.x1bbgyigo2fq58(), var1x.xttubpoe0ch3r())));
      var1.putLongArray("surveyed", this.x3pic36dm8dawn.stream().mapToLong(Long::longValue).toArray());
      var1.putLongArray("abandoned", this.x2fh8ft5lrq7el.stream().mapToLong(BlockPos::asLong).toArray());
      var1.put("managed", var2);
      var1.put("pending", var3);
      return var1;
   }

   public void x3e1m4qdtyt44s(NbtCompound var1) {
      this.x14zjd0to6v6yv = Math.clamp((long)var1.getInt("radius", 0), 0, 512);
      this.x1fvrorwquv0wt = Math.max(0, var1.getInt("skipped", 0));
      this.x37dzr2zekycme = var1.getLong("next_plan", 0L);

      for (long var5 : var1.getLongArray("surveyed").orElse(new long[0])) {
         this.x3pic36dm8dawn.add(var5);
      }

      for (long var16 : var1.getLongArray("abandoned").orElse(new long[0])) {
         if (this.x2fh8ft5lrq7el.size() < 32768) {
            this.x2fh8ft5lrq7el.add(BlockPos.fromLong(var16));
         }
      }

      for (NbtElement var11 : var1.getListOrEmpty("managed")) {
         if (var11 instanceof NbtCompound var14 && this.x16wpjsjmva3od.size() < 16384) {
            x2eha1n45eqf4l var17 = x127ho0uxrxw7f(var14);
            this.x16wpjsjmva3od.put(var17.x1oppt2nr5wv2g(), var17.x1bbgyigo2fq58());
         }
      }

      for (NbtElement var12 : var1.getListOrEmpty("pending")) {
         if (var12 instanceof NbtCompound var15 && this.x1q0gb1h69ywu7.size() < 32768) {
            this.x1q0gb1h69ywu7.add(x127ho0uxrxw7f(var15));
         }
      }
   }

   private static NbtCompound x153vc7q6umx05(BlockPos var0, BlockState var1, boolean var2) {
      NbtCompound var3 = new NbtCompound();
      var3.putLong("pos", var0.asLong());
      var3.put("state", NbtHelper.fromBlockState(var1));
      var3.putBoolean("remove", var2);
      return var3;
   }

   private static x2eha1n45eqf4l x127ho0uxrxw7f(NbtCompound var0) {
      return new x2eha1n45eqf4l(
         BlockPos.fromLong(var0.getLong("pos", 0L)),
         NbtHelper.toBlockState(Registries.BLOCK, var0.getCompoundOrEmpty("state")),
         var0.getBoolean("remove", false)
      );
   }
}
