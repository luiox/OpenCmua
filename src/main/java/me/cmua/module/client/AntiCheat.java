package me.cmua.module.client;

import me.cmua.api.config.setting.BooleanSetting;
import me.cmua.api.config.setting.EnumSetting;
import me.cmua.api.config.setting.FloatSetting;
import me.cmua.module.Module;
import me.cmua.module.ModuleCategory;
import me.cmua.module.ModuleInfo;
import me.cmua.utils.entity.Placement;
import me.cmua.utils.player.SwingSide;

@ModuleInfo(name = "AntiCheat", description = "反作弊选项", category = ModuleCategory.Client)
public class AntiCheat extends Module {
    private static AntiCheat INSTANCE;
    public final EnumSetting<Page> page = addSetting("Page", Page.General);
    public final BooleanSetting multiPlace = addSetting("MultiPlace", true, () -> page.is(Page.General));
    public final BooleanSetting packetPlace = addSetting("PacketPlace", true, () -> page.is(Page.General));
    public final BooleanSetting attackRotate = addSetting("AttackRotation", false, () -> page.is(Page.General));
    public final BooleanSetting invSwapBypass = addSetting("PickSwap", false, () -> page.is(Page.General));
    public final FloatSetting boxSize = addSetting("HitBoxSize", 0.6f, 0f, 1f, 0.01f, () -> page.is(Page.General));
    public final FloatSetting attackDelay = addSetting("BreakDelay", 0.2f, 0f, 1f, 0.01f, () -> page.is(Page.General));
    public final BooleanSetting noBadSlot = addSetting("NoBadSlot", false, () -> page.is(Page.General));
    public final EnumSetting<Placement> placement = addSetting("Placement", Placement.Vanilla, () -> page.is(Page.General));
    public final BooleanSetting blockCheck = addSetting("BlockCheck", true, () -> page.is(Page.General));
    public final BooleanSetting oldNCP = addSetting("OldNCP", false, () -> page.is(Page.General));
    public final BooleanSetting grimRotation = addSetting("GrimRotation", false, () -> page.is(Page.Rotation));
    public final BooleanSetting snapBack = addSetting("SnapBack", false, () -> page.is(Page.Rotation));
    public final BooleanSetting look = addSetting("Look", true, () -> page.is(Page.Rotation));
    public final FloatSetting rotateTime = addSetting("LookTime", 0.5f, 0f, 1f, 0.01f, () -> page.is(Page.Rotation));
    public final EnumSetting<SwingSide> swingMode = addSetting("SwingType", SwingSide.All);
    public final BooleanSetting noSpamRotation = addSetting("SpamCheck", true, () -> page.is(Page.Rotation));
    public final FloatSetting fov = addSetting("Fov", 10, 0, 180, 0.1f, () -> page.is(Page.Rotation) && noSpamRotation.getValue().getRawValue());
    public final FloatSetting steps = addSetting("Steps", 0.6f, 0f, 1f, 0.0f, () -> page.is(Page.Rotation));
    public final BooleanSetting forceSync = addSetting("ServerSide", false, () -> page.is(Page.Rotation));
    public final BooleanSetting obsMode = addSetting("OBSServer", false, () -> page.is(Page.Misc));
    public final BooleanSetting inventorySync = addSetting("InventorySync", false, () -> page.is(Page.Misc));

    public static AntiCheat getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AntiCheat();
        }
        return INSTANCE;
    }

    public static double getOffset() {
        if (INSTANCE != null) return INSTANCE.boxSize.getValue().getValue().get() / 2;
        return 0.3;
    }

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

    private enum Page {
        General,
        Rotation,
        Misc
    }
}
