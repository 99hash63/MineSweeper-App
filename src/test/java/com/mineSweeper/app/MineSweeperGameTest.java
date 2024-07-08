package com.mineSweeper.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class MineSweeperGameTest {

    @Test
     void testCheckWin() {
        // Test with a grid where all non-mine squares are revealed
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
