package org.slientpom.rocket.domain.flight.seekers;

import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 16.07.2018.
 */
public interface OmegaMeter {
    double findAngleSpeed(double t, Vector direction);
}
