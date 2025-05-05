package com.codeonigiri.gtspaceage.worldgen;

import com.codeonigiri.gtspaceage.Tags;
import com.codeonigiri.gtspaceage.util.GTSALogger;
import com.codeonigiri.gtspaceage.worldgen.moon.biome.BiomeMoon;
import com.codeonigiri.gtspaceage.worldgen.moon.MoonWorldGen;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@GameRegistry.ObjectHolder(Tags.MODID)
public class ModBiomes {

    // 登録および後続処理で使用するバイオームインスタンスを保持
    public static final BiomeMoon MOON_BIOME = new BiomeMoon();

    @Mod.EventBusSubscriber(modid = Tags.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Biome> event){
            final IForgeRegistry<Biome> registry = event.getRegistry();
            GTSALogger.logger.info("Registering biome: " + MoonWorldGen.MOON_DIM_NAME);

            // フィールドで保持しているインスタンスを登録
            // setRegistryName でリソースロケーション（MODIDと名前）を設定
            registry.register(MOON_BIOME.setRegistryName(Tags.MODID, MoonWorldGen.MOON_DIM_NAME));
        }
    }

    public static void initBiomeManagerAndDictionary(){
        // Moon biome should only be used in the Moon dimension, not in the overworld
        // So we don't add it to BiomeManager, which is for the overworld
        // BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(MOON_BIOME, 10));

        // Still add it to BiomeDictionary for type identification
        BiomeDictionary.addTypes(MOON_BIOME, BiomeDictionary.Type.DRY);
    }
}
