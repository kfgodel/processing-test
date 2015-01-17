package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.state.StatefulObject;
import processing.core.PApplet;

/**
 * Created by ikari on 17/01/2015.
 */
public class DynamicInterruptor extends StatefulObject implements Interruptor {

    public static final String STATUS = "STATUS";
    public static final String POSITION = "POSITION";

    private Point2d getPosition(){
        return getState().getPart(POSITION);
    }
    private void setPosition(Point2d position){
        getState().setPart(POSITION, position);
    }

    private InterruptorStatus getStatus() {
        return getState().<InterruptorStatus>getPart(STATUS);
    }
    public void setStatus(InterruptorStatus status) {
        getState().setPart(STATUS, status);
    }

    @Override
    public void toggle() {
        getStatus().toggle(this);
    }

    @Override
    public void drawOn(PApplet applet) {
        getStatus().drawOn(applet, getPosition());
    }

    public static DynamicInterruptor create(Point2d position) {
        DynamicInterruptor interruptor = new DynamicInterruptor();
        interruptor.setPosition(position);
        InterruptorStatus.ON.toggle(interruptor);
        return interruptor;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + getState().toString();
    }

}
