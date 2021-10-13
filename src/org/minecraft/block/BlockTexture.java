package org.minecraft.block;

public class BlockTexture {

    private final int textureId;

    public BlockTexture(int textureId) {
        this.textureId = textureId;
    }

    public final int getTextureId() {
        return textureId;
    }
}
