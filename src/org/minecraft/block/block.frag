#version 400 core

in vec2 pass_tcs;

out vec4 out_Colour;

uniform sampler2D tex;

void main() {
    out_Colour = texture(tex, pass_tcs);
}
