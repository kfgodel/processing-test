package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This type verifies the manipulation of images and pixels from: https://processing.org/tutorials/pixels/
 * Created by ikari on 25/12/2014.
 */
public class ImagesAndPixels extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ImagesAndPixels.class.getName() });
    }

    PImage img;

    public void setup() {
        size(320,240);
        // Make a new instance of a PImage by loading an image file
        img = loadImage("mysummervacation.jpg");
    }

    public void draw() {
        background(0);
        // Draw the image to the screen at coordinate (0,0)
        tint(128,128);
        image(img,0,0);
    }

}
