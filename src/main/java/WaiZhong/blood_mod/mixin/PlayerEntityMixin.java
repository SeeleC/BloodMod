package WaiZhong.blood_mod.mixin;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.spongepowered.asm.mixin.injection.At.Shift;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ManaManagerAccess {
	private ManaManager manaManager = new ManaManager();

	@Override
	public ManaManager getManaManager(PlayerEntity player) {
		return this.manaManager;
	}

	public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "readCustomDataFromNbt", at = @At(value = "TAIL"))
	private void readCustomDataFromTagMixin(NbtCompound tag, CallbackInfo info) {
		this.manaManager.readNbt(tag);
	}

	@Inject(method = "writeCustomDataToNbt", at = @At(value = "TAIL"))
	private void writeCustomDataToTagMixin(NbtCompound tag, CallbackInfo info) {
		this.manaManager.writeNbt(tag);
	}
}