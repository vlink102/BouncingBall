package me.vlink102.personal.bouncingball;

import me.vlink102.personal.bouncingball.components.Ball;
import me.vlink102.personal.bouncingball.components.Block;
import me.vlink102.personal.bouncingball.components.DrawablePanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main implements Runnable, ActionListener {
    private final static String APP_TITLE = "Bouncing Ball";

    private JFrame frame;
    private DrawablePanel drawPanel;

    private Timer clock;
    private int fps = 60;
    private int timeout = 1000 / fps;

    private final Ball ball;
    private final Block block;

    public Main() {
        frame = new JFrame();
        drawPanel = new DrawablePanel(400, 400);

        frame.setTitle(APP_TITLE);
        frame.setContentPane(drawPanel);

        ball = new Ball(20, Color.RED, drawPanel);
        block = new Block(50, Color.GRAY, drawPanel);

        drawPanel.addDrawable(ball);
        drawPanel.addDrawable(block);
    }

    @Override
    public void run() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        clock = new Timer(timeout, this);
        clock.start();
        ball.getEnvironment().setCurrentTime(0);
        ball.getEnvironment().setLastBounceMs(System.currentTimeMillis());
    }

    public void actionPerformed(ActionEvent e) {
        drawPanel.updateAll();
        drawPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main());
    }
}