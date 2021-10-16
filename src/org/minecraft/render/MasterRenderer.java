package org.minecraft.render;

import org.lwjgl.opengl.GL11;
import org.minecraft.old.block.BlockRender;
import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Camera;
import org.minecraft.models.Entity;
import org.minecraft.models.TexturedModel;
import org.minecraft.utils.matrix.MatrixUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

    public void prepare() {

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0.4f,0.7f,1.0f,1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

    }

    public void render(Shader shader, Camera camera) {

        prepare();
        shader.enable();
        shader.setUniformMat4f("vw_matrix", MatrixUtils.createViewMatrix(camera));
        EntityRender.render(entities, shader);
        shader.disable();

        BlockRender.render(camera);
        org.minecraft.block.BlockRender.render(camera);

        entities.clear();
    }

    public void addEntity(Entity entity) {
        List<Entity> batch = entities.get(entity.getModel());

        if (batch == null) {
            batch = new ArrayList<>();
        }
        batch.add(entity);

        entities.put(entity.getModel(), batch);
    }

}
