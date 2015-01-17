package app.ar.com.dgarcia.processing.sandbox;

/**
 * This type serves as a base class for objects that require an internal state
 * Created by ikari on 16/01/2015.
 */
public class StatefulObject {

    private ObjectState state = MapState.create();

    public ObjectState getState() {
        return state;
    }

    public void setState(ObjectState state) {
        this.state = state;
    }
}
