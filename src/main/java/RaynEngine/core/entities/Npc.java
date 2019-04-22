package RaynEngine.core.entities;

import RaynEngine.core.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public abstract class Npc extends Entity {
    public Npc(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public abstract void update();

    public abstract void interact();

}
