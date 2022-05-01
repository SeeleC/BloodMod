package WaiZhong.blood_mod.network;

import io.netty.buffer.Unpooled;
import WaiZhong.blood_mod.access.ManaManagerAccess;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ManaServerPacket {
    public static final Identifier MANA_UPDATE = new Identifier("blood_mod", "mana_update");
    public static final Identifier EXCLUDED_SYNC = new Identifier("blood_mod", "excluded_player_sync");

    public static void init() {
    }

    public static void writeS2CExcludedSyncPacket(ServerPlayerEntity serverPlayerEntity, boolean setThirst) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(setThirst);
        serverPlayerEntity.networkHandler.sendPacket(new CustomPayloadS2CPacket(EXCLUDED_SYNC, buf));
    }

    public static void writeS2CManaUpdatePacket(ServerPlayerEntity serverPlayerEntity) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeIntArray(new int[] { serverPlayerEntity.getId(), ((ManaManagerAccess) serverPlayerEntity).getManaManager(serverPlayerEntity).getManaLevel() });
        serverPlayerEntity.networkHandler.sendPacket(new CustomPayloadS2CPacket(ManaServerPacket.MANA_UPDATE, buf));
    }
}
