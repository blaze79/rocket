package org.slientpom.rocket.domain.flight;

/**
 * Created by Vlad on 29.06.2018.
 */
public interface FlyTarget {
    Fly currentPosition();
    void step(double t);
}
