package app.ar.com.dgarcia.processing.tests;

import processing.core.PApplet;

/**
 * Created by ikari on 05/01/2015.
 */
public class HoverableSquare {

    private float x;
    private float y;
    private float size;

    public static HoverableSquare create(float x, float y, float size) {
        HoverableSquare square = new HoverableSquare();
        square.x = x;
        square.y = y;
        square.size = size;
        return square;
    }

    public void drawOn(PApplet applet){
        applet.stroke(0);
        applet.fill(225);
        applet.rect(x, y, size, size);
    }

    public boolean contains(float mouseX, float mouseY) {
        return mouseX >= x && mouseX <= (x + size) && mouseY >= y && mouseY <= (y+size);
    }

    @Override
    public String toString() {
        return Integer.toHexString(this.hashCode());
    }
}
