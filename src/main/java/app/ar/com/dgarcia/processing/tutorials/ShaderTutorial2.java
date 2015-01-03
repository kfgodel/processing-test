package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;
import processing.core.PShape;
import processing.opengl.PShader;

/**
 * This type addexamples to the code from: https://processing.org/tutorials/pshader/
 * Created by ikari on 31/12/2014.
 */
public class ShaderTutorial2 extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ShaderTutorial2.class.getName() });
    }

    PShape can;
    float angle;
    PShader colorShader;

    public void setup() {
        size(640, 360, P3D);
        can = createCan(100, 200, 32);
        colorShader = loadShader("colorfrag.glsl", "colorvert.glsl");
    }

    public void draw() {
        shader(colorShader);
        background(0);
        translate(width/2, height/2);
        rotateY(angle);
        shape(can);
        angle += 0.01;
    }

    PShape createCan(float r, float h, int detail) {
        textureMode(NORMAL);
        PShape sh = createShape();
        sh.beginShape(QUAD_STRIP);
        sh.fill(255, 255, 0);
        sh.noStroke();
        for (int i = 0; i <= detail; i++) {
            float angle = TWO_PI / detail;
            float x = sin(i * angle);
            float z = cos(i * angle);
            float u = i / (float)detail;
            sh.normal(x, 0, z);
            sh.vertex(x * r, -h/2, z * r, u, 0);
            sh.vertex(x * r, +h/2, z * r, u, 1);
        }
        sh.endShape();
        return sh;
    }
}
