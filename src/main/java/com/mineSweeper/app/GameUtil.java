package com.mineSweeper.app;

public class GameUtil {

    private GameUtil() {}
    public static int getMaxMineCount(int gridSize) {
        return (int) (gridSize * gridSize * 0.35);
    }
}
