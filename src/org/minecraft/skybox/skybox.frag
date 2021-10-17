#version 400

in vec3 tcs;

out vec4 out_Colour;

uniform samplerCube tex;
uniform vec3 fogColour;

const float lowerLimit = 0.0;
const float upperLimit = 30.0;

void main() {
    vec4 finalColour = texture(tex, tcs);

    float factor = (tcs.y-lowerLimit) / (upperLimit-lowerLimit);
    factor = clamp(factor, 0.0, 1.0);
    out_Colour = mix(vec4(fogColour, 1.0), finalColour, factor);

}
