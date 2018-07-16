package org.slientpom.rocket.domain.flight.missiles;

import org.slientpom.rocket.domain.flight.ThermalSeekerMissile;
import org.slientpom.rocket.domain.flight.seekers.PropSeeker;
import org.slientpom.rocket.domain.flight.seekers.ThermalSeeker;
import org.slientpom.rocket.domain.geom.Fly;

/**
 * Created by Vlad on 16.07.2018.
 */
public class PropThermalMissile  extends ThermalSeekerMissile {

    public PropThermalMissile(Fly fly) {
        super(fly, new PropSeeker());
    }

    public PropThermalMissile(Fly fly, double coef) {
        super(fly, new PropSeeker(coef));
    }
}
