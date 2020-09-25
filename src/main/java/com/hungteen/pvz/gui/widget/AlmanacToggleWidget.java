package com.hungteen.pvz.gui.widget;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.AlmanacSearchGui;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlmanacToggleWidget extends ToggleWidget {

	protected Almanacs.Categories category;
//	private float animationTime;
	
	public AlmanacToggleWidget(Almanacs.Categories category) {
		super(0, 0, 35, 26, false);
		this.category = category;
		this.initTextureValues(153, 2, 35, 0, AlmanacSearchGui.TEXTURE);
	}

	public void startAnimation(Minecraft mc) {
//		ClientRecipeBook clientrecipebook = mc.player.getRecipeBook();
//		List<RecipeList> list = clientrecipebook.getRecipes(this.category);
//		this.animationTime = 15.0F;
	}

	public void renderButton(int mouseX, int mouseY, float partialTicks) {
//		if (this.animationTime > 0.0F) {
//			float f = 1.0F + 0.1F * (float) Math.sin((double) (this.animationTime / 15.0F * (float) Math.PI));
//			
//			RenderSystem.translatef((float) (this.x + 8), (float) (this.y + 12), 0.0F);
//			RenderSystem.scalef(1.0F, f, 1.0F);
//			RenderSystem.translatef((float) (-(this.x + 8)), (float) (-(this.y + 12)), 0.0F);
//		}
        RenderSystem.pushMatrix();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(this.resourceLocation);
		RenderSystem.disableDepthTest();
		
		int posX = this.stateTriggered ? this.x - 2 : this.x;
		int posY = this.y;
		int texX = this.stateTriggered ? this.xTexStart + this.xDiffTex : this.xTexStart;
		int texY = this.stateTriggered ? this.yTexStart + this.yDiffTex : this.yTexStart;
		this.blit(posX, posY, texX, texY, this.width, this.height);
//		if(this.category == Almanacs.Categories.ALL) {
//			System.out.println(posX+","+posY);
//		}
		RenderSystem.enableDepthTest();
		this.renderIcon(minecraft.getItemRenderer());
		RenderSystem.popMatrix();
//		if (this.animationTime > 0.0F) {
//			
//			this.animationTime -= partialTicks;
//		}

	}

	private void renderIcon(ItemRenderer renderer) {
		int i = this.stateTriggered ? -2 : 0;
		renderer.renderItemAndEffectIntoGUI(getRenderItemStack(), this.x + 9 + i, this.y + 5);
	}
	
	private ItemStack getRenderItemStack() {
		switch (this.category) {
		case ALL: return new ItemStack(Items.COMPASS);
		case PLANTS: return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		case ZOMBIES: return new ItemStack(ItemRegister.BUCKET_HEAD.get());
		default:{
			PVZMod.LOGGER.debug("Category ERROR !");
			return ItemStack.EMPTY;
		}
		}
	}

	public Almanacs.Categories getCategory() {
		return this.category;
	}

//	public boolean func_199500_a(ClientRecipeBook p_199500_1_) {
//		List<RecipeList> list = p_199500_1_.getRecipes(this.category);
//		this.visible = false;
//		if (list != null) {
//			for (RecipeList recipelist : list) {
//				if (recipelist.isNotEmpty() && recipelist.containsValidRecipes()) {
//					this.visible = true;
//					break;
//				}
//			}
//		}
//
//		return this.visible;
//	}

}
