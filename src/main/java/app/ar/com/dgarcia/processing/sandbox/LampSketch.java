package app.ar.com.dgarcia.processing.sandbox;

import app.ar.com.dgarcia.processing.sandbox.collisions.MouseClickable;
import app.ar.com.dgarcia.processing.sandbox.draws.Drawable;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.interruptor.DynamicInterruptor;
import app.ar.com.dgarcia.processing.sandbox.lamp.DynamicLamp;
import app.ar.com.dgarcia.processing.sandbox.lamp.Lamp;
import ar.com.kfgodel.vortex.api.VortexEndpoint;
import ar.com.kfgodel.vortex.impl.connection.ConnectionHandlerImpl;
import ar.com.kfgodel.vortex.impl.connection.InMemoryNet;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * This type draws two states of a lamp
 * Created by ikari on 13/01/2015.
 */
public class LampSketch extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { LampSketch.class.getName() });
    }

    private Lamp dynamicLamp;
    private VortexEndpoint vortexEndpoint;
    private List<MouseClickable> clickables = new ArrayList<>();
    private List<Drawable> drawables = new ArrayList<>();

    @Override
    public void setup() {
        size(1024,768);

        InMemoryNet.create().connect(ConnectionHandlerImpl.create((connectedEndpoint) -> vortexEndpoint = connectedEndpoint));
        dynamicLamp = DynamicLamp.create(vortexEndpoint, Point2d.centerOf(this).toTheTop(50));
        addElement(dynamicLamp);
        addElement(DynamicInterruptor.create(vortexEndpoint, Point2d.centerOf(this).toTheBottom(50).toTheLeft(40)));
        addElement(DynamicInterruptor.create(vortexEndpoint, Point2d.centerOf(this).toTheBottom(50).toTheRight(40)));
    }

    private void addElement(Object any){
        if(any instanceof Drawable){
            drawables.add((Drawable) any);
        }
        if(any instanceof MouseClickable){
            clickables.add((MouseClickable) any);
        }
    }

    private void drawFrame() {
        background(200);
        GridRepresentation.drawOn(this);

        for (Drawable drawable : drawables) {
            drawable.drawOn(this);
        }
    }

    @Override
    public void draw() {
        drawFrame();
    }

    @Override
    public void mouseClicked() {
        if(mouseButton == RIGHT){
            dynamicLamp.toggleEvents();
        }else{
            Point2d mousePosition = Point2d.create(() -> mouseX, () -> mouseY);
            for (MouseClickable clickable : clickables) {
                if(clickable.collisions(mousePosition)){
                    clickable.handleMouseClick();
                }
            }
        }
    }
}
