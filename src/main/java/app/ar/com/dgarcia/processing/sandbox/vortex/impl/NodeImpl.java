package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kfgodel on 18/01/15.
 */
public class NodeImpl implements VortexNode {

    private List<VortexProducer> producers;
    private List<VortexConsumer> consumers;

    public static NodeImpl create(){
        NodeImpl node = new NodeImpl();
        node.consumers = new ArrayList<>();
        node.producers = new ArrayList<>();
        return node;
    }

    @Override
    public VortexProducer declareProducer(ProducerManifest producerManifest) {
        VortexProducer newProducer = ProducerImpl.create(producerManifest);

        List<VortexConsumer> interestedConsumers = consumers.stream()
                .filter((consumer) -> consumer.isInterestedIn(newProducer))
                .collect(Collectors.toList());
        newProducer.connectWith(interestedConsumers);

        producers.add(newProducer);
        return newProducer;
    }

    @Override
    public void retireProducer(VortexProducer producer) {
        producers.remove(producer);
        producer.disconnectAll();
    }

    @Override
    public VortexConsumer declareConsumer(ConsumerManifest consumerManifest) {
        ConsumerImpl newConsumer = ConsumerImpl.create(consumerManifest);

        List<VortexProducer> interestingProducers = producers.stream()
                .filter((producer) -> newConsumer.isInterestedIn(producer))
                .collect(Collectors.toList());
        newConsumer.connectWith(interestingProducers);

        consumers.add(newConsumer);
        return newConsumer;
    }

    @Override
    public void retireConsumer(VortexConsumer consumer) {
        consumers.remove(consumer);
        consumer.disconnectAll();
    }
}
