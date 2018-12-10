package dttraverse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dttraverse.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=DynamicTreesTraverse.MODID, name = DynamicTreesTraverse.NAME, version = DynamicTreesTraverse.VERSION, dependencies = DynamicTreesTraverse.DEPENDENCIES)
public class DynamicTreesTraverse {
	
	public static final String MODID = "dttraverse";
	public static final String NAME = "Dynamic Trees Traverse";
	//public static final String VERSION = "@VERSION@";
	public static final String VERSION = "1.4";
	public static final String DEPENDENCIES = "required-after:dynamictrees@[1.12.2-0.7.7e,);required-after:traverse";
	
	@Mod.Instance
	public static DynamicTreesTraverse instance;
	
	public static final Logger logger = LogManager.getLogger(MODID);
	
	@SidedProxy(clientSide = "dttraverse.proxy.ClientProxy", serverSide = "dttraverse.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		proxy.postInit();
	}
	
}
