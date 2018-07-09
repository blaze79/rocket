package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.flight.filter.ControlFilter;
import org.slientpom.rocket.domain.flight.filter.SimpleControlFilter;
import org.slientpom.rocket.domain.flight.seekers.ThermalSeeker;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 29.06.2018.
 */
public class ThermalSeekerMissile extends MissileWithSeeker implements FlyWithSeeker {
    private ThermalSeeker seeker;
    private double lastANormal = 0;
    private ControlFilter filter = new SimpleControlFilter();

    public ThermalSeekerMissile(Fly fly, ThermalSeeker seeker) {
        super(fly);
        this.seeker = seeker;
    }

    @Override
    public Fly currentPosition() {
        return getFly().copy();
    }

    protected double calculateA(Fly target) {
        final Vector vectorToTarget = getFly().getPoint().vectorTo(target.getPoint()).unify();
        final double aNormal = seeker.calculateNormalAcceleration(vectorToTarget, getFly().getVelocity());
        final double aNormalLimit = limitAcceleration(aNormal);
        return limitAcceleration(
                filter.filterControl(aNormalLimit)
        );
    }

    @Override
    public boolean step(double t, Fly target) {
        final double nextA = calculateA(target);
        final double drag = calculateDrag(nextA);

        if(lastANormal * nextA < 0) {
            System.out.printf("aNormal change sign:%f%n ", nextA);
        }
        lastANormal = nextA;

        getFly().step(t, drag, nextA);
        return isHit(target);
    }

    @Override
    public boolean canFly() {
        return canFlyImpl();
    }

    public void setFilter(ControlFilter filter) {
        this.filter = filter;
    }
}
