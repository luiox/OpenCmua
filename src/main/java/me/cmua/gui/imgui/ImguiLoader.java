package me.cmua.gui.imgui;

import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.extension.implot.ImPlot;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import me.cmua.Cmua;
import me.cmua.client.FontManager;

import java.io.File;

public class ImguiLoader {
    private final static ImGuiImplGlfw imGuiImplGlfw = new ImGuiImplGlfw();
    private final static ImGuiImplGl3 imGuiImplGl3 = new ImGuiImplGl3();
    private static String configPath = Cmua.MOD_ID + File.separator + "ui.ini";

    public static void create(final long handle) {
        ImGui.createContext();
        ImPlot.createContext();

        final ImGuiIO data = ImGui.getIO();
        data.setIniFilename(configPath);
        data.setFontGlobalScale(1F);

        data.setConfigFlags(ImGuiConfigFlags.DockingEnable);

        imGuiImplGlfw.init(handle, true);
        imGuiImplGl3.init();

        ImFontConfig fontConfig = new ImFontConfig();
        fontConfig.setFontDataOwnedByAtlas(false);
        fontConfig.setMergeMode(false);
        fontConfig.setSizePixels(30);

        ImGui.getIO().getFonts().addFontFromMemoryTTF(FontManager.getFont(), 30f, fontConfig);
    }

    public static void draw(final RenderInterface runnable) {
        imGuiImplGl3.newFrame();
        imGuiImplGlfw.newFrame();
        ImGui.newFrame();

        runnable.render(ImGui.getIO());

        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());
    }

    public static void dispose() {
        imGuiImplGl3.shutdown();

        ImGui.destroyContext();
        ImPlot.destroyContext();
    }

    public static void saveImGuiConfig() {
        ImGui.saveIniSettingsToDisk(configPath);
    }
}
