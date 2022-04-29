package WaiZhong.blood_mod.init;

import WaiZhong.blood_mod.item.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;

public class ItemInit {
    public static final Item DEFENSE_GEM = new Gem(
            new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .maxCount(1)
                    .food(
                            new FoodComponent.Builder()
                                    .alwaysEdible()
                                    .build()
                    ),
            (short) 8,
            Arrays.asList(new StatusEffectInstance(StatusEffects.RESISTANCE, 1800, 2, true, false), new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3600, 0, true, false)),
            Arrays.asList((TranslatableText) new TranslatableText("item.blood_mod.defense_gem.tooltip1").formatted(Formatting.BLUE), (TranslatableText) new TranslatableText("item.blood_mod.defense_gem.tooltip2").formatted(Formatting.BLUE))
    );
    public static final Item BATTLE_GEM = new Gem(
            new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .maxCount(1)
                    .food(
                            new FoodComponent.Builder()
                                    .alwaysEdible()
                                    .build()
                    ),
            (short) 12,
            Arrays.asList(new StatusEffectInstance(StatusEffects.STRENGTH, 900, 2, true, false), new StatusEffectInstance(StatusEffects.SPEED, 1200, 0, true, false)),
            Arrays.asList((TranslatableText) new TranslatableText("item.blood_mod.battle_gem.tooltip1").formatted(Formatting.BLUE), (TranslatableText) new TranslatableText("item.blood_mod.battle_gem.tooltip2").formatted(Formatting.BLUE), new TranslatableText("newline"), (TranslatableText) new TranslatableText("item.blood_mod.battle_gem.tooltip3").formatted(Formatting.DARK_PURPLE), (TranslatableText) new TranslatableText("item.blood_mod.battle_gem.tooltip4").formatted(Formatting.BLUE), (TranslatableText) new TranslatableText("item.blood_mod.battle_gem.tooltip5").formatted(Formatting.BLUE))
    );
    public static final Item BLOOD_SWORD = new BloodSword(BloodToolMaterial.INSTANCE, 4, -2.2f, new Item.Settings()
            .fireproof()
            .rarity(Rarity.EPIC)
    );
    public static final Item BLOOD_PICKAXE = new BloodPickaxe(BloodToolMaterial.INSTANCE, 0, -2.6f, new Item.Settings()
            .fireproof()
            .rarity(Rarity.EPIC)
            .maxDamage(2750)
    );
    public static final Item BLOOD_AXE = new BloodAxe(BloodToolMaterial.INSTANCE, 7, -3, new Item.Settings()
            .fireproof()
            .rarity(Rarity.EPIC)
            .maxDamage(2690)
    );
    public static final Item BLOOD_CORE = new BloodCore(new Item.Settings()
            .rarity(Rarity.RARE)
            .fireproof()
    );
    public static final Item BLOOD_DEBRIS = new BloodDebris(new Item.Settings()
            .fireproof()
    );
    public static final Item BLOOD_INGOT = new BloodIngot(new Item.Settings()
            .rarity(Rarity.UNCOMMON)
            .fireproof()
    );
    public static final Item BLOOD_BOTTLE = new BloodBottle(new Item.Settings()
            .food(
                    new FoodComponent.Builder()
                            .alwaysEdible()
                            .build()
            )
            .maxCount(1)
            .recipeRemainder(Items.GLASS_BOTTLE)
            .rarity(Rarity.UNCOMMON)
            .fireproof()
    );

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_core"), BLOOD_CORE);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_debris"), BLOOD_DEBRIS);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_bottle"), BLOOD_BOTTLE);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_ingot"), BLOOD_INGOT);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_sword"), BLOOD_SWORD);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_pickaxe"), BLOOD_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_axe"), BLOOD_AXE);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "defense_gem"), DEFENSE_GEM);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "battle_gem"), BATTLE_GEM);
    }
}
