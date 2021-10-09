package org.minecraft.graphics.shader;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.minecraft.utils.shader.ShaderUtils;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

@SuppressWarnings("all")
public class Shader {

    private boolean enabled = false;

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);
        if (result == -1)
            System.err.println("Could not find uniform variable '" + name + "'!");
        else
            locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2i(String name, int v1, int v2) {
        if (!enabled) enable();
        glUniform2i(getUniform(name), v1, v2);
    }

    public void setUniform2f(String name, Vector2f vector) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), vector.x, vector.y);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniform3f(String name, float x, float y, float z) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), x, y, z);
    }

    public void setUniform4f(String name, Vector4f vector) {
        if (!enabled) enable();
        glUniform4f(getUniform(name), vector.x, vector.y, vector.z, vector.w);
    }

    public void setUniform4f(String name, float x, float y, float z, float w) {
        if (!enabled) enable();
        glUniform4f(getUniform(name), x, y, z, w);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix.store(buffer);
        buffer.flip();
        glUniformMatrix4(getUniform(name),false,buffer);
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}