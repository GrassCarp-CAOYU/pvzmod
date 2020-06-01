package com.hungteen.pvzmod.gui.guicontainer.plantcards.common;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.gui.guicontainer.plantcards.GuiPlantBase;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class GuiPeaShooter extends GuiPlantBase{

	public GuiPeaShooter(Container inventorySlotsIn) {
		super(inventorySlotsIn, Plants.PEA_SHOOTER);
		/*
		   ����һ��Զ�̹�����ֲ��
		   ÿ�ι�������һ���㶹
		   ֱ�ߵ���
		  It is a plant that attacks remotely
		  Fire one pea per attack
		  Linear trajectory
		 */
		this.plantInfo[1]=new TextComponentTranslation("text.pea_shooter1.name").getFormattedText();
		this.plantInfo[3]=new TextComponentTranslation("text.pea_shooter2.name").getFormattedText();
		this.plantInfo[5]=new TextComponentTranslation("text.pea_shooter3.name").getFormattedText();
	}	
	
}
