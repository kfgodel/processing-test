package app.ar.com.dgarcia.processing.sandbox.lamp;

/**
 * This type represents a lamp identity
 * Created by ikari on 15/01/2015.
 */
public interface Lamp extends app.ar.com.dgarcia.processing.sandbox.draws.Drawable {

    void turnOn();

    void turnOff();

    void toggleEvents();
}
