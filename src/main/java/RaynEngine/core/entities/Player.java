package RaynEngine.core.entities;

import RaynEngine.core.models.Mesh;
import RaynEngine.core.models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import RaynEngine.core.render.engine.DisplayManager;
import RaynEngine.core.terrains.Terrain;

public class Player extends Entity {

    private static final float RUN_SPEED = 40;
    private static final float TURN_SPEED = 160;
    public static final float GRAVITY = -50;
    private static final float JUMP_POWER = 18;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardsSpeed = 0;
    private boolean isInAir = false;
    private boolean canMove = true;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
                  float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public Player(Mesh mesh, Vector3f position, float rotX, float rotY, float rotZ,
                  float scale) {
        super(mesh.getTexturedModel(), position, rotX, rotY, rotZ, scale);
    }

    public void move() {
        if (canMove) {
            checkInputs();
            super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
            float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
            float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
            float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
            super.increasePosition(dx, 0, dz);
            upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
            super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
            if (super.getPosition().y < 0) {
                upwardsSpeed = 0;
                isInAir = false;
                super.getPosition().y = 0;
            }

            System.out.println(getPosition());
        }
    }


    public void setCanMove(boolean b) {
        this.canMove = b;
    }

    private void jump() {
        if (!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
            isInAir = true;
        }
    }

    private void checkInputs() {

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            this.currentTurnSpeed = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            this.currentTurnSpeed = TURN_SPEED;
        } else {
            this.currentTurnSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }
    }

}
