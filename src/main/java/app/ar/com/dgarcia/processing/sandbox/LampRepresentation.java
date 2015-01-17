package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

/**
 * This type represents the ways a lamp interacts with world
 * Created by ikari on 13/01/2015.
 */
public class LampRepresentation {

    /**
     * Draws the lamp as turned on in the given applet
     * @param applet The drawing canvas
     */
    public static void drawActiveOn(PApplet applet, Point2d position) {
        applet.stroke(0);
        applet.fill(255, 247, 159);
        applet.ellipse(centeredIn(position.getX()), centeredIn(position.getY()), lampDiameter(), lampDiameter());
    }

    private static float centeredIn(Point1d point) {
        return  point.getAsInt() - lampRadius();
    }

    /**
     * @return Half of the size of the lamp
     */
    private static int lampRadius() {
        return lampDiameter() / 2;
    }

    public static void drawInactiveOn(PApplet applet, Point2d position) {
        applet.stroke(80);
        applet.fill(187);
        applet.ellipse(position.getX().getAsInt() - lampRadius(), position.getY().getAsInt() - lampRadius(), lampDiameter(), lampDiameter());
    }

    /**
     * @return The size in diameter of a lamp
     */
    private static int lampDiameter() {
        return 60;
    }

}
