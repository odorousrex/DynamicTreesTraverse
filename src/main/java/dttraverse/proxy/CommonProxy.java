package dttraverse.proxy;

import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.trees.Species;

import dttraverse.DynamicTreesTraverse;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import prospector.traverse.init.TraverseBlocks;

public class CommonProxy {
	
	public void preInit() {
	}
	
	public void init() {
		registerSaplingReplacement("fir_sapling", "fir");
		registerSaplingReplacement("yellow_autumnal_sapling", "autumn_yellow");
		registerSaplingReplacement("orange_autumnal_sapling", "autumn_orange");
		registerSaplingReplacement("red_autumnal_sapling", "autumn_red");
		registerSaplingReplacement("brown_autumnal_sapling", "autumn_brown");
	}
	
	private static void registerSaplingReplacement(String saplingName, String speciesName) {
		IBlockState sapling = TraverseBlocks.blocks.get(saplingName).getDefaultState();
		Species species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, speciesName));
		TreeRegistry.registerSaplingReplacer(sapling, species);
	}
	
	public void postInit() {
	}
	
}
