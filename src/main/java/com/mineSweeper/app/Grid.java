package com.mineSweeper.app;


import java.util.Random;

public class Grid {
    private final int size;
    private final int mineCount;
    private final Square[][] squares;

    public Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.squares = new Square[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = new Square();
            }
        }

        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!squares[row][col].isMine()) {
                squares[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

}
