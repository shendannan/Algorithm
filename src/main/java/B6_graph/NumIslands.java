package B6_graph;

/**
 * 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 */
public class NumIslands {
    public void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);//上
        dfs(grid, r + 1, c);//下
        dfs(grid, r, c - 1);//左
        dfs(grid, r, c + 1);//右
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        // 深度优先搜索，遇见1就用一次DFS将相邻的1都变成0，用了几次DFS就有几个岛
         for (int r = 0; r < nr; ++r) {
             for (int c = 0; c < nc; ++c) {
                 if (grid[r][c] == '1') {
                     ++num_islands;
                     dfs(grid, r, c);
                 }
             }
         }

        // 广度优先搜索，遇见1就用一次BFS将相邻的1都变成0，用了几次BFS就有几个岛
//        for (int r = 0; r < nr; r++) {
//            for (int c = 0; c < nc; c++) {
//                if (grid[r][c] == '1') {
//                    num_islands++;
//                    grid[r][c] = '0';
//                    Deque<Integer> neighbors = new ArrayDeque<>();
//                    neighbors.offer(r * nc + c);//记录当前位置
//                    while (!neighbors.isEmpty()) {
//                        int id = neighbors.poll();
//                        //反算得到行列号
//                        int row = id / nc;
//                        int col = id % nc;
//                        //将相邻的1加入队列
//                        if (row - 1 >= 0 && grid[row-1][col] == '1') {
//                            neighbors.offer((row-1) * nc + col);
//                            grid[row-1][col] = '0';
//                        }
//                        if (row + 1 < nr && grid[row+1][col] == '1') {
//                            neighbors.offer((row+1) * nc + col);
//                            grid[row+1][col] = '0';
//                        }
//                        if (col - 1 >= 0 && grid[row][col-1] == '1') {
//                            neighbors.offer(row * nc + col-1);
//                            grid[row][col-1] = '0';
//                        }
//                        if (col + 1 < nc && grid[row][col+1] == '1') {
//                            neighbors.offer(row * nc + col+1);
//                            grid[row][col+1] = '0';
//                        }
//                    }
//                }
//            }
//        }

        return num_islands;
    }
}
