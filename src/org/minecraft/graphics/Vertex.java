package org.minecraft.graphics;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Vertex {

    public Vector3f position;
    public Vector3f normals;
    public Vector2f tcs;

    public Vertex(Vector3f position, Vector2f tcs, Vector3f normals) {
        this.position = position;
        this.normals = normals;
        this.tcs = tcs;
    }


}
