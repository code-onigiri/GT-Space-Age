package com.codeonigiri.gtspaceage.worldgen;

import com.codeonigiri.gtspaceage.Tags;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Tags.MODID)
public class ModBiomes {

    public final static BiomeTest test_biome = null;

    public static void initBiomeManagerAndDictionary(){
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT,new BiomeManager.BiomeEntry(test_biome, 16));
        BiomeDictionary.addTypes(test_biome, BiomeDictionary.Type.DRY);
    }
}
