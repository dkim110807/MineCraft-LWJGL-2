package org.minecraft.block;

import org.lwjgl.util.vector.Vector3f;
import org.minecraft.world.chunk.Chunk;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class BlockMesh implements Serializable {

    /**
     * The serial version
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The vertices
     */
    private static final float[] VERTICES = new float[] {
            //Positive X
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,

            //Negative X
            -0.5f, 0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,

            //Positive Y
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f,
            0.5f, 0.5f, 0.5f,

            //Negative Y
            -0.5f, 0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,

            //Positive Z
            -0.5f, 0.5f, 0.5f,
            -0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, 0.5f, 0.5f,

            //Negative Z
            -0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, 0.5f
    };

    private static final float[] NORMALS = new float[] {
            //Positive X
            0.5f, 0.0f, 0.0f,

            //Negative X
            -0.5f, 0.0f, 0.0f,

            //Positive Y
            0.0f, 0.5f, 0.0f,

            //Negative Y
            0.0f, -0.5f, 0.0f,

            //Positive Z
            0.0f, 0.0f, 0.5f,

            //Negative Z
            0.0f, 0.0f, -0.5f
    };

    private static final int[] INDICES = new int[]{
            //Positive X
            0, 1, 3,
            3, 1, 2,

            //Negative X
            4, 5, 7,
            7, 5, 6,

            //Positive Y
            8, 9, 11,
            11, 9, 10,

            //Negative Y
            12, 13, 15,
            15, 13, 14,

            //Positive Z
            16, 17, 19,
            19, 17, 18,

            //Negative Z
            20, 21, 23,
            23, 21, 22
    };

    private final List<Float> posList = new ArrayList<>();
    private final List<Float> tcsList = new ArrayList<>();
    private final List<Float> normalsList = new ArrayList<>();
    private final List<Integer> indicesList = new ArrayList<>();

    private Chunk chunk;
    private List<Block> blocks;

    public Vector3f origin;

    public BlockMesh(Chunk chunk) {
        this.chunk = chunk;

        this.blocks = chunk.blocks;
        this.origin = chunk.origin;

        build();
        populate();
    }

    public void update(Chunk chunk) {
        this.chunk = chunk;

        this.blocks = chunk.blocks;
        this.origin = chunk.origin;

        build();
        populate();
    }

    private void build() {

        for (Block block : blocks) {

            boolean px = chunk.isBlockIn(block.getPosition().x + 1f, block.getPosition().y, block.getPosition().z);
            boolean nx = chunk.isBlockIn(block.getPosition().x - 1f, block.getPosition().y, block.getPosition().z);
            boolean py = chunk.isBlockIn(block.getPosition().x, block.getPosition().y + 1f, block.getPosition().z);
            boolean ny = chunk.isBlockIn(block.getPosition().x, block.getPosition().y - 1f, block.getPosition().z);
            boolean pz = chunk.isBlockIn(block.getPosition().x, block.getPosition().y, block.getPosition().z + 1f);
            boolean nz = chunk.isBlockIn(block.getPosition().x, block.getPosition().y, block.getPosition().z - 1f);

            if (!px) {

            }

        }

    }

    public float[] positions;
    public float[] tcs;
    public float[] normals;

    private void populate() {



        cleanUp();

    }

    private void cleanUp() {
        posList.clear();
        tcsList.clear();
        normalsList.clear();
        indicesList.clear();
    }

}
