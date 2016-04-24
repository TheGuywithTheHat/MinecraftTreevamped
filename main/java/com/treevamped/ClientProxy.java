package com.treevamped;

import com.treevamped.block.TBlock;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy {
    public void preInit() {
        //
    }
    
    public void init() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
            .register(TBlock.GROWTH_BLOCK.item, 0, new ModelResourceLocation(TBlock.GROWTH_BLOCK.item.getRegistryName(), "inventory"));
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, access, pos, tintIndex) -> {
                if(pos != null) {
                    return BiomeColorHelper.getFoliageColorAtPos(access, pos);
                }
                return 0xff00ff00;
            }, TBlock.GROWTH_BLOCK);
    }
    
    public void postInit() {
        //
    }
}
