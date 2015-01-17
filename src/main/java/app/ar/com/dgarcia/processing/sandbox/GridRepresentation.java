package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

/**
 * Created by ikari on 17/01/2015.
 */
public class GridRepresentation {

    public static void drawOn(PApplet applet) {
        int minResolution = 50;
        applet.stroke(230);
        drawGrid(applet, minResolution);

        int maxResolution = 100;
        applet.stroke(180);
        drawGrid(applet, maxResolution);

        drawMiddleIndicators(applet);
    }

    private static void drawMiddleIndicators(PApplet applet) {
        applet.stroke(100);
        applet.strokeWeight(2);

        int verticalCenter = applet.width / 2;
        applet.line(verticalCenter,0,verticalCenter,applet.height);

        int horizontalCenter = applet.height / 2;
        applet.line(0, horizontalCenter, applet.width, horizontalCenter);
    }

    private static void drawGrid(PApplet applet, int resolution) {
        for (int i = 0; i <= applet.width; i+= resolution) {
            applet.line(i,0,i,applet.height);
        }
        for (int i = 0; i <= applet.height; i+= resolution) {
            applet.line(0,i,applet.width,i);
        }
    }
}
