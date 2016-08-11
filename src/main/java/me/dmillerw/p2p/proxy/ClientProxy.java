package me.dmillerw.p2p.proxy;

import me.dmillerw.p2p.block.BlockRegistry;
import me.dmillerw.p2p.client.model.loader.BaseModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by dmillerw
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        ModelLoaderRegistry.registerLoader(new BaseModelLoader());

        BlockRegistry.initializeBlockModels();
        BlockRegistry.initializeItemModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
