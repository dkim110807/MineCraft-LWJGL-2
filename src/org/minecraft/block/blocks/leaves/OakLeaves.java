package org.minecraft.block.blocks.leaves;

import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.block.BlockModel;
import org.minecraft.block.BlockTexture;

/**
 * <h1>Represents the OAK LEAVES</h1>
 *
 * @see Block
 * @author 4347
 */
@SuppressWarnings("all")
public class OakLeaves extends Block {

    /**
     * The texture of this block({@link OakLeaves OAK LEAVES})
     * <p></p>
     *
     * @see BlockTexture
     * @author 4347
     */
    private static final BlockTexture TEXTURE = new BlockTexture(loadTexture("texture.png"));

    /**
     * The type of this block.
     * <p></p>
     *
     * @see Type#OAK_LEAVES
     * @author 4347
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
     * @see Block#TCS
     * @author 4347
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
     * @see #load(float[], int[], float[], BlockTexture)
     * @see #load(float[], float[], BlockTexture)
     * @author 4347
     */
    public static final BlockModel MODEL = load(VERTICES,TCS,TEXTURE);

    public OakLeaves(float x,float y,float z) throws IllegalArgumentException {
        super(MODEL,new Vector3f(x, y, z));
    }
}
