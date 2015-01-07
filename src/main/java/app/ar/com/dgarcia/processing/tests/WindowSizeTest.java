package app.ar.com.dgarcia.processing.tests;

import processing.core.PApplet;

/**
 * Created by ikari on 07/01/2015.
 */
public class WindowSizeTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { WindowSizeTest.class.getName() });
    }

    public void setup() {
        size(displayWidth, displayHeight);
        //size(400, 400);  // size always goes first!
        if (frame != null) {
            frame.setResizable(true);
        }
    }
//    public boolean sketchFullScreen() {
//        return true;
//    }
}
