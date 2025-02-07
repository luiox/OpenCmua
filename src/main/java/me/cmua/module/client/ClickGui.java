package me.cmua.module.client;

import me.cmua.gui.clickgui.ClickGuiScreen;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;
import me.cmua.module.ModuleInfo;
import me.cmua.utils.MinecraftUtil;

@ModuleInfo(name = "ClickGui", category = ModuleCategory.Client, description = "打开点击gui")
public class ClickGui extends Module {
    //    public static final FadeUtils fade = new FadeUtils(300);
    private static ClickGui INSTANCE;


//    private final EnumSetting<Pages> page = add(new EnumSetting<>("Page", Pages.General));
//    public final EnumSetting<Type> uiType = add(new EnumSetting<>("UIType", Type.Old, () -> page.getValue() == Pages.Element));
//    public final BooleanSetting activeBox = add(new BooleanSetting("ActiveBox", true, () -> page.getValue() == Pages.Element));
//    public final BooleanSetting center = add(new BooleanSetting("Center", false, () -> page.getValue() == Pages.Element));
//    public final ColorSetting bind = add(new ColorSetting("Bind", new Color(255, 255, 255), () -> page.getValue() == Pages.Element).injectBoolean(false));
//    public final ColorSetting gear = add(new ColorSetting("Gear", new Color(255, 255, 255), () -> page.getValue() == Pages.Element).injectBoolean(true));
//    public final BooleanSetting chinese = add(new BooleanSetting("Chinese", false, () -> page.getValue() == Pages.General));
//    public final BooleanSetting font = add(new BooleanSetting("Font", true, () -> page.getValue() == Pages.General));
//    public final BooleanSetting maxFill = add(new BooleanSetting("MaxFill", false, () -> page.getValue() == Pages.General));
//    public final BooleanSetting sound = add(new BooleanSetting("Sound", true, () -> page.getValue() == Pages.General));
//    public final SliderSetting height = add(new SliderSetting("Height", 15, 10, 20, 1, () -> page.getValue() == Pages.General));
//    public final EnumSetting<Mode> mode = add(new EnumSetting<>("EnableAnim", Mode.Pull, () -> page.getValue() == Pages.General));
//    public final SliderSetting animationTime = add(new SliderSetting("AnimationTime", 200, 0, 1000, 1, () -> page.getValue() == Pages.General));
//    public final EnumSetting<Easing> ease = add(new EnumSetting<>("Ease", Easing.QuadInOut, () -> page.getValue() == Pages.General));
//    public final ColorSetting color = add(new ColorSetting("Main", 1297678336, () -> page.getValue() == Pages.Color));
//    public final ColorSetting mainEnd = add(new ColorSetting("MainEnd", -2113929216, () -> page.getValue() == Pages.Color).injectBoolean(false));
//    public final ColorSetting mainHover = add(new ColorSetting("Hover", 2086600704, () -> page.getValue() == Pages.Color));
//    public final ColorSetting bar = add(new ColorSetting("Bar", 1781465088, () -> page.getValue() == Pages.Color));
//    public final ColorSetting barEnd = add(new ColorSetting("BarEnd", -2113929216, () -> page.getValue() == Pages.Color).injectBoolean(false));
//    public final ColorSetting disableText = add(new ColorSetting("DisableText", new Color(255, 255, 255), () -> page.getValue() == Pages.Color));
//    public final ColorSetting enableText = add(new ColorSetting("EnableText", -1, () -> page.getValue() == Pages.Color));
//    public final ColorSetting enableTextS = add(new ColorSetting("EnableText2", -2424832, () -> page.getValue() == Pages.Color));
//    public final ColorSetting module = add(new ColorSetting("OldModule", 4144959, () -> page.getValue() == Pages.Color));
//    public final ColorSetting moduleHover = add(new ColorSetting("ModuleHover", 693703957, () -> page.getValue() == Pages.Color));
//    public final ColorSetting setting = add(new ColorSetting("Setting", 4144959, () -> page.getValue() == Pages.Color));
//    public final ColorSetting settingHover = add(new ColorSetting("SettingHover", 693703957, () -> page.getValue() == Pages.Color));
//    public final ColorSetting background = add(new ColorSetting("Background", 1881014272, () -> page.getValue() == Pages.Color));
//    int lastHeight;

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    @Override
    public void onUpdate() {
        if (!(mc.currentScreen instanceof ClickGuiScreen)) {
            disable();
        }
    }

    @Override
    public void onEnable() {
        if (MinecraftUtil.playerOrWorldIsNull()) {
            disable();
            return;
        }
        mc.setScreen(ClickGuiScreen.getInstance());
    }

    @Override
    public void onDisable() {
        if (mc.currentScreen instanceof ClickGuiScreen) {
            mc.setScreen(null);
        }
    }

    public enum Mode {
        Scale, Pull, None
    }

    private enum Pages {
        General,
        Color,
        Element
    }

    public enum Type {
        Old,
        New
    }
}