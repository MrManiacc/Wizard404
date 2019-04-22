package RaynEngine.core.render.fontRendering;

import RaynEngine.bootstrap.GameLoader;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import RaynEngine.core.render.shaders.ShaderProgram;

public class FontShader extends ShaderProgram {

    private static final String VERTEX_FILE = GameLoader.fileHandler.getShader("fontVertex");
    private static final String FRAGMENT_FILE = GameLoader.fileHandler.getShader("fontFragment");

    private int location_colour;
    private int location_translation;

    private int location_width;
    private int location_edge;
    private int location_borderWidth;
    private int location_borderEdge;
    private int location_offset;
    private int location_outlineColor;

    public FontShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_colour = super.getUniformLocation("colour");
        location_translation = super.getUniformLocation("translation");
        location_width = super.getUniformLocation("width");
        location_edge = super.getUniformLocation("edge");
        location_borderWidth = super.getUniformLocation("borderWidth");
        location_borderEdge = super.getUniformLocation("borderEdge");
        location_offset = super.getUniformLocation("offset");
        location_outlineColor = super.getUniformLocation("outlineColor");

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    protected void loadColour(Vector3f colour) {
        super.loadVector(location_colour, colour);
    }

    protected void loadTranslation(Vector2f translation) {
        super.load2DVector(location_translation, translation);
    }

    protected void loadEffects(float width, float edge, float borderWidth, float borderEdge, Vector2f offset, Vector3f outlineColor) {
        super.loadFloat(location_width, width);
        super.loadFloat(location_edge, edge);
        super.loadFloat(location_borderWidth, borderWidth);
        super.loadFloat(location_borderEdge, borderEdge);
        super.load2DVector(location_offset, offset);
        super.loadVector(location_outlineColor, outlineColor);
    }


}
