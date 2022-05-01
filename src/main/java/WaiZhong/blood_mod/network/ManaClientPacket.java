package WaiZhong.blood_mod.network;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;

public class ManaClientPacket {
    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ManaServerPacket.MANA_UPDATE, (client, handler, buffer, responseSender) -> {
            int[] bufferArray = buffer.readIntArray();
            int entityId = bufferArray[0];
            int thirstLevel = bufferArray[1];
            client.execute(() -> {
                assert client.player != null;
                if (client.player.world.getEntityById(entityId) != null) {
                    PlayerEntity player = (PlayerEntity) client.player.world.getEntityById(entityId);
                    assert player != null;
                    ManaManager thirstManager = ((ManaManagerAccess) player).getManaManager(player);
                    thirstManager.setManaLevel(thirstLevel);
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(ManaServerPacket.EXCLUDED_SYNC, (client, handler, buffer, responseSender) -> {
            boolean setThirst = buffer.readBoolean();
            client.execute(() -> {
                assert client.player != null;
                ((ManaManagerAccess) client.player).getManaManager(client.player).setMana(setThirst);
            });
        });
    }
}
