package Tests;

import java.util.Scanner;

public class Test01 {
    /**
     * 第一行输入矩阵行，列，走的步数；
     * 然后输入矩阵（@表示开始位置，#表示不能通过，.表示可以走）
     * 然后输入方向序列。（每次选定一个方向那么就走到头，除非遇到墙（#）或者到边上）
     * 输出最终位置
     * 3 4 4
     * @...
     * .#..
     * ...#
     *
     * EAST
     * SOUTH
     * WEST
     * NORTH
     *
     * 结果输出 1 3
     */
    /**
     *
     * @param matrix 迷宫矩阵 0表示可走 1表示墙
     * @param x 当前行位置
     * @param y 当前列位置
     * @param at 方向
     * @return 行和列
     */
    public static int[] destination(int[][] matrix, int x,int y, String at){
        int[] dest = new int[]{-1,-1};
        int m = matrix.length;
        int n = matrix[0].length;
        switch (at) {
            case "EAST":
                dest[0] = x;
                for(int j = y; j < n;j++){
                    if(matrix[x][j] == 1){
                        dest[1] = j - 1;
                    }
                }
                dest[1] = dest[1] == -1 ? n-1 : dest[1];
                break;
            case "SOUTH":
                dest[1] = y;
                for(int i = x; i < m ;i++){
                    if(matrix[i][y] == 1){
                        dest[0] = i - 1;
                    }
                }
                dest[0] = dest[0] == -1 ? m-1 : dest[0];
                break;
            case "WEST":
                dest[0] = x;
                for(int j = y; j >=0 ;j--){
                    if(matrix[x][j] == 1){
                        dest[1] = j + 1;
                    }
                }
                dest[1] = dest[1] == -1 ? 0 : dest[1];
                break;
            case "NORTH":
                dest[1] = y;
                for(int i = x; i >= 0;i--){
                    if(matrix[i][y] == 1){
                        dest[0] = i + 1;
                    }
                }
                dest[0] = dest[0] == -1 ? 0 : dest[0];
                break;
        }
        return dest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int[][] matrix = new int[m][n];
        String[] whereToGo = new String[p];
        int x = -1, y = -1; //起点
        for(int i = 0;i < m; i++){
            for(int j = 0;j < n;j++){
                String c = scanner.next();
                switch (c) {
                    case "@":
                        x = i;
                        y = j;
                        break;
                    case ".":
                        matrix[i][j] = 0;
                        break;
                    case "#":
                        matrix[i][j] = 1;
                        break;
                }
            }
        }
        for(int i = 0;i < p; i++){
            whereToGo[i] = scanner.next();
        }

        for(String at: whereToGo){
            int[] xy = destination(matrix, x, y, at);
            x = xy[0];
            y = xy[1];
        }

        System.out.println((x+1)+ " "+ (y+1));
    }
}
