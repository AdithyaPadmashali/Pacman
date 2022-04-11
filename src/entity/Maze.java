package entity;

// import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

// import main.GamePanel;

public class Maze {

    final int W = 1;
    final int F = 2 ;
    final int E = 3 ;

    GamePanel gp;
    int x;
    int y;
    KeyHandler KeyH;

    public BufferedImage wall;

    public int board[][] = { 
    { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
    { W, F, F, F, F, F, F, F, F, F, F, F, F, F, F, W },
    { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
    { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
    { W, F, F, F, F, F, F, W, W, F, F, F, F, F, F, W },
    { W, W, W, F, W, W, W, W, W, W, W, W, F, W, W, W },
    { W, F, F, F, F, F, F, W, W, F, F, F, F, F, F, W },
    { F, F, W, W, W, W, F, W, W, F, W, W, W, W, F, F },
    { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
    { W, F, F, F, F, F, F, W, W, F, F, F, F, F, F, W },
    { W, F, W, W, W, W, F, F, F, F, W, W, W, W, F, W },
    { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W }
};



    public Maze(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        this.setDefaultValues();
        this.getMazeImages();
    }

    public void getMazeImages() {
        try {
            wall = ImageIO.read(getClass().getResourceAsStream("/wall1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
    }

    public void update() {
        // System.out.println("detected pacman at x=" + pacman.x + " and y=" +
        // pacman.y);
    }

    public void draw(Graphics2D g2) {

        try {
            wall = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == W) {
                    g2.drawImage(wall, this.x, this.y, 48, 48, null);
                    
                }this.x += gp.tileSize;
                
            }this.x = 0;this.y += gp.tileSize;
        }
        this.setDefaultValues();
    }
}
