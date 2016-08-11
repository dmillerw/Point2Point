package me.dmillerw.p2p.block.cpu;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import me.dmillerw.p2p.block.BlockRegistry;
import me.dmillerw.p2p.block.cable.TileCable;
import me.dmillerw.p2p.block.core.TileCore;
import me.dmillerw.p2p.block.node.TileNode;
import me.dmillerw.p2p.util.PathFinder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ITickable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Set;

/**
 * Created by dmillerw
 */
public class TileCPU extends TileCore implements ITickable {

    private List<TileNode> attachedNodes = Lists.newArrayList();
    private List<BlockPos> connectedCables = Lists.newArrayList();

    private boolean analyzedOnLoad = false;

    @Override
    public void update() {
        if (worldObj != null && !worldObj.isRemote && !analyzedOnLoad) {
            analyzePath();
            analyzedOnLoad = true;
        }
    }

    public final void analyzePath() {
        if (!worldObj.isRemote) {
            PathFinder finder = new PathFinder(worldObj, pos);
            finder.find(true, (pos, face) -> {
                IBlockState state = worldObj.getBlockState(pos);
                return state.getBlock() == BlockRegistry.CABLE;
            });

            for (BlockPos p : finder.getConnectedBlocks()) {
                for (EnumFacing e : EnumFacing.VALUES) {
                    if (p.offset(e).equals(getPos()))
                        continue;

                    // If we run into another CPU, disconnect our nodes and cables, and set
                    // our error state to true
                    //TODO

                    if (worldObj.getBlockState(p).getBlock() == BlockRegistry.NODE) {
                        attachedNodes.add((TileNode)worldObj.getTileEntity(pos));
                    }
                }
            }

            Set<BlockPos> newCables = Sets.newHashSet();
            finder.forEach(newCables::add);

            connectedCables.forEach((pos) -> {
                if (!newCables.contains(pos)) {
                    TileCable cable = (TileCable) worldObj.getTileEntity(pos);
                    if (cable != null) cable.setNetworkCPU(null);
                }
            });

            connectedCables.clear();
            connectedCables.addAll(newCables);

            connectedCables.forEach((pos) -> {
                TileCable cable = (TileCable) worldObj.getTileEntity(pos);
                if (cable != null) cable.setNetworkCPU(this);
            });
        }
    }

    @Override
    public void destroy() {
        if (!worldObj.isRemote) {
            connectedCables.forEach((pos) -> {
                TileCable cable = (TileCable) worldObj.getTileEntity(pos);
                if (cable != null) cable.setNetworkCPU(null);
            });
        }
    }
}
