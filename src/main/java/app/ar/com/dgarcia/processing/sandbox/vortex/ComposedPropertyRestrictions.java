package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This type represents a composed set of restrictions for the same property
 * Created by ikari on 28/01/2015.
 */
public class ComposedPropertyRestrictions implements PropertyRestriction {

    private String propertyName;
    private List<PropertyRestriction> composedRestrictions;

    @Override
    public boolean isMetBy(Map<String, Object> message) {
        for (PropertyRestriction composedRestriction : composedRestrictions) {
            if(!composedRestriction.isMetBy(message)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public boolean intersects(PropertyRestriction otherRestriction) {
        for (PropertyRestriction thisRestriction : composedRestrictions) {
            if(!thisRestriction.intersects(otherRestriction)){
                return false;
            }
        }
        return true;
    }

    public static ComposedPropertyRestrictions create() {
        ComposedPropertyRestrictions restrictions = new ComposedPropertyRestrictions();
        restrictions.composedRestrictions = new ArrayList<>();
        return restrictions;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + this.composedRestrictions.toString();
    }
}
