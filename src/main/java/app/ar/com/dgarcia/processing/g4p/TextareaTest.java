package app.ar.com.dgarcia.processing.g4p;

import g4p_controls.*;
import processing.core.PApplet;

/**
 * Created by ikari on 07/01/2015.
 */
public class TextareaTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { TextareaTest.class.getName() });
    }


    // The code in this tab is used to create all the other
// controls needed to configure the knob control.


    GTextArea txaSample;
    int bgcol = 32;
    String startText;

    public void setup() {
        size(700, 360);

        //Load some sample text
        String[] paragraphs = loadStrings("book3.txt");
        startText = PApplet.join(paragraphs, '\n');

        // Create a text area with both horizontal and
        // vertical scrollbars that automatically hide
        // when not needed.
        txaSample = new GTextArea(this, 80, 20, 290, 300, G4P.SCROLLBARS_BOTH | G4P.SCROLLBARS_AUTOHIDE);
        txaSample.setText(startText, 310);
        // Set some default text
        txaSample.setPromptText("Please enter some text");

        createConfigControls();
    }

    public void draw() {
        background(bgcol);
        fill(227, 230, 255);
        noStroke();
        rect(width - 190, 0, 200, height);
    }

    public void handleSliderEvents(GValueControl slider, GEvent event) {
        if (slider == sdrBack)
            bgcol = slider.getValueI();
    }

    public void handleTextEvents(GEditableTextControl textarea, GEvent event) {
        println(event);
    }

    public void handleButtonEvents(GButton button, GEvent event) {
        if (event == GEvent.CLICKED) {
            if (button.tagNo >= 100)
                txaSample.setLocalColorScheme(button.tagNo - 1000);
            if (button == btnItalic)
                txaSample.setSelectedTextStyle(G4P.POSTURE, G4P.POSTURE_OBLIQUE);
            if (button == btnPlain)
                txaSample.clearStyles();
            if (button == btnBold)
                txaSample.setSelectedTextStyle(G4P.WEIGHT, G4P.WEIGHT_BOLD);
            if (button == btnSuper)
                txaSample.setSelectedTextStyle(G4P.SUPERSCRIPT, G4P.SUPERSCRIPT_SUPER);
            if (button == btnSub)
                txaSample.setSelectedTextStyle(G4P.SUPERSCRIPT, G4P.SUPERSCRIPT_SUB);
            if (button == btnText)
                txaSample.setText(startText, 310);
        }
    }

    public void handleToggleControlEvents(GToggleControl checkbox, GEvent event) {
        if (checkbox == cbxJustify)
            txaSample.setJustify(checkbox.isSelected());
    }

    public void handleKnobEvents(GValueControl knob, GEvent event) {
        txaSample.setRotation(knbAngle.getValueF(), GControlMode.CENTER);
    }




    GButton[] btnColours = new GButton[8];
    GButton btnItalic, btnBold, btnPlain, btnSuper, btnSub, btnText;
    GCheckbox cbxJustify;
    GLabel lblStyleInstr;
    GKnob knbAngle;
    GSlider sdrBack;
    StyledString ss;

    public void createConfigControls() {
        int x = width - 42, y = 2;
        for (int i = 0; i < btnColours.length; i++) {
            btnColours[i] = new GButton(this, x, y + i * 20, 40, 18, "" + (i+1));
            btnColours[i].tag = "Button: " + (i+1);
            btnColours[i].setLocalColorScheme(i);
            btnColours[i].tagNo = 1000+i;
        }

        // Create sliders
        x = width-100;
        y = 162;
        sdrBack = new GSlider(this, x, y, 162, 80, 12);
        sdrBack.setLimits(bgcol, 0, 255);
        sdrBack.setRotation(-PI/2);
        sdrBack.setTextOrientation(G4P.ORIENT_RIGHT);
        sdrBack.setEasing(20);
        sdrBack.setShowValue(true);
        sdrBack.setShowTicks(true);

        x = width -180;
        y = 2;
        btnPlain = new GButton(this, x, 2, 66, 20, "Clear");
        btnPlain.tag = "Button:  Clear";

        btnItalic = new GButton(this, x, 2 + 22, 66, 20, "Italic");
        btnItalic.tag = "Button:  Italic";
        ss = new StyledString("Italic");
        ss.addAttribute(G4P.POSTURE, G4P.POSTURE_OBLIQUE);
        btnItalic.setStyledText(ss);

        btnBold = new GButton(this, x, 2 + 44, 66, 20, "Bold");
        btnBold.tag = "Button:  Bold";
        ss = new StyledString("Bold");
        ss.addAttribute(G4P.WEIGHT, G4P.WEIGHT_BOLD);
        btnBold.setStyledText(ss);

        btnSuper = new GButton(this, x, 2 + 66, 66, 20, "Superscript");
        btnSuper.tag = "Button:  Superscript";
        ss = new StyledString("Superscript");
        ss.addAttribute(G4P.SUPERSCRIPT, G4P.SUPERSCRIPT_SUPER, 5, 11);
        btnSuper.setStyledText(ss);

        btnSub = new GButton(this, x, 2 + 88, 66, 20, "SubScript");
        btnSub.tag = "Button:  Subscript";
        ss = new StyledString("Subscript");
        ss.addAttribute(G4P.SUPERSCRIPT, G4P.SUPERSCRIPT_SUB, 3, 9);
        btnSub.setStyledText(ss);

        cbxJustify = new GCheckbox(this, x, 2 + 110, 66, 20, "Justify");
        cbxJustify.setOpaque(true);

        btnText = new GButton(this, x, 142, 66, 36, "RESET TEXT");

        x = width - 180;
        y = 176;
        String si = "Use the keyboard (Windows shortcut keys) or the mouse to select some text then click on the style buttons to change the style of the selected text";
        lblStyleInstr = new GLabel(this, x, y, 170, 100, si);
        lblStyleInstr.setOpaque(true);

        x = width - 130;
        y = height - 80;
        knbAngle = new GKnob(this, x, y, 70, 70, 0.6f);
        knbAngle.setTurnRange(0, 360);
        knbAngle.setLimits(0.0f, 0.0f, TWO_PI);
        knbAngle.setTurnMode(G4P.CTRL_ANGULAR);
        knbAngle.setIncludeOverBezel(true);
        knbAngle.setNbrTicks(13);
        knbAngle.setStickToTicks(true);
    }
}
