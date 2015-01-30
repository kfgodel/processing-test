package app.ar.com.dgarcia.processing.sandbox.lamp;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2dImpl;
import app.ar.com.dgarcia.processing.sandbox.state.StatefulObject;
import app.ar.com.dgarcia.processing.sandbox.vortex.RequiredProperty;
import app.ar.com.dgarcia.processing.sandbox.vortex.RestrictedInterest;
import app.ar.com.dgarcia.processing.sandbox.vortex.RestrictedValueSetProperty;
import ar.com.dgarcia.colecciones.sets.Sets;
import ar.com.dgarcia.objectmapper.impl.TransformerMapper;
import ar.com.kfgodel.vortex.api.VortexEndpoint;
import ar.com.kfgodel.vortex.api.manifest.ReceiverManifest;
import ar.com.kfgodel.vortex.api.manifest.VortexInterest;
import ar.com.kfgodel.vortex.impl.manifest.AllInterest;
import ar.com.kfgodel.vortex.impl.manifest.NoInterest;
import ar.com.kfgodel.vortex.impl.manifest.ReceiverManifestImpl;
import processing.core.PApplet;

import java.util.Map;

/**
 * This type represents a lamp that can change its on/off state and the according representation
 * Created by ikari on 16/01/2015.
 */
public class DynamicLamp extends StatefulObject implements Lamp {

    public static final String VORTEX_NODE = "VORTEX_NODE";
    public static final String STATUS = "STATUS";
    public static final String POSITION = "POSITION";

    private ReceiverManifest vortexManifest;

    private Point2d closestInterruptor;

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

    private VortexEndpoint getNode(){
        return getState().getPart(VORTEX_NODE);
    }
    private void setNode(VortexEndpoint node){
        getState().setPart(VORTEX_NODE, node);
    }


    @Override
    public void turnOff() {
        setStatus(LampStatus.OFF);
    }

    @Override
    public void toggleEvents() {
        VortexInterest newInterest = (vortexManifest.getInterest() == AllInterest.INSTANCE)? NoInterest.INSTANCE: AllInterest.INSTANCE;
        vortexManifest.changeInterest(newInterest);
    }

    @Override
    public void turnOn() {
        setStatus(LampStatus.ON);
    }

    @Override
    public void drawOn(PApplet applet) {
        getStatus().drawOn(applet, getPosition());
    }

    public static DynamicLamp create(VortexEndpoint nodo, Point2d position) {
        DynamicLamp lamp = new DynamicLamp();
        lamp.setPosition(position);
        lamp.turnOff();
        lamp.setNode(nodo);
        lamp.listenToEvents();
        return lamp;
    }

    private void listenToEvents() {
        RestrictedInterest lampEventInterest = RestrictedInterest.create();
        lampEventInterest.addRestriction(RestrictedValueSetProperty.create(LampEvent.status_FIELD, Sets.newLinkedHashSet(LampStatus.ON.name(), LampStatus.OFF.name())));
        RestrictedInterest positionRestrictions = RestrictedInterest.create(LampEvent.position_FIELD);
        positionRestrictions.addRestriction(RequiredProperty.create(Point2dImpl.x_FIELD));
        positionRestrictions.addRestriction(RequiredProperty.create(Point2dImpl.y_FIELD));
        lampEventInterest.addRestriction(positionRestrictions);

        vortexManifest = ReceiverManifestImpl.create(lampEventInterest, () ->
                (message) -> onLampEventReceived((Map<String, Object>) message));
        getNode().declareReceiver(vortexManifest);
    }

    private void onLampEventReceived(Map<String, Object> message){
        LampEvent event = TransformerMapper.create().fromMap(message, LampEvent.class);
        if(isTheClosestInterruptor(event.getPosition())){
            event.getStatus().turn(this);
        }else{
            // We ignore other interruptors
        }
    }

    private boolean isTheClosestInterruptor(Point2d newInterruptor) {
        if(closestInterruptor == null || this.getPosition().distanceTo(newInterruptor) <= this.getPosition().distanceTo(closestInterruptor)){
            closestInterruptor = newInterruptor;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + getState().toString();
    }
}
