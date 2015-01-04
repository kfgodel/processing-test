package app.ar.com.dgarcia.processing.controlP5tests;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.DropdownList;
import processing.core.PApplet;

/**
 * Created by ikari on 03/01/2015.
 */
public class DropdownListTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { DropdownListTest.class.getName() });
    }
    ControlP5 cp5;

    DropdownList d1, d2;

    int cnt = 0;

    public void setup() {
        size(700, 400,P3D);

        cp5 = new ControlP5(this);
        // create a DropdownList
        d1 = cp5.addDropdownList("myList-d1")
                .setPosition(100, 100)
        ;

        customize(d1); // customize the first list

        // create a second DropdownList
        d2 = cp5.addDropdownList("myList-d2")
                .setPosition(400, 100)
                .setSize(200,200)
        ;

        customize(d2); // customize the second list
        d2.setIndex(10);
    }


    void customize(DropdownList ddl) {
        // a convenience function to customize a DropdownList
        ddl.setBackgroundColor(color(190));
        ddl.setItemHeight(20);
        ddl.setBarHeight(15);
        ddl.captionLabel().set("dropdown");
        ddl.captionLabel().style().marginTop = 3;
        ddl.captionLabel().style().marginLeft = 3;
        ddl.valueLabel().style().marginTop = 3;
        for (int i=0;i<40;i++) {
            ddl.addItem("item "+i, i);
        }
        //ddl.scroll(0);
        ddl.setColorBackground(color(60));
        ddl.setColorActive(color(255, 128));
    }



    public void keyPressed() {
        // some key events to change the properties of DropdownList d1
        if (key=='1') {
            // set the height of a pulldown menu, should always be a multiple of itemHeight
            d1.setHeight(210);
        }
        else if (key=='2') {
            // set the height of a pulldown menu, should always be a multiple of itemHeight
            d1.setHeight(120);
        }
        else if (key=='3') {
            // set the height of a pulldown menu item, should always be a fraction of the pulldown menu
            d1.setItemHeight(30);
        }
        else if (key=='4') {
            // set the height of a pulldown menu item, should always be a fraction of the pulldown menu
            d1.setItemHeight(12);
            d1.setBackgroundColor(color(255));
        }
        else if (key=='5') {
            // add new items to the pulldown menu
            int n = (int)(random(100000));
            d1.addItem("item "+n, n);
        }
        else if (key=='6') {
            // remove items from the pulldown menu  by name
            d1.removeItem("item "+cnt);
            cnt++;
        }
        else if (key=='7') {
            d1.clear();
        }
    }

    void controlEvent(ControlEvent theEvent) {
        // DropdownList is of type ControlGroup.
        // A controlEvent will be triggered from inside the ControlGroup class.
        // therefore you need to check the originator of the Event with
        // if (theEvent.isGroup())
        // to avoid an error message thrown by controlP5.

        if (theEvent.isGroup()) {
            // check if the Event was triggered from a ControlGroup
            println("event from group : "+theEvent.getGroup().getValue()+" from "+theEvent.getGroup());
        }
        else if (theEvent.isController()) {
            println("event from controller : "+theEvent.getController().getValue()+" from "+theEvent.getController());
        }
    }

    public void draw() {
        background(128);
    }
}
