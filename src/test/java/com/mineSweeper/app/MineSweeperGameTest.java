package com.mineSweeper.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MineSweeperGameTest {

    @Test
    void testGetMaxMineCount() {
        // Test with various grid sizes
        assertEquals(0, GameUtil.getMaxMineCount(0)); // Edge case: grid size 0
        assertEquals(1, GameUtil.getMaxMineCount(2)); // Small grid size
        assertEquals(5, GameUtil.getMaxMineCount(4)); // Medium grid size
        assertEquals(17, GameUtil.getMaxMineCount(7)); // Larger grid size
        assertEquals(35, GameUtil.getMaxMineCount(10)); // Maximum grid size
    }

    @Test
    void testCheckWin() {
        MineSweeperGame game = new MineSweeperGame();
        Grid grid1 = new Grid(4, 3);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!grid1.getSquare(i, j).isMine()) {
                    grid1.getSquare(i, j).setRevealed(true);
                }
            }
        }
        assertTrue(game.checkWin(grid1));
        Grid grid2 = new Grid(4, 3);
        grid2.getSquare(0, 0).setRevealed(true);  // Simulate one revealed square
        assertFalse(game.checkWin(grid2));
    }
}
