package app.ar.com.dgarcia.processing.sandbox.iterables;

import java.util.Collection;

/**
 * Created by ikari on 21/01/2015.
 */
public class Collections {

    public static<T> MergeResult<T> merge(Collection<T> older, Collection<T> newer) {
        return MergeResultImpl.create(older.iterator(), newer.iterator());
    }
}
