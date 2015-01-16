package app.ar.com.dgarcia.processing.sandbox;

/**
 * Created by ikari on 15/01/2015.
 */
public class ArrayState implements ObjectState {

    private Object[] state;

    public static ArrayState create(int size) {
        ArrayState arrayState = new ArrayState();
        arrayState.state = new Object[size];
        return arrayState;
    }

    @Override
    public <T> T getPart(int partIndex) {
        return (T) state[partIndex];
    }

    @Override
    public void setPart(int partIndex, Object part) {
        state[partIndex] = part;
    }
}
