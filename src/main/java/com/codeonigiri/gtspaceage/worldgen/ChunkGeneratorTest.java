package com.codeonigiri.gtspaceage.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorTest implements IChunkGenerator {
    private final World world;
    private static final IBlockState END_STONE = Blocks.END_STONE.getDefaultState();
    private static final IBlockState AIR = Blocks.AIR.getDefaultState();
    private final Random random;

    public ChunkGeneratorTest(World world) {
        this.world = world;
        this.random = new Random(world.getSeed());
    }

    /**
     * Creates a semicircular crater depression at the specified location
     * @param primer The ChunkPrimer to modify
     * @param centerX Center X position within the chunk (0-15)
     * @param centerZ Center Z position within the chunk (0-15)
     * @param radius Radius of the crater
     * @param depth Maximum depth of the crater
     */
    private void generateCrater(ChunkPrimer primer, int centerX, int centerZ, int radius, int depth) {
        // Surface level (top of the terrain)
        int surfaceY = 60;

        // Generate a semicircular depression
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                // Skip if outside chunk boundaries
                if (x < 0 || x >= 16 || z < 0 || z >= 16) {
                    continue;
                }

                // Calculate distance from center (squared)
                int distSq = (x - centerX) * (x - centerX) + (z - centerZ) * (z - centerZ);

                // If within the radius of the crater
                if (distSq <= radius * radius) {
                    // Calculate depth at this point (deeper at center, shallower at edges)
                    // Using a semicircular formula: depth * sqrt(1 - (dist/radius)²)
                    double normalizedDist = Math.sqrt(distSq) / radius;
                    int craterDepth = (int) (depth * Math.sqrt(1 - normalizedDist * normalizedDist));

                    // Remove blocks to create the crater
                    for (int y = surfaceY; y > surfaceY - craterDepth && y > 0; y--) {
                        primer.setBlockState(x, y, z, AIR);
                    }
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();

        // Fill the chunk with End Stone from y=0 to y=60
        for (int xPos = 0; xPos < 16; xPos++) {
            for (int zPos = 0; zPos < 16; zPos++) {
                for (int yPos = 0; yPos <= 60; yPos++) {
                    primer.setBlockState(xPos, yPos, zPos, END_STONE);
                }
            }
        }

        // Occasionally add meteor craters (approximately 15% chance per chunk)
        if (random.nextFloat() < 0.15f) {
            // Randomize crater parameters
            int centerX = random.nextInt(16);
            int centerZ = random.nextInt(16);

            // Radius between 3 and 8 blocks
            int radius = 3 + random.nextInt(6);

            // Depth between 3 and 10 blocks
            int depth = 3 + random.nextInt(8);

            // Generate the crater
            generateCrater(primer, centerX, centerZ, radius, depth);
        }

        Chunk chunk = new Chunk(this.world, primer, x, z);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        return biome.getSpawnableList(EnumCreatureType.CREATURE);
    }

    @Override
    public @Nullable BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
