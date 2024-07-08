package com.mineSweeper.app;

/**
 * The type Game util.
 */
public class GameUtil {

    private GameUtil() {
    }

    /**
     * Gets maximum mine count at the given grid size.
     *
     * @param gridSize the grid size
     * @return the max mine count
     */
    public static int getMaxMineCount(int gridSize) {
        return (int) (gridSize * gridSize * 0.35);
    }
}
