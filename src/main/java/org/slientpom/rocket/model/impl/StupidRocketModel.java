package org.slientpom.rocket.model.impl;

import org.slientpom.rocket.domain.flight.Fly;
import org.slientpom.rocket.domain.flight.PursitTrack;
import org.slientpom.rocket.domain.flight.StupidMissileDron;
import org.slientpom.rocket.domain.flight.TargetDron;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;
import org.slientpom.rocket.model.PursitFlightModel;
import org.slientpom.rocket.model.PursitFlightProducer;

/**
 * Created by Vlad on 29.06.2018.
 */
public class StupidRocketModel implements PursitFlightModel {
    private double gLoad = 5.5;
    private double g = 9.8;

    @Override
    public PursitTrack generateFlight() {
        TargetDron dron = new TargetDron(
                new Fly(
                        Point.point(-700, -100),
                        Vector.vector(0, 200)
                ),
                g * gLoad
        );

        StupidMissileDron missileDron = new StupidMissileDron(
                new Fly(
                        Point.point(-600, -100),
                        Vector.vector(0, 180)
                ),
                g * gLoad
        );
        PursitFlightProducer flightScenario = new PursitFlightProducer(dron, missileDron);
        return flightScenario.flyPursit(0.01, 2000);
    }
}
