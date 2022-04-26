package com.github.blood_mod;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

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
        return Ingredient.ofItems(Main.BLOOD_INGOT);
    }
}
