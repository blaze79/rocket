package org.slientpom.rocket.domain.flight;

/**
 * Created by Vlad on 29.06.2018.
 */
public class TargetDron implements FlyTarget {
    private Fly fly;
    private double normalA;

    public TargetDron(Fly fly, double normalA) {
        this.fly = fly.copy();
        this.normalA = normalA;
    }

    @Override
    public Fly currentPosition() {
        return fly.copy();
    }

    @Override
    public void step(double t) {
        fly.step(t, 0, normalA);
    }
}
