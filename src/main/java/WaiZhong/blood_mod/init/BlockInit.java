package WaiZhong.blood_mod.init;

import WaiZhong.blood_mod.block.BloodOre;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    public static final Block BLOOD_ORE = new BloodOre(FabricBlockSettings
            .of(Material.STONE)
            .hardness(1.3f)
            .requiresTool()
            .nonOpaque()
    );

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("blood_mod", "blood_ore"), BLOOD_ORE);
        Registry.register(Registry.ITEM, new Identifier("blood_mod", "blood_ore"), new BlockItem(BLOOD_ORE, new Item.Settings()));
    }
}
