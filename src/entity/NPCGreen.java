package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPCGreen extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;

    public NPCGreen(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-green.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 4;

    }

    public void update() {
        System.out.println("detected pacman at x=" + pacman.x + " and y=" + pacman.y);
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
