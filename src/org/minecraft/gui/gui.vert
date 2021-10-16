#version 140

in vec2 position;

out vec2 tcs;

uniform mat4 tr_matrix;

void main() {
    gl_Position = tr_matrix * vec4(position, 0.0, 1.0);
    tcs = vec2((position.x + 1.0)/2.0, 1-(position.y+1.0)/2.0);
}
