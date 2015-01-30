package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2dImpl;

/**
 * Created by ikari on 17/01/2015.
 */
public class InterruptorEvent {

    private InterruptorStatus status;
    public static final String status_FIELD = "status";

    private Point2dImpl position;
    public static final String position_FIELD = "position";

    public InterruptorStatus getStatus() {
        return status;
    }

    public void setStatus(InterruptorStatus status) {
        this.status = status;
    }

    public Point2dImpl getPosition() {
        return position;
    }

    public void setPosition(Point2dImpl position) {
        this.position = position;
    }

    public static InterruptorEvent create(InterruptorStatus status, Point2d position) {
        InterruptorEvent event = new InterruptorEvent();
        event.status = status;
        event.position = Point2dImpl.create(position);
        return event;
    }

}
