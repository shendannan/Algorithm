package B8_dp;


import java.util.Arrays;

/**
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。
 * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 *
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 *
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 */
public class PokeBalloons {
    public int[][] memo;
    public int[] val;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        //先添加两端的数字1，避免数组下标超限
        val = new int[n + 2];
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n + 1] = 1;
        //设置备忘录
        memo = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(memo[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            //转换为插入气球，反过来看
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            memo[left][right] = Math.max(memo[left][right], sum);
        }
        return memo[left][right];
    }
}
