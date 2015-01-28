package app.ar.com.dgarcia.processing.sandbox.vortex;

import ar.com.kfgodel.vortex.api.manifest.VortexInterest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This type represents an interest based on a map of keys
 * Created by ikari on 28/01/2015.
 */
public class MapBasedInterest implements VortexInterest {

    private List<PropertyRestriction> propertyRestrictions;

    @Override
    public boolean intersects(VortexInterest otherInterest) {
        if(!(otherInterest instanceof  MapBasedInterest)){
            // We don't know how to play with others yet
            return false;
        }
        MapBasedInterest other = (MapBasedInterest) otherInterest;

        boolean differentSpaces = true;
        for (PropertyRestriction thisRestriction : propertyRestrictions) {
            String propertyName = thisRestriction.getPropertyName();
            PropertyRestriction otherRestriction = other.getRestrictionFor(propertyName);
            if(otherRestriction == null){
                //It's not restricted as we do
                continue;
            }
            // At least one restriction is for same property (they share a space)
            differentSpaces = false;
            if(!thisRestriction.intersects(otherRestriction)){
                // We have disjoint restrictions
                return false;
            }
        }
        if(differentSpaces){
            // If the restrictions are for completely different spaces then there's no intersection
            // of interest or the definition of interest is incomplete (it's not explicit about all the
            // expected features of a message). In either case we need to consider only explicit restriction
            // otherwise empty restriction will match every message. In other words unknown messages
            // will match the interest only to be discarded on reception
            return false;
        }

        return true;
    }

    private PropertyRestriction getRestrictionFor(String propertyName) {
        return propertyRestrictions.stream()
                .filter((restriction)-> restriction.getPropertyName().equals(propertyName))
                .findFirst().orElse(null);
    }

    @Override
    public boolean contains(Object message) {
        if(!(message instanceof  Map)){
            // We cannot interpret the content
            return false;
        }
        Map<String, Object> messageAsMap = (Map<String, Object>) message;
        for (PropertyRestriction restriction : propertyRestrictions) {
            if(!restriction.isMetBy(messageAsMap)){
                return false;
            }
        }
        return true;
    }

    public static MapBasedInterest create() {
        MapBasedInterest interest = new MapBasedInterest();
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
