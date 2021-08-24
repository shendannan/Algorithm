package B6_graph;

import B0_BasicDataStructure.UDS;

import java.util.Arrays;
import java.util.Comparator;

public class MinCost {
    /**
     * 最小成本连接所有城市
     * 地图上有N座孤立城市，它们按以 1 到 N 的次序编号。
     *
     * 给你一些可连接的选项 conections
     * conections[i] = [city1, city2, cost]表示将城市 city1 和城市 city2 连接所要的成本。
     *
     * 返回使得每对城市间都存在将它们连接在一起的连通路径（可能长度为1的）最小成本。
     * 该最小成本应该是所用全部连接代价的综合。如果根据已知条件无法完成该项任务，则请你返回 -1。
     * 原文链接：https://blog.csdn.net/qq_21201267/article/details/107796632
     */
    public int minimumCost(int N, int[][] connections) {
        UDS uds = new UDS(N);

        //将边的权值排序，小的先遍历
        Arrays.sort(connections, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });

        int edge = 0, p1, p2, dis, total = 0;
        for (int[] connection : connections) {
            p1 = connection[0];
            p2 = connection[1];
            dis = connection[2];
            if (uds.find(p1) != uds.find(p2)){
                //两个还未链接
                uds.union(p1, p2);
                edge++;
                total += dis;
            }
            if (edge == N - 1) break;
        }
        return edge == N - 1 ? total : -1;
    }

    public static void main(String[] args) {
        MinCost obj = new MinCost();
        int N = 4;
        int[][] a = new int[3][];
        a[0] = new int[]{1,2,5};
        a[1] = new int[]{1,3,6};
        a[2] = new int[]{2,3,1};
        System.out.println(obj.minimumCost(3,a));
    }
}
