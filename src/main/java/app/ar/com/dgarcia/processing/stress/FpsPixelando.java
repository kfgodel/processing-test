package app.ar.com.dgarcia.processing.stress;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * This type tests the drawing capabilities painting the screen with one pixel at the time
 * Created by ikari on 25/12/2014.
 */
public class FpsPixelando extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { FpsPixelando.class.getName() });
    }

    private PFont font;
    private FpsMeasurer fps;
    private int color = 255;

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(1024, 780, OPENGL);
        smooth();
        font = createFont("Arial", 16, true);
        textFont(font, 22);
        fps = FpsMeasurer.create();
        frameRate(100000);
        background(255);
    }

    @Override
    public void draw() {
        fps.startingFrame();

        // Before we deal with pixels
        loadPixels();
        // Loop through every pixel
        for (int i = 0; i < pixels.length; i++) {
            // Set pixel at that location to random color
            pixels[i] = color(color);
        }
        // When we are finished dealing with pixels
        updatePixels();

        color += 10;
        if(color > 255){
            color = 0;
        }

        fill(255);
        text(String.format("FPS: %.3f", fps.getCurrentFps()) ,10,100);
    }

}
