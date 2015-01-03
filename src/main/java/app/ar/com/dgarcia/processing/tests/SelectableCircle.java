package app.ar.com.dgarcia.processing.tests;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This type serves as a selectable circle
 * Created by ikari on 02/01/2015.
 */
public class SelectableCircle {

    private PVector position;
    private PVector size;


    public static SelectableCircle create(float x, float y, float width, float height) {
        SelectableCircle circle = new SelectableCircle();
        circle.position = new PVector(x,y);
        circle.size = new PVector(width, height);
        return circle;
    }

    public void drawOn(PApplet sketch) {
        sketch.stroke(0);
        sketch.strokeWeight(getBorderWidth());
        sketch.fill(225);
        sketch.ellipse(position.x, position.y, size.x, size.y);

    }

    public float getBorderWidth() {
        return 3;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getSize() {
        return size;
    }

    public void setSize(PVector size) {
        this.size = size;
    }

    public boolean touches(PVector mousePosition) {
        return mousePosition.dist(this.getPosition()) < (this.getSize().x/2);
    }
}
