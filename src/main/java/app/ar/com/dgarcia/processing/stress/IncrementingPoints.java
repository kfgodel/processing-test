package app.ar.com.dgarcia.processing.stress;

import app.ar.com.dgarcia.processing.FpsMeasurer;
import processing.core.PApplet;
import processing.core.PFont;

import java.util.Random;

/**
 * This type verifies the maximum number of points drawabable for a target fps
 * Created by ikari on 25/12/2014.
 */
public class IncrementingPoints extends PApplet {

    private Random random;

    static public void main(String args[]) {
        PApplet.main(new String[]{IncrementingPoints.class.getName()});
    }

    private PFont font;
    private FpsMeasurer fps;
    private long elementCount = 0;

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(1024, 780, OPENGL);
        smooth();
        font = createFont("Arial", 16, true);
        textFont(font, 22);
        fill(0);
        fps = FpsMeasurer.create();
        frameRate(100000);
        random = new Random();
    }

    @Override
    public void draw() {
        fps.startingFrame();

        noFill();
        for (int i = 0; i < elementCount; i++) {
            stroke(random.nextInt(256));
            float x = random(0, width);
            float y = random(0, height);
            point(x, y);
        }

        long increment;
        double currentFps = fps.getCurrentFps();
        if(currentFps > 60){
            increment = 100;
        }else if (currentFps > 30 ){
            increment = 10;
        }else{
            increment = 1;
        }

        elementCount += increment;

        stroke(0);
        fill(0);
        rect(7,80,300,30);
        fill(255);
        text(String.format("FPS: %.3f, points: %d", currentFps, elementCount) ,10,100);
    }
}
