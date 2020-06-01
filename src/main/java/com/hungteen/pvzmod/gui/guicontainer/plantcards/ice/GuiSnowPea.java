package com.hungteen.pvzmod.gui.guicontainer.plantcards.ice;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSnowPea extends GuiPlantBase{

	public GuiSnowPea(Container inventorySlotsIn) {
		super(inventorySlotsIn,Plants.SNOW_PEA);
		/*
		 ���к���Ч�����㶹����
               ÿ�η���һ�������㶹
               ʹ��ʬ�����ƶ�
		  Pea shooter with ice effect
           Fire one frozen pea at a time
           Make zombies difficult to move
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.snow_pea1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.snow_pea2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.snow_pea3.name").getFormattedText();
	}
}
