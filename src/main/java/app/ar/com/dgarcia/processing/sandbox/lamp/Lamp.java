package app.ar.com.dgarcia.processing.sandbox.lamp;

import processing.core.PApplet;

/**
 * This type represents a lamp identity
 * Created by ikari on 15/01/2015.
 */
public interface Lamp {

    void drawOn(PApplet applet);

    void turnOn();

    void turnOff();

    void toggleEvents();
}
