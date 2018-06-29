package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.geom.Fly;

/**
 * Created by Vlad on 29.06.2018.
 */
public interface FlyTarget {
    Fly currentPosition();
    void step(double t);
}
