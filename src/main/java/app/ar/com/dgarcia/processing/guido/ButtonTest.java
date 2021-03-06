package app.ar.com.dgarcia.processing.guido;

import de.bezier.guido.Interactive;
import processing.core.PApplet;

/**
 * Created by ikari on 07/01/2015.
 */
public class ButtonTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ButtonTest.class.getName() });
    }

    public void setup()
    {
        size ( 200, 200 );

        Interactive.make( this ); // start GUIDO

        MyButton mb = new MyButton( 20, 20, 50, 50 ); // create an instance of your element
    }

    public void draw()
    {
        background(255);

        // GUIDO calls "element.draw()" of every element registered with it after
        // this draw for the elements to be drawn on top.
    }

    public class MyButton
    {
        float x,y,width,height;
        boolean on;

        MyButton ( float xx, float yy, float ww, float hh )
        {
            x = xx; y = yy; width = ww; height = hh;

            Interactive.add( this ); // add this to GUIDO manager, important!
        }

        void mousePressed ()
        {
            // called when the button has been pressed

            on = !on;
        }

        void draw ()
        {
            // called by GUIDO after PApplet.draw() has finished

            fill( on ? 80 : 140 );
            rect( x, y, width, height );
        }
    }
}
