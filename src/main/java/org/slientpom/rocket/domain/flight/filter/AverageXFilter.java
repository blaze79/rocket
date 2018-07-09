package org.slientpom.rocket.domain.flight.filter;

/**
 * Created by Vlad on 09.07.2018.
 */
public class AverageXFilter implements ControlFilter {

    private double last = 0;
    @Override
    public double filterControl(double a) {
        double res = (last + a) / 2;
        last = a;
        return res;
    }
}
