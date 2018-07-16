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

        double angle = Math.acos(Vector.dot(last, direction));
        double part = Vector.dot(last.normal(), direction);
        last = direction;

        if (part > 0) {
            return angle / t;
        } else {
            return -angle / t;
        }
    }
}
