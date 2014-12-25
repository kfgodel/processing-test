package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;

/**
 * This type tests the tutorial code from: https://processing.org/tutorials/color/
 * Created by ikari on 24/12/2014.
 */
public class ColorTutorial extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ColorTutorial.class.getName() });
    }

    @Override
    public void setup() {
        size(200,200);
        background(255);    // Setting the background to white
        stroke(0);          // Setting the outline (stroke) to black
        fill(150);          // Setting the interior of a shape (fill) to grey
        rect(50, 50, 75, 100); // Drawing the rectangle

        noStroke();

        // Bright red
        fill(255,0,0);
        ellipse(20,20,16,16);

        // Dark red
        fill(127,0,0);
        ellipse(40,20,16,16);

        // Pink (pale red)
        fill(255,200,200);
        ellipse(60,20,16,16);



        fill(0,0,255);
        rect(0,0,100,200);

// 255 means 100% opacity.
        fill(255,0,0,255);
        rect(0,0,200,40);

// 75% opacity.
        fill(255,0,0,191);
        rect(0,50,200,40);

// 55% opacity.
        fill(255,0,0,127);
        rect(0,100,200,40);

// 25% opacity.
        fill(255,0,0,63);
        rect(0,150,200,40);
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
