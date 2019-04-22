package RaynEngine.wiz;

import RaynEngine.core.particles.ParticleSystem;
import RaynEngine.core.particles.ParticleTexture;
import RaynEngine.core.render.engine.Loader;
import org.lwjgl.util.vector.Vector3f;

public class Effects {
    private ParticleSystem fireParitcles;

    public Effects(Loader loader){
        //Fire
        ParticleTexture fireTexture = new ParticleTexture(loader.loadTexture("fire"), 8);
        fireTexture.setAdditive(true);
        fireParitcles = new ParticleSystem(fireTexture, 400, 10, 0.1f, 2, 5f);
        fireParitcles.setDirection(new Vector3f(0, 2, 0), 0.1f);
        fireParitcles.setLifeError(0.2f);
        fireParitcles.setSpeedError(0.6f);
        fireParitcles.setScaleError(1f);
        fireParitcles.randomizeRotation();
    }

    private void renderFire(Vector3f position){
        fireParitcles.generateParticles(position);
    }
}
