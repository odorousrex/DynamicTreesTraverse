package dttraverse.event;

import com.ferreusveritas.dynamictrees.ModConfigs;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.trees.Species;
import dttraverse.DynamicTreesTraverse;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import prospector.traverse.init.TraverseBlocks;

@Mod.EventBusSubscriber(modid = DynamicTreesTraverse.MODID)
public class ReplaceSaplingEventHandler {

    @SubscribeEvent
    public static void onPlaceSapling(BlockEvent.PlaceEvent event) {
        if (!ModConfigs.replaceVanillaSapling) return;

        IBlockState state = event.getPlacedBlock();

        Species species = null;
        if (state.getBlock() == TraverseBlocks.blocks.get("fir_sapling")) species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "fir"));
        else if (state.getBlock() == TraverseBlocks.blocks.get("yellow_autumnal_sapling")) species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_yellow"));
        else if (state.getBlock() == TraverseBlocks.blocks.get("orange_autumnal_sapling")) species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_orange"));
        else if (state.getBlock() == TraverseBlocks.blocks.get("red_autumnal_sapling")) species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_red"));
        else if (state.getBlock() == TraverseBlocks.blocks.get("brown_autumnal_sapling")) species = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_brown"));

            if (species != null) {
            event.getWorld().setBlockToAir(event.getPos());
            if(!species.plantSapling(event.getWorld(), event.getPos())) {
                double x = event.getPos().getX() + 0.5;
                double y = event.getPos().getY() + 0.5;
                double z = event.getPos().getZ() + 0.5;
                EntityItem itemEntity = new EntityItem(event.getWorld(), x, y, z, species.getSeedStack(1));
                event.getWorld().spawnEntity(itemEntity);
            }
        }
    }

}

