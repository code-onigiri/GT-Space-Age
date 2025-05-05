package com.codeonigiri.gtspaceage.dimentions;

import com.codeonigiri.gtspaceage.worldgen.ChunkGeneratorTest;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class TestWorldProvider extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return TestWorldGen.TEST_DIM_TYPE;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorTest(world);
    }
}
