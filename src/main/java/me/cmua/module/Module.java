package me.cmua.module;

import me.cmua.api.config.setting.BindSetting;
import me.cmua.api.config.setting.BooleanSetting;
import me.cmua.api.config.setting.IntSetting;
import me.cmua.api.module.ConfigModuleBase;
import me.cmua.utils.MinecraftUtil;
import me.cmua.utils.Wrapper;

import java.util.ArrayList;
import java.util.List;

public class Module extends ConfigModuleBase implements Wrapper {

    private final BindSetting bindSetting;
    private final BooleanSetting drawnSetting;
    private final IntSetting prioritySetting;
    private final List<INotifyObserver> notifyObservers = new ArrayList<>();
    private ModuleCategory category;

    public Module() {
        String name = "";
        ModuleCategory category = ModuleCategory.Misc;
        String description = "";
        int priority = ModulePriority.MEDIUM;
        if (getClass().isAnnotationPresent(ModuleInfo.class)) {
            // 有注解就从注解上获取信息
            var annotation = getClass().getAnnotation(ModuleInfo.class);
            name = annotation.name();
            category = annotation.category();
            description = annotation.description();
            priority = annotation.priority();
        }
        this.name = name;
        this.category = category;
        this.description = description;

        bindSetting = addSetting("Key", -1);
        drawnSetting = addSetting("Drawn", false);
        prioritySetting = addSetting("Priority", priority, 0, 4, 1);
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public void setCategory(ModuleCategory category) {
        this.category = category;
    }

    public String getDisplayName() {
        return getName();
    }

    public int getBind() {
        return bindSetting.getKey().getRawValue();
    }

    public void setBind(int key) {
        bindSetting.setKey(key);
    }

    public boolean isDrawn() {
        return drawnSetting.getValue().getRawValue();
    }

    public void setDrawn(boolean drawn) {
        drawnSetting.setValue(drawn);
    }

    public int getPriority() {
        return prioritySetting.getValue().getRawValue();
    }

    public void setPriority(int priority) {
        prioritySetting.setValue(priority);
    }

    public void addNotifyObserver(INotifyObserver observer) {
        notifyObservers.add(observer);
    }

    public void removeNotifyObserver(INotifyObserver observer) {
        notifyObservers.remove(observer);
    }

    // 保证enable、disable、toggle的on方法里面不会有null
    @Override
    public boolean onPreEnable() {
        return !MinecraftUtil.playerOrWorldIsNull();
    }

    @Override
    public boolean onPreDisable() {
        return !MinecraftUtil.playerOrWorldIsNull();
    }

    @Override
    public void enable() {
        notifyObservers.forEach((o) -> o.notify(this));
        super.enable();
    }

    @Override
    public void disable() {
        notifyObservers.forEach((o) -> o.notify(this));
        super.disable();
    }

    public void setState(boolean state) {
        if (this.state == state) return;
        if (state) {
            enable();
        } else {
            disable();
        }
    }

}
