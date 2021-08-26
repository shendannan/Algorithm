package B8_dp;

/**
 * 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class StockSell {
    //case1: 只能进行1次买卖
    public int maxProfit1(int[] prices) {
        //动态规划 f(x)为手头的现金数
        //dp[0]代表手头没股 两种情况：昨天没股 今天照旧；昨天有股 今天卖了
        //dp[1]代表手头有股 两种情况：昨天有股 今天照旧；昨天没股 今天买了
        if(prices.length<2) return 0;
        int[] dp = new int[2];
        //初始时，第一天如果没股则现金为0，否则买了股份现金为0-prices[0]
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int price : prices) {
            dp[0] = Math.max(dp[0], dp[1] + price);
            //注意，由于只能限制一次交易，因此dp[1]每次更新都是从现金0开始买入，更新为-prices[i]
            dp[1] = Math.max(dp[1], -price);
        }
        //最终手头没股
        return dp[0];
    }

    //case2：不限买卖次数
    public int maxProfit2(int[] prices) {
        //动态规划 f(x)为手头的现金数
        //dp[0]代表手头没股 两种情况：昨天没股 今天照旧；昨天有股 今天卖了
        //dp[1]代表手头有股 两种情况：昨天有股 今天照旧；昨天没股 今天买了
        if(prices.length<2) return 0;
        int[] dp = new int[2];
        //初始时，第一天如果没股则现金为0，否则买了股份现金为0-prices[0]
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int price : prices) {
            dp[0] = Math.max(dp[0], dp[1] + price);
            //注意，由于不限制交易次数，因此dp[1]每次更新都是从当前为持股的现金数量开始买入，除了下一行，其余代码与仅限买入一次完全相同
            dp[1] = Math.max(dp[1], dp[0] - price);
        }
        //最终手头没股
        return dp[0];
    }

    //case3: 只能进行 2 次买卖
    public int maxProfit3(int[] prices) {
        //动态规划 f(x)为手头的现金数
        //dp[i][j] i=0 or 1 or 2 代表已经开始了几个交易，当买入时变化 j=0 or 1 代表手头没股或有股
        //dp[][0]代表手头没股 两种情况：昨天没股 今天照旧；昨天有股 今天卖了
        //dp[][1]代表手头有股 两种情况：昨天有股 今天照旧；昨天没股 今天买了
        if (prices.length < 2)  return 0;
        int[][] dp = new int[3][2];
        //关键！！初始值怎么设定，初始值即第一天结束后可能的情况：
        // dp[1][0] = 0：表示发生了 1 次交易，但是不持股，这是不可能的。可以假设第一次交易是无效的，利润为0。
        // dp[1][1] = -prices[0]：表示发生了一次交易，并且持股，所以我们持有的现金数就是当天股价的相反数；
        // dp[2][0] = 0：表示发生了 2 次交易，但是不持股，这是不可能的。可以假设为0不影响求最大值；
        // dp[2][1] = 负无穷：表示发生了 2 次交易，并且持股，这是不可能的。注意：虽然没有意义，但是不能设置成 0，因为第一次成交后可能现金为负。
        // 总结：任何还没发生的交易，且还规定了当天必须持股的状态值应该设置为负无穷
        dp[1][1] = -prices[0];
        dp[2][1] = Integer.MIN_VALUE;
        for (int price : prices) {
            //第一次买入，从现金0开始买入，更新为-prices[i]
            dp[1][1] = Math.max(dp[1][1], dp[0][0] - price);
            //第一次卖出
            dp[1][0] = Math.max(dp[1][0], dp[1][1] + price);
            //第二次买入
            dp[2][1] = Math.max(dp[2][1], dp[1][0] - price);
            //第二次卖出
            dp[2][0] = Math.max(dp[2][0], dp[2][1] + price);
        }
        //最后可能只进行一次交易的利润是最大的
        return Math.max(dp[1][0], dp[2][0]);
    }

    //case4: 只能进行 k 次买卖（难）
    public int maxProfit4(int k, int[] prices) {
        if (k == 0 || prices.length < 2) return 0;
        int[][] dp = new int[k + 1][2];
        //总结：任何还没发生的交易，且还规定了当天必须持股的状态值应该设置为负无穷
        for (int i = 0; i <= k; i++) {
            dp[i][1] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            for (int j = 1; j <= k; j++) {
                dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - price);
                dp[j][0] = Math.max(dp[j][0], dp[j][1] + price);
            }
        }
        return dp[k][0];
    }

    //case5: 不限制买卖次数但有1天冷却期（难）
    public int maxProfit5(int[] prices) {
        if (prices.length < 2) return 0;
        int[] dp = new int[3];

        // dp[0]: 手上不持有股票，并且今天是由于冷却期而不持股，我们拥有的现金数
        // dp[1]: 手上持有股票时，我们拥有的现金数
        // dp[2]: 手上不持有股票，并且今天是由于卖出股票而不持股，我们拥有的现金数
        dp[1] = -prices[0];

        int pre0 = dp[0];
        int pre2 = dp[2];

        //dp[0] 两种情况：昨天没买(dp[0]) 今天照旧；昨天刚卖(pre2) 今天冷却
        //dp[1] 两种情况：昨天买了(dp[1]) 今天不卖；昨天没股 今天买了(pre0 - price)
        //dp[2] 一种情况: 昨天卖了(dp[1]+price) 今天冷却
        for (int price : prices) {
            dp[0] = Math.max(dp[0], pre2);
            dp[1] = Math.max(dp[1], pre0 - price);
            dp[2] = dp[1] + price;

            pre0 = dp[0];
            pre2 = dp[2];
        }
        return Math.max(dp[0], dp[2]);
    }

    //case6: 不限制买卖次数但有手续费
    public int maxProfit6(int[] prices, int fee) {
        //动态规划 f(x)为手头的现金数
        //dp[0]代表手头没股 两种情况：昨天没股 今天照旧；昨天有股 今天卖了
        //dp[1]代表手头有股 两种情况：昨天有股 今天照旧；昨天没股 今天买了
        if(prices.length<2) return 0;
        int[] dp = new int[2];
        //初始时，第一天如果没股则现金为0，否则买了股份现金为0-prices[0]-fee
        dp[1] = -prices[0] - fee;
        for (int price : prices) {
            dp[0] = Math.max(dp[0], dp[1] + price);
            //注意，由于只能限制一次交易，因此dp[1]每次更新都是从现金0开始买入，更新为-prices[i]
            dp[1] = Math.max(dp[1], dp[0] - price - fee);
        }
        //最终手头没股
        return dp[0];
    }
}
