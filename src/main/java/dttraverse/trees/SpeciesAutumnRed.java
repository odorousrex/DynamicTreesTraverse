package dttraverse.trees;

import com.ferreusveritas.dynamictrees.ModBlocks;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicSaplingRare;
import com.ferreusveritas.dynamictrees.trees.SpeciesRare;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import dttraverse.DynamicTreesTraverse;
import dttraverse.ModContent;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import prospector.traverse.world.TraverseWorld;

import java.util.Random;

public class SpeciesAutumnRed extends SpeciesRare {

    public SpeciesAutumnRed(TreeFamily treeFamily) {
        super(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_red"), treeFamily, ModContent.autumnRedLeavesProperties);

        setBasicGrowingParameters(0.15f, 14.0f, 4, 4, 1.05f);

        setDynamicSapling(new BlockDynamicSaplingRare("autumn_redsapling").getDefaultState());

        envFactor(BiomeDictionary.Type.HOT, 0.50f);
        envFactor(BiomeDictionary.Type.DRY, 0.50f);
        envFactor(BiomeDictionary.Type.FOREST, 1.05f);

        generateSeed();
        setupStandardSeedDropping();

        leavesProperties.setTree(treeFamily);
    }

    @Override
    public boolean isBiomePerfect(Biome biome) {
        return isOneOfBiomes(biome, TraverseWorld.autumnalWoodedHillsBiome, TraverseWorld.autumnalWoodsBiome);
    }

    @Override
    public boolean rot(World world, BlockPos pos, int neighborCount, int radius, Random random, boolean rapid) {
        if(super.rot(world, pos, neighborCount, radius, random, rapid)) {
            if(radius > 4 && TreeHelper.isRooty(world.getBlockState(pos.down())) && world.getLightFor(EnumSkyBlock.SKY, pos) < 4) {
                world.setBlockState(pos, random.nextInt(3) == 0 ? ModBlocks.blockStates.redMushroom : ModBlocks.blockStates.brownMushroom);//Change branch to a mushroom
                world.setBlockState(pos.down(), ModBlocks.blockStates.podzol);//Change rooty dirt to Podzol
            }
            return true;
        }

        return false;
    }

}
