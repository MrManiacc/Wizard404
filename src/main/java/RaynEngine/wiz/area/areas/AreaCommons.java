package RaynEngine.wiz.area.areas;

import RaynEngine.core.entities.Camera;
import RaynEngine.core.entities.Light;
import RaynEngine.core.models.Mesh;
import RaynEngine.core.render.engine.Loader;
import RaynEngine.core.render.engine.MasterRenderer;
import RaynEngine.core.render.engine.OBJLoader;
import RaynEngine.wiz.area.Area;
import org.lwjgl.util.vector.Vector3f;

public class AreaCommons extends Area {

    public AreaCommons(Camera camera, MasterRenderer masterRenderer, OBJLoader objLoader, Loader loader) {
        super(ZONE.COMMONS, masterRenderer, camera, objLoader, loader);
    }

    @Override
    protected void registers() {
       // createMesh(new Vector3f(0,0,0), new Vector3f(0,0,0), "pine", "pine");
        createMesh(new Vector3f(0,0,0), new Vector3f(0,0,0), 1f,"stand", "stand");
//        createMesh(new Vector3f(0,0,0), new Vector3f(0,0,0), 7.5f,"wall", "wall");
       // createMesh(new Vector3f(20,0,0), new Vector3f(0,0,0), "lamp", "lamp");

        /* Light related */
        Light sun = new Light(new Vector3f(10000, 10000, -10000), new Vector3f(1.3f, 1.3f, 1.3f));
        lights.add(sun);
    }

}
