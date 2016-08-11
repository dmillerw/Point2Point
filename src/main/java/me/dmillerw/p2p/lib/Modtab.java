package me.dmillerw.p2p.lib;

import me.dmillerw.p2p.block.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by dmillerw
 */
public class ModTab extends CreativeTabs {

    public static final ModTab INSTANCE = new ModTab();

    public ModTab() {
        super(ModInfo.ID);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockRegistry.CABLE);
    }
}
