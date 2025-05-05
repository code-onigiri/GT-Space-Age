package com.codeonigiri.gtspaceage.worldgen;

import com.codeonigiri.gtspaceage.Tags;
import com.codeonigiri.gtspaceage.dimentions.TestWorldGen;
import com.codeonigiri.gtspaceage.util.GTSALogger;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

// @GameRegistry.ObjectHolder は、フィールド名を登録名と一致させる場合に便利ですが、
// 今回はインスタンスを直接管理する方式を採用します。
public class ModBiomes {

    // 登録および後続処理で使用するバイオームインスタンスを保持
    public static final BiomeTest TEST_BIOME = new BiomeTest();

    @Mod.EventBusSubscriber(modid = Tags.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Biome> event){
            final IForgeRegistry<Biome> registry = event.getRegistry();
            GTSALogger.logger.info("Registering biome: " + TestWorldGen.TEST_DIM_NAME);

            // フィールドで保持しているインスタンスを登録
            // setRegistryName でリソースロケーション（MODIDと名前）を設定
            registry.register(TEST_BIOME.setRegistryName(Tags.MODID, TestWorldGen.TEST_DIM_NAME));
        }
    }

    public static void initBiomeManagerAndDictionary(){
        // 登録したインスタンス (TEST_BIOME) を使用する
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(TEST_BIOME, 10));
        BiomeDictionary.addTypes(TEST_BIOME, BiomeDictionary.Type.DRY);
    }
}