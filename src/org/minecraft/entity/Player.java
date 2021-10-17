package org.minecraft.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.DisplayManager;

public final class Player extends Entity implements Damageable {

    private static final float SPEED = 0.15f;
    private static final float SENSITIVITY = 0.1f;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;

    private Vector3f position;
    private Vector3f rotation;
    private float upwardsSpeed;

    private boolean isInAir = false;

    public Player(EntityModel model, Vector3f position, Vector3f rotation, Vector3f scale) {
        super(model, position, rotation, scale);
    }

    public void move() {
        checkInputs();
        upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        position.y += upwardsSpeed * DisplayManager.getFrameTimeSeconds();
        super.setPosition(position);
        super.setRotation(position);
    }

    private void jump() {
        if (!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
            isInAir = true;
        }
    }

    private void checkInputs() {
        float x = (float) Math.sin(-Math.toRadians(rotation.getY())) * SPEED;
        float z = (float) Math.cos(-Math.toRadians(rotation.getY())) * SPEED;

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0, x),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) position = Vector3f.add(position, new Vector3f(z, 0, -x),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) position = Vector3f.add(position, new Vector3f(x, 0, z),position);
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) jump();
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) position = Vector3f.add(position, new Vector3f(0, -SPEED, 0),position);

        float dx = Mouse.getDX();
        float dy = -Mouse.getDY();

        rotation = Vector3f.add(rotation, new Vector3f(dy * SENSITIVITY, dx * SENSITIVITY, 0),rotation);
    }

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     * @author 4347
     */
    @Override
    public void damage(double amount) {

    }

    /**
     * Gets the entity's health from 0 to {@link #getMaxHealth() getMaxHealth()}, where 0 is dead.
     *
     * @return Health represented from 0 to max
     * @author 4347
     */
    @Override
    public double getHealth() {
        return 0;
    }

    /**
     * Sets the entity's health from 0 to getMaxHealth(), where 0 is dead.
     *
     * @param health New health represented from 0 to max
     * @throws IllegalArgumentException Thrown if the health is < 0 or > {@link #getMaxHealth()}
     * @author 4347
     */
    @Override
    public void setHealth(double health) throws IllegalArgumentException {

    }

    /**
     * Gets the maximum health this entity has.
     *
     * @return Maximum health
     * @author 4347
     */
    @Override
    public double getMaxHealth() {
        return 0;
    }

    /**
     * Sets the maximum health this entity can have.
     * <p>
     * If the health of the entity is above the value provided it will be set to that value.
     *
     * @param health amount of health to set the maximum to
     * @author 4347
     */
    @Override
    public void setMaxHealth(double health) {

    }

    /**
     * Resets the max health to the original amount.
     *
     * @author 4347
     */
    @Override
    public void resetMaxHealth() {

    }

}
