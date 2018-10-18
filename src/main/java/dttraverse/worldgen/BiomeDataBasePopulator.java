package dttraverse.worldgen;

import java.util.ArrayList;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.worldgen.BiomePropertySelectors;
import com.ferreusveritas.dynamictrees.api.worldgen.IBiomeDataBasePopulator;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;

import dttraverse.DynamicTreesTraverse;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import prospector.traverse.world.TraverseWorld;

public class BiomeDataBasePopulator implements IBiomeDataBasePopulator {

    private Species fir, autumn_yellow, autumn_orange, autumn_red, autumn_brown,
    apple, jungle, spruce, birch, oak, acacia, swamp, darkoak, cactus;

    @Override
    public void populate(BiomeDataBase biomeDataBase) {

        //Traverse trees
        fir = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "fir"));
        autumn_brown = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_brown"));
        autumn_orange = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_orange"));
        autumn_red = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_red"));
        autumn_yellow = TreeRegistry.findSpecies(new ResourceLocation(DynamicTreesTraverse.MODID, "autumn_yellow"));

        //Vanilla trees
        apple = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "oakapple"));
        jungle = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "jungle"));
        spruce = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "spruce"));
        birch = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "birch"));
        oak = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "oak"));
        acacia = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "acacia"));
        swamp = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "oakswamp"));
        darkoak = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "darkoak"));
        cactus = TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "cactus"));

        //TraverseWorld.autumnalWoodsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.autumnalWoodsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(autumn_yellow, 4).add(autumn_orange, 5).add(oak, 1).add(autumn_brown, 2).add(autumn_red, 4));
        addDensitySelector(biomeDataBase, TraverseWorld.autumnalWoodsBiome,scale(0.9) );

        //TraverseWorld.autumnalWoodedHillsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.autumnalWoodedHillsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(autumn_yellow, 4).add(autumn_orange, 5).add(oak, 1).add(autumn_brown, 2).add(autumn_red, 4));
        addDensitySelector(biomeDataBase, TraverseWorld.autumnalWoodedHillsBiome,scale(0.9) );

        //TraverseWorld.temperateRainforestBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.temperateRainforestBiome,new BiomePropertySelectors.StaticSpeciesSelector(fir));

        //TraverseWorld.snowyConiferousForestBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.snowyConiferousForestBiome,new BiomePropertySelectors.StaticSpeciesSelector(fir));

        //TraverseWorld.aridHighlandBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.aridHighlandBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(acacia, 4).add(oak, 1).add(cactus, 1));
        addDensitySelector(biomeDataBase, TraverseWorld.aridHighlandBiome,scale(0.1) );

        //TraverseWorld.badlandsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.badlandsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(autumn_brown, 1).add(oak, 1));
        addDensitySelector(biomeDataBase, TraverseWorld.badlandsBiome,scale(0.03) );

        //TraverseWorld.birchForestedHillsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.birchForestedHillsBiome,new BiomePropertySelectors.StaticSpeciesSelector(birch));

        //TraverseWorld.forestedHillsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.forestedHillsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 4).add(birch, 2));
        addDensitySelector(biomeDataBase, TraverseWorld.forestedHillsBiome,scale(0.9) );

        //TraverseWorld.lushHillsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.lushHillsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 4).add(swamp, 1));
        addDensitySelector(biomeDataBase, TraverseWorld.lushHillsBiome,scale(0.4) );

        //TraverseWorld.lushSwampBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.lushSwampBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(swamp, 4).add(oak, 1));
        addDensitySelector(biomeDataBase, TraverseWorld.lushSwampBiome,scale(0.6) );

        //TraverseWorld.meadowBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.meadowBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 2).add(birch, 1));
        addDensitySelector(biomeDataBase, TraverseWorld.meadowBiome,scale(0.01) );

        //TraverseWorld.rockyPlainsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.rockyPlainsBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 2));
        addDensitySelector(biomeDataBase, TraverseWorld.rockyPlainsBiome,scale(0.01) );

        //TraverseWorld.rockyPlateauBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.rockyPlateauBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 2));
        addDensitySelector(biomeDataBase, TraverseWorld.rockyPlateauBiome,scale(0.01) );

        //TraverseWorld.thicketBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.thicketBiome,new BiomePropertySelectors.RandomSpeciesSelector().add(oak, 4).add(darkoak, 2));
        addDensitySelector(biomeDataBase, TraverseWorld.thicketBiome,scale(1.5) );

        //TraverseWorld.woodlandsBiome
        addSpeciesSelector(biomeDataBase, TraverseWorld.woodlandsBiome,new BiomePropertySelectors.StaticSpeciesSelector(oak));
        addDensitySelector(biomeDataBase, TraverseWorld.woodlandsBiome,scale(0.6) );

        ArrayList<Biome> blackList = new ArrayList<>();
        blackList.add(TraverseWorld.canyonBiome);
        blackList.add(TraverseWorld.cliffsBiome);
        blackList.add(TraverseWorld.cragCliffsBiome);
        blackList.add(TraverseWorld.desertShrublandBiome);
        blackList.add(TraverseWorld.glacierBiome);
        blackList.add(TraverseWorld.glacierSpikesBiome);
        blackList.add(TraverseWorld.miniJungleBiome);
        blackList.add(TraverseWorld.mountainousDesertBiome);
        blackList.add(TraverseWorld.redDesertBiome);

        Biome.REGISTRY.forEach(biome -> {
            if (biome.getRegistryName().getResourceDomain().equals("traverse") && !blackList.contains(biome)) {
            	biomeDataBase.setCancelVanillaTreeGen(biome, true);
            }
        });
    }

    private void addSpeciesSelector(BiomeDataBase dbase, Biome biome, BiomePropertySelectors.ISpeciesSelector selector) {
        dbase.setSpeciesSelector(biome, selector, BiomeDataBase.Operation.REPLACE);
    }

    private BiomePropertySelectors.IChanceSelector rand(float threshhold) {
        return (rnd, spc, rad) -> rnd.nextFloat() < threshhold ? BiomePropertySelectors.EnumChance.OK : BiomePropertySelectors.EnumChance.CANCEL;
    }

    private void addDensitySelector(BiomeDataBase dbase, Biome biome, BiomePropertySelectors.IDensitySelector selector) {
            dbase.setDensitySelector(biome, selector, BiomeDataBase.Operation.REPLACE);
    }

    private BiomePropertySelectors.IDensitySelector scale() {
        return (rnd, nd) -> nd;
    }

    private BiomePropertySelectors.IDensitySelector scale(double factor1) {
        return (rnd, nd) -> nd * factor1;
    }

    private BiomePropertySelectors.IDensitySelector scale(double factor1, double addend) {
        return (rnd, nd) -> (nd * factor1) + addend;
    }

    private BiomePropertySelectors.IDensitySelector scale(double factor1, double addend, double factor2) {
        return (rnd, nd) -> ((nd * factor1) + addend) * factor2;
    }
    
}
