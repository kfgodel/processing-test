package app.ar.com.dgarcia.processing.sandbox.lamp;

import processing.core.PApplet;

/**
 * This type represents a lamp identity
 * Created by ikari on 15/01/2015.
 */
public interface Lamp {
    /**
     * Inverts the state of this lamp
     */
    void toggle();

    void drawOn(PApplet applet);

}
