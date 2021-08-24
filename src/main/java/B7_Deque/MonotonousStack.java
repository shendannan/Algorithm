package B7_Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonousStack {
    /**
     * 单调栈
     * 典型例题：给一个数组，返回一个相同长度的数组，值为当前位置对应的下一个最大元素（值或索引）
     * 没有更大的则赋值-1
     *
     * 扩展：如果是环状数组则将原始数组翻倍进行处理。
     */
    public static int[] monotonousStack(int[] nums){
        int[] res = new int[nums.length];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = nums.length - 1; i >= 0; i--){
            while(!q.isEmpty() && q.peek() <= nums[i]){
                q.pop();
            }
            res[i] = q.isEmpty()?-1:q.peek();
            //如果是返回更大的位置到当前位置的距离则用下面这句
//            res[i] = q.isEmpty()?-1:(q.peek()-i);
            q.push(nums[i]);
        }
        return res;
    }

    /**
     * 单调栈
     * 典型例题：一横排高楼，返回一个新数组，在当前高楼能左右看到的楼数。（不算本身这座楼）
     *（当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
     * 输入[5,3,8,3,2,5]，输出[2,2,4,3,3,3]
     */
    public static int[] findBuilding(int[] heights) {
        int[] res = new int[heights.length];
        Deque<Integer> q1 = new ArrayDeque<>();
        //从右向左看
        for(int i = heights.length-1; i>0 ; i--){
            while(!q1.isEmpty() && q1.peek() <= heights[i]){//栈底最小
                q1.pop();
            }
            q1.push(heights[i]);
            res[i-1] += q1.size();
        }
        Deque<Integer> q2 = new ArrayDeque<>();
        //从左向右看
        for(int i=0;i<heights.length-1;i++){
            while(!q2.isEmpty() && q2.peek() <= heights[i]){
                q2.pop();
            }
            q2.push(heights[i]);
            res[i+1]+=q2.size();
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] test = {2,1,2,4,3};
//        test = monotonousStack(test);
//        for(int i : test){
//            System.out.println(i);
//        }

        int[] test2 = {5,3,8,3,2,5};
        test2 = findBuilding(test2);
        for(int i : test2){
            System.out.println(i);
        }
    }
}
