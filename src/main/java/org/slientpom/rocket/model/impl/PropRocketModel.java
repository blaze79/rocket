package org.slientpom.rocket.model.impl;

import org.slientpom.rocket.domain.flight.TargetDron;
import org.slientpom.rocket.domain.flight.missiles.PropThermalMissile;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.PursitTrack;
import org.slientpom.rocket.domain.geom.Vector;
import org.slientpom.rocket.model.PursitFlightModel;
import org.slientpom.rocket.model.PursitFlightProducer;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 29.06.2018.
 */
public class PropRocketModel implements PursitFlightModel {
    private double maxG = 5.5;
    private double maxGBang = 24;

    @Override
    public PursitTrack generateFlight() {
        TargetDron dron = new TargetDron(
                new Fly(
                        Point.point(-600, -100),
                        Vector.vector(0, 200)
                ),
                gLoad(maxG)
        );

        PropThermalMissile missile = new PropThermalMissile(
                new Fly(
                        Point.point(-1200 - 2000, -1000 - 2000),
                        Vector.vector(0, 700)
                ),
                3
        );
        missile.setMaxG(gLoad(maxGBang));
        //missile.setLiftToDrag(3.5);
        missile.setLiftToDrag(3);
        //missile.setMinSpeed(200);
        //missile.setMinDistance(4*4);

        PursitFlightProducer flightScenario = new PursitFlightProducer(dron, missile);
        return flightScenario.flyPursit(0.005, 2700);
    }
}
