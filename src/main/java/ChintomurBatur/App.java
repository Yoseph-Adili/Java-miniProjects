package ChintomurBatur;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        int boardWidth = 1024;
        int boardHeight = 576;

        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Index game = new Index(boardWidth, boardHeight);
        frame.add(game);
        frame.pack();  // Adjusts to game panel's preferred size

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.requestFocus();
    }
}
