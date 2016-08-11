package me.dmillerw.p2p.block.node;

import me.dmillerw.p2p.block.core.BlockTileCore;
import me.dmillerw.p2p.block.core.TileCore;
import net.minecraft.block.material.Material;

/**
 * Created by dmillerw
 */
public class BlockNode extends BlockTileCore {

    public static final String NAME = "node";

    public BlockNode() {
        super(Material.IRON);
    }

    @Override
    public String getBlockName() {
        return NAME;
    }

    @Override
    public Class<? extends TileCore> getTile() {
        return null;
    }
}
