package WaiZhong.blood_mod.access;

import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.entity.player.PlayerEntity;

public interface ManaManagerAccess {

    ManaManager getManaManager(PlayerEntity player);

}
