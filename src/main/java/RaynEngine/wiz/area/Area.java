package RaynEngine.wiz.area;

import RaynEngine.core.entities.Camera;
import RaynEngine.core.entities.Light;
import RaynEngine.core.entities.Npc;
import RaynEngine.core.entities.Player;
import RaynEngine.core.misc.textures.ModelTexture;
import RaynEngine.core.models.Mesh;
import RaynEngine.core.models.TexturedModel;
import RaynEngine.core.render.engine.Loader;
import RaynEngine.core.render.engine.MasterRenderer;
import RaynEngine.core.render.engine.OBJLoader;
import org.lwjgl.util.vector.Vector3f;

import java.util.*;

/**
 * This class should store a list of npc entities, area id
 * it should be able to be loaded and unloaded like wizard101
 */
public abstract class Area {
    private ZONE zone;


    protected OBJLoader objloader;
    protected Loader loadTexture;
    public final List<Mesh> staticObjects = new ArrayList<Mesh>();
    public final List<Light> lights = new ArrayList<Light>();
    public final List<Npc> npcs = new ArrayList<Npc>();
    public final Camera camera;
    private MasterRenderer masterRenderer;

    public Area(ZONE zone, MasterRenderer renderer, Camera camera, OBJLoader Objloader, Loader textureLoader){
        this.zone = zone;
        this.camera = camera;
        this.masterRenderer = renderer;
        this.objloader = Objloader;
        this.loadTexture = textureLoader;
        registers();
    }

    protected abstract void registers();



    protected void createMesh(Vector3f location, Vector3f rotation, float scale, String model, String texture){
        ModelTexture textu = new ModelTexture(loadTexture.loadTexture(model));
        TexturedModel mod = new TexturedModel(objloader.loadRegularObj(texture), textu);
        mod.getTexture().setHasTransparency(false);
        mod.getTexture().setUseFakeLighting(true);
        staticObjects.add(new Mesh(location, rotation, scale, mod));
    }

    public ZONE getZone() {
        return zone;
    }

    public enum ZONE{
        COMMONS(0X1);

        private int id;

        ZONE(int id) {
            this.id = id;
        }

        public int ID(){
            return id;
        }


    }

}
