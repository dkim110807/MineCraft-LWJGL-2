package org.minecraft.chunks;

import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;

import java.util.List;

public class Chunk {

    public static final int SIZE = 16;
    public static final int HEIGHT = 256;

    public List<Block> blocks;
    public Vector3f origin;

    public Chunk(List<Block> blocks, Vector3f origin) {
        this.blocks = blocks;
        this.origin = origin;
    }
}