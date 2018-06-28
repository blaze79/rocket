package org.slientpom.rocket.model;

import org.slientpom.rocket.domain.flight.*;

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
            target.step(t);
            Fly targetPosition = target.currentPosition();
            boolean result = rocket.step(t, targetPosition.copy());

            targetTrack.push(targetPosition);
            rocketTrack.push(rocket.currentPosition());
            if(result) {
                return PursitTrack.kill(targetTrack, rocketTrack);
            }
        }

        return PursitTrack.miss(targetTrack, rocketTrack);
    }

}
