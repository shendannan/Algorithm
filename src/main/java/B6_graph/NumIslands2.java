package B6_graph;

import B0_BasicDataStructure.UDS;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumIslands2 {
    /**
     * 类似岛屿数量，给定一个无向图，每一个能连接成一片的称为一个岛，返回岛的数量。
     *
     * 给你一个 n x n 的矩阵 isConnected，
     * isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
     * 而 isConnected[i][j] = 0 表示二者不直接相连。
     *
     * 链接：https://leetcode-cn.com/problems/number-of-provinces
     */
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;

        //DFS
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, provinces, i);
                circles++;
            }
        }

        //BFS
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }

        //UDS
        UDS uds = new UDS(provinces);
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    uds.union(i, j);
                }
            }
        }
        for (int i = 1; i <= provinces; i++) {
            //并查集的特点：如果下标和值相同，就是一个新的岛屿，否则就是附属于其他岛屿。
            if (uds.parent[i] == i) circles++;
        }
        return circles;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i) {
        for (int j = 0; j < provinces; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, provinces, j);
            }
        }
    }

}
