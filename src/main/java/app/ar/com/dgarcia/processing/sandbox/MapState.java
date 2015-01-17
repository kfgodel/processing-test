package app.ar.com.dgarcia.processing.sandbox;

import java.util.HashMap;
import java.util.Map;

/**
 * This type implements the state with a map
 * Created by ikari on 16/01/2015.
 */
public class MapState implements ObjectState {

    private Map<String,Object> map;

    @Override
    public <T> T getPart(String partName) {
        return (T) map.get(partName);
    }

    @Override
    public void setPart(String partName, Object part) {
        map.put(partName, part);
    }

    public static MapState create() {
        MapState state = new MapState();
        state.map = new HashMap<>();
        return state;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }
}
