package B6_graph;

import B0_BasicDataStructure.UDS;

public class FindDisjointCircle {

    /**
     * 给定无向图，返回一条多余的边使其去掉后成为一棵树，返回二维数组中最后出现的多余边。
     * Input: [[1,2], [1,3], [2,3]]
     * Output: [2,3]
     * Explanation: The given undirected graph will be like this:
     *   1
     *  / \
     * 2 - 3
     * https://leetcode-cn.com/problems/redundant-connection/solution/rong-yu-lian-jie-by-leetcode-solution-pks2/
     */
    public int[] findRedundantConnection(int[][] edges) {
        //可以通过并查集寻找附加的边。初始时，每个节点都属于不同的连通分量。
        // 遍历每一条边，判断这条边连接的两个顶点是否属于相同的连通分量。
        UDS dsu = new UDS(edges.length);//N个节点，N条边（冗余1条）
        int[] res = new int[]{-1,-1};
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if (dsu.find(from) == dsu.find(to)) {
                // 如果两个顶点属于相同的连通分量，则说明在遍历到当前的边之前，
                // 这两个顶点之间已经连通，因此当前的边导致环出现，为附加的边。更新结果
                res[0] = from;
                res[1] = to;
            }else {
                // 如果两个顶点属于不同的连通分量，则说明在遍历到当前的边之前，
                // 这两个顶点之间不连通，因此当前的边不会导致环出现，合并这两个顶点的连通分量。
                dsu.union(from, to);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindDisjointCircle obj = new FindDisjointCircle();
        int[][] edges = new int[5][];
        edges[0] = new int[]{1,2};
        edges[1] = new int[]{2,3};
        edges[2] = new int[]{3,4};
        edges[3] = new int[]{1,4};
        edges[4] = new int[]{1,5};
        int[] res = obj.findRedundantConnection(edges);
        System.out.println(res[0] + " " + res[1]);
    }
}
