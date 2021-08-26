package B6_graph;

import java.util.*;


public class ShortestPath {
    static class Node {
        int busID;
        int transferCount;

        public Node(int x, int y) {
            this.busID = x;
            this.transferCount = y;
        }
    }
    /**
     * 公交换乘的最少次数
     * 本题与最短路径无关，主要用到建图和BFS
     * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
     * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ...这样的车站路线行驶。
     * 现在从 S 车站出发（初始时不在公交车上），要前往 T 车站。 期间仅可乘坐公交车。
     * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
     * <p>
     * 链接：https://leetcode-cn.com/problems/bus-routes
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        int m = routes.length; //共有m辆公交车
        //图的构建：将一辆公交车（包括它完整的行驶路线）抽象成图中的节点
        //arr代表图，其中的每一个List代表当前结点相邻的其他节点，即路线相交的其他车
        List<List<Integer>> arr = new ArrayList<>();
        for (int[] route : routes) {
            Arrays.sort(route);
            arr.add(new ArrayList<>());
        }
        //创建图的邻接关系，注意是无向图，因此两个结点的邻接List都要互相放
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                if (intersect(routes[i], routes[j])) {
                    arr.get(i).add(j);
                    arr.get(j).add(i);
                }
            }
        }
        //图建立好后，开始着手BFS，由于求最短换乘，因此要记录车辆是否已乘坐过（即是否入队过）
        Set<Integer> visited = new HashSet<>();//起始站入队，即所有经过起点的公交车编号
        Set<Integer> target = new HashSet<>();//含有终点站的公交车编号
        Queue<Node> queue = new ArrayDeque<>();//BFS核心数据结构，这里需要记录第几辆车和转车次数
        for (int i = 0; i < m; i++) {
            //这里查找用API自带的二分查找降低复杂度
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                visited.add(i);//存放始发站
                queue.offer(new Node(i, 0));//存放第几辆车和转车次数
            }
            if (Arrays.binarySearch(routes[i], T) >= 0) {
                target.add(i);//存放终点站
            }
        }
        while (!queue.isEmpty()) {
            Node info = queue.poll();
            int busID = info.busID, depth = info.transferCount;//x为第几辆车,y是转车的次数
            if (target.contains(busID)) return depth + 1;
            //图的邻接节点入队，即可以转乘的公交车入队
            for (int nei : arr.get(busID)) {
                if (!visited.contains(nei)) {
                    visited.add(nei);
                    queue.offer(new Node(nei, depth + 1));
                }
            }
        }
        return -1;
    }

    public boolean intersect(int[] a, int[] b) {
        //由于先前排过序，因此这里直接通过对比有无相同站点来判断两个车是否路线相交
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                return true;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }

    public int shortestPath(int[][] routes, int S, int T) {
        if (S == T) return 0;
        Map<Integer, Set<Integer>> adj = new HashMap<>();//邻接表
        for (int[] route : routes) {
            for(int i = 0;i<route.length-1; i++){
                Set<Integer> adjStation = adj.getOrDefault(route[i],new HashSet<>());
                adjStation.add(route[i+1]);
                adj.put(route[i],adjStation);
            }
            //最后一个站点的下一个站点是起始站点
            Set<Integer> adjStation = adj.getOrDefault(route[route.length-1],new HashSet<>());
            adjStation.add(route[0]);
            adj.put(route[route.length-1],adjStation);
        }

        //图建立好后，开始着手BFS，由于求最短距离，因此要记录车站是否曾经过（即是否入队过）
        Set<Integer> visited = new HashSet<>();//起始站入队
        Queue<Integer> queue = new ArrayDeque<>();//BFS核心数据结构
        visited.add(S);
        queue.offer(S);
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0;i < size;i++){
                int curStation = queue.poll();
                if (curStation == T) return distance;
                for (int nei : adj.get(curStation)) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        queue.offer(nei);
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] routes = new int[3][];
        routes[0] = new int[]{1,2,7};
        routes[1] = new int[]{3,6,7};
        routes[2] = new int[]{2,3,4,6};
//        routes[3] = new int[]{15,19};
//        routes[4] = new int[]{9,12,13};
        ShortestPath obj = new ShortestPath();
        int S = 1,T = 6;
        System.out.println(obj.numBusesToDestination(routes,S,T));
        System.out.println(obj.shortestPath(routes,S,T));
    }
}
