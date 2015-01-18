package app.ar.com.dgarcia.processing.sandbox;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.interruptor.DynamicInterruptor;
import app.ar.com.dgarcia.processing.sandbox.interruptor.Interruptor;
import app.ar.com.dgarcia.processing.sandbox.lamp.DynamicLamp;
import app.ar.com.dgarcia.processing.sandbox.lamp.Lamp;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexNode;
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
    private Interruptor dynamicInterruptor;
    private VortexNode nodo;

    @Override
    public void setup() {
        size(1024,768);

        dynamicInterruptor = DynamicInterruptor.create(nodo, Point2d.centerOf(this).toTheBottom(50));
        dynamicLamp = DynamicLamp.create(nodo, Point2d.centerOf(this).toTheTop(50));
    }

    private void drawFrame() {
        background(200);
        GridRepresentation.drawOn(this);

        dynamicLamp.drawOn(this);
        dynamicInterruptor.drawOn(this);
    }

    @Override
    public void draw() {
        drawFrame();
    }

    @Override
    public void mouseClicked() {
        Point2d mousePosition = Point2d.create(() -> mouseX, () -> mouseY);
        if(dynamicInterruptor.collisions(mousePosition)){
            dynamicInterruptor.toggle();
        }
    }
}
