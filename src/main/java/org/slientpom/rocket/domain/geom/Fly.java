package org.slientpom.rocket.domain.geom;

/**
 * Created by Vlad on 27.06.2018.
 */
public class Fly {
    private Point point;
    private Vector velocity;

    public Fly(Point point, Vector velocity) {
        this.point = point;
        this.velocity = velocity;
    }

    public Point getPoint() {
        return point;
    }

    public Vector getVelocity() {
        return velocity;
    }

    /*public void step(double t, double axial, double normal) {
        Vector oldV = velocity.unify();
        Vector oldN = velocity.normal().unify();

        Vector newV = velocity.add(oldV, t * axial).add(oldN, t * normal);

        Vector v = newV.add(velocity).mul(0.5);

        point = point.move(v, t);
        velocity = newV;
    }*/

    public void step(double t, double axial, double normal) {
        Vector oldV = velocity.unify();
        Vector oldN = velocity.normal().unify();

        Vector newV = velocity.add(oldV, t * axial).add(oldN, t * normal);

        Vector v = newV.add(velocity).mul(0.5);

        point = point.move(v, t);
        velocity = newV;
    }

    public Fly copy() {
        return new Fly(point, velocity);
    }
}
