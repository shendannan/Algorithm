import java.util.Scanner;

import java.util.*;

public class Test02 {
    /**
     * 给你一个披萨，它由 3n 块不同大小的部分组成，现在你和你的朋友们需要按照如下规则来分披萨：
     * 你挑选 任意 一块披萨。
     * Alice 将会挑选你所选择的披萨逆时针方向的下一块披萨。
     * Bob 将会挑选你所选择的披萨顺时针方向的下一块披萨。
     * 重复上述过程直到没有披萨剩下。
     * 每一块披萨的大小按顺时针方向由循环数组 slices 表示。
     *
     * 请你返回你可以获得的披萨大小总和的最大值。
     *
     * 链接：https://leetcode-cn.com/problems/pizza-with-3n-slices
     * 输入：slices = [1,2,3,4,5,6]
     * 输出：10
     */
    public int maxSizeSlices(int[] nums) {
        //和打家劫舍2的区别在于，打家劫舍在长度为n的环形数组里，最多可以选择n/2个不相邻数字，并要求取出数字的和最大
        //而本题是在长度为3n的环形数组里，不多不少刚好取出n个不相邻数字，且要求取出数字的和最大
        int length = nums.length;
        int n = length /3;

        int[][] memo1 = new int[length-1][n];
        int[][] memo2 = new int[length-1][n];
        for(int[] i:memo1){
            Arrays.fill(i,-1);
        }
        for(int[] i:memo2){
            Arrays.fill(i,-1);
        }
        int[] nums1 = Arrays.copyOfRange(nums,0,length-1);
        int[] nums2 = Arrays.copyOfRange(nums,1,length);
        return Math.max(dp(0,0,nums1,memo1),dp(0,0,nums2,memo2));
    }

    //已经选了K个数
    public int dp(int start, int k, int[] nums, int[][] memo){
        if(start>nums.length-1) return 0;
        if(k == (nums.length+1) / 3) return 0;
        if(memo[start][k] != -1) return memo[start][k];
        int p = Math.max(nums[start] + dp(start+2,k+1,nums,memo), dp(start+1,k,nums,memo));
        memo[start][k] = p;
        return p;
    }
}
