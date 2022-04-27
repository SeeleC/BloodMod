package WaiZhong.blood_mod.mixin;

import com.mojang.authlib.GameProfile;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.spongepowered.asm.mixin.injection.At;

import WaiZhong.blood_mod.access.ServerPlayerAccess;
import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.network.ManaServerPacket;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements ServerPlayerAccess {
    private ManaManager manaManager = ((ManaManagerAccess) this).getManaManager(this);
    private int syncedManaLevel = -99999999;
    public int compatSync = 0;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "playerTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;tick()V", shift = Shift.AFTER))
    public void playerTickMixin(CallbackInfo info) {
        if (this.syncedManaLevel != this.manaManager.getManaLevel() && this.manaManager.hasMana()) {
            ManaServerPacket.writeS2CManaUpdatePacket((ServerPlayerEntity) (Object) this);
            this.syncedManaLevel = manaManager.getManaLevel();
        }
        if (compatSync > 0) {
            compatSync--;
            if (compatSync == 1)
                ManaServerPacket.writeS2CExcludedSyncPacket((ServerPlayerEntity) (Object) this, manaManager.hasMana());
        }
    }

    @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setHealth(F)V"))
    public void copyFromMixinOne(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        this.manaManager.setManaLevel(((ManaManagerAccess) oldPlayer).getManaManager(oldPlayer).getManaLevel());
    }

    @Inject(method = "Lnet/minecraft/server/network/ServerPlayerEntity;copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At(value = "TAIL"))
    public void copyFromMixinTwo(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        this.syncedManaLevel = -1;
    }

    @Inject(method = "teleport", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setWorld(Lnet/minecraft/server/world/ServerWorld;)V"))
    void teleportFix(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch, CallbackInfo info) {
        this.syncedManaLevel = -1;
    }

    @Nullable
    @Inject(method = "moveToWorld", at = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerPlayerEntity;syncedFoodLevel:I", ordinal = 0))
    private void moveToWorldMixin(ServerWorld destination, CallbackInfoReturnable<Entity> info) {
        this.syncedManaLevel = -1;
    }

    @Override
    public void compatSync() {
        compatSync = 5;
    }
}
