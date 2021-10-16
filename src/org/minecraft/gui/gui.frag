#version 140

in vec2 tcs;

out vec4 out_Colour;

uniform sampler2D tex;

void main() {
    out_Colour = texture(tex, tcs);
}
