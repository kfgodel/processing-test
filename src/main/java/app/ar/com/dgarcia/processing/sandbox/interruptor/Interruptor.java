package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import processing.core.PApplet;

/**
 * Created by ikari on 17/01/2015.
 */
public interface Interruptor {

    void toggle();

    void drawOn(PApplet applet);

    boolean collisions(Point2d mousePosition);
}
