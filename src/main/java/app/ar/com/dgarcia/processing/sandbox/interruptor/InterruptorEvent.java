package app.ar.com.dgarcia.processing.sandbox.interruptor;

/**
 * Created by ikari on 17/01/2015.
 */
public class InterruptorEvent {

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
