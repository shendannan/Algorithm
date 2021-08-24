package B2_array;
import java.util.*;

/**
 * 组合总和
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的数字可以无限制重复被选取。
 *
 * 说明：所有数字（包括target）都是正整数。解集不能包含重复的组合。 
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, res, 0, new ArrayList<>());
        return res;
    }

    public void backtrack(int[] candidates, int target, List<List<Integer>> res, int start, ArrayList<Integer> tmp_list) {
        if (target < 0) return;//目标错误，回溯
        if (target == 0) {//目标正确，添加结果
            res.add(new ArrayList<>(tmp_list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            tmp_list.add(candidates[i]);//做选择，可重复
            backtrack(candidates, target - candidates[i], res, i, tmp_list);
            tmp_list.remove(tmp_list.size() - 1);//移除选择
        }
    }

    /**
     * 组合总和变体：
     * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合的个数。
     * candidates中的数字可以无限制重复被选取。
     *
     * nums = [1, 2, 3]
     * target = 4
     * return 7
     * 说明：所有数字（包括target）都是正整数。顺序不同的序列如（1,3）（3,1）将视为不同组合
     * 链接：https://leetcode-cn.com/problems/combination-sum-iv/
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // 这个值被其它状态参考，设置为 1 是合理的
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] = dp[i] + dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
