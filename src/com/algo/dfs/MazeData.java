package com.algo.dfs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int rowsOfMaze;
    private int colsOfMaze;

    private int rowOfEntrance;
    private int colOfEntrance;
    private int rowOfExit;
    private int colOfExit;

    private char[][] maze;
    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;

    public MazeData(String filename) {

        Scanner scanner = null;

        try {

            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);

            scanner = new Scanner(new BufferedInputStream(fis), StandardCharsets.UTF_8);

            String nmLine = scanner.nextLine();
            String[] nm = nmLine.trim().split("\\s+");
            this.rowsOfMaze = Integer.parseInt(nm[0]);
            this.colsOfMaze = Integer.parseInt(nm[1]);

            this.maze = new char[this.rowsOfMaze][this.colsOfMaze];
            this.visited = new boolean[this.rowsOfMaze][this.colsOfMaze];
            this.path = new boolean[this.rowsOfMaze][this.colsOfMaze];
            this.result = new boolean[this.rowsOfMaze][this.colsOfMaze];

            for (int i = 0; i < this.rowsOfMaze; i++) {
                String tmpStr = scanner.nextLine();
                if (tmpStr.length() != this.colsOfMaze) {
                    throw new IllegalArgumentException("filename is wrong");
                }

                for (int j = 0; j < this.colsOfMaze; j++) {
                    this.maze[i][j] = tmpStr.charAt(j);
                    this.visited[i][j] = false;
                    this.path[i][j] = false;
                    this.result[i][j] = false;
                }
            }

            this.rowOfEntrance = 1;
            this.colOfEntrance = 0;
            this.rowOfExit = this.rowsOfMaze - 2;
            this.colOfExit = this.colsOfMaze - 1;

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public int getRowsOfMaze() {
        return rowsOfMaze;
    }

    public int getColsOfMaze() {
        return colsOfMaze;
    }

    public int getRowOfEntrance() {
        return rowOfEntrance;
    }

    public int getColOfEntrance() {
        return colOfEntrance;
    }

    public int getRowOfExit() {
        return rowOfExit;
    }

    public int getColOfExit() {
        return colOfExit;
    }

    public char getMazeAt(int row, int col) {
        if (!this.inArea(row, col)) {
            throw new IllegalArgumentException("row or col is out of range");
        }
        return this.maze[row][col];
    }

    public boolean inArea(int row, int col) {
        return
                row >= 0 &&
                row < this.rowsOfMaze &&
                col >= 0 &&
                col < this.colsOfMaze;
    }

    public void printToConsole() {
        for (int i = 0; i < this.getRowsOfMaze(); i++) {
            for (int j = 0; j < this.getColsOfMaze(); j++) {
                System.out.print(this.maze[i][j]);
            }
            System.out.println();
        }
    }










}

