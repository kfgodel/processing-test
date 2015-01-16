package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

/**
 * This type draws two states of a lamp
 * Created by ikari on 13/01/2015.
 */
public class LampSketch extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { LampSketch.class.getName() });
    }

    private Lamp dynamicLamp;

    @Override
    public void setup() {
        size(1024,768);

        dynamicLamp = LampState.create(Point2d.centerOf(this).toTheTop(50));
    }

    private void drawFrame() {
        background(200);
        Point2d fromCenterPoint = Point2d.centerOf(this);
        LampBehavior.drawActiveOn(this, fromCenterPoint.toTheRight(30));
        LampBehavior.drawInactiveOn(this, fromCenterPoint.toTheLeft(30));
        dynamicLamp.drawOn(this);
    }

    @Override
    public void draw() {
        drawFrame();
    }

    @Override
    public void mouseClicked() {
        dynamicLamp.toggle();
    }
}
