package app.ar.com.dgarcia.processing.sandbox.interruptor;

import app.ar.com.dgarcia.processing.sandbox.geo.Point1d;
import app.ar.com.dgarcia.processing.sandbox.geo.Point2d;
import processing.core.PApplet;

/**
 * This type represents an interruptor to turn lamps on and off
 * Created by ikari on 17/01/2015.
 */
public class InterruptorRepresentation {

    public static void drawActiveOn(PApplet applet, Point2d position) {
        drawInterruptorBase(applet, position);
        drawState(applet, position, true);
    }
    public static void drawInactiveOn(PApplet applet, Point2d position) {
        drawInterruptorBase(applet, position);
        drawState(applet, position, false);
    }

    public static void drawState(PApplet applet, Point2d position, boolean active) {
        applet.noStroke();
        applet.fill(96, 145, 156);

        int horizontalPosition = horizontallyCenteredOn(position.getX(), buttonWidth());

        int verticalPosition = position.getY().getAsInt();
        if(active){
            // Move it up if active
            verticalPosition -= stateHeight();
        }
        applet.rect(horizontalPosition, verticalPosition, buttonWidth(), stateHeight());
    }

    public static void drawInterruptorBase(PApplet applet, Point2d position) {
        applet.stroke(0);
        applet.strokeWeight(1);
        applet.fill(176, 213, 224);
        drawBox(applet, position, interruptorWidth(), interruptorHeight());

        applet.fill(138,196,210);
        drawBox(applet, position, buttonWidth(), buttonHeight());
    }

    public static void drawBox(PApplet applet, Point2d position, int width, int height) {
        applet.rect(horizontallyCenteredOn(position.getX(), width), verticallyCenteredOn(position.getY(), height), width, height);
    }

    public static int stateHeight() {
        return buttonHeight() / 2;
    }

    public static int buttonWidth(){
        return (int) (interruptorWidth() * 0.4);
    }

    public static int buttonHeight(){
        return (int) (interruptorHeight() * 0.6);
    }

    public static int interruptorHeight() {
        return 50;
    }

    public static int interruptorWidth() {
        return 40;
    }

    public static float verticallyCenteredOn(Point1d positionY, int height) {
        return positionY.getAsInt() - Point1d.halfOf(height);
    }

    public static int horizontallyCenteredOn(Point1d positionX, int width) {
        return positionX.getAsInt() - Point1d.halfOf(width);
    }
}
