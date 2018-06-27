package org.slientpom.rocket.domain.geom;

import org.testng.annotations.Test;

import static org.slientpom.rocket.domain.geom.Vector.dot;
import static org.testng.Assert.*;

/**
 * Created by Vlad on 27.06.2018.
 */
public class VectorTest {

    @Test
    public void testNormal() throws Exception {
        Vector v1 = Vector.vector(10, 20);
        Vector normal = v1.normal();
        assertEquals(dot(v1, normal), 0.0, 1e-6);
    }

    @Test
    public void testUnify() throws Exception {
        Vector v1 = Vector.vector(10, 20);
        Vector normal = v1.normal();

        Vector unify = v1.unify();
        assertEquals(dot(unify, normal), 0.0, 1e-6);
        assertEquals(unify.length(), 1, 1e-6);
    }
}