package me.vlink102.personal.bouncingball.components;

import java.awt.*;

public class Block extends DrawableShape {
    public Block(int size, Color color, DrawablePanel panel) {
        super(new Vector2D(size, size), new Vector2D(0, panel.getHeight() - size));
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void update() {

    }
}
