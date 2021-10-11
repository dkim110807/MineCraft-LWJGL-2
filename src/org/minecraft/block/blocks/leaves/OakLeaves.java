package org.minecraft.block.blocks.leaves;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.block.BlockModel;
import org.minecraft.block.BlockTexture;

/**
 * <h1>Represents the OAK LEAVES</h1>
 *
 * @author 4347
 * @see Block
 */
@SuppressWarnings("all")
public class OakLeaves extends Block {

    /**
     * The texture of this block({@link OakLeaves OAK LEAVES})
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
     * @see Type#OAK_LEAVES
     */
    private static final Block.Type TYPE = Block.Type.OAK_LEAVES;

    /**
     * The name of this block.
     * <p></p>
     *
     * @author 4347
     */
    private static final String NAME = "minecraft:oak_leaves";

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
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f,

            //Negative X
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f,

            //Positive Y
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f,

            //Negative Y
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f,

            //Positive Z
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f,

            //Negative Z
            8 / 25f, 0 / 25f,
            8 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 1 / 25f,
            9 / 25f, 0 / 25f,
            8 / 25f, 0 / 25f
    };

    /**
     * The model of this block.
     * <p></p>
     *
     * @author 4347
     * @see #load(float[], int[], float[], BlockTexture)
     * @see #load(float[], float[], BlockTexture)
     */
    public static final BlockModel MODEL = load(VERTICES, TCS, TEXTURE);

    /**
     * Create a block with the position x,y,z with the default {@link #MODEL model}
     * <li>This is same as {@link #OakLeaves(Vector3f)OakLeaves(new Vector3f(x, y, z))}
     * <li>This is same as {@link #OakLeaves(BlockModel, float, float, float)OakLeaves(OakLeaves.MODEL, x, y, z)}
     * <p></p>
     *
     * @param x The x position of the block
     * @param y The y position of the block
     * @param z The z position of the block
     * @throws IllegalArgumentException If the y value of the position is lower than 0 or greater than 255
     * @author 4347
     * @see #OakLeaves(Vector3f)
     */
    public OakLeaves(float x, float y, float z) throws IllegalArgumentException {
        super(MODEL, new Vector3f(x, y, z));
    }

    /**
     * Create a block with the position with the default {@link #MODEL model}
     * <li>This is same as {@link #OakLeaves(BlockModel, Vector3f)OakLeaves(OakLeaves.MODEL, position)}
     * <p></p>
     *
     * @param position The position of this block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @author 4347
     * @see #OakLeaves(BlockModel, Vector3f)
     */
    public OakLeaves(@NotNull Vector3f position) throws IllegalArgumentException {
        super(MODEL, position);
    }

    /**
     * Create a block with the position and rotation with the default {@link #MODEL model}
     * <li>This is same as {@link #OakLeaves(BlockModel, Vector3f, Vector3f)OakLeaves(OakLeaves.MODEL, position, rotation)}
     * <p></p>
     *
     * @deprecated No rotation for block {@link OakLeaves OAK LEVAES}
     * @param position The position of this block
     * @param rotation The rotation of this block
     * @throws IllegalArgumentException If the y value of the position is smaller than 0 or greater than 255
     * @see #OakLeaves(BlockModel, Vector3f, Vector3f)
     * @author 4347
     */
    @Deprecated
    public OakLeaves(@NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(MODEL, position, rotation);
    }

    /**
     * Create a block with the position and texture
     * <li>Use {@link #OakLeaves(BlockModel, Vector3f)} instead
     * <p></p>
     *
     * @deprecated Use {@link #OakLeaves(BlockModel, Vector3f)} instead
     * @param texture The texture of the block
     * @param position The position of the block
     * @throws IllegalArgumentException If the y value of the position is lower than 0 or greater than 255
     * @see #OakLeaves(BlockModel, Vector3f)
     * @author 4347
     */
    @Deprecated
    public OakLeaves(@NotNull BlockTexture texture, @NotNull Vector3f position) throws IllegalArgumentException {
        super(texture, position);
    }


    @Deprecated
    public OakLeaves(@NotNull BlockTexture texture, @NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(texture, position, rotation);
    }

    public OakLeaves(@NotNull BlockModel model, float x, float y, float z) throws IllegalArgumentException {
        super(model, new Vector3f(x, y, z));
    }

    public OakLeaves(@NotNull BlockModel model, @NotNull Vector3f position) throws IllegalArgumentException {
        super(model, position);
    }

    @Deprecated
    public OakLeaves(@NotNull BlockModel model, @NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(model, position, rotation);
    }
}
