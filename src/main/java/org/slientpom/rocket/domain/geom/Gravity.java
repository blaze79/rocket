package org.slientpom.rocket.domain.geom;

/**
 * Created by Vlad on 29.06.2018.
 */
public class Gravity {
    public static final double G_CONST = 9.8;

    public static double gLoad(double n) {
        return G_CONST * n;
    }
}
