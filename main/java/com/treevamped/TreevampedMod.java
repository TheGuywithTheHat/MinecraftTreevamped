package com.treevamped;

import com.treevamped.block.TBlock;

import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = TreevampedMod.MODID, name = TreevampedMod.MODNAME, version = TreevampedMod.MODVERSION)
public class TreevampedMod
{
    public static final String MODID = "treevamped";
    public static final String MODNAME = "Minecraft Treevamped";
    public static final String MODVERSION = "1.0.0";
    
    @SidedProxy(clientSide = "com.treevamped.ClientProxy")
    public static ClientProxy proxy;
    
    
    /**
     * @param event The event passed by Forge 
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        TBlock.loadBlocks();
        proxy.preInit();
    }
    
    /**
     * @param event The event passed by Forge 
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    
    /**
     * @param event The event passed by Forge 
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
    
    /**
     * @param event The event passed by Forge 
     */
    @SubscribeEvent
    public void saplingGrew(SaplingGrowTreeEvent event)
    {
        //
    }
}