package thebetweenlands.common.world.gen.biome.decorator;

import net.minecraft.world.biome.Biome;

public class BiomeDecoratorSludgePlains extends BiomeDecoratorBetweenlands {
	public BiomeDecoratorSludgePlains(Biome biome) {
		super(biome);
	}

	@Override
	public void decorate() {
		super.decorate();

		this.startProfilerSection("mudStructures");
		this.generate(5, DecorationHelper::generateMudStructures);
		this.endProfilerSection();

		this.startProfilerSection("deadWeedwoodTree");
		this.generate(4, DecorationHelper::generateRottenWeedwoodTree);
		this.endProfilerSection();

		this.startProfilerSection("tarPoolSurface");
		this.generate(60, DecorationHelper::generateTarPoolSurface);
		this.endProfilerSection();

		this.startProfilerSection("spawnerStructure");
		this.generate(0.25F, DecorationHelper::generateSpawnerStructure);
		this.endProfilerSection();

		this.startProfilerSection("smallHollowLog");
		this.generate(15, DecorationHelper::generateSmallHollowLog);
		this.endProfilerSection();

		this.startProfilerSection("sludgecrepCluster");
		this.generate(50, DecorationHelper::generateSludgecreepCluster);
		this.endProfilerSection();

		this.startProfilerSection("deadWeedwoodBushCluster");
		this.generate(10, DecorationHelper::generateDeadWeedwoodBushCluster);
		this.endProfilerSection();

		this.startProfilerSection("rootsCluster");
		this.generate(0.8F, DecorationHelper::generateRootsCluster);
		this.endProfilerSection();
	}
}
