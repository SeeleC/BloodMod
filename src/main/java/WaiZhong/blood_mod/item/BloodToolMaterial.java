package WaiZhong.blood_mod.item;

import WaiZhong.blood_mod.BloodModMain;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import static WaiZhong.blood_mod.init.ItemInit.BLOOD_INGOT;

public class BloodToolMaterial implements ToolMaterial {
    public  static  final BloodToolMaterial INSTANCE =  new BloodToolMaterial() ;

    @Override
    public int getDurability() {
        return 2500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 14.0f;
    }

    @Override
    public float getAttackDamage() {
        return 6.0f;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 35;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(BLOOD_INGOT);
    }
}
