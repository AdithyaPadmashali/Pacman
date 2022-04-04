package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPCBlue extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;

    public NPCBlue(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ghost-blue.png"));
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
        // System.out.println("detected pacman at x=" + pacman.x + " and y=" +
        // pacman.y);
        double r_direction =(int)((Math.random() * (5 - 1)) + 1);
        // 1:up , 2:down , 3:left , 4:right
        if(r_direction == 1)
        {   direction = "up";
            y-=speed;
        }
        if(r_direction == 2)
        {
            direction = "down";
            y+=speed;
        }
        if(r_direction == 3)
        {
            direction ="left";
            x-=speed;
        }
        if(r_direction == 4)
        {
            direction = "rigth";
            x+=speed;
        }


    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
