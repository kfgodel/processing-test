package app.ar.com.dgarcia.processing.sandbox.geo;

/**
 * This type represents a statuful point 2d
 * Created by ikari on 29/01/2015.
 */
public class Point2dImpl implements Point2d {

    private int x;
    public static final String x_FIELD = "x";

    private int y;
    public static final String y_FIELD = "y";


    @Override
    public Point1d getX() {
        return Point1d.create(x);
    }

    @Override
    public Point1d getY() {
        return Point1d.create(y);
    }

    public static Point2dImpl create(int x, int y) {
        Point2dImpl point2d = new Point2dImpl();
        point2d.x = x;
        point2d.y = y;
        return point2d;
    }

    public static Point2dImpl create(Point2d otherPoint) {
        return create(otherPoint.getX().getAsInt(), otherPoint.getY().getAsInt());
    }


}
