package RaynEngine.core.models;

import org.lwjgl.util.vector.Vector3f;

public class Mesh {
    private Vector3f position;
    private Vector3f rotation;
    private float scale;
    private boolean doesSway = false;
    private TexturedModel texturedModel;

    public Mesh(Vector3f position, Vector3f rotation, float scale, TexturedModel texturedModel) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.texturedModel = texturedModel;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setSway(boolean sway) {
        this.doesSway = sway;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isSway() {
        return doesSway;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public TexturedModel getTexturedModel() {
        return texturedModel;
    }

    public void setTexturedModel(TexturedModel texturedModel) {
        this.texturedModel = texturedModel;
    }
}
