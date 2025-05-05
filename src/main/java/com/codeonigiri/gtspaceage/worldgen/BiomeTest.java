package com.codeonigiri.gtspaceage.worldgen;

import com.codeonigiri.gtspaceage.dimentions.TestWorldGen;
import net.minecraft.world.biome.Biome;

public class BiomeTest extends Biome {
    public BiomeTest() {
        super(new BiomeProperties(TestWorldGen.TEST_DIM_NAME)
                .setBaseHeight(0.1F)
                .setRainDisabled()
        );

        setSpawnables();
    }

    private void setSpawnables(){
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
    }
}
