package com.mineSweeper.app;

import org.junit.jupiter.api.Test;
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

        assertEquals(4, grid.getSquare(1, 0).getAdjacentMines());
        assertEquals(3, grid.getSquare(3, 0).getAdjacentMines());
        assertEquals(2, grid.getSquare(0, 1).getAdjacentMines());
        assertEquals(0, grid.getSquare(5, 1).getAdjacentMines());
        assertEquals(4, grid.getSquare(3, 2).getAdjacentMines());
        assertEquals(2, grid.getSquare(1, 3).getAdjacentMines());
        assertEquals(1, grid.getSquare(0, 4).getAdjacentMines());
        assertEquals(0, grid.getSquare(2, 4).getAdjacentMines());

    }

}
