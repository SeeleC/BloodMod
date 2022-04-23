package com.github.fabric_example_mod;

import com.mojang.datafixers.optics.Affine;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public final void Main() {
		Registry.register(Registry.ITEM, new Identifier("fabric_example_mod", "fabric_item"), FABRIC_ITEM);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}

	public static final Item FABRIC_ITEM = new FabricItem(new Item.Settings().group(ItemGroup.MISC).maxCount(16));
}