package me.vlink102.personal.bouncingball.components;

import java.awt.Container;
import java.awt.Graphics;

public interface Drawable {
    Container getParent();
    void setParent(Container parent);

    void update();
    void draw(Graphics g);
}
