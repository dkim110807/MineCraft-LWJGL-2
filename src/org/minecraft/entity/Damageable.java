package org.minecraft.entity;

/**
 * Represents an Entity that has health and can take damage.
 *
 * @author 4347
 */
public interface Damageable {

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     * @author 4347
     */
    void damage(double amount);

    /**
     * Gets the entity's health from 0 to {@link #getMaxHealth() getMaxHealth()}, where 0 is dead.
     *
     * @return Health represented from 0 to max
     * @author 4347
     */
    double getHealth();

    /**
     * Sets the entity's health from 0 to getMaxHealth(), where 0 is dead.
     *
     * @param health New health represented from 0 to max
     * @throws IllegalArgumentException Thrown if the health is < 0 or > {@link #getMaxHealth()}
     * @author 4347
     */
    void setHealth(double health) throws IllegalArgumentException;

    /**
     * Gets the maximum health this entity has.
     *
     * @return Maximum health
     * @author 4347
     */
    double getMaxHealth();

    /**
     * Sets the maximum health this entity can have.
     * <p>
     * If the health of the entity is above the value provided it will be set to that value.
     *
     * @param health amount of health to set the maximum to
     * @author 4347
     */
    void setMaxHealth(double health);

    /**
     * Resets the max health to the original amount.
     *
     * @author 4347
     */
    void resetMaxHealth();

}
