package org.minecraft;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.block.BlockTexture;
import org.minecraft.graphics.font.mesh.FontType;
import org.minecraft.graphics.font.mesh.GUIText;
import org.minecraft.graphics.font.render.FontLoader;
import org.minecraft.graphics.font.render.TextMaster;
import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Camera;
import org.minecraft.old.block.Block;
import org.minecraft.old.block.BlockMesh;
import org.minecraft.old.block.BlockRender;
import org.minecraft.old.block.blocks.Bedrock;
import org.minecraft.old.block.blocks.Dirt;
import org.minecraft.old.block.blocks.Grass;
import org.minecraft.old.block.blocks.Stone;
import org.minecraft.old.block.blocks.leaves.OakLeaves;
import org.minecraft.old.block.blocks.logs.OakLog;
import org.minecraft.old.chunks.Chunk;
import org.minecraft.render.Loader;
import org.minecraft.render.MasterRenderer;
import org.minecraft.skybox.SkyboxLoader;
import org.minecraft.utils.MousePicker;
import org.minecraft.utils.matrix.MatrixUtils;
import org.minecraft.world.PerlinNoise;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Main {

    /**
     * The field of view
     */
    public static final float FOV = 70f;

    /**
     * The near plane
     */
    public static final float NEAR = 0.01f;

    /**
     * The far plane
     */
    public static final float FAR = 1000f;

    /**
     * The world size
     */
    public static final int WORLD_SIZE = 64;

    private static final List<BlockMesh> meshes = Collections.synchronizedList(new ArrayList<>());
    private static final List<Vector3f> used = new ArrayList<>();

    private static boolean close = false;

    private static final PerlinNoise noise = new PerlinNoise(4347);

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        MasterRenderer renderer = new MasterRenderer();
        Loader loader = new Loader();
        TextMaster.init();

        FontType font = new FontType(FontLoader.loadTexture("fonts/candara.png"), new File("src/res/fonts/candara.fnt"));
        GUIText text = new GUIText("This is a test", 3, font, new Vector2f(0.4f, 0.4f), 1f, false);
        text.setColour(0.5f,0.1f,0.1f);
        text.setOutlineColour(0.1f,0.5f,0.1f);

        Camera camera = new Camera(new Vector3f(0, 60, 0), new Vector3f(0, 0, 0));

        MousePicker picker = new MousePicker(camera,MatrixUtils.createProjectionMatrix());

        Shader shader = new Shader("src/org/minecraft/graphics/shader/main.vert", "src/org/minecraft/graphics/shader/main.frag");
        shader.enable();
        shader.setUniform1i("tex", 1);
        shader.setUniformMat4f("pr_matrix", MatrixUtils.createProjectionMatrix());
        shader.disable();

        Shader block = BlockRender.shader;
        block.enable();
        block.setUniform1i("tex", 1);
        block.setUniformMat4f("pr_matrix", MatrixUtils.createProjectionMatrix());
        block.disable();

        org.minecraft.block.Block.prepare();

        new Dirt(0, 0, 0);
        new Grass(0, 0, 0);
        new Stone(0, 0, 0);
        new Bedrock(0, 0, 0);
        new OakLog(0, 0, 0);
        new OakLeaves(0, 0, 0);

        AtomicInteger total = new AtomicInteger(0);

        new Thread(() -> {

            while (!close) {
                for (int x = (int) (camera.getPosition().x - WORLD_SIZE) / Chunk.SIZE; x < (camera.getPosition().x + WORLD_SIZE) / Chunk.SIZE; x++) {
                    for (int z = (int) (camera.getPosition().z - WORLD_SIZE) / Chunk.SIZE; z < (camera.getPosition().z + WORLD_SIZE) / Chunk.SIZE; z++) {
                        if (!used.contains(new Vector3f(x * Chunk.SIZE, 0, z * Chunk.SIZE))) {

                            List<Block> blocks = new ArrayList<>();

                            for (int i = 0; i < Chunk.SIZE; i++) {
                                for (int j = 0; j < Chunk.SIZE; j++) {

                                    int height = (int) noise.generateHeight(x * Chunk.SIZE + i, z * Chunk.SIZE + j);

                                    blocks.add(new Bedrock(new Vector3f(i, 54, j)));
                                    for (int k = 55; k <= height + 56; k++) {
                                        blocks.add(new Stone(new Vector3f(i, k, j)));
                                    }
                                    blocks.add(new Dirt(new Vector3f(i, height + 57, j)));
                                    blocks.add(new Dirt(new Vector3f(i, height + 58, j)));
                                    blocks.add(new Dirt(new Vector3f(i, height + 59, j)));
                                    blocks.add(new Grass(new Vector3f(i, height + 60, j)));
                                }
                            }

                            total.addAndGet(blocks.size());

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

            picker.update();

            if (index < meshes.size()) {

                BlockMesh mesh = meshes.get(index);

                blocks.add(new Block(loader.load(mesh.positions, mesh.tcs, Block.TEXTURE), mesh.origin));

                mesh.positions = null;
                mesh.tcs = null;

                index++;

                System.out.println(total + " blocks");
            }

            for (Block value : blocks) {

                Vector3f origin = value.getPosition();

                int distX = (int) (camera.getPosition().x - origin.x);
                int distZ = (int) (camera.getPosition().z - origin.z);

                distX = Math.abs(distX);
                distZ = Math.abs(distZ);

                if ((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {
                    value.add();
                }

            }

            renderer.render(shader, camera);
            TextMaster.render();

            DisplayManager.updateDisplay();

            frame++;

            if (System.currentTimeMillis() - time >= 1000) {
                time = System.currentTimeMillis();
                System.out.println(frame + " fps");
                frame = 0;
            }

        }

        close = true;

        //************ Clean Ups **********

        TextMaster.cleanUp();
        DisplayManager.closeDisplay();
        loader.cleanUp();
        Block.cleanUp();
        BlockTexture.cleanUp();
        SkyboxLoader.cleanUp();
    }

}
