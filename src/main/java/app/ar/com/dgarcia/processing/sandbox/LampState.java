package app.ar.com.dgarcia.processing.sandbox;

import processing.core.PApplet;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by ikari on 15/01/2015.
 */
public class LampState {


    public static final int TOGGLE_METHOD = 0;
    public static final int DRAW_METHOD = 1;
    public static final int POSITION = 2;

    public static Consumer<ObjectState> getToggleMethodFrom(ObjectState state){
        return state.<Consumer<ObjectState>>getPart(TOGGLE_METHOD);
    }
    public static void setToggleMethodTo(ObjectState state, Consumer<ObjectState> method){
        state.setPart(TOGGLE_METHOD, method);
    }

    public static BiConsumer<PApplet,Point2d> getDrawMethodFrom(ObjectState state){
        return state.<BiConsumer<PApplet,Point2d>>getPart(DRAW_METHOD);
    }
    public static void setDrawMethodTo(ObjectState state, BiConsumer<PApplet,Point2d> method){
        state.setPart(DRAW_METHOD, method);
    }

    public static Point2d getPositionFrom(ObjectState state){
        return state.<Point2d>getPart(POSITION);
    }
    public static void setPositionTo(ObjectState state, Point2d position){
        state.setPart(POSITION, position);
    }

    private static void toggleOn(ObjectState state) {
        setDrawMethodTo(state, LampBehavior::drawActiveOn);
        setToggleMethodTo(state, LampState::toggleOff);
    }

    private static void toggleOff(ObjectState state) {
        setDrawMethodTo(state, LampBehavior::drawInactiveOn);
        setToggleMethodTo(state, LampState::toggleOn);
    }

    public static Lamp create(Point2d position) {
        ObjectState state = ArrayState.create(3);
        setPositionTo(state,position);
        toggleOff(state);
        return new Lamp() {
            @Override
            public void toggle() {
                getToggleMethodFrom(state).accept(state);
            }

            @Override
            public void drawOn(PApplet applet) {
                getDrawMethodFrom(state).accept(applet, getPositionFrom(state));
            }
        };
    }

}
