package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.Color;
import java.awt.Font;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public int score=0;
    BufferedImage test;

    Font f1 = new Font("Arial",Font.BOLD,18);

    boolean openMouth;
    // public boolean collidedWithWall;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.openMouth = true;
        this.collidedWithWall = false;
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
        x = 1 * gp.tileSize;
        y = 1 * gp.tileSize;
        speed = 4;
        direction = "right";
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                // y -= speed;
            }
            if (keyH.downPressed) {
                direction = "down";
                // y += speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                // x -= speed;
            }
            if (keyH.rightPressed) {
                direction = "right";
                // x += speed;
            }

            this.collidedWithWall = false;
            gp.cChecker.checkWall2(this);
            int check;
            if (!collidedWithWall) {
                switch (direction) {
                    case "up":
                        check = y - speed;
                        if (check > 0)
                            y -= speed;
                        break;
                    case "down":
                        check = y + speed;
                        if (check < gp.screenHeight)
                            y += speed;
                        break;
                    case "left":
                        check = x - speed;
                        if (check > 0)
                            x -= speed;
                        break;
                    case "right":
                        check = x + speed;
                        if (check < gp.screenWidth - 48)
                            x += speed;
                        break;
                }
            }
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

    public void showScore(Graphics2D g2){
        g2.setFont(f1);
        g2.setColor(Color.WHITE);
        String s=Integer.toString(gp.score);
        g2.drawString("Score : ", 24, 24);
        g2.drawString(s, 96, 24);
    }
}
