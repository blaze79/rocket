package org.slientpom.rocket.domain.geom;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by Vlad on 27.06.2018.
 */
public class PointTest {
    @Test
    public void testMove() throws Exception {
        Point a = Point.point(1, 3);
        Point b = Point.point(2, 10);
        Vector ab = a.vectorTo(b);

        Point bMoved = a.move(ab);

        assertEquals(bMoved.vectorTo(b).length(), 0, 1e-6);
    }

}