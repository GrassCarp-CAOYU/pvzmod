package com.hungteen.pvzmod.gui.guicontainer.plantcards.electricity;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiLightningRod extends GuiPlantBase{

	public GuiLightningRod(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.LIGHTLING_ROD);
		/*
		 * ��֮�������������
ÿ�ι���ʱ�����ɸÿ�
Producer of ElectricityOre
generate ore when attacking
		 */
		this.plantInfo[2]=new TextComponentTranslation("text.lightning_rod1.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.lightning_rod2.name").getFormattedText();
	}
}
