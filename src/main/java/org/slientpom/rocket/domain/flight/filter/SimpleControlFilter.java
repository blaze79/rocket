package org.slientpom.rocket.domain.flight.filter;

/**
 * Created by Vlad on 09.07.2018.
 */
public class SimpleControlFilter implements ControlFilter {
    @Override
    public double filterControl(double a) {
        return a;
    }
}
