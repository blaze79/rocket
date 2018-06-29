package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.flight.seekers.ThermalSeeker;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 29.06.2018.
 */
public class ThermalSeekerMissile extends MissileWithSeeker implements FlyWithSeeker {
    private ThermalSeeker seeker;
    private double lastANormal = 0;

    public ThermalSeekerMissile(Fly fly, ThermalSeeker seeker) {
        super(fly);
        this.seeker = seeker;
    }

    @Override
    public Fly currentPosition() {
        return getFly().copy();
    }

    @Override
    public boolean step(double t, Fly target) {
        final Vector vectorToTarget = getFly().getPoint().vectorTo(target.getPoint()).unify();
        final double aNormal = seeker.calculateNormalAcceleration(vectorToTarget, getFly().getVelocity());
        final double aNormalLimit = limitAcceleration(aNormal);
        final double drag = calculateDrag(aNormalLimit);

        if(lastANormal * aNormalLimit < 0) {
            System.out.printf("aNormal change sign:%f%n ", aNormalLimit);
        }
        lastANormal = aNormalLimit;

        getFly().step(t, drag, aNormalLimit);

        return isHit(target);
    }

    @Override
    public boolean canFly() {
        return canFlyImpl();
    }
}
