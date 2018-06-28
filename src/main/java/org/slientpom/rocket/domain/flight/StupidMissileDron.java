package org.slientpom.rocket.domain.flight;

/**
 * Created by Vlad on 29.06.2018.
 */

/**
 * I don't want to be a missile, I am target drone
 */
public class StupidMissileDron implements FlyWithSeeker {

    private TargetDron implDrone;

    public StupidMissileDron(Fly fly, double normalA) {
        implDrone = new TargetDron(fly, normalA);
    }

    @Override
    public Fly currentPosition() {
        return implDrone.currentPosition();
    }

    @Override
    public boolean step(double t, Fly target) {
        implDrone.step(t);
        return false;
    }
}
