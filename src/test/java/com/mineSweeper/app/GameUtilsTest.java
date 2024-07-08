package com.mineSweeper.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameUtilsTest {

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
        GameUtils.printGrid(false, grid);

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
        GameUtils.printGrid(true, grid);

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
        GameUtils.printGrid(false, grid);

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
        GameUtils.printGrid(true, grid);
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    public void testRevealSquare() {
        Grid grid = new Grid(4, 3);
        int[][] squaresToReveal = {{0, 0}, {1, 1}, {2, 2}, {3, 1}}; // Example positions to reveal
        for (int[] square : squaresToReveal) {
            GameUtils.revealSquare(square[0], square[1], grid);
        }
        for (int[] square : squaresToReveal) {
            assertTrue(grid.getSquare(square[0], square[1]).isRevealed());
        }
    }

    @Test
    public void testCheckWin() {
        // Test with a grid where all non-mine squares are revealed
        Grid grid1 = new Grid(4, 3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!grid1.getSquare(i, j).isMine()) {
                    grid1.getSquare(i, j).setRevealed(true);
                }
            }
        }
        assertTrue(GameUtils.checkWin(grid1));
        Grid grid2 = new Grid(4, 3);
        grid2.getSquare(0, 0).setRevealed(true);  // Simulate one revealed square
        assertFalse(GameUtils.checkWin(grid2));
    }
}
