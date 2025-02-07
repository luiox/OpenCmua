package me.cmua.config;

import me.cmua.ManagerLoader;
import me.cmua.api.config.DefaultConfigManager;
import me.cmua.api.config.IConfig;

import java.io.*;
import java.util.Hashtable;
import java.util.List;

// 统一的配置管理器
public class ConfigManager extends DefaultConfigManager {
    private String defaultConfigName = "default";

    public ConfigManager() {
        var pathPrefix = ConfigUtil.getConfigPath() + File.separator + defaultConfigName + File.separator;

        addConfig(pathPrefix + "command.json", ManagerLoader.commandManager);
        addConfig(pathPrefix + "friend.json", ManagerLoader.friendManager);

        for (var module : ManagerLoader.moduleManager.getModules()) {
            addConfig(pathPrefix + module.getCategory().toString().toLowerCase()
                    + File.separator + module.getConfigName() + ".json", module);
        }
    }

    public void setConfigName(String oldName, String newName) {
        var newConfig = new Hashtable<String, List<IConfig>>();
        for (var entry : configs.entrySet()) {
            var key = entry.getKey();
            var list = entry.getValue();
            var newPath = key.replace(oldName, newName);
            newConfig.put(newPath, list);
        }
        configs = newConfig;
        defaultConfigName = newName;
    }

    public String getConfigName() {
        return defaultConfigName;
    }

    @Override
    public void writeConfig() {
        // 先写一个配置文件
        var folder = new File(ConfigUtil.getConfigPath());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, "CurrentConfig.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(defaultConfigName);
            bufferedWriter.newLine(); // 如果你想在内容后添加一个新行
            bufferedWriter.flush(); // 确保所有内容都被写入到文件中
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 再写配置文件
        super.writeConfig();
    }

    @Override
    public void readConfig() {
        File folder = new File(ConfigUtil.getConfigPath());
        File file = new File(folder, "CurrentConfig.txt");

        // 检查文件是否存在
        if (file.exists() && file.isFile()) {
            try (var fileReader = new FileReader(file);
                 var bufferedReader = new BufferedReader(fileReader)) {
                // 读取第一行，配置文件只有一行文件夹名字
                var newName = bufferedReader.readLine();
                setConfigName(defaultConfigName, newName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 文件不存在或不是一个文件，可以根据需要处理这种情况
            ManagerLoader.logManager.info("Config file does not exist.");
        }
        super.readConfig();
    }
}