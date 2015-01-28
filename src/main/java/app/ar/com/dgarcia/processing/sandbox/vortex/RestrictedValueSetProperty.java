package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.Map;
import java.util.Set;

/**
 * This type represents a restriction over the values of a property, that must be present and also have a value
 * contained by the restriction set
 * Created by ikari on 28/01/2015.
 */
public class RestrictedValueSetProperty implements PropertyRestriction {

    private String propertyName;
    private Set<?> allowedValues;

    @Override
    public boolean isMetBy(Map<String, Object> message) {
        Object messageValue = message.get(propertyName);
        return allowedValues.contains(messageValue);
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public boolean intersects(PropertyRestriction otherRestriction) {
        if(otherRestriction instanceof RequiredProperty){
            // Is less restrictive than us
            return true;
        }
        if(otherRestriction instanceof RestrictedValueSetProperty){
            // At least one of our values should be in the other restriction set to be instersection
            Set<?> otherValues = ((RestrictedValueSetProperty) otherRestriction).allowedValues;
            boolean atLeastOneValueIsInOtherRestriction = this.allowedValues.stream()
                    .anyMatch(otherValues::contains);
            return atLeastOneValueIsInOtherRestriction;
        }
        //It's a restriction type we don't know. Let's delegate
        return otherRestriction.intersects(this);
    }

    public static RestrictedValueSetProperty create(String propertyName, Set<?> allowedValues) {
        RestrictedValueSetProperty property = new RestrictedValueSetProperty();
        property.allowedValues = allowedValues;
        property.propertyName = propertyName;
        return property;
    }

    @Override
    public String toString() {
        return propertyName + " in " + allowedValues;
    }
}
