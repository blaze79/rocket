package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.geom.Fly;

/**
 * Created by Vlad on 29.06.2018.
 */
public interface FlyWithSeeker {
    Fly currentPosition();
    boolean step(double t, Fly target);
    boolean canFly();
}
