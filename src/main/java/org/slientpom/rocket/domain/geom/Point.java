package org.slientpom.rocket.domain.geom;

/**
 * Created by Vlad on 27.06.2018.
 */
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point move(Vector vec) {
        return point(x + vec.getX(), y + vec.getY());
    }

    public Point move(Vector vec, double l) {
        return point(x + vec.getX() * l, y + vec.getY() * l);
    }

    public Vector vectorTo(Point a) {
        return Vector.vector(a.x - x, a.y - y);
    }

    public static Point point(double x, double y) {
        return new Point(x, y);
    }
}
