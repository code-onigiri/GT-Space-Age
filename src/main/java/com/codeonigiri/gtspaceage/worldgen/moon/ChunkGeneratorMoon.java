package com.codeonigiri.gtspaceage.worldgen.moon;

import com.codeonigiri.gtspaceage.worldgen.ModBiomes;
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

public class ChunkGeneratorMoon implements IChunkGenerator {
    private final World world;
    private static final IBlockState END_STONE = Blocks.END_STONE.getDefaultState();
    private static final IBlockState AIR = Blocks.AIR.getDefaultState();
    private final Random random;
    private static final int BASE_HEIGHT = 60;

    public ChunkGeneratorMoon(World world) {
        this.world = world;
        this.random = new Random(world.getSeed());
    }

    /**
     * Generates a height value for the given x,z coordinates with gentle undulations
     * @param x X coordinate in world space
     * @param z Z coordinate in world space
     * @return Height value between BASE_HEIGHT-5 and BASE_HEIGHT+5
     */
    private int getHeightAt(int x, int z) {
        // Use simplex-like noise by combining multiple frequencies
        // This creates more natural looking terrain than pure random
        double noise1 = Math.sin(x * 0.05) * Math.cos(z * 0.05) * 2.5;
        double noise2 = Math.sin(x * 0.02 + z * 0.03) * 1.5;
        double noise3 = Math.cos(x * 0.07 - z * 0.04) * 1.0;

        // Combine the noise values and round to integer
        int heightVariation = (int) Math.round(noise1 + noise2 + noise3);

        // Clamp to range -5 to +5
        heightVariation = Math.max(-5, Math.min(5, heightVariation));

        return BASE_HEIGHT + heightVariation;
    }

    /**
     * Creates a semicircular crater depression at the specified world location
     * This version can span across chunk boundaries
     * @param worldX Center X position in world coordinates
     * @param worldZ Center Z position in world coordinates
     * @param radius Radius of the crater
     * @param depth Maximum depth of the crater
     */
    private void generateCraterInWorld(int worldX, int worldZ, int radius, int depth) {
        // Generate a semicircular depression
        for (int x = worldX - radius; x <= worldX + radius; x++) {
            for (int z = worldZ - radius; z <= worldZ + radius; z++) {
                // Calculate distance from center (squared)
                int distSq = (x - worldX) * (x - worldX) + (z - worldZ) * (z - worldZ);

                // If within the radius of the crater
                if (distSq <= radius * radius) {
                    // Get the surface height at this position (with undulations)
                    int surfaceY = getHeightAt(x, z);

                    // Calculate depth at this point (deeper at center, shallower at edges)
                    // Using a semicircular formula: depth * sqrt(1 - (dist/radius)²)
                    double normalizedDist = Math.sqrt(distSq) / radius;
                    int craterDepth = (int) (depth * Math.sqrt(1 - normalizedDist * normalizedDist));

                    // Remove blocks to create the crater
                    for (int y = surfaceY; y > surfaceY - craterDepth && y > 0; y--) {
                        BlockPos pos = new BlockPos(x, y, z);
                        world.setBlockState(pos, AIR, 2);
                    }
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();

        // For each column in the chunk
        for (int xPos = 0; xPos < 16; xPos++) {
            for (int zPos = 0; zPos < 16; zPos++) {
                // Calculate world coordinates
                int worldX = x * 16 + xPos;
                int worldZ = z * 16 + zPos;

                // Get the height at this position (with undulations)
                int surfaceHeight = getHeightAt(worldX, worldZ);

                // Fill the column with End Stone from y=0 to the surface height
                for (int yPos = 0; yPos <= surfaceHeight; yPos++) {
                    primer.setBlockState(xPos, yPos, zPos, END_STONE);
                }
            }
        }

        Chunk chunk = new Chunk(this.world, primer, x, z);
        byte[] abyte = chunk.getBiomeArray();
        final byte b = (byte) Biome.getIdForBiome(ModBiomes.MOON_BIOME);
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = b;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        // Occasionally add meteor craters (approximately 5% chance per chunk)
        if (random.nextFloat() < 0.05f) {
            // Convert chunk coordinates to world coordinates for the center of the chunk
            int worldX = x * 16 + 8;
            int worldZ = z * 16 + 8;

            // Add some randomness to the position within the chunk
            worldX += random.nextInt(16) - 8;
            worldZ += random.nextInt(16) - 8;

            // Radius between 8 and 20 blocks
            int radius = 8 + random.nextInt(12);

            // Depth between 3 and 6 blocks
            int depth = 3 + random.nextInt(3);

            // Generate the crater that can span across chunks
            generateCraterInWorld(worldX, worldZ, radius, depth);
        }
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
