package me.cmua.gui.clickgui;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.type.ImBoolean;
import me.cmua.ManagerLoader;
import me.cmua.api.module.ConfigModuleBase;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryTab {
    private final List<Module> modules = new ArrayList<>();
    private final Map<Module, ImBoolean> stateList = new HashMap<>();
    private boolean visible;
    private String title;

    public CategoryTab(ModuleCategory category) {
        this.title = category.toString();
        for (var module : ManagerLoader.moduleManager.getModules()) {
            if (module.getCategory() == category) {
                modules.add(module);
                stateList.put(module, new ImBoolean(module.isOn()));
            }
        }

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getTitle() {
        return title;
    }

    public ConfigModuleBase onDraw(ImGuiIO io) {
        ConfigModuleBase ret = null;
        for (var module : modules) {
            ImGui.beginGroup();

            if (stateList.containsKey(module)) {
                var state = stateList.get(module);
                if (state.get() != module.isOn()) {
                    state.set(module.isOn());
                }
            }
            // 复选框表示是否启用
            if (ImGui.checkbox("##enable_checkbox_" + module.getName(), stateList.get(module))) {
                module.setState(stateList.get(module).get());
            }

            // 文本表示功能
            ImGui.sameLine(); // 强制同一行显示
            ImGui.text(module.getName());

            // 编辑按钮，负责打开属性编辑窗口
            ImGui.sameLine(); // 强制同一行显示
            if (ImGui.button("Edit##edit_button" + module.getName())) {
                ret = module;
            }

            // 结束水平布局
            ImGui.endGroup();
            ImGui.separator();
        }
        return ret;
    }

}
