package net.smartcolonies.r;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public final class x1g8ugdychfo2j {
   private x1g8ugdychfo2j() {
   }

   public static LiteralArgumentBuilder<ServerCommandSource> x2bhz7qcxu7mfc() {
      LiteralArgumentBuilder var0 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal(
                                                      "film"
                                                   )
                                                   .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK)))
                                                .executes(var0x -> x2ed6fw6gftd0v((ServerCommandSource)var0x.getSource())))
                                             .then(CommandManager.literal("help").executes(var0x -> x2ed6fw6gftd0v((ServerCommandSource)var0x.getSource()))))
                                          .then(
                                             CommandManager.literal("on")
                                                .executes(
                                                   var0x -> {
                                                      x8veg662b2f5p.xevco0r0wp03v(((ServerCommandSource)var0x.getSource()).getWorld());
                                                      return x3kspok0jyxjks(
                                                         (ServerCommandSource)var0x.getSource(),
                                                         "Съёмочный режим включён в этом измерении до выхода из мира. Используйте КОПИЮ сохранения: команды меняют мир, off не откатывает изменения. Автоматические новые экспедиции приостановлены; уже запущенные продолжают путь.",
                                                         1
                                                      );
                                                   }
                                                )
                                          ))
                                       .then(
                                          CommandManager.literal("off")
                                             .executes(
                                                var0x -> {
                                                   x8veg662b2f5p.x3evocl9yxshic(((ServerCommandSource)var0x.getSource()).getWorld());
                                                   return x3kspok0jyxjks(
                                                      (ServerCommandSource)var0x.getSource(),
                                                      "Съёмочный режим выключен, ускорение max остановлено. Изменения мира сохранены. Обычные экспедиции снова разрешены.",
                                                      1
                                                   );
                                                }
                                             )
                                       ))
                                    .then(
                                       CommandManager.literal("status")
                                          .executes(
                                             var0x -> x30301j211m4ot(
                                                (ServerCommandSource)var0x.getSource(),
                                                (var0xx, var1x) -> new x1lf12nv67hipd(1, x8veg662b2f5p.x24jh62s2oq06y(var0xx.getWorld(), var1x)),
                                                false
                                             )
                                          )
                                    ))
                                 .then(
                                    CommandManager.literal("supply")
                                       .executes(
                                          var0x -> x30301j211m4ot(
                                             (ServerCommandSource)var0x.getSource(),
                                             (var0xx, var1x) -> x8veg662b2f5p.x2b94htgx8j8wb(var0xx.getWorld(), var1x),
                                             true
                                          )
                                       )
                                 ))
                              .then(
                                 CommandManager.literal("population")
                                    .then(
                                       CommandManager.argument("count", IntegerArgumentType.integer(2, 64))
                                          .executes(
                                             var0x -> x30301j211m4ot(
                                                (ServerCommandSource)var0x.getSource(),
                                                (var1x, var2) -> x8veg662b2f5p.xcqb3adsjgu8q(
                                                   var1x.getWorld(), var2, IntegerArgumentType.getInteger(var0x, "count")
                                                ),
                                                true
                                             )
                                          )
                                    )
                              ))
                           .then(
                              CommandManager.literal("max")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (ServerCommandSource)var0x.getSource(), (var0xx, var1x) -> x8veg662b2f5p.x3hszkva73cyco(var0xx.getWorld(), var1x), true
                                    )
                                 )
                           ))
                        .then(
                           CommandManager.literal("stop")
                              .executes(
                                 var0x -> x30301j211m4ot(
                                    (ServerCommandSource)var0x.getSource(), (var0xx, var1x) -> x8veg662b2f5p.x2dwx84ddtzhb0(var0xx.getWorld(), var1x), true
                                 )
                              )
                        ))
                     .then(
                        CommandManager.literal("rebalance")
                           .executes(
                              var0x -> x30301j211m4ot(
                                 (ServerCommandSource)var0x.getSource(), (var0xx, var1x) -> x8veg662b2f5p.x32g8vo9hf8rm3(var0xx.getWorld(), var1x), true
                              )
                           )
                     ))
                  .then(
                     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("develop")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (ServerCommandSource)var0x.getSource(),
                                       (var0xx, var1x) -> x8veg662b2f5p.x1uwgtgswk0hva(var0xx.getWorld(), var1x, 3),
                                       true
                                    )
                                 ))
                              .then(
                                 CommandManager.literal("prepare")
                                    .executes(
                                       var0x -> x30301j211m4ot(
                                          (ServerCommandSource)var0x.getSource(),
                                          (var0xx, var1x) -> x8veg662b2f5p.x241eq3b06s3l0(var0xx.getWorld(), var1x, BlockPos.ofFloored(var0xx.getPosition())),
                                          true
                                       )
                                    )
                              ))
                           .then(
                              CommandManager.literal("status")
                                 .executes(
                                    var0x -> x30301j211m4ot(
                                       (ServerCommandSource)var0x.getSource(),
                                       (var0xx, var1x) -> new x1lf12nv67hipd(1, x8veg662b2f5p.x24jh62s2oq06y(var0xx.getWorld(), var1x)),
                                       false
                                    )
                                 )
                           ))
                        .then(
                           CommandManager.argument("days", IntegerArgumentType.integer(1, 30))
                              .executes(
                                 var0x -> x30301j211m4ot(
                                    (ServerCommandSource)var0x.getSource(),
                                    (var1x, var2) -> x8veg662b2f5p.x1uwgtgswk0hva(var1x.getWorld(), var2, IntegerArgumentType.getInteger(var0x, "days")),
                                    true
                                 )
                              )
                        )
                  ))
               .then(
                  ((LiteralArgumentBuilder)CommandManager.literal("expedition")
                        .executes(
                           var0x -> x30301j211m4ot(
                              (ServerCommandSource)var0x.getSource(), (var0xx, var1x) -> x313o9woxa22fw.x3seqvpn908svy(var0xx.getWorld(), var1x, null), true
                           )
                        ))
                     .then(
                        CommandManager.argument("destination", BlockPosArgumentType.blockPos())
                           .executes(
                              var0x -> {
                                 BlockPos var1x = BlockPosArgumentType.getLoadedBlockPos(var0x, "destination");
                                 return x30301j211m4ot(
                                    (ServerCommandSource)var0x.getSource(),
                                    (var1xx, var2) -> x313o9woxa22fw.x3seqvpn908svy(var1xx.getWorld(), var2, var1x),
                                    true
                                 );
                              }
                           )
                     )
               ))
            .then(
               CommandManager.literal("build")
                  .executes(
                     var0x -> x30301j211m4ot(
                        (ServerCommandSource)var0x.getSource(),
                        (var0xx, var1x) -> {
                           var1x.xs28n9ortj0j6(0L);
                           x3d5vk1zj3fvkj.get(var0xx.getWorld()).markDirty();
                           return new x1lf12nv67hipd(
                              1,
                              "Поиск участка разрешён без ожидания повтора. Строитель работает с обычной скоростью; активный дом не отменён. Состояние: "
                                 + x2n55y2tom37h2.x2y69zyuty3rxs(var0xx.getWorld(), var1x)
                           );
                        },
                        true
                     )
                  )
            ))
         .then(CommandManager.literal("walls").executes(var0x -> x30301j211m4ot((ServerCommandSource)var0x.getSource(), (var0xx, var1x) -> {
            if (!x3sqkg3n6v972r.x2by5f9ehxvwk9().roles.enable_village_walls) {
               return new x1lf12nv67hipd(0, "Стены выключены в конфиге.");
            } else {
               var1x.x2f7z1gnnht5ab().x37dzr2zekycme = 0L;
               x3d5vk1zj3fvkj.get(var0xx.getWorld()).markDirty();
               return new x1lf12nv67hipd(1, "Запрошена обычная проверка периметра. Стены ставят строители, нужны материалы, дневное время и загруженные чанки.");
            }
         }, true)));
      LiteralArgumentBuilder var1 = CommandManager.literal("shortage");

      for (String var5 : new String[]{"food", "wood"}) {
         var1.then(
            CommandManager.literal(var5)
               .executes(
                  var1x -> x30301j211m4ot(
                     (ServerCommandSource)var1x.getSource(), (var1xx, var2) -> x8veg662b2f5p.xl7doh5h33e42(var1xx.getWorld(), var2, var5.equals("food")), true
                  )
               )
         );
      }

      var0.then(var1);
      LiteralArgumentBuilder var8 = CommandManager.literal("role");
      LiteralArgumentBuilder var9 = CommandManager.literal("change");

      for (x1rxiy9mtu5b3h var7 : x1rxiy9mtu5b3h.values()) {
         if (var7 != x1rxiy9mtu5b3h.x1saccptp3i00w && var7 != x1rxiy9mtu5b3h.x2xja04fpuesoh) {
            var9.then(
               CommandManager.literal(var7.serializedName())
                  .executes(
                     var1x -> x30301j211m4ot(
                        (ServerCommandSource)var1x.getSource(),
                        (var1xx, var2) -> x8veg662b2f5p.x2sc3yk5od1kev(var1xx.getWorld(), var2, var7, BlockPos.ofFloored(var1xx.getPosition())),
                        true
                     )
                  )
            );
            var8.then(
               ((LiteralArgumentBuilder)CommandManager.literal(var7.serializedName())
                     .executes(var1x -> x1myq7of069kaw((ServerCommandSource)var1x.getSource(), var7, 1)))
                  .then(
                     CommandManager.argument("count", IntegerArgumentType.integer(1, 16))
                        .executes(var1x -> x1myq7of069kaw((ServerCommandSource)var1x.getSource(), var7, IntegerArgumentType.getInteger(var1x, "count")))
                  )
            );
         }
      }

      return (LiteralArgumentBuilder<ServerCommandSource>)((LiteralArgumentBuilder)var0.then(var8)).then(var9);
   }

   private static int x1myq7of069kaw(ServerCommandSource var0, x1rxiy9mtu5b3h var1, int var2) {
      return x30301j211m4ot(
         var0, (var2x, var3) -> x8veg662b2f5p.x2zuchidlrmdh(var2x.getWorld(), var3, var1, var2, BlockPos.ofFloored(var2x.getPosition())), true
      );
   }

   private static int x30301j211m4ot(ServerCommandSource var0, BiFunction<ServerCommandSource, x15kgoaq6i3m6i, x1lf12nv67hipd> var1, boolean var2) {
      if (var2 && !x8veg662b2f5p.x2d8qtehqomuj6(var0.getWorld())) {
         return x3kspok0jyxjks(var0, "Сначала /smartcolonies film on. Не используйте команды на единственной копии мира.", 0);
      } else {
         Optional var3 = x2n55y2tom37h2.x3psqezd5jem54(var0.getWorld(), BlockPos.ofFloored(var0.getPosition()));
         if (var3.isEmpty()) {
            return x3kspok0jyxjks(var0, "Рядом нет зарегистрированной деревни. Подойдите к колоколу сгенерированной деревни.", 0);
         } else {
            x1lf12nv67hipd var4 = (x1lf12nv67hipd)var1.apply(var0, (x15kgoaq6i3m6i)var3.get());
            return x3kspok0jyxjks(var0, var4.x2fa4oag1sp0w9(), var4.x3cg1vvxnlossv());
         }
      }
   }

   private static int x3kspok0jyxjks(ServerCommandSource var0, String var1, int var2) {
      var0.sendFeedback(() -> Text.literal(var1), false);
      return var2;
   }

   private static int x2ed6fw6gftd0v(ServerCommandSource var0) {
      x3kspok0jyxjks(var0, "Съёмка только на копии мира: /smartcolonies film on | off | status | stop", 1);
      x3kspok0jyxjks(var0, "Подготовка: film supply; film population 24; film max (до 12 новых домов, до 48 жителей, ускоренная подготовка).", 1);
      x3kspok0jyxjks(
         var0, "Сцены: film expedition [X Y Z]; film develop [1..30 дней]; film build; film walls; film shortage food|wood; film role <профессия> [1..16].", 1
      );
      x3kspok0jyxjks(
         var0,
         "Ближайший житель: film change <профессия>. Реальный спрос без обхода кулдауна: film rebalance. Офлайн-сцена: film develop prepare → film develop 3 → film develop status.",
         1
      );
      x3kspok0jyxjks(
         var0,
         "on не отключает обычный ИИ. max, supply, role, population — постановочные команды; shortage и develop необратимы без копии мира. Экспедиция — основатель-житель, не ванильный странствующий торговец.",
         1
      );
      return 1;
   }
}
