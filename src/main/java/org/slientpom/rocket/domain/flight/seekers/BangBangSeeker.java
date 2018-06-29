package org.slientpom.rocket.domain.flight.seekers;

import org.slientpom.rocket.domain.geom.Vector;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 29.06.2018.
 */
public class BangBangSeeker implements ThermalSeeker {
    @Override
    public double calculateNormalAcceleration(Vector target, Vector velocity) {
        Vector rightNormal = velocity.normal();
        if (Vector.dot(rightNormal, target) > 0) {
            return gLoad(100);
        }
        return -gLoad(100);
    }
}
