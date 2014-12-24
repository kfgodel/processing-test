package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;

/**
 * This type tests the example 3.2 from learning processing: http://www.learningprocessing.com/examples/chapter-3/example-3-2/
 * Created by ikari on 24/12/2014.
 */
public class Example3_2 extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { Example3_2.class.getName() });
    }


    // Learning Processing
// Daniel Shiffman
// http://www.learningprocessing.com

// Example 3-2: mouseX and mouseY

    public void setup() {
        size(200,200);
    }

    public void draw() {
        // Try moving background() to setup() and see the difference!
        background(255);

        // Body
        stroke(0);
        fill(175);
        rectMode(CENTER);

        // mouseX is a keyword that the sketch replaces with the horizontal position of the mouse.
        // mouseY is a keyword that the sketch replaces with the vertical position of the mouse.
        rect(mouseX,mouseY,50,50);
    }
}
