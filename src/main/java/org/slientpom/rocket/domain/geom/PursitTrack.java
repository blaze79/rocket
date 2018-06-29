package org.slientpom.rocket.domain.geom;

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

    public static PursitTrack miss(FlyTrack target, FlyTrack rocket) {
        return new PursitTrack(target, rocket, false);
    }

    public static PursitTrack kill(FlyTrack target, FlyTrack rocket) {
        return new PursitTrack(target, rocket, true);
    }
}
