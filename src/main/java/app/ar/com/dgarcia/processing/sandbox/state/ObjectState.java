package app.ar.com.dgarcia.processing.sandbox.state;

/**
 * This type represents a generic representation of an object internal state, that can change during runtime.<br>
 *     The state is divided into parts that are references to other objects, methods, values, etc
 * Created by ikari on 15/01/2015.
 */
public interface ObjectState {

    /**
     * Gets the current value of the given part name.
     * Depending on the implementation of this type an error or null may be returned when not found
     * @param partName The name that identifies the part of this state
     * @param <T> The type for the expected part
     * @return The existing part value
     */
    <T> T getPart(String partName);

    /**
     * Defines the value of a part with a given name.<br>
     *     It replaces the current value if defined
     * @param partName The name to give to a part
     * @param part The value to give to that part
     */
    void setPart(String partName, Object part);
}
