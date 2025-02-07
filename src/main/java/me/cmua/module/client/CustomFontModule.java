package me.cmua.module.client;

import me.cmua.api.config.setting.FloatSetting;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;
import me.cmua.module.ModuleInfo;

@ModuleInfo(name = "Font", description = "字体设置", category = ModuleCategory.Client)
public class CustomFontModule extends Module {
    private static CustomFontModule INSTANCE;
    //    public final StringSetting fontName = addSetting("FontName", FontManager.defaultClientFontName);
    public final FloatSetting size = addSetting("Size", 30f, 1, 40, 1);
    public final FloatSetting yOffset = addSetting("Offset", 0, -5, 15, 0.1f);

    public static CustomFontModule getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomFontModule();
        }
        return INSTANCE;
    }

    @Override
    public void onEnable() {
//        try {
//            FontManager.loadNewFont(fontName.getValue().getValue().get(), Font.PLAIN, size.getValue().getValue().get());
//        } catch (IOException | FontFormatException e) {
//            ChatUtil.sendChatMessage("加载字体失败");
//            e.printStackTrace();
//        }
    }
}
