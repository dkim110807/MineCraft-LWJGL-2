package org.minecraft.utils.buffer;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

    private BufferUtils() {
    }

    public static ByteBuffer createByteBuffer(byte[] array) {
        ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
        result.put(array).flip();
        return result;
    }

    public static FloatBuffer createFloatBuffer(float[] array) {
        FloatBuffer result = org.lwjgl.BufferUtils.createFloatBuffer(array.length);
        result.put(array).flip();
        return result;
    }

    public static FloatBuffer createFloatBuffer(Vector2f[] vector) {
        FloatBuffer result = ByteBuffer.allocateDirect(vector.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        float[] array = new float[vector.length * 2];
        for (int i = 0; i < vector.length; i++) {
            array[i * 2] = vector[i].x;
            array[i * 2 + 1] = vector[i].y;
        }
        result.put(array).flip();
        return result;
    }

    public static FloatBuffer createFloatBuffer(Vector3f[] vector) {
        FloatBuffer result = ByteBuffer.allocateDirect(vector.length * 6).order(ByteOrder.nativeOrder()).asFloatBuffer();
        float[] array = new float[vector.length * 3];
        for (int i = 0; i < vector.length; i++) {
            array[i * 3] = vector[i].x;
            array[i * 3 + 1] = vector[i].y;
            array[i * 3 + 2] = vector[i].z;
        }
        result.put(array).flip();
        return result;
    }

    public static FloatBuffer createFloatBuffer(Vector4f[] vector) {
        FloatBuffer result = ByteBuffer.allocateDirect(vector.length * 8).order(ByteOrder.nativeOrder()).asFloatBuffer();
        float[] array = new float[vector.length * 4];
        for (int i = 0; i < vector.length; i++) {
            array[i * 4] = vector[i].x;
            array[i * 4 + 1] = vector[i].y;
            array[i * 4 + 2] = vector[i].z;
            array[i * 4 + 3] = vector[i].w;
        }
        result.put(array).flip();
        return result;
    }

    public static IntBuffer createIntBuffer(int[] array) {
        IntBuffer result = org.lwjgl.BufferUtils.createIntBuffer(array.length);
        result.put(array).flip();
        return result;
    }
}