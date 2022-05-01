package WaiZhong.blood_mod;

import WaiZhong.blood_mod.network.ManaClientPacket;
import net.fabricmc.api.ClientModInitializer;

public class BloodModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ManaClientPacket.init();
    }

}