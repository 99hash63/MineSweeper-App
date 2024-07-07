package com.mineSweeper.app;

import java.util.Scanner;


public class MineSweeperGame {
    private Grid grid;
    private static final int MIN_GRID_SIZE = 2;
    private static final int MAX_GRID_SIZE = 15;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int gridSize;
        int mineCount;

        System.out.println();
        System.out.println("Welcome to Minesweeper!");
        System.out.println();
        do {
            System.out.println("Enter the size of the grid between " + MIN_GRID_SIZE + " and " + MAX_GRID_SIZE + " (e.g. 4 for a 4x4 grid): ");
            gridSize = getIntInput(scanner);
        } while (gridSize < MIN_GRID_SIZE || gridSize > MAX_GRID_SIZE);

        int maxMineCount = (int) (gridSize * gridSize * 0.35);
        do {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares = " + maxMineCount + "): ");
            mineCount = getIntInput(scanner);
        } while (mineCount < 1 || mineCount > maxMineCount);
        grid = new Grid(gridSize, mineCount);

        printGrid(false);
        while (true) {
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.next();
            if (!isSquareWithinValidRange(input, gridSize)) {
                System.out.println("Incorrect input");
                continue;
            }
            int row = input.charAt(0) - 'A';
            int col = Integer.parseInt(input.substring(1)) - 1;

            if (grid.getSquare(row, col).isMine()) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                break;
            } else {
                revealSquare(row, col);
                System.out.println("This square contains " + grid.getSquare(row, col).getAdjacentMines() + " adjacent mines.");
                printGrid(true);

                if (checkWin()) {
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            }
        }
        System.out.println("Press any key to play again...");
        scanner.next();
        start();
    }

    private int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private boolean isSquareWithinValidRange(String input, int gridSize) {
        if (input.length() < 2 || input.length() > 3) return false;
        char row = input.charAt(0);
        int col = Integer.parseInt(input.substring(1));
        return row >= 'A' && row < 'A' + gridSize && col >= 1 && col < 1 + gridSize;
    }

    private void printGrid(boolean isUpdated) {
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

    private void revealSquare(int row, int col) {
        Square square = grid.getSquare(row, col);
        if (square.isRevealed()) return;
        square.setRevealed(true);
        if (square.getAdjacentMines() == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i >= 0 && i < grid.getSize() && j >= 0 && j < grid.getSize()) {
                        revealSquare(i, j);
                    }
                }
            }
        }
    }

    private boolean checkWin() {
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
