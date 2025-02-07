package me.cmua.gui.clickgui;

import imgui.ImGui;
import me.cmua.api.imgui.virtualui.SettingWindow;
import me.cmua.gui.imgui.ImguiLoader;
import me.cmua.module.ModuleCategory;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * 仅做参数转发
 */
public class ClickGuiScreen extends Screen {

    private static ClickGuiScreen instance;
    private Map<ModuleCategory, CategoryTab> categoryTabs = new HashMap<>();
    private float[] scale = new float[1];
    private SettingWindow settingWindow;

    public ClickGuiScreen() {
        super(Text.of("CmuaClickGui"));
        scale[0] = 1;
        for (ModuleCategory category : ModuleCategory.values()) {
            categoryTabs.put(category, new CategoryTab(category));
        }
        settingWindow = new SettingWindow();
    }

    public static ClickGuiScreen getInstance() {
        if (instance == null) {
            instance = new ClickGuiScreen();
        }
        return instance;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTicks) {

        ImguiLoader.draw(io -> {
            ImGui.begin("Cmua MainWindow");
            {
                ImGui.beginGroup(); // 开始新组
                {
                    var pos = ImGui.getWindowPos();
                    ImGui.text("Window Position: (" + pos.x + ", " + pos.y + ")");
                    var windowSize = ImGui.getWindowSize(); // 获取当前窗口的大小
                    // 使用 windowSize.x 和 windowSize.y 来获取宽度和高度
                    ImGui.text("Window Size: (" + windowSize.x + ", " + windowSize.y);

                    ImGui.pushItemWidth(pos.x);
                    for (var category : ModuleCategory.values()) {
                        if (ImGui.button(category.toString())) {
                            categoryTabs.get(category).setVisible(!categoryTabs.get(category).isVisible());
                            ImGui.separator();
                        }
                    }
                    ImGui.sliderFloat("scale", scale, 0.5f, 2f, "%.1f");
                }
                ImGui.endGroup();
            }
            ImGui.end();


            for (var tab : categoryTabs.values()) {
                if (tab.isVisible()) {
                    ImGui.begin(tab.getTitle());
                    var ret = tab.onDraw(io);
                    if (ret != null) {
                        settingWindow.setModule(ret);
                    }
                    ImGui.end();
                }
            }

            if (settingWindow.getModule() != null) {
                String name = settingWindow.getModule().getName();
                if (name == "") {
                    name = "##";
                }
                ImGui.begin(name);
                SettingWindow.draw(io);
                ImGui.end();
            }
        });
    }

}
