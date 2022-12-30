package me.vlink102.personal.bouncingball.components;

import java.lang.Math;

public class Vector2D {
    protected double dX;
    protected double dY;

    public double getX() {
        return dX;
    }
    public double getY() {
        return dY;
    }

    public void setX(double x) {
        dX = x;
    }
    public void setY(double y) {
        dY = y;
    }
    public void addX(double x) {
        dX += x;
    }
    public void addY(double y) {
        dY += y;
    }

    // Constructor methods ....
    public Vector2D() {
        dX = dY = 0.0;
    }

    public Vector2D(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public String toString() {
        return "Vector2D(" + dX + ", " + dY + ")";
    }

    public double length() {
        return Math.sqrt(dX * dX + dY * dY);
    }

    public Vector2D add(Vector2D v1) {
        Vector2D v2 = new Vector2D(this.dX + v1.dX, this.dY + v1.dY);
        return v2;
    }

    public Vector2D sub(Vector2D v1) {
        Vector2D v2 = new Vector2D(this.dX - v1.dX, this.dY - v1.dY);
        return v2;
    }

    public Vector2D scale(double scaleFactorX, double scaleFactorY) {
        Vector2D v2 = new Vector2D(this.dX * scaleFactorX, this.dY * scaleFactorY);
        return v2;
    }

    public Vector2D scale(double scaleFactor) {
        return scale(scaleFactor, scaleFactor);
    }

    public Vector2D normalize() {
        Vector2D v2 = new Vector2D();

        double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY);
        if (length != 0) {
            v2.dX = this.dX / length;
            v2.dY = this.dY / length;
        }

        return v2;
    }

    public double magnitude() {
        return Math.sqrt(this.dX + this.dY);
    }

    public double dotProduct(Vector2D v1) {
        return this.dX * v1.dX + this.dY * v1.dY;
    }

}