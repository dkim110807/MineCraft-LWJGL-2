package org.minecraft.old.block;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractBlock extends BlockRender{

    /**
     *
     * Returns the name of this block
     *
     * @return The name of this block
     * @author 4347
     */
    public abstract @NotNull
    String getName();

    public abstract float getWidth();

    /**
     *
     * Returns the height of this block
     *
     * @return The height of this block
     * @author 4347
     */
    public abstract float getHeight();

    /**
     *
     * Returns the scale of this block
     *
     * @return The scale of this block
     * @author 4347
     */
    public abstract Vector3f getScale();

    /**
     *
     * Add this block to the render queue
     *
     * @author 4347
     * @see BlockRender#addBlock(Block)
     */
    public abstract void add();

    /**
     *
     * Returns the BlockModel of this block
     *
     * @return The BlockModel of this block
     * @author 4347
     * @see BlockModel
     */
    public abstract BlockModel getModel();

    /**
     *
     * Returns the type of this block
     *
     * @return The type of this block
     * @author 4347
     * @see Block.Type
     */
    public abstract Block.Type getType();
}
