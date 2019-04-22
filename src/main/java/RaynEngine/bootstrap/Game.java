package RaynEngine.bootstrap;

import RaynEngine.core.render.engine.Loader;
import RaynEngine.core.render.engine.MasterRenderer;
import RaynEngine.core.render.engine.OBJLoader;
import RaynEngine.core.misc.toolbox.FileHandler;

/**
 * Created by Vtboy on 5/21/2016.
 */
public interface Game {
    void start();

    void render();

    void update();

    void stop();

    void setLoader(Loader loader);

    void setMasterRenderer(MasterRenderer masterRenderer);

    void setOBJLoader(OBJLoader objLoader);

    void setFileHandler(FileHandler fileHandler);
}
