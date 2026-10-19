<div align="center">

# Igoo Mod

**Một mod Minecraft Fabric mở rộng hệ thống kim loại, hợp kim và vật phẩm chế tạo, cũng như gia tăng độ khó cho việc sinh tồn.**

[![Minecraft](https://img.shields.io/badge/Minecraft-26.2-4CAF50?style=for-the-badge&logo=minecraft)](https://minecraft.net)
[![Fabric](https://img.shields.io/badge/Fabric-Loader-DBB69C?style=for-the-badge)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk)](https://openjdk.org)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)](LICENSE)

</div>

---

<!-- ## 📖 Mục lục

- [Giới thiệu](#-giới-thiệu)
- [Tính năng](#-tính-năng)
- [Yêu cầu hệ thống](#-yêu-cầu-hệ-thống)
- [Cài đặt & Build](#-cài-đặt--build)
- [Cấu trúc dự án](#-cấu-trúc-dự-án)
- [Hướng dẫn phát triển](#-hướng-dẫn-phát-triển)
- [Kiểm thử trong game](#-kiểm-thử-trong-game)
- [Xử lý sự cố](#-xử-lý-sự-cố)
- [Lộ trình phát triển](#-lộ-trình-phát-triển)
- [Đóng góp](#-đóng-góp)
- [Giấy phép](#-giấy-phép)
- [Tác giả](#-tác-giả)

--- -->

## 🌟 Giới thiệu

**Igoo Mod** là một dự án mod Fabric được xây dựng nhằm mở rộng hệ thống vật phẩm và chế tạo của Minecraft. Mod tập trung vào việc bổ sung các loại **kim loại và hợp kim mới**, thế giới, cấu trúc hoàn toàn mới, mang đến lối chơi sinh tồn hoàn toàn mới thay vì end game bằng cách đánh rồng.

Mod được phát triển với mục tiêu:
- 🎯 Cân bằng giữa vanilla và modded gameplay
- ⚡ Hiệu năng cao, không gây lag
- 🧩 Dễ mở rộng, code sạch và có tài liệu
- 🌐 Hỗ trợ đa ngôn ngữ (Anh / Việt)

---

## ✨ Tính năng

### 🔩 Nguyên liệu (Materials)
| Tên | ID | Mô tả |
| --- | --- | --- |
| Aluminum Ingot | `aluminum_ingot` | Thỏi nhôm – nhẹ |
| Bronze Ingot | `bronze_ingot` | Thỏi đồng thiếc – bền |
| Carbon Steel Ingot | `carbon_steel_ingot` | Thép carbon – cứng, dùng cho dụng cụ |
| Chromium Ingot | `chromium_ingot` | Thỏi crom – kim loại hiếm |
| Stainless Steel Ingot | `stainless_steel_ingot` | Thép không gỉ – cao cấp, bền bỉ |
| Tin Ingot | `tin_ingot` | Thỏi thiếc – nguyên liệu hợp kim, dễ uốn |
| Titanium Alloy | `titanium_alloy` | Hợp kim titan – siêu bền, nhẹ |
| Titanium Ingot | `titanium_ingot` | Thỏi titan – kim loại quý, độ bền cao |
| Tungsten Carbide Ingot | `tungsten_carbide_ingot` | Thép vonfram – cực cứng |
| Tungsten Ingot | `tungsten_ingot` | Thỏi vonfram – kim loại nặng |
| Raw Aluminum | `raw_aluminum` | Nhôm thô |
| Raw Chromium | `raw_chromium` | Crom thô |
| Raw Tin | `raw_tin` | Thiếc thô |
| Raw Titanium | `raw_titanium` | Titan thô |
| Raw Tungsten | `raw_tungsten` | Vonfram thô |

### ⚔️ Dụng cụ (Tools)
Mod bổ sung đầy đủ 5 loại dụng cụ (Kiếm, Cuốc chim, Rìu, Xẻng, Cuốc) cho 5 kim loại chính. Mỗi loại có cấp độ (Tier) tương ứng:
- **Nhôm (Aluminum):** Cấp Gỗ (Yếu nhưng độ bền tốt hơn).
- **Thiếc (Tin) & Crom (Chromium):** Cấp Đá.
- **Titan (Titanium):** Cấp Kim cương.
- **Vonfram (Tungsten):** Cấp Netherite (Cực mạnh).

| Tên | ID |
| --- | --- |
| Aluminum Sword / Pickaxe / Axe / Shovel / Hoe | `aluminum_sword`, `aluminum_pickaxe`, ... |
| Tin Sword / Pickaxe / Axe / Shovel / Hoe | `tin_sword`, `tin_pickaxe`, ... |
| Chromium Sword / Pickaxe / Axe / Shovel / Hoe | `chromium_sword`, `chromium_pickaxe`, ... |
| Titanium Sword / Pickaxe / Axe / Shovel / Hoe | `titanium_sword`, `titanium_pickaxe`, ... |
| Tungsten Sword / Pickaxe / Axe / Shovel / Hoe | `tungsten_sword`, `tungsten_pickaxe`, ... |

### 🍖 Thức ăn (Foods)
| Tên | ID | Mô tả |
| --- | --- | --- |
| Raw Chicken Wing | `chicken_wing` | Cánh gà sống |
| Cooked Chicken Wing | `chicken_wing_cooked` | Cánh gà chín (Nung, Hun khói, Lửa trại) |
| Raw Pork | `pork` | Thịt heo sống |
| Cooked Pork | `pork_cooked` | Thịt heo chín (Nung, Hun khói, Lửa trại) |
| Baguette | `baguette` | Bánh mì Pháp (Nướng từ 1 Kiện Rơm) |
| Block of Cheese | `cheese_full` | Phô mai nguyên khối (Từ Sữa + Lúa mì) |
| Cheese Slice | `cheese_slice` | Phô mai lát (Cắt từ khối, ăn nhanh) |

### 🪨 Khối & Quặng (Blocks & Ores)

**Khối kim loại (Metal Blocks):**
| Tên | ID | Yêu cầu dụng cụ |
| --- | --- | --- |
| Aluminum Block | `aluminum_block` | Cấp Đá |
| Bronze Block | `bronze_block` | Cấp Đá |
| Carbon Steel Block | `carbon_steel_block` | Cấp Đá |
| Chromium Block | `chromium_block` | Cấp Đá |
| Stainless Steel Block | `stainless_steel_block` | Cấp Sắt |
| Tin Block | `tin_block` | Cấp Đá |
| Titanium Alloy Block | `titanium_alloy_block` | Cấp Kim cương |
| Titanium Block | `titanium_block` | Cấp Kim cương |
| Tungsten Block | `tungsten_block` | Cấp Kim cương |
| Tungsten Carbide Block| `tungsten_carbide_block` | Cấp Kim cương |

**Quặng tự nhiên (Ores):**
| Tên | ID | Yêu cầu dụng cụ | Rơi ra |
| --- | --- | --- | --- |
| Aluminum Ore | `aluminum_ore` | Cấp Gỗ | Raw Aluminum (2-5) |
| Tin Ore | `tin_ore` | Cấp Đá | Raw Tin (2-4) |
| Chromium Ore | `chromium_ore` | Cấp Đá | Raw Chromium (1-2) |
| Titanium Ore | `titanium_ore` | Cấp Kim cương | Raw Titanium (1-2) |
| Tungsten Ore | `tungsten_ore` | Cấp Kim cương | Raw Tungsten (1) |

### 🚧 Tính năng dự kiến

- [x] Dụng cụ (Pickaxe, Axe, Shovel, Sword, Hoe) cho mỗi loại kim loại
- [ ] Giáp (Helmet, Chestplate, Leggings, Boots) cho mỗi loại kim loại
- [x] Công thức chế tạo cho lò nung (Furnace) và bàn chế tạo (Crafting Table)
- [ ] Hệ thống máy móc / năng lượng
- [ ] Cây trồng và thực phẩm mới
- [x] Thức ăn

---

## 💻 Yêu cầu hệ thống

### Để chạy mod
- **Minecraft Java Edition** phiên bản `21.2`
- **Fabric Loader** phiên bản `0.19.5`
- **Fabric API** phiên bản `0.161.0+26.2`

### Để phát triển mod
- **JDK 25** trở lên ([Tải tại đây](https://www.oracle.com/java/technologies/downloads/))
- **IntelliJ IDEA** hoặc **Visual Studio Code** (khuyến nghị dùng Extension Pack for Java)
- **Git** để quản lý phiên bản

---

## 🚀 Cài đặt & Build

### 1️⃣ Clone dự án

```bash
git clone https://github.com/earthen-r4x/minecraft-hardcore-fabric26.2.git
cd igoo-template-26.2
```

### 2️⃣ Build mod

-  Windows (PowerShell / CMD), macOS / Linux
```bash
./gradlew build
```

- File .jar sau khi build thành công sẽ nằm trong:
```bash
build/libs/igoo-<version>.jar
```

### 3️⃣ Chạy thử trong môi trường phát triển

| Lệnh | Mô tả |
| --- | --- |
| `./gradlew runClient` | Chạy Minecraft Client (có giao diện) |
| `./gradlew runServer` | Chạy Minecraft Server (không giao diện) |
| `./gradlew runDatagen` | Sinh file model, lang, recipe tự động |
| `./gradlew clean` | Xóa toàn bộ thư mục build |
| `./gradlew --stop` | Dừng tất cả Gradle daemon |

### 4️⃣ Cài đặt mod vào Minecraft
1. Cài Fabric Loader cho phiên bản Minecraft tương ứng

2. Tải Fabric API từ Modrinth và bỏ vào thư mục mods/

3. Bỏ file igoo-<version>.jar (vừa build) vào thư mục mods/

4. Khởi động game với profile Fabric

---

## 📁 Cấu trúc dự án

```text
igoo-template-26.2/
├── src/
│   ├── main/
│   │   ├── java/com/igoo/
│   │   │   ├── Igoo.java                 # Entry point chính (Đăng ký item, block, biome, worldgen)
│   │   │   ├── block/
│   │   │   │   └── ModBlocks.java        # Đăng ký khối, quặng và phân loại cấp độ dụng cụ
│   │   │   ├── item/
│   │   │   │   ├── ModItems.java         # Đăng ký vật phẩm, dụng cụ, thức ăn
│   │   │   │   └── ModToolMaterials.java # Cấu hình thông số vật liệu dụng cụ (độ bền, tốc độ,...)
│   │   │   ├── creativemodtab/
│   │   │   │   └── ModCreativeModeTabs.java # Đăng ký các tab sáng tạo (Materials, Tools, Blocks)
│   │   │   └── worldgen/
│   │   │       ├── ModWorldConfiguredFeatures.java # Cấu hình kích thước, khối bị thay thế của quặng
│   │   │       └── ModWorldPlacedFeatures.java     # Cấu hình tần suất và độ cao (Y-level) sinh quặng
│   │   │
│   │   └── resources/
│   │       ├── fabric.mod.json           # Metadata của mod
│   │       └── assets/igoo/
│   │           ├── lang/                 # File ngôn ngữ
│   │           │   ├── en_us.json
│   │           │   └── vi_vn.json
│   │           └── textures/
│   │               ├── item/             # Ảnh texture 16x16 cho vật phẩm/dụng cụ/thức ăn
│   │               └── block/            # Ảnh texture cho khối/quặng
│   │
│   ├── client/
│   │   └── java/com/igoo/client/
│   │       ├── IgooClient.java           # Entry point phía Client
│   │       └── datagen/                  # Hệ thống sinh dữ liệu tự động (Datagen)
│   │           ├── IgooDataGenerator.java        # Điểm khởi chạy Datagen
│   │           ├── ModBlockTagsProvider.java     # Sinh tag cho khối (cấp độ dụng cụ)
│   │           ├── ModItemTagsProvider.java      # Sinh tag cho vật phẩm (Swords, Pickaxes,...)
│   │           ├── ModBlockLootTableProvider.java # Sinh bảng rơi đồ (loot table) cho quặng/khối
│   │           ├── ModModelProvider.java         # Sinh model cho vật phẩm
│   │           ├── ModRecipeProvider.java        # Sinh công thức chế tạo và nấu ăn
│   │           └── ModWorldgenProvider.java      # Sinh file cấu hình worldgen
│   │
│   └── main/resources/
│       └── igoo.mixins.json              # Cấu hình Mixin
│
├── src/main/generated/                   # Thư mục chứa dữ liệu sinh ra tự động (KHÔNG SỬA TAY)
│   ├── assets/igoo/                      # Models, Blockstates, Items (do ModModelProvider sinh)
│   └── data/igoo/                        # Recipes, Loot Tables, Tags, Worldgen (do Datagen sinh)
│
├── build/
│   └── libs/                             # Chứa file .jar sau khi build thành công
├── build.gradle                          # Cấu hình build của Gradle
├── gradle.properties                     # Khai báo phiên bản Minecraft, Fabric, Java
├── settings.gradle
├── gradlew / gradlew.bat                 # Gradle wrapper
├── README.md
└── LICENSE
```

---

## 🛠️ Hướng dẫn phát triển

Dự án này sử dụng **Fabric Datagen** để tự động sinh ra các file JSON (Model, Blockstate, Recipe, Loot Table, Tags, Worldgen). Điều này giúp hạn chế tối đa việc phải viết tay các file cấu hình.

### ➕ 1. Thêm Vật phẩm cơ bản (Item)

- **Bước 1:** Đăng ký item trong `src/main/java/com/igoo/item/ModItems.java`:
```java
public static final Item MY_NEW_ITEM = registerItem("my_new_item", Item::new);
// Nhớ thêm MY_NEW_ITEM vào danh sách ALL_ITEMS
```

- Bước 2: Thêm model trong src/client/java/com/igoo/client/datagen/ModModelProvider.java:

```java
itemModelGenerator.generateFlatItem(ModItems.MY_NEW_ITEM, ModelTemplates.FLAT_ITEM);
```

- Bước 3: Thêm tên hiển thị vào 2 file ngôn ngữ lang/en_us.json và lang/vi_vn.json:

```json
"item.igoo.my_new_item": "My New Item"
```

- Bước 4: Tạo file ảnh 16x16 .png và đặt vào src/main/resources/assets/igoo/textures/item/my_new_item.png.

- Bước 5: Chạy lệnh ./gradlew runDatagen để sinh file JSON model.

### ⚔️ 2. Thêm Dụng cụ mới (Tool)

- Bước 1: Khai báo vật liệu trong ModToolMaterials.java:

```java
public static final ToolMaterial MY_MATERIAL = new ToolMaterial(
    BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 7.0f, 2.0f, 15,
    TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Igoo.MOD_ID, "my_ingot"))
);
```

- Bước 2: Đăng ký item trong ModItems.java (Sử dụng API mới của 26.2, không dùng SwordItem hay PickaxeItem nữa):

```java
public static final Item MY_SWORD = registerItem("my_sword", p -> new Item(p.sword(MY_MATERIAL, 3.0f, -2.4f)));
public static final Item MY_PICKAXE = registerItem("my_pickaxe", p -> new Item(p.pickaxe(MY_MATERIAL, 1.0f, -2.8f)));
// Nhớ thêm vào ALL_ITEMS và ALL_TOOLS
```

- Bước 3: Thêm model với ModelTemplates.FLAT_HANDHELD_ITEM (để cầm đúng hướng) trong ModModelProvider.java.

- Bước 4: Thêm công thức chế tạo vào ModRecipeProvider.java và thêm vào Tag công cụ (Swords, Pickaxes,...) trong ModItemTagsProvider.java.

### 🍖 3. Thêm Thức ăn mới (Food)

- Bước 1: Khai báo FoodProperties (độ no) và Consumable (thời gian ăn) trong ModItems.java:

```java
public static final FoodProperties MY_FOOD_PROPS = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6f).build();
public static final Consumable MY_CONSUMABLE = Consumables.defaultFood().build(); // Mặc định 1.6s, dùng consumeSeconds(0.8f) để ăn nhanh
```

- Bước 2: Đăng ký item với cả 2 thuộc tính:

```java
public static final Item MY_FOOD = registerItem("my_food", p -> new Item(p.food(MY_FOOD_PROPS, MY_CONSUMABLE)));
```

- Bước 3: Thêm công thức nấu/hun khói/lửa trại vào ModRecipeProvider.java bằng hàm cookFood() có sẵn.

### 🧱 4. Thêm Khối hoặc Quặng mới (Block/Ore)

- Bước 1: Đăng ký block trong ModBlocks.java:

```java
public static final Block MY_BLOCK = registerBlock("my_block", 
  properties -> new Block(properties.strength(3.0f, 6.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
// Nhớ thêm vào ALL_BLOCKS và list phân loại cấp độ (MINE_WITH_STONE, MINE_WITH_IRON, v.v.)
```

- Bước 2: Thêm model khối (thường là registerSimpleCubeAll) trong ModModelProvider.java.

- Bước 3: Khai báo Loot Table (rơi ra gì) trong ModBlockLootTableProvider.java.

- Bước 4: Thêm công thức chế tạo trong ModRecipeProvider.java.

- Bước 5: Chạy ./gradlew runDatagen.

### 🌍 5. Thêm Quặng vào Worldgen

- Bước 1: Khai báo cấu hình kích thước mạch quặng trong ModWorldConfiguredFeatures.java.

- Bước 2: Khai báo tần suất và độ cao (Y-level) trong ModWorldPlacedFeatures.java.

- Bước 3: Đăng ký với Biome trong hàm onInitialize() của Igoo.java thông qua BiomeModifications.addFeature().

###🎨 Quy ước đặt tên

| Loại | Quy ước | Ví dụ |
| --- | --- | --- |
| Class Java | PascalCase | ModItems, ModBlocks |
| Hằng số (Biến static) | SCREAMING_SNAKE_CASE | ALUMINUM_INGOT |
| ID vật phẩm / Khối | snake_case (chữ thường) | aluminum_ingot |
| File texture | snake_case | aluminum_ingot.png |
| Package | lowercase | com.igoo.item |

> [!IMPORTANT]
> Tuyệt đối không sửa trực tiếp các file trong thư mục src/main/generated/.
> Mọi thay đổi phải được thực hiện thông qua code Java trong thư mục src/client/java/com/igoo/client/datagen/ và chạy lại lệnh runDatagen.

### 📦 Thêm dependency mới

- Mở build.gradle và thêm vào khối dependencies:

```groovy
dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
    
    // Thêm mod dependency khác ở đây
}
```

---

## 🧪 Kiểm thử trong game
Sau khi chạy `./gradlew runClient`:

1. Mở Creative Inventory (phím E)

2. Nhấn vào phím mũi tên ở dưới nút tìm kiếm vật phẩm

3. Lựa chọn tab khối, vật liệu,...

4. Tìm các vật phẩm mà bạn muốn

### ⌨️ Phím tắt hữu ích khi phát triển

| Phím | Chức năng |
| --- | --- |
| `F3 + T` | Reload tài nguyên (texture, model, lang) |
| `F3 + H` | Bật/tắt tooltip chi tiết (hiện ID item) |
| `F3 + Q` | Hiện danh sách phím tắt debug |
| `F3 + A` | Reload chunk |

### 📝 Kiểm tra log
Log của game nằm ở:

- Client: <profile>/logs/latest.log

- Dev environment: hiển thị trực tiếp trong terminal

Tìm các dòng có tiền tố [igoo] hoặc com.igoo để debug.

---

## 🐛 Xử lý sự cố
### Lỗi thường gặp và cách khắc phục
| Lỗi | Nguyên nhân | Cách khắc phục |
| --- | --- | --- |
| `NullPointerException: Item id not set` | Thiếu `.setId()` trong `Item.Properties` | Thêm `ResourceKey` vào `Properties` khi khởi tạo item |
| `Missing block model: igoo:item/...` | Sai đường dẫn thư mục model | Đảm bảo thư mục là models/item (số ít), không phải models/items |
| `Timeout of 120000 reached waiting for exclusive access` | File Gradle bị khóa | Chạy `./gradlew --stop`, xóa `%USERPROFILE%\.gradle\wrapper\dists` |
| `spawn EFTYPE` (VS Code) | Lỗi extension `vscode-javac` cũ | Gỡ và cài Extension Pack for Java chính thức |
| `Unable to delete directory 'build'` | OneDrive/antivirus đang khóa file | Di chuyển dự án ra khỏi OneDrive, tắt antivirus tạm thời |
| Texture hiển thị dấu hỏi đen/trắng | Đường dẫn `layer0` sai | Kiểm tra `layer0` khớp với vị trí file PNG thực tế |
| Build chậm, ngốn CPU | VS Code bị quá tải extension | Tắt extension không cần, cấu hình `files.watcherExclude` |

### 🔍 Cách debug hiệu quả
1. Đọc kỹ stack trace: Lỗi ở dòng nào, class nào?
2. Dùng --stacktrace: Chạy ./gradlew build --stacktrace để có log chi tiết
3. Kiểm tra log game: Tìm dòng ERROR hoặc WARN có chứa igoo
4. So sánh với mod mẫu: Tham khảo fabric-example-mod

---

## 🗺️ Lộ trình phát triển

---

## 🤝 Đóng góp
Mọi đóng góp đều được hoan nghênh! Để đóng góp:

1. Fork dự án
2. Tạo nhánh mới: git checkout -b feature/tinh-nang-moi
3. Commit thay đổi: git commit -m "Thêm tính năng X"
4. Push lên nhánh: git push origin feature/tinh-nang-moi
5. Mở Pull Request

### 🐛 Báo cáo bug
Khi báo cáo bug, vui lòng cung cấp:

- Phiên bản Minecraft, Fabric Loader, Fabric API
- Phiên bản mod Igoo
- File log (logs/latest.log)
- Các bước tái hiện lỗi
- Ảnh chụp màn hình (nếu có)

---

## 📜 Giấy phép
Dự án này được phát hành theo giấy phép MIT License.

```text
MIT License

Copyright (c) 2026 Igoo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 📚 Tài liệu tham khảo
- [📘 Fabric Documentation](https://docs.fabricmc.net/) – Hướng dẫn chính thức
- [📗 Fabric Wiki](https://fabricmc.net/wiki/) – Wiki cộng đồng
- [📙 Minecraft Wiki](https://minecraft.wiki/) – Thông tin về game
- [📕 fabric-example-mod](https://github.com/FabricMC/fabric-example-mod) – Template chính thức
- [🎥 Modding by Kaupenjoe](https://youtube.com/@moddingbykaupenjoe) – Kênh YouTube hướng dẫn

---

## 👤 Tác giả

[![earthen-r4x](https://img.shields.io/badge/GitHub-earthen--r4x-181717?style=for-the-badge&logo=github)](https://github.com/earthen-r4x)

</div>
