package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.flight.filter.ControlFilter;
import org.slientpom.rocket.domain.flight.filter.SimpleControlFilter;
import org.slientpom.rocket.domain.flight.seekers.ThermalSeeker;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 29.06.2018.
 */
public class ThermalSeekerMissile extends AbstractMissile {
    private ThermalSeeker seeker;

    public ThermalSeekerMissile(Fly fly, ThermalSeeker seeker) {
        super(fly);
        this.seeker = seeker;
    }

    @Override
    protected double findNextAcceleration(Fly target) {
        final Vector vectorToTarget = getFly().getPoint().vectorTo(target.getPoint()).unify();
        return seeker.calculateNormalAcceleration(vectorToTarget, getFly().getVelocity());
    }

}
