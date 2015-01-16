package app.ar.com.dgarcia.processing.sandbox;

/**
 * This type represents a generic representation of an object internal state, that can change during runtime
 * Created by ikari on 15/01/2015.
 */
public interface ObjectState {
    <T> T getPart(int partIndex);

    void setPart(int i, Object part);
}
