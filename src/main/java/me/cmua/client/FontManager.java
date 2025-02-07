package me.cmua.client;

import me.cmua.utils.font.FontAdapter;
import me.cmua.utils.font.RendererFontAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class FontManager {
    public static final String defaultMinecraftFontPath = "assets/minecraft/font/";
    public static final String defaultClientFontPath = "assets/cmua/font/";
    public static final String defaultClientFontName = "LexendDeca-Regular";
    public static FontAdapter ui;
    public static FontAdapter Calibri;

    private static byte[] fonts = null;

    public static byte[] getFont() {
        if (fonts == null) {
            // 从类路径加载字体资源

            try {
                var fontInputStream = FontManager.class.getClassLoader()
                        .getResourceAsStream(FontManager.defaultClientFontPath + defaultClientFontName + ".ttf");
                // 读取字体数据到字节数组
                fonts = fontInputStream.readAllBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fonts;
    }

    public static void loadNewFont(String name, int style, float size) throws IOException, FontFormatException {
        ui = new RendererFontAdapter(Font.createFont(Font.TRUETYPE_FONT,
                        Objects.requireNonNull(FontManager.class.getClassLoader()
                                .getResourceAsStream(FontManager.defaultClientFontPath + name + ".ttf")))
                .deriveFont(style, size), size);
    }

    public static @NotNull RendererFontAdapter createDefault(String name, float size)
            throws IOException, FontFormatException {
        return new RendererFontAdapter(Font.createFont(Font.TRUETYPE_FONT,
                        Objects.requireNonNull(FontManager.class.getClassLoader()
                                .getResourceAsStream(defaultClientFontPath + name + ".ttf")))
                .deriveFont(Font.PLAIN, size), size);
    }

    public static void createDefault(float size) {

        try {
            ui = createDefault(defaultClientFontName, size);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static RendererFontAdapter createFont(String name, int style, float size) {
        return new RendererFontAdapter(new Font(name, style, (int) size), size);
    }

    public static void initFont() {
        try {
            createDefault(30f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
