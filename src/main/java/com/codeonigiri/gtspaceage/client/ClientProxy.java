package com.codeonigiri.gtspaceage.client;

import com.codeonigiri.gtspaceage.client.texture.machines.GTSASingleMachinetexture;
import com.codeonigiri.gtspaceage.common.CommonProxy;
import com.codeonigiri.gtspaceage.util.GTSALogger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public void init(FMLInitializationEvent event){

    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        GTSALogger.logger.info("Loading Client preinit");
        super.preInit(e);
        GTSASingleMachinetexture.preInit();
    }
}
