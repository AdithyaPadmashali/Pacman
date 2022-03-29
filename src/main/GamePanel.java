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

    // GAME STATE
    public boolean paused;
    // FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Player player = new Player(this, keyH);
    UI ui = new UI(this, keyH);

    // set the player's default position
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4;

    public GamePanel() {
        this.paused = false;
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
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

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

        if (!this.paused) {
            player.update();
        }
    }

    // This one is a Standard method
    public void paintComponent(Graphics g) {

        // Parent class is JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // change to 2d graphics object

        if (this.paused) {
            ui.draw(g2);
        } else {
            player.draw(g2);
        }
        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
