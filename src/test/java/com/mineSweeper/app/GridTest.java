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


}
