package com.mineSweeper.app;

import java.util.Scanner;

/**
 * The type Input util.
 */
public class InputUtil {

    private InputUtil() {
    }

    /**
     * Gets int input.
     *
     * @param scanner the scanner
     * @return the int input
     */
    public static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Gets square from input.
     *
     * @param input the input
     * @param grid  the grid
     * @return the square from input
     */
    public static Square getSquareFromInput(String input, Grid grid) {
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return grid.getSquare(row, col);
    }

    /**
     * Checks if valid square input.
     *
     * @param input    the input
     * @param gridSize the grid size
     * @return the boolean
     */
    public static boolean isValidSquareInput(String input, int gridSize) {
        if (input.length() < 2 || input.length() > 3) return false;
        try {
            char row = input.charAt(0);
            int col = Integer.parseInt(input.substring(1));
            return row >= 'A' && row < 'A' + gridSize && col >= 1 && col < 1 + gridSize;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
