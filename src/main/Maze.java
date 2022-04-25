package main;

// import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// import main.GamePanel;

public class Maze {

    GamePanel gp;
    int x;
    int y;
    KeyHandler KeyH;

    public BufferedImage wall;
    public BufferedImage test;
    public BufferedImage coin;

    public Board collectibles;
    public int[][] board;
    // public int[][] collectibles = new Board().getCollectibles();

    public Maze(GamePanel gp, KeyHandler KeyH) {
        this.collectibles = new Board();
        this.board = new Board().getBoard();
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

    public void reset() {
        this.collectibles = new Board();
        this.board = new Board().getBoard();

    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
    }

    public void update() {

        gp.cChecker.checkCollectible(this);
        gp.player.score = getScore();
    }

    public int getCoinsLeft() {
        int xloc = 0;
        int yloc = 0;
        int coinsLeft = 0;
        for (int i = 0; i < collectibles.getCollectibles().length; i++) {
            for (int j = 0; j < collectibles.getCollectibles()[i].length; j++) {

                if (collectibles.getCollectibles()[i][j] == 3) {
                    coinsLeft += 1;
                }
                xloc += gp.tileSize;
            }
            xloc = 0;
            yloc += gp.tileSize;
        }
        return coinsLeft;
    }

    public int getScore() {
        return (collectibles.initalCoins - getCoinsLeft()) * 10;
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
                this.x += gp.tileSize;
            }
            this.x = 0;
            this.y += gp.tileSize;
        }
        this.setDefaultValues();

    }
}
