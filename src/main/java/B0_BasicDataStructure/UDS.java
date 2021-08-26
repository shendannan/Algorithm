package B0_BasicDataStructure;

public class UDS {
    //并查集：每次查询一个结点时，找到它的最新的前驱结点并更新为当前结点的值（即合并）。
    // 标准模板工具类：
    public int[] parent;
    public int[] suger;//可选：糖果数
    public int[] people;//可选：人数
    //新建并查集，初始化为每个节点一个单独的集合
    public UDS(int N){
        parent = new int[N+1];
        for(int i = 1;i <= N;i++){
            parent[i] = i;
        }
    }

    public UDS(int N, int[] candy){
        parent = new int[N+1];
        suger = new int[N+1];
        people = new int[N+1];
        for(int i = 1;i <= N;i++){
            parent[i] = i;
            people[i] = 1;
        }
        System.arraycopy(candy, 0, suger, 1, candy.length);
    }

    public int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x,int y){
        parent[find(x)] = find(y);
        //由于每个集里有他们的属性，在合并时，需要连属性一起合并。
        //合并后的代表即对应索引和值相等的那一项，所有合并后的属性也在代表里。
        //典型例题：牛牛吃糖果
        suger[find(x)] += suger[y];
        people[find(x)] += people[y];
    }

    //常用UDS功能
    //1.判断是否是组的代表(或者计算共有几组) if(i == find(i))
    //2.判断是否是一组 if(find(i)==find(j))
}
