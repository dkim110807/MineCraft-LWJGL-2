package org.minecraft.block;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Camera;
import org.minecraft.utils.matrix.MatrixUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockRender {

    public static final Shader shader = new Shader("src/org/minecraft/block/block.vert","src/org/minecraft/block/block.frag");

    private static final Map<BlockModel, List<Block>> blocks = new HashMap<>();

    public static void render(Camera camera) {

        shader.enable();
        shader.setUniformMat4f("vw_matrix",MatrixUtils.createViewMatrix(camera));

        for (BlockModel model : blocks.keySet()) {
            GL30.glBindVertexArray(model.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            GL13.glActiveTexture(GL13.GL_TEXTURE1);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,model.getTexture().getTextureID());

            List<Block> batch = blocks.get(model);

            for (Block block : batch) {
                shader.setUniformMat4f("tr_matrix", MatrixUtils.createTransformationMatrix(block.getPosition(),block.getRotation(),block.getScale()));

                //GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
                GL11.glDrawArrays(GL11.GL_TRIANGLES,0,model.getVertexCount() / 3);
            }

            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        }

        blocks.clear();

        shader.disable();

    }

    protected void addBlock(Block block) {
        List<Block> batch = blocks.get(block.getModel());

        if (batch == null) {
            batch = new ArrayList<>();
        }

        batch.add(block);

        blocks.put(block.getModel(),batch);
    }



}
