package com.hungteen.pvzmod.gui.guicontainer.plantcards.flame;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiTorchWood extends GuiPlantBase{

	public GuiTorchWood(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.TORCH_WOOD);
		/*
		 * �����Ⱦ������㶹
���㶹��ɻ��㶹
���㶹���˺��ӱ�
���㶹Ҳ���ɻ��㶹
It heats the passing peas
Turn peas into fire peas
Fire Pea damage doubled
Ice pea too
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.torch_wood1.name").getFormattedText();
		this.plantInfo[2]=new TextComponentTranslation("text.torch_wood2.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.torch_wood3.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.torch_wood4.name").getFormattedText();
	}

	@Override
	protected void drawPlantInfo()
	{
		int x=this.textLength/2+this.textOffsetX;
		int y=this.textOffsetY;
		for(int i=0;i<6;i++) {
			if(!plantInfo[i].isEmpty()) {
				if(i==5) {
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.RED, RenderUtil.StringRenderType.NORMAL);
				}else{
					RenderUtil.drawCenteredScaledString(mc.fontRenderer, plantInfo[i], x, y+10*i,1f, Colors.BLACK, RenderUtil.StringRenderType.NORMAL);
				}
			}
		}
	}
}
