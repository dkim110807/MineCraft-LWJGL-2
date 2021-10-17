package org.minecraft.entity;

import java.util.Objects;

public final class EntityModel {


    /**
     * The vao id of this model
     */
    private final int vao;

    /**
     * The vertex count of this model
     */
    private final int vertex;

    private EntityTexture texture;


    /**
     * Create the model of the block
     *
     * @param vao     The vao id of the block
     * @param vertex  The count of vertex
     * @param texture The texture of the block
     * @author 4347
     */
    public EntityModel(int vao, int vertex, EntityTexture texture) {
        this.vao = vao;
        this.vertex = vertex;
        this.texture = texture;
    }


    /**
     * Returns the vao id of this model
     *
     * @return The vao id of this model
     * @author 4347
     */
    public final int getVaoID() {
        return vao;
    }

    /**
     * Returns the count of vertex of this model
     *
     * @return The vertex count of this model
     * @author 4347
     */
    public final int getVertexCount() {
        return vertex;
    }

    /**
     * Returns the texture of this model
     *
     * @return The texture of this model
     * @author 4347
     */
    public final EntityTexture getTexture() {
        return texture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityModel that = (EntityModel) o;
        return vao == that.vao && vertex == that.vertex && Objects.equals(texture, that.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vao, vertex, texture);
    }

    @Override
    public String toString() {
        return "EntityModel{" +
                "vao=" + vao +
                ", vertex=" + vertex +
                ", texture=" + texture +
                '}';
    }
}
