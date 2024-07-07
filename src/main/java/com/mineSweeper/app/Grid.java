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

        generateGrid();
        placeMines();
        calculateAdjacentMines();
    }

    private void generateGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = new Square();
            }
        }
    }

    private void placeMines() {
        Random random = new Random();
        int mineCount = 0;
        while (mineCount < this.mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!squares[row][col].isMine()) {
                squares[row][col].setMine(true);
                mineCount++;
            }
        }
    }
    public void calculateAdjacentMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!squares[i][j].isMine()) {
                    int count = 0;
                    for (int row = i - 1; row <= i + 1; row++) {
                        for (int col = j - 1; col <= j + 1; col++) {
                            if (row >= 0 && row < size && col >= 0 && col < size && squares[row][col].isMine()) {
                                count++;
                            }
                        }
                    }
                    squares[i][j].setAdjacentMines(count);
                }
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
