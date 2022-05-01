package WaiZhong.blood_mod.mixin.client;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.config.BloodModConfig;
import WaiZhong.blood_mod.init.ConfigInit;
import WaiZhong.blood_mod.mana.ManaManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    @Shadow
    @Final
    @Mutable
    private final MinecraftClient client;
    @Shadow
    private int ticks;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    private static final Identifier THIRST_ICON = new Identifier("blood_mod:textures/gui/hud.png");

    public InGameHudMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 1))
    private void renderStatusBarsMixin(MatrixStack matrices, CallbackInfo info) {
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null && !playerEntity.isInvulnerable() && ConfigInit.CONFIG.display_hud) {
            ManaManager manaManager = ((ManaManagerAccess) playerEntity).getManaManager(playerEntity);
            if (manaManager.hasMana()) {
                int thirst = manaManager.getManaLevel();
                LivingEntity livingEntity = this.getRiddenEntity();
                int variable_one;
                int variable_two;
                int variable_three;
                int height = this.scaledHeight - 49;
                int width = this.scaledWidth / 2 + 91;
                if (this.getHeartCount(livingEntity) == 0) {
                    for (variable_one = 0; variable_one < 10; ++variable_one) {
                        variable_three = height;
                        if (this.ticks % (thirst * 8 + 3) == 0) {
                            assert client.world != null;
                            variable_three = height + (client.world.random.nextInt(3) - 1); // bouncy
                        }
                        int uppderCoord = 9;
                        int beneathCoord = 0;
                        variable_two = width - variable_one * 8 - 9;
                        variable_two = variable_two + ConfigInit.CONFIG.hud_x;
                        variable_three = variable_three + ConfigInit.CONFIG.hud_y;
                        RenderSystem.setShaderTexture(0, THIRST_ICON);
                        this.drawTexture(matrices, variable_two, variable_three, 0, 0, 9, 9);
                        if (variable_one * 2 + 1 < thirst) {
                            this.drawTexture(matrices, variable_two, variable_three, beneathCoord, uppderCoord, 9, 9); // Big icon
                        }
                        if (variable_one * 2 + 1 == thirst) {
                            this.drawTexture(matrices, variable_two, variable_three, beneathCoord + 9, uppderCoord, 9, 9); // Small icon
                        }
                    }
                    RenderSystem.setShaderTexture(0, GUI_ICONS_TEXTURE);
                }
            }
        }
    }

    @Inject(method = "getHeartRows", at = @At(value = "HEAD"), cancellable = true)
    private void getHeartRowsMixin(int heartCount, CallbackInfoReturnable<Integer> info) {
        info.setReturnValue((int) Math.ceil((double) heartCount / 10.0D) + 1);
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }

    @Shadow
    private LivingEntity getRiddenEntity() {
        return null;
    }

    @Shadow
    private int getHeartCount(LivingEntity entity) {
        return 0;
    }
}