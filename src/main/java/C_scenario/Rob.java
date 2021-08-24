package C_scenario;

import B0_BasicDataStructure.TreeNode;

import java.util.*;

public class Rob {
    /**
     * 基本打家劫舍：不能抢连续两家，且首尾不相连
     */
    public int rob1(int[] nums) {
        // 初始化备忘录 dp[start..] 能抢到的最大值
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        // 强盗从第 0 间房子开始抢劫
        return dp(nums, 0, memo);
    }

    private int dp(int[] nums, int start,int[] memo) {
        if (start >= nums.length) return 0;
        if (memo[start] != -1) return memo[start];
        int res = Math.max(dp(nums,start + 1,memo),
                nums[start] + dp(nums,start + 2,memo));
        memo[start] = res;
        return res;
    }

    /**
     * 打家劫舍变体：不能抢连续两家，且首尾相连
     */
    public int rob2(int[] nums) {
        int length = nums.length;
        //特殊情况：只有一家
        if(length == 1) return nums[0];
        int[] memo1 = new int[length-1];
        int[] memo2 = new int[length-1];
        Arrays.fill(memo1,-1);
        Arrays.fill(memo2,-1);
        int[] nums1 = Arrays.copyOfRange(nums,0,length-1);
        int[] nums2 = Arrays.copyOfRange(nums,1,length);
        return Math.max(dp(nums1,0,memo1),dp(nums2,0,memo2));
    }

    /**
     * 打家劫舍变体：不能抢连续两家，且二叉树排列
     */
    Map<TreeNode, Integer> memo = new HashMap<>();
    public int rob3(TreeNode root) {
        if (root == null) return 0;
        // 利用备忘录消除重叠子问题
        if (memo.containsKey(root))
            return memo.get(root);
        // 抢，然后去下下家
        int do_it = root.val
                + (root.left == null ?
                0 : rob3(root.left.left) + rob3(root.left.right))
                + (root.right == null ?
                0 : rob3(root.right.left) + rob3(root.right.right));
        // 不抢，然后去下家
        int not_do = rob3(root.left) + rob3(root.right);

        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }

}
