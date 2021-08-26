package Tests;

import java.util.*;

/**
 * 华为2021.8.25笔试题1，分值100
 * 最大子矩阵和
 * 给你一个 n * m 的矩阵，值均为任意整数
 * 找到该矩阵的一个子矩阵，使得子矩阵的所有值的和最大
 *
 * 示例输入：
 * 4 3      //4行3列
 * 0 -2 -7
 * 9 2 -6
 * -4 1 -4
 * -1 8 0
 * 示例输出：15
 * 解释：子矩阵为
 * 9 2
 * -4 1
 * -1 8
 * 和为15
 */
public class HuaweiTest01 {
    //最大子矩阵和
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); //n行
        int m = in.nextInt(); //m列
        int[][] matrix = new int[n][m];
        for(int i = 0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        System.out.println(getMaxSub(matrix));
    }

    public static int getMaxSub(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] += matrix[i - 1][j];
            }
        }
        int maxSum = Integer.MIN_VALUE;
        //循环暴力搜索
        // 第一层和第二层循环用于控制所有可能的行号组合 并将其压缩成一维数组
        // 第三层循环用于计算这个一维数组的最大子数组和，使用贪心算法
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {//从i到j行
                // result 保存的是从 i 行 到第 j 行 所对应的矩阵纵向相加的和（即把j-i+1 * k的矩阵压缩成1 * k的一维数组）
                int[] result = new int[matrix[0].length];
                for (int k = 0; k < matrix[0].length; k++) { //k列
                    result[k] = i == 0 ? matrix[j][k] : matrix[j][k] - matrix[i - 1][k];
                }
                maxSum = Math.max(maxSubsequence(result),maxSum);
            }
        }
        return maxSum;
    }

    //一维数组的最大子数组和
    public static int maxSubsequence(int[] a) {
        if (a.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[] maxSub = new int[a.length];
        maxSub[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            maxSub[i] = (maxSub[i - 1] > 0) ? (maxSub[i - 1] + a[i]) : a[i];
            max = Math.max(max,maxSub[i]);
        }
        return max;
    }
}
