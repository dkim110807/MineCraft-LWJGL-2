package org.minecraft.old.chunks;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.old.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chunk {

    public static final int SIZE = 16;
    public static final int HEIGHT = 256;

    public List<Block> blocks;
    public Vector3f origin;
    public Map<String,Block> posToBlock = new HashMap<>();

    public Chunk(List<Block> blocks, Vector3f origin) {
        this.blocks = blocks;
        this.origin = origin;

        for (Block block : blocks) {
            posToBlock.put(block.getPosition().toString(),block);
        }
    }

    @Nullable
    public Block getBlockAt(Vector3f position) {
        return posToBlock.get(position);
    }

    @Nullable
    public Block getBlockAt(float x,float y,float z) {
        return getBlockAt(new Vector3f(x,y,z));
    }

    public boolean isBlockIn(float x,float y,float z) {
        return posToBlock.containsKey(new Vector3f(x,y,z).toString());
    }

}