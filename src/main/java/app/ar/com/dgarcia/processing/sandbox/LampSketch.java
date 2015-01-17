package app.ar.com.dgarcia.processing.sandbox;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.interruptor.InterruptorRepresentation;
import app.ar.com.dgarcia.processing.sandbox.lamp.DynamicLamp;
import app.ar.com.dgarcia.processing.sandbox.lamp.Lamp;
import app.ar.com.dgarcia.processing.sandbox.lamp.LampRepresentation;
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

        dynamicLamp = DynamicLamp.create(Point2d.centerOf(this).toTheTop(50));
    }

    private void drawFrame() {
        background(200);
        GridRepresentation.drawOn(this);

        Point2d fromCenterPoint = Point2d.centerOf(this);
        LampRepresentation.drawActiveOn(this, fromCenterPoint.toTheRight(30));
        LampRepresentation.drawInactiveOn(this, fromCenterPoint.toTheLeft(30));
        dynamicLamp.drawOn(this);

        InterruptorRepresentation.drawActiveOn(this, fromCenterPoint.toTheBottom(50));
        InterruptorRepresentation.drawInactiveOn(this, fromCenterPoint.toTheBottom(50).toTheLeft(50));
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
