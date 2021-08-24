package C_scenario;
import java.util.*;

/**
 * 有n种水果，第i种水果的数量为a[i]，现在需要把水果分给m个人。
 * 分给每个人水果的数量必须是相等的，并且每个人只能选择一种水果。
 * 也就是说，可以把一种水果分给多个人，但是一个人不能有多种水果。
 * 问每个人最多能分到几个水果？
 * 输入：第一行有一个整数T，代表接下来有T组测试数据。
 * 接下来T组，每组第一行有两个整数n，m，第二行有n个整数a[i]，表示第i种水果的个数。
 * 1 <= m <= n <=1000000
 * 1 <= a[i] <= 10^9
 * 保证一个文件内n的总和不超过10^6
 * 输入示例
 * 2
 * 3 3
 * 2 3 4
 * 2 5
 * 2 4
 * 输出：对于每组数据输出一行，一个整数：每个人最多分到水果的数量。
 * 2
 * 1
 * https://blog.csdn.net/qq_36078715/article/details/105862925
 */
public class FruitsDistribution {

    //实现Comparator接口
    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            // 如果o1小于o2，我们就返回正值，如果o1大于o2我们就返回负值，
            // 这样颠倒一下，就可以实现降序排序了,反之即可自定义升序排序了
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // T组测试数据
        while (T > 0) {
            T--;
            int n = sc.nextInt(); // n种水果
            int m = sc.nextInt(); // m个人
            Integer[] arr = new Integer[n]; // 记录每种水果有多少个
            int[] count = new int[n]; // 记录每种水果有多少人选
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr, new MyComparator()); // 将水果的数量从大到小排列
            // 如果只有一个人，那他要最大的水果即可
            if (m == 1) {
                System.out.println(arr[0]);
                continue;
            }
            // 否则记下1个人的情况，用于2个人的取法
            int max = arr[0];
            count[0] = 1;
            int maxIndex = 0; // 递增的水果序列中，必然是从前往后拿的，maxIndex记录了最后取到的水果位置。
            // 动态规划，f(m) = max(g(m-1,i) | 0<=i<= maxIndex+1(或者n))
            for (int i = 1; i < m; i++) {
                int maxval = 0;
                int maxi = 0;
                int tmpIndex = maxIndex+1 < n ? maxIndex+1 : maxIndex; // 处理越界情况，如果越界了，就没有新的水果种类了
                for (int j = 0; j <= tmpIndex; j++) {
                    // 当前这个人i选j种水果可分水果的数量
                    int cur = arr[j] / (count[j] + 1);
                    if (cur > maxval) {
                        maxval = cur;
                        maxi = j;
                    }
                }
                // maxi是第i个人选定的水果种类
                // 如果是全新的水果，已有的水果种类指针后移一位，
                // 注意这种策略下，选择过的水果必然是连续的。
                if (maxi == maxIndex + 1) {
                    maxIndex += 1;
                }
                // 记录下第i个人的数据
                max = maxval;
                count[maxi] += 1;
            }
            System.out.println(max);
        }
    }
}

