package entity;

import main.KeyHandler;
import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.Board;
import main.GamePanel;

public class Wall extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Player pacman;
    int r_direction;
    int board[][]=new Board().getBoard();
    public Wall(GamePanel gp, KeyHandler keyH, Player pacman) {
        this.gp = gp;
        this.keyH = keyH;
        this.pacman = pacman;
        this.getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
                }
                this.x += gp.tileSize;
            }
            this.x = 0;
            this.y += gp.tileSize;
        }
        this.setDefaultValues();
    }
}
