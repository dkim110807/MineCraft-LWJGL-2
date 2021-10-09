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
    public BlockType getType() {
        return BlockType.NONE;
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
}