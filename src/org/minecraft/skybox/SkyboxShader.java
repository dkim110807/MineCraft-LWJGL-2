package org.minecraft.skybox;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.minecraft.DisplayManager;
import org.minecraft.graphics.shader.Shader;
import org.minecraft.models.Camera;
import org.minecraft.utils.matrix.MatrixUtils;

public final class SkyboxShader extends Shader {

    private static final String VERTEX = "src/org/minecraft/skybox/skybox.vert";
    private static final String FRAGMENT = "src/org/minecraft/skybox/skybox.frag";

    private static final float ROTATE_SPEED = 1f;

    private float rotation = 0;

    public SkyboxShader() {
        super(VERTEX, FRAGMENT);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f vw_matrix = MatrixUtils.createViewMatrix(camera);
        vw_matrix.m30 = 0;
        vw_matrix.m31 = 0;
        vw_matrix.m32 = 0;
        rotation += ROTATE_SPEED;
        super.setUniformMat4f("vw_matrix", vw_matrix);
    }

    public void loadFogColour(float r, float g, float b) {
        super.setUniform3f("fogColour", r, g, b);
    }

}
