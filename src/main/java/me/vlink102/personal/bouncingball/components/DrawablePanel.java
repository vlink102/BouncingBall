package me.vlink102.personal.bouncingball.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

public class DrawablePanel extends JPanel {
    private List<Drawable> objects;
    private final int width;
    private final int height;

    public DrawablePanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        objects = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public boolean addDrawable(Drawable drawable) {
        drawable.setParent(this);
        return objects.add(drawable);
    }

    public boolean removeDrawable(Drawable drawable) {
        drawable.setParent(null);
        return objects.remove(drawable);
    }

    public void updateAll() {
        for (Drawable object : objects) {
            object.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Drawable object : objects) {
            object.draw(g);
        }
    }
}