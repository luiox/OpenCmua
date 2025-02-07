package me.cmua.module.client;

import me.cmua.Cmua;
import me.cmua.api.config.setting.*;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;
import me.cmua.module.ModuleInfo;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;

import java.awt.*;
import java.util.HashMap;

@ModuleInfo(name = "ClientSetting", description = "客户端设置", category = ModuleCategory.Client)
public class ClientSetting extends Module {
    //    public static final FadeUtils inventoryFade = new FadeUtils(500);
//    public static final Animation animation = new Animation();
    public static final HashMap<OrderedText, StringVisitable> chatMessage = new HashMap<>();
    private static volatile ClientSetting INSTANCE;
    public final EnumSetting<Page> page = addSetting("Page", Page.Game);
    public final BooleanSetting lowVersion = addSetting("1.12", false, () -> page.is(Page.Game));
    public final BooleanSetting crawl = addSetting("Crawl", true, () -> page.is(Page.Game));
    public final BooleanSetting rotations = addSetting("ShowRotations", true, () -> page.is(Page.Game));
    public final BooleanSetting titleFix = addSetting("TitleFix", true, () -> page.is(Page.Game));
    public final StringSetting windowTitle = addSetting("WindowTitle", Cmua.MOD_NAME, () -> page.is(Page.Misc));
    public final BooleanSetting titleOverride = addSetting("TitleOverride", true, () -> page.is(Page.Misc));
    public final BooleanSetting debug = addSetting("DebugException", true, () -> page.is(Page.Misc));
    public final BooleanSetting caughtException = addSetting("CaughtException", false, () -> page.is(Page.Misc));
    public final BooleanSetting log = addSetting("Log", true, () -> page.is(Page.Misc) && caughtException.getValue().getRawValue());
    public final IntSetting hotbarTime = addSetting("HotbarTime", 300, 0, 1000, 10, () -> page.is(Page.Gui));
    //    public final EnumSetting<Easing> animEase = addSetting("AnimEase", Easing.CubicInOut, () -> page.is(Page.Gui)));
    public final BooleanSetting guiBackground = addSetting("GuiBackground", true, () -> page.is(Page.Gui));
    public final ColorSetting customBackground = addSetting("CustomBackground", new Color(0, 0, 0, 36), () -> page.is(Page.Gui));
    public final ColorSetting endColor = addSetting("End", new Color(255, 0, 0, 80), () -> page.is(Page.Gui));
    public final ColorSetting customButton = addSetting("CustomButton", new Color(0, 0, 0, 100), () -> page.is(Page.Gui));
    public final ColorSetting hover = addSetting("Hover", new Color(255, 255, 255, 100), () -> page.is(Page.Gui));
    public final IntSetting speed = addSetting("Time", 100, 0, 500, 1, () -> page.is(Page.Gui));
    public final ColorSetting snow = addSetting("Snow", new Color(255, 255, 255, 70), () -> page.is(Page.Gui));
    public final StringSetting hackName = addSetting("Notification", "Cmua", () -> page.getValue() == Page.Notification);
    public final ColorSetting color = addSetting("Color", new Color(255, 38, 38), () -> page.getValue() == Page.Notification);
    public final ColorSetting pulse = addSetting("Pulse", new Color(145, 0, 0), () -> page.getValue() == Page.Notification);
    public final FloatSetting pulseSpeed = addSetting("Speed", 1f, 0f, 5f, 0.1f, () -> page.getValue() == Page.Notification);
    public final IntSetting pulseCounter = addSetting("Counter", 10, 1, 50, 1, () -> page.getValue() == Page.Notification);
    public final EnumSetting<Style> messageStyle = addSetting("Style", Style.Mio, () -> page.getValue() == Page.Notification);
    public final BooleanSetting toggle = addSetting("ModuleToggle", true, () -> page.getValue() == Page.Notification);
    public final BooleanSetting onlyOne = addSetting("OnlyOne", false, () -> page.getValue() == Page.Notification);
    public final BooleanSetting keepHistory = addSetting("KeepHistory", true, () -> page.getValue() == Page.ChatHud);
    public final BooleanSetting infiniteChat = addSetting("InfiniteChat", true, () -> page.getValue() == Page.ChatHud);
    public final IntSetting animateTime = addSetting("AnimTime", 300, 0, 1000, 10, () -> page.getValue() == Page.ChatHud);
    public final IntSetting animateOffset = addSetting("AnimOffset", -40, -200, 100, 10, () -> page.getValue() == Page.ChatHud);
    //    public final EnumSetting<Easing> ease = addSetting("Ease", Easing.CubicInOut, () -> page.getValue() == Page.ChatHud));
    public final BooleanSetting fade = addSetting("Fade", true, () -> page.getValue() == Page.ChatHud);
    public final BooleanSetting yAnim = addSetting("YAnim", false, () -> page.getValue() == Page.ChatHud);
    public final IntSetting fadeTime = addSetting("FadeTime", 300, 0, 1000, 10, () -> page.getValue() == Page.ChatHud);
    public final BooleanSetting inputBoxAnim = addSetting("InputBoxAnim", true, () -> page.getValue() == Page.ChatHud);
    public final BooleanSetting hideIndicator = addSetting("HideIndicator", true, () -> page.getValue() == Page.ChatHud);
    // Colors
    public final ColorSetting clientColor = addSetting("ClientColor", new Color(92, 240, 246));
    private final BooleanSetting portalGui = addSetting("PortalGui", true, () -> page.is(Page.Game));
    private final BooleanSetting inventoryAnim = addSetting("InventoryAnim", true, () -> page.is(Page.Gui));
    private final IntSetting inventoryTime = addSetting("InvTime", 300, 0, 1000, 10, () -> page.is(Page.Gui));
    private final BooleanSetting hotbar = addSetting("HotbarAnim", true, () -> page.is(Page.Gui));


    public static ClientSetting getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientSetting.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientSetting();
                }
            }
        }
        return INSTANCE;
    }

//    @Override
//    public void onUpdate() {
//        inventoryFade.setLength(inventoryTime.getValueInt());
//        if (mc.currentScreen == null && inventoryAnim.getValue()) {
//            inventoryFade.reset();
//        }
//    }
//
//    public boolean portalGui() {
//        return isOn() && portalGui.getValue();
//    }
//
//    public boolean hotbar() {
//        return isOn() && hotbar.getValue();
//    }

    @Override
    public void enable() {
        this.state = true;
    }

    @Override
    public void disable() {
        this.state = true;
    }

    @Override
    public boolean isOn() {
        return true;
    }

    public enum Page {
        Game,
        Gui,
        Misc,
        Notification,
        ChatHud
    }

    public enum Style {
        Mio,
        Debug,
        Lowercase,
        Normal,
        Future,
        Earth,
        Moon,
        Melon,
        Chinese,
        None
    }
}