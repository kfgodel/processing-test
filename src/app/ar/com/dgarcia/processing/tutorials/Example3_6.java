package app.ar.com.dgarcia.processing.tutorials;

import processing.core.PApplet;

/**
 * Example taken from the comments of example 3.6
 * Created by ikari on 24/12/2014.
 */
public class Example3_6 extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { Example3_6.class.getName() });
    }


    public void setup(){
        size(400,400);
        smooth();
        frameRate(10);
    }

    public void draw(){
        background(255);
        rectMode(CENTER);
        ellipseMode(CENTER);

//antennas
        stroke(255,0,0);
        line(mouseX-10,mouseY-50,pmouseX-20,pmouseY-70);
        line(mouseX+10,mouseY-50,pmouseX+20,pmouseY-70);
        stroke(0);
        strokeWeight(2);
        point(pmouseX-15,pmouseY-70);
        point(pmouseX-25,pmouseY-70);
        point(pmouseX-20,pmouseY-75);
        point(pmouseX+15,pmouseY-70);
        point(pmouseX+25,pmouseY-70);
        point(pmouseX+20,pmouseY-75);

//head//
        fill(100);
        noStroke();
        rect(mouseX,mouseY-45,40,30);

//eyes
        fill(255);
        stroke(0);
        strokeWeight(1);
        ellipse(mouseX-10,mouseY-50,(abs(mouseX-pmouseX)*2),(abs(mouseY-pmouseY)*2));
        ellipse(mouseX+10,mouseY-50,(abs(mouseX-pmouseX)*2),(abs(mouseY-pmouseY)*2));
        fill(mouseX,0,mouseY);
        ellipse(mouseX-10,mouseY-50,10,10);
        ellipse(mouseX+10,mouseY-50,10,10);
        fill(255);
        noStroke();

//mouth
        ellipse(mouseX+7,mouseY-37,5,5);
        ellipse(mouseX-7,mouseY-37,5,5);
        rect(mouseX,mouseY-37,16,5);
        stroke(1);
        line(mouseX-5,mouseY-40,mouseX-5,mouseY-35);
        line(mouseX,mouseY-40,mouseX,mouseY-35);
        line(mouseX+5,mouseY-40,mouseX+5,mouseY-35);

//neck
        noStroke();
        fill(80);
        rect(mouseX,mouseY-25,10,10);

//wheels
        ellipse(mouseX-20,mouseY-20,10,10);
        ellipse(mouseX+20,mouseY-20,10,10);

//body
        ellipse(mouseX,mouseY+20,30,30);
        fill(100,200);
        rect(mouseX,mouseY,40,40);

//hands
        stroke(1);
        strokeWeight(1);
        line(mouseX-20,mouseY-20,pmouseX-30,pmouseY+10);
        line(mouseX+20,mouseY-20,pmouseX+30,pmouseY+10);
        noStroke();
        ellipse(pmouseX-30,pmouseY+10,10,10);
        ellipse(pmouseX+30,pmouseY+10,10,10);
        fill(255);
        ellipseMode(CORNER);
        ellipse(pmouseX-35,pmouseY+10,5,5);
        ellipse(pmouseX+30,pmouseY+10,5,5);
    }

    public void keyPressed(){
        background(255);
    }

    public void mousePressed(){
        println("Take me to your leader!");
    }
}
