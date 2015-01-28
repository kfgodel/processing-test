package app.ar.com.dgarcia.processing.sandbox.vortex;

/**
 * Created by ikari on 28/01/2015.
 */
public interface ValueRestriction {
    /**
     * Indicates if the value must be present to be acceptable message
     * @return false if the value is optional
     */
    boolean isValueRequired();
}
