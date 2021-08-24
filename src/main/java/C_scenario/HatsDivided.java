package C_scenario;

import java.util.*;

/**
 * 总共有 n个人和40 种不同的帽子，帽子编号从 1 到 40 。
 * 给你一个整数列表的列表 hats ，其中 hats[i] 是第 i 个人所有喜欢帽子的列表。
 * 请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。
 * 由于答案可能很大，请返回它对 10^9 + 7 取余后的结果。
 *
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-wear-different-hats-to-each-other
 */
public class HatsDivided {
    //回溯解法：超时
    public int numberWays(List<List<Integer>> hats) {
        Set<Integer> choosed = new HashSet<>();
        return dfs(0,choosed,hats);
    }
    // [0, i) 这些人都已经有了帽子。返回值是给接下来的人选帽子的方案数。
    public int dfs(int i,Set<Integer> choosed, List<List<Integer>> hats){
        int n = hats.size();//总人数
        if(i == n){// 已经给所有人选了帽子
            return 1;
        }
        int count = 0;
        // 给第 i 个人选一顶帽子，不能选已经被用了的帽子。
        for (int hat : hats.get(i)) {
            if (!choosed.contains(hat)) {
                // 可以把 hat 给第 i 个人
                choosed.add(hat);
                count = (count + dfs(i + 1,choosed,hats)) % 1000000007;
                // 回溯，把 hat 拿回来
                choosed.remove(hat);
            }
        }
        return count;
    }

    //动规解法：看不懂
    public int numberWays1(List<List<Integer>> hats) {
        final int MOD = 1000000007;
        int n = hats.size();
        // 找到帽子编号的最大值，这样我们只需要求出 f[maxhatid][2^n - 1] 作为答案
        int maxHatId = 0;
        for (int i = 0; i < n; ++i) {
            List<Integer> list = hats.get(i);
            for (int h: list) {
                maxHatId = Math.max(maxHatId, h);
            }
        }

        // 对于每一顶帽子 h，hatToPerson[h] 中存储了喜欢这顶帽子的所有人，方便进行动态规划
        List<List<Integer>> hatToPerson = new ArrayList<List<Integer>>();
        for (int i = 0; i <= maxHatId; i++) {
            hatToPerson.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; ++i) {
            List<Integer> list = hats.get(i);
            for (int h: list) {
                hatToPerson.get(h).add(i);
            }
        }

        int[][] f = new int[maxHatId + 1][1 << n];
        // 边界条件
        f[0][0] = 1;
        for (int i = 1; i <= maxHatId; ++i) {
            for (int mask = 0; mask < (1 << n); ++mask) {
                f[i][mask] = f[i - 1][mask];
                List<Integer> list = hatToPerson.get(i);
                for (int j: list) {
                    if ((mask & (1 << j)) != 0) {
                        f[i][mask] += f[i - 1][mask ^ (1 << j)];
                        f[i][mask] %= MOD;
                    }
                }
            }
        }

        return f[maxHatId][(1 << n) - 1];
    }
}
