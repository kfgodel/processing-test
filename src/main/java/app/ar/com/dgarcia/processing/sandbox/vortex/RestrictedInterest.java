package app.ar.com.dgarcia.processing.sandbox.vortex;

import ar.com.kfgodel.vortex.api.manifest.VortexInterest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This type represents a set of restrictions that can be applied to a property or a message
 * Created by ikari on 29/01/2015.
 */
public class RestrictedInterest implements VortexInterest, PropertyRestriction {

    private List<PropertyRestriction> propertyRestrictions;
    private String propertyName;

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public boolean intersects(PropertyRestriction otherRestriction) {
        return Intersections.intersects(this, otherRestriction);
    }

    @Override
    public boolean intersects(VortexInterest otherInterest) {
        if(!(otherInterest instanceof RestrictedInterest)){
            // for the moment we don't know how to play with other types
            return false;
        }
        return Intersections.intersects(this, (RestrictedInterest)otherInterest);
    }

    @Override
    public boolean contains(Object message) {
        if(!(message instanceof  Map)){
            // We cannot interpret the content
            return false;
        }
        Map<String, Object> messageAsMap = (Map<String, Object>) message;
        return isMetBy(messageAsMap);
    }

    @Override
    public boolean isMetBy(Map<String, Object> message) {
        for (PropertyRestriction restriction : propertyRestrictions) {
            if(!restriction.isMetBy(message)){
                return false;
            }
        }
        return true;
    }

    public List<PropertyRestriction> getPropertyRestrictions() {
        return propertyRestrictions;
    }

    public PropertyRestriction getRestrictionFor(String propertyName) {
        return propertyRestrictions.stream()
                .filter((restriction)-> restriction.getPropertyName().equals(propertyName))
                .findFirst().orElse(null);
    }

    public static RestrictedInterest create() {
        RestrictedInterest interest = new RestrictedInterest();
        interest.propertyRestrictions = new ArrayList<>();
        return interest;
    }

    public void addRestriction(PropertyRestriction restriction) {
        this.propertyRestrictions.add(restriction);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+": " + this.propertyRestrictions.toString();
    }

}
