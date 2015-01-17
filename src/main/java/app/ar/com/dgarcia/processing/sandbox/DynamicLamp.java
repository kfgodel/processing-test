package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

import java.util.function.BiConsumer;

/**
 * This type represents a lamp that can change its on/off state and the according representation
 * Created by ikari on 16/01/2015.
 */
public class DynamicLamp extends StatefulObject implements Lamp {

    public static final String TOGGLE_BEHAVIOR = "TOGGLE_BEHAVIOR";
    public static final String DRAW_BEHAVIOR = "DRAW_BEHAVIOR";
    public static final String POSITION = "POSITION";

    private Runnable getToggleBehavior(){
        return getState().getPart(TOGGLE_BEHAVIOR);
    }
    private void setToggleBehavior(Runnable toggleBehavior) {
        getState().setPart(TOGGLE_BEHAVIOR, toggleBehavior);
    }

    private BiConsumer<PApplet, Point2d> getDrawBehavior(){
        return getState().getPart(DRAW_BEHAVIOR);
    }
    private void setDrawBehavior(BiConsumer<PApplet, Point2d> drawBehavior) {
        getState().setPart(DRAW_BEHAVIOR, drawBehavior);
    }

    private Point2d getPosition(){
        return getState().getPart(POSITION);
    }
    private void setPosition(Point2d position){
        getState().setPart(POSITION, position);
    }

    @Override
    public void toggle() {
        getToggleBehavior().run();
    }

    @Override
    public void drawOn(PApplet applet) {
        getDrawBehavior().accept(applet, getPosition());
    }

    private void toggleOn(){
        setDrawBehavior(LampRepresentation::drawActiveOn);
        setToggleBehavior(this::toggleOff);
    }

    private void toggleOff(){
        setDrawBehavior(LampRepresentation::drawInactiveOn);
        setToggleBehavior(this::toggleOn);
    }

    public static DynamicLamp create(Point2d position) {
        DynamicLamp lamp = new DynamicLamp();
        lamp.setPosition(position);
        lamp.toggleOff();
        return lamp;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + getState().toString();
    }
}
