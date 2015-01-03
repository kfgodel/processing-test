package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;
import processing.opengl.PShader;

/**
 * This type implements the code from: https://processing.org/tutorials/pshader/
 * Created by ikari on 30/12/2014.
 */
public class ShaderTutorial extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ShaderTutorial.class.getName() });
    }

    PShader toon;

    public void setup() {
        size(640, 360, P3D);
        noStroke();
        fill(204);
        toon = loadShader("ToonFrag.glsl", "ToonVert.glsl");
        toon.set("fraction", 1.0f);
    }

    public void draw() {
        shader(toon);
        background(0);
        float dirY = (mouseY / (float)height - 0.5f) * 2f;
        float dirX = (mouseX / (float)width - 0.5f) * 2f;
        directionalLight(204, 204, 204, -dirX, -dirY, -1);
        translate(width / 2, height / 2);
        sphere(120);
    }

}
