package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ikari on 20/01/2015.
 */
public class ProducerImpl implements VortexProducer {

    private ProducerManifest manifest;
    private List<VortexConsumer> activeConsumers;
    private BroadcastStream activeStream;

    public static ProducerImpl create(ProducerManifest manifest) {
        ProducerImpl producer = new ProducerImpl();
        producer.manifest = manifest;
        producer.activeConsumers = new ArrayList<>();
        return producer;
    }

    @Override
    public void connectWith(List<VortexConsumer> consumers) {
        if(consumers.isEmpty()){
            // No consumers available yet
            return;
        }
        withActiveStream((stream) -> {
            consumers.forEach((consumer) -> connectWith(consumer));
        });
    }

    @Override
    public ProducerManifest getManifest() {
        return manifest;
    }

    @Override
    public void connectWith(VortexConsumer consumer) {
        withActiveStream((stream)->{
            activeConsumers.add(consumer);
            VortexStream consumerStream = consumer.getStreamFor(this);
            stream.addReceiver(consumerStream);
        });
    }

    private void withActiveStream(Consumer<BroadcastStream> code){
        boolean streamIsNew = this.activeStream == null;
        if(streamIsNew){
            this.activeStream = BroadcastStreamImpl.create();
        }
        code.accept(this.activeStream);
        if(streamIsNew){
            manifest.onConsumersAvailable(activeStream);
        }
    }
}
