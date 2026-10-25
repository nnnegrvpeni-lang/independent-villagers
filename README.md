# Independent Villagers (Smart Colonies)

Мод для Minecraft **1.21.11** (Fabric Loader), добавляющий автономное развитие ванильных деревень жителями: строительство домов, добычу ресурсов (дерево, камень, руда, рыбалка, скотоводство), ополчение и экономику.

---

## 🛠 Технический стек
- **Minecraft**: `1.21.11`
- **Fabric Loader**: `0.19.3`
- **Fabric API**: `0.141.6+1.21.11`
- **Yarn Mappings**: `1.21.11+build.6`
- **Java**: `21` (поддерживается Java 25)
- **Сборщик**: Gradle 9.2.1 / Loom 1.14.10

---

## 📋 История изменений и проделанная работа

### 1. Деобфускация и восстановление исходного кода
- Расшифровано **985** `ConstantDynamic` констант и инлайнено **1796** `invokedynamic` вызовов `CallSite`.
- Выполнено ремаппирование промежуточных имён на актуальные Yarn mappings (`1.21.11+build.6`).
- Извлечены и подключены все **1248** NBT-структур и ассетов.
- Восстановлены сигнатуры коллекций, дженерики (`List<VillagerEntity>`, `Map<Identifier, Integer>`, и др.) и устранены сбои декомпиляции Java Records.

### 2. Исправление отображения имён и ролей жителей
- **Проблема**: Над жителями отображались сырые ключи локализации `entity.smart_colonies.role.xhds9crfjdp9i` вместо читаемых имён.
- **Причина**: Имена ролей в enum брались из обфусцированных констант, а в `ru_ru.json` лежали канонические ключи (`fisherman`, `elder`, `guard`).
- **Решение**: В enum ролей восстановлены канонические сериализованные имена, добавлены обратные алиасы в языковые файлы и механизм авто-обновления NBT для уже созданных миров.

### 3. Рефакторинг и деобфускация кодовой базы (все 145 классов)
- **Проблема**: Все классы логики и миксинов носили обфусцированные имена (`x1wie612630ex`, `xpmscpidlka0a`, `x12eywdeqyc6hd` и т.д.), что делало разработку и понимание архитектуры крайне затруднительными.
- **Решение**: Выполнен полный смысловой рефакторинг:
  - **113 классов доменной логики** в `net.smartcolonies.r`:
    - `FarmerFieldTask`, `FarmerFieldPhase`, `FarmerIrrigationHelper`, `FarmerCropPlanner` — система фермерства.
    - `BuilderConstructionTask`, `BuilderStarterChestTask`, `BuilderRepairTask`, `BuilderVillageWallTask`, `BuilderRoadTask` — система строительства.
    - `ColonyManager`, `Colony`, `ColonyState`, `ColonyRole`, `ConstructionJob`, `ConstructionStatus` — ядро колонии и данные.
    - `MilitiaCombatTask`, `VillagerRaidShelterTask`, `IronGolemAttackHelper` — защита и ополчение.
    - `LumberjackTask`, `MinerExpeditionTask`, `FishermanTask`, `RancherAnimalTask` — специализированные роли.
    - `ColonyConfig`, `VillageConfig`, `RolesConfig`, `WorkConfig`, `DebugConfig` — модульная конфигурация.
  - **32 класса миксинов** в `net.smartcolonies.mixin` и конфиг `smart_colonies.mixins.json`:
    - `VillagerEntityMixin`, `WorldChunkMixin`, `StructureTemplateAccessor`, `IronGolemEntityMixin`, `ActivityInvoker`, `SensorTypeInvoker`, `FarmerVillagerTaskMixin`, `LivingEntityMixin`, `MinecraftServerMixin` и др.
- **Результат**: Кодовая база теперь чистая, структурированная и читаемая, проект собирается на 100% без ошибок.

---

## 🚀 Сборка проекта
Для сборки мода выполните:
```bash
./gradlew build
```
Готовый `.jar` файл будет создан в `build/libs/`.
