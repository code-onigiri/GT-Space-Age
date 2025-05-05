package com.codeonigiri.gtspaceage.worldgen.moon;

import com.codeonigiri.gtspaceage.worldgen.moon.biome.BiomeProviderMoon;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeMoon extends WorldType {

    // Static instance for easy access
    public static final WorldTypeMoon INSTANCE = new WorldTypeMoon();

    public WorldTypeMoon() {
        super(MoonWorldGen.MOON_DIM_NAME);
    }

    @Override
    public BiomeProvider getBiomeProvider(World world){
        return new BiomeProviderMoon();
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions){
        return new ChunkGeneratorMoon(world);
    }

    @Override
    public boolean isCustomizable() {
        // Return true if you want to add custom options to this world type
        return false;
    }

    @Override
    public float getCloudHeight() {
        // Set a custom cloud height for this world type
        return 128.0F;
    }

    @Override
    public boolean hasInfoNotice() {
        // Return true to display a notice when this world type is selected
        return true;
    }

    @Override
    public double getHorizon(World world) {
        // Set the horizon level for this world type
        return 63.0D;
    }
}
