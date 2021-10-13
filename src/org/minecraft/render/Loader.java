package org.minecraft.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.minecraft.old.block.BlockModel;
import org.minecraft.old.block.BlockTexture;
import org.minecraft.graphics.TextureLoader;
import org.minecraft.models.RawModel;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static final List<Integer> vaos = new ArrayList<>();
    private static final List<Integer> vbos = new ArrayList<>();
    private static final List<Integer> textures = new ArrayList<>();

    public RawModel loadToVao(float[] vertices,int[] indices,float[] tcs) {

        int vaoID = createVAO();
        storeDataInAttributeList(vertices,0,3);
        storeDataInAttributeList(tcs,1,2);
        bindIndicesBuffer(indices);
        GL30.glBindVertexArray(0);

        return new RawModel(vaoID,indices.length);
    }

    public RawModel loadToVao(float[] vertices,float[] tcs) {
        int vaoID = createVAO();
        storeDataInAttributeList(vertices,0,3);
        storeDataInAttributeList(tcs,1,2);

        GL30.glBindVertexArray(0);

        return new RawModel(vaoID,vertices.length);
    }

    public BlockModel load(float[] vertices, float[] tcs, BlockTexture texture) {

        int vao = createVAO();

        storeDataInAttributeList(vertices, 0, 3);
        storeDataInAttributeList(tcs, 1, 2);

        GL30.glBindVertexArray(0);

        return new BlockModel(vao, vertices.length, texture);
    }

    private int createVAO() {

        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);

        return vaoID;
    }

    public int loadTexture(String fileName) {

        try {
            int textureID = TextureLoader.getTexture("../../../res/"+fileName);
            textures.add(textureID);

            return textureID;

        } catch (NullPointerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {

        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, storeDataInFloatBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,dimensions, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
    }

    private void bindIndicesBuffer(int[] indices) {

        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboID);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,storeDataInIntBuffer(indices),GL15.GL_STATIC_DRAW);

    }

    private IntBuffer storeDataInIntBuffer(int[] data) {

        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    private FloatBuffer storeDataInFloatBuffer(float[] data) {


        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();

        return buffer;
    }

    public void cleanUp() {

        vaos.forEach(GL30::glDeleteVertexArrays);
        vbos.forEach(GL15::glDeleteBuffers);
        textures.forEach(GL11::glDeleteTextures);

    }

}
