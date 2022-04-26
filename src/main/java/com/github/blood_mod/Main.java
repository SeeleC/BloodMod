package com.github.blood_mod;

import com.github.blood_mod.Tools.BloodAxe;
import com.github.blood_mod.Tools.BloodPickaxe;
import com.github.blood_mod.Tools.BloodSword;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

public class Main implements ModInitializer {
	public static final Item BLOOD_SWORD = new BloodSword(BloodToolMaterial.INSTANCE, 4, -2.2f, new Item.Settings()
			.fireproof()
			.rarity(Rarity.EPIC)
	);
	public static final Item BLOOD_PICKAXE = new BloodPickaxe(BloodToolMaterial.INSTANCE, 0, -2.6f, new Item.Settings()
			.fireproof()
			.rarity(Rarity.EPIC)
			.maxDamage(2750)
	);
	public static final Item BLOOD_AXE = new BloodAxe(BloodToolMaterial.INSTANCE, 6, -3, new Item.Settings()
			.fireproof()
			.rarity(Rarity.RARE)
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
	public static final Block BLOOD_ORE = new BloodOre(FabricBlockSettings
			.of(Material.STONE)
			.hardness(1.3f)
			.requiresTool()
			.nonOpaque()
	);


	public static final ItemGroup GROUP = FabricItemGroupBuilder.create(
			new Identifier("blood_mod", "group")
	).icon(() -> new ItemStack(BLOOD_DEBRIS))
			.appendItems(itemStacks -> {
				itemStacks.add(new ItemStack(BLOOD_CORE));
				itemStacks.add(new ItemStack(BLOOD_DEBRIS));
				itemStacks.add(new ItemStack(BLOOD_BOTTLE));
				itemStacks.add(new ItemStack(BLOOD_INGOT));
				itemStacks.add(new ItemStack(BLOOD_ORE));
				itemStacks.add(new ItemStack(BLOOD_SWORD));
				itemStacks.add(new ItemStack(BLOOD_PICKAXE));
				itemStacks.add(new ItemStack(BLOOD_AXE));
			})
			.build();

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_core"), BLOOD_CORE);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_debris"), BLOOD_DEBRIS);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_bottle"), BLOOD_BOTTLE);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_ingot"), BLOOD_INGOT);
		Registry.register(Registry.BLOCK, new Identifier("blood_mod", "blood_ore"), BLOOD_ORE);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_ore"), new BlockItem(BLOOD_ORE, new Item.Settings()));
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_sword"), BLOOD_SWORD);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_pickaxe"), BLOOD_PICKAXE);
		Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_axe"), BLOOD_AXE);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier("blood_mod", "blood_ore"), OVERWORLD_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("blood_mod", "blood_ore"),
				OVERWORLD_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier("blood_mod", "blood_ore")));
	}

	private static final ConfiguredFeature<?, ?> OVERWORLD_ORE_CONFIGURED_FEATURE = new ConfiguredFeature
			(Feature.ORE, new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
					BLOOD_ORE.getDefaultState(),
					15)); // vein size

	public static PlacedFeature OVERWORLD_ORE_PLACED_FEATURE = new PlacedFeature(
			RegistryEntry.of(OVERWORLD_ORE_CONFIGURED_FEATURE),
			Arrays.asList(
					CountPlacementModifier.of(5), // number of veins per chunk
					SquarePlacementModifier.of(), // spreading horizontally
					HeightRangePlacementModifier.uniform(YOffset.fixed(45), YOffset.fixed(55))
			)); // height
}
