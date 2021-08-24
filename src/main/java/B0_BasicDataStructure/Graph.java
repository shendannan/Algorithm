package B0_BasicDataStructure;

import java.util.*;

public class Graph {
    public static class DirectedEdge {
        private int v;  //有向边中的指出顶点
        private int w;  //有向边的指向顶点
        private double weight;  //有向边的权重

        //初始化加权有向边
        public DirectedEdge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public double weight() {
            return weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public String toString() {
            return String.format("%d->%d  %.2f", v, w, weight);
        }
    }

    //有向图
    public static class Digraph {
        public int V;//顶点数目
        public int E;//边的数目
        private List<List<Integer>> adj;//邻接表

        public Digraph(int V){
            this.V = V;
            this.E = 0;
            this.adj = new ArrayList<>(V);//初始化邻接表
            for (int i = 0;i < V;i++) {
                adj.add(new ArrayList<>());
            }
        }

        //向有向图中添加一条边 v->w
        public void addEdge(int v, int w) {
            //只需要让顶点w出现在顶点v的邻接表中，因为边是有方向的
            adj.get(v).add(w);
            E++;
        }

        //获取由v指出的边所连接的所有顶点
        public List<Integer> adj(int v){
            return adj.get(v);
        }

        //扩展：该图的反向图
        private Digraph reverse(){
            //创建有向图对象
            Digraph r = new Digraph(V);
            for (int v=0; v<V; v++){
                //获取由该顶点v指出的所有边
                for (Integer w : adj.get(v)) {//原图中表示的是由顶点v->w的边
                    r.addEdge(w,v);//w->v
                }
            }
            return r;
        }
    }
}




