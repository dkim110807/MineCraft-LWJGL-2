package org.minecraft.old.block.blocks.slabs;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.old.block.Block;
import org.minecraft.old.block.BlockModel;

/**
 * <h1>Slab blocks</h1>
 *
 * @author 4347
 * @see Block
 */
@SuppressWarnings("all")
public class Slabs extends Block {

    /**
     * Vertices for 1, 1, 0.5 blocks
     * <p></p>
     *
     * @author 4347
     */
    protected static final float[] VERTICES = new float[]{
            //Positive X
            0.5f, 0.0f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.0f, 0.5f,
            0.5f, 0.0f, -0.5f,

            //Negative X
            -0.5f, 0.0f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.0f, 0.5f,
            -0.5f, 0.0f, -0.5f,

            //Positive Y
            -0.5f, 0.0f, 0.5f,
            -0.5f, 0.0f, -0.5f,
            0.5f, 0.0f, -0.5f,
            0.5f, 0.0f, -0.5f,
            0.5f, 0.0f, 0.5f,
            -0.5f, 0.0f, 0.5f,

            //Negative Y
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,

            //Positive Z
            -0.5f, 0.0f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.0f, 0.5f,
            -0.5f, 0.0f, 0.5f,

            //Negative Z
            -0.5f, 0.0f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.0f, -0.5f,
            -0.5f, 0.0f, -0.5f
    };

    public enum Half {
        BOTTOM("slabs:bottom"),
        TOP("slabs:top");

        public final String name;

        Half(String name) {
            this.name = name;
        }
    }

    private final Half half;

    public Slabs(@NotNull BlockModel model, @NotNull Vector3f position, @NotNull Half half) throws IllegalArgumentException {
        super(model,position);
        this.half = half;
    }

    public Slabs(@NotNull BlockModel model, @NotNull Vector3f position, @NotNull Vector3f rotation, @NotNull Half half) throws IllegalArgumentException {
        super(model,position,rotation);
        this.half = half;
    }

}
