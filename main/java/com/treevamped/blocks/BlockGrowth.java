package com.treevamped.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrowth extends BlockLeaves {
    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
    
    public BlockPlanks.EnumType type;
   
    public BlockGrowth() {
        super();
        setRegistryName("growth");
        setUnlocalizedName(getRegistryName().toString());
        
        //setDefaultState(this.blockState.getBaseState()/*.withProperty(LEVEL, new Integer(0))*/);
        type = BlockPlanks.EnumType.OAK;
    }

    
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if((type == BlockPlanks.EnumType.OAK || type == BlockPlanks.EnumType.DARK_OAK) && worldIn.rand.nextInt(chance) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.apple));
        }
    }
    
    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return type == BlockPlanks.EnumType.JUNGLE ? 40 : super.getSaplingDropChance(state);
    }
    
    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, type.getMetadata());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()/*.withProperty(LEVEL, new Integer(meta))*/;
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;//state.getValue(LEVEL).intValue();
    }
    
    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.OAK;
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this/*, LEVEL*/);
    }
    
    /**
     * Gets the metadata of the item this Block can drop. This method is called
     * when the block gets destroyed. It returns the metadata of the dropped
     * item based on the old metadata of the block.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }
    
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        if(!worldIn.isRemote && stack != null && stack.getItem() == Items.shears) {
            player.addStat(StatList.func_188055_a(this));
        } else {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
    
    @Override
    public List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Block block = type.getMetadata() < 4 ? Blocks.leaves : Blocks.leaves2;
        int meta = type.getMetadata() % 4;
        return java.util.Arrays.asList(new ItemStack(block, 1, meta));
    }
    
    /**
     * Just here to prevent BlockLeaves's updateTick from running.
     */
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        //
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return Minecraft.isFancyGraphicsEnabled() ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }
    
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return !Minecraft.isFancyGraphicsEnabled();
    }
}
