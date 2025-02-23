package com.codeonigiri.gtspaceage.common.machines;

import com.codeonigiri.gtspaceage.Tags;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.util.GTUtility;
import net.minecraft.util.ResourceLocation;

import static com.codeonigiri.gtspaceage.client.texture.machines.GTSASingleMachinetexture.TEST_OVERLAY;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class testmachine {
    public static SimpleMachineMetaTileEntity[] testMachine = new SimpleMachineMetaTileEntity[GTValues.V.length-1];

    public static void init(){
        registerSingleMachine();
    }

    public static final RecipeMap<SimpleRecipeBuilder> TEST_RECIPE_MAP = new RecipeMap<>("testmachine",
            2,2,1,1,
            new SimpleRecipeBuilder(),false)
                .setSlotOverlay(false,false, GuiTextures.SLOT)
                .setSlotOverlay(true,false, GuiTextures.SLOT)
                .setSlotOverlay(false,true, GuiTextures.FLUID_SLOT)
                .setSlotOverlay(true,true, GuiTextures.FLUID_SLOT)
                .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);

    public static void registerSingleMachine(){
        int id = 18000;
        for (int i = 1; i < testMachine.length; i++) {
            if (i <= GTValues.UV){
                String voltageName = GTValues.VN[i].toLowerCase();
                testMachine[i] = registerMetaTileEntity(id + (i -1),
                        new SimpleMachineMetaTileEntity(
                                new ResourceLocation(Tags.MODID, String.format("%s.%s", "testmachine", voltageName)),
                                TEST_RECIPE_MAP,
                                TEST_OVERLAY,
                                i,
                                true,
                                GTUtility.hvCappedTankSizeFunction
                        ));
            }
        }
    }
}
