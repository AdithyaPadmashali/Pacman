package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    boolean openMouth;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.openMouth = true;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            // right = ImageIO.read(getClass().getResourceAsStream("/right.png"));
            // left = ImageIO.read(getClass().getResourceAsStream("/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/pacman-right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/pacman-left.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/topfromleft.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/downfromleft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/pacman-right1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/pacman-left1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/topfromleft1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/downfromleft1.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";

    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        }
        if (keyH.downPressed) {
            direction = "down";
            y += speed;
        }
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }

        animCounter++;
        if (animCounter == 15) {
            this.openMouth = !this.openMouth;
            animCounter = 0;
        }

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
    }
}
