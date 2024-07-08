package com.mineSweeper.app;

import java.util.Scanner;

public class InputUtil {

    private InputUtil() {}

    public static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Incorrect input.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static Square getSquareFromInput(String input, Grid grid) {
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return grid.getSquare(row, col);
    }

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
