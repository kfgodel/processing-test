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
        producers.add(newProducer);

        List<VortexConsumer> interestedConsumers = consumers.stream()
                .filter((consumer) -> consumer.isInterestedIn(producerManifest))
                .collect(Collectors.toList());
        newProducer.connectWith(interestedConsumers);

        return newProducer;
    }

    @Override
    public void retireProducer(VortexProducer addedProducer) {
        producers.remove(addedProducer);
    }

    @Override
    public VortexConsumer declareConsumer(ConsumerManifest consumerManifest) {
        ConsumerImpl newConsumer = ConsumerImpl.create(consumerManifest);
        consumers.add(newConsumer);

        List<VortexProducer> interestingProducers = producers.stream()
                .filter((producer) -> newConsumer.isInterestedIn(producer.getManifest()))
                .collect(Collectors.toList());
        newConsumer.connectWith(interestingProducers);

        return newConsumer;
    }

    @Override
    public void retireConsumer(VortexConsumer addedConsumer) {
        consumers.remove(addedConsumer);
    }
}
