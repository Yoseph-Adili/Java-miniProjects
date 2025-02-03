package Yoseph.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {


    private class Tile {
        int x, y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;


    Tile snakeHead;
    ArrayList<Tile> snakeBody;
    int highScore;

    Tile food;
    Random random;
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);


        snakeHead = new Tile(5, 5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10, 10);
        random = new Random();
        placeFood();
        highScore=0;


        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
//        for (int i = 0; i < boardWidth / tileSize; i++) {
//            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
//            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
//        }


        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //snake head
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        //snake body
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 15));
        if (gameOver) {
            g.setColor(Color.green);
            g.drawString("Game Over: "+String.valueOf(snakeBody.size()), tileSize-16, tileSize);
            g.drawString("High score: "+highScore, tileSize*20, tileSize);
            g.setFont(new Font("Arial", Font.PLAIN, 35));
            g.drawString("Press Space to play again", tileSize*4, tileSize*12);
        }else {
            g.drawString("Score: "+String.valueOf(snakeBody.size()), tileSize-16, tileSize);
            g.drawString("High score:  "+highScore, tileSize*20, tileSize);
        }

    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }


    public void move() {
        if (collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        //snake body
        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            } else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }


        snakeHead.x = snakeHead.x + velocityX;
        snakeHead.y = snakeHead.y + velocityY;

        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);

            if (collision(snakeHead, snakePart)) {
                gameOver = true;
            }
        }
        if (
                snakeHead.x * tileSize < 0 ||
                snakeHead.y * tileSize < 0 ||
                snakeHead.x * tileSize > boardWidth ||
                snakeHead.y * tileSize > boardHeight
        ) {
            gameOver = true;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
        if (highScore<snakeBody.size()) {
            highScore=snakeBody.size();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
        if (gameOver&& e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameOver = false;
            placeFood();
            snakeBody.clear();
            snakeHead.x=5;
            snakeHead.y=5;
            velocityX = 0;
            velocityY = 1;
            gameLoop.start();
        }
    }


    //dont use
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }


}
