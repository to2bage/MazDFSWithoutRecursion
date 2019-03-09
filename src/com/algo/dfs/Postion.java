package com.algo.dfs;

public class Postion {

    private int rowX;
    private int colY;
    public Postion prevPos;

    public Postion(int row, int col, Postion prevPos) {
        this.rowX = row;
        this.colY = col;
        this.prevPos = prevPos;
    }

    public Postion(int row, int col) {
        this(row, col, null);
    }

    public int getRowX() {
        return rowX;
    }

    public int getColY() {
        return colY;
    }


}
