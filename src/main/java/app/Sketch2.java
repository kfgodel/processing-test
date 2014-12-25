package app;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by dgarcia on 08/09/2014.
 */
public class Sketch2 extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[]{"app.Sketch2"});
    }


    PGraphics pg;

    public void setup() {
        size(640, 360);
        pg = createGraphics(400, 200);
        frameRate(4);
    }

    public void draw() {
        fill(0, 12);
        rect(0, 0, width, height);
        fill(255);
        noStroke();
        ellipse(mouseX, mouseY, 60, 60);

        pg.beginDraw();
        pg.background(51);
        pg.noFill();
        pg.stroke(255);
        pg.ellipse(mouseX - 120, mouseY - 60, 60, 60);
        pg.endDraw();

        // Draw the offscreen buffer to the screen with image()
        image(pg, 120, 60);

    }

}