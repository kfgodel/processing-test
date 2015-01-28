package app.ar.com.dgarcia.processing.sandbox.lamp;


import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;

/**
 * Created by ikari on 27/01/2015.
 */
public class LampEvent {

    private LampStatus status;
    public static final String status_FIELD = "status";
    
    private int positionX;
    public static final String positionX_FIELD = "positionX";

    private int positionY;
    public static final String positionY_FIELD = "positionY";


    public LampStatus getStatus() {
        return status;
    }

    public void setStatus(LampStatus status) {
        this.status = status;
    }

    public Point2d getPosition(){
        return Point2d.create(()-> positionX, ()-> positionY);
    }
}
