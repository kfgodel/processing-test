package app.ar.com.dgarcia.processing.sandbox.collisions;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;

/**
 * Created by ikari on 27/01/2015.
 */
public interface MouseClickable {
    boolean collisions(Point2d mousePosition);

    void handleMouseClick();
}
