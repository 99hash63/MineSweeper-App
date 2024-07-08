package com.mineSweeper.app;

public class GameUtils {
    public static int getMaxMineCount(int gridSize) {
        return (int) (gridSize * gridSize * 0.35);
    }
}
