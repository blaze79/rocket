package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.geom.Fly;

import static org.slientpom.rocket.domain.geom.Gravity.gLoad;

/**
 * Created by Vlad on 29.06.2018.
 */
public abstract class MissileWithSeeker {
    private Fly fly;
    private double maxG = gLoad(20);
    /**
     * square of min distance
     */
    private double minDistance = 6 * 6;
    private double liftToDrag = 50;
    private double minSpeed = 300;

    protected MissileWithSeeker(Fly fly) {
        this.fly = fly;
    }

    protected Fly getFly() {
        return fly;
    }

    protected double limitAcceleration(double aNormal) {
        if (aNormal > maxG) {
            aNormal = maxG;
        } else if (aNormal < -maxG) {
            aNormal = -maxG;
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

    protected boolean canFlyImpl() {
        double speed = fly.getVelocity().length();
        if (speed < minSpeed) {
            System.out.printf("Missile out of speed %f%n", speed);
            return false;
        }
        return true;
    }
}
