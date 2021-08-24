package C_scenario;
import B0_BasicDataStructure.UDS;

import java.util.*;
/**
 * 有n个牛牛一起去朋友家吃糖果，第i个牛牛一定要吃ai块糖果。
 * 而朋友家一共只有m块糖果，可能不会满足所有的牛牛都吃上糖果。
 * 同时牛牛们有k个约定，每一个约定为一个牛牛的编号对(i, j)，
 * 表示第i个和第j个牛牛是好朋友，他俩要么一起都吃到糖果，要么一起都不吃。
 * 保证每个牛牛最多只出现在一个编号对中。您可以安排让一些牛牛吃糖果，一些牛牛不吃。
 * 要求是能吃上糖果的牛牛数量最多（吃掉的糖果总量要小于等于m），并要满足不违反牛牛们的k个约定。
 * 输入描述
 *
 * 第一行2个正整数n、m，1<=n<=10^3   1<=m<=10^3。
 * 第二行n个正整数a1，a2，……，an，1<=ai<=10^6
 * 第三行1个正整数k，0 <= k <= n/2
 * 接下来k行，每行两个正整数i，j，表示第i个牛牛与第j个牛牛有约定。
 *
 * 输出描述: 一行一个数字表示最多能吃上糖果的牛牛个数
 *
 * 输入
 * 3 10
 * 5 1 5
 * 1
 * 1 3
 * 输出
 * 2
 * 原文链接：https://blog.csdn.net/qq_36078715/article/details/105862925
 */
public class Candy {
    public static void main(String[] args) {
        //STEP1: 使用并查集将牛牛分成若干组，分组后，一个组内的牛牛要么都吃，要么都不吃，一次问题简化成了
        //有若干群人，分配资源，求能够满足资源最大化的人数。
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n个牛牛
        int m = sc.nextInt(); // m个糖果
        int[] suger = new int[n];
        // 初始化，suger[i]代表以位置i为根节点的树一共需要多少糖
        for (int i = 0; i < n; i++) {
            suger[i] = sc.nextInt();
        }
        UDS uds = new UDS(n,suger);
        int k = sc.nextInt(); // k个约定
        for (int i = 0; i < k; i++) {
            uds.union(sc.nextInt(), sc.nextInt());
        }
        // 已经使用并查集将牛牛分类了，现在遍历一遍，如果某个牛牛i是自己的根节点，就取出来。
        // 实际上这样的牛牛是一个组的根（代表）,
        // 它的suger代表它所在的组的所需糖果总数, people代表这组有多少人
        ArrayList<Integer> arrSuger = new ArrayList<>();
        ArrayList<Integer> arrPeople = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == uds.find(i)) {
                //找到新的一组，记录组内需要的糖果总数和总人数
                arrSuger.add(uds.suger[i]);
                arrPeople.add(uds.people[i]);
            }
        }

        //STEP2: 使用动态规划求解能吃上糖果的牛牛数量最多的最优解(0-1背包问题)
        // arrSuger的值代表每一组需要的糖果数，arrPeople代表每一组有多少人
        int len = arrSuger.size();//一共有这么多组牛牛（互不相交）
        // arr[组数][糖果数] = 能吃上糖的总人数，初始化都为0
        int[][] arr = new int[len + 1][m + 1];
        // 开始规划
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Math.max(arrPeople.get(i - 1) + //注意i-1才是当前这组
                                arr[i - 1][Math.max(0, j - arrSuger.get(i - 1))],
                        arr[i - 1][j]);
            }
        }
        System.out.println(arr[len][m]);
    }
}
