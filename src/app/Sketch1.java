package app;

import processing.core.PApplet;

/**
 * Created by dgarcia on 07/09/2014.
 */
public class Sketch1 extends PApplet {


    static public void main(String args[]) {
        PApplet.main(new String[] { "app.Sketch1" });
    }


    // Cube rotation
    float yTheta = 0.0f;
    float xTheta = 0.0f;

    // A boolean variable that when set to true triggers the PDF recording process
    boolean recordPDF = false;

    public void setup() {
        // OPENGL or P3D mode requires the use of beginRaw() and endRaw() instead of beginRecord() and endRecord().
        size(400, 400, OPENGL);
        smooth();
    }

    public void draw() {
        // Begin making the PDF
        if (recordPDF) {
            beginRaw(PDF, "3D.pdf" ); // If you include "####" in the filename -- "3D-####.pdf" -- separate, numbered PDFs will be made for each frame that is rendered.
        }

        background(255);
        stroke(0);
        noFill();
        line(0,0,0,500,0,0);
        line(0,0,0,0,500,0);
        line(0,0,0,0,0,500);
        translate(width/2,height/2);
        rotateX(xTheta);
        rotateY(yTheta);
        box(100);
        line(0,0,0,500,0,0);
        line(0,0,0,0,500,0);
        line(0,0,0,0,0,500);

        xTheta += 0.02;
        yTheta += 0.03;

        // End making the PDF
        if (recordPDF) {
            endRaw();
            recordPDF = false;
        }
    }

    // Make the PDF when the mouse is pressed
    public void mousePressed() {
        recordPDF = true;
    }

}
