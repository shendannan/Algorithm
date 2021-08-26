package B2_array;

import java.util.Arrays;

/**
 * 最近的三数之和
 * 给定一个包括n个整数的数组nums和一个目标值target。
 * 找出nums中的三个整数，使得它们的和与target最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 *
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        //排序+双指针，左右逼近最接近的值
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            //优化1：如果当前数与上个数相等，则跳过这个数，因为上次循环已经枚举过一轮了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //核心：双指针，左右指针法，固定一个动两个指针
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int value = nums[i] + nums[start] + nums[end];
                //优化2：如果求和直接等于target，则直接返回
                if (value == target) {
                    return value;
                }
                //满足最接近，更新最小差值、答案。
                if (Math.abs(value - target) < min) {
                    min = Math.abs(value - target);
                    ans = value;
                }
                //如果求和大于target，则移动右指针，否则移动左指针。
                if (value > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return ans;
    }
}
