package net.smartcolonies.r;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;

public final class ColonyState extends PersistentState {
   private static final String x41xas6lstatb = "smart_colonies";
   private static final PersistentStateType<ColonyState> xlj3y4jgsvf46 = new PersistentStateType(
      "smart_colonies", ColonyState::new, NbtCompound.CODEC.xmap(ColonyState::x1vfd7ma58aaai, ColonyState::x17s9f8fvs5qrt), null
   );
   private final Map<UUID, Colony> x1g5jtc8hqnb9v = new LinkedHashMap<>();
   private final Set<Long> x2zsg3w2bwvt8r = new HashSet<>();
   private final Map<Long, Set<Long>> x1j9cli2zfshem = new HashMap<>();
   private final Map<Long, UUID> x2fxchwsury85l = new HashMap<>();
   private final List<CitizenDeedAssignment> x33hs2tmxu4ojw = new ArrayList<>();
   private final Map<Long, EntityCooldownEntry> x38t3bkbt21nlp = new HashMap<>();

   private void x1iwjnc1stouur(long var1) {
      BlockPos var3 = BlockPos.fromLong(var1);
      this.x1j9cli2zfshem.computeIfAbsent(ChunkPos.toLong(var3.getX() >> 4, var3.getZ() >> 4), var0 -> new HashSet<>()).add(var1);
   }

   public boolean playerBuildNear(BlockPos var1, int var2) {
      for (int var3 = var1.getX() - var2 >> 4; var3 <= var1.getX() + var2 >> 4; var3++) {
         for (int var4 = var1.getZ() - var2 >> 4; var4 <= var1.getZ() + var2 >> 4; var4++) {
            Set<Long> var5 = this.x1j9cli2zfshem.get(ChunkPos.toLong(var3, var4));
            if (var5 != null) {
               for (long var7 : var5) {
                  BlockPos var9 = BlockPos.fromLong(var7);
                  long var10 = (long)var9.getX() - var1.getX();
                  long var12 = (long)var9.getZ() - var1.getZ();
                  if (var10 * var10 + var12 * var12 <= (long)var2 * var2) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public List<CitizenDeedAssignment> land() {
      return Collections.unmodifiableList(this.x33hs2tmxu4ojw);
   }

   public void addLand(CitizenDeedAssignment var1) {
      this.x33hs2tmxu4ojw.add(var1);
      this.markDirty();
   }

   public Optional<UUID> blockOwner(BlockPos var1) {
      return Optional.ofNullable(this.x2fxchwsury85l.get(var1.asLong()));
   }

   public boolean foreignBlocks(CitizenDeedAssignment var1) {
      return this.x2fxchwsury85l
         .entrySet()
         .stream()
         .anyMatch(var1x -> !var1x.getValue().equals(var1.xetxwd9l2gcx6()) && var1.contains(BlockPos.fromLong(var1x.getKey())));
   }

   public boolean isPlayerBlock(BlockPos var1) {
      return this.x2zsg3w2bwvt8r.contains(var1.asLong());
   }

   public void recordPlacement(BlockPos var1, UUID var2, long var3) {
      this.x2zsg3w2bwvt8r.add(var1.asLong());
      this.x1iwjnc1stouur(var1.asLong());
      this.x2fxchwsury85l.put(var1.asLong(), var2);
      this.x38t3bkbt21nlp.entrySet().removeIf(var2x -> var3 - var2x.getValue().xv5ezs7er1s4p() > 1200L);
      this.x38t3bkbt21nlp.put(var1.asLong(), new EntityCooldownEntry(var2, var3));
      this.markDirty();
   }

   public boolean recentAttackerBlock(BlockPos var1, UUID var2, long var3) {
      EntityCooldownEntry var5 = this.x38t3bkbt21nlp.get(var1.asLong());
      return var5 != null && var5.x1v3u0njrh3j7a().equals(var2) && var3 - var5.xv5ezs7er1s4p() >= 0L && var3 - var5.xv5ezs7er1s4p() <= 1200L;
   }

   public void forgetPlacement(BlockPos var1) {
      if (this.x2zsg3w2bwvt8r.remove(var1.asLong())) {
         this.markDirty();
      }

      long var2 = ChunkPos.toLong(var1.getX() >> 4, var1.getZ() >> 4);
      Set var4 = this.x1j9cli2zfshem.get(var2);
      if (var4 != null) {
         var4.remove(var1.asLong());
         if (var4.isEmpty()) {
            this.x1j9cli2zfshem.remove(var2);
         }
      }

      this.x2fxchwsury85l.remove(var1.asLong());
      this.x38t3bkbt21nlp.remove(var1.asLong());
   }

   public static ColonyState get(ServerWorld var0) {
      return (ColonyState)var0.getPersistentStateManager().getOrCreate(xlj3y4jgsvf46);
   }

   public Collection<Colony> colonies() {
      return this.x1g5jtc8hqnb9v.values();
   }

   public Optional<Colony> byId(UUID var1) {
      return Optional.ofNullable(this.x1g5jtc8hqnb9v.get(var1));
   }

   public void add(Colony var1) {
      this.x1g5jtc8hqnb9v.put(var1.id(), var1);
      this.markDirty();
   }

   private NbtCompound x17s9f8fvs5qrt() {
      NbtCompound var1 = new NbtCompound();
      NbtList var2 = new NbtList();
      this.x1g5jtc8hqnb9v.values().forEach(var1x -> var2.add(var1x.xufzblerzrsnk()));
      var1.put("colonies", var2);
      var1.putLongArray("player_blocks", this.x2zsg3w2bwvt8r.stream().mapToLong(Long::longValue).toArray());
      NbtList var3 = new NbtList();
      this.x2fxchwsury85l.forEach((var1x, var2x) -> {
         NbtCompound var3x = new NbtCompound();
         var3x.putLong("pos", var1x);
         var3x.putString("owner", var2x.toString());
         var3.add(var3x);
      });
      var1.put("block_owners", var3);
      NbtList var4 = new NbtList();
      this.x33hs2tmxu4ojw.forEach(var1x -> var4.add(var1x.toNbt()));
      var1.put("land", var4);
      DeedItemHandler.x2xqw0zvyjpw3b(var1, this);
      return var1;
   }

   private static ColonyState x1vfd7ma58aaai(NbtCompound var0) {
      ColonyState var1 = new ColonyState();

      for (long var5 : var0.getLongArray("player_blocks").orElse(new long[0])) {
         var1.x2zsg3w2bwvt8r.add(var5);
         var1.x1iwjnc1stouur(var5);
      }

      NbtList var9 = var0.getListOrEmpty("block_owners");

      for (int var10 = 0; var10 < var9.size(); var10++) {
         NbtCompound var12 = var9.getCompoundOrEmpty(var10);

         try {
            var1.x2fxchwsury85l.put(var12.getLong("pos", 0L), UUID.fromString(var12.getString("owner", "")));
         } catch (IllegalArgumentException var8) {
         }
      }

      NbtList var11 = var0.getListOrEmpty("land");

      for (int var13 = 0; var13 < var11.size(); var13++) {
         try {
            var1.x33hs2tmxu4ojw.add(CitizenDeedAssignment.fromNbt(var11.getCompoundOrEmpty(var13)));
         } catch (IllegalArgumentException var7) {
         }
      }

      DeedItemHandler.x1vdpt6b5sksjj(var0, var1);
      NbtList var14 = var0.getListOrEmpty("colonies");

      for (int var15 = 0; var15 < var14.size(); var15++) {
         Colony var6 = Colony.x3bvvkrzdde479(var14.getCompoundOrEmpty(var15));
         var1.x1g5jtc8hqnb9v.put(var6.id(), var6);
      }

      return var1;
   }
}
