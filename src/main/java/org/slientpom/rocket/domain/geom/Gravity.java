package org.slientpom.rocket.domain.geom;

/**
 * Created by Vlad on 29.06.2018.
 */
public class Gravity {
    public static final double G_CONST = 9.8;
    private static double G_LIMIT_SPEED = 600;

    public static double gLoad(double n) {
        return G_CONST * n;
    }

    public static double gLimitForSpeed(double sqSpeed, double gLimit) {
        return gLimit * sqSpeed / G_LIMIT_SPEED / G_LIMIT_SPEED;
    }
}
