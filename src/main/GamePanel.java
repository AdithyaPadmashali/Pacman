package main;

import java.io.*;

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

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16; // 16pixels sqare
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Actual tile size
    final int maxScreenlCol = 16;
    final int maxScreenlRow = 12;

    public final int screenWidth = tileSize * maxScreenlCol; // 768
    public final int screenHeight = tileSize * maxScreenlRow; // 576

    public int score = 0;

    // GAME STATES
    public boolean paused;
    public boolean atTitleScreen;
    public boolean playing;
    public boolean atSelectDifficulty;
    public boolean gameOver;
    public boolean atCongrats;
    public boolean toLoad;
    public boolean toSave;
    public boolean toLeaderboard;
    public boolean toPushtoLeaderboard;

    // 0, 1 and 2 -> easy, medium and hard respectively.
    public int difficulty;

    // FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Maze maze = new Maze(this, keyH);
    public Player player = new Player(this, keyH);
    public NPCGreen NPCGreen = new NPCGreen(this, keyH, player);
    public NPCBlue NPCBlue = new NPCBlue(this, keyH, player);
    public NPCPurple NPCPurple = new NPCPurple(this, keyH, player);
    public NPCWhite NPCWhite = new NPCWhite(this, keyH, player);
    UI ui = new UI(this, keyH);
    Leaderboard leaderboard = new Leaderboard();

    public CollisionChecker cChecker = new CollisionChecker(this);

    public GamePanel() {
        this.paused = false;
        this.atSelectDifficulty = false;
        this.atTitleScreen = true;
        this.toSave = false;
        this.playing = false;
        this.atCongrats = false;
        this.toPushtoLeaderboard = false;
        this.toLoad = false;
        this.toLeaderboard = false;
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
            player.update();
            NPCGreen.update();
            NPCBlue.update();
            NPCPurple.update();
            NPCWhite.update();
            maze.update();
            // leaderboard.showRecords();
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
            if (this.toSave)
                this.saveGame();
            player.setDefaultValues();
            NPCBlue.setDefaultValues();
            NPCGreen.setDefaultValues();
            NPCPurple.setDefaultValues();
            NPCWhite.setDefaultValues();
            if (!this.toSave)
                maze.reset();
            player.score = 0;
        } else if (this.atSelectDifficulty) {
            ui.draw(g2);
        } else if (this.playing) {
            this.toSave = true;
            if (this.toLoad) {
                try {
                    loadGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            maze.draw(g2);
            NPCGreen.draw(g2);
            NPCBlue.draw(g2);
            NPCPurple.draw(g2);
            player.showRemaining(g2);
            NPCWhite.draw(g2);
            player.draw(g2);
            ui.draw(g2);

        } else if (this.gameOver) {
            ui.draw(g2);
            player.setDefaultValues();
            NPCBlue.setDefaultValues();
            NPCGreen.setDefaultValues();
            NPCPurple.setDefaultValues();
            NPCWhite.setDefaultValues();
            maze.reset();
            this.toSave = false;
            this.toLoad = false;
            player.score = 0;
        } else if (this.atCongrats) {
            ui.draw(g2);
            player.setDefaultValues();
            NPCBlue.setDefaultValues();
            NPCGreen.setDefaultValues();
            NPCPurple.setDefaultValues();
            NPCWhite.setDefaultValues();
            maze.reset();
            this.toSave = false;
            this.toLoad = false;
            player.score = 0;
            if (this.toPushtoLeaderboard == true) {
                leaderboard.pushRecords(this);
                this.toPushtoLeaderboard = false;
                this.atCongrats=false;
                this.atTitleScreen=true;
            }
        } else if (this.toLeaderboard) {
            leaderboard.showRecords(g2);
        }
        g2.dispose();
    }

    public void saveGame() {
        FileWriter filewrite;
        try {
            filewrite = new FileWriter("savedata.txt");

            // Initialing BufferedWriter
            BufferedWriter bufferwrite = new BufferedWriter(filewrite);

            // save player details
            String text = new String(player.x + " " + player.y);
            text += " " + player.direction;
            text += " " + player.score;

            // save maze details
            // have to save this.maze.collectibles

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < maze.collectibles.getCollectibles().length; i++)// for each row
            {
                for (int j = 0; j < maze.collectibles.getCollectibles()[0].length; j++)// for each column
                {
                    builder.append(maze.collectibles.getCollectibles()[i][j] + "");// append to the output string
                    if (j < maze.collectibles.getCollectibles()[0].length - 1)// if this is not the last row element
                        builder.append(",");// then add comma (if you don't like commas you can use spaces)
                }
                builder.append("\n");// append new line at the end of the row
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("collectibleState.txt"));
            writer.write(builder.toString());// save the string representation of the board
            writer.close();

            // Use of write() method to write the value in 'ABC' file
            bufferwrite.write(text);

            // Closing BufferWriter to end operation
            bufferwrite.close();
            System.out.println("Saved successfully");
        } catch (IOException excpt) {
            excpt.printStackTrace();
        }
        this.toSave = false;
    }

    public void loadGame() throws FileNotFoundException, IOException {
        File fi = new File("savedata.txt");
        BufferedReader br = new BufferedReader(new FileReader(fi));
        String st;
        String readText = new String();
        try {
            while ((st = br.readLine()) != null) {
                readText += st;
            }
            String[] values = readText.split(" ");
            player.x = Integer.parseInt(values[0]);
            player.y = Integer.parseInt(values[1]);
            player.direction = values[2];
            player.score = Integer.parseInt(values[3]);

            // Load the previously mapped collectibles (2d array)
            String savedCollectibles = "collectibleState.txt";
            int[][] loadedCollectibles = new int[this.maxScreenlRow][this.maxScreenlCol];
            BufferedReader reader = new BufferedReader(new FileReader(savedCollectibles));
            String line = "";
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");
                int col = 0;
                for (String c : cols) {
                    loadedCollectibles[row][col] = Integer.parseInt(c);
                    col++;
                }
                row++;
            }
            reader.close();

            this.maze.collectibles.setCollectibles(loadedCollectibles);
            System.out.println("loaded the map");

        } catch (Exception e) {
            e.printStackTrace();
        }
        br.close();
        this.toLoad = false;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

}
