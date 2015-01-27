package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;

/**
 * Created by ikari on 17/01/2015.
 */
public class InterruptorEvent {

    private InterruptorStatus status;
    private int positionX;
    private int positionY;

    public InterruptorStatus getStatus() {
        return status;
    }

    public void setStatus(InterruptorStatus status) {
        this.status = status;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public static InterruptorEvent create(InterruptorStatus status, Point2d position) {
        InterruptorEvent event = new InterruptorEvent();
        event.status = status;
        event.positionX = position.getX().getAsInt();
        event.positionY = position.getY().getAsInt();
        return event;
    }

}
