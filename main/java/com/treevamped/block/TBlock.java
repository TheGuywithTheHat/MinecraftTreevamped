package com.treevamped.block;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TBlock {
    public static BlockGrowth GROWTH_BLOCK;
    
    public static void loadBlocks() {
        GROWTH_BLOCK = registerBlock(new BlockGrowth());
    }
    
    private static <T extends BlockGrowth> T registerBlock(T block) {
        GameRegistry.register(block);
        block.item = new ItemBlock(block);
        GameRegistry.register(block.item.setRegistryName(block.getRegistryName()));
        return block;
    }
}
