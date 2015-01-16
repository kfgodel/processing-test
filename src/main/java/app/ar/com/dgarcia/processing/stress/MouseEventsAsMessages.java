package app.ar.com.dgarcia.processing.stress;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * This type measures the performance of creating messages for mouse events and dealing with them as messages
 * Created by ikari on 12/01/2015.
 */
public class MouseEventsAsMessages extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[]{MouseEventsPerSecond.class.getName()});
    }

    private PFont font;
    private FpsMeasurer fps;
    private long lastDrawMoment;

    @Override
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
        long currentMoment = System.currentTimeMillis();
        long elapsed = currentMoment - lastDrawMoment;
        if(elapsed < 1000){
            return;
        }
        lastDrawMoment = currentMoment;
        background(255);
        text(String.format("FPS: %.3f", fps.getCurrentFps()), 10, 100);
    }

    @Override
    public void mouseMoved() {
        fps.startingFrame();
    }

}
