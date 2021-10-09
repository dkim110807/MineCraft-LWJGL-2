package org.minecraft.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Entity;
import org.minecraft.models.TexturedModel;
import org.minecraft.utils.matrix.MatrixUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityRender {

    public static void render(Entity entity, Shader shader) {

        GL30.glBindVertexArray(entity.getModel().getModel().getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        shader.setUniformMat4f("tr_matrix", MatrixUtils.createTransformationMatrix(entity.getPosition(),entity.getRotation(),entity.getScale()));
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D,entity.getModel().getTexture().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES,entity.getModel().getModel().getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public static void render(Map<TexturedModel, List<Entity>> entities,Shader shader) {

        for (TexturedModel model : entities.keySet()) {

            GL30.glBindVertexArray(model.getModel().getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);

            GL13.glActiveTexture(GL13.GL_TEXTURE1);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,model.getTexture().getTextureID());

            List<Entity> batch = entities.get(model);

            for (Entity entity : batch) {
                shader.setUniformMat4f("tr_matrix",MatrixUtils.createTransformationMatrix(entity.getPosition(),entity.getRotation(),entity.getScale()));

                //GL11.glDrawElements(GL11.GL_TRIANGLES,model.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT,0);
                GL11.glDrawArrays(GL11.GL_TRIANGLES,0,model.getModel().getVertexCount());
            }

            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);

        }

    }

}
