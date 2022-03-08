package com.hungteen.pvz.data;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * NO USE
 */
public class BlockModelGenerator extends BlockModelProvider{

	private Set<Block> addedBlocks = new HashSet<>();
	
	public BlockModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, PVZMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//special model generated by blockbench.
		addedBlocks.addAll(Arrays.asList(BlockRegister.CARD_FUSION_TABLE.get(), BlockRegister.CHOMPER.get(), BlockRegister.DIAMOND_SUNFLOWER_TROPHY.get(),
				BlockRegister.ESSENCE_ALTAR.get(), BlockRegister.FLOWER_POT.get(), BlockRegister.GOLD_SUNFLOWER_TROPHY.get(), BlockRegister.LANTERN.get(), 
				BlockRegister.LILY_PAD.get(), BlockRegister.SILVER_SUNFLOWER_TROPHY.get(), BlockRegister.STEEL_LADDER.get(), BlockRegister.SUN_CONVERTER.get()
				
				));
		//crop
		genCrop(BlockRegister.CABBAGE.get(), 3);
		genCrop(BlockRegister.CORN.get(), 6);
		genCrop(BlockRegister.PEA_PLANT.get(), 7);
		genCrop(BlockRegister.TOXIC_SHROOM.get(), 3);
		//up down side
		Arrays.asList(BlockRegister.FRAGMENT_SPLICE.get(), BlockRegister.SLOT_MACHINE.get(), BlockRegister.ARMA_ORE.get(), BlockRegister.TOXIC_ORE.get()
				).forEach(b -> {
				topSideBottom(b);
				});
		//up side
		Arrays.asList(BlockRegister.FROZEN_MELON.get()
				).forEach(b -> {
				topSide(b);
				});
		Arrays.asList(BlockRegister.NUT_SAPLING.get()
				).forEach(b -> {
				cross(b.getRegistryName().getPath(), StringUtil.prefix("block/" + b.getRegistryName().getPath()));
				this.addedBlocks.add(b);	
				});
		//last step for all normal block models.
		for(Block b : ForgeRegistries.BLOCKS) {
			if(b.getRegistryName().getNamespace().equals(PVZMod.MOD_ID) && ! addedBlocks.contains(b)) {
				fullCube(b);
			}
		}
	}
	
	private void fullCube(Block block) {
		cubeAll(block.getRegistryName().getPath(), StringUtil.prefix("block/" + block.getRegistryName().getPath()));
	}
	
	private void genCrop(Block block, int cnt) {
		for(int i = 0; i <= cnt; ++ i) {
			crop(block.getRegistryName().getPath() + "_" + i, StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_" + i));
		}
		this.addedBlocks.add(block);
	}
	
	private void topSideBottom(Block block) {
		cube(block.getRegistryName().getPath(), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_down"), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_top"), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_side"), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_side"), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_side"), 
			StringUtil.prefix("block/" + block.getRegistryName().getPath() + "_side")
		);
		this.addedBlocks.add(block);
	}
	
	private void topSide(Block b) {
		cubeColumn(b.getRegistryName().getPath(), StringUtil.prefix("block/" + b.getRegistryName().getPath() + "_side"), StringUtil.prefix("block/" + b.getRegistryName().getPath() + "_top"));
		this.addedBlocks.add(b);
	}
	
	@Override
	public String getName() {
		return "Plants vs Zombies block models";
	}

}
