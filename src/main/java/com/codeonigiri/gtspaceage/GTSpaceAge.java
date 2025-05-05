package com.codeonigiri.gtspaceage;

import com.codeonigiri.gtspaceage.common.CommonProxy;
import com.codeonigiri.gtspaceage.dimentions.TestWorldGen;
import com.codeonigiri.gtspaceage.worldgen.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12.2]")
public class GTSpaceAge {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @SidedProxy(
        modId = Tags.MODID,
        clientSide = "com.codeonigiri.gtspaceage.client.ClientProxy",
        serverSide = "com.codeonigiri.gtspaceage.common.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc. (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        // register to the event bus so that we can listen to events
        MinecraftForge.EVENT_BUS.register(this);
        TestWorldGen.registerDimensions();
        // Initialize the WorldTypeTest
        LOGGER.info("Initializing WorldTypeTest: " + com.codeonigiri.gtspaceage.worldgen.WorldTypeTest.INSTANCE.getName());
        LOGGER.info("I am " + Tags.MODNAME + " + at version " + Tags.VERSION);
        proxy.preInit(event);
    }

    @SubscribeEvent
    // Register recipes here (Remove if not needed)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        proxy.registerRecipes(event);
    }

    @SubscribeEvent
    // Register items here (Remove if not needed)
    public void registerItems(RegistryEvent.Register<Item> event) {

    }

    @SubscribeEvent
    // Register blocks here (Remove if not needed)
    public void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        ModBiomes.initBiomeManagerAndDictionary();
        proxy.init(event);
    }

    @EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }
}
