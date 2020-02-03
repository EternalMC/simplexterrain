package supercoder79.simplexterrain.world.feature.smallvegetation;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import supercoder79.simplexterrain.api.feature.SimplexFeature;

import java.util.*;

public class MoreGenericSpruceTreeFeature extends SimplexFeature {
	private BlockState logState;
	private BlockState leafState;
	private Biome biome;

	public MoreGenericSpruceTreeFeature(BlockState logState, BlockState leafState, Biome biome) {
		this.logState = logState;
		this.leafState = leafState;
		this.biome = biome;
	}

	@Override
	public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos, DefaultFeatureConfig config) {
		if (world.getBlockState(pos.down()) != Blocks.GRASS_BLOCK.getDefaultState()) return true;

		int height = 5;

		for (int i = 0; i < height; i++) {
			world.setBlockState(pos.add(0, i, 0), logState, 2);
		}

		for (int i = height+1; i > 1; i--) {
			world.setBlockState(pos.add(-1, i-1, 0), leafState, 2);
			world.setBlockState(pos.add(1, i-1, 0), leafState, 2);
			world.setBlockState(pos.add(0, i-1, -1), leafState, 2);
			world.setBlockState(pos.add(0, i-1, 1), leafState, 2);
		}
		for (int i = 3; i > 2; i--) {
			world.setBlockState(pos.add(-1, i-1, -1), leafState, 2);
			world.setBlockState(pos.add(-1, i-1, 1), leafState, 2);
			world.setBlockState(pos.add(1, i-1, -1), leafState, 2);
			world.setBlockState(pos.add(1, i-1, 1), leafState, 2);
		}
		// outer leaves
		world.setBlockState(pos.add(-2,2, -1), leafState, 2);
		world.setBlockState(pos.add(-2,2, 0), leafState, 2);
		world.setBlockState(pos.add(-2,2, 1), leafState, 2);

		world.setBlockState(pos.add(2, 2, -1), leafState, 2);
		world.setBlockState(pos.add(2, 2, 0), leafState, 2);
		world.setBlockState(pos.add(2, 2, 1), leafState, 2);

		world.setBlockState(pos.add(-1, 2, -2), leafState, 2);
		world.setBlockState(pos.add(0, 2, -2), leafState, 2);
		world.setBlockState(pos.add(1, 2, -2), leafState, 2);

		world.setBlockState(pos.add(-1, 2, 2), leafState, 2);
		world.setBlockState(pos.add(0, 2, 2), leafState, 2);
		world.setBlockState(pos.add(1, 2, 2), leafState, 2);


		world.setBlockState(pos.add(0, height+1, 0), leafState, 2);

		return true;
	}

	@Override
	public Set<Biome> generatesIn() {
		return new HashSet<>(Collections.singletonList(biome));
	}

	@Override
	public ConfiguredDecorator configureDecorator() {
		return Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.35f, 1));
	}

	@Override
	public GenerationStep.Feature generationStep() {
		return GenerationStep.Feature.VEGETAL_DECORATION;
	}
}