package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.inventory.Container;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiSplitPea extends GuiPlantBase{

	public GuiSplitPea(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.SPLIT_PEA);
		/*
		 * ����ǰ�����㶹
��˭Ҳ��֪��Ϊʲô
����һ�η���2���㶹
��ǰ��ֻ����1���㶹
It will fire peas back and forth
But no one knows why
It fire 2 peas back at a time
But only fire 1 pea in front
		 */
		this.plantInfo[0]=new TextComponentTranslation("text.split_pea1.name").getFormattedText();
		this.plantInfo[2]=new TextComponentTranslation("text.split_pea2.name").getFormattedText();
		this.plantInfo[4]=new TextComponentTranslation("text.split_pea3.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.split_pea4.name").getFormattedText();
	}

}
