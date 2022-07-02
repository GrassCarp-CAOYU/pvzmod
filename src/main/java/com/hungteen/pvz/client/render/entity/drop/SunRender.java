package com.hungteen.pvz.client.render.entity.drop;

import com.hungteen.pvz.client.model.PVZModelLayers;
import com.hungteen.pvz.client.model.entity.misc.DropEntityModel;
import com.hungteen.pvz.common.entity.drop.PVZDrop;
import com.hungteen.pvz.common.entity.drop.Sun;
import com.hungteen.pvz.utils.Util;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 11:44
 **/
public class SunRender extends DropEntityRender<Sun> {

    private static final ResourceLocation YELLOW = Util.prefix("textures/entity/drop/yellow_sun.png");
    private static final ResourceLocation RED = Util.prefix("textures/entity/drop/red_sun.png");

    public SunRender(EntityRendererProvider.Context context) {
        super(context, new DropEntityModel<>(context.bakeLayer(PVZModelLayers.SUN)));
    }

    @Override
    protected float getScaleByEntity(Sun entity) {
        return (float) (Math.pow(entity.getAmount() / 25, 0.5) * 0.1f + 0.8f);
    }

    @Override
    public ResourceLocation getTextureLocation(Sun entity) {
        return entity.getDropState() == PVZDrop.DropStates.STEAL ? RED : YELLOW;
    }
}
