package com.mineSweeper.app;

public class Square {
    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMines;

    public Square(){
        this.isMine = false;
        this.isRevealed = false;
        this.adjacentMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }


}
