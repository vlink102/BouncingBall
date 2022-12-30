package me.vlink102.personal.bouncingball.components;

import jdk.dynalink.linker.GuardingTypeConverterFactory;

import java.awt.*;

public class Ball extends DrawableShape {
    private final Color color;
    private final BodySetup motionPhysics;
    private final Environment environment;

    public Ball(int size, Color color, DrawablePanel panel) {
        super(new Vector2D(size, size), new Vector2D(0, panel.getHeight() - size));

        this.color = color;
        this.motionPhysics = new BodySetup(BodySetup.EARTH, new Body(1, 0.0335));
        this.environment = motionPhysics.createEnvironment(80, 15, 0);
    }

    public BodySetup getMotionPhysics() {
        return motionPhysics;
    }
    public Environment getEnvironment() {
        return environment;
    }

    private double round(double x, double dp) {
        return Math.round(x * Math.pow(10, dp)) / Math.pow(10, dp);
    }

    @Override
    public void draw(Graphics g) {
        g.drawString("X: " + round(position.getX(), 3) + ", Y: " + round(position.getY(), 3) + " (" + round(getParent().getHeight() - position.getY(), 3) + ")", 20, 20);
        g.drawString("Field Strength: " + environment.g, 20, 40);
        g.drawString("V0: " + round(environment.getVelocity().length(), 2) + ", Vx: " + round(environment.getVelocity().getX(), 4) + ", Vy: " + round(environment.getVelocity().getY(), 4), 20, 60);

        g.drawString("Time Elapsed: " + round((System.currentTimeMillis() - environment.getLastBounceMs()) / 1000D, 3), 20, 100);
        if (g.getColor() != color) {
            g.setColor(color);
        }
        g.fillOval((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    @Override
    public void update() {
        double t = environment.getCurrentTime();
        environment.updateFieldStrength(getParent().getHeight() - position.getY());
        double dX = environment.xVel();
        double dY = environment.yVel(t / 1000F);

        addX(dX);
        addY(dY);

        environment.setCurrentTime(System.currentTimeMillis() - environment.getLastBounceMs());

    }
    /*
        Environment.SurfaceNormal outBounds = outBounds();
        if (outBounds != null) {
            System.out.println("Hit boundary: " + outBounds);
            Vector2D n = outBounds.getVector().normalize();
            System.out.println("Surface Normal: " + n);
            Vector2D v = new Vector2D(dX, dY);
            System.out.println("Velocity vector: " + v);
            double dotProduct = n.dotProduct(v);
            System.out.println("Dot product: " + dotProduct);
            Vector2D w = v.sub(n.scale(dotProduct));
            System.out.println("Normal parallel vector: " + w);
            Vector2D reflected = new Vector2D(v.getX() - 2 * dotProduct * n.getX(), v.getY() - 2 * dotProduct * n.getY()); // No factors
            Vector2D u = n.scale(v.dotProduct(n));
            System.out.println("Normal perpendicular vector: " + u);
            Vector2D rV = w.scale(environment.getFriction()).sub(u.scale(environment.getRestitutionCoeff())); // f + e factored
            System.out.println("Resultant velocity" + rV);
            environment.setA(Math.toDegrees(Math.atan(rV.getX() / rV.getY())));
            System.out.println("New angle: " + environment.getA());
            environment.updateV0(rV.getX(), rV.getY());
            System.out.println("New velocity: " + environment.getV0());
            environment.updateFieldStrength(environment.vDist(environment.getCurrentTime()));

            environment.setLastBounceMs(System.currentTimeMillis());
        }
        environment.setCurrentTime(System.currentTimeMillis() - environment.getLastBounceMs());
     */
}
