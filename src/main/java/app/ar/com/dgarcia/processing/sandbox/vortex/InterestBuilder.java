package app.ar.com.dgarcia.processing.sandbox.vortex;

import ar.com.kfgodel.diamond.api.Diamond;
import ar.com.kfgodel.diamond.api.fields.TypeField;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This type helps on the construction of interests to limit the set of manipulated messages
 * Created by ikari on 28/01/2015.
 */
public class InterestBuilder {

    public static InterestBuilder create() {
        InterestBuilder builder = new InterestBuilder();
        return builder;
    }

    /**
     * This method creates an interest that will restrict circulating messages to the ones that can be converted to instances of the given type.<br>
     *     The interest will contain messages that have all of the fields as keys, and type restrictions on the values according to the given
     *     type. This will be recursively applied to non primitive types
     * @param anInterpretationType The type to use as a model of the applicable restrictions
     * @return The created interest
     */
    public RestrictedInterest forMessagesLike(Class<?> anInterpretationType) {
        if(isNotASupportedType(anInterpretationType)){
            throw new IllegalArgumentException("Only non-primitive, non-collection types are allowed: " + anInterpretationType);
        }
        String propertyName = null;
        return createNestedTypeRestriction(propertyName, anInterpretationType);
    }

    private RestrictedInterest createNestedTypeRestriction(String propertyName, Class<?> anInterpretationType) {
        List<PropertyRestriction> fieldRestrictions = Diamond.of(anInterpretationType).fields().all()
                .filter(TypeField::isInstanceMember)
                .map(this::createRestrictionFor)
                .collect(Collectors.toList());
        RestrictedInterest interest = RestrictedInterest.create(propertyName);
        fieldRestrictions.forEach(interest::addRestriction);
        return interest;
    }

    private boolean isNotASupportedType(Class<?> anInterpretationType) {
        return anInterpretationType.isPrimitive()
                || Number.class.isAssignableFrom(anInterpretationType)
                || CharSequence.class.isAssignableFrom(anInterpretationType)
                || Enum.class.isAssignableFrom(anInterpretationType)
                || anInterpretationType.getComponentType() != null
                || Collection.class.isAssignableFrom(anInterpretationType)
                || Map.class.isAssignableFrom(anInterpretationType);
    }

    private PropertyRestriction createRestrictionFor(TypeField typeField) {
        Class<?> nativeType = typeField.type().nativeTypes().get();
        if(nativeType.isPrimitive()
                || Number.class.isAssignableFrom(nativeType)
                || CharSequence.class.isAssignableFrom(nativeType)
                || nativeType.getComponentType() != null
                || Collection.class.isAssignableFrom(nativeType)
                || Map.class.isAssignableFrom(nativeType)){
            // We ask only that the property exists
            return createUnspecificTypeRestriction(typeField.name());
        }
        if(Enum.class.isAssignableFrom(nativeType)){
            Class<? extends Enum> enumType = (Class<? extends Enum>) nativeType;
            return createEnumTypeRestriction(typeField.name(), enumType);
        }
        return createNestedTypeRestriction(typeField.name(), nativeType);
    }

    private PropertyRestriction createUnspecificTypeRestriction(String propertyName) {
        return RequiredProperty.create(propertyName);
    }

    private PropertyRestriction createEnumTypeRestriction(String propertyName, Class<? extends Enum> enumType) {
        Enum[] enumConstants = enumType.getEnumConstants();
        Set<String> restrictedValues = Arrays.stream(enumConstants)
                .map(Enum::name)
                .collect(Collectors.toSet());
        return RestrictedValueSet.create(propertyName, restrictedValues);
    }
}
