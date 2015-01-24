package ar.com.dgarcia.vortex;

import app.ar.com.dgarcia.processing.sandbox.vortex.VortexInterest;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexMessage;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexProducer;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexStream;
import app.ar.com.dgarcia.processing.sandbox.vortex.impl.*;
import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import org.junit.runner.RunWith;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.mockito.Mockito.*;

/**
 * Created by kfgodel on 18/01/15.
 */
@RunWith(JavaSpecRunner.class)
public class VortexEndpointTest extends JavaSpec<VortexTestContext> {
    @Override
    public void define() {
        describe("vortex endpoint", ()->{

            context().node(NodeImpl::create);
            
            it("allows message passing between consumer and producer",()->{
                // The consumer declaration to hold the communication stream in a variable
                Variable<VortexStream> producerStreamHolder = Variable.create();
                Consumer<VortexStream> consumerAvailabilityHandler = (stream)-> producerStreamHolder.set(stream);
                ProducerManifestImpl producerManifest = ProducerManifestImpl.create(AllInterest.INSTANCE, consumerAvailabilityHandler);
                context().node().declareProducer(producerManifest);

                // The consumer declaration to mock the reception of the message
                VortexStream mockedConsumerStream = mock(VortexStream.class);
                Supplier<VortexStream> producerAvailabilityHandler = ()-> mockedConsumerStream;
                declareConsumer(AllInterest.INSTANCE, producerAvailabilityHandler);

                // We send a message in one end and verify we received it in the other
                VortexMessage sentMessage = mock(VortexMessage.class);
                producerStreamHolder.get().receive(sentMessage);

                // It should be the exact same message
                verify(mockedConsumerStream).receive(sentMessage);
            });

            describe("consumers", () -> {
                describe("are notified", () -> {
                    describe("when matching producers", () -> {
                        describe("are available", () -> {
                            it("before consumer declaration", () -> {
                                declareProducer(AllInterest.INSTANCE);

                                // Mocked handler to verify
                                Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                                declareConsumer(AllInterest.INSTANCE, producerAvailabilityHandler);

                                // Our handler gets called
                                verify(producerAvailabilityHandler).get();
                            });
                            it("after consumer declaration", () -> {
                                Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                                declareConsumer(AllInterest.INSTANCE, producerAvailabilityHandler);

                                declareProducer(AllInterest.INSTANCE);

                                verify(producerAvailabilityHandler).get();
                            });
                        });
                        it("become unavailable after consumer declaration", () -> {
                            // The producer we will remove
                            VortexProducer producer = declareProducer(AllInterest.INSTANCE);

                            Runnable producerUnavailabilityHandler = mock(Runnable.class);
                            declareConsumer(AllInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);

                            //Let's remove the producer to get notified
                            context().node().retireProducer(producer);

                            verify(producerUnavailabilityHandler).run();
                        });
                    });
                    describe("when interest change", () -> {
                        it("makes un-matching producers into matching producers",()->{
                            declareProducer(AllInterest.INSTANCE);

                            Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                            ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(NoInterest.INSTANCE, producerAvailabilityHandler);
                            context().node().declareConsumer(consumerManifest);

                            consumerManifest.changeInterest(AllInterest.INSTANCE);

                            verify(producerAvailabilityHandler).get();
                        });
                        it("makes matching producers into un-matching producers",()->{
                            declareProducer(AllInterest.INSTANCE);

                            Runnable producerUnavailabilityHandler = mock(Runnable.class);
                            ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(AllInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);
                            context().node().declareConsumer(consumerManifest);

                            consumerManifest.changeInterest(NoInterest.INSTANCE);

                            verify(producerUnavailabilityHandler).run();
                        });
                    });
                });

                describe("are not notified", () -> {
                    describe("when un-matching producers", () -> {
                        describe("are available", () -> {
                            it("before consumer declaration", () -> {
                                declareProducer(AllInterest.INSTANCE);

                                Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                                declareConsumer(NoInterest.INSTANCE, producerAvailabilityHandler);

                                verify(producerAvailabilityHandler, never()).get();
                            });

                            it("after consumer declaration", () -> {
                                Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                                declareConsumer(NoInterest.INSTANCE, producerAvailabilityHandler);

                                declareProducer(AllInterest.INSTANCE);

                                verify(producerAvailabilityHandler, never()).get();
                            });
                        });
                        describe("are unavailable", () -> {
                            it("before consumer declaration", () -> {
                                Runnable producerUnavailabilityHandler = mock(Runnable.class);
                                declareConsumer(NoInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);

                                verify(producerUnavailabilityHandler, never()).run();
                            });

                            it("after consumer declaration", () -> {
                                // The producer we will remove
                                VortexProducer producer = declareProducer(AllInterest.INSTANCE);

                                Runnable producerUnavailabilityHandler = mock(Runnable.class);
                                declareConsumer(NoInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);

                                context().node().retireProducer(producer);

                                verify(producerUnavailabilityHandler, never()).run();
                            });
                        });
                    });

                    it("when matching producers are unavailable before consumer declaration", () -> {
                        Runnable producerUnavailabilityHandler = mock(Runnable.class);
                        declareConsumer(NoInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);

                        verify(producerUnavailabilityHandler, never()).run();
                    });

                    describe("when interest change", () -> {
                        it("doesn't make un-matching producers into matching producers",()->{
                            declareProducer(AllInterest.INSTANCE);

                            Supplier<VortexStream> producerAvailabilityHandler = mockAvailabilityHandler();
                            ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(NoInterest.INSTANCE, producerAvailabilityHandler);
                            context().node().declareConsumer(consumerManifest);

                            consumerManifest.changeInterest(NoInterest.INSTANCE);

                            verify(producerAvailabilityHandler, never()).get();
                        });
                        it("doesn't make matching producers into un-matching producers",()->{
                            declareProducer(AllInterest.INSTANCE);

                            Runnable producerUnavailabilityHandler = mock(Runnable.class);
                            ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(AllInterest.INSTANCE, mock(Supplier.class), producerUnavailabilityHandler);
                            context().node().declareConsumer(consumerManifest);

                            consumerManifest.changeInterest(AllInterest.INSTANCE);

                            verify(producerUnavailabilityHandler, never()).run();
                        });
                    });

                });
            });
        });
    }

    private void declareConsumer(VortexInterest interest, Supplier<VortexStream> producerAvailabilityHandler) {
        ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(interest, producerAvailabilityHandler);
        context().node().declareConsumer(consumerManifest);
    }

    private void declareConsumer(VortexInterest interest, Supplier producerAvailabilityHandler, Runnable producerUnavailabilityHandler) {
        ConsumerManifestImpl consumerManifest = ConsumerManifestImpl.create(interest, producerAvailabilityHandler, producerUnavailabilityHandler);
        context().node().declareConsumer(consumerManifest);
    }


    private Supplier<VortexStream> mockAvailabilityHandler() {
        Supplier<VortexStream> producerAvailabilityHandler = mock(Supplier.class);
        when(producerAvailabilityHandler.get()).thenReturn(mock(VortexStream.class));
        return producerAvailabilityHandler;
    }

    private VortexProducer declareProducer(VortexInterest producerInterest) {
        ProducerManifestImpl producerManifest = ProducerManifestImpl.create(producerInterest, mock(Consumer.class));
        return context().node().declareProducer(producerManifest);
    }
}
