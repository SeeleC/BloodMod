package WaiZhong.blood_mod;

import WaiZhong.blood_mod.init.BlockInit;
import WaiZhong.blood_mod.init.ItemInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
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

import static WaiZhong.blood_mod.init.BlockInit.BLOOD_ORE;
import static WaiZhong.blood_mod.init.ItemInit.*;

public class BloodModMain implements ModInitializer {
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
				itemStacks.add(new ItemStack(DEFENSE_GEM));
				itemStacks.add(new ItemStack(BATTLE_GEM));
			})
			.build();

	@Override
	public void onInitialize() {
		BlockInit.init();
		ItemInit.init();

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
