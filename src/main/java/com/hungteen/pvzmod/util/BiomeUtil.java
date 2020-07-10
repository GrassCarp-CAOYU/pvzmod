package com.hungteen.pvzmod.util;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

public class BiomeUtil {

	public static final Biome[] allBiomes = {
			Biomes.DEFAULT, 
			//ƽԭ
			Biomes.PLAINS, 
			Biomes.MUTATED_PLAINS,
			//ɽ��
			Biomes.EXTREME_HILLS,
			Biomes.EXTREME_HILLS_EDGE,
			Biomes.EXTREME_HILLS_WITH_TREES,
			Biomes.MUTATED_EXTREME_HILLS,
		    Biomes.MUTATED_EXTREME_HILLS_WITH_TREES,
			//ɭ��
			Biomes.FOREST, 
			Biomes.FOREST_HILLS,
			Biomes.BIRCH_FOREST,
			Biomes.BIRCH_FOREST_HILLS,
			Biomes.ROOFED_FOREST,
			Biomes.MUTATED_BIRCH_FOREST,
			Biomes.MUTATED_BIRCH_FOREST_HILLS,
			Biomes.MUTATED_ROOFED_FOREST,
			Biomes.MUTATED_FOREST,
			//����
			Biomes.SWAMPLAND, 
			Biomes.MUTATED_SWAMPLAND,
			//����
			Biomes.RIVER, 
			//�
			Biomes.DEEP_OCEAN,
			Biomes.STONE_BEACH,
		    Biomes.COLD_BEACH,
			//����
			Biomes.HELL,
			//���
			//Biomes.SKY
			//����ѩ�����
			Biomes.FROZEN_OCEAN,
			Biomes.FROZEN_RIVER, 
			Biomes.ICE_PLAINS, 
			Biomes.ICE_MOUNTAINS, 
			Biomes.MUTATED_ICE_FLATS,
			//Ģ�������
			Biomes.MUSHROOM_ISLAND,
			Biomes.MUSHROOM_ISLAND_SHORE,
			//ɳĮ���
			Biomes.DESERT,
			Biomes.BEACH,
		    Biomes.DESERT_HILLS,
		    Biomes.MUTATED_DESERT,
		    //�������
		    Biomes.JUNGLE,
		    Biomes.JUNGLE_HILLS,
		    Biomes.JUNGLE_EDGE,
		    Biomes.MUTATED_JUNGLE,
		    Biomes.MUTATED_JUNGLE_EDGE,
		    //��ɼ�����
		    Biomes.TAIGA, 
		    Biomes.COLD_TAIGA,
		    Biomes.COLD_TAIGA_HILLS,
		    Biomes.MUTATED_TAIGA_COLD,
		    Biomes.TAIGA_HILLS,
		    Biomes.MUTATED_TAIGA,
		    //�޴���ɼ��
		    Biomes.REDWOOD_TAIGA,
		    Biomes.REDWOOD_TAIGA_HILLS,
		    Biomes.MUTATED_REDWOOD_TAIGA,
		    Biomes.MUTATED_REDWOOD_TAIGA_HILLS,
		    //��������ԭ���
		    Biomes.SAVANNA,
		    Biomes.SAVANNA_PLATEAU,
		    Biomes.MUTATED_SAVANNA,
		    Biomes.MUTATED_SAVANNA_ROCK,
		    //���ɽ
		    Biomes.MESA,
		    Biomes.MESA_ROCK,
		    Biomes.MESA_CLEAR_ROCK,
		    Biomes.MUTATED_MESA,
		    Biomes.MUTATED_MESA_ROCK,
		    Biomes.MUTATED_MESA_CLEAR_ROCK,
		    //��գ�
		    //Biomes.VOID,
	};
	
	public static final Biome[] overworldLand = { 
			Biomes.DEFAULT, 
			//ƽԭ
			Biomes.PLAINS, 
			Biomes.MUTATED_PLAINS,
			//ɽ��
			Biomes.EXTREME_HILLS,
			Biomes.EXTREME_HILLS_EDGE,
			Biomes.EXTREME_HILLS_WITH_TREES,
			Biomes.MUTATED_EXTREME_HILLS,
		    Biomes.MUTATED_EXTREME_HILLS_WITH_TREES,
			//ɭ��
			Biomes.FOREST, 
			Biomes.FOREST_HILLS,
			Biomes.BIRCH_FOREST,
			Biomes.BIRCH_FOREST_HILLS,
			Biomes.ROOFED_FOREST,
			Biomes.MUTATED_BIRCH_FOREST,
			Biomes.MUTATED_BIRCH_FOREST_HILLS,
			Biomes.MUTATED_ROOFED_FOREST,
			Biomes.MUTATED_FOREST,
			//����
			Biomes.SWAMPLAND, 
			Biomes.MUTATED_SWAMPLAND,
//			//����
//			Biomes.RIVER, 
//			//�
//			Biomes.DEEP_OCEAN,
			//ɳ̲
			Biomes.STONE_BEACH,
		    Biomes.COLD_BEACH,
			//����
//			Biomes.HELL,
			//���
			//Biomes.SKY
			//����ѩ�����
			Biomes.FROZEN_OCEAN,
			Biomes.FROZEN_RIVER, 
			Biomes.ICE_PLAINS, 
			Biomes.ICE_MOUNTAINS, 
			Biomes.MUTATED_ICE_FLATS,
			//Ģ�������
			Biomes.MUSHROOM_ISLAND,
			Biomes.MUSHROOM_ISLAND_SHORE,
			//ɳĮ���
			Biomes.DESERT,
			Biomes.BEACH,
		    Biomes.DESERT_HILLS,
		    Biomes.MUTATED_DESERT,
		    //�������
		    Biomes.JUNGLE,
		    Biomes.JUNGLE_HILLS,
		    Biomes.JUNGLE_EDGE,
		    Biomes.MUTATED_JUNGLE,
		    Biomes.MUTATED_JUNGLE_EDGE,
		    //��ɼ�����
		    Biomes.TAIGA, 
		    Biomes.COLD_TAIGA,
		    Biomes.COLD_TAIGA_HILLS,
		    Biomes.MUTATED_TAIGA_COLD,
		    Biomes.TAIGA_HILLS,
		    Biomes.MUTATED_TAIGA,
		    //�޴���ɼ��
		    Biomes.REDWOOD_TAIGA,
		    Biomes.REDWOOD_TAIGA_HILLS,
		    Biomes.MUTATED_REDWOOD_TAIGA,
		    Biomes.MUTATED_REDWOOD_TAIGA_HILLS,
		    //��������ԭ���
		    Biomes.SAVANNA,
		    Biomes.SAVANNA_PLATEAU,
		    Biomes.MUTATED_SAVANNA,
		    Biomes.MUTATED_SAVANNA_ROCK,
		    //���ɽ
		    Biomes.MESA,
		    Biomes.MESA_ROCK,
		    Biomes.MESA_CLEAR_ROCK,
		    Biomes.MUTATED_MESA,
		    Biomes.MUTATED_MESA_ROCK,
		    Biomes.MUTATED_MESA_CLEAR_ROCK,
		    //��գ�
		    //Biomes.VOID,
	};
	
	public static final Biome[] plain = new Biome[] {
			Biomes.PLAINS, 
			Biomes.MUTATED_PLAINS,
			Biomes.DESERT,
			Biomes.ICE_PLAINS, 
	};
	
	public static final Biome[] snowLand = new Biome[] {
			Biomes.FROZEN_OCEAN,
			Biomes.FROZEN_RIVER, 
			Biomes.ICE_PLAINS, 
			Biomes.ICE_MOUNTAINS, 
			Biomes.MUTATED_ICE_FLATS,
	};
	
	public static final Biome[] sea = new Biome[] {
			Biomes.DEEP_OCEAN,
	};
	
	public static final Biome[] hell = new Biome[] {
			Biomes.HELL,
	};
	
	public static final Biome[] desert = new Biome[] {
			Biomes.DESERT,
			Biomes.BEACH,
		    Biomes.DESERT_HILLS,
		    Biomes.MUTATED_DESERT
	};
	
	public static final Biome[] mesa = new Biome[] {
			Biomes.MESA,
		    Biomes.MESA_ROCK,
		    Biomes.MESA_CLEAR_ROCK,
		    Biomes.MUTATED_MESA,
		    Biomes.MUTATED_MESA_ROCK,
		    Biomes.MUTATED_MESA_CLEAR_ROCK,
	};
	
	public static final Biome[] mountain = new Biome[] {
			Biomes.EXTREME_HILLS,
			Biomes.EXTREME_HILLS_EDGE,
			Biomes.EXTREME_HILLS_WITH_TREES,
			Biomes.MUTATED_EXTREME_HILLS,
		    Biomes.MUTATED_EXTREME_HILLS_WITH_TREES,
			Biomes.ICE_MOUNTAINS, 
	};
	
	public static final Biome[] swampland = new Biome[]{
			Biomes.SWAMPLAND, 
			Biomes.MUTATED_SWAMPLAND,
	};
	
	public static final Biome[] sky = new Biome[] {
			Biomes.SKY,
	};
}
