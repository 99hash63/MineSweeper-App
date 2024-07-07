package com.mineSweeper.app;
import java.util.Scanner;


public class MineSweeperGame {
    private Grid grid;
    private static final int MIN_GRID_SIZE = 2;
    private static final int MAX_GRID_SIZE = 10;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int gridSize;
        int mineCount;

        System.out.println("Welcome to Minesweeper!");
        do {
            System.out.println("Enter the size of the grid between " + MIN_GRID_SIZE + " and " + MAX_GRID_SIZE + " (e.g. 4 for a 4x4 grid): ");
            gridSize = getIntInput(scanner);
        } while (gridSize < MIN_GRID_SIZE || gridSize > MAX_GRID_SIZE);

        int maxMineCount = (int) (gridSize * gridSize * 0.35);

        do {
            System.out.println("Enter the number of mines to place on the grid (maximum is " + maxMineCount + "): ");
            mineCount = getIntInput(scanner);
        } while (mineCount < 1 || mineCount > maxMineCount);

    }

    private int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input.");
            scanner.next();
        }
        return scanner.nextInt();
    }


    }
