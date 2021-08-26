package Tests;

import java.util.*;

/**
 * 华为2021.8.25笔试题2，分值200
 * 逃出生天
 * 给你一个row*col的矩阵，值均为非负整数，代表该位置的剩余秒数
 * 你将从0，0开始出发，每次横向或纵向行走1格，耗时1秒
 * 若你能成功走到右下角row-1，col-1，则输出耗时最短的秒数，若不能走到右下角，返回-1
 *
 * 示例输入：
 * 3 3      //3行3列
 * 2 3 2
 * 5 1 1
 * 4 5 5
 * 示例输出：4
 * 解释：走法为0,0 -> 1,0 -> 2,0 -> 2,1 -> 2,2 一共走4步，耗时4s
 *
 * 下面的方法只通过了85%，用动规可能更好
 */
public class HuaweiTest02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        row = in.nextInt(); //行
        col = in.nextInt(); //列
        int[][] matrix = new int[row][col];
        for(int i = 0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int i = run(matrix);
        //输出最短时间
        System.out.println(i);
    }
    static int time = 0;
    static int minTime = Integer.MAX_VALUE;
    static int row = 0;
    static int col = 0;
    public static int run(int[][] matrix){
        if(matrix[row-1][col-1] <= row+col-2 || matrix[0][0] == 0){
            return -1;
        }
        boolean[][] visited = new boolean[row][col];
        dfs(matrix, 0,0, visited);
        return minTime == Integer.MAX_VALUE? -1:minTime;
    }

    //i j 当前的位置 time 当前已经过了time秒
    public static void dfs(int[][] matrix, int i,int j, boolean[][] visited){
        if(minTime == row + col - 2) return;
        //如果到达终点，更新最短时间
        if(i == row-1 && j == col-1){
            minTime = Math.min(minTime,time);
        }
        visited[i][j] = true;
        time++;
        if(i+1<row && !visited[i+1][j] && matrix[i+1][j] - time > 0)
            dfs(matrix,i+1,j,visited);
        if(i-1>=0 && !visited[i-1][j] && matrix[i-1][j] - time > 0)
            dfs(matrix,i-1,j,visited);
        if(j+1<col && !visited[i][j+1] && matrix[i][j+1] - time > 0)
            dfs(matrix,i,j+1,visited);
        if(j-1>=0 && !visited[i][j-1] && matrix[i][j-1] - time > 0)
            dfs(matrix,i,j-1,visited);
        time--;
        visited[i][j] = false;
    }
}
