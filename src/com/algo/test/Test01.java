package com.algo.test;

import com.algo.dfs.AlgoVisualizer;
import com.algo.dfs.MazeData;

public class Test01 {
    public static void main(String[] args) {

        String filename = "maze_101_101.txt";
//        MazeData mzData = new MazeData(filename);
//        mzData.printToConsole();

        AlgoVisualizer visualizer = new AlgoVisualizer(filename, "看得见的迷宫(深度优先)");
    }
}
