package ChintomurBatur;

import ChintomurBatur.Classes.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class keys{
    static boolean a;
    static boolean w;
    static boolean s;
    static boolean d;
}

public class Index extends JPanel implements ActionListener, KeyListener {
    public static int boardWidth;
    public static int boardHeight;
    Player player1 = new Player(10, 10);
    public float gravity = 0.5F;

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player1.update();
        player1.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (keys.a){
            player1.positionX--;
        }
        if (keys.d){
            player1.positionX++;
        }
        if (keys.s){
            player1.positionY+=10;
        }
        if (keys.w){
            player1.positionY-=10;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> keys.d=true;
            case KeyEvent.VK_A -> keys.a=true;
            case KeyEvent.VK_W -> keys.w=true;
            case KeyEvent.VK_S -> keys.s=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D -> keys.d=false;
            case KeyEvent.VK_A -> keys.a=false;
            case KeyEvent.VK_W -> keys.w=false;
            case KeyEvent.VK_S -> keys.s=false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }


}
