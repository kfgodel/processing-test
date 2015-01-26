package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.state.StatefulObject;
import ar.com.kfgodel.vortex.api.VortexEndpoint;
import ar.com.kfgodel.vortex.impl.manifest.AllInterest;
import ar.com.kfgodel.vortex.impl.manifest.EmitterManifestImpl;
import processing.core.PApplet;

import java.util.function.Consumer;

/**
 * Created by ikari on 17/01/2015.
 */
public class DynamicInterruptor extends StatefulObject implements Interruptor {

    public static final String STATUS = "STATUS";
    public static final String POSITION = "POSITION";
    public static final String VORTEX_NODE = "VORTEX_NODE";
    public static final String VORTEX_STREAM = "VORTEX_STREAM";

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

    private VortexEndpoint getNode(){
        return getState().getPart(VORTEX_NODE);
    }
    private void setNode(VortexEndpoint node){
        getState().setPart(VORTEX_NODE, node);
    }

    private Consumer<Object> getStream(){return getState().getPart(VORTEX_STREAM);}
    private void setStream(Consumer<Object> stream){getState().setPart(VORTEX_STREAM, stream);}

    @Override
    public void toggle() {
        getStatus().toggle(this);
    }

    public void turnOn() {
        changeStatusTo(InterruptorStatus.ON);
    }

    private void changeStatusTo(InterruptorStatus newStatus) {
        this.setStatus(newStatus);
        Consumer<Object> currentStream = getStream();
        if(currentStream == null){
            //No interested receivers
            return;
        }
        currentStream.accept(InterruptorEvent.create(newStatus));
    }

    public void turnOff() {
        changeStatusTo(InterruptorStatus.OFF);
    }

    @Override
    public void drawOn(PApplet applet) {
        getStatus().drawOn(applet, getPosition());
    }

    @Override
    public boolean collisions(Point2d mousePosition) {
        Point2d distance = mousePosition.relativeTo(getPosition()).absolutize();
        boolean insideHeight = distance.getY().getAsInt() < InterruptorRepresentation.interruptorHeight() / 2;
        boolean insideWidth = distance.getX().getAsInt() < InterruptorRepresentation.interruptorWidth() / 2;
        return insideHeight && insideWidth;
    }


    public static DynamicInterruptor create(VortexEndpoint nodo, Point2d position) {
        DynamicInterruptor interruptor = new DynamicInterruptor();
        interruptor.setPosition(position);
        interruptor.turnOff();
        interruptor.setNode(nodo);
        interruptor.startCommunications();
        return interruptor;
    }

    private void startCommunications() {
        EmitterManifestImpl producerManifest = EmitterManifestImpl.create(AllInterest.INSTANCE, this::setStream);
        getNode().declareEmitter(producerManifest);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + getState().toString();
    }

}
