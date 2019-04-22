package RaynEngine.core.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import RaynEngine.core.render.engine.DisplayManager;

public class Camera {

    private float distanceFromPlayer = 35;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 20;
    private float yaw = 0;
    private float roll;
    private float MoveSpeed = 6f;
    private final float OriginalMoveSpeed = MoveSpeed;

    private float angle = 0;

    private Player player;

    private boolean freecamInput = false;

    private boolean MoveForward = false, MoveBackward = false, MoveLeft = false, MoveRight = false, MoveUp = false, MoveDown = false, SpeedMultipler = false;

    private boolean isFreecam = true;

    public Camera(Player player) {
        this.player = player;
    }

    public void move() {
            calculateZoom();
            calculatePitch();
            calculateAngleAroundPlayer();
            float horizontalDistance = calculateHorizontalDistance();
            float verticalDistance = calculateVerticalDistance();
            calculateCameraPosition(horizontalDistance, verticalDistance);
            this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
            yaw %= 360;
    }


    private void checkKeys() {
        MoveForward = (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP));
        MoveBackward = (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN));
        MoveRight = (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT));
        MoveLeft = (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT));
        MoveUp = (Keyboard.isKeyDown(Keyboard.KEY_SPACE));
        MoveDown = (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT));
        SpeedMultipler = (Keyboard.isKeyDown(Keyboard.KEY_TAB) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL));
    }


    public void shouldCheckFreeCamInput(boolean b) {
        this.freecamInput = b;
    }

    public boolean isFreecamInput() {
        return freecamInput;
    }

    public void invertPitch() {
        this.pitch = -pitch;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    private void calculateCameraPosition(float horizDistance, float verticDistance) {
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = player.getPosition().y + verticDistance + 4;
    }

    private float calculateHorizontalDistance() {
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch + 4)));
    }

    private float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch + 4)));
    }

    private void calculateZoom() {
        float zoomLevel = Mouse.getDWheel() * 0.03f;
        distanceFromPlayer -= zoomLevel;
        if (distanceFromPlayer < 5) {
            distanceFromPlayer = 5;
        }
    }

    private void calculatePitch() {
        if (Mouse.isButtonDown(1)) {
            float pitchChange = Mouse.getDY() * 0.2f;
            pitch -= pitchChange;
            if (pitch < 0) {
                pitch = 0;
            } else if (pitch > 90) {
                pitch = 90;
            }
        }
    }

    private void calculateAngleAroundPlayer() {
        if (Mouse.isButtonDown(0)) {
            float angleChange = Mouse.getDX() * 0.3f;
            angleAroundPlayer -= angleChange;
        }
    }
}
