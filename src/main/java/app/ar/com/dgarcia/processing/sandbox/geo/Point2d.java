package app.ar.com.dgarcia.processing.sandbox.geo;

import processing.core.PApplet;

/**
 * Este tipo representa un punto con 2 dimensiones
 * Created by ikari on 13/01/2015.
 */
public interface Point2d {

    Point1d getX();
    Point1d getY();

    public static Point2d create(Point1d x, Point1d y){
        return new Point2d() {
            @Override
            public Point1d getX() {
                return x;
            }

            @Override
            public Point1d getY() {
                return y;
            }
        };
    }

    default Point2d toTheRight(int rightOffset){
        return create(this.getX().plus(rightOffset),this.getY());
    };

    default Point2d toTheLeft(int leftOffset){
        return create(this.getX().minus(leftOffset),this.getY());
    };

    default Point2d toTheTop(int topOffset){
        return  create(this.getX(), this.getY().minus(topOffset));
    };

    default Point2d toTheBottom(int bottomOffset){
        return  create(this.getX(), this.getY().plus(bottomOffset));
    };

    static Point2d centerOf(PApplet applet) {
        return create(Point1d.horizontalCenterOf(applet), Point1d.verticalCenterOf(applet));
    }

    default Point2d relativeTo(Point2d position){
        return create(getX().minus(position.getX().getAsInt()), getY().minus(position.getY().getAsInt()));
    }

    default Point2d absolutize(){
        return Point2d.create(getX().absolutize(), getY().absolutize());
    };

    default float distanceTo(Point2d otherPosition){
        return otherPosition.relativeTo(this).norm();
    }

    default float norm(){
        return (float) Math.sqrt(getX().toPow(2) + getY().toPow(2));
    };
}
