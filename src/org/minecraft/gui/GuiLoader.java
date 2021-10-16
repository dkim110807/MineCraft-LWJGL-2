package org.minecraft.gui;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.minecraft.graphics.TextureLoader;
import org.minecraft.utils.buffer.BufferUtils;

import java.util.ArrayList;
import java.util.List;

public final class GuiLoader {

    private static final List<Integer> vaos = new ArrayList<>();
    private static final List<Integer> vbos = new ArrayList<>();
    private static final List<Integer> textures = new ArrayList<>();

    private GuiLoader() {
    }

    public static GuiModel loadToVAO(float[] positions) {
        int vaoID = createVAO();

        storeDataInAttributeList(positions, 0, 2);

        GL30.glBindVertexArray(0);

        return new GuiModel(vaoID, positions.length / 2);
    }

    /**
     * Load the texture to the vbos
     * <li>The path is only the path in the {@link res res} folder
     *
     * @param path The path of the file
     * @return The texture id of the loaded texture
     * @author 4347
     */
    public static int loadTexture(@NotNull String path) {
        try {
            int texture = TextureLoader.getTexture("../../../res/" + path);

            textures.add(texture);

            return texture;
        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }
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
    @SuppressWarnings("all")
    private static void storeDataInAttributeList(float[] data, int attribute, int dimensions) {
        int vboID = GL15.glGenBuffers();

        vbos.add(vboID);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(data), GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * Cleans all the vaos and vbos at the end of program
     *
     * @author 4347
     */
    public static void cleanUp() {
        vaos.forEach(GL30::glDeleteVertexArrays);
        vbos.forEach(GL15::glDeleteBuffers);
        textures.forEach(GL11::glDeleteTextures);
    }

}
