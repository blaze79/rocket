package org.slientpom.rocket.domain.flight.filter;

/**
 * Created by Vlad on 09.07.2018.
 */
public class AverageYFilter implements ControlFilter {

    private double last = 0;
    @Override
    public double filterControl(double a) {
        last = (last + a) / 2;
        return last;
    }
}
