package org.minecraft.chunks;

import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chunk {

    public static final int SIZE = 16;
    public static final int HEIGHT = 256;

    public List<Block> blocks;
    public Vector3f origin;
    public Map<Vector3f,Block> posToBlock = new HashMap<>();

    public Chunk(List<Block> blocks, Vector3f origin) {
        this.blocks = blocks;
        this.origin = origin;

        for (Block block : blocks) {
            posToBlock.put(block.getPosition(),block);
        }
    }

    public Block getBlockAt(Vector3f position) {
        return posToBlock.getOrDefault(position,null);
    }

    public Block getBlockAt(float x,float y,float z) {
        return getBlockAt(new Vector3f(x,y,z));
    }

}