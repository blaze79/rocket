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
public class HeadToHead2MRocketModel implements PursitFlightModel {
    private double maxG = 4;
    private double maxGBang = 20 + 6;// + 4 + 2 + 1;

    @Override
    public PursitTrack generateFlight() {

        Point dronPoint = Point.point(-900, -700);
        TargetDron dron = new TargetDron(
                new Fly(
                        dronPoint,
                        Vector.vector(0, 600 + 100)
                ),
                gLoad(maxG)
        );

        Point rocketPoint = Point.point(-1200, 900);
        PropThermalMissile missile = new PropThermalMissile(
                new Fly(
                        rocketPoint,
                        rocketPoint.vectorTo(dronPoint).unify().mul(600 + 60)
                        //Vector.vector(0, -600)
                ),
                5
        );
        missile.setMaxG(gLoad(maxGBang));
        missile.setLiftToDrag(3);
        missile.setMinSpeed(300);

        //missile.setMinDistance(4*4);

        PursitFlightProducer flightScenario = new PursitFlightProducer(dron, missile);
        return flightScenario.flyPursit(0.005, 2000);
    }
}
