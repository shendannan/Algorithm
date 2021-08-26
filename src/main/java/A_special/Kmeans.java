package A_special;

public class Kmeans {

    public static void main(String[] args) {
        int[] p = {1,4,8,10,20};
        int k = 3;
        int[][] g;
        g = cluster(p, k);
        for (int[] ints : g) {
            for (int anInt : ints) {
                System.out.print(anInt);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
     * 聚类函数主体。
     * 针对一维 double 数组。指定聚类数目 k。
     * 将数据聚成 k 类。
     */
    public static int[][] cluster(int[] p, int k) {
        // 存放聚类旧的聚类中心
        int[] c = new int[k];
        // 存放新计算的聚类中心
        int[] nc = new int[k];
        // 存放放回结果
        int[][] g;
        // 初始化聚类中心
        // 经典方法是随机选取 k 个
        // 本例中采用前 k 个作为聚类中心
        // 聚类中心的选取不影响最终结果
        if (k >= 0) System.arraycopy(p, 0, c, 0, k);
        // 循环聚类，更新聚类中心
        // 到聚类中心不变为止
        while (true) {
            // 根据聚类中心将元素分类
            g = group(p, c);
            // 计算分类后的聚类中心
            for (int i = 0; i < g.length; i++) {
                nc[i] = sum(g[i]) / g[i].length;
            }
            // 如果聚类中心不同
            if (!equal(nc, c)) {
                // 为下一次聚类准备
                c = nc;
                nc = new int[k];
            } else // 聚类结束
                break;
        }
        // 返回聚类结果
        return g;
    }
    /*
     * 给定数组 p 和聚类中心 c。
     * 根据 c 将 p 中元素聚类。返回二维数组。
     * 存放各组元素。
     */
    public static int[][] group(int[] p, int[] c) {
        // 中间变量，用来分组标记
        int[] gi = new int[p.length];
        // 考察每一个元素 pi 同聚类中心 cj 的距离
        // pi 与 cj 的距离最小则归为 j 类
        for (int i = 0; i < p.length; i++) {
            // 存放距离
            int[] d = new int[c.length];
            // 计算到每个聚类中心的距离
            for (int j = 0; j < c.length; j++) {
                d[j] = distance(p[i], c[j]);
            }
            // 找出最小距离，返回最小值的下标
            int ci = min(d);
            // 标记属于哪一组
            gi[i] = ci;
        }
        // 存放分组结果
        int[][] g = new int[c.length][];
        // 遍历每个聚类中心，分组
        for (int i = 0; i < c.length; i++) {
            // 中间变量，记录聚类后每一组的大小
            int s = 0;
            // 计算每一组的长度
            for (int value : gi)
                if (value == i) s++;
            // 存储每一组的成员
            g[i] = new int[s];
            s = 0;
            // 根据分组标记将各元素归位
            for (int j = 0; j < gi.length; j++)
                if (gi[j] == i) {
                    g[i][s] = p[j];
                    s++;
                }
        }
        // 返回分组结果
        return g;
    }

    /*
     * 计算两个点之间的距离， 这里采用最简单得一维欧氏距离， 可扩展。
     */
    public static int distance(int x, int y) {
        return Math.abs(x - y);
    }

    /*
     * 返回给定数组各元素之和。
     */
    public static int sum(int[] p) {
        int sum = 0;
        for (int value : p) sum += value;
        return sum;
    }

    /*
     * 返回最小值得下标。
     */
    public static int min(int[] p) {
        int i = 0;
        int m = p[0];
        for (int j = 1; j < p.length; j++) {
            if (p[j] < m) {
                i = j;
                m = p[j];
            }
        }
        return i;
    }

    /*
     * 判断两个数组是否相等。 长度一样且对应位置值相同返回真。
     */
    public static boolean equal(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        else {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i])
                    return false;
            }
        }
        return true;
    }
}