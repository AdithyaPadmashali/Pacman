package main;

import entity.Entity;
import entity.Player;

public class CollisionChecker {

    GamePanel gp;
    Board board1 = new Board();
    int[][] board = board1.getBoard();
    int[][] collectibles = board1.collectibles;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkWall(Entity entity) {

        // putting a border slack of 5 pixels for easier movement
        int topy = (entity.y - 2) / gp.tileSize;
        int bottomy = (entity.y + gp.tileSize) / gp.tileSize;
        int leftx = (entity.x - 2) / gp.tileSize;
        int rightx = (entity.x + gp.tileSize) / gp.tileSize;

        if (entity.direction == "up") {
            if (board[topy][(entity.x) / gp.tileSize] == 1 ||
                    board[topy][(entity.x + gp.tileSize - 2) / gp.tileSize] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "down") {
            if (board[bottomy][(entity.x) / gp.tileSize] == 1 ||
                    board[bottomy][(entity.x + gp.tileSize - 2) / gp.tileSize] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "left") {
            if (board[(entity.y) / gp.tileSize][leftx] == 1 ||
                    board[(entity.y + gp.tileSize - 2) / gp.tileSize][leftx] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "right") {
            if (board[(entity.y) / gp.tileSize][rightx] == 1 ||
                    board[(entity.y + gp.tileSize - 2) / gp.tileSize][rightx] == 1) {
                entity.collidedWithWall = true;
            }
        }
    }

    public void checkWall2(Entity entity) {

        // putting a border slack of 5 pixels for easier movement
        int topy = (entity.y - 1 + 7) / gp.tileSize;
        int bottomy = (entity.y + gp.tileSize - 6) / gp.tileSize;
        int leftx = (entity.x - 1 + 7) / gp.tileSize;
        int rightx = (entity.x + gp.tileSize - 6) / gp.tileSize;

        if (entity.direction == "up") {
            if (board[topy][(entity.x + 8) / gp.tileSize] == 1 ||
                    board[topy][(entity.x + gp.tileSize - 1 - 13) / gp.tileSize] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "down") {
            if (board[bottomy][(entity.x + 8) / gp.tileSize] == 1 ||
                    board[bottomy][(entity.x + gp.tileSize - 1 - 13) / gp.tileSize] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "left") {
            if (board[(entity.y + 8) / gp.tileSize][leftx] == 1 ||
                    board[(entity.y + gp.tileSize - 1 - 13) / gp.tileSize][leftx] == 1) {
                entity.collidedWithWall = true;
            }
        }
        if (entity.direction == "right") {
            if (board[(entity.y + 8) / gp.tileSize][rightx] == 1 ||
                    board[(entity.y + gp.tileSize - 1 - 13) / gp.tileSize][rightx] == 1) {
                entity.collidedWithWall = true;
            }
        }
    }

    public void checkCollectible(Maze maze) {
        if (maze.collectibles.getCollectibles()[gp.player.y / 48][gp.player.x / 48] == 3) {
            maze.collectibles.setCollectibles(gp.player.y / 48, gp.player.x / 48, 2);
            gp.score+=10;
            // System.out.println("coin");
            // System.out.println(maze.collectibles.getCollectibles()[gp.player.y /
            // 48][gp.player.x / 48]);
        }

    }
}
