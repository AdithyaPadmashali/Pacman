package main;

public class Board {

    public int W = 1;
    public int F = 2;
    public int E = 3;
    public int[][] board = { { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
            { W, F, F, E, F, F, E, F, F, E, F, F, E, F, F, W },
            { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
            { W, E, W, W, W, W, F, W, W, F, W, W, W, W, E, W },
            { W, F, F, E, F, E, F, W, W, F, E, F, E, F, F, W },
            { W, W, W, F, W, W, W, W, W, W, W, W, F, W, W, W },
            { W, E, F, E, F, E, F, W, W, F, E, F, E, F, E, W },
            { F, F, W, W, W, W, E, W, W, E, W, W, W, W, F, F },
            { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
            { W, F, F, E, F, E, F, W, W, F, E, F, E, F, F, W },
            { W, F, W, W, W, W, F, E, E, F, W, W, W, W, F, W },
            { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W }
    };

    public int[][] collectibles = { { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
            { W, F, F, E, F, F, E, F, F, E, F, F, E, F, F, W },
            { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
            { W, E, W, W, W, W, F, W, W, F, W, W, W, W, E, W },
            { W, F, F, E, F, E, F, W, W, F, E, F, E, F, F, W },
            { W, W, W, F, W, W, W, W, W, W, W, W, F, W, W, W },
            { W, E, F, E, F, E, F, W, W, F, E, F, E, F, E, W },
            { F, F, W, W, W, W, E, W, W, E, W, W, W, W, F, F },
            { W, F, W, W, W, W, F, W, W, F, W, W, W, W, F, W },
            { W, F, F, E, F, E, F, W, W, F, E, F, E, F, F, W },
            { W, F, W, W, W, W, F, E, E, F, W, W, W, W, F, W },
            { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W }
    };

    public Board() {

    }

    public int[][] getBoard() {
        // System.out.println("from board" + this.board);
        return this.board;
    }

    public int[][] getCollectibles() {
        // System.out.println("from board" + this.board);
        return this.collectibles;
    }

    // public int getCollectibles(int i, int j) {
    // // System.out.println("from board" + this.board);
    // return this.collectibles[i][j];
    // }

    // public void update() {

    // }

    public void setCollectibles(int i, int j, int n) {
        this.collectibles[i][j] = n;
    }

}
