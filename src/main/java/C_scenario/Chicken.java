package C_scenario;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * 农场养鸡问题
 * n个农场，第i个农场有a[i]只鸡，每天每个农场都会增加k只鸡。
 * 每晚农场主都会选择鸡最多的农场，将该农场鸡的个数除以2下取整，在m天后共剩下多少只鸡？
 * 输入：
 * n=3 m=3 k=100
 * a[3] = {100 200 400}
 * 输出：
 * 925
 * 解释：
 * (100 200 400)->(200 300 250)->(300 200 350)->(400 300 225)->400+300+225=925
 */
public class Chicken {
    /**
     *思路：
     *  同步增加就相当于不增加，到最后一起增加
     *步骤：
     *  1.每次取优先队列最大值max，然后减去（需要减去的值也就是）当前为i天则(i*k+max)/2上取整，最后将值放回优先队列中
     *  2.最终将优先队列中的值全相加再加上同步增加的值（m*n*k）
     */
    //n,m,k,a[i]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }

        //每次取优先队列最大值max，然后减去（需要减去的值也就是）当前为i天则(i*k+max)/2上取整，最后将值放回优先队列中
        for (int i = 1; i <= m; i++) {
            int tmp = queue.poll(); //获得没有更新的最大值
            int num = tmp + i * k;  //获得真实的最大值
            num -= num / 2;         //获得一半的上界
            tmp -= num;             //（没有更新的最大值）减去（需要减去的真实上界一半的值）
            queue.add(tmp);
        }

        //最终将优先队列中的值全相加再加上同步增加的值（m天*n个农场*每天增加k个）
        int result = m * n * k;
        for(int i: queue) {
            result += i;
        }
        System.out.println(result);
    }
}
