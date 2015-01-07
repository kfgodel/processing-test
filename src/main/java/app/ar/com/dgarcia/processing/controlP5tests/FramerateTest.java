package app.ar.com.dgarcia.processing.controlP5tests;

import controlP5.ControlP5;
import processing.core.PApplet;

/**
 * Created by ikari on 07/01/2015.
 */
public class FramerateTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { FramerateTest.class.getName() });
    }

    ControlP5 cp5;

    public void setup() {
        size(400,500);
        frameRate(30);
        cp5 = new ControlP5(this);
        cp5.addFrameRate().setInterval(10).setPosition(0,height - 10);

    }

    public void draw() {
        background(129);
    }
}
