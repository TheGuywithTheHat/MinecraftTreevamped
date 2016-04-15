package com.treevamped;

import com.treevamped.blocks.BlockGrowth;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = TreevampedMod.MODID, name = TreevampedMod.MODNAME, version = TreevampedMod.MODVERSION)
public class TreevampedMod
{
    public static final String MODID = "treevamped";
    public static final String MODNAME = "Minecraft Treevamped";
    public static final String MODVERSION = "1.0.0";
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        BlockGrowth growthLeaf = (BlockGrowth)new BlockGrowth().setRegistryName("treevamped", "growth");
        ItemBlock growthLeafItem = (ItemBlock)new ItemBlock(growthLeaf).setRegistryName("treevamped", "growth");
        GameRegistry.register(growthLeaf);
        GameRegistry.register(growthLeafItem);
        
        String variants[] = { "oak", "spruce", "birch", "jungle", "acacia", "dark_oak" };
        ResourceLocation[] resLocs = new ResourceLocation[6];
        for(int i = 0; i < resLocs.length; i++) {
            resLocs[i] = new ResourceLocation("minecraft:" + variants[i] + "_leaves");
        }
        ModelBakery.registerItemVariants(Item.getItemFromBlock(growthLeaf), resLocs);
    }
    
    @SubscribeEvent
    public void saplingGrew(SaplingGrowTreeEvent event)
    {
    	
    }
}