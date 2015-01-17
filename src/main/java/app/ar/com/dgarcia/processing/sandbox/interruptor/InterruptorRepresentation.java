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

    private static void drawState(PApplet applet, Point2d position, boolean active) {
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

    private static void drawInterruptorBase(PApplet applet, Point2d position) {
        applet.stroke(0);
        applet.fill(176, 213, 224);
        drawBox(applet, position, interruptorWidth(), interruptorHeight());

        applet.fill(138,196,210);
        drawBox(applet, position, buttonWidth(), buttonHeight());
    }

    private static void drawBox(PApplet applet, Point2d position, int width, int height) {
        applet.rect(horizontallyCenteredOn(position.getX(), width), verticallyCenteredOn(position.getY(), height), width, height);
    }

    private static int stateHeight() {
        return buttonHeight() / 2;
    }

    private static int buttonWidth(){
        return (int) (interruptorWidth() * 0.4);
    }

    private static int buttonHeight(){
        return (int) (interruptorHeight() * 0.6);
    }

    private static int interruptorHeight() {
        return 50;
    }

    private static int interruptorWidth() {
        return 40;
    }

    private static float verticallyCenteredOn(Point1d positionY, int height) {
        return positionY.getAsInt() - Point1d.halfOf(height);
    }

    private static int horizontallyCenteredOn(Point1d positionX, int width) {
        return positionX.getAsInt() - Point1d.halfOf(width);
    }
}
