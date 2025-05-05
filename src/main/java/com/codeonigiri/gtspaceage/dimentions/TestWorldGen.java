package com.codeonigiri.gtspaceage.dimentions;

import net.minecraftforge.fml.common.registry.GameRegistry;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class TestWorldGen {

    public static final String TEST_DIM_NAME = "Test";
    public static final int TEST_DIM_ID = findFreeDimentionId();
    public static final DimensionType TEST_DIM_TYPE = DimensionType.register(TEST_DIM_NAME, "_" + TEST_DIM_NAME.toLowerCase(), TEST_DIM_ID, TestWorldProvider.class, false);

    public static final void registerDimensions(){
        DimensionManager.registerDimension(TEST_DIM_ID, TEST_DIM_TYPE);
    }

    @Nullable
    private static Integer findFreeDimentionId() {
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if (!DimensionManager.isDimensionRegistered(i)) {
                return i;

            }
        }

        return null;
    }
}
