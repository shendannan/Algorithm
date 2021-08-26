package B8_dp;

import B0_BasicDataStructure.TreeNode;

import java.util.*;

public class Rob {
    /**
     * 基本打家劫舍：不能抢连续两家，且首尾不相连
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    /**
     * 打家劫舍变体：不能抢连续两家，且首尾相连
     */
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] a1 = new int[nums.length-1];
        int[] a2 = new int[nums.length-1];
        System.arraycopy(nums,0,a1,0,nums.length-1);
        System.arraycopy(nums,1,a2,0,nums.length-1);
        return Math.max(rob1(a1), rob1(a2));
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
        int do_it = root.val + (root.left == null ? 0 : rob3(root.left.left) + rob3(root.left.right))
                + (root.right == null ? 0 : rob3(root.right.left) + rob3(root.right.right));
        // 不抢，然后去下家
        int not_do = rob3(root.left) + rob3(root.right);
        memo.put(root, Math.max(do_it, not_do));
        return memo.get(root);
    }

}
