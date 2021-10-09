#version 400 core

in vec2 pass_textureCoord;

out vec4 out_colour;

uniform sampler2D tex;

void main() {
    out_colour = texture(tex,pass_textureCoord);
}