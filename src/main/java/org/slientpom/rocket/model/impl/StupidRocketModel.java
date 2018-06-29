package org.slientpom.rocket.model.impl;

import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.PursitTrack;
import org.slientpom.rocket.domain.flight.StupidMissileDron;
import org.slientpom.rocket.domain.flight.TargetDron;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;
import org.slientpom.rocket.model.PursitFlightModel;
import org.slientpom.rocket.model.PursitFlightProducer;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 29.06.2018.
 */
public class StupidRocketModel implements PursitFlightModel {
    private double maxG = 5.5;

    @Override
    public PursitTrack generateFlight() {
        TargetDron dron = new TargetDron(
                new Fly(
                        Point.point(-700, -100),
                        Vector.vector(0, 200)
                ),
                gLoad(maxG)
        );

        StupidMissileDron missileDron = new StupidMissileDron(
                new Fly(
                        Point.point(-600, -100),
                        Vector.vector(0, 180)
                ),
                gLoad(maxG)
        );
        PursitFlightProducer flightScenario = new PursitFlightProducer(dron, missileDron);
        return flightScenario.flyPursit(0.01, 2000);
    }
}
