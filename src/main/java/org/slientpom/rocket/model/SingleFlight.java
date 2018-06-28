package org.slientpom.rocket.model;

import org.slientpom.rocket.domain.flight.Fly;
import org.slientpom.rocket.domain.flight.FlyTrack;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 27.06.2018.
 */
public class SingleFlight {
    Fly fly;

    public SingleFlight(Point object, Vector velocity) {
        fly = new Fly(object, velocity);
    }

    public FlyTrack flyCircle(double a, double t, int iter) {
        FlyTrack track = new FlyTrack();
        track.push(fly.copy());
        for (int i = 0; i < iter; ++i) {
            fly.step(t, 0, a);
            track.push(fly.copy());
        }

        return track;
    }

}
