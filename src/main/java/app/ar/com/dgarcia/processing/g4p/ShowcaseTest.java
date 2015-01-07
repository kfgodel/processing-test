package app.ar.com.dgarcia.processing.g4p;

import g4p_controls.*;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Created by ikari on 07/01/2015.
 */
public class ShowcaseTest extends PApplet {

    static public void main(String args[]) {
        PApplet.main(new String[] { ShowcaseTest.class.getName() });
    }


    // G4P components for main window
    GPanel pnlControls;
    GLabel lblAction;
    GTextField txfDemo;
    GTextArea txaDemo;
    GKnob knbDemo;
    GTimer tmrTimer;
    GButton btnTimer;
    GTabManager tt;
    GCheckbox cbxSticky;
    GButton btnControl;  // Used to start controller window
    GStick astick;
    String startText;
    PImage imgBgImage;

    int count = 0;
    GLabel lblMC;
    GOption optAngular, optYdrag, optXdrag;
    GToggleGroup tg;
    GSketchPad spad;
    PGraphics pg;

    public void setup() {
        size(600, 440);
        // Load the background image
        imgBgImage = loadImage("castle.jpg");
        makeDemoControls();
    }

    public void updateGraphic(int n) {
        n = (n < 0) ? 0 : n % 30;
        pg.beginDraw();
        pg.background(0);
        pg.noStroke();
        pg.fill(255);
        pg.ellipseMode(CENTER);
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                int r = i / 10;
                int c = i % 10;
                pg.ellipse(5 + c * 10, 7 + r * 15, 6, 10);
            }
        }
        pg.endDraw();
    }

    public void draw() {
        background(imgBgImage);
        updateGraphic(count);
    }

    public void handleTextEvents(GEditableTextControl textcontrol, GEvent event) {
        if (textcontrol == txaDemo)
            lblAction.setText("TextArea: " + event);
        if (textcontrol == txfDemo)
            lblAction.setText("TextField: " + event);
    }

    public void handlePanelEvents(GPanel panel, GEvent event) {
        lblAction.setText("Panel: " + event);
        if (event == GEvent.DRAGGED && sdrPanelPos != null) {
            sdrPanelPos.setValueX(pnlControls.getX());
            sdrPanelPos.setValueY(pnlControls.getY());
        }
    }

    public void handleSliderEvents(GValueControl slider, GEvent event) {
        if (slider == sdrAlpha) {
            int alpha = sdrAlpha.getValueI();
            if (alpha >= G4P.ALPHA_BLOCK)
                slider.setLocalColorScheme(G4P.BLUE_SCHEME);
            else
                slider.setLocalColorScheme(G4P.RED_SCHEME);
            G4P.setWindowAlpha(this, alpha);
        }
    }

    public void handleToggleControlEvents(GToggleControl option, GEvent event) {
        if (option == optAngular) {
            lblAction.setText("Knob is now using angular drag for rotation");
            knbDemo.setTurnMode(G4P.CTRL_ANGULAR);
        }
        if (option == optXdrag) {
            lblAction.setText("Knob is now using horizontal drag for rotation");
            knbDemo.setTurnMode(G4P.CTRL_HORIZONTAL);
        }
        if (option == optYdrag) {
            lblAction.setText("Knob is now using vertical drag for rotation");
            knbDemo.setTurnMode(G4P.CTRL_VERTICAL);
        }
        if (option == cbxSticky) {
            lblAction.setText("Stick to ticks option changed");
            knbDemo.setStickToTicks(cbxSticky.isSelected());
        }
    }

    public void handleStickEvents(GStick stick, GEvent event) {
        String pos = "";
        // 4-way stick so forget 1,3,5 & 7 positions
        switch(stick.getPosition()) {
            case 0:
                pos = "RIGHT";
                break;
            case 2:
                pos = "DOWN";
                break;
            case 4:
                pos = "LEFT";
                break;
            case 6:
                pos = "UP";
                break;
        }
        lblAction.setText("Stick control is in " + pos + " position");
    }

    public void handleSlider2DEvents(GSlider2D slider2d, GEvent event) {
        pnlControls.moveTo(slider2d.getValueXI(), slider2d.getValueYI());
    }

    public void handleKnobEvents(GValueControl knob, GEvent event) {
        if (knob == knbDemo)
            lblAction.setText("Knob value is now " + knbDemo.getValueS());
        if (knob == knbAngle) {
            pnlControls.setRotation(knbAngle.getValueF(), GControlMode.CENTER);
        }
    }

    public void handleButtonEvents(GButton button, GEvent event) {
        // Create the control window?
        if (button == btnControl && event == GEvent.CLICKED) {
            lblAction.setText("Open control window and disable the button");
            createControlWindow();
            btnControl.setEnabled(false);
        }
        // Change the colour scheme
        if (button.tagNo >= 1000 && event == GEvent.CLICKED)
            G4P.setWindowColorScheme(this, button.tagNo - 1000);
        if (button == btnTimer && event == GEvent.CLICKED) {
            if (tmrTimer.isRunning())
                tmrTimer.stop();
            else
                tmrTimer.start();
        }
    }

    public void myTimerFunction(GTimer timer) {
        count++;
    }


    // =============================================
// Create the configuration window and controls
// =============================================

    GWindow windControl;
    GButton[] btnColours = new GButton[8];
    GSlider sdrAlpha;
    GKnob knbAngle;
    GLabel lblAlpha1, lblAlpha2;
    GSlider2D sdrPanelPos;

    public void createControlWindow() {
        windControl = new GWindow(this, "Controls", 600, 400, 400, 300, false, JAVA2D);
        PApplet app = windControl.papplet; // save some typing
        // Create colour scheme selectors
        int x = 8;
        int y = 50; // app.height - 24;
        for (int i = 0; i < btnColours.length; i++) {
            btnColours[i] = new GButton(app, x  + i * 34, y, 30, 18, "" + (i+1));
            btnColours[i].tag = "Button: " + (i+1);
            btnColours[i].setLocalColorScheme(i);
            btnColours[i].tagNo = 1000+i;
        }
        y = 0; //app.height - 90;
        sdrAlpha = new GSlider(app, x, y, 268, 60, 12);
        sdrAlpha.setLimits(255, 0, 255);
//  sdrAlpha.setRotation(-PI/2);
//  sdrAlpha.setTextOrientation(G4P.ORIENT_RIGHT);
        sdrAlpha.setEasing(20);
        sdrAlpha.setShowValue(true);
        sdrAlpha.setShowTicks(true);

        x = 290;
        y = 16;
        lblAlpha1 = new GLabel(app, x, y, 120, 26, "Alpha Slider");
        lblAlpha1.setTextBold();
        lblAlpha2 = new GLabel(app, x, y + 30, 110, 80, "When alpha falls below " + G4P.ALPHA_BLOCK + " it will disable the controls in the main sketch.");

        x = app.width - 120;
        y = app.height - 120;
        knbAngle = new GKnob(app, x, y, 100, 100, 0.75f);
        knbAngle.setTurnRange(0, 360);
        knbAngle.setLimits(0.0f, 0.0f, TWO_PI);
        knbAngle.setTurnMode(G4P.CTRL_ANGULAR);
        knbAngle.setIncludeOverBezel(true);
        knbAngle.setNbrTicks(13);
        knbAngle.setStickToTicks(true);

        sdrPanelPos = new GSlider2D(app, 40, 84, 200, 200);
        sdrPanelPos.setLimitsX(pnlControls.getX(), 0, 200);
        sdrPanelPos.setLimitsY(pnlControls.getY(), 0, 200);

        windControl.addDrawHandler(this, "drawController");
    }

    /*
       * The draw handler for the control window
     */
    public void drawController(GWinApplet appc, GWinData data) {
        appc.background(227, 230, 255);
    }

    // ============================================
// Create the showcase controls
// ============================================

    public void makeDemoControls() {
        // Create the panel of this size
        int pX = 10, pY = 30, pHeight = 240, pWidth = 400;
        pnlControls = new GPanel(this, pX, pY, pWidth, pHeight, "Panel Tab Text (drag to move : click to open/close)");
        pnlControls.setOpaque(true);
        pnlControls.setCollapsed(false);
        // Create the user action feedback label
        lblAction = new GLabel(this, 0, pHeight-20, pWidth, 20, "This is a GLabel control - for status info");
        lblAction.setOpaque(true);
        pnlControls.addControl(lblAction);

        //Load some sample text
        String[] paragraphs = loadStrings("book.txt");
        startText = PApplet.join(paragraphs, '\n');

        // Create a text area with both horizontal and
        // vertical scrollbars that automatically hide
        // when not needed.
        txaDemo = new GTextArea(this, 4, 24, 220, 96, G4P.SCROLLBARS_BOTH | G4P.SCROLLBARS_AUTOHIDE);
        txaDemo.setText(startText, 250);
        txaDemo.setPromptText("Please enter some text");
        pnlControls.addControl(txaDemo);
        // Create a text field with horizontal scrollbar
        txfDemo = new GTextField(this, 4, 126, 220, 30, G4P.SCROLLBARS_HORIZONTAL_ONLY | G4P.SCROLLBARS_AUTOHIDE);
        txfDemo.setPromptText("This is prompt text");
        pnlControls.addControl(txfDemo);
        // Add tab controls
        tt = new GTabManager();
        tt.addControls(txaDemo, txfDemo);

        // Checkbox
        cbxSticky = new GCheckbox(this, 230, 130, 120, 20, "Stick to ticks");
        pnlControls.addControl(cbxSticky);

        // Knob
        knbDemo = new GKnob(this, 230, 30, 90, 90, 0.76f);
        knbDemo.setLimits(0.0f, 0.0f, TWO_PI);
        knbDemo.setNbrTicks(10);
        knbDemo.setEasing(5);
        knbDemo.setShowArcOnly(true);
        knbDemo.setOverArcOnly(true);
        knbDemo.setSensitivity(2.0f);
        pnlControls.addControl(knbDemo);

        lblMC = new GLabel(this, 310, 22, 100, 50, "Knob turning control");
        lblMC.setTextAlign(GAlign.LEFT, null);
        lblMC.setTextAlign(GAlign.CENTER, GAlign.TOP);
        lblMC.setTextBold();
        optAngular = new GOption(this, 326, 60, 80, 18, "Angular");
        optXdrag = new GOption(this, 326, 80, 80, 18, "X drag");
        optYdrag = new GOption(this, 326, 100, 80, 18, "Y drag");
        tg = new GToggleGroup();
        tg.addControls(optAngular, optXdrag, optYdrag);
        optXdrag.setSelected(true);
        pnlControls.addControls(lblMC, optAngular, optXdrag, optYdrag);

        // Create timer button
        btnTimer = new GButton(this, pWidth-100, pHeight-60, 100, 40, "Start");
        btnTimer.setIcon("time.png", 1, null, null);
        tmrTimer = new GTimer(this, this, "myTimerFunction", 300);
        pnlControls.addControl(btnTimer);
        // When the timer is on we will see it in a GSketchPad control
        // Create the GSkethPad control to position and display the graphic
        spad = new GSketchPad(this, pWidth-210, pHeight-60, 100, 40);
        // Now create and clear graphic to be used (JAVA parameter
        // parameter is only needed for Processing 1.5.1)
        pg = createGraphics(100, 46, JAVA2D);
        // Add the graphic to the control.
        spad.setGraphic(pg);
        updateGraphic(3); // Method for drawing the graphic
        pnlControls.addControl(spad);
        // 4-way stick control
        astick = new GStick(this, pWidth - 290, pHeight - 80, 60, 60);
        pnlControls.addControl(astick);
        // Create button to open the control window
        btnControl = new GButton(this, 2, pHeight - 60, 100, 40, "G4P config window");
        pnlControls.addControl(btnControl);
    }

}
