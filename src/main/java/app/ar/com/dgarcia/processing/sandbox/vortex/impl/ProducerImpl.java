package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;

import java.util.ArrayList;
import java.util.List;

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
        withActiveStream(() -> {
            consumers.forEach(this::addActiveConsumer);
        });
    }

    @Override
    public ProducerManifest getManifest() {
        return manifest;
    }

    @Override
    public void connectWith(VortexConsumer consumer) {
        withActiveStream(()->{
            addActiveConsumer(consumer);
        });
    }

    @Override
    public void disconnectAll() {
        List<VortexConsumer> disconnected = new ArrayList<>(activeConsumers);
        withActiveStream(()->{
            disconnected.forEach(this::removeActiveConsumer);
        });
    }

    @Override
    public void disconnectFrom(VortexConsumer consumer) {
        withActiveStream(()->{
            this.removeActiveConsumer(consumer);
        });
    }

    private void removeActiveConsumer(VortexConsumer consumer) {
        activeConsumers.remove(consumer);
        VortexStream consumerStream = consumer.getActiveStream();
        this.activeStream.removeReceiver(consumerStream);
        consumer.removeActiveProducer(this);
    }

    private void addActiveConsumer(VortexConsumer consumer) {
        consumer.addActiveProducer(this);
        VortexStream consumerStream = consumer.getActiveStream();
        this.activeStream.addReceiver(consumerStream);
        activeConsumers.add(consumer);
    }

    private void withActiveStream(Runnable code){
        boolean streamIsNew = this.activeStream == null;
        if(streamIsNew){
            this.activeStream = BroadcastStreamImpl.create();
        }
        code.run();
        if(this.activeStream.hasReceivers()){
            if(streamIsNew){
                manifest.onConsumersAvailable(activeStream);
            }
        }else{
            this.activeStream = null;
            manifest.onNoConsumersAvailable();
        }
    }
}
