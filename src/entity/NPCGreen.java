package entity;

import main.KeyHandler;
import java.awt.image.BufferedImage;

import java.util.*;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Board;

import main.GamePanel;

public class NPCGreen extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;
    BufferedImage test;
    // int reliefCounter;

    String[] d_list = { "up", "down", "left", "right" };
    int r_direction;
    int[][] board = new Board().getBoard();

    public NPCGreen(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.direction = "left";
        this.r_direction = 0;
        this.setDefaultValues();
        this.getPlayerImage();
        try {
            test = ImageIO.read(getClass().getResourceAsStream("/test.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-green.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 9 * gp.tileSize;
        y = 4 * gp.tileSize;
        speed = 4;
    }

    private void updatePositionRandom() {

        this.collidedWithWall = false;
        gp.cChecker.checkWall2(this);

        if (!collidedWithWall) {
            int check;
            switch (direction) {
                case "up":
                    check = y - speed;
                    if (check > 0)
                        y -= speed;
                    break;
                case "down":
                    check = y + speed;
                    if (check < gp.screenHeight - gp.tileSize)
                        y += speed;
                    break;
                case "left":
                    check = x - speed;
                    if (check > 0)
                        x -= speed;
                    break;
                case "right":
                    check = x + speed;
                    if (check < gp.screenWidth - gp.tileSize)
                        x += speed;
                    break;
            }
        } else {
            switch (direction) {
                case "up":
                    String[] possibleDirections = { "left", "right" };
                    direction = possibleDirections[0 + (int) (Math.random() * 1)];
                    break;
                case "down":
                    String[] possibleDirections1 = { "left", "right" };
                    direction = possibleDirections1[0 + (int) (Math.random() * 1)];
                    break;
                case "left":
                    String[] possibleDirections2 = { "down", "up" };
                    direction = possibleDirections2[0 + (int) (Math.random() * 1)];
                    break;
                case "right":
                    String[] possibleDirections3 = { "down", "up" };
                    direction = possibleDirections3[0 + (int) (Math.random() * 1)];
                    break;
            }
        }
    }

    public void update() {
        animCounter += 1;
        if (animCounter == 60) {
            animCounter = 0;
            direction = d_list[(int) ((Math.random() * (5 - 1)) + 0)];
        }
        this.collidedWithEntity = false;
        gp.cChecker.checkEntityCollision(gp.player, this);

        if (collidedWithEntity) {
            if (gp.player.rect <= 0) {
                gp.playing = false;
                gp.gameOver = true;
                gp.player.rect=4;
            }
            this.collidedWithEntity = false;
            gp.player.setDefaultValues();
            gp.player.rect -= 1;
        }

        if (gp.difficulty == 0 || gp.difficulty == 1) {
            updatePositionRandom();
        } else if (gp.difficulty == 2) {
            updatePositionRandom();
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
