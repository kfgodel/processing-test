package app;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * Created by ikari on 24/12/2014.
 */
public class FpsVacio extends PApplet {

    private PFont font;
    private FpsMeasurer fps;

    static public void main(String args[]) {
        PApplet.main(new String[] { FpsVacio.class.getName() });
    }

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(400, 400, OPENGL);
        smooth();
        font = createFont("Arial", 16, true);
        textFont(font,36);
        fill(0);
        fps = FpsMeasurer.create();
    }

    @Override
    public void draw() {
        fps.startingFrame();
        background(255);
        text("FPS: " + fps.getCurrentFps(),10,100);
    }
}
