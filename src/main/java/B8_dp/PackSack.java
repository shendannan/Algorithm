package B8_dp;

public class PackSack {
    /** 0-1背包问题
     * 给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。
     * 其中第i个物品的重量为wt[i]，价值为val[i]，
     * 现在让你用这个背包装物品，最多能装的价值是多少？
     *
     * 例子，输入如下：
     * N = 3, W = 4
     * wt = [2, 1, 3]
     * val = [4, 2, 3]
     * 算法返回 6，选择前两件物品装进背包，总重量 3 小于W，可以获得最大价值 6。
     */
    public int knapsack_01(int W, int N, int[] wt, int[] val) {
        //base case 默认初始化为0
        //dp[所放物品][背包容量] = 最大价值
        int[][] dp = new int[N+1][W+1];
        for (int i = 1; i <= N; i++) {//从第一个物品开始选
            for (int w = 1; w <= W; w++) {//为什么w从1开始？是因为背包不一定装满时的价值最大
                if (w - wt[i - 1] < 0) {
                    // 当前背包容量装不下，只能选择不装入背包
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w - wt[i-1]]+val[i-1],//装入
                            dp[i - 1][w] //不装入背包
                    );
                }
            }
        }
        return dp[N][W];
    }

    public int knapsack_01_1(int W, int N, int[] wt, int[] val) {
        //base case 默认初始化为0
        //dp[背包容量] = 最大价值
        int[] dp = new int[W+1];
        for (int i = 0; i < N; i++) {//先物品后背包
            for (int w = W; w >=wt[i]; w--) {//01背包内层倒序遍历
                dp[w] = Math.max(
                        dp[w - wt[i]]+val[i],//装入
                        dp[w] //不装入背包
                );
            }
        }
        return dp[W];
    }

    /** 0-1背包问题变体：子集背包问题（相等子集分割）
     * 给你一个可装载重量为sum的背包和N个物品，每个物品有重量和价值两个属性。
     * 其中第i个物品的重量为wt[i]，价值为val[i]，
     * 是否存在一种装法可以恰好装满背包？
     *
     * 例子，输入如下：
     * nums = [1,5, 11, 5] sum = 11
     * 返回true;
     */
    public boolean canPartition(int[] nums, int sum) {
        //base case 默认初始化为0
        //dp[背包容量] = 最大价值
        int[] dp = new int[sum+1];
        for (int i = 0; i < nums.length; i++) {//先物品后背包
            for (int w = sum; w >=nums[i]; w--) {//01背包内层倒序遍历
                dp[w] = Math.max(
                        dp[w - nums[i]]+nums[i],//装入
                        dp[w] //不装入背包
                );
            }
        }
        return dp[sum] == sum;
    }

    /** 0-1背包问题变体：完全背包问题（物品可重复使用，典型例题零钱兑换）
     * 给你一个可装载重量为amount的背包和无数个物品，每个物品的重量是nums[i]。
     * 问有多少种装法可以恰好装满背包？
     *
     * 例子，输入如下：
     * amount = 5，nums = [1,2,5]
     * 返回 4 ，因为{5，（2+2+1），（2+1+1+1），（1+1+1+1+1）}
     */
    public int coinChange(int[] coins, int amount){
        //dp[背包容量] = 最大凑法
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {//先物品后背包
            for (int j = coins[i]; j <= amount; j++) {//完全背包内层顺序遍历
                dp[j] += dp[j - coins[i]];

            }
        }
        return dp[amount];//返回利用n个（全部）硬币凑成amount数量的最大凑法
    }

    /** 0-1背包问题变体：
     * 给你一个可装载重量为n的背包和一些物品，每个物品有重量和价值两个属性。
     * 其中第i个物品的重量为group[i]，价值为profit[i]，
     * 现在让你用这个背包装物品，总价值超过minProfit，有几种可行的方案？
     *
     * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
     * 输出：2
     * 至少产生 3 的利润，该集团可以完成工作0和工作1，或仅完成工作1。总的来说，有两种计划。
     */
    public int profitableSchemes(int n, int minProfit, int[] weight, int[] profit) {
        //base case 默认初始化为0
        int MOD = 1000000007;
        //dp[背包容量][最大收益] = 方案数
        int[][] dp = new int[n+1][minProfit+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= weight.length; i++) {//先物品后背包
            for (int j = n; j >= weight[i-1]; j--) {//背包 内层倒序遍历
                for(int k = minProfit;k >= 0;k--){//状态2：收益
                    dp[j][k] = dp[j][k] + dp[j-weight[i-1]][Math.max(k-profit[i-1],0)];//选择要么不选这个工作，要么选
                    dp[j][k] = dp[j][k] % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }

    public static void main(String[] args) {
        PackSack obj = new PackSack();
        System.out.println(obj.knapsack_01(4,3,new int[]{2,1,3},new int[]{4,2,3}));
        System.out.println(obj.knapsack_01_1(4,3,new int[]{2,1,3},new int[]{4,2,3}));
        System.out.println(obj.canPartition(new int[]{1,2,3,5},6));
        System.out.println(obj.coinChange(new int[]{1,2,5},5));
        System.out.println(obj.profitableSchemes(5,3,new int[]{2,2}, new int[]{2,3}));
    }
}
