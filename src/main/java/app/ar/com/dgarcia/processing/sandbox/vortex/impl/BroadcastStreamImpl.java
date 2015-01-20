package app.ar.com.dgarcia.processing.sandbox.vortex.impl;

import app.ar.com.dgarcia.processing.sandbox.vortex.BroadcastStream;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexMessage;
import app.ar.com.dgarcia.processing.sandbox.vortex.VortexStream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikari on 20/01/2015.
 */
public class BroadcastStreamImpl implements BroadcastStream {

    private List<VortexStream> receiverStreams;

    @Override
    public void receive(VortexMessage message) {
        for (VortexStream receiverStream : receiverStreams) {
            receiverStream.receive(message);
        }
    }

    public static BroadcastStreamImpl create() {
        BroadcastStreamImpl stream = new BroadcastStreamImpl();
        stream.receiverStreams = new ArrayList<>();
        return stream;
    }

    @Override
    public void addReceiver(VortexStream consumerStream) {
        receiverStreams.add(consumerStream);
    }
}
