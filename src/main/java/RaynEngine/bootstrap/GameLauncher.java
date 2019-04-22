package RaynEngine.bootstrap;

import org.lwjgl.opengl.Display;

import java.net.URISyntaxException;

import RaynEngine.core.render.fontRendering.TextMaster;
import RaynEngine.core.particles.ParticleMaster;
import RaynEngine.core.render.engine.DisplayManager;
import RaynEngine.core.render.engine.Loader;
import RaynEngine.core.render.engine.MasterRenderer;
import RaynEngine.core.render.engine.OBJLoader;
import RaynEngine.core.misc.toolbox.FileHandler;
import org.lwjgl.opengl.GL11;

/**
 * Created by Vtboy on 5/21/2016.
 */
public class GameLauncher {
    private Game game;
    private FileHandler fileHandler;

    public GameLauncher(Game game, FileHandler fileHandler) {
        this.game = game;
        this.fileHandler = fileHandler;
    }

    public void init() {
        game.setFileHandler(fileHandler);
        //GameLoader initialization
        try {
            fileHandler.loadNatives();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        game.setLoader(loader);
        TextMaster.init(loader);
        MasterRenderer renderer = new MasterRenderer(loader);
        game.setMasterRenderer(renderer);
        ParticleMaster.init(loader, renderer.getProjectionMatrix());
        OBJLoader objLoader = new OBJLoader(loader);
        game.setOBJLoader(objLoader);
        game.start();
        //
        while (!Display.isCloseRequested()) {
            game.update();
            game.render();
            DisplayManager.updateDisplay();
        }
        game.stop();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}

