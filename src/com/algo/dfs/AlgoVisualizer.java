package com.algo.dfs;

import java.awt.*;
import java.util.Stack;

public class AlgoVisualizer {

    private MazeData mzData;
    private AlgoFrame frame;
    private int DELAY = 5;
    private int[][] direction = {
            {-1, 0},     // up
            {0, -1},     // left
            {1, 0},     // down
            {0, 1}      // right
    };

    public AlgoVisualizer(String filename, String title) {

        this.mzData = new MazeData(filename);

        int blockSideLen = 8;
        int canvasWidth = this.mzData.getColsOfMaze() * blockSideLen;
        int canvasHeight = this.mzData.getRowsOfMaze() * blockSideLen;

        EventQueue.invokeLater(() -> {
            this.frame = new AlgoFrame(title, canvasWidth, canvasHeight);
            new Thread(() -> {
                this.run();
            }).start();
        });
    }

    private void run() {
        this.renderData();          // render

        this.goBfsWithoutRecursion(
                this.mzData.getRowOfEntrance(),
                this.mzData.getColOfEntrance()
        );

        this.renderData();          // render
    }

    private void goBfsWithoutRecursion(int row, int col) {
        boolean hasResult = false;

        if (!this.mzData.inArea(row, col)) {
            throw new IllegalArgumentException("row or col is out of range");
        }

        this.mzData.visited[row][col] = true;
        this.mzData.path[row][col] = true;

        this.renderData();          // render

        Stack<Postion> stack = new Stack<Postion>();
        stack.push(new Postion(row, col));

        while (!stack.empty()) {

            Postion curPos = stack.pop();

            if (
                    curPos.getRowX() == this.mzData.getRowOfExit() &&
                    curPos.getColY() == this.mzData.getColOfExit()
            ) {
                hasResult = true;
                this.showResult(curPos);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newOfRow = curPos.getRowX() + this.direction[i][0];
                int newOfCol = curPos.getColY() + this.direction[i][1];

                if (
                        this.mzData.inArea(newOfRow, newOfCol) &&
                        this.mzData.getMazeAt(newOfRow, newOfCol) == MazeData.ROAD &&
                        !this.mzData.visited[newOfRow][newOfCol]
                ) {
                    this.mzData.visited[newOfRow][newOfCol] = true;
                    this.mzData.path[newOfRow][newOfCol] = true;

                    this.renderData();      // render

                    stack.push(new Postion(newOfRow, newOfCol, curPos));
                }
            }

        }

        if (!hasResult) {
            System.out.println("no result");
        }
    }

    private void showResult(Postion pos) {
        while(pos != null) {
            this.mzData.result[pos.getRowX()][pos.getColY()] = true;
            pos = pos.prevPos;
        }
    }

    private void renderData() {


        this.frame.render(this.mzData);
        AlgoVisHelper.pause(this.DELAY);
    }
}
