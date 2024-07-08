package com.mineSweeper.app;

public class GameUtils {
    public static void printGrid(boolean isUpdated, Grid grid) {
        System.out.println();
        String message = isUpdated ? "Here is your updated minefield:" : "Here is your minefield:";
        System.out.println(message);
        System.out.print("  ");
        for (int i = 1; i <= grid.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < grid.getSize(); i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < grid.getSize(); j++) {
                Square square = grid.getSquare(i, j);
                if (square.isRevealed()) {
                    System.out.print(square.getAdjacentMines() + " ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void revealSquare(int row, int col, Grid grid) {
        Square square = grid.getSquare(row, col);
        if (square.isRevealed()) return;
        square.setRevealed(true);
        if (square.getAdjacentMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < grid.getSize() && j >= 0 && j < grid.getSize()) {
                        revealSquare(i, j, grid);
                    }
                }
            }
        }
    }

    public static boolean checkWin(Grid grid) {
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Square square = grid.getSquare(i, j);
                if (!square.isMine() && !square.isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }
}