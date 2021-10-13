package org.minecraft.old.block;

/**
 *
 * Old Way
 *
 */
@Deprecated
public enum BlockType {

    NONE("none",0,0,null),
    DIRT("minecraft:dirt_block",1f,1f,new BlockTexture(Block.loadTexture("dirt.png"))),
    GRASS("minecraft:grass_block",1f,1f,new BlockTexture(Block.loadTexture("grass.png"))),
    STONE("minecraft:stone",1f,1f,new BlockTexture(Block.loadTexture("stone.png"))),
    BEDROCK("minecraft:bedrock",1f,1f,new BlockTexture(Block.loadTexture("bedrock.png")));

    public final String name;
    public final float height;
    public final float width;
    public final BlockTexture texture;

    BlockType(String name,float height,float width,BlockTexture texture) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.texture = texture;
    }

}
