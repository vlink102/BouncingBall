package me.vlink102.personal.bouncingball.components;

public class BodySetup {

    public static final Body EARTH = new Body(5.9722 * Math.pow(10, 24), 6.371 * Math.pow(10, 6));
    public static final Body MERCURY = new Body(3.3 * Math.pow(10, 23), 2.4397 * Math.pow(10, 6));
    public static final Body VENUS = new Body(4.8673 * Math.pow(10, 24), 6.0518 * Math.pow(10, 6));
    public static final Body MARS = new Body(6.4185 * Math.pow(10, 23), 3.3895 * Math.pow(10, 6));
    public static final Body JUPITER = new Body(1.8982 * Math.pow(10, 27), 6.9911 * Math.pow(10, 7));
    public static final Body SATURN = new Body(5.6834 * Math.pow(10, 26), 5.8232 * Math.pow(10, 7));
    public static final Body URANUS = new Body(8.6810 * Math.pow(10, 25), 2.5362 * Math.pow(10, 7));
    public static final Body NEPTUNE = new Body(1.02413 * Math.pow(10, 26), 2.4622 * Math.pow(10, 7));
    public static final Body SUN = new Body(1.989 * Math.pow(10, 30), 6.9634 * Math.pow(10, 8));

    private final Body b1;
    private final Body b2;
    private final double d;

    public BodySetup(Body b1, Body b2) {
        this.b1 = b1;
        this.b2 = b2;
        this.d = b1.getRadius() + b2.getRadius();
    }

    public Body getBody1() {
        return b1;
    }
    public Body getBody2() {
        return b2;
    }
    public double getDist() {
        return d;
    }

    public Environment createEnvironment(int a, double V0, double h) {
        return new Environment(this, a, V0, h, 0.8, 0.6);
    }
}
