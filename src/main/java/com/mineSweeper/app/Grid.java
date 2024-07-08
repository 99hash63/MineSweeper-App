package com.mineSweeper.app;


import java.util.Random;

public class Grid {
    private final int size;
    private final int mineCount;
    private final Square[][] squares;
    private final Random random = new Random();


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
        int currentMineCount = 0;
        while (currentMineCount < this.mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!squares[row][col].isMine()) {
                squares[row][col].setMine(true);
                currentMineCount++;
            }
        }
    }

    public void calculateAdjacentMines() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!squares[i][j].isMine()) {
                    squares[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    private int countAdjacentMines(int i, int j) {
        int count = 0;
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if (isInBounds(row, col) && squares[row][col].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
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
