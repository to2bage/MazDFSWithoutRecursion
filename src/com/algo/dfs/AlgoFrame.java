package com.algo.dfs;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        this.setContentPane(canvas);
        this.pack();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    // TODO:
    private MazeData mzData;
    public void render(MazeData mzData) {
        this.mzData = mzData;
        this.repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            super(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2d.setRenderingHints(hints);

            // TODO:
            int width = AlgoFrame.this.getCanvasWidth() / AlgoFrame.this.mzData.getColsOfMaze();
            int height = AlgoFrame.this.getCanvasHeight() / AlgoFrame.this.mzData.getRowsOfMaze();

            for (int i = 0; i < AlgoFrame.this.mzData.getRowsOfMaze(); i++) {
                for (int j = 0; j < AlgoFrame.this.mzData.getColsOfMaze(); j++) {

                    if (AlgoFrame.this.mzData.getMazeAt(i, j) == MazeData.ROAD) {
                        AlgoVisHelper.setColor(g2d, Color.white);
                    } else if (AlgoFrame.this.mzData.getMazeAt(i, j) == MazeData.WALL) {
                        AlgoVisHelper.setColor(g2d, Color.blue);
                    }

                    if (AlgoFrame.this.mzData.visited[i][j]) {
                        AlgoVisHelper.setColor(g2d, Color.green);
                    }

                    if (AlgoFrame.this.mzData.path[i][j]) {
                        AlgoVisHelper.setColor(g2d, Color.red);
                    }

                    if (AlgoFrame.this.mzData.result[i][j]) {
                        AlgoVisHelper.setColor(g2d, Color.yellow);
                    }

                    AlgoVisHelper.fillRectangle(
                            g2d,
                            j * width,
                            i * height,
                            width,
                            height
                    );
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(
                    AlgoFrame.this.getCanvasWidth(),
                    AlgoFrame.this.getCanvasHeight()
            );
        }
    }









}
