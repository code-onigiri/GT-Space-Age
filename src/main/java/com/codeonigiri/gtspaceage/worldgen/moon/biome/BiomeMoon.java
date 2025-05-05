package com.codeonigiri.gtspaceage.worldgen.moon.biome;

import com.codeonigiri.gtspaceage.worldgen.moon.MoonWorldGen;
import net.minecraft.world.biome.Biome;

public class BiomeMoon extends Biome {
    public BiomeMoon() {
        super(new BiomeProperties(MoonWorldGen.MOON_DIM_NAME)
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
