package ChintomurBatur;

import Yoseph.snake.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Index extends JPanel implements ActionListener , KeyListener{
    int boardWidth;
    int boardHeight;

    int gravity=1;
    int playerY=100;
    int playerX=100;
    public Index(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        setFocusable(true);  // Enable keyboard input
        addKeyListener(this); // Register key listener

        Timer timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(100,playerY,100,100);
    }

    public void requestFocus() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        playerY+=gravity;

        repaint();

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            playerY -= 20;
        }
    }





    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
