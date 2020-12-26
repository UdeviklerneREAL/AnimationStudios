#shader vertex
    #version 400 core

    layout (location = 0) in vec3 position;
    layout (location = 1) in vec2 textureCoordinates;

    out vec2 pass_textureCoordinates;

    void main(void) {
        gl_Position = vec4(position, 1.0);
        pass_textureCoordinates = textureCoordinates;
    }

#shader fragment
    #version 400 core

    in vec2 pass_textureCoordinates;

    out vec4 out_Color;

    uniform sampler2D textureSampler;

    void main(void) {
        out_Color = texture(textureSampler, pass_textureCoordinates);
    }

