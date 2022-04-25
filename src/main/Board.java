package main;

public class Board {

    public int initalCoins;
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
        this.initalCoins = 24;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public int[][] getCollectibles() {
        return this.collectibles;
    }

    public void setCollectibles(int i, int j, int n) {
        this.collectibles[i][j] = n;
    }

    public void setCollectibles(int[][] c) {
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                this.collectibles[i][j] = c[i][j];
            }
        }
    }

}
