package com.mineSweeper.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.jupiter.api.Assertions.assertEquals;


public class GridTest {
    @Test
    public void testGridGeneration() {
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
    public void testAdjacentMines() {
        Grid grid = new Grid(6, 0);
        grid.getSquare(0, 0).setMine(true);
        grid.getSquare(2, 0).setMine(true);
        grid.getSquare(1, 1).setMine(true);
        grid.getSquare(2, 1).setMine(true);
        grid.getSquare(3, 1).setMine(true);
        grid.getSquare(2, 2).setMine(true);
        grid.getSquare(0, 3).setMine(true);
        grid.getSquare(4, 3).setMine(true);
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
    public void testRevealSquare() {
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
    public void testGetMaxMineCount() {
        // Test with various grid sizes
        assertEquals(0, GameUtils.getMaxMineCount(0)); // Edge case: grid size 0
        assertEquals(1, GameUtils.getMaxMineCount(2)); // Small grid size
        assertEquals(5, GameUtils.getMaxMineCount(4)); // Medium grid size
        assertEquals(17, GameUtils.getMaxMineCount(7)); // Larger grid size
        assertEquals(35, GameUtils.getMaxMineCount(10)); // Maximum grid size
    }

    @Test
    public void testPrintEmptyGridSmall() {
        Grid grid = new Grid(4, 0); // 4x4 grid with no mines
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
    public void testPrintGridWithRevealedSquaresSmall() {
        Grid grid = new Grid(4, 3); // 4x4 grid with 3 mines
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
    public void testPrintEmptyGridLarge() {
        Grid grid = new Grid(10, 20); // 3x3 grid with 2 mines
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
    public void testPrintGridWithRevealedSquaresLarge() {
        Grid grid = new Grid(10, 20); // 10x10 grid with 20 mines
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
        StringBuilder expectedOutputBuilder = new StringBuilder("Here is your updated minefield:\n");
        expectedOutputBuilder.append("  ");
        for (int i = 1; i <= gridSize; i++) {
            expectedOutputBuilder.append(i).append(" ");
        }
        expectedOutputBuilder.append("\n");
        for (int i = 0; i < gridSize; i++) {
            expectedOutputBuilder.append((char) ('A' + i)).append(" ");
            for (int j = 0; j < gridSize; j++) {
                if (grid.getSquare(i, j).isRevealed()) {
                    expectedOutputBuilder.append(adjacentMines[i][j]).append(" ");
                } else {
                    expectedOutputBuilder.append("_ ");
                }
            }
            expectedOutputBuilder.append("\n");
        }

        String expectedOutput = expectedOutputBuilder.toString().trim();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        grid.printGrid(true);
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }


}
