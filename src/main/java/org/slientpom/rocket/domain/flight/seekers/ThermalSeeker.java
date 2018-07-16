package org.slientpom.rocket.domain.flight.seekers;

import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 29.06.2018.
 */
public interface ThermalSeeker {
    double calculateNormalAcceleration(Vector target, Vector velocity, double t);
}
