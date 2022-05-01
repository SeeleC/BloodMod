package WaiZhong.blood_mod.item;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.util.UseAction.DRINK;

public class BloodBottle extends Item {
    public BloodBottle(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
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
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 4), user);

        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;

        if (playerEntity == null || !playerEntity.isCreative()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        return stack;
    }
}
