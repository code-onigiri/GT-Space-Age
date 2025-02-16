package com.codeonigiri.gtspaceage.client.texture.machines;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class GTSASingleMachinetexture {
    public static final OrientedOverlayRenderer TEST_OVERLAY = new OrientedOverlayRenderer("machines/testmachine");
    public static void preInit() {}
}
