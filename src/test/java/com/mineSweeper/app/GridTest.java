package com.mineSweeper.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GridTest {
    @Test
    void testGridGeneration() {
        Grid grid = new Grid(5, 3);
        assertEquals(5, grid.getSize());
        int mineCount = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (grid.getSquare(i, j).isMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(3, mineCount);
    }

    @Test
    void testAdjacentMines() {
        Grid grid = new Grid(6, 0);
        int[][] mines = {{0, 0}, {2, 0}, {1, 1}, {2, 1}, {3, 1}, {2, 2}, {0, 3}, {4, 3}};
        for (int[] coordinate : mines) {
            int row = coordinate[0];
            int col = coordinate[1];
            grid.getSquare(row, col).setMine(true);
        }
        grid.calculateAdjacentMines();

        assertEquals(4, grid.getSquare(1, 0).getAdjacentMines());
        assertEquals(3, grid.getSquare(3, 0).getAdjacentMines());
        assertEquals(2, grid.getSquare(0, 1).getAdjacentMines());
        assertEquals(0, grid.getSquare(5, 1).getAdjacentMines());
        assertEquals(4, grid.getSquare(3, 2).getAdjacentMines());
        assertEquals(2, grid.getSquare(1, 3).getAdjacentMines());
        assertEquals(1, grid.getSquare(0, 4).getAdjacentMines());
        assertEquals(0, grid.getSquare(2, 4).getAdjacentMines());

    }

    @Test
    void testRevealSquare() {
        Grid grid = new Grid(4, 3);
        int[][] squaresToReveal = {{0, 0}, {1, 1}, {2, 2}, {3, 1}}; // Example positions to reveal
        for (int[] square : squaresToReveal) {
            grid.revealSquare(grid.getSquare(square[0], square[1]));
        }
        for (int[] square : squaresToReveal) {
            assertTrue(grid.getSquare(square[0], square[1]).isRevealed());
        }
    }

    @Test
    void testPrintEmptyGridSmall() {
        Grid grid = new Grid(4, 0);
        String expectedOutput = "Here is your minefield:\n" +
                "  1 2 3 4 \n" +
                "A _ _ _ _ \n" +
                "B _ _ _ _ \n" +
                "C _ _ _ _ \n" +
                "D _ _ _ _";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        grid.printGrid(false);

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testPrintGridWithRevealedSquaresSmall() {
        Grid grid = new Grid(4, 3);
        grid.getSquare(0, 0).setRevealed(true);
        grid.getSquare(1, 1).setRevealed(true);
        int adjacentMines1 = grid.getSquare(0, 0).getAdjacentMines();
        int adjacentMines2 = grid.getSquare(1, 1).getAdjacentMines();

        String expectedOutput = "Here is your updated minefield:\n" +
                "  1 2 3 4 \n" +
                "A " + adjacentMines1 + " _ _ _ \n" +
                "B _ " + adjacentMines2 + " _ _ \n" +
                "C _ _ _ _ \n" +
                "D _ _ _ _";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        grid.printGrid(true);

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testPrintEmptyGridLarge() {
        Grid grid = new Grid(10, 20);
        String expectedOutput = "Here is your minefield:\n" +
                "  1 2 3 4 5 6 7 8 9 10 \n" +
                "A _ _ _ _ _ _ _ _ _ _ \n" +
                "B _ _ _ _ _ _ _ _ _ _ \n" +
                "C _ _ _ _ _ _ _ _ _ _ \n" +
                "D _ _ _ _ _ _ _ _ _ _ \n" +
                "E _ _ _ _ _ _ _ _ _ _ \n" +
                "F _ _ _ _ _ _ _ _ _ _ \n" +
                "G _ _ _ _ _ _ _ _ _ _ \n" +
                "H _ _ _ _ _ _ _ _ _ _ \n" +
                "I _ _ _ _ _ _ _ _ _ _ \n" +
                "J _ _ _ _ _ _ _ _ _ _";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        grid.printGrid(false);

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testPrintGridWithRevealedSquaresLarge() {
        Grid grid = new Grid(10, 20);
        int gridSize = grid.getSize();
        int[][] squaresToReveal = {
                {0, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4},
                {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}
        };
        for (int[] square : squaresToReveal) {
            grid.getSquare(square[0], square[1]).setRevealed(true);
        }
        int[][] adjacentMines = new int[gridSize][gridSize];
        for (int[] square : squaresToReveal) {
            adjacentMines[square[0]][square[1]] = grid.getSquare(square[0], square[1]).getAdjacentMines();
        }

        String expectedOutput = "Here is your updated minefield:\n" +
                "  1 2 3 4 5 6 7 8 9 10 \n" +
                "A " + adjacentMines[0][0] + " _ _ _ _ _ _ _ _ _ \n" +
                "B _ " + adjacentMines[1][1] + " _ _ _ _ _ _ _ _ \n" +
                "C _ _ " + adjacentMines[2][2] + " _ _ _ _ _ _ _ \n" +
                "D _ _ _ " + adjacentMines[3][3] + " _ _ _ _ _ _ \n" +
                "E _ _ _ _ " + adjacentMines[4][4] + " _ _ _ _ _ \n" +
                "F _ _ _ _ _ " + adjacentMines[5][5] + " _ _ _ _ \n" +
                "G _ _ _ _ _ _ " + adjacentMines[6][6] + " _ _ _ \n" +
                "H _ _ _ _ _ _ _ " + adjacentMines[7][7] + " _ _ \n" +
                "I _ _ _ _ _ _ _ _ " + adjacentMines[8][8] + " _ \n" +
                "J _ _ _ _ _ _ _ _ _ " + adjacentMines[9][9] + "";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        grid.printGrid(true);

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
