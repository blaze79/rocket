package org.slientpom.rocket.domain.flight;

import org.slientpom.rocket.domain.geom.Fly;
import org.slientpom.rocket.domain.geom.Point;
import org.slientpom.rocket.domain.geom.Vector;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Vlad on 27.06.2018.
 */
public class FlyTest {
    @Test
    public void testStep() throws Exception {
        double v0 = 1;
        Fly fly = new Fly(Point.point(0,0), Vector.vector(v0, 0));
        double a=10;
        double t=1/10.0;
        for(int i=1; i<10; ++i) {
            fly.step(t, a, 0);
            double x = fly.getPoint().getX();

            double mathX = v0*(i*t) + a*(i*t)*(i*t)/2;
            assertEquals(x, mathX, 1e-6, "Iteration " + i);
        }

    }

}