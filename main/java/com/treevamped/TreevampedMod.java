package com.treevamped;

import com.treevamped.blocks.BlockGrowth;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
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
    public void preInit(FMLInitializationEvent event) {
        BlockGrowth growth = new BlockGrowth();
        ItemBlock growthItem = new ItemBlock(growth);
        GameRegistry.register(growth);
        GameRegistry.register(growthItem.setRegistryName(growth.getRegistryName()));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(growthItem, 0, new ModelResourceLocation(growthItem.getRegistryName(), "inventory"));
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        //
    }
    
    @SubscribeEvent
    public void saplingGrew(SaplingGrowTreeEvent event)
    {
        //
    }
}