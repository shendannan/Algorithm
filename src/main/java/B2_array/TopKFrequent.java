package B2_array;
import java.util.*;
/**
 * 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 提示：给定的k总是合理的，且 1≤k≤数组中不相同的元素的个数。
 * 数组中前k个高频元素的集合是唯一的。可以按任意顺序返回答案。
 *
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 */
public class TopKFrequent {

    public static List<Integer> topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(b) - map.get(a);
            }
        });
        pq.addAll(map.keySet());
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        for(int i = 0;i < k;i++){
            res.add(pq.remove());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,3,3,3,5,5,5,5,5};
        List<Integer> res = topKFrequent(nums,2);
        for(int i:res){
            System.out.println(i);
        }
    }
}
