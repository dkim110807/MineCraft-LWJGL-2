package org.minecraft.block;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.graphics.TextureLoader;
import org.minecraft.utils.buffer.BufferUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block extends AbstractBlock {

    /**
     * The vertices for 1,1,1 block
     */
    protected static final float[] VERTICES = new float[]{
            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            
            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            -0.5f,0.5f,-0.5f
    };

    /**
     * Indices for default vertices
     * @deprecated Old method (Can't mesh with indices)
     */
    @Deprecated
    protected static final int[] INDICES = new int[]{
            0, 1, 3,
            3, 1, 2,
            4, 5, 7,
            7, 5, 6,
            8, 9, 11,
            11, 9, 10,
            12, 13, 15,
            15, 13, 14,
            16, 17, 19,
            19, 17, 18,
            20, 21, 23,
            23, 21, 22
    };

    /**
     * The default TCS for blocks
     * etc) dirt,planks
     */
    protected static final float[] TCS = new float[]{
            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0,

            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0,

            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0,

            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0,

            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0,

            0, 0,
            0, 1,
            1, 1,
            1, 1,
            1, 0,
            0, 0
    };

    private static final List<Integer> vaos = new ArrayList<>();
    private static final List<Integer> vbos = new ArrayList<>();
    private static final List<Integer> textures = new ArrayList<>();

    public static final BlockTexture TEXTURE = new BlockTexture(loadTexture("texture.png"));

    public enum Type {

        NONE("none",0f,0f),
        DIRT("minecraft:dirt_block",1f,1f),
        GRASS("minecraft:grass_block",1f,1f),
        STONE("minecraft:stone",1f,1f);

        public final String name;
        public final float width;
        public final float height;

        Type(String name, float width,float height) {
            this.name = name;
            this.width = width;
            this.height = height;
        }
    }

    /**
     * The model of this block
     */
    private final BlockModel model;
    /**
     * The texture of this block
     */
    private final BlockTexture texture;
    /**
     * The position of this block
     */
    private final Vector3f position;
    /**
     * The rotation of this block
     */
    private final Vector3f rotation;

    public Block(@NotNull BlockTexture texture, @NotNull Vector3f position) throws IllegalArgumentException {

        if (position.y < 0 || position.y > 255) {
            throw new IllegalArgumentException("The y position of the block can't be lower than 0 or higher than 255");
        }

        model = null;
        this.texture = texture;
        this.position = position;
        rotation = new Vector3f(0, 0, 0);
    }

    public Block(@NotNull BlockTexture texture, @NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {

        if (position.y < 0 || position.y > 255) {
            throw new IllegalArgumentException("The y position of the block can't be lower than 0 or higher than 255");
        }

        model = null;
        this.texture = texture;
        this.position = position;
        this.rotation = rotation;
    }

    public Block(@NotNull BlockModel model, @NotNull Vector3f position) throws IllegalArgumentException {

        if (position.y < 0 || position.y > 255) {
            throw new IllegalArgumentException("The y position of the block can't be lower than 0 or higher than 255");
        }

        this.model = model;
        this.texture = model.getTexture();
        this.position = position;
        this.rotation = new Vector3f(0, 0, 0);
    }

    public Block(@NotNull BlockModel model, @NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {

        if (position.y < 0 || position.y > 255) {
            throw new IllegalArgumentException("The y position of the block can't be lower than 0 or higher than 255");
        }

        this.model = model;
        this.texture = model.getTexture();
        this.position = position;
        this.rotation = rotation;
    }

    /**
     * Returns the name of the block
     * <p></p>
     * Default is "minecraft:none"
     *
     * @return The name of the block
     * @author 4347
     */
    @NotNull
    @Override
    public String getName() {
        return "minecraft:none";
    }

    /**
     * Returns the width of the block
     * <p></p>
     * Default is to 1f
     *
     * @return The width of the block
     * @author 4347
     */
    @Override
    public float getWidth() {
        return 1f;
    }

    /**
     * Returns the height of the block
     * <p></p>
     * Default is to 1f
     *
     * @return The height of the block
     * @author 4347
     */
    @Override
    public float getHeight() {
        return 1f;
    }

    /**
     * Returns the scale of this block
     * <p></p>
     * It will usually returns Vector3f(1,1,1)
     *
     * @return The scale of this block
     * @author 4347
     */
    @NotNull
    @Override
    public Vector3f getScale() {
        return new Vector3f(1, 1, 1);
    }

    /**
     * Add this block to the render queue
     *
     * @author 4347
     * @see BlockRender#addBlock(Block)
     * @see AbstractBlock#add()
     */
    @Override
    public void add() {
        addBlock(this);
    }

    /**
     * Returns the model of this block
     * <p></p>
     * It may return null (Deprecated method)
     *
     * @return The model of this block
     * @author 4347
     */
    @Override
    public BlockModel getModel() {
        return model;
    }

    /**
     * Returns the texture id of the texture
     * <p></p>
     * It is same as getTexture().getTextureID()
     *
     * @return The texture id of the block texture
     * @author 4347
     * @see Block#getTexture()
     */
    public final int getTextureID() {
        return texture.getTextureID();
    }

    /**
     * Returns the texture of the block
     *
     * @return The texture of the block
     * @author 4347
     * @see BlockTexture
     */
    @NotNull
    public final BlockTexture getTexture() {
        return texture;
    }

    /**
     * Returns the position of the block
     * <p>
     * The y position of the block is > 0 and < 255
     *
     * @return The position of the block
     * @author 4347
     */
    @NotNull
    public final Vector3f getPosition() {
        return position;
    }

    /**
     * Returns the rotation of the block
     * <p>
     * If the block has no rotation it will return Vector3f(0,0,0)
     *
     * @return The rotation of the block
     * @author 4347
     */
    public final Vector3f getRotation() {
        return rotation;
    }

    /**
     * Returns the type of the block
     * <p>
     * </p>
     * Default is to BlockType.NONE
     *
     * @return The type of the block
     * @author 4347
     * @see AbstractBlock#getType()
     */
    @NotNull
    @Override
    public Block.Type getType() {
        return Block.Type.NONE;
    }

    /**
     * Returns the tcs of the block
     * <p></p>
     * Default is an empty array
     *
     * @return The tcs of the block
     * @author 4347
     */
    public float[] getTCS() {
        return new float[0];
    }

    /**
     * Serialize the block
     * <p></p>
     * Default is empty map
     *
     * @return the map of data of the block
     * @author 4347
     */
    public Map<String, Object> serialize() {
        return new HashMap<>();
    }

    /**
     * Return the data of this block to String
     * <p></p>
     * Default is to "none"
     *
     * @return The data of this block to String
     * @author 4347
     */
    @Override
    public String toString() {
        return "none";
    }

    /**
     * Load the vertices, indices, tcs , texture to vbos
     *
     * @param vertices The vertices to load
     * @param indices  The indices
     * @param tcs      The texture coordinates
     * @param texture  The texture
     * @return The model with the vertices,indices,tcs,texture
     * @author 4347
     * @see BlockModel
     * @see BlockTexture
     */
    protected static BlockModel load(float[] vertices, int[] indices, float[] tcs, BlockTexture texture) {
        int vao = createVAO();

        storeDataInAttributeList(vertices, 0, 3);                                   //0 VBO => VERTICES
        storeDataInAttributeList(tcs, 1, 2);                                        //1 VBO => TCS
        bindIndicesBuffer(indices);

        GL30.glBindVertexArray(0);

        return new BlockModel(vao, indices.length, texture);
    }

    /**
     * Load the vertices,tcs,texture to BlockModel
     *
     * @param vertices The vertices to load
     * @param tcs      The texture coordinates
     * @param texture  The texture
     * @return The model with the vertices,tcs,texture
     * @author 4347
     * @see BlockModel
     * @see BlockTexture
     */
    protected static BlockModel load(float[] vertices, float[] tcs, BlockTexture texture) {

        int vao = createVAO();

        storeDataInAttributeList(vertices, 0, 3);
        storeDataInAttributeList(tcs, 1, 2);

        GL30.glBindVertexArray(0);

        return new BlockModel(vao, vertices.length, texture);
    }

    /**
     * Load the texture to the vbos
     *
     * @param path The path of the file
     * @return The texture id of the loaded texture
     * @author 4347
     */
    protected static int loadTexture(String path) {

        try {
            int texture = TextureLoader.getTexture("../../../res/" + path);
            textures.add(texture);

            return texture;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Creates the vao and adds to vaos
     *
     * @return The vao id
     * @author 4347
     */
    private static int createVAO() {
        int vaoID = GL30.glGenVertexArrays();

        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);

        return vaoID;
    }

    private static void storeDataInAttributeList(float[] data, int attribute, int dimensions) {
        int vboID = GL15.glGenBuffers();

        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private static void bindIndicesBuffer(int[] indices) {
        int vboID = GL15.glGenBuffers();

        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createIntBuffer(indices), GL15.GL_STATIC_DRAW);
    }

    /**
     * Clean up the vaos, vbos, textures
     *
     * @author 4347
     */
    public static void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);                               //clean up the vaos
        vbos.forEach(GL15::glDeleteBuffers);                                    //clean up the vbos
        textures.forEach(GL11::glDeleteTextures);                               //clean up the textures
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
        return super.equals(obj);
    }
}