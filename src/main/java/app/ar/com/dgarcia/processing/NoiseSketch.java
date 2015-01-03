package app.ar.com.dgarcia.processing;

import processing.core.PApplet;

import java.util.function.Supplier;

/**
 * Created by ikari on 02/01/2015.
 */
public class NoiseSketch extends PApplet {

    private Supplier<Integer>[] randoms;
    private int randomIndex = 0;

    static public void main(String args[]) {
        PApplet.main(new String[] { NoiseSketch.class.getName() });
    }

    @Override
    public void setup() {
        noLoop();
        size(1024, 768);
        randoms = new Supplier[2];
        randoms[0] = this::generateRandomColor;
        randoms[1] = this::generateRandomGaussianColor;
    }

    @Override
    public void draw() {
        loadPixels();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = randoms[randomIndex].get();
        }
        updatePixels();
        randomIndex++;
        if(randomIndex >= randoms.length ){
            randomIndex = 0;
        }
    }

    private int generateRandomColor() {
        return (int)random(0, Integer.MAX_VALUE);
    }
    private int generateRandomGaussianColor(){
        float gaussian = randomGaussian();
        int middleValue = Integer.MAX_VALUE / 2;
        int color = (int) (middleValue + gaussian * middleValue);
        return color;
    }

    @Override
    public void mousePressed() {
        redraw();
    }
}
