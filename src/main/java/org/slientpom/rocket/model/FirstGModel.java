package org.slientpom.rocket.model;

import org.slientpom.rocket.domain.flight.FlyTrack;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 27.06.2018.
 */
public class FirstGModel implements SingleFlightModel {

    private double gLoad = 5.5;
    private double g=9.8;

    @Override
    public FlyTrack generateFlight() {
        SingleFlight flight = new SingleFlight(
                Point.point(-700, -100),
                Vector.vector(0, 200)
        );

        return flight.flyCircle(g*gLoad, 0.1, 260);
    }
}
