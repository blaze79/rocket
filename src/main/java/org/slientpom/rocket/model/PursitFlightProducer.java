package org.slientpom.rocket.model;

import org.slientpom.rocket.domain.flight.*;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.FlyTrack;
import org.slientpom.rocket.domain.geom.PursitTrack;

/**
 * Created by Vlad on 27.06.2018.
 */
public class PursitFlightProducer {
    private FlyTarget target;
    private FlyWithSeeker rocket;

    public PursitFlightProducer(FlyTarget target, FlyWithSeeker rocket) {
        this.target = target;
        this.rocket = rocket;
    }

    public PursitTrack flyPursit(double t, int iter) {
        FlyTrack targetTrack = new FlyTrack();
        FlyTrack rocketTrack = new FlyTrack();

        targetTrack.push(target.currentPosition());
        rocketTrack.push(rocket.currentPosition());

        for (int i = 0; i < iter; ++i) {
            if(!rocket.canFly()) {
                System.out.printf("Dron steel alive! Rocket is out at %f second!%n", t*i);
                return logResult(PursitTrack.miss(targetTrack, rocketTrack, t));
            }

            target.step(t);
            Fly targetPosition = target.currentPosition();
            boolean result = rocket.step(t, targetPosition.copy());

            targetTrack.push(targetPosition);
            rocketTrack.push(rocket.currentPosition());
            if(result) {
                System.out.printf("We got a kill! Well done at %f second %n", t*i);
                return PursitTrack.kill(targetTrack, rocketTrack, t);
            }
        }

        System.out.printf("Dron still alive! You miss!%n");
        return logResult(PursitTrack.miss(targetTrack, rocketTrack, t));
    }

    private PursitTrack logResult(PursitTrack result) {
        System.out.printf("Closes distance is %f %n", result.minDistance());
        return result;
    }

}
