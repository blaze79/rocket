package org.slientpom.rocket.domain.flight.seekers;

import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 16.07.2018.
 */
public class SimpleOmegaMeter implements OmegaMeter {

    private Vector last = null;

    @Override
    public double findAngleSpeed(double t, Vector direction) {
        if (last == null) {
            last = direction;
            return 0;
        }

        double part = Vector.dot(last.normal(), direction);
        double angle = Math.acos(Vector.dot(last, direction));

        last = direction;
        if (Double.isNaN(angle)) {
            return 0;
        }

        if (part > 0) {
            return angle / t;
        } else {
            return -angle / t;
        }
    }
}
