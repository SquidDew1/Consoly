package me.squiddew.consoly;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.squiddew.consoly.services.PathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConsolyOptions {

    public static boolean dark = false;
    public static boolean show = true;
    public static boolean bold = false;
    public static boolean style = true;
    private static final Logger LOGGER = LoggerFactory.getLogger("Consoly");
    private static final String darkStr = "dark";
    private static final String showStr = "show";
    private static final String boldStr = "bold";
    private static final String styleStr = "style";
    private static final String FILE_NAME = "consoly-options.json";
    private static final Gson GSON = new
            GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void save(){
            Path configPath = getConfigPath();

            try (FileWriter writer = new FileWriter(String.valueOf(configPath))) {
                JsonObject json = new JsonObject();

                json.addProperty(darkStr, dark);
                json.addProperty(showStr, show);
                json.addProperty(boldStr, bold);
                json.addProperty(styleStr, style);

                GSON.toJson(json, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public static void load(){
        Path configPath = getConfigPath();
        if (!configPath.toFile().exists()){
            save();
        } else {
            try (FileReader reader = new FileReader(configPath.toFile())){
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                if (jsonObject.has(darkStr)){
                    dark = jsonObject.get(darkStr).getAsBoolean();
                }
                if (jsonObject.has(showStr)){
                    show = jsonObject.get(showStr).getAsBoolean();
                }
                if (jsonObject.has(boldStr)){
                    bold = jsonObject.get(boldStr).getAsBoolean();
                }
                if (jsonObject.has(styleStr)){
                    style = jsonObject.get(styleStr).getAsBoolean();
                }
                LOGGER.info("Loaded options for Consoly");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Path getConfigPath(){
        return PathHelper
                .getInstance()
                .getConfigDir()
                .resolve(FILE_NAME);
    }
}
