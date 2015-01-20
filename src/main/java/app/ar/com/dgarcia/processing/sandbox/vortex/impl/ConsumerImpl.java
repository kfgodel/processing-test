package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikari on 20/01/2015.
 */
public class ConsumerImpl implements VortexConsumer {

    private ConsumerManifest manifest;
    private List<VortexProducer> interestingProducers;
    private VortexStream activeStream;

    public static ConsumerImpl create(ConsumerManifest manifest) {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.manifest = manifest;
        consumer.interestingProducers = new ArrayList<>();
        return consumer;
    }

    @Override
    public boolean isInterestedIn(ProducerManifest producerManifest) {
        return true;
    }

    @Override
    public VortexStream getStreamFor(VortexProducer newProducer) {
        interestingProducers.add(newProducer);
        return getActiveStream();
    }

    @Override
    public void connectWith(List<VortexProducer> interestingProducers) {
        interestingProducers.forEach((producer)->{
            interestingProducers.add(producer);
            producer.connectWith(this);
        });
    }

    private VortexStream getActiveStream() {
        if (activeStream == null) {
            activeStream = manifest.onAvailableProducers();
        }
        return activeStream;
    }
}
