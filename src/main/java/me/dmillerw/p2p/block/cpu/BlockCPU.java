package me.dmillerw.p2p.block.cpu;

import me.dmillerw.p2p.block.core.BlockTileCore;
import me.dmillerw.p2p.block.core.TileCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by dmillerw
 */
public class BlockCPU extends BlockTileCore {

    public static final String NAME = "cpu";

    public static final PropertyBool ERROR = PropertyBool.create("error");

    public BlockCPU() {
        super(Material.IRON);

        setDefaultState(this.blockState.getBaseState().withProperty(ERROR, false));
    }

    @Override
    public String getBlockName() {
        return NAME;
    }

    @Override
    public Class<? extends TileCore> getTile() {
        return TileCPU.class;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ERROR);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(ERROR, meta != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ERROR) ? 1 : 0;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            TileCPU cpu = (TileCPU) worldIn.getTileEntity(pos);
            if (cpu != null) cpu.analyzePath();
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        if (!worldIn.isRemote) {
            TileCPU cpu = (TileCPU) worldIn.getTileEntity(pos);
            if (cpu != null) cpu.analyzePath();
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            TileCPU cpu = (TileCPU) worldIn.getTileEntity(pos);
            if (cpu != null) cpu.destroy();
        }
        super.breakBlock(worldIn, pos, state);
    }
}
