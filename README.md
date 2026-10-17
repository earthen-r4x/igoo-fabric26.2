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

### 🔩 Vật phẩm hiện có (Items)
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

### 🔩 Khối hiện có (Blocks)
| Tên | ID |Mô tả |
| --- | --- | --- |
| Aluminum Block | `aluminum_block` | Khối nhôm |
| Bronze Block | `bronze_block` | Khối đồng thiếc |
| Carbon Steel Block | `carbon_steel_block` | Khối thép carbon |
| Chromium Block | `chromium_block` | Khối crom |
| Stainless Steel Block | `stainless_steel_block` | Khối thép không gỉ |
| Tin Block | `tin_block` | Khối thiếc |
| Titanium Alloy Block | `titanium_alloy_block` | Khối hợp kim titan |
| Titanium Block | `titanium_block` | Khối titan |
| Tungsten Block | `tungsten_block` | Khối vonfram |
| Tungsten Carbide Block| `tungsten_carbide_block` | Khối thép vonfram |

### 🚧 Tính năng dự kiến

- [ ] Dụng cụ (Pickaxe, Axe, Shovel, Sword, Hoe) cho mỗi loại kim loại
- [ ] Giáp (Helmet, Chestplate, Leggings, Boots) cho mỗi loại kim loại
- [ ] Công thức chế tạo cho lò nung (Furnace) và bàn chế tạo (Crafting Table)
- [ ] Hệ thống máy móc / năng lượng
- [ ] Cây trồng và thực phẩm mới

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

```
igoo-template-26.2/
├── src/
│   ├── main/
│   │   ├── java/com/igoo/
│   │   │   ├── Igoo.java                 # Entry point chính của mod
│   │   │   ├── item/
│   │   │   │   └── ModItems.java         # Đăng ký tất cả vật phẩm
│   │   │   ├── block/                    # Đăng ký khối (dự kiến)
│   │   │   │   └── ModBlocks.java
│   │   │   └── util/                     # Các tiện ích
│   │   ├── resources/
│   │   │   ├── fabric.mod.json           # Metadata của mod
│   │   │   └── assets/igoo/
│   │   │       ├── lang/                 # File ngôn ngữ
│   │   │       │   ├── en_us.json
│   │   │       │   └── vi_vn.json
│   │   │       ├── textures/item/        # Ảnh texture 16x16
│   │   │       │   ├── aluminum_ingot.png
│   │   │       │   └── ...
│   │   │       └── models/               # Model (được sinh tự động)
│   │   └── ...
│   ├── client/
│   │   └── java/com/igoo/client/
│   │       ├── IgooClient.java
│   │       └── datagen/
│   │           ├── IgooDataGenerator.java
│   │           └── ModModelProvider.java
│   └── main/resources/
│       └── igoo.mixins.json
├── build/
│   └── libs/                             # File .jar sau khi build
├── build.gradle                          # Cấu hình build
├── gradle.properties                     # Phiên bản Minecraft, Fabric...
├── settings.gradle
├── gradlew / gradlew.bat                 # Gradle wrapper
├── README.md
└── LICENSE
```

---

## 🛠️ Hướng dẫn phát triển

### ➕ Thêm vật phẩm mới

- Bước 1: Đăng ký item trong ModItems.java
``` java
public static final Item MY_NEW_ITEM = register("my_new_item");
```

- Bước 2: Thêm model trong ModModelProvider.java
```java
@Override
public void generateItemModels(ItemModelGenerators itemModelGenerator) {
    itemModelGenerator.generateFlatItem(ModItems.MY_NEW_ITEM, ModelTemplates.FLAT_ITEM);
}
```

- Bước 3: Thêm tên hiển thị trong lang/en_us.json
```json
{
  "item.igoo.my_new_item": "My New Item"
}
```
- Và lang/vi_vn.json:

```json
{
  "item.igoo.my_new_item": "Vật Phẩm Mới"
}
```

- Bước 4: Thêm texture
-- Đặt file ảnh 16x16 PNG vào:

```text
src/main/resources/assets/igoo/textures/item/my_new_item.png
```

- Bước 5: Sinh lại tài nguyên
```bash
./gradlew runDatagen
```

### 🎨 Quy ước đặt tên

| Loại | Quy ước | Ví dụ |
| --- | --- | --- |
| Class Java | PascalCase | `ModItems`, `ModBlocks`
| Hằng số | SCREAMING_SNAKE_CASE | `ALUMINUM_INGOT`
| ID vật phẩm | snake_case | `aluminum_ingot`
| File texture | snake_case | `aluminum_ingot.png`
| Package | lowercase | `com.igoo.item`

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

2. Vào tab Ingredients (biểu tượng nguyên liệu)

3. Tìm các vật phẩm của mod với tiền tố igoo:

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
