package app.ar.com.dgarcia.processing.sandbox.geo;

import processing.core.PApplet;

import java.util.function.IntSupplier;

/**
 * This type represent a point in a single dimension
 * Created by ikari on 13/01/2015.
 */
public interface Point1d extends IntSupplier {

    /**
     * The x coordinate that is in the middle of the screen
     * @param applet The applet that represents the screen
     * @return The point that represents the value
     */
    static Point1d horizontalCenterOf(PApplet applet) {
        return ()-> halfOf(applet.width);
    }

    /**
     * The y coordinate that is in the middle of the screen
     * @param applet The applet that represents the screen
     * @return The point that represents the value
     */
    static Point1d verticalCenterOf(PApplet applet) {
        return ()->halfOf(applet.height);
    }

    /**
     * @param total Value that represents a unity
     * @return A value that represents half of the unity
     */
    static int halfOf(int total) {
        return total / 2;
    }

    /**
     * Sums the amount give to this point
     * @param addend The amount to displace this point in the positive direction
     * @return The new point
     */
    default Point1d plus(int addend){
        return ()-> this.getAsInt() + addend;
    };

    /**
     * Subtracts the given value to this point
     * @param subtraction The value to subtract
     * @return The new displaced point
     */
    default Point1d minus(int subtraction){
        return ()-> this.getAsInt() - subtraction;
    };

    default Point1d absolutize(){
        return ()-> Math.abs(getAsInt());
    };


}
