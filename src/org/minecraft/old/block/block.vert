#version 400 core

in vec3 position;
in vec2 textureCoord;

out vec2 pass_textureCoord;

uniform mat4 tr_matrix;
uniform mat4 pr_matrix;
uniform mat4 vw_matrix;

void main() {
    gl_Position = pr_matrix * vw_matrix * tr_matrix * vec4(position, 1.0);
    pass_textureCoord = textureCoord;
}
