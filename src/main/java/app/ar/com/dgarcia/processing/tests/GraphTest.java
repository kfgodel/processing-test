package app.ar.com.dgarcia.processing.tests;

import processing.core.PApplet;

/**
 *
 * Created by ikari on 04/01/2015.
 */
public class GraphTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { GraphTest.class.getName() });
    }

    @Override
    public void setup() {
        size(1024,768);
        background(200);
        translate(width / 2, height / 2);
        
        drawZoomCircles();

    }

    private void drawZoomCircles() {
        stroke(0);
        int startingCircleSize = 100;
        int rings = 7;
        float arcLongitude = HALF_PI / rings;
        for (int i = 0; i < rings; i++) {
            float arc = HALF_PI - arcLongitude * i;
            float ringWidth = width * cos(arc);
            float ringRadius = ringWidth / 2;

            noFill();
            strokeWeight(1);
            ellipse(0, 0, ringWidth, ringWidth);

            strokeWeight(2);
            float circleSize = startingCircleSize * sin(arc);
            float ringLongitude = 2 * PI * ringRadius;
            int divisions = (int) (ringLongitude / circleSize);
            if(divisions == 0){
                //La primera vez es el circulo del medio
                divisions = 1;
            }
            for (int j = 0; j < divisions; j++) {
                fill(225);
                pushMatrix();
                rotate((TWO_PI/divisions) * j);
                ellipse(ringWidth / 2, 0, circleSize,circleSize);
                fill(100);
                text(String.valueOf(divisions), ringWidth / 2, 0);
                popMatrix();
            }
        }

    }

    private void drawArc() {
        stroke(0);
        noFill();
        arc(0,0,height,height,-HALF_PI,0);
        stroke(blue(128));
        strokeWeight(2);
        int divisions = 5;
        float arcSeparation = HALF_PI / divisions;
        float radio = height / 2;
        for (int i = 0; i < divisions; i++) {
            float arc = HALF_PI - arcSeparation * i;
            float distancia = radio* cos(arc);
            float altura = radio * sin(arc);
            line(distancia,0,distancia,-altura);
            text("sin: " +  sin(arc) + " cos: " + cos(arc), distancia,-altura + 20);
        }
    }

    private void drawCircles() {
        stroke(0);
        int circles = 10;
        int circleSeparation = width / circles;
        for (int i = 0; i < circles; i++) {
            noFill();
            strokeWeight(1);
            int circleSize = (i + 1) * circleSeparation;
            ellipse(0, 0, circleSize, circleSize);
            strokeWeight(2);
            float circleLongitude = 2 * PI * (circleSize / 2);
            int divisions = (int) (circleLongitude / 60);
            for (int j = 0; j < divisions; j++) {
                fill(225);
                pushMatrix();
                rotate((TWO_PI/divisions) * j);
                ellipse(circleSize / 2, 0, 60,60);
                fill(0);
                text(String.valueOf(divisions),circleSize/2,0);
                popMatrix();
            }
        }
    }
}
