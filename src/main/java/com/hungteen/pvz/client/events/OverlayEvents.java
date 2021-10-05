package com.hungteen.pvz.client.events;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.client.events.handler.PVZOverlayHandler;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.world.invasion.FogManager;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class OverlayEvents {

	private static Minecraft mc = Minecraft.getInstance();

	@SubscribeEvent
	public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || ! canRender() || mc.options.hideGui) {
			return;
		}
		/* no opened gui */
		if (mc.screen == null) {
			PVZOverlayHandler.renderResources(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getScreenHeight());
//			if(PVZInputEvents.ShowOverlay) {
//				if(PlayerUtil.isPlayerSurvival(mc.player)  && PVZConfig.CLIENT_CONFIG.InvasionRender.RenderInvasionProgress.get()) {
//					PVZOverlayHandler.renderInvasionProgress(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//				}
//			}
//			if(mc.player.getVehicle() instanceof CobCannonEntity) {
//				CobCannonEntity cob = (CobCannonEntity) mc.player.getVehicle();
//				if(cob.getCornNum() > 0) {
//					PVZOverlayHandler.renderTargetAim(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
//				}
//			}
			PVZOverlayHandler.drawCardInventory(mc.player, ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
		}
	}

	@SubscribeEvent
	public static void onRenderFog(RenderGameOverlayEvent.Pre ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || mc.player == null || mc.player.isSpectator()) {
			return;
		}
//		if (PVZConfig.CLIENT_CONFIG.EnvironmentRender.RenderFog.get()) {
//			int tick = ClientPlayerResources.getPlayerStats(Resources.NO_FOG_TICK);
//			if(tick < 0) {
//				PVZOverlayHandler.renderFog(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight(), Math.min(- tick * 1F / FogManager.CD, 1F));
//			}
//		}
	}
	
	private static boolean canRender() {
		return mc.player != null && ! mc.player.isSpectator();
	}
	
}
