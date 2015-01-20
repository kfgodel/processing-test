package app.ar.com.dgarcia.processing.sandbox.vortex;

import java.util.List;

/**
 * Created by kfgodel on 18/01/15.
 */
public interface VortexProducer {

    void connectWith(List<VortexConsumer> consumerStreams);

    ProducerManifest getManifest();

    void connectWith(VortexConsumer consumer);
}
