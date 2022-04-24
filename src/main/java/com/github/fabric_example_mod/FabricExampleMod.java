package com.github.fabric_example_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FabricExampleMod implements ModInitializer {

	public final void Main() {
		Registry.register(Registry.ITEM, new Identifier("fabric_example_mod", "blood_core"), BLOOD_CORE);
		Registry.register(Registry.ITEM, new Identifier("fabric_example_mod", "blood_debris"), BLOOD_DEBRIS);
		Registry.register(Registry.ITEM, new Identifier("fabric_example_mod", "blood_bottle"), BLOOD_BOTTLE);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}

	public static final Item BLOOD_CORE = new BloodCore(new Item.Settings());
	public static final Item BLOOD_DEBRIS = new BloodDebris(new Item.Settings());
	public static final Item BLOOD_BOTTLE = new BloodBottle(new Item.Settings());

	public static final ItemGroup GROUP = FabricItemGroupBuilder.create(
			new Identifier("fabric_example_mod", "group")
	).icon(() -> new ItemStack(BLOOD_CORE))
			.appendItems(itemStacks -> {
				itemStacks.add(new ItemStack(BLOOD_CORE));
				itemStacks.add(new ItemStack(BLOOD_DEBRIS));
				itemStacks.add(new ItemStack(BLOOD_BOTTLE));
			})
			.build();

}