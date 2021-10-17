package org.minecraft.skybox;

import org.lwjgl.opengl.*;
import org.minecraft.decoder.PNGDecoder;
import org.minecraft.graphics.texture.TextureData;
import org.minecraft.utils.buffer.BufferUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class SkyboxLoader {

    private static final List<Integer> vaos = new ArrayList<>();
    private static final List<Integer> vbos = new ArrayList<>();
    private static final List<Integer> textures = new ArrayList<>();

    private SkyboxLoader() {
    }

    /**
     * Returns the {@link SkyboxModel} with the positions loaded to vao
     *
     * @param positions The positions of the model
     * @return The model with the positions loaded to vao
     * @author 4347
     */
    public static SkyboxModel loadToVAO(float[] positions) {
        int vaoID = createVAO();

        storeDataInAttributeList(positions, 0, 3);

        GL30.glBindVertexArray(0);

        return new SkyboxModel( vaoID, positions.length / 3);
    }

    /**
     * Creates the vao and adds to vaos
     * <li>The vaos needs to be cleared at the end of program
     *
     * @return The vao id
     * @author 4347
     */
    private static int createVAO() {
        int vaoID = GL30.glGenVertexArrays();

        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);

        return vaoID;
    }

    /**
     * Store data in attribute list
     * <li>Adds to {@link #vbos vbo list} and clears at the end of program
     *
     * @param data       The data to store
     * @param attribute  The attribute number to store
     * @param dimensions The dimensions of the data
     * @author 4347
     */
    private static void storeDataInAttributeList(float[] data, int attribute, int dimensions) {
        int vboID = GL15.glGenBuffers();

        vbos.add(vboID);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public static int loadCubeMap(String[] textureFiles) {
        int texID = GL11.glGenTextures();
        GL13.glActiveTexture(texID);
        GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texID);

        for (int i = 0; i < textureFiles.length; i++) {
            TextureData data = decodeTextureFile("src/res/" + textureFiles[i]);
            GL11.glTexImage2D(GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());
        }
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        textures.add(texID);

        return texID;
    }

    private static TextureData decodeTextureFile(String fileName) {
        int width = 0;
        int height = 0;
        ByteBuffer buffer = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            PNGDecoder decoder = new PNGDecoder(fis);
            width = decoder.getWidth();
            height = decoder.getHeight();
            buffer = ByteBuffer.allocateDirect(4 * width * height);
            decoder.decode(buffer, width * 4, PNGDecoder.Format.RGBA);
            buffer.flip();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Tried to load texture " + fileName + ", didn't work!");
            System.exit(-1);
        }

        return new TextureData(buffer, width, height);
    }

    public static void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        vbos.forEach(GL15::glDeleteBuffers);
        textures.forEach(GL11::glDeleteTextures);
    }

}
