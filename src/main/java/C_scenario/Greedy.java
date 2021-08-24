package C_scenario;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 一条长度为L的河道,即可以看作是[0,L]的一条数轴。有n个守卫负责看守数轴上的一段范围
 * 问至少用几个守卫可以覆盖整段河道
 * 输入第一行包括两个正整数n和L
 * 接下来的n行,每行两个正整数xi,yi,表示第i个守卫覆盖的区间。
 */
public class Greedy {
    //贪心算法
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//共有n个卫兵
        int L = in.nextInt();//河道长
        int[][] temp = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                temp[i][j] = in.nextInt();
            }
        }
        //获得了数组，进行排序
        Arrays.sort(temp, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int index = 0; //第index个卫兵
        int count = 0;
        int pre = 0;   //右边界
        while (pre < L) {
            //无法满足起点
            if (temp[index][0] > pre) {
                System.out.println(-1);
                return;
            }
            int max = 0;
            //贪心，找到最大范围的守卫
            while (index < n && temp[index][0] <= pre) {
                max = Math.max(max, temp[index][1]);
                index++;
            }
            count++;
            pre = max;
            //已满足条件
            if (pre >= L) {
                System.out.println(count);
                return;
            }
            //无法满足终点
            if (index >= n) {
                System.out.println(-1);
                return;
            }
        }
    }
}