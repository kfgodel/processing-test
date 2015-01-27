package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.collisions.MouseClickable;
import app.ar.com.dgarcia.processing.sandbox.draws.Drawable;
import processing.core.PApplet;

/**
 * This type represents an interruptor
 * Created by ikari on 17/01/2015.
 */
public interface Interruptor extends MouseClickable, Drawable {

    void toggle();

    void drawOn(PApplet applet);

}
