package WaiZhong.blood_mod.item;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AncientTear extends Item {
    public AncientTear(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;

        assert playerEntity != null;
        ManaManager manaManager = ((ManaManagerAccess) playerEntity).getManaManager(playerEntity);

        manaManager.add(4);
        if (playerEntity.isCreative()) {
            return stack;
        }
        return new ItemStack(Items.GLASS_BOTTLE);
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
    public int getMaxUseTime(ItemStack stack) {
        return 8;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.blood_mod.ancient_tear.tooltip").formatted(Formatting.GRAY));
    }
}
