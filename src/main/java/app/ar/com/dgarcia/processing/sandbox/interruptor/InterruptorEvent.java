package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.vortex.VortexMessage;

/**
 * Created by ikari on 17/01/2015.
 */
public class InterruptorEvent implements VortexMessage {

    private InterruptorStatus status;

    public InterruptorStatus getStatus() {
        return status;
    }

    public void setStatus(InterruptorStatus status) {
        this.status = status;
    }

    public static InterruptorEvent create(InterruptorStatus status) {
        InterruptorEvent event = new InterruptorEvent();
        event.status = status;
        return event;
    }

}
