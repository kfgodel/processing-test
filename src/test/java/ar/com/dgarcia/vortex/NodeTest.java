package ar.com.dgarcia.vortex;

import app.ar.com.dgarcia.processing.sandbox.vortex.*;
import app.ar.com.dgarcia.processing.sandbox.vortex.impl.AllInterest;
import app.ar.com.dgarcia.processing.sandbox.vortex.impl.ConsumerManifestImpl;
import app.ar.com.dgarcia.processing.sandbox.vortex.impl.NodeImpl;
import app.ar.com.dgarcia.processing.sandbox.vortex.impl.ProducerManifestImpl;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by kfgodel on 18/01/15.
 */
@RunWith(JavaSpecRunner.class)
public class NodeTest extends JavaSpec<VortexTestContext> {
    @Override
    public void define() {
        describe("a vortex node", ()->{

            context().node(NodeImpl::create);
            
            it("allows message passing between consumer and producer",()->{
                // The consumer declaration to hold the communication stream in a variable
                Variable<VortexStream> producerStreamHolder = Variable.create();
                Consumer<VortexStream> mockedProducerHandler = (stream)-> producerStreamHolder.set(stream);
                ProducerManifestImpl producerManifest = ProducerManifestImpl.create(AllInterest.INSTANCE, mockedProducerHandler);
                context().node().declareProducer(producerManifest);

                // The consumer declaration to mock the reception of the message
                VortexStream consumerStream = mock(VortexStream.class);
                Supplier<VortexStream> consumerConnectionHandler = ()-> consumerStream;
                ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(AllInterest.INSTANCE, consumerConnectionHandler);
                context().node().declareConsumer(consumerManifest);

                // We send a message in one end and verify we received it in the other
                VortexMessage sentMessage = mock(VortexMessage.class);
                producerStreamHolder.get().receive(sentMessage);

                // It should be the exact same message
                verify(consumerStream).receive(sentMessage);
            });

            describe("message producer", ()->{
                it("can be added", ()->{
                    ProducerManifest producerManifest = mock(ProducerManifest.class);

                    VortexProducer newProducer = context().node().declareProducer(producerManifest);

                    Assertions.assertThat(newProducer).isNotNull();
                });
                it("can be removed", ()->{
                    ProducerManifest producerManifest = mock(ProducerManifest.class);
                    VortexProducer addedProducer = context().node().declareProducer(producerManifest);

                    context().node().retireProducer(addedProducer);
                });
                describe("manifest", ()->{
                    it("gets notified when matching consumers are available",()->{
                        VortexInterest commonCondition = mock(VortexInterest.class);

                        ConsumerManifest consumerManifest = mock(ConsumerManifest.class);
                        when(consumerManifest.isCompatibleWith(any(ProducerManifest.class))).thenReturn(true);
                        ProducerManifest producerManifest = mock(ProducerManifest.class);
                        when(producerManifest.getInterest()).thenReturn(commonCondition);


                        context().node().declareConsumer(consumerManifest);

                        context().node().declareProducer(producerManifest);

                        verify(producerManifest).onConsumersAvailable(any(VortexStream.class));

                    });
                    it("gets notified when no matching consumers are available", ()->{
                        ProducerManifest producerManifest = mock(ProducerManifest.class);

                        context().node().declareProducer(producerManifest);

                        verify(producerManifest).onNoConsumersAvailable();
                    });
                });


                describe("notification", ()->{

                    describe("of available consumers", () -> {

                        it("allows production of messages when consumers are available", ()->{
                            ProducerManifest producerManifest = mock(ProducerManifest.class);
                            context().node().declareProducer(producerManifest);


                        });

                        describe("it's triggered", () -> {

                            it("when producer is declared and consumers are available", () -> {

                            });

                            it("when no consumers are available and one is added", () -> {

                            });
                            it("it's not triggered when consumers are available and one more is added", () -> {

                            });
                        });
                    });

                    describe("of no available consumers", () -> {

                        it("allows resource preservation when no consumers available", ()->{

                        });

                        describe("it's triggered", () -> {

                            it("when producer is declared and no consumers are available", ()->{

                            });

                            it("when consumers are available and the last one is removed", ()->{

                            });
                        });
                    });
                });

            });

            describe("message consumer", ()->{
                it("can be added", ()->{
                    ConsumerManifest consumerManifest = mock(ConsumerManifest.class);
                    
                    VortexConsumer newConsumer = context().node().declareConsumer(consumerManifest);

                    Assertions.assertThat(newConsumer).isNotNull();
                });
                it("can be removed", ()->{
                    ConsumerManifest consumerManifest = mock(ConsumerManifest.class);
                    VortexConsumer addedConsumer = context().node().declareConsumer(consumerManifest);

                    context().node().retireConsumer(addedConsumer);
                });

                describe("manifest", ()->{
                    it("gets notified when matching producers are available", ()->{

                    });
                    it("get notified when no matching producers are available", ()->{

                    });
                });

                describe("notification", ()->{

                    describe("of available producers", () -> {

                        it("allows consumption of messages when available", () -> {

                        });

                        describe("it's triggered", () -> {

                            it("when consumer is declared and producers are available", () -> {

                            });

                            it("when no producers are available and one is added", () -> {

                            });
                            it("it's not triggered when producers are available and one more is added", () -> {

                            });
                        });
                    });

                    describe("of no available producers", () -> {

                        it("allows resource preservation when no messages available", () -> {

                        });

                        describe("it's triggered", () -> {

                            it("when consumer is declared and no producers are available", ()->{

                            });

                            it("when producers are available and the last one is removed", ()->{

                            });
                        });
                    });
                });
            });

        });
    }
}
