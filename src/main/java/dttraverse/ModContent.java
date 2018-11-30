package dttraverse;

import java.util.ArrayList;
import java.util.Collections;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.ModItems;
import com.ferreusveritas.dynamictrees.ModRecipes;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.items.DendroPotion;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;

import dttraverse.trees.SpeciesAutumnBrown;
import dttraverse.trees.SpeciesAutumnOrange;
import dttraverse.trees.SpeciesAutumnRed;
import dttraverse.trees.SpeciesAutumnYellow;
import dttraverse.trees.TreeFir;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import prospector.traverse.init.TraverseBlocks;

@Mod.EventBusSubscriber(modid = DynamicTreesTraverse.MODID)
@GameRegistry.ObjectHolder(DynamicTreesTraverse.MODID)
public class ModContent {
    public static ILeavesProperties firLeavesProperties, autumnYellowLeavesProperties, autumnOrangeLeavesProperties, autumnRedLeavesProperties, autumnBrownLeavesProperties;
    public static ArrayList<TreeFamily> trees = new ArrayList<TreeFamily>();

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        TreeFamily oakTree = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "oak")).getFamily();
        TreeFamily birchTree = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "birch")).getFamily();

        Block firLeaves = TraverseBlocks.blocks.get("fir_leaves");
        Item firLeavesItem = new ItemBlock(firLeaves);
        firLeavesItem.setRegistryName(firLeaves.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(firLeavesItem);
        firLeavesProperties = new LeavesProperties(
                firLeaves.getDefaultState(),
                new ItemStack(firLeavesItem,1, 0),
                TreeRegistry.findCellKit("conifer")){
            @Override
            public int getSmotherLeavesMax() {
                return 5;
            }
            @Override
            public int foliageColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos) {
                return 0xffffff;
            }
        };

        Block autumnYellowLeaves = TraverseBlocks.blocks.get("yellow_autumnal_leaves");
        Item autumnYellowLeavesItem = new ItemBlock(autumnYellowLeaves);
        autumnYellowLeavesItem.setRegistryName(autumnYellowLeaves.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(autumnYellowLeavesItem);
        autumnYellowLeavesProperties = new LeavesProperties(
                autumnYellowLeaves.getDefaultState(),
                new ItemStack(autumnYellowLeavesItem, 1),
                TreeRegistry.findCellKit("deciduous")) {
            @Override
            public int foliageColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos) {
                return 0xffffff;
            }
        };

        Block autumnOrangeLeaves = TraverseBlocks.blocks.get("orange_autumnal_leaves");
        Item autumnOrangeLeavesItem = new ItemBlock(autumnOrangeLeaves);
        autumnOrangeLeavesItem.setRegistryName(autumnOrangeLeaves.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(autumnOrangeLeavesItem);
        autumnOrangeLeavesProperties = new LeavesProperties(
                autumnOrangeLeaves.getDefaultState(),
                new ItemStack(autumnOrangeLeavesItem, 1),
                TreeRegistry.findCellKit("deciduous")) {
            @Override
            public int foliageColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos) {
                return 0xffffff;
            }
        };

        Block autumnRedLeaves = TraverseBlocks.blocks.get("red_autumnal_leaves");
        Item autumnRedLeavesLeavesItem = new ItemBlock(autumnRedLeaves);
        autumnRedLeavesLeavesItem.setRegistryName(autumnRedLeaves.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(autumnRedLeavesLeavesItem);
        autumnRedLeavesProperties = new LeavesProperties(
                autumnRedLeaves.getDefaultState(),
                new ItemStack(autumnRedLeavesLeavesItem, 1),
                TreeRegistry.findCellKit("deciduous")) {
            @Override
            public int foliageColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos) {
                return 0xffffff;
            }
        };

        Block autumnBrownLeaves = TraverseBlocks.blocks.get("brown_autumnal_leaves");
        Item autumnBrownLeavesLeavesItem = new ItemBlock(autumnBrownLeaves);
        autumnBrownLeavesLeavesItem.setRegistryName(autumnBrownLeaves.getRegistryName());
        GameRegistry.findRegistry(Item.class).register(autumnBrownLeavesLeavesItem);
        autumnBrownLeavesProperties = new LeavesProperties(
                autumnBrownLeaves.getDefaultState(),
                new ItemStack(autumnBrownLeavesLeavesItem,1),
                TreeRegistry.findCellKit("deciduous")) {
            @Override
            public int getSmotherLeavesMax() {
                return 1;
            }
            @Override
            public int foliageColorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos) {
                return 0xffffff;
            }
        };

        LeavesPaging.getLeavesBlockForSequence(DynamicTreesTraverse.MODID, 0, firLeavesProperties);
        LeavesPaging.getLeavesBlockForSequence(DynamicTreesTraverse.MODID, 4, autumnYellowLeavesProperties);
        LeavesPaging.getLeavesBlockForSequence(DynamicTreesTraverse.MODID, 8, autumnOrangeLeavesProperties);
        LeavesPaging.getLeavesBlockForSequence(DynamicTreesTraverse.MODID, 12, autumnRedLeavesProperties);
        LeavesPaging.getLeavesBlockForSequence(DynamicTreesTraverse.MODID, 16, autumnBrownLeavesProperties);

        Species.REGISTRY.register(new SpeciesAutumnYellow(birchTree));
        Species.REGISTRY.register(new SpeciesAutumnOrange(oakTree));
        Species.REGISTRY.register(new SpeciesAutumnRed(oakTree));
        Species.REGISTRY.register(new SpeciesAutumnBrown(oakTree));

        TreeFamily firTree = new TreeFir();
        Collections.addAll(trees, firTree);
        trees.forEach(tree -> tree.registerSpecies(Species.REGISTRY));
        
        ArrayList<Block> treeBlocks = new ArrayList<>();
        trees.forEach(tree -> tree.getRegisterableBlocks(treeBlocks));
        treeBlocks.addAll(LeavesPaging.getLeavesMapForModId(DynamicTreesTraverse.MODID).values());
        registry.registerAll(treeBlocks.toArray(new Block[treeBlocks.size()]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_yellow")).getSeed(),
                TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_orange")).getSeed(),
                TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_red")).getSeed(),
                TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_brown")).getSeed()
        );

        ArrayList<Item> treeItems = new ArrayList<>();
        trees.forEach(tree -> tree.getRegisterableItems(treeItems));
        registry.registerAll(treeItems.toArray(new Item[treeItems.size()]));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        setupRecipes("fir","fir_sapling");
        setupRecipes("autumn_yellow","yellow_autumnal_sapling");
        setupRecipes("autumn_orange","orange_autumnal_sapling");
        setupRecipes("autumn_red","red_autumnal_sapling");
        setupRecipes("autumn_red","red_brown_sapling");
    }

    public static void setupRecipes(String species, String sapling) {
        Species specie = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, species));
        ItemStack seed = specie.getSeedStack(1);
        ItemStack transofrmationPotion = ModItems.dendroPotion.setTargetTree(new ItemStack(ModItems.dendroPotion, 1, DendroPotion.DendroPotionType.TRANSFORM.getIndex()), specie.getFamily());
        BrewingRecipeRegistry.addRecipe(new ItemStack(ModItems.dendroPotion, 1, DendroPotion.DendroPotionType.TRANSFORM.getIndex()), seed, transofrmationPotion);
        ModRecipes.createDirtBucketExchangeRecipes(new ItemStack(TraverseBlocks.blocks.get(sapling)), seed, true);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(TreeFamily tree : ModContent.trees) {
            ModelHelper.regModel(tree.getDynamicBranch());
            ModelHelper.regModel(tree.getCommonSpecies().getSeed());
            ModelHelper.regModel(tree);
        }
        ModelHelper.regModel(TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_yellow")).getSeed());
        ModelHelper.regModel(TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_orange")).getSeed());
        ModelHelper.regModel(TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_red")).getSeed());
        ModelHelper.regModel(TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_brown")).getSeed());

        LeavesPaging.getLeavesMapForModId(DynamicTreesTraverse.MODID).forEach((key,leaves) -> ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build()));
    }
}
