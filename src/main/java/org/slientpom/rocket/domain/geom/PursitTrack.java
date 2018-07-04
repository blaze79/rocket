package org.slientpom.rocket.domain.geom;

import java.util.stream.IntStream;

/**
 * Created by Vlad on 29.06.2018.
 */
public class PursitTrack {
    private FlyTrack target;
    private FlyTrack rocket;
    private boolean killed;

    public PursitTrack(FlyTrack target, FlyTrack rocket, boolean killed) {
        this.target = target;
        this.rocket = rocket;
        this.killed = killed;
    }

    public FlyTrack getTarget() {
        return target;
    }

    public FlyTrack getRocket() {
        return rocket;
    }

    public boolean isKilled() {
        return killed;
    }

    public double minDistance() {
        int minLength = Math.min(target.getTrack().size(), rocket.getTrack().size());
        return Math.sqrt(
                IntStream.range(0, minLength).mapToDouble(
                        i -> target.getTrack().get(i).getPoint().vectorTo(
                                rocket.getTrack().get(i).getPoint()
                        ).lengthSq()
                ).min().getAsDouble()
        );
    }


    public static PursitTrack miss(FlyTrack target, FlyTrack rocket) {
        return new PursitTrack(target, rocket, false);
    }

    public static PursitTrack kill(FlyTrack target, FlyTrack rocket) {
        return new PursitTrack(target, rocket, true);
    }
}
