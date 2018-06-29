package org.slientpom.rocket.domain.geom;

/**
 * Created by Vlad on 27.06.2018.
 */
public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector mul(double d) {
        return vector(x*d, y*d);
    }

    public Vector add(Vector b) {
        return add(b, 1);
    }

    public Vector add(Vector b, double l) {
        return vector(x + b.x*l, y + b.y*l);
    }

    public Vector normal() {
        return vector(y, -x);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double lengthSq() {
        return x * x + y * y;
    }

    public Vector unify() {
        return mul(1/length());
    }

    public static double dot(Vector a, Vector b) {
        return a.x*b.x + a.y*b.y;
    }

    public static Vector vector(double x, double y){
        return new Vector(x,y);
    }
}
