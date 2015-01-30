package app.ar.com.dgarcia.processing.sandbox.vortex;

/**
 * Created by ikari on 29/01/2015.
 */
public class IntersectionException extends RuntimeException {

    public IntersectionException(String message) {
        super(message);
    }

    public IntersectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntersectionException(Throwable cause) {
        super(cause);
    }
}
