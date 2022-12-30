package me.vlink102.personal.bouncingball.components;

public class Environment {
    private final Body body1;
    private final Body body2;

    private static final double G = 6.6743 * Math.pow(10, -11);

    private Vector2D velocity;
    public double g; // Gravitational field strength
    private final double h; // Initial height
    private final double f; // Friction
    private final double e; // Coefficient of restitution

    public double getH() {
        return h;
    }
    public double getFriction() {
        return f;
    }
    public double getRestitutionCoeff() {
        return e;
    }
    public Vector2D getVelocity() {
        return velocity;
    }

    private long t = 0;
    public long getCurrentTime() {
        return t;
    }
    public void setCurrentTime(long t) {
        this.t = t;
    }

    private long t0 = 0;
    public long getLastBounceMs() {
        return t0;
    }
    public void setLastBounceMs(long t0) {
        this.t0 = t0;
    }

    public Environment(BodySetup physics, int initialAngle, double initialVelocity, double initialHeight, double friction, double restitutionCoefficient) {
        this.body1 = physics.getBody1();
        this.body2 = physics.getBody2();
        this.g = gField(body1, body2, physics.getDist());
        this.velocity = velocity(initialAngle, initialVelocity);
        this.h = initialHeight;
        this.f = friction;
        this.e = restitutionCoefficient;
    }

    public Vector2D velocity(int angle, double magnitude) {
        return new Vector2D(magnitude * cos(angle), magnitude * sin(angle));
    }

    public double hDist(double t) {
        return velocity.getX() * t;
    }
    public double vDist(double t) {
        return h + (velocity.getY() * t) - ((g * Math.pow(t, 2)) / 2);
    }

    public double yVel(double t) {
        return velocity.getY() - (g * t);
    }
    public double xVel() {
        return velocity.getX();
    }

    public double xA() {
        return 0;
    }
    public double yA() {
        return -g;
    }

    public double fT() {
        return (2 * velocity.getY()) / g;
    }
    public double fT(double h) {
        return (velocity.getY() + Math.sqrt(Math.pow(velocity.getY(), 2) + (2 * g * h))) / g;
    }

    public double range() {
        return (2 * velocity.getX() * velocity.getY()) / g;
    }
    public double range(double h) {
        return (velocity.getX() * (velocity.getY() + Math.sqrt(Math.pow(velocity.getY(), 2) + (2 * g * h)))) / g;
    }

    public double hMax() {
        return Math.pow(velocity.getY(), 2) / (2 * g);
    }
    public double hMax(double h) {
        return hMax() + h;
    }

    public double V(double t) {
        return Math.sqrt(Math.pow(yVel(t), 2) + Math.pow(xVel(), 2));
    }

    private double cos(double x) {
        return Math.cos(Math.toRadians(x));
    }
    private double sin(double x) {
        return Math.sin(Math.toRadians(x));
    }

    public enum SurfaceNormal {
        TOP(new Vector2D(0, -1).normalize()),
        BOTTOM(new Vector2D(0, 1).normalize()),
        LEFT(new Vector2D(1, 0).normalize()),
        RIGHT(new Vector2D(-1, 0).normalize());

        private final Vector2D vector;

        SurfaceNormal(Vector2D vector) {
            this.vector = vector;
        }

        public Vector2D getVector() {
            return vector;
        }
    }

    public double gField(double M, double m, double r) {
        return (G * M * m) / Math.pow(r, 2);
    }
    public double gField(Body b1, Body b2, double d) {
        return gField(b1.getMass(), b2.getMass(), d);
    }

    public void updateFieldStrength(double h) {
        g = gField(body1, body2, (body1.getRadius() + body2.getRadius()) + h);
    }
}
