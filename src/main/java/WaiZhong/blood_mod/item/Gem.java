package WaiZhong.blood_mod.item;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.*;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gem extends Item {
    public List<StatusEffectInstance> effects;
    public short cost;
    public List<TranslatableText> tooltips;

    public Gem(Settings settings, short cost, List<StatusEffectInstance> effects, List<TranslatableText> tooltips) {
        super(settings);
        this.effects = effects;
        this.cost = cost;
        this.tooltips  = tooltips;
    }

    @Nullable
    @Override
    public FoodComponent getFoodComponent() {
        return new FoodComponent.Builder().alwaysEdible().build();
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player) {
        PlayerEntity playerEntity = player instanceof PlayerEntity ? (PlayerEntity) player : null;

        assert playerEntity != null;
        if (!playerEntity.isCreative()) {
            ManaManager manaManager = ((ManaManagerAccess) playerEntity).getManaManager(playerEntity);
            if (manaManager.getManaLevel() >= this.cost) {
                manaManager.add(-this.cost);

                for (StatusEffectInstance effect : this.effects) {
                    player.addStatusEffect(effect);
                }
            }
        } else if (playerEntity.isCreative()) {
            for (StatusEffectInstance effect : this.effects) {
                player.addStatusEffect(effect);
            }
        }

        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.addAll(this.tooltips);
    }
}
