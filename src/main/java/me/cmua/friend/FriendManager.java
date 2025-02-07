package me.cmua.friend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import me.cmua.api.config.ConfigException;
import me.cmua.api.config.IConfig;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;

public class FriendManager implements IConfig {
    public final ArrayList<String> friendList = new ArrayList<>();

    public FriendManager() {
        addFriend("Lo_cy");
        addFriend("洛兮");
    }

    public boolean isFriend(String name) {
        return friendList.contains(name);
    }

    public void removeFriend(String name) {
        friendList.remove(name);
    }

    public void addFriend(String name) {
        if (!friendList.contains(name)) {
            friendList.add(name);
        }
    }

    public boolean isFriend(PlayerEntity entity) {
        return isFriend(entity.getGameProfile().getName());
    }

    @Override
    public String getConfigName() {
        return "Friend";
    }

    @Override
    public JsonElement toConfig() {
        var list = new JsonArray();
        for (String str : friendList) {
            list.add(str);
        }
        return list;
    }

    @Override
    public void fromConfig(JsonElement jsonElement) throws ConfigException {
        if (!jsonElement.isJsonArray()) {
            throw new ConfigException("Config must be a JSON array");
        } else {
            var config = jsonElement.getAsJsonArray();
            config.forEach(friend -> {
                addFriend(friend.getAsString());
            });
        }
    }
}
