package main;

// import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// import main.GamePanel;

public class Maze {

    // public final int W = 1;
    // public final int F = 2;
    // public final int E = 3;

    GamePanel gp;
    int x;
    int y;
    KeyHandler KeyH;

    public BufferedImage wall;
    public BufferedImage test;
    public BufferedImage coin;

    public Board collectibles = new Board();

    public int[][] board = new Board().getBoard();
    // public int[][] collectibles = new Board().getCollectibles();

    public Maze(GamePanel gp, KeyHandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        this.setDefaultValues();
        this.getMazeImages();
        // System.out.println("from maze " + this.board[0][0]);
    }

    public void getMazeImages() {
        try {
            wall = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
            coin = ImageIO.read(getClass().getResourceAsStream("/coin.png"));
            test = ImageIO.read(getClass().getResourceAsStream("/test.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
    }

    public void update() {
        gp.cChecker.checkCollectible(this);
        // System.out.println("test");
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == 1) {
                    g2.drawImage(wall, x, y, gp.tileSize, gp.tileSize, null);
                }
                if (collectibles.getCollectibles()[i][j] == 3) {
                    g2.drawImage(coin, x, y, gp.tileSize, gp.tileSize, null);
                }

                // else {
                // g2.drawImage(test, x, y, gp.tileSize, gp.tileSize, null);
                // }

                this.x += gp.tileSize;
            }
            this.x = 0;
            this.y += gp.tileSize;
        }
        this.setDefaultValues();
        //System.out.println(collectibles.getCollectibles()[1][3]);

    }
}
