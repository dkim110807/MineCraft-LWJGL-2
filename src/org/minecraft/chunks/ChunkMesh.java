package org.minecraft.chunks;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.graphics.Vertex;

import java.util.ArrayList;
import java.util.List;

public class ChunkMesh {

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

    private List<Vertex> vertices;

    private List<Float> positionsList;
    private List<Float> tcsList;
    private List<Float> normalsList;

    public float[] positions, tcs, normals;

    public Chunk chunk;

    public ChunkMesh(Chunk chunk) {

        this.chunk = chunk;

        vertices = new ArrayList<>();
        positionsList = new ArrayList<>();
        tcsList = new ArrayList<>();
        normalsList = new ArrayList<>();

        buildMesh();
        populateLists();

    }

    public void update(Chunk chunk) {
        this.chunk = chunk;

        buildMesh();
        populateLists();
    }

    private void buildMesh() {

        //Loop through blocks in chunk and determine which is visible

        for (Block block : chunk.blocks) {

            boolean px = false, nx = false, py = false, ny = false, pz = false, nz = false;

            for (Block other : chunk.blocks) {

                //PX
                px = block.getPosition().x + 1 == other.getPosition().x && block.getPosition().y == other.getPosition().y && block.getPosition().z == other.getPosition().z;

                //NX
                nx = block.getPosition().x - 1 == other.getPosition().x && block.getPosition().y == other.getPosition().y && block.getPosition().z == other.getPosition().z;

                //PY
                py = block.getPosition().x == other.getPosition().x && block.getPosition().y + 1 == other.getPosition().y && block.getPosition().z == other.getPosition().z;

                //NY
                ny = block.getPosition().x == other.getPosition().x && block.getPosition().y - 1 == other.getPosition().y && block.getPosition().z == other.getPosition().z;

                //PZ
                pz = block.getPosition().x == other.getPosition().x && block.getPosition().y == other.getPosition().y && block.getPosition().z + 1 == other.getPosition().z;

                //PX
                nz = block.getPosition().x == other.getPosition().x && block.getPosition().y == other.getPosition().y && block.getPosition().z - 1 == other.getPosition().z;
            }

            //Add the visible sides to the chunk mesh

            if (!px) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(PX_POS[i].x + block.getPosition().x, PX_POS[i].y + block.getPosition().y, PX_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

            if (!nx) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(NX_POS[i].x + block.getPosition().x, NX_POS[i].y + block.getPosition().y, NX_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

            if (!py) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(PY_POS[i].x + block.getPosition().x, PY_POS[i].y + block.getPosition().y, PY_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

            if (!ny) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(NY_POS[i].x + block.getPosition().x, NY_POS[i].y + block.getPosition().y, NY_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

            if (!pz) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(PZ_POS[i].x + block.getPosition().x, PZ_POS[i].y + block.getPosition().y, PZ_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

            if (!nz) {
                for (int i = 0; i < 6; i++) {
                    vertices.add(new Vertex(new Vector3f(NZ_POS[i].x + block.getPosition().x, NZ_POS[i].y + block.getPosition().y, NZ_POS[i].z + block.getPosition().z), TCS[i], NORMALS[i]));
                }
            }

        }

    }

    private void populateLists() {

        for (Vertex vertices : vertices) {
            positionsList.add(vertices.position.x);
            positionsList.add(vertices.position.y);
            positionsList.add(vertices.position.z);
            tcsList.add(vertices.tcs.x);
            tcsList.add(vertices.tcs.y);
            normalsList.add(vertices.normals.x);
            normalsList.add(vertices.normals.y);
            normalsList.add(vertices.normals.z);
        }

        positions = new float[positionsList.size()];
        tcs = new float[tcsList.size()];
        normals = new float[normalsList.size()];

        for (int i = 0; i < positionsList.size(); i++) {
            positions[i] = positionsList.get(i);
        }

        for (int i = 0; i < tcsList.size(); i++) {
            tcs[i] = tcsList.get(i);
        }

        for (int i = 0; i < normalsList.size(); i++) {
            normals[i] = normalsList.get(i);
        }

    }

}
