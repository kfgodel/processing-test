package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.Map;

/**
 * Created by ikari on 28/01/2015.
 */
public interface PropertyRestriction {
    boolean isMetBy(Map<String, Object> message);

    String getPropertyName();

    boolean intersects(PropertyRestriction otherRestriction);
}
