package net.smartcolonies.r;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.Identifier;

public final class VillageCatalog {
   private static final Map<String, CatalogStyleEntry> x12zy4sxc537f5 = xpemk38ztgjx4();

   private VillageCatalog() {
   }

   private static Map<String, CatalogStyleEntry> xpemk38ztgjx4() {
      LinkedHashMap var0 = new LinkedHashMap();
      var0.put("savanna", new CatalogStyleEntry(HouseVariantCatalog.xk7wovhaogxn4, HouseVariantCatalog.x11oxp2ntj65hs));
      var0.put("taiga", new CatalogStyleEntry(VillageBuildingCatalog.x22d0iut9v3npb, VillageBuildingCatalog.x1krr6wlni86ip));

      try (InputStream var1 = VillageCatalog.class.getResourceAsStream("/data/smart_colonies/village_catalog.json")) {
         if (var1 == null) {
            throw new IllegalStateException("Missing village catalog");
         }

         JsonObject var2 = JsonParser.parseReader(new InputStreamReader(var1, StandardCharsets.UTF_8)).getAsJsonObject();

         for (String var4 : List.of("plains", "desert", "snowy")) {
            JsonObject var5 = var2.getAsJsonObject(var4);
            ArrayList var6 = new ArrayList();
            ArrayList var7 = new ArrayList();
            var5.getAsJsonArray("homes").forEach(var1x -> var6.add(var1x.getAsString()));
            var5.getAsJsonArray("all").forEach(var1x -> var7.add(var1x.getAsString()));
            if (var6.isEmpty() || !var7.containsAll(var6)) {
               throw new IllegalStateException("Invalid catalog: " + var4);
            }

            var0.put(var4, new CatalogStyleEntry(List.copyOf(var6), List.copyOf(var7)));
         }
      } catch (IOException var10) {
         throw new ExceptionInInitializerError(var10);
      }

      return Collections.unmodifiableMap(var0);
   }

   public static Set<String> x1wyc34sve24jn() {
      return x12zy4sxc537f5.keySet();
   }

   public static List<String> xrbmqlm78i747(String var0) {
      return x12zy4sxc537f5.get(var0).x21whxpe5mewip();
   }

   public static List<String> x3tmz8uu7gcem2(String var0) {
      return x12zy4sxc537f5.get(var0).x1egj5ug4fom0i();
   }

   public static Identifier x4jnab85o8lop(String var0, String var1) {
      if (!x3tmz8uu7gcem2(var0).contains(var1)) {
         throw new IllegalArgumentException("Unknown village template: " + var0 + "/" + var1);
      } else {
         return Identifier.of("smart_colonies", "village/" + var0 + "/houses/" + var0 + "_" + var1);
      }
   }

   public static Identifier x2xvromqtmmr47(String var0, int var1) {
      String var10000;
      if (var1 < 2) {
         var10000 = "small_house_1";
      } else {
         switch (var0) {
            case "taiga":
            case "snowy":
               var10000 = "medium_house_3";
               break;
            case "plains":
               var10000 = "big_house_1";
               break;
            default:
               var10000 = "medium_house_2";
         }
      }

      String var2 = var10000;
      return x4jnab85o8lop(var0, var2);
   }
}
