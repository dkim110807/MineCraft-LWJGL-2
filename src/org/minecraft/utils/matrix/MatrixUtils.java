package org.minecraft.utils.matrix;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.minecraft.Main;
import org.minecraft.models.Camera;

public class MatrixUtils {

    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(scale, matrix, matrix);

        return matrix;
    }

    public static Matrix4f createProjectionMatrix() {

        Matrix4f matrix = new Matrix4f();

        float aspect = Display.getWidth() / (float) Display.getHeight();
        float yScale = 1f / (float) Math.tan(Math.toRadians(Main.FOV / 2f));
        float xScale = yScale / aspect;
        float zp = Main.FAR + Main.NEAR;
        float zm = Main.FAR - Main.NEAR;

        matrix.m00 = xScale;
        matrix.m11 = yScale;
        matrix.m22 = -zp / zm;
        matrix.m23 = -1;
        matrix.m32 = -(2*Main.FAR * Main.NEAR) / zm;
        matrix.m33 = 0;

        return matrix;
    }

    public static Matrix4f createViewMatrix(Camera camera) {

        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();

        Matrix4f.rotate((float) Math.toRadians(camera.getRotation().x),new Vector3f(1,0,0),matrix,matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRotation().y),new Vector3f(0,1,0),matrix,matrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRotation().z),new Vector3f(0,0,1),matrix,matrix);
        Matrix4f.translate(new Vector3f(-camera.getPosition().x,-camera.getPosition().y,-camera.getPosition().z),matrix,matrix);

        return matrix;
    }

}
