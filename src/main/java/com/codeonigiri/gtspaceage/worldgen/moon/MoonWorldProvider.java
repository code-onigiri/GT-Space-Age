package com.codeonigiri.gtspaceage.worldgen.moon;

import com.codeonigiri.gtspaceage.worldgen.ModBiomes;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

public class MoonWorldProvider extends WorldProvider {

    @Override
    @MethodsReturnNonnullByDefault
    protected void init(){
        this.biomeProvider = new BiomeProviderSingle(ModBiomes.MOON_BIOME);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public DimensionType getDimensionType() {
        return MoonWorldGen.MOON_DIM_TYPE;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorMoon(world);
    }
}
