package app.ar.com.dgarcia.processing.controlP5tests;

import controlP5.ControlP5;
import controlP5.Textarea;
import processing.core.PApplet;

/**
 * Created by ikari on 03/01/2015.
 */
public class TextAreaTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { TextAreaTest.class.getName() });
    }

    ControlP5 cp5;
    Textarea myTextarea;

    public void setup() {
        size(700,400);
        cp5 = new ControlP5(this);

        myTextarea = cp5.addTextarea("txt")
                .setPosition(100,100)
                .setSize(200,200)
                .setFont(createFont("arial",12))
                .setLineHeight(14)
                .setColor(color(128))
                .setColorBackground(color(255,100))
                .setColorForeground(color(255,100));
        ;
        myTextarea.setText("Lorem Ipsum is simply dummy text of the printing and typesetting"
                        +" industry. Lorem Ipsum has been the industry's standard dummy text"
                        +" ever since the 1500s, when an unknown printer took a galley of type"
                        +" and scrambled it to make a type specimen book. It has survived not"
                        +" only five centuries, but also the leap into electronic typesetting,"
                        +" remaining essentially unchanged. It was popularised in the 1960s"
                        +" with the release of Letraset sheets containing Lorem Ipsum passages,"
                        +" and more recently with desktop publishing software like Aldus"
                        +" PageMaker including versions of Lorem Ipsum."
        );

        cp5.addSlider("changeWidth")
                .setRange(100,400)
                .setValue(200)
                .setPosition(100,20)
                .setSize(100,19)
        ;

        cp5.addSlider("changeHeight")
                .setRange(100,400)
                .setValue(200)
                .setPosition(100,40)
                .setSize(100,19)
        ;

    }


    public void keyPressed() {
        if(key=='r') {
            myTextarea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                            +" Quisque sed velit nec eros scelerisque adipiscing vitae eu sem."
                            +" Quisque malesuada interdum lectus. Pellentesque pellentesque molestie"
                            +" vestibulum. Maecenas ultricies, neque at porttitor lacinia, tellus enim"
                            +" suscipit tortor, ut dapibus orci lorem non ipsum. Mauris ut velit velit."
                            +" Fusce at purus in augue semper tincidunt imperdiet sit amet eros."
                            +" Vestibulum nunc diam, fringilla vitae tristique ut, viverra ut felis."
                            +" Proin aliquet turpis ornare leo aliquam dapibus. Integer dui nisi, condimentum"
                            +" ut sagittis non, fringilla vestibulum sapien. Sed ullamcorper libero et massa"
                            +" congue in facilisis mauris lobortis. Fusce cursus risus sit amet leo imperdiet"
                            +" lacinia faucibus turpis tempus. Pellentesque pellentesque augue sed purus varius"
                            +" sed volutpat dui rhoncus. Lorem ipsum dolor sit amet, consectetur adipiscing elit"
            );

        } else if(key=='c') {
            myTextarea.setColor(0xffffffff);
        }
    }
    public void draw() {
        background(0);
        if(keyPressed && key==' ') {
            myTextarea.scroll((float)mouseX/(float)width);
        }
        if(keyPressed && key=='l') {
            myTextarea.setLineHeight(mouseY);
        }
    }

    void changeWidth(int theValue) {
        myTextarea.setWidth(theValue);
    }

    void changeHeight(int theValue) {
        myTextarea.setHeight(theValue);
    }
}
