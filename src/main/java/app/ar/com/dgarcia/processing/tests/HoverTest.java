package app.ar.com.dgarcia.processing.tests;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by ikari on 05/01/2015.
 */
public class HoverTest extends PApplet {

    private List<HoverableSquare> squares = new ArrayList<HoverableSquare>();
    private Consumer<PVector> movementHandler = this::noOp;

    static public void main(String args[]) {
        PApplet.main(new String[] { HoverTest.class.getName() });
    }

    @Override
    public void setup() {
        size(1024, 768);

        createSquares();
        movementHandler = this::noActiveHover;
    }

    @Override
    public void draw() {
        background(200);
        drawSquares();
        fill(255);
        text("FPS: " + frameRate, 0, 30);
        mouseMoved();
    }

    public void noOp(PVector event){
    }

    public void noActiveHover(PVector event){
        for (HoverableSquare square : squares) {
            if(square.contains(event.x,event.y)){
                onHoverStarted(square);
                break; // Only one allowed
            }
        }
    }

    private void onHoverStarted(HoverableSquare hoveredSquare) {
        println("-> " + hoveredSquare);
        movementHandler = (event) -> onHovering(event, hoveredSquare);
    }

    public void onHovering(PVector event, HoverableSquare hoveredSquare){
        if(!hoveredSquare.contains(event.x, event.y)){
            onHoverStopped(hoveredSquare);
        }
    }

    private void onHoverStopped(HoverableSquare hoveredSquare) {
        println("<- " + hoveredSquare);
        movementHandler = this::noActiveHover;
    }



    @Override
    public void mouseMoved() {
        PVector mousePosition = new PVector(mouseX, mouseY);
        movementHandler.accept(mousePosition);
    }

    private void drawSquares() {
        for (HoverableSquare square : squares) {
            square.drawOn(this);
        }
    }

    private void createSquares() {
        Random rand = new Random(0);
        for (int i = 0; i < 10000; i++) {
            float x = rand.nextFloat() * width;
            float y = rand.nextFloat() * height;
            float size = rand.nextFloat() * 100f;
            HoverableSquare square = HoverableSquare.create(x, y, size);
            squares.add(square);
        }
    }
}
