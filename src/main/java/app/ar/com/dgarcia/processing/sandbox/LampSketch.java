package app.ar.com.dgarcia.processing.sandbox;

import app.ar.com.dgarcia.processing.sandbox.collisions.MouseClickable;
import app.ar.com.dgarcia.processing.sandbox.draws.Drawable;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.interruptor.DynamicInterruptor;
import app.ar.com.dgarcia.processing.sandbox.lamp.DynamicLamp;
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

    private VortexEndpoint vortexEndpoint;
    private List<MouseClickable> clickables = new ArrayList<>();
    private List<Drawable> drawables = new ArrayList<>();

    @Override
    public void setup() {
        size(1024,768);

        InMemoryNet.create().connect(ConnectionHandlerImpl.create((connectedEndpoint) -> vortexEndpoint = connectedEndpoint));
        addElement(DynamicLamp.create(vortexEndpoint, Point2d.centerOf(this).toTheTop(50).toTheLeft(50)));
        addElement(DynamicLamp.create(vortexEndpoint, Point2d.centerOf(this).toTheTop(50).toTheRight(50)));
        addElement(DynamicInterruptor.create(vortexEndpoint, Point2d.centerOf(this).toTheBottom(50).toTheLeft(40)));
        addElement(DynamicInterruptor.create(vortexEndpoint, Point2d.centerOf(this).toTheBottom(50).toTheRight(40)));

        for (int i = 0; i < 10; i++) {
            int xPosition = (int) random(40, 960);
            int yPosition = (int) random(40, 700);
            addElement(DynamicLamp.create(vortexEndpoint, Point2d.create(()->xPosition,()->yPosition)));
        }

        for (int i = 0; i < 10; i++) {
            int xPosition = (int) random(40, 960);
            int yPosition = (int) random(40, 700);
            addElement(DynamicInterruptor.create(vortexEndpoint, Point2d.create(()->xPosition,()->yPosition)));
        }

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
        Point2d mousePosition = Point2d.create(() -> mouseX, () -> mouseY);
        for (MouseClickable clickable : clickables) {
            if(clickable.collisions(mousePosition)){
                clickable.handleMouseClick();
            }
        }
    }
}
