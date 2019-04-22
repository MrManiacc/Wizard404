package RaynEngine.wiz;

import RaynEngine.bootstrap.Game;
import RaynEngine.bootstrap.GameLoader;
import RaynEngine.core.entities.Camera;
import RaynEngine.core.entities.Light;
import RaynEngine.core.entities.Player;
import RaynEngine.core.misc.textures.ModelTexture;
import RaynEngine.core.models.RawModel;
import RaynEngine.core.models.TexturedModel;
import RaynEngine.core.render.engine.Loader;
import RaynEngine.core.render.engine.MasterRenderer;
import RaynEngine.core.render.engine.OBJLoader;
import RaynEngine.core.misc.toolbox.FileHandler;
import RaynEngine.wiz.area.Area;
import RaynEngine.wiz.area.areas.AreaCommons;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vtboy on 5/21/2016.
 */
public class Wizard101Game implements Game {

    protected Loader loader;
    protected OBJLoader OBJloader;
    protected MasterRenderer masterRenderer;
    protected FileHandler fileHandler;
    private Camera camera;
    private Player player;
    private String playerModel = "chest";
    private AreaCommons commons;

    @Override
    public void start() {
        TexturedModel playerModel = new TexturedModel(OBJloader.loadRegularObj(this.playerModel), new ModelTexture(loader.loadTexture(this.playerModel)));
        player = new Player(playerModel, new Vector3f(0, 0, 0), 0, 100, 0, 0.6f);
        camera = new Camera(player);
        commons = new AreaCommons(camera, masterRenderer, OBJloader, loader);
    }

    @Override
    public void render() {
        player.move();
        camera.move();
        masterRenderer.renderArea(commons, player);
    }

    @Override
    public void update() {
        handleInput();

    }

    private void handleInput() {
        while (Keyboard.next()) {
            //FreeCam input
            if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
                Mouse.setGrabbed(!Mouse.isGrabbed());
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void setMasterRenderer(MasterRenderer masterRenderer) {
        this.masterRenderer = masterRenderer;
    }

    @Override
    public void setOBJLoader(OBJLoader objLoader) {
        this.OBJloader = objLoader;
    }

    @Override
    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public static void main(String[] args) {
        GameLoader.initialize(args, new Wizard101Game());
    }
}
