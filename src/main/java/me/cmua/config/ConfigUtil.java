package me.cmua.config;

import com.google.gson.JsonObject;
import me.cmua.Cmua;
import me.cmua.utils.MinecraftUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigUtil {
    public static String getConfigPath() {
        return MinecraftUtil.getRunPath() + File.separator + Cmua.MOD_NAME.toLowerCase();
    }

    public static File getFile(String s) {
        File folder = new File(MinecraftUtil.getRunPath() + File.separator + Cmua.MOD_NAME.toLowerCase());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return new File(folder, s);
    }

    public static void writeJsonToFile(JsonObject root, File file) {
        // 写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(root.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
