package com.hungteen.pvz.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

public class SleepParticle extends PVZNormalParticle{

	public SleepParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);
		this.particleScale = 0.3f;
		this.maxAge = this.rand.nextInt(10) + 10;
		this.canCollide = false;
		this.particleGravity = 0f;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {

		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite sprite) {
			this.sprite = sprite;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			SleepParticle particle = new SleepParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.selectSpriteRandomly(this.sprite);
			return particle;
		}

		@SuppressWarnings("unused")
		private Factory() {
			throw new UnsupportedOperationException("Use the Factory(IAnimatedSprite sprite) constructor");
		}
	}
	
}
