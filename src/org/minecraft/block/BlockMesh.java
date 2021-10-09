package org.minecraft.block;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.chunks.Chunk;
import org.minecraft.graphics.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockMesh {

    private static final Vector3f[] PX_POS = {

            new Vector3f(0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, -0.5f)

    };

    private static final Vector3f[] NX_POS = {

            new Vector3f(-0.5f, 0.5f, -0.5f),
            new Vector3f(-0.5f, -0.5f, -0.5f),
            new Vector3f(-0.5f, -0.5f, 0.5f),
            new Vector3f(-0.5f, -0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, -0.5f)

    };

    private static final Vector3f[] PY_POS = {

            new Vector3f(-0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, 0.5f)

    };

    private static final Vector3f[] NY_POS = {

            new Vector3f(-0.5f, -0.5f, 0.5f),
            new Vector3f(-0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
            new Vector3f(-0.5f, -0.5f, 0.5f)

    };

    private static final Vector3f[] PZ_POS = {

            new Vector3f(-0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, -0.5f, 0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
            new Vector3f(0.5f, -0.5f, 0.5f),
            new Vector3f(0.5f, 0.5f, 0.5f),
            new Vector3f(-0.5f, 0.5f, 0.5f)

    };

    private static final Vector3f[] NZ_POS = {

            new Vector3f(-0.5f, 0.5f, -0.5f),
            new Vector3f(-0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, -0.5f, -0.5f),
            new Vector3f(0.5f, 0.5f, -0.5f),
            new Vector3f(-0.5f, 0.5f, -0.5f)

    };

    private static final Vector2f[] TCS = {

            new Vector2f(0.f, 0.f),
            new Vector2f(0.f, 1.f),
            new Vector2f(1.f, 1.f),
            new Vector2f(1.f, 1.f),
            new Vector2f(1.f, 0.f),
            new Vector2f(0.f, 0.f)

    };

    private static final Vector3f[] NORMALS = {

            new Vector3f(0.f, 0.f, 0.f),
            new Vector3f(0.f, 0.f, 0.f),
            new Vector3f(0.f, 0.f, 0.f),
            new Vector3f(0.f, 0.f, 0.f),
            new Vector3f(0.f, 0.f, 0.f),
            new Vector3f(0.f, 0.f, 0.f)

    };

    private Map<BlockType, List<Block>> blocks = new HashMap<>();
    private final Map<BlockType, List<Vertex>> vertices = new HashMap<>();
    public Vector3f origin;

    public BlockMesh(List<Block> blocks) {
        for (Block block : blocks) {
            List<Block> batch = this.blocks.get(block.getType());

            if (batch == null) {
                batch = new ArrayList<>();
            }

            batch.add(block);

            this.blocks.put(block.getType(), batch);
        }

        origin = blocks.get(0).getPosition();
    }

    public BlockMesh(Map<BlockType, List<Block>> blocks) {
        this.blocks = blocks;
    }

    public BlockMesh(Chunk chunk) {
        for (Block block : chunk.blocks) {
            List<Block> batch = this.blocks.get(block.getType());

            if (batch == null) {
                batch = new ArrayList<>();
            }

            batch.add(block);

            this.blocks.put(block.getType(), batch);
        }

        origin = chunk.origin;
        build();
        populate();
    }

    public void update(List<Block> blocks) {
        for (Block block : blocks) {
            List<Block> batch = this.blocks.get(block.getType());

            if (batch == null) {
                batch = new ArrayList<>();
            }

            batch.add(block);

            this.blocks.put(block.getType(), batch);
        }


    }

    public void update(Map<BlockType, List<Block>> blocks) {
        this.blocks = blocks;
    }

    public void update(Chunk chunk) {
        for (Block block : chunk.blocks) {
            List<Block> batch = this.blocks.get(block.getType());

            if (batch == null) {
                batch = new ArrayList<>();
            }

            batch.add(block);

            this.blocks.put(block.getType(), batch);
        }
    }

    private void build() {

        List<Block> all = new ArrayList<>();
        blocks.values().forEach(all::addAll);

        for (BlockType type : blocks.keySet()) {
            List<Block> batch = blocks.get(type);
            vertices.put(type, new ArrayList<>());

            for (Block block : batch) {

                boolean px = false;
                boolean nx = false;
                boolean py = false;
                boolean ny = false;
                boolean pz = false;
                boolean nz = false;

                for (Block other : all) {

                    if (((block.getPosition().x + 1) == (other.getPosition().x)) && (block.getPosition().y == other.getPosition().y) && (block.getPosition().z == other.getPosition().z))
                        px = true;

                    if (((block.getPosition().x - 1) == (other.getPosition().x)) && (block.getPosition().y == other.getPosition().y) && (block.getPosition().z == other.getPosition().z))
                        nx = true;

                    if ((block.getPosition().x == other.getPosition().x) && (block.getPosition().y + 1 == other.getPosition().y) && (block.getPosition().z == other.getPosition().z))
                        py = true;

                    if ((block.getPosition().x == other.getPosition().x) && (block.getPosition().y - 1 == other.getPosition().y) && (block.getPosition().z == other.getPosition().z))
                        ny = true;

                    if ((block.getPosition().x == other.getPosition().x) && (block.getPosition().y == other.getPosition().y) && (block.getPosition().z + 1 == other.getPosition().z))
                        pz = true;

                    if ((block.getPosition().x == other.getPosition().x) && (block.getPosition().y == other.getPosition().y) && (block.getPosition().z - 1 == other.getPosition().z))
                        nz = true;

                }

                if (!px) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(PX_POS[i].x + block.getPosition().x,
                                PX_POS[i].y + block.getPosition().y,
                                PX_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i], NORMALS[i]));
                    }
                }

                if (!nx) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(NX_POS[i].x + block.getPosition().x,
                                NX_POS[i].y + block.getPosition().y,
                                NX_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i + 6], NORMALS[i]));
                    }
                }

                if (!py) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(PY_POS[i].x + block.getPosition().x,
                                PY_POS[i].y + block.getPosition().y,
                                PY_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i + 12], NORMALS[i]));
                    }
                }

                if (!ny) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(NY_POS[i].x + block.getPosition().x,
                                NY_POS[i].y + block.getPosition().y,
                                NY_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i + 18], NORMALS[i]));
                    }
                }

                if (!pz) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(PZ_POS[i].x + block.getPosition().x,
                                PZ_POS[i].y + block.getPosition().y,
                                PZ_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i + 24], NORMALS[i]));
                    }
                }

                if (!nz) {
                    for (int i = 0; i < 6; i++) {
                        vertices.get(type).add(new Vertex(new Vector3f(NZ_POS[i].x + block.getPosition().x,
                                NZ_POS[i].y + block.getPosition().y,
                                NZ_POS[i].z + block.getPosition().z), toVector2f(block.getTCS())[i + 30], NORMALS[i]));
                    }
                }
            }

        }
    }

    public List<Block> render = new ArrayList<>();

    public Map<BlockType,float[]> positions = new HashMap<>();
    public Map<BlockType,float[]> tcs = new HashMap<>();

    private void populate() {
        for (BlockType type : blocks.keySet()) {

            List<Vertex> vertices = this.vertices.get(type);

            float[] positions = new float[vertices.size() * 3];
            float[] tcs = new float[vertices.size() * 2];
            float[] normals = new float[vertices.size() * 3];

            for (int i = 0; i < vertices.size(); i++) {
                positions[i * 3] = vertices.get(i).position.x;
                positions[i * 3 + 1] = vertices.get(i).position.y;
                positions[i * 3 + 2] = vertices.get(i).position.z;
                tcs[i*2] = vertices.get(i).tcs.x;
                tcs[i*2+1] = vertices.get(i).tcs.y;
                normals[i * 3] = vertices.get(i).normals.x;
                normals[i * 3 + 1] = vertices.get(i).normals.y;
                normals[i * 3 + 2] = vertices.get(i).normals.z;
            }

            this.positions.put(type,positions);
            this.tcs.put(type,tcs);
            //render.add(new Block(Block.load(positions,tcs,blocks.get(type).get(0).getTexture()),new Vector3f(0,0,0)));
        }

        cleanup();
    }

    private void cleanup() {
        vertices.clear();
    }

    private Vector2f[] toVector2f(float[] array) {

        Vector2f[] result = new Vector2f[array.length / 2];

        for (int i = 0; i < array.length; i += 2) {
            result[i / 2] = new Vector2f(array[i], array[i + 1]);
        }

        return result;
    }
}
