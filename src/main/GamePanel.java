package main;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import entity.NPCGreen;
import entity.NPCBlue;
import entity.NPCPurple;
import entity.NPCWhite;
import entity.Player;
import entity.Wall;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16pixels sqare
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Actual tile size
    final int maxScreenlCol = 16;
    final int maxScreenlRow = 12;

    public int screenWidth = tileSize * maxScreenlCol; // 768
    public int screenHeight = tileSize * maxScreenlRow; // 576

    // GAME STATES
    public boolean paused;
    public boolean atTitleScreen;
    public boolean playing;
    public boolean atSelectDifficulty;
    public int difficulty;

    // FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Maze maze = new Maze(this, keyH);
    Player player = new Player(this, keyH);
    public NPCGreen NPCGreen = new NPCGreen(this, keyH, player);
    public NPCBlue NPCBlue = new NPCBlue(this, keyH, player);
    public NPCPurple NPCPurple = new NPCPurple(this, keyH, player);
    public NPCWhite NPCWhite = new NPCWhite(this, keyH, player);
    UI ui = new UI(this, keyH);
    CollisionCheck collisionCheck=new CollisionCheck();
    Wall wall=new Wall(this, keyH, player);

    public GamePanel() {
        this.paused = false;
        this.atSelectDifficulty = false;
        this.atTitleScreen = true;
        this.playing = false;
        this.difficulty = 0;
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
            //maze.update();
            player.update();
            NPCGreen.update();
            NPCBlue.update();
            NPCPurple.update();
            NPCWhite.update();
            collisionCheck.check(player, NPCBlue, NPCGreen, NPCPurple, NPCWhite);
        }
    }

    // This one is a Standard method
    public void paintComponent(Graphics g) {

        // Parent class is JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // change to 2d graphics object

        if (this.paused) {
            ui.draw(g2);
        } else if (this.atTitleScreen) {
            ui.draw(g2);
        } else if (this.atSelectDifficulty) {
            ui.draw(g2);
        } else if (this.playing) {
            wall.draw(g2);
            NPCGreen.draw(g2);
            NPCBlue.draw(g2);
            NPCPurple.draw(g2);
            NPCWhite.draw(g2);
            player.draw(g2);
        }
        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
