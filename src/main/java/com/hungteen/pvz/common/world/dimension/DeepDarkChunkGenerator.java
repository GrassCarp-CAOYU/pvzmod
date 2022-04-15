package com.hungteen.pvz.common.world.dimension;

import com.hungteen.pvz.utils.Util;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-14 10:12
 **/
public class DeepDarkChunkGenerator extends ChunkGenerator {

    private static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("base").forGetter(Settings::baseHeight),
                    Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::verticalVariance),
                    Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::horizontalVariance)
            ).apply(instance, Settings::new));

    public static final Codec<DeepDarkChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    RegistryOps.retrieveRegistry(Registry.STRUCTURE_SET_REGISTRY).forGetter(DeepDarkChunkGenerator::getStructureSetRegistry),
                    RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter(DeepDarkChunkGenerator::getBiomeRegistry),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(DeepDarkChunkGenerator::getSettings)
            ).apply(instance, DeepDarkChunkGenerator::new));

    public static final ResourceLocation DEEP_DARK_DIMENSION_SET = Util.prefix("deep_dark_dimension_structure_set");
    private final Settings settings;


    public DeepDarkChunkGenerator(Registry<StructureSet> structureSetRegistry, Registry<Biome> registry, Settings settings) {
        super(structureSetRegistry, getSet(structureSetRegistry), new DeepDarkBiomeProvider(registry));
        this.settings = settings;
    }

    private static Optional<HolderSet<StructureSet>> getSet(Registry<StructureSet> structureSetRegistry) {
        HolderSet.Named<StructureSet> structureSet = structureSetRegistry.getOrCreateTag(TagKey.create(Registry.STRUCTURE_SET_REGISTRY, DEEP_DARK_DIMENSION_SET));
        return Optional.of(structureSet);
    }

    public Settings getSettings() {
        return settings;
    }

    public Registry<Biome> getBiomeRegistry() {
        return ((DeepDarkBiomeProvider) biomeSource).getBiomeRegistry();
    }

    public Registry<StructureSet> getStructureSetRegistry() {
        return structureSets;
    }

    @Override
    public void buildSurface(WorldGenRegion region, StructureFeatureManager featureManager, ChunkAccess chunk) {
        BlockState bedrock = Blocks.BEDROCK.defaultBlockState();
        BlockState stone = Blocks.STONE.defaultBlockState();
        ChunkPos chunkpos = chunk.getPos();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int x;
        int z;

//        for (x = 0; x < 16; x++) {
//            for (z = 0; z < 16; z++) {
//                chunk.setBlockState(pos.set(x, -50, z), bedrock, false);
//            }
//        }

//        int baseHeight = settings.baseHeight();
//        float verticalVariance = settings.verticalVariance();
//        float horizontalVariance = settings.horizontalVariance();
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
//                int realx = chunkpos.x * 16 + x;
//                int realz = chunkpos.z * 16 + z;
//                int height = getHeightAt(baseHeight, verticalVariance, horizontalVariance, realx, realz);
//                for (int y = 1 ; y < height ; y++) {
//                    chunk.setBlockState(pos.set(x, y, z), stone, false);
//                }
                chunk.setBlockState(pos.set(x, 50, z), bedrock, false);
                chunk.setBlockState(pos.set(x, 100, z), stone, false);
                chunk.setBlockState(pos.set(x, 150, z), stone, false);
            }
        }
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, StructureFeatureManager featureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.completedFuture(chunkAccess);
    }

    @Override
    public Climate.Sampler climateSampler() {
        return new Climate.Sampler(DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), DensityFunctions.constant(0.0),
                DensityFunctions.constant(0.0), DensityFunctions.constant(0.0), Collections.emptyList());
    }

    // This makes sure passive mob spawning works for generated chunks. i.e. mobs that spawn during the creation of chunks themselves.
    @Override
    public void spawnOriginalMobs(WorldGenRegion level) {
        ChunkPos chunkpos = level.getCenter();
        Holder<Biome> biome = level.getBiome(chunkpos.getWorldPosition().atY(level.getMaxBuildHeight() - 1));
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.seedUniquifier()));
        worldgenrandom.setDecorationSeed(level.getSeed(), chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(level, biome, chunkpos, worldgenrandom);
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getGenDepth() {
        return 256;
    }

    @Override
    public int getSeaLevel() {
        return 63;
    }

    // Make sure this is correctly implemented so that structures and features can use this.
    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor) {
        int baseHeight = settings.baseHeight();
        float verticalVariance = settings.verticalVariance();
        float horizontalVariance = settings.horizontalVariance();
        return getHeightAt(baseHeight, verticalVariance, horizontalVariance, x, z);
    }

    // Make sure this is correctly implemented so that structures and features can use this.
    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor levelHeightAccessor) {
        int y = getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor);
        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState[] states = new BlockState[y];
        states[0] = Blocks.BEDROCK.defaultBlockState();
        for (int i = 1 ; i < y ; i++) {
            states[i] = stone;
        }
        return new NoiseColumn(levelHeightAccessor.getMinBuildHeight(), states);
    }

    @Override
    public void applyCarvers(WorldGenRegion p_187691_, long p_187692_, BiomeManager p_187693_, StructureFeatureManager p_187694_, ChunkAccess p_187695_, GenerationStep.Carving p_187696_) {
        // Carvers only work correctly in combination with NoiseBasedChunkGenerator so we keep this empty here.
    }

    @Override
    public void addDebugScreenInfo(List<String> list, BlockPos pos) {
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new DeepDarkChunkGenerator(getStructureSetRegistry(), getBiomeRegistry(), settings);
    }

    private int getHeightAt(int baseHeight, float verticalVariance, float horizontalVariance, int x, int z) {
        return (int) (baseHeight + Math.sin(x / horizontalVariance) * verticalVariance + Math.cos(z / horizontalVariance) * verticalVariance);
    }

    private record Settings(int baseHeight, float verticalVariance, float horizontalVariance) { }
}
