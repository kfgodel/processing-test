package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.List;

/**
 * Created by kfgodel on 18/01/15.
 */
public interface VortexConsumer {
    boolean isInterestedIn(ProducerManifest producerManifest);

    void addActiveProducer(VortexProducer newProducer);

    void connectWith(List<VortexProducer> interestingProducers);

    VortexStream getActiveStream();

    void removeActiveProducer(VortexProducer producer);

    void disconnectAll();
}
