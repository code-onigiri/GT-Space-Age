package com.codeonigiri.gtspaceage.loaders.recipes;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

import static com.codeonigiri.gtspaceage.common.machines.testmachine.TEST_RECIPE_MAP;
import static gregtech.api.GTValues.HV;
import static gregtech.api.GTValues.VA;

public class TestRecipe {
    public static void addRecipe(){
        TEST_RECIPE_MAP.recipeBuilder()
                .input(Item.getByNameOrId("minecraft:iron_ingot"), 1)
                .output(Item.getByNameOrId("minecraft:iron_ingot"), 2)
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("water"), 2000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("water"), 1000))
                .duration(2000)
                .EUt(VA[HV])
                .buildAndRegister();
    }
}
