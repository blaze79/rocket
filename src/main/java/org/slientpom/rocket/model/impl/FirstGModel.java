package org.slientpom.rocket.model.impl;

import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.FlyTrack;
import org.slientpom.rocket.domain.flight.TargetDron;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;
import org.slientpom.rocket.model.SingleFlightModel;
import org.slientpom.rocket.model.SingleFlightProducer;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 27.06.2018.
 */
public class FirstGModel implements SingleFlightModel {

    private double maxG = 5.5;

    @Override
    public FlyTrack generateFlight() {
        TargetDron dron = new TargetDron(
                new Fly(
                        Point.point(-700, -100),
                        Vector.vector(0, 200)
                ),
                gLoad(maxG)
        );

        SingleFlightProducer flightScenario = new SingleFlightProducer(dron);
        return flightScenario.flyDrone(0.01, 2500);
    }
}
