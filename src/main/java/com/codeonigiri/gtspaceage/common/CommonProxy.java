package com.codeonigiri.gtspaceage.common;

import com.codeonigiri.gtspaceage.common.machines.testmachine;
import com.codeonigiri.gtspaceage.loaders.recipes.GTSARecipe;
import com.codeonigiri.gtspaceage.util.GTSALogger;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {
    public void init(FMLInitializationEvent event){

    }

    @SubscribeEvent
    // Register recipes here (Remove if not needed)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GTSALogger.logger.info("Registering recipes");
        testmachine.init();
        GTSARecipe.addRecipe();
    }

    public void preInit(FMLPreInitializationEvent event) {
    }
}
