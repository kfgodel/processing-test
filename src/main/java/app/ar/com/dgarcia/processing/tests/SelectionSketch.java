package app.ar.com.dgarcia.processing.tests;

import com.google.common.collect.Lists;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ikari on 02/01/2015.
 */
public class SelectionSketch extends PApplet {

    private int fillColor;

    private int selectionColor = color(248, 255, 199);

    private List<SelectableCircle> circles;
    private Set<SelectableCircle> selected;

    static public void main(String args[]) {
        PApplet.main(new String[] { SelectionSketch.class.getName() });
    }


    @Override
    public void setup() {
        size(1024, 768);
        background(200);
        smooth();
        selected = new HashSet<>();
        circles = Lists.newArrayList(SelectableCircle.create(width / 2 - 20, height / 2 - 30, 100, 100), SelectableCircle.create(width / 2 + 40, height / 2 + 20, 100, 100));
    }

    @Override
    public void draw() {
        noStroke();
        fill(200);
        rect(0,0,1024,768);


        PVector mousePosition = getMouseVector();
        for (SelectableCircle circle : circles) {
            if(selected.contains(circle) || circle.touches(mousePosition) ){
                stroke(selectionColor);
                int selectionWidth = 3;
                strokeWeight(selectionWidth);
                noFill();
                ellipse(circle.getPosition().x, circle.getPosition().y, circle.getSize().x + selectionWidth + circle.getBorderWidth(), circle.getSize().y + selectionWidth + circle.getBorderWidth());
            }
            circle.drawOn(this);
        }
    }

    private PVector getMouseVector() {
        return new PVector(mouseX,mouseY);
    }

    @Override
    public void mouseClicked() {
        PVector mousePosition = getMouseVector();
        for (SelectableCircle circle : circles) {
            if(circle.touches(mousePosition)){
                if(selected.contains(circle)){
                    selected.remove(circle);
                }else{
                    selected.add(circle);
                }
            }
        }
    }
}
