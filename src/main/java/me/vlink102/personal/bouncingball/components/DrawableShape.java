package me.vlink102.personal.bouncingball.components;

import java.awt.Container;

public abstract class DrawableShape implements Drawable {
    private Container parent;
    protected Vector2D size;
    protected Vector2D position;

    public abstract void update();

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public void setParent(Container parent) {
        this.parent = parent;
    }

    public DrawableShape(Vector2D size, Vector2D position) {
        this.size = size;
        this.position = position;
        this.parent = null;
    }

    public boolean outBoundsX() {
        return position.getX() < size.getX() || position.getX() > parent.getWidth() - size.getX();
    }

    public boolean outBoundsY() {
        return position.getY() < size.getY() || position.getY() > parent.getHeight() - size.getY();
    }

    public boolean outBoundsX(double add) {
        return position.getX() + add < size.getX() || position.getX() + add > parent.getWidth() - size.getX();
    }

    public boolean outBoundsY(double add) {
        return position.getY() + add < size.getY() || position.getY() + add > parent.getHeight() - size.getY();
    }

    public boolean hitFloor() {
        return position.getY() < 0;
    }

    public Environment.SurfaceNormal outBounds() {
        if (position.getX() <= 0) {
            return Environment.SurfaceNormal.LEFT;
        }
        if (position.getX() + size.getX() >= parent.getWidth()) {
            return Environment.SurfaceNormal.RIGHT;
        }
        if (position.getY() <= 0) {
            return Environment.SurfaceNormal.TOP;
        }
        if (position.getY() + size.getX() >= parent.getHeight()) {
            return Environment.SurfaceNormal.BOTTOM;
        }
        return null;
    }

    public void addY(double dY) {
        position.dY -= dY;
    }

    public void addX(double dX) {
        position.dX += dX;
    }

    public void setY(double y) {
        position.dY = parent.getHeight() - y;
    }

    public void setX(double x) {
        position.dX = x;
    }
}