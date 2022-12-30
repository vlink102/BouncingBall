package me.vlink102.personal.bouncingball.components;

public class Body {
    private final double mass;
    private final double radius;

    public Body(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }
}
