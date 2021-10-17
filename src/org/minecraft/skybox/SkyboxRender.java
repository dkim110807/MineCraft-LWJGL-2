package org.minecraft.skybox;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.minecraft.models.Camera;
import org.minecraft.utils.matrix.MatrixUtils;

public final class SkyboxRender {

    private static final float SIZE = 500f;

    private static final float[] VERTICES = {
            -SIZE, SIZE, -SIZE,
            -SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,

            -SIZE, -SIZE, SIZE,
            -SIZE, -SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,
            -SIZE, SIZE, -SIZE,
            -SIZE, SIZE, SIZE,
            -SIZE, -SIZE, SIZE,

            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,

            -SIZE, -SIZE, SIZE,
            -SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            SIZE, -SIZE, SIZE,
            -SIZE, -SIZE, SIZE,

            -SIZE, SIZE, -SIZE,
            SIZE, SIZE, -SIZE,
            SIZE, SIZE, SIZE,
            SIZE, SIZE, SIZE,
            -SIZE, SIZE, SIZE,
            -SIZE, SIZE, -SIZE,

            -SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE, SIZE,
            SIZE, -SIZE, -SIZE,
            SIZE, -SIZE, -SIZE,
            -SIZE, -SIZE, SIZE,
            SIZE, -SIZE, SIZE
    };

    private static final String[] TEXTURE_FILES = {
            "skybox/right.png", "skybox/left.png",
            "skybox/top.png", "skybox/bottom.png",
            "skybox/back.png", "skybox/front.png"
    };

    private final SkyboxModel cube;
    private final int texture;
    private final SkyboxShader shader;

    public SkyboxRender(Matrix4f pr_matrix) {
        cube = SkyboxLoader.loadToVAO(VERTICES);
        texture = SkyboxLoader.loadCubeMap(TEXTURE_FILES);
        shader = new SkyboxShader();
        shader.enable();
        shader.setUniformMat4f("pr_matrix", pr_matrix);
        shader.disable();
    }

    public void render(Camera camera, float r, float g, float b) {
        shader.enable();

        shader.loadViewMatrix(camera);
        shader.loadFogColour(r, g, b);

        GL30.glBindVertexArray(cube.getVaoID());

        GL20.glEnableVertexAttribArray(0);

        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());

        GL20.glDisableVertexAttribArray(0);

        GL30.glBindVertexArray(0);

        shader.disable();
    }
}
