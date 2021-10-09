package org.minecraft.blocks;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.block.BlockModel;
import org.minecraft.block.BlockTexture;
import org.minecraft.block.BlockType;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Represents the dirt block
 *
 * Use org.minecraft.block.blocks.Dirt instead
 *
 * @author 4347
 * @see org.minecraft.block.Block
 * @deprecated Old Way
 * @see org.minecraft.block.blocks.Dirt
 */
@Deprecated
public class Dirt extends Block {

    /**
     * 
     * The texture of this block
     *
     * @see BlockTexture
     */
    private static final BlockTexture texture = new BlockTexture(loadTexture("dirt.png"));
    /**
     * 
     * The type of this block
     * 
     * @see BlockType#DIRT
     */
    private static final BlockType type = BlockType.DIRT;
    /**
     * The name of this block
     */
    private static final String name = "minecraft:dirt_block";

    /**
     * 
     * The model of this block
     * <p></p>
     *
     * @see Block#load(float[], int[], float[], BlockTexture)
     * @see Block#load(float[], float[], BlockTexture)
     */
    public static final BlockModel model = load(VERTICES,TCS,texture);

    /**
     *
     * Create a block with the position with the default model (Dirt.model)
     * <p></p>
     * This is same as Dirt(Dirt.model,position)
     *
     * @param position The position of this block
     * @author 4347
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     * @see #Dirt(BlockModel,Vector3f)
     */
    public Dirt(@NotNull Vector3f position) throws IllegalArgumentException {
        super(model,position);
    }

    /**
     *
     * Create a block with the position and rotation with the default model (Dirt.model)
     * <p></p>
     * This is same as Dirt(Dirt.model,position,rotation)
     *
     * @param position The position of this block
     * @param rotation The rotation of this block
     * @author 4347
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     * @deprecated No rotation for block DIRT
     * @see #Dirt(BlockModel,Vector3f,Vector3f)
     */
    @Deprecated
    public Dirt(@NotNull Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(model,position,rotation);
    }

    /**
     *
     * Create a block with the position
     *
     * @param position The position of the block
     * @author 4347
     * @deprecated Use Dirt(BlockModel,Vector3f) instead
     * @see #Dirt(BlockModel, Vector3f)
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     */
    @Deprecated
    public Dirt(@NotNull BlockTexture texture,@NotNull Vector3f position) throws IllegalArgumentException {
        super(texture, position);
    }

    /**
     *
     * Create a block with the position,rotation
     *
     * @param position The position of the block
     * @param rotation The rotation of the block
     * @deprecated No rotation for block DIRT
     * @author 4347
     * @see #Dirt(BlockModel, Vector3f, Vector3f)
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     */
    @Deprecated
    public Dirt(@NotNull BlockTexture texture,Vector3f position, @NotNull Vector3f rotation) throws IllegalArgumentException {
        super(texture, position, rotation);
    }

    /**
     *
     * Create a block with the position and model
     *
     * @param model The model of this block. Use Dirt.model or custom model
     * @param position The position of this block.
     * @author 4347
     * @see Dirt#model
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     */
    public Dirt(@NotNull BlockModel model, @NotNull Vector3f position) throws IllegalArgumentException {
        super(model,position);
    }

    /**
     *
     * Create a block with the position,rotation and model
     *
     * @param model The model of this block. Use Dirt.model or custom model
     * @param position The position of this block.
     * @param rotation The rotation of this block.
     * @author 4347
     * @see Dirt#model
     * @deprecated No rotation for block DIRT
     * @throws IllegalArgumentException If the y value of the position is < 0 or > 255
     */
    @Deprecated
    public Dirt(@NotNull BlockModel model, @NotNull Vector3f position,@NotNull Vector3f rotation) throws IllegalArgumentException {
        super(model,position,rotation);
    }

    /*/**
     *
     * Returns the type of this block
     *
     * In this case it will return BlockType.DIRT
     *
     * @return The type of this block. (BlockType.DIRT)
     * @author 4347
     * @see Block#getType()
     * /
    @NotNull
    @Override
    public BlockType getType() {
        return type;
    }*/

    /**
     *
     * Returns the tcs of this block
     * <p></p>
     * In this case it will return the TCS
     *
     * @return The tcs of this block(DIRT).
     * @author 4347
     * @see Block#TCS
     */
    @Override
    public float[] getTCS() {
        return TCS;
    }

    /**
     *
     * Serialize the block
     *
     * @return the map of data of the block
     * @author 4347
     */
    @Override
    public Map<String,Object> serialize() {
        Map<String,Object> result = new HashMap<>();
        result.put("model",model);
        result.put("texture",texture);
        result.put("type",type);
        result.put("position",super.getPosition());
        result.put("rotation",super.getRotation());

        return result;
    }

    /**
     *
     * Returns the data of this block to String
     *
     * @return The data to String
     * @author 4347
     */
    @Override
    public String toString() {
        Map<String,Object> data = serialize();

        StringBuilder sb = new StringBuilder();

        for (String key : data.keySet()) {
            sb.append(key).append(" ").append(data.get(key)).append("\n");
        }

        return sb.toString();
    }

    /**
     * 
     * Add this block to the render queue
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
     *
     * @return The name of the block
     * @author 4347
     */
    @NotNull
    @Override
    public String getName() {
        return name;
    }
}
