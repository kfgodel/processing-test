package app.ar.com.dgarcia.processing.stress;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * This sketch tries to measure fps on the most basic processing setup
 * Created by ikari on 24/12/2014.
 */
public class FpsVacio extends PApplet {

    private PFont font;
    private FpsMeasurer fps;
    private long lastDrawMoment;

    static public void main(String args[]) {
        PApplet.main(new String[] { FpsVacio.class.getName() });
    }

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(1024, 780, OPENGL);
        smooth();
        font = createFont("Arial", 16, true);
        textFont(font, 22);
        fill(0);
        fps = FpsMeasurer.create();
        frameRate(100000);
    }

    @Override
    public void draw() {
        fps.startingFrame();
        long currentMoment = System.currentTimeMillis();
        long elapsed = currentMoment - lastDrawMoment;
        if(elapsed < 1000){
            return;
        }
        lastDrawMoment = currentMoment;
        background(255);
        text(String.format("FPS: %.3f", fps.getCurrentFps()) ,10,100);
    }
}
