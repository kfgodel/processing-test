package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

/**
 * This type represents one of the possible states of a lamp
 * Created by ikari on 17/01/2015.
 */
public enum LampStatus {
    /**
     * Lighting
     */
    ON{
        @Override
        public void toggle(DynamicLamp lamp) {
            lamp.setStatus(OFF);
        }

        @Override
        public void drawOn(PApplet applet, Point2d position) {
            LampRepresentation.drawActiveOn(applet,position);
        }

    },
    /**
     * Obscure
     */
    OFF{
        @Override
        public void toggle(DynamicLamp lamp) {
            lamp.setStatus(ON);
        }

        @Override
        public void drawOn(PApplet applet, Point2d position) {
            LampRepresentation.drawInactiveOn(applet,position);
        }

    };

    public abstract void toggle(DynamicLamp lamp);

    public abstract void drawOn(PApplet applet, Point2d position);
}
