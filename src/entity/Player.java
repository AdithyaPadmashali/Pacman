package entity;

import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;
import main.CollisionCheck;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public boolean collided;
    boolean openMouth;
    int board[][]=new Board().getBoard();
    public int lives=3;
    CollisionCheck collisionCheck;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.openMouth = true;
        this.setDefaultValues();
        this.getPlayerImage();
        this.collided=false;
    }

    public void getPlayerImage() {
        try {
            right = ImageIO.read(getClass().getResourceAsStream("/pacman-right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/pacman-left.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/topfromleft.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/downfromleft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/pacman-right1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/pacman-left1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/topfromleft1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/downfromleft1.png"));
            collidedimg=ImageIO.read(getClass().getResourceAsStream("/collided.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 1 * gp.tileSize;
        y = 1 * gp.tileSize;
        prevX = 1 * gp.tileSize;
        prevY = 1 * gp.tileSize;
        speed = 2;
        direction = "right";
    }

    public void update() {
        if (keyH.upPressed && y>=0 && !collided) {
            direction = "up";
            prevY=y;
            prevX=x;
            y -= speed;
        }
        if (keyH.downPressed && y<=gp.screenHeight-48 && !collided) {
            direction = "down";
            prevY=y;
            prevX=x;
            y += speed; 
        }
        if (keyH.leftPressed && x>=0 && !collided) {
            direction = "left";
            prevY=y;
            prevX=x;
            x -= speed;
        }
        if (keyH.rightPressed && x<=gp.screenWidth-48 && !collided) {
            direction = "right";
            prevY=y;
            prevX=x;
            x += speed;
        }

        if(collided){
            
            x=prevX;
            y=prevY;
        }

        animCounter++;
        if (animCounter == 15) {
            this.openMouth = !this.openMouth;
            animCounter = 0;
        }

        // System.out.println(
        // "Player on tile: " + (int) (this.x / (16 * gp.tileSize)) + " " + (int)
        // (this.y / (16 * gp.tileSize)));
        // System.out.println("Player on tile: " + this.x / (gp.tileSize) + " " + this.y
        // / (gp.tileSize));

        // System.out.println("Next valid tile = " + this.nextValidX + " " +
        // this.nextValidY);
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "right":
                if (this.openMouth) {
                    image = right;
                } else if (!this.openMouth) {
                    image = right1;
                }
                break;
            case "left":
                if (this.openMouth) {
                    image = left;
                } else if (!this.openMouth) {
                    image = left1;
                }
                break;
            case "up":
                if (this.openMouth) {
                    image = up;
                } else if (!this.openMouth) {
                    image = up1;
                }
                break;
            case "down":
                if (this.openMouth) {
                    image = down;
                } else if (!this.openMouth) {
                    image = down1;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        if(this.collided==true){
            image=collidedimg;
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}
