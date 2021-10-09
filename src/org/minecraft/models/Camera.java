package org.minecraft.models;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private static final float SPEED = 0.15f;
    private static final float SENSITIVITY = 0.1f;

    private Vector3f position;
    private Vector3f rotation;

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void move() {

        float x = (float) Math.sin(-Math.toRadians(rotation.getY())) * SPEED;
        float z = (float) Math.cos(-Math.toRadians(rotation.getY())) * SPEED;

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0, x),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) position = Vector3f.add(position, new Vector3f(z, 0, -x),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) position = Vector3f.add(position, new Vector3f(x, 0, z),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, SPEED, 0),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) position = Vector3f.add(position, new Vector3f(0, -SPEED, 0),position);

        float dx = Mouse.getDX();
        float dy = -Mouse.getDY();

        rotation = Vector3f.add(rotation, new Vector3f(dy * SENSITIVITY, dx * SENSITIVITY, 0),rotation);

    }

    public Vector3f getPosition() {
        return position;
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
}
