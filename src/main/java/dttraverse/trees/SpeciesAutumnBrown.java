package dttraverse.trees;

import com.ferreusveritas.dynamictrees.ModBlocks;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicSaplingRare;
import com.ferreusveritas.dynamictrees.trees.SpeciesRare;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import dttraverse.DynamicTreesTraverse;
import dttraverse.ModContent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import prospector.traverse.world.TraverseWorld;

import java.util.Random;

public class SpeciesAutumnBrown extends SpeciesRare {

    public SpeciesAutumnBrown(TreeFamily treeFamily) {
        super(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_brown"), treeFamily, ModContent.autumnBrownLeavesProperties);

        setBasicGrowingParameters(0.3f, 12.0f, upProbability, lowestBranchHeight, 0.65f);

        setDynamicSapling(new BlockDynamicSaplingRare("autumn_brownsapling").getDefaultState());

        envFactor(BiomeDictionary.Type.LUSH, 0.75f);
        envFactor(BiomeDictionary.Type.SPOOKY, 1.05f);
        envFactor(BiomeDictionary.Type.DEAD, 1.05f);

        generateSeed();

        setupStandardSeedDropping();


        leavesProperties.setTree(treeFamily);
    }

    @Override
    public boolean isBiomePerfect(Biome biome) {
        return isOneOfBiomes(biome, TraverseWorld.autumnalWoodedHillsBiome, TraverseWorld.autumnalWoodsBiome);
    }

    @Override
    public boolean rot(World world, BlockPos pos, int neighborCount, int radius, Random random) {
        if(super.rot(world, pos, neighborCount, radius, random)) {
            if(radius > 4 && TreeHelper.isRooty(world.getBlockState(pos.down())) && world.getLightFor(EnumSkyBlock.SKY, pos) < 4) {
                world.setBlockState(pos, random.nextInt(3) == 0 ? ModBlocks.blockStates.redMushroom : ModBlocks.blockStates.brownMushroom);//Change branch to a mushroom
                world.setBlockState(pos.down(), ModBlocks.blockStates.podzol);//Change rooty dirt to Podzol
            }
            return true;
        }
        return false;
    }
}
