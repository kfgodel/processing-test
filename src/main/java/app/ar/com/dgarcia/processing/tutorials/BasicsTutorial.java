package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;

/**
 * This type implements tests the basic tutorial concepts from: https://processing.org/tutorials/drawing/
 * Created by ikari on 24/12/2014.
 */
public class BasicsTutorial extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { BasicsTutorial.class.getName() });
    }

    @Override
    public void setup() {
//        size(600, 600, P2D);
//        fill(255);
        size(200,200);
        rectMode(CENTER);
        rect(100,100,20,100);
        ellipse(100,70,60,60);
        ellipse(81,70,16,32);
        ellipse(119,70,16,32);
        line(90,150,80,160);
        line(110,150,120,160);
    }

    @Override
    public void draw() {
//        point(400,500);
//        line(100,200,500,200);
//        rect(100,202,400,200);
//
//        ellipseMode(CENTER);
//        ellipse(50,100,100,200);
    }
}
