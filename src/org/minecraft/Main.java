package org.minecraft;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.Block;
import org.minecraft.block.BlockMesh;
import org.minecraft.block.BlockRender;
import org.minecraft.block.blocks.Bedrock;
import org.minecraft.block.blocks.Dirt;
import org.minecraft.block.blocks.Grass;
import org.minecraft.block.blocks.Stone;
import org.minecraft.block.blocks.leaves.OakLeaves;
import org.minecraft.block.blocks.logs.OakLog;
import org.minecraft.chunks.Chunk;
import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Camera;
import org.minecraft.models.ModelTexture;
import org.minecraft.models.RawModel;
import org.minecraft.models.TexturedModel;
import org.minecraft.render.Loader;
import org.minecraft.render.MasterRenderer;
import org.minecraft.utils.matrix.MatrixUtils;
import org.minecraft.world.PerlinNoise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final float FOV = 70f;
    public static final float NEAR = 0.01f;
    public static final float FAR = 1000f;

    public static final int WORLD_SIZE = 64;

    private static List<BlockMesh> meshes = Collections.synchronizedList(new ArrayList<>());
    private static List<Vector3f> used = new ArrayList<>();

    private static boolean close = false;

    private static final PerlinNoise noise = new PerlinNoise(4347);

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        MasterRenderer renderer = new MasterRenderer();
        Loader loader = new Loader();

        float[] vertices = {
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,

                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,

                -0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, 0.5f,

                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f

        };

        float[] tcs = {

                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0,
                0, 0,
                0, 1,
                1, 1,
                1, 0


        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2,
                4, 5, 7,
                7, 5, 6,
                8, 9, 11,
                11, 9, 10,
                12, 13, 15,
                15, 13, 14,
                16, 17, 19,
                19, 17, 18,
                20, 21, 23,
                23, 21, 22
        };

        RawModel model = loader.loadToVao(vertices, indices, tcs);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dirt.png"));
        TexturedModel texModel = new TexturedModel(model, texture);
        //Entity entity = new Entity(texModel, new Vector3f(0, 0, -1), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera(new Vector3f(0, 60, 0), new Vector3f(0, 0, 0));

        Shader shader = new Shader("src/org/minecraft/graphics/shader/main.vert", "src/org/minecraft/graphics/shader/main.frag");
        shader.setUniform1i("tex", 1);
        shader.setUniformMat4f("pr_matrix", MatrixUtils.createProjectionMatrix());

        Shader block = BlockRender.shader;
        block.setUniform1i("tex", 1);
        block.setUniformMat4f("pr_matrix", MatrixUtils.createProjectionMatrix());

        new Dirt(new Vector3f(0,0,0));
        new Grass(0,0,0);
        new Stone(0,0,0);
        new Bedrock(0,0,0);
        new OakLog(0,0,0);
        new OakLeaves(0,0,0);

        AtomicInteger totalBlocks = new AtomicInteger();

        new Thread(() -> {

            while (!close) {
                for (int x = (int) (camera.getPosition().x - WORLD_SIZE) / Chunk.SIZE; x < (camera.getPosition().x + WORLD_SIZE) / Chunk.SIZE; x++) {
                    for (int z = (int) (camera.getPosition().z - WORLD_SIZE) / Chunk.SIZE; z < (camera.getPosition().z + WORLD_SIZE) / Chunk.SIZE; z++) {
                        if (!used.contains(new Vector3f(x * Chunk.SIZE, 0, z * Chunk.SIZE))) {

                            List<Block> blocks = new ArrayList<>();

                            for (int i = 0; i < Chunk.SIZE; i++) {
                                for (int j = 0; j < Chunk.SIZE; j++) {

                                    int height = (int) noise.generateHeight(x * Chunk.SIZE + i, z * Chunk.SIZE + j);

                                    blocks.add(new Bedrock(new Vector3f(i, 49, j)));
                                    for (int k = 50; k <= height + 56; k++) {
                                        blocks.add(new Stone(new Vector3f(i, k, j)));
                                    }
                                    blocks.add(new Dirt(new Vector3f(i, height + 57, j)));
                                    blocks.add(new Dirt(new Vector3f(i, height + 58, j)));
                                    blocks.add(new Dirt(new Vector3f(i, height + 59, j)));
                                    blocks.add(new Grass(new Vector3f(i, height + 60, j)));
                                }
                            }

                            totalBlocks.addAndGet(blocks.size());

                            BlockMesh mesh = new BlockMesh(new Chunk(blocks, new Vector3f(x * Chunk.SIZE, 0, z * Chunk.SIZE)));

                            meshes.add(mesh);
                            used.add(new Vector3f(x * Chunk.SIZE, 0, z * Chunk.SIZE));
                        }
                    }
                }
            }

        }).start();

        long time = System.currentTimeMillis();
        int frame = 0;

        List<Block> blocks = new ArrayList<>();

        int index = 0;
        while (!Display.isCloseRequested()) {

            camera.move();

            if (index < meshes.size()) {

                BlockMesh mesh = meshes.get(index);

                blocks.add(new Block(loader.load(mesh.positions,mesh.tcs,  Block.TEXTURE),mesh.origin));

                mesh.positions = null;
                mesh.tcs = null;

                index++;

                System.out.println(totalBlocks + " BLOCKS");
            }

            for (int i = 0; i < blocks.size(); i++) {

                Vector3f origin = blocks.get(i).getPosition();

                int distX = (int) (camera.getPosition().x - origin.x);
                int distZ = (int) (camera.getPosition().z - origin.z);

                distX = Math.abs(distX);
                distZ = Math.abs(distZ);

                if ((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {
                    blocks.get(i).add();
                }

            }

            renderer.render(shader, camera);

            DisplayManager.updateDisplay();

            frame++;

            if (System.currentTimeMillis() - time >= 1000) {
                time = System.currentTimeMillis();
                System.out.println(frame + " fps");
                frame = 0;
            }

        }

        DisplayManager.closeDisplay();
        loader.cleanUp();
        Block.cleanUp();
        close = true;
    }

}
