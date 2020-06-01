package com.hungteen.pvzmod.render.layers;

import java.util.Random;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class LayerDanceLight implements LayerRenderer<EntityZombieBase>
{
    public void doRenderLayer(EntityZombieBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            RenderHelper.disableStandardItemLighting();
            float f = 0.2f;//((float)entity.ticksExisted + partialTicks) / 200.0F;
            float f1 = 0.0F;

            if (f > 0.8F)
            {
                f1 = (f - 0.8F) / 0.2F;
            }

            Random random = new Random(432L);
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.enableCull();
            GlStateManager.depthMask(false);
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 0F, 0F);

            for (int i = 0; (float)i < (f + f * f) / 2.0F * 60.0F; ++i)
            {
//                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
//                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
//                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
//                GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
//                GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
//                GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
                float f2 = random.nextFloat() * 5.0F + f1 * 10.0F;
                float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
                bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
                bufferbuilder.pos(0.0D, -20.0D, 0.0D).color(128,0,128, (int)(255.0F * (1.0F - f1))).endVertex();
                bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
                bufferbuilder.pos(0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
                bufferbuilder.pos(0.0D, (double)f2, (double)(1.0F * f3)).color(128,0,128, 0).endVertex();
                bufferbuilder.pos(-0.866D * (double)f3, (double)f2, (double)(-0.5F * f3)).color(128,0,128, 0).endVertex();
//                bufferbuilder.pos(-3, 10, 0).color(255, 255,0, 0).endVertex();
//                bufferbuilder.pos(3, 10, 0).color(255, 255,0, 0).endVertex();
//                bufferbuilder.pos(0, 10, 0).color(255, 255,0, 0).endVertex();
//                bufferbuilder.pos(0, 10, -3).color(255, 255,0, 0).endVertex();
//                bufferbuilder.pos(0, 10, 3).color(255, 255,0, 0).endVertex();
                tessellator.draw();
            }

            GlStateManager.popMatrix();
            GlStateManager.depthMask(true);
            GlStateManager.disableCull();
            GlStateManager.disableBlend();
            GlStateManager.shadeModel(7424);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableTexture2D();
            GlStateManager.enableAlpha();
            RenderHelper.enableStandardItemLighting();
        
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }
}