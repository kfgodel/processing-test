package app.ar.com.dgarcia.processing.sandbox.vortex;

/**
 * This type represents a communication hub to send and receive messages
 * Created by ikari on 17/01/2015.
 */
public interface VortexNode {
    VortexTunnel createReceptionTunnel();

    void returnReceptionTunnel(VortexTunnel tunnel);

    void addEndpoint(VortexEndpoint endpoint);
}
