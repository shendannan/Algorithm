package B7_Deque;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 单调队列
 * 典型例题：
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class MonotonousQueue {
    static class monotonousQueue{
        private Deque<Integer> data;
        public monotonousQueue(){
            data = new ArrayDeque<>();
        }
        public void push(int n){
            //队尾插入前把前面比新元素小的都删掉
            while(!data.isEmpty() && data.peekLast() < n){
                data.pollLast();
            }
            data.offerLast(n);
        }
        //返回单调队列的最大值
        public int max(){
            return data.peekFirst();
        }
        //队头删除值为n的元素，因为可能需要删除的值已经在插入时被删除了
        public void pop(int n){
            if(!data.isEmpty() && data.peekFirst() == n){
                data.pollFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k){
        monotonousQueue window = new monotonousQueue();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i<nums.length; i++){
            if(i < k - 1){
                //先填满窗口的前k-1
                window.push(nums[i]);
            }else{
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i-k+1]);
            }
        }
        int[] ans = new int[res.size()];
        for(int i = 0;i<res.size();i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
}
