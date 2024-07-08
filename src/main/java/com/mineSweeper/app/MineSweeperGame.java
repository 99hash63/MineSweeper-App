package com.mineSweeper.app;

import java.util.Scanner;

import static com.mineSweeper.app.GameConstants.MAX_GRID_SIZE;
import static com.mineSweeper.app.GameConstants.MIN_GRID_SIZE;
import static com.mineSweeper.app.InputHandler.getIntInput;
import static com.mineSweeper.app.InputHandler.isValidSquareInput;
import static com.mineSweeper.app.GameUtils.printGrid;
import static com.mineSweeper.app.GameUtils.revealSquare;
import static com.mineSweeper.app.GameUtils.checkWin;

public class MineSweeperGame {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int gridSize;
        int mineCount;

        System.out.println("\nWelcome to Minesweeper!\n");

        gridSize = promptForGridSize(scanner);

        int maxMineCount = (int) (gridSize * gridSize * 0.35);
        mineCount = promptForMineCount(scanner, maxMineCount);

        Grid grid = new Grid(gridSize, mineCount);
        printGrid(false, grid);

        while (true) {
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.next();

            if (!isValidSquareInput(input, gridSize)) {
                System.out.println("Incorrect input");
                continue;
            }

            int row = input.charAt(0) - 'A';
            int col = Integer.parseInt(input.substring(1)) - 1;

            if (grid.getSquare(row, col).isMine()) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                break;
            } else {
                revealSquare(row, col, grid);
                System.out.println("This square contains " + grid.getSquare(row, col).getAdjacentMines() + " adjacent mines.");
                printGrid(true, grid);

                if (checkWin(grid)) {
                    System.out.println("Congratulations, you have won the game!");
                    break;
                }
            }
        }

        System.out.println("Press -1 to exit, any other key to play again.");
        String userInput = scanner.next();
        if ("-1".equals(userInput))
            System.exit(0);
        else
            start();
        scanner.close();
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
}
