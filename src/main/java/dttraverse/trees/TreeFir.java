package dttraverse.trees;

import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicSapling;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import dttraverse.DynamicTreesTraverse;
import dttraverse.ModContent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import prospector.traverse.init.TraverseBlocks;
import java.util.Collections;
import java.util.List;


public class TreeFir extends TreeFamily {

    public class SpeciesFir extends Species {

        SpeciesFir(TreeFamily treeFamily) {
            super(treeFamily.getName(), treeFamily, ModContent.firLeavesProperties);

            setBasicGrowingParameters(0.3f, 16.0f, 3, 3, 0.9f);

            setDynamicSapling(new BlockDynamicSapling("firsapling").getDefaultState());

            envFactor(BiomeDictionary.Type.HOT, 0.50f);
            envFactor(BiomeDictionary.Type.DRY, 0.25f);
            envFactor(BiomeDictionary.Type.WET, 0.75f);

            generateSeed();
            setupStandardSeedDropping();

        }

        @Override
        public boolean isBiomePerfect(Biome biome) {
            return BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS);
        }

        @Override
        protected int[] customDirectionManipulation(World world, BlockPos pos, int radius, GrowSignal signal, int probMap[]) {

            EnumFacing originDir = signal.dir.getOpposite();

            //Alter probability map for direction change
            probMap[0] = 0;//Down is always disallowed for spruce
            probMap[1] = signal.isInTrunk() ? getUpProbability(): 0;
            probMap[2] = probMap[3] = probMap[4] = probMap[5] = //Only allow turns when we aren't in the trunk(or the branch is not a twig and step is odd)
                    !signal.isInTrunk() || (signal.isInTrunk() && signal.numSteps % 2 == 1 && radius > 1) ? 2 : 0;
            probMap[originDir.ordinal()] = 0;//Disable the direction we came from
            probMap[signal.dir.ordinal()] += signal.isInTrunk() ? 0 : signal.numTurns == 1 ? 2 : 1;//Favor current travel direction

            return probMap;
        }

        @Override
        protected EnumFacing newDirectionSelected(EnumFacing newDir, GrowSignal signal) {
            if(signal.isInTrunk() && newDir != EnumFacing.UP){//Turned out of trunk
                signal.energy /= 3.0f;
            }
            return newDir;
        }

        @Override
        public float getEnergy(World world, BlockPos pos) {
            long day = world.getTotalWorldTime() / 24000L;
            int month = (int)day / 30;//Change the hashs every in-game month

            return super.getEnergy(world, pos) * biomeSuitability(world, pos) + (coordHashCode(pos.up(month)) % 5);//Vary the height energy by a psuedorandom hash function
        }

        public int coordHashCode(BlockPos pos) {
            int hash = (pos.getX() * 9973 ^ pos.getY() * 8287 ^ pos.getZ() * 9721) >> 1;
            return hash & 0xFFFF;
        }

        @Override
        public void postGeneration(World world, BlockPos rootPos, Biome biome, int radius, List<BlockPos> endPoints, SafeChunkBounds safeBounds) {
            //Manually place the highest few blocks of the conifer since the leafCluster voxmap won't handle it
            BlockPos highest = Collections.max(endPoints, (a, b) -> a.getY() - b.getY());
            world.setBlockState(highest.up(1), leavesProperties.getDynamicLeavesState(4));
            world.setBlockState(highest.up(2), leavesProperties.getDynamicLeavesState(3));
            world.setBlockState(highest.up(3), leavesProperties.getDynamicLeavesState(1));
        }
    }

    public TreeFir() {
        super(new ResourceLocation(DynamicTreesTraverse.MODID, "fir"));

        Block firLog = TraverseBlocks.blocks.get("fir_log");
        Item firLeavesItem = new ItemBlock(firLog);
        firLeavesItem.setRegistryName(firLog.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(firLeavesItem);
        IBlockState primLog = firLog.getDefaultState();
        DynamicTreesTraverse.logger.info("FIRLOG:" + firLog + " " + new ItemStack(firLog,1));
        setPrimitiveLog(primLog, new ItemStack(Item.getItemFromBlock(firLog),1));

        ModContent.firLeavesProperties.setTree(this);
    }

    @Override
    public void createSpecies() {
        setCommonSpecies(new SpeciesFir(this));
    }

    @Override
    public int getRadiusForCellKit(IBlockAccess blockAccess, BlockPos pos, IBlockState blockState, EnumFacing dir, BlockBranch branch) {
        int radius = branch.getRadius(blockState);
        if(radius == 1) {
            if(blockAccess.getBlockState(pos.down()).getBlock() == branch) {
                return 128;
            }
        }
        return radius;
    }

    @Override
    public List<Block> getRegisterableBlocks(List<Block> blockList) {
        blockList.add(getCommonSpecies().getDynamicSapling().getBlock());
        return super.getRegisterableBlocks(blockList);
    }

}
