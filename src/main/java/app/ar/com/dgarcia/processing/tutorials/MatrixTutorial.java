package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;

/**
 * This type tests the tutorial code from: https://processing.org/tutorials/color/
 * Created by ikari on 24/12/2014.
 */
public class MatrixTutorial extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { MatrixTutorial.class.getName() });
    }

    @Override
    public void setup() {
        size(200,200);
    }

    @Override
    public void draw() {
        if (frameCount % 10 == 0) {
            fill(frameCount * 3 % 255, frameCount * 5 % 255,
                    frameCount * 7 % 255);
            pushMatrix();
            translate(100, 100);
            rotate(radians(frameCount * 2  % 360));
            rect(0, 0, 80, 20);
            popMatrix();
        }

        float angle = atan2(mouseY - 100, mouseX - 100);

        pushMatrix();
        translate(100, 100);
        rotate(angle);
        rect(0, 0, 10, 50);
        popMatrix();
    }
}
