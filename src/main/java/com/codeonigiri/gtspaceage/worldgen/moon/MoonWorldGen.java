package com.codeonigiri.gtspaceage.worldgen.moon;

import com.codeonigiri.gtspaceage.GTSpaceAge;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MoonWorldGen {

    public static final String MOON_DIM_NAME = "Moon";
    public static final int MOON_DIM_ID = 2; // Use a fixed ID for the Moon dimension
    public static final DimensionType MOON_DIM_TYPE = DimensionType.register(MOON_DIM_NAME, "_" + MOON_DIM_NAME.toLowerCase(), MOON_DIM_ID, MoonWorldProvider.class, false);

    public static final void registerDimensions(){
        DimensionManager.registerDimension(MOON_DIM_ID, MOON_DIM_TYPE);
        updateDimensionsConfig();
    }

    private static void updateDimensionsConfig() {
        try {
            File configDir = new File("config/gregtech");
            if (!configDir.exists()) {
                configDir.mkdirs();
            }

            File dimensionsFile = new File(configDir, "dimensions.json");
            JsonObject root;

            if (dimensionsFile.exists()) {
                JsonParser parser = new JsonParser();
                root = parser.parse(new FileReader(dimensionsFile)).getAsJsonObject();
            } else {
                root = new JsonObject();
                root.add("dims", new JsonArray());
            }

            JsonArray dims = root.getAsJsonArray("dims");

            // Check if Moon dimension is already in the config
            boolean moonExists = false;
            for (int i = 0; i < dims.size(); i++) {
                JsonObject dim = dims.get(i).getAsJsonObject();
                if (dim.has("dimID") && dim.get("dimID").getAsInt() == MOON_DIM_ID) {
                    moonExists = true;
                    break;
                }
            }

            // Add Moon dimension if it doesn't exist
            if (!moonExists) {
                JsonObject moonDim = new JsonObject();
                moonDim.addProperty("dimID", MOON_DIM_ID);
                moonDim.addProperty("dimName", MOON_DIM_NAME);
                dims.add(moonDim);

                // Write the updated config
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                FileWriter writer = new FileWriter(dimensionsFile);
                gson.toJson(root, writer);
                writer.close();

                GTSpaceAge.LOGGER.info("Added Moon dimension to GregTech dimensions.json");
            }
        } catch (IOException e) {
            GTSpaceAge.LOGGER.error("Failed to update GregTech dimensions.json", e);
        }
    }
}
