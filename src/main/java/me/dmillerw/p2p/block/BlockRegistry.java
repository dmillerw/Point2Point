package me.dmillerw.p2p.block;

import me.dmillerw.p2p.block.cable.BlockCable;
import me.dmillerw.p2p.block.cpu.BlockCPU;
import me.dmillerw.p2p.block.node.BlockNode;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author dmillerw
 */
public class BlockRegistry {

    public static BlockCable CABLE;
    public static BlockCPU CPU;
    public static BlockNode NODE;

    public static void initialize() {
        CABLE = new BlockCable();
        CPU = new BlockCPU();
    }

    @SideOnly(Side.CLIENT)
    public static void initializeBlockModels() {
        CABLE.initializeBlockModel();
        // CPU - NOOP
    }

    @SideOnly(Side.CLIENT)
    public static void initializeItemModels() {
        CABLE.initializeItemModel();
        // CPU - NOOP
    }
}
