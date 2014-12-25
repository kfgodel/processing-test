package app.ar.com.dgarcia.processing.stress;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This type tests the capacity to draw frames while doing work on a secondary thread
 * Created by ikari on 24/12/2014.
 */
public class FpsConBackgroundProcessing extends PApplet {

    private PFont font;
    private FpsMeasurer fps;
    private long lastDrawMoment;
    private AtomicLong contador = new AtomicLong(0);

    static public void main(String args[]) {
        PApplet.main(new String[] { FpsConBackgroundProcessing.class.getName() });
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
        new Thread(()->{
            while(true){
                contador.incrementAndGet();
            }
        }).start();
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
        text(String.format("FPS: %.3f - contador: %d", fps.getCurrentFps(), contador.getAndSet(0)) ,10,100);
    }

}
