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
                squares[i][j] = new Square(i,j);
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
                            if (row < 0 || row >= size || col < 0 || col >= size) {
                                continue;
                            }
                            if (squares[row][col].isMine()) {
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
        return this.size;
    }

    public Square getSquare(int row, int col) {
        return squares[row][col];
    }

    public void revealSquare(Square square) {
        if (square.isRevealed()) return;
        square.setRevealed(true);
        if (square.getAdjacentMines() == 0) {
            for (int i = square.getRow() - 1; i <= square.getRow() + 1; i++) {
                for (int j = square.getCol() - 1; j <= square.getCol() + 1; j++) {
                    if (i >= 0 && i < this.getSize() && j >= 0 && j < this.getSize()) {
                        revealSquare(this.getSquare(i, j));
                    }
                }
            }
        }
    }

    public void printGrid(boolean isUpdated) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        String message = isUpdated ? "Here is your updated minefield:" : "Here is your minefield:";
        sb.append(message).append("\n");
        sb.append("  ");
        for (int i = 1; i <= this.getSize(); i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < this.getSize(); i++) {
            sb.append((char) ('A' + i)).append(" ");
            for (int j = 0; j < this.getSize(); j++) {
                Square square = this.getSquare(i, j);
                if (square.isRevealed()) {
                    sb.append(square.getAdjacentMines()).append(" ");
                } else {
                    sb.append("_ ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
