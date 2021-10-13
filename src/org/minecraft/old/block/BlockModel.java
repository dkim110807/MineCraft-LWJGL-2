package org.minecraft.old.block;

public class BlockModel{

    private final int vaoID;
    private final int vertexCount;
    private final BlockTexture texture;

    public BlockModel(int vaoID, int vertexCount, BlockTexture texture) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = texture;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public BlockTexture getTexture() {
        return texture;
    }
}
