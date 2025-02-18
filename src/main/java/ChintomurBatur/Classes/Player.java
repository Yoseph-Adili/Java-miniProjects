package ChintomurBatur.Classes;

import ChintomurBatur.Index;

import java.awt.*;
import javax.swing.*;

public class Player extends JPanel {

    private final int height;
    public int positionX;
    public int positionY;
    public int velocityX =0;
    public float velocityY=1;
    float gravity=0.5f;





    public Player(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height=100;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ensure that the panel is correctly painted
        draw(g);  // Call the draw method to render the player
    }

    private void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(positionX, positionY, 100, this.height);  // Draw the red square at current position
    }

    public void update() {
        this.positionY+=this.velocityY;// Move the player to the right
        if (this.positionY + this.height < Index.boardHeight) {
            this.velocityY += gravity;  // Accelerate downwards due to gravity
        } else {
            this.positionY = Index.boardHeight - this.height;
            this.velocityY = 0;
        }

    }
}
