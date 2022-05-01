package WaiZhong.blood_mod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "title.blood_mod.config")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class BloodModConfig implements ConfigData {

    @Comment("blood_mod:describe.blood_mod.config.display_hud")
    public boolean display_hud = true;

    @ConfigEntry.Category("blood_mod:title.blood_mod.config.advanced")
    public int hud_x = 0;
    @ConfigEntry.Category("blood_mod:title.blood_mod.config.advanced")
    public int hud_y = 0;
}
