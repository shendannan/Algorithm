package B2_array;

import java.util.*;

/**
 * 四数之和
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，
 * 使得a + b + c + d = target？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 *
 * 链接：https://leetcode-cn.com/problems/4sum
 */
public class FourSum {
    List<List<Integer>> ans = new ArrayList<>();
    LinkedList<Integer> curList = new LinkedList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //回溯法:主要看如何剪枝
        Arrays.sort(nums);
        if(nums.length<4) return ans;
        dfs(nums, target, 0, 0);
        return ans;
    }

    public void dfs(int[] nums, int target, int index, int sum) {
        if (curList.size() == 4) {
            if (sum == target) {
                ans.add(new ArrayList<>(curList));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            //剪枝1：后续数不足凑齐4个，直接返回
            if (nums.length - i < 4 - curList.size()) return;
            //剪枝2：从第二轮循环开始，如果数组中当前数字和前一个相同，则剪掉
            if (index != i && nums[i - 1] == nums[i]) continue;
            //剪枝3：如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组中当前数字的下一个数字 > target，则说明后面的数无论怎么选，加起来都一定大于 target，所以剪掉（递归返回）；
            if (i < nums.length - 1 && sum + nums[i] + (3 - curList.size()) * nums[i + 1] > target) return;
            //剪枝4：如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组最后一个数字 < target，则说明后面的数无论怎么选，加起来都一定小于 target，所以剪掉（进行下一次循环）。
            if (i < nums.length - 1 && sum + nums[i] + (3 - curList.size()) * nums[nums.length - 1] < target) continue;
            curList.add(nums[i]);
            dfs(nums, target, i + 1, sum+nums[i]);
            curList.removeLast();
        }
    }
}
