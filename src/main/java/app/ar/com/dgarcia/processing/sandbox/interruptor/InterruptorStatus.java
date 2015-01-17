package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import processing.core.PApplet;

/**
 * Created by ikari on 17/01/2015.
 */
public enum InterruptorStatus {
    ON{
        @Override
        public void toggle(DynamicInterruptor dynamicInterruptor) {
            dynamicInterruptor.setStatus(OFF);
        }

        @Override
        public void drawOn(PApplet applet, Point2d position) {
            InterruptorRepresentation.drawActiveOn(applet,position);
        }
    },
    OFF{
        @Override
        public void toggle(DynamicInterruptor dynamicInterruptor) {
            dynamicInterruptor.setStatus(ON);
        }

        @Override
        public void drawOn(PApplet applet, Point2d position) {
            InterruptorRepresentation.drawInactiveOn(applet,position);
        }
    };

    public abstract void toggle(DynamicInterruptor dynamicInterruptor);

    public abstract void drawOn(PApplet applet, Point2d position);
}
