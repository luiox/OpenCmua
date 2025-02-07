package me.cmua.module;


import me.cmua.Cmua;
import me.cmua.api.event.eventbus.EventHandler;
import me.cmua.event.client.KeyPressEvent;
import me.cmua.event.client.KeyReleaseEvent;
import me.cmua.event.client.MousePressEvent;
import me.cmua.event.client.MouseReleaseEvent;
import me.cmua.module.client.AntiCheat;
import me.cmua.module.client.ClickGui;
import me.cmua.module.client.ClientSetting;
import me.cmua.module.client.Debug;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleManager {

    private static ModuleManager instance;
    private ArrayList<Module> modules = new ArrayList<>();

    private ModuleManager() {
        var mo = new Module();
        mo.setName("Test");
        mo.setCategory(ModuleCategory.Combat);
        addModule(mo);
        addModule(ClickGui.getInstance());
//        addModule(CustomFontModule.getInstance());
        addModule(AntiCheat.getInstance());
        addModule(ClientSetting.getInstance());
        addModule(Debug.getInstance());
        ClickGui.getInstance().setBind(GLFW.GLFW_KEY_Y);

        for (var module : modules) {
            module.setState(false);
            // 给所有Module添加通知的观察者
            module.addNotifyObserver(new EventSubscribeObserver());
            module.addNotifyObserver(new ChatNotifyObserver.DefaultChatNotifyObserver());
        }

        Cmua.EVENT_BUS.subscribe(this);
    }

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void removeModule(Module module) {
        modules.remove(module);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String string) {
        for (var module : modules) {
            if (module.getName().equalsIgnoreCase(string)) {
                return module;
            }
        }
        return null;
    }

    public void updateModules() {
        modules.stream().filter(Module::isOn).forEach(module -> {
            try {
                module.onUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @EventHandler
    public void onKeyReleaseEvent(KeyReleaseEvent event) {

    }

    @EventHandler
    public void onMouseReleaseEvent(MouseReleaseEvent event) {

    }

    @EventHandler
    public void onKeyPressEvent(KeyPressEvent event) {
        var key = event.getKey();

        onKeyPressed(key);
    }

    @EventHandler
    public void onMousePressEvent(MousePressEvent event) {
        var key = event.getKey();

        onKeyPressed(key);
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey == -1 || eventKey == 0) {
            return;
        }
        for (var module : modules) {
            if (module.getBind() == eventKey) {
                module.toggle();
            }
        }
    }
}
