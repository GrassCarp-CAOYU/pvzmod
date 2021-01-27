package com.hungteen.pvz.register;

import java.util.Map;

import com.hungteen.pvz.model.baked.BowlingGloveBakedModel;
import com.hungteen.pvz.particle.BlueFlameParticle;
import com.hungteen.pvz.particle.DirtBurstOutParticle;
import com.hungteen.pvz.particle.DoomParticle;
import com.hungteen.pvz.particle.FumeParticle;
import com.hungteen.pvz.particle.SleepParticle;
import com.hungteen.pvz.particle.SnowFlowerParticle;
import com.hungteen.pvz.particle.SporeParticle;
import com.hungteen.pvz.particle.YellowFlameParticle;
import com.hungteen.pvz.particle.bomb.CherryBombParticle;
import com.hungteen.pvz.particle.bomb.PotatoMineParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegister {

	@SubscribeEvent
	public static void onModelBaked(ModelBakeEvent ev) {
		Map<ResourceLocation, IBakedModel> modelRegistry = ev.getModelRegistry();
		ModelResourceLocation location = new ModelResourceLocation(ItemRegister.BOWLING_GLOVE.get().getRegistryName(), "inventory");
		IBakedModel model = modelRegistry.get(location);
		if(model == null) {
			throw new RuntimeException("Did not find Obsidian Hidden in registry");
		} else if(model instanceof BowlingGloveBakedModel) {
			throw new RuntimeException("Tried to replaceObsidian Hidden twice");
		} else {
			BowlingGloveBakedModel tmp = new BowlingGloveBakedModel(model);
			modelRegistry.put(location, tmp);
		}
	}
	
	@SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
		@SuppressWarnings("resource")
		ParticleManager manager = Minecraft.getInstance().particles;
        manager.registerFactory(ParticleRegister.RED_BOMB.get(), (sprite) -> {return new CherryBombParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.YELLOW_BOMB.get(), (sprite) -> {return new PotatoMineParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.DIRT_BURST_OUT.get(), (sprite) -> {return new DirtBurstOutParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.YELLOW_FLAME.get(), (sprite) -> {return new YellowFlameParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.BLUE_FLAME.get(), (sprite) -> {return new BlueFlameParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.SLEEP.get(), (sprite) -> {return new SleepParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.SPORE.get(), (sprite) -> {return new SporeParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.FUME.get(), (sprite) -> {return new FumeParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.SNOW_FLOWER.get(), (sprite) -> {return new SnowFlowerParticle.Factory(sprite);});
        manager.registerFactory(ParticleRegister.DOOM.get(), (sprite) -> {return new DoomParticle.Factory(sprite);});
	}
	
	@SubscribeEvent
	public static void reigsterRenderType(FMLClientSetupEvent ev){
		RenderTypeLookup.setRenderLayer(BlockRegister.ORIGIN_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegister.PEA_PLANT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.NUT_LEAVES.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.NUT_SAPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.TOXIC_SHROOM.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.LANTERN.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegister.FLOWER_POT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.CABBAGE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.BUTTER_BLOCK.get(), RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(BlockRegister.CORN.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockRegister.ESSENCE_ALTAR.get(), RenderType.getTranslucent());
		TileEntityRegister.bindRenderers(ev);
	}
	
}
