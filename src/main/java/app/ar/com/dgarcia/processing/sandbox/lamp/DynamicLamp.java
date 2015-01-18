package app.ar.com.dgarcia.processing.sandbox.lamp;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.state.StatefulObject;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexNode;
import processing.core.PApplet;

/**
 * This type represents a lamp that can change its on/off state and the according representation
 * Created by ikari on 16/01/2015.
 */
public class DynamicLamp extends StatefulObject implements Lamp {

    public static final String VORTEX_NODE = "VORTEX_NODE";
    public static final String STATUS = "STATUS";
    public static final String POSITION = "POSITION";

    private Point2d getPosition(){
        return getState().getPart(POSITION);
    }
    private void setPosition(Point2d position){
        getState().setPart(POSITION, position);
    }

    private LampStatus getStatus() {
        return getState().<LampStatus>getPart(STATUS);
    }
    public void setStatus(LampStatus status) {
        getState().setPart(STATUS, status);
    }

    private VortexNode getNode(){
        return getState().getPart(VORTEX_NODE);
    }
    private void setNode(VortexNode node){
        getState().setPart(VORTEX_NODE, node);
    }


    @Override
    public void turnOff() {
        setStatus(LampStatus.OFF);
    }

    @Override
    public void turnOn() {
        setStatus(LampStatus.ON);
    }

    @Override
    public void drawOn(PApplet applet) {
        getStatus().drawOn(applet, getPosition());
    }

    public static DynamicLamp create(VortexNode nodo, Point2d position) {
        DynamicLamp lamp = new DynamicLamp();
        lamp.setPosition(position);
        lamp.turnOff();
        lamp.setNode(nodo);
        lamp.listenToEvents();
        return lamp;
    }

    private void listenToEvents() {
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + getState().toString();
    }
}
