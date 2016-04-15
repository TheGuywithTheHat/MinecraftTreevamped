package com.treevamped.blocks;

import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrowth extends BlockLeaves {
    
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType> create("variant", BlockPlanks.EnumType.class);
            
    public BlockGrowth() {
        super();
        this.setUnlocalizedName("growthLeaf");
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }
    
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if((state.getValue(VARIANT) == BlockPlanks.EnumType.OAK || state.getValue(VARIANT) == BlockPlanks.EnumType.DARK_OAK) && worldIn.rand.nextInt(chance) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(Items.apple));
        }
    }
    
    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return state.getValue(VARIANT) == BlockPlanks.EnumType.JUNGLE ? 40 : super.getSaplingDropChance(state);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
        list.add(new ItemStack(itemIn, 1, 3));
        list.add(new ItemStack(itemIn, 1, 4));
        list.add(new ItemStack(itemIn, 1, 5));
    }
    
    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT).getMetadata());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, this.getWoodType(meta)).withProperty(DECAYABLE, new Boolean(true)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(VARIANT).getMetadata();
        
        if(state.getValue(CHECK_DECAY).booleanValue()) {
            i |= 8;
        }
        
        return i;
    }
    
    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.byMetadata(meta & 7);
    }
    
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {
                VARIANT, CHECK_DECAY, DECAYABLE
        });
    }
    
    /**
     * Gets the metadata of the item this Block can drop. This method is called
     * when the block gets destroyed. It returns the metadata of the dropped
     * item based on the old metadata of the block.
     */
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
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
        return java.util.Arrays.asList(new ItemStack(this, 1, world.getBlockState(pos).getValue(VARIANT).getMetadata()));
    }
}
