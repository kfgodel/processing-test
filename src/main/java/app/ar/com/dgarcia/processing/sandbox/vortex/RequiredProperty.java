package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.Map;

/**
 * This type represents a restriction on a property that must be present on the message (without restrictions over the values)
 * Created by ikari on 28/01/2015.
 */
public class RequiredProperty implements PropertyRestriction {

    private String propertyName;

    @Override
    public boolean isMetBy(Map<String, Object> message) {
        return message.containsKey(propertyName);
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public boolean intersects(PropertyRestriction otherRestriction) {
        // If the other restriction is for the same property then we intersect
        // either is more restrictive than us, or equally restrictive
        return true;
    }

    public static RequiredProperty create(String propertyName) {
        RequiredProperty property = new RequiredProperty();
        property.propertyName = propertyName;
        return property;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +"{" + propertyName + "}";
    }
}
