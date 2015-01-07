package app.ar.com.dgarcia.processing.controlP5tests;

import controlP5.ControlP5;
import controlP5.Controller;
import processing.core.PApplet;

/**
 * Created by ikari on 07/01/2015.
 */
public class NumberBoxTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { NumberBoxTest.class.getName() });
    }

    ControlP5 cp5;

    int myColorBackground = color(0,0,0);

    public float numberboxValue = 100;

    public void setup() {
        size(700,400);
        noStroke();
        cp5 = new ControlP5(this);

        cp5.addNumberbox("numberbox")
                .setPosition(100,160)
                .setSize(100,14)
                .setScrollSensitivity((float) 1.1)
                .setValue(50)
        ;


        cp5.addNumberbox("numberboxValue")
                .setPosition(100,200)
                .setSize(100,14)
                .setRange(0,200)
                .setMultiplier((float) 0.1) // set the sensitifity of the numberbox
                .setDirection(Controller.HORIZONTAL) // change the control direction to left/right
                .setValue(100)
        ;

    }

    public void draw() {
        background(myColorBackground);
        fill(numberboxValue);
        rect(0,0,width,100);
    }

    void numberbox(int theColor) {
        myColorBackground = color(theColor);
        println("a numberbox event. setting background to "+theColor);
    }
}
