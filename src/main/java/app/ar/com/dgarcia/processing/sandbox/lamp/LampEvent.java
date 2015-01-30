package app.ar.com.dgarcia.processing.sandbox.lamp;


import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2dImpl;

/**
 * Created by ikari on 27/01/2015.
 */
public class LampEvent {

    private LampStatus status;
    public static final String status_FIELD = "status";

    private Point2dImpl position;
    public static final String position_FIELD = "position";


    public LampStatus getStatus() {
        return status;
    }

    public void setStatus(LampStatus status) {
        this.status = status;
    }

    public Point2d getPosition(){
        return this.position;
    }
}
