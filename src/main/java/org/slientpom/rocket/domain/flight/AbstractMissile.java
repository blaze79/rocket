package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.flight.filter.ControlFilter;
import org.slientpom.rocket.domain.flight.filter.SimpleControlFilter;
import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Gravity;
import org.slientpom.rocket.domain.geom.Vector;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 29.06.2018.
 */
public abstract class AbstractMissile implements FlyWithSeeker {
    private Fly fly;
    private double maxG = gLoad(20);
    /**
     * square of min distance
     */
    private double minDistance = 6 * 6;
    private double liftToDrag = 50;
    private double minSpeed = 300;


    private ControlFilter filter = new SimpleControlFilter();

    private double lastANormal = 0;

    protected AbstractMissile(Fly fly) {
        this.fly = fly;
    }

    protected Fly getFly() {
        return fly;
    }

    protected double limitAcceleration(double aNormal) {

        double gLimit = Gravity.gLimitForSpeed(fly.getVelocity().lengthSq(), maxG);

        if (aNormal > gLimit) {
            aNormal = gLimit;
        } else if (aNormal < -gLimit) {
            aNormal = -gLimit;
        }

        return aNormal;
    }

    /**
     * calculates drag from aNormal gload and gravity. If L/D > 25 - no drag penalty. UFO may be
     *
     * @param aNormal normal g-load
     * @return drag
     */
    protected double calculateDrag(double aNormal) {
        if (liftToDrag > 25) {
            return 0;
        }
        return -Math.sqrt(aNormal * aNormal + 1) / liftToDrag;
    }

    protected boolean isHit(Fly target) {
        if (fly.getPoint().vectorTo(target.getPoint()).lengthSq() < minDistance) {
            System.out.printf("Missile hit with distance %f%n", fly.getPoint().vectorTo(target.getPoint()).length());
            return true;
        }
        return false;
    }

    public double getMaxG() {
        return maxG;
    }

    public void setMaxG(double maxG) {
        this.maxG = maxG;
    }

    public double getLiftToDrag() {
        return liftToDrag;
    }

    public void setLiftToDrag(double liftToDrag) {
        this.liftToDrag = liftToDrag;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public ControlFilter getFilter() {
        return filter;
    }

    public void setFilter(ControlFilter filter) {
        this.filter = filter;
    }

    @Override
    public Fly currentPosition() {
        return getFly().copy();
    }

    protected abstract double findNextAcceleration(Fly target, double t);

    protected double calculateA(Fly target, double t) {
        final double aNormal = findNextAcceleration(target, t);
        final double aNormalLimit = limitAcceleration(aNormal);
        return limitAcceleration(
                filter.filterControl(aNormalLimit)
        );
    }

    @Override
    public boolean step(double t, Fly target) {
        final double nextA = calculateA(target, t);
        final double drag = calculateDrag(nextA);

        if(lastANormal * nextA < 0) {
            System.out.printf("aNormal change sign:%f%n ", nextA);
        }
        lastANormal = nextA;

        getFly().step(t, drag, nextA);
        return isHit(target);
    }


    @Override
    public boolean canFly() {
        double speed = fly.getVelocity().length();
        if (speed < minSpeed) {
            System.out.printf("Missile out of speed %f%n", speed);
            return false;
        }
        return true;
    }
}
