package org.minecraft.world.chunk;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <b>Chunk</b> class for minecraft
 *
 * @author 4347
 */
public class Chunk implements Serializable {

    /**
     * The serial version
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The size of this chunk.
     * <li>16 like the original minecraft
     */
    public static final int SIZE = 16;

    /**
     * The blocks of this chunk
     */
    public List<Block> blocks;

    /**
     * The origin of this chunk
     */
    public Vector3f origin;

    /**
     * The map of position of the block to the block
     * <p>Faster way to find which block is in the position</p>
     */
    private final Map<String, Block> posToBlock = new HashMap<>();

    /**
     * Chunk with blocks and origin
     *
     * @param blocks The blocks in the chunk
     * @param origin The origin of the chunk
     * @author 4347
     */
    public Chunk(@NotNull List<Block> blocks, @NotNull Vector3f origin) {
        this.blocks = blocks;
        this.origin = origin;

        blocks.forEach(it -> posToBlock.put(it.getPosition().toString(), it));
    }

    public boolean addBlock(@NotNull Block block) {
        if (posToBlock.containsKey(block.getPosition().toString()))
            return false;

        this.blocks.add(block);
        posToBlock.put(block.getPosition().toString(), block);

        return true;
    }

    public boolean addBlock(@NotNull Block block, boolean force) {
        if (force) {
            if (this.blocks.contains(posToBlock.get(block.getPosition().toString())))
                blocks.remove(posToBlock.get(block.getPosition().toString()));

            blocks.add(block);
            posToBlock.put(block.getPosition().toString(), block);

            return true;
        }

        if (posToBlock.containsKey(block.getPosition().toString()))
            return false;

        this.blocks.add(block);
        posToBlock.put(block.getPosition().toString(), block);

        return true;
    }

    /**
     * Returns true if there is a block in the position if not returns false
     * <li>This is same as {@link #isBlockIn(float, float, float) isBlockIn(position.x, position.y, position.z)}
     * @param position The position
     * @return True if there is a block in the position if not false
     * @author 4347
     */
    public boolean isBlockIn(@NotNull Vector3f position) {
        return posToBlock.containsKey(position.toString());
    }

    /**
     * Returns true if there is a block in the position if not returns false
     * <li>This is same as {@link #isBlockIn(Vector3f) isBlockIn(new Vector3f(x, y, z))}
     *
     * @param x The x position
     * @param y The y position
     * @param z The z position
     * @return True if there is a block in the position if not false
     * @author 4347
     */
    public boolean isBlockIn(float x, float y, float z) {
        return posToBlock.containsKey("Vector3f[" + x + ", " + y + ", " + z + "]");
    }

}
