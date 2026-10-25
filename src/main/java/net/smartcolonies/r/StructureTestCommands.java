package net.smartcolonies.r;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;

public final class StructureTestCommands {
   public static final Identifier x2l0wl5wa7yi4i = Identifier.of("smart_colonies", "village/savanna/houses/savanna_veranda_1");
   public static final int xbuv0x96xapb5 = 2;

   private StructureTestCommands() {
   }

   public static LiteralArgumentBuilder<ServerCommandSource> x2aso8h2ks6ywe() {
      LiteralArgumentBuilder var0 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("savanna")
            .executes(
               var0x -> x3vftv9p8ig9cy(
                  (ServerCommandSource)var0x.getSource(), BlockPos.ofFloored(((ServerCommandSource)var0x.getSource()).getPosition()).add(-6, -1, 6)
               )
            ))
         .then(
            CommandManager.argument("floor", BlockPosArgumentType.blockPos())
               .executes(var0x -> x3vftv9p8ig9cy((ServerCommandSource)var0x.getSource(), BlockPosArgumentType.getLoadedBlockPos(var0x, "floor")))
         );

      for (String var2 : HouseVariantCatalog.x11oxp2ntj65hs) {
         Identifier var3 = HouseVariantCatalog.xhp130tff2ww4(var2);
         var0.then(
            ((LiteralArgumentBuilder)CommandManager.literal(var2)
                  .executes(
                     var1 -> x1f5jbrpm0wxwk(
                        (ServerCommandSource)var1.getSource(),
                        BlockPos.ofFloored(((ServerCommandSource)var1.getSource()).getPosition()).add(-6, -1, 6),
                        var3,
                        0
                     )
                  ))
               .then(
                  CommandManager.argument("floor", BlockPosArgumentType.blockPos())
                     .executes(var1 -> x1f5jbrpm0wxwk((ServerCommandSource)var1.getSource(), BlockPosArgumentType.getLoadedBlockPos(var1, "floor"), var3, 0))
               )
         );
      }

      LiteralArgumentBuilder var9 = CommandManager.literal("taiga");

      for (String var12 : VillageBuildingCatalog.x1krr6wlni86ip) {
         Identifier var4 = VillageBuildingCatalog.x1l4umcgiak8iw(var12);
         var9.then(
            ((LiteralArgumentBuilder)CommandManager.literal(var12)
                  .executes(
                     var1 -> x1f5jbrpm0wxwk(
                        (ServerCommandSource)var1.getSource(),
                        BlockPos.ofFloored(((ServerCommandSource)var1.getSource()).getPosition()).add(-6, -1, 6),
                        var4,
                        0
                     )
                  ))
               .then(
                  CommandManager.argument("floor", BlockPosArgumentType.blockPos())
                     .executes(var1 -> x1f5jbrpm0wxwk((ServerCommandSource)var1.getSource(), BlockPosArgumentType.getLoadedBlockPos(var1, "floor"), var4, 0))
               )
         );
      }

      LiteralArgumentBuilder var11 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("house")
               .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK)))
            .then(var0))
         .then(var9);

      for (String var14 : List.of("plains", "desert", "snowy")) {
         LiteralArgumentBuilder var5 = CommandManager.literal(var14);

         for (String var7 : VillageCatalog.x3tmz8uu7gcem2(var14)) {
            Identifier var8 = VillageCatalog.x4jnab85o8lop(var14, var7);
            var5.then(
               ((LiteralArgumentBuilder)CommandManager.literal(var7)
                     .executes(
                        var1 -> x1f5jbrpm0wxwk(
                           (ServerCommandSource)var1.getSource(),
                           BlockPos.ofFloored(((ServerCommandSource)var1.getSource()).getPosition()).add(-6, -1, 6),
                           var8,
                           0
                        )
                     ))
                  .then(
                     CommandManager.argument("floor", BlockPosArgumentType.blockPos())
                        .executes(var1 -> x1f5jbrpm0wxwk((ServerCommandSource)var1.getSource(), BlockPosArgumentType.getLoadedBlockPos(var1, "floor"), var8, 0))
                  )
            );
         }

         var11.then(var5);
      }

      return var11;
   }

   public static int x3vftv9p8ig9cy(ServerCommandSource var0, BlockPos var1) {
      return x1f5jbrpm0wxwk(var0, var1, x2l0wl5wa7yi4i, 2);
   }

   public static int x1f5jbrpm0wxwk(ServerCommandSource var0, BlockPos var1, Identifier var2, int var3) {
      ServerWorld var4 = var0.getWorld();
      Optional var5 = var4.getStructureTemplateManager().getTemplate(var2);
      if (var5.isEmpty()) {
         return x3nxixnbj7g1le(var0, "Шаблон тестового дома не найден. Проверьте версию мода.");
      } else {
         StructureTemplate var6 = (StructureTemplate)var5.get();
         Vec3i var7 = var6.getSize();
         BlockPos var8 = var1.down(var3);
         BlockPos var9 = var8.add(var7.getX() - 1, var7.getY() - 1, var7.getZ() - 1);
         if (!var4.isOutOfHeightLimit(var8) && !var4.isOutOfHeightLimit(var9) && var4.getWorldBorder().contains(var8) && var4.getWorldBorder().contains(var9)) {
            for (int var10 = var8.getX() >> 4; var10 <= var9.getX() >> 4; var10++) {
               for (int var11 = var8.getZ() >> 4; var11 <= var9.getZ() >> 4; var11++) {
                  if (!var4.getChunkManager().isChunkLoaded(var10, var11)) {
                     return x3nxixnbj7g1le(var0, "Вся площадка должна быть загружена. Подойдите ближе.");
                  }
               }
            }

            Box var19 = Box.enclosing(var8, var9);
            if (!var4.getOtherEntities(null, var19, var0x -> var0x.isAlive() && var0x instanceof LivingEntity && !var0x.isSpectator()).isEmpty()) {
               return x3nxixnbj7g1le(var0, "Внутри площадки есть игрок или животное. Освободите её и повторите команду.");
            } else {
               ColonyState var20 = ColonyState.get(var4);
               BlockBox var12 = new BlockBox(var8.getX(), var8.getY(), var8.getZ(), var9.getX(), var9.getY(), var9.getZ());
               if (var20.colonies().stream().flatMap(var0x -> var0x.x1ja61emeln2hc().stream()).anyMatch(var1x -> var1x.x24kbqv6g4kkrg().intersects(var12))) {
                  return x3nxixnbj7g1le(var0, "Площадка пересекает существующий дом или заказ строителя.");
               } else {
                  LinkedHashMap var13 = new LinkedHashMap();

                  for (BlockPos var15 : BlockPos.iterate(var8, var9)) {
                     BlockPos var16 = var15.toImmutable();
                     BlockState var17 = var4.getBlockState(var16);
                     if (var20.isPlayerBlock(var16) && !var17.isAir()
                        || var4.getBlockEntity(var16) != null
                        || !var17.getFluidState().isEmpty()
                        || !var17.isAir() && !var17.isReplaceable() && (var16.getY() > var1.getY() || !x6rhl7fd6f8wq(var17))) {
                        return x3nxixnbj7g1le(
                           var0,
                           "Площадка занята в "
                              + var16.toShortString()
                              + ". Нужен свободный участок "
                              + var7.getX()
                              + "×"
                              + var7.getZ()
                              + "; постройки не перезаписываются."
                        );
                     }

                     var13.put(var16, var17);
                  }

                  try {
                     if (!var6.place(var4, var8, var8, new StructurePlacementData().setIgnoreEntities(true), var4.random, 3)) {
                        x14l2hggbuya9a(var4, var13);
                        return x3nxixnbj7g1le(var0, "Не удалось поставить дом; прежние блоки возвращены.");
                     }
                  } catch (RuntimeException var18) {
                     x14l2hggbuya9a(var4, var13);
                     throw var18;
                  }

                  var0.sendFeedback(
                     () -> Text.literal(
                        "Здание готово: "
                           + var2.getPath().substring(var2.getPath().lastIndexOf(47) + 1)
                           + ". Участок: "
                           + var1.toShortString()
                           + "; размер "
                           + var7.getX()
                           + "×"
                           + var7.getZ()
                           + "; фасад смотрит на север."
                     ),
                     false
                  );
                  return 1;
               }
            }
         } else {
            return x3nxixnbj7g1le(var0, "Дом выходит за высоту мира или его границу.");
         }
      }
   }

   private static boolean x6rhl7fd6f8wq(BlockState var0) {
      return var0.isIn(BlockTags.DIRT)
         || var0.isIn(BlockTags.BASE_STONE_OVERWORLD)
         || var0.isIn(BlockTags.SAND)
         || var0.isOf(Blocks.GRAVEL)
         || var0.isOf(Blocks.CLAY)
         || var0.isOf(Blocks.SNOW_BLOCK);
   }

   private static void x14l2hggbuya9a(ServerWorld var0, Map<BlockPos, BlockState> var1) {
      var1.forEach((var1x, var2) -> var0.setBlockState(var1x, var2, 18));
   }

   private static int x3nxixnbj7g1le(ServerCommandSource var0, String var1) {
      var0.sendError(Text.literal(var1));
      return 0;
   }
}
