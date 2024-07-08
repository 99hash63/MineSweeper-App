package com.mineSweeper.app;

import java.util.Scanner;

import static com.mineSweeper.app.GameUtils.*;
import static com.mineSweeper.app.InputUtil.getIntInput;
import static com.mineSweeper.app.InputUtil.getSquareFromInput;
import static com.mineSweeper.app.InputUtil.isValidSquareInput;

public class MineSweeperGame {
    //Constants can be moved to a separate file if used in other classes 
    private static final int MIN_GRID_SIZE = 2;
    private static final int MAX_GRID_SIZE = 10;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int gridSize;
        int mineCount;

        System.out.println("\nWelcome to Minesweeper!\n");
        gridSize = promptForGridSize(scanner);
        int maxMineCount = getMaxMineCount(gridSize);
        mineCount = promptForMineCount(scanner, maxMineCount);
        Grid grid = new Grid(gridSize, mineCount);

        grid.printGrid(false);
        playGame(scanner, gridSize, grid);
        endGame(scanner);
    }

    private int promptForGridSize(Scanner scanner) {
        int gridSize;
        do {
            System.out.println("Enter the size of the grid between " + MIN_GRID_SIZE + " and " + MAX_GRID_SIZE + " (e.g. 4 for a 4x4 grid): ");
            gridSize = getIntInput(scanner);
            if (gridSize < MIN_GRID_SIZE)
                System.out.println("Minimum size of the grid is " + MIN_GRID_SIZE);
            if (gridSize > MAX_GRID_SIZE)
                System.out.println("Maximum size of the grid is " + MAX_GRID_SIZE);
        } while (gridSize < MIN_GRID_SIZE || gridSize > MAX_GRID_SIZE);
        return gridSize;
    }

    private int promptForMineCount(Scanner scanner, int maxMineCount) {
        int mineCount;
        do {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares = " + maxMineCount + "): ");
            mineCount = getIntInput(scanner);
            if (mineCount > maxMineCount)
                System.out.println("Maximum number is 35% of the total squares = " + maxMineCount);
            if (mineCount < 1)
                System.out.println("There must be at least 1 mine.");
        } while (mineCount < 1 || mineCount > maxMineCount);
        return mineCount;
    }

    private void playGame(Scanner scanner, int gridSize, Grid grid) {
        while (true) {
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.next();
            if (!isValidSquareInput(input, gridSize)) {
                System.out.println("Incorrect input");
                continue;
            }
            Square currentSquare = getSquareFromInput(input, grid);

            if (currentSquare.isMine()) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                break;
            } else {
                grid.revealSquare(currentSquare);
                System.out.println("This square contains " + currentSquare.getAdjacentMines() + " adjacent mines.");
                grid.printGrid(true);

                if (checkWin(grid)) {
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            }
        }
    }

    public boolean checkWin(Grid grid) {
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

    private void endGame(Scanner scanner) {
        System.out.println("Press -1 to exit, any other key to play again.");
        String userInput = scanner.next();
        if ("-1".equals(userInput))
            System.exit(0);
        else
            start();
        scanner.close();
    }


}
