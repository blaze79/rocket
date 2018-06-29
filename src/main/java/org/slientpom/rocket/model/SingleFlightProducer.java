package org.slientpom.rocket.model;

import org.slientpom.rocket.domain.flight.FlyTarget;
import org.slientpom.rocket.domain.geom.FlyTrack;

/**
 * Created by Vlad on 27.06.2018.
 */
public class SingleFlightProducer {
    private FlyTarget target;

    public SingleFlightProducer(FlyTarget target) {
        this.target = target;
    }

    public FlyTrack flyDrone(double t, int iter) {
        FlyTrack track = new FlyTrack();
        track.push(target.currentPosition());
        for (int i = 0; i < iter; ++i) {
            target.step(t);
            track.push(target.currentPosition());
        }

        return track;
    }

}
