package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikari on 20/01/2015.
 */
public class ConsumerImpl implements VortexConsumer {

    private ConsumerManifest manifest;
    private List<VortexProducer> activeProducers;
    private VortexStream activeStream;

    public static ConsumerImpl create(ConsumerManifest manifest) {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.manifest = manifest;
        consumer.activeProducers = new ArrayList<>();
        return consumer;
    }

    @Override
    public boolean isInterestedIn(ProducerManifest producerManifest) {
        return true;
    }

    @Override
    public void addActiveProducer(VortexProducer newProducer) {
        activeProducers.add(newProducer);
    }

    @Override
    public void connectWith(List<VortexProducer> interestingProducers) {
        interestingProducers.forEach((producer)->{
            producer.connectWith(this);
        });
    }

    public VortexStream getActiveStream() {
        if (activeStream == null) {
            activeStream = manifest.onAvailableProducers();
        }
        return activeStream;
    }

    @Override
    public void removeActiveProducer(VortexProducer producer) {
        activeProducers.remove(producer);
        if(activeProducers.isEmpty()){
            manifest.onNoAvailableProducers();
            this.activeStream = null;
        }
    }

    @Override
    public void disconnectAll() {
        List<VortexProducer> disconnected = new ArrayList<>(activeProducers);
        disconnected.forEach((producer) -> {
            producer.disconnectFrom(this);
        });
    }
}
