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
    private long lastDrawMoment;
    private int color = 255;

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(1024, 780, OPENGL);
        smooth();
        font = createFont("Arial", 16, true);
        textFont(font, 22);
        fill(0);
        fps = FpsMeasurer.create();
        frameRate(100000);
        background(255);
    }

    @Override
    public void draw() {
        fps.startingFrame();

        stroke(color);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                point(x, y);
            }
        }
        color += 10;
        if(color > 255){
            color = 0;
        }

        fill(255);
        text(String.format("FPS: %.3f", fps.getCurrentFps()) ,10,100);
    }

}
