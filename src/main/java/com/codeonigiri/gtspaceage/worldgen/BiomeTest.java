package com.codeonigiri.gtspaceage.worldgen;

import com.codeonigiri.gtspaceage.dimentions.TestWorldGen;
import net.minecraft.world.biome.Biome;

public class BiomeTest extends Biome {
    public BiomeTest(BiomeProperties properties) {
        super(new BiomeProperties(TestWorldGen.TEST_DIM_NAME)
                .setBaseHeight(0.1F)
                .setRainDisabled()
        );
    }

    @Override


}
