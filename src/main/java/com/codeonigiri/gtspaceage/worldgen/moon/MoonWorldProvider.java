package com.codeonigiri.gtspaceage.worldgen.moon;

import com.codeonigiri.gtspaceage.worldgen.ModBiomes;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

public class MoonWorldProvider extends WorldProvider {

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_){
        return new Vec3d(250, 0, 0);
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks){
        return new Vec3d(250, 0, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight(){
        return 0.0F;
    }

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

    @Nullable
    @SideOnly(Side.CLIENT)
    public net.minecraftforge.client.IRenderHandler getSkyRenderer() {
        return super.getSkyRenderer();
    }
}
