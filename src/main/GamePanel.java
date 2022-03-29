package main;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16pixels sqare
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Actual tile size
    final int maxScreenlCol = 16;
    final int maxScreenlRow = 12;

    final int screenWidth = tileSize * maxScreenlCol; // 768
    final int screenHeight = tileSize * maxScreenlRow; // 576

    // FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    // set the player's default position
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // To let the game panel to be focused to receive key input
    }

    @Override
    public void run() {
        // create a game loop - the core
        // as long as the thread exists

        double drawInterval = 1000000000 / FPS; // Draw the screen 60 times per second
        // double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            // update information such as character position
            // update();

            // draw the screen with the updated information
            // repaint(); // repaint is the way to call the paintComponent method...

            // Sleep method
            // try {
            // double remainingTime = nextDrawTime - System.nanoTime();
            // // because sleep takes in time in milliseconds, we convert remainingtime from
            // ns
            // // to ms
            // remainingTime = remainingTime / 1000000;
            // if (remainingTime < 0) {
            // remainingTime = 0;
            // }
            // Thread.sleep((long) remainingTime);

            // nextDrawTime += drawInterval;

            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

            // Delta method
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
        player.update();
    }

    // This one is a Standard method
    public void paintComponent(Graphics g) {

        // Parent class is JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // change to 2d graphics object

        player.draw(g2);

        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
