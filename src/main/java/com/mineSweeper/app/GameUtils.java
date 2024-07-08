package com.mineSweeper.app;

public class GameUtils {

    public static int getMaxMineCount(int gridSize) {
        return (int) (gridSize * gridSize * 0.35);
    }

    public static void printGrid(boolean isUpdated, Grid grid) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        String message = isUpdated ? "Here is your updated minefield:" : "Here is your minefield:";
        sb.append(message).append("\n");
        sb.append("  ");
        for (int i = 1; i <= grid.getSize(); i++) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < grid.getSize(); i++) {
            sb.append((char) ('A' + i)).append(" ");
            for (int j = 0; j < grid.getSize(); j++) {
                Square square = grid.getSquare(i, j);
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
