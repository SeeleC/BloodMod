package WaiZhong.blood_mod.init;

import WaiZhong.blood_mod.config.BloodModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ConfigInit {
    public static BloodModConfig CONFIG = new BloodModConfig();

    public static void init() {
        AutoConfig.register(BloodModConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(BloodModConfig.class).getConfig();
    }

}
