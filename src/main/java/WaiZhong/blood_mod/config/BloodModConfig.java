package WaiZhong.blood_mod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "blood_mod")
@Config.Gui.Background("minecraft:textures/gui/options_background.png")
public class BloodModConfig implements ConfigData {
    // @Comment("display_hud.describe")
    @ConfigEntry.Category("general")
    public boolean display_hud = true;

    @ConfigEntry.Category("advanced")
    public int hud_x = 0;
    @ConfigEntry.Category("advanced")
    public int hud_y = 0;
}
