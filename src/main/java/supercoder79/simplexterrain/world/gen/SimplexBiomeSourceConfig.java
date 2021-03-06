package supercoder79.simplexterrain.world.gen;

import net.minecraft.world.biome.source.BiomeSourceConfig;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;
import net.minecraft.world.level.LevelProperties;

public class SimplexBiomeSourceConfig implements BiomeSourceConfig {
	private final long seed;
	private final LevelGeneratorType generatorType;
	private OverworldChunkGeneratorConfig generatorSettings = new OverworldChunkGeneratorConfig();

	public SimplexBiomeSourceConfig(Object levelProperties) {
		this.seed = ((LevelProperties)(levelProperties)).getSeed();
		this.generatorType = ((LevelProperties)(levelProperties)).getGeneratorType();
	}

	public SimplexBiomeSourceConfig setGeneratorSettings(OverworldChunkGeneratorConfig overworldChunkGeneratorConfig) {
		this.generatorSettings = overworldChunkGeneratorConfig;
		return this;
	}

	public OverworldChunkGeneratorConfig getGeneratorSettings() {
		return this.generatorSettings;
	}

	public long getSeed() {
		return seed;
	}

	public LevelGeneratorType getGeneratorType() {
		return generatorType;
	}
}
