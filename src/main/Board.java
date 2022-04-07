package main;

public class Board {

    public final int W = 1;
    public final int F = 2;
    public final int E = 3;
    public int[][] board = { { W, W, W, W, W, W, W, W, W, W, W, W, W, W, W, W },
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

    public Board() {

    }

    public int[][] getBoard() {
        // System.out.println("from board" + this.board);
        return this.board;
    }
}
