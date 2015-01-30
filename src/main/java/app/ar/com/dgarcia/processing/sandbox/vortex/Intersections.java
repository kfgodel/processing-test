package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.Set;

/**
 * This type represents the rules of intersection between interests and restrictions
 * Created by ikari on 29/01/2015.
 */
public class Intersections {

    public static boolean intersects(PropertyRestriction oneRestriction, PropertyRestriction otherRestriction){
        if(oneRestriction instanceof RequiredProperty){
            return intersects((RequiredProperty) oneRestriction, otherRestriction);
        }
        if(oneRestriction instanceof RestrictedValueSet){
            return intersects((RestrictedValueSet) oneRestriction, otherRestriction);
        }
        if(oneRestriction instanceof RestrictedInterest){
            return intersects((RestrictedInterest) oneRestriction, otherRestriction);
        }
        throw new IntersectionException("Unknown restriction type: " + oneRestriction);
    }

    public static boolean intersects(RestrictedValueSet oneRestriction, PropertyRestriction otherRestriction){
        if(otherRestriction instanceof RequiredProperty){
            return intersects((RequiredProperty)otherRestriction, oneRestriction);
        }
        if(otherRestriction instanceof RestrictedValueSet){
            return intersects(oneRestriction, (RestrictedValueSet)otherRestriction);
        }
        if(otherRestriction instanceof RestrictedInterest){
            return intersects(oneRestriction, (RestrictedInterest)otherRestriction);
        }
        throw new IntersectionException("Unknown restriction type: " + oneRestriction);
    }

    public static boolean intersects(RestrictedInterest oneRestriction, PropertyRestriction otherRestriction){
        if(otherRestriction instanceof RequiredProperty){
            return intersects((RequiredProperty)otherRestriction, oneRestriction);
        }
        if(otherRestriction instanceof RestrictedValueSet){
            return intersects((RestrictedValueSet)otherRestriction, oneRestriction);
        }
        if(otherRestriction instanceof RestrictedInterest){
            return intersects(oneRestriction, (RestrictedInterest)otherRestriction);
        }
        throw new IntersectionException("Unknown restriction type: " + oneRestriction);
    }

    public static boolean intersects(RequiredProperty oneRestriction, PropertyRestriction otherRestriction){
        // As one of the restrictions is that the property is mandatory, it doesn't matter how specific the other is,
        // there's an intersection that depends on how small the second restriction is, but it's an intersection.<br>
        // There's no way for the second restriction to specify a set that doesn't intersect the first one, unless
        // it's empty or it's a negation of the first, which seems impossible at this point
        return true;
    }

    public static boolean intersects(RestrictedValueSet oneRestriction, RestrictedValueSet otherRestriction){
        // If one of the values in one restriction is contained in the other, then there's an possible intersection
        Set<?> oneValues = oneRestriction.getAllowedValues();
        Set<?> otherValues = otherRestriction.getAllowedValues();
        boolean atLeastOneValueIsInOtherValuesSet = oneValues.stream()
                .anyMatch(otherValues::contains);
        return atLeastOneValueIsInOtherValuesSet;
    }

    public static boolean intersects(RestrictedValueSet oneRestriction, RestrictedInterest otherRestriction){
        //As the first restriction is a set of values, the only intersection possible is that the second restriction
        //accepts any of the values. That will mean that one of the restricted values matches the second restriction.
        //Otherwise there's no way to intersect (probably one is a set of primitives and the second is for objects)
        Set<?> oneValues = oneRestriction.getAllowedValues();
        for (Object oneValue : oneValues) {
            if(otherRestriction.contains(oneValue)){
                return true;
            }
        }
        return false;
    }

    public static boolean intersects(RestrictedInterest oneSet, RestrictedInterest otherSet){
        // For two set of restrictions to intersect, we need a shared space (otherwise the operate in different spaces
        // and there's no intersection) and we need the restrictions in the same space to intersect each other.
        // The shared space is needed because we can have a 3d space on x,y,z and a 3d space on u,v,w and there's no possible intersection.
        // If one of the restriction doesn't intersect the other for the same dimension it means the are on the same space,
        // but different sub-space. Like two rect lines in a 3d space that don't touch each other.
        boolean sharedSpace = false;
        for (PropertyRestriction oneRestriction : oneSet.getPropertyRestrictions()) {
            String propertyName = oneRestriction.getPropertyName();
            PropertyRestriction otherRestriction = otherSet.getRestrictionFor(propertyName);
            if(otherRestriction == null){
                // It's a restriction not found on the second set. Let's skip it
                continue;
            }
            // If we have a common restriction, we have a common space
            sharedSpace = true;
            if(!Intersections.intersects(oneRestriction, otherRestriction)){
                // Same dimension different sub-spaces, => no intersection
                return false;
            }
        }
        // Now it depends if we had a shared space or not
        return sharedSpace;

    }

}
