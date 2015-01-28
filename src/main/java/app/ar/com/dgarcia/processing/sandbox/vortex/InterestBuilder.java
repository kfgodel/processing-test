package app.ar.com.dgarcia.processing.sandbox.vortex;

import ar.com.kfgodel.vortex.api.manifest.VortexInterest;

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
    public VortexInterest toReceiveInstancesOf(Class<?> anInterpretationType) {
        return null;
    }
}
