package com.igoo.worldgen;

import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        // Chỉ đăng ký Region, bỏ hoàn toàn Surface Rules
        ModOverworldRegion.registerRegions();
    }
}