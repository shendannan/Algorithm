package B2_array;

import java.util.*;

/**
 * 四数之和
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，
 * 使得 a + b + c + d = target？找出所有满足条件且不重复的四元组。
 * 注意：答案中不可以包含重复的四元组。
 *
 * 链接：https://leetcode-cn.com/problems/4sum
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //回溯法:主要看如何剪枝
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        if(nums.length<4) return ans;
        dfs(ans, new LinkedList<Integer>(), nums, target, 0, 0);
        return ans;
    }

    public void dfs(List<List<Integer>> ans,LinkedList<Integer> curList, int[] nums, int target, int index, int cur) {
        if (curList.size() == 4) {
            if (cur == target) {
                ans.add(new ArrayList<>(curList));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            //剪枝1：后续数不足凑齐4个，直接返回
            if (nums.length - i < 4 - curList.size()) return;
            //剪枝2：从第二轮循环开始，如果数组中当前数字和前一个相同，则剪掉
            if (index != i && nums[i - 1] == nums[i]) continue;
            //剪枝3：如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组中当前数字的下一个数字 > target当前数字+已确定数字的和+(n−1)∗排序后数组中当前数字的下一个数字>target，则说明后面的数无论怎么选，加起来都一定大于 targettarget，所以剪掉（递归返回）；
            if (i < nums.length - 1 && cur + nums[i] + (3 - curList.size()) * nums[i + 1] > target) return;
            //剪枝4：如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组最后一个数字 < target当前数字+已确定数字的和+(n−1)∗排序后数组最后一个数字<target，则说明后面的数无论怎么选，加起来都一定小于 targettarget，所以剪掉（进行下一次循环）。
            if (i < nums.length - 1 && cur + nums[i] + (3 - curList.size()) * nums[nums.length - 1] < target) continue;
            cur += nums[i];
            curList.add(nums[i]);
            dfs(ans, curList, nums, target, i + 1, cur);
            curList.removeLast();
            cur -= nums[i];
        }
    }
}
