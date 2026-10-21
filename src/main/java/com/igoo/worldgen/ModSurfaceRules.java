package com.igoo.worldgen;

import com.igoo.block.ModBlocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {

    // Tạo một bộ quy tắc riêng cho biome cursed_savanna
    public static SurfaceRules.RuleSource makeCursedSavannaRules() {
        // Quy tắc 1: Đặt Cursed Grass Block ở lớp trên cùng (độ sâu từ 0 đến -1 tính từ bề mặt)
        SurfaceRules.RuleSource topRule = SurfaceRules.ifTrue(
                SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
                SurfaceRules.state(ModBlocks.Cursed_grass_block.defaultBlockState())
        );

        // Quy tắc 2: Đặt Cursed Dirt ở các lớp ngay bên dưới (độ sâu từ 1 đến 3)
        SurfaceRules.RuleSource fillerRule = SurfaceRules.ifTrue(
                SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR),
                SurfaceRules.state(ModBlocks.Cursed_dirt.defaultBlockState())
        );

        // Kết hợp các quy tắc lại với nhau bằng Sequence (chạy lần lượt từ trên xuống dưới)
        return SurfaceRules.sequence(topRule, fillerRule);
    }
}