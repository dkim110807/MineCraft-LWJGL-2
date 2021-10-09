package org.minecraft.block.blocks;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.block.BlockModel;
import org.minecraft.block.BlockTexture;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Represents the GRASS block</h1>
 *
 * @author 4347
 * @see Block
 */
@SuppressWarnings("all")
public class Grass extends Block {

    /**
     * The texture of this block(GRASS).
     * <p></p>
     *
     * @author 4347
     * @see BlockTexture
     */
    private static final BlockTexture TEXTURE = new BlockTexture(loadTexture("texture.png"));

    /**
     * The type of this block.
     * <p></p>
     *
     * @author 4347
     * @see Block.Type#GRASS
     */
    private static final Block.Type TYPE = Block.Type.GRASS;

    /**
     * The name of this block.
     * <p></p>
     *
     * @author 4347
     */
    private static final String NAME = "minecraft:grass_block";

    /**
     * The texture coordinates of this block
     * <p></p>
     * This is without using indices
     * <p></p>
     *
     * @author 4347
     * @see Block#TCS
     */
    private static final float[] TCS = {
            //Positive X
            0 / 25f, 0 / 25f,
            0 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 0 / 25f,
            0 / 25f, 0 / 25f,

            //Negative X
            0 / 25f, 0 / 25f,
            0 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 0 / 25f,
            0 / 25f, 0 / 25f,

            //Positive Y
            1 / 25f, 0 / 25f,
            1 / 25f, 1 / 25f,
            2 / 25f, 1 / 25f,
            2 / 25f, 1 / 25f,
            2 / 25f, 0 / 25f,
            1 / 25f, 0 / 25f,

            //Negative Y
            2 / 25f, 0 / 25f,
            2 / 25f, 1 / 25f,
            3 / 25f, 1 / 25f,
            3 / 25f, 1 / 25f,
            3 / 25f, 0 / 25f,
            2 / 25f, 0 / 25f,

            //Positive Z
            0 / 25f, 0 / 25f,
            0 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 0 / 25f,
            0 / 25f, 0 / 25f,

            //Negative Z
            0 / 25f, 0 / 25f,
            0 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 1 / 25f,
            1 / 25f, 0 / 25f,
            0 / 25f, 0 / 25f
    };

    /**
     * The model of this block.
     * <p></p>
     *
     * @author 4347
     * @see Block#load(float[], int[], float[], BlockTexture)
     * @see Block#load(float[], float[], BlockTexture)
     */
    public static final BlockModel MODEL = load(VERTICES, TCS, TEXTURE);

    /**
     * Create a block with the position x,y,z with the default model (Grass.model)
     * <p></p>
     * This is same as Dirt(new Vector3f(x,y,z)
     * <p></p>
     *
     * @param x The x position of the block
     * @param y The y position of the block
     * @param z The z position of the block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(Vector3f)
     */
    public Grass(float x, float y, float z) throws IllegalArgumentException {
        super(MODEL, new Vector3f(x, y, z));
    }

    /**
     * Create a block with the position with the default model (Grass.model)
     * <p></p>
     * This is same as Grass(Grass.model,position)
     * <p></p>
     *
     * @param position The position of this block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(BlockModel, Vector3f)
     */
    public Grass(@NotNull Vector3f position) throws IllegalArgumentException {
        super(MODEL, position);
    }

    /**
     * Create a block with the position and rotation with the default model (Grass.model)
     * <p></p>
     * This is same as Grass(Grass.model,position,rotation)
     * <p></p>
     *
     * @param position The position of this block
     * @param rotation The rotation of this block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(BlockModel, Vector3f, Vector3f)
     * @deprecated No rotation for block GRASS
     */
    @Deprecated
    public Grass(@NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(MODEL, position, rotation);
    }


    /**
     * Create a block with the position
     * <p></p>
     *
     * @param position The position of the block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(BlockModel, Vector3f)
     * @deprecated Use Grass(BlockModel,Vector3f) instead
     */
    @Deprecated
    public Grass(@NotNull BlockTexture texture, @NotNull Vector3f position) throws IllegalArgumentException {
        super(texture, position);
    }

    /**
     * Create a block with the position,rotation
     * <p></p>
     *
     * @param position The position of the block
     * @param rotation The rotation of the block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(BlockModel, Vector3f, Vector3f)
     * @deprecated No rotation for block GRASS
     */
    @Deprecated
    public Grass(@NotNull BlockTexture texture, Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(texture, position, rotation);
    }

    /**
     * Create a block with the position x,y,z and the custome model
     * <p></p>
     * This is same as Grass(model,new Vector3f(x,y,z))
     * <p></p>
     *
     * @param model The model of this block. Use Grass.model or custom model
     * @param x     The x position of the block
     * @param y     The y position of the block
     * @param z     The z position of the block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #Grass(BlockModel, Vector3f)
     */
    public Grass(@NotNull BlockModel model, float x, float y, float z) throws IllegalArgumentException {
        super(model, new Vector3f(x, y, z));
    }

    /**
     * Create a block with the position and model
     * <p></p>
     *
     * @param model    The model of this block. Use Grass.model or custom model
     * @param position The position of this block.
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #MODEL
     */
    public Grass(@NotNull BlockModel model, @NotNull Vector3f position) throws IllegalArgumentException {
        super(model, position);
    }

    /**
     * Create a block with the position,rotation and model
     * <p></p>
     *
     * @param model    The model of this block. Use Grass.model or custom model
     * @param position The position of this block.
     * @param rotation The rotation of this block.
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #MODEL
     * @deprecated No rotation for block GRASS
     */
    @Deprecated
    public Grass(@NotNull BlockModel model, @NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(model, position, rotation);
    }


    /**
     * Returns the type of this block
     * <p>
     * In this case it will return Block.Type.GRASS
     * <p></p>
     *
     * @return The type of this block. (Block.Type.GRASS)
     * @author 4347
     * @see Block#getType()
     * @see Block.Type
     * @see Block.Type#GRASS
     */
    @NotNull
    @Override
    public Block.Type getType() {
        return TYPE;
    }

    /**
     * Returns the tcs of this block
     * <p></p>
     * In this case it will return the TCS
     * <p></p>
     *
     * @return The tcs of this block(GRASS).
     * @author 4347
     * @see Block#TCS
     * @see #TCS
     */
    @NotNull
    @Override
    public float[] getTCS() {
        return TCS;
    }

    /**
     * Serialize the block
     * <p></p>
     *
     * @return The map of data of the block
     * @author 4347
     * @see Block#serialize()
     */
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>();
        result.put("model", MODEL);
        result.put("texture", TEXTURE);
        result.put("type", TYPE.name());
        result.put("position", super.getPosition());
        result.put("rotation", super.getRotation());

        return result;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString();
    }


    /**
     * Add this block to the render queue
     * <p></p>
     * <h2>Needs to mesh first to reduce lag</h2>
     *
     * @author 4347
     * @see org.minecraft.block.BlockRender#addBlock(Block)
     */
    @Override
    public void add() {
        addBlock(this);
    }

    /**
     * Returns the name of the block
     * <p></p>
     * Default is "minecraft:none"
     * <p></p>
     *
     * @return The name of the block
     * @author 4347
     * @see #NAME
     */
    @NotNull
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Returns the width of this block.
     * <p></p>
     * In this case it will return 1f.
     * <p></p>
     *
     * @return The width of this block which is 1f
     * @author 4347
     * @see Block#getWidth()
     */
    @Override
    public float getWidth() {
        return 1f;
    }

    /**
     * Returns the height of this block.
     * <p></p>
     * In this case it will return 1f.
     * <p></p>
     *
     * @return The height of this block which is 1f
     * @author 4347
     * @see Block#getHeight
     */
    @Override
    public float getHeight() {
        return 1f;
    }

    /**
     * Returns the scale of this block.
     * <p></p>
     * In this case it will return Vector3f(1,1,1).
     * <p></p>
     *
     * @return The scale of this block which is Vector3f(1,1,1)
     * @author 4347
     * @see Block#getScale()
     */
    @Override
    public Vector3f getScale() {
        return new Vector3f(1, 1, 1);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Grass other = (Grass) obj;
        if (!other.getPosition().equals(getPosition()))
            return false;
        if (!other.getModel().equals(getModel()))
            return false;

        return true;
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
