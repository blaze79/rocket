package org.slientpom.rocket.domain.flight.missiles;

import org.slientpom.rocket.domain.flight.ThermalSeekerMissile;
import org.slientpom.rocket.domain.flight.filter.AverageYFilter;
import org.slientpom.rocket.domain.flight.seekers.BangBangSeeker;
import org.slientpom.rocket.domain.geom.Fly;

/**
 * Created by Vlad on 29.06.2018.
 */
public class BangBangMissile extends ThermalSeekerMissile {

    public BangBangMissile(Fly fly) {
        super(fly, new BangBangSeeker());
    }

}
