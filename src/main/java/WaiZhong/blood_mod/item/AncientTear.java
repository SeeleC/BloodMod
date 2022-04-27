package WaiZhong.blood_mod.item;

import WaiZhong.blood_mod.access.ManaManagerAccess;
import WaiZhong.blood_mod.mana.ManaManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class AncientTear extends Item {
    public AncientTear(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player) {
        PlayerEntity playerEntity = player instanceof PlayerEntity ? (PlayerEntity) player : null;

        assert playerEntity != null;
        if (!stack.hasNbt() & !playerEntity.isCreative()) {
            ManaManager manaManager = ((ManaManagerAccess) playerEntity).getManaManager(playerEntity);
            manaManager.add(2);
            //manaManager.setManaLevel(0);
            System.out.println(manaManager.getManaLevel());
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        return stack;
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
