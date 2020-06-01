package com.hungteen.pvzmod.gui.guicontainer.plantcards.fight;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSpikeWeed extends GuiPlantBase{

	public GuiSpikeWeed(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.SPIKE_WEED);
		/*
		 * ���ṥ������·���Ľ�ʬ
��ʬ�������
���԰������ڳ��ӵ���
		 * it will attack all passing enemy
		 * Zombies will not eat it
Try to put it under the car
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.spike_weed1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.spike_weed2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.spike_weed3.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5||i==3) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.GREEN, RenderUtil.StringRenderType.NORMAL);
				}else{
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}

}
