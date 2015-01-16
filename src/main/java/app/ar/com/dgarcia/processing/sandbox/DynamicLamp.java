package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by ikari on 15/01/2015.
 */
public class DynamicLamp implements Lamp {

    private Point2d position;
    private Consumer<DynamicLamp> toggleBehavior;
    private BiConsumer<PApplet, Point2d> drawDefinition;

    private static void toggleOn(DynamicLamp instance) {
        instance.drawDefinition = LampBehavior::drawActiveOn;
        instance.toggleBehavior = DynamicLamp::toggleOff;
    }

    private static void toggleOff(DynamicLamp instance) {
        instance.drawDefinition = LampBehavior::drawInactiveOn;
        instance.toggleBehavior = DynamicLamp::toggleOn;
    }


    @Override
    public void toggle() {
        this.toggleBehavior.accept(this);
    }

    @Override
    public void drawOn(PApplet applet) {
        this.drawDefinition.accept(applet,position);
    }

    public static DynamicLamp create(Point2d position) {
        DynamicLamp lamp = new DynamicLamp();
        lamp.position = position;
        toggleOff(lamp);
        return lamp;
    }


}
