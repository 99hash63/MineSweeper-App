package com.mineSweeper.app;
import java.util.Scanner;


public class MineSweeperGame {
    private Grid grid;
    private static final int MIN_GRID_SIZE = 2;
    private static final int MAX_GRID_SIZE = 9;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int gridSize;
        int mineCount;

        System.out.println(" ");
        System.out.println("Welcome to Minesweeper!");
        System.out.println(" ");
        do {
            System.out.println("Enter the size of the grid between " + MIN_GRID_SIZE + " and " + MAX_GRID_SIZE + " (e.g. 4 for a 4x4 grid): ");
            gridSize = getIntInput(scanner);
        } while (gridSize < MIN_GRID_SIZE || gridSize > MAX_GRID_SIZE);

        int maxMineCount = (int) (gridSize * gridSize * 0.35);
        do {
            System.out.println("Enter the number of mines to place on the grid (maximum is " + maxMineCount + "): ");
            mineCount = getIntInput(scanner);
        } while (mineCount < 1 || mineCount > maxMineCount);
        grid = new Grid(gridSize, mineCount);

    }

    private int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void printGrid() {
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
    }
}
