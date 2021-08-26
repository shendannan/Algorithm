package B2_array;

import java.util.Arrays;

/**
 * 最长上升子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LengthOfLIS {
    public static int lengthOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        // dp记录每一个位置的上升子序列的最长的长度，最少为1
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0,3,1,6,2,2,7}));
    }
}
