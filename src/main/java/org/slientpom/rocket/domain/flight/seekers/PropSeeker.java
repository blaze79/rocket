package org.slientpom.rocket.domain.flight.seekers;

import org.slientpom.rocket.domain.geom.Vector;

/**
 * Created by Vlad on 16.07.2018.
 */
public class PropSeeker implements ThermalSeeker {

    private OmegaMeter omegaMeter = new SimpleOmegaMeter();
    private double propN = 3.0;

    public PropSeeker() {
    }

    public PropSeeker(double propN) {
        this.propN = propN;
    }

    public PropSeeker(OmegaMeter omegaMeter, double propN) {
        this.omegaMeter = omegaMeter;
        this.propN = propN;
    }

    @Override
    public double calculateNormalAcceleration(Vector target, Vector velocity, double t) {
        double omega = omegaMeter.findAngleSpeed(t, target);
        return omega * propN * velocity.length();
    }

    public OmegaMeter getOmegaMeter() {
        return omegaMeter;
    }

    public void setOmegaMeter(OmegaMeter omegaMeter) {
        this.omegaMeter = omegaMeter;
    }

    public double getPropN() {
        return propN;
    }

    public void setPropN(double propN) {
        this.propN = propN;
    }
}
