package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;
import processing.core.PFont;

/**
 * Este tipo prueba el dibujado de strings
 * Created by ikari on 26/12/2014.
 */
public class TextTutorial extends PApplet {
    static public void main(String args[]) {
        PApplet.main(new String[] { TextTutorial.class.getName() });
    }

    PFont f;                          // STEP 2 Declare PFont variable
    float theta;

    public void setup() {
        size(1024,768);
        f = createFont("Arial",16,true); // STEP 3 Create Font

        String[] allFontNames = PFont.list();
        for (String fontName : allFontNames) {
            System.out.println("- " + fontName);
        }
    }

    public void draw() {
        background(255);

        stroke(175);
        line(width / 2, 0, width / 2, height);

        textFont(f,22);
        fill(0);

        textAlign(CENTER);
        text("This text is centered.", width / 2, 60);
        stroke(180);
        float centerWidth = textWidth("This text is centered.");
        line(width / 2 - centerWidth/2, 60, width / 2 + centerWidth/2, 60);
        line(width / 2 - centerWidth/2, 60 - 22, width / 2 - centerWidth/2, 60);



        textAlign(LEFT);
        text("This text is left aligned.", width / 2, 100);
        float leftWidth = textWidth("This text is left aligned.");
        line(width / 2, 100, width / 2 + leftWidth, 100);

        textAlign(RIGHT);
        text("This text is right aligned.", width / 2, 140);
        float rightWidth = textWidth("This text is right aligned.");
        line(width / 2 - rightWidth, 140, width / 2 , 140);

        translate(width/2,height/2);  // Translate to the center
        rotate(theta -= 0.001);                // Rotate by theta
        textAlign(CENTER);
        text("Watching the wheels",0,0);
    }
}
